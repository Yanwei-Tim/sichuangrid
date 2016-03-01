package com.tianque.plugin.weChat.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.inbox.WechatInboxVoicePrompt;

/** 微信消息提示语音设置类 */
public interface WechatInboxVoicePromptService {

	/** 列表查询 */
	public PageInfo<WechatInboxVoicePrompt> findwechatInboxVoicePrompts(
			WechatInboxVoicePrompt wechatInboxVoicePrompt, Integer pageNum,
			Integer pageSize, String sidx, String sord);

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
