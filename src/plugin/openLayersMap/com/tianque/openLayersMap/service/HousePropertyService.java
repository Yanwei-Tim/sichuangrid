package com.tianque.openLayersMap.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.HousePropertyInfoVo;

/**
 * 二维地图住房的接口
 * 
 * @date 2013-3-15
 */
public interface HousePropertyService {

	/**
	 * 同步住房信息
	 * 
	 * @param housePropertyInfoVo
	 * @return
	 */
	public void synchronousHousePropertyInfoTwoDimensionMap(
			HousePropertyInfoVo housePropertyInfoVo, String shardCode);

	/**
	 * 根据楼宇id查询房屋信息数量
	 * 
	 * @param buildDataId
	 * @return
	 */
	public Integer countHousePropertyByBuildDataId(Long buildDataId, Long orgId);

	/**
	 * 根据经纬度坐标查询房屋信息
	 * 
	 * @param centerLon
	 * @param centerLat
	 * @return
	 */
	public List<HousePropertyInfoVo> findHousePropertysByCenterLonLat(
			String centerLon, String centerLat, String gisType);

	/**
	 * 通过建筑物ID查询住房列表信息
	 * 
	 * @param orgId
	 * @param buildDataId
	 * @param typeName
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<HousePropertyInfoVo> findTwoDimensionMapPageInfoByBuildingId(
			Long orgId, Long buildDataId, String typeName, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 根据建筑物id得到住房信息
	 * 
	 * @param builddatasId
	 * @return
	 */
	public List<HousePropertyInfoVo> findHousePropertyInfoVoListByBuilddatasId(
			Long builddatasId, Long orgId);
}
