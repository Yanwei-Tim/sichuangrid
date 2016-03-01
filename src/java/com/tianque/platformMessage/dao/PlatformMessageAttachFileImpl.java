package com.tianque.platformMessage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.platformMessage.domain.PlatformMessageAttachFile;

@Repository("platformMessagesAttachFileDao")
public class PlatformMessageAttachFileImpl extends AbstractBaseDao implements
		PlatformMessagesAttachFileDao {

	/***
	 * 添加消息来附件
	 */
	@Override
	public PlatformMessageAttachFile addPlatformMessageAttachFile(
			PlatformMessageAttachFile pmAttachFile) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"platformMessagesAttachFile.addPlatformMessagesAttachFile", pmAttachFile);
		return getPlatformMessageAttachFileById(id);
	}

	/**
	 * 根据ID获取消息附件
	 */
	@Override
	public PlatformMessageAttachFile getPlatformMessageAttachFileById(Long id) {
		return (PlatformMessageAttachFile) getSqlMapClientTemplate().queryForObject(
				"platformMessagesAttachFile.getPlatformMessagesAttachFileById", id);
	}

	/**
	 * 根据消息ID和附件类型(发送、接收)获取消息附件
	 */
	@Override
	public List<PlatformMessageAttachFile> getPlatformMessageAttachFileByPmIdAndType(Long pmId,
			int attachType) {
		Map conditions = new HashMap();
		conditions.put("pmId", pmId);
		conditions.put("attachType", attachType);
		return getSqlMapClientTemplate().queryForList(
				"platformMessagesAttachFile.getPlatformMessageAttachFileByIdAndType", conditions);
	}

	@Override
	public void deletePlatformMessagesAttachFileById(Long id) {
		getSqlMapClientTemplate().delete(
				"platformMessagesAttachFile.deletePlatformMessagesAttachFileById", id);
	}

	@Override
	public Long sumAllFileSizeByUserId(Long userId) {
		Long count = (Long) getSqlMapClientTemplate().queryForObject(
				"personnelMessagesAttachFile.sumAllFileSizeByUserId", userId);
		return count == null ? 0 : count;
	}

	@Override
	public void deletePlatformMessageAttachFileByPmIdAndType(Long pmId,
			int attachType) {
		Map conditions = new HashMap();
		conditions.put("pmId", pmId);
		conditions.put("attachType", attachType);
		getSqlMapClientTemplate()
				.delete("platformMessagesAttachFile.deletePlatformMessageAttachFileByIdAndType",
						conditions);
	}
	
}
