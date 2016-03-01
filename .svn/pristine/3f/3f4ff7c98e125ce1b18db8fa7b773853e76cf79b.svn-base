package com.tianque.openLayersMap.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.CityComponentsInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

public interface CityComponentsTwoDimensionMapDao {
	
	/**
	 * 通过组织机构Code统计二维地图绑定数（主要应用于图层）
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCode(
			String orgInternalCode,String partType,String partName);
	
	/**
	 * 通过组织机构ID和屏幕坐标集分页查询二维地图数据（主要应用于图层）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<CityComponentsInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo, Integer pageNum,
			Integer pageSize, String sidx, String sord,String partType,String partName);
	
	/**
	 * 通过组织机构ID、屏幕坐标集和搜索条件分页查询二维地图数据（主要应用于搜索）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param searchValue
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<CityComponentsInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(String mainTableName,
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo, String searchValue,
			Integer pageNum, Integer pageSize, String sidx, String sord);
	

	/**
	 * 通过组织机构ID、屏幕坐标集和搜索条件统计二维地图绑定数（主要应用于搜索）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param searchValue
	 * @return
	 */
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(String tableName,
			String orgInternalCode, String searchValue);
	
	/**
	 * 二维地图绑定、解除绑定
	 * 
	 * @param cityComponentsInfoVo
	 *            
	 * @return boolean
	 */
	public boolean updateTwoDimensionMap(CityComponentsInfoVo cityComponentsInfoVo);
	
	/**
	 * 根据Id得到详情查看页面
	 * @param id
	 * @return
	 */
	public CityComponentsInfoVo getViewPopupInfoById(Long id);
	
	
	/**
	 * 通过组织机构编码模糊查询某地图区域范围内的部件信息
	 * 
	 * @param orgInternalCode
	 * 			组织机构编码
	 * @param screenCoordinateVo
	 * 			地图某区域最大和最小经纬度
	 * @return List<CityComponentsInfoVo>
	 */
	public List<CityComponentsInfoVo> findCityComponentsInfoVosByOrgCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo);

	/**
	 * 通过组织机构Code获取二维地图数据总数（主要应用于画区域统计）
	 * @param orgInternalCode
	 * @return
	 */
	public Integer statisticTwoDimensionMapInfoSumByOrgInternalCode(
			String orgInternalCode);
		
	/**
	 * 周边搜素
	 */
	public PageInfo<CityComponentsInfoVo> findTwoDimensionMapCircumInfoByRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,String sidx, String sord);
	
}
