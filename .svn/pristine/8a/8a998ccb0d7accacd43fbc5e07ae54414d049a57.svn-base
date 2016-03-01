package com.tianque.openLayersMap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.SysManageTableNameDao;
import com.tianque.openLayersMap.domian.vo.TableNameNoteVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.SysManageTableNameService;

/**
 * 表名管理service实现类(根据表名获得表字段及其注释)
 * 
 * @date 2013-3-15
 * 
 */
@Transactional
@Service("sysManageTableNameService")
public class SysManageTableNameServiceImpl extends BaseService implements
		SysManageTableNameService {
	@Autowired
	private SysManageTableNameDao manageTableNameDao;

	@Override
	public List<TableNameNoteVo> findColumnNameAndComments(String tableName) {
		if (tableName == null) {
			throw new BusinessValidationException("参数错误!");
		}
		return manageTableNameDao.findColumnNameAndComments(tableName
				.toUpperCase());
	}
}
