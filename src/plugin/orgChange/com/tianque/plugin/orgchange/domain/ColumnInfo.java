package com.tianque.plugin.orgchange.domain;

import com.tianque.core.base.BaseDomain;

/**字段信息类*/
public class ColumnInfo extends BaseDomain {
	/**表名*/
	private String tableName;
	/**字段名*/
	private String columnName;
	/**注解*/
	private String comments;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
