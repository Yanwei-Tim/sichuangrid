package com.tianque.platformMessage.dao;

import java.util.List;

import com.tianque.platformMessage.domain.PlatformMessageAttachFile;

public interface PlatformMessagesAttachFileDao {

	public PlatformMessageAttachFile addPlatformMessageAttachFile(
			PlatformMessageAttachFile pmAttachFile);

	public PlatformMessageAttachFile getPlatformMessageAttachFileById(Long id);

	public List<PlatformMessageAttachFile> getPlatformMessageAttachFileByPmIdAndType(
			Long id, int attachType);

	public void deletePlatformMessageAttachFileByPmIdAndType(Long id,
			int attachType);

	public void deletePlatformMessagesAttachFileById(Long id);

	public Long sumAllFileSizeByUserId(Long userId);

}
