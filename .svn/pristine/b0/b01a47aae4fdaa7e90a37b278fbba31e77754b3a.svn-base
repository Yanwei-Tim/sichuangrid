package com.tianque.working.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.WorkBulletin;
import com.tianque.working.domain.WorkBulletinAttachFiles;

/**
 * 工作简报
 * 
 * @author wangshirui
 */
public interface WorkBulletinService {
	/**
	 * 添加简报信息
	 * 
	 * @param workBulletin
	 * @return
	 */
	WorkBulletin addWorkBulletin(WorkBulletin workBulletin);

	/**
	 * 修改工作简报信息
	 * 
	 * @param workBulletin
	 * @return
	 */
	WorkBulletin updateWorkBulletin(WorkBulletin workBulletin);

	/**
	 * 删除工作简报信息
	 * 
	 * @param id
	 * @return
	 */
	void deleteWorkBulletinById(Long id);

	/**
	 * 查询工作简报信息
	 * 
	 * @param id
	 * @return
	 */
	WorkBulletin findWorkBulletinById(Long id);

	/**
	 * 工作简报分页查询
	 * 
	 * @return
	 */
	PageInfo<WorkBulletin> findBulletinsForPageByOrgInternalCode(Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 查询附件信息
	 * 
	 * @param id
	 * @return
	 */
	WorkBulletinAttachFiles findWorkBulletinAttachFilesById(Long id);

	boolean addWorkBulletinAttachFile(WorkBulletinAttachFiles workBulletinAttachFile);

	Long getSumAllFileSizeById(Long id);

	boolean deleteWorkBulletinAttachFileById(Long attachFileId);

}
