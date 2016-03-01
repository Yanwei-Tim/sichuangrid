package com.tianque.openLayersMap.dao;


import java.util.List;

import com.tianque.openLayersMap.domian.CommonEntityInfoVo;
import com.tianque.openLayersMap.domian.GisModuleVo;

/**
 * 大类类别管理dao接口
 * 
 * @author yubin
 *
 */
public interface SysModuleManageDao {

	public GisModuleVo addModule(GisModuleVo gisModuleVo);

	public void deleteModuleById(Long id);

	public GisModuleVo updateModule(GisModuleVo gisModuleVo);

	public GisModuleVo getModuleById(Long id);

	/**
	 * 查询所有模块信息
	 * 
	 * @return List<GisModuleVo>
	 */
	public List<GisModuleVo> findAllModule();

	public GisModuleVo getModuleByTableName(String tableName);

	/**
	 * 得到搜索栏显示列表
	 * 
	 * @return List<CommonEntityInfoVo>
	 */
	public List<CommonEntityInfoVo> getGisModuleByIssearch();

	public GisModuleVo getGisModuleByModeType(String modeType);
}
