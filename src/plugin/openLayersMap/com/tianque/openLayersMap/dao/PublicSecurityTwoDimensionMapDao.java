package com.tianque.openLayersMap.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.PublicSecurityInfoVo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

/**
 * 二维地图 公安部件的dao
 * 
 */
public interface PublicSecurityTwoDimensionMapDao {

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
	 * @return
	 */
	PageInfo<PublicSecurityInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String typeName, Integer pageNum, Integer pageSize, String sidx,
			String sord);

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
	 * @param gisTypeManageList
	 * @return
	 */

	PageInfo<PublicSecurityInfoVo> findTwoDimensionMapPageInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, Integer pageNum, Integer pageSize, String sidx,
			String sord, List list);

	/**
	 * 根据Id和表名得到详情查看popup页面
	 * 
	 * @param id
	 * @param tableName
	 *            子类表名
	 * @return
	 */
	PublicSecurityInfoVo getViewPopupInfoByIdAndTableName(Long id,
			String tableName);

	/**
	 * 通过组织机构Code、屏幕坐标集和类型统计绑定数(主要应用于图层)
	 * 
	 * @param orgInternalCode
	 * @param typeName
	 *            区分下面的子类
	 * @return
	 */
	Integer statisticBoundedTwoDimensionMapInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName);

	/**
	 * 通过组织机构Code、屏幕坐标集和搜索条件统计二维地图数据（主要应用于搜索）
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param searchValue
	 *            搜索条件
	 * @param typeName
	 *            区分下面的子类
	 * @return
	 */
	Integer statisticTwoDimensionMapInfoByOrgInternalCodeAndScreenCoordinateVoAndTypeNameAndSearchValue(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			String searchValue, String typeName);

	/**
	 * 二维地图绑定、解除绑定
	 * 
	 * @param publicSecurityInfoVo
	 * @return
	 */
	boolean updateTwoDimensionMap(PublicSecurityInfoVo publicSecurityInfoVo);

	/**
	 * 通过所选要素和范围查找某点的周边信息
	 * 
	 * @param circumInfoVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<PublicSecurityInfoVo> findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

}
