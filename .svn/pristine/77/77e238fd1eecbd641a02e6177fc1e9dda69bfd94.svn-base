package com.tianque.plugin.weChat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.plugin.weChat.dao.ReplyMessageDao;
import com.tianque.plugin.weChat.domain.inbox.ReplyMessage;
import com.tianque.plugin.weChat.service.ReplyMessageService;

/**回复消息的管理服务类*/
@Service("replyMessageService")
@Transactional
public class ReplyMessageServiceImpl extends AbstractBaseService implements ReplyMessageService {
	@Autowired
	private ReplyMessageDao replyMessageDao;

	/*@Override
	public PageInfo<ReplyMessage> findReplyMessages(ReplyMessage replyMessage, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("replyMessage", replyMessage);
		PageInfo<ReplyMessage> pageInfo = replyMessageDao.findReplyMessages(map, pageNum, pageSize);
		return pageInfo;
	}*/

	@Override
	public Long saveReplyMessage(ReplyMessage replyMessage) {
		return replyMessageDao.saveReplyMessage(replyMessage);
	}

	@Override
	public int deleteReplyMessageByInboxId(Long inboxId) {
		return replyMessageDao.deleteReplyMessageByInboxId(inboxId);
	}

	@Override
	public List<ReplyMessage> findReplyMessagesByInboxId(Long InboxId) {
		return replyMessageDao.findReplyMessagesByInboxId(InboxId);
	}

	@Override
	public Long countRMByInboxId(Long inboxId) {
		return replyMessageDao.countRMByInboxId(inboxId);
	}

	@Override
	public Long countRMByPreciseInboxId(Long preciseInboxId) {
		return replyMessageDao.countRMByPreciseInboxId(preciseInboxId);
	}

	@Override
	public int deleteReplyMessageByPreciseInboxId(Long preciseInboxId) {
		return replyMessageDao.deleteReplyMessageByPreciseInboxId(preciseInboxId);
	}

	@Override
	public List<ReplyMessage> findReplyMessagesByPreciseInboxId(
			Long preciseInboxId) {
		return replyMessageDao.findReplyMessagesByPreciseInboxId(preciseInboxId);
	}

	@Override
	public List<ReplyMessage> findReplyMessagesByOpenIdAndDealState(
			String fromUserName, Long isIssue) {
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("fromUserName", fromUserName);
		map.put("isIssue", isIssue);
		return replyMessageDao.findReplyMessagesByOpenIdAndDealState(map);
	}

	@Override
	public void update(ReplyMessage replyMessage) {
		replyMessageDao.update(replyMessage);
	}
	
}
