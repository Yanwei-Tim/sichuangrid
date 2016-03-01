package com.tianque.resourcePool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.resourcePool.dao.MyProfileAttachFileDao;
import com.tianque.resourcePool.domain.MyProfileAttachFile;

@Service("myProfileAttachFileService")
@Transactional
public class MyProfileAttachFileServiceImpl implements
		MyProfileAttachFileService {

	@Autowired
	private MyProfileAttachFileDao myProfileAttachFileDao;

	@Override
	public MyProfileAttachFile addMyProfileAttachFile(
			MyProfileAttachFile myProfileAttachFile) {
		return myProfileAttachFileDao
				.addMyProfileAttachFile(myProfileAttachFile);
	}

	@Override
	public List<MyProfileAttachFile> getMyProfileAttachFileByMyProfileId(
			Long myProfileId) {
		return myProfileAttachFileDao
				.getMyProfileAttachFileByMyProfileId(myProfileId);
	}

	@Override
	public MyProfileAttachFile getSimpleMyProfileAttachFileById(Long id) {

		return myProfileAttachFileDao.getSimpleMyProfileAttachFileById(id);
	}

	@Override
	public void deleteMyProfileAttachFileById(Long id) {

		myProfileAttachFileDao.deleteMyProfileAttachFileById(id);
	}

	@Override
	public void deleteMyProfileAttachFileByMyProfileId(Long myProfileId) {
		myProfileAttachFileDao
				.deleteMyProfileAttachFileByMyProfileId(myProfileId);
	}

	@Override
	public void deleteMyProfileAttachFileByMyProfileIds(String[] myProfileIds) {
		if (myProfileIds == null || myProfileIds.length < 0) {
			throw new BusinessValidationException("参数错误");
		}
		myProfileAttachFileDao
				.deleteMyProfileAttachFileByMyProfileIds(myProfileIds);
	}

}
