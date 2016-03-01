package com.tianque.plugin.analysisNew.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.PropertyDomain;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.job.JobHelper;
import com.tianque.plugin.analysisNew.dao.BaseSituationStatementNewDAO;
import com.tianque.plugin.analysisNew.domain.BaseSituationStatement;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;
import com.tianque.tableManage.service.TableManageService;

@Service("baseSituationStatementNewService")
public class BaseSituationStatementNewServiceImpl implements
		BaseSituationStatementNewService {
	private static final Logger logger = LoggerFactory
			.getLogger(BaseSituationStatementNewServiceImpl.class);
	private static final Integer SEARCH_TYPE_FIRST = 1;
	private static final Integer SEARCH_TYPE_SECONED = 2;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PropertyDomainService propertyDomainService;
	@Autowired
	private BaseSituationStatementNewDAO baseSituationStatementNewDAO;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private TableManageService tableService;

	@Override
	public List<BaseSituationStatement> findBaseSituationStatementList(
			Long orgId, int year, int month, Integer type,
			List<Long> primaryOrgTypes, Long masseduty, Long orgType) {
		if (orgId == null) {
			throw new BusinessValidationException("统计失败,组织机构信息不存在!");
		}
		List<BaseSituationStatement> list = new ArrayList<BaseSituationStatement>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("masseduty", masseduty);
		map.put("floatingPopulation", BaseInfoTables.FLOATINGPOPULATION_KEY);

		map.put("positiveInfo", BaseInfoTables.POSITIVEINFO_KEY);
		map.put("positivePersionBaseDomainId",
				getPropertyBaseDomainIdByName(PropertyTypes.POSITIVEINFO));

		map.put("rectificativePerson", BaseInfoTables.RECTIFICATIVEPERSON_KEY);
		map.put("rectificativePersonBaseDomainId",
				getPropertyBaseDomainIdByName(PropertyTypes.EXECUTE_TYPE));

		map.put("mentalPatient", BaseInfoTables.MENTALPATIENT_KEY);
		map.put("mentalPatientBaseDomainId",
				getPropertyBaseDomainIdByName(PropertyTypes.MENTALPATIENT_DANGEROUS_LEVEL));

		map.put("druggy", BaseInfoTables.DRUGGY_KEY);
		map.put("druggyBaseDomainId",
				getPropertyBaseDomainIdByName(PropertyTypes.DETOXICATE_CASE));

		map.put("idleYouth", BaseInfoTables.IDLEYOUTH_KEY);
		map.put("idleYouthBaseDomainId",
				getPropertyBaseDomainIdByName(PropertyTypes.IDLEYOUTH_STAFF_TYPE));

		map.put("endDate", CalendarUtil.getNextMonthStart(year, month));
		map.put("startDate",
				CalendarUtil.getBeforeMonthFirstDayByTime(year, month));
		map.put("orgType", orgType);
		map.put("functionType",
				propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.FUNCTION_KEY).getId());
		map.put("orgLevel",
				propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.ORGANIZATION_LEVEL,
								OrganizationLevel.DISTRICT_KEY).getId());
		map.put("year", year);
		map.put("month", month);
		map.put("orgId", orgId);
		try {
			list = baseSituationStatementNewDAO
					.queryBaseSituationStatementForList(map);
		} catch (Exception e) {
			throw new ServiceValidationException("基本情况统计表查询错误", e);
		}
		return list;
	}

	private Long getPropertyBaseDomainIdByName(String domainName) {
		PropertyDomain domain = propertyDomainService
				.getPropertyDomainByDomainName(domainName);
		if (domain == null || domain.getId() == null) {
			throw new BusinessValidationException("未找到对应统计类型");
		}
		return domain.getId();
	}

	/***
	 * 统计之前，先根据年月判断所需要查询的表是否存在，如果不存在则将表新建
	 */
	private void tableIsCreate(int year, int month) {
		// 群防群治组织所在表
		tableService.createAnalyseTable(
				AnalyseUtilNew.PRIMARYORGANIZATIONSENAME,
				AnalyseUtilNew.PRIMARYORGANIZATIONSSQL, year, month);
		// 实有人员表
		tableService.createAnalyseTable(AnalyseUtilNew.ACTUALPERSONTABLENAME,
				AnalyseUtilNew.ACTUALPERSONSQL, year, month);
		// 人员，房屋等表
		tableService.createAnalyseTable(AnalyseUtilNew.IMPORTPERSONTABLENAME,
				AnalyseUtilNew.IMPORTPERSONSQL, year, month);
		tableService.createAnalyseIndex("statistichistory", "baseinfoType",
				"orgInternalCode");
	}

	/** 将群防群治组织大类下所有子类Id查询出来 */
	private Long getMasseduty(List<PropertyDict> dictList) {
		Long dictIds = null;
		if (dictList == null || dictList.size() == 0) {
			return dictIds;
		}
		for (PropertyDict dict : dictList) {
			if (BaseInfoTables.MASSES_DISPLAYNAME.equals(dict.getDisplayName())) {
				dictIds = dict.getId();
			}
		}
		return dictIds;
	}

	/** 将综治组织需要统计的 综治委 综治办 专项工作领导小组的字典项ID查询出来 */
	private List<Long> getNeedPropertyDict(List<PropertyDict> dictList) {
		List<Long> dictIds = new ArrayList<Long>();
		if (dictList == null || dictList.size() == 0) {
			return dictIds;
		}
		for (PropertyDict dict : dictList) {
			if (BaseInfoTables.COMPREHENSIVE.equals(dict.getDisplayName())
					|| BaseInfoTables.COMMITTEE.equals(dict.getDisplayName())
					|| BaseInfoTables.ZXGZLDXIAOZU
							.equals(dict.getDisplayName())) {
				dictIds.add(dict.getId());
			}
		}
		return dictIds;
	}

	/** 获取网格层级的orgLevel */
	private Long getGridLevel(List<PropertyDict> list) {
		if (list != null && list.size() != 0) {
			for (PropertyDict dict : list) {
				if (dict.getInternalId() == OrganizationLevel.GRID) {
					return dict.getId();
				}
			}
		}
		return null;
	}

	@Override
	public void addBaseStituationStatementHistory(final int year,
			final int month) {
		boolean isCreated = tableService.createAnalyseTable(
				AnalyseUtilNew.BASESITUATION_STATEMENT_TABLE_NAME,
				AnalyseUtilNew.BASESITUATION_STATEMENT_TABLE_NAME_SQL, year,
				month);
		createBaseSituationStatementTableIndex(year, month);
		if (!isCreated) {
			// 表已经存在，删除表中数据
			baseSituationStatementNewDAO
					.deleteBaseSituationStatement(AnalyseUtilNew.BASESITUATION_STATEMENT_TABLE_NAME
							+ "_" + year + "_" + month);
		}
		tableIsCreate(year, month);// 判断需要查询的表是否都已经存在
		final List<Long> primaryOrgTypes = getNeedPropertyDict(propertyDictService
				.findPropertyDictByDomainName(BaseInfoTables.DEPARTMENTPARTY));
		final Long masseduty = getMasseduty(propertyDictService
				.findPropertyDictByDomainName(BaseInfoTables.PRIMARYORGANIZATIONS));
		final Long orgType = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_KEY).getId();
		Long gridOrgLevel = getGridLevel(propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL));
		// 查询获得所有网格层级以上的组织机构ID--当前层级直属下辖统计数据 统计报表1
		List<Long> orgList = organizationDubboService
				.getOrganizationByOrgLevelAndOrgType(gridOrgLevel, orgType);
		ExecutorService pool = Executors.newFixedThreadPool(5);
		int num = orgList.size() / 1000;// 跑几次
		if (orgList.size() % 1000 != 0) {
			num += 1;
		}
		final CountDownLatch latch = new CountDownLatch(num);
		int i = 0;
		while (num > 0) {
			int end = i + 1000;
			if (end > orgList.size()) {
				end = orgList.size();
			}
			final List<Long> runList = orgList.subList(i, end);
			Runnable runnable = new Runnable() {
				public void run() {
					logger.error("----线程开始执行----");
					final BaseSituationStatement baseData = new BaseSituationStatement();
					baseData.setYear(year);
					baseData.setMonth(month);
					JobHelper.createMockAdminSession();
					long startTime = System.currentTimeMillis();
					try {
						for (final Long id : runList) {
							// 查询统计报表1数据
							Organization organization = organizationDubboService
									.getFullOrgById(id);
							baseData.setOrganization(organization);

							String key = MemCacheConstant
									.getBasesituationStatementCachKey(
											MemCacheConstant.BASE_SITUATION_STATEMENT_CACHKEY,
											year, month, SEARCH_TYPE_FIRST, id,
											ModulTypes.STATISTICSTABLELIST);
							List<BaseSituationStatement> listData = findBaseSituationStatementList(
									id, year, month, SEARCH_TYPE_FIRST,
									primaryOrgTypes, masseduty, orgType);
							if (key != null) {
								cacheService
										.set(MemCacheConstant.ANALYSE_BASE_SITUATION_STATEMENT_NAMESPACE,
												key, ModulTypes.CACHETIME,
												listData);
							}

							addBaseSituationStatementStatistics(baseData,
									SEARCH_TYPE_FIRST, listData);

							if (organization.getOrgLevel().getInternalId() > OrganizationLevel.DISTRICT) {// 组织机构层级是市级以上的，查询出
																											// 统计报表2的数据
								if (listData != null) {
									listData.clear();
								}
								List<Organization> parentOrgList = organizationDubboService
										.findAdminOrgsByParentId(id);
								for (Organization org : parentOrgList) {
									List<BaseSituationStatement> chirledListData = findBaseSituationStatementList(
											org.getId(), year, month,
											SEARCH_TYPE_SECONED,
											primaryOrgTypes, masseduty, orgType);
									listData.addAll(chirledListData);
								}
								String tableKey = MemCacheConstant
										.getBasesituationStatementCachKey(
												MemCacheConstant.BASE_SITUATION_STATEMENT_CACHKEY,
												year, month,
												SEARCH_TYPE_SECONED, id,
												ModulTypes.STATISTICSTABLELIST);
								if (tableKey != null) {
									cacheService
											.set(MemCacheConstant.ANALYSE_BASE_SITUATION_STATEMENT_NAMESPACE,
													tableKey,
													ModulTypes.CACHETIME,
													listData);
								}
								addBaseSituationStatementStatistics(baseData,
										SEARCH_TYPE_SECONED, listData);
							}
						}
						logger.error("线程使用时间为："
								+ (System.currentTimeMillis() - startTime));
					} finally {
						latch.countDown();
					}
				};

			};
			pool.execute(runnable);
			num--;
			i = end;
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}

	}

	/** 属性封装，数据新增 */
	private void addBaseSituationStatementStatistics(
			BaseSituationStatement baseData, Integer searchType,
			List<BaseSituationStatement> listData) {
		JSONArray jsonArray = JSONArray.fromObject(listData);
		baseData.setStatisticsType(searchType);
		baseData.setStatisticsData(jsonArray.toString());
		baseSituationStatementNewDAO
				.addBaseSituationStatementStatistics(baseData);
	}

	@Override
	public List<BaseSituationStatement> findBaseSituationStatementListByCondition(
			Long orgId, int year, int month, Integer type) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构信息为获得");
		}
		List<BaseSituationStatement> list = null;
		List<BaseSituationStatement> lastList = null;
		// 获取当年传入年月的缓存key
		String key = getCachkey(orgId, year, month, type);
		// 获取上个月缓存key
		String lastKey = getCachkey(orgId, CalendarUtil.getYear(CalendarUtil
				.getLastMonthEnd(year, month)), CalendarUtil
				.getMonth(CalendarUtil.getLastMonthEnd(year, month)), type);
		if (key != null) {
			list = (List<BaseSituationStatement>) cacheService
					.get(MemCacheConstant.ANALYSE_BASE_SITUATION_STATEMENT_NAMESPACE,
							key);
		}
		if (lastKey != null) {
			lastList = (List<BaseSituationStatement>) cacheService
					.get(MemCacheConstant.ANALYSE_BASE_SITUATION_STATEMENT_NAMESPACE,
							lastKey);
		}
		if (list != null && lastList != null) {
			calculationChainData(list, lastList);
			return bindCount(list);
		} else {
			list = new ArrayList<BaseSituationStatement>();
			lastList = new ArrayList<BaseSituationStatement>();
		}
		list = getBaseSituationStatementForList(orgId, year, month, type);
		lastList = getBaseSituationStatementForList(
				orgId,
				CalendarUtil.getYear(CalendarUtil.getLastMonthEnd(year, month)),
				CalendarUtil.getMonth(CalendarUtil.getLastMonthEnd(year, month)),
				type);
		if (key != null) {
			cacheService
					.set(MemCacheConstant.ANALYSE_BASE_SITUATION_STATEMENT_NAMESPACE,
							key, ModulTypes.CACHETIME, list);
		}
		if (lastKey != null) {
			cacheService
					.set(MemCacheConstant.ANALYSE_BASE_SITUATION_STATEMENT_NAMESPACE,
							lastKey, ModulTypes.CACHETIME, lastList);
		}
		calculationChainData(list, lastList);
		return bindCount(list);
	}

	private List<BaseSituationStatement> getBaseSituationStatementForList(
			Long orgId, int year, int month, Integer type) {
		List<BaseSituationStatement> list = new ArrayList<BaseSituationStatement>();
		tableService.createAnalyseTable(
				AnalyseUtilNew.BASESITUATION_STATEMENT_TABLE_NAME,
				AnalyseUtilNew.BASESITUATION_STATEMENT_TABLE_NAME_SQL, year,
				month);
		createBaseSituationStatementTableIndex(year, month);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("orgId", orgId);
		map.put("statisticsType", type);
		BaseSituationStatement baseSituationStatement = baseSituationStatementNewDAO
				.getBaseSituationStatementByOrgIdAndType(map);// 当前月份数据
		if (baseSituationStatement != null
				&& baseSituationStatement.getStatisticsData() != null) {
			JSONArray ja = JSONArray.fromObject(baseSituationStatement
					.getStatisticsData());
			list = (List<BaseSituationStatement>) ja.toCollection(ja,
					BaseSituationStatement.class);
		}
		return list;
	}

	/**
	 * 
	 * @param currentData
	 *            当前月数据
	 * @param beforData
	 *            上月数据
	 * @return currentData(实体中有计算出当前月的数据)
	 */
	private List<BaseSituationStatement> calculationChainData(
			List<BaseSituationStatement> currentData,
			List<BaseSituationStatement> beforData) {
		if (currentData == null) {
			throw new BusinessValidationException("数据统计失败，未获得当前月数据");
		}
		if (beforData == null) {
			beforData = new ArrayList<BaseSituationStatement>();
		}
		// 先循环得到上月新增量
		for (BaseSituationStatement currentBaseData : currentData) {
			for (BaseSituationStatement beforBaseData : beforData) {
				if (currentBaseData.getOrganization().getId()
						.equals(beforBaseData.getOrganization().getId())) {
					// 本月变量 本月总是-上月总数
					currentBaseData.setCurrentPreventionCount(currentBaseData
							.getPreventionCount()
							- beforBaseData.getPreventionCount());
					currentBaseData
							.setCurrentFloatingPersionCount(currentBaseData
									.getFloatingPersionCount()
									- beforBaseData.getFloatingPersionCount());
					currentBaseData
							.setCurrentPositivePersionCount(currentBaseData
									.getPositivePersionCount()
									- beforBaseData.getPositivePersionCount());
					currentBaseData
							.setCurrentRectificativePersonCount(currentBaseData
									.getRectificativePersonCount()
									- beforBaseData
											.getRectificativePersonCount());
					currentBaseData
							.setCurrentMentalPatientPersionCount(currentBaseData
									.getMentalPatientPersionCount()
									- beforBaseData
											.getMentalPatientPersionCount());
					currentBaseData
							.setCurrentDruggyPersionCount(currentBaseData
									.getDruggyPersionCount()
									- beforBaseData.getDruggyPersionCount());
					currentBaseData.setCurrentIdleYouthCount(currentBaseData
							.getIdleYouthCount()
							- beforBaseData.getIdleYouthCount());
					// 截止上月总数 将上月总数覆盖本月总数
					currentBaseData.setPreventionCount(beforBaseData
							.getPreventionCount());
					currentBaseData.setFloatingPersionCount(beforBaseData
							.getFloatingPersionCount());
					currentBaseData.setPositivePersionCount(beforBaseData
							.getPositivePersionCount());
					currentBaseData.setRectificativePersonCount(beforBaseData
							.getRectificativePersonCount());
					currentBaseData.setMentalPatientPersionCount(beforBaseData
							.getMentalPatientPersionCount());
					currentBaseData.setDruggyPersionCount(beforBaseData
							.getDruggyPersionCount());
					currentBaseData.setIdleYouthCount(beforBaseData
							.getIdleYouthCount());
				}
			}
		}
		return currentData;
	}

	/**
	 * 计算数据合计
	 * 
	 * @param list
	 *            当前月数据(暂时没用)
	 * @param lastList
	 *            上月数据
	 * @param thridList
	 *            上上月数据(暂时没用)
	 * @return
	 */
	private List<BaseSituationStatement> bindCount(
			List<BaseSituationStatement> list) {
		int preventionCount = 0;// 本年月 群防群治组织人数
		int floatingPersionCount = 0;// 本年月 流动人口
		int positivePersionCount = 0;// 本年月 刑释人员
		int rectificativePersonCount = 0;// 本年月 矫正人员
		int mentalPatientPersionCount = 0;// 本年月 精神病
		int druggyPersionCount = 0;// 本年月 吸毒人员
		int idleYouthCount = 0;// 本年月 重点青少年
		int countyCount = 0;// 区县数量
		int functionDepartmentCount = 0;// 职能部门加入数量
		int specialCrowdCount = 0;// 特殊人群走访量
		if (list == null) {
			throw new BusinessValidationException("数据合计计算失败");
		}
		for (BaseSituationStatement lastData : list) {
			preventionCount += lastData.getPreventionCount();
			floatingPersionCount += lastData.getFloatingPersionCount();
			positivePersionCount += lastData.getPositivePersionCount();
			rectificativePersonCount += lastData.getRectificativePersonCount();
			mentalPatientPersionCount += lastData
					.getMentalPatientPersionCount();
			druggyPersionCount += lastData.getDruggyPersionCount();
			idleYouthCount += lastData.getIdleYouthCount();
			countyCount += lastData.getCountyCount();
			functionDepartmentCount += lastData.getFunctionDepartmentCount();
			specialCrowdCount += lastData.getSpecialCrowdCount();
		}
		BaseSituationStatement countBase = new BaseSituationStatement();
		countBase.setOrganization(new Organization());
		countBase.getOrganization().setOrgName("合计");
		countBase.setPreventionCount(preventionCount);
		countBase.setFloatingPersionCount(floatingPersionCount);
		countBase.setPositivePersionCount(positivePersionCount);
		countBase.setRectificativePersonCount(rectificativePersonCount);
		countBase.setMentalPatientPersionCount(mentalPatientPersionCount);
		countBase.setDruggyPersionCount(druggyPersionCount);
		countBase.setIdleYouthCount(idleYouthCount);
		countBase.setCountyCount(countyCount);
		countBase.setFunctionDepartmentCount(functionDepartmentCount);
		countBase.setSpecialCrowdCount(specialCrowdCount);
		list.add(countBase);
		return list;
	}

	@Override
	public List<BaseSituationStatement> findBaseSituationStatementListBySort(
			Long orgId, int year, int month, Integer type,
			final String sortName, final String sort) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构信息未获得");
		}
		List<BaseSituationStatement> list = null;
		List<BaseSituationStatement> lastList = null;
		String key = getCachkey(orgId, year, month, type);
		String lastKey = getCachkey(orgId, CalendarUtil.getYear(CalendarUtil
				.getLastMonthEnd(year, month)), CalendarUtil
				.getMonth(CalendarUtil.getLastMonthEnd(year, month)), type);
		// 获取上上个月的缓存key
		// 先根据key从缓存中取出当前月份数据,如果没有则走数据库查询
		if (key != null) {
			list = (List<BaseSituationStatement>) cacheService
					.get(MemCacheConstant.ANALYSE_BASE_SITUATION_STATEMENT_NAMESPACE,
							key);
		}
		if (list == null) {
			list = getBaseSituationStatementForList(orgId, year, month, type);
			if (key != null) {
				cacheService
						.set(MemCacheConstant.ANALYSE_BASE_SITUATION_STATEMENT_NAMESPACE,
								key, ModulTypes.CACHETIME, list);
			}
		}
		// 根据上个月key缓存获取数据，没有则走数据库
		if (lastKey != null) {
			lastList = (List<BaseSituationStatement>) cacheService
					.get(MemCacheConstant.ANALYSE_BASE_SITUATION_STATEMENT_NAMESPACE,
							lastKey);
		}
		if (lastList == null) {
			lastList = getBaseSituationStatementForList(orgId,
					CalendarUtil.getYear(CalendarUtil.getLastMonthEnd(year,
							month)), CalendarUtil.getMonth(CalendarUtil
							.getLastMonthEnd(year, month)), type);
			if (lastKey != null) {
				cacheService
						.set(MemCacheConstant.ANALYSE_BASE_SITUATION_STATEMENT_NAMESPACE,
								lastKey, ModulTypes.CACHETIME, lastList);
			}
		}
		// 根据上上个月key缓存获取数据，没有则走数据库
		calculationChainData(list, lastList);
		if (!StringUtil.isStringAvaliable(sortName)) {
			bindCount(list);
			return list;
		}
		Collections.sort(list, new Comparator<BaseSituationStatement>() {
			@Override
			public int compare(BaseSituationStatement firstBaseinfo,
					BaseSituationStatement secondBaseinfo) {
				try {
					String firstLetter = sortName.substring(0, 1).toUpperCase();
					String getMethodName = "get" + firstLetter
							+ sortName.substring(1);
					Method getMethodForFirst = firstBaseinfo.getClass()
							.getMethod(getMethodName, new Class[] {});
					Method getMethodForScoend = secondBaseinfo.getClass()
							.getMethod(getMethodName, new Class[] {});

					String firstNumStr = String.valueOf(getMethodForFirst
							.invoke(firstBaseinfo, new Object[] {}));
					String socendNumStr = String.valueOf(getMethodForScoend
							.invoke(secondBaseinfo, new Object[] {}));
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

	private String getCachkey(Long orgId, int year, int month, Integer type) {
		if (orgId == null || year == 0 || month == 0) {
			return null;
		}
		return MemCacheConstant.getBasesituationStatementCachKey(
				MemCacheConstant.BASE_SITUATION_STATEMENT_CACHKEY, year, month,
				type, orgId, ModulTypes.STATISTICSTABLELIST);
	}

	/**
	 * 创建Basesituationstatement表的索引
	 * 
	 * @param year
	 * @param month
	 */
	private void createBaseSituationStatementTableIndex(int year, int month) {
		String indexName = "";
		boolean isCreatIndex = false;
		for (Map.Entry<String, String> entry : AnalyseUtilNew.BASESITUATIONSTATEMENT_INDEX_MAP
				.entrySet()) {
			indexName = "idx_bss_" + year + "_" + month + "_"
					+ entry.getValue();
			isCreatIndex = tableService.isCreateIndexByIndexName(indexName);
			if (!isCreatIndex) {
				tableService.crateIndex(assemblyIndexSql(year, month,
						entry.getKey(), entry.getValue()));
			}
		}

	}

	/**
	 * 拼接出一个创建索引的sql
	 * 
	 * @param year
	 * @param month
	 * @param column
	 * @return
	 */
	private String assemblyIndexSql(int year, int month, String column,
			String columnName) {
		String indexSql = "";
		if (StringUtil.isStringAvaliable(column)) {
			indexSql = "create index idx_bss_" + year + "_" + month + "_"
					+ columnName + " on Basesituationstatement_" + year + "_"
					+ month + "(" + column + ")";
		}
		return indexSql;
	}

}
