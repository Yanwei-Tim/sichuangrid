package com.tianque.working.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.WorkBulletin;

/**
 * 工作简报基本信息
 * 
 * @author wangshirui
 */
public interface WorkBulletinsDao {

	void deleteWorkBulletinById(Long id);

	WorkBulletin addWorkBulletin(WorkBulletin record);

	WorkBulletin findWorkBulletinById(Long id);

	int updateWorkBulletin(WorkBulletin record);

	PageInfo<WorkBulletin> findWorkBulletinForPageByOrgInternalCode(String orgInternalCode,
			Integer pageNum, Integer pageSize, String sidx, String sord);

}