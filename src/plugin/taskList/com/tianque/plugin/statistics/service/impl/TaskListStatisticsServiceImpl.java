package com.tianque.plugin.statistics.service.impl;

import java.util.ArrayList;
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
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.PropertyDomain;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.HighchartDataColumnVo;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.plugin.statistics.constants.GeneralStituationConstants;
import com.tianque.plugin.statistics.constants.TypeConstants;
import com.tianque.plugin.statistics.dao.TaskListStatisticsDao;
import com.tianque.plugin.statistics.domain.GeneralSituation;
import com.tianque.plugin.statistics.service.TaskListStatisticsService;
import com.tianque.plugin.statistics.util.GenneralsituationUtil;
import com.tianque.plugin.statistics.vo.TaskListMapVo;
import com.tianque.plugin.statistics.vo.TaskListStatisticsVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tableManage.service.TableManageService;
import com.tianque.userAuth.api.PropertyDictDubboService;
import com.tianque.userAuth.api.PropertyDomainDubboService;

@Service("taskListStatisticsService")
@Transactional
public class TaskListStatisticsServiceImpl implements TaskListStatisticsService {

	@Autowired
	private TaskListStatisticsDao taskListStatisticsDao;
	@Autowired
	private TableManageService tableService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;
	 @Autowired
	 private PropertyDomainDubboService propertyDomainDubboService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private CacheService cacheService;

	private List<GeneralSituation> getTaskListColumnList(
			TaskListStatisticsVo taskListStatisticsVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", taskListStatisticsVo.getOrgId());
		map.put("orgType",
				propertyDictDubboService
						.findPropertyDictByDomainNameAndDictDisplayName(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.ADMINISTRATIVE_KEY).getId());
		String tableName = getTableName(taskListStatisticsVo);
		if (!StringUtil.isStringAvaliable(tableName)) {
			throw new BusinessValidationException("查询失败，未获得表名");
		}
		map.put("tableName", tableName);
		map.put("searchCurrent", "false");
		List<GeneralSituation> list = null;
		List<GeneralSituation> deallist = new ArrayList<GeneralSituation>();
		if (taskListStatisticsVo.getSituationType() == 0) {
			if (taskListStatisticsVo.getSelectTypes().split(",").length == 1
					&& taskListStatisticsVo.getSelectTypes().equals(
							TypeConstants.PANDECT_KEY)) {
				taskListStatisticsVo
						.setSelectTypes(TypeConstants.allBasesicType);
			}
			map.put("basesicType",
					taskListStatisticsVo.getSelectTypes().split(","));
			list = taskListStatisticsDao.getTaskListColumn(map);
		} else if (taskListStatisticsVo.getSituationType() == 1) {
			if (taskListStatisticsVo.getSelectTypes().split(",").length == 1
					&& taskListStatisticsVo.getSelectTypes().equals(
							TypeConstants.PANDECT_KEY)) {
				taskListStatisticsVo
						.setSelectTypes(TypeConstants.allFloatingType);
			}
			map.put("detailType",
					taskListStatisticsVo.getSelectTypes().split(","));
			list = taskListStatisticsDao.getFloatingColumn(map);
		} else if (taskListStatisticsVo.getSituationType() == 3) {// 特殊人群
			if (taskListStatisticsVo.getSelectTypes().split(",").length == 1
					&& taskListStatisticsVo.getSelectTypes().equals(
							TypeConstants.SPECIAL_KEY)) {
				taskListStatisticsVo
						.setSelectTypes(TypeConstants.allSpecialType);
			}
			map.put("basesicType",
					taskListStatisticsVo.getSelectTypes().split(","));
			list = taskListStatisticsDao.getTaskListColumn(map);
		}
		if (list == null || list.size() == 0) {
			list = new ArrayList<GeneralSituation>();
			List<Organization> orgList = organizationDubboService
					.findAdminOrgsByParentId(taskListStatisticsVo.getOrgId());
			// if (orgList == null || orgList.size() == 0) {
			// return null;
			// }
			for (Organization organization : orgList) {
				String[] basesicType = taskListStatisticsVo.getSelectTypes()
						.split(",");
				for (String type : basesicType) {
					if (taskListStatisticsVo.getSituationType() == 0) {
						GeneralSituation generalSituation = new GeneralSituation(
								organization, type, null);
						list.add(generalSituation);
					} else {
						GeneralSituation generalSituation = new GeneralSituation(
								organization, null, type);
						list.add(generalSituation);
					}
				}
			}
		} else {
			List<Organization> orgList = organizationDubboService
					.findAdminOrgsByParentId(taskListStatisticsVo.getOrgId());
			// if (orgList == null || orgList.size() == 0) {
			// return highchartColumnVo;
			// }
			int i = 0;
			int j = 0;
			for (GeneralSituation generalSituation : list) {
				if (taskListStatisticsVo.getSituationType() == 0
						|| taskListStatisticsVo.getSituationType() ==3) {
					if (generalSituation.getBasesicType() == null) {
						for (String type : taskListStatisticsVo
								.getSelectTypes().split(",")) {
							GeneralSituation g = new GeneralSituation(
									orgList.get(i), type, null);
							deallist.add(g);
						}
						i++;
					} else {
						deallist.add(generalSituation);
						j++;
						if (j == taskListStatisticsVo.getSelectTypes().split(
								",").length) {
							i++;
							j = 0;
						}
					}
				} else {
					if (generalSituation.getDetailType() == null) {
						for (String type : taskListStatisticsVo
								.getSelectTypes().split(",")) {
							GeneralSituation g = new GeneralSituation(
									orgList.get(i), null, type);
							deallist.add(g);
						}
						i++;
					} else {
						deallist.add(generalSituation);
						j++;
						if (j == taskListStatisticsVo.getSelectTypes().split(
								",").length) {
							i++;
							j = 0;
						}
					}
				}
			}
		}
		return deallist;
	}

	@Override
	public HighchartColumnVo getTaskListColumn(
			TaskListStatisticsVo taskListStatisticsVo) {
		
		HighchartColumnVo highchartColumnVo = null;
		if (taskListStatisticsVo == null
				|| taskListStatisticsVo.getSearchDateType() == null
				|| taskListStatisticsVo.getYear() == null
				|| taskListStatisticsVo.getIsSegin() == null
				|| taskListStatisticsVo.getOrgId() == null
				|| taskListStatisticsVo.getSituationType() == null) {
			throw new BusinessValidationException("数据查询失败，条件不正确");
		}
		String[] keyAndNameSpace = GenneralsituationUtil.getCachKeyByTypes(taskListStatisticsVo,0,1);
		String key = keyAndNameSpace[1];
		String nameSpace = keyAndNameSpace[0];
		if(key != null){
			highchartColumnVo = (HighchartColumnVo)cacheService.get(nameSpace, key);
			if(highchartColumnVo != null){
				highchartColumnVo.setModuleName("任务清单柱状统计");
				return highchartColumnVo;
			}
		}
		highchartColumnVo = new HighchartColumnVo();
		highchartColumnVo.setModuleName("任务清单柱状统计");
		List<GeneralSituation> deallist = getTaskListColumnList(taskListStatisticsVo);
		if (taskListStatisticsVo.getIsSegin() == 2
				&& taskListStatisticsVo.getSelectTypes().split(",").length == 1) {
			// 只选择了一个类别，并且需要对比上报和签收
			List<Organization> orgList = organizationDubboService
					.findAdminOrgsByParentId(taskListStatisticsVo.getOrgId());
			if (orgList == null || orgList.size() == 0) {
				return highchartColumnVo;
			}
			highchartColumnVo = getHighchartColumnForSign(deallist, taskListStatisticsVo,
					highchartColumnVo, orgList.size());
			if(key != null){
				cacheService.set(
						nameSpace,
						key, ModulTypes.CACHETIME, highchartColumnVo);
			}
			return highchartColumnVo;
		}
		highchartColumnVo = getHighchartColumn(deallist, taskListStatisticsVo,
				highchartColumnVo);
		if(key != null){
			cacheService.set(
					nameSpace,
					key, ModulTypes.CACHETIME, highchartColumnVo);
		}
		return highchartColumnVo;
	}

	private HighchartColumnVo getHighchartColumnForSign(
			List<GeneralSituation> list,
			TaskListStatisticsVo taskListStatisticsVo,
			HighchartColumnVo highchartColumnVo, int orgLength) {
		int len = list.size();// 查询list长度 21
		List<List<Integer>> datas = new ArrayList<List<Integer>>();// 存放各类型对应的数量
		for (int i = 0; i < 2; i++) {
			datas.add(new ArrayList<Integer>());
		}
		List<HighchartDataColumnVo> series = new ArrayList<HighchartDataColumnVo>(); // 各类型对应的数据
		HighchartDataColumnVo column;
		List<String> categoriesList = new ArrayList<String>();
		for (int i = 0; i < len; i++) {
			String orgName = list.get(i).getOrganization().getOrgName();
			categoriesList.add(orgName);
			// if (i % 2 == 0) {
			datas.get(0).add(list.get(i).getMonthCreateCount());
			datas.get(1).add(list.get(i).getMonthCreateSign());
			// }
		}

		String[] categories = new String[categoriesList.size()];
		categoriesList.toArray(categories);
		highchartColumnVo.setCategories(categories);

		Integer[] data = new Integer[orgLength];
		for (int i = 0; i < 2; i++) {
			column = new HighchartDataColumnVo();
			datas.get(i).toArray(data);
			column.setData(getIntByInteger(data));
			if (i % 2 == 0) {
				column.setName("上报");
				column.setStack("上报");
			} else {
				column.setName("签收");
				column.setStack("签收");
			}
			series.add(column);

		}
		if (null == highchartColumnVo.getSeries()) {
			highchartColumnVo.setSeries(series);
		}
		return highchartColumnVo;
	}

	private HighchartColumnVo getHighchartColumn(List<GeneralSituation> list,
			TaskListStatisticsVo taskListStatisticsVo,
			HighchartColumnVo highchartColumnVo) {
		int len = list.size();// 查询list长度
		int typeSize = taskListStatisticsVo.getSelectTypes().split(",").length;// 选择的主类别长度
		int size = list.size() / typeSize;// 结果集根据类别分组
		List<List<Integer>> datas = new ArrayList<List<Integer>>();// 存放各类型对应的数量
		for (int i = 0; i < typeSize; i++) {
			datas.add(new ArrayList<Integer>());
		}
		List<HighchartDataColumnVo> series = new ArrayList<HighchartDataColumnVo>(); // 各类型对应的数据
		HighchartDataColumnVo column;
		List<String> categoriesList = new ArrayList<String>();
		for (int i = 0; i < len; i++) {
			String orgName = list.get(i).getOrganization().getOrgName();
			Integer sum = null;
			if (taskListStatisticsVo.getIsSegin() == 0) {
				sum = list.get(i).getMonthCreateCount();
			} else {
				sum = list.get(i).getMonthCreateSign();
			}
			if (i % typeSize == 0) {
				categoriesList.add(orgName);
				datas.get(i % typeSize).add(sum);
			} else {
				datas.get(i % typeSize).add(sum);
			}
		}
		String[] categories = new String[categoriesList.size()];
		categoriesList.toArray(categories);
		highchartColumnVo.setCategories(categories);

		Integer[] data = new Integer[size];
		for (int i = 0; i < typeSize; i++) {
			column = new HighchartDataColumnVo();
			datas.get(i).toArray(data);
			column.setData(getIntByInteger(data));
			Map<String, String> map = new HashMap<String, String>();
			if (taskListStatisticsVo.getSituationType() == 0) {
				map = TypeConstants.typeMapName;
			} else if (taskListStatisticsVo.getSituationType() == 1) {
				map = TypeConstants.typeMapByName;
			} else if (taskListStatisticsVo.getSituationType() == 3) {
				map = TypeConstants.specialMapByName;
			}
			if (null != highchartColumnVo.getSeries()) {
				column.setName(map.get(taskListStatisticsVo.getSelectTypes()
						.split(",")[i]));
				column.setStack(map.get(taskListStatisticsVo.getSelectTypes()
						.split(",")[i]));
				highchartColumnVo.getSeries().add(column);
			} else {
				column.setName(map.get(taskListStatisticsVo.getSelectTypes()
						.split(",")[i]));
				column.setStack(map.get(taskListStatisticsVo.getSelectTypes()
						.split(",")[i]));
				series.add(column);
			}

		}
		if (null == highchartColumnVo.getSeries()) {
			highchartColumnVo.setSeries(series);
		}
		return highchartColumnVo;
	}

	private int[] getIntByInteger(Integer[] integers) {

		int len = integers.length;
		int[] results = new int[len];
		for (int i = 0; i < len; i++) {
			if (null == integers[i]) {
				results[i] = 0;
			} else {
				results[i] = integers[i];
			}
		}
		return results;
	}

	/***
	 * 判断所查询的表是否存在，并且返回表名
	 */
	public String getTableName(TaskListStatisticsVo taskListStatisticsVo) {
		String tableName = null;
		if (taskListStatisticsVo.getSearchDateType() == null) {
			return tableName;
		}
		if (GeneralStituationConstants.SEARCH_BY_MONTH
				.equals(taskListStatisticsVo.getSearchDateType())) {
			tableService.createAnalyseTable(
					AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME,
					AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
					taskListStatisticsVo.getYear(),
					taskListStatisticsVo.getMonth());
			tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME
					+ "_" + taskListStatisticsVo.getYear() + "_"
					+ taskListStatisticsVo.getMonth();
		} else if (GeneralStituationConstants.SEARCH_BY_QUARTER
				.equals(taskListStatisticsVo.getSearchDateType())) {
			tableService.createAnalyseTable(
					AnalyseUtil.TASK_LIST_GENERALSITUATION_QUARTER_TABLENAME,
					AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
					taskListStatisticsVo.getYear(),
					taskListStatisticsVo.getQuarter());
			tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_QUARTER_TABLENAME
					+ "_"
					+ taskListStatisticsVo.getYear()
					+ "_"
					+ taskListStatisticsVo.getQuarter();
		} else if (GeneralStituationConstants.SEARCH_BY_YEAR
				.equals(taskListStatisticsVo.getSearchDateType())) {
			// 按年查詢暫時不做
			if (taskListStatisticsVo.getYearType() == 0) {
				// 上半年
				tableService
						.createAnalyseTableForYear(
								AnalyseUtil.TASK_LIST_GENERALSITUATION_FIR_YEAR_TABLENAME,
								AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
								taskListStatisticsVo.getYear());
				tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_FIR_YEAR_TABLENAME
						+ "_" + taskListStatisticsVo.getYear();
			} else if (taskListStatisticsVo.getYearType() == 1) {
				tableService
						.createAnalyseTableForYear(
								AnalyseUtil.TASK_LIST_GENERALSITUATION_SCO_YEAR_TABLENAME,
								AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
								taskListStatisticsVo.getYear());
				tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_SCO_YEAR_TABLENAME
						+ "_" + taskListStatisticsVo.getYear();
			} else if (taskListStatisticsVo.getYearType() == 2) {
				tableService.createAnalyseTableForYear(
						AnalyseUtil.TASK_LIST_GENERALSITUATION_YEAR_TABLENAME,
						AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
						taskListStatisticsVo.getYear());
				tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_YEAR_TABLENAME
						+ "_" + taskListStatisticsVo.getYear();
			}
		}
		return tableName;
	}

	@Override
	public List<Object[]> getTaskListPie(
			TaskListStatisticsVo taskListStatisticsVo) {
		List<Object[]> pieList = null;
		HighchartColumnVo highchartColumnVo = new HighchartColumnVo();
		highchartColumnVo.setModuleName("任务清单饼状图统计");
		if (taskListStatisticsVo == null
				|| taskListStatisticsVo.getSearchDateType() == null
				|| taskListStatisticsVo.getYear() == null
				|| taskListStatisticsVo.getIsSegin() == null
				|| taskListStatisticsVo.getOrgId() == null
				|| taskListStatisticsVo.getSituationType() == null) {
			throw new BusinessValidationException("数据查询失败，条件不正确");
		}
		String[] keyAndNameSpace = GenneralsituationUtil.getCachKeyByTypes(taskListStatisticsVo,1,1);
		String key = keyAndNameSpace[1];
		String nameSpace = keyAndNameSpace[0];
		if(key != null){
			pieList = (List<Object[]>) cacheService.get(nameSpace, key);
			if(pieList != null){
				return pieList;
			}
		}
		pieList = new ArrayList<Object[]>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", taskListStatisticsVo.getOrgId());
		map.put("orgType",
				propertyDictDubboService
						.findPropertyDictByDomainNameAndDictDisplayName(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.ADMINISTRATIVE_KEY).getId());
		String tableName = getTableName(taskListStatisticsVo);
		if (!StringUtil.isStringAvaliable(tableName)) {
			throw new BusinessValidationException("查询失败，未获得表名");
		}
		map.put("tableName", tableName);
		map.put("searchCurrent", "true");
		List<GeneralSituation> list = null;
		if (taskListStatisticsVo.getSituationType() == 0) {
			if (taskListStatisticsVo.getSelectTypes().split(",").length == 1
					&& taskListStatisticsVo.getSelectTypes().equals(
							TypeConstants.PANDECT_KEY)) {
				taskListStatisticsVo
						.setSelectTypes(TypeConstants.allBasesicType);
			}
			map.put("basesicType",
					taskListStatisticsVo.getSelectTypes().split(","));
			list = taskListStatisticsDao.getTaskListColumn(map);
		} else if (taskListStatisticsVo.getSituationType() == 1) {
			if (taskListStatisticsVo.getSelectTypes().split(",").length == 1
					&& taskListStatisticsVo.getSelectTypes().equals(
							TypeConstants.PANDECT_KEY)) {
				taskListStatisticsVo
						.setSelectTypes(TypeConstants.allFloatingType);
			}
			map.put("detailType",
					taskListStatisticsVo.getSelectTypes().split(","));
			list = taskListStatisticsDao.getFloatingColumn(map);
		} else if (taskListStatisticsVo.getSituationType() == 3) {// 特殊人群
			if (taskListStatisticsVo.getSelectTypes().split(",").length == 1
					&& taskListStatisticsVo.getSelectTypes().equals(
							TypeConstants.SPECIAL_KEY)) {
				taskListStatisticsVo
						.setSelectTypes(TypeConstants.allSpecialType);
			}
			map.put("basesicType",
					taskListStatisticsVo.getSelectTypes().split(","));
			list = taskListStatisticsDao.getTaskListColumn(map);
		}

		String[] selectTypes = taskListStatisticsVo.getSelectTypes().split(",");
		Map<String, String> typeMap = new HashMap<String, String>();
		if (taskListStatisticsVo.getSituationType() == 0) {
			typeMap = TypeConstants.typeMapName;
		} else if (taskListStatisticsVo.getSituationType() == 1) {
			typeMap = TypeConstants.typeMapByName;
		} else if (taskListStatisticsVo.getSituationType() == 3) {
			typeMap = TypeConstants.specialMapByName;
		}
		if (list != null && list.size() != 0) {
			// 计算total
			int total = calculationTotal(list,
					taskListStatisticsVo.getIsSegin());
			for (GeneralSituation generalSituation : list) {
				Object[] object = new Object[2];
				if (taskListStatisticsVo.getIsSegin() == 0) {
					if (taskListStatisticsVo.getSituationType() == 0
							|| taskListStatisticsVo.getSituationType() == 3) {
						object[0] = typeMap.get(generalSituation
								.getBasesicType())
								+ "("
								+ generalSituation.getMonthCreateCount() + ")";
					} else if (taskListStatisticsVo.getSituationType() == 1) {
						object[0] = typeMap.get(generalSituation
								.getDetailType())
								+ "("
								+ generalSituation.getMonthCreateCount() + ")";
					}

					if (total == 0) {
						object[1] = 0;
					} else {
						object[1] = Double
								.parseDouble(new java.text.DecimalFormat("#.00")
										.format((double) generalSituation
												.getMonthCreateCount()
												/ total
												* 100));
					}
				} else if (taskListStatisticsVo.getIsSegin() == 1) {
					if (taskListStatisticsVo.getSituationType() == 0
							|| taskListStatisticsVo.getSituationType() == 3) {
						object[0] = typeMap.get(generalSituation
								.getBasesicType())
								+ "("
								+ generalSituation.getMonthCreateSign() + ")";
					} else if (taskListStatisticsVo.getSituationType() == 1) {
						object[0] = typeMap.get(generalSituation
								.getDetailType())
								+ "("
								+ generalSituation.getMonthCreateSign() + ")";
					}
					if (total == 0) {
						object[1] = 0;
					} else {
						object[1] = Double
								.parseDouble(new java.text.DecimalFormat("#.00")
										.format((double) generalSituation
												.getMonthCreateSign()
												/ total
												* 100));
					}
				}
				pieList.add(object);
			}
		} else {
			for (String type : selectTypes) {
				Object[] object = new Object[2];
				object[0] = typeMap.get(type) + "(0)";
				object[1] = 0;
				pieList.add(object);
			}
		}
		if(key != null){
			cacheService.set(
					nameSpace,
					key, ModulTypes.CACHETIME, pieList);
		}
		return pieList;
	}

	private int calculationTotal(List<GeneralSituation> list, Integer isSign) {
		int total = 0;
		for (GeneralSituation generalSituation : list) {
			if (isSign == 0) {// 上报
				total += generalSituation.getMonthCreateCount();
			} else {// 签收
				total += generalSituation.getMonthCreateSign();
			}
		}
		return total;
	}

	@Override
	public HighchartColumnVo getTaskListSubTypeOfColumn(
			TaskListStatisticsVo taskListStatisticsVo) {
		HighchartColumnVo highchartColumnVo = null;
		if (taskListStatisticsVo == null
				|| taskListStatisticsVo.getSearchDateType() == null
				|| taskListStatisticsVo.getYear() == null
				|| taskListStatisticsVo.getIsSegin() == null
				|| taskListStatisticsVo.getOrgId() == null) {
			throw new BusinessValidationException("数据查询失败，条件不正确");
		}
		String[] keyAndNameSpace = GenneralsituationUtil.getCachKeyByTypes(taskListStatisticsVo,0,0);
		String key = keyAndNameSpace[1];
		String nameSpace = keyAndNameSpace[0];
		if(key != null){
			highchartColumnVo = (HighchartColumnVo)cacheService.get(nameSpace, key);
			if(highchartColumnVo != null){
				highchartColumnVo.setModuleName("任务清单柱状统计");
				return highchartColumnVo;
			}
		}
		highchartColumnVo = new HighchartColumnVo();
		highchartColumnVo.setModuleName("任务清单柱状统计");
		if (taskListStatisticsVo.getSelectTypes().split(",").length == 1
				&& taskListStatisticsVo.getSelectTypes().equals(
						TypeConstants.PANDECT_KEY)) {
			taskListStatisticsVo.setSelectTypes(GenneralsituationUtil
					.getSubTypeIds(propertyDictDubboService
							.findPropertyDictByDomainName(taskListStatisticsVo
									.getPropertyDomainName())));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", taskListStatisticsVo.getOrgId());
		map.put("orgType",
				propertyDictDubboService
						.findPropertyDictByDomainNameAndDictDisplayName(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.ADMINISTRATIVE_KEY).getId());
		String tableName = getTableName(taskListStatisticsVo);
		if (!StringUtil.isStringAvaliable(tableName)) {
			throw new BusinessValidationException("查询失败，未获得表名");
		}
		map.put("tableName", tableName);
		List<GeneralSituation> list = null;
		map.put("subType", taskListStatisticsVo.getSelectTypes().split(","));
		map.put("searchCurrent", "false");
		list = taskListStatisticsDao.getTaskListForSubType(map);
		if (list == null || list.size() == 0) {
			List<Organization> orgList = organizationDubboService
					.findAdminOrgsByParentId(taskListStatisticsVo.getOrgId());
			if (orgList == null || orgList.size() == 0) {
				return highchartColumnVo;
			}
			list = new ArrayList<GeneralSituation>();
			for (Organization organization : orgList) {
				for (String subType : taskListStatisticsVo.getSelectTypes()
						.split(",")) {
					if(StringUtil.isStringAvaliable(subType)){
						GeneralSituation generalSituation = new GeneralSituation(
								organization, Long.parseLong(subType));
						list.add(generalSituation);
					}
				}
			}
		}
		if (taskListStatisticsVo.getIsSegin() == 2
				&& taskListStatisticsVo.getSelectTypes().split(",").length == 1) {
			// 签收和上报统计
			List<Organization> orgList = organizationDubboService
					.findAdminOrgsByParentId(taskListStatisticsVo.getOrgId());
			if (orgList == null || orgList.size() == 0) {
				return highchartColumnVo;
			}
			highchartColumnVo = getHighchartColumnForSign(list, taskListStatisticsVo,
					highchartColumnVo, orgList.size());
			if(key != null){
				cacheService.set(
						nameSpace,
						key, ModulTypes.CACHETIME, highchartColumnVo);
			}
			return highchartColumnVo;
		}
		highchartColumnVo = getCommonColumn(list, taskListStatisticsVo, highchartColumnVo);
		if(key != null){
			cacheService.set(
					nameSpace,
					key, ModulTypes.CACHETIME, highchartColumnVo);
		}
		return highchartColumnVo;
	}

	private HighchartColumnVo getCommonColumn(List<GeneralSituation> list,
			TaskListStatisticsVo taskListStatisticsVo,
			HighchartColumnVo highchartColumnVo) {
		int len = list.size();// 查询list长度
		int typeSize = taskListStatisticsVo.getSelectTypes().split(",").length;// 选择的主类别长度
		int size = list.size() / typeSize;// 结果集根据类别分组
		List<List<Integer>> datas = new ArrayList<List<Integer>>();// 存放各类型对应的数量
		for (int i = 0; i < typeSize; i++) {
			datas.add(new ArrayList<Integer>());
		}
		List<HighchartDataColumnVo> series = new ArrayList<HighchartDataColumnVo>(); // 各类型对应的数据
		HighchartDataColumnVo column;
		List<String> categoriesList = new ArrayList<String>();
		for (int i = 0; i < len; i++) {
			String orgName = list.get(i).getOrganization().getOrgName();
			Integer sum = null;
			if (taskListStatisticsVo.getIsSegin() == 0) {
				sum = list.get(i).getMonthCreateCount();
			} else {
				sum = list.get(i).getMonthCreateSign();
			}
			if (i % typeSize == 0) {
				categoriesList.add(orgName);
				datas.get(i % typeSize).add(sum);
			} else {
				datas.get(i % typeSize).add(sum);
			}
		}
		String[] categories = new String[categoriesList.size()];
		categoriesList.toArray(categories);
		highchartColumnVo.setCategories(categories);

		Integer[] data = new Integer[size];
		for (int i = 0; i < typeSize; i++) {
			column = new HighchartDataColumnVo();
			datas.get(i).toArray(data);
			column.setData(getIntByInteger(data));
			if (null != highchartColumnVo.getSeries()) {
				column.setName(list.get(i).getTypeName());
				column.setStack(list.get(i).getTypeName());
				highchartColumnVo.getSeries().add(column);
			} else {
				column.setName(list.get(i).getTypeName());
				column.setStack(list.get(i).getTypeName());
				series.add(column);
			}

		}
		if (null == highchartColumnVo.getSeries()) {
			highchartColumnVo.setSeries(series);
		}
		return highchartColumnVo;
	}

	@Override
	public List<Object[]> getTaskListSubTypeOfPie(
			TaskListStatisticsVo taskListStatisticsVo) {
		if (taskListStatisticsVo == null
				|| taskListStatisticsVo.getSearchDateType() == null
				|| taskListStatisticsVo.getYear() == null
				|| taskListStatisticsVo.getIsSegin() == null
				|| taskListStatisticsVo.getOrgId() == null) {
			throw new BusinessValidationException("数据查询失败，条件不正确");
		}
		List<Object[]> pieList = null;
		String[] keyAndNameSpace = GenneralsituationUtil.getCachKeyByTypes(taskListStatisticsVo,1,0);
		String key = keyAndNameSpace[1];
		String nameSpace = keyAndNameSpace[0];
		if(key != null){
			pieList = (List<Object[]>) cacheService.get(nameSpace, key);
			if(pieList != null){
				return pieList;
			}
		}
		pieList = new ArrayList<Object[]>();
		if (taskListStatisticsVo.getSelectTypes().split(",").length == 1
				&& taskListStatisticsVo.getSelectTypes().equals(
						TypeConstants.PANDECT_KEY)) {
			taskListStatisticsVo.setSelectTypes(GenneralsituationUtil
					.getSubTypeIds(propertyDictDubboService
							.findPropertyDictByDomainName(taskListStatisticsVo
									.getPropertyDomainName())));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", taskListStatisticsVo.getOrgId());
		map.put("searchCurrent", "true");
		map.put("orgType",
				propertyDictDubboService
						.findPropertyDictByDomainNameAndDictDisplayName(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.ADMINISTRATIVE_KEY).getId());
		String tableName = getTableName(taskListStatisticsVo);
		if (!StringUtil.isStringAvaliable(tableName)) {
			throw new BusinessValidationException("查询失败，未获得表名");
		}
		map.put("tableName", tableName);
		List<GeneralSituation> list = null;
		map.put("subType", taskListStatisticsVo.getSelectTypes().split(","));
		list = taskListStatisticsDao.getTaskListForSubType(map);
		if (list == null || list.size() == 0) {
			List<Organization> orgList = organizationDubboService
					.findAdminOrgsByParentId(taskListStatisticsVo.getOrgId());
			if (orgList == null || orgList.size() == 0) {
				return pieList;
			}
			list = new ArrayList<GeneralSituation>();
			for (Organization organization : orgList) {
				for (String subType : taskListStatisticsVo.getSelectTypes()
						.split(",")) {
					GeneralSituation generalSituation = new GeneralSituation(
							organization, Long.parseLong(subType));
					list.add(generalSituation);
				}
			}

		}
		pieList = getSubTypePieList(list, taskListStatisticsVo, pieList);
		if(key != null){
			cacheService.set(
					nameSpace,
					key, ModulTypes.CACHETIME, pieList);
		}
		return pieList;
	}

	private List<Object[]> getSubTypePieList(List<GeneralSituation> list,
			TaskListStatisticsVo taskListStatisticsVo, List<Object[]> pieList) {
		String[] selectTypes = taskListStatisticsVo.getSelectTypes().split(",");
		if (list != null && list.size() != 0) {
			// 计算total
			int total = calculationTotal(list,
					taskListStatisticsVo.getIsSegin());
			for (GeneralSituation generalSituation : list) {
				Object[] object = new Object[2];
				if (taskListStatisticsVo.getIsSegin() == 0) {
					object[0] = generalSituation.getTypeName() + "("
							+ generalSituation.getMonthCreateCount() + ")";

					if (total == 0) {
						object[1] = 0;
					} else {
						object[1] = Double
								.parseDouble(new java.text.DecimalFormat("#.00")
										.format((double) generalSituation
												.getMonthCreateCount()
												/ total
												* 100));
					}
				} else if (taskListStatisticsVo.getIsSegin() == 1) {
					object[0] = generalSituation.getTypeName() + "("
							+ generalSituation.getMonthCreateSign() + ")";
					if (total == 0) {
						object[1] = 0;
					} else {
						object[1] = Double
								.parseDouble(new java.text.DecimalFormat("#.00")
										.format((double) generalSituation
												.getMonthCreateSign()
												/ total
												* 100));
					}
				}
				pieList.add(object);
			}
		} else {
			for (String type : selectTypes) {
				Object[] object = new Object[2];
				PropertyDict dict = propertyDictDubboService
						.getPropertyDictById(Long.parseLong(type));
				object[0] = dict.getDisplayName() + "(0)";
				object[1] = 0;
				pieList.add(object);
			}
		}
		return pieList;
	}

	@Override
	public HighchartColumnVo getTaskListTrend(
			TaskListStatisticsVo taskListStatisticsVo) {
		HighchartColumnVo highchartColumnVo = null;
		if (taskListStatisticsVo == null
				|| taskListStatisticsVo.getSearchDateType() == null
				|| taskListStatisticsVo.getYear() == null
				|| taskListStatisticsVo.getMonth() == null
				|| taskListStatisticsVo.getIsSegin() == null
				|| taskListStatisticsVo.getOrgId() == null
				|| taskListStatisticsVo.getSituationType() == null) {
			throw new BusinessValidationException("数据查询失败，条件不正确");
		}
		String[] keyAndNameSpace = GenneralsituationUtil.getCachKeyByTypes(taskListStatisticsVo,2,taskListStatisticsVo.getSituationType() == 2 ? 0 : 1);
		String key = keyAndNameSpace[1];
		String nameSpace = keyAndNameSpace[0];
		if(key != null){
			highchartColumnVo = (HighchartColumnVo) cacheService.get(nameSpace, key);
			if(highchartColumnVo != null){
				highchartColumnVo.setModuleName("任务清单线性统计");
				return highchartColumnVo;
			}
		}
		highchartColumnVo = new HighchartColumnVo();
		highchartColumnVo.setModuleName("任务清单线性统计");
		Integer year = taskListStatisticsVo.getYear();
		Integer month = taskListStatisticsVo.getMonth();
		String[] time = new String[12];
		time = GenneralsituationUtil.getTime(year, month, time);
		// 验证表名是否存在
		tableIsCreate(time);
		highchartColumnVo.setCategories(time);
		List<HighchartDataColumnVo> highchartDataColumnVos = dealHighchartDataColumn(
				time, taskListStatisticsVo);
		highchartColumnVo.setSeries(highchartDataColumnVos);
		if(key != null){
			cacheService.set(nameSpace,
					key, ModulTypes.CACHETIME, highchartColumnVo);
		}
		return highchartColumnVo;
	}

	private List<HighchartDataColumnVo> dealHighchartDataColumn(String[] time,
			TaskListStatisticsVo taskListStatisticsVo) {
		List<HighchartDataColumnVo> list = new ArrayList<HighchartDataColumnVo>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (taskListStatisticsVo.getSelectTypes().equals(
				TypeConstants.PANDECT_KEY)) {
			taskListStatisticsVo.setSelectTypes(TypeConstants.allBasesicType);
		} else if (taskListStatisticsVo.getSelectTypes().equals(
				TypeConstants.FLOATING_POPULATION_KEY)) {
			if(taskListStatisticsVo.getSituationType()!=0){
				taskListStatisticsVo.setSelectTypes(TypeConstants.allFloatingType);
			}
		} else if (taskListStatisticsVo.getSelectTypes().equals(
				TypeConstants.SPECIAL_KEY)) {// 特殊人群
			taskListStatisticsVo.setSelectTypes(TypeConstants.allSpecialType);
		} else if (taskListStatisticsVo.getSelectTypes().equals(
				TypeConstants.WORKINGSITUATION_KEY)) {// 民警带领下宣传工作
			if(taskListStatisticsVo.getSituationType()!=1){
				// 根据数据字典获取字典ID
				List<PropertyDict> dictList = propertyDictDubboService
						.findPropertyDictByDomainName(PropertyTypes.WORKING_CONTENT_TYPE);
				String dictStr = GenneralsituationUtil.getSubTypeIds(dictList);
				taskListStatisticsVo.setSelectTypes(dictStr);
			}
		} else if (taskListStatisticsVo.getSelectTypes().equals(
				TypeConstants.EXCEPTIONALSITUATIONRECORD_KEY)) {// 异常情况报告
			if(taskListStatisticsVo.getSituationType()!=1){
				// 根据数据字典获取字典ID
				List<PropertyDict> dictList = propertyDictDubboService
						.findPropertyDictByDomainName(PropertyTypes.EXCEPTION_SITUATION_TYPE);
				String dictStr = GenneralsituationUtil.getSubTypeIds(dictList);
				taskListStatisticsVo.setSelectTypes(dictStr);
			}
		} else if (taskListStatisticsVo.getSelectTypes().equals(
				TypeConstants.HIDDENDANGGER_KEY)) {// 隐患排查
			// 根据数据字典获取字典ID
			if(taskListStatisticsVo.getSituationType()!=0){
				List<PropertyDict> dictList = propertyDictDubboService
						.findPropertyDictByDomainName(PropertyTypes.DANGER_EXCEPTION_TYPE);
				String dictStr = GenneralsituationUtil.getSubTypeIds(dictList);
				taskListStatisticsVo.setSelectTypes(dictStr);
			}
		}else if(taskListStatisticsVo.getSelectTypes().equals(
				TypeConstants.PROPAGANDAANDVERIFICATION_KEY)){
			taskListStatisticsVo.setSelectTypes(TypeConstants.PROPAGANDAANDVERIFICATION_KEY);
		}
		map.put("orgId", taskListStatisticsVo.getOrgId());
		if (taskListStatisticsVo.getIsSegin() == 0) {
			map.put("isSign", "false");
		} else {
			map.put("isSign", "true");
		}
		HighchartDataColumnVo column;
		for (String type : taskListStatisticsVo.getSelectTypes().split(",")) {
			String typeName = null;
			if (taskListStatisticsVo.getSituationType() == 0) {
				map.put("basesicType", type);
				typeName = TypeConstants.typeMapName.get(type);
			} else if (taskListStatisticsVo.getSituationType() == 1) {
				map.put("detailType", type);
				typeName = TypeConstants.typeMapByName.get(type);
			} else if (taskListStatisticsVo.getSituationType() == 3) {
				map.put("basesicType", type);
				typeName = TypeConstants.specialMapByName.get(type);
			} else {
				map.put("subType", type);
				typeName = propertyDictDubboService.getPropertyDictById(
						Long.parseLong(type)).getDisplayName();
			}
			column = new HighchartDataColumnVo();
			column.setData(new int[time.length]);
			column.setName(typeName);
			for (int i = 0; i < time.length; i++) {
				String[] data = time[i].split("-");
				Integer year = Integer.valueOf(data[0]);
				Integer month = Integer.valueOf(data[1]);
				String tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME
						+ "_" + year + "_" + month;
				map.put("tableName", tableName);
				Integer count = taskListStatisticsDao.getTaskListForTrend(map);
				column.getData()[i] = count == null ? 0 : count;
			}
			list.add(column);
		}
		return list;
	}

	/***
	 * 验证表是否创建
	 */
	private void tableIsCreate(String[] times) {
		for (int i = 0; i < times.length; i++) {
			String[] data = times[i].split("-");
			int year = Integer.valueOf(data[0]);
			int month = Integer.valueOf(data[1]);
			tableService.createAnalyseTable(
					AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME,
					AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL, year, month);
		}
	}

	@Override
	public TaskListMapVo getTaskListForMap(
			TaskListStatisticsVo taskListStatisticsVo) {
		if (taskListStatisticsVo == null){
			throw new BusinessValidationException("数据查询失败，条件不正确");
		}
		int  year= 0;
		int  month = 0;
		if(taskListStatisticsVo.getYear()!=null ){
			year = taskListStatisticsVo.getYear();
		}else{
			year = CalendarUtil.getYear(CalendarUtil.getLastMonthStart(CalendarUtil.getYear(CalendarUtil.now()), CalendarUtil.getMonth(CalendarUtil.now())));
			taskListStatisticsVo.setYear(year);
		}
		if(taskListStatisticsVo.getMonth()!=null ){
			month = taskListStatisticsVo.getMonth();
		}else{
			month = CalendarUtil.getMonth(CalendarUtil.getLastMonthStart(CalendarUtil.getYear(CalendarUtil.now()), CalendarUtil.getMonth(CalendarUtil.now())));
			taskListStatisticsVo.setMonth(month);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		
		Long orgId = getOrganizationIdByNames(taskListStatisticsVo.getOrgName());
		if(orgId  == null){
			throw new BusinessValidationException("数据查询失败，组织结构不存在！");
		}
		taskListStatisticsVo.setOrgId(orgId);
		TaskListMapVo mapVo = null;
		String[] keyAndNameSpace = GenneralsituationUtil.getCachKeyByTypes(taskListStatisticsVo,5,2);
		String key = keyAndNameSpace[1];
		String nameSpace = keyAndNameSpace[0];
		if(key != null){
			mapVo = (TaskListMapVo) cacheService.get(nameSpace, key);
			if(mapVo != null){
				return mapVo;
			}
		}
		mapVo = new TaskListMapVo();
		map.put("orgId", orgId);
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		mapVo.setOrganization(organization);

		tableService
				.createAnalyseTable(
						AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME,
						AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
						year,
						month);
		String tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME
				+ "_" + year + "_" + month;
		map.put("tableName", tableName);
		map.put("orgType", propertyDictDubboService
				.findPropertyDictByDomainNameAndDictDisplayName(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_KEY).getId());
		map.put("isSign", taskListStatisticsVo.getIsSegin());
		if (!TypeConstants.PANDECT_KEY.equals(taskListStatisticsVo
				.getBasesicType())) {
			if(taskListStatisticsVo.getBasesicType().equals("special")){
				taskListStatisticsVo.setBasesicType(TypeConstants.allSpecialType);
			}
			map.put("basesicType", taskListStatisticsVo.getBasesicType().split(","));
		}else{
			taskListStatisticsVo
			.setSelectTypes(TypeConstants.allBasesicType);
			map.put("basesicType",taskListStatisticsVo.getSelectTypes().split(","));
		}
		if (StringUtil.isStringAvaliable(taskListStatisticsVo.getDetailType())) {
			map.put("detailType", taskListStatisticsVo.getDetailType());
		}
		if (StringUtil.isStringAvaliable(taskListStatisticsVo.getSubType())) {
			map.put("subType", taskListStatisticsVo.getSubType());
		}
		int currentMonthCreateSign = taskListStatisticsDao
				.getMonthCreateSignByCondition(map);
		mapVo.setMonthCreateSign(currentMonthCreateSign);//10
		// 获取同比数据
		// 根据传入条件获取上个月的月份以及年份
		tableService.createAnalyseTable(
				AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME,
				AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL, year - 1, month);
		tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME
				+ "_" + (year - 1) + "_" + month;
		map.put("tableName", tableName);
		int onMonthCreateSign = taskListStatisticsDao
				.getMonthCreateSignByCondition(map);
		mapVo.setOnMonthCreateSign(currentMonthCreateSign - onMonthCreateSign);// 同比差值
		// 获取环比
		int lastMonthYear = CalendarUtil.getYear(CalendarUtil.getLastMonthEnd(
				year, month));
		int lastMonth = CalendarUtil.getMonth(CalendarUtil.getLastMonthEnd(
				year, month));
		tableService.createAnalyseTable(
				AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME,
				AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL, lastMonthYear,
				lastMonth);
		tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME
				+ "_" + lastMonthYear + "_" + lastMonth;
		map.put("tableName", tableName);
		int momMonthCreateSign = taskListStatisticsDao
				.getMonthCreateSignByCondition(map);
		mapVo.setMomMonthCreateSign(currentMonthCreateSign - momMonthCreateSign);// 环比差值
		// 计算同比值还环比值
		mapVo = calculationMonData(mapVo,momMonthCreateSign);
		mapVo = calculationOnData(mapVo,onMonthCreateSign);
		if(key != null){
			cacheService.set(
					nameSpace,
					key, ModulTypes.CACHETIME, mapVo);
		}
		return mapVo;
	}

	/***
	 * 计算环比
	 * @param vo
	 * @param onMonthCreateSign
	 * @param momMonthCreateSign
	 * @return
	 */
	private TaskListMapVo calculationMonData(TaskListMapVo vo, int momMonthCreateSign) {
		if(vo.getMomMonthCreateSign() == 0 && momMonthCreateSign == 0){//如果当前月为0  上月数据为0 
			vo.setMomProportion("0%");
		}else{
			if (momMonthCreateSign == 0) {
				vo.setMomProportion("100%");
			} else {
				double momData = Double.parseDouble(new java.text.DecimalFormat(
						"#.00").format((double) vo.getMomMonthCreateSign()
						/ momMonthCreateSign * 100));
				vo.setMomProportion(momData + "%");
			}
		}
		return vo;
	}
	/**
	 * 计算环比
	 */
	private TaskListMapVo calculationOnData(TaskListMapVo vo,int onMonthCreateSign) {
		if(vo.getOnMonthCreateSign() == 0 && onMonthCreateSign == 0){//如果当前月为0 去年本月数据为0 
			vo.setOnProportion("0%");
		}else{
			if (onMonthCreateSign == 0) {
				vo.setOnProportion("100%");
			} else {
				double onData = Double.parseDouble(new java.text.DecimalFormat(
						"#.00").format((double) vo.getOnMonthCreateSign()
						/ onMonthCreateSign * 100));
				vo.setOnProportion(onData + "%");
			}
		}
		return vo;
	}
	@Override
	public List<TaskListMapVo> getMaxCreatSignOrgByType(
			TaskListStatisticsVo taskListStatisticsVo) {
		if (taskListStatisticsVo == null
//				|| taskListStatisticsVo.getYear() == null
//				|| taskListStatisticsVo.getMonth() == null
				|| taskListStatisticsVo.getIsSegin() == null) {
			throw new BusinessValidationException("数据查询失败，条件不正确");
		}
		List<TaskListMapVo> taskListMapVoList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		Long orgId = getOrganizationIdByNames(taskListStatisticsVo.getOrgName());
		if(orgId  == null){
			throw new BusinessValidationException("数据查询失败，组织结构不存在！");
		}
		taskListStatisticsVo.setOrgId(orgId);
		map.put("orgId", orgId);
		int  year= 0;
		int  month = 0;
		if(taskListStatisticsVo.getYear()==null ){
			year = CalendarUtil.getYear(CalendarUtil.getLastMonthStart(CalendarUtil.getYear(CalendarUtil.now()), CalendarUtil.getMonth(CalendarUtil.now())));
			taskListStatisticsVo.setYear(year);
		}else{
			year = taskListStatisticsVo.getYear();
		}
		if(taskListStatisticsVo.getMonth()==null ){
			month = CalendarUtil.getMonth(CalendarUtil.getLastMonthStart(CalendarUtil.getYear(CalendarUtil.now()), CalendarUtil.getMonth(CalendarUtil.now())));
			taskListStatisticsVo.setMonth(month);
		}else{
			month = taskListStatisticsVo.getMonth();
		}
		String[] keyAndNameSpace = GenneralsituationUtil.getCachKeyByTypes(taskListStatisticsVo,3,2);
		String key = keyAndNameSpace[1];
		String nameSpace = keyAndNameSpace[0];
		if(key != null){
			taskListMapVoList = (List<TaskListMapVo>) cacheService.get(nameSpace, key);
			if(taskListMapVoList != null){
				return taskListMapVoList;
			}
		}
		tableService
				.createAnalyseTable(
						AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME,
						AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL,
						taskListStatisticsVo.getYear(),
						taskListStatisticsVo.getMonth());
		String tableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME
				+ "_" + year + "_" + month;
		map.put("tableName", tableName);
		map.put("orgType", propertyDictDubboService
				.findPropertyDictByDomainNameAndDictDisplayName(
						OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_KEY).getId());
		if (taskListStatisticsVo.getBasesicType().equals(
				TypeConstants.PANDECT_KEY)) {
				taskListStatisticsVo
						.setSelectTypes(TypeConstants.allBasesicType);
				map.put("basesicType",
						taskListStatisticsVo.getSelectTypes().split(","));
		}else{
				if(taskListStatisticsVo.getBasesicType().equals("special")){
					taskListStatisticsVo.setBasesicType(TypeConstants.allSpecialType);
				}
				map.put("basesicType", taskListStatisticsVo.getBasesicType().split(","));
				if(StringUtil.isStringAvaliable(taskListStatisticsVo.getDetailType())){
					map.put("detailType", taskListStatisticsVo.getDetailType());
				}
				if(StringUtil.isStringAvaliable(taskListStatisticsVo.getSubType())){
					map.put("subType", taskListStatisticsVo.getSubType());
				}
		}
		if(taskListStatisticsVo.getIsSegin() == 0){//上报
			map.put("isSegin", "monthCreateCount");
		}else if(taskListStatisticsVo.getIsSegin() == 1){//签收
			map.put("isSegin", "monthCreateSign");
		}
		map.put("isSign", taskListStatisticsVo.getIsSegin());
		if(taskListStatisticsVo.getMapTypeByOrg() == 0){
			map.put("mapTypeByOrg", "0");
		}else{
			map.put("mapTypeByOrg", "1");
		}
		taskListMapVoList = taskListStatisticsDao.getMaxCreatSignOrgByType(map);
		// 获取同比数据
		// 根据传入条件获取上个月的月份以及年份
		tableService.createAnalyseTable(
				AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME,
				AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL, year - 1, month);
		String onMonthTableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME
				+ "_" + (year - 1) + "_" + month;
		
		// 获取环比
		int lastMonthYear = CalendarUtil.getYear(CalendarUtil.getLastMonthEnd(
				year, month));
		int lastMonth = CalendarUtil.getMonth(CalendarUtil.getLastMonthEnd(
				year, month));
		tableService.createAnalyseTable(
				AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME,
				AnalyseUtil.TASK_LIST_GENERALSITUATION_SQL, lastMonthYear,
				lastMonth);
		String momMonthTableName = AnalyseUtil.TASK_LIST_GENERALSITUATION_MONTH_TABLENAME
				+ "_" + lastMonthYear + "_" + lastMonth;
//		int onMonthCreateSign = 0;
//		int momMonthCreateSign = 0;
//		int currentMonthCreateSign = 0;
		map.put("tableName", onMonthTableName);
		List<TaskListMapVo> onTaskList = new ArrayList<TaskListMapVo>();
		onTaskList = taskListStatisticsDao.getMaxCreatSignOrgByType(map);//同比List
		map.put("tableName", momMonthTableName);
		List<TaskListMapVo> momTaskList = new ArrayList<TaskListMapVo>();
		momTaskList = taskListStatisticsDao.getMaxCreatSignOrgByType(map);//环比List
		for(TaskListMapVo taskList: taskListMapVoList){
			for(TaskListMapVo onTask: onTaskList){
				if(taskList.getOrganization().getId().equals(onTask.getOrganization().getId())){
					taskList.setOnMonthCreateSign(taskList.getMonthCreateSign() - onTask.getMonthCreateSign());
					taskList = calculationOnData(taskList,  onTask.getMonthCreateSign());
				}
			}
			for(TaskListMapVo momTask: momTaskList){
				if(taskList.getOrganization().getId().equals(momTask.getOrganization().getId())){
					taskList.setMomMonthCreateSign(taskList.getMonthCreateSign() - momTask.getMonthCreateSign());
					taskList = calculationMonData(taskList,  momTask.getMonthCreateSign());
				}
			}
			
			//获得每个区域下的同比环比数据
//			if(taskList.getOrganization().getOrgName() != null){
//				Long orgById = getOrganizationIdByNames(taskList.getOrganization().getOrgName());
//				if(orgById != null){
//					map.put("orgId", orgById);
//				}else{
//					throw new BusinessValidationException("数据查询失败，组织机构不存在！");
//				}
//				map.put("tableName", tableName);
//				currentMonthCreateSign = taskListStatisticsDao
//				.getMonthCreateSignByCondition(map);
//				taskList.setMonthCreateSign(currentMonthCreateSign);
//				//获得同比数据和设置条件值
//				map.put("tableName", onMonthTableName);
//				onMonthCreateSign = taskListStatisticsDao
//						.getMonthCreateSignByCondition(map);
//				taskList.setOnMonthCreateSign(currentMonthCreateSign - onMonthCreateSign);// 同比差值
//				//获得环比数据和设置条件值
//				map.put("tableName", momMonthTableName);
//				momMonthCreateSign = taskListStatisticsDao
//						.getMonthCreateSignByCondition(map);
//				taskList.setMomMonthCreateSign(currentMonthCreateSign - momMonthCreateSign);// 环比差值
//				taskList = calculationOnMonData(taskList,onMonthCreateSign,momMonthCreateSign);
//			}
		}
		if(key != null){
			cacheService.set(
					nameSpace,
					key, ModulTypes.CACHETIME, taskListMapVoList);
		}
		return taskListMapVoList;
	}

	@Override
	public List<Object[]> getTaskListPieForMap(
			TaskListStatisticsVo taskListStatisticsVo) {
		int  year= 0;
		int  month = 0;
		if(taskListStatisticsVo.getYear()==null ){
			year = CalendarUtil.getYear(CalendarUtil.getLastMonthStart(CalendarUtil.getYear(CalendarUtil.now()), CalendarUtil.getMonth(CalendarUtil.now())));
			taskListStatisticsVo.setYear(year);
		}
		if(taskListStatisticsVo.getMonth()==null ){
			month = CalendarUtil.getMonth(CalendarUtil.getLastMonthStart(CalendarUtil.getYear(CalendarUtil.now()), CalendarUtil.getMonth(CalendarUtil.now())));
			taskListStatisticsVo.setMonth(month);
		}
		List<Object[]> listPie = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		Long orgId = getOrganizationIdByNames(taskListStatisticsVo.getOrgName());
		if(orgId  == null){
			throw new BusinessValidationException("数据查询失败，组织机构不存在！");
		}
		taskListStatisticsVo.setOrgId(orgId);
		map.put("orgId", orgId);
		map.put("orgType",
				propertyDictDubboService
						.findPropertyDictByDomainNameAndDictDisplayName(
								OrganizationType.ORG_TYPE_KEY,
								OrganizationType.ADMINISTRATIVE_KEY).getId());
		String tableName = getTableName(taskListStatisticsVo);
		if (!StringUtil.isStringAvaliable(tableName)) {
			throw new BusinessValidationException("查询失败，未获得表名");
		}
		String[] keyAndNameSpace = GenneralsituationUtil.getCachKeyByTypes(taskListStatisticsVo,4,2);
		String key = keyAndNameSpace[1];
		String nameSpace = keyAndNameSpace[0];
		if(key != null){
			listPie = (List<Object[]>) cacheService.get(nameSpace, key);
			if(listPie != null){
				return listPie;
			}
		}
		listPie = new ArrayList<Object[]>();
		map.put("tableName", tableName);
		map.put("searchCurrent", "false");
		List<GeneralSituation> list = null;
		boolean flg= false;
		if (taskListStatisticsVo.getBasesicType().equals(
							TypeConstants.PANDECT_KEY)) {
				flg = true;
				taskListStatisticsVo
						.setSelectTypes(TypeConstants.allBasesicType);
				map.put("basesicType",
						taskListStatisticsVo.getSelectTypes().split(","));
		}else if(taskListStatisticsVo.getBasesicType().equals(
				TypeConstants.SPECIAL_KEY)){
			taskListStatisticsVo.setSelectTypes(TypeConstants.allSpecialType);
			map.put("basesicType",taskListStatisticsVo.getSelectTypes().split(","));
		}else{
				if(taskListStatisticsVo.getBasesicType()!=null){
					map.put("basesicType",
							taskListStatisticsVo.getBasesicType().split(","));
				}
				if(StringUtil.isStringAvaliable(taskListStatisticsVo.getDetailType())){
					map.put("detailType",
							taskListStatisticsVo.getDetailType());
				}
				if(StringUtil.isStringAvaliable(taskListStatisticsVo.getSubType())){
					map.put("subType",
							taskListStatisticsVo.getSubType());
				}
			}
		int isSign =taskListStatisticsVo.getIsSegin();
		if(!flg){
			map.put("searchCurrent", "true");
			if(taskListStatisticsVo.getBasesicType()!=null &&!taskListStatisticsVo.getBasesicType().equals(TypeConstants.HIDDENDANGGER_KEY)  && !StringUtil.isStringAvaliable(taskListStatisticsVo.getDetailType()) && !StringUtil.isStringAvaliable(taskListStatisticsVo.getSubType())){
				boolean isFloating = false;
				if(taskListStatisticsVo.getBasesicType().equals(TypeConstants.FLOATING_POPULATION_KEY)){
					isFloating=true;
					list = taskListStatisticsDao.getTaskListColumnForDetailTypeMap(map);
				}else{
					list = taskListStatisticsDao.getTaskListColumnForBasesicTypeMap(map);
				}
				int total = calculationTotal(list,isSign);
				Map<String,String> mapType = null;
				if(taskListStatisticsVo.getBasesicType().equals(TypeConstants.FLOATING_POPULATION_KEY)){
					mapType= TypeConstants.typeMapByName;
				}else{
					mapType= TypeConstants.specialMapByName;
				}
				for (GeneralSituation g : list) {
					Object[] obj = new Object[2];
					int num =0;
					if(isSign==0){
						num=g.getMonthCreateCount();
					}else{
						num=g.getMonthCreateSign();
					}
					String pieName = "";
					if(isFloating){
						pieName = mapType.get(g.getDetailType());
					}else{
						pieName = mapType.get(g.getBasesicType());
					}
					if(!StringUtil.isStringAvaliable(pieName)){
						pieName = "无数据";
					}
					obj[0] = pieName+ "("+ num + ")";
					if (total == 0) {
						obj[1] = 0;
					} else {
						obj[1] = Double.parseDouble(new java.text.DecimalFormat("#.00")
								.format((double) num / total * 100));
					}
					listPie.add(obj);
				}
			}else if(taskListStatisticsVo.getBasesicType()!=null && StringUtil.isStringAvaliable(taskListStatisticsVo.getDetailType()) && !StringUtil.isStringAvaliable(taskListStatisticsVo.getSubType())){
				if(taskListStatisticsVo.getDetailType().equals(TypeConstants.PROPAGANDAANDVERIFICATION_KEY)){
					list = taskListStatisticsDao.getTaskListColumnForDetailTypeMap(map);
					int total = calculationTotal(list,isSign);
					for (GeneralSituation g : list) {
						Object[] obj = new Object[2];
						int num =0;
						if(isSign==0){
							num=g.getMonthCreateCount();
						}else{
							num=g.getMonthCreateSign();
						}
						obj[0] = "宣传核查"+ "("
								+ num + ")";
						if (total == 0) {
							obj[1] = 0;
						} else {
							obj[1] = Double.parseDouble(new java.text.DecimalFormat("#.00")
									.format((double) num / total * 100));
						}
						listPie.add(obj);
					}
				}else{
					PropertyDomain domain = null;
					if(taskListStatisticsVo.getDetailType().equals(TypeConstants.WORKINGSITUATION_KEY)){
						domain = propertyDomainDubboService.getPropertyDomainByDomainName(PropertyTypes.WORKING_CONTENT_TYPE);
					}else if(taskListStatisticsVo.getDetailType().equals(TypeConstants.EXCEPTIONALSITUATIONRECORD_KEY)){
						domain = propertyDomainDubboService.getPropertyDomainByDomainName(PropertyTypes.EXCEPTION_SITUATION_TYPE);
					}else if(taskListStatisticsVo.getDetailType().equals(TypeConstants.HIDDENDANGGER_KEY)){
						domain = propertyDomainDubboService.getPropertyDomainByDomainName(PropertyTypes.DANGER_EXCEPTION_TYPE);
					}
					if(domain==null){
						throw new BusinessValidationException("查询失败");
					}
					map.put("propertyDomainId",domain.getId());
					list = taskListStatisticsDao.getTaskListColumnForSubTypeTypeMap(map);
					int total = calculationTotal(list,isSign);
					for (GeneralSituation g : list) {
						Object[] obj = new Object[2];
						int num =0;
						if(isSign==0){
							num=g.getMonthCreateCount();
						}else{
							num=g.getMonthCreateSign();
						}
						obj[0] = g.getTypeName()+ "("+ num + ")";
						if (total == 0) {
							obj[1] = 0;
						} else {
							obj[1] = Double.parseDouble(new java.text.DecimalFormat("#.00")
										.format((double) num / total * 100));
						}
						listPie.add(obj);
					}
				}
			}else{
				    list = taskListStatisticsDao.getTaskListColumnForSubTypesTypeMap(map);
				    int total = calculationTotal(list,isSign);
				    for (GeneralSituation g : list) {
						Object[] obj = new Object[2];
						int num =0;
						if(isSign==0){
							num=g.getMonthCreateCount();
						}else{
							num=g.getMonthCreateSign();
						}
						PropertyDict dict = propertyDictDubboService.getPropertyDictById(g.getSubType());
						if(dict==null){
								continue;
						}
						obj[0] = dict.getDisplayName()+ "("+ num + ")";
						if (total == 0) {
							obj[1] = 0;
						} else {
							obj[1] = Double.parseDouble(new java.text.DecimalFormat("#.00")
									.format((double) num / total * 100));
						}
						listPie.add(obj);
					}
			}
		}else{
			list = taskListStatisticsDao.getTaskListColumnForMap(map);
			int total = calculationTotal(list,isSign);
			for (GeneralSituation g : list) {
				Object[] obj = new Object[2];
				int num =0;
				if(isSign==0){
					num=g.getMonthCreateCount();
				}else{
					num=g.getMonthCreateSign();
				}
				obj[0] = g.getOrganization().getOrgName() + "("
						+ num + ")";
				if (total == 0) {
					obj[1] = 0;
				} else {
					obj[1] = Double.parseDouble(new java.text.DecimalFormat("#.00")
							.format((double) num / total * 100));
				}
				listPie.add(obj);
			}
		}
		if(key != null){
			cacheService.set(
					nameSpace,
					key, ModulTypes.CACHETIME, listPie);
		}
		return listPie;
	}
	
	/**
	 * 根据一个或多个组织机构名称获得组织机构Id
	 * @param orgNames 组织机构名称
	 * @return
	 */
	private Long getOrganizationIdByNames(String orgNames){
		String[] orgNameArray = orgNames.split(",");
		Long parentId = null;
		Long orgId = null;
		Organization  org = null;
		for(int i = 0;i < orgNameArray.length;i++){
			List<Organization> list = organizationDubboService.findOrganizationsByOrgNameAndOrgTypeForPage(orgNameArray[i], 1, 20);
			if(list!=null && list.size()!=0){
				parentId = list.get(0).getId();
				if(i == 0){
					orgId = list.get(0).getId();
				}
				//判断后面是否还有数据
				if(i+1 < orgNameArray.length){
					org = organizationDubboService.getOrganizationsByParentAndOrgName(parentId, orgNameArray[i+1]);
					if(org != null){
						orgId = org.getId();
					}else{
						return null;
					}
				}
			}
		}
		return orgId;
	}
	
}
