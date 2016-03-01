package com.tianque.openLayersMap.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.SysTableOperateDao;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.SysTableOperateService;

/**
 * 表名及字段的校验的service实现类
 * 
 * @date 2013-3-15
 * 
 */
@Service("sysTableOperateService")
public class SysTableOperateServiceImpl extends BaseService implements
		SysTableOperateService {
	private static Logger logger = LoggerFactory
			.getLogger(SysTableOperateServiceImpl.class);
	@Autowired
	private SysTableOperateDao tableOperateDao;

	@Override
	public String existTableFieldsFindByTableNameAndFields(String tableName,
			String[] fields) {
		if (tableName == null || fields == null) {
			throw new BusinessValidationException("参数错误");
		}
		boolean result = false;
		boolean error = false;
		String message = "";
		for (int i = 0; i < fields.length; i++) {
			if (fields[i] == null || fields[i].equals(""))
				continue;
			result = existTableFieldFindByTableNameAndField(tableName,
					fields[i]);
			if (!result) {
				message += fields[i] + ", ";
				error = true;
			}
		}
		if (error) {
			message = message.substring(0, message.length() - 2);
			message = "数据库中，表(" + tableName + ")没有字段：" + message;
			logger.error(message);
		} else {
			message = "true";
		}
		return message;
	}

	@Override
	public String existTableFindByTableName(String tableName) {
		if (tableName == null) {
			throw new BusinessValidationException("参数错误");
		}
		boolean result = false;
		String message = "true";
		result = tableOperateDao.existTableFindByTableName(tableName
				.toUpperCase());
		if (!result) {
			message = "数据库中，不存在表：" + tableName;
			logger.error(message);
		}
		return message;
	}

	@Override
	public String addFieldsToTable(String tableName, String fields,
			String fieldsType) {
		if (tableName == null || fields == null || fieldsType == null
				|| fieldsType.equals("")) {
			throw new BusinessValidationException("参数错误");
		}
		boolean exist = existTableFieldFindByTableNameAndField(tableName,
				fields);
		if (!exist) {
			tableOperateDao.addFieldsToTable(tableName, fields, fieldsType);
		}
		return null;
	}

	@Override
	public String dropFieldsFromTable(String tableName, String fields) {
		if (tableName == null || fields == null) {
			throw new BusinessValidationException("参数错误");
		}
		boolean exist = existTableFieldFindByTableNameAndField(tableName,
				fields);
		if (exist) {
			tableOperateDao.dropFieldsFromTable(tableName, fields);
		}
		return null;
	}

	@Override
	public void statementEditSql(String sql) {
		if (sql == null || "".equals(sql)) {
			throw new BusinessValidationException("参数错误");
		}
		tableOperateDao.statementEditSql(sql);
	}

	/**
	 * 根据表名和字段判断表字段是否存在
	 * 
	 * @param tableName
	 *            表名
	 * @param fields
	 *            字段
	 * @return boolean
	 */
	private boolean existTableFieldFindByTableNameAndField(String tableName,
			String fields) {
		boolean exist = tableOperateDao.existTableFieldFindByTableNameAndField(
				tableName.toUpperCase(), fields.toUpperCase());
		return exist;
	}

	@Override
	public void updateLonlatById(Long id, String tableName, String centerLon,
			String centerLat, String centerLon2, String centerLat2) {
		if (id == null || tableName == null || "".equals(tableName)) {
			throw new BusinessValidationException("请选择表" + tableName + "的一条数据");
		}
		tableOperateDao.updateLonlatById(id, tableName, centerLon, centerLat,
				centerLon2, centerLat2);
	}

	@Override
	public void updateLonlatByIdAndType(Long id, String type, String tableName,
			String centerLon, String centerLat, String centerLon2,
			String centerLat2) {
		if (id == null || type == null || tableName == null
				|| "".equals(tableName)) {
			throw new BusinessValidationException("请选择表" + tableName + "的一条数据");
		}
		tableOperateDao.updateLonlatByIdAndType(id, type, tableName, centerLon,
				centerLat, centerLon2, centerLat2);

	}

}
