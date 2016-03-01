package com.tianque.plugin.weChat.service;

import java.util.List;

import com.tianque.plugin.weChat.domain.inbox.InboxAttachment;

public interface InboxAttachmentService {
	/**附件新增*/
	public Long saveInboxAttachment(InboxAttachment inboxAttachment);

	/**根据收件箱id获取对应的附件列表*/
	public List<InboxAttachment> getInboxAttachmentByInboxId(Long inboxId);

	/**根据id获取对象*/
	public InboxAttachment getInboxAttachmentById(Long id);

	/**根据inboxId删除*/
	public int deleteInboxAttachmentByInboxId(Long inboxId);
}
