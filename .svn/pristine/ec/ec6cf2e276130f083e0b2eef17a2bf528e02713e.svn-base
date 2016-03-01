package com.tianque.openLayersMap.service;


import com.tianque.openLayersMap.domian.GisFunction;

/**
 * gis功能(精确搜索、辖区分布、图层搜索)管理service接口
 * 
 * @date 2013-3-15
 * 
 */
public interface SysGisFunctionService {

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
	 * 根据父类id和功能类型得到功能对象
	 * 
	 * @param moduleId
	 *            父类id
	 * @param functionType
	 *            功能类型
	 * @return GisFunction
	 */
	public GisFunction getGisFunctionByModuleIdAndFunctionType(Long moduleId, String functionType);

	/**
	 * 根据子类id和功能类型得到功能类型
	 * 
	 * @param sonClassId
	 *            子类id
	 * @param functionType
	 *            功能类型
	 * @return GisFunction
	 */
	public GisFunction getGisFunctionBySonClassIdAndFunctionType(Long sonClassId, String functionType);

	/**
	 * 添加功能
	 * 
	 * @param gisFunction
	 *            功能对象
	 * @param moduleId
	 *            大类id
	 * @param sonClassId
	 *            子类id
	 * @param functionType
	 *            功能类型
	 */
	public void addFunction(GisFunction gisFunction, Long moduleId, Long sonClassId);

	/**
	 * 修改功能对象
	 * 
	 * @param gisFunction
	 *            功能对象
	 * @param moduleId
	 *            父类id
	 * @param sonClassId
	 *            子类id
	 * @param functionType
	 *            功能类型
	 */
	public void updateFunction(GisFunction gisFunction, Long moduleId, Long sonClassId);

}
