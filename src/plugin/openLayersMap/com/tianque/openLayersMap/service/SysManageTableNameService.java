package com.tianque.openLayersMap.service;


import java.util.List;

import com.tianque.openLayersMap.domian.vo.TableNameNoteVo;
/**
 * 根据表名获得表字段及其注释
 * 
 * @author zhanghuafei
 * @date 2013-3-15
 */
public interface SysManageTableNameService {
	/**
	 * 根据表名获得表字段及其注释（主要应用于类别管理的选择字段）
	 * @param tableName
	 * @author zhanghuafei
	 */
	public List<TableNameNoteVo> findColumnNameAndComments(String tableName);
}
