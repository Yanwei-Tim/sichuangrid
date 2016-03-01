package com.tianque.plugin.weChat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.plugin.weChat.dao.InboxAttachmentDao;
import com.tianque.plugin.weChat.domain.inbox.InboxAttachment;
import com.tianque.plugin.weChat.service.InboxAttachmentService;

@Service("inboxAttachmentService")
@Transactional
public class InboxAttachmentServiceImpl extends AbstractBaseService implements
		InboxAttachmentService {
	
	@Autowired
	private InboxAttachmentDao inboxAttachmentDao;

	@Override
	public Long saveInboxAttachment(InboxAttachment inboxAttachment) {
		Long id = inboxAttachmentDao.saveInboxAttachment(inboxAttachment);
		return id;
	}

	@Override
	public List<InboxAttachment> getInboxAttachmentByInboxId(Long inboxId) {
		return inboxAttachmentDao.getInboxAttachmentByInboxId(inboxId);
	}

	@Override
	public InboxAttachment getInboxAttachmentById(Long id) {
		return inboxAttachmentDao.getInboxAttachmentById(id);
	}

	@Override
	public int deleteInboxAttachmentByInboxId(Long inboxId) {
		return inboxAttachmentDao.deleteInboxAttachmentByInboxId(inboxId);
	}

}
