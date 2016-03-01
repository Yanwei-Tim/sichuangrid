package com.tianque.openLayersMap.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.KeyPersonInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

/**
 * 二维地图 重点人员的dao
 * 
 * @author jiangjinling
 * 
 */
public interface KeyPersonTwoDimensionMapDao {
	/**
	 * 通过组织机构Code、屏幕坐标集、类型分页查询二维地图数据（主要应用于图层）
	 * 
	 * @param orgInternalCode
	 *            组织机构code
	 * @param screenCoordinateVo
	 *            当前屏幕的对象
	 * @param typeName
	 *            区分下面的子类
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<KeyPersonInfoVo>
	 */
	public PageInfo<KeyPersonInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeName(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord, String shardCode);

	/**
	 * 通过组织机构Code、屏幕坐标集和类型统计总数
	 * 
	 * @param orgInternalCode
	 * @param typeName
	 *            区分下面的子类
	 * @return Integer
	 */
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName, String shardCode);

	/**
	 * 通过组织机构Code、屏幕坐标集和类型统计绑定数
	 * 
	 * @param orgInternalCode
	 * @param typeName
	 *            区分下面的子类
	 * @return Integer
	 */
	public Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName, String shardCode);

	/**
	 * 通过组织机构Code、屏幕坐标集和搜索条件统计二维地图数据（主要应用于搜索）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param searchValue
	 *            搜索条件
	 * @param typeName
	 *            区分下面的子类
	 * @return Integer
	 */
	public Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, String typeName, String shardCode);

	/**
	 * 通过组织机构Code和类型分页查询二维地图数据（主要应用于辖区分布）
	 * 
	 * @param orgInternalCode
	 * @param typeName
	 *            区分下面的子类
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<KeyPersonInfoVo>
	 */
	public PageInfo<KeyPersonInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName, Integer pageNum,
			Integer pageSize, String sidx, String sord, String shardCode);

	/**
	 * 通过组织机构Code、屏幕坐标集、类型和搜索条件分页查询二维地图数据（主要应用于搜索）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param searchValue
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<KeyPersonInfoVo>
	 */
	public PageInfo<KeyPersonInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, List<GisTypeManage> list, String shardCode);

	/**
	 * 通过所选要素和范围查找某点的周边信息
	 * 
	 * @param circumInfoVo
	 * @return PageInfo<KeyPersonInfoVo>
	 */
	public PageInfo<KeyPersonInfoVo> findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,
			String sidx, String sord, String shardCode);

	/**
	 * 通过组织机构id统计重点人员二维地图数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @param tableName
	 *            子类表名
	 * @return Integer
	 */
	public Integer countTwoDimensionMapPageInfoByOrgId(String orgInternalCode,
			String tableName, String shardCode);

	/**
	 * 通过组织机构Code统计所有重点人员二维地图数据（主要应用于辖区分布）
	 * 
	 * @param orgInternalCode
	 * @return Integer
	 */
	public Integer countGeneralCategoryTwoDimensionMapInfoByOrgInternalCode(
			String orgInternalCode);

	/**
	 * 通过类型和组织机构编码模糊查询某地图区域范围内的重点人员集合对象
	 * 
	 * @param orgCode
	 *            组织机构编码
	 * @param screenCoordinateVo
	 *            地图某区域最大和最小经纬度
	 * @param typeName
	 *            子类类型
	 * @return List<KeyPersonInfoVo>
	 */
	public List<KeyPersonInfoVo> findKeyPersonInfoVosByOrgCodeAndScreenCoordinateVoAndTypeName(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String typeName, String shardCode);

	/**
	 * 根据Id和表名得到详情查看popup页面
	 * 
	 * @param id
	 * @param tableName
	 *            子类表名
	 * @return KeyPersonInfoVo
	 */
	public KeyPersonInfoVo getViewPopupInfoByIdAndTableName(Long id,
			String tableName, String shardCode);
}
