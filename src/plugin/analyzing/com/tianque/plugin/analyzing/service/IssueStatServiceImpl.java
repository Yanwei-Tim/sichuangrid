package com.tianque.plugin.analyzing.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.component.comparator.OrganizationSeqComparator;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.dao.IssueLogDaoNew;
import com.tianque.dao.IssueNewDao;
import com.tianque.domain.IssueDealStat;
import com.tianque.domain.IssueMonthEndDealingHistory;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationType;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.plugin.analyzing.dao.IssueStatDao;
import com.tianque.report.builder.IssueDealStatBuilder;
import com.tianque.report.builder.IssueDealStatRealTimeBuilder;
import com.tianque.service.WorkCalendarService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Service("issueStatService")
public class IssueStatServiceImpl extends AbstractBaseService implements
		IssueStatService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueLogDaoNew issueLogDao;
	@Autowired
	private IssueNewDao issueDao;
	@Autowired
	private IssueStatDao issueStatDao;
	@Autowired
	private WorkCalendarService workCalendarService;

	@Override
	public List<IssueDealStat> buildIssueDealStatsByMonth(Long rootOrgId,
			int year, int month) {
		Organization rootOrg = organizationDubboService
				.getSimpleOrgById(rootOrgId);
		List<Organization> needBuildOrgs = organizationDubboService
				.findOrganizationsByOrgInternalCode(rootOrg
						.getOrgInternalCode());

		Date startDate = CalendarUtil.getMonthStart(year, month);
		Date endDate = CalendarUtil.getNextMonthStart(year, month);
		List<IssueLogNew> issueLogNews = issueLogDao
				.findDealStatIssueLogByOrgInternalCodeAndDate(
						rootOrg.getOrgInternalCode(), startDate, endDate);

		Date lastStart = CalendarUtil.getLastMonthStart(year, month);
		List<IssueDealStat> lastMonthStats = issueStatDao
				.findIssueDealStatsByOrgInternalCodeMonthStartDate(
						rootOrg.getOrgInternalCode(), lastStart);

		IssueDealStatBuilder reportBuilder = new IssueDealStatBuilder(year,
				month, workCalendarService, issueLogDao, issueDao);
		reportBuilder.setNeedBuildedOrganization(needBuildOrgs);
		reportBuilder.setLastMonthStatDatas(lastMonthStats);
		reportBuilder.setLastMonthDealingHistory(issueStatDao
				.findMonthDealingLogRecordsByOrgInternalCodeMonthStartDate(
						rootOrg.getOrgInternalCode(),
						CalendarUtil.getLastMonthStart(year, month)));
		for (IssueLogNew log : issueLogNews) {
			reportBuilder.addIssueLog(log);
		}
		List<IssueDealStat> result = reportBuilder.getIssueDealStats();
		issueStatDao.deleteIssueDealStatsByOrgInternalCodeMonthStartDate(
				rootOrg.getOrgInternalCode(), startDate);
		issueStatDao.addIssueDealStats(result);
		List<IssueMonthEndDealingHistory> dealingLogHistoryss = reportBuilder
				.getDealingHistories();
		for (IssueMonthEndDealingHistory record : dealingLogHistoryss) {
			record.setIssueSerialNumber(issueDao.getSimpleIssueById(
					record.getIssueId()).getSerialNumber());
			issueStatDao.addMonthDealingLogRecord(record);
		}
		return result;
	}

	@Override
	public List<IssueDealStat> findAdminstrateOrgIssueDealStatsByMonth(
			Long rootOrgId, int year, int month) {
		Date monthStart = CalendarUtil.getMonthStart(year, month);
		List<IssueDealStat> result = new ArrayList<IssueDealStat>();
		List<Organization> needStatOrgs = organizationDubboService
				.findOrgsByParentIdAndOrgTypeInternalIds(
						rootOrgId,
						new Long[] { Long
								.valueOf(OrganizationType.ADMINISTRATIVE_REGION) });
		IssueDealStat rootData = issueStatDao
				.getIssueDealStatsByOrgIdAndStatDate(rootOrgId, monthStart);
		boolean hasGenerated = rootData != null;
		if (!hasGenerated) {
			result = generateEmptyStatData(needStatOrgs, year, month);
			Organization rootOrg = organizationDubboService
					.getSimpleOrgById(rootOrgId);
			rootData = IssueDealStatBuilder.createEmptyDealStatInfo(rootOrg,
					year, month);
		} else {
			for (Organization org : needStatOrgs) {
				IssueDealStat stat = issueStatDao
						.sumIssueDealStatsByOrgInternalCodeAndStatDateAndOrgType(
								org.getOrgInternalCode(), org.getOrgType()
										.getId(), monthStart);
				populationOrgProperty(stat, org);
				populationDateProperty(stat, rootData.getStatStartTime(),
						rootData.getStatEndTime());
				result.add(stat);
			}
		}
		Collections.sort(result, new OrganizationSeqComparator<IssueDealStat>(
				"org"));
		// result.add(0, rootData);
		return result;
	}

	private List<IssueDealStat> generateEmptyStatData(
			List<Organization> needStatOrgs, int year, int month) {
		List<IssueDealStat> result = new ArrayList<IssueDealStat>();
		for (Organization org : needStatOrgs) {
			result.add(IssueDealStatBuilder.createEmptyDealStatInfo(org, year,
					month));
		}
		return result;
	}

	@Override
	public List<IssueDealStat> findFunctionOrgIssueDealStatsByParentIdMonth(
			Long rootOrgId, int year, int month) {
		Date monthStart = CalendarUtil.getMonthStart(year, month);
		List<IssueDealStat> result = new ArrayList<IssueDealStat>();
		List<Organization> needStatOrgs = organizationDubboService
				.findOrgsByParentIdAndFunTypes(rootOrgId);
		IssueDealStat rootData = issueStatDao
				.getIssueDealStatsByOrgIdAndStatDate(rootOrgId, monthStart);
		boolean hasGenerated = rootData != null;
		if (!hasGenerated) {
			result = generateEmptyStatData(needStatOrgs, year, month);
		} else {
			for (Organization org : needStatOrgs) {
				IssueDealStat stat = issueStatDao
						.getIssueDealStatsByOrgIdAndStatDate(org.getId(),
								monthStart);
				populationOrgProperty(stat, org);
				populationDateProperty(stat, rootData.getStatStartTime(),
						rootData.getStatEndTime());
				result.add(stat);
			}
		}
		Collections.sort(result, new OrganizationSeqComparator<IssueDealStat>(
				"org"));
		return result;
	}

	private void populationOrgProperty(IssueDealStat stat, Organization org) {
		stat.setOrg(org);
		stat.setOrgInternalCode(org.getOrgInternalCode());
		stat.setOrgLevel(org.getOrgLevel());
		stat.setOrgType(org.getOrgType());
	}

	private void populationDateProperty(IssueDealStat stat, Date startTime,
			Date endTime) {
		stat.setStatStartTime(startTime);
		stat.setStatEndTime(endTime);
	}

	@Override
	public List<IssueDealStat> findRealTimeAdminstrateOrgIssueDealStats(
			Long orgId) {
		Organization rootOrg = organizationDubboService.getSimpleOrgById(orgId);
		List<Organization> needStatOrgs = organizationDubboService
				.findOrgsByParentIdAndOrgTypeInternalIds(
						orgId,
						new Long[] { Long
								.valueOf(OrganizationType.ADMINISTRATIVE_REGION) });
		// needStatOrgs.add(rootOrg);

		IssueDealStat lastReport = getLastExistedReportDate(orgId);
		Date statStartDate = null;
		int baseYear = lastReport == null ? 1900 : CalendarUtil
				.getYear(lastReport.getStatStartTime());
		int baseMonth = lastReport == null ? 1 : CalendarUtil
				.getMonth(lastReport.getStatStartTime());
		if (lastReport != null
				&& lastReport.getStatEndTime().before(
						CalendarUtil.getMonthEnd(baseYear, baseMonth))) {
			statStartDate = lastReport.getStatEndTime();
		} else {
			statStartDate = CalendarUtil.getNextMonthStart(baseYear, baseMonth);
		}
		Date statEndDate = CalendarUtil.getTomorrow();
		List<IssueLogNew> issueLogNews = issueLogDao
				.findDealStatIssueLogByOrgInternalCodeAndDate(
						rootOrg.getOrgInternalCode(), statStartDate,
						statEndDate);
		List<IssueDealStat> lastMonthStats = findAdminstrateOrgIssueDealStatsByMonth(
				orgId, baseYear, baseMonth);
		IssueDealStatRealTimeBuilder reportBuilder = new IssueDealStatRealTimeBuilder(
				organizationDubboService, workCalendarService, issueLogDao,
				issueDao, statStartDate);
		reportBuilder.setNeedBuildedOrganization(needStatOrgs);
		reportBuilder.setLastMonthStatDatas(lastMonthStats);
		reportBuilder.setSelectedOrganization(rootOrg);
		reportBuilder.setLastMonthDealingHistory(issueStatDao
				.findMonthDealingLogRecordsByOrgInternalCodeMonthStartDate(
						rootOrg.getOrgInternalCode(), statStartDate));
		for (IssueLogNew log : issueLogNews) {
			reportBuilder.addIssueLog(log);
		}
		List<IssueDealStat> result = reportBuilder.getIssueDealStats();
		return result;
	}

	@Override
	public List<IssueDealStat> findRealTimeFunctionOrgIssueDealStats(Long orgId) {
		Organization rootOrg = organizationDubboService.getSimpleOrgById(orgId);
		List<Organization> needStatOrgs = organizationDubboService
				.findOrgsByParentIdAndFunTypes(orgId);

		IssueDealStat lastReport = getLastExistedReportDate(orgId);
		Date statStartDate = null;
		int baseYear = lastReport == null ? 1900 : CalendarUtil
				.getYear(lastReport.getStatStartTime());
		int baseMonth = lastReport == null ? 1 : CalendarUtil
				.getMonth(lastReport.getStatStartTime());
		if (lastReport != null
				&& lastReport.getStatEndTime().before(
						CalendarUtil.getMonthEnd(baseYear, baseMonth))) {
			statStartDate = lastReport.getStatEndTime();
		} else {
			statStartDate = CalendarUtil.getNextMonthStart(baseYear, baseMonth);
		}
		Date statEndDate = CalendarUtil.getTomorrow();
		List<IssueLogNew> issueLogNews = issueLogDao
				.findDealStatIssueLogByOrgInternalCodeAndDate(
						rootOrg.getOrgInternalCode(), statStartDate,
						statEndDate);
		List<IssueDealStat> lastMonthStats = findFunctionOrgIssueDealStatsByParentIdMonth(
				orgId, baseYear, baseMonth);
		IssueDealStatRealTimeBuilder reportBuilder = new IssueDealStatRealTimeBuilder(
				organizationDubboService, workCalendarService, issueLogDao,
				issueDao, statStartDate);
		reportBuilder.setNeedBuildedOrganization(needStatOrgs);
		reportBuilder.setLastMonthStatDatas(lastMonthStats);
		reportBuilder.setLastMonthDealingHistory(issueStatDao
				.findMonthDealingLogRecordsByOrgInternalCodeMonthStartDate(
						rootOrg.getOrgInternalCode(), statStartDate));
		for (IssueLogNew log : issueLogNews) {
			reportBuilder.addIssueLog(log);
		}
		List<IssueDealStat> result = reportBuilder.getIssueDealStats();
		return result;
	}

	private IssueDealStat getLastExistedReportDate(Long orgId) {
		List<IssueDealStat> exsited = issueStatDao
				.findIssueDealStatsByOrgId(orgId);
		Collections.sort(exsited, new Comparator<IssueDealStat>() {
			public int compare(IssueDealStat stat1, IssueDealStat stat2) {
				return stat1.getStatStartTime().compareTo(
						stat2.getStatStartTime());
			}
		});
		if (exsited == null || exsited.size() == 0) {
			return null;
		}
		return exsited.get(exsited.size() - 1);
	}

}
