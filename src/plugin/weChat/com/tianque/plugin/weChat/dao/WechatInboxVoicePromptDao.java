package com.tianque.plugin.weChat.dao;

import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.inbox.WechatInboxVoicePrompt;

/** 微信消息提示语音设置类 */
public interface WechatInboxVoicePromptDao {

	/** 列表查询 */
	public PageInfo<WechatInboxVoicePrompt> findWechatInboxVoicePrompts(
			Map<String, Object> parameterMap, Integer pageNum, Integer pageSize);

	/** 查询语音提示对象 */
	public WechatInboxVoicePrompt findWechatInboxVoicePrompt(
			WechatInboxVoicePrompt wechatInboxVoicePrompt);

	/** 添加语音提示对象 */
	public void addWechatInboxVoicePrompt(
			WechatInboxVoicePrompt wechatInboxVoicePrompt);
	
	/** 修改 */
	public void updateWechatInboxVoicePromptById(
			WechatInboxVoicePrompt wechatInboxVoicePrompt);
}
