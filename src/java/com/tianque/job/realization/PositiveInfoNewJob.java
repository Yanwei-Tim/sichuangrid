package com.tianque.job.realization;

import java.text.ParseException;
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

import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.positiveInfo.service.PositiveInfoService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.job.JobHelper;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.service.util.PopulationType;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("positiveInfoNewJob")
public class PositiveInfoNewJob implements Job {

	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private PositiveInfoService positiveInfoService;
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
		emphasisPositiveInfoJob();
	}

	private void emphasisPositiveInfoJob() {
		long startTime = System.currentTimeMillis();
		logger.error("刑释人员自动取消关注job开始执行！");
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());

		try {
			// 刑释人员
			List<PositiveInfo> releasedList = positiveInfoService
					.findPositiveInfoByDate("刑释人员", 60L);
			// 解教人员
			List<PositiveInfo> programList = positiveInfoService
					.findPositiveInfoByDate("解教人员", 36L);

			logoutPositiveInfo(releasedList, "刑释", 5L);

			logoutPositiveInfo(programList, "解教", 3L);

			List<PositiveInfo> positiveInfoList = new ArrayList<PositiveInfo>();

			positiveInfoList.addAll(releasedList);
			positiveInfoList.addAll(programList);

			if (positiveInfoList.size() > 0) {

				Set<Long> groups = new HashSet<Long>();

				for (PositiveInfo positiveInfo : positiveInfoList) {

					positiveInfo.setGender(propertyDictService
							.findPropertyDictByIds(
									new Long[] { positiveInfo.getGender()
											.getId() }).get(0));
					positiveInfo.setPositiveInfoType(propertyDictService
							.findPropertyDictByIds(
									new Long[] { positiveInfo
											.getPositiveInfoType().getId() })
							.get(0));
					positiveInfo.setOrganization(organizationDubboService
							.getFullOrgById(positiveInfo.getOrganization()
									.getId()));

					// 把到期的刑释人员按照所属网格id分组
					groups.add(positiveInfo.getOrganization().getId());
				}
				List<PositiveInfo> dataList = new ArrayList<PositiveInfo>();

				for (Long orgId : groups) {
					dataList.clear();
					for (PositiveInfo p : positiveInfoList) {
						if (orgId.equals(p.getOrganization().getId())) {
							dataList.add(p);
						}
					}
					sendLogoutMessage(orgId, dataList);
				}
			}

			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行刑释人员自动取消关注job！", true);

		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"刑释人员自动取消关注job执行错误:" + e.getMessage(), false);
		}
	}

	private void logoutPositiveInfo(List<PositiveInfo> list, String type,
			Long time) throws ParseException {

		if (null != list && 0 < list.size()) {
			Long[] positiveInfoIds = new Long[list.size()];
			for (int i = 0; i < list.size(); i++) {
				positiveInfoIds[i] = list.get(i).getId();
			}
			LogoutDetail logoutDetail = new LogoutDetail();
			logoutDetail.setLogoutDate(CalendarUtil.today());
			logoutDetail.setLogout(IsEmphasis.IsNotEmphasis);
			logoutDetail.setLogoutReason(type + "满" + time + "年");

			positiveInfoService
					.updateLogOutDetailByPopulationTypeAndIds(logoutDetail,
							PopulationType.POSITIVE_INFO, positiveInfoIds);
		}
	}

	private void sendLogoutMessage(Long orgId, List<PositiveInfo> list) {

		PlatformMessage pm = platformMessageFactory
				.createPositiveInfoPlatformMessage(list);

		platformMessageService.sendPlatformMessageToOrg(orgId, pm);

	}

}
