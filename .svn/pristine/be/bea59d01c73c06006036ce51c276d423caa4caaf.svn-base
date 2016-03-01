package com.tianque.plugin.orgchange.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.orgchange.domain.ColumnInfo;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.TableInfo;

/**
 * 业务模块配置DAO
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月23日
 */
@DynamicIbatisDAO(value = "ModuleTableDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("ModuleTableDAO")
public interface ModuleTableDAO {

	public abstract Long addModuleTable(ModuleTable moduleTable);

	public abstract int deleteModuleTable(Long id);

	public abstract int updateModuleTable(ModuleTable moduleTable);

	public abstract List<ModuleTable> queryModuleTableForList(String ename);

	public abstract ModuleTable getModueTableById(Long id);

	public abstract List<ModuleTable> queryAllForList(Boolean active);

	public abstract Integer getCount(String ename);

	/** 表名列表显示 */
	public PageResult<TableInfo> queryTableInfosForPageResult(
			Map<String, Object> parameterMap, int pageNum, int pageSize);

	/** 表字段名列表显示 */
	public PageResult<ColumnInfo> queryColumnInfosForPageResult(
			Map<String, Object> parameterMap, int pageNum, int pageSize);

	public PageResult<ModuleTable> queryfindModuleTableForPageResult(
			Map<String, Object> parameterMap, int pageNum, int pageSize);

	/** 模块禁用 */
	public void updateMpduleTable(Map<String, Object> map);

}
