package com.tianque.platformMessage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.platformMessage.dao.PlatformMessagesAttachFileDao;
import com.tianque.platformMessage.domain.PlatformMessageAttachFile;

@Service("platformMessagesAttachFileService")
@Transactional
public class PlatformMessagesAttachFileServiceImpl implements
		PlatformMessagesAttachFileService {

	@Autowired
	private PlatformMessagesAttachFileDao platformMessagesAttachFileDao;

	@Override
	public PlatformMessageAttachFile getPlatformMessageAttachFileById(Long id)
			throws Exception {
		if (id == null) {
			throw new BusinessValidationException("附件id没有获得");
		}
		return platformMessagesAttachFileDao
				.getPlatformMessageAttachFileById(id);
	}

}
