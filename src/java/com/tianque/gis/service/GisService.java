package com.tianque.gis.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.gis.domain.vo.HouseInfoVo;
import com.tianque.gis.domain.vo.LocationVo;
import com.tianque.gis.domain.vo.PopulationVo;

public interface GisService {

	public HouseInfoVo getHouseById(Long id);

	public HouseInfoVo getKeyPlaceByIdAndType(Long id, String type);

	GisInfo boundGisToLocation(LocationVo locationVo);

	Boolean unboundGisToLocation(LocationVo locationVo);

	public PopulationVo bindPersonOnMap(PopulationVo populationVo);

	public PageInfo<PopulationVo> searchPersonByName(Long orgId, String queryStr, Integer page,
			Integer rows, String sidx, String sord);

	public PageInfo<PopulationVo> searchActulaPersonByName(Long orgId, Integer page, Integer rows,
			String sidx, String sord);

	// public PageInfo<PopulationVo> getPopulationByHouseId(Long id, Integer page, Integer rows,
	// String sidx, String sord);

	public HouseInfoVo getHouseInfoByHouseId(Long houseId);

	public PopulationVo unBindPersonOnMap(PopulationVo populationVo);

	public PopulationVo bindPartyWorkOnMap(PopulationVo populationVo);

	public PopulationVo bindAcualCompanyOnMap(PopulationVo populationVo);

	PopulationVo unBindOrgOnMap(PopulationVo populationVo);

	PageInfo<PopulationVo> getPopulationInfosByHouseId(Long houseId, Integer page, Integer rows,
			String sidx, String sord);

	PopulationVo unBindActualCompanyOnMap(PopulationVo populationVo);

	PopulationVo bindIssueNewsOnMap(PopulationVo populationVo);

	PopulationVo unBindIssueNewsOnMap(PopulationVo populationVo);

	public void bindHouseOnMap(Long houseId, GisInfo gisInfo);

	void unBindHouseOnMap(Long houseId);

	void bindIssueOnMap(Long issueId, GisInfo gisInfo);

	void unBindIssueOnMap(Long issueId);

	void bindActualUnitOnMap(Long unitId, GisInfo gisInfo);

	void unBindActualUnitOnMap(Long unitId);
}
