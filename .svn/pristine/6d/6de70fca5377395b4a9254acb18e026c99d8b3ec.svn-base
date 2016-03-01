package com.tianque.gis.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.vo.HouseLivingTotalInfo;
import com.tianque.gis.domain.vo.BuildingInfoVo;
import com.tianque.gis.domain.vo.GisCountNumVo;
import com.tianque.gis.domain.vo.HouseInfoVo;
import com.tianque.gis.domain.vo.RoundBuildingInfo;

public interface SearchGisHouseInfoService {

	PageInfo<HouseInfoVo> getPageInfoByQueryStrForSearchHouseInfo(Long orgId, String queryStr,
			Integer page, Integer rows, String sidx, String sord);

	public List<GisCountNumVo> countActualPopulationByOrgIdAndActulaType(Long orgId);

	public List<GisCountNumVo> countActualHouseByOrgId(Long orgId);

	public List<HouseLivingTotalInfo> countActualPopulationByParentOrgIdAndActulaType(Long orgId,
			String actulaType);

	PageInfo<HouseInfoVo> searchkeyHouseInfoListByorgId(Long orgId, String houseType, Integer page,
			Integer rows, String sidx, String sord);

	public List<HouseInfoVo> findAllBindingHouseInfoBy(final Long orgId);

	public BuildingInfoVo getBuildingInfoVoByBuildingId(final Long buildingId);

	public RoundBuildingInfo getRoundHouseInfo(GisInfo minOption, GisInfo maxOption);
}
