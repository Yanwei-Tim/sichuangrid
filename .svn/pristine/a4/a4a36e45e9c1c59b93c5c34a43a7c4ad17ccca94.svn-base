package com.tianque.gis.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.domain.Layer;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.gis.dao.LayerDao;
import com.tianque.util.ParametersConvertUtil;

@Repository("layerDao")
public class LayerDaoImpl extends AbstractBaseDao implements LayerDao {

	@Override
	public Layer addLayer(Layer layer) {
		Long id = (Long) getSqlMapClientTemplate().insert("layer.addLayer",
				layer);
		return getLayerById(id);
	}

	@Override
	public Layer getLayerById(Long id) {
		return (Layer) getSqlMapClientTemplate().queryForObject(
				"layer.getLayerById", id);
	}

	@Override
	public List<Layer> getLayerByOrganizationId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return getSqlMapClientTemplate().queryForList(
				"layer.getLayerByOrganizationId", orgId);
	}

	@Override
	public void deleteLayer(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		getSqlMapClientTemplate().delete("layer.deleteLayer", id);
	}

	@Override
	public int deleteLayerByOrganizationId(Long orgId) {
		return getSqlMapClientTemplate().delete(
				"layer.deleteLayerByOrganizationId", orgId);
	}

	@Override
	public Layer updateLayer(Layer layer) {
		getSqlMapClientTemplate().update("layer.updateLayerByOrgId", layer);
		return getLayerById(layer.getId());
	}

	@Override
	public String getSuperiorPointsByOrgId(Long parOrgId) {
		Layer layer = (Layer) getSqlMapClientTemplate().queryForObject(
				"layer.getSuperiorPointsByOrgId", parOrgId);
		if (layer != null && layer.getId() != null) {
			return layer.getPoints();
		} else {
			return "";
		}
	}

	@Override
	public List<Layer> getDistrictLayerByOrgCode(List<Long> orgIdList){
		if (orgIdList == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgIdsList", ParametersConvertUtil.convertToListString(orgIdList));
		return getSqlMapClientTemplate().queryForList(
				"layer.getAllDistrictLayerByOrgCode", map);
	}

	@Override
	public Layer getPointersByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return (Layer) getSqlMapClientTemplate().queryForObject(
				"layer.getPointersByOrgId", orgId);
	}

}
