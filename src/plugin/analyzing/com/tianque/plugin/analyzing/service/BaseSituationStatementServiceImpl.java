package com.tianque.plugin.analyzing.service;

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
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.state.IssueState;
import com.tianque.job.JobHelper;
import com.tianque.plugin.analysisNew.service.BaseinfoStatisticNewService;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analyzing.dao.BaseSituationStatementDAO;
import com.tianque.plugin.analyzing.domain.BaseSituationStatement;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;
import com.tianque.tableManage.service.TableManageService;

@Service("baseSituationStatementService")
public class BaseSituationStatementServiceImpl implements
		BaseSituationStatementService {
	private static final Logger logger = LoggerFactory
			.getLogger(BaseSituationStatementServiceImpl.class);
	/** 网格化服务管理工作情况通报表1  city级*/
	private static final Integer SEARCH_TYPE_FIRST = 1;
	/** 网格化服务管理工作情况通报表2  district级*/
	private static final Integer SEARCH_TYPE_SECONED = 2;
	/** 网格化服务管理工作情况通报表(本层级)  currentLevel级*/
	private static final Integer CURRENT_LEVEL_VAL = 3;
	private static final String ZHONGGUO="中国";
	private static final String SICHUAN="四川省";
	private static final String PANZHIHUA="攀枝花市";
	private static final String DONGQU="东区";
	

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private PropertyDomainService propertyDomainService;
	@Autowired
	private BaseSituationStatementDAO baseSituationStatementDAO;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private TableManageService tableService;
	@Autowired
	private BaseinfoStatisticNewService baseinfoStatisticNewService;

	private Map<String, Object> createMap(int year, int month, Long orgType) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("positiveInfo", BaseInfoTables.POSITIVEINFO_KEY);

		map.put("rectificativePerson", BaseInfoTables.RECTIFICATIVEPERSON_KEY);

		map.put("mentalPatient", BaseInfoTables.MENTALPATIENT_KEY);

		map.put("druggy", BaseInfoTables.DRUGGY_KEY);

		map.put("idleYouth", BaseInfoTables.IDLEYOUTH_KEY);

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

		// 事件办结code
		map.put("stateCode", IssueState.ISSUECOMPLETE_CODE);
		return map;
	}

	public List<BaseSituationStatement> findBaseSituationStatementList(
			Long orgId, Map<String, Object> map) {
		if (orgId == null) {
			throw new BusinessValidationException("统计失败,组织机构信息不存在!");
		}
		List<BaseSituationStatement> list = new ArrayList<BaseSituationStatement>();
		map.put("orgId", orgId);
		//关于重点青少年添加查询条件('0~6岁'，'6~18岁'，'12~25岁'，'25~35岁')
		String typeNames = baseinfoStatisticNewService.getIdleYouth_TypeName();
		map.put("typeNames", typeNames);
		// 事件办结code
		try {
			if (AnalyseUtilNew.IS_NEWANALYSE_JOB) {
				list = baseSituationStatementDAO
						.queryBaseSituationStatementNewForList(map);
				// 统计事件对接里面相应层级的数据放到listData里面
				list = findIssueAbutmentJointList(map, list);
			} else {
				list = baseSituationStatementDAO
						.queryBaseSituationStatementForList(map);
			}
			
		} catch (Exception e) {
			throw new ServiceValidationException("基本情况统计表查询错误", e);
		}
		return list;
	}

	/***
	 * 统计之前，先根据年月判断所需要查询的表是否存在，如果不存在则将表新建
	 */
	private void tableIsCreate(int year, int month) {
		// 人员，房屋等表
		tableService.createAnalyseTable(AnalyseUtil.IMPORTPERSONTABLENAME,
				AnalyseUtil.IMPORTPERSONSQL, year, month);
		tableService.createAnalyseIndex("statistichistory", "baseinfoType",
				"orgInternalCode");
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
		boolean isCreated = tableService
				.createAnalyseTable(
						AnalyseUtil.BASESITUATION_STATEMENT_TABLE_NAME,
						AnalyseUtil.BASESITUATION_STATEMENT_TABLE_NAME_SQL,
						year, month);
		createBaseSituationStatementTableIndex(year, month);
		if (!isCreated) {
			// 表已经存在，删除表中数据
			baseSituationStatementDAO
					.deleteBaseSituationStatement(AnalyseUtil.BASESITUATION_STATEMENT_TABLE_NAME
							+ "_" + year + "_" + month);
		}
		tableIsCreate(year, month);// 判断需要查询的表是否都已经存在
		final Long orgType = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_KEY).getId();
		Long gridOrgLevel = getGridLevel(propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL));
		
		// 查询获得所有网格层级以上的组织机构ID--当前层级直属下辖统计数据 统计报表1
		List<Long> orgList = organizationDubboService
				.getOrganizationByOrgLevelAndOrgType(gridOrgLevel, null);

		final Map<String, Object> map = createMap(year, month, orgType);

		ExecutorService pool = Executors.newFixedThreadPool(1);
		int num = orgList.size() / 1000;// 跑几次
		if (orgList.size() % 1000 != 0) {
			num += 1;
		}
		final CountDownLatch latch = new CountDownLatch(num);
		//移除四川省—攀枝花市-东区统计的数据缓存，以免影响下面操作
		cacheService.remove("issueJoint");
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
							map.put("orgName", organization.getOrgName());
							List<BaseSituationStatement> listData = findBaseSituationStatementList(
									id, map);
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
											org.getId(), map);
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

	/**
	 * 统计事件对接里面相应层级的数据
	 */
	public List<BaseSituationStatement> findIssueAbutmentJointList(
			Map<String, Object> map, List<BaseSituationStatement> list) {
		if(map.get("orgName")==null){
			return list;
		}
		//如果不是中国、四川省、攀枝花市的话没必要统计东区的事件对接的数据
		String orgName=(String)map.get("orgName");
		if(!orgName.equals(SICHUAN) && !orgName.equals(PANZHIHUA)&& !orgName.equals(ZHONGGUO)){
			return list;
		}
		if (map.get("orgId") == null) {
			throw new BusinessValidationException("统计失败,组织机构信息不存在!");
		}
		BaseSituationStatement issueJoint=null;
		if(cacheService.get("issueJoint")!=null){
			issueJoint=(BaseSituationStatement)cacheService.get("issueJoint");
		}
		int i = 0;
		for (BaseSituationStatement statement : list) {
			orgName = statement.getOrganization().getOrgName();
			//只有层级为东区、攀枝花市和四川省时才去统计东区的事件对接的数据并加到层级里面
			if (orgName.equals(DONGQU) || orgName.equals(PANZHIHUA)|| orgName.equals(SICHUAN)) {
				map.put("orgId", statement.getOrganization().getId());
				map.put("orgName", DONGQU);
				if(issueJoint==null){
					issueJoint = baseSituationStatementDAO
							.getBaseSituationStatementByParams(map);
					//给东区统计的数据放入缓存，避免下次重复查询
					cacheService.set("issueJoint", issueJoint);
				}
				if(issueJoint==null){
					i++;
					continue;
				}
				statement.setIssueDealCount(statement.getIssueDealCount()
						+ issueJoint.getIssueDealCount());
				statement.setAgencyOfOpinionCount(statement
						.getAgencyOfOpinionCount()
						+ issueJoint.getAgencyOfOpinionCount());
				list.set(i, statement);
			}
			i++;
		}
		return list;
	}

	/** 属性封装，数据新增 */
	private void addBaseSituationStatementStatistics(
			BaseSituationStatement baseData, Integer searchType,
			List<BaseSituationStatement> listData) {
		JSONArray jsonArray = JSONArray.fromObject(listData);
		baseData.setStatisticsType(searchType);
		baseData.setStatisticsData(jsonArray.toString());
		baseSituationStatementDAO.addBaseSituationStatementStatistics(baseData);
	}

	@Override
	public List<BaseSituationStatement> findBaseSituationStatementListByCondition(
			Long orgId, int year, int month, Integer type) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构信息为获得");
		}
		if (CURRENT_LEVEL_VAL.equals(type)) {
			type = SEARCH_TYPE_FIRST;// 因为查询本层级数据，传入的ID是父ID，默认查询的就是父层级所查询的默认列表
			Organization parentOrganization = organizationDubboService
					.getSimpleOrgById(orgId).getParentOrg();
			if (parentOrganization == null) {
				throw new BusinessValidationException("父组织机构ID未获得");
			}
			orgId = parentOrganization.getId();
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
		tableService
				.createAnalyseTable(
						AnalyseUtil.BASESITUATION_STATEMENT_TABLE_NAME,
						AnalyseUtil.BASESITUATION_STATEMENT_TABLE_NAME_SQL,
						year, month);
		createBaseSituationStatementTableIndex(year, month);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("orgId", orgId);
		map.put("statisticsType", type);
		BaseSituationStatement baseSituationStatement = baseSituationStatementDAO
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

	/***
	 * 
	 * @param currentAddCount
	 *            本月新增
	 * @param currentMonthCount
	 *            本月总数
	 * @param lastMonthCount
	 *            上月总数
	 * @return
	 */
	private int getCurrentCount(int currentAddCount, int currentMonthCount,
			int lastMonthCount) {
		return currentAddCount
				+ Math.abs(lastMonthCount + currentAddCount - currentMonthCount);
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
					// 本月净产量 ：3月净变量＝3月新增量＋｜2月总量＋3月新增量－3｜
					currentBaseData.setCurrentPreventionCount(getCurrentCount(
							currentBaseData.getPreventionAddCount(),
							currentBaseData.getPreventionCount(),
							beforBaseData.getPreventionCount()));

					currentBaseData
							.setCurrentFloatingPersionCount(getCurrentCount(
									currentBaseData
											.getFloatingPersionAddCount(),
									currentBaseData.getFloatingPersionCount(),
									beforBaseData.getFloatingPersionCount()));

					currentBaseData
							.setCurrentPositivePersionCount(getCurrentCount(
									currentBaseData
											.getPositivePersionAddCount(),
									currentBaseData.getPositivePersionCount(),
									beforBaseData.getPositivePersionCount()));

					currentBaseData
							.setCurrentRectificativePersonCount(getCurrentCount(
									currentBaseData
											.getRectificativePersonAddCount(),
									currentBaseData
											.getRectificativePersonCount(),
									beforBaseData.getRectificativePersonCount()));

					currentBaseData
							.setCurrentMentalPatientPersionCount(getCurrentCount(
									currentBaseData
											.getMentalPatientPersionAddCount(),
									currentBaseData
											.getMentalPatientPersionCount(),
									beforBaseData
											.getMentalPatientPersionCount()));
					currentBaseData
							.setCurrentDruggyPersionCount(getCurrentCount(
									currentBaseData.getDruggyPersionAddCount(),
									currentBaseData.getDruggyPersionCount(),
									beforBaseData.getDruggyPersionCount()));

					currentBaseData.setCurrentIdleYouthCount(getCurrentCount(
							currentBaseData.getIdleYouthAddCount(),
							currentBaseData.getIdleYouthCount(),
							beforBaseData.getIdleYouthCount()));
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

		int currentFloatingPersionCount = 0;// 本月流口净产量
		int currentPositivePersionCount = 0;// 本月刑释人员净产量
		int currentRectificativePersonCount = 0;// 本月矫正人员净产量
		int currentMentalPatientPersionCount = 0;// 本月精神病净产量
		int currentDruggyPersionCount = 0;// 本月吸毒人员净产量
		int currentIdleYouthCount = 0;// 本月重点青少年净产量
		int currentPreventionCount = 0;// 本月群防群治人数净产量

		int agencyOfOpinionCount = 0;// 社情民意数据
		int issueDealCount = 0;// 事件处理数据

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

			currentFloatingPersionCount += lastData
					.getCurrentFloatingPersionCount();
			currentPositivePersionCount += lastData
					.getCurrentPositivePersionCount();
			currentRectificativePersonCount += lastData
					.getCurrentRectificativePersonCount();
			currentMentalPatientPersionCount += lastData
					.getCurrentMentalPatientPersionCount();
			currentDruggyPersionCount += lastData
					.getCurrentDruggyPersionCount();
			currentIdleYouthCount += lastData.getCurrentIdleYouthCount();
			currentPreventionCount += lastData.getCurrentPreventionCount();
			agencyOfOpinionCount += lastData.getAgencyOfOpinionCount();
			issueDealCount += lastData.getIssueDealCount();
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

		countBase.setCurrentFloatingPersionCount(currentFloatingPersionCount);
		countBase.setCurrentPositivePersionCount(currentPositivePersionCount);
		countBase
				.setCurrentRectificativePersonCount(currentRectificativePersonCount);
		countBase
				.setCurrentMentalPatientPersionCount(currentMentalPatientPersionCount);
		countBase.setCurrentDruggyPersionCount(currentDruggyPersionCount);
		countBase.setCurrentIdleYouthCount(currentIdleYouthCount);
		countBase.setCurrentPreventionCount(currentPreventionCount);
		// 事件和社情民意事件总数赋值
		countBase.setAgencyOfOpinionCount(agencyOfOpinionCount);
		countBase.setIssueDealCount(issueDealCount);
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
		if (CURRENT_LEVEL_VAL.equals(type)) {
			type = SEARCH_TYPE_FIRST;// 因为查询本层级数据，传入的ID是父ID，默认查询的就是父层级所查询的默认列表
			Organization parentOrganization = organizationDubboService
					.getSimpleOrgById(orgId).getParentOrg();
			if (parentOrganization == null) {
				throw new BusinessValidationException("父组织机构ID未获得");
			}
			orgId = parentOrganization.getId();
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
		for (Map.Entry<String, String> entry : AnalyseUtil.BASESITUATIONSTATEMENT_INDEX_MAP
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
