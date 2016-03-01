package com.tianque.resourcePool.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.resourcePool.domain.MyProfileAttachFile;

@Repository("myProfileAttachFileDao")
public class MyProfileAttachFileDaoImpl extends AbstractBaseDao implements
		MyProfileAttachFileDao {

	@Override
	public MyProfileAttachFile addMyProfileAttachFile(
			MyProfileAttachFile myProfileAttachFile) {
		validate(myProfileAttachFile);
		myProfileAttachFile.setId((Long) getSqlMapClientTemplate().insert(
				"myProfileAttachFile.addMyProfileAttachFile",
				myProfileAttachFile));

		return null;
	}

	private void validate(MyProfileAttachFile myProfileAttachFile) {
		if (myProfileAttachFile == null)
			throw new BusinessValidationException("附件不能为空");
		if (myProfileAttachFile.getFileName() == null
				|| "".equals(myProfileAttachFile.getFileName().trim()))
			throw new BusinessValidationException("名称不能为空");
		if (myProfileAttachFile.getFileActualUrl() == null
				|| "".equals(myProfileAttachFile.getFileActualUrl().trim()))
			throw new BusinessValidationException("存储路径不能为空");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MyProfileAttachFile> getMyProfileAttachFileByMyProfileId(
			Long myProfileId) {
		return getSqlMapClientTemplate().queryForList(
				"myProfileAttachFile.getMyProfileAttachFileByMyProfileId",
				myProfileId);
	}

	@Override
	public MyProfileAttachFile getSimpleMyProfileAttachFileById(Long id) {
		return (MyProfileAttachFile) getSqlMapClientTemplate().queryForObject(
				"myProfileAttachFile.getSimpleMyProfileAttachFileById", id);
	}

	@Override
	public void deleteMyProfileAttachFileById(Long id) {
		getSqlMapClientTemplate().delete(
				"myProfileAttachFile.deleteMyProfileAttachFileById", id);
	}

	@Override
	public void deleteMyProfileAttachFileByMyProfileId(Long myProfileId) {
		getSqlMapClientTemplate().delete(
				"myProfileAttachFile.deleteMyProfileAttachFileByMyProfileId",
				myProfileId);
	}

	@Override
	public void deleteMyProfileAttachFileByMyProfileIds(String[] myProfileIds) {
		if (myProfileIds == null || myProfileIds.length < 0) {
			throw new BusinessValidationException("参数错误");
		}
		getSqlMapClientTemplate().delete(
				"myProfileAttachFile.deleteMyProfileAttachFileByMyProfileIds",
				myProfileIds);
	}

}
