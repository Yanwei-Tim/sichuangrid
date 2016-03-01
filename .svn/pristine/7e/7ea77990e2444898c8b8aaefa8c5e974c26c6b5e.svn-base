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

import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.baseInfo.idleYouth.service.IdleYouthService;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.job.JobHelper;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("idleYouthTowJob")
public class IdleYouthTowJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);

	@Autowired
	private IdleYouthService idleYouthService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private PlatformMessageService platformMessageService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private final static int pageSize = GridProperties.POPULATIONJOB_PAGESIZE;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		emphasisIdleYouthJob();
	}

	private void emphasisIdleYouthJob() {
		long startTime = System.currentTimeMillis();
		logger.error("重点青少年job开始执行！");
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());

		try {
			Integer count = idleYouthService.countIdleYouthsByBirthdayForMark();
			int pages = count / pageSize;
			// 到期的重点青少年
			Set<Long> groups = new HashSet<Long>();

			List<IdleYouth> allIdleYouthList = new ArrayList<IdleYouth>();

			for (int page = 0; page <= pages; page++) {
				List<IdleYouth> idleYouthList = idleYouthService
						.findIdleYouthsByBirthdayForMark(0, pageSize);
				if (idleYouthList != null && idleYouthList.size() > 0) {

					List<Long> idleIdList = new ArrayList<Long>();

					for (IdleYouth idYouth : idleYouthList) {

						idleIdList.add(idYouth.getId());

						idYouth.setGender(propertyDictService
								.findPropertyDictByIds(
										new Long[] { idYouth.getGender()
												.getId() }).get(0));
						idYouth.setOrganization(organizationDubboService
								.getFullOrgById(idYouth.getOrganization()
										.getId()));
						// 把到期的重点青少年按照所属网格id分组
						groups.add(idYouth.getOrganization().getId());

					}
					allIdleYouthList.addAll(idleYouthList);

					idleYouthService.updateEmphasiseByIds(idleIdList,
							IsEmphasis.IsNotEmphasis);
				}
			}
			List<IdleYouth> list = new ArrayList<IdleYouth>();

			for (Long orgId : groups) {
				list.clear();
				for (IdleYouth idYouth : allIdleYouthList) {

					if (orgId.equals(idYouth.getOrganization().getId())) {

						list.add(idYouth);

					}
				}
				sendLogoutMessage(orgId, list);
			}

			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行重点青少年job！", true);
			// List<IdleYouth> idleYouthList = idleYouthService
			// .findIdleYouthsByBIRTHDAY();
			//
			// if (idleYouthList != null && idleYouthList.size() > 0) {
			//
			// Set<Long> groups = new HashSet<Long>();
			//
			// List<Long> idleIdList = new ArrayList<Long>();
			//
			// for (IdleYouth idYouth : idleYouthList) {
			//
			// idleIdList.add(idYouth.getId());
			//
			// idYouth.setGender(propertyDictService
			// .findPropertyDictByIds(
			// new Long[] { idYouth.getGender().getId() })
			// .get(0));
			// idYouth.setOrganization(organizationDubboService
			// .getFullOrgById(idYouth.getOrganization().getId()));
			// // 把到期的重点青少年按照所属网格id分组
			// groups.add(idYouth.getOrganization().getId());
			//
			// }
			//
			// List<IdleYouth> list = new ArrayList<IdleYouth>();
			//
			// for (Long orgId : groups) {
			// list.clear();
			// for (IdleYouth idYouth : idleYouthList) {
			//
			// if (orgId.equals(idYouth.getOrganization().getId())) {
			//
			// list.add(idYouth);
			//
			// }
			// }
			// sendLogoutMessage(orgId, list);
			// }
			//
			// idleYouthService.updateEmphasiseByIds(idleIdList,
			// IsEmphasis.IsNotEmphasis);
			// }
			// jobMonitorService.updateJobMonitor(id,
			// "花了" + (System.currentTimeMillis() - startTime)
			// + "执行重点青少年job！", true);

		} catch (Exception e) {

			jobMonitorService.updateJobMonitor(id,
					"重点青少年job执行错误:" + e.getMessage(), false);
		}
	}

	private void sendLogoutMessage(Long orgId, List<IdleYouth> list) {

		PlatformMessage pm = platformMessageFactory
				.createIdleYouthPlatformMessage(list);

		platformMessageService.sendPlatformMessageToOrg(orgId, pm);

	}

}
