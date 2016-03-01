package com.tianque.gis.dao;

import java.util.List;

import com.tianque.domain.Layer;

public interface LayerDao {

	Layer addLayer(Layer layer);

	Layer getLayerById(Long id);

	List<Layer> getLayerByOrganizationId(Long orgId);

	void deleteLayer(Long id);

	int deleteLayerByOrganizationId(Long orgId);

	Layer updateLayer(Layer layer);

	String getSuperiorPointsByOrgId(Long parOrgId);

	public List<Layer> getDistrictLayerByOrgCode(List<Long> orgIdList);

	public Layer getPointersByOrgId(Long orgId);

}
