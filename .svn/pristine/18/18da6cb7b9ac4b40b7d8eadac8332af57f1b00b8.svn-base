package com.tianque.fourTeams.fourTeamsIssue.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsAdministrativeOrgTimeLimitStandard;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStandardForFunOrg;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsAdministrativeOrgTimeLimitStandardService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueService;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueStandardForFunOrgService;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueState;
import com.tianque.fourTeams.fourTeamsIssue.vo.FourTeamsIssueViewObject;
import com.tianque.service.IssueTypeService;
import com.tianque.service.WorkCalendarService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("fourTeamsIssueOvertimeHelper")
public class FourTeamsIssueOvertimeHelper {
	private static Logger logger = LoggerFactory
			.getLogger(FourTeamsIssueOvertimeHelper.class);
	@Autowired
	private WorkCalendarService workCalendarService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private FourTeamsIssueStandardForFunOrgService standardForFunOrgService;
	@Autowired
	private FourTeamsAdministrativeOrgTimeLimitStandardService administrativeOrgTimeLimitStandardService;
	@Autowired
	@Qualifier("fourTeamsDefaultIssueService")
	private FourTeamsIssueService issueService;
	@Autowired
	private IssueTypeService issueTypeService;
	private final static Double DEFAULT_PERCENTAGE = 0.3D;

	public void fillEaringWarnField(FourTeamsIssueViewObject issueViewObject) {
		try {
			Map<String, Integer> workDay = new HashMap<String, Integer>();
			switch (issueViewObject.getDealState().intValue()) {
			case FourTeamsIssueState.UNCONCEPTED_CODE:
				workDay = getSuperviseAcceptedLimitTime(issueViewObject);
				break;
			case FourTeamsIssueState.NEWISSUE_CODE:
				;
			case FourTeamsIssueState.DEALING_CODE:
				workDay = getSuperviseHandleLimitTime(issueViewObject);
				break;
			default:
				break;
			}
			if (workDay.size() > 0) {
				autoFillEaringWarnField(issueViewObject, workDay);
			}
		} catch (Exception e) {
			logger.error("IssueOvertimeHelper出现异常：", e);
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
	private Map<String, Integer> getSuperviseAcceptedLimitTime(
			FourTeamsIssueViewObject issueViewObject) {
		Map<String, Integer> workDay = new HashMap<String, Integer>();
		if (isAdministrativeOrg(issueViewObject.getTargeOrg().getId())) {
			FourTeamsAdministrativeOrgTimeLimitStandard limitStandard = getAdministrativeOrgTimeLimitStandard(issueViewObject);
			workDay.put("redWorkDay", limitStandard.getRedLimitAccept());
			workDay.put("yellowWorkDay", limitStandard.getYellowLimitAccept());
		} else {
			FourTeamsIssueStandardForFunOrg standardForFunOrg = getStandardForFunOrg(issueViewObject);
			workDay.put("redWorkDay", standardForFunOrg.getRedLimitAccept());
			workDay.put("yellowWorkDay",
					standardForFunOrg.getYellowLimitAccept());
		}
		return workDay;
	}

	private void autoFillEaringWarnField(
			FourTeamsIssueViewObject issueViewObject,
			Map<String, Integer> workDay) throws ParseException {
		Integer redWorkDay = workDay.get("redWorkDay");
		Integer yellowWorkDay = workDay.get("yellowWorkDay");
		if (issueViewObject.getSupervisionState() != FourTeamsIssueState.YELLOW_CARD_SUPERVISE
				&& issueViewObject.getSupervisionState() != FourTeamsIssueState.RED_CARD_SUPERVISE) {
			issueViewObject.setEaringWarn(intervalTime(issueViewObject,
					yellowWorkDay));
		} else if (issueViewObject.getSupervisionState() == FourTeamsIssueState.YELLOW_CARD_SUPERVISE
				&& issueViewObject.getSupervisionState() != FourTeamsIssueState.RED_CARD_SUPERVISE) {
			issueViewObject.setEaringWarn(intervalTime(issueViewObject,
					redWorkDay));
		}
	}

	/**
	 * 获取未处理超期时限
	 * 
	 * @param step
	 */
	private Map<String, Integer> getSuperviseHandleLimitTime(
			FourTeamsIssueViewObject issueViewObject) {
		Map<String, Integer> workDay = new HashMap<String, Integer>();
		if (isAdministrativeOrg(issueViewObject.getTargeOrg().getId())) {
			FourTeamsAdministrativeOrgTimeLimitStandard limitStandard = getAdministrativeOrgTimeLimitStandard(issueViewObject);
			workDay.put("redWorkDay", limitStandard.getRedLimitHandle());
			workDay.put("yellowWorkDay", limitStandard.getYellowLimitHandle());
		} else {
			FourTeamsIssueStandardForFunOrg standardForFunOrg = getStandardForFunOrg(issueViewObject);
			workDay.put("redWorkDay", standardForFunOrg.getRedLimitHandle());
			workDay.put("yellowWorkDay",
					standardForFunOrg.getYellowLimitHandle());
		}
		return workDay;
	}

	private int intervalTime(FourTeamsIssueViewObject issueViewObject,
			Integer workDays) throws ParseException {
		if (null == workDays) {
			return 0;
		}
		FourTeamsIssueStep step = issueService.getIssueStepById(issueViewObject
				.getIssueStepId());
		int delayDays = issueService
				.getIssueStepDelayWorkDaysById(issueViewObject.getIssueStepId()); // 申请延迟的工作天数
		// int usedWorkDays = workCalendarService
		// .getWorkDaysFromStartTimeToEndTime(step.getEntryDate(),
		// new Date()).intValue(); // 计算用掉的工作天数
		int usedWorkDays = workCalendarService
				.getWorkDaysFromStartTimeToEndTimeByFeature(
						step.getEntryDate(), new Date(),
						issueViewObject.getTargeOrg().getId()).intValue(); // 调用特色日历计算用掉的工作天数
		return warm(workDays, delayDays, usedWorkDays);
	}

	private int warm(int workDays, int delayDays, int usedWorkDays) {
		int warmDays = (int) Math.floor((workDays + delayDays)
				* DEFAULT_PERCENTAGE); // 警告的工作天数
		if (usedWorkDays - warmDays >= 0) {
			int remaining = workDays + delayDays - usedWorkDays;
			return remaining > 0 ? remaining : 0;
		} else {
			return 0;
		}
	}

	private FourTeamsIssueStandardForFunOrg getStandardForFunOrg(
			FourTeamsIssueViewObject issueViewObject) {
		FourTeamsIssueStandardForFunOrg standardForFunOrg = null;
		FourTeamsIssueStep step = issueService.getIssueStepById(issueViewObject
				.getIssueStepId());
		if (step.getItemTypeId() == null) {
			standardForFunOrg = standardForFunOrgService
					.getIssueStandardForFunOrgByOrgIdAndItemId(null, null);
		} else {
			standardForFunOrg = standardForFunOrgService
					.getIssueStandardForFunOrgByOrgIdAndItemId(issueViewObject
							.getTargeOrg().getId(), step.getItemTypeId());
		}
		return standardForFunOrg;
	}

	private FourTeamsAdministrativeOrgTimeLimitStandard getAdministrativeOrgTimeLimitStandard(
			FourTeamsIssueViewObject issueViewObject) {

		Organization organization = organizationDubboService
				.getFullOrgById(issueViewObject.getTargeOrg().getId());
		PropertyDict orgLevel = propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId());
		FourTeamsAdministrativeOrgTimeLimitStandard administrativeOrgTimeLimitStandard = administrativeOrgTimeLimitStandardService
				.getAdminOrgTimeLimitStandardByOrgLevelIdAndIssueTypeId(
						orgLevel.getId(), issueViewObject.getIssueType()
								.getId(), organization.getParentOrg().getId());
		return administrativeOrgTimeLimitStandard;
	}
}
