/**
 * 
 */
package com.tianque.plugin.weChat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.weChat.domain.WeiXinMedia;
import com.tianque.plugin.weChat.domain.sendMessage.Article;
import com.tianque.plugin.weChat.domain.sendMessage.Image;
import com.tianque.plugin.weChat.domain.sendMessage.ImageMessage;
import com.tianque.plugin.weChat.domain.sendMessage.NewsMessage;
import com.tianque.plugin.weChat.domain.sendMessage.TextMessage;
import com.tianque.plugin.weChat.domain.sendMessage.Voice;
import com.tianque.plugin.weChat.domain.sendMessage.VoiceMessage;
import com.tianque.plugin.weChat.domain.user.KeyWord;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.domain.user.WeChatMenu;
import com.tianque.plugin.weChat.domain.user.WeChatSource;
import com.tianque.plugin.weChat.service.EventService;
import com.tianque.plugin.weChat.service.FanService;
import com.tianque.plugin.weChat.service.KeyWordService;
import com.tianque.plugin.weChat.service.NewsService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.UploadFileService;
import com.tianque.plugin.weChat.service.WeChatGroupService;
import com.tianque.plugin.weChat.service.WeChatMenuService;
import com.tianque.plugin.weChat.service.WeChatService;
import com.tianque.plugin.weChat.service.WeChatSourceService;
import com.tianque.plugin.weChat.util.Constants;
import com.tianque.plugin.weChat.util.MessageUtil;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

/**
 * 事件入口
 * 
 * @author
 * @date 2014年4月18日
 */
@Service("eventService")
@Transactional
public class EventServiceImpl extends AbstractBaseService implements
		EventService {

	@Autowired
	private FanService fanService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private WeChatMenuService weChatMenuService;
	@Autowired
	private WeChatSourceService weChatSourceService;
	@Autowired
	private PropertyDictDubboService propertyDictService;
	@Autowired
	private WeChatService weChatService;
	@Autowired
	private TencentUserService tencentUserService;
	@Autowired
	private UploadFileService uploadFileService;
	@Autowired
	private KeyWordService keyWordService;
	@Autowired
	private WeChatGroupService weChatGroupService;
	@Autowired
	private PermissionDubboService permissionService;

	/**
	 * 事件处理入口
	 * 
	 * @param messageMap
	 * @return
	 */
	public String eventHandler(Map<String, String> messageMap,
			TencentUser tencentUser) {
		// 发送方帐号（open_id）
		String fromUserName = messageMap.get("FromUserName");
		// 公众帐号
		String toUserName = messageMap.get("ToUserName");
		// 事件类型
		String eventType = messageMap.get("Event");
		// 订阅
		if (eventType.equals(Constants.EVENT_TYPE_SUBSCRIBE)) {
			fanService.saveFan(messageMap, tencentUser);
			ThreadVariable.setUser(permissionService
					.findUserByUserName("admin"));
			boolean temp = weChatGroupService.saveWeChatGroup(tencentUser);// 更新微信分组及分组内粉丝数
			if (temp == true)
				logger.error(tencentUser.getWeChatUserId() + "分组信息同步成功！（关注微信号）");
			else
				logger.error(tencentUser.getWeChatUserId()
						+ "分组信息同步失败！ （关注微信号）");
			if (tencentUser != null) {
				boolean flag = false;
				if (tencentUser.getIsAppendKeyWord() == null
						|| tencentUser.getIsAppendKeyWord() == 2)
					flag = false;
				else if (tencentUser.getIsAppendKeyWord() == 1)
					flag = true;

				return createMessageXml(tencentUser.getSourceId(), flag,
						fromUserName, toUserName, tencentUser);
			}
		}
		// 取消订阅
		else if (eventType.equals(Constants.EVENT_TYPE_UNSUBSCRIBE)) {
			// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
			fanService.deleteFanByOpenIdAndWeChatUserId(
					messageMap.get("FromUserName"),
					tencentUser.getWeChatUserId());
			// JobHelper.createMockAdminSession();// 模拟session
			ThreadVariable.setUser(permissionService
					.findUserByUserName("admin"));
			boolean temp = weChatGroupService.saveWeChatGroup(tencentUser);// 更新微信分组及分组内粉丝数
			if (temp == true)
				logger.error(tencentUser.getWeChatUserId() + "分组信息同步成功！（关注微信号）");
			else
				logger.error(tencentUser.getWeChatUserId() + "分组信息同步失败！（关注微信号）");
			return null;
		}
		// 扫描带参数二维码事件
		else if (eventType.equals(Constants.EVENT_TYPE_SCAN)) {
			return MessageUtil.textMessageToXml(createErrorMessage(toUserName,
					fromUserName, "亲，扫描带参数二维码功能还在开发中， 请稍后在试！"));
		}// 上报地理位置事件
		else if (eventType.equals(Constants.EVENT_TYPE_LOCATION)) {
			return MessageUtil.textMessageToXml(createErrorMessage(toUserName,
					fromUserName, "亲，上报地理位置功能还在开发中， 请稍后在试！"));
		}
		// 自定义菜单点击事件
		else if (eventType.equals(Constants.EVENT_TYPE_CLICK)) {
			String eventKey = messageMap.get("EventKey");
			WeChatMenu weChatMenu = weChatMenuService
					.getWeChatMenuByMenuKeyAndWeChatUserId(eventKey, toUserName);
			if (weChatMenu != null) {
				String xmlData = createMessageXml(weChatMenu.getSourceID(),
						false, fromUserName, toUserName, tencentUser);
				if (xmlData != null)
					return xmlData;
			}
			return MessageUtil.textMessageToXml(createErrorMessage(toUserName,
					fromUserName, "亲，该菜单功能还在开发中， 请稍后在试！"));

		}
		return MessageUtil.textMessageToXml(createErrorMessage(toUserName,
				fromUserName, "亲，请求出了一丁点的问题， 请稍后在试！"));
	}

	/**
	 * 创建xml响应数据
	 * 
	 * @param sourceId
	 * @param isAppendKeyWord
	 * @param fromUserName
	 * @param toUserName
	 * @param tencentUser
	 * @return
	 */
	private String createMessageXml(String sourceId, Boolean isAppendKeyWord,
			String fromUserName, String toUserName, TencentUser tencentUser) {
		if (sourceId != null && !"".equals(sourceId)) {
			if (sourceId.indexOf(",") == -1) {
				WeChatSource source = weChatSourceService.getWeChatSource(Long
						.parseLong(sourceId));
				PropertyDict p = propertyDictService.getPropertyDictById(source
						.getSourceTypeDict().getId());
				if (!"".equals(p.getDisplayName())
						&& p.getDisplayName().equals("文本")) {
					TextMessage t = new TextMessage();
					t.setToUserName(fromUserName);
					if (isAppendKeyWord == true) {
						String temp = getKeyWordInfo(tencentUser
								.getWeChatUserId());
						if (temp != null)
							t.setContent(source.getContent() + "\n" + temp);
						else
							t.setContent(source.getContent());
					} else
						t.setContent(source.getContent());
					t.setFromUserName(toUserName);
					t.setMsgType(Constants.RESP_MESSAGE_TYPE_TEXT);
					t.setCreateTime(new Date().getTime());
					return MessageUtil.textMessageToXml(t);
				} else if (!"".equals(p.getDisplayName())
						&& p.getDisplayName().equals("图片")) {
					String imagePath = GridProperties.PROXY_SERVER_DOMAIN_NAME + source.getPath();
					// 上传图片
					WeiXinMedia media = uploadFileService.uploadMedia(
							weChatService.getAccessToken(tencentUser), "image",
							imagePath);
					if (media != null) {
						Image img = new Image();
						img.setMediaId(media.getMediaId());
						ImageMessage image = new ImageMessage();
						image.setCreateTime(new Date().getTime());
						image.setFromUserName(toUserName);
						image.setMsgType(Constants.RESP_MESSAGE_TYPE_IMAGE);
						image.setToUserName(fromUserName);
						image.setImage(img);
						return MessageUtil.imageMessageToXml(image);
					}
				} else if (!"".equals(p.getDisplayName())
						&& p.getDisplayName().equals("语音")) {
					String voicePath = GridProperties.PROXY_SERVER_DOMAIN_NAME + source.getPath();
					// 上传语音
					WeiXinMedia media = uploadFileService.uploadMedia(
							weChatService.getAccessToken(tencentUser), "voice",
							voicePath);
					if (media != null) {
						Voice voice = new Voice();
						voice.setMediaId(media.getMediaId());
						VoiceMessage voiceMessage = new VoiceMessage();
						voiceMessage.setCreateTime(new Date().getTime());
						voiceMessage.setFromUserName(toUserName);
						voiceMessage
								.setMsgType(Constants.RESP_MESSAGE_TYPE_VOICE);
						voiceMessage.setToUserName(fromUserName);
						voiceMessage.setVoice(voice);
						return MessageUtil.voiceMessageToXml(voiceMessage);
					}
				}
			} else {
				String ids = sourceId.substring(0, sourceId.lastIndexOf(","));
				// List<WeChatSource> sourceList =
				// weChatSourceService.getWeChatSourceByIds(ids);
				List<WeChatSource> sourceListTemp = weChatSourceService
						.getWeChatSourceByIds(ids);
				// 排序
				String[] idsTemp = ids.split(",");
				List<WeChatSource> sourceList = new ArrayList<WeChatSource>();
				for (int i = 0; i < idsTemp.length; i++) {
					for (int j = 0; j < sourceListTemp.size(); j++) {
						if (idsTemp[i].equals(sourceListTemp.get(j).getId()
								.toString()))
							sourceList.add(sourceListTemp.get(j));
					}
				}
				if (sourceList.size() == 0)
					return null;
				PropertyDict p = propertyDictService
						.getPropertyDictById(sourceList.get(0)
								.getSourceTypeDict().getId());
				if (!"".equals(p.getDisplayName())
						&& p.getDisplayName().equals("图文")) {
					// 创建图文消息
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(fromUserName);
					newsMessage.setFromUserName(toUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(Constants.RESP_MESSAGE_TYPE_NEWS);
					List<Article> articleList = new ArrayList<Article>();
					for (int i = 0; i < sourceList.size(); i++) {
						Article article = new Article();
						article.setTitle(sourceList.get(i).getTitle());
						article.setDescription(sourceList.get(i)
								.getDescription());
						article.setPicUrl(GridProperties.PROXY_SERVER_DOMAIN_NAME
								+ sourceList.get(i).getPath());
						article.setUrl(sourceList.get(i).getUrl());
						if (i == sourceList.size() - 1) {
							if (isAppendKeyWord == true) {
								String temp = getKeyWordInfo(tencentUser
										.getWeChatUserId());
								if (temp != null)
									article.setDescription(sourceList.get(i)
											.getDescription() + "\n" + temp);
								else
									article.setDescription(sourceList.get(i)
											.getDescription());
							} else {
								article.setDescription(sourceList.get(i)
										.getDescription());
							}
						}
						articleList.add(article);
					}
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					return MessageUtil.newsMessageToXml(newsMessage);
				}
			}
		}
		return null;
	}

	public String getKeyWordInfo(String weChatUserId) {
		List<KeyWord> list = keyWordService
				.getKeyWordByWeChatUserId(weChatUserId);
		if (list.size() > 0) {
			String tempInfo = "";
			for (KeyWord k : list) {
				tempInfo += "回复" + k.getKeyName() + "        "
						+ k.getKeyRemark() + "\n";
			}
			return tempInfo;
		}
		return null;

	}

	/**
	 * 创建缺省消息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public TextMessage createErrorMessage(String toUserName,
			String fromUserName, String content) {
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(Constants.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent(content);
		return textMessage;
	}

}
