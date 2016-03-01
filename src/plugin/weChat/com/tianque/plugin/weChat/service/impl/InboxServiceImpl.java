package com.tianque.plugin.weChat.service.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.weChat.dao.InboxDao;
import com.tianque.plugin.weChat.domain.WeiXinMedia;
import com.tianque.plugin.weChat.domain.inbox.Inbox;
import com.tianque.plugin.weChat.domain.inbox.InboxAttachment;
import com.tianque.plugin.weChat.domain.inbox.ReplyMessage;
import com.tianque.plugin.weChat.domain.sendMessage.Article;
import com.tianque.plugin.weChat.domain.sendMessage.News;
import com.tianque.plugin.weChat.domain.sendMessage.TextMessage;
import com.tianque.plugin.weChat.domain.sendMessage.text.TextSendMessage;
import com.tianque.plugin.weChat.domain.user.Fan;
import com.tianque.plugin.weChat.domain.user.KeyWord;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.proxy.service.BaseHttpClientService;
import com.tianque.plugin.weChat.service.FanService;
import com.tianque.plugin.weChat.service.InboxAttachmentService;
import com.tianque.plugin.weChat.service.InboxService;
import com.tianque.plugin.weChat.service.KeyWordService;
import com.tianque.plugin.weChat.service.ReplyMessageService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.UploadFileService;
import com.tianque.plugin.weChat.service.WeChatService;
import com.tianque.plugin.weChat.util.Constants;
import com.tianque.sysadmin.service.OrganizationDubboService;

/** 收件箱数据处理服务类 */
@Service("inboxService")
@Transactional
public class InboxServiceImpl extends AbstractBaseService implements
		InboxService {

	@Autowired
	private InboxDao inboxDao;
	@Autowired
	private WeChatService weChatService;
	@Autowired
	private InboxAttachmentService inboxAttachmentService;
	@Autowired
	private ReplyMessageService replyMessageService;
	@Autowired
	private FanService fanService;
	@Autowired
	private TencentUserService tencentUserService;
	@Autowired
	private KeyWordService keyWordService;
	@Autowired
	private OrganizationDubboService organizationService;
	@Autowired
	private BaseHttpClientService baseHttpClientService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private UploadFileService uploadFileService;

	@Override
	public PageInfo<Inbox> findInboxs(Inbox inbox, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		if ("createUser".equals(sidx))
			sidx = "create_User";
		else if ("createTime".equals(sidx))
			sidx = "create_Time";
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("inbox", inbox);
		PageInfo<Inbox> pageInfo = inboxDao.findInboxs(map, pageNum, pageSize);
		for (Inbox vo : pageInfo.getResult()) {
			vo.setInboxAttachments(inboxAttachmentService
					.getInboxAttachmentByInboxId(vo.getInboxId()));
			vo.setCount(replyMessageService.countRMByInboxId(vo.getInboxId()));
		}
		return pageInfo;
	}

	/**
	 * 保存消息（收件箱）
	 */
	@Override
	public Long saveInbox(Map<String, String> messageMap,
			TencentUser tencentUser) {
		String msgType = messageMap.get("MsgType");
		Long inboxId = saveInboxMain(messageMap, tencentUser);
		if (msgType.equals(Constants.REQ_MESSAGE_TYPE_IMAGE)
				|| msgType.equals(Constants.REQ_MESSAGE_TYPE_VOICE)) {
			String fileName = weChatService.downloadMedia(
					messageMap.get("MediaId"), tencentUser);
			InboxAttachment inboxAttachment = new InboxAttachment();
			inboxAttachment.setFileName(fileName);
			inboxAttachment.setExtFileName(fileName.substring(fileName
					.indexOf(".") + 1));
			inboxAttachment.setFileActualUrl("/uploadFile/weChat/" + fileName);
			inboxAttachment.setInboxId(inboxId);
			inboxAttachmentService.saveInboxAttachment(inboxAttachment);
		}
		return inboxId;
	}

	/** 保存收件箱主消息 */
	private Long saveInboxMain(Map<String, String> messageMap,
			TencentUser tencentUser) {
		Inbox inbox = new Inbox();
		inbox.setToUserName(messageMap.get("ToUserName"));
		inbox.setFromUserName(messageMap.get("FromUserName"));
		inbox.setMsgType(messageMap.get("MsgType"));
		inbox.setMsgId(Long.parseLong(messageMap.get("MsgId")));
		// inbox.setCreateTime(formatTime(messageMap.get("CreateTime")));
		// 存服务时间，不存微信发过来的时间，避免差异
		inbox.setCreateTime(new Date());
		inbox.setOrg(tencentUser.getOrganization());
		inbox.setAvailability("1");
		Organization org = organizationService.getSimpleOrgById(tencentUser
				.getOrganization().getId());
		if (org == null) {
			throw new ServiceException("组织机构为空");
		}
		inbox.setOrgInternalCode(org.getOrgInternalCode());
		Fan fan = fanService.getFanByOpenIdAndWeChatUserId(
				messageMap.get("FromUserName"), tencentUser.getWeChatUserId());
		if (fan == null) {
			fanService.saveFan(messageMap, tencentUser);
			fan = fanService.getFanByOpenIdAndWeChatUserId(
					messageMap.get("FromUserName"),
					tencentUser.getWeChatUserId());
		}
		inbox.setCreateUser(fan.getName());// 区别系统中的ccuu字段 存的是粉丝名
		inbox.setGroupId(fan.getGroupId());
		inbox.setForwardingState(Constants.NOT_FORWARD);
		inbox.setContent(messageMap.get("Content"));

		if (messageMap.get("Content") != null
				&& !"".equals(messageMap.get("Content"))) {
			KeyWord k = new KeyWord();
			k.setKeyName(messageMap.get("Content").trim());
			k.setWeChatUserId(messageMap.get("ToUserName"));
			KeyWord keyWord = keyWordService
					.validateKeyWordByWeChatUserIdAndKeyName(k);
			if (keyWord != null) {
				inbox.setIsKeyWordMsg(Constants.KEYWORDMSG);
			} else {
				// 不是关键字消息才判断该用户是否处于接入状态
				inbox.setIsKeyWordMsg(Constants.NOT_KEYWORDMSG);
				// 查询次消息是否被接入
				inbox.setDealState(Constants.ACCESS);
				// 新消息设置为未读
				inbox.setIsRead(Constants.NOT_READ);
				List<Inbox> inboxList = inboxDao.findAccessInboxList(inbox);
				if (inboxList == null || inboxList.isEmpty()) {
					// 为空转为未受理
					inbox.setDealState(Constants.NOT_ACCEPT);
				} else {
					inbox.setUpdateUser(inboxList.get(0).getUpdateUser());
					inbox.setUpdateDate(new Date());
				}
			}
		} else {
			inbox.setIsKeyWordMsg(Constants.NOT_KEYWORDMSG);
			// 查询次消息是否被接入
			inbox.setDealState(Constants.ACCESS);
			// 新消息设置为未读
			inbox.setIsRead(Constants.NOT_READ);
			List<Inbox> inboxList = inboxDao.findAccessInboxList(inbox);
			if (inboxList == null || inboxList.isEmpty()) {
				// 为空转为未受理
				inbox.setDealState(Constants.NOT_ACCEPT);
			} else {
				inbox.setUpdateUser(inboxList.get(0).getUpdateUser());
				inbox.setUpdateDate(new Date());
			}
		}
		if (messageMap.get("MsgType") != null
				&& Constants.REQ_MESSAGE_TYPE_LOCATION.equals(messageMap
						.get("MsgType"))) {
			inbox.setContent(messageMap.get("Label"));
			inbox.setLat(messageMap.get("Location_X"));
			inbox.setLng(messageMap.get("Location_Y"));
		}
		if (messageMap.get("MsgType") != null
				&& Constants.REQ_MESSAGE_TYPE_VOICE.equals(messageMap
						.get("MsgType"))) {
			inbox.setContent(messageMap.get("Recognition"));
		}
		return inboxDao.saveInbox(inbox);
	}

	private Date formatTime(String createTime) {
		Date date = new Date();
		try {
			long msgCreateTime = Long.parseLong(createTime) * 1000L;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = format.parse(format.format(new Date(msgCreateTime)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/****
	 * 设置有无效
	 */
	@Override
	public void setAvailabilityOrInvalid(List<Long> inboxId, String flag) {
		for (int i = 0; i < inboxId.size(); i++) {
			Inbox in = new Inbox();
			in.setInboxId(inboxId.get(i));
			in.setAvailability(flag);
			inboxDao.setAvailabilityOrInvalid(in);
		}

	}

	/**
	 * 根据微信号和粉丝号，修改粉丝所在的群组Id
	 * 
	 * @param inbox
	 */

	public Integer setGroupIdByWeChatUserIdAndFanId(Inbox inbox) {
		return inboxDao.setGroupIdByWeChatUserIdAndFanId(inbox);
	}

	/****
	 * 删除消息
	 */
	@Override
	public void deleteInboxById(List<Long> inboxId) {
		for (int i = 0; i < inboxId.size(); i++) {
			inboxAttachmentService.deleteInboxAttachmentByInboxId(inboxId
					.get(i));
			replyMessageService.deleteReplyMessageByInboxId(inboxId.get(i));
		}
		inboxDao.deleteInboxById(inboxId);
	}

	/**
	 * 事件处理调用该方法(微信不调用)
	 */
	@Override
	public int replyMessage(Inbox inbox, TextSendMessage textSendMessage) {
		textSendMessage.setMsgtype(Constants.REQ_MESSAGE_TYPE_TEXT);
		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(inbox.getToUserName());
		int resultCode = weChatService.replyMessage(textSendMessage,
				tencentUser);
		if (resultCode == 0) {
			saveReplyMessage(inbox, textSendMessage);
		}
		return resultCode;

	}

	/**
	 * 回复文本
	 * 
	 * @param inbox
	 * @param textSendMessage
	 * @return
	 */
	@Override
	public String replyTextMessage(Inbox inbox, TextMessage textMessage) {
		if (inbox.getToUserName() == null || "".equals(inbox.getToUserName()))
			throw new ServiceException("回复文本消息时，服务号不能为空！");
		textMessage.setMsgType(Constants.REQ_MESSAGE_TYPE_TEXT);
		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(inbox.getToUserName());// getToUserName()
																		// 开发者微信号
		if (tencentUser == null)
			throw new ServiceException("回复文本消息时，服务号获取失败！");
		List<TextMessage> listTextMessage = new ArrayList<TextMessage>();
		listTextMessage.add(textMessage);
		String result = weChatService.replyTextMessage(listTextMessage,
				tencentUser);
		if (result == null && !"null".equals(result)) {
			saveReplyMessage(inbox, textMessage);
			return result;
		} else {
			throw new ServiceException(result);
		}
	}

	@Override
	public String replyMoreFortyEightTextMessage(Inbox inbox,
			TextMessage textMessage) {
		if (inbox.getToUserName() == null || "".equals(inbox.getToUserName()))
			throw new ServiceException("回复文本消息时，服务号不能为空！");
		textMessage.setMsgType(Constants.REQ_MESSAGE_TYPE_TEXT);
		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(inbox.getToUserName());// getToUserName()
																		// 开发者微信号
		if (tencentUser == null)
			throw new ServiceException("回复文本消息时，服务号获取失败！");
		List<TextMessage> listTextMessage = new ArrayList<TextMessage>();
		listTextMessage.add(textMessage);
		String result = weChatService.replyMoreFortyEightTextMessage(
				listTextMessage, tencentUser);
		if (result == null && !"null".equals(result)) {
			saveReplyMessage(inbox, textMessage);
			return result;
		} else {
			throw new ServiceException(result);
		}
	}

	/******* 转发文本（支持群转发） **/
	public String retransmissionTextMessage(Inbox inbox,
			TextMessage textMessage, String openIds) {
		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(inbox.getToUserName());// getToUserName()
																		// 开发者微信号
		if (tencentUser == null)
			throw new ServiceException("转发文本消息时，服务号获取失败！");
		List<TextMessage> listTextMessage = getListTextMessage(inbox,
				textMessage, openIds, tencentUser);
		if (listTextMessage.size() > 0) {
			String result = weChatService.replyTextMessage(listTextMessage,
					tencentUser);
			if (result == null && !"null".equals(result)) {
				return result;
			} else {
				throw new ServiceException(result);
			}
		} else {
			throw new ServiceException("转发文本消息时，转发粉丝不能为空！");
		}
	}

	/******* 转发图片（支持群转发） **/
	public String retransmissionImageMessage(Inbox inbox,
			TextMessage textMessage, String openIds, String pathImage) {
		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(inbox.getToUserName());// getToUserName()
																		// 开发者微信号
		if (tencentUser == null)
			throw new ServiceException("转发图片消息时，服务号获取失败！");
		List<TextMessage> listTextMessage = getListTextMessage(inbox,
				textMessage, openIds, tencentUser);
		File file = new File(FileUtil.getWebRoot() + pathImage);
		if (!file.exists())
			throw new ServiceException("图片文件不存在，操件失败");
		if (listTextMessage.size() > 0) {
			String path = GridProperties.PROXY_SERVER_DOMAIN_NAME + pathImage;
			String result = weChatService.replyImageMessage(listTextMessage,
					tencentUser, path);
			if (result == null && !"null".equals(result))
				return result;
			else
				throw new ServiceException(result);
		} else {
			throw new ServiceException("转发图片消息时，转发粉丝不能为空！");
		}
	}

	/******* 回复图片 **/
	@Override
	public String replyImageMessage(Inbox inbox, TextMessage textMessage,
			Set<String> attachFiles) {
		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(inbox.getToUserName());// getToUserName()
																		// 开发者微信号
		if (tencentUser == null)
			throw new ServiceException("回复图片消息时，服务号获取失败！");
		String result = null;
		for (String fileNameAndId : attachFiles) {
			String fileName = fileNameAndId.substring(1);
			File file = new File(FileUtil.getWebRoot() + File.separator + GridProperties.TMP
					+ File.separator + (int)(Math.floor(ThreadVariable.getUser().getId()/10)) + File.separator + fileName);
			if (!file.exists())
				throw new ServiceException("图片文件不存在，操件失败");
			if (fileName.contains(" "))
				throw new ServiceException("请上传文件名不含空格的文件");
			String path = GridProperties.PROXY_SERVER_DOMAIN_NAME + "/uploadFile/tmp/"
					+ (int)(Math.floor(ThreadVariable.getUser().getId()/10)) + "/" + fileName;
			List<TextMessage> listTextMessages = new ArrayList<TextMessage>();
			listTextMessages.add(textMessage);
			result = weChatService.replyImageMessage(listTextMessages,
					tencentUser, path);
			if (result == null) {
				if (file.exists())
					file.delete();
			}
		}
		if (result != null)
			throw new ServiceException(result);
		else
			return result;
	}

	@Override
	public String replyMoreFortyEightImageMessage(Inbox inbox,
			TextMessage textMessage, Set<String> attachFiles) {
		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(inbox.getToUserName());// getToUserName()
																		// 开发者微信号
		if (tencentUser == null)
			throw new ServiceException("回复图片消息时，服务号获取失败！");
		String result = null;
		for (String fileNameAndId : attachFiles) {
			String fileName = fileNameAndId.substring(1);
			File file = new File(FileUtil.getWebRoot() + File.separator + GridProperties.TMP
					+ File.separator + (int)(Math.floor(ThreadVariable.getUser().getId()/10)) + File.separator + fileName);
			if (!file.exists())
				throw new ServiceException("图片文件不存在，操件失败");
			if (fileName.contains(" "))
				throw new ServiceException("请上传文件名不含空格的文件");
			String path = GridProperties.PROXY_SERVER_DOMAIN_NAME + "/uploadFile/tmp/"
					+ (int)(Math.floor(ThreadVariable.getUser().getId()/10)) + "/" + fileName;
			List<TextMessage> listTextMessages = new ArrayList<TextMessage>();
			listTextMessages.add(textMessage);
			result = weChatService.replyMoreFortyEightImageMessage(
					listTextMessages, tencentUser, path);
			if (result == null) {
				if (file.exists())
					file.delete();
			}
		}
		if (result != null)
			throw new ServiceException(result);
		else
			return result;
	}

	/******* 回复图文 **/
	@Override
	public String replyNewsMessage(Inbox inbox, TextMessage textMessage,
			Set<String> attachFiles, Article article) {
		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(inbox.getToUserName());// getToUserName()
																		// 开发者微信号
		if (tencentUser == null)
			throw new ServiceException("回复图文消息时，服务号获取失败！");
		String result = null;
		try {
			for (String fileNameAndId : attachFiles) {
				String fileName = fileNameAndId.substring(1);
				File file = new File(FileUtil.getWebRoot() + File.separator + GridProperties.TMP
						+ File.separator + (int)(Math.floor(ThreadVariable.getUser().getId()/10)) + File.separator
						+ fileName);
				if (!file.exists())
					throw new ServiceException("图片文件不存在，操件失败");
				if (fileName.contains(" "))
					throw new ServiceException("请上传文件名不含空格的文件");
				StoredFile s = FileUtil.copyTmpFileToStoredFile(fileName,
						Constants.WECHAT_ATTACHFILE);
				String picUrl = (GridProperties.PROXY_SERVER_DOMAIN_NAME
						+ File.separator + s.getFullName()).replaceAll("\\\\",
						"/");
				List<Article> listArticles = new ArrayList<Article>();
				article.setPicUrl(picUrl);
				listArticles.add(article);
				List<TextMessage> listTextMessage = new ArrayList<TextMessage>();
				listTextMessage.add(textMessage);
				result = weChatService.replyNewsMessage(listTextMessage,
						tencentUser, listArticles);
			}
		} catch (Exception e) {
		}
		if (result != null)
			throw new ServiceException(result);
		else
			return result;
	}

	@Override
	public String replyMoreFortyEightNewsMessage(Inbox inbox,
			TextMessage textMessage, Set<String> attachFiles, Article article) {
		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(inbox.getToUserName());// getToUserName()
																		// 开发者微信号
		if (tencentUser == null)
			throw new ServiceException("回复图文消息时，服务号获取失败！");
		String result = null;
		try {
			String accessToken = getAccessToken(tencentUser);
			for (String fileNameAndId : attachFiles) {
				String fileName = fileNameAndId.substring(1);
				File file = new File(FileUtil.getWebRoot() + File.separator + GridProperties.TMP
						+ File.separator + (int)(Math.floor(ThreadVariable.getUser().getId()/10)) + File.separator
						+ fileName);
				if (!file.exists())
					throw new ServiceException("图片文件不存在，操件失败");
				if (fileName.contains(" "))
					throw new ServiceException("请上传文件名不含空格的文件");
				StoredFile s = FileUtil.copyTmpFileToStoredFile(fileName,
						Constants.WECHAT_ATTACHFILE);
				String picUrl = (GridProperties.PROXY_SERVER_DOMAIN_NAME
						+ File.separator + s.getFullName()).replaceAll("\\\\",
						"/");

				// 上传图片
				WeiXinMedia mediaTemp = uploadFileService.uploadMedia(
						accessToken, "image", picUrl);
				if (mediaTemp == null) {
					logger.error("发送图片消息时上传图片失败");
					return "发送图片消息时上传图片失败";
				}
				List<News> newslist = new ArrayList<News>();
				News news = new News();
				news.setTitle(article.getTitle());
				news.setContentSourceUrl(article.getUrl());
				news.setDigest(article.getDescription());
				news.setThumbMediaId(mediaTemp.getMediaId());
				news.setContent("");
				newslist.add(news);
				List<TextMessage> listTextMessage = new ArrayList<TextMessage>();
				listTextMessage.add(textMessage);
				result = weChatService.replyMoreFortyEightNewsMessage(
						listTextMessage, tencentUser, newslist);
			}
		} catch (Exception e) {
		}
		if (result != null)
			throw new ServiceException(result);
		else
			return result;
	}

	/******* 回复语音 **/
	@Override
	public String replyVoiceMessage(Inbox inbox, TextMessage textMessage,
			Set<String> attachFiles) {
		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(inbox.getToUserName());// getToUserName()
																		// 开发者微信号
		if (tencentUser == null)
			throw new ServiceException("回复语音消息时，服务号获取失败！");
		String result = null;
		for (String fileNameAndId : attachFiles) {
			String fileName = fileNameAndId.substring(1);
			File file = new File(FileUtil.getWebRoot() + File.separator + GridProperties.TMP
					+ File.separator + (int)(Math.floor(ThreadVariable.getUser().getId()/10)) + File.separator + fileName);
			if (!file.exists())
				throw new ServiceException("语音文件不存在，操件失败");
			if (fileName.contains(" "))
				throw new ServiceException("请上传文件名不含空格的文件");
			String path = GridProperties.PROXY_SERVER_DOMAIN_NAME + "/uploadFile/tmp/"
					+ (int)(Math.floor(ThreadVariable.getUser().getId()/10)) + "/" + fileName;
			List<TextMessage> listTextMessage = new ArrayList<TextMessage>();
			listTextMessage.add(textMessage);
			result = weChatService.replyVoiceMessage(listTextMessage,
					tencentUser, path);
			if (result == null) {
				if (file.exists())
					file.delete();
			}
		}
		if (result != null)
			throw new ServiceException(result);
		else
			return result;

	}

	@Override
	public String replyMoreFortyEightVoiceMessage(Inbox inbox,
			TextMessage textMessage, Set<String> attachFiles) {
		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(inbox.getToUserName());// getToUserName()
																		// 开发者微信号
		if (tencentUser == null)
			throw new ServiceException("回复语音消息时，服务号获取失败！");
		String result = null;
		for (String fileNameAndId : attachFiles) {
			String fileName = fileNameAndId.substring(1);
			File file = new File(FileUtil.getWebRoot() + File.separator + GridProperties.TMP
					+ File.separator + (int)(Math.floor(ThreadVariable.getUser().getId()/10)) + File.separator + fileName);
			if (!file.exists())
				throw new ServiceException("语音文件不存在，操件失败");
			if (fileName.contains(" "))
				throw new ServiceException("请上传文件名不含空格的文件");
			String path = GridProperties.PROXY_SERVER_DOMAIN_NAME + "/uploadFile/tmp/"
					+ (int)(Math.floor(ThreadVariable.getUser().getId()/10)) + "/" + fileName;
			List<TextMessage> listTextMessage = new ArrayList<TextMessage>();
			listTextMessage.add(textMessage);
			result = weChatService.replyMoreFortyEightVoiceMessage(
					listTextMessage, tencentUser, path);
			if (result == null) {
				if (file.exists())
					file.delete();
			}
		}
		if (result != null)
			throw new ServiceException(result);
		else
			return result;

	}

	/***
	 * 转发语音
	 */
	public String retransmissionVoiceMessage(Inbox inbox,
			TextMessage textMessage, String openIds, String pathImage) {
		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(inbox.getToUserName());// getToUserName()
																		// 开发者微信号
		if (tencentUser == null)
			throw new ServiceException("转发语音消息时，服务号获取失败！");
		List<TextMessage> listTextMessage = getListTextMessage(inbox,
				textMessage, openIds, tencentUser);
		File file = new File(FileUtil.getWebRoot() + pathImage);
		if (!file.exists())
			throw new ServiceException("语音文件不存在，操件失败");
		if (listTextMessage.size() > 0) {
			String path = GridProperties.PROXY_SERVER_DOMAIN_NAME + pathImage;
			String result = weChatService.replyVoiceMessage(listTextMessage,
					tencentUser, path);
			if (result == null && !"null".equals(result))
				return result;
			else
				throw new ServiceException(result);
		} else {
			throw new ServiceException("转发语音消息时，转发粉丝不能为空！");
		}
	}

	/**
	 * 保存回复消息 事件处理调用该方法
	 */
	@Override
	public Long saveReplyMessage(Inbox inbox, TextSendMessage textSendMessage) {
		ReplyMessage replyMessage = new ReplyMessage();
		replyMessage.setFromUserName(inbox.getFromUserName());
		replyMessage.setServiceId(inbox.getServiceId());
		replyMessage.setContent(textSendMessage.getText().getContent());
		replyMessage.setReceiveUser(inbox.getCreateUser());// 区别系统中的ccuu字段
															// 存的是粉丝名
		replyMessage.setCreateDate(new Date());
		replyMessage.setCreateUser(ThreadVariable.getUser().getUserName());
		replyMessage.setWechatUserId(inbox.getToUserName());
		replyMessage.setIsIssue(Constants.ACCEPT);
		return replyMessageService.saveReplyMessage(replyMessage);
	}

	/**
	 * 保存回复消息
	 */

	public Long saveReplyMessage(Inbox inbox, TextMessage textMessage) {
		ReplyMessage replyMessage = new ReplyMessage();
		// replyMessage.setInboxId(inbox.getInboxId());

		if (inbox.getToUserName() == null || "".equals(inbox.getToUserName())) {
			throw new ServiceException("回复文本消息时，服务号不能为空！");
		}
		if (inbox.getFromUserName() == null
				|| "".equals(inbox.getFromUserName())) {
			throw new ServiceException("回复文本消息时，用户openId不能为空！");
		}
		if (inbox.getCreateUser() == null || "".equals(inbox.getCreateUser())) {
			throw new ServiceException("回复文本消息时，用户微信昵称不能为空！");
		}
		if (textMessage.getContent() == null
				|| "".equals(textMessage.getContent())) {
			throw new ServiceException("回复文本消息时，回复内容不能为空！");
		}
		if (textMessage.getIsIssue() == null
				|| "".equals(textMessage.getIsIssue())) {
			// 回复消息相关发送者发送的消息是否转为事件(0为否,1为是)
			replyMessage.setIsIssue(0L);
		} else {
			replyMessage.setIsIssue(textMessage.getIsIssue());
		}

		replyMessage.setFromUserName(inbox.getFromUserName());
		replyMessage.setContent(textMessage.getContent());
		replyMessage.setReceiveUser(inbox.getCreateUser());// 区别系统中的ccuu字段
															// 存的是粉丝名
		replyMessage.setCreateDate(new Date());
		replyMessage.setCreateUser(ThreadVariable.getUser().getUserName());
		replyMessage.setWechatUserId(inbox.getToUserName());
		return replyMessageService.saveReplyMessage(replyMessage);
	}

	/***
	 * 转发时提取转发的粉丝
	 * 
	 * @param inbox
	 * @param textMessage
	 * @param openIds
	 * @return
	 */
	private List<TextMessage> getListTextMessage(Inbox inbox,
			TextMessage textMessage, String openIds, TencentUser tencentUser) {
		if (inbox.getToUserName() == null || "".equals(inbox.getToUserName()))
			throw new ServiceException("转送文本消息时，服务号不能为空！");
		List<TextMessage> listTextMessage = new ArrayList<TextMessage>();
		if (openIds.startsWith("fan_")) {
			String openId = openIds.replaceAll("fan_", "");
			String[] openIdArray = openId.split(",");
			for (int i = 0; i < openIdArray.length; i++) {
				TextMessage t = new TextMessage();
				t.setContent(textMessage.getContent());
				t.setToUserName(openIdArray[i]);
				listTextMessage.add(t);
			}
		}
		if (openIds.startsWith("group_")) {
			String groupIds = openIds.replaceAll("group_", "");
			List<Fan> fanList = fanService.getFanListByGroupIdsAndWeChatUserId(
					groupIds, tencentUser.getWeChatUserId());
			if (fanList.size() > 0) {
				for (int i = 0; i < fanList.size(); i++) {
					TextMessage t = new TextMessage();
					t.setContent(textMessage.getContent());
					t.setToUserName(fanList.get(i).getOpenId());
					listTextMessage.add(t);
				}
			} else {
				throw new ServiceException("转送文本消息时，转送群组不能为空！");
			}
		}
		return listTextMessage;
	}

	/**
	 * 获取公众号的全局唯一票据
	 */
	private String getAccessToken(TencentUser tencentUser) {
		String accessToken = (String) cacheService.get("weChatAccessToken"
				+ tencentUser.getWeChatUserId());
		if (accessToken != null && "代理错误".equals(accessToken)) {
			accessToken = null;
			cacheService.remove("weChatAccessToken"
					+ tencentUser.getWeChatUserId());
		}
		if (accessToken == null) {
			String appid = tencentUser.getAppId();
			String secret = tencentUser.getAppSecret();
			String url = Constants.ACCESS_TOKEN_URL.replace("APPID", appid)
					.replace("APPSECRET", secret)
					+ "&requestType=getAccessToken";
			accessToken = baseHttpClientService.postMethod(url);
			cacheService.set(
					"weChatAccessToken" + tencentUser.getWeChatUserId(), 7100,
					accessToken);
		}
		return accessToken;

	}

	@Override
	public Inbox getInboxById(Long id) {
		return inboxDao.getInboxById(id);
	}

	@Override
	public void update(Inbox inbox) {
		inboxDao.update(inbox);
	}

	@Override
	public Long getMaxInboxId() {
		return inboxDao.getMaxInboxId();
	}

	@Override
	public PageInfo<Inbox> findUntreatedInboxs(Inbox inbox, Integer pageNum,
			Integer pageSize, String sidx, String sord) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("inbox", inbox);
		PageInfo<Inbox> pageInfo = inboxDao.findUntreatedInboxs(map, pageNum,
				pageSize);

		if (pageInfo != null) {
			List<Inbox> list = pageInfo.getResult();
			if (list != null && !list.isEmpty()) {
				//这是循环所有用户
				for (Inbox inbox2 : list) {
					
					inbox2.setIsRead(Constants.NOT_READ);
					inbox2.setDealState(Constants.NOT_ACCEPT);
					inbox2.setIsKeyWordMsg(Constants.NOT_KEYWORDMSG);
					List<Inbox> oldList = inboxDao
							.findInboxsByOpenIdAndDealState(inbox2);
					
					if(oldList==null||oldList.isEmpty()){
						inbox2.setIsRead(Constants.READ);
					}
				}
				pageInfo.setResult(list);		
			}
		}
		return pageInfo;
	}

	@Override
	public void updateDealState(String openIds, Inbox inbox, Long oldDealState) {

		if (openIds == null) {
			throw new ServiceException("操作失败，未获取到关键参数!");
		}
		String[] openidArray = openIds.split(",");
		if (openidArray == null || openidArray.length == 0 || inbox == null
				|| inbox.getDealState() == null
				|| inbox.getIsKeyWordMsg() == null || oldDealState == null) {
			throw new ServiceException("操作失败，未获取到关键参数!");
		}

		for (String openId : openidArray) {
			Map<String, Object> map = new HashMap<String, Object>();
			inbox.setFromUserName(openId);
			inbox.setUpdateUser(ThreadVariable.getUser().getUserName());
			inbox.setUpdateDate(new Date());

			map.put("inbox", inbox);
			map.put("oldDealState", oldDealState);
			inboxDao.updateDealState(map);
		}
	}

	@Override
	public List<Inbox> findInboxsAndReplyMessages(Inbox inbox) {
		/*	*//** 查询社管与单个用户聊天内容 */
		/*
		 * public List<Inbox> findInboxsAndReplyMessages(Inbox inbox);
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("inbox", inbox);
		// 未转为事件,查询回复消息使用
		map.put("isIssue", 0l);
		List<Inbox> inboxList = inboxDao.findInboxsAndReplyMessages(map);

		for (Inbox inbox2 : inboxList) {
			inbox2.setInboxAttachments(inboxAttachmentService
					.getInboxAttachmentByInboxId(inbox2.getInboxId()));
		}

		return inboxList;
	}

	@Override
	public List<Inbox> findInboxsByOpenIdAndDealState(Inbox inbox) {
		if(inbox.getDealState()==null){
			throw new ServiceException("查询失败，未获取到关键参数!");
		}

		return inboxDao.findInboxsByOpenIdAndDealState(inbox);
	}

	@Override
	public void deleteInboxsByOpenIdAndDealState(String openIds, Inbox inbox) {
		if (openIds == null) {
			throw new ServiceException("删除失败，未获取到关键参数!");
		}
		String[] openidArray = openIds.split(",");
		if (openidArray == null || openidArray.length == 0 || inbox == null
				|| inbox.getDealState() == null) {
			throw new ServiceException("删除失败，未获取到关键参数!");
		}

		for (String openId : openidArray) {

			inbox.setFromUserName(openId);
			inbox.setDealState(inbox.getDealState());
			inboxDao.deleteInboxsByOpenIdAndDealState(inbox);
		}
	}

	@Override
	public void inboxRollOutByOpenIdAndDealState(Inbox inbox) {
		if (inbox.getFromUserName() == null) {
			throw new ServiceException("转出失败，未获取到关键参数!");
		}
		if (inbox.getDealState() == null) {
			inbox.setDealState(0L);
		}
		inbox.setUpdateDate(new Date());
		inbox.setUpdateUser(ThreadVariable.getUser().getUserName());
		inboxDao.inboxRollOutByOpenIdAndDealState(inbox);
	}

	@Override
	public Inbox findLastInboxByFromUserName(Inbox inbox) {

		if (inbox == null || inbox.getFromUserName() == null
				|| inbox.getDealState() == null) {
			throw new ServiceException("回复失败，未获取到关键参数!");
		}
		return inboxDao.findLastInboxByFromUserName(inbox);
	}

	@Override
	public void updateIsRead(Inbox inbox) {	
		if (inbox == null || inbox.getFromUserName() == null) {
			throw new ServiceException("修改失败，未获取到关键参数!");
		}
		inbox.setIsRead(Constants.READ);
		inboxDao.updateIsRead(inbox);
	}
}
