package com.tianque.openLayersMap.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.openLayersMap.dao.BaseDao;
import com.tianque.openLayersMap.dao.SysTableOperateDao;

/**
 * 表名及字段的校验的dao实现类
 * 
 * @author yubin
 */
@SuppressWarnings("unchecked")
@Repository("tableOperateDao")
public class SysTableOperateDaoImpl extends BaseDao implements SysTableOperateDao {

	@Override
	public Boolean existTableFieldFindByTableNameAndField(String tableName, String field) {
		boolean result = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("field", field);
		Long number = (Long) getSqlMapClientTemplate().queryForObject(
				"tableOperate.existTableFieldFindByTableNameAndField", map);
		if (number != null && number > 0)
			result = true;
		return result;
	}

	@Override
	public Boolean existTableFindByTableName(String tableName) {
		boolean result = false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		Long number = (Long) getSqlMapClientTemplate().queryForObject(
				"tableOperate.existTableFindByTableName", map);
		if (number != null && number > 0)
			result = true;
		return result;
	}

	@Transactional(propagation = Propagation.NESTED)
	@Override
	public void addFieldsToTable(String tableName, String fields, String fieldsType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("fields", fields);
		map.put("fieldsType", fieldsType);
		getSqlMapClientTemplate().update("tableOperate.addFieldsToTable");
	}

	@Override
	public void dropFieldsFromTable(String tableName, String fields) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("fields", fields);
		getSqlMapClientTemplate().update("tableOperate.dropFieldsFromTable", map);
	}

	@Override
	public void statementEditSql(String sql) {
		getSqlMapClientTemplate().update("tableOperate.statementEditSql",
				sql);
	}

	@Override
	public void updateLonlatById(Long id, String tableName, String centerLon,
		String centerLat, String centerLon2, String centerLat2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("tableName", tableName);
		map.put("centerLon", centerLon);
		map.put("centerLat", centerLat);
		map.put("centerLon2", centerLon2);
		map.put("centerLat2", centerLat2);
		getSqlMapClientTemplate().update("tableOperate.updateLonlat", map);
	}

	@Override
	public void updateLonlatByIdAndType(Long id, String type, String tableName,
		String centerLon, String centerLat, String centerLon2,
		String centerLat2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		map.put("tableName", tableName);
		map.put("centerLon", centerLon);
		map.put("centerLat", centerLat);
		map.put("centerLon2", centerLon2);
		map.put("centerLat2", centerLat2);
		getSqlMapClientTemplate().update("tableOperate.updateLonlat", map);
	}

}
