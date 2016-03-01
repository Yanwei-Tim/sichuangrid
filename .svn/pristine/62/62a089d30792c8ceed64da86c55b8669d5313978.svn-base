package com.tianque.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.working.dao.WorkBulletinAttachFilesDao;
import com.tianque.working.domain.WorkBulletinAttachFiles;

@Repository("workBulletinAttachFilesDaoImpl")
public class WorkBulletinAttachFilesDaoImpl extends AbstractBaseDao implements
		WorkBulletinAttachFilesDao {

	@Override
	public int deleteByBulletinId(Long bulletinId) {
		WorkBulletinAttachFiles workBulletinAttachFiles = new WorkBulletinAttachFiles();
		workBulletinAttachFiles.setBulletinId(bulletinId);
		int rows = getSqlMapClientTemplate().delete(
				"workBulletinAttachFiles.deleteByBulletinId",
				workBulletinAttachFiles);
		return rows;
	}

	@Override
	public void insert(WorkBulletinAttachFiles record) {
		getSqlMapClientTemplate().insert("workBulletinAttachFiles.insert",
				record);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WorkBulletinAttachFiles> selectWorkBulletinAttachFilesByBulletId(
			Long bulletinId) {
		WorkBulletinAttachFiles workBulletinAttachFiles = new WorkBulletinAttachFiles();
		workBulletinAttachFiles.setBulletinId(bulletinId);
		return getSqlMapClientTemplate().queryForList(
				"workBulletinAttachFiles.selectByBulletinId",
				workBulletinAttachFiles);
	}

	@Override
	public WorkBulletinAttachFiles findWorkBulletinAttachFilesById(Long id) {
		WorkBulletinAttachFiles workBulletinAttachFiles = new WorkBulletinAttachFiles();
		workBulletinAttachFiles.setId(id);
		return (WorkBulletinAttachFiles) getSqlMapClientTemplate()
				.queryForObject("workBulletinAttachFiles.selectById",
						workBulletinAttachFiles);
	}

	@Override
	public Long getSumAllFileSizeById(Long id) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"workBulletinAttachFiles.getSumAllFileSizeById", id);
	}

	@Override
	public boolean addWorkBulletinAttach(
			WorkBulletinAttachFiles workBulletinAttachFile) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"workBulletinAttachFiles.insert", workBulletinAttachFile);
		return id != null;
	}

	@Override
	public boolean deleteWorkBulletinAttachFileById(Long id) {
		getSqlMapClientTemplate().delete(
				"workBulletinAttachFiles.deleteWorkBulletinAttachFileById", id);
		return findWorkBulletinAttachFilesById(id) == null;
	}

}