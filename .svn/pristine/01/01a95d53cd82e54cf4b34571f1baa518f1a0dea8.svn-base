package com.tianque.plugin.weChat.service;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.weChat.domain.WeiXinMedia;
import com.tianque.plugin.weChat.domain.sendMessage.Article;
import com.tianque.plugin.weChat.domain.sendMessage.News;
import com.tianque.plugin.weChat.domain.sendMessage.TextMessage;
import com.tianque.plugin.weChat.domain.sendMessage.text.TextSendMessage;
import com.tianque.plugin.weChat.domain.user.Fan;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.domain.user.WeChatGroup;

public interface WeChatService {

	/**接收所有消息（入口）*/
	public String receiveMessage(Map<String, String> messageMap);

	/**主动回复消息*/
	public int replyMessage(TextSendMessage textSendMessage, TencentUser tencentUser);

	/**下载媒体*/
	public String downloadMedia(String mediaId, TencentUser tencentUser);

	/**获取access_token*/
	public String getAccessToken(TencentUser tencentUser);

	/**获取该公共号粉丝信息*/
	public Fan getFanByOpenId(String openId, TencentUser tencentUser);

	/**根据微信公众号获取群组列表*/
	public List<WeChatGroup> getWeChatGroupList(TencentUser tencentUser);

	/**根据openId获取他所在群组的groupId(微信方的id)*/
	public Long getGroupIdByOpenId(String openId, TencentUser tencentUser);
	
	/**
	 * 本地素材上传到微信服务器，群发图文用
	 * @Title: localMediaIdUploadToWeChat 
	 * @param sourceId
	 * @return: Map<String,String>
	 */
	public WeiXinMedia localMediaIdUploadToWeChat(Long sourceId, TencentUser tencentUser);

	/**
	 * 按组群发
	 * @param list
	 * @param tencentUser
	 * @param groupId
	 * @return
	 */
	public boolean sendNewsToGroup(List<News> list, TencentUser tencentUser, String groupId);

	/**
	 * 按用户openid列表群发
	 * @Title: sendNewsToMassUser 
	 * @param list
	 * @param tencentUser
	 * @param toUser
	 * @return: boolean
	 */
	public boolean sendNewsToMassUser(List<News> list, TencentUser tencentUser, String[] toUser);

	/**
	 * 按用户openid列表群发
	 * @Title: sendNewsToMassUser 
	 * @param list
	 * @param tencentUser
	 * @param toUser
	 * @return: String
	 */
	public String sendNewsToMassUserByMediaId(String mediaId, TencentUser tencentUser,
			String[] toUser);

	public String sendTextToMassUser(String mediaId, TencentUser tencentUser, String[] toUser);

	/**
	 * 回复文本消息
	 */
	public String replyTextMessage(List<TextMessage> listTextMessage, TencentUser tencentUser);
	/**
	 * 48小时后回复文本消息
	 */
	public String replyMoreFortyEightTextMessage(List<TextMessage> listTextMessage, TencentUser tencentUser);

	/**
	 * 回复图片消息
	 */
	public String replyImageMessage(List<TextMessage> listTextMessage, TencentUser tencentUser,
			String imagePath);
	/**
	 * 48小时后回复图片消息
	 */
	public String replyMoreFortyEightImageMessage(List<TextMessage> listTextMessage, TencentUser tencentUser,
			String imagePath);

	/**
	 * 回复语音消息
	 */

	public String replyVoiceMessage(List<TextMessage> listTextMessage, TencentUser tencentUser,
			String imagePath);
	/**
	 * 48小时后回复语音消息
	 */
	
	public String replyMoreFortyEightVoiceMessage(List<TextMessage> listTextMessage, TencentUser tencentUser,
			String filePath);

	/**
	 * 回复图文消息
	 */
	public String replyNewsMessage(List<TextMessage> listTextMessage, TencentUser tencentUser,
			List<Article> articleList);
	/**
	 * 48小时后回复图文消息
	 */
	public String replyMoreFortyEightNewsMessage(List<TextMessage> listTextMessage, TencentUser tencentUser,
			List<News> list);
	/**
	 * 微信红包发送(根据配置好的红包id和所要发送的对象openId和weChatUserId公共微信号)
	 */
	public String sendRedEnvelope(Long redEnvelopeId,String re_openid,String weChatUserId);
	
}
