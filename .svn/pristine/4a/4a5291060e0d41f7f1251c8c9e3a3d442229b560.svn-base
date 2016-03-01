package com.tianque.openLayersMap.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

/**
 * 二维地图 重点场所的dao
 * 
 * @author jiangjinling
 * 
 */
public interface KeyPlaceTwoDimensionMapDao {
	/**
	 * 通过组织机构ID和类型分页查询二维地图数据（主要应用于辖区分布）
	 * 
	 * @param orgInternalCode
	 * @param typeName
	 *            区分下面的子类
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<KeyPlaceInfoVo>
	 */
	public PageInfo<KeyPlaceInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 通过组织机构ID、屏幕坐标集、类型分页查询二维地图数据（主要应用于图层）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param typeName
	 *            区分下面的子类
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<KeyPlaceInfoVo>
	 */
	public PageInfo<KeyPlaceInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	/**
	 * 通过组织机构Code和类型统计绑定数
	 * 
	 * @param orgInternalCode
	 * @param searchValue
	 *            区分下面的子类
	 * @return Integer
	 */
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndSearchValue(
			String orgInternalCode, String searchValue, List<String> typeList);

	/**
	 * 通过组织机构ID、屏幕坐标集、类型和搜索条件分页查询二维地图数据（主要应用于搜索）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param searchValue
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<KeyPlaceInfoVo>
	 */
	public PageInfo<KeyPlaceInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			List<String> typeList, String searchValue, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 通过组织机构code和类型统计二维地图绑定数量（主要应用于图层）
	 * 
	 * @param orgInternalCode
	 * @param typeName
	 *            区分下面的子类
	 * @return Integer
	 */
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName(
			String orgInternalCode, String typeName);

	/**
	 * 二维地图绑定、解除绑定
	 * 
	 * @param keyPlaceInfoVo
	 * 
	 * @return boolean
	 */
	public boolean updateTwoDimensionMap(KeyPlaceInfoVo infoVo);

	/**
	 * 通过所选要素和范围查找某点的周边信息
	 * 
	 * @param circumInfoVo
	 * @return List<DetailsListInfoVo>
	 */
	public PageInfo<KeyPlaceInfoVo> findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	/**
	 * 通过组织机构code和场所类型统计场所数据（主要应用于辖区分布）
	 * 
	 * @param orgInternalCode
	 * @param typeName
	 * @return Integer
	 */
	public Integer countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName);

	/**
	 * 通过组织机构Code和类型统计总数
	 * 
	 * @param orgInternalCode
	 * @param typeName
	 *            区分下面的子类
	 * @return Integer
	 */
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName);

	/**
	 * 通过组织机构编码模糊查询某地图区域范围内的重点场所集合对象
	 * 
	 * @param orgCode
	 *            组织机构编码
	 * @param screenCoordinateVo
	 *            地图某区域最大和最小经纬度
	 * @return List<keyPlaceInfoVo>
	 */
	public List<KeyPlaceInfoVo> findKeyPlaceInfoVosByOrgCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo);

	/**
	 * 通过组织机构编码模糊查询某地图区域范围内的重点场所集合对象
	 * 
	 * @param orgCode
	 *            组织机构编码
	 * @param maxAndMinLonLatArrys
	 *            地图某区域最大和最小经纬度
	 * @param type
	 *            查询的类型
	 * @return List<KeyPlaceInfoVo>
	 */
	public List<KeyPlaceInfoVo> findKeyPlaceInfoVosByOrgCodeAndMaxAndMinLonLatArrys(
			String orgCode, Double[] maxAndMinLonLatArrys, String type);

	/**
	 * 通过组织机构ID和类型分页查询重点场所数据
	 * 
	 * @param orgInternalCode
	 * @param type
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param queryStr
	 * @return PageInfo<KeyPlaceInfoVo>
	 */
	public PageInfo<KeyPlaceInfoVo> findUnBoundKeyPlaceByOrgInternalCodeAndType(
			String orgInternalCode, String type, Integer page, Integer rows,
			String sidx, String sord, String queryStr);

	/**
	 * 根据id和类型得到详情查看popup页面
	 * 
	 * @param id
	 * @param type
	 * @return KeyPlaceInfoVo
	 */
	public KeyPlaceInfoVo getViewPopupInfoByIdAndType(Long id, String type);

	/**
	 * 根据经纬度坐标获取各类场所数量
	 * 
	 * @param centerLon
	 * @param centerLat
	 * @return
	 */
	public Map<String, Integer> countKeyPlacesByCenterLonLat(String centerLon,
			String centerLat, String gisType);

	/**
	 * 通过组织机构ID和类型分页查询已绑定重点场所数据
	 * 
	 * @param orgInternalCode
	 * @param typeName
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param queryStr
	 * @return PageInfo<KeyPlaceInfoVo>
	 */
	public PageInfo<KeyPlaceInfoVo> findBoundKeyPlaceByOrgInternalCodeAndTypeName(
			String orgInternalCode, Long buildDataId, String typeName,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 根据建筑物id统计场所数量
	 * 
	 * @param hourseId
	 * @return
	 */
	public Map<String, Integer> countKeyPlacesByHourseId(Long hourseId);

}
