package com.tianque.plugin.orgchange.service;

import org.oproject.framework.orm.PageResult;

import com.tianque.plugin.orgchange.domain.BackupBaseData;
import com.tianque.plugin.orgchange.domain.OrgMapping;

public interface BackupBaseDataService {

	public void addBackupBaseData(OrgMapping OrgMapping);

	/**
	 * 
	 * @Title: findAllBackupInfos
	 * @Description: TODO查询所有备份数据
	 * @param @param backupBaseData
	 * @param @param pageNum
	 * @param @param pageSize
	 * @param @param sidx
	 * @param @param sord
	 * @param @return 设定文件
	 * @return PageResult<BackupBaseData> 返回类型
	 * @author wanggz
	 * @date 2014-10-22 上午10:05:17
	 */
	public PageResult<BackupBaseData> findAllBackupInfos(
			BackupBaseData backupBaseData, Integer pageNum, Integer pageSize,
			String sidx, String sord);

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

	
	public void addBackupBaseDataRelation(BackupBaseData backupBaseData);

}
