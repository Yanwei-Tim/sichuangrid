package com.tianque.plugin.weChat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.plugin.weChat.dao.ReplyMessageDao;
import com.tianque.plugin.weChat.domain.inbox.ReplyMessage;

@Repository("replyMessageDao")
public class ReplyMessageDaoImpl extends AbstractBaseDao implements
		ReplyMessageDao {

	/*
	 * @Override public PageInfo<ReplyMessage> findReplyMessages(Map<String,
	 * Object> parameterMap, Integer pageNum, Integer pageSize) { return
	 * getPageInfoByParamMap(new PageInfo<ModuleTable>(),
	 * "replyMessage.countFindReplyMessages", "replyMessage.findReplyMessages",
	 * pageNum, pageSize, parameterMap); }
	 */
	@Override
	public Long saveReplyMessage(ReplyMessage replyMessage) {
		return (Long) getSqlMapClientTemplate().insert(
				"replyMessage.saveReplyMessage", replyMessage);
	}

	@Override
	public int deleteReplyMessageByInboxId(Long inboxId) {
		return getSqlMapClientTemplate().delete(
				"replyMessage.deleteReplyMessageByInboxId", inboxId);
	}

	@Override
	public Long countRMByInboxId(Long inboxId) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"replyMessage.countRMByInboxId", inboxId);
	}

	@Override
	public List<ReplyMessage> findReplyMessagesByInboxId(Long InboxId) {
		return getSqlMapClientTemplate().queryForList(
				"replyMessage.findReplyMessagesByInboxId", InboxId);
	}

	@Override
	public Long countRMByPreciseInboxId(Long preciseInboxId) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"replyMessage.countRMByPreciseInboxId", preciseInboxId);
	}

	@Override
	public int deleteReplyMessageByPreciseInboxId(Long preciseInboxId) {
		return getSqlMapClientTemplate().delete(
				"replyMessage.deleteReplyMessageByPreciseInboxId",
				preciseInboxId);
	}

	@Override
	public List<ReplyMessage> findReplyMessagesByPreciseInboxId(
			Long preciseInboxId) {
		return getSqlMapClientTemplate().queryForList(
				"replyMessage.findReplyMessagesByPreciseInboxId",
				preciseInboxId);
	}

	@Override
	public List<ReplyMessage> findReplyMessagesByOpenIdAndDealState(
			Map<String, Object> map) {
		return getSqlMapClientTemplate().queryForList(
				"replyMessage.findReplyMessagesByOpenIdAndDealState", map);
	}

	@Override
	public void update(ReplyMessage replyMessage) {
		getSqlMapClientTemplate()
		.update("replyMessage.updateReplyMessageById", replyMessage);
	}

}
