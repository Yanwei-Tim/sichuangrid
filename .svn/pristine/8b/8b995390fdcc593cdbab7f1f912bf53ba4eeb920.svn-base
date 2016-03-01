package com.tianque.openLayersMap.service;


import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

/**
 * 乡镇级别下查询
 * 
 * @date 2013-3-15
 * @param <T>
 */
public interface TownshipsUnderSearchService<T> {

	/**
	 * 通过组织机构ID和类型分页查询二维地图数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @param typeName
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<T>
	 */
	public PageInfo<T> findPageInfoByOrgIdAndTypeName(Long orgId, String typeName, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 通过组织机构ID、屏幕坐标集、类型分页查询二维地图数据（主要应用于图层）
	 * 
	 * @param orgId
	 * @param screenCoordinateVo
	 * @param typeName
	 *            区分下面的子类
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<T>
	 */
	public PageInfo<T> findPageInfoByOrgIdAndScreenCoordinateVoAndTypeName(Long orgId,
			ScreenCoordinateVo screenCoordinateVo, String typeName,String childTableName, Integer pageNum, Integer pageSize, String sidx, String sord);
	
	/**
	 * 通过组织机构ID、屏幕坐标集和搜索条件分页查询二维地图数据（主要应用于搜索）
	 * 
	 * @param orgId
	 * @param screenCoordinateVo
	 * @param searchValue
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<T>
	 */
	public PageInfo<T> findPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue(Long orgId,
			ScreenCoordinateVo screenCoordinateVo, String searchValue, Integer pageNum, Integer pageSize, String sidx, String sord,String mainTableName);
	
	
}
