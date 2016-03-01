package com.tianque.plugin.weChat.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.jms.OperateMode;
import com.tianque.jms.sender.ActiveMQMessageSender;
import com.tianque.plugin.weChat.dao.InboxDao;
import com.tianque.plugin.weChat.domain.ModuleTable;
import com.tianque.plugin.weChat.domain.WeChatMsg;
import com.tianque.plugin.weChat.domain.inbox.Inbox;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.util.Constants;

@Repository("inboxDao")
public class InboxDaoImpl extends AbstractBaseDao implements InboxDao {
	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public PageInfo<Inbox> findInboxs(Map<String, Object> parameterMap,
			Integer pageNum, Integer pageSize) {
		return getPageInfoByParamMap(new PageInfo<ModuleTable>(),
				"inbox.countFindInboxs", "inbox.findInboxs", pageNum, pageSize,
				parameterMap);
	}

	@Override
	public Long saveInbox(Inbox inbox) {
		return (Long) getSqlMapClientTemplate()
				.insert("inbox.saveInbox", inbox);
	}

	@Override
	public void deleteInboxById(List<Long> inboxId) {
		for (Long ids : inboxId) {
			getSqlMapClientTemplate().delete("inbox.deleteInboxById", ids);
		}
	}

	@Override
	public Inbox getInboxById(Long id) {
		return (Inbox) getSqlMapClientTemplate().queryForObject(
				"inbox.getInboxById", id);
	}

	@Override
	public void update(Inbox inbox) {
		getSqlMapClientTemplate().update("inbox.updateById", inbox);
	}

	@Override
	public Long getMaxInboxId() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"inbox.getMaxInboxId");
	}

	/****
	 * 设置有无效
	 */
	@Override
	public void setAvailabilityOrInvalid(Inbox inbox) {
		getSqlMapClientTemplate().update("inbox.updateAvailabilityById", inbox);
	}

	/**
	 * 根据微信号和粉丝号，修改粉丝所在的群组Id
	 * 
	 * @param inbox
	 */

	public Integer setGroupIdByWeChatUserIdAndFanId(Inbox inbox) {
		return (Integer) getSqlMapClientTemplate().update(
				"inbox.updateGroupIdByWeChatUserIdAndFanId", inbox);
	}

	/**
	 * 在mq端保存信息（文本，语音，图片）
	 * 
	 * @param messageMap
	 * @param tencentUser
	 * @param msgType
	 * @param access_Token
	 */
	public void saveMsgByMq(Map<String, String> messageMap,
			TencentUser tencentUser, String msgType, String access_Token) {
		if (msgType.equals(Constants.REQ_MESSAGE_TYPE_IMAGE)
				|| msgType.equals(Constants.REQ_MESSAGE_TYPE_VOICE)) {
			activeMQMessageSender.senderMsg(new WeChatMsg(messageMap,
					tencentUser, messageMap.get("MediaId"), access_Token,
					OperateMode.ADD));
		} else {
			activeMQMessageSender.senderMsg(new WeChatMsg(messageMap,
					tencentUser, OperateMode.ADD));
		}
	}

	@Override
	public PageInfo<Inbox> findUntreatedInboxs(
			Map<String, Object> parameterMap, Integer pageNum, Integer pageSize) {
		return getPageInfoByParamMap(new PageInfo<ModuleTable>(),
				"inbox.countFindUntreatedInboxs", "inbox.findUntreatedInboxs",
				pageNum, pageSize, parameterMap);
	}

	@Override
	public void updateDealState(Map<String, Object> map) {

		getSqlMapClientTemplate().update("inbox.updateDealStateByOpenId", map);
	}

	@Override
	public List<Inbox> findAccessInboxList(Inbox inbox) {
		List<Inbox> inboxList = (List<Inbox>) getSqlMapClientTemplate()
				.queryForList("inbox.findAccessInboxList", inbox);
		return inboxList;
	}

	@Override
	public List<Inbox> findInboxsAndReplyMessages(Map<String, Object> map) {
		return (List<Inbox>) getSqlMapClientTemplate().queryForList(
				"inbox.findInboxsAndReplyMessages", map);
	}

	@Override
	public List<Inbox> findInboxsByOpenIdAndDealState(Inbox inbox) {

		return (List<Inbox>) getSqlMapClientTemplate().queryForList(
				"inbox.findInboxsByOpenIdAndDealState", inbox);
	}

	@Override
	public void deleteInboxsByOpenIdAndDealState(Inbox inbox) {
		getSqlMapClientTemplate().delete(
				"inbox.deleteInboxsByOpenIdAndDealState", inbox);
	}

	@Override
	public void inboxRollOutByOpenIdAndDealState(Inbox inbox) {
		getSqlMapClientTemplate().update(
				"inbox.inboxRollOutByOpenIdAndDealState", inbox);
	}

	@Override
	public Inbox findLastInboxByFromUserName(Inbox inbox) {
		return (Inbox) getSqlMapClientTemplate().queryForObject(
				"inbox.findLastInboxByFromUserName", inbox);
	}

	@Override
	public void updateIsRead(Inbox inbox) {
		getSqlMapClientTemplate().update(
				"inbox.updateIsRead", inbox);
	}
}
