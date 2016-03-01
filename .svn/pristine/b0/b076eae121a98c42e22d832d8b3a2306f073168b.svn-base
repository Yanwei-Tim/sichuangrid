package com.tianque.openLayersMap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.gis.util.GisGlobalValue;
import com.tianque.openLayersMap.dao.BaseDao;
import com.tianque.openLayersMap.dao.SysGisTypeManageDao;
import com.tianque.openLayersMap.domian.GisTypeManage;

/**
 * 子类类别管理dao实现类
 * 
 * @author yubin
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository("manageTwoDimensionMapDao")
public class SysGisTypeManageDaoImpl extends BaseDao implements
		SysGisTypeManageDao {

	@Override
	public GisTypeManage addGisTypeManage(GisTypeManage domain) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"gisTypeManage.addGisTypeManage", domain);
		return findGisTypeManageById(id);
	}

	@Override
	public Integer countGisTypesByInnerType(GisTypeManage domain) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("innerKey", domain.getInnerKey());
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"gisTypeManage.countGisTypesByInnerType", map);
	}

	@Override
	public Integer countGisTypesByInnerTypeOfNull() {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"gisTypeManage.countGisTypesByInnerTypeOfNull");
	}

	@Override
	public GisTypeManage findGisTypeManageById(Long id) {
		if (id == null)
			return null;
		return (GisTypeManage) getSqlMapClientTemplate().queryForObject(
				"gisTypeManage.findGisTypeManageById", id);

	}

	@Override
	public List<GisTypeManage> findGisTypesByInnerType(GisTypeManage domain,
			int rows, int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("innerKey", domain.getInnerKey());
		List<GisTypeManage> list = getSqlMapClientTemplate().queryForList(
				"gisTypeManage.findGisTypesByInnerType", map,
				(rows - 1) * page, page);
		return list;
	}

	@Override
	public List<GisTypeManage> findGisTypesByInnerTypeOfNull(Integer pageSize,
			Integer pageNum) {
		List<GisTypeManage> list = getSqlMapClientTemplate().queryForList(
				"gisTypeManage.findGisTypesByInnerTypeOfNull",
				(pageNum - 1) * pageSize, pageSize);
		return list;
	}

	@Override
	public GisTypeManage updateGisTypeManage(GisTypeManage domain) {
		getSqlMapClientTemplate().update("gisTypeManage.updateGisTypeManage",
				domain);
		return domain;
	}

	@Override
	public void deleteGisTypeManageById(Long id) {
		getSqlMapClientTemplate().delete(
				"gisTypeManage.deleteGisTypeManageById", id);
	}

	@Override
	public void deleteGisTypeManageByInnerKeyOfNull() {
		getSqlMapClientTemplate().delete(
				"gisTypeManage.deleteGisTypeManageByInnerKeyOfNull");
	}

	@Override
	public List<GisTypeManage> getNeedGisTypeManagesByInnerType(
			GisTypeManage domain) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("innerKey", domain.getInnerKey());
		if (GisGlobalValue.PERSON_MODE.equals(domain.getInnerKey())) {
//			if (ThreadVariable.getOrganization().getOrgLevel().getInternalId() >= OrganizationLevel.CITY) {
				// --------过滤
				map.put("keyType", 1);
//			}
		}
		List<GisTypeManage> list = getSqlMapClientTemplate().queryForList(
				"gisTypeManage.getNeedGisTypeManagesByInnerType", map);
		return list;
	}

	@Override
	public Boolean isHasDuplicateGisTypeManage(GisTypeManage domain) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", domain.getKey());
		map.put("name", domain.getName());
		List<GisTypeManage> list = getSqlMapClientTemplate().queryForList(
				"gisTypeManage.hasDuplicateGisTypeManage", map);
		if (list != null && list.size() > 0) {
			if (list.get(0).getId().equals(domain.getId())) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	public GisTypeManage getGisTypeManagesByKey(GisTypeManage domain) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", domain.getKey());
		return (GisTypeManage) getSqlMapClientTemplate().queryForObject(
				"gisTypeManage.getGisTypeManagesByKey", map);
	}

	@Override
	public GisTypeManage getGisTypeManagesByTableName(GisTypeManage domain) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", domain.getTableName());
		return (GisTypeManage) getSqlMapClientTemplate().queryForObject(
				"gisTypeManage.getGisTypeManagesByTableName", map);
	}

	@Override
	public GisTypeManage getGisTypeManageByTableNameAndKeyType(
			String tableName, String keyType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("keyType", keyType);
		return (GisTypeManage) getSqlMapClientTemplate().queryForObject(
				"gisTypeManage.getGisTypeManageByTableNameAndKeyType", map);
	}
}
