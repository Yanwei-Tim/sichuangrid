package com.tianque.plugin.weChat.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Permission;

/**模块表格管理domain类*/
public class ModuleTable extends BaseDomain {
	/**id*/
	private Long moduleTableId;
	/**所述的权限菜单模块*/
	private Permission permission;
	/**表名*/
	private String tableName;
	/**组织机构id在该表中的字段名*/
	private String attributeName;
	/**搜索sql语句*/
	private String searchSql;
	/**删除sql语句*/
	private String deleteSql;
	/**该表在该权限菜单模块中的顺序*/
	private Long seq;
	/**是否主表*/
	private Long isMainTable;
	/**-----------------------------暂时无用---------------------------------*/
	/**是否已删除*/
	private Long isDeleted;
	/**创建部门*/
	private Long createDept;
	/**修改部门*/
	private Long updateDept;

	/**每个表中orgcode的业务代表*/
	private String orgcodeBusiness;
	/**执行类型 0 java 1 sql*/
	private Long implementationtype;

	/**------------------------组织机构迁移合并新增-----------------------------*/
	/**begin*/

	/**功能类型 0：组织机构删除功能，1:组织机构迁移合并功能*/
	private Long operateMode;
	/**查询详细内容sql*/
	private String searchDetailSql;

	/**更新sql*/
	private String updateSql;

	/**end*/

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getSearchSql() {
		return searchSql;
	}

	public void setSearchSql(String searchSql) {
		this.searchSql = searchSql;
	}

	public String getDeleteSql() {
		return deleteSql;
	}

	public void setDeleteSql(String deleteSql) {
		this.deleteSql = deleteSql;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Long isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Long getCreateDept() {
		return createDept;
	}

	public void setCreateDept(Long createDept) {
		this.createDept = createDept;
	}

	public Long getUpdateDept() {
		return updateDept;
	}

	public void setUpdateDept(Long updateDept) {
		this.updateDept = updateDept;
	}

	public Long getModuleTableId() {
		return moduleTableId;
	}

	public void setModuleTableId(Long moduleTableId) {
		this.moduleTableId = moduleTableId;
	}

	public Long getIsMainTable() {
		return isMainTable;
	}

	public void setIsMainTable(Long isMainTable) {
		this.isMainTable = isMainTable;
	}

	public Long getOperateMode() {
		return operateMode;
	}

	public void setOperateMode(Long operateMode) {
		this.operateMode = operateMode;
	}

	public String getSearchDetailSql() {
		return searchDetailSql;
	}

	public void setSearchDetailSql(String searchDetailSql) {
		this.searchDetailSql = searchDetailSql;
	}

	public String getUpdateSql() {
		return updateSql;
	}

	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
	}

	public String getOrgcodeBusiness() {
		return orgcodeBusiness;
	}

	public void setOrgcodeBusiness(String orgcodeBusiness) {
		this.orgcodeBusiness = orgcodeBusiness;
	}

	public Long getImplementationtype() {
		return implementationtype;
	}

	public void setImplementationtype(Long implementationtype) {
		this.implementationtype = implementationtype;
	}

}
