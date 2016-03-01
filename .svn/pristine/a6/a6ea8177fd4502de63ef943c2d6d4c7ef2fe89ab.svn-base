package com.tianque.openLayersMap.dao;


import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.CommonEntityInfoVo;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

public interface CommonTwoDimensionMapManageDao {
	/**
	 * 修改地图应用的对象的一些字段（主要运用于绑定与解绑）
	 * @param map
	 * @return
	 */
	public Boolean updateDomainByTableName(Map<String, Object> map);
	/**
	 *获取地图应用的对象的某些值（fields）
	 * @param map
	 * @return
	 */
	public Object getDomainByTableNameAndFields(Map<String, Object> map);

	/**
	 * 通过配置信息，屏幕坐标、orgCode查询显示信息
	 * @param gisFunction
	 * @param tableName
	 * @param orgInternalCode
	 * @param orgFiled 
	 * @param screenCoordinateVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<CommonEntityInfoVo> findCommonEntityInfoByGisModuleVoAndOrgCodeAndScreenCoordinate(
			GisFunction gisFunction, String orgInternalCode,
			String orgFiled, ScreenCoordinateVo screenCoordinateVo, String tableName, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 根据orgCode和表名统计绑定数量
	 * @param orgInternalCode
	 * @param tableName
	 * @return
	 */
	public Integer countBoundedTwoDimensionMapByOrgInternalCodeAndTablename(
			String orgInternalCode, String tableName);
	
	/**
	 * 通过配置信息，屏幕坐标、orgCode搜索显示信息
	 * @param gisFunction
	 * @param tableName
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param searchValue
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<CommonEntityInfoVo> findTwoDimensionMapPageInfoByOrgCodeAndScreenCoordinateAndModuleTypeAndSearchValue(
			 GisFunction gisFunction, String orgInternalCode,String orgFiled, ScreenCoordinateVo screenCoordinateVo, String tableName,
			 String searchValue, Integer pageNum, Integer pageSize, 
			 String sidx, String sord);

	/**
	 * 根据orgCode、表名和搜索条件统计绑定数量
	 * @param orgInternalCode
	 * @param tableName
	 * @param searchFieldA
	 * @param searchValueB
	 * @param searchValue
	 * @param functionType
	 * @return
	 */
	public Integer countBoundedTwoDimensionMapByOrgInternalCodeAndTablenameAndSearchValue(
			String orgInternalCode, String tableName, String searchValue,
			String searchFieldA, String searchValueB, String functionType,ScreenCoordinateVo screenCoordinateVo);
	

}
