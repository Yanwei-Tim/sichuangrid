package com.tianque.openLayersMap.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.openLayersMap.dao.BaseDao;
import com.tianque.openLayersMap.dao.SysManageTableNameDao;
import com.tianque.openLayersMap.domian.vo.TableNameNoteVo;

/**
 * 表名管理dao实现类
 * 
 * @author yubin
 *
 */
@SuppressWarnings("rawtypes")
@Repository("manageTableNameDao")
public class SysManageTableNameDaoIpml extends BaseDao implements SysManageTableNameDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<TableNameNoteVo> findColumnNameAndComments(String tableName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tableName", tableName);
		List<TableNameNoteVo> list = (List<TableNameNoteVo>)getSqlMapClientTemplate().queryForList("tableNameNote.findColumnNameAndComments", map);
		return list;
	}

}
