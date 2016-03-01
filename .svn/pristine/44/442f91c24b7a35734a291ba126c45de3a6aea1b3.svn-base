package com.tianque.openLayersMap.dao;

/**
 * 表名及字段的校验的dao接口
 * 
 * @author yubin
 * 
 */
public interface SysTableOperateDao {
	/** 根据表名 判断数据库中是否存在此表 */
	public Boolean existTableFindByTableName(String tableName);

	/** 根据表名和字段 判断数据库中 表（tableName）是否存在此字段 */
	public Boolean existTableFieldFindByTableNameAndField(String tableName,
			String fields);

	/** 向表添加字段 */
	public void addFieldsToTable(String tableName, String fields,
			String fieldsType);

	/** 从表删除字段 */
	public void dropFieldsFromTable(String tableName, String fields);

	/** 执行编辑的sql字符串 */
	public void statementEditSql(String sql);

	public void updateLonlatById(Long id, String tableName, String centerLon,
			String centerLat, String centerLon2, String centerLat2);

	public void updateLonlatByIdAndType(Long id, String type, String tableName,
			String centerLon, String centerLat, String centerLon2,
			String centerLat2);
}
