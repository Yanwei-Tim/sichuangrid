package com.tianque.report.builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.util.CalendarUtil;
import com.tianque.dao.IssueLogDaoNew;
import com.tianque.dao.IssueNewDao;
import com.tianque.domain.IssueDealStat;
import com.tianque.domain.IssueMonthEndDealingHistory;
import com.tianque.domain.Organization;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.service.WorkCalendarService;
import com.tianque.state.IssueDealState;
import com.tianque.state.IssueDealType;
import com.tianque.state.OverTime;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.IssueLogDealOverTimeHelper;

public class IssueDealStatRealTimeBuilder {
	public static IssueDealStat createEmptySummaryOfDealStatInfo(Organization org) {
		IssueDealStat stat = new IssueDealStat();
		stat.setOrg(org);
		stat.setOrgLevel(org.getOrgLevel());
		stat.setOrgType(org.getOrgType());
		stat.setOrgInternalCode(org.getOrgInternalCode());
		stat.setMonthAddCount(0);
		stat.setMonthNomalCompletedCount(0);
		stat.setMonthNomalDealingCount(0);
		stat.setMonthOvertimeCompletedCount(0);
		stat.setMonthOvertimeDealingCount(0);
		stat.setTotalAddCount(0);
		stat.setTotalCompletedCount(0);
		stat.setYearAddCount(0);
		stat.setYearCompletedCount(0);
		stat.setMonthComplededAddCount(0);
		stat.setYearComplededAddCount(0);
		stat.setTotalComplededAddCount(0);
		return stat;
	}

	private static final String DEFAULT_FORMAT = "yyyy-MM-dd";
	private List<IssueDealStat> baseMonthData = new ArrayList<IssueDealStat>();
	private Organization selectedOrganization = null;
	private List<Organization> cachedNeedBuildOrgs = new ArrayList<Organization>();
	private Map<Long, IssueMonthEndDealingHistory> cachedLastMonthDealingHistory = new HashMap<Long, IssueMonthEndDealingHistory>();
	private WorkCalendarService workCalendarService;
	private OrganizationDubboService orgService;
	private Date today;
	private Date statStartDate;
	private IssueNewDao issueDao;
	private Date yearStart;
	private IssueLogDaoNew issueLogDao;
	private Map<Long, Organization> cachedIssueLogOrgs = new HashMap<Long, Organization>();

	public IssueDealStatRealTimeBuilder(OrganizationDubboService orgService,
			WorkCalendarService workCalendarService, IssueLogDaoNew issueLogDao,
			IssueNewDao issueDao, Date startDate) {
		this.orgService = orgService;
		this.workCalendarService = workCalendarService;
		this.issueLogDao = issueLogDao;
		this.issueDao = issueDao;
		today = CalendarUtil.now("yyyy-MM-dd");
		yearStart = CalendarUtil.parseDate(CalendarUtil.getNowYear() + "-01-01", DEFAULT_FORMAT);
		statStartDate = startDate;
	}

	public void setSelectedOrganization(Organization organization) {
		selectedOrganization = organization;
	}

	public void setNeedBuildedOrganization(List<Organization> needBuildOrgs) {
		cachedNeedBuildOrgs.clear();
		cachedNeedBuildOrgs.addAll(needBuildOrgs);
	}

	public void setLastMonthStatDatas(List<IssueDealStat> lastMonthStats) {
		baseMonthData.clear();
		for (IssueDealStat stat : lastMonthStats) {
			if (statStartDate.after(stat.getStatEndTime())) {
				stat.setMonthAddCount(0);
				stat.setMonthNomalCompletedCount(0);
				stat.setMonthNomalDealingCount(0);
				stat.setMonthOvertimeCompletedCount(0);
				stat.setMonthOvertimeDealingCount(0);
				stat.setMonthComplededAddCount(0);
			}
			if (CalendarUtil.getMonth(stat.getStatStartTime()) == (Calendar.DECEMBER + 1)) {
				stat.setYearAddCount(0);
				stat.setYearCompletedCount(0);
				stat.setYearComplededAddCount(0);
			}
			baseMonthData.add(stat);
		}
	}

	public void setLastMonthDealingHistory(List<IssueMonthEndDealingHistory> histories) {
		for (IssueMonthEndDealingHistory record : histories) {
			cachedLastMonthDealingHistory.put(record.getIssueLogId(), record);
		}
	}

	public void addIssueLog(IssueLogNew log) {
		if (cachedLastMonthDealingHistory.containsKey(log.getId())) {
			cachedLastMonthDealingHistory.remove(log.getId());
		}
		if (isUnConcepteLog(log) || isUnReadLog(log)) {
			processUnConceptLog(log);
		} else if (isDealingLog(log)) {
			processDealingLog(log);
		} else if (isAddLog(log)) {
			processAddLog(log);
		} else if (isConceptedLog(log)) {
			processConceptedLog(log);
		} else if (isTellLog(log)) {
			processTellLog(log);
		} else if (isStepCompletedLog(log)) {
			processStepCompletedLog(log);
		} else if (isIssueCompletedLog(log)) {
			processIssueCompletedLog(log);
		}
	}

	public List<IssueDealStat> getIssueDealStats() {
		processAlwaysUndoingIssueLog();
		for (Organization org : cachedNeedBuildOrgs) {
			lookupIssueDealStat(org);
		}
		return baseMonthData;
	}

	// 上月底仍然在处理中的，本月没有做过任何操作的
	private void processAlwaysUndoingIssueLog() {
		for (IssueMonthEndDealingHistory record : cachedLastMonthDealingHistory.values()) {
			IssueLogNew log = issueLogDao.getIssueLogById(record.getIssueLogId());
			if (log == null) {
				// 表明服务办事已经删除,并不合理，因为删除上月服务办事会导致上月统计数据不准确
			} else {
				processDealingLog(log);
			}
		}
	}

	private void processUnConceptLog(IssueLogNew log) {
		// 针对新增立即受理之前的数据
		if (isAddLog(log)) {
			processAddLog(log);
		}
		IssueDealStat statData = lookupIssueDealStat(log.getTargeOrg());
		if (statData == null)
			return;
		if (isOvertimeUnConceptLog(log)) {
			statData.setMonthOvertimeDealingCount(statData.getMonthOvertimeDealingCount() + 1);
		} else {
			statData.setMonthNomalDealingCount(statData.getMonthNomalDealingCount() + 1);
		}
	}

	private void processDealingLog(IssueLogNew log) {
		IssueDealStat statData = lookupIssueDealStat(log.getTargeOrg());
		if (statData == null)
			return;
		if (isOvertimeDealingLog(log)) {
			statData.setMonthOvertimeDealingCount(statData.getMonthOvertimeDealingCount() + 1);
		} else {
			statData.setMonthNomalDealingCount(statData.getMonthNomalDealingCount() + 1);
		}
	}

	private void processAddLog(IssueLogNew log) {
		if (!statStartDate.after(log.getDealTime())) {
			IssueDealStat statData = lookupIssueDealStat(log.getDealOrg());
			if (statData == null)
				return;
			statData.setMonthAddCount(statData.getMonthAddCount() + 1);
			statData.setYearAddCount(statData.getYearAddCount() + 1);
			statData.setTotalAddCount(statData.getTotalAddCount() + 1);
		}
	}

	private void processConceptedLog(IssueLogNew log) {
	}

	private void processTellLog(IssueLogNew log) {
	}

	private void processStepCompletedLog(IssueLogNew log) {
	}

	private void processIssueCompletedLog(IssueLogNew log) {
		IssueDealStat statData = lookupIssueDealStat(log.getTargeOrg());
		if (statData == null)
			return;
		statData.setYearCompletedCount(statData.getYearCompletedCount() + 1);
		statData.setTotalCompletedCount(statData.getTotalCompletedCount() + 1);
		if (isOvertimeCompletedLog(log)) {
			statData.setMonthOvertimeCompletedCount(statData.getMonthOvertimeCompletedCount() + 1);
		} else {
			statData.setMonthNomalCompletedCount(statData.getMonthNomalCompletedCount() + 1);
		}
		increateCompletedAddCount(log);
	}

	private void increateCompletedAddCount(IssueLogNew log) {
		IssueNew issue = issueDao.getSimpleIssueById(log.getIssue().getId());
		IssueDealStat statData = lookupIssueDealStat(issue.getCreateOrg());
		if (statData == null)
			return;
		if (yearStart.before(issue.getCreateDate())) {
			statData.setMonthComplededAddCount(statData.getMonthComplededAddCount() + 1);
			statData.setYearComplededAddCount(statData.getYearComplededAddCount() + 1);
		}
		statData.setTotalComplededAddCount(statData.getTotalComplededAddCount() + 1);
	}

	private IssueDealStat lookupIssueDealStat(Organization org) {
		Organization appropriateParentOrg = selectedOrganization;
		if (selectedOrganization == null || !org.getId().equals(selectedOrganization.getId())) {
			appropriateParentOrg = getAppropriateOrg(getOrganizationInternalCode(org));
		}
		if (appropriateParentOrg != null) {
			for (IssueDealStat stat : baseMonthData) {
				if (stat.getOrg().getId().equals(appropriateParentOrg.getId())) {
					return stat;
				}
			}
			IssueDealStat result = createEmptySummaryOfDealStatInfo(appropriateParentOrg);
			baseMonthData.add(result);
			return result;
		}
		return null;
	}

	private String getOrganizationInternalCode(Organization org) {
		if (cachedIssueLogOrgs.containsKey(org.getId())) {
			return cachedIssueLogOrgs.get(org.getId()).getOrgInternalCode();
		} else {
			Organization resultOrg = orgService.getSimpleOrgById(org.getId());
			cachedIssueLogOrgs.put(org.getId(), resultOrg);
			return resultOrg.getOrgInternalCode();
		}
	}

	private Organization getAppropriateOrg(String orgInternalCode) {
		for (Organization org : cachedNeedBuildOrgs) {
			if (org == null
					|| (selectedOrganization != null && org.getId().equals(
							selectedOrganization.getId())))
				continue;
			if (orgInternalCode.startsWith(org.getOrgInternalCode())) {
				return org;
			}
		}
		return null;
	}

	private boolean isOvertimeUnConceptLog(IssueLogNew log) {
		return IssueLogDealOverTimeHelper.isOvertime(today, log.getDealTime(), workCalendarService,
				OverTime.YELLOW_CONCEPT);
	}

	private boolean isOvertimeDealingLog(IssueLogNew log) {
		return IssueLogDealOverTimeHelper.isOvertime(today, log.getDealTime(), workCalendarService,
				OverTime.YELLOW_DONE);
	}

	private boolean isOvertimeCompletedLog(IssueLogNew log) {
		return IssueLogDealOverTimeHelper.isOvertime(log.getDealTime(), log.getForLogEntryTime(),
				workCalendarService, OverTime.YELLOW_DONE);
	}

	// 未受理
	private boolean isUnConcepteLog(IssueLogNew log) {
		return IssueDealState.UN_DONE.equals(log.getDealState());
	}

	// 未阅读
	private boolean isUnReadLog(IssueLogNew log) {
		return IssueDealState.UN_READ.equals(log.getDealState());
	}

	// 处理中
	private boolean isDealingLog(IssueLogNew log) {
		return IssueDealState.DOING.equals(log.getDealState());
	}

	// 新增
	private boolean isAddLog(IssueLogNew log) {
		return log.getDealType().equals(IssueDealType.Add);
	}

	// 受理
	private boolean isConceptedLog(IssueLogNew log) {
		return log.getDealState() >= 1001L
				&& (log.getDealType().equals(IssueDealType.Add)
						|| log.getDealType().equals(IssueDealType.SUBMIT_FORWARD) || log
						.getDealType().equals(IssueDealType.ASSIGN));

	}

	// 抄告
	private boolean isTellLog(IssueLogNew log) {
		return log.getDealType().equals(IssueDealType.TELL);

	}

	// 本部门已经处理完成
	private boolean isStepCompletedLog(IssueLogNew log) {
		return log.getDealState().equals(IssueDealState.DONE)
				&& (log.getDealType().equals(IssueDealType.CONCEPT)
						|| log.getDealType().equals(IssueDealType.COMMENT) || log.getDealType()
						.equals(IssueDealType.SEND_BACK));
	}

	// 办结
	private boolean isIssueCompletedLog(IssueLogNew log) {
		return log.getDealState().equals(IssueDealState.COMPLETE);
	}

}
