package com.tianque.working.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.working.dao.WorkBulletinAttachFilesDao;
import com.tianque.working.dao.WorkBulletinsDao;
import com.tianque.working.domain.WorkBulletin;
import com.tianque.working.domain.WorkBulletinAttachFiles;
import com.tianque.working.service.WorkBulletinService;

/**
 * 工作简报业务类
 * 
 * @author wangshirui
 */
@Transactional
@Service("WorkBulletinService")
public class WorkBulletinServiceImpl extends AbstractBaseService implements WorkBulletinService {
	@Autowired
	private WorkBulletinsDao workBulletinsDao;
	@Autowired
	private WorkBulletinAttachFilesDao workBulletinAttachFilesDao;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PermissionService permissionService;

	/**
	 * 添加简报信息
	 * 
	 * @param workBulletin
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public WorkBulletin addWorkBulletin(WorkBulletin workBulletin) {
		WorkBulletin workBulletinNew = null;
		workBulletinNew = workBulletinsDao.addWorkBulletin(workBulletin);
		List<WorkBulletinAttachFiles> workBulletinAttachFileList = workBulletin
				.getWorkBulletinAttachFileList();
		for (WorkBulletinAttachFiles workBulletinAttachFile : workBulletinAttachFileList) {
			workBulletinAttachFile.setBulletinId(workBulletin.getId());
			workBulletinAttachFilesDao.insert(workBulletinAttachFile);
		}

		return workBulletinNew;
	}

	/**
	 * 删除工作简报信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteWorkBulletinById(Long id) {
		workBulletinAttachFilesDao.deleteByBulletinId(id);
		workBulletinsDao.deleteWorkBulletinById(id);

	}

	/**
	 * 查询工作简报信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public WorkBulletin findWorkBulletinById(Long id) {
		WorkBulletin workBulletin = workBulletinsDao.findWorkBulletinById(id);
		if (workBulletin != null && workBulletin.getUser() != null) {
			User user = permissionService.getSimpleUserById(workBulletin
					.getUser().getId());
			if (user != null) {
				workBulletin.setUserName(user.getUserName());
			}
		}
		workBulletin.setWorkBulletinAttachFileList(workBulletinAttachFilesDao
				.selectWorkBulletinAttachFilesByBulletId(id));
		return workBulletin;
	}

	/**
	 * 修改工作简报信息
	 * 
	 * @param workBulletin
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public WorkBulletin updateWorkBulletin(WorkBulletin workBulletin) {
		workBulletinsDao.updateWorkBulletin(workBulletin);
		return findWorkBulletinById(workBulletin.getId());
	}

	/**
	 * 工作简报分页查询（未定）
	 * 
	 * @return
	 */
	@Override
	public PageInfo<WorkBulletin> findBulletinsForPageByOrgInternalCode(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return constructEmptyPageInfo();
			} else {
				return workBulletinsDao.findWorkBulletinForPageByOrgInternalCode(
						org.getOrgInternalCode(), pageNum, pageSize, sidx, sord);
			}
		}
	}

	private PageInfo<WorkBulletin> constructEmptyPageInfo() {
		PageInfo<WorkBulletin> result = new PageInfo<WorkBulletin>();
		result.setResult(new ArrayList<WorkBulletin>());
		return result;
	}

	@Override
	public WorkBulletinAttachFiles findWorkBulletinAttachFilesById(Long id) {
		return workBulletinAttachFilesDao.findWorkBulletinAttachFilesById(id);
	}

	@Override
	public boolean addWorkBulletinAttachFile(WorkBulletinAttachFiles workBulletinAttachFile) {
		return workBulletinAttachFilesDao.addWorkBulletinAttach(workBulletinAttachFile);
	}

	@Override
	public Long getSumAllFileSizeById(Long id) {
		return workBulletinAttachFilesDao.getSumAllFileSizeById(id);
	}

	@Override
	public boolean deleteWorkBulletinAttachFileById(Long attachFileId) {
		if (attachFileId == null)
			return false;
		return workBulletinAttachFilesDao.deleteWorkBulletinAttachFileById(attachFileId);
	}

}
