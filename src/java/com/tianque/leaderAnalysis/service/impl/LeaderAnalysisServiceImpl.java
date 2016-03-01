package com.tianque.leaderAnalysis.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.leaderAnalysis.constants.MobileModelTypes;
import com.tianque.leaderAnalysis.domain.GeneralSituation;
import com.tianque.leaderAnalysis.domain.IssueAnalysisToMobile;
import com.tianque.leaderAnalysis.domain.IssueTotalCaseListData;
import com.tianque.leaderAnalysis.domain.PopulationDetails;
import com.tianque.leaderAnalysis.domain.PopulationTableDatas;
import com.tianque.leaderAnalysis.service.IssueAnalysisToMobileService;
import com.tianque.leaderAnalysis.service.LeaderAnalysisService;
import com.tianque.plugin.analyzing.domain.BaseinfoStatisticDetailVo;
import com.tianque.plugin.analyzing.domain.BaseinfoStatisticVo;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.PopulationAreaDataVo;
import com.tianque.plugin.analyzing.domain.PopulationDetailDataVo;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;
import com.tianque.plugin.analyzing.service.BaseinfoStatisticService;
import com.tianque.plugin.analyzing.service.LeaderViewService;
import com.tianque.plugin.analyzing.service.StatisticsPopulationService;
import com.tianque.plugin.analyzing.service.TendencyChartService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Service("leaderAnalysisService")
public class LeaderAnalysisServiceImpl implements LeaderAnalysisService {

	@Autowired
	private LeaderViewService leaderViewService;
	@Autowired
	private StatisticsPopulationService statisticsPopulationService;
	@Autowired
	private TendencyChartService tendencyChartService;
	@Autowired
	private BaseinfoStatisticService baseinfoStatisticService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private IssueAnalysisToMobileService issueAnalysisToMobileService;

	public static final String CACHE_MOBILE_TABLE_KEY = "MOBILE_TABLE_KEY_";
	public static final String CACHE_MOBILE_PIE_KEY = "MOBILE_PIE_KEY_";
	public static final String CACHE_MOBILE_LINEAR_KEY = "MOBILE_LINEAR_KEY_";
	public static final String CACHE_MOBILE_COLUMN_KEY = "MOBILE_PCOLUMN_KEY_";

	/***
	 * 人口，事件总况统计入口
	 */
	@Override
	public Map<Integer, List<GeneralSituation>> getGeneralSituationDatas(
			Long orgId, boolean isGrid) {
		// 人口信息，户籍人口，流动人口
		Map<Integer, List<GeneralSituation>> generalSituationDatas = new HashMap<Integer, List<GeneralSituation>>();
		List<GeneralSituation> populationData = getPopulationGeneralSituationListDataByType(
				orgId, isGrid, MobileModelTypes.POPULATION_TABLE_MAP);
		// 特殊人群
		List<GeneralSituation> specialpopulationData = getPopulationGeneralSituationListDataByType(
				orgId, isGrid, MobileModelTypes.SPECIALPOPULATION_TABLE_MAP);
		// 关怀对象
		List<GeneralSituation> civilData = getCivilData(getPopulationGeneralSituationListDataByType(
				orgId, isGrid, MobileModelTypes.CIVIL_TABLE_MAP));
		// 育龄妇女
		List<GeneralSituation> nurtureswomenData = getPopulationGeneralSituationListDataByType(
				orgId, isGrid, MobileModelTypes.NURTURESWOMEN_TABLE_MAP);
		// 失业人员
		List<GeneralSituation> unemployedData = getPopulationGeneralSituationListDataByType(
				orgId, isGrid, MobileModelTypes.UNEMPLOYEDPEOPLE_TABLE_MAP);
		// 事件
		List<GeneralSituation> issueData = getIssueSituationListData(orgId);

		generalSituationDatas.put(MobileModelTypes.TYPE_POPULATION,
				populationData);
		generalSituationDatas.put(MobileModelTypes.TYPE_SPECIALPOPULATION,
				specialpopulationData);
		generalSituationDatas.put(MobileModelTypes.TYPE_CIVIL, civilData);
		generalSituationDatas.put(MobileModelTypes.TYPE_NURTURESWOMEN,
				nurtureswomenData);
		generalSituationDatas.put(MobileModelTypes.TYPE_UNEMPLOYEDPEOPLE,
				unemployedData);
		generalSituationDatas.put(MobileModelTypes.TYPE_ISSUE, issueData);

		return generalSituationDatas;
	}

	/**
	 * 获取事件主页数据区分出各个大类
	 * 
	 * @param orgId
	 * @return
	 */
	private List<GeneralSituation> getIssueSituationListData(Long orgId) {
		GeneralSituation generalSituation = null;
		String issueType = "";
		String key = MemCacheConstant.GETISSUESITUATIONLISTDATAFORMOBILE + "_"
				+ orgId;
		List<GeneralSituation> result = (List<GeneralSituation>) cacheService
				.get(MemCacheConstant.LEADERVIEW_NAMESPACE, key);

		if (result == null) {
			result = new ArrayList<GeneralSituation>();
			for (Entry<String, String> map : MobileModelTypes.ISSUE_MODEL_MAP
					.entrySet()) {
				issueType = map.getKey();
				if (MobileModelTypes.ALL_ISSUE_MODEL.equals(issueType)) {
					continue;
				}
				int year = CalendarUtil.getNowYear();
				int month = CalendarUtil.getNowMonth();
				generalSituation = new GeneralSituation();
				generalSituation.setCname(map.getValue());
				generalSituation.setTableName(issueType);
				IssueAnalysisToMobile data = issueAnalysisToMobileService
						.getIssueDatasListTotalByOrgParentId(issueType, orgId,
								year, month);
				if (data != null) {
					generalSituation.setCount(data.getIssueTotal());
					generalSituation.setProportion(computePercentage(
							data.getAddIssueSum(), data.getIssueTotal()));
				}
				result.add(generalSituation);
			}
		}
		cacheService.set(MemCacheConstant.LEADERVIEW_NAMESPACE, key, result);
		return result;
	}

	// 获取统计对象
	private List<GeneralSituation> getPopulationGeneralSituationListDataByType(
			Long orgId, boolean isGrid, Map<String, Integer> tableMap) {
		List<GeneralSituation> GeneralSituationDatas = new ArrayList<GeneralSituation>();
		if (tableMap != null) {
			for (Entry<String, Integer> map : tableMap.entrySet()) {
				// 排除关怀总况，后来计算得出
				if (!BaseInfoTables.CIVIL_KEY.equals(map.getKey())) {
					GeneralSituationDatas
							.add(getPopulationGeneralSituationDataByType(orgId,
									map.getKey(), isGrid, map.getValue()));
				}
			}
		}
		return GeneralSituationDatas;
	}

	// 获取统计对象
	private GeneralSituation getPopulationGeneralSituationDataByType(
			Long orgId, String tableName, boolean isGrid, int childType) {
		GeneralSituation generalSituation = new GeneralSituation();
		generalSituation.setTableName(tableName);
		// 中文名称
		generalSituation.setCname(MobileModelTypes.CNAME_MAP.get(tableName));
		List<StatisticsBaseInfoAddCountVo> populationData = null;
		// 获取总况的方法
		if (BaseInfoTables.POPULATION_KEY.equals(tableName)
				|| BaseInfoTables.IMPORTANTPERSONNEL_KEY.equals(tableName)
				|| BaseInfoTables.CIVIL_KEY.equals(tableName)) {
			populationData = leaderViewService.personGeneralCondition(orgId,
					tableName);
		} else {
			// 获取子类的的方法
			populationData = leaderViewService.statisticsBaseInfo(orgId,
					tableName, isGrid);
		}
		generalSituation.setChildType(childType);
		generalSituation.setCount(populationData.get(populationData.size() - 1)
				.getAllCount());
		generalSituation.setProportion(computePercentage(
				populationData.get(populationData.size() - 1)
						.getThisMonthCount(),
				populationData.get(populationData.size() - 1).getAllCount()));
		// generalSituation.setDataInfo(populationData);
		generalSituation.setMonthCount(populationData.get(
				populationData.size() - 1).getThisMonthCount());
		return generalSituation;
	}

	private List<GeneralSituation> getCivilData(List<GeneralSituation> data) {
		List<GeneralSituation> civilData = new ArrayList<GeneralSituation>();
		int count = 0;
		int monthCount = 0;
		if (data != null && data.size() > 0) {
			civilData.addAll(data);
			for (GeneralSituation generalSituation : civilData) {
				count += generalSituation.getCount();
				monthCount += generalSituation.getMonthCount();
			}
		}
		GeneralSituation civil = new GeneralSituation();
		civil.setCount(count);
		civil.setChildType(MobileModelTypes.CIVIL_GENERAL);
		civil.setTableName(BaseInfoTables.CIVIL_KEY);
		civil.setProportion(computePercentage(monthCount, count));
		civil.setCname(MobileModelTypes.CNAME_MAP.get(BaseInfoTables.CIVIL_KEY));
		civilData.add(civil);
		return civilData;
	}

	/*** 人口,详情,返回四张图表 **/
	@Override
	public Map<String, Object> getPopulationDatasDetails(String tableName,
			Integer orgLevelDistance, Long orgId, int year, int month) {
		Map<String, Object> populationDatas = new HashMap<String, Object>();
		if (orgId == null) {
			throw new BusinessValidationException("部门编号错误!");
		}

		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			throw new BusinessValidationException("所查月份数据尚未生产！");
		}
		populationDatas.put(
				MobileModelTypes.VIEW_TABLE,
				getPopulationTableDatas(tableName, orgLevelDistance, orgId,
						year, month));
		populationDatas.put(
				MobileModelTypes.VIEW_COLUMN,
				getPopulationColumnDatas(tableName, orgLevelDistance, orgId,
						year, month));
		populationDatas.put(
				MobileModelTypes.VIEW_PIE,
				getPopulationPieDatas(tableName, orgLevelDistance, orgId, year,
						month));
		populationDatas.put(
				MobileModelTypes.VIEW_LINEAR,
				getPopulationLinearDatas(tableName, orgLevelDistance, orgId,
						year, month));
		return populationDatas;
	}

	// 列表（总况单独分开，其他可通用）
	private List<PopulationTableDatas> getPopulationTableDatas(
			String tableName, Integer orgLevelDistance, Long orgId, int year,
			int month) {
		String cacheKey = CACHE_MOBILE_TABLE_KEY + "_" + tableName + "_" + year
				+ "_" + month + "_" + +orgId;
		// 查询的是总况
		List<PopulationTableDatas> tableDatas = (List<PopulationTableDatas>) cacheService
				.get(cacheKey);
		if (tableDatas != null) {
			return tableDatas;
		} else {
			tableDatas = new ArrayList<PopulationTableDatas>();
		}
		// 人口总况，包括：人口总数，户籍，流口，特殊，关怀，失业，育龄
		if (BaseInfoTables.POPULATION_KEY.equals(tableName)) {
			tableDatas = getPopulationTableDatasForGeneral("",
					orgLevelDistance, orgId, year, month);
		} else if (BaseInfoTables.FLOATINGPOPULATION_KEY.equals(tableName)
				|| BaseInfoTables.HOUSEHOLDSTAFF_KEY.equals(tableName)) {
			tableDatas = getPopulationTableDatasForAreaData(
					statisticsPopulationService.getAreaDateByDate(orgId,
							tableName, year, month, orgLevelDistance),
					tableDatas);
		} else {
			if (BaseInfoTables.IMPORTANTPERSONNEL_KEY.equals(tableName)) {
				tableName = MobileModelTypes.POPULATION_TYPE_ATTENTION;
			} else if (BaseInfoTables.CIVIL_KEY.equals(tableName)) {
				tableName = MobileModelTypes.POPULATION_TYPE_CIVIL;
			}
			tableDatas = getPopulationTableDatasForBaseInfo(
					baseinfoStatisticService.getStatisticInfoForList(orgId,
							year, month, tableName, null, orgLevelDistance),
					tableDatas);
		}
		cacheService.set(cacheKey, tableDatas);
		return tableDatas;
	}

	// 户籍人口，流动人口详细
	private List<PopulationTableDatas> getPopulationTableDatasForAreaData(
			List<PopulationAreaDataVo> areaDataVos,
			List<PopulationTableDatas> tableDatas) {
		if (areaDataVos != null) {
			List<PopulationDetails> pdList = null;
			PopulationDetails pd = null;
			PopulationTableDatas populationTableDatas = null;
			//int sum = 0;
			for (PopulationAreaDataVo padv : areaDataVos) {
				// if (padv.getOrgId() != null) {
				pdList = new ArrayList<PopulationDetails>();
				populationTableDatas = new PopulationTableDatas();
				populationTableDatas.setOrganization(padv.getOrg());
				populationTableDatas.setOrgId(padv.getOrgId());
				populationTableDatas.setSum(padv.getAmount() == null ? 0 : padv
						.getAmount());
				for (PopulationDetailDataVo pddv : padv
						.getPopulationDetailDatas()) {
					pd = new PopulationDetails();
					pd.setCount(pddv.getAmount());
					pd.setType(pddv.getName());
					pdList.add(pd);
				}
				populationTableDatas.setPopulationList(pdList);
				tableDatas.add(populationTableDatas);

				// }
			}
		}

		return tableDatas;
	}

	// 非总况人口统计表
	private List<PopulationTableDatas> getPopulationTableDatasForBaseInfo(
			List<BaseinfoStatisticVo> baseInfoList,
			List<PopulationTableDatas> tableDatas) {
		// List<PopulationTableDatas> ptdList = null;
		if (baseInfoList != null) {
			// ptdList = new ArrayList<PopulationTableDatas>();
			List<PopulationDetails> pdList = null;
			PopulationTableDatas populationTableDatas = null;
			PopulationDetails pd = null;
			for (BaseinfoStatisticVo baseInfo : baseInfoList) {
				populationTableDatas = new PopulationTableDatas();
				populationTableDatas.setOrganization(baseInfo.getOrg());
				populationTableDatas.setOrgId(baseInfo.getOrgId());
				populationTableDatas.setOrgName(baseInfo.getOrgName());
				pdList = new ArrayList<PopulationDetails>();
				int sum = 0;
				// if
				// (!MobileModelTypes.DISNAME_SUM.equals(baseInfo.getOrgName()))
				// {
				for (BaseinfoStatisticDetailVo bsdv : baseInfo
						.getBaseinfoStatisticDetailVo()) {
					if (!MobileModelTypes.DISNAME_SUM
							.equals(bsdv.getTypeName())) {
						pd = new PopulationDetails();
						int count = Long.valueOf(bsdv.getSum()).intValue();
						pd.setCount(count);
						sum += count;
						pd.setMonthCreate(Long.valueOf(
								bsdv.getMonthCreate() == null ? 0 : bsdv
										.getMonthCreate()).intValue());
						pd.setType(bsdv.getTypeName());
						pdList.add(pd);
					}
				}
				// }
				populationTableDatas.setSum(sum);
				populationTableDatas.setPopulationList(pdList);
				tableDatas.add(populationTableDatas);
			}
		}
		return tableDatas;
	}

	// 总况表数据
	private List<PopulationTableDatas> getPopulationTableDatasForGeneral(
			String tableName, Integer orgLevelDistance, Long orgId, int year,
			int month) {
		List<PopulationTableDatas> tableDatas = new ArrayList<PopulationTableDatas>();
		List<PopulationAreaDataVo> populationData = statisticsPopulationService
				.getAreaDateByDate(orgId, tableName, year, month,
						orgLevelDistance);
		if (populationData != null && populationData.size() > 0) {
			PopulationDetails pType = null;
			PopulationTableDatas pTableData = null;
			List<PopulationDetails> typeList = null;
			for (PopulationAreaDataVo population : populationData) {
				if (population.getOrgId() != null) {
					pTableData = new PopulationTableDatas();
					typeList = new ArrayList<PopulationDetails>();
					pTableData.setOrganization(population.getOrg());
					pTableData.setOrgId(population.getOrgId());
					pTableData.setSum(population.getAmount());
					for (PopulationDetailDataVo pddv : population
							.getPopulationDetailDatas()) {
						if (MobileModelTypes.DISNAME_FLOATING.equals(pddv
								.getName())
								|| MobileModelTypes.DISNAME_HOUSEHOLDSTAFF
										.equals(pddv.getName())) {
							pType = new PopulationDetails();
							pType.setCount(pddv.getAmount());
							pType.setType(pddv.getName());
							typeList.add(pType);
						}
					}
					for (String type : MobileModelTypes.POPULATION_TYPE_LIST) {
						List<BaseinfoStatisticVo> statisticList = baseinfoStatisticService
								.getStatisticInfoForList(orgId, year, month,
										type, "", null);
						typeList = getgetPopulationTableDatasForGeneralByOther(
								typeList, statisticList, population, pType,
								type);
					}
					pTableData.setPopulationList(typeList);
					tableDatas.add(pTableData);

				}
			}
		}

		return tableDatas;
	}

	// 除去其户籍和流口的数据
	private List<PopulationDetails> getgetPopulationTableDatasForGeneralByOther(
			List<PopulationDetails> typeList,
			List<BaseinfoStatisticVo> statisticList,
			PopulationAreaDataVo population, PopulationDetails pType,
			String type) {
		for (BaseinfoStatisticVo bsvo : statisticList) {
			// 组织机构相同的就进行统计
			if (population.getOrgId() != null
					&& population.getOrgId().equals(bsvo.getOrgId())) {
				for (BaseinfoStatisticDetailVo bsdv : bsvo
						.getBaseinfoStatisticDetailVo()) {
					pType = new PopulationDetails();
					if (type.equals(MobileModelTypes.POPULATION_TYPE_ATTENTION)) {
						if (MobileModelTypes.DISNAME_SUM.equals(bsdv
								.getTypeName())) {
							pType.setType(MobileModelTypes.DISNAME_ATTENTION);
							pType.setCount((new Long(bsdv.getSum())).intValue());
							typeList.add(pType);
						}
					} else if (type
							.equals(MobileModelTypes.POPULATION_TYPE_CIVIL)) {
						if (MobileModelTypes.DISNAME_SUM.equals(bsdv
								.getTypeName())) {
							pType.setType(MobileModelTypes.DISNAME_CIVIL);
							pType.setCount((new Long(bsdv.getSum())).intValue());
							typeList.add(pType);
						}
					} else if (type
							.equals(MobileModelTypes.POPULATION_TYPE_NURTURESWOMEN)) {
						pType.setType(MobileModelTypes.DISNAME_NURTURESWOMEN);
						pType.setCount((new Long(bsdv.getSum())).intValue());
						typeList.add(pType);
					} else if (type
							.equals(MobileModelTypes.POPULATION_TYPE_UNEMPLOYEDPEOPLE)) {
						pType.setType(MobileModelTypes.DISNAME_UNEMPLOYEDPEOPLE);
						pType.setCount((new Long(bsdv.getSum())).intValue());
						typeList.add(pType);
					}
				}
			}
		}
		return typeList;
	}

	// 柱形图(基本通用)
	private HighchartColumnVo getPopulationColumnDatas(String tableName,
			Integer orgLevelDistance, Long orgId, int year, int month) {
		String cacheKey = CACHE_MOBILE_COLUMN_KEY + "_" + tableName + "_"
				+ year + "_" + month + "_" + orgId;
		HighchartColumnVo columnVo = (HighchartColumnVo) cacheService
				.get(cacheKey);
		if (columnVo != null) {
			return columnVo;
		}
		if (tableName == null || "".equals(tableName)
				|| BaseInfoTables.POPULATION_KEY.equals(tableName)) {
			tableName = "";
		} else if (BaseInfoTables.IMPORTANTPERSONNEL_KEY.equals(tableName)) {
			tableName = MobileModelTypes.POPULATION_TYPE_ATTENTION;
		} else if (BaseInfoTables.CIVIL_KEY.equals(tableName)) {
			tableName = MobileModelTypes.POPULATION_TYPE_CIVIL;
		}
		if (NewBaseInfoTables.bussinessPopulationTypes.containsKey(tableName)) {
			columnVo = baseinfoStatisticService
					.getArealDistributionListFromHistory(orgId, tableName,
							year, month);
		} else {
			columnVo = statisticsPopulationService.getPopulationColumnByTime(
					orgId, tableName, year, month);
		}
		cacheService.set(cacheKey, columnVo);
		return columnVo;
	}

	// 饼形图，沿用以前的数据
	private List<Object[]> getPopulationPieDatas(String tableName,
			Integer orgLevelDistance, Long orgId, int year, int month) {
		if (BaseInfoTables.FLOATINGPOPULATION_KEY.equals(tableName)
				|| BaseInfoTables.NURTURESWOMEN_KEY.equals(tableName)
				|| BaseInfoTables.UNEMPLOYED_KEY.equals(tableName)) {
			return null;
		}
		List<Object[]> pieDatas = new ArrayList<Object[]>();
		if (BaseInfoTables.POPULATION_KEY.equals(tableName)) {
			pieDatas = statisticsPopulationService.getPopulationPieInfo(year,
					month, orgId, "");
		} else if (BaseInfoTables.HOUSEHOLDSTAFF_KEY.equals(tableName)) {
			pieDatas = statisticsPopulationService.getPopulationPieInfo(year,
					month, orgId, tableName);
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(orgId);
			if (BaseInfoTables.IMPORTANTPERSONNEL_KEY.equals(tableName)) {
				tableName = MobileModelTypes.POPULATION_TYPE_ATTENTION;
			} else if (BaseInfoTables.CIVIL_KEY.equals(tableName)) {
				tableName = MobileModelTypes.POPULATION_TYPE_CIVIL;
			}
			pieDatas = baseinfoStatisticService.getStatisticInfo(year, month,
					tableName, organization.getOrgInternalCode(), null);
		}
		return pieDatas;
	}

	// // 饼形图（人口确定，其他按照以前的业务）
	// private Map<String, String> getPopulationPieDatas(String tableName,
	// Integer orgLevelDistance, Long orgId, int year, int month) {
	// String cacheKey = CACHE_MOBILE_PIE_KEY + tableName + orgId + year
	// + month;
	// Map<String, String> pieData = (Map<String, String>) cacheService
	// .get(cacheKey);
	// if (pieData != null) {
	// return pieData;
	// } else {
	// pieData = new HashMap<String, String>();
	// }
	// if (tableName == null || "".equals(tableName)
	// || BaseInfoTables.POPULATION_KEY.equals(tableName)) {
	// // 人口总况
	// // 直接从列表数据中取数据
	// List<PopulationTableDatas> tableDatas = getPopulationTableDatas(
	// tableName, orgLevelDistance, orgId, year, month);
	// if (tableDatas != null) {
	// int floatingCount = 0;
	// int householdstaffCount = 0;
	// int nurtureswomenCount = 0;
	// int unemployedpeopleCount = 0;
	// int civilCount = 0;
	// int attentionCount = 0;
	// for (PopulationTableDatas table : tableDatas) {
	// for (PopulationDetails type : table.getPopulationList()) {
	// String disName = type.getType();
	// int count = type.getCount();
	// if (MobileModelTypes.DISNAME_FLOATING.equals(disName)) {
	// floatingCount += count;
	// } else if (MobileModelTypes.DISNAME_HOUSEHOLDSTAFF
	// .equals(disName)) {
	// householdstaffCount += count;
	// } else if (MobileModelTypes.DISNAME_NURTURESWOMEN
	// .equals(disName)) {
	// nurtureswomenCount += count;
	// } else if (MobileModelTypes.DISNAME_UNEMPLOYEDPEOPLE
	// .equals(disName)) {
	// unemployedpeopleCount += count;
	// } else if (MobileModelTypes.DISNAME_CIVIL
	// .equals(disName)) {
	// civilCount += count;
	// } else if (MobileModelTypes.DISNAME_ATTENTION
	// .equals(disName)) {
	// attentionCount += count;
	// }
	//
	// }
	// }
	// pieData.put(MobileModelTypes.DISNAME_FLOATING, floatingCount
	// + "");
	// pieData.put(MobileModelTypes.DISNAME_HOUSEHOLDSTAFF,
	// householdstaffCount + "");
	// pieData.put(MobileModelTypes.DISNAME_NURTURESWOMEN,
	// nurtureswomenCount + "");
	// pieData.put(MobileModelTypes.DISNAME_ATTENTION, attentionCount
	// + "");
	// pieData.put(MobileModelTypes.DISNAME_CIVIL, civilCount + "");
	// pieData.put(MobileModelTypes.DISNAME_UNEMPLOYEDPEOPLE,
	// unemployedpeopleCount + "");
	// }
	// } else {
	//
	// }
	// cacheService.set(cacheKey, pieData);
	// return pieData;
	// }

	// 线形图(基本通用，人口，户籍，流口公用一个接口其他的公用一个接口)
	private HighchartColumnVo getPopulationLinearDatas(String tableName,
			Integer orgLevelDistance, Long orgId, int year, int month) {
		String cacheKey = CACHE_MOBILE_LINEAR_KEY + "_" + tableName + "_"
				+ year + "_" + month + "_" + orgId;
		HighchartColumnVo columnVo = (HighchartColumnVo) cacheService
				.get(cacheKey);
		if (columnVo != null) {
			return columnVo;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		if (BaseInfoTables.POPULATION_KEY.equals(tableName)
				|| BaseInfoTables.HOUSEHOLDSTAFF_KEY.equals(tableName)
				|| BaseInfoTables.FLOATINGPOPULATION_KEY.equals(tableName)) {
			columnVo = tendencyChartService.findTendencyChart(tableName,
					organization.getOrgInternalCode());
		} else {
			if (BaseInfoTables.IMPORTANTPERSONNEL_KEY.equals(tableName)) {
				tableName = MobileModelTypes.POPULATION_TYPE_ATTENTION;
			} else if (BaseInfoTables.CIVIL_KEY.equals(tableName)) {
				tableName = MobileModelTypes.POPULATION_TYPE_CIVIL;
			}
			columnVo = tendencyChartService.findTendencyChartForPositiveinfo(
					tableName, organization.getOrgInternalCode());
		}
		cacheService.set(cacheKey, columnVo);
		return columnVo;
	}

	@Override
	public List<IssueAnalysisToMobile> getIssueDatasList(String issueType,
			Long orgId, int year, int month) {
		if (orgId == null || !StringUtil.isStringAvaliable(issueType)
				|| !MobileModelTypes.ISSUE_MODEL_MAP.containsKey(issueType)) {
			throw new BusinessValidationException("参数错误!");
		}
		String key = MemCacheConstant.GETISSUEDATASLISTFORMOBILE + "_"
				+ issueType + "_" + year + "_" + month + "_" + orgId;

		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			throw new BusinessValidationException("所查月份数据尚未生产！");
		}
		try {
			List<IssueAnalysisToMobile> result = (List<IssueAnalysisToMobile>) cacheService
					.get(MemCacheConstant.LEADERVIEW_NAMESPACE, key);
			if (result == null) {
				result = issueAnalysisToMobileService.getIssueDatasList(
						issueType, orgId, year, month);
				cacheService.set(MemCacheConstant.LEADERVIEW_NAMESPACE, key,
						result);
			}
			return result;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类LeaderAnalysisServiceImpl的getIssueDatasList方法出现异常，原因：",
					"手机事件的研判分析事件各个大类对应的列表错误", e);
		}
	}

	/***
	 * 计算百分比
	 * 
	 * @param formula
	 * @param denominator
	 * @return
	 */
	private String computePercentage(int formula, int denominator) {
		String result = "";
		// 创建一个数值格式化对象

		NumberFormat numberFormat = NumberFormat.getInstance();

		// 设置精确到小数点后2位

		numberFormat.setMaximumFractionDigits(1);
		if (denominator != 0) {
			if (formula > denominator) {
				result = "100";
			} else {
				result = numberFormat.format((float) formula
						/ (float) denominator * 100);
			}

		} else {
			result = "0.0";
		}

		result = result + "%";
		return result;
	}

	@Override
	public List<IssueTotalCaseListData> getIssueTotalCaseListForMobile(
			Long orgId, int year, int month) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误!");
		}

		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			throw new BusinessValidationException("所查月份数据尚未生产！");
		}
		List<Organization> orgs = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		if (orgs == null || orgs.size() == 0) {
			return new ArrayList<IssueTotalCaseListData>();
		}
		try {
			String key = MemCacheConstant.GETISSUETOTALCASELISTFORMOBILE + "_"
					+ year + "_" + month + "_" + orgId;
			List<IssueTotalCaseListData> results = (List<IssueTotalCaseListData>) cacheService
					.get(MemCacheConstant.LEADERVIEW_NAMESPACE, key);
			if (results == null) {

				GeneralSituation generalSituation = null;
				String issueType = "";
				List<GeneralSituation> datas = null;
				List<GeneralSituation> totalDatas = new ArrayList<GeneralSituation>();
				Map<String, Map<String, Integer>> totalDataMap = new HashMap<String, Map<String, Integer>>();
				results = new ArrayList<IssueTotalCaseListData>();
				Map<String, Integer> dataMap = null;
				IssueTotalCaseListData issueTotalCaseListData = null;
				for (Organization org : orgs) {
					issueTotalCaseListData = new IssueTotalCaseListData();

					datas = new ArrayList<GeneralSituation>();
					for (Entry<String, String> map : MobileModelTypes.ISSUE_MODEL_MAP
							.entrySet()) {
						issueType = map.getKey();
						if (MobileModelTypes.ALL_ISSUE_MODEL.equals(issueType)) {
							continue;
						}

						generalSituation = new GeneralSituation();
						generalSituation.setCname(map.getValue());
						generalSituation.setTableName(issueType);
						IssueAnalysisToMobile data = issueAnalysisToMobileService
								.getIssueDatasListTotalByOrgParentId(issueType,
										orgId, year, month);
						if (data != null) {
							generalSituation.setCount(data.getIssueTotal());
							generalSituation
									.setProportion(computePercentage(
											data.getAddIssueSum(),
											data.getIssueTotal()));

							if (totalDataMap.get(issueType) == null) {
								dataMap = new HashMap<String, Integer>();
								dataMap.put("issueTotal", data.getIssueTotal());
								dataMap.put("addIssueSum",
										data.getAddIssueSum());
								totalDataMap.put(issueType, dataMap);
							} else {
								dataMap = totalDataMap.get(issueType);
								dataMap.put(
										"issueTotal",
										dataMap.get("issueTotal")
												+ data.getIssueTotal());
								dataMap.put(
										"addIssueSum",
										dataMap.get("addIssueSum")
												+ data.getAddIssueSum());
								totalDataMap.put(issueType, dataMap);
							}

						}
						datas.add(generalSituation);
					}
					issueTotalCaseListData.setOrg(org);
					issueTotalCaseListData.setGeneralSituations(datas);
					results.add(issueTotalCaseListData);
				}
				for (Entry<String, String> map : MobileModelTypes.ISSUE_MODEL_MAP
						.entrySet()) {
					issueType = map.getKey();
					if (MobileModelTypes.ALL_ISSUE_MODEL.equals(issueType)) {
						continue;
					}

					generalSituation = new GeneralSituation();
					generalSituation.setCname(map.getValue());
					generalSituation.setTableName(issueType);
					dataMap = totalDataMap.get(issueType);
					if (dataMap != null) {
						generalSituation.setCount(dataMap.get("issueTotal"));
						generalSituation.setProportion(computePercentage(
								dataMap.get("addIssueSum"),
								dataMap.get("issueTotal")));
					}
					totalDatas.add(generalSituation);
				}
				issueTotalCaseListData = new IssueTotalCaseListData();
				Organization totalOrg = new Organization();
				totalOrg.setOrgName("合计");
				issueTotalCaseListData.setOrg(totalOrg);
				issueTotalCaseListData.setGeneralSituations(totalDatas);
				results.add(issueTotalCaseListData);
				cacheService.set(MemCacheConstant.LEADERVIEW_NAMESPACE, key,
						results);
			}
			return results;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类LeaderAnalysisServiceImpl的getIssueTotalCaseListForMobile方法出现异常，原因：",
					"手机事件的研判分析事件总况对应的列表错误", e);
		}
	}
}
