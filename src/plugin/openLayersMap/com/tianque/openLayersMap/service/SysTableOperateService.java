package com.tianque.openLayersMap.service;

/**
 * 表名及字段的校验的service接口
 * 
 * @date 2013-3-15
 * 
 */
public interface SysTableOperateService {
	/** 根据表名 判断数据库中是否存在此表 */
	public String existTableFindByTableName(String tableName);

	/** 根据表名和字段集合 判断数据库中 表（tableName）是否存在这些字段 */
	public String existTableFieldsFindByTableNameAndFields(String tableName,
			String[] fields);

	/** 将字段添加到表 */
	public String addFieldsToTable(String tableName, String fields,
			String fieldsType);

	/** 删除表字段 */
	public String dropFieldsFromTable(String tableName, String fields);

	/**
	 * 执行编辑SQL字符串
	 */
	public void statementEditSql(String sql);

	public void updateLonlatById(Long id, String tableName, String centerLon,
			String centerLat, String centerLon2, String centerLat2);

	public void updateLonlatByIdAndType(Long id, String type, String tableName,
			String centerLon, String centerLat, String centerLon2,
			String centerLat2);
}
