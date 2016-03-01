package com.tianque.plugin.orgchange.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Permission;
import com.tianque.plugin.orgchange.domain.ColumnInfo;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.TableInfo;
import com.tianque.plugin.orgchange.service.ModuleTableService;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 业务模块配置功能
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月23日
 */
@Namespace("/orgchange/moduleTableManage")
@Scope("prototype")
@Controller("moduleTableController")
public class ModuleTableController extends BaseAction {

	@Autowired
	private ModuleTableService moduleTableService;
	@Autowired
	private PermissionService permissionService;
	/** 模块表格对象 */
	private ModuleTable moduleTable;
	/** 表格信息对象 */
	private TableInfo tableInfo;
	/** 字段信息对象 */
	private ColumnInfo columnInfo;
	/** 该权限下一共有的表格数量 */
	private Long count;
	/** 删除的条数 */
	private int deleteCount;
	/** 赋值的字段ID */
	private String column;
	/** 配置的ID */
	private Long moduleId;
	/** 权限ID */
	private Long permissionId;

	/** 业务跳转 */
	@Action(value = "dispatch", results = {
			@Result(name = "maintain", location = "/template/moduleTableManage/moduleTableMaintainDlg.ftl"),
			@Result(name = "selectTableInfo", location = "/template/moduleTableManage/tableInfoSelectDlg.ftl"),
			@Result(name = "selectColumnInfo", location = "/template/moduleTableManage/columnInfoSelectDlg.ftl") })
	public String dispatch() {
		if (DialogMode.SELECT_TABLEINFO.equals(mode)) {
			return DialogMode.SELECT_TABLEINFO;
		} else if (DialogMode.SELECT_COLUMNINFO.equals(mode)) {
			return DialogMode.SELECT_COLUMNINFO;
		}
		Permission permission = permissionService
				.getPermissionByPermissionId(moduleTable.getPermission()
						.getId());
		if (DialogMode.ADD_MODE.equals(mode)) {
			moduleTable.setPermission(permission);
			return DialogMode.OGRANIZATIONS_MAINTAIN;
		} else if (DialogMode.ORGANIZATIONS_UPDATE.equals(mode)) {
			moduleTable = moduleTableService.findModuleTable(moduleTable
					.getId());
			moduleTable.setPermission(permission);
			return DialogMode.OGRANIZATIONS_MAINTAIN;
		}
		return ERROR;
	}

	/** 新增或修改 */
	@PermissionFilters(value = {
			@PermissionFilter(ename = "addModuleTable", actionName = "maintainModuleTable"),
			@PermissionFilter(ename = "updateModuleTable", actionName = "maintainModuleTable") })
	@Action(value = "maintainModuleTable", results = {
			@Result(name = "success", type = "json", params = { "root",
					"moduleTable", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainModuleTable() throws Exception {
		moduleTable = moduleTableService.maintainModuleTable(moduleTable);
		return SUCCESS;
	}

	/** 删除 */
	@PermissionFilter(ename = "deleteModuleTable")
	@Action(value = "deleteModuleTable", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }) })
	public String deleteModuleTable() throws Exception {
		deleteCount = moduleTableService.deleteModuleTable(count, moduleTable);
		return SUCCESS;
	}

	/** 表信息列表 */
	@Action(value = "findTableInfos", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findTableInfos() throws Exception {
		gridPage = new GridPage(moduleTableService.findTableInfos(tableInfo,
				page, rows, sidx, sord));
		return SUCCESS;
	}

	/** 模块表格列表 */
	@Action(value = "findModuleTables", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findModuleTables() throws Exception {
		gridPage = new GridPage(moduleTableService.findModuleTableList(
				moduleTable, page, rows, sidx, sord));
		return SUCCESS;
	}

	/** 字段信息列表 */
	@Action(value = "findColumnInfos", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findColumnInfos() throws Exception {
		gridPage = new GridPage(moduleTableService.findColumnInfos(columnInfo,
				page, rows, sidx, sord));
		return SUCCESS;
	}

	/** 全部禁用 */
	@Action(value = "stopModuleTable", results = { @Result(type = "json", name = "success", params = {
			"root", "true", "ignoreHierarchy", "false" }) })
	public String stopModuleTable() throws Exception {
		moduleTableService.stopMpduleTable(moduleId, permissionId);
		return SUCCESS;
	}

	/** 全部启用 */
	@Action(value = "startModuleTable", results = { @Result(type = "json", name = "success", params = {
			"root", "true", "ignoreHierarchy", "false" }) })
	public String startModuleTable() throws Exception {
		moduleTableService.startMpduleTable(moduleId, permissionId);
		return SUCCESS;
	}

	public void showModuleTable() {

	}

	public String mergeModuleTable() {
		return null;
	}

	public ModuleTable getModuleTable() {
		return moduleTable;
	}

	public void setModuleTable(ModuleTable moduleTable) {
		this.moduleTable = moduleTable;
	}

	public TableInfo getTableInfo() {
		return tableInfo;
	}

	public void setTableInfo(TableInfo tableInfo) {
		this.tableInfo = tableInfo;
	}

	public ColumnInfo getColumnInfo() {
		return columnInfo;
	}

	public void setColumnInfo(ColumnInfo columnInfo) {
		this.columnInfo = columnInfo;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public int getDeleteCount() {
		return deleteCount;
	}

	public void setDeleteCount(int deleteCount) {
		this.deleteCount = deleteCount;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

}
