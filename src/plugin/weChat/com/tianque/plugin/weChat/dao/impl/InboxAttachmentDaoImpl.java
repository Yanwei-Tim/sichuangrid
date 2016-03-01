package com.tianque.plugin.weChat.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.weChat.dao.InboxAttachmentDao;
import com.tianque.plugin.weChat.domain.inbox.InboxAttachment;

@Repository("InboxAttachmentDao")
public class InboxAttachmentDaoImpl extends AbstractBaseDao implements InboxAttachmentDao {
	private static Logger logger = Logger.getLogger(InboxAttachmentDaoImpl.class);

	@Override
	public Long saveInboxAttachment(InboxAttachment inboxAttachment) {
		return (Long) getSqlMapClientTemplate().insert("inboxAttachment.saveInboxAttachment",
				inboxAttachment);
	}

	@Override
	public List<InboxAttachment> getInboxAttachmentByInboxId(Long inboxId) {
		return getSqlMapClientTemplate().queryForList(
				"inboxAttachment.getInboxAttachmentByInboxId", inboxId);
	}

	@Override
	public InboxAttachment getInboxAttachmentById(Long id) {
		return (InboxAttachment) getSqlMapClientTemplate().queryForObject(
				"inboxAttachment.getInboxAttachmentById", id);
	}

	@Override
	public int deleteInboxAttachmentByInboxId(Long inboxId) {
		return getSqlMapClientTemplate().delete("inboxAttachment.deleteInboxAttachmentByInboxId",
				inboxId);
	}
}
