package com.tianque.plugin.orgchange.service;

import java.util.List;

import org.oproject.framework.orm.PageResult;

import com.tianque.plugin.orgchange.domain.ColumnInfo;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.TableInfo;

/**
 * 功能模块表配置服务接口
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月23日
 */
public interface ModuleTableService {

	// public abstract List<ModuleTable> findModuleTableList(String ename);

	public abstract ModuleTable findModuleTable(Long id);

	public abstract void mergeModuleTable(ModuleTable moduleTable);

	public abstract void deleteModuleTable(Long id);

	public ModuleTable maintainModuleTable(ModuleTable moduleTable);

	/** 表名列表显示 */
	public PageResult<TableInfo> findTableInfos(TableInfo tableInfo,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/** 表字段名列表显示 */
	public PageResult<ColumnInfo> findColumnInfos(ColumnInfo columnInfo,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/** 模块表列表显示 */
	public PageResult<ModuleTable> findModuleTableList(ModuleTable moduleTable,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/** 根据id删除 */
	public int deleteModuleTable(Long count, ModuleTable moduleTable);

	public abstract List<ModuleTable> queryAllForList(boolean b);

	/** 模块禁用 */
	public void stopMpduleTable(Long moduleId, Long permissionId);

	/** 模块启用 */
	public void startMpduleTable(Long moduleId, Long permissionId);
}
