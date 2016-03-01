package com.tianque.plugin.weChat.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.inbox.Inbox;
import com.tianque.plugin.weChat.domain.user.TencentUser;

public interface InboxDao {
	/**列表查询*/
	public PageInfo<Inbox> findInboxs(Map<String, Object> parameterMap, Integer pageNum,
			Integer pageSize);
	
	/**统计未处理的消息数和发信人*/
	public PageInfo<Inbox> findUntreatedInboxs(Map<String, Object> parameterMap, Integer pageNum,
			Integer pageSize);

	/**新增*/
	public Long saveInbox(Inbox inbox);

	/**根据inboxId删除*/
	public void deleteInboxById(List<Long> inboxId);

	/****
	 * 设置有无效
	 */
	public void setAvailabilityOrInvalid(Inbox inbox);

	/**根据id获取单个对象*/
	public Inbox getInboxById(Long id);

	public void update(Inbox inbox);

	/**查找最大Id*/
	public Long getMaxInboxId();
	
	/**
	 * 根据微信号和粉丝号，修改粉丝所在的群组Id
	 * @param inbox
	 */

	public Integer setGroupIdByWeChatUserIdAndFanId(Inbox inbox);
	/**
	 * 在mq端保存信息（文本，语音，图片）
	 * @param messageMap
	 * @param tencentUser
	 * @param msgType
	 * @param access_Token
	 */
	public void saveMsgByMq(Map<String, String> messageMap,TencentUser tencentUser,String msgType,String access_Token);
	
	/**接入客户消息*/
	public void updateDealState(Map<String,Object> map);
	/**
	 * 根据微信号和粉丝号查询已接入记录
	 * @param inbox
	 */
	public List<Inbox> findAccessInboxList(Inbox inbox);
	
		/**查询社管与单个用户聊天内容*/
	public List<Inbox> findInboxsAndReplyMessages(Map<String,Object> map);
	
	/**查询单个用户未处理的所有消息*/
	public List<Inbox> findInboxsByOpenIdAndDealState(Inbox inbox);
	/**根据openId删除未处理消息*/
	public void deleteInboxsByOpenIdAndDealState(Inbox inbox);
	/**根据openId和处理状态转出已经接入消息*/
	public void inboxRollOutByOpenIdAndDealState(Inbox inbox);
	/**查询当前粉丝发送的最后一条消息*/
	public Inbox findLastInboxByFromUserName(Inbox inbox);
	/**修改查看后的消息状态(改为已读)*/
	public void updateIsRead(Inbox inbox);
}
