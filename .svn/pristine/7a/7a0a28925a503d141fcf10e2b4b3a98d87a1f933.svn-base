package com.tianque.plugin.analysisNew.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.domain.Organization;
import com.tianque.plugin.analysisNew.dao.IssueAreaStatNewDao;
import com.tianque.plugin.analysisNew.domain.IssueAreaStatNew;
import com.tianque.plugin.analysisNew.helper.IssueReportStatHelper;
import com.tianque.plugin.analysisNew.util.QueryType;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("issueAreaStatNewService")
public class IssueAreaStatNewServiceImpl implements IssueAreaStatNewService {

	@Autowired
	private IssueAreaStatNewDao issueAreaStatDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public List<IssueAreaStatNew> findIssueAreaStatsByYearAndMonthAndParentOrgId(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType) {
		return findIssueAreaStatsByYearAndMonth(startDate, endDate,
				parentOrgId, queryType, !IssueReportStatHelper.ISDATEOVER);
	}

	@Override
	public List<IssueAreaStatNew> findHistoryIssueAreaStatsByYearAndMonthAndParentOrgId(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType) {
		return findIssueAreaStatsByYearAndMonth(startDate, endDate,
				parentOrgId, queryType, IssueReportStatHelper.ISDATEOVER);
	}

	private List<IssueAreaStatNew> findIssueAreaStatsByYearAndMonth(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType,
			boolean isDateOver) {
		List<Organization> organizations = organizationDubboService
				.findOrganizationsByParentId(parentOrgId);
		List<IssueAreaStatNew> issueAreaStats = new ArrayList<IssueAreaStatNew>();
		for (Organization organization : organizations) {
			IssueAreaStatNew issueAreaStat = getIssueAreaStatsByYearAndMonthAndOrgCode(
					startDate, endDate, organization, queryType, isDateOver);
			autoFillIssueAreaStat(startDate, endDate, parentOrgId,
					organization, issueAreaStat, queryType);
			issueAreaStats.add(issueAreaStat);
		}

		return issueAreaStats;
	}

	private void autoFillIssueAreaStat(Date startDate, Date endDate,
			Long parentOrgId, Organization organization,
			IssueAreaStatNew issueAreaStat, Integer queryType) {
		issueAreaStat.setOrganization(organization);
		issueAreaStat.setParentOrgId(parentOrgId);

		if (queryType.intValue() == QueryType.YEAR_ON_YEAR
				|| queryType.intValue() == QueryType.MONTH_ON_MONTH) {
			IssueReportStatHelper
					.autoFillIssueProportionStatRate(issueAreaStat);
		}

		if (QueryType.SCALE.contains(queryType)) {
			IssueReportStatHelper.autoFillCompletionRate(issueAreaStat);
			IssueReportStatHelper.autoFillExtendedRate(issueAreaStat);
		}
	}

	private IssueAreaStatNew getIssueAreaStatsByYearAndMonthAndOrgCode(
			Date startDate, Date endDate, Organization organization,
			Integer queryType, boolean isDateOver) {
		if (isDateOver) {
			return issueAreaStatDao
					.getHistoryIssueAreaStatsByYearAndMonthAndOrgCode(
							startDate, endDate,
							organization.getOrgInternalCode(), queryType);
		} else {
			return issueAreaStatDao.getIssueAreaStatsByYearAndMonthAndOrgCode(
					startDate, endDate, organization, queryType);
		}
	}
}
