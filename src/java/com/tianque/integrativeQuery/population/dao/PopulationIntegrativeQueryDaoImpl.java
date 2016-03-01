package com.tianque.integrativeQuery.population.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.integrativeQuery.population.domain.PopulationBaseQueryVo;
import com.tianque.integrativeQuery.population.domain.PopulationQueryVo;
import com.tianque.service.util.PopulationCatalog;

@Repository("populationIntegrativeQueryDao")
public class PopulationIntegrativeQueryDaoImpl extends AbstractBaseDao implements
		PopulationIntegrativeQueryDao {

	@Override
	public PageInfo<Countrymen> searchPopulationByIntegrativeCondition(
			PopulationQueryVo populationQueryVo, String actualPersonType,
			String[] attentionPopulationTypes, String[] actualTypes, PropertyDict gender,
			Date birthdayStrart, Date birthdayEnd, Boolean hasHouse, String orgCode,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = buildMap(populationQueryVo, actualPersonType, attentionPopulationTypes, actualTypes,
				gender, birthdayStrart, birthdayEnd, hasHouse, orgCode, sidx, sord);

		int countNum = 0;
		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"integrativeQueryPopulation.count_integrative_query_result", map);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		if (countNum > 0) {
			map.put("skipResults", (pageNum - 1) * pageSize);
			map.put("maxResults", ((pageNum - 1) * pageSize) + pageSize);
		}

		map.put("order", sord);
		map.put("sortField", sidx);
		List<Countrymen> list = getSqlMapClientTemplate().queryForList(
				"integrativeQueryPopulation.integrativeQueryPopulation", map);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<Countrymen> createPageInfo(Integer pageNum, Integer pageSize,
			Integer countNum, List list) {
		PageInfo<Countrymen> pageInfo = new PageInfo<Countrymen>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum == null ? 0 : pageNum);
		pageInfo.setPerPageSize(pageSize == null ? 0 : pageSize);
		return pageInfo;
	}

	/**
	 * 获取选择的业务的表的表名的list集合
	 * 
	 * @param attentionPopulationTypes
	 * @param map
	 * @return
	 */
	private List<String> tableAliasList(String[] attentionPopulationTypes, Map<String, Object> map) {
		List<String> tableAliasList = new ArrayList<String>();
		if (attentionPopulationTypes == null || attentionPopulationTypes.length == 0) {
			return tableAliasList;
		}

		String tableName;
		for (String type : attentionPopulationTypes) {
			tableName = PopulationCatalog.parse(type).getStatisticListSetting().getTableName();
			// 如果是重点青少年的话，不添加到tablesAliaslist中，并且在map中新增一个key
			if (tableName.equals("idleyouths")) {
				map.put("idleYouth", tableName);
			} else {
				tableAliasList.add(tableName);
				map.put(tableName, tableName);
			}

		}
		return tableAliasList;
	}

	private void putActualTypeToMap(Map<String, Object> map, String[] actualTypes) {
		String tableName;
		for (String type : actualTypes) {
			tableName = PopulationCatalog.parse(type).getStatisticListSetting().getTableName();
			map.put(tableName, tableName);
		}
	}

	/**
	 * 拼装连接条件的语句，如a.orgid=b.orgid and a.idcardno=b.idcardno ...
	 */
	private String budileConnectSql(List<String> tables) {
		StringBuilder stb = new StringBuilder();
		int temp;
		for (int i = 0; i < tables.size() - 1; i++) {
			temp = i;
			stb.append(tables.get(i)).append(".").append("orgId").append("=")
					.append(tables.get(temp + 1)).append(".").append("orgId").append(" and ")
					.append(tables.get(i)).append(".").append("baseinfoid").append("=")
					.append(tables.get(temp + 1)).append(".").append("baseinfoid").append(" and ");
		}
		return stb.substring(0, stb.lastIndexOf("and"));
	}

	@Override
	public List<Countrymen> searchPopulationByIntegrativeConditionForExport(
			PopulationQueryVo populationQueryVo, String acutalPopulation,
			String[] attentionPopulationTypes, String[] actualTypesByPermission,
			PropertyDict gender, Date birthdayStrart, Date birthdayEnd, Boolean hasHouse,
			String orgCode, Integer pageNum, Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = new HashMap<String, Object>();
		map = buildMap(populationQueryVo, acutalPopulation, attentionPopulationTypes,
				actualTypesByPermission, gender, birthdayStrart, birthdayEnd, hasHouse, orgCode,
				sidx, sord);
		if (pageNum != null && pageSize != null) {
			map.put("skipResults", (pageNum - 1) * pageSize);
			map.put("maxResults", ((pageNum - 1) * pageSize) + pageSize);
		}
		map.put("order", sord);
		map.put("sortField", sidx);
		return getSqlMapClientTemplate().queryForList(
				"integrativeQueryPopulation.integrativeQueryPopulation", map);
	}

	private Map<String, Object> buildMap(PopulationQueryVo populationQueryVo,
			String actualPersonType, String[] attentionPopulationTypes, String[] actualTypes,
			PropertyDict gender, Date birthdayStrart, Date birthdayEnd, Boolean hasHouse,
			String orgCode, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationQueryVo", populationQueryVo);
		map.put("gender", gender);
		map.put("birthdayStrart", birthdayStrart);
		map.put("birthdayEnd", birthdayEnd);
		map.put("hasHouse", hasHouse);
		map.put("orgCode", orgCode);
		if (actualPersonType == null || actualPersonType.isEmpty()
				|| actualPersonType.equals(PopulationQueryVo.ALL_ACTUAL_POPULATION)) {
			actualPersonType = PopulationCatalog.ALL_ACTUAL_POPULATION;
		}
		map.put("actualPersonType", actualPersonType);
		List<String> tables = tableAliasList(attentionPopulationTypes, map);
		map.put("attentionPopulationTypes", tableAliasList(attentionPopulationTypes, map));
		if (actualTypes != null && actualTypes.length > 0) {
			putActualTypeToMap(map, actualTypes);
		}

		map.put("attentionTypesNum", attentionPopulationTypes.length);
		if (tables.size() > 1) {
			map.put("connectSql", budileConnectSql(tables));
		}

		return map;
	}

	@Override
	public PageInfo queryPopulationForWorkBench(String searchText, String orgCode, Integer page,
			Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", orgCode);
		map.put("searchText", searchText);

		int countNum = 0;
		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"integrativeQueryPopulation.count_queryPopulationForWorkBench", map);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;

		if (countNum > 0) {
			map.put("skipResults", (page - 1) * rows);
			map.put("maxResults", ((page - 1) * rows) + rows);
		}

		map.put("order", sord);
		map.put("sortField", sidx);
		List<Countrymen> list = getSqlMapClientTemplate().queryForList(
				"integrativeQueryPopulation.queryPopulationForWorkBench", map);
		return createPageInfo(page, rows, countNum, list);
	}

	@Override
	public PageInfo<Countrymen> findPopulationsByBaseQueryVoAndTypes(
			PopulationBaseQueryVo populationBaseQueryVo,
			List<Map<String, String>> typeTableMapList, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationBaseQueryVo", populationBaseQueryVo);
		map.put("typeTableMapList", typeTableMapList);

		int countNum = 0;
		countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"integrativeQueryPopulation.count_findPopulationsByBaseQueryVoAndTypes", map);
		if (null != pageNum && pageSize != null) {
			int pageCount = 0;
			if ((countNum % pageSize) == 0) {
				pageCount = countNum / pageSize;
			} else {
				pageCount = countNum / pageSize + 1;
			}
			pageNum = pageNum > pageCount ? pageCount : pageNum;

			if (countNum > 0) {
				map.put("skipResults", (pageNum - 1) * pageSize);
				map.put("maxResults", ((pageNum - 1) * pageSize) + pageSize);
			}
		}

		map.put("order", sord);
		map.put("sortField", sidx);
		List<Countrymen> list = getSqlMapClientTemplate().queryForList(
				"integrativeQueryPopulation.findPopulationsByBaseQueryVoAndTypes", map);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public Countrymen findPopulationsByPopulationIdAndType(Long populationId, String tableName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationId", populationId);
		map.put("tableName", tableName);
		return (Countrymen) getSqlMapClientTemplate().queryForObject(
				"integrativeQueryPopulation.findPopulationsByPopulationIdAndType", map);
	}

}
