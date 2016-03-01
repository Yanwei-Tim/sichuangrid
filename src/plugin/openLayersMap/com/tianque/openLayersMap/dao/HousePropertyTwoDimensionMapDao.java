package com.tianque.openLayersMap.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.HousePropertyInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

/**
 * 实有房屋Dao
 * 
 * @author zhanghuafei
 * 
 */
public interface HousePropertyTwoDimensionMapDao {
	/**
	 * 通过组织机构Code分页查询二维地图已绑定数据（主要应用于辖区分布）
	 * 
	 * @param orgInternalCode
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<HousePropertyInfoVo> findBoundedTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 通过组织机构Code和屏幕坐标集分页查询二维地图数据（主要应用于图层）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<HousePropertyInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			int isRentalHouse, String shardCode);

	/**
	 * 通过组织机构统计二维地图绑定数量（主要应用于图层）
	 * 
	 * @param orgInternalCode
	 * @return Integer
	 */
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
			String orgInternalCode, String typeName, Boolean isBound,
			String shardCode);

	/**
	 * 通过组织机构Code、屏幕坐标集和搜索条件分页查询二维地图数据（主要应用于搜索）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param searchValue
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<HousePropertyInfoVo>
	 */
	public PageInfo<HousePropertyInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, String shardCode);

	/**
	 * 通过组织机构统计二维地图绑定数量（主要应用于搜索）
	 * 
	 * @param orgInternalCode
	 * @return Integer
	 */
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			String orgInternalCode, String searchValue, String shardCode);

	/**
	 * 通过buildingId查询二维地图绑定数据（主要应用于辖区分布）
	 * 
	 * @param buildingId
	 * @return Integer
	 */
	public Integer statisticBoundedTwoDimensionMapInfoByBuildingId(
			Long buildingId);

	/**
	 * 通过楼宇id查询住房二维地图数据
	 * 
	 * @param buildingId
	 * @return
	 */
	public List<HousePropertyInfoVo> findTwoDimensionMapByBuildingId(
			Long buildingId, String shardCode);

	/**
	 * 更新二维地图数据
	 * 
	 * @param housePropertyInfoVo
	 * @return
	 */
	public void updateHousePropertyInfoVoTwoDimensionMap(
			HousePropertyInfoVo housePropertyInfoVo, String shardCode);

	/**
	 * 根据Id得到HousePropertyInfoVo对象
	 * 
	 * @param id
	 * @return HousePropertyInfoVo
	 */
	public HousePropertyInfoVo getHousePropertyInfoById(Long id,
			String shardCode);

	/**
	 * 通过组织机构code统计已绑定住房数据（主要应用于辖区分布）
	 * 
	 * @param orgInternalCode
	 * @return Integer
	 */
	public Integer countBoundedTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, String shardCode);

	/**
	 * 通过组织机构code统计未绑定住房数据（主要应用于辖区分布）
	 * 
	 * @param orgInternalCode
	 * @return Integer
	 */
	public Integer countUnBoundedTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, String shardCode);

	/**
	 * 根据orgCode和屏幕最大最小坐标模糊查询某地图区域范围内的住房集合对象（主要应用于画面统计）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @return
	 */
	public List<HousePropertyInfoVo> findHousePropertyInfoVosByOrgCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String shardCode);

	/**
	 * 根据orgCode统计所以住房数量
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	public Integer countHousePropertyByOrgCode(String orgInternalCode,
			String shardCode);

	/**
	 * 通过组织机构Code分页查询二维地图未绑的数据（主要应用于辖区分布）
	 * 
	 * @param orgInternalCode
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<HousePropertyInfoVo> findUnBoundedTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 通过楼宇id查询住房数量
	 * 
	 * @param centerLon
	 * @param centerLat
	 * @return
	 */
	public Integer countHousePropertyByBuildDataId(Long buildDataId,
			String shardCode);

	public PageInfo<HousePropertyInfoVo> findHouseInfoTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, String typeName, String shardCode);

	/**
	 * 通过经纬度坐标获取房屋集合
	 * 
	 * @param centerLon
	 * @param centerLat
	 * @return
	 */
	public List<HousePropertyInfoVo> findHousePropertysByCenterLonLat(
			String centerLon, String centerLat, String gisType);

	/**
	 * 通过buildingId分页查询已绑定的住房
	 * 
	 * @param buildingId
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<HousePropertyInfoVo> findTwoDimensionMapPageInfoByBuildingId(
			Long buildingId, Integer pageNum, Integer pageSize, String sidx,
			String sord, String shardCode);
}
