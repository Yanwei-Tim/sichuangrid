package com.tianque.openLayersMap.domian.vo;

/**
 * 表字段VO
 * @author zhanghuafei
 *
 */
public class TableNameNoteVo {
	private String tableName;
	private String columnName;
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
