package com.tianque.plugin.analysisNew.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.baseInfo.companyPlace.constant.IsKeyType;
import com.tianque.baseInfo.leaderViewCache.constants.LeaderViewCacheType;
import com.tianque.baseInfo.leaderViewCache.domain.LeaderViewCache;
import com.tianque.baseInfo.leaderViewCache.service.LeaderViewCacheService;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.analysisNew.dao.CompanyPlaceLeaderViewNewDAO;
import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.domain.HighchartDataColumnVo;
import com.tianque.plugin.analysisNew.domain.PopulationAreaDataVo;
import com.tianque.plugin.analysisNew.domain.PopulationDetailDataVo;
import com.tianque.plugin.analysisNew.helper.AnalyzStatisticsNewHelper;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analysisNew.util.PacketStatisticsHelper;
import com.tianque.plugin.analysisNew.util.PacketStatisticsTables;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;
import com.tianque.plugin.analyzing.domain.StatisticsNode;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tableManage.service.TableManageService;

/**
 * @Description:单位场所领导视图service实现类
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-19 上午10:09:55
 */
@Service("companyPlaceLeaderViewNewService")
@Transactional
public class CompanyPlaceLeaderViewNewServiceImpl implements
		CompanyPlaceLeaderViewNewService {
	public final static Logger logger = LoggerFactory
			.getLogger(CompanyPlaceLeaderViewNewServiceImpl.class);

	private static final String ROOT_ORGCODE = ".";
	private static final String ORGCODE_FIND_LEVEL = "0,3";
	private static final String ALL_COMPANY_STATISTICS = "all_company_statistics";
	@Autowired
	private CacheService cacheService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private CompanyPlaceLeaderViewNewDAO companyPlaceLeaderViewNewDAO;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private AnalyzStatisticsNewHelper analyzStatisticsNewHelper;
	@Autowired
	private TableManageService tableManageService;
	@Autowired
	private PacketStatisticsService packetStatisticsService;
	@Autowired
	private LeaderViewCacheService<StatisticsBaseInfoAddCountVo> leaderViewCacheService;

	@Override
	public List<StatisticsBaseInfoAddCountVo> statisticsBaseInfo(Long orgId,
			String moduleKey) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			if (moduleKey == null || "".equals(moduleKey)) {
				moduleKey = ModulTypes.ALL_COMPANY_PLACE_KEY;
			}
			String key = MemCacheConstant.getCurrentKey(orgId, moduleKey);
			List<StatisticsBaseInfoAddCountVo> countVos = leaderViewCacheService
					.getDatasByCacheKeyForJob(
							MemCacheConstant.LEADERVIEW_NAMESPACE, key,
							StatisticsBaseInfoAddCountVo.class);
			if (countVos == null) {
				Organization org = organizationDubboService
						.getSimpleOrgById(orgId);
				StatisticsNode root = AnalyseUtilNew.getRootNode(cacheService,
						organizationDubboService, org, false);
				List<StatisticsBaseInfoAddCountVo> countList = statisticsBaseInfoAddCurrentByKey(
						moduleKey, org.getOrgInternalCode());
				countVos = AnalyseUtilNew.totalResult(leaderViewCacheService,
						"单位场所-" + moduleKey, countList, root, null, key, false);
			}
			return countVos;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl的statisticsBaseInfo方法错误：",
					"领导视图 统计当前", e);
		}
	}

	@Override
	public List<StatisticsBaseInfoAddCountVo> statisticsSummary(Long orgId,
			String moduleKey) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		if (!StringUtil.isStringAvaliable(moduleKey)) {
			moduleKey = ModulTypes.ALL_COMPANY_PLACE_KEY;
		}
		try {
			// Organization currentOrg = organizationDubboService
			// .getSimpleOrgById(orgId);
			// String orgCode = currentOrg.getOrgInternalCode();
			String[] time = new String[12];
			time = analyzStatisticsNewHelper.sortTime(analyzStatisticsNewHelper
					.getTime(time, time.length));
			String catchKey = MemCacheConstant.getSummaryKey(moduleKey,
					time[0], orgId);
			return getStatisticsSummaryCacheValue(catchKey, orgId, time,
					moduleKey);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl的statisticsSummary方法错误：",
					"领导视图 线形图（历史）错误", e);
		}
	}

	private List<StatisticsBaseInfoAddCountVo> getStatisticsSummaryCacheValue(
			String cacheKey, Long orgId, String[] time, String moduleKey) {
		List<StatisticsBaseInfoAddCountVo> result = leaderViewCacheService
				.getDatasByCacheKeyForJob(
						MemCacheConstant.LEADERVIEW_NAMESPACE, cacheKey,
						StatisticsBaseInfoAddCountVo.class);
		if (result != null) {
			return result;
		} else {
			result = new ArrayList<StatisticsBaseInfoAddCountVo>();
			for (int j = 0; j < time.length; j++) {
				String strTime = time[j];
				String year = strTime.substring(0, strTime.indexOf("-"));
				String month = strTime.substring(strTime.indexOf("-") + 1);
				StatisticsBaseInfoAddCountVo vo = null;
				vo = createBaseinfo(moduleKey, orgId, strTime, year, month);
				vo.setStatisticsType(strTime);
				result.add(vo);
			}
			Collections.sort(result);
			leaderViewCacheService.addOrUpdateCacheByKey(
					MemCacheConstant.LEADERVIEW_NAMESPACE, new LeaderViewCache(
							cacheKey, result,
							LeaderViewCacheType.STATISTICS_SUMMARY_TYPE),
					StatisticsBaseInfoAddCountVo.class);
		}
		return result;
	}

	private StatisticsBaseInfoAddCountVo createBaseinfo(String moduleKey,
			Long orgId, String strTime, String year, String month) {
		tableManageService.createAnalyseTable(
				AnalyseUtilNew.COMPANY_PLACE_TABLE_NAME,
				AnalyseUtilNew.COMPANY_PLACE_TABLE_NAME_SQL,
				Integer.valueOf(year), Integer.valueOf(month));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", Integer.valueOf(year));
		map.put("month", Integer.valueOf(month));
		map.put("orgId", orgId);
		map.put("moduleKey", moduleKey);
		if (moduleKey == null || "".equals(moduleKey)
				|| ModulTypes.ALL_COMPANY_PLACE_KEY.equals(moduleKey)) {
			map.put("moduleKey", ModulTypes.ALL_COMPANY_PLACE_KEY);
			map.put("allModuleKeys", ModulTypes.allModuleKeys);
		}
		return companyPlaceLeaderViewNewDAO.getStatisticsSummary(map);
	}

	/**
	 * 根据orgId和对应的单位场所的类别去统计领导视图当月的数据
	 * 
	 * @param orgId
	 * @param moduleKey
	 *            【类型】
	 * @param key
	 *            【缓存对应的key】
	 * @param orgLevelInternalId
	 *            【组织机构对应的orgLevel的InternalId】
	 * @return
	 */
	private List<StatisticsBaseInfoAddCountVo> statisticsBaseInfoCurrentByKey(
			Long orgId, String moduleKey, int orgLevelInternalId) {
		List<Long> dictIds = analyzStatisticsNewHelper.getDictIds();
		int nowYear = CalendarUtil.getNowYear();
		int nowMonth = CalendarUtil.getNowMonth();
		Map<String, Object> map = new HashMap<String, Object>();
		String column = "";
		String module = "";
		String keyType = "";
		map.put("orgId", orgId);
		map.put("today", CalendarUtil.today());
		map.put("nextDay", CalendarUtil.getTomorrow());
		map.put("monthStart", CalendarUtil.getMonthStart(nowYear, nowMonth));
		map.put("nextMonthStart",
				CalendarUtil.getNextMonthStart(nowYear, nowMonth));
		map.put("dictIds", dictIds);
		map.put("orgLevelInternalId", orgLevelInternalId);
		keyType = getKeyTypeByModuleKey(moduleKey);
		if (ModulTypes.BUSINESS_COMPANY_PLACE.containsKey(moduleKey)) {// 业务信息
			// 要匹配的字段
			column = "isKeytype";
			// 判断是统计什么的
			module = "business";
		} else if (ModulTypes.SIZED_ENTERPRISE.containsKey(moduleKey)) {// 规模企业
			column = "scaleType";
			module = "companyplace";
		} else {
			column = "classIficationen";
			module = "companyplace";
		}
		if (!ModulTypes.ALL_COMPANY_PLACE_KEY.equals(moduleKey)) {
			map.put("column", column);
			map.put("keyType", keyType);
		}
		map.put("module", module);
		List<StatisticsBaseInfoAddCountVo> result = companyPlaceLeaderViewNewDAO
				.queryStatisticsBaseInfoCurrentByKeyForList(map);
		// Collections.sort(result);
		analyzStatisticsNewHelper.getLastResult(result);
		return result;
	}

	private List<StatisticsBaseInfoAddCountVo> statisticsBaseInfoAddCurrentByKey(
			String moduleKey, String orginternalcode) {
		List<Long> dictIds = analyzStatisticsNewHelper.getDictIds();
		int nowYear = CalendarUtil.getNowYear();
		int nowMonth = CalendarUtil.getNowMonth();
		Map<String, Object> map = new HashMap<String, Object>();
		String column = "";
		String module = "";
		String keyType = "";
		map.put("today", CalendarUtil.today());
		map.put("nextDay", CalendarUtil.getTomorrow());
		map.put("monthStart", CalendarUtil.getMonthStart(nowYear, nowMonth));
		map.put("nextMonthStart",
				CalendarUtil.getNextMonthStart(nowYear, nowMonth));
		map.put("dictIds", dictIds);
		map.put("orgInternalCode", orginternalcode);
		keyType = getKeyTypeByModuleKey(moduleKey);
		if (ModulTypes.BUSINESS_COMPANY_PLACE.containsKey(moduleKey)) {// 业务信息
			// 要匹配的字段
			column = "isKeytype";
			// 判断是统计什么的
			module = "business";
		} else if (ModulTypes.SIZED_ENTERPRISE.containsKey(moduleKey)) {// 规模企业
			column = "scaleType";
			module = "companyplace";
		} else {
			column = "classIficationen";
			module = "companyplace";
		}
		if (!ModulTypes.ALL_COMPANY_PLACE_KEY.equals(moduleKey)) {
			map.put("column", column);
			map.put("keyType", keyType);
		}
		map.put("module", module);
		map.put("charToday", CalendarUtil.format(new Date()));
		String dateFormat = "yyyy-MM";
		map.put("yearMonth", CalendarUtil.format(dateFormat, new Date()));
		List<StatisticsBaseInfoAddCountVo> result = companyPlaceLeaderViewNewDAO
				.queryStatisticsBaseInfoAddCurrentByKeyForList(map);
		// Collections.sort(result);
		// analyzStatisticsNewHelper.getLastResult(result);
		return result;
	}

	/**
	 * 判断要匹配的条件的值根据moduleKey
	 * 
	 * @param moduleKey
	 * @return
	 */
	private String getKeyTypeByModuleKey(String modulKey) {
		String result = "";
		if (null != modulKey && !"".equals(modulKey)) {
			if (modulKey.equals(ModulTypes.ENTERPRISE_KEY)) {
				// 规上
				PropertyDict modul = propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.ENTERPRISE_TYPE,
								ModulTypes.Enterprise);
				result = String.valueOf(modul.getId());
			} else if (modulKey.equals(ModulTypes.ENTERPRISEDOWN_KEY)) {
				// 规下
				PropertyDict modul = propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.ENTERPRISE_TYPE,
								ModulTypes.EnterpriseDown);
				result = String.valueOf(modul.getId());
			} else if (modulKey.equals(ModulTypes.SAFETYPRODUCTIONKEY_KEY)) {
				// 安全生产重点
				result = String.valueOf(IsKeyType.PRODUCTION_KEY_TYPE);
			} else if (modulKey.equals(ModulTypes.FIRESAFETYKEY_KEY)) {
				// 消防安全重点
				result = String.valueOf(IsKeyType.FIRESAFETY_KEY_TYPE);
			} else if (modulKey.equals(ModulTypes.SECURITYKEY_KEY)) {
				// 治安重点
				result = String.valueOf(IsKeyType.SECURITY_KEY_TYPE);
			} else if (modulKey.equals(ModulTypes.POLLUTIONSOURCE_KEY)) {
				// 污染源
				result = String.valueOf(IsKeyType.POLLUTION_SOURCE_TYPE);
			} else {
				result = modulKey;
			}
		}
		return result;
	}

	@Override
	public void companyPlaceLeaderViewStatistics() {
		try {
			List<PropertyDict> orgTypes = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							OrganizationType.ORG_TYPE_KEY,
							OrganizationType.ADMINISTRATIVE_REGION);
			if (orgTypes != null && orgTypes.size() > 0
					&& orgTypes.get(0) != null) {
				Long orgTypeId = orgTypes.get(0).getId();
				List<Organization> allTownUpAdministrativeOrgs = organizationDubboService
						.findDistrictAdminOrgsByOrgType(orgTypeId,
								ROOT_ORGCODE, ORGCODE_FIND_LEVEL);
				String key = "";
				for (Organization org : allTownUpAdministrativeOrgs) {// 所有的县级以上的组织机构（包括中国）
					for (String moduleKey : ModulTypes.allCompanyPlaceMapKey) {// 所有的类型（不包括总的单位场所）
						List<StatisticsBaseInfoAddCountVo> countList = statisticsBaseInfoCurrentByKey(
								org.getId(), moduleKey,
								OrganizationLevel.DISTRICT);
						key = MemCacheConstant.getCurrentKey(org.getId(),
								moduleKey);
						cacheService.set(MemCacheConstant.LEADERVIEW_NAMESPACE,
								key, 36000, countList);
					}
					// 总的单位场所
					List<StatisticsBaseInfoAddCountVo> countList = statisticsBaseInfoCurrentByKey(
							org.getId(), ModulTypes.ALL_COMPANY_PLACE_KEY,
							OrganizationLevel.DISTRICT);
					key = AnalyseUtilNew.STATCOUNTBYORGIDKEY + org.getId()
							+ ModulTypes.ALL_COMPANY_PLACE_KEY;
					cacheService.set(MemCacheConstant.LEADERVIEW_NAMESPACE,
							key, 36000, countList);

				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl的companyPlaceLeaderViewStatistics方法错误：",
					"单位场所领导视图当月数据统计job(每隔一段时间统计一次然后放到缓存，只针对县级以上)出现错误", e);
		}
	}

	public void companyPlaceLeaderViewStatisticsforAdd() {
		try {
			// 直接查询 所有的网格数据
			Organization country = organizationDubboService
					.getRootOrganization();
			// 各种类型的单位场所
			List<String> companyPlaceMayKey = new ArrayList<String>(
					ModulTypes.allCompanyPlaceMapKey);
			companyPlaceMayKey.add(ModulTypes.ALL_COMPANY_PLACE_KEY);
			for (String moduleKey : companyPlaceMayKey) {
				StatisticsNode root = AnalyseUtilNew.getRootNode(cacheService,
						organizationDubboService, country, true);
				List<StatisticsBaseInfoAddCountVo> countList = statisticsBaseInfoAddCurrentByKey(
						moduleKey, country.getOrgInternalCode());
				AnalyseUtilNew.totalResult(leaderViewCacheService, "单位场所-"
						+ moduleKey, countList, root, moduleKey, null, true);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl的companyPlaceLeaderViewStatistics方法错误：单位场所领导视图当月数据统计job(每隔一段时间统计一次然后放到缓存,全部层级)出现错误",
					e);
		}
	}

	@Override
	public void companyPlaceAnalyzStatistics() {
		try {
			int nowYear = CalendarUtil.getNowYear();
			int nowMonth = CalendarUtil.getNowMonth();
			addCompanyPlaceAnalyzStatisticsByTime(null, null, null, nowYear,
					nowMonth);
			packetStatisticsService
					.packetStatisticsByTableAndGroupType(PacketStatisticsHelper
							.createPacketStatisticsVo(
									nowYear,
									nowMonth,
									PacketStatisticsTables.COMPANYPLACESTATTYPE_KEY));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl的companyPlaceAnalyzStatistics方法错误：",
					"单位场所研判分析统计统计历史月份的数据出现错误", e);
		}
	}

	/**
	 * 根据开始和结束时间统计单位场所当前月份的数据
	 * 
	 * @param monthStart
	 * @param nextMonthStart
	 */
	@Override
	public void addCompanyPlaceAnalyzStatisticsByTime(Long orgId,
			String orgInternalCode, List<String> moduleKeys, Integer nowYear,
			Integer nowMonth) {
		if (nowMonth == null || nowYear == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			// 判断表是否存在，不存在创建表
			tableManageService.createAnalyseTable(
					AnalyseUtilNew.COMPANY_PLACE_TABLE_NAME,
					AnalyseUtilNew.COMPANY_PLACE_TABLE_NAME_SQL, nowYear,
					nowMonth);
			companyPlaceLeaderViewNewDAO
					.deleteCompanyPlaceAnalyzStatistics(getDeleteMap(null,
							null, null, nowYear, nowMonth));
			for (String moduleKey : ModulTypes.allCompanyPlaceMapKey) {
				addCompanyPlaceAnalyzStatisticsByTimeAndModuleKey(null,
						nowYear, nowMonth, moduleKey);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl的addCompanyPlaceAnalyzStatisticsByTime方法错误：",
					"单位场所研判分析根据开始和结束时间统计单位场所当前月份的数据出现错误", e);
		}
	}

	private Map<String, Object> getDeleteMap(Long orgId,
			String orgInternalCode, List<String> moduleKeys, Integer year,
			Integer month) {
		Map<String, Object> map = null;
		if (year != null && month != null) {
			map = new HashMap<String, Object>();
			map.put("year", year);
			map.put("month", month);
			if (orgInternalCode != null && !"".equals(orgInternalCode)) {
				map.put("orgInternalCode", orgInternalCode);
			}
			if (orgId == null) {
				map.put("orgId", orgId);
			}
			if (moduleKeys != null) {
				map.put("moduleKeys", moduleKeys);
			}
		}
		return map;
	}

	/**
	 * 根据开始和结束时间和类别，统计出所有的网格的数据
	 * 
	 * @param monthStart
	 * @param nextMonthStart
	 * @param moduleKey
	 */
	private void addCompanyPlaceAnalyzStatisticsByTimeAndModuleKey(
			String orgInternalCode, Integer nowYear, Integer nowMonth,
			String moduleKey) {
		String column = "";
		String module = "";
		String keyType = "";
		Map<String, Object> map = new HashMap<String, Object>();
		Date monthStart = CalendarUtil.getMonthStart(nowYear, nowMonth);
		Date nextMonthStart = CalendarUtil.getNextMonthStart(nowYear, nowMonth);
		map.put("monthStart", monthStart);
		map.put("nextMonthStart", nextMonthStart);
		map.put("year", nowYear);
		map.put("month", nowMonth);
		map.put("startDate", monthStart);
		map.put("endDate", nextMonthStart);
		map.put("baseinfoType", moduleKey);
		keyType = getKeyTypeByModuleKey(moduleKey);
		if (ModulTypes.BUSINESS_COMPANY_PLACE.containsKey(moduleKey)) {// 业务信息
			// 要匹配的字段
			column = "isKeytype";
			// 判断是统计什么的
			module = "business";
		} else if (ModulTypes.SIZED_ENTERPRISE.containsKey(moduleKey)) {// 规模企业
			column = "scaleType";
			module = "companyplace";
		} else {
			column = "classIficationen";
			module = "companyplace";
		}
		if (!ModulTypes.ALL_COMPANY_PLACE_KEY.equals(moduleKey)) {
			map.put("column", column);
			map.put("keyType", keyType);
		}
		map.put("module", module);
		if (orgInternalCode != null && "".equals(orgInternalCode)) {
			map.put("orgInternalCode", orgInternalCode);
		}
		List<PropertyDict> orgTypes = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.ADMINISTRATIVE_REGION);
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.GRID);
		if (orgTypes != null && orgLevels != null && orgTypes.size() > 0
				&& orgLevels.size() > 0 && orgTypes.get(0) != null
				&& orgLevels.get(0) != null) {
			map.put("orgType", orgTypes.get(0).getId());
			map.put("orgLevel", orgLevels.get(0).getId());
			companyPlaceLeaderViewNewDAO
					.addCompanyPlaceAnalyzStatisticsByTimeAndModuleKey(map);

		}

	}

	@Override
	public HighchartColumnVo getCompanyPlaceColumnByOrgIdAndType(Long orgId,
			String moduleType) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		try {
			HighchartColumnVo highchartColumn = new HighchartColumnVo();

			if (moduleType == null || "".equals(moduleType)) {
				highchartColumn.setModuleName(ModulTypes.CompanyPlace);
			} else {
				highchartColumn.setModuleName(ModulTypes.MODULE_NAME
						.get(moduleType));
			}

			getCompanyPlaceColumnDate(highchartColumn, orgId, moduleType);
			return highchartColumn;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl的getCompanyPlaceColumnByOrgIdAndType方法错误：",
					"单位场所根据orgId和类型查询单位场所的区域分布图（当月）出现错误", e);
		}
	}

	private void getCompanyPlaceColumnDate(HighchartColumnVo highchartColumn,
			Long orgId, String moduleType) {
		List<String> categories = new ArrayList<String>();
		List<Integer> datas = new ArrayList<Integer>();
		List<Map<String, Object>> list = companyPlaceLeaderViewNewDAO
				.queryColumnDateByMapForList(getColumnDateMap(orgId, moduleType));

		for (int i = 0; i < list.size(); i++) {
			String orgName = (String) list.get(i).get("ORGNAME");
			Integer sum = ((BigDecimal) list.get(i).get("SUM")).intValue();
			if (!categories.contains(orgName)) {
				categories.add(orgName);
			}
			// 每一次list的长度都一样 所以第一次把数据填充上去 以后每次都是相加
			if (datas.size() < i + 1) {
				datas.add(sum);
			} else {
				datas.set(i, datas.get(i) + sum);
			}
		}

		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		positiveinfoColumnData.setName(highchartColumn.getModuleName());
		positiveinfoColumnData.setData(ArrayUtils.toPrimitive(datas
				.toArray(new Integer[datas.size()])));
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		positiveinfoColumns.add(positiveinfoColumnData);
		highchartColumn.setCategories(categories.toArray(new String[categories
				.size()]));
		highchartColumn.setSeries(positiveinfoColumns);
	}

	/**
	 * 实时查询当月的区域分布图的参数map
	 * 
	 * @param orgId
	 * @param moduleType
	 * @return
	 */
	private Map<String, Object> getColumnDateMap(Long orgId, String moduleType) {
		Map<String, Object> map = null;
		if (orgId != null) {
			map = new HashMap<String, Object>();
			map.put("orgId", orgId);
		}
		if (moduleType != null && !"".equals(moduleType)) {
			map.put("moduleType", moduleType);
			if ("place".equals(moduleType)) {
				map.put("column", "classIficationen");
				map.put("keyTypes",
						convertMapKeyToList(ModulTypes.PLACE_COMPANY_PLACE));

			} else if ("company".equals(moduleType)) {
				map.put("column", "classIficationen");
				map.put("keyTypes",
						convertMapKeyToList(ModulTypes.COMPANY_COMPANY_PLACE));
			} else if ("enterprise".equals(moduleType)) {
				map.put("column", "scaleType");
				map.put("keyTypes", getEnterpriseTypeIds());
			} else if ("keyCompanyPlace".equals(moduleType)) {
				map.put("column", "isKeytype");
				map.put("keyTypes", getKeyTypes());
			}
		}
		map.put("orgType", getOrgType());
		return map;
	}

	/**
	 * 实时查询历史月的区域分布图的参数map
	 * 
	 * @param orgId
	 * @param moduleType
	 * @return
	 */
	private Map<String, Object> getHistoricalColumnDateMap(Long orgId,
			String moduleType) {
		Map<String, Object> map = null;
		if (orgId != null) {
			map = new HashMap<String, Object>();
			map.put("orgId", orgId);
		}
		if (moduleType != null && !"".equals(moduleType)) {
			map.put("moduleType", moduleType);
			if ("place".equals(moduleType)) {
				map.put("column", "classIficationen");
				map.put("keyTypes",
						convertMapKeyToList(ModulTypes.PLACE_COMPANY_PLACE));

			} else if ("company".equals(moduleType)) {
				map.put("column", "classIficationen");
				map.put("keyTypes",
						convertMapKeyToList(ModulTypes.COMPANY_COMPANY_PLACE));
			} else if ("enterprise".equals(moduleType)) {
				map.put("column", "scaleType");
				map.put("keyTypes",
						convertMapKeyToList(ModulTypes.SIZED_ENTERPRISE));
			} else if ("keyCompanyPlace".equals(moduleType)) {
				map.put("column", "isKeytype");
				map.put("keyTypes",
						convertMapKeyToList(ModulTypes.BUSINESS_COMPANY_PLACE));
			}
		}
		map.put("orgType", getOrgType());
		return map;
	}

	/**
	 * 获取企业类型字典项的id的集合
	 * 
	 * @return
	 */
	private List<Long> getEnterpriseTypeIds() {
		List<PropertyDict> enterpriseTypesDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ENTERPRISE_TYPE);
		List<Long> ids = new ArrayList<Long>();
		for (PropertyDict propertyDict : enterpriseTypesDicts) {
			ids.add(propertyDict.getId());
		}
		return ids;
	}

	private List<Long> getKeyTypes() {
		List<Long> ids = new ArrayList<Long>();
		ids.add(IsKeyType.PRODUCTION_KEY_TYPE);
		ids.add(IsKeyType.FIRESAFETY_KEY_TYPE);
		ids.add(IsKeyType.SECURITY_KEY_TYPE);
		ids.add(IsKeyType.POLLUTION_SOURCE_TYPE);
		return ids;

	}

	@Override
	public HighchartColumnVo getCompanyPlaceColumnByTime(Long orgId,
			String moduleType, int year, int month) {
		if (year == 0 || month == 0 || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}

		String keyName = "";
		if (!StringUtil.isStringAvaliable(moduleType)) {
			keyName = ALL_COMPANY_STATISTICS;
		} else {
			keyName = moduleType;
		}
		String key = MemCacheConstant.getColumnByTimeKey(orgId, keyName,
				MemCacheConstant.STATISTICSCOLUMN, year, month);
		HighchartColumnVo highchartColumnVo = (HighchartColumnVo) cacheService
				.get(MemCacheConstant.LEADERVIEW_NAMESPACE, key);
		if (highchartColumnVo != null) {
			return highchartColumnVo;
		}

		try {
			tableManageService.createAnalyseTable(
					AnalyseUtilNew.COMPANY_PLACE_TABLE_NAME,
					AnalyseUtilNew.COMPANY_PLACE_TABLE_NAME_SQL,
					Integer.valueOf(year), Integer.valueOf(month));
			HighchartColumnVo highchartColumn = new HighchartColumnVo();

			if (moduleType == null || "".equals(moduleType)) {
				highchartColumn.setModuleName(ModulTypes.CompanyPlace);
			} else {
				highchartColumn.setModuleName(ModulTypes.MODULE_NAME
						.get(moduleType));
			}
			getCompanyPlaceColumnDateByTime(highchartColumn, orgId, moduleType,
					year, month);
			cacheService.set(MemCacheConstant.LEADERVIEW_NAMESPACE, key,
					ModulTypes.CACHETIME, highchartColumn);
			return highchartColumn;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl的getCompanyPlaceColumnByTime方法错误：",
					"单位场所根据orgId、类型、年、月查询区域分布图（历史月份）出现错误", e);
		}
	}

	/**
	 * 区域分布历史
	 * 
	 * @param highchartColumn
	 * @param orgId
	 * @param moduleType
	 * @param year
	 * @param month
	 */
	private void getCompanyPlaceColumnDateByTime(
			HighchartColumnVo highchartColumn, Long orgId, String moduleType,
			int year, int month) {
		List<String> categories = new ArrayList<String>();
		List<Integer> datas = new ArrayList<Integer>();

		Map<String, Object> map = getHistoricalColumnDateMap(orgId, moduleType);
		if (map != null) {
			map.put("year", year);
			map.put("month", month);
			if (moduleType == null || "".equals(moduleType)) {
				map.put("keyTypes", ModulTypes.allModuleKeys);
			}
		}
		List<Map<String, Object>> list = companyPlaceLeaderViewNewDAO
				.queryColumnDateByTimeMapForList(map);

		for (int i = 0; i < list.size(); i++) {
			String orgName = (String) list.get(i).get("ORGNAME");
			Integer sum = ((BigDecimal) list.get(i).get("SUM")).intValue();
			if (!categories.contains(orgName)) {
				categories.add(orgName);
			}
			// 每一次list的长度都一样 所以第一次把数据填充上去 以后每次都是相加
			if (datas.size() < i + 1) {
				datas.add(sum);
			} else {
				datas.set(i, datas.get(i) + sum);
			}
		}

		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		positiveinfoColumnData.setName(highchartColumn.getModuleName());
		positiveinfoColumnData.setData(ArrayUtils.toPrimitive(datas
				.toArray(new Integer[datas.size()])));
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		positiveinfoColumns.add(positiveinfoColumnData);
		highchartColumn.setCategories(categories.toArray(new String[categories
				.size()]));
		highchartColumn.setSeries(positiveinfoColumns);

	}

	@Override
	public List<PopulationAreaDataVo> getCurrentAreaDate(Long orgId,
			String moduleType, Integer orgLevelDistance) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			/** 总况的列表信息（只统计单位和场所） */
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (moduleType == null || "".equals(moduleType)) {
				return getCompanyAndPlaceCurrentAreaDate(orgId,
						org.getOrgInternalCode(), orgLevelDistance);
			} else {// 对应的各个大类的数据(单位、场所、业务、企业)
				return getSoleCurrentAreaDate(orgId, moduleType);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl类的getCurrentAreaDate方法错误：",
					"查询当月的列表数据错误", e);
		}
	}

	/**
	 * 总况的列表信息（只统计单位和场所）
	 * 
	 * @param orgId
	 * @return
	 */
	private List<PopulationAreaDataVo> getCompanyAndPlaceCurrentAreaDate(
			Long orgId, String orgInternalCode, Integer orgLevelDistance) {

		List<PopulationAreaDataVo> populationAreaDataVos = new ArrayList<PopulationAreaDataVo>();
		int total = 0;
		Map<String, Object> map = null;
		for (Entry<String, String> entry : ModulTypes.COMPANY_AND_PLACE
				.entrySet()) {
			map = getColumnDateMap(orgId, entry.getKey());
			if (orgLevelDistance != null && orgLevelDistance == 2) {// 显示第二视图
				map.put("orgInternalCode", orgInternalCode);
				map.put("orgLevelDistance", orgLevelDistance);
			}
			List<Map<String, Object>> list = companyPlaceLeaderViewNewDAO
					.queryColumnDateByMapForList(map);
			for (int i = 0; i < list.size(); i++) {
				Long orginternalId = ((BigDecimal) list.get(i).get("ORGID"))
						.longValue();
				String orgCode = (String) list.get(i).get("ORGCODE");
				String orgName = (String) list.get(i).get("ORGNAME");
				Integer sum = ((BigDecimal) list.get(i).get("SUM")).intValue();
				total = total + sum;
				PopulationAreaDataVo areaDate = createPopulationAreaDatas(
						orginternalId, orgCode, orgName, populationAreaDataVos);
				createCompanyPlaceDetailDate(
						ModulTypes.COMPANY_AND_PLACE.get(entry.getKey()), sum,
						areaDate, null);
			}
		}

		// 添加每个区域合计行
		countAreaNumber(populationAreaDataVos);

		PopulationAreaDataVo areaDateTotal = createLastTotal(total);
		populationAreaDataVos.add(areaDateTotal);
		return populationAreaDataVos;
	}

	private PopulationAreaDataVo createLastTotal(int countNum) {
		PopulationAreaDataVo areaDateTotal = new PopulationAreaDataVo();
		Organization organization = new Organization();
		organization.setOrgName("合计");
		areaDateTotal.setOrg(organization);
		areaDateTotal
				.setPopulationDetailDatas((List) new ArrayList<PopulationAreaDataVo>());
		PopulationDetailDataVo detailDateTotal = new PopulationDetailDataVo();
		detailDateTotal.setName("/");
		detailDateTotal.setAmount(countNum);
		detailDateTotal.setShowLink(false);
		if (countNum == 0) {
			detailDateTotal
					.setAmountPercentage(Double
							.parseDouble(new java.text.DecimalFormat("#.00")
									.format(0)));
		} else {
			detailDateTotal
					.setAmountPercentage(Double
							.parseDouble(new java.text.DecimalFormat("#.00")
									.format(1)));
		}
		areaDateTotal.getPopulationDetailDatas().add(detailDateTotal);
		return areaDateTotal;
	}

	private void countAreaNumber(
			List<PopulationAreaDataVo> populationAreaDataVos) {
		for (int i = 0; i < populationAreaDataVos.size(); i++) {
			PopulationDetailDataVo temp = new PopulationDetailDataVo();
			temp.setName("合计");
			temp.setShowLink(false);
			temp.setAmount(populationAreaDataVos.get(i).getAmount());
			temp.setAmountPercentage(Double
					.parseDouble(new java.text.DecimalFormat("#.00").format(1)));
			populationAreaDataVos.get(i).getPopulationDetailDatas().add(temp);
			for (int j = 0; j < populationAreaDataVos.get(i)
					.getPopulationDetailDatas().size(); j++) {
				populationAreaDataVos
						.get(i)
						.getPopulationDetailDatas()
						.get(j)
						.setAmountPercentage(
								Double.parseDouble(new java.text.DecimalFormat(
										"#.0000").format(populationAreaDataVos
										.get(i).getPopulationDetailDatas()
										.get(j).getAmount()
										/ (double) (temp.getAmount() == 0 ? 1
												: temp.getAmount()))));

			}

		}
	}

	private PopulationAreaDataVo createPopulationAreaDatas(Long orginternalId,
			String orgCode, String orgName,
			List<PopulationAreaDataVo> populationAreaDataVos) {
		PopulationAreaDataVo areaDate = exist(orginternalId,
				populationAreaDataVos);
		if (areaDate == null) {
			Organization org = new Organization();
			org.setId(orginternalId);
			org.setOrgName(orgName);
			org.setOrgInternalCode(orgCode);
			areaDate = new PopulationAreaDataVo();
			areaDate.setOrg(org);
			areaDate.setOrgId(orginternalId);
			areaDate.setPopulationDetailDatas((List) new ArrayList<PopulationAreaDataVo>());
			populationAreaDataVos.add(areaDate);
		}
		return areaDate;

	}

	private PopulationAreaDataVo exist(Long orginternalId,
			List<PopulationAreaDataVo> populationAreaDataVos) {
		for (int i = 0; i < populationAreaDataVos.size(); i++) {
			if (populationAreaDataVos.get(i).getOrgId().equals(orginternalId)) {
				return populationAreaDataVos.get(i);
			}
		}
		return null;
	}

	private void createCompanyPlaceDetailDate(String populationType,
			Integer sum, PopulationAreaDataVo areaDate, Integer total) {
		PopulationDetailDataVo detailDate = new PopulationDetailDataVo();
		detailDate.setName(populationType);
		detailDate.setAmount(sum);
		if (total != null && total != 0) {
			detailDate.setAmountPercentage(Double
					.parseDouble(new java.text.DecimalFormat("#.0000")
							.format(sum / (double) total)));
		}
		areaDate.getPopulationDetailDatas().add(detailDate);
		if (areaDate.getAmount() != null) {
			areaDate.setAmount(areaDate.getAmount() + sum);
		} else {
			areaDate.setAmount(sum);
		}
	}

	/**
	 * 对应的各个大类的数据(单位、场所、业务、企业)
	 * 
	 * @param orgId
	 * @param moduleType
	 * @return
	 */
	private List<PopulationAreaDataVo> getSoleCurrentAreaDate(Long orgId,
			String moduleType) {
		List<Map<String, Object>> list = companyPlaceLeaderViewNewDAO
				.queryColumnDateByMapForList(getColumnDateMap(orgId, moduleType));
		List<PopulationAreaDataVo> populationAreaDataVos = new ArrayList<PopulationAreaDataVo>();
		int total = 0;
		for (int i = 0; i < list.size(); i++) {
			Long orginternalId = ((BigDecimal) list.get(i).get("ORGID"))
					.longValue();
			String orgCode = (String) list.get(i).get("ORGCODE");
			String orgName = (String) list.get(i).get("ORGNAME");
			Integer sum = ((BigDecimal) list.get(i).get("SUM")).intValue();
			total = total + sum;
			PopulationAreaDataVo areaDate = createPopulationAreaDatas(
					orginternalId, orgCode, orgName, populationAreaDataVos);
			createCompanyPlaceDetailDate(
					ModulTypes.MODULE_NAME.get(moduleType), sum, areaDate, null);
		}
		// 添加每个区域合计行
		countAreaNumber(populationAreaDataVos);

		PopulationAreaDataVo areaDateTotal = createLastTotal(total);
		populationAreaDataVos.add(areaDateTotal);
		return populationAreaDataVos;
	}

	@Override
	public List<PopulationAreaDataVo> getAreaDateByDate(Long orgId,
			String moduleType, int year, int month, Integer orgLevelDistance) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		String keyName = "";
		if (!StringUtil.isStringAvaliable(moduleType)) {
			if (orgLevelDistance != null) {
				keyName = ALL_COMPANY_STATISTICS + orgLevelDistance;
			} else {
				keyName = ALL_COMPANY_STATISTICS;
			}
		} else {
			keyName = moduleType;
		}
		String key = MemCacheConstant.getColumnByTimeKey(orgId, keyName,
				MemCacheConstant.STATISTICSTABLELIST, year, month);
		List<PopulationAreaDataVo> list = (List<PopulationAreaDataVo>) cacheService
				.get(MemCacheConstant.LEADERVIEW_NAMESPACE, key);
		if (list != null) {
			return list;
		}
		try {
			/** 总况的列表信息（只统计单位和场所） */
			Organization org = organizationDubboService.getSimpleOrgById(orgId);

			if (moduleType == null || "".equals(moduleType)) {
				list = getCompanyAndPlaceCurrentAreaDateByTime(orgId,
						org.getOrgInternalCode(), orgLevelDistance, year, month);
			} else {// 对应的各个大类的数据(单位、场所、业务、企业)
				list = getSoleCurrentAreaDateByTime(orgId, moduleType, year,
						month);
			}

			cacheService.set(MemCacheConstant.LEADERVIEW_NAMESPACE, key,
					ModulTypes.CACHETIME, list);
			return list;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl类的getAreaDateByDate方法错误：",
					"查询当月的列表数据错误", e);
		}
	}

	/**
	 * 列表历史记录
	 * 
	 * @param orgId
	 * @param month
	 * @param year
	 * @return
	 */
	private List<PopulationAreaDataVo> getSoleCurrentAreaDateByTime(Long orgId,
			String moduleType, int year, int month) {
		Map<String, Object> map = getHistoricalColumnDateMap(orgId, moduleType);
		if (map != null) {
			map.put("year", year);
			map.put("month", month);
		}
		List<Map<String, Object>> list = companyPlaceLeaderViewNewDAO
				.queryColumnDateByTimeMapForList(map);
		List<PopulationAreaDataVo> populationAreaDataVos = new ArrayList<PopulationAreaDataVo>();
		int total = 0;
		for (int i = 0; i < list.size(); i++) {
			Long orginternalId = ((BigDecimal) list.get(i).get("ORGID"))
					.longValue();
			String orgCode = (String) list.get(i).get("ORGCODE");
			String orgName = (String) list.get(i).get("ORGNAME");
			Integer sum = ((BigDecimal) list.get(i).get("SUM")).intValue();
			total = total + sum;
			PopulationAreaDataVo areaDate = createPopulationAreaDatas(
					orginternalId, orgCode, orgName, populationAreaDataVos);
			createCompanyPlaceDetailDate(
					ModulTypes.MODULE_NAME.get(moduleType), sum, areaDate, null);
		}
		// 添加每个区域合计行
		countAreaNumber(populationAreaDataVos);

		PopulationAreaDataVo areaDateTotal = createLastTotal(total);
		populationAreaDataVos.add(areaDateTotal);
		return populationAreaDataVos;
	}

	/**
	 * 列表历史记录
	 * 
	 * @param orgId
	 * @param string
	 * @param month
	 * @param year
	 * @param month
	 * @return
	 */
	private List<PopulationAreaDataVo> getCompanyAndPlaceCurrentAreaDateByTime(
			Long orgId, String orgInternalCode, Integer orgLevelDistance,
			int year, int month) {
		List<PopulationAreaDataVo> populationAreaDataVos = new ArrayList<PopulationAreaDataVo>();
		int total = 0;
		Map<String, Object> map = null;
		for (Entry<String, String> entry : ModulTypes.COMPANY_AND_PLACE
				.entrySet()) {
			map = getHistoricalColumnDateMap(orgId, entry.getKey());
			if (map != null) {
				map.put("year", year);
				map.put("month", month);
			}
			if (orgLevelDistance != null && orgLevelDistance == 2) {// 显示第二视图
				map.put("orgInternalCode", orgInternalCode);
				map.put("orgLevelDistance", orgLevelDistance);
			}

			List<Map<String, Object>> list = companyPlaceLeaderViewNewDAO
					.queryColumnDateByTimeMapForList(map);

			for (int i = 0; i < list.size(); i++) {
				Long orginternalId = ((BigDecimal) list.get(i).get("ORGID"))
						.longValue();
				String orgCode = (String) list.get(i).get("ORGCODE");
				String orgName = (String) list.get(i).get("ORGNAME");
				Integer sum = ((BigDecimal) list.get(i).get("SUM")).intValue();
				total = total + sum;
				PopulationAreaDataVo areaDate = createPopulationAreaDatas(
						orginternalId, orgCode, orgName, populationAreaDataVos);
				createCompanyPlaceDetailDate(
						ModulTypes.COMPANY_AND_PLACE.get(entry.getKey()), sum,
						areaDate, null);
			}
		}

		// 添加每个区域合计行
		countAreaNumber(populationAreaDataVos);

		PopulationAreaDataVo areaDateTotal = createLastTotal(total);
		populationAreaDataVos.add(areaDateTotal);
		return populationAreaDataVos;
	}

	@Override
	public void addStatisticsCompanyPlace(int year, int month, Long orgId,
			String moduleType) {
		try {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org != null) {

				addCompanyPlaceAnalyzStatisticsByTime(orgId,
						org.getOrgInternalCode(), null, year, month);
			}
			packetStatisticsService
					.packetStatisticsByTableAndGroupType(PacketStatisticsHelper
							.createPacketStatisticsVo(
									year,
									month,
									PacketStatisticsTables.COMPANYPLACESTATTYPE_KEY));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl的addStatisticsCompanyPlace方法错误：",
					"单位场所根据类别和orgId和时间统计出当前月的数据生成报表出现错误", e);
		}

	}

	@Override
	public HighchartColumnVo findTendencyChart(String moduleType, Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		String keyName = "";
		if (!StringUtil.isStringAvaliable(moduleType)) {
			keyName = ALL_COMPANY_STATISTICS;
		} else {
			keyName = moduleType;
		}
		String key = MemCacheConstant.getColumnByTimeKey(orgId, keyName,
				MemCacheConstant.STATISTICSTABLELIST);
		HighchartColumnVo highchartColumnVo = (HighchartColumnVo) cacheService
				.get(MemCacheConstant.LEADERVIEW_NAMESPACE, key);
		if (highchartColumnVo != null) {
			return highchartColumnVo;
		}
		try {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				throw new BusinessValidationException("参数错误");
			}
			String orgInternalCode = org.getOrgInternalCode();
			highchartColumnVo = new HighchartColumnVo();
			List<HighchartDataColumnVo> highchartDataColumnVos = new ArrayList<HighchartDataColumnVo>();
			String[] time = new String[12];
			time = analyzStatisticsNewHelper.getTime(time, time.length);

			highchartColumnVo.setCategories(time);
			// 单位场所
			isExistTable(time);
			highchartColumnVo = judgeHighchartDataColumn(moduleType, orgId,
					highchartColumnVo, highchartDataColumnVos, time);
			cacheService.set(MemCacheConstant.LEADERVIEW_NAMESPACE, key,
					ModulTypes.CACHETIME, highchartColumnVo);
			return highchartColumnVo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl的findTendencyChart方法错误：",
					"单位场所趋势图出现错误", e);
		}

	}

	private void isExistTable(String[] time) {
		for (int i = 0; i < time.length; i++) {
			String[] data = time[i].split("-");
			int year = Integer.valueOf(data[0]);
			int month = Integer.valueOf(data[1]);

			tableManageService.createAnalyseTable(
					AnalyseUtilNew.COMPANY_PLACE_TABLE_NAME,
					AnalyseUtilNew.COMPANY_PLACE_TABLE_NAME_SQL, year, month);
		}
	}

	private HighchartColumnVo judgeHighchartDataColumn(String moduleType,
			Long orgId, HighchartColumnVo highchartColumnVo,
			List<HighchartDataColumnVo> highchartDataColumnVos, String[] time) {
		if (moduleType == null || "".equals(moduleType)) {// 总况
			highchartColumnVo
					.setModuleName(ModulTypes.CompanyPlace + "总况趋势增长图");
			highchartColumnVo.setSeries(getAllHighchartDataColumnVo(true,
					orgId, time, highchartDataColumnVos, null));
		} else {
			highchartColumnVo.setModuleName(ModulTypes.MODULE_NAME
					.get(moduleType) + "增长图");
			highchartColumnVo.setSeries(getAllHighchartDataColumnVo(false,
					orgId, time, highchartDataColumnVos, moduleType));
		}
		return highchartColumnVo;
	}

	private List<HighchartDataColumnVo> getAllHighchartDataColumnVo(
			boolean all, Long orgId, String[] time,
			List<HighchartDataColumnVo> highchartDataColumnVos,
			String moduleType) {
		highchartDataColumnVos.add(getHighchartDataColumnVo(all, moduleType,
				orgId, time));
		return highchartDataColumnVos;
	}

	private HighchartDataColumnVo getHighchartDataColumnVo(boolean all,
			String moduleType, Long orgId, String[] time) {
		HighchartDataColumnVo highchartDataColumnVo = new HighchartDataColumnVo();
		highchartDataColumnVo.setData(tendencyImportChart(moduleType, time,
				orgId));
		if (moduleType == null || "".equals(moduleType)) {
			highchartDataColumnVo.setName(ModulTypes.CompanyPlace);
		} else {
			highchartDataColumnVo.setName(ModulTypes.MODULE_NAME
					.get(moduleType));
		}
		highchartDataColumnVo.setStack("12345");
		return highchartDataColumnVo;
	}

	private int[] tendencyImportChart(String moduleType, String[] time,
			Long orgId) {
		int[] data = new int[12];
		Integer countSum = 0;
		Map<String, Object> map = null;
		for (int i = 0; i < time.length; i++) {
			map = getTendencyChartMap(moduleType, time[i], orgId);
			countSum = companyPlaceLeaderViewNewDAO.getTendencyChartToal(map);
			data[i] = countSum;
		}
		return data;
	}

	private Map<String, Object> getTendencyChartMap(String moduleType,
			String time, Long orgId) {
		Map<String, Object> map = null;
		if (time != null && !"".equals(time) && orgId != null
				&& !"".equals(orgId)) {
			map = new HashMap<String, Object>();
			map.put("orgId", orgId);
			String[] data = time.split("-");
			int year = Integer.valueOf(data[0]);
			int month = Integer.valueOf(data[1]);
			map.put("year", year);
			map.put("month", month);
			if (moduleType == null || "".equals(moduleType)) {
				map.put("keyTypes", ModulTypes.allModuleKeys);
			} else if ("place".equals(moduleType)) {
				map.put("keyTypes",
						convertMapKeyToList(ModulTypes.PLACE_COMPANY_PLACE));
			} else if ("company".equals(moduleType)) {
				map.put("keyTypes",
						convertMapKeyToList(ModulTypes.COMPANY_COMPANY_PLACE));
			} else if ("enterprise".equals(moduleType)) {
				map.put("keyTypes",
						convertMapKeyToList(ModulTypes.SIZED_ENTERPRISE));
			} else if ("keyCompanyPlace".equals(moduleType)) {
				map.put("keyTypes",
						convertMapKeyToList(ModulTypes.BUSINESS_COMPANY_PLACE));
			}
		}
		return map;
	}

	@Override
	public List<Object[]> getCompanyPlacePieInfo(Integer year, Integer month,
			Long orgId, String moduleType) {
		if (orgId == null || year == null || month == null) {
			throw new BusinessValidationException("参数错误");
		}
		String keyName = "";
		if (!StringUtil.isStringAvaliable(moduleType)) {
			keyName = ALL_COMPANY_STATISTICS;
		} else {
			keyName = moduleType;
		}
		String key = MemCacheConstant.getColumnByTimeKey(orgId, keyName,
				MemCacheConstant.STATISTICSPIE, year, month);
		List<Object[]> list = (List<Object[]>) cacheService.get(
				MemCacheConstant.LEADERVIEW_NAMESPACE, key);
		if (list != null) {
			return list;
		}
		try {
			Organization organization = organizationDubboService
					.getSimpleOrgById(orgId);
			outofDate(year, month);
			// 当前月份实时取数据
			Map<String, String> moduleTypesMap = getModuleTypeMap(moduleType);
			if (year == Calendar.getInstance().get(Calendar.YEAR)
					&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
				// 类型分布数据
				// 当前月份的
				list = getCompanyPlacePieByOrgId(organization.getId(),
						moduleType, moduleTypesMap);
				cacheService.set(MemCacheConstant.LEADERVIEW_NAMESPACE, key,
						ModulTypes.CACHETIME, list);
				return list;

			} else {
				// 历史月份的

				list = getCompanyPlacePieByTime(organization.getId(),
						moduleType, year, month, moduleTypesMap);
				cacheService.set(MemCacheConstant.LEADERVIEW_NAMESPACE, key,
						ModulTypes.CACHETIME, list);
				return list;
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl的getCompanyPlacePieInfo方法错误：",
					"单位场所类型分布图出现错误", e);
		}

	}

	/**
	 * 根据类型返回所对应的类型分布图的类型的集合
	 * 
	 * @param moduleType
	 * @return
	 */
	private Map<String, String> getModuleTypeMap(String moduleType) {
		Map<String, String> map = null;
		if (moduleType == null || "".equals(moduleType)) {// 全部的 （单位场所）
			map = ModulTypes.COMPANY_AND_PLACE;
		} else if (ModulTypes.PLACE.equals(moduleType)) {// 场所
			map = ModulTypes.PLACE_COMPANY_PLACE;
		} else if (ModulTypes.COMPANY.equals(moduleType)) {// 单位
			map = ModulTypes.COMPANY_COMPANY_PLACE;
		} else if (ModulTypes.KEY_COMPANY_PLACE.equals(moduleType)) {// 重点
			map = ModulTypes.BUSINESS_COMPANY_PLACE;
		} else if (ModulTypes.ENTERPRISE.equals(moduleType)) {// 规模企业
			map = ModulTypes.SIZED_ENTERPRISE;
		}
		return map;
	}

	/**
	 * 历史月份的没有考虑其他的情况（即各个大类单独的的数据）
	 * 
	 * @param orgInternalCode
	 * @param moduleType
	 * @param year
	 * @param month
	 * @return
	 */
	private Map<String, Integer> getCompanyplaceCountSumByTime(Long orgId,
			String moduleType, Map<String, String> moduleTypesMap, int year,
			int month) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int total = 0;
		if (moduleType == null || "".equals(moduleType)) {// 总况

			for (Entry<String, String> entry : moduleTypesMap.entrySet()) {
				int sum = companyPlaceLeaderViewNewDAO
						.getTendencyChartToal(getCompanyPlacePieMap(orgId,
								entry.getKey(), year, month));
				map.put(entry.getKey(), sum);
				total = total + sum;
			}
			map.put("total", total);
		} else {// 单个的
			for (Entry<String, String> entry : moduleTypesMap.entrySet()) {
				int sum = companyPlaceLeaderViewNewDAO
						.getCompanyPlacePieToAloneMold(getCompanyPlacePieMap(
								orgId, entry.getKey(), year, month));
				map.put(entry.getKey(), sum);
				total = total + sum;
			}
			map.put("total", total);
		}
		return map;
	}

	private List<Object[]> getCompanyPlacePieByTime(Long orgId,
			String moduleType, int year, int month,
			Map<String, String> moduleTypesMap) {
		tableManageService.createAnalyseTable(
				AnalyseUtilNew.COMPANY_PLACE_TABLE_NAME,
				AnalyseUtilNew.COMPANY_PLACE_TABLE_NAME_SQL,
				Integer.valueOf(year), Integer.valueOf(month));
		List<Object[]> companyPlacePieDatas = new ArrayList<Object[]>();
		Map<String, Integer> map = getCompanyplaceCountSumByTime(orgId,
				moduleType, moduleTypesMap, year, month);
		double total = map.get("total");
		for (Entry<String, String> entry : moduleTypesMap.entrySet()) {

			Object[] companyPlaceData = new Object[2];
			double count = map.get(entry.getKey());
			if (total == 0) {
				companyPlaceData[1] = 0;
			} else {
				companyPlaceData[1] = Double
						.parseDouble(new java.text.DecimalFormat("#.00")
								.format(count / total * 100));
			}
			companyPlaceData[0] = entry.getValue() + "( "
					+ new java.text.DecimalFormat("#").format(count) + " )";
			companyPlacePieDatas.add(companyPlaceData);
		}
		return companyPlacePieDatas;
	}

	private List<Object[]> getCompanyPlacePieByOrgId(Long orgId,
			String moduleType, Map<String, String> moduleTypesMap) {
		List<Object[]> companyPlacePieDatas = new ArrayList<Object[]>();
		Map<String, Integer> map = getCompanyplaceCountSumByOrgInternalCode(
				orgId, moduleType, moduleTypesMap);
		double total = map.get("total");
		for (Entry<String, String> entry : moduleTypesMap.entrySet()) {

			Object[] companyPlaceData = new Object[2];
			double count = map.get(entry.getKey());
			if (total == 0) {
				companyPlaceData[1] = 0;
			} else {
				companyPlaceData[1] = Double
						.parseDouble(new java.text.DecimalFormat("#.00")
								.format(count / total * 100));
			}
			companyPlaceData[0] = entry.getValue() + "( "
					+ new java.text.DecimalFormat("#").format(count) + " )";
			companyPlacePieDatas.add(companyPlaceData);
		}
		return companyPlacePieDatas;
	}

	/**
	 * 没有考虑其他的情况（即各个大类单独的的数据）当月
	 * 
	 * @param orgCode
	 * @return
	 */
	private Map<String, Integer> getCompanyplaceCountSumByOrgInternalCode(
			Long orgId, String moduleType, Map<String, String> moduleTypesMap) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int total = 0;
		int sum = 0;
		if (moduleType == null || "".equals(moduleType)) {

			for (Entry<String, String> entry : moduleTypesMap.entrySet()) {
				sum = companyPlaceLeaderViewNewDAO
						.getCompanyplaceCountSumByOrgInternalCode(getCompanyPlacePieMap(
								orgId, entry.getKey(), null, null));
				map.put(entry.getKey(), sum);
				total = total + sum;
			}
			map.put("total", total);
		} else {
			for (Entry<String, String> entry : moduleTypesMap.entrySet()) {
				sum = companyPlaceLeaderViewNewDAO
						.getCompanyplaceCountSumByOrgInternalCodeToAloneMold(getCompanyPlacePieMapToAloneMold(
								orgId, moduleType, entry.getKey(), null, null));
				map.put(entry.getKey(), sum);
				total = total + sum;
			}
			map.put("total", total);
		}
		return map;
	}

	private Map<String, Object> getCompanyPlacePieMapToAloneMold(Long orgId,
			String moduleType, String keyType, Integer year, Integer month) {
		if (moduleType == null || "".equals(moduleType)) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		if (year != null && month != null) {// 说明是历史的就直接返回
			map.put("year", year);
			map.put("month", month);
			map.put("keyType", keyType);
			return map;
		}
		String column = "";
		String module = "";
		String keyTypeValue = "";
		// 说明是当月的就要做处理
		if ("enterprise".equals(moduleType)) {
			column = "scaleType";
			module = "companyplace";
		} else if ("keyCompanyPlace".equals(moduleType)) {
			// 要匹配的字段
			column = "isKeytype";
			// 判断是统计什么的
			module = "business";
		} else {
			column = "classIficationen";
			module = "companyplace";
		}
		keyTypeValue = getKeyTypeByModuleKey(keyType);
		map.put("column", column);
		map.put("keyType", keyTypeValue);
		map.put("module", module);

		return map;
	}

	/**
	 * 类型分布图的参数(总的)
	 * 
	 * @param orgInternalCode
	 * @param moduleType
	 * @return
	 */
	private Map<String, Object> getCompanyPlacePieMap(Long orgId,
			String moduleType, Integer year, Integer month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);

		if (moduleType == null || "".equals(moduleType)) {
			map.put("keyTypes", ModulTypes.allModuleKeys);
		} else if ("place".equals(moduleType)) {
			map.put("keyTypes",
					convertMapKeyToList(ModulTypes.PLACE_COMPANY_PLACE));
		} else if ("company".equals(moduleType)) {
			map.put("keyTypes",
					convertMapKeyToList(ModulTypes.COMPANY_COMPANY_PLACE));
		} else if ("enterprise".equals(moduleType)) {
			map.put("keyTypes",
					convertMapKeyToList(ModulTypes.SIZED_ENTERPRISE));
		} else if ("keyCompanyPlace".equals(moduleType)) {
			map.put("keyTypes",
					convertMapKeyToList(ModulTypes.BUSINESS_COMPANY_PLACE));
		} else {// 单一的
			map.put("keyType", moduleType);
		}
		if (year != null && month != null) {
			map.put("year", year);
			map.put("month", month);
		}
		return map;
	}

	// 判断时间是否超出了时间范围
	private void outofDate(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		if (c.after(Calendar.getInstance())) {
			throw new BusinessValidationException("所查月份有误，请重新选择要生成报表的月份！");
		}
	}

	/**
	 * 把map的key转换为list集合
	 * 
	 * @param map
	 * @return
	 */
	public List<String> convertMapKeyToList(Map<String, String> map) {
		List<String> list = null;
		if (map != null) {
			list = new ArrayList<String>();
			for (Entry<String, String> entry : map.entrySet()) {
				list.add(entry.getKey());
			}

		}

		return list;
	}

	private Long getOrgType() {
		return propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.ADMINISTRATIVE_REGION).get(0).getId();
	}

	@Override
	public List<StatisticsBaseInfoAddCountVo> statisticsSummaryForJob(
			Long orgId, String moduleKey) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		if (!StringUtil.isStringAvaliable(moduleKey)) {
			moduleKey = ModulTypes.ALL_COMPANY_PLACE_KEY;
		}
		try {

			String[] time = new String[12];
			time = analyzStatisticsNewHelper.sortTime(analyzStatisticsNewHelper
					.getTime(time, time.length));
			String cacheKey = MemCacheConstant.getSummaryKey(moduleKey,
					time[0], orgId);
			List<StatisticsBaseInfoAddCountVo> result = null;

			result = new ArrayList<StatisticsBaseInfoAddCountVo>();
			for (int j = 0; j < time.length; j++) {
				String strTime = time[j];
				String year = strTime.substring(0, strTime.indexOf("-"));
				String month = strTime.substring(strTime.indexOf("-") + 1);
				StatisticsBaseInfoAddCountVo vo = null;
				vo = createBaseinfo(moduleKey, orgId, strTime, year, month);
				vo.setStatisticsType(strTime);
				result.add(vo);
			}
			Collections.sort(result);
			leaderViewCacheService.addOrUpdateCacheByKey(
					MemCacheConstant.LEADERVIEW_NAMESPACE, new LeaderViewCache(
							cacheKey, result,
							LeaderViewCacheType.STATISTICS_SUMMARY_TYPE),
					StatisticsBaseInfoAddCountVo.class);
			return result;

		} catch (Exception e) {
			throw new ServiceValidationException(
					"CompanyPlaceLeaderViewServiceImpl的statisticsSummaryForJob方法错误：",
					"领导视图 线形图（历史）错误", e);
		}

	}
}
