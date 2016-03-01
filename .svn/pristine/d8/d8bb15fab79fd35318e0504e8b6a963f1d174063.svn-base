package com.tianque.openLayersMap.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.openLayersMap.dao.BaseDao;
import com.tianque.openLayersMap.dao.SysModuleManageDao;
import com.tianque.openLayersMap.domian.CommonEntityInfoVo;
import com.tianque.openLayersMap.domian.GisModuleVo;

/**
 * 大类类别管理dao实现类
 * 
 * @author yubin
 *
 */
@SuppressWarnings("rawtypes")
@Repository("twoDimensionMapModuleManageDao")
public class SysModuleManageDaoImpl extends BaseDao implements
		SysModuleManageDao {

	@Override
	public GisModuleVo addModule(GisModuleVo gisModuleVo) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"sysGisModule.addModule", gisModuleVo);
		return getModuleById(id);
	}

	@Override
	public void deleteModuleById(Long id) {
		getSqlMapClientTemplate()
				.delete("sysGisModule.deleteModuleById", id);

	}

	@Override
	public GisModuleVo updateModule(GisModuleVo gisModuleVo) {
		getSqlMapClientTemplate().update("sysGisModule.updateModule",
				gisModuleVo);
		return gisModuleVo;
	}

	@Override
	public GisModuleVo getModuleById(Long id) {
		return (GisModuleVo) getSqlMapClientTemplate().queryForObject(
				"sysGisModule.getModuleById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GisModuleVo> findAllModule() {
		return getSqlMapClientTemplate().queryForList(
				"sysGisModule.findModuleForPage");
	}

	@Override
	public GisModuleVo getModuleByTableName(String tableName) {
		return (GisModuleVo) getSqlMapClientTemplate().queryForObject(
				"sysGisModule.getModuleByTableName", tableName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommonEntityInfoVo> getGisModuleByIssearch() {
		return getSqlMapClientTemplate().queryForList(
				"sysGisModule.getGisModuleByIsBusinessLayerAndPopulation");
	}

	@Override
	public GisModuleVo getGisModuleByModeType(String modeType) {
		return (GisModuleVo) getSqlMapClientTemplate().queryForObject(
				"sysGisModule.getGisModuleByModeType", modeType);
	}

}
