package com.tianque.plugin.weChat.service;

import java.util.List;
import java.util.Set;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.sendMessage.Article;
import com.tianque.plugin.weChat.domain.sendMessage.TextMessage;
import com.tianque.plugin.weChat.domain.user.TencentUser;

public interface TencentUserService {

	PageInfo getTencentUserList(TencentUser tencentUser, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	Long addTencentUser(TencentUser tencentUser);

	/**
	 * 微信号绑定自动回复消息（素材）
	 * @param tencentUser
	 * @return
	 */
		public Integer updateTencentUser(TencentUser tencentUser);
	TencentUser getTencentUserByTencentUserId(Long tencentUserId);

	/**
	 * 根据素材id加载Tencent集合（条件Like）
	 * @param sourceId
	 * @return
	 */
	public List<TencentUser> getTencentUserBySourceId(String sourceId);
	void deleteTencentUser(List<Long> ids);

	/**根据组织机构获取微信公众账号列表*/
	public List<TencentUser> getTencentUserListByOrgId(Long orgId);

	/**根据weChatUserId（微信方公众号id）获取公众号对象*/
	public TencentUser getTencentUserByWeChatUserId(String weChatUserId);

	/**
	 * 发送文本消息
	 */
	public String sendTextMessage(TextMessage textMessage, TencentUser tencentUser, String openIds);

	/**
	 * 发送图片消息
	 */
	public String sendImageMessage(TextMessage textMessage, TencentUser tencentUser,
			Set<String> attachFiles, String openIds);

	/**
	 * 发送语音消息
	 */

	public String sendVoiceMessage(TextMessage textMessage, TencentUser tencentUser,
			Set<String> attachFiles, String openIds);

	/**
	 * 发送图文消息
	 */
	public String sendNewsMessage(TextMessage textMessage, TencentUser tencentUser,
			Set<String> attachFiles, Article article, String openIds);

	/**根据组织机构编号获取微信公众账号列表*/
	List<TencentUser> getTencentUserListByOrgCode(String orgInternalCode);

}
