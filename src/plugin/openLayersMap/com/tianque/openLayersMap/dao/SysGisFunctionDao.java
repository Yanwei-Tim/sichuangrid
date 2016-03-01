package com.tianque.openLayersMap.dao;


import com.tianque.openLayersMap.domian.GisFunction;

/**
 * gis功能(精确搜索、辖区分布、图层搜索)管理dao接口
 * 
 * @author yubin
 *
 */
public interface SysGisFunctionDao {

	public GisFunction addGisFunction(GisFunction gisFunction);

	public GisFunction getGisFunctionById(Long id);

	/**
	 * 根据主表id删除GisFunction对象
	 * 
	 * @param moduleId
	 */
	public void deleteGisFunctionByModuleId(Long moduleId);

	/**
	 * 根据子类表id删除 GisFunction对象
	 * 
	 * @param sonClassId
	 */
	public void deleteGisFunctionBySonClassId(Long sonClassId);

	public GisFunction updateGisFunction(GisFunction gisFunction);

	/**
	 * 根据主表id和功能得到gisFunction对象
	 * 
	 * @param moduleId
	 *            主表id
	 * @param functionType
	 *            功能
	 * @return GisFunction
	 */
	public GisFunction getGisFunctionByModuleIdAndFunctionType(Long moduleId,
			String functionType);

	/**
	 * 根据子类id和功能得到gisFunction对象
	 * 
	 * @param sonClassId
	 *            子类id
	 * @param functionType
	 *            功能
	 * @return GisFunction
	 */
	public GisFunction getGisFunctionBySonClassIdAndFunctionType(
			Long sonClassId, String functionType);

}
