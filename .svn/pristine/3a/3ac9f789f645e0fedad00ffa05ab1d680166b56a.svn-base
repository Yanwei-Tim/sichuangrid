package com.tianque.openLayersMap.service;

import java.util.List;

import com.tianque.openLayersMap.domian.CommonEntityInfoVo;
import com.tianque.openLayersMap.domian.GisModuleVo;

/**
 * 大类类别管理接口
 * 
 * @author yubin
 * 
 */
public interface SysModuleManageService {
	/**
	 * 添加模块
	 * 
	 * @param GisModuleVo
	 */
	public GisModuleVo addModule(GisModuleVo gisModuleVo);

	/**
	 * 根据id删除模块
	 * 
	 * @param id
	 */
	public void deleteModuleById(Long id);

	/**
	 * 修改模块
	 * 
	 * @param commonEntityInfoVo
	 * @return GisModuleVo
	 */
	public GisModuleVo updateModule(GisModuleVo gisModuleVo);

	/**
	 * 根据id得到指定模块信息
	 * 
	 * @param id
	 * @return GisModuleVo
	 */
	public GisModuleVo getModuleById(Long id);

	/**
	 * 查询所有模块信息
	 * 
	 * @return List<GisModuleVo>
	 */
	public List<GisModuleVo> findAllModule();

	/**
	 * 根据tableName是否存在
	 * 
	 * @param tableName
	 * @return Boolean
	 */
	public Boolean isExistTableName(String tableName);

	public GisModuleVo getModuleByTableName(String tableName);

	/**
	 * 得到搜索栏显示列表
	 * 
	 * @return List<GisModuleVo>
	 */
	public List<CommonEntityInfoVo> getGisModuleByIssearch();

	/**
	 * 根据modeType得到List<GisModuleVo>对象
	 * 
	 * @param modeType
	 *            针对本系统特殊业务处理的值
	 * @return List<GisModuleVo>
	 */
	public List<GisModuleVo> getGisModuleByModeType(String modeType);
}
