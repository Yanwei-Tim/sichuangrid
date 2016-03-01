package com.tianque.plugin.weChat.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.inbox.Inbox;
import com.tianque.plugin.weChat.domain.sendMessage.Article;
import com.tianque.plugin.weChat.domain.sendMessage.TextMessage;
import com.tianque.plugin.weChat.domain.sendMessage.text.TextSendMessage;
import com.tianque.plugin.weChat.domain.user.TencentUser;

public interface InboxService {
	/**收件箱列表显示*/
	public PageInfo<Inbox> findInboxs(Inbox inbox, Integer pageNum, Integer pageSize, String sidx,
			String sord);
	
	/**统计未处理的消息数和发信人*/
	public PageInfo<Inbox> findUntreatedInboxs(Inbox inbox, Integer pageNum, Integer pageSize, String sidx,
			String sord);
	//inboxService.findUntreatedInboxs(inbox, page, rows, sidx, sord)

	/**新增收件箱信息*/
	public Long saveInbox(Map<String, String> messageMap, TencentUser tencentUser);

	/**主动回复 事件处理调用该方法*/
	public int replyMessage(Inbox inbox, TextSendMessage textSendMessage);

	/**保存回复 事件处理在用（TextSendMessage），微信没用*/
	public Long saveReplyMessage(Inbox inbox, TextSendMessage textSendMessage);

	/**根据id获取单个对象*/
	public Inbox getInboxById(Long id);

	/**根据inboxId删除*/
	public void deleteInboxById(List<Long> inboxId);

	/****
	 * 设置有无效
	 */
	public void setAvailabilityOrInvalid(List<Long> inboxId, String flag);

	public void update(Inbox inbox);

	/**查找最大Id*/
	public Long getMaxInboxId();

	/*******回复文本**/
	public String replyTextMessage(Inbox inbox, TextMessage textMessage);
	/*******48小时后回复文本**/
	public String replyMoreFortyEightTextMessage(Inbox inbox, TextMessage textMessage);
	/*******转发文本（支持群转发）**/
	public String retransmissionTextMessage(Inbox inbox, TextMessage textMessage,String openIds);
	

	/*******回复图片**/
	public String replyImageMessage(Inbox inbox, TextMessage textMessage, Set<String> attachFiles);
	/*******48小时后回复图片**/
	public String replyMoreFortyEightImageMessage(Inbox inbox, TextMessage textMessage, Set<String> attachFiles);

	/*******转发图片（支持群转发）**/
	public String retransmissionImageMessage(Inbox inbox, TextMessage textMessage,String openIds,String pathImage );

	/*******回复图文**/
	public String replyNewsMessage(Inbox inbox, TextMessage textMessage, Set<String> attachFiles,
			Article article);
	/*******48小时后回复图文**/
	public String replyMoreFortyEightNewsMessage(Inbox inbox, TextMessage textMessage, Set<String> attachFiles,
			Article article);

	/*******回复语音**/
	public String replyVoiceMessage(Inbox inbox, TextMessage textMessage, Set<String> attachFiles);
	/*******48小时后回复语音**/
	public String replyMoreFortyEightVoiceMessage(Inbox inbox, TextMessage textMessage, Set<String> attachFiles);
	/*******转发语音（支持群转发）**/
	public String retransmissionVoiceMessage(Inbox inbox, TextMessage textMessage,String openIds,String pathVoice );

	/**
	 * 根据微信号和粉丝号，修改粉丝所在的群组Id
	 * @param inbox
	 */
	public Integer setGroupIdByWeChatUserIdAndFanId(Inbox inbox);
	/**接入客户消息*/
	public void updateDealState(String openIds,Inbox inbox,Long oldDealState);
	/**查询社管与单个用户聊天内容*/
	public List<Inbox> findInboxsAndReplyMessages(Inbox inbox);
	/**查询单个用户未处理的所有消息*/
	public List<Inbox> findInboxsByOpenIdAndDealState(Inbox inbox);
	/**根据openId删除用户未处理消息*/
	public void deleteInboxsByOpenIdAndDealState(String openIds,Inbox inbox);
	/**根据openId和处理状态转出已经接入消息*/
	public void inboxRollOutByOpenIdAndDealState(Inbox inbox);
	/**查询当前粉丝发送的最后一条消息*/
	public Inbox findLastInboxByFromUserName(Inbox inbox);
	/**修改查看后的消息状态(改为已读)*/
	public void updateIsRead(Inbox inbox);
}
