package com.tianque.plugin.analysisNew.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.analysisNew.dao.BaseInfoStatisticNewDao;
import com.tianque.plugin.analysisNew.dao.PopulationStatTypeNewDao;
import com.tianque.plugin.analysisNew.dao.TendencyChartNewDao;
import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.domain.HighchartDataColumnVo;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.plugin.analysisNew.util.PopulationCatalog;
import com.tianque.plugin.analysisNew.util.PopulationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tableManage.service.TableManageService;

@Service("tendencyChartNewService")
public class TendencyChartNewServiceImpl extends AbstractBaseService implements
		TendencyChartNewService {
	private static final String TENDENCY_CHART_STATISTICS = "tendencyChartStatistics";
	@Autowired
	private TendencyChartNewDao tendencyChartNewDao;
	@Autowired
	private BaseInfoStatisticNewDao baseInfoStatisticsNewDao;

	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PopulationStatTypeNewDao populationStatTypeNewDao;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private BaseinfoStatisticNewService baseinfoStatisticNewService;
	// 分表存储时用的service
	@Autowired
	private TableManageService tableService;

	private void isExistTable(String[] time) {
		for (int i = 0; i < time.length; i++) {
			String[] data = time[i].split("-");
			int year = Integer.valueOf(data[0]);
			int month = Integer.valueOf(data[1]);
			tableService.createAnalyseTable(
					AnalyseUtilNew.ACTUALPERSONTABLENAME,
					AnalyseUtilNew.ACTUALPERSONSQL, year, month);
			tableService.createAnalyseTable(
					AnalyseUtilNew.IMPORTPERSONTABLENAME,
					AnalyseUtilNew.IMPORTPERSONSQL, year, month);
			tableService.createAnalyseTable(
					AnalyseUtilNew.IMPORTPLACETABLENAME,
					AnalyseUtilNew.IMPORTPLACESQL, year, month);
		}
	}

	@Override
	public HighchartColumnVo findTendencyChart(String typeTableName, Long orgId) {
		HighchartColumnVo highchartColumnVo = new HighchartColumnVo();
		String keyName = "";
		if ("POPULATION".equals(typeTableName)) {
			keyName = "population_statistics";
		} else {
			keyName = typeTableName;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		if (organization == null || organization.getId() == null) {
			throw new BusinessValidationException("组织机构信息未获得");
		}
		String key = TENDENCY_CHART_STATISTICS + "_" + keyName + "_"
				+ organization.getId() + "_" + ModulTypes.STATISTICSTREND;
		highchartColumnVo = (HighchartColumnVo) cacheService.get(key);
		if (highchartColumnVo != null) {
			return highchartColumnVo;
		}
		highchartColumnVo = new HighchartColumnVo();
		List<HighchartDataColumnVo> highchartDataColumnVos = new ArrayList<HighchartDataColumnVo>();
		String[] time = new String[12];
		time = getTime(time);
		highchartColumnVo.setCategories(time);
		// 实有人口
		isExistTable(time);
		highchartColumnVo = judgeHighchartDataColumn(typeTableName, orgId,
				highchartColumnVo, highchartDataColumnVos, time);
		cacheService.set(key, ModulTypes.CACHETIME, highchartColumnVo);
		return highchartColumnVo;

	}

	private List<PopulationCatalog> getPopulationCatalogListByType(String type) {
		if (type.equals(PopulationCatalog.ALL_BIRTH_POPULATION)) {
			return PopulationCatalog.getAllBirthPopulationCatalog();

		} else if (type.equals(PopulationCatalog.ALL_UNEMPLOYED_POPULATION)) {
			return PopulationCatalog.getAllUnemployPopulationCatalog();
		} else if (type.equals(PopulationCatalog.ALL_CIVIL_POPULATION)) {
			return PopulationCatalog.getAllCivilPopulationCatalog();
		} else if (type.equals(PopulationCatalog.ALL_ATTENTION_POPULATION)) {
			List<PopulationCatalog> populationCatalogs = PopulationCatalog
					.getAllAttentionPopulationCatalog();
			if (populationCatalogs == null) {
				return populationCatalogs;
			}
			for (int i = 0; i < populationCatalogs.size(); i++) {
				if (PopulationType.AIDSPOPULATIONS.equals(populationCatalogs
						.get(i).getCatalog())) {
					populationCatalogs.remove(i--);
				}
				if (PopulationType.F_PERSONNEL.equals(populationCatalogs.get(i)
						.getCatalog())) {
					populationCatalogs.remove(i--);
				}
				if (PopulationType.Q_PERSONNEL.equals(populationCatalogs.get(i)
						.getCatalog())) {
					populationCatalogs.remove(i--);
				}
				if (PopulationType.M_PERSONNEL.equals(populationCatalogs.get(i)
						.getCatalog())) {
					populationCatalogs.remove(i--);
				}
				if (PopulationType.GOOD_SAMARITAN.equals(populationCatalogs
						.get(i).getCatalog())) {
					populationCatalogs.remove(i--);
				}
			}
			return populationCatalogs;
		} else {
			return null;
		}
	}

	/**
	 * 趋势图的数据
	 * 
	 * @param type
	 * @param orgInternalCode
	 * @return
	 */
	private List<HighchartDataColumnVo> getHighchartDataColumnVoListByType(
			String type, Long orgId) {
		String[] time = new String[12];
		time = getTime(time);

		if (!PopulationType.ALL_YOUTH_POPULATION.equals(type)) {
			List<PopulationCatalog> list = getPopulationCatalogListByType(type);
			if (list != null && list.size() > 0) {
				return getTotalHighchartDataColumnVoList(time, list, orgId);
			}
		}

		PopulationCatalog populationCatalog = PopulationCatalog.parse(type);
		List<PropertyDict> propertyDictList = propertyDictService
				.findPropertyDictByDomainName(populationCatalog
						.getStatisticListSetting().getDomainName());
		HighchartDataColumnVo column;
		List<HighchartDataColumnVo> resultList = new ArrayList<HighchartDataColumnVo>();
		for (PropertyDict propertyDict : propertyDictList) {

			String typeName = propertyDict.getDisplayName();
			final int len = time.length;
			column = new HighchartDataColumnVo();
			column.setData(new int[len]);
			column.setName(typeName);
			for (int i = 0; i < len; i++) {
				column.getData()[i] = baseInfoStatisticsNewDao.countByMap(
						typeName, type, time[i], orgId);
			}
			resultList.add(column);
		}
		return resultList;
	}

	private List<HighchartDataColumnVo> getTotalHighchartDataColumnVoList(
			String[] time, List<PopulationCatalog> list, Long orgId) {

		List<HighchartDataColumnVo> resultList = new ArrayList<HighchartDataColumnVo>();
		if (list != null && list.size() > 0) {
			String name = PopulationCatalog.parse(
					(list.get(0).getParentCatalog())).getDisplayName();

			HighchartDataColumnVo highchartDataColumnVo = new HighchartDataColumnVo();
			highchartDataColumnVo.setName(name);
			highchartDataColumnVo.setStack(name);
			//获取('0~6岁','6~18岁','18~25岁','25~35岁')
			String typeNames = baseinfoStatisticNewService.getIdleYouth_TypeName();
			int[] datas = new int[time.length];
			for (int i = 0; i < time.length; i++) {

				datas[i] = baseInfoStatisticsNewDao.getOneTypeBaseinfoTotal(
						list, time[i], orgId, typeNames);
			}
			highchartDataColumnVo.setData(datas);
			resultList.add(highchartDataColumnVo);
		}
		return resultList;
	}

	private HighchartColumnVo judgeHighchartDataColumn(String typeTableName,
			Long orgId, HighchartColumnVo highchartColumnVo,
			List<HighchartDataColumnVo> highchartDataColumnVos, String[] time) {
		if (BaseInfoTables.keyTablesMaps.keySet().contains(typeTableName)) {
			highchartColumnVo.setModuleName(BaseInfoTables
					.getTypeDisplayNames(typeTableName) + "总况趋势增长图");
			highchartColumnVo.setSeries(getAllHighchartDataColumnVo(true,
					orgId, time, highchartDataColumnVos, typeTableName));
		} else {
			highchartColumnVo.setModuleName(BaseInfoTables
					.getTypeDisplayNames(typeTableName) + "增长图");
			// highchartDataColumnVos.add(getHighchartDataColumnVo(typeTableName,orgInternalCode,time));
			highchartColumnVo.setSeries(getAllHighchartDataColumnVo(false,
					orgId, time, highchartDataColumnVos,
					typeTableName));
		}
		return highchartColumnVo;
	}

	private List<HighchartDataColumnVo> getAllHighchartDataColumnVo(
			boolean all, Long orgId, String[] time,
			List<HighchartDataColumnVo> highchartDataColumnVos,
			String typeTableName) {
		highchartDataColumnVos.add(getHighchartDataColumnVo(all, typeTableName,
				orgId, time));
		return highchartDataColumnVos;
	}

	// private HighchartDataColumnVo getHighchartDataColumnVo(
	// boolean all, String orgInternalCode, String[] time,String type) {
	// HighchartDataColumnVo highchartDataColumnVo = new
	// HighchartDataColumnVo();
	// highchartDataColumnVo.setData(tendencyImportChart(typeTableName, time,
	// orgInternalCode));
	// highchartDataColumnVo.setName(type.equals(BaseInfoTables.IMPORTANTPERSONNEL_KEY)?BaseInfoTables.IMPORTANTPERSONNEL_DISPLAYNAME:BaseInfoTables.IMPORTANTPLACE_DISPLAYNAME);
	// highchartDataColumnVo.setStack("12345");
	// return highchartDataColumnVo;
	// }

	private HighchartDataColumnVo getHighchartDataColumnVo(boolean all,
			String typeTableName, Long orgId, String[] time) {
		HighchartDataColumnVo highchartDataColumnVo = new HighchartDataColumnVo();
		if (all) {
			highchartDataColumnVo.setData(tendencyImportChart(typeTableName,
					time, orgId));
		} else {
			highchartDataColumnVo.setData(tendencyChart(typeTableName, time,
					orgId));
		}
		highchartDataColumnVo.setName(BaseInfoTables
				.getTypeDisplayNames(typeTableName));
		highchartDataColumnVo.setStack("12345");
		return highchartDataColumnVo;
	}

	private String[] getTime(String[] time) {
		Date nowDate = new Date();
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTime(nowDate);
		nowCalendar.add(Calendar.MONTH, -12);

		for (int i = 0; i < 12; i++) {
			SimpleDateFormat timePattern = new SimpleDateFormat("yyyy-MM");
			time[i] = timePattern.format(nowCalendar.getTime());
			nowCalendar.add(Calendar.MONTH, 1);
		}

		return time;
	}

	private int[] tendencyImportChart(String typeTableName, String[] time,
			Long orgId) {
		List<String> keyNameList = new ArrayList<String>(
				BaseInfoTables.keyTablesMaps.get(typeTableName).keySet());
		boolean flag = BaseInfoTables.POPULATION_KEY.equals(typeTableName);
		int[] data = new int[12];
		for (int j = 0; j < time.length; j++) {
			Integer countSum = 0;
			for (int i = 0; i < keyNameList.size(); i++) {

				Integer count = null;
				if (flag) {
					count = populationStatTypeNewDao.findTendencyChart(
							keyNameList.get(i), orgId, time[j]);
				} else {
					count = tendencyChartNewDao.findTendencyChart(
							BaseInfoTables.getTypeValue(keyNameList.get(i)),
							ThreadVariable.getSession().getOrgInternalCode(),
							time[j]);
				}
				if (count != null) {
					countSum = countSum + count;
				}
				// countSum　+= baseInfoStatisticsNewDao.countByMonth(typeName,
				// t,
				// timeStr, orgInternalCode)
			}
			data[j] = countSum;
		}
		return data;
	}

	private int[] tendencyChartForPositiveinfo(String typeTableName,
			String[] time, Long orgId) {
		int[] data = new int[12];
		String typeNames = null;
		if(typeTableName != null && typeTableName.equals(PopulationType.IDLE_YOUTH)){
			//获取('0~6岁','6~18岁','18~25岁','25~35岁')
			typeNames = baseinfoStatisticNewService.getIdleYouth_TypeName();
		}
		
		for (int i = 0; i < time.length; i++) {
			Integer countSum = baseInfoStatisticsNewDao.countTotalByMap(null,
					typeTableName, time[i], orgId, typeNames);
			// 由于青少年历时数据表统计了年龄层与类型2种统计的数据,所以统计青少年总数要去掉一半
			if (PopulationCatalog.ALL_YOUTH_POPULATION.equals(typeTableName)) {
				countSum = countSum / 2;
			}
			if (countSum != null) {
				data[i] = countSum;
			} else {
				data[i] = 0;
			}
		}
		return data;
	}

	private int[] tendencyChart(String typeTableName, String[] time, Long orgId) {
		boolean flag = BaseInfoTables.getPopulationKeys().contains(
				typeTableName);
		int[] data = new int[12];
		for (int i = 0; i < time.length; i++) {
			Integer countSum = null;
			if (flag) {
				countSum = populationStatTypeNewDao.findTendencyChart(
						typeTableName, orgId, time[i]);
			} else {
				countSum = tendencyChartNewDao.findTendencyChart(
						BaseInfoTables.getTypeValue(typeTableName),
						ThreadVariable.getSession().getOrgInternalCode(),
						time[i]);
			}
			if (countSum != null) {
				data[i] = countSum;
			} else {
				data[i] = 0;
			}
		}
		return data;
	}

	@Override
	public HighchartColumnVo findTendencyChartForPositiveinfo(String type,
			Long orgId) {
		HighchartColumnVo highchartColumnVo = null;
//		Organization organization = organizationDubboService
//				.getSimpleOrgById(orgId);
//		if (organization == null || organization.getId() == null) {
//			throw new BusinessValidationException("组织机构信息未获得");
//		}//tendencyChartStatistics
		if (orgId==null) {
			throw new BusinessValidationException("组织机构信息未获得");
		}
		String key = TENDENCY_CHART_STATISTICS + "_" + type + "_"
				+ orgId + "_" + ModulTypes.STATISTICSTREND;
		highchartColumnVo = (HighchartColumnVo) cacheService.get(key);
		if (highchartColumnVo != null) {
//			return highchartColumnVo;
		} else {
			highchartColumnVo = new HighchartColumnVo();
		}
		String[] time = new String[12];
		time = getTime(time);
		highchartColumnVo.setCategories(time);
		// 重点人员
		isExistTable(time);
		List<HighchartDataColumnVo> highchartDataColumnVos = getHighchartDataColumnVoListByType(
				type, orgId);
		highchartColumnVo = newJudgeHighchartDataColumn(type, orgId,
				highchartColumnVo, highchartDataColumnVos, time);
		cacheService.set(key, ModulTypes.CACHETIME, highchartColumnVo);
		return highchartColumnVo;
	}

	private HighchartColumnVo newJudgeHighchartDataColumn(String typeTableName,
			Long orgId, HighchartColumnVo highchartColumnVo,
			List<HighchartDataColumnVo> highchartDataColumnVos, String[] time) {
		if (typeTableName.equals(PopulationCatalog.ALL_ATTENTION_POPULATION)) {
			highchartColumnVo.setModuleName("特殊人群" + "增长图");
		} else if (typeTableName
				.equals(PopulationCatalog.ALL_UNEMPLOYED_POPULATION)) {
			highchartColumnVo.setModuleName("失业人员" + "增长图");
		} else if (typeTableName.equals(PopulationCatalog.ALL_BIRTH_POPULATION)) {
			highchartColumnVo.setModuleName("计生" + "增长图");
		} else if (typeTableName.equals(PopulationCatalog.ALL_CIVIL_POPULATION)) {
			highchartColumnVo.setModuleName("关怀对象" + "增长图");
		} else {

			highchartColumnVo.setModuleName(PopulationType
					.getCnameByPopulationType(typeTableName) + "增长图");

			highchartDataColumnVos.add(newGetHighchartDataColumnVo(
					typeTableName, orgId, time));

		}
		highchartColumnVo.setSeries(highchartDataColumnVos);

		return highchartColumnVo;
	}

	private HighchartDataColumnVo newGetHighchartDataColumnVo(
			List<HighchartDataColumnVo> highchartDataColumnVos, String[] times) {
		HighchartDataColumnVo highchartDataColumnVo = new HighchartDataColumnVo();

		int[] datas = new int[12];
		for (int i = 0; i < times.length; i++) {
			for (HighchartDataColumnVo vo : highchartDataColumnVos) {
				datas[i] += vo.getData()[i];
			}

		}

		highchartDataColumnVo.setData(datas);
		// TODO 把人口和场所区分开
		// if(typeTableName.toLowerCase().indexOf("House".toLowerCase())>=0){
		// highchartDataColumnVo.setName("总数量");
		// }else {
		highchartDataColumnVo.setName("总数量");
		// }
		highchartDataColumnVo.setStack("12345");
		return highchartDataColumnVo;
	}

	private HighchartDataColumnVo newGetHighchartDataColumnVo(
			String typeTableName, Long orgId, String[] time) {
		HighchartDataColumnVo highchartDataColumnVo = new HighchartDataColumnVo();
		highchartDataColumnVo.setData(tendencyChartForPositiveinfo(
				typeTableName, time, orgId));
		// TODO 把人口和场所区分开
		// if(typeTableName.toLowerCase().indexOf("House".toLowerCase())>=0){
		// highchartDataColumnVo.setName("总数量");
		// }else {
		highchartDataColumnVo.setName("总数量");
		// }
		highchartDataColumnVo.setStack("12345");
		return highchartDataColumnVo;
	}

	@Override
	public HighchartColumnVo findTendencyChartForActualPopulation(Long orgId) {
		HighchartColumnVo highchartColumnVo = new HighchartColumnVo();
		List<HighchartDataColumnVo> highchartDataColumnVos = new ArrayList<HighchartDataColumnVo>();
		String[] time = new String[12];
		time = getTime(time);
		isExistTable(time);
		highchartColumnVo.setCategories(time);
		HighchartDataColumnVo highchartDataColumnVo = new HighchartDataColumnVo();
		List<PopulationCatalog> list = PopulationCatalog
				.getAllActualPopulationCatalog();
		for (PopulationCatalog populationCatalog : list) {
			getHighchartDataColumnVoListByOrgInternalCode(orgId, time,
					highchartDataColumnVos, populationCatalog.getCatalog()
							.toString());
		}
		String population = "POPULATION";
		highchartDataColumnVo.setData(tendencyImportChart(population, time,
				orgId));
		highchartDataColumnVo.setName("人员总数");
		highchartDataColumnVo.setStack("12345");
		highchartDataColumnVos.add(highchartDataColumnVo);
		highchartColumnVo.setSeries(highchartDataColumnVos);

		return highchartColumnVo;
	}

	/**
	 * 工作台高层实有人口趋势图的数据
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	private List<HighchartDataColumnVo> getHighchartDataColumnVoListByOrgInternalCode(
			Long orgId, String[] time,
			List<HighchartDataColumnVo> highchartDataColumnVos,
			String typeTableName) {
		return getAllHighchartDataColumnVo(false, orgId, time,
				highchartDataColumnVos, typeTableName);
	}
}
