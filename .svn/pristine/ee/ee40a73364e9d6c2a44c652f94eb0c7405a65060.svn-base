package com.tianque.openLayersMap.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.AbstractDao;
import com.tianque.openLayersMap.dao.Gis2DLayerDao;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;

/**
 * 地图区域管理dao实现类
 */
@Repository("gis2DLayerDao")
public class Gis2DLayerDaoImpl extends AbstractDao<Gis2DLayer> implements
		Gis2DLayerDao {

	@Override
	public Gis2DLayer addGis2DLayer(Gis2DLayer gis2DLayer) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"gis2DLayer.addGis2DLayer", gis2DLayer);
		return getById(id,gis2DLayer.getGisType());
	}

	@Override
	public Gis2DLayer updateGis2DLayer(Gis2DLayer gis2DLayer) {
		getSqlMapClientTemplate().update("gis2DLayer.updateGis2DLayer",
				gis2DLayer);
		return getById(gis2DLayer.getId(),gis2DLayer.getGisType());
	}

	@Override
	public void deleteByOrgCode(String orgCode) {
		if (!StringUtil.isStringAvaliable(orgCode)) {
			throw new BusinessValidationException("组织机构为空");
		}
		getSqlMapClientTemplate().delete(
				"gis2DLayer.deleteByOrgCode", orgCode);
	}
	@Override
	public void deleteByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构为空");
		}
		getSqlMapClientTemplate().delete(
				"gis2DLayer.deleteByOrgId", orgId);
	}
	@Override
	public void deleteById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("ID为空");
		}
		getSqlMapClientTemplate().delete("gis2DLayer.deleteById", id);
	}
	
	@Override
	public Gis2DLayer getByOrgId(Long orgId, String gisType) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("gisType", gisType);
		return (Gis2DLayer) getSqlMapClientTemplate().queryForObject(
				"gis2DLayer.getByOrgId", map);
	}
	
	@Override
	public Gis2DLayer getById(Long id, String gisType) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("gisType", gisType);
		return (Gis2DLayer) getSqlMapClientTemplate().queryForObject(
				"gis2DLayer.getById", map);
	}

	@Override
	public List<Gis2DLayer> findByCenterPointAndZoom(Gis2DLayer domian, String gisType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("centerX", domian.getCenterX());
		map.put("centerY", domian.getCenterY());
		map.put("zoom", domian.getZoom());
		map.put("gisType", domian.getGisType());
		return getSqlMapClientTemplate().queryForList(
				"gis2DLayer.findByCenterPointAndZoom", map);
	}

	@Override
	public Integer countByOrgCodeAndOrgLevel(SearchInfoVo searchVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", searchVo.getOrgInternalCode());
		map.put("orgIdsList", searchVo.getOrgIdsList());
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"gis2DLayer.countByOrgCodeAndOrgLevel",
						map);
	}

	@Override
	public PageInfo<Gis2DLayer> findForPageBySearchVo(SearchInfoVo searchVo,Integer page, Integer rows, String sidx, String sord){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchVo", searchVo);
		map.put("screenVo", searchVo.getScreenVo());
		map.put("gisType", searchVo.getGisType());
		return getPageInfo(
				"gis2DLayer.countBySearchVo",
				"gis2DLayer.findBySearchVo",
				page, rows, map);
	}

	@Override
	public List<Gis2DLayer> findUndersBySearchVo(SearchInfoVo searchVo, String gisType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgIdsList", searchVo.getOrgIdsList());
		map.put("gisType", gisType);
		if(searchVo.getScreenVo()!=null){
			map.put("screenVo", searchVo.getScreenVo());
		}
		return getSqlMapClientTemplate().queryForList(
				"gis2DLayer.findUndersByOrgIdAndScreenVo", map);
	}

	@Override
	public List<Gis2DLayer> findByOrgCode(String orgCode, String gisType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgCode);
		map.put("gisType", gisType);
		return getSqlMapClientTemplate().queryForList(
				"gis2DLayer.findByOrgCode", map);
	}
}
