package com.tianque.plugin.analysisNew.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.analysisNew.dao.StatAnalysePlaceNewDao;
import com.tianque.plugin.analysisNew.dao.StatisticsBaseInfoNewDao;
import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.domain.HighchartDataColumnVo;
import com.tianque.plugin.analysisNew.domain.StatAnalysePlaceVo;
import com.tianque.plugin.analysisNew.util.AnalyseUtilNew;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tableManage.service.TableManageService;

@Service("statAnalysePlaceNewService")
public class StatAnalysePlaceNewServiceImpl extends AbstractBaseService
		implements StatAnalysePlaceNewService {
	@Autowired
	private StatAnalysePlaceNewDao statAnalysePlaceNewDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private StatisticsBaseInfoNewDao statisticsBaseInfoNewDao;
	// 分表存储时用的service
	@Autowired
	private TableManageService tableService;
	@Autowired
	private PropertyDictService propertyDictService;

	/**
	 * 列表数据，实时
	 */
	@Override
	public List<StatAnalysePlaceVo> findStatAnalysePlace(String tableName,
			Long orgId, String keyType) {
		List<StatAnalysePlaceVo> statAnalysePlaceVolist = new ArrayList<StatAnalysePlaceVo>();

		List<Organization> list = organizationDubboService
				.findAdminOrgsByParentId(orgId);

		int sum = 0; // 总数
		int totalHelp = 0; // 已帮教总数
		for (int i = 0; i < list.size(); i++) {
			sum += statAnalysePlaceNewDao.getStatAnalysePlace(tableName, list
					.get(i).getOrgInternalCode(), keyType);
		}
		// 添加每条记录
		for (int i = 0; i < list.size(); i++) {
			StatAnalysePlaceVo statAnalysePlaceVo = new StatAnalysePlaceVo();
			int total = statAnalysePlaceNewDao.getStatAnalysePlace(tableName,
					list.get(i).getOrgInternalCode(), keyType);
			statAnalysePlaceVo.setTotal(total);
			int ishelp = statAnalysePlaceNewDao.getIsHelpStatAnalysePlace(
					tableName, list.get(i).getOrgInternalCode(), keyType);
			totalHelp = totalHelp + ishelp;
			statAnalysePlaceVo.setHelped(ishelp);
			statAnalysePlaceVo.setNoHelp(total - ishelp);
			statAnalysePlaceVo.setOrganization(list.get(i));
			// 设置每条记录百分比
			statAnalysePlaceVo.setPercentage(Double
					.parseDouble(new java.text.DecimalFormat("#.0000")
							.format(total / (double) (sum == 0 ? 1 : sum))));
			statAnalysePlaceVolist.add(statAnalysePlaceVo);
		}
		// 合计
		StatAnalysePlaceVo statAnalysePlaceVo = new StatAnalysePlaceVo();
		Organization org = new Organization();
		org.setOrgName("合计");
		statAnalysePlaceVo.setOrganization(org);
		statAnalysePlaceVo.setTotal(sum);
		statAnalysePlaceVo.setHelped(totalHelp);
		statAnalysePlaceVo.setNoHelp(sum - totalHelp);
		// 设置合计的百分比
		if (sum == 0) {
			statAnalysePlaceVo
					.setPercentage(Double
							.parseDouble(new java.text.DecimalFormat("#.00")
									.format(0)));
		} else {
			statAnalysePlaceVo
					.setPercentage(Double
							.parseDouble(new java.text.DecimalFormat("#.00")
									.format(1)));
		}
		statAnalysePlaceVolist.add(statAnalysePlaceVo);

		return statAnalysePlaceVolist;
	}

	/**
	 * 列表数据，历史
	 */
	@Override
	public List<StatAnalysePlaceVo> findStatAnalysePlaceFromHistory(
			String tableName, Long orgId, String keyType, int year, int month) {
		tableService.createAnalyseTable(AnalyseUtilNew.IMPORTPLACETABLENAME,
				AnalyseUtilNew.IMPORTPLACESQL, year, month);
		List<StatAnalysePlaceVo> statAnalysePlaceVolist = new ArrayList<StatAnalysePlaceVo>();
		List<Organization> list = organizationDubboService
				.findAdminOrgsByParentId(orgId);

		int sum = 0;// 求合计的总数
		int ishelpCount = 0; // 求已帮扶的总数
		for (int i = 0; i < list.size(); i++) {
			sum += getCountByTime(list.get(i).getOrgInternalCode(),
					BaseInfoTables.getTypeDisplayNames(keyType),
					BaseInfoTables.IMPORTANTPLACE_KEY, year, month);
		}

		for (int i = 0; i < list.size(); i++) {
			StatAnalysePlaceVo statAnalysePlaceVo = new StatAnalysePlaceVo();
			// 有待优化
			int total = getCountByTime(list.get(i).getOrgInternalCode(),
					BaseInfoTables.getTypeDisplayNames(keyType),
					BaseInfoTables.IMPORTANTPLACE_KEY, year, month);
			int ishelp = statisticsBaseInfoNewDao.getIsHelpFromHistory(list
					.get(i).getOrgInternalCode(), BaseInfoTables
					.getTypeDisplayNames(keyType),
					BaseInfoTables.IMPORTANTPLACE_KEY, year, month);
			ishelpCount = ishelpCount + ishelp;
			statAnalysePlaceVo.setTotal(total);
			statAnalysePlaceVo.setHelped(ishelp);
			statAnalysePlaceVo.setNoHelp(total - ishelp);
			statAnalysePlaceVo.setOrganization(list.get(i));
			// 设置每条记录百分比
			statAnalysePlaceVo.setPercentage(Double
					.parseDouble(new java.text.DecimalFormat("#.0000")
							.format(total / (double) (sum == 0 ? 1 : sum))));
			statAnalysePlaceVolist.add(statAnalysePlaceVo);
		}
		// 合计数据
		StatAnalysePlaceVo statAnalysePlaceVo = new StatAnalysePlaceVo();
		Organization org = new Organization();
		org.setOrgName("合计");
		statAnalysePlaceVo.setOrganization(org);
		statAnalysePlaceVo.setTotal(sum);
		statAnalysePlaceVo.setHelped(ishelpCount);
		statAnalysePlaceVo.setNoHelp(sum - ishelpCount);
		// 设置合计的百分比
		if (sum == 0) {
			statAnalysePlaceVo
					.setPercentage(Double
							.parseDouble(new java.text.DecimalFormat("#.00")
									.format(0)));
		} else {
			statAnalysePlaceVo
					.setPercentage(Double
							.parseDouble(new java.text.DecimalFormat("#.00")
									.format(1)));
		}
		statAnalysePlaceVolist.add(statAnalysePlaceVo);
		return statAnalysePlaceVolist;
	}

	/**
	 * 区域分布图，历史
	 */
	@Override
	public HighchartColumnVo getStatAnalysePlaceFromHistory(Long orgId,
			String keyType, int year, int month) {
		// 判断表是否存在，不存在创建表
		tableService.createAnalyseTable(AnalyseUtilNew.IMPORTPLACETABLENAME,
				AnalyseUtilNew.IMPORTPLACESQL, year, month);
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo highchartColumn = new HighchartColumnVo();
		highchartColumn.setModuleName(BaseInfoTables
				.getTypeDisplayNames(keyType) + "区域分布图");
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);

		List<HighchartDataColumnVo> datas = new ArrayList<HighchartDataColumnVo>();

		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		int[] data = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			data[i] = getCountByTime(organizations.get(i).getOrgInternalCode(),
					BaseInfoTables.getTypeDisplayNames(keyType),
					BaseInfoTables.IMPORTANTPLACE_KEY, year, month);
			positiveinfoColumnData.setData(data);
			positiveinfoColumnData.setName(BaseInfoTables
					.getTypeDisplayNames(keyType));
			positiveinfoColumnData.setStack(BaseInfoTables
					.getTypeDisplayNames(keyType));
		}
		datas.add(positiveinfoColumnData);
		highchartColumn.setSeries(datas);
		highchartColumn.setCategories(getOrgArraysByParentId(orgId));
		// 查询帮教情况，历史
		List<Map<String, Object>> list = statisticsBaseInfoNewDao
				.getHelpedCountFromHistory(orgId,
						BaseInfoTables.IMPORTANTPLACE_KEY,
						BaseInfoTables.getTypeDisplayNames(keyType), year,
						month);
		// 查询帮教情况
		return finHelpData(keyType, organizations, highchartColumn, list);
	}

	/**
	 * 添加帮教的情况
	 * 
	 * @param orgId
	 * @param keyType
	 * @param year
	 * @param month
	 * @param organizations
	 * @param highchartColumn
	 * @return
	 */
	private HighchartColumnVo finHelpData(String keyType,
			List<Organization> organizations,
			HighchartColumnVo highchartColumn, List<Map<String, Object>> list) {

		if (list == null || list.size() == 0) {
			return new HighchartColumnVo();
		}
		String[] propertyDictList = AnalyseUtilNew.groupMap.get(BaseInfoTables
				.getTypeDisplayNames(keyType));
		int DictSize = propertyDictList.length - 2;// 属性的条目
		int size = list.size();// 地区条目

		List<List<Integer>> datas = new ArrayList<List<Integer>>();// 存放各类型对应的数量
		HighchartDataColumnVo column;

		List<Integer> isHelpLists = new ArrayList<Integer>();
		List<Integer> noHelpLists = new ArrayList<Integer>();
		for (int i = 0; i < organizations.size(); i++) {
			// 从查询结果中取一条记录
			isHelpLists.add(Integer.parseInt(((BigDecimal) list.get(i).get(
					"ISHELP")).toString()));

			noHelpLists.add(Integer.parseInt(((BigDecimal) list.get(i).get(
					"NOHELP")).toString()));
		}
		datas.add(isHelpLists);
		datas.add(noHelpLists);
		Integer[] data = new Integer[size];
		for (int i = 0; i < DictSize; i++) {
			column = new HighchartDataColumnVo();
			column.setName(propertyDictList[i]);
			column.setStack(propertyDictList[DictSize]);
			datas.get(i).toArray(data);
			column.setData(getIntByInteger(data));
			highchartColumn.getSeries().add(column);
		}
		return highchartColumn;
	}

	private int[] getIntByInteger(Integer[] integers) {
		int len = integers.length;
		int[] results = new int[len];
		for (int i = 0; i < len; i++) {
			results[i] = integers[i];
		}
		return results;
	}

	private int getCountByTime(String orgInternalCode, String tableName,
			String baseinfotype, int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("orgInternalCode", orgInternalCode);
		map.put("typeName", tableName);
		map.put("baseinfotype", baseinfotype);
		int total = statisticsBaseInfoNewDao.getCountFromHistory(map);
		return total;
	}

	private String[] getOrgArraysByParentId(Long orgId) {
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		String[] orgNames = new String[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			orgNames[i] = organizations.get(i).getOrgName();
		}
		return orgNames;
	}

	@Override
	public HighchartColumnVo getStatAnalysePlace(String tableName, Long orgId,
			String keyType) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo highchartColumn = new HighchartColumnVo();
		highchartColumn.setModuleName(BaseInfoTables
				.getTypeDisplayNames(keyType) + "区域分布图");
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);

		List<HighchartDataColumnVo> datas = new ArrayList<HighchartDataColumnVo>();

		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		int[] data = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			data[i] = statAnalysePlaceNewDao.getStatAnalysePlace(tableName,
					organizations.get(i).getOrgInternalCode(), keyType);
			positiveinfoColumnData.setData(data);
			positiveinfoColumnData.setName(BaseInfoTables
					.getTypeDisplayNames(keyType));
		}
		datas.add(positiveinfoColumnData);

		positiveinfoColumns.addAll(datas);
		highchartColumn.setSeries(positiveinfoColumns);
		highchartColumn.setCategories(getOrgArraysByParentId(orgId));

		// 查询帮教情况，历史
		List<Map<String, Object>> list = statAnalysePlaceNewDao
				.getHelpedStatAnalysePlace(tableName, orgId, keyType,
						getOrgType());
		// 查询帮教情况
		return finHelpData(keyType, organizations, highchartColumn, list);
	}

	private Long getOrgType() {
		return propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.ADMINISTRATIVE_REGION).get(0).getId();
	}
}
