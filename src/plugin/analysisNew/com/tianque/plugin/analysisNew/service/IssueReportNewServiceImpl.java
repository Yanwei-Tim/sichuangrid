package com.tianque.plugin.analysisNew.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.CollectionUtil;
import com.tianque.core.util.StatAnalyseHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.ReportDateType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.analysisNew.dao.IssueReportStatNewDao;
import com.tianque.plugin.analysisNew.domain.IssueAreaStatNew;
import com.tianque.plugin.analysisNew.domain.IssueTypeStat;
import com.tianque.plugin.analysisNew.util.QueryType;
import com.tianque.statRegrad.util.RegradedPointUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("issueReportNewService")
public class IssueReportNewServiceImpl implements IssueReportNewService {

	@Autowired
	private IssueReportStatNewDao issueReportStatDao;
	@Autowired
	private IssueAreaStatNewService issueAreaStatService;
	@Autowired
	private IssueLevelStatNewService issueLevelStatService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public void statIssueReport() {
		List<Organization> organizations = findAllOrganizationExcludeGrid();
		List<String> errorMessages = new ArrayList<String>();
		Map<String, Date> date = RegradedPointUtil.getStartDateAndEndDate(
				CalendarUtil.getNowYear(), CalendarUtil.getNowMonth(),
				ReportDateType.GROUPBYMONTH_KEY);
		for (Organization organization : organizations) {
			try {
				addIssueHandleStats(date, organization.getId());
			} catch (Exception e) {
				errorMessages.add("生成办理情况报表出错");
			}
			try {
				addIssueClassificationStats(date, organization.getId());
			} catch (Exception e) {
				errorMessages.add("生成分类统计报表出错");
			}
			try {
				addIssueStepStats(date, organization.getId());
			} catch (Exception e) {
				errorMessages.add("生成流程统计报表出错");
			}
		}
		if (CollectionUtil.isAvaliable(errorMessages)) {
			throw new BusinessValidationException(errorMessages.toString());
		}
	}

	@Transactional
	public void addIssueHandleStats(Map<String, Date> date, Long orgId) {
		List<IssueAreaStatNew> handleStats = issueAreaStatService
				.findIssueAreaStatsByYearAndMonthAndParentOrgId(
						date.get(RegradedPointUtil.START_DATE),
						date.get(RegradedPointUtil.END_TDATE), orgId,
						QueryType.AREA_DEAL_HISTORY);
		issueReportStatDao.addIssueHandleStats(handleStats);
	}

	@Transactional
	public void addIssueClassificationStats(Map<String, Date> date, Long orgId) {
		List<IssueAreaStatNew> classificationStats = issueAreaStatService
				.findIssueAreaStatsByYearAndMonthAndParentOrgId(
						date.get(RegradedPointUtil.START_DATE),
						date.get(RegradedPointUtil.END_TDATE), orgId,
						QueryType.AREA_CLASSIFICATION_HISTORY);
		issueReportStatDao.addIssueClassificationStats(classificationStats);
	}

	@Transactional
	public void addIssueStepStats(Map<String, Date> date, Long orgId) {
		List<IssueAreaStatNew> stepStats = issueAreaStatService
				.findIssueAreaStatsByYearAndMonthAndParentOrgId(
						date.get(RegradedPointUtil.START_DATE),
						date.get(RegradedPointUtil.END_TDATE), orgId,
						QueryType.STEP_HISTORY);
		issueReportStatDao.addIssueStepStats(stepStats);
	}

	private List<Organization> findAllOrganizationExcludeGrid() {
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		List<Organization> organizations = new ArrayList<Organization>();
		for (PropertyDict orgLevel : orgLevels) {
			if (orgLevel.getInternalId() != OrganizationLevel.GRID) {
				organizations.addAll(organizationDubboService
						.fingOrganizationforLevel(orgLevel.getId()));
			}
		}
		return organizations;
	}

	@Override
	public List<IssueAreaStatNew> findIssueAreaStatsByYearAndMonthAndParentOrgId(
			Integer year, Integer month, Long parentOrgId, Integer queryType,
			PropertyDict reportDateType) {
		Map<String, Date> date = null;
		reportDateType = propertyDictService.getPropertyDictById(reportDateType
				.getId());
		if (reportDateType.getInternalId() == ReportDateType.GROUPBYMONTH) {
			date = RegradedPointUtil.getStartDateAndEndDate(year, month,
					ReportDateType.GROUPBYMONTH_KEY);
			if (StatAnalyseHelper.isNowYear(year)
					&& StatAnalyseHelper.isNowMonth(month)) {
				return issueAreaStatService
						.findIssueAreaStatsByYearAndMonthAndParentOrgId(
								date.get(RegradedPointUtil.START_DATE),
								date.get(RegradedPointUtil.END_TDATE),
								parentOrgId, queryType);
			}
		} else if (reportDateType.getInternalId() == ReportDateType.GROUPBYQUARTER) {
			date = RegradedPointUtil.getStartDateAndEndDate(year, month,
					ReportDateType.GROUPBYQUARTER_KEY);
			if (StatAnalyseHelper.isNowYear(year)
					&& StatAnalyseHelper.isNowQuarter(month)) {
				return issueAreaStatService
						.findIssueAreaStatsByYearAndMonthAndParentOrgId(
								date.get(RegradedPointUtil.START_DATE),
								date.get(RegradedPointUtil.END_TDATE),
								parentOrgId, queryType);
			}
		} else {
			date = RegradedPointUtil.getStartDateAndEndDate(year, month,
					ReportDateType.GROUPBYYEAR_KEY);
			if (StatAnalyseHelper.isNowYear(year)) {
				return issueAreaStatService
						.findIssueAreaStatsByYearAndMonthAndParentOrgId(
								date.get(RegradedPointUtil.START_DATE),
								date.get(RegradedPointUtil.END_TDATE),
								parentOrgId, queryType);
			}
		}
		return issueAreaStatService
				.findHistoryIssueAreaStatsByYearAndMonthAndParentOrgId(
						date.get(RegradedPointUtil.START_DATE),
						date.get(RegradedPointUtil.END_TDATE), parentOrgId,
						queryType);
	}

	@Override
	public List<IssueTypeStat> findIssueLevelStatsByYearAndMonthAndParentOrgId(
			Integer year, Integer month, Long parentOrgId, Integer queryType,
			PropertyDict reportDateType) {
		Map<String, Date> date = null;
		reportDateType = propertyDictService.getPropertyDictById(reportDateType
				.getId());
		if (reportDateType.getInternalId() == ReportDateType.GROUPBYMONTH) {
			date = RegradedPointUtil.getStartDateAndEndDate(year, month,
					ReportDateType.GROUPBYMONTH_KEY);
			if (StatAnalyseHelper.isNowYear(year)
					&& StatAnalyseHelper.isNowMonth(month)) {
				return issueLevelStatService
						.findIssueLevelStatsByYearAndMonthAndParentOrgId(
								date.get(RegradedPointUtil.START_DATE),
								date.get(RegradedPointUtil.END_TDATE),
								parentOrgId, queryType);
			}
		} else if (reportDateType.getInternalId() == ReportDateType.GROUPBYQUARTER) {
			date = RegradedPointUtil.getStartDateAndEndDate(year, month,
					ReportDateType.GROUPBYQUARTER_KEY);
			if (StatAnalyseHelper.isNowYear(year)
					&& StatAnalyseHelper.isNowQuarter(month)) {
				return issueLevelStatService
						.findIssueLevelStatsByYearAndMonthAndParentOrgId(
								date.get(RegradedPointUtil.START_DATE),
								date.get(RegradedPointUtil.END_TDATE),
								parentOrgId, queryType);
			}
		} else {
			date = RegradedPointUtil.getStartDateAndEndDate(year, month,
					ReportDateType.GROUPBYYEAR_KEY);
			if (StatAnalyseHelper.isNowYear(year)) {
				return issueLevelStatService
						.findIssueLevelStatsByYearAndMonthAndParentOrgId(
								date.get(RegradedPointUtil.START_DATE),
								date.get(RegradedPointUtil.END_TDATE),
								parentOrgId, queryType);
			}
		}
		return issueLevelStatService
				.findHistoryIssueLevelStatsByYearAndMonthAndParentOrgId(
						date.get(RegradedPointUtil.START_DATE),
						date.get(RegradedPointUtil.END_TDATE), parentOrgId,
						queryType);
	}

	// add by bing 2014年11月12日 下午6:00:55 // 取机构 for tbschedule
	@Override
	public List<Long> findOrgByCondition(int taskItemNum, List<Long> idModList,
			int fetchNum, int year, int month, String targetIssueTable) {
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		Long orgLevelGridId = null;
		for (PropertyDict orgLevel : orgLevels) {
			if (orgLevel.getInternalId() == OrganizationLevel.GRID) {
				orgLevelGridId = orgLevel.getId();
				break;
			}
		}
		return organizationDubboService.findOrganIdForLevelExcludeGrid(
				orgLevelGridId, taskItemNum, idModList, fetchNum, year, month,
				targetIssueTable);
	}
	// add by bing 2014年11月12日 下午6:00:59

}
