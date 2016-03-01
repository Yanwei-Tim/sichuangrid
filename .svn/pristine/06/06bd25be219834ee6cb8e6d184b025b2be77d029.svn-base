package com.tianque.report.builder;

import java.util.ArrayList;
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
import com.tianque.util.IssueLogDealOverTimeHelper;

public class IssueDealStatBuilder {
	public static IssueDealStat createEmptyDealStatInfo(Organization org, int year, int month) {
		IssueDealStat stat = new IssueDealStat();
		stat.setOrg(org);
		stat.setOrgLevel(org.getOrgLevel());
		stat.setOrgType(org.getOrgType());
		stat.setOrgInternalCode(org.getOrgInternalCode());
		stat.setMonth(month);
		stat.setYear(year);
		stat.setMonthAddCount(0);
		stat.setMonthNomalCompletedCount(0);
		stat.setMonthNomalDealingCount(0);
		stat.setMonthOvertimeCompletedCount(0);
		stat.setMonthOvertimeDealingCount(0);
		stat.setStatEndTime(new Date().before(CalendarUtil.getNextMonthStart(year, month)) ? new Date()
				: CalendarUtil.getMonthEnd(year, month));
		stat.setStatStartTime(CalendarUtil.getMonthStart(year, month));
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
	private Map<Long, Organization> cachedNeedBuildOrgs = new HashMap<Long, Organization>();
	private Map<Long, Organization> cachedAllOrgs = new HashMap<Long, Organization>();
	private Map<Long, IssueDealStat> cachedLastMonthStats = new HashMap<Long, IssueDealStat>();
	private Map<Long, IssueDealStat> cachedMonthStats = new HashMap<Long, IssueDealStat>();
	private List<IssueLogNew> cachedMonthDealingLog = new ArrayList<IssueLogNew>();
	private Map<Long, IssueMonthEndDealingHistory> cachedLastMonthDealingHistory = new HashMap<Long, IssueMonthEndDealingHistory>();
	private int year;
	private int month;
	private Date nextMonthStart;
	private Date now;
	private Date yearStart;
	private Date monthStart;
	private WorkCalendarService workCalendarService;
	private IssueLogDaoNew issueLogDao;
	private IssueNewDao issueDao;

	public IssueDealStatBuilder(int year, int month, WorkCalendarService workCalendarService,
			IssueLogDaoNew issueLogDao, IssueNewDao issueDao) {
		this.year = year;
		this.month = month;
		this.workCalendarService = workCalendarService;
		this.issueLogDao = issueLogDao;
		this.issueDao = issueDao;
		nextMonthStart = CalendarUtil.getNextMonthStart(year, month);
		now = CalendarUtil.now("yyyy-MM-dd HH:mm:ss");
		yearStart = CalendarUtil.parseDate(year + "-01-01", DEFAULT_FORMAT);
		monthStart = CalendarUtil.getMonthStart(year, month);
	}

	public void setNeedBuildedOrganization(List<Organization> needBuildOrgs) {
		cachedNeedBuildOrgs.clear();
		for (Organization org : needBuildOrgs) {
			cachedNeedBuildOrgs.put(org.getId(), org);
			cachedAllOrgs.put(org.getId(), org);
		}
	}

	public void setLastMonthStatDatas(List<IssueDealStat> lastMonthStats) {
		cachedLastMonthStats.clear();
		for (IssueDealStat stat : lastMonthStats) {
			cachedLastMonthStats.put(stat.getOrg().getId(), stat);
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
		} else if (isNextPeriodStepCompletedLog(log)) {
			processDealingLog(log);
		}
	}

	public List<IssueDealStat> getIssueDealStats() {
		processAlwaysUndoingIssueLog();
		List<IssueDealStat> result = new ArrayList<IssueDealStat>(cachedMonthStats.values());
		result.addAll(builderDefaultIssueDealStat());
		return result;
	}

	public List<IssueMonthEndDealingHistory> getDealingHistories() {
		List<IssueMonthEndDealingHistory> result = new ArrayList<IssueMonthEndDealingHistory>();
		Date start = CalendarUtil.getMonthStart(year, month);
		Date end = CalendarUtil.getMonthEnd(year, month);
		for (IssueLogNew log : cachedMonthDealingLog) {
			IssueMonthEndDealingHistory record = constructDealingHistoryRecord(start, end, log);
			result.add(record);
		}
		for (IssueMonthEndDealingHistory record : cachedLastMonthDealingHistory.values()) {
			IssueLogNew log = issueLogDao.getIssueLogById(record.getIssueLogId());
			if (log != null) {
				IssueMonthEndDealingHistory newrecord = copyDealingHistoryRecord(start, end, record);
				result.add(newrecord);
			}
		}
		return result;
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

	private IssueMonthEndDealingHistory copyDealingHistoryRecord(Date start, Date end,
			IssueMonthEndDealingHistory record) {
		IssueMonthEndDealingHistory newrecord = new IssueMonthEndDealingHistory();
		newrecord.setIssueId(record.getIssueId());
		newrecord.setIssueLogId(record.getIssueLogId());
		newrecord.setIssueOperationDescription(record.getIssueOperationDescription());
		newrecord.setMonth(month);
		newrecord.setOrg(record.getOrg());
		newrecord.setOrgInternalCode(record.getOrgInternalCode());
		newrecord.setOrgLevel(record.getOrgLevel());
		newrecord.setOrgType(record.getOrgType());
		newrecord.setOvertimeEndTime(end);
		newrecord.setOvertimeStartTime(start);
		newrecord.setYear(year);
		return newrecord;
	}

	private IssueMonthEndDealingHistory constructDealingHistoryRecord(Date start, Date end,
			IssueLogNew log) {
		Organization org = cachedAllOrgs.get(log.getTargeOrg().getId());
		IssueMonthEndDealingHistory record = new IssueMonthEndDealingHistory();
		record.setIssueId(log.getIssue().getId());
		record.setIssueLogId(log.getId());
		record.setIssueOperationDescription(log.getDealDescription());
		record.setMonth(month);
		record.setOrg(org);
		record.setOrgInternalCode(org.getOrgInternalCode());
		record.setOrgLevel(org.getOrgLevel());
		record.setOrgType(org.getOrgType());
		record.setOvertimeEndTime(end);
		record.setOvertimeStartTime(start);
		record.setYear(year);
		return record;
	}

	private void processConceptedLog(IssueLogNew log) {
		if (log.getLogCompleteTime().equals(nextMonthStart)
				|| log.getLogCompleteTime().after(nextMonthStart)
				|| log.getLogCompleteTime().equals(now) || log.getLogCompleteTime().after(now)) {
			processUnConceptLog(log);
		}
	}

	private void processTellLog(IssueLogNew log) {
		if (isReadedTellLog(log)) {
			processStepCompletedLog(log);
		}
	}

	private void processStepCompletedLog(IssueLogNew log) {
		if (log.getLogCompleteTime().equals(nextMonthStart)
				|| log.getLogCompleteTime().after(nextMonthStart)
				|| log.getLogCompleteTime().equals(now) || log.getLogCompleteTime().after(now)) {
			processDealingLog(log);
		}
	}

	private void processUnConceptLog(IssueLogNew log) {
		// 针对新增立即受理之前的数据
		if (isAddLog(log)) {
			processAddLog(log);
		}
		IssueDealStat statData = lookupIssueDealStat(log.getTargeOrg().getId());
		if (isOvertimeUnConceptLog(log)) {
			statData.setMonthOvertimeDealingCount(statData.getMonthOvertimeDealingCount() + 1);
		} else {
			statData.setMonthNomalDealingCount(statData.getMonthNomalDealingCount() + 1);
		}
		cachedMonthDealingLog.add(log);
	}

	private void processDealingLog(IssueLogNew log) {
		IssueDealStat statData = lookupIssueDealStat(log.getTargeOrg().getId());
		if (isOvertimeDealingLog(log)) {
			statData.setMonthOvertimeDealingCount(statData.getMonthOvertimeDealingCount() + 1);
		} else {
			statData.setMonthNomalDealingCount(statData.getMonthNomalDealingCount() + 1);
		}
		cachedMonthDealingLog.add(log);
	}

	private void processAddLog(IssueLogNew log) {
		if (!monthStart.after(log.getDealTime())) {
			IssueDealStat statData = lookupIssueDealStat(log.getDealOrg().getId());
			statData.setMonthAddCount(statData.getMonthAddCount() + 1);
			statData.setYearAddCount(statData.getYearAddCount() + 1);
			statData.setTotalAddCount(statData.getTotalAddCount() + 1);
		}
	}

	private void processIssueCompletedLog(IssueLogNew log) {
		IssueDealStat statData = lookupIssueDealStat(log.getTargeOrg().getId());
		if (isCompletedDataAfterStatEndData(log)) {
			processDealingLog(log);
		} else {
			statData.setYearCompletedCount(statData.getYearCompletedCount() + 1);
			statData.setTotalCompletedCount(statData.getTotalCompletedCount() + 1);
			if (isOvertimeCompletedLog(log)) {
				statData.setMonthOvertimeCompletedCount(statData.getMonthOvertimeCompletedCount() + 1);
			} else {
				statData.setMonthNomalCompletedCount(statData.getMonthNomalCompletedCount() + 1);
			}
			increateCompletedAddCount(log);
		}
	}

	private void increateCompletedAddCount(IssueLogNew log) {
		IssueNew issue = issueDao.getSimpleIssueById(log.getIssue().getId());
		IssueDealStat statData = lookupIssueDealStat(issue.getCreateOrg().getId());
		if (statData == null)
			return;
		if (yearStart.before(issue.getCreateDate())) {
			statData.setMonthComplededAddCount(statData.getMonthComplededAddCount() + 1);
			statData.setYearComplededAddCount(statData.getYearComplededAddCount() + 1);
		}
		statData.setTotalComplededAddCount(statData.getTotalComplededAddCount() + 1);
	}

	private boolean isOvertimeCompletedLog(IssueLogNew log) {
		return IssueLogDealOverTimeHelper.isOvertime(log.getDealTime(), log.getForLogEntryTime(),
				workCalendarService, OverTime.YELLOW_DONE);
	}

	private boolean isCompletedDataAfterStatEndData(IssueLogNew log) {
		return log.getDealTime().after(nextMonthStart) || log.getDealTime().after(now)
				|| log.getDealTime().equals(nextMonthStart) || log.getDealTime().equals(now);
	}

	private boolean isOvertimeUnConceptLog(IssueLogNew log) {
		return IssueLogDealOverTimeHelper.isOvertime(nextMonthStart.after(now) ? now
				: nextMonthStart, log.getDealTime(), workCalendarService, OverTime.YELLOW_CONCEPT);
	}

	private boolean isOvertimeDealingLog(IssueLogNew log) {
		return IssueLogDealOverTimeHelper.isOvertime(nextMonthStart.after(now) ? now
				: nextMonthStart, log.getDealTime(), workCalendarService, OverTime.YELLOW_DONE);
	}

	private IssueDealStat lookupIssueDealStat(Long orgid) {
		if (!cachedMonthStats.containsKey(orgid)) {
			Organization org = cachedNeedBuildOrgs.get(orgid);
			IssueDealStat result = createNewDealStatInfo(org);
			cachedMonthStats.put(orgid, result);
			cachedNeedBuildOrgs.remove(orgid);
			return result;
		} else {
			return cachedMonthStats.get(orgid);
		}
	}

	private IssueDealStat createNewDealStatInfo(Organization org) {
		IssueDealStat stat = createEmptyDealStatInfo(org, year, month);
		IssueDealStat lastedMonthStat = lookupLastMonthIssueDealStat(org);
		stat.setTotalAddCount(lastedMonthStat == null ? 0 : lastedMonthStat.getTotalAddCount());
		stat.setTotalCompletedCount(lastedMonthStat == null ? 0 : lastedMonthStat
				.getTotalCompletedCount());
		stat.setTotalComplededAddCount(lastedMonthStat == null ? 0 : lastedMonthStat
				.getTotalComplededAddCount());
		if (!isFirstYearMonth()) {
			stat.setYearAddCount(lastedMonthStat == null ? 0 : lastedMonthStat.getYearAddCount());
			stat.setYearCompletedCount(lastedMonthStat == null ? 0 : lastedMonthStat
					.getYearCompletedCount());
			stat.setYearComplededAddCount(lastedMonthStat == null ? 0 : lastedMonthStat
					.getYearComplededAddCount());
		}
		return stat;
	}

	private boolean isFirstYearMonth() {
		return month == 1;
	}

	private IssueDealStat lookupLastMonthIssueDealStat(Organization org) {
		return cachedLastMonthStats.get(org.getId());
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
		return IssueDealType.Add.equals(log.getDealType());
	}

	// 受理
	private boolean isConceptedLog(IssueLogNew log) {
		return log.getDealState() >= 1001L
				&& (IssueDealType.Add.equals(log.getDealType())
						|| IssueDealType.SUBMIT_FORWARD.equals(log.getDealType()) || IssueDealType.ASSIGN
							.equals(log.getDealType()));

	}

	// 抄告
	private boolean isTellLog(IssueLogNew log) {
		return IssueDealType.TELL.equals(log.getDealType());

	}

	// 抄告已阅读
	private boolean isReadedTellLog(IssueLogNew log) {
		return IssueDealState.DONE.equals(log.getDealState());
	}

	// 本部门已经处理完成
	private boolean isStepCompletedLog(IssueLogNew log) {
		return IssueDealState.DONE.equals(log.getDealState())
				&& (IssueDealType.CONCEPT.equals(log.getDealType())
						|| IssueDealType.COMMENT.equals(log.getDealType()) || IssueDealType.SEND_BACK
							.equals(log.getDealType()));
	}

	// 办结
	private boolean isIssueCompletedLog(IssueLogNew log) {
		return IssueDealState.COMPLETE.equals(log.getDealState());
	}

	// 受理后在统计截止时间之后步骤完成
	private boolean isNextPeriodStepCompletedLog(IssueLogNew log) {
		return IssueDealState.STEP_COMPLETE.equals(log.getDealState())
				&& (IssueDealType.CONCEPT.equals(log.getDealType()) || IssueDealType.COMMENT
						.equals(log.getDealType()))
				&& (log.getLogCompleteTime() != null)
				&& (log.getLogCompleteTime().equals(nextMonthStart)
						|| log.getLogCompleteTime().after(nextMonthStart)
						|| log.getLogCompleteTime().equals(now) || log.getLogCompleteTime().after(
						now));
	}

	private List<IssueDealStat> builderDefaultIssueDealStat() {
		List<IssueDealStat> result = new ArrayList<IssueDealStat>();
		for (Organization org : cachedNeedBuildOrgs.values()) {
			result.add(createNewDealStatInfo(org));
		}
		return result;
	}
}