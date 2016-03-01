package com.tianque.job;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Session;
import com.tianque.domain.property.OrganizationType;
import com.tianque.issue.domain.AdministrativeOrgTimeLimitStandard;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueStandardForFunOrg;
import com.tianque.issue.domain.IssueStep;
import com.tianque.issue.factory.IssueServiceFactory;
import com.tianque.issue.service.AdministrativeOrgTimeLimitStandardService;
import com.tianque.issue.service.IssueService;
import com.tianque.issue.service.IssueStandardForFunOrgService;
import com.tianque.issue.service.impl.DefaultIssueWorkFlowEngineImpl;
import com.tianque.issue.state.IssueOperate;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.state.exception.UnsupportOperationException;
import com.tianque.service.WorkCalendarService;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * @declaration : 事件超时自动督办job
 */
@Component("issueOvertimeHandlerJob")
public class IssueOvertimeHandlerJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(IssueOvertimeHandlerJob.class);
	@Autowired
	private IssueServiceFactory issueServiceFactory;
	@Autowired
	private DefaultIssueWorkFlowEngineImpl defaultIssueWorkFlowEngine;
	@Autowired
	private WorkCalendarService workCalendarService;
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private IssueStandardForFunOrgService standardForFunOrgService;
	@Autowired
	private AdministrativeOrgTimeLimitStandardService administrativeOrgTimeLimitStandardService;

	private final static String SYS_SERIES = IssueServiceFactory.DEFAULT_SERIES;
	private final int[] issueeStetes = new int[] { IssueState.UNCONCEPTED_CODE,
			IssueState.DEALING_CODE, IssueState.NEWISSUE_CODE };

	// IssueState.ISSUEVERIFICATION_CODE };

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		autoIsueSupervise();
	}

	private void autoIsueSupervise() {
		long startTime = System.currentTimeMillis();
		logger.error("事件处理自动督办执行开始！");
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			List<IssueStep> list = defaultIssueWorkFlowEngine
					.findIssueStepListByIssueState(issueeStetes);
			if (list == null || list.size() <= 0) {
				return;
			}
			Map<String, Integer> workDay = new HashMap<String, Integer>();
			for (IssueStep step : list) {
				try {
					switch (step.getStateCode()) {
					case IssueState.UNCONCEPTED_CODE:
						workDay = getSuperviseAcceptedLimitTime(step);
						break;
					case IssueState.NEWISSUE_CODE:
						;
					case IssueState.DEALING_CODE:
						workDay = getSuperviseHandleLimitTime(step);
						break;
					// case IssueState.ISSUEVERIFICATION_CODE:
					// workDay = getSuperviseVerificationLimitTime(step);
					// break;
					default:
						break;
					}
					autoSupervise(step, workDay);
				} catch (Exception e) {
					if (e instanceof UnsupportOperationException) {
						logger.error("本步骤已经不能被督办,job执行时该事件已被办理：", e);
					} else {
						logger.error("督办错误:", e);
					}
				}

			}
			logger.error("事件处理自动督办执行结束！");
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "事件处理自动督办job", true);
		} catch (Exception e) {
			logger.error("事件处理自动督办job出现异常：", e);
			jobMonitorService.updateJobMonitor(id,
					"事件处理自动督办job出现异常：" + e.getMessage(), false);
		}
	}

	private boolean isAdministrativeOrg(Long orgId) {
		Organization org = organizationDubboService.getFullOrgById(orgId);
		PropertyDict orgType = propertyDictService.getPropertyDictById(org
				.getOrgType().getId());
		return orgType.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION;
	}

	/**
	 * 获取未受理超期时限
	 * 
	 * @param step
	 */
	private Map<String, Integer> getSuperviseAcceptedLimitTime(IssueStep step) {
		Map<String, Integer> workDay = new HashMap<String, Integer>();
		if (isAdministrativeOrg(step.getTarget().getId())) {
			AdministrativeOrgTimeLimitStandard limitStandard = getAdministrativeOrgTimeLimitStandard(step);
			workDay.put("redWorkDay", limitStandard.getRedLimitAccept());
			workDay.put("yellowWorkDay", limitStandard.getYellowLimitAccept());
		} else {
			IssueStandardForFunOrg standardForFunOrg = getStandardForFunOrg(step);
			workDay.put("redWorkDay", standardForFunOrg.getRedLimitAccept());
			workDay.put("yellowWorkDay",
					standardForFunOrg.getYellowLimitAccept());
		}
		return workDay;
	}

	private void autoSupervise(IssueStep step, Map<String, Integer> workDay) {
		Integer redWorkDay = workDay.get("redWorkDay");
		Integer yellowWorkDay = workDay.get("yellowWorkDay");
		if (isOverTime(step, redWorkDay)) {
			if (step.getSuperviseLevel() == IssueState.RED_CARD_SUPERVISE) {
				return;// 事件已经是红牌的不再红牌督办
			}
			getActualIssueServiceInstance().supervise(
					IssueOperate.RED_SUPERVISE, step.getId(),
					createIssueLogNew("红牌"));
		} else if (isOverTime(step, yellowWorkDay)) {
			if (step.getSuperviseLevel() == IssueState.YELLOW_CARD_SUPERVISE) {
				return;// 事件已经是黄牌的不再黄牌督办
			}
			getActualIssueServiceInstance().supervise(
					IssueOperate.YELLOW_SUPERVISE, step.getId(),
					createIssueLogNew("黄牌"));
		}
	}

	/**
	 * 获取验证时限
	 * 
	 * @param step
	 */
	private Map<String, Integer> getSuperviseVerificationLimitTime(
			IssueStep step) {
		Map<String, Integer> workDay = new HashMap<String, Integer>();
		if (isAdministrativeOrg(step.getTarget().getId())) {
			AdministrativeOrgTimeLimitStandard limitStandard = getAdministrativeOrgTimeLimitStandard(step);
			workDay.put("redWorkDay", limitStandard.getRedLimitVerify());
			workDay.put("yellowWorkDay", limitStandard.getYellowLimitVerify());
		} else {
			IssueStandardForFunOrg standardForFunOrg = getStandardForFunOrg(step);
			workDay.put("redWorkDay", standardForFunOrg.getRedLimitVerify());
			workDay.put("yellowWorkDay",
					standardForFunOrg.getYellowLimitVerify());
		}
		return workDay;
	}

	/**
	 * 获取未处理超期时限
	 * 
	 * @param step
	 */
	private Map<String, Integer> getSuperviseHandleLimitTime(IssueStep step) {
		Map<String, Integer> workDay = new HashMap<String, Integer>();
		if (isAdministrativeOrg(step.getTarget().getId())) {
			AdministrativeOrgTimeLimitStandard limitStandard = getAdministrativeOrgTimeLimitStandard(step);
			workDay.put("redWorkDay", limitStandard.getRedLimitHandle());
			workDay.put("yellowWorkDay", limitStandard.getYellowLimitHandle());
		} else {
			IssueStandardForFunOrg standardForFunOrg = getStandardForFunOrg(step);
			workDay.put("redWorkDay", standardForFunOrg.getRedLimitHandle());
			workDay.put("yellowWorkDay",
					standardForFunOrg.getYellowLimitHandle());
		}
		return workDay;
	}

	private IssueLogNew createIssueLogNew(String cardType) {
		IssueLogNew log = new IssueLogNew();
		Session session = ThreadVariable.getSession();
		log.setDealOrg(session.getOrganization());
		log.setDealUserName(session.getUserName());
		log.setContent("未在规定的期限里处理，系统进行自动" + cardType + "督办！");
		log.setMobile("13999999999");
		return log;
	}

	private boolean isOverTime(IssueStep step, Integer workDays) {
		if (null == workDays) {
			return false;
		}
		Date date = new Date();
		Calendar calendar = CalendarUtil.convertToCalendar(step
				.getLastDealDate());
		Long cityOrgId = organizationDubboService.getCityOrganizationId(
				step.getTarget().getId()).getId();
		if (workCalendarService.checkHasFeatureCalendar(
				Long.valueOf(calendar.get(Calendar.YEAR)), cityOrgId)) {
			// 如果有特色日历，掉用特色日历的时限标准
			return date.after(workCalendarService.getFutureDateByCityOrgId(
					step.getLastDealDate(), step.getDelayWorkday() + workDays
							- 1, cityOrgId));
		} else {

			return date.after(workCalendarService.getFutureDate(
					step.getLastDealDate(), step.getDelayWorkday() + workDays
							- 1));
		}

	}

	private IssueService getActualIssueServiceInstance() {
		return issueServiceFactory.getIssueServiceBySeries(getSeries());
	}

	protected String getSeries() {
		return SYS_SERIES;
	}

	private IssueStandardForFunOrg getStandardForFunOrg(IssueStep step) {
		IssueStandardForFunOrg standardForFunOrg = null;
		if (step.getItemTypeId() == null) {
			standardForFunOrg = standardForFunOrgService
					.getIssueStandardForFunOrgByOrgIdAndItemId(null, null);
		} else {
			standardForFunOrg = standardForFunOrgService
					.getIssueStandardForFunOrgByOrgIdAndItemId(step.getTarget()
							.getId(), step.getItemTypeId());
		}
		return standardForFunOrg;
	}

	private AdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandard(
			IssueStep step) {
		Organization organization = organizationDubboService
				.getFullOrgById(step.getTarget().getId());
		PropertyDict orgLevel = propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId());
		AdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard = new AdministrativeOrgTimeLimitStandard();
		if (organization.getParentOrg() != null
				&& organization.getParentOrg().getId() != null) {
			administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardService
					.getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId(
							orgLevel.getId(), step.getIssue().getIssueType()
									.getId(), organization.getParentOrg());

		}
		return administrativeOrgTimeLimitStandard;
	}
}
