package com.tianque.plugin.orgchange.service;

import java.util.HashMap;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.orgchange.dao.BackupBaseDataDAO;
import com.tianque.plugin.orgchange.domain.BackupBaseData;
import com.tianque.plugin.orgchange.domain.OrgMapping;

@Transactional
@Service("backupBaseDataService")
public class BackupBaseDataServiceImpl implements BackupBaseDataService {
	@Autowired
	private BackupBaseDataDAO backupBaseDataDAO;

	@Override
	public void addBackupBaseData(OrgMapping orgMapping) {
		backupBaseDataDAO.addBackupBaseData(orgMapping);
	}

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
	@Override
	public PageResult<BackupBaseData> findAllBackupInfos(
			BackupBaseData backupBaseData, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sortField", sidx);
			map.put("order", sord);
			PageResult<BackupBaseData> backupInfo = backupBaseDataDAO
					.queryBackupInfoForPageResult(map, pageNum, pageSize);
			return backupInfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类BackupBaseDataServiceImpl的findAllBackupInfos查询方法出错",
					"查询备份数据信息报错", e);
		}
	}

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
	public BackupBaseData getBackupInfoById(Long id) {
		try {
			return backupBaseDataDAO.getBackupInfoById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类BackupBaseDataServiceImpl的getBackupInfoById查询方法出错",
					"查询备份数据信息报错", e);
		}
	}
	@Override
	public void addBackupBaseDataRelation(BackupBaseData backupBaseData) {
		backupBaseDataDAO.addBackupBaseDataRelation(backupBaseData);
	}

}
