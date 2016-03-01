package com.tianque.leaderAnalysis.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.leaderAnalysis.constants.MobileModelTypes;
import com.tianque.leaderAnalysis.dao.IssueAnalysisToMobileDAO;
import com.tianque.leaderAnalysis.domain.IssueAnalysisToMobile;
import com.tianque.leaderAnalysis.service.IssueAnalysisToMobileService;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.service.IssueTypeDomainService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tableManage.service.TableManageService;

/**
 * @Description:手机事件的研判分析业务实现
 * @author zhangyouwei@hztianque.com
 * @date: 2015-3-31 下午05:17:25
 */
@Service("issueAnalysisToMobileService")
@Transactional
public class IssueAnalysisToMobileServiceImpl implements
		IssueAnalysisToMobileService {

	@Autowired
	private IssueTypeDomainService issueTypeDomainService;
	@Autowired
	private IssueAnalysisToMobileDAO issueAnalysisToMobileDAO;
	@Autowired
	private TableManageService tableManageService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private CacheService cacheService;

	@Override
	public void createIssueAnalysisToMobileDataByTime(int year, int month) {
		try {
			if (year == Calendar.getInstance().get(Calendar.YEAR)
					&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
				throw new BusinessValidationException("年份或月份错误！");
			}
			List<IssueTypeDomain> issueTypeDomains = issueTypeDomainService
					.findIssueTypeDomainsToIssue();
			if (issueTypeDomains != null && issueTypeDomains.size() != 0) {
				Date startDate = CalendarUtil.getBeforeMonthFirstDayByTime(
						year, month);
				Date endDate = CalendarUtil.getBeforeMonthLastDayByTime(year,
						month);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("year", year);
				map.put("month", month);
				map.put("startDate", startDate);
				map.put("endDate", endDate);
				boolean isCreat = tableManageService.createAnalyseTable(
						AnalyseUtil.ISSUE_ANALYSIS_TO_MOBILE_TABLE_NAME,
						AnalyseUtil.ISSUE_ANALYSIS_TO_MOBILE_TABLE_NAME_SQL,
						Integer.valueOf(year), Integer.valueOf(month));
				if (!isCreat) {
					issueAnalysisToMobileDAO.deleteAllDataByYearAndMonth(map);
				}
				for (IssueTypeDomain issueTypeDomain : issueTypeDomains) {
					map.put("issueTypeDomainId", issueTypeDomain.getId());
					issueAnalysisToMobileDAO.addIssueAnalysisToMobile(map);
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类IssueAnalysisToMobileServiceImpl的createIssueAnalysisToMobileDataByTime方法出现异常，原因：",
					"手机事件的研判分析生成各个大类办理情况错误", e);
		}
	}

	@Override
	public void createIssueAnalysisToMobileData() {
		try {
			List<IssueTypeDomain> issueTypeDomains = issueTypeDomainService
					.findIssueTypeDomainsToIssue();
			if (issueTypeDomains != null && issueTypeDomains.size() != 0) {
				int year = CalendarUtil.getNowYear();
				int month = CalendarUtil.getNowMonth();
				Date startDate = CalendarUtil.getBeforeMonthFirstDayByTime(
						year, month);
				Date endDate = CalendarUtil.getBeforeMonthLastDayByTime(year,
						month);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("year", year);
				map.put("month", month);
				map.put("startDate", startDate);
				map.put("endDate", endDate);
				boolean isCreat = tableManageService.createAnalyseTable(
						AnalyseUtil.ISSUE_ANALYSIS_TO_MOBILE_TABLE_NAME,
						AnalyseUtil.ISSUE_ANALYSIS_TO_MOBILE_TABLE_NAME_SQL,
						Integer.valueOf(year), Integer.valueOf(month));
				if (!isCreat) {
					issueAnalysisToMobileDAO.deleteAllDataByYearAndMonth(map);
				}
				for (IssueTypeDomain issueTypeDomain : issueTypeDomains) {
					map.put("issueTypeDomainId", issueTypeDomain.getId());
					issueAnalysisToMobileDAO.addIssueAnalysisToMobile(map);
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类IssueAnalysisToMobileServiceImpl的createIssueAnalysisToMobileData方法出现异常，原因：",
					"手机事件的研判分析生成各个大类办理情况错误", e);
		}
	}

	@Override
	public List<IssueAnalysisToMobile> getIssueDatasList(String issueType,
			Long orgId, int year, int month) {
		if (orgId == null || !StringUtil.isStringAvaliable(issueType)
				|| !MobileModelTypes.ISSUE_MODEL_MAP.containsKey(issueType)) {
			throw new BusinessValidationException("参数错误!");
		}

		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			throw new BusinessValidationException("所查月份数据尚未生产！");
		}
		List<IssueAnalysisToMobile> datas = null;
		try {
			boolean isCreat = tableManageService.createAnalyseTable(
					AnalyseUtil.ISSUE_ANALYSIS_TO_MOBILE_TABLE_NAME,
					AnalyseUtil.ISSUE_ANALYSIS_TO_MOBILE_TABLE_NAME_SQL,
					Integer.valueOf(year), Integer.valueOf(month));
			if (isCreat) {
				datas = new ArrayList<IssueAnalysisToMobile>();
				return datas;
			}
			String issueTypeDomainName = MobileModelTypes.ISSUE_MODEL_MAP
					.get(issueType);
			IssueTypeDomain issueTypeDomain = issueTypeDomainService
					.getIssueTypeDoaminByDomainName(issueTypeDomainName);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("year", year);
			map.put("month", month);
			map.put("orgId", orgId);
			map.put("issueTypeDomainId", issueTypeDomain.getId());
			PropertyDict adminDict = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.ORGANIZATION_TYPE,
							OrganizationType.ADMINISTRATIVE_REGION).get(0);
			map.put("orgTypeId", adminDict.getId());

			datas = issueAnalysisToMobileDAO.queryIssueDatasListForList(map);
			fullOrg(datas);
			return datas;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类IssueAnalysisToMobileServiceImpl的getIssueDatasList方法出现异常，原因：",
					"手机事件的研判分析事件各个大类对应的列表错误", e);
		}
	}

	/**
	 * 填充组织机构信息
	 * 
	 * @param datas
	 */
	private void fullOrg(List<IssueAnalysisToMobile> datas) {
		if (datas != null && datas.size() != 0) {
			for (IssueAnalysisToMobile issueAnalysisToMobile : datas) {
				issueAnalysisToMobile.setOrg(organizationDubboService
						.getSimpleOrgById(issueAnalysisToMobile.getOrg()
								.getId()));
			}
		}

	}

	@Override
	public IssueAnalysisToMobile getIssueDatasListTotalByOrgParentId(
			String issueType, Long orgId, int year, int month) {

		if (orgId == null || !StringUtil.isStringAvaliable(issueType)
				|| !MobileModelTypes.ISSUE_MODEL_MAP.containsKey(issueType)) {
			throw new BusinessValidationException("参数错误!");
		}

		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			throw new BusinessValidationException("所查月份数据尚未生产！");
		}
		try {
			IssueAnalysisToMobile result = null;
			boolean isCreat = tableManageService.createAnalyseTable(
					AnalyseUtil.ISSUE_ANALYSIS_TO_MOBILE_TABLE_NAME,
					AnalyseUtil.ISSUE_ANALYSIS_TO_MOBILE_TABLE_NAME_SQL,
					Integer.valueOf(year), Integer.valueOf(month));
			if (!isCreat) {
				String issueTypeDomainName = MobileModelTypes.ISSUE_MODEL_MAP
						.get(issueType);
				IssueTypeDomain issueTypeDomain = issueTypeDomainService
						.getIssueTypeDoaminByDomainName(issueTypeDomainName);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("year", year);
				map.put("month", month);
				map.put("issueTypeDomainId", issueTypeDomain.getId());
				result = issueAnalysisToMobileDAO
						.getIssueDatasListTotalByOrgParentId(map);
			}
			return result;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类IssueAnalysisToMobileServiceImpl的getIssueDatasListTotalByOrgParentId方法出现异常，原因：",
					"手机事件的研判分析获取某个组织机构下辖的统计总数错误", e);
		}
	}
}
