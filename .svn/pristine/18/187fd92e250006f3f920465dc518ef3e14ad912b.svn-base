package com.tianque.plugin.analyzing.service;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.state.IssueState;
import com.tianque.plugin.analyzing.dao.UserActivateReportDAO;
import com.tianque.plugin.analyzing.domain.UserActivateReport;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tableManage.service.TableManageService;

/**
 * @Description:用户覆盖率业务接口实现类
 * @author zhangyouwei@hztianque.com
 * @date: 2015-1-16 上午12:24:37
 */
@Service("userActivateReportService")
@Transactional
public class UserActivateReportServiceImpl implements UserActivateReportService {
	public static final Long CITY_VAL = 0L;
	public static final Long DISTRICT_VAL = 1L;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private TableManageService tableManageService;
	@Autowired
	private UserActivateReportDAO userActivateReportDAO;
	@Autowired
	private CacheService cacheService;

	@Override
	public void createUserActivateReportData() {
		try {
			int year = CalendarUtil.getNowYear();
			int month = CalendarUtil.getNowMonth();
			createUserActivateReportDataByYearAndMonth(year, month);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类UserActivateReportServiceImpl的createUserActivateReportData方法出现异常，原因：",
					"用户覆盖率报表记录job生成错误", e);
		}

	}

	@Override
	public void createUserActivateReportList(int year, int month) {
		if (validateOtherDate(year, month)) {
			throw new BusinessValidationException("查询出错，所选择月份未生成");
		}
		try {
			createUserActivateReportDataByYearAndMonth(year, month);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类UserActivateReportServiceImpl的createUserActivateReportList方法出现异常，原因：",
					"用户覆盖率根据年月生成报表错误", e);
		}
	}

	private boolean validateOtherDate(int year, int month) {
		return year == Calendar.getInstance().get(Calendar.YEAR)
				&& month >= Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	private List<UserActivateReport> getUserActivateReportListByMap(int year,
			int month, Integer orgLevelDistance, Organization org) {
		String key = "";
		if (orgLevelDistance == 0) {
			key = MemCacheConstant.getUserActivateReportCachKey(
					MemCacheConstant.ANALYSE_USERACTIVATEREPORT_NAMESPACE,
					year, month, CITY_VAL, org.getId().toString());
		} else {
			key = MemCacheConstant.getUserActivateReportCachKey(
					MemCacheConstant.ANALYSE_USERACTIVATEREPORT_NAMESPACE,
					year, month, DISTRICT_VAL, org.getId().toString());
		}
		List<UserActivateReport> list = null;
		if (key != null) {
			list = (List<UserActivateReport>) cacheService.get(
					MemCacheConstant.ANALYSE_USERACTIVATEREPORT_NAMESPACE, key);
		}
		if (list != null) {
			return list;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("orgCode", org.getOrgInternalCode());
		if (orgLevelDistance == 0) {
			map.put("statisticsType", CITY_VAL);// 查询市层级
		} else {
			map.put("statisticsType", DISTRICT_VAL);// 查询区县
		}
		list = calculationPercentage(userActivateReportDAO
				.queryUserActivateReportHistoryForList(map));
		if (key != null) {
			cacheService.set(
					MemCacheConstant.ANALYSE_USERACTIVATEREPORT_NAMESPACE, key,
					ModulTypes.CACHETIME, list);
		}
		return list;
	}

	/***
	 * 计算百分比和活跃度
	 */

	public List<UserActivateReport> calculationPercentage(
			List<UserActivateReport> list) {
		if (list != null) {
			for (UserActivateReport userActivateReport : list) {
				// 乡镇参与百分比
				userActivateReport.setTownAccountPercentage(getLingRate(
						userActivateReport.getTownActivateCount(),
						userActivateReport.getTownCount()));
				// 社区参与百分比
				userActivateReport.setCommunityAccountPercentage(getLingRate(
						userActivateReport.getCommunityActivateCount(),
						userActivateReport.getCommunityCount()));
				// 村参与百分比
				userActivateReport.setVillageAcountPercentage(getLingRate(
						userActivateReport.getVillageActivateCount(),
						userActivateReport.getVillageCount()));
				// 乡镇街道活跃度
				userActivateReport.setTownMonthCoverageRate(getLingRate(
						userActivateReport.getTownActiveAccountCount(),
						userActivateReport.getTownAccountCount()));
				// 村社区周活跃度
				userActivateReport.setCommunityWeekCoverageRate(getLingRate(
						userActivateReport.getCommunityWeekActiveCount(),
						userActivateReport.getCommunityAccountCount()));
				// 村社区月活跃度
				userActivateReport.setCommunityMonthCoverageRate(getLingRate(
						userActivateReport.getCommunityMonthActiveCount(),
						userActivateReport.getCommunityAccountCount()));
			}
		}
		return list;
	}

	@Override
	public List<UserActivateReport> getUserActivateReportList(int year,
			int month, Integer orgLevelDistance) {
		if (validateOtherDate(year, month)) {
			throw new BusinessValidationException("查询出错，所选择月份未生成");
		}
		Organization userOrg = ThreadVariable.getUser().getOrganization();
		PropertyDict userOrgLevel = userOrg.getOrgLevel();
		if (userOrgLevel == null
				|| userOrgLevel.getInternalId() < OrganizationLevel.CITY) {
			throw new BusinessValidationException("只有县级以上才能查看该报表");
		}
		tableManageService.createAnalyseTable(
				AnalyseUtil.USER_ACTIVATE_REPORT_TABLE_NAME,
				AnalyseUtil.USER_ACTIVATE_REPORT_TABLE_NAME_SQL,Integer
				.valueOf(year), Integer.valueOf(month));
		if (orgLevelDistance == null) {
			throw new BusinessValidationException("查询统计失败，未找到查询类别");
		}
		List<UserActivateReport> list = null;
		try {
			list = getUserActivateReportListByMap(year, month,
					orgLevelDistance, userOrg);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类UserActivateReportServiceImpl的getUserActivateReportList方法出现异常，原因：",
					"用户覆盖率根据年月查询报表信息错误", e);
		}
		return bindCount(list);
	}

	/***
	 * 计算合计
	 */
	private List<UserActivateReport> bindCount(List<UserActivateReport> list) {
		if (list != null && list.size() != 0) {
			UserActivateReport userActivateReportCount = new UserActivateReport();
			int townCount = 0;// 组织机构乡镇个数
			int communityCount = 0;// 组织机构下辖社区的个数
			int villageCount = 0;// 组织机构下辖村的个数
			int townActivateCount = 0;// 乡镇激活量
			int communityActivateCount = 0;// 社区激活量
			int villageActivateCount = 0;// 村激活量
			int agencyOfOpinionCount = 0;// 上月社情民意办结量
			int issueDealCount = 0;// 上月办结数量量

			int townActiveAccountCount = 0;// 乡镇层级月活跃账号数
			int townAccountCount = 0;// 乡镇层级账号总数

			int communityAccountCount = 0;// 社区层级账号总数
			int communityWeekActiveCount = 0;// 社区层级周活跃数
			int communityMonthActiveCount = 0;// 社区层级月活跃数
			for (UserActivateReport userActivateReport : list) {
				townCount += userActivateReport.getTownCount();
				communityCount += userActivateReport.getCommunityCount();
				villageCount += userActivateReport.getVillageCount();
				townActivateCount += userActivateReport.getTownActivateCount();
				communityActivateCount += userActivateReport
						.getCommunityActivateCount();
				villageActivateCount += userActivateReport
						.getVillageActivateCount();
				agencyOfOpinionCount += userActivateReport
						.getAgencyOfOpinionCount();
				issueDealCount += userActivateReport.getIssueDealCount();

				townAccountCount += userActivateReport.getTownAccountCount();
				townActiveAccountCount += userActivateReport
						.getTownActiveAccountCount();

				communityAccountCount += userActivateReport
						.getCommunityAccountCount();
				communityWeekActiveCount += userActivateReport
						.getCommunityWeekActiveCount();
				communityMonthActiveCount += userActivateReport
						.getCommunityMonthActiveCount();

			}
			Organization organization = new Organization();
			organization.setOrgName("合计");
			userActivateReportCount.setOrganization(organization);
			userActivateReportCount.setTownCount(townCount);
			userActivateReportCount.setCommunityCount(communityCount);
			userActivateReportCount.setVillageCount(villageCount);
			userActivateReportCount.setTownActivateCount(townActivateCount);
			userActivateReportCount
					.setCommunityActivateCount(communityActivateCount);
			userActivateReportCount
					.setVillageActivateCount(villageActivateCount);
			userActivateReportCount
					.setAgencyOfOpinionCount(agencyOfOpinionCount);
			userActivateReportCount.setIssueDealCount(issueDealCount);

			userActivateReportCount.setTownAccountPercentage(getLingRate(
					townActivateCount, townCount));
			userActivateReportCount.setTownMonthCoverageRate(getLingRate(
					townActiveAccountCount, townAccountCount));
			userActivateReportCount.setCommunityAccountPercentage(getLingRate(
					communityActivateCount, communityCount));
			userActivateReportCount.setVillageAcountPercentage(getLingRate(
					villageActivateCount, villageCount));
			userActivateReportCount.setCommunityWeekCoverageRate(getLingRate(
					communityWeekActiveCount, communityAccountCount));
			userActivateReportCount.setCommunityMonthCoverageRate(getLingRate(
					communityMonthActiveCount, communityAccountCount));
			list.add(userActivateReportCount);
		}
		return list;
	}

	/**
	 * 计算百分比值 firstData:开展工作数 secondData:总数
	 */
	private String getLingRate(double firstData, double secondData) {
		DecimalFormat df = new DecimalFormat("######0.00");

		String chain = "";
		if (firstData == 0 || secondData == 0) {
			chain = "0.00%";
			return chain;
		}
		double data = firstData / secondData * 100;
		chain = df.format(data) + "%";
		return chain;
	}

	/***
	 * 根据组织大类和internalId获取字典项Id
	 * 
	 * @param domainName
	 * @param internalId
	 * @return
	 */
	private Long findPropertyDictByDomainNameAndInternalId(String domainName,
			int internalId) {
		Long dictId = null;
		List<PropertyDict> propertyDictList = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(domainName,
						internalId);
		if (propertyDictList != null && propertyDictList.size() > 0) {
			PropertyDict dict = propertyDictList.get(0);
			if (dict != null && dict.getId() != null) {
				dictId = dict.getId();
			}
		}
		return dictId;
	}

	/**
	 * 根据时间生成数据
	 * 
	 * @param year
	 * @param month
	 */

	private void createUserActivateReportDataByYearAndMonth(int year, int month) {
		boolean isCreat = tableManageService.createAnalyseTable(
				AnalyseUtil.USER_ACTIVATE_REPORT_TABLE_NAME,
				AnalyseUtil.USER_ACTIVATE_REPORT_TABLE_NAME_SQL,
				Integer.valueOf(year), Integer.valueOf(month));
		if (!isCreat) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("year", year);
			map.put("month", month);
			userActivateReportDAO.deleteAllDataByYearAndMonth(map);
		}
		// 本月月初
		Date lastMonthDate = CalendarUtil.getBeforeMonthFirstDayByTime(year,
				month);
		// 下个月月初
		Date startDate = CalendarUtil.getNextMonthStart(year, month);

		// 本月最后一周开始时间
		Date lastWeekDate = CalendarUtil.getBeforeMonthWeekDayByTime(year,
				month);
		// 乡镇层级
		Long townOrgLevel = findPropertyDictByDomainNameAndInternalId(
				PropertyTypes.ORGANIZATION_LEVEL, OrganizationLevel.TOWN);
		// 社区层级
		Long villageOrgLevel = findPropertyDictByDomainNameAndInternalId(
				PropertyTypes.ORGANIZATION_LEVEL, OrganizationLevel.VILLAGE);
		// 市层级
		Long cityOrgLevel = findPropertyDictByDomainNameAndInternalId(
				PropertyTypes.ORGANIZATION_LEVEL, OrganizationLevel.CITY);
		// 市层级
		Long districtOrgLevel = findPropertyDictByDomainNameAndInternalId(
				PropertyTypes.ORGANIZATION_LEVEL, OrganizationLevel.DISTRICT);
		// 已办结数据code
		int stateCode = IssueState.ISSUECOMPLETE_CODE;
		Long orgType = findPropertyDictByDomainNameAndInternalId(
				PropertyTypes.ORGANIZATION_TYPE,
				OrganizationType.ADMINISTRATIVE_REGION);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		// map.put("endDate", endDate);
		map.put("lastWeekDate", lastWeekDate);
		map.put("townOrgLevel", townOrgLevel);
		map.put("villageOrgLevel", villageOrgLevel);
		map.put("orgLevel", cityOrgLevel);
		map.put("stateCode", stateCode);
		map.put("lastMonthDate", lastMonthDate);
		map.put("orgType", orgType);
		map.put("year", year);
		map.put("month", month);
		try {
			querySimpleUserActivateReportForList(map, CITY_VAL);// 先统计市层级数据
			map.put("orgLevel", districtOrgLevel);
			querySimpleUserActivateReportForList(map, DISTRICT_VAL);// 再统计区县层级数据
		} catch (Exception e) {
			throw new ServiceValidationException("生成网格化服务管理工作统计概况表出现错误", e);
		}

	}

	private void querySimpleUserActivateReportForList(Map<String, Object> map,
			Long statistics) {
		List<UserActivateReport> list = userActivateReportDAO
				.querySimpleUserActivateReportForList(map);
		if (list != null) {
			for (UserActivateReport userActivateReport : list) {
				if (statistics == 0) {
					userActivateReport.setStatisticsType(DISTRICT_VAL);
				} else {
					userActivateReport.setStatisticsType(CITY_VAL);
				}
				userActivateReport.setCommunityCount(userActivateReport
						.getCommunityAllCount()
						- userActivateReport.getVillageCount());
				userActivateReport.setCommunityActivateCount(userActivateReport
						.getCommunityAllCountPercentage()
						- userActivateReport.getVillageActivateCount());
				userActivateReportDAO.addUserActivateReport(userActivateReport);
			}
			list = calculationPercentage(list);

			/** 数据量不大，查询速度快，直接清除掉缓存 */
			cacheService
					.invalidateNamespaceCache(MemCacheConstant.ANALYSE_USERACTIVATEREPORT_NAMESPACE);
		}
	}

	@Override
	public List<UserActivateReport> getUserActivateReportSort(int year,
			int month, Integer orgLevelDistance, final String sortName,
			final String sort) {
		if (!StringUtil.isStringAvaliable(sortName)) {
			throw new BusinessValidationException("排序失败，所选排序字段未获得");
		}
		Organization userOrg = ThreadVariable.getUser().getOrganization();
		PropertyDict userOrgLevel = userOrg.getOrgLevel();
		if (userOrgLevel == null
				|| userOrgLevel.getInternalId() < OrganizationLevel.CITY) {
			throw new BusinessValidationException("只有县级以上才能查看该报表");
		}
		List<UserActivateReport> list = getUserActivateReportListByMap(year,
				month, orgLevelDistance, userOrg);
		Collections.sort(list, new Comparator<UserActivateReport>() {
			@Override
			public int compare(UserActivateReport firstUserActivateReport,
					UserActivateReport secondUserActivateReport) {
				try {

					String firstLetter = sortName.substring(0, 1).toUpperCase();
					String getMethodName = "get" + firstLetter
							+ sortName.substring(1);
					Method getMethodForFirst = firstUserActivateReport
							.getClass()
							.getMethod(getMethodName, new Class[] {});
					Method getMethodForScoend = secondUserActivateReport
							.getClass()
							.getMethod(getMethodName, new Class[] {});

					String firstNumStr = String.valueOf(getMethodForFirst
							.invoke(firstUserActivateReport, new Object[] {}));
					String socendNumStr = String.valueOf(getMethodForScoend
							.invoke(secondUserActivateReport, new Object[] {}));
					Double firstNum = 0d;
					Double socendNum = 0d;
					if (StringUtil.isStringAvaliable(firstNumStr)
							&& StringUtil.isStringAvaliable(socendNumStr)) {
						firstNum = Double
								.parseDouble(firstNumStr.indexOf("%") == -1 ? firstNumStr
										: firstNumStr.substring(0,
												firstNumStr.indexOf("%")));
						socendNum = Double.parseDouble(socendNumStr
								.indexOf("%") == -1 ? socendNumStr
								: socendNumStr.substring(0,
										socendNumStr.indexOf("%")));
					}

					if (DialogMode.SORT_ASCENDING.equals(sort)) {
						return firstNum.compareTo(socendNum);// 升序
					} else {
						return socendNum.compareTo(firstNum);// 降序
					}

				} catch (Exception e) {
					return 0;
				}
			}
		});
		return bindCount(list);
	}
}
