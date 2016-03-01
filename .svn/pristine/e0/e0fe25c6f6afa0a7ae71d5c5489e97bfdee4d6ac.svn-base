package com.tianque.plugin.weChat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.GridProperties;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.weChat.dao.KeyWordDao;
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
import com.tianque.plugin.weChat.domain.user.WeChatSource;
import com.tianque.plugin.weChat.service.KeyWordService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.UploadFileService;
import com.tianque.plugin.weChat.service.WeChatService;
import com.tianque.plugin.weChat.service.WeChatSourceService;
import com.tianque.plugin.weChat.util.Constants;
import com.tianque.plugin.weChat.util.MessageUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

/**关键字处理服务类*/
@Service("keyWordService")
@Transactional
public class KeyWordServiceImpl extends AbstractBaseService implements KeyWordService {

	@Autowired
	private KeyWordDao keyWordDao;
	@Autowired
	private OrganizationDubboService organizationService;
	@Autowired
	private WeChatSourceService weChatSourceService;
	@Autowired
	private PropertyDictDubboService propertyDictService;
	@Autowired
	private UploadFileService uploadFileService;
	@Autowired
	private WeChatService weChatService;
	@Autowired
	private TencentUserService tencentUserService;

	/**
	 * 关键字列表
	 * @param keyWord
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<KeyWord> findKeyWord(KeyWord keyWord, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		if ("keyName".equals(sidx))
			sidx = "key_name";
		if ("sourceTypeDict.id".equals(sidx))
			sidx = "source_type";
		if ("sourceDescription".equals(sidx))
			sidx = "source_description";
		if ("sourceId".equals(sidx))
			sidx = "source_id";
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("keyWord", keyWord);
		PageInfo<KeyWord> pageInfo = keyWordDao.findKeyWord(map, pageNum, pageSize);
		return pageInfo;

	}

	/**
	 * 添加关键词
	 * @param keyWord
	 * @return
	 */
	public Long addKeyWord(KeyWord keyWord) {
		Organization org = organizationService.getSimpleOrgById(keyWord.getOrg().getId());
		keyWord.setOrg(org);
		if (getCountByWeChatUserId(keyWord.getWeChatUserId()) >= Constants.KEYWORD_COUNT) {
			throw new ServiceException("微信号：[" + keyWord.getWeChatUserId() + "] 已经添加了"
					+ Constants.KEYWORD_COUNT + "个关键字，不能再添加了!");
		} else {
			return keyWordDao.addKeyWord(keyWord);
		}
	}

	/**
	 * 验证关键词存在（微信号和关键字加载实体）
	 * @param keyWord
	 * @return
	 */
	public KeyWord validateKeyWordByWeChatUserIdAndKeyName(KeyWord keyWord) {
		return keyWordDao.validateKeyWordByWeChatUserIdAndKeyName(keyWord);
	}

	/**
	 * 关键字自动回复
	 * @param keyName
	 * @param weChatUserId
	 * @param fromUser
	 * @return
	 */
	public String receiveMessageByKeyWord(String keyName, String weChatUserId, String fromUser) {
		if (keyName == null && "".equals(keyName))
			return null;
		KeyWord k = new KeyWord();
		k.setKeyName(keyName.trim());
		k.setWeChatUserId(weChatUserId);
		KeyWord keyWord = validateKeyWordByWeChatUserIdAndKeyName(k);
		if (keyWord == null)
			return null;
		else {
			if (keyWord.getSourceId() != null && !"".equals(keyWord.getSourceId())) {
				if (keyWord.getSourceId().indexOf(",") == -1) {
					WeChatSource source = weChatSourceService.getWeChatSource(Long
							.parseLong(keyWord.getSourceId()));
					if (source == null)
						return null;
					PropertyDict p = propertyDictService.getPropertyDictById(source
							.getSourceTypeDict().getId());
					if (!"".equals(p.getDisplayName()) && p.getDisplayName().equals("文本")) {
						TextMessage t = new TextMessage();
						t.setToUserName(fromUser);
						t.setContent(source.getContent());
						t.setFromUserName(weChatUserId);
						t.setMsgType(Constants.RESP_MESSAGE_TYPE_TEXT);
						t.setCreateTime(new Date().getTime());
						return MessageUtil.textMessageToXml(t);
					} else if (!"".equals(p.getDisplayName()) && p.getDisplayName().equals("图片")) {
						TencentUser tencentUser = tencentUserService
								.getTencentUserByWeChatUserId(weChatUserId);
						String imagePath = GridProperties.PROXY_SERVER_DOMAIN_NAME
								+ source.getPath();
						//上传图片
						WeiXinMedia media = uploadFileService.uploadMedia(
								weChatService.getAccessToken(tencentUser), "image", imagePath);
						if (media != null) {
							Image img = new Image();
							img.setMediaId(media.getMediaId());
							ImageMessage image = new ImageMessage();
							image.setCreateTime(new Date().getTime());
							image.setFromUserName(weChatUserId);
							image.setMsgType(Constants.RESP_MESSAGE_TYPE_IMAGE);
							image.setToUserName(fromUser);
							image.setImage(img);
							return MessageUtil.imageMessageToXml(image);
						} else {
							return MessageUtil.textMessageToXml(createErrorMessage(fromUser,
									weChatUserId, "亲，网络有点不给力， 请稍后在试！"));
						}
					} else if (!"".equals(p.getDisplayName()) && p.getDisplayName().equals("语音")) {
						TencentUser tencentUser = tencentUserService
								.getTencentUserByWeChatUserId(weChatUserId);
						String voicePath = GridProperties.PROXY_SERVER_DOMAIN_NAME
								+ source.getPath();
						//上传语音
						WeiXinMedia media = uploadFileService.uploadMedia(
								weChatService.getAccessToken(tencentUser), "voice", voicePath);
						if (media != null) {
							Voice voice = new Voice();
							voice.setMediaId(media.getMediaId());
							VoiceMessage voiceMessage = new VoiceMessage();
							voiceMessage.setCreateTime(new Date().getTime());
							voiceMessage.setFromUserName(weChatUserId);
							voiceMessage.setMsgType(Constants.RESP_MESSAGE_TYPE_VOICE);
							voiceMessage.setToUserName(fromUser);
							voiceMessage.setVoice(voice);
							return MessageUtil.voiceMessageToXml(voiceMessage);
						} else {
							return MessageUtil.textMessageToXml(createErrorMessage(fromUser,
									weChatUserId, "亲，网络有点不给力， 请稍后在试！"));
						}
					} else {
						return null;
					}
				} else {
					String ids = keyWord.getSourceId().substring(0,
							keyWord.getSourceId().lastIndexOf(","));
					//List<WeChatSource> sourceList = weChatSourceService.getWeChatSourceByIds(ids);
					List<WeChatSource> sourceListTemp = weChatSourceService
							.getWeChatSourceByIds(ids);
					//排序
					String[] idsTemp = ids.split(",");
					List<WeChatSource> sourceList = new ArrayList<WeChatSource>();
					for (int i = 0; i < idsTemp.length; i++) {
						System.err.println(idsTemp[i]);
						for (int j = 0; j < sourceListTemp.size(); j++) {
							if (idsTemp[i].equals(sourceListTemp.get(j).getId().toString()))
								sourceList.add(sourceListTemp.get(j));
						}
					}

					if (sourceList.size() == 0)
						return null;
					PropertyDict p = propertyDictService.getPropertyDictById(sourceList.get(0)
							.getSourceTypeDict().getId());
					if (!"".equals(p.getDisplayName()) && p.getDisplayName().equals("图文")) {
						// 创建图文消息  
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUser);
						newsMessage.setFromUserName(weChatUserId);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(Constants.RESP_MESSAGE_TYPE_NEWS);
						List<Article> articleList = new ArrayList<Article>();
						for (int i = 0; i < sourceList.size(); i++) {
							Article article = new Article();
							article.setTitle(sourceList.get(i).getTitle());
							article.setDescription(sourceList.get(i).getDescription());
							article.setPicUrl(GridProperties.PROXY_SERVER_DOMAIN_NAME
									+ sourceList.get(i).getPath());
							article.setUrl(sourceList.get(i).getUrl());
							articleList.add(article);
						}
						// 设置图文消息个数  
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息包含的图文集合  
						newsMessage.setArticles(articleList);
						return MessageUtil.newsMessageToXml(newsMessage);
					} else {
						return null;
					}
				}
			}
			return null;
		}

	}

	/**
	 * 修改关键词
	 * @param keyWord
	 * @return
	 */
	public Integer updateKeyWord(KeyWord keyWord) {
		return keyWordDao.updateKeyWord(keyWord);
	}

	/**
	 * 创建缺省消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public TextMessage createErrorMessage(String toUserName, String fromUserName, String content) {
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(toUserName);
		textMessage.setFromUserName(fromUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(Constants.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent(content);
		return textMessage;
	}

	/**
	 * 根据sourceId修改关键字表中的素材描述
	 * @param keyWord
	 * @return
	 */
	public Integer updateKeyWordBySourceId(KeyWord keyWord) {
		return keyWordDao.updateKeyWordBySourceId(keyWord);
	}

	/**
	 * 删除关键词
	 * @param id
	 * @return
	 */
	public Integer deleteKeyWord(String ids) {
		String[] idArray = ids.split(",");
		int flag = 0;
		for (int i = 0; i < idArray.length; i++) {
			flag += keyWordDao.deleteKeyWord(Long.parseLong(idArray[i]));
		}
		return flag;
	}

	/**
	 * 根据服务号删除关键词
	 * @param weChatUserId
	 * @return
	 */
	public Integer deleteKeyWordByWeChatUserId(String weChatUserId) {
		return keyWordDao.deleteKeyWordByWeChatUserId(weChatUserId);
	}

	/**
	 * 加载关键词对象
	 * @param id
	 * @return
	 */
	public KeyWord getKeyWordById(Long id) {
		return keyWordDao.getKeyWordById(id);
	}

	/**
	 * 根据微信号获取关键词总数
	 * @param weChatUserId
	 * @return
	 */
	public Long getCountByWeChatUserId(String weChatUserId) {
		return keyWordDao.getCountByWeChatUserId(weChatUserId);
	}

	/**
	 * 根据素材id加载关键字（条件 like）
	 * @param sourceId
	 * @return
	 */
	public List<KeyWord> getKeyWordBySourceId(String sourceId) {
		return keyWordDao.getKeyWordBySourceId(sourceId);
	}

	/**
	 * 根据服务号获取关键字集合
	 * @param weChatUserId
	 * @return
	 */
	public List<KeyWord> getKeyWordByWeChatUserId(String weChatUserId) {
		return keyWordDao.getKeyWordByWeChatUserId(weChatUserId);
	}

}
