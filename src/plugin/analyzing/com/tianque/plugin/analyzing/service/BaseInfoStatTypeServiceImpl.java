package com.tianque.plugin.analyzing.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.analyzing.dao.BaseInfoStatTypeDao;
import com.tianque.plugin.analyzing.dao.StatisticsBaseInfoDao;
import com.tianque.plugin.analyzing.domain.BaseInfoStatType;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tableManage.service.TableManageService;

@Service("baseInfoStatTypeService")
public class BaseInfoStatTypeServiceImpl extends AbstractBaseService implements
		BaseInfoStatTypeService {
	Logger logger = Logger.getLogger(getClass());
	@Autowired
	private BaseInfoStatTypeDao baseInfoStatTypeDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private StatisticsBaseInfoDao statisticsBaseInfoDao;
	// 分表存储时用的service
	@Autowired
	private TableManageService tableService;

	// FIXME 这两个居然是全局的？？
	public int help = 0;
	public int noHelp = 0;

	@Override
	public void addBaseInfoStatType() {
		PropertyDict propertyDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_LEVEL, "片组片格");
		List<Organization> list = organizationDubboService
				.fingOrganizationforLevel(propertyDict.getId());
		int year = CalendarUtil.getLastMonthYearValue();
		int month = CalendarUtil.getLastMonth();
		saveImportantPlaceTablesTables(list, year, month,
				BaseInfoTables.importantPlaceTables);
	}

	@Override
	public void addBaseInfoStatType(int year, int month) {
		PropertyDict propertyDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_LEVEL, "片组片格");
		List<Organization> list = organizationDubboService
				.fingOrganizationforLevel(propertyDict.getId());
		saveImportantPlaceTablesTables(list, year, month,
				BaseInfoTables.importantPlaceTables);
	}

	private void saveImportantPlaceTablesTables(List<Organization> list,
			int year, int month, Map<String, String> placeMap) {
		// 规上规下backTablesMap
		placeMap.put(BaseInfoTables.ENTERPRISEKEY_KEY, BaseInfoTables
				.backTablesMap().get(BaseInfoTables.ENTERPRISEKEY_KEY));
		placeMap.put(BaseInfoTables.ENTERPRISEDOWNKEY_KEY, BaseInfoTables
				.backTablesMap().get(BaseInfoTables.ENTERPRISEDOWNKEY_KEY));
		// 判断是否存在表，不存在创建表
		tableService.createAnalyseTable(AnalyseUtil.IMPORTPLACETABLENAME,
				AnalyseUtil.IMPORTPLACESQL, year, month);
		//创建索引 
		tableService.createAnalyseIndex("baseinfostattype", "typeName",
				"orgInternalCode");
		// 删除原有的数据
		deleteAllDataByYearAndMonthAndPlaceMap(
				BaseInfoTables.IMPORTANTPLACE_KEY, placeMap, year, month);

		for (int i = 0; i < list.size(); i++) {
			// 大类总数（包含关注与非关注）
			int sum = getImportantPlaceCountSumByOrgInternalCode(list.get(i)
					.getOrgInternalCode(), placeMap, year, month);
			if (sum == 0) {
				continue;
			}
			for (Entry<String, String> entry : placeMap.entrySet()) {
				// 小类总数
				int objectSum = getCountByOrgInternalCode(list.get(i)
						.getOrgInternalCode(), entry, year, month);
				if (objectSum == 0) {
					continue;
				}
				// 小类关注总数
				int total = getAttentionCountByOrgInternalCode(list.get(i)
						.getOrgInternalCode(), entry.getValue(),
						entry.getKey(), year, month);
				// 小类每月新增
				int monthCreateSum = getMonthCreateSumByOrgInternalCode(list
						.get(i).getOrgInternalCode(), entry, year, month);
				// 小类已落实
				help = getHelpCountByOrgInternalCode(list.get(i)
						.getOrgInternalCode(), entry.getValue(),
						entry.getKey(), year, month);
				createBaseInfoStatType(total,
						BaseInfoTables.getTypeDisplayNames(entry.getKey()),
						BaseInfoTables.IMPORTANTPLACE_KEY, list.get(i)
								.getOrgInternalCode(), sum, objectSum,
						monthCreateSum, year, month);
			}
		}
	}

	private void deleteAllDataByYearAndMonthAndPlaceMap(String baseinfotype,
			Map<String, String> placeMap, int year, int month) {
		List types = new ArrayList();
		for (Entry<String, String> entry : placeMap.entrySet()) {
			types.add(BaseInfoTables.getTypeDisplayNames(entry.getKey()));
		}
		baseInfoStatTypeDao.deleteByYearAndMonth(baseinfotype, types, year,
				month);
	}

	private int getImportantPlaceCountSumByOrgInternalCode(
			String orgInternalCode, Map<String, String> map, int year, int month) {
		int sum = 0;
		for (Entry<String, String> entry : map.entrySet()) {
			sum += getImportantPlaceCountByOrgInternalCode(orgInternalCode,
					entry.getValue(), entry.getKey(), year, month);
		}
		return sum;
	}

	private int getImportantPlaceCountByOrgInternalCode(String orgInternalCode,
			String tableName, String type, int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", type);
		buildPacleMap(tableName, map);
		map.put("endDate", CalendarUtil.getNextMonthStart(year, month));
		return statisticsBaseInfoDao
				.getCountByOrgInternalCodeAndTableNameAndMap(
						orgInternalCode,
						map.get("tableName") == null ? tableName : (String) map
								.get("tableName"), map);
	}

	/**
	 * 根据组织机构获取表中的数据
	 * 
	 * @param orgInternalCode
	 * @param tableName
	 * @param type
	 * @param year
	 * @param month
	 * @return
	 */
	private int getAttentionCountByOrgInternalCode(String orgInternalCode,
			String tableName, String type, int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("endDate", CalendarUtil.getNextMonthStart(year, month));
		map.put("key", type);
		map.put("isEmphasis", IsEmphasis.Emphasis);
		buildPacleMap(tableName, map);
		return statisticsBaseInfoDao
				.getCountByOrgInternalCodeAndTableNameAndMap(orgInternalCode,
						tableName, map);
	}

	private int getHelpCountByOrgInternalCode(String orgInternalCode,
			String tableName, String type, int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("help", "help");
		map.put("endDate", CalendarUtil.getNextMonthStart(year, month));
		buildPacleMap(tableName, map);
		map.put("isEmphasis", IsEmphasis.Emphasis);
		return statisticsBaseInfoDao
				.getCountByOrgInternalCodeAndTableNameAndMap(orgInternalCode,
						tableName, map);
	}

	private void buildPacleMap(String tableName, Map<String, Object> map) {
		if (BaseInfoTables.getTypeValue(BaseInfoTables.FIRESAFETYKEY_KEY)
				.equals(tableName)
				|| BaseInfoTables.getTypeValue(
						BaseInfoTables.SAFETYPRODUCTIONKEY_KEY).equals(
						tableName)
				|| BaseInfoTables.getTypeValue(BaseInfoTables.SECURITYKEY_KEY)
						.equals(tableName)
				|| BaseInfoTables
						.getTypeValue(BaseInfoTables.ENTERPRISEKEY_KEY).equals(
								tableName)
				|| BaseInfoTables.getTypeValue(
						BaseInfoTables.ENTERPRISEDOWNKEY_KEY).equals(tableName)) {
			map.put("keyType", tableName);

			tableName = "enterprises";
			map.put("tableName", tableName);
		}
	}

	private int getCountByOrgInternalCode(String orgInternalCode,
			Entry<String, String> entry, int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", entry.getValue());
		map.put("orgInternalCode", orgInternalCode);
		map.put("isEmphasis", IsEmphasis.Emphasis);
		map.put("nextMonthStart", CalendarUtil.getNextMonthStart(year, month));
		return getObjectSumfor(map);

	}

	private int getMonthCreateSumByOrgInternalCode(String orgInternalCode,
			Entry<String, String> entry, int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", entry.getValue());
		map.put("orgInternalCode", orgInternalCode);
		return getMonthCreateSum(map, year, month);
	}

	private void createBaseInfoStatType(int total, String propertyDictName,
			String type, String orgInternalCode, int sum, int objectSum,
			int monthCreateSum, int year, int month) {
		BaseInfoStatType baseInfoStatType = new BaseInfoStatType();
		baseInfoStatType.setTotal(Long.valueOf(total));
		baseInfoStatType.setOrgInternalCode(orgInternalCode);
		baseInfoStatType.setBaseinfoType(type);
		baseInfoStatType.setYear(year);
		baseInfoStatType.setMonth(month);
		baseInfoStatType.setTypeName(propertyDictName);
		if (baseInfoStatType.getTotal() == null) {
			baseInfoStatType.setTotal(0L);
		}
		baseInfoStatType.setSum(sum);
		baseInfoStatType.setPercentage(createPercentage(sum, total));
		baseInfoStatType.setIsHelp(help);
		baseInfoStatType.setNoHelp(total - help);
		baseInfoStatType.setObjectsum(objectSum);
		baseInfoStatType.setMonthcreate(monthCreateSum);
		logger.info("插入历史记录信息：" + baseInfoStatType.toString());
		baseInfoStatTypeDao.addBaseInfoStatType(baseInfoStatType);
	}

	private String createPercentage(int sum, int total) {
		if (sum == 0) {
			return String.valueOf("0.00");
		}
		return String.valueOf(Double.parseDouble(new java.text.DecimalFormat(
				"#.00").format((double) total / (double) sum * 100))) + "%";
	}

	private int getObjectSumfor(Map<String, Object> map) {

		buildPacleMap((String) map.get("tableName"), map);
		return baseInfoStatTypeDao.getOBjectSumOrMonthCreate(map);
	}

	private int getMonthCreateSum(Map<String, Object> map, int year, int nowMoth) {
		// Calendar calendar = new GregorianCalendar();
		// calendar.set(Calendar.DATE, 1);
		int nowYear = year;
		int nowMonth = nowMoth;

		buildPacleMap((String) map.get("tableName"), map);

		map.put("createDate", CalendarUtil.getMonthStart(nowYear, nowMonth));
		map.put("nextMonthStart",
				CalendarUtil.getNextMonthStart(nowYear, nowMonth));
		return baseInfoStatTypeDao.getOBjectSumOrMonthCreate(map);
	}

	@Override
	public void createEnterpriseHistoryStatistic(int year, int month) {
		PropertyDict propertyDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_LEVEL, "片组片格");
		List<Organization> list = organizationDubboService
				.fingOrganizationforLevel(propertyDict.getId());
		saveImportantPlaceTablesTables(list, year, month,
				BaseInfoTables.enterpriseTables);
	}

}
