package com.tianque.plugin.analyzing.service;

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
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.analyzing.dao.BaseInfoStatisticDao;
import com.tianque.plugin.analyzing.dao.PopulationStatTypeDao;
import com.tianque.plugin.analyzing.dao.TendencyChartDao;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.HighchartDataColumnVo;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tableManage.service.TableManageService;

@Service("tendencyChartService")
public class TendencyChartServiceImpl extends AbstractBaseService implements
		TendencyChartService {
	private static final String TENDENCY_CHART_STATISTICS = "tendencyChartStatistics";
	@Autowired
	private TendencyChartDao tendencyChartDao;
	@Autowired
	private BaseInfoStatisticDao baseInfoStatisticsDao;

	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PopulationStatTypeDao populationStatTypeDao;
	@Autowired
	private CacheService cacheService;
	// 分表存储时用的service
	@Autowired
	private TableManageService tableService;

	private void isExistTable(String[] time) {
		for (int i = 0; i < time.length; i++) {
			String[] data = time[i].split("-");
			int year = Integer.valueOf(data[0]);
			int month = Integer.valueOf(data[1]);
			tableService.createAnalyseTable(AnalyseUtil.ACTUALPERSONTABLENAME,
					AnalyseUtil.ACTUALPERSONSQL, year, month);
			tableService.createAnalyseTable(AnalyseUtil.IMPORTPERSONTABLENAME,
					AnalyseUtil.IMPORTPERSONSQL, year, month);
			tableService.createAnalyseTable(AnalyseUtil.IMPORTPLACETABLENAME,
					AnalyseUtil.IMPORTPLACESQL, year, month);
		}
	}

	@Override
	public HighchartColumnVo findTendencyChart(String typeTableName,
			String orgInternalCode) {
		HighchartColumnVo highchartColumnVo = new HighchartColumnVo();
		String keyName = "";
		if ("POPULATION".equals(typeTableName)) {
			keyName = "population_statistics";
		} else {
			keyName = typeTableName;
		}
		Organization organization = organizationDubboService
				.getOrganizationByOrganizationCode(orgInternalCode);
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
		highchartColumnVo = judgeHighchartDataColumn(typeTableName,
				orgInternalCode, highchartColumnVo, highchartDataColumnVos,
				time);
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
			return PopulationCatalog.getAllAttentionPopulationCatalog();
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
			String type, String orgInternalCode) {
		String[] time = new String[12];
		time = getTime(time);

		if (!PopulationType.ALL_YOUTH_POPULATION.equals(type)) {
			List<PopulationCatalog> list = getPopulationCatalogListByType(type);
			if (list != null && list.size() > 0) {
				return getTotalHighchartDataColumnVoList(time, list,
						orgInternalCode);
			}
		}
		PopulationCatalog populationCatalog = PopulationCatalog.parse(type);
		List<HighchartDataColumnVo> resultList = new ArrayList<HighchartDataColumnVo>();
		if (populationCatalog == null
				|| populationCatalog.getStatisticListSetting() == null
				|| !StringUtil.isStringAvaliable(populationCatalog
						.getStatisticListSetting().getDomainName())) {
			return resultList;
		}
		List<PropertyDict> propertyDictList = propertyDictService
				.findPropertyDictByDomainName(populationCatalog
						.getStatisticListSetting().getDomainName());
		HighchartDataColumnVo column;

		for (PropertyDict propertyDict : propertyDictList) {

			String typeName = propertyDict.getDisplayName();
			final int len = time.length;
			column = new HighchartDataColumnVo();
			column.setData(new int[len]);
			column.setName(typeName);
			for (int i = 0; i < len; i++) {
				column.getData()[i] = baseInfoStatisticsDao.countByMap(
						typeName, type, time[i], orgInternalCode);
			}
			resultList.add(column);
		}
		return resultList;
	}

	private List<HighchartDataColumnVo> getTotalHighchartDataColumnVoList(
			String[] time, List<PopulationCatalog> list, String orgInternalCode) {

		List<HighchartDataColumnVo> resultList = new ArrayList<HighchartDataColumnVo>();
		if (list != null && list.size() > 0) {
			String name = PopulationCatalog.parse(
					(list.get(0).getParentCatalog())).getDisplayName();

			HighchartDataColumnVo highchartDataColumnVo = new HighchartDataColumnVo();
			highchartDataColumnVo.setName(name);
			highchartDataColumnVo.setStack(name);
			int[] datas = new int[time.length];
			for (int i = 0; i < time.length; i++) {

				datas[i] = baseInfoStatisticsDao.getOneTypeBaseinfoTotal(list,
						time[i], orgInternalCode);
			}
			highchartDataColumnVo.setData(datas);
			resultList.add(highchartDataColumnVo);
		}
		return resultList;
	}

	private HighchartColumnVo judgeHighchartDataColumn(String typeTableName,
			String orgInternalCode, HighchartColumnVo highchartColumnVo,
			List<HighchartDataColumnVo> highchartDataColumnVos, String[] time) {
		if (BaseInfoTables.keyTablesMaps.keySet().contains(typeTableName)) {
			highchartColumnVo.setModuleName(BaseInfoTables
					.getTypeDisplayNames(typeTableName) + "总况趋势增长图");
			highchartColumnVo.setSeries(getAllHighchartDataColumnVo(true,
					orgInternalCode, time, highchartDataColumnVos,
					typeTableName));
		} else {
			highchartColumnVo.setModuleName(BaseInfoTables
					.getTypeDisplayNames(typeTableName) + "增长图");
			// highchartDataColumnVos.add(getHighchartDataColumnVo(typeTableName,orgInternalCode,time));
			highchartColumnVo.setSeries(getAllHighchartDataColumnVo(false,
					orgInternalCode, time, highchartDataColumnVos,
					typeTableName));
		}
		return highchartColumnVo;
	}

	private List<HighchartDataColumnVo> getAllHighchartDataColumnVo(
			boolean all, String orgInternalCode, String[] time,
			List<HighchartDataColumnVo> highchartDataColumnVos,
			String typeTableName) {
		highchartDataColumnVos.add(getHighchartDataColumnVo(all, typeTableName,
				orgInternalCode, time));
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
			String typeTableName, String orgInternalCode, String[] time) {
		HighchartDataColumnVo highchartDataColumnVo = new HighchartDataColumnVo();
		if (all) {
			highchartDataColumnVo.setData(tendencyImportChart(typeTableName,
					time, orgInternalCode));
		} else {
			highchartDataColumnVo.setData(tendencyChart(typeTableName, time,
					orgInternalCode));
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
			String orgInternalCode) {
		List<String> keyNameList = new ArrayList<String>(
				BaseInfoTables.keyTablesMaps.get(typeTableName).keySet());
		boolean flag = BaseInfoTables.POPULATION_KEY.equals(typeTableName);
		int[] data = new int[12];
		for (int j = 0; j < time.length; j++) {
			Integer countSum = 0;
			for (int i = 0; i < keyNameList.size(); i++) {

				Integer count = null;
				if (flag) {
					count = populationStatTypeDao.findTendencyChart(
							keyNameList.get(i), orgInternalCode, time[j]);
				} else {
					count = tendencyChartDao.findTendencyChart(
							BaseInfoTables.getTypeValue(keyNameList.get(i)),
							orgInternalCode, time[j]);
				}
				if (count != null) {
					countSum = countSum + count;
				}
				// countSum　+= baseInfoStatisticsDao.countByMonth(typeName, t,
				// timeStr, orgInternalCode)
			}
			data[j] = countSum;
		}
		return data;
	}

	private int[] tendencyChartForPositiveinfo(String typeTableName,
			String[] time, String orgInternalCode) {
		int[] data = new int[12];
		for (int i = 0; i < time.length; i++) {
			Integer countSum = baseInfoStatisticsDao.countTotalByMap(null,
					typeTableName, time[i], orgInternalCode);
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

	private int[] tendencyChart(String typeTableName, String[] time,
			String orgInternalCode) {
		boolean flag = BaseInfoTables.getPopulationKeys().contains(
				typeTableName);
		int[] data = new int[12];
		for (int i = 0; i < time.length; i++) {
			Integer countSum = null;
			if (flag) {
				countSum = populationStatTypeDao.findTendencyChart(
						typeTableName, orgInternalCode, time[i]);
			} else {
				countSum = tendencyChartDao.findTendencyChart(
						BaseInfoTables.getTypeValue(typeTableName),
						orgInternalCode, time[i]);
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
			String orgInternalCode) {
		HighchartColumnVo highchartColumnVo = null;
		Organization organization = organizationDubboService
				.getOrganizationByOrganizationCode(orgInternalCode);
		if (organization == null || organization.getId() == null) {
			throw new BusinessValidationException("组织机构信息未获得");
		}
		String key = TENDENCY_CHART_STATISTICS + "_" + type + "_"
				+ organization.getId() + "_" + ModulTypes.STATISTICSTREND;
		highchartColumnVo = (HighchartColumnVo) cacheService.get(key);
		if (highchartColumnVo != null) {
			return highchartColumnVo;
		} else {
			highchartColumnVo = new HighchartColumnVo();
		}
		String[] time = new String[12];
		time = getTime(time);
		highchartColumnVo.setCategories(time);
		// 重点人员
		isExistTable(time);
		List<HighchartDataColumnVo> highchartDataColumnVos = getHighchartDataColumnVoListByType(
				type, orgInternalCode);
		highchartColumnVo = newJudgeHighchartDataColumn(type, orgInternalCode,
				highchartColumnVo, highchartDataColumnVos, time);
		cacheService.set(key, ModulTypes.CACHETIME, highchartColumnVo);
		return highchartColumnVo;
	}

	private HighchartColumnVo newJudgeHighchartDataColumn(String typeTableName,
			String orgInternalCode, HighchartColumnVo highchartColumnVo,
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
					typeTableName, orgInternalCode, time));

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
			String typeTableName, String orgInternalCode, String[] time) {
		HighchartDataColumnVo highchartDataColumnVo = new HighchartDataColumnVo();
		highchartDataColumnVo.setData(tendencyChartForPositiveinfo(
				typeTableName, time, orgInternalCode));
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
	public HighchartColumnVo findTendencyChartForActualPopulation(
			String orgInternalCode) {
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
			getHighchartDataColumnVoListByOrgInternalCode(orgInternalCode,
					time, highchartDataColumnVos, populationCatalog
							.getCatalog().toString());
		}
		String population = "POPULATION";
		highchartDataColumnVo.setData(tendencyImportChart(population, time,
				orgInternalCode));
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
			String orgInternalCode, String[] time,
			List<HighchartDataColumnVo> highchartDataColumnVos,
			String typeTableName) {
		return getAllHighchartDataColumnVo(false, orgInternalCode, time,
				highchartDataColumnVos, typeTableName);
	}
}
