package com.tianque.job.realization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.baseInfo.rectificativePerson.service.RectificativePersonService;
import com.tianque.core.util.ThreadVariable;
import com.tianque.job.JobHelper;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("rectificativePersonJob")
public class RectificativePersonJob implements Job {

	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private RectificativePersonService rectificativePersonService;
	@Autowired
	private PlatformMessageService platformMessageService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		emphasisRectificativePersonJob();
	}

	private void emphasisRectificativePersonJob() {
		long startTime = System.currentTimeMillis();
		logger.error("社区矫正人员-矫正日到期job开始执行！");
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService
				.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());

		try {
			// 到期的社区矫正人员集合
			List<RectificativePerson> rectificativePersonList = rectificativePersonService
					.remindByRectifyDate();
			if (rectificativePersonList != null
					&& rectificativePersonList.size() > 0) {
				// 到期的社区矫正人员的id
				Long[] rectificativeIds = new Long[rectificativePersonList
						.size()];

				RectificativePerson rectificativePerson = new RectificativePerson();

				Set<Long> groups = new HashSet<Long>();

				for (int i = 0; i < rectificativePersonList.size(); i++) {

					rectificativePerson = rectificativePersonList.get(i);

					rectificativeIds[i] = rectificativePerson.getId();

					rectificativePerson.setGender(propertyDictService
							.findPropertyDictByIds(
									new Long[] { rectificativePerson
											.getGender().getId() }).get(0));
					rectificativePerson
							.setExecuteType(propertyDictService
									.findPropertyDictByIds(
											new Long[] { rectificativePerson
													.getExecuteType().getId() })
									.get(0));
					rectificativePerson
							.setOrganization(organizationDubboService
									.getFullOrgById(rectificativePerson
											.getOrganization().getId()));
					// 把到期的社区矫正人员按照所属网格id分组
					groups.add(rectificativePerson.getOrganization().getId());
					rectificativePersonService
							.updateIsRemindByid(rectificativeIds[i]);
				}

				List<RectificativePerson> dataList = new ArrayList<RectificativePerson>();

				for (Long orgId : groups) {
					dataList.clear();
					for (RectificativePerson p : rectificativePersonList) {
						if (orgId.equals(p.getOrganization().getId())) {
							dataList.add(p);
						}
					}
					sendPlatformMessageToOrg(orgId, dataList);
				}
			}
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行社区矫正人员-矫正日到期job！", true);

		} catch (Exception e) {
			e.printStackTrace();
			jobMonitorService.updateJobMonitor(id,
					"社区矫正人员-矫正日到期job执行错误:" + e.getMessage(), false);
		}

	}

	public void sendPlatformMessageToOrg(Long orgId,
			List<RectificativePerson> list) {

		PlatformMessage pm = platformMessageFactory
				.createRectificativePersonPlatformMessage(list);

		platformMessageService.sendPlatformMessageToOrg(orgId, pm);
	}

}
