package com.tianque.job.optmize;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taobao.pamirs.schedule.TaskItemDefine;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.baseInfo.nurturesWomen.service.NurturesWomenService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.User;
import com.tianque.job.JobHelper;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.plugin.tbSchedule.IScheduleTaskDealSingleBase;
import com.tianque.plugin.tbSchedule.constant.ScheduleNameConstant;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.PropertyUtil;

@Component
public class HouseHoldNurturesWomenJob extends
		IScheduleTaskDealSingleBase<HouseholdStaff> {
	private final static Logger LOG = LoggerFactory
			.getLogger(HouseHoldNurturesWomenJob.class);
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private HousePopulationMaintanceService housePopulationMaintanceService;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private PlatformMessageService platformMessageService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private NurturesWomenService nurturesWomenService;
	@Autowired
	private HouseholdStaffService householdStaffService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private ShardConversion shardConversion;

	User user;
	Set<Long> groups = new HashSet<Long>();
	List<HouseholdStaff> successList = new ArrayList<HouseholdStaff>();

	@Override
	public Comparator<HouseholdStaff> getComparator() {
		return new Comparator<HouseholdStaff>() {
			@Override
			public int compare(HouseholdStaff o1, HouseholdStaff o2) {
				return o1.getId().compareTo(o2.getId());
			}
		};
	}

	@Override
	public List<HouseholdStaff> queryTasks(String taskParameter,
			String ownSign, int taskItemNum, List<TaskItemDefine> taskItemList,
			int fetchNum) throws Exception {
		List<String> all = shardConversion.getAllShardCode();

		List<String> excuteShard = OptimizeJobUtils.getExcuteShard(
				taskParameter, taskItemList, all);

		List<HouseholdStaff> data = householdStaffService
				.findhouseholdStaffWhenIsNurturesWomenOptmize(excuteShard,
						fetchNum);

		String cardNo;
		for (int i = 0; i < data.size(); i++) {
			HouseholdStaff population = data.get(i);
			cardNo = population.getIdCardNo();
			if (!IdCardValidator.validate(cardNo)) {
				data.remove(i--);
				scheduleTaskDealService.addIdCardNoExpLog(
						population.getIdCardNo(), "身份证号码不合法");
			} else if (cardNo.charAt(cardNo.length() - 2) % 2 != 0) {
				data.remove(i--);
				LOG.warn(cardNo + "不是女性的身份证号码");
			}
		}

		user = permissionService.findUserByUserName("admin");
		successList.clear();
		groups.clear();
		return data;
	}

	@Override
	public boolean execute(HouseholdStaff population, String arg1)
			throws Exception {
		ExcelImportHelper.isImport.set(true);
		if (!GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			JobHelper.createMockAdminSession();
			ThreadVariable.setUser(user);
			try {
				NurturesWomen nurturesWomen = nurturesWomenService
						.getNurturesWomenByIdCardNoAndOrganizationId(population
								.getIdCardNo(), population.getOrganization()
								.getId());
				if (nurturesWomen != null) {
					return true;
				}
				population.setHouseId(housePopulationMaintanceService
						.getPopulationLivingHouseId(PopulationCatalog
								.parse(BaseInfoTables.HOUSEHOLDSTAFF_KEY),
								population.getId()));
				nurturesWomenService
						.addNurturesWomenBaseInfo(convertHouseholdStaffNurturesWomen(population));
			} catch (Exception e) {
				throw new Exception("该身份证[" + population.getIdCardNo() + "]在执行"
						+ getJobRemark() + "发生异常", e);
			}

			population.setGender(propertyDictService
					.getPropertyDictById(population.getGender().getId()));
			population.setOrganization(organizationDubboService
					.getFullOrgById(population.getOrganization().getId()));
			population.setActualtype("户籍人口");

			// 把变为育龄妇女按照所属网格id分组
			groups.add(population.getOrganization().getId());
			successList.add(population);
		}
		return true;
	}

	private NurturesWomen convertHouseholdStaffNurturesWomen(
			HouseholdStaff population) throws Exception {
		NurturesWomen nurturesWomen = new NurturesWomen();
		PropertyUtil.copyPropertiesWithRecursion(Countrymen.class,
				nurturesWomen, population, new String[] { "id" });
		nurturesWomen.setSourcesState(ConstantsProduct.SourcesState.JOB);
		return nurturesWomen;
	}

	private void sendMessageHouseholdStaff(Long orgId,
			List<ActualPopulation> list) {
		PlatformMessage pm = platformMessageFactory
				.createNurturesWomenformMessage(list, "户籍人口");
		platformMessageService.sendPlatformMessageToOrg(orgId, pm);
	}

	@Override
	public void callBack() {
		List<ActualPopulation> dataList = new ArrayList<ActualPopulation>();
		for (Long orgId : groups) {
			dataList.clear();
			for (HouseholdStaff population : successList) {
				if (orgId.equals(population.getOrganization().getId())) {
					dataList.add(population);
				}
			}
			sendMessageHouseholdStaff(orgId, dataList);
		}
	}

	@Override
	public String getJobRemark() {
		return ScheduleNameConstant.HOUSEHOLD_NURTURESWOMEN_JOB_REMARK;
	}

}
