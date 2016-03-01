package com.tianque.job.optmize;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taobao.pamirs.schedule.TaskItemDefine;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.baseInfo.elderlyPeople.service.ElderlyPeopleService;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
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
public class HouseholdElderlyJob extends
		IScheduleTaskDealSingleBase<HouseholdStaff> {
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private HouseholdStaffService householdStaffService;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ElderlyPeopleService elderlyPeopleService;

	@Autowired
	private HousePopulationMaintanceService housePopulationMaintanceService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private PlatformMessageService platformMessageService;
	@Autowired
	private ShardConversion shardConversion;

	User user;
	Set<Long> groups = new HashSet<Long>();
	List<HouseholdStaff> successList = new ArrayList<HouseholdStaff>();

	private ElderlyPeople convertFloatingPopulationToElderlyPeople(
			HouseholdStaff population) throws Exception {
		ElderlyPeople elderlyPeople = new ElderlyPeople();
		PropertyUtil.copyPropertiesWithRecursion(Countrymen.class,
				elderlyPeople, population, new String[] { "id" });
		elderlyPeople.setSourcesState(ConstantsProduct.SourcesState.JOB);
		return elderlyPeople;
	}

	private void sendMessage(Long orgId, List<ActualPopulation> list) {
		PlatformMessage pm = platformMessageFactory
				.createElderlyPeoplePlatformMessage(list, "户籍人口");
		platformMessageService.sendPlatformMessageToOrg(orgId, pm);
	}

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
			String ownSign, int taskItemNum,
			List<TaskItemDefine> queryCondition, int fetchNum) throws Exception {
		List<String> all = shardConversion.getAllShardCode();

		List<String> excuteShard = OptimizeJobUtils.getExcuteShard(
				taskParameter, queryCondition, all);

		List<HouseholdStaff> data = householdStaffService
				.findhouseholdStaffWhenIsOldOptmize(excuteShard, fetchNum);

		for (int i = 0; i < data.size(); i++) {
			HouseholdStaff population = data.get(i);
			if (!IdCardValidator.validate(population.getIdCardNo())) {
				data.remove(i--);
				scheduleTaskDealService.addIdCardNoExpLog(
						population.getIdCardNo(), "身份证号码不合法");
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
				population.setHouseId(housePopulationMaintanceService
						.getPopulationLivingHouseId(PopulationCatalog
								.parse(BaseInfoTables.HOUSEHOLDSTAFF_KEY),
								population.getId()));
				elderlyPeopleService
						.addElderlyPeopleBaseInfo(convertFloatingPopulationToElderlyPeople(population));
			} catch (Exception e) {
				throw new Exception("该身份证[" + population.getIdCardNo() + "]在执行"
						+ getJobRemark() + "发生异常", e);
			}

			population.setGender(propertyDictService
					.getPropertyDictById(population.getGender().getId()));
			population.setOrganization(organizationDubboService
					.getFullOrgById(population.getOrganization().getId()));
			population.setActualtype("户籍人口");

			// 把变为老年人按照所属网格id分组
			groups.add(population.getOrganization().getId());
			successList.add(population);

		}
		return true;
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
			sendMessage(orgId, dataList);
		}
	}

	@Override
	public String getJobRemark() {
		return ScheduleNameConstant.HOUSEHOLD_POPULATION_CHANGE_ELDERLY_PEOPLE_JOB_REMARK;
	}
}
