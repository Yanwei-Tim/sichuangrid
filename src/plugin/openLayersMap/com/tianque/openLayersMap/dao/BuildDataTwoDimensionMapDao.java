package com.tianque.openLayersMap.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

/**
 * 二位地图 楼宇信息Dao
 * 
 * @author zhanghuafei
 * 
 */
public interface BuildDataTwoDimensionMapDao {

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
	public PageInfo<BuildDataInfoVo> findBoundedTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 通过组织机构Code分页查询二维地图未绑定数据
	 * 
	 * @param orgInternalCode
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<BuildDataInfoVo> findUnBoundTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 更新二维地图楼宇数据
	 * 
	 * @param buildDataInfoVo
	 * @return
	 */
	public BuildDataInfoVo updateBuildDataTwoDimensionMap(
			BuildDataInfoVo buildDataInfoVo);

	/**
	 * 通过楼宇id查询楼宇信息
	 * 
	 * @param id
	 * @return
	 */
	public BuildDataInfoVo getBuildDataInfoById(Long id);

	/**
	 * 根据组织机构code统计已绑定楼宇数据（主要应用于辖区分布）
	 * 
	 * @param orgInternalCode
	 * @return Integer
	 */
	public Integer countBoundedTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode);

	/**
	 * 根据组织机构code统计未绑定楼宇数据（主要应用于辖区分布）
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	public Integer countUnBoundedTwoDimensionMapPageInfoByOrgInternalCode(
			String orgInternalCode);

	/**
	 * 根据orgCode和屏幕最大最小坐标模糊查询某地图区域范围内的楼宇集合对象（主要应用于画面统计）
	 * 
	 * @param orgInternalCode
	 * @param maxAndMinLonLatArray
	 * @return
	 */
	public List<BuildDataInfoVo> findBuildDataInfoVosByOrgCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo);

	/**
	 * 根据orgCode统计所有楼宇数量
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	public Integer countBuildDataByOrgCode(String orgInternalCode);

	/**
	 * 通过组织机构查询未绑定楼宇(主要应用于房屋绑定楼宇)
	 * 
	 * @param str
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @param orgInternalCode
	 * @return
	 */
	public PageInfo<BuildDataInfoVo> findUnBoundBuilddatasByStr(String str,
			Integer pageNum, Integer pageSize, String sortField, String order,
			String orgInternalCode);

	/**
	 * 通过组织机构统计二维地图绑定数量（主要应用于搜索）
	 * 
	 * @param orgInternalCode
	 * @return Integer
	 */
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			String orgInternalCode, String searchValue);

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
	public PageInfo<BuildDataInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	/**
	 * 根据建筑物经纬度坐标获取建筑物信息
	 * 
	 * @param centerLon
	 * @param centerLat
	 * @return
	 */
	public BuildDataInfoVo getBuildDataInfoVoByCenterLonLat(String centerLon,
			String centerLat, String gisType);

	/**
	 * 根据建筑物id得到楼宇信息
	 */
	public BuildDataInfoVo getBuildDataInfoByHourseInfoId(Long hourseInfoId);
}
