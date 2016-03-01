package com.tianque.statAnalyse.issueManage.listManage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.CollectionUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.ReportDateType;
import com.tianque.statAnalyse.issueManage.listManage.dao.IssueLevelStatDao;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueAreaStat;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueLevelStat;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueTypeStat;
import com.tianque.statAnalyse.issueManage.listManage.helper.IssueReportStatHelper;
import com.tianque.statAnalyse.issueManage.listManage.service.IssueLevelStatService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("issueLevelStatService")
public class IssueLevelStatServiceImpl implements IssueLevelStatService {

	@Autowired
	private IssueLevelStatDao issueLevelStatDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public List<IssueTypeStat> findIssueLevelStatsByYearAndMonthAndParentOrgId(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType) {
		return findIssueLevelStatsByYearAndMonth(startDate, endDate,
				parentOrgId, queryType, !IssueReportStatHelper.ISDATEOVER);
	}

	@Override
	public List<IssueTypeStat> findHistoryIssueLevelStatsByYearAndMonthAndParentOrgId(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType) {
		return findIssueLevelStatsByYearAndMonth(startDate, endDate,
				parentOrgId, queryType, IssueReportStatHelper.ISDATEOVER);
	}

	private List<IssueTypeStat> findIssueLevelStatsByYearAndMonth(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType,
			boolean isDateOver) {
		List<IssueTypeStat> issueTypeStats = new ArrayList<IssueTypeStat>();
		Organization organization = organizationDubboService
				.getFullOrgById(parentOrgId);
		autoFillAdminOrgData(startDate, endDate, issueTypeStats, organization,
				queryType, isDateOver);
		PropertyDict orgType = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.FUNCTIONAL_ORG).get(0);
		List<Organization> OrgFunList = organizationDubboService
				.findOrganizationByParentIdAndOrgType(parentOrgId,orgType.getId());
		if(OrgFunList != null && OrgFunList.size()>0){
			autoFillFunOrgData(startDate, endDate, issueTypeStats, organization,
					queryType, isDateOver);
		}
		
		return issueTypeStats;
	}

	private void autoFillAdminOrgData(Date startDate, Date endDate,
			List<IssueTypeStat> issueTypeStats, Organization organization,
			Integer queryType, boolean isDateOver) {
		List<IssueLevelStat> issueLevelStats = autoFillIssueLevelStatsForAdminOrg(
				startDate, endDate, organization, queryType, isDateOver);
		if (!CollectionUtil.isAvaliable(issueLevelStats)) {
			return;
		}
		PropertyDict orgType = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.ADMINISTRATIVE_REGION).get(0);
		issueTypeStats.add(autoIssueTypeStat(orgType, issueLevelStats));
	}

	private void autoFillFunOrgData(Date startDate, Date endDate,
			List<IssueTypeStat> issueTypeStats, Organization organization,
			Integer queryType, boolean isDateOver) {
		List<IssueLevelStat> issueLevelStats = autoFillIssueLevelStatsForFunOrg(
				startDate, endDate, organization, queryType, isDateOver);
		if (!CollectionUtil.isAvaliable(issueLevelStats)) {
			return;
		}
		PropertyDict orgType = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.FUNCTIONAL_ORG).get(0);
		
		issueTypeStats.add(autoIssueTypeStat(orgType, issueLevelStats));
	}

	private IssueTypeStat autoIssueTypeStat(PropertyDict orgType,
			List<IssueLevelStat> issueLevelStats) {
		IssueTypeStat issueTypeStat = new IssueTypeStat();
		issueTypeStat.setOrgType(orgType);
		issueTypeStat.setIssueLevelStats(issueLevelStats);
		return issueTypeStat;
	}

	private IssueLevelStat autoFillIssueLevelStatForAdminOrg(Date startDate,
			Date endDate, PropertyDict orgLevel, Organization organization,
			Integer queryType, boolean isDateOver) {
		IssueAreaStat issueAreaStat = null;
		if (isDateOver) {
			issueAreaStat = issueLevelStatDao
					.getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndOrgLevel(
							startDate, endDate, organization, orgLevel.getId(),
							queryType);
		} else {
			issueAreaStat = issueLevelStatDao
					.getIssueLevelStatsByYearAndMonthAndOrgCodeAndOrgLevel(
							startDate, endDate, organization, orgLevel.getId(),
							queryType);
		}
		return autoFillIssueLevelStat(orgLevel, issueAreaStat);
	}

	private IssueLevelStat autoFillIssueLevelStatForFunOrg(Date startDate,
			Date endDate, PropertyDict funOrgType, Organization organization,
			Integer queryType, boolean isDateOver) {
		IssueAreaStat issueAreaStat = null;
		if (isDateOver) {
			issueAreaStat = issueLevelStatDao
					.getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndFunOrgType(
							startDate, endDate, organization,
							funOrgType.getId(), queryType);
		} else {
			issueAreaStat = issueLevelStatDao
					.getIssueLevelStatsByYearAndMonthAndOrgCodeAndFunOrgType(
							startDate, endDate, organization,
							funOrgType.getId(), queryType);
		}
		return autoFillIssueLevelStat(funOrgType, issueAreaStat);
	}

	private IssueLevelStat autoFillIssueLevelStat(
			PropertyDict orgLevelOrFunOrgType, IssueAreaStat issueAreaStat) {
		IssueLevelStat issueLevelStat = new IssueLevelStat();
		issueLevelStat.setOrgLevelOrFunOrgType(orgLevelOrFunOrgType);
		IssueReportStatHelper.autoFillCompletionRate(issueAreaStat);
		IssueReportStatHelper.autoFillExtendedRate(issueAreaStat);
		issueLevelStat.setIssueAreaStat(issueAreaStat);
		return issueLevelStat;
	}

	private List<IssueLevelStat> autoFillIssueLevelStatsForAdminOrg(
			Date startDate, Date endDate, Organization organization,
			Integer queryType, boolean isDateOver) {
		List<IssueLevelStat> issueLevelStats = new ArrayList<IssueLevelStat>();
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		for (int i = orgLevels.size() - 1; i >= 0; i--) {
			if (organization.getOrgType().getInternalId() == OrganizationType.ADMINISTRATIVE_REGION
					&& orgLevels.get(i).getInternalId() < organization
							.getOrgLevel().getInternalId()) {
				issueLevelStats.add(autoFillIssueLevelStatForAdminOrg(
						startDate, endDate, orgLevels.get(i), organization,
						queryType, isDateOver));
			}
		}
		return issueLevelStats;
	}

	private List<IssueLevelStat> autoFillIssueLevelStatsForFunOrg(
			Date startDate, Date endDate, Organization organization,
			Integer queryType, boolean isDateOver) {
		List<PropertyDict> funOrgTypes = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.FUNCTIONAL_ORG_TYPE);
		List<IssueLevelStat> issueLevelStats = new ArrayList<IssueLevelStat>();
		for (PropertyDict funOrgType : funOrgTypes) {
			if (organization.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG
					|| organizationDubboService
							.hasFunOrganizationByParentOrgAndFunOrgType(
									organization.getOrgInternalCode(),
									funOrgType.getId())) {
				continue;
			}
			issueLevelStats.add(autoFillIssueLevelStatForFunOrg(startDate,
					endDate, funOrgType, organization, queryType, isDateOver));
		}
		return issueLevelStats;
	}

	@Override
	public List<IssueLevelStat> findHistoryIssueLevelStatsByYearAndMonthAndParentOrgIdNew(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType) {
		return findIssueLevelStatsByYearAndMonthNew(startDate, endDate,
				parentOrgId, queryType);
	}

	private List<IssueLevelStat> findIssueLevelStatsByYearAndMonthNew(
			Date startDate, Date endDate, Long parentOrgId, Integer queryType) {
		Organization organization = organizationDubboService
				.getFullOrgById(parentOrgId);
		if (queryType == ReportDateType.ORGLEVELDEALSTAT) {
			return autoFillIssueLevelStatsForAll(startDate, endDate,
					organization, queryType);
		} else if (queryType == ReportDateType.ORGLEVELDEALSTAT_ADMINORG) {
			return autoFillIssueLevelStatsForAdminOrgNew(startDate, endDate,
					organization, queryType);
		} else if (queryType == ReportDateType.ORGLEVELDEALSTAT_FUNORG) {
			return autoFillIssueLevelStatsForFunOrgNew(startDate, endDate,
					organization, queryType);
		} else {
			return new ArrayList<IssueLevelStat>();
		}
	}

	private List<IssueLevelStat> autoFillIssueLevelStatsForAll(Date startDate,
			Date endDate, Organization organization, Integer queryType) {
		List<IssueLevelStat> issueLevelStats = new ArrayList<IssueLevelStat>();
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		for (int i = orgLevels.size() - 1; i >= 0; i--) {
			if (organization.getOrgType().getInternalId() == OrganizationType.ADMINISTRATIVE_REGION
					&& orgLevels.get(i).getInternalId() < organization
							.getOrgLevel().getInternalId()) {
				issueLevelStats.add(autoFillIssueLevelStatForALL(startDate,
						endDate, orgLevels.get(i), organization, queryType));
			}
		}
		return issueLevelStats;
	}

	private List<IssueLevelStat> autoFillIssueLevelStatsForAdminOrgNew(
			Date startDate, Date endDate, Organization organization,
			Integer queryType) {
		List<IssueLevelStat> issueLevelStats = new ArrayList<IssueLevelStat>();
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		for (int i = orgLevels.size() - 1; i >= 0; i--) {
			if (organization.getOrgType().getInternalId() == OrganizationType.ADMINISTRATIVE_REGION
					&& orgLevels.get(i).getInternalId() < organization
							.getOrgLevel().getInternalId()) {
				issueLevelStats.add(autoFillIssueLevelStatForAdminOrgNew(
						startDate, endDate, orgLevels.get(i), organization,
						queryType));
			}
		}
		return issueLevelStats;
	}

	private List<IssueLevelStat> autoFillIssueLevelStatsForFunOrgNew(
			Date startDate, Date endDate, Organization organization,
			Integer queryType) {
		List<PropertyDict> funOrgTypes = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.FUNCTIONAL_ORG_TYPE);
		List<IssueLevelStat> issueLevelStats = new ArrayList<IssueLevelStat>();
		for (PropertyDict funOrgType : funOrgTypes) {
			if (organization.getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG
					|| organizationDubboService
							.hasFunOrganizationByParentOrgAndFunOrgType(
									organization.getOrgInternalCode(),
									funOrgType.getId())) {
				continue;
			}
			issueLevelStats.add(autoFillIssueLevelStatForFunOrgNew(startDate,
					endDate, funOrgType, organization, queryType));
		}
		return issueLevelStats;
	}

	private IssueLevelStat autoFillIssueLevelStatForALL(Date startDate,
			Date endDate, PropertyDict orgLevel, Organization organization,
			Integer queryType) {
		IssueAreaStat issueAreaStat = issueLevelStatDao
				.getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndOrgLevelNew(
						startDate, endDate, organization, orgLevel.getId(),
						queryType);

		return autoFillIssueLevelStat(orgLevel, issueAreaStat);
	}

	private IssueLevelStat autoFillIssueLevelStatForAdminOrgNew(Date startDate,
			Date endDate, PropertyDict orgLevel, Organization organization,
			Integer queryType) {
		IssueAreaStat issueAreaStat = issueLevelStatDao
				.getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndOrgLevelForAdminNew(
						startDate, endDate, organization, orgLevel.getId(),
						queryType);

		return autoFillIssueLevelStat(orgLevel, issueAreaStat);
	}

	private IssueLevelStat autoFillIssueLevelStatForFunOrgNew(Date startDate,
			Date endDate, PropertyDict funOrgType, Organization organization,
			Integer queryType) {
		IssueAreaStat issueAreaStat = issueLevelStatDao
				.getHistoryIssueLevelStatsByYearAndMonthAndOrgCodeAndFunOrgTypeNew(
						startDate, endDate, organization, funOrgType.getId(),
						queryType);

		return autoFillIssueLevelStat(funOrgType, issueAreaStat);
	}
}
