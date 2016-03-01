package com.tianque.openLayersMap.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.BaseDao;
import com.tianque.openLayersMap.dao.SysGisFunctionDao;
import com.tianque.openLayersMap.domian.GisFunction;

/**
 * gis功能(精确搜索、辖区分布、图层搜索)管理dao实现类
 * 
 * @author yubin
 */
@SuppressWarnings("rawtypes")
@Repository("twoDimensionMapFunctionDao")
public class SysGisFunctionDaoImpl extends BaseDao implements SysGisFunctionDao {

	@Override
	public GisFunction addGisFunction(GisFunction gisFunction) {
		if (null == gisFunction || null == gisFunction.getFunctionType()) {
			throw new BusinessValidationException("功能类型不能为空!");
		}
		Long id = (Long) getSqlMapClientTemplate()
				.insert("gisFunction.addGisFunction", gisFunction);
		return getGisFunctionById(id);
	}

	@Override
	public GisFunction getGisFunctionById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("id不能为空!");
		}
		return (GisFunction) getSqlMapClientTemplate().queryForObject(
				"gisFunction.getGisFunctionById", id);
	}

	@Override
	public void deleteGisFunctionByModuleId(Long moduleId) {
		if (null == moduleId) {
			throw new BusinessValidationException("moduleId不能为空!");
		}
		getSqlMapClientTemplate().delete("gisFunction.deleteGisFunctionByModuleId", moduleId);
	}

	@Override
	public void deleteGisFunctionBySonClassId(Long sonClassId) {
		if (null == sonClassId) {
			throw new BusinessValidationException("sonClassId不能为空!");
		}
		getSqlMapClientTemplate().delete("gisFunction.deleteGisFunctionBySonClassId", sonClassId);
	}

	@Override
	public GisFunction updateGisFunction(GisFunction gisFunction) {
		if (null == gisFunction || null == gisFunction.getId()
				|| null == gisFunction.getFunctionType()) {
			throw new BusinessValidationException("id或功能类型不能为空!");
		}
		Long id = (long) getSqlMapClientTemplate().update("gisFunction.updateGisFunction",
				gisFunction);
		return getGisFunctionById(id);
	}

	@Override
	public GisFunction getGisFunctionByModuleIdAndFunctionType(Long moduleId, String functionType) {
		if (null == moduleId || null == functionType) {
			throw new BusinessValidationException("moduleId或功能类型不能为空!");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("moduleId", moduleId);
		map.put("functionType", functionType);
		return (GisFunction) getSqlMapClientTemplate().queryForObject(
				"gisFunction.getGisFunctionByModuleIdAndFunctionType", map);
	}

	@Override
	public GisFunction getGisFunctionBySonClassIdAndFunctionType(Long sonClassId,
			String functionType) {
		if (null == sonClassId || null == functionType) {
			throw new BusinessValidationException("moduleId或功能类型不能为空!");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sonClassId", sonClassId);
		map.put("functionType", functionType);
		return (GisFunction) getSqlMapClientTemplate().queryForObject(
				"gisFunction.getGisFunctionBySonClassIdAndFunctionType", map);
	}

}
