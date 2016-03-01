/*package com.tianque.job.optmize;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taobao.pamirs.schedule.TaskItemDefine;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.User;
import com.tianque.job.JobHelper;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.plugin.tbSchedule.IScheduleTaskDealSingleBase;
import com.tianque.plugin.tbSchedule.constant.ScheduleNameConstant;
import com.tianque.userAuth.api.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component
public class EndFloatingPopulationJob extends
		IScheduleTaskDealSingleBase<Exception> {
	@Autowired
	private FloatingPopulationService floatingPopulationService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private PlatformMessageService platformMessageService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private GlobalSettingService globalSettingService;

	User user;
	List<FloatingPopulation> successList = new ArrayList<FloatingPopulation>();
	Set<Long> groups = new HashSet<Long>();

	@Override
	public Comparator<Exception> getComparator() {
		return null;
	}

	@Override
	public List<Exception> queryTasks(String taskParameter, String ownSign,
			int taskItemNum, List<TaskItemDefine> queryCondition, int fetchNum)
			throws Exception {
		List<Long> idModList = new ArrayList<Long>();
		for (int i = 0; i < queryCondition.size(); i++) {
			String taskItemId = ((TaskItemDefine) queryCondition.get(i))
					.getTaskItemId();
			if (taskItemId != null && !"".equals(taskItemId.trim())) {
				idModList.add(Long.parseLong(taskItemId.trim()));
			}
		}
		List<FloatingPopulation> floatingPopulationList = floatingPopulationService
				.queryFloatingPopulationsWhenEndOptmize(idModList, fetchNum,
						taskParameter);
		user = permissionService.findUserByUserName("admin");
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(user);
		successList.clear();
		groups.clear();
		List<Exception> exceptions = new ArrayList<Exception>();
		List<Long> ids = new ArrayList<Long>();
		for (FloatingPopulation population : floatingPopulationList) {
			if (!GlobalSetting.NOT_DEPENDENT
					.equals(globalSettingService
							.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
				try {
					floatingPopulationService.updateIsRemindByid(population
							.getId());
					population
							.setGender(propertyDictService
									.getPropertyDictById(population.getGender()
											.getId()));
					population.setOrganization(organizationDubboService
							.getFullOrgById(population.getOrganization()
									.getId()));
					population.setActualtype("流动人口");
					// 把到期的流动人口按照所属网格id分组
					groups.add(population.getOrganization().getId());
					successList.add(population);
					ids.add(population.getId());
				} catch (Exception e) {
					exceptions.add(e);
				}
			}
		}

		try {
			floatingPopulationService.batchUpdateIsRemindByid(ids);
			size.set(ids.size());
		} catch (Exception e) {
			exceptions.add(e);
		}

		if (!floatingPopulationList.isEmpty() && exceptions.isEmpty()) {
			exceptions.add(null);
		}

		return exceptions;
	}

	@Override
	public boolean execute(Exception exception, String ownSign)
			throws Exception {
		if (exception != null) {
			throw exception;
		}
		return true;
	}

	private void sendEndFloatingPopulation(Long orgId,
			List<ActualPopulation> list) {
		PlatformMessage pm = platformMessageFactory
				.createEndFloatingPopulationPlatformMessage(list, "流动人口");
		platformMessageService.sendPlatformMessageToOrg(orgId, pm);
	}

	@Override
	public void callBack() {
		List<ActualPopulation> dataList = new ArrayList<ActualPopulation>();
		for (Long orgId : groups) {
			dataList.clear();
			for (FloatingPopulation population : successList) {
				if (orgId.equals(population.getOrganization().getId())) {
					dataList.add(population);
				}
			}
			sendEndFloatingPopulation(orgId, dataList);
		}
	}

	@Override
	public String getJobRemark() {
		return ScheduleNameConstant.FLOATING_POPULATION_END_JOB_REMARK;
	}

}
*/