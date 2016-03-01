package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HousePopulationDao;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.HouseLivingPopulationInfo;
import com.tianque.domain.vo.HouseLivingTotalInfo;
import com.tianque.domain.vo.HouseSimpleInfoForSearch;
import com.tianque.search.vo.QuickFilterType;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.shard.util.ShardConversion;

@Repository("housePopulationDao")
public class HousePopulationDaoImpl extends AbstractBaseDao implements
		HousePopulationDao {
	@Autowired
	private ShardConversion shardConversion;

	private final static String DEFAULT_LIVING = "defaultLivingHouse";

	private Map<String, Object> getSinglePopulationHouseParams(
			PopulationCatalog catalog, Long populationId, Long houseId) {
		Map<String, Object> result = getSinglePopulationParams(catalog,
				populationId);
		result.put("houseId", houseId);
		return result;
	}

	private Map<String, Object> getSingleHouseParams(PopulationCatalog catalog,
			Long houseId) {
		Map<String, Object> result = getPopulationCatalogParams(catalog);
		result.put("houseId", houseId);
		return result;
	}

	private Map<String, Object> getMultiPopulationParams(
			PopulationCatalog catalog, Long[] populationIds) {
		Map<String, Object> result = getPopulationCatalogParams(catalog);
		result.put(DEFAULT_LIVING, Boolean.TRUE);
		result.put("populationIds", populationIds);
		return result;
	}

	private Map<String, Object> getSinglePopulationParams(
			PopulationCatalog catalog, Long populationId) {
		Map<String, Object> result = getPopulationCatalogParams(catalog);
		result.put(DEFAULT_LIVING, Boolean.TRUE);
		result.put("populationId", populationId);
		return result;
	}

	private Map<String, Object> getPopulationCatalogParams(
			PopulationCatalog catalog) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("personType", catalog.getParentCatalog());
		result.put("populationType", catalog.getCatalog());
		return result;
	}

	@Override
	public boolean bindActualPopulationHouse(PopulationCatalog catalog,
			Long populationId, Long houseId, boolean ignoreDefaultLiving,
			String shardCode) {
		Map<String, Object> condition = getSinglePopulationHouseParams(catalog,
				populationId, houseId);
		condition.put(DEFAULT_LIVING, ignoreDefaultLiving);
		getSqlMapClientTemplate().insert(
				"housePopulations.bindActualPopulationHouse", condition);
		super.updateTableUpdateDateById("houseinfo" + "_" + shardCode, houseId);
		return Boolean.TRUE;
	}

	@Override
	public boolean bindImpotantPopulationHouse(PopulationCatalog catalog,
			Long populationId, Long houseId, boolean ignoreDefaultLiving) {
		Map<String, Object> condition = getSinglePopulationHouseParams(catalog,
				populationId, houseId);
		condition.put(DEFAULT_LIVING, ignoreDefaultLiving);
		getSqlMapClientTemplate().insert(
				"housePopulations.bindImpotantPopulationHouse", condition);
		return Boolean.TRUE;
	}

	@Override
	public Long getActualPopulationLivingHouseId(PopulationCatalog catalog,
			Long id) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"housePopulations.getActualPopulationLivingHouseId",
				getSinglePopulationParams(catalog, id));
	}

	@Override
	public Long getImportantPopulationLivingHouseId(PopulationCatalog catalog,
			Long id) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"housePopulations.getImportantPopulationLivingHouseId",
				getSinglePopulationParams(catalog, id));
	}

	@Override
	public void releaseActualPopulationAllHouse(PopulationCatalog catalog,
			Long populationId) {
		getSqlMapClientTemplate().delete(
				"housePopulations.releaseActualPopulationAllHouse",
				getSinglePopulationParams(catalog, populationId));
	}

	@Override
	public boolean releaseActualPopulationHouse(PopulationCatalog catalog,
			Long populationId, Long houseId, String shardCode) {
		getSqlMapClientTemplate().delete(
				"housePopulations.releaseActualPopulationHouse",
				getSinglePopulationHouseParams(catalog, populationId, houseId));
		super.updateTableUpdateDateById("houseinfo" + "_" + shardCode, houseId);
		return Boolean.TRUE;
	}

	@Override
	public void releaseActualPopulationsAllHouse(PopulationCatalog catalog,
			Long[] populationIds) {
		getSqlMapClientTemplate().delete(
				"housePopulations.releaseActualPopulationsAllHouse",
				getMultiPopulationParams(catalog, populationIds));

	}

	@Override
	public void releaseImportantPopulationAllHouse(PopulationCatalog catalog,
			Long populationId) {
		getSqlMapClientTemplate().delete(
				"housePopulations.releaseImportantPopulationAllHouse",
				getSinglePopulationParams(catalog, populationId));
	}

	@Override
	public void releaseImportantPopulationsAllHouse(PopulationCatalog catalog,
			Long[] populationIds) {
		getSqlMapClientTemplate().delete(
				"housePopulations.releaseImportantPopulationsAllHouse",
				getMultiPopulationParams(catalog, populationIds));
	}

	@Override
	public boolean releaseImpotantPopulationHouse(PopulationCatalog catalog,
			Long populationId, Long houseId) {
		getSqlMapClientTemplate().delete(
				"housePopulations.releaseImpotantPopulationHouse",
				getSinglePopulationHouseParams(catalog, populationId, houseId));
		return Boolean.TRUE;
	}

	@Override
	public HouseSimpleInfoForSearch getActualPopulationLivingHouseInfo(
			PopulationCatalog catalog, Long populationId, String shardCode) {
		Map<String, Object> map = getPopulationCatalogParams(catalog);
		map.put(DEFAULT_LIVING, Boolean.TRUE);
		map.put("populationId", populationId);
		map.put("shardCode", shardCode);
		List<HouseSimpleInfoForSearch> list = getSqlMapClientTemplate()
				.queryForList(
						"housePopulations.getActualPopulationLivingHouseInfo",
						map);
		if (list != null && list.size() > 0) {
			return (HouseSimpleInfoForSearch) list.get(0);
		}
		return null;
	}

	@Override
	public HouseSimpleInfoForSearch getImportantPopulationLivingHouseInfo(
			PopulationCatalog catalog, Long populationId, String shardCode) {
		Map<String, Object> result = getPopulationCatalogParams(catalog);
		result.put(DEFAULT_LIVING, Boolean.TRUE);
		result.put("populationId", populationId);
		result.put(shardCode, shardCode);
		return (HouseSimpleInfoForSearch) getSqlMapClientTemplate()
				.queryForObject(
						"housePopulations.getImportantPopulationLivingHouseInfo",
						result);
	}

	@Override
	public List<HouseLivingTotalInfo> findLivingHouseActualPopulationTotalInfos(
			PopulationCatalog catalog, Long houseId) {
		return getSqlMapClientTemplate().queryForList(
				"housePopulations.findLivingHouseActualPopulationTotalInfos",
				getSingleHouseParams(catalog, houseId));
	}

	@Override
	public List<HouseLivingTotalInfo> findLivingHouseAllActualPopulationTotalInfos(
			Long houseId) {
		return getSqlMapClientTemplate()
				.queryForList(
						"housePopulations.findLivingHouseAllActualPopulationTotalInfos",
						houseId);
	}

	@Override
	public List<HouseLivingTotalInfo> findLivingHouseAllAttentionPopulationTotalInfos(
			Long houseId) {
		return getSqlMapClientTemplate()
				.queryForList(
						"housePopulations.findLivingHouseAllAttentionPopulationTotalInfos",
						houseId);
	}

	@Override
	public List<HouseLivingTotalInfo> findLivingHouseAllPopulationTotalInfos(
			Long houseId) {
		List<HouseLivingTotalInfo> result = findLivingHouseAllActualPopulationTotalInfos(houseId);
		result.addAll(findLivingHouseAllAttentionPopulationTotalInfos(houseId));
		return result;
	}

	@Override
	public List<HouseLivingTotalInfo> findLivingHouseAttentionPopulationTotalInfos(
			PopulationCatalog catalog, Long houseId) {
		return getSqlMapClientTemplate()
				.queryForList(
						"housePopulations.findLivingHouseAttentionPopulationTotalInfos",
						getSingleHouseParams(catalog, houseId));
	}

	@Override
	public List<HouseLivingPopulationInfo> findLivingHouseActualPopulationInfos(
			PopulationCatalog catalog, Long houseId) {
		return getSqlMapClientTemplate().queryForList(
				"housePopulations.findLivingHouseActualPopulationInfos",
				houseId);
	}

	@Override
	public List<HouseLivingPopulationInfo> findLivingHouseAllActualPopulationInfos(
			Long houseId, String shardCode) {
		// TODO 系统中房屋内人员信息
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("houseId", houseId);
		condition.put("catalogs", new ArrayList<Map<String, Object>>());
		condition.put("shardCode", shardCode);
		List<PopulationCatalog> allcatalogs = PopulationCatalog
				.getAllActualPopulationCatalog();
		for (PopulationCatalog catalog : allcatalogs) {
			((List) condition.get("catalogs"))
					.add(constructPopulationTableParams(catalog));
		}
		return getSqlMapClientTemplate().queryForList(
				"housePopulations.findLivingHouseAllActualPopulationInfos",
				condition);
	}

	@Override
	public Integer countLivingHouseAllActualPopulationInfos(Long houseId,
			String shardCode) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("houseId", houseId);
		condition.put("shardCode", shardCode);
		condition.put("catalogs", new ArrayList<Map<String, Object>>());
		List<PopulationCatalog> allcatalogs = PopulationCatalog
				.getAllActualPopulationCatalog();
		for (PopulationCatalog catalog : allcatalogs) {
			((List) condition.get("catalogs"))
					.add(constructPopulationTableParams(catalog));
		}
		Integer result = (Integer) getSqlMapClientTemplate().queryForObject(
				"housePopulations.countLivingHouseAllActualPopulationInfos",
				condition);
		return result;
	}

	@Override
	public List<HouseLivingPopulationInfo> findLivingHouseAllAttentionPopulationInfos(
			Long houseId) {
		return getSqlMapClientTemplate().queryForList(
				"housePopulations.findLivingHouseAllAttentionPopulationInfos",
				houseId);
	}

	@Override
	public List<HouseLivingPopulationInfo> findLivingHouseAttentionPopulationInfos(
			PopulationCatalog catalog, Long houseId) {
		return getSqlMapClientTemplate().queryForList(
				"housePopulations.findLivingHouseAttentionPopulationInfos",
				houseId);
	}

	private Map<String, Object> constructPopulationTableParams(
			PopulationCatalog catalogs) {
		Map<String, Object> catalogParam = new LinkedHashMap<String, Object>();
		catalogParam.put("tableName", catalogs.getTableName());
		catalogParam.put("type", catalogs.getCatalog());
		return catalogParam;
	}

	@Override
	public PageInfo<HouseLivingPopulationInfo> findNotLivingHouseAllActualPopulationInfos(
			Long houseId, String shardCode, String orgInternalCode,
			String certificationNo, String name, QuickFilterType deathStatus,
			QuickFilterType registeStatus, Integer gender,
			List<Long> baseInfoIds, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("houseId", houseId);
		params.put("shardCode", shardCode);
		params.put("orgCode", orgInternalCode + "%");
		params.put("certificationNo", certificationNo);
		params.put("name", name + "%");
		params.put("gender", gender);
		params.put("baseInfoIds", baseInfoIds);
		if (null != deathStatus && null != deathStatus.toBoolean()) {
			params.put("deadth", deathStatus.toBoolean());
		}
		if (null != registeStatus && null != registeStatus.toBoolean()) {
			params.put("actived", registeStatus.toBoolean());
		}
		List<Map<String, Object>> catalogParams = new ArrayList<Map<String, Object>>();
		List<PopulationCatalog> catalogs = PopulationCatalog
				.getAllActualPopulationCatalog();
		for (PopulationCatalog catalog : catalogs) {
			catalogParams.add(constructPopulationTableParams(catalog));
		}
		params.put("catalogs", catalogParams);
		if (isStrotFieldAvaliable(sidx)) {
			params.put("sortField", sidx);
			params.put("order", sord);
		}
		Integer totalCount = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"housePopulations.countNotLivingActualPopulationsByNameAndIdcard",
						params);
		return createPageInfo(
				pageNum,
				pageSize,
				totalCount,
				getSqlMapClientTemplate()
						.queryForList(
								"housePopulations.findNotLivingActualPopulationsByNameAndIdcard",
								params, (pageNum - 1) * pageSize, pageSize));
	}

	private PageInfo<HouseLivingPopulationInfo> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<HouseLivingPopulationInfo> pageInfo = new PageInfo<HouseLivingPopulationInfo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@Override
	public PageInfo<HouseLivingPopulationInfo> findGisPopulationInfosByQueryStrAndOrgcode(
			String orgInternalCode, String queryStr, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("queryStr", queryStr);
		params.put("orgCode", orgInternalCode);
		params.put("catalogs",
				formCatalogs(PopulationCatalog.getAllActualPopulationCatalog()));
		params.put("sortField", sidx);
		params.put("order", sord);
		Integer totalCount = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"housePopulations.countGisPopulationsByNameAndOrgCode",
						params);
		List<HouseLivingPopulationInfo> list = getSqlMapClientTemplate()
				.queryForList(
						"housePopulations.findGisPopulationsByNameAndOrgCode",
						params, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, totalCount, list);
	}

	@Override
	public int countActualPopulationByHouseId(Map<String, Object> params) {
		Integer totalCount = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"housePopulations.countActualPopulationByHouseId",
						params);
		if (null != totalCount) {
			return totalCount.intValue();
		}
		return 0;
	}

	@Override
	public PageInfo<HouseLivingPopulationInfo> findFurtherStepGisPersonInfoSearchByPersonTypeAndOrgId(
			String orgInternalCode, String personType, String queryStr,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("populationtypes", personType);
		map.put("queryStr", queryStr);
		List<Map<String, Object>> catalogsParams = new ArrayList<Map<String, Object>>();
		List<PopulationCatalog> catalogs = PopulationCatalog
				.getAllActualPopulationCatalog();
		for (PopulationCatalog populationCatalog : catalogs) {
			catalogsParams
					.add(constructPopulationTableParams(populationCatalog));
		}
		map.put("catalogs", catalogsParams);
		map.put("sortField", sidx);
		Integer count = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"housePopulations.countGisFurtherStepSearchByQueryStrAndOrgCodeAndPersonType",
						map);
		List<HouseLivingPopulationInfo> list = getSqlMapClientTemplate()
				.queryForList(
						"housePopulations.findGisFurtherStepSearchByQueryStrAndOrgCodeAndPersonType",
						map);
		return createPageInfo(pageNum, pageSize, count, list);
	}

	@Override
	public PageInfo<HouseLivingPopulationInfo> findFurtherStepGisPersonInfoSearchByPersonTypeListAndOrgId(
			String orgInternalCode, List personType, String queryStr,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("populationtypes", personType);
		map.put("queryStr", queryStr);
		map.put("catalogs", formAttentCatalogs(PopulationCatalog
				.getAllActualPopulationCatalog()));
		map.put("sortField", sidx);
		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"housePopulations.countGisFurtherStepSearchByQueryStrAndOrgCodeAndPersonTypes",
						map);
		List<HouseLivingPopulationInfo> list = getSqlMapClientTemplate()
				.queryForList(
						"housePopulations.findGisFurtherStepSearchByQueryStrAndOrgCodeAndPersonTypeList",
						map, (pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public List<HouseLivingTotalInfo> findGisActualPersonTotalInfosByorgInternalCode(
			String orgInternalCode) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("catalogs",
				formCatalogs(PopulationCatalog.getAllActualPopulationCatalog()));
		return getSqlMapClientTemplate().queryForList(
				"housePopulations.findGisActualPersonTotalInfosByOrgId", map);
	}

	public int countGisAttenPopulationByOrgIdAndKinds(String orgInternalCode,
			List<String> kinds) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
		map.put("kinds", kinds);
		map.put("catalogs", formAttentCatalogs(PopulationCatalog
				.getAllActualPopulationCatalog()));
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"housePopulations.countGisAttentionPopulationByOrgIdAndKinds",
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

	private List<Map<String, Object>> formAttentCatalogs(
			List<PopulationCatalog> catalogs) {
		List<Map<String, Object>> catalogsParams = new ArrayList<Map<String, Object>>();
		for (PopulationCatalog populationCatalog : catalogs) {
			if (populationCatalog.getCatalog().equals(
					BaseInfoTables.FLOATINGPOPULATION_KEY)
					|| populationCatalog.getCatalog().equals(
							BaseInfoTables.HOUSEHOLDSTAFF_KEY)) {
				catalogsParams
						.add(constructPopulationTableParams(populationCatalog));
			}
		}
		return catalogsParams;
	}

	@Override
	public List<HouseLivingTotalInfo> findGisActualPersonTotalInfosByOrgs(
			List<Organization> orgs, String actulaType) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> catalogsParams = new ArrayList<Map<String, Object>>();
		String tableName = getTableMsg(actulaType);
		if (orgs != null && orgs.size() > 0) {
			for (Organization org : orgs) {
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("orgCode", org.getDepartmentNo());
				tempMap.put("orgName", org.getOrgName());
				tempMap.put("orgInternalCode", org.getOrgInternalCode());
				catalogsParams.add(tempMap);
			}
		}
		map.put("tableName", tableName);
		map.put("type", actulaType);
		map.put("catalogs", catalogsParams);
		List<HouseLivingTotalInfo> uu = getSqlMapClientTemplate().queryForList(
				"housePopulations.findGisActualPersonTotalInfosByParentOrgId",
				map);
		return uu;
	}

	private String getTableMsg(String actulaType) {
		boolean temp = false;
		String tableName = "";
		List<PopulationCatalog> catalogs = PopulationCatalog
				.getAllActualPopulationCatalog();
		for (PopulationCatalog populationCatalog : catalogs) {
			if (populationCatalog.getCatalog() != null
					&& populationCatalog.getCatalog().equals(actulaType)) {
				tableName = populationCatalog.getTableName();
				temp = true;
				break;
			}
		}
		if (!temp) {
			List<PopulationCatalog> birthCatalog = PopulationCatalog
					.getAllBirthPopulationCatalog();
			for (PopulationCatalog populationCatalog : birthCatalog) {
				if (populationCatalog.getCatalog() != null
						&& populationCatalog.getCatalog().equals(actulaType)) {
					tableName = populationCatalog.getTableName();
					temp = true;
					break;
				}
			}
		}
		if (!temp) {
			List<PopulationCatalog> civilCatalog = PopulationCatalog
					.getAllCivilPopulationCatalog();
			for (PopulationCatalog populationCatalog : civilCatalog) {
				if (populationCatalog.getCatalog() != null
						&& populationCatalog.getCatalog().equals(actulaType)) {
					tableName = populationCatalog.getTableName();
					temp = true;
					break;
				}
			}
		}
		if (!temp) {
			List<PopulationCatalog> attentionCatalog = PopulationCatalog
					.getAllAttentionPopulationCatalog();
			for (PopulationCatalog populationCatalog : attentionCatalog) {
				if (populationCatalog.getCatalog() != null
						&& populationCatalog.getCatalog().equals(actulaType)) {
					tableName = populationCatalog.getTableName();
					temp = true;
					break;
				}
			}
		}
		return tableName;
	}

	@Override
	public PageInfo<HouseLivingPopulationInfo> searchActulaPersonByName(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgCode", orgInternalCode);
		List<Map<String, Object>> catalogsParams = new ArrayList<Map<String, Object>>();
		List<PopulationCatalog> catalogs = PopulationCatalog
				.getAllActualPopulationCatalog();
		for (PopulationCatalog populationCatalog : catalogs) {
			catalogsParams
					.add(constructPopulationTableParams(populationCatalog));
		}
		params.put("catalogs", catalogsParams);
		params.put("sortField", sidx);
		params.put("order", sord);
		Integer totalCount = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"housePopulations.countGissearchActulaPersonByNameAndOrgCode",
						params);
		List<HouseLivingPopulationInfo> list = getSqlMapClientTemplate()
				.queryForList(
						"housePopulations.findGissearchActulaPersonByNameAndOrgCode",
						params, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, totalCount, list);
	}

	@Override
	public List<HouseLivingPopulationInfo> findActulaPopulationInHouseByHouseIdForGis(
			Long houseId, Integer page, Integer rows, String sidx, String sord) {
		// TODO gis 房屋内人员信息
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("houseId", houseId);
		condition.put("catalogs", new ArrayList<Map<String, Object>>());
		List<PopulationCatalog> allcatalogs = PopulationCatalog
				.getAllActualPopulationCatalog();
		for (PopulationCatalog catalog : allcatalogs) {
			((List) condition.get("catalogs"))
					.add(constructPopulationTableParams(catalog));
		}
		return getSqlMapClientTemplate().queryForList(
				"housePopulations.findGisActulaPopulationInHouseByHouseId",
				condition, (page - 1) * rows, rows);
	}

	@Override
	public Map<String, Integer> getMapForSpecialPopulation(Long houseId) {
		return getSqlMapClientTemplate().queryForMap(
				"housePopulations.getMapForSpecialPopulation", houseId, "key",
				"value");
	}

	@Override
	public Map<String, Integer> getMapForActualPopulation(Long houseId,
			String shardCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", houseId);
		map.put("shardCode", shardCode);
		return getSqlMapClientTemplate().queryForMap(
				"housePopulations.getMapForActualPopulation", map, "key",
				"value");
	}

	@Override
	public PageInfo<HouseLivingPopulationInfo> findHouseholdStaffForPage(
			Long houseId, Integer pageNum, Integer pageSize, String sidx,
			String sord, String shardCode) {

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("sortField", sidx);
		condition.put("order", sord);
		condition.put("houseId", houseId);
		condition.put("catalogs", new ArrayList<Map<String, Object>>());
		List<PopulationCatalog> allcatalogs = PopulationCatalog
				.getAllActualPopulationCatalog();
		for (PopulationCatalog catalog : allcatalogs) {
			((List) condition.get("catalogs"))
					.add(constructPopulationTableParams(catalog));
		}
		condition.put("shardCode", shardCode);
		Integer countInteger = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"housePopulations.countFindLivingHouseAllActualPopulationInfos",
						condition);
		condition.put("startRow", (pageNum - 1) * pageSize);
		condition.put("endRow", pageNum * pageSize);
		List<HouseLivingPopulationInfo> resultList = getSqlMapClientTemplate()
				.queryForList(
						"housePopulations.findLivingHouseAllActualPopulationInfos",
						condition);
		PageInfo<HouseLivingPopulationInfo> pageResult = createPageInfo(
				pageNum, pageSize, countInteger, resultList);
		return pageResult;
	}
}
