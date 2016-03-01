package com.tianque.plugin.orgchange.dao;

import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.orgchange.domain.BackupBaseData;
import com.tianque.plugin.orgchange.domain.OrgMapping;

/**
 * 迁移合并删除数据备份
 * 
 * @author 王超
 * 
 */
@DynamicIbatisDAO(value = "BackupBaseDataDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("BackupBaseDataDAO")
public interface BackupBaseDataDAO {

	/***
	 * 新增数据备份信息
	 * 
	 * @param backupBaseData
	 */
	public void addBackupBaseData(OrgMapping orgMapping);

	/**
	 * 
	 * @Title: queryBackupInfoForPageResult
	 * @Description: TODO查询所有备份数据
	 * @param @param parameterMap
	 * @param @param pageNum
	 * @param @param pageSize
	 * @param @return 设定文件
	 * @return PageResult<BackupBaseData> 返回类型
	 * @author wanggz
	 * @date 2014-10-22 上午10:03:06
	 */
	public PageResult<BackupBaseData> queryBackupInfoForPageResult(
			Map<String, Object> parameterMap, int pageNum, int pageSize);

	/**
	 * 
	 * @Title: getBackupInfoById
	 * @Description: TODO根据ID查询备份信息
	 * @param @param id
	 * @param @return 设定文件
	 * @return BackupBaseData 返回类型
	 * @author wanggz
	 * @date 2014-10-22 上午11:29:12
	 */
	public BackupBaseData getBackupInfoById(Long id);
	/***
	 * 已对象备份数据
	 * 
	 * @param addBackupBaseDataRelation
	 */
	public void addBackupBaseDataRelation(BackupBaseData backupBaseData);
}
