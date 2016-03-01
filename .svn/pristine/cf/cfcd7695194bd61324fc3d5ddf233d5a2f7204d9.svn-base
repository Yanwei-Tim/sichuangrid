package com.tianque.baseInfo.base.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.service.util.PopulationCatalog;

@Repository("countrymenDao")
public class CountrymenDaoImpl extends AbstractBaseDao implements CountrymenDao {

	@Override
	public Countrymen getCountrymenByPopulationIdAndPopulationCatalog(
			Long populationId, PopulationCatalog populationCatalog) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("populationId", populationId);
		map.put("catalog", constructPopulationTableParams(populationCatalog));
		return (Countrymen) getSqlMapClientTemplate().queryForObject(
				"countrymen.getCountrymenByPopulationIdAndPopulationCatalog",
				map);
	}

	@Override
	public List<Countrymen> findCountrymensByPopulationIdAndPopulationType(
			Long populationId, String populationType, String orgInternalCode,
			String shardCode) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("populationId", populationId);
		map.put("populationType", populationType);
		map.put("orgInternalCode", orgInternalCode);
		map.put("catalogs",
				formCatalogs(PopulationCatalog.getAllActualPopulationCatalog()));
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate().queryForList(
				"countrymen.findCountrymensByPopulationIdAndPopulationType",
				map);
	}

	private List<Map<String, Object>> formCatalogs(
			List<PopulationCatalog> catalogs) {
		List<Map<String, Object>> catalogsParams = new ArrayList<Map<String, Object>>();
		for (PopulationCatalog populationCatalog : catalogs) {
			catalogsParams
					.add(constructPopulationTableParams(populationCatalog));
		}
		return catalogsParams;
	}

	private Map<String, Object> constructPopulationTableParams(
			PopulationCatalog catalogs) {
		Map<String, Object> catalogParam = new LinkedHashMap<String, Object>();
		catalogParam.put("tableName", catalogs.getTableName());
		catalogParam.put("type", catalogs.getCatalog());
		return catalogParam;
	}

	@Override
	public List<Countrymen> findCountrymensByIdCardNoAndOrgInternalCode(
			String idCardNo, String orgInternalCode, String shardCode) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("idCardNo", idCardNo);
		map.put("orgInternalCode", orgInternalCode);
		map.put("catalogs",
				formCatalogs(PopulationCatalog.getAllActualPopulationCatalog()));
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate().queryForList(
				"countrymen.findCountrymensByIdCardNoAndOrgInternalCode", map);
	}
}
