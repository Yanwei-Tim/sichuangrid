package com.tianque.working.dao;

import java.util.List;

import com.tianque.working.domain.WorkBulletinAttachFiles;

public interface WorkBulletinAttachFilesDao {

	int deleteByBulletinId(Long bulletinId);

	void insert(WorkBulletinAttachFiles record);

	WorkBulletinAttachFiles findWorkBulletinAttachFilesById(Long id);

	List<WorkBulletinAttachFiles> selectWorkBulletinAttachFilesByBulletId(Long bulletinId);

	Long getSumAllFileSizeById(Long id);

	boolean addWorkBulletinAttach(WorkBulletinAttachFiles workBulletinAttachFile);

	boolean deleteWorkBulletinAttachFileById(Long attachFileId);

}