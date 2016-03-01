package com.tianque.plugin.weChat.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueType;
import com.tianque.domain.MyGroup;
import com.tianque.domain.Organization;
import com.tianque.domain.WorkContacter;
import com.tianque.issue.controller.strategy.IssueManageStrategy;
import com.tianque.issue.domain.Issue;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.factory.IssueManageStrategyFactory;
import com.tianque.issue.vo.IssueTypeViewDefine;
import com.tianque.plugin.weChat.domain.inbox.Inbox;
import com.tianque.plugin.weChat.domain.inbox.InboxAttachment;
import com.tianque.plugin.weChat.domain.inbox.ReplyMessage;
import com.tianque.plugin.weChat.domain.sendMessage.Article;
import com.tianque.plugin.weChat.domain.sendMessage.TextMessage;
import com.tianque.plugin.weChat.domain.sendMessage.text.TextSendMessage;
import com.tianque.plugin.weChat.domain.user.WeChatGroup;
import com.tianque.plugin.weChat.service.InboxAttachmentService;
import com.tianque.plugin.weChat.service.InboxService;
import com.tianque.plugin.weChat.service.ReplyMessageService;
import com.tianque.plugin.weChat.service.WeChatGroupService;
import com.tianque.plugin.weChat.util.Constants;
import com.tianque.plugin.weChat.util.DateUtil;
import com.tianque.plugin.weChat.util.MediaUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.ContacterDubboService;

/** 收件箱控制类（所有来自页面的请求） */
@Namespace("/weChat/inbox")
@Scope("prototype")
@Controller("inboxController")
public class InboxController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(InboxController.class);
	@Autowired
	private InboxService inboxService;
	@Autowired
	private InboxAttachmentService inboxAttachmentService;
	@Autowired
	private OrganizationDubboService organizationService;
	@Autowired
	private IssueManageStrategyFactory factory;
	private List<InboxAttachment> inboxAttachmentList;
	@Autowired
	private ReplyMessageService replyMessageService;
	@Autowired
	private WeChatGroupService weChatGroupService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/**收件箱对象*/
	private Inbox inbox;
	/**附件下载id*/
	private Long attachmentId;
	private Long inboxId;
	/**发送的返回情况code（0为成功，再删除时>0未成功）*/
	private int resultCode;

	/**主动回复消息对象*/
	private TextSendMessage textSendMessage;
	private InboxAttachment inboxAttachment;
	private String inboxIds;
	private List<Inbox> map = new ArrayList<Inbox>();
	private List<ReplyMessage> replyMessageList = new ArrayList<ReplyMessage>();
	private List<InboxAttachment> mapAtt = new ArrayList<InboxAttachment>();
	private Date date;
	private String name;
	/** 事件分类 key是大类名称(民生服务、治安、安全隐...) value是每个大类的下面的小类 用于在页面上显示每个大类下小类 **/
	private Map<String, List<IssueType>> issueTypes;
	/** 事件分类的大类名称 (民生服务、治安、安全隐...) 用于页面上显示分类名称 **/
	private List<IssueTypeViewDefine> issueTypeNames;
	private IssueManageStrategy strategy;
	private Issue issue;
	private IssueNew issueNew;
	List<Inbox> inboxsAndReplyMessages;
	List<Inbox> untreatedInboxs;

	/** 主动回复后的记录信息 */
	private ReplyMessage replyMessage;
	/** 是否需要播放信息提示音 */
	private String playMessageSound;

	/** 收件人自动联想补全时输入的关键字 */
	private String tag;

	/** 根据输入的关键字返回的联想补全数据 */
	private List<AutoCompleteData> autoCompletePersonnelDatas = new ArrayList<AutoCompleteData>();

	/** 当前登录用户的群组List */
	private List<MyGroup> myGroups;
	@Autowired
	private ContacterDubboService contacterService;
	//判断是发送还是回复
	private String sendOrReply;
	/** 文本*/
	private TextMessage textMessage;
	/** 附件名称 */
	private Set<String> attachFiles;
	//用于删除文件
	private String fileName;
	//图文
	private Article article;
	/** 粉丝id(用于群发) */
	private String openId;
	/**分组*/
	private List<WeChatGroup> weChatGroupList;
	/** 单个用户未处理消息列表 */
	private PageInfo<Inbox> untreatedInboxList;
	/** 接入用户openId */
	private String openIds;
	private String createOrgByTencent;
	/** 之前受理的状态 */
	private Long oldDealState;

	/**
	 * 业务跳转
	 * 
	 * @throws ParseException
	 */
	@Action(value = "dispatch", results = {
			@Result(name = "replyMessage", location = "/template/inbox/replyMessageDlg.ftl"),
			@Result(name = "replyTextMessage", location = "/wechat/sendTextMessageDlg.jsp"),
			@Result(name = "replyImageMessage", location = "/wechat/sendImageMessageDlg.jsp"),
			@Result(name = "replyNewsMessage", location = "/wechat/sendNewsMessageDlg.jsp"),
			@Result(name = "replyVoiceMessage", location = "/wechat/sendVoiceMessageDlg.jsp"),
			@Result(name = "replyMoreFortyEightTextMessage", location = "/template/inbox/replyMoreFortyEightTextMessage.ftl"),
			@Result(name = "replyMoreFortyEightImageMessage", location = "/template/inbox/replyMoreFortyEightImageMessageDlg.ftl"),
			@Result(name = "replyMoreFortyEightNewsMessage", location = "/template/inbox/replyMoreFortyEightNewsMessageDlg.ftl"),
			@Result(name = "replyMoreFortyEightVoiceMessage", location = "/template/inbox/replyMoreFortyEightVoiceMessageDlg.ftl"),
			@Result(name = "eventsToAccept", location = "/issue/otherModule/maintainInboxDlgs.jsp"),
			@Result(name = "viewReplyMessage", location = "/template/inbox/replyMessageList.ftl"),
			@Result(name = "playMedia", location = "/template/inbox/playMediaDlg.ftl"),
			@Result(name = "selectReceiver", location = "/wechat/selectPersonDlg.jsp"),
			@Result(name = "viewUntreatedInboxByOpenId", location = "/wechat/fuzzyInbox/viewUntreatedInboxList.jsp"),
			@Result(name = "search", location = "/template/inbox/searchInbox.ftl") })
	public String dispatch() throws Exception {
		if ("replyMessage".equals(mode)) {
			inbox = inboxService.getInboxById(inbox.getInboxId());
			return "replyMessage";
		} else if ("replyTextMessage".equals(mode)) {// 文本
			inbox = inboxService.getInboxById(inbox.getInboxId());
			sendOrReply = "reply";
			return "replyTextMessage";
		} else if ("replyMoreFortyEightTextMessage".equals(mode)) {// 文本
			inbox = inboxService.getInboxById(inbox.getInboxId());
			sendOrReply = "reply";
			return "replyMoreFortyEightTextMessage";
		} else if ("replyImageMessage".equals(mode)) {// 图片
			inbox = inboxService.getInboxById(inbox.getInboxId());
			sendOrReply = "reply";
			return "replyImageMessage";
		} else if ("replyMoreFortyEightImageMessage".equals(mode)) {// 图片
			inbox = inboxService.getInboxById(inbox.getInboxId());
			sendOrReply = "reply";
			return "replyMoreFortyEightImageMessage";
		} else if ("replyNewsMessage".equals(mode)) {// 图文
			inbox = inboxService.getInboxById(inbox.getInboxId());
			sendOrReply = "reply";
			return "replyNewsMessage";
		} else if ("replyMoreFortyEightNewsMessage".equals(mode)) {// 图文
			inbox = inboxService.getInboxById(inbox.getInboxId());
			sendOrReply = "reply";
			return "replyMoreFortyEightNewsMessage";
		} else if ("replyVoiceMessage".equals(mode)) {// 语音
			inbox = inboxService.getInboxById(inbox.getInboxId());
			sendOrReply = "reply";
			return "replyVoiceMessage";
		} else if ("replyMoreFortyEightVoiceMessage".equals(mode)) {// 语音
			inbox = inboxService.getInboxById(inbox.getInboxId());
			sendOrReply = "reply";
			return "replyMoreFortyEightVoiceMessage";
		} else if ("retransmissionMsg".equals(mode)) {// 转发
			inbox = inboxService.getInboxById(inbox.getInboxId());
			sendOrReply = "retransmission";
			if ("text".equals(inbox.getMsgType())
					|| "location".equals(inbox.getMsgType()))
				return "replyTextMessage";// 转发文本
			else if ("image".equals(inbox.getMsgType())) {
				inboxAttachment = inboxAttachmentService
						.getInboxAttachmentByInboxId(inbox.getInboxId()).get(0);
				return "replyImageMessage";// 转发图片
			} else if ("voice".equals(inbox.getMsgType())) {
				inboxAttachment = inboxAttachmentService
						.getInboxAttachmentByInboxId(inbox.getInboxId()).get(0);
				return "replyVoiceMessage";// 转发图片
			}

		} else if ("viewReplyMessage".equals(mode)) {
			replyMessageList = replyMessageService
					.findReplyMessagesByInboxId(inboxId);
			return "viewReplyMessage";
		} else if ("playMedia".equals(mode)) {
			inboxAttachment = inboxAttachmentService
					.getInboxAttachmentById(attachmentId);
			return "playMedia";
		} else if ("eventsToAccept".equals(mode)) {
			date = null;
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			loadIssueKind();
			for (int i = 0; i < Arrays.asList(analyzePopulationIds()).size(); i++) {
				inbox = inboxService.getInboxById(Arrays.asList(
						analyzePopulationIds()).get(i));
				name = organizationService.getSimpleOrgById(
						inbox.getOrg().getId()).getOrgName();
				if (date == null) {
					date = inbox.getCreateTime();
				} else {
					Date d1 = df.parse(df.format(date));
					Date d2 = df.parse(df.format(inbox.getCreateTime()));
					long diff = d1.getTime() - d2.getTime();
					if (diff > 0) {
						date = d2;
					}
				}
				issue = new Issue();
				issue.setOccurOrg(inbox.getOrg());
				map.add(inbox);
				inboxAttachmentList = inboxAttachmentService
						.getInboxAttachmentByInboxId(Arrays.asList(
								analyzePopulationIds()).get(i));
				if (inboxAttachmentList != null) {
					for (int j = 0; j < inboxAttachmentList.size(); j++) {

						mapAtt.add(inboxAttachmentList.get(j));
					}
				}
			}
			return "eventsToAccept";
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			if (inbox.getOrg() == null || inbox.getOrg().getId() == null) {
				errorMessage = "组织机构为空!";
				return ERROR;
			}
			weChatGroupList = weChatGroupService.getGroupListByOrgId(inbox
					.getOrg().getId());
			return mode;

		} else if ("viewUntreatedInboxByOpenId".equals(mode)) {
			return mode;
		}
		return ERROR;
	}

	/**
	 * 收件箱列表
	 * 
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	@Action(value = "findInboxs", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findInboxs() {
		try {
			PageInfo<Inbox> inboxList = inboxService.findInboxs(inbox, page,
					rows, sidx, sord);
			gridPage = new GridPage(inboxList);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("findInboxsERROR", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/**
	 * 收件箱列表
	 * 
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	@Action(value = "playMessageSound", results = {
			@Result(type = "json", name = "success", params = { "root",
					"playMessageSound", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String playMessageSound() {
		try {
			playMessageSound = String.valueOf(MediaUtil
					.opinionInboxList(inboxService.getMaxInboxId()));
			return SUCCESS;
		} catch (Exception e) {
			logger.error("findInboxsERROR", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/** 页面点击主动发送微信信息（前提是24小时与用户有交互） */
	/*
	 * @PermissionFilter(ename = "replyInbox")
	 * 
	 * @Action(value = "replyMessage", results = {
	 * 
	 * @Result(type = "json", name = "success", params = { "root", "resultCode",
	 * "ignoreHierarchy", "false" }),
	 * 
	 * @Result(type = "json", name = "error", params = { "root", "resultCode",
	 * "ignoreHierarchy", "false" }) }) public String replyMessage() { try {
	 * resultCode = inboxService.replyMessage(inbox, textSendMessage); return
	 * SUCCESS; } catch (ServiceException e) { this.errorMessage =
	 * e.getMessage(); return ERROR; } }
	 */

	/** 回复文本（前提是48小时与用户有交互） */
	@PermissionFilter(ename = "replyInbox")
	@Action(value = "replyTextMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "null" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String replyTextMessage() {
		try {
			String sendResult = inboxService.replyTextMessage(inbox,
					textMessage);
			if (sendResult == null)
				return SUCCESS;
			else {
				this.errorMessage = sendResult;
				return ERROR;
			}
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/** 回复文本（前提是48小时后与用户无交互） */
	@PermissionFilter(ename = "replyInbox")
	@Action(value = "replyMoreFortyEightTextMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "null" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String replyMoreFortyEightTextMessage() {
		try {
			String sendResult = inboxService.replyMoreFortyEightTextMessage(
					inbox, textMessage);
			if (sendResult == null)
				return SUCCESS;
			else {
				this.errorMessage = sendResult;
				return ERROR;
			}
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/** 转发文本（支持群转发）（前提是48小时与用户有交互） */
	@PermissionFilter(ename = "replyInbox")
	@Action(value = "retransmissionTextMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "null" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String retransmissionTextMessage() {
		try {
			String sendResult = inboxService.retransmissionTextMessage(inbox,
					textMessage, openId);
			if (sendResult == null) {
				// 设置已接发
				inbox = inboxService.getInboxById(inbox.getInboxId());
				inbox.setForwardingState(Constants.FORWARD);
				Organization org = organizationService.getSimpleOrgById(inbox
						.getOrg().getId());
				if (org == null) {
					errorMessage = "组织机构为空";
					return ERROR;
				}
				inbox.setOrgInternalCode(org.getOrgInternalCode());
				inboxService.update(inbox);
				return SUCCESS;
			} else {
				this.errorMessage = sendResult;
				return ERROR;
			}
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/** 回复图片（前提是48小时与用户有交互） */
	@PermissionFilter(ename = "replyInbox")
	@Action(value = "replyImageMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "null" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String replyImageMessage() {
		try {
			String sendResult = inboxService.replyImageMessage(inbox,
					textMessage, attachFiles);
			if (sendResult == null)
				return SUCCESS;
			else {
				this.errorMessage = sendResult;
				return ERROR;
			}
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/** 回复图片（前提是48小时后与用户无交互） */
	@PermissionFilter(ename = "replyInbox")
	@Action(value = "replyMoreFortyEightImageMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "null" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String replyMoreFortyEightImageMessage() {
		try {
			String sendResult = inboxService.replyMoreFortyEightImageMessage(
					inbox, textMessage, attachFiles);
			if (sendResult == null)
				return SUCCESS;
			else {
				this.errorMessage = sendResult;
				return ERROR;
			}
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/** 转发图片（前提是48小时与用户有交互） */
	@PermissionFilter(ename = "replyInbox")
	@Action(value = "retransmissionImageMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "null" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String retransmissionImageMessage() {
		try {
			String sendResult = inboxService.retransmissionImageMessage(inbox,
					textMessage, openId, inboxAttachment.getFileActualUrl());
			if (sendResult == null) {
				// 设置已接发
				inbox = inboxService.getInboxById(inbox.getInboxId());
				inbox.setForwardingState(Constants.FORWARD);
				Organization org = organizationService.getSimpleOrgById(inbox
						.getOrg().getId());
				if (org == null) {
					errorMessage = "组织机构为空";
					return ERROR;
				}
				inbox.setOrgInternalCode(org.getOrgInternalCode());
				inboxService.update(inbox);
				return SUCCESS;
			}

			else {
				this.errorMessage = sendResult;
				return ERROR;
			}
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/** 回复图文（前提是48小时与用户有交互） */
	@PermissionFilter(ename = "replyInbox")
	@Action(value = "replyNewsMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "null" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String replyNewsMessage() {
		try {
			String sendResult = inboxService.replyNewsMessage(inbox,
					textMessage, attachFiles, article);
			if (sendResult == null)
				return SUCCESS;
			else {
				this.errorMessage = sendResult;
				return ERROR;
			}
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/** 回复图文（前提是48小时与用户无交互） */
	@PermissionFilter(ename = "replyInbox")
	@Action(value = "replyMoreFortyEightNewsMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "null" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String replyMoreFortyEightNewsMessage() {
		try {
			String sendResult = inboxService.replyMoreFortyEightNewsMessage(
					inbox, textMessage, attachFiles, article);
			if (sendResult == null)
				return SUCCESS;
			else {
				this.errorMessage = sendResult;
				return ERROR;
			}
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/** 回复语音（前提是48小时与用户有交互） */
	@PermissionFilter(ename = "replyInbox")
	@Action(value = "replyVoiceMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "null" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String replyVoiceMessage() {
		try {
			String sendResult = inboxService.replyVoiceMessage(inbox,
					textMessage, attachFiles);
			if (sendResult == null)
				return SUCCESS;
			else {
				this.errorMessage = sendResult;
				return ERROR;
			}
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/** 回复语音（前提是48小时与用户无交互） */
	@PermissionFilter(ename = "replyInbox")
	@Action(value = "replyMoreFortyEightVoiceMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "null" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String replyMoreFortyEightVoiceMessage() {
		try {
			String sendResult = inboxService.replyMoreFortyEightVoiceMessage(
					inbox, textMessage, attachFiles);
			if (sendResult == null)
				return SUCCESS;
			else {
				this.errorMessage = sendResult;
				return ERROR;
			}
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/** 转发语音（前提是48小时与用户有交互） */
	@PermissionFilter(ename = "replyInbox")
	@Action(value = "retransmissionVoiceMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "null" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String retransmissionVoiceMessage() {
		try {
			String sendResult = inboxService.retransmissionVoiceMessage(inbox,
					textMessage, openId, inboxAttachment.getFileActualUrl());
			if (sendResult == null) {
				// 设置已接发
				inbox = inboxService.getInboxById(inbox.getInboxId());
				inbox.setForwardingState(Constants.FORWARD);
				Organization org = organizationService.getSimpleOrgById(inbox
						.getOrg().getId());
				if (org == null) {
					errorMessage = "组织机构为空";
					return ERROR;
				}
				inbox.setOrgInternalCode(org.getOrgInternalCode());
				inboxService.update(inbox);
				return SUCCESS;
			}

			else {
				this.errorMessage = sendResult;
				return ERROR;
			}
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/** 附件下载 */
	@Action(value = "downloadInboxAttachment", results = { @Result(name = "error", type = "json", params = {
			"root", "errorMessage" }) })
	public String downloadInboxAttachment() {
		if (null == attachmentId) {
			errorMessage = "参数不正确";
			return ERROR;
		}
		InboxAttachment attachment = inboxAttachmentService
				.getInboxAttachmentById(attachmentId);
		if (null == attachment) {
			errorMessage = "收件箱附件不存在";
			return ERROR;
		}
		try {
			inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
					+ File.separator + attachment.getFileActualUrl());
			downloadFileName = new String(attachment.getFileName().getBytes(
					"gbk"), "ISO8859-1");
		} catch (FileNotFoundException e) {
			errorMessage = "附件文件不存在";
			return ERROR;
		} catch (UnsupportedEncodingException uee) {
			errorMessage = "文件编码格式不正确";
			return ERROR;
		}
		return STREAM_SUCCESS;
	}

	/**
	 * 设置有效
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "setAvailability")
	@Action(value = "setAvailability", results = { @Result(type = "json", name = "success", params = {
			"root", "true" }) })
	public String setAvailability() {
		inboxService.setAvailabilityOrInvalid(
				Arrays.asList(analyzePopulationIds()), "1");
		return SUCCESS;

	}

	/**
	 * 设置无效
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "setAvailability")
	@Action(value = "setInvalid", results = { @Result(type = "json", name = "success", params = {
			"root", "true" }) })
	public String setInvalid() {
		inboxService.setAvailabilityOrInvalid(
				Arrays.asList(analyzePopulationIds()), "2");
		return SUCCESS;

	}

	/** 删除 */
	@PermissionFilter(ename = "deleteInbox")
	@Action(value = "deleteInboxById", results = { @Result(type = "json", name = "success", params = {
			"root", "true", "ignoreHierarchy", "false" }) })
	public String deleteInboxById() {
		inboxService.deleteInboxById(Arrays.asList(analyzePopulationIds()));
		return SUCCESS;
	}

	private Long[] analyzePopulationIds() {
		String[] deleteId = inboxIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
			// resultCode = inboxService.deleteInboxById(ids);
		}
		return ids;
	}

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	/**
	 * 加载事件类型
	 */
	private void loadIssueKind() {
		issueTypeNames = getActualIssueManageStrategy().getIssueTypeNames();
		issueTypes = getActualIssueManageStrategy().loadEnabledIssueTypes(
				issueTypeNames);
	}

	private IssueManageStrategy getActualIssueManageStrategy() {
		if (strategy == null) {
			strategy = factory.getIssueManageStrategyByModule(null);
		}
		// resultCode = inboxService.deleteInboxById(inboxId);
		return strategy;
	}

	/**
	 * 删除附件
	 * 
	 * @return
	 */
	@Action(value = "delelteAttachFile", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String delelteAttachFile() {
		fileName = fileName.substring(1);
		File file = new File(FileUtil.getWebRoot() + File.separator
				+ GridProperties.TMP + File.separator
				+ ThreadVariable.getUser().getId() + File.separator + fileName);
		if (file.exists()) {
			file.delete();
		}
		return SUCCESS;

	}

	/**
	 * 回复消息时校验时间是否超过48小时
	 * 
	 * @return
	 */
	@Action(value = "checkDate", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String checkDate() {

		Inbox listInbox = inboxService.findLastInboxByFromUserName(inbox);

		if (listInbox == null) {
			errorMessage = "该粉丝没有发送过任何消息，不能回复信息！";
			return ERROR;
		}
		if (DateUtil.isActiveTime(listInbox.getCreateTime()) == true)
			return SUCCESS;
		else {
			errorMessage = "该粉丝发送的最后一条消息时间到现在已经超过48小时，不能再回复信息！";
			return ERROR;
		}

	}

	/**
	 * 回复消息时校验时间是否超过48小时
	 */
	@Action(value = "checkDateMoreThanFortyEight", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String checkDateMoreThanFortyEight() {
		if (DateUtil.isActiveTime(inbox.getCreateTime()) == false)
			return SUCCESS;
		else {
			errorMessage = "该粉丝互动时间未超过48小时，请选择回复操作！";
			return ERROR;
		}

	}

	/** 收件人自动联想补全 */
	@Action(value = "searchContacterForAutoComplete", results = {
			@Result(name = "success", type = "json", params = { "root",
					"autoCompletePersonnelDatas", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchContacterForAutoComplete() {
		if (!StringUtil.isStringAvaliable(tag)) {
			return SUCCESS;
		}
		// 站内联系人
		List<WorkContacter> workContacterList = new ArrayList<WorkContacter>();
		WorkContacter w = new WorkContacter();
		w.setId(Long.parseLong("1"));
		w.setName("uswwerReceivers");
		WorkContacter ww = new WorkContacter();
		ww.setId(Long.parseLong("2"));
		ww.setName("ww陶海洋");
		workContacterList.add(w);
		workContacterList.add(ww);
		for (WorkContacter workContacter : workContacterList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(workContacter.getName());
			autoCompleteData.setValue(workContacter.getId().toString());
			autoCompletePersonnelDatas.add(autoCompleteData);
		}
		// 我的群组
		List<MyGroup> myGroupList = new ArrayList<MyGroup>();
		MyGroup m = new MyGroup();
		m.setName("ww组");
		m.setId(1L);
		myGroupList.add(m);

		for (MyGroup myGroup : myGroupList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(myGroup.getName());
			autoCompleteData.setValue(myGroup.getId().toString());
			autoCompletePersonnelDatas.add(autoCompleteData);
		}
		// System.out.println(autoCompletePersonnelDatas);
		return SUCCESS;
	}

	/** 统计未处理消息列表 */
	@Action(value = "findUntreatedInboxs", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findUntreatedInboxs() {
		try {
			PageInfo<Inbox> inboxList = inboxService.findUntreatedInboxs(inbox,
					page, rows, sidx, sord);

			gridPage = new GridPage(inboxList);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("findMissedInboxsERROR", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/** 查询单个用户发送的消息的消息(未处理的) */
	@Action(value = "findInboxsByOpenIdAndDealState", results = {
			@Result(name = "success", type = "json", params = { "root",
					"untreatedInboxs", }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findInboxsByOpenIdAndDealState() throws Exception {

		if(inbox==null){
			errorMessage="未获取关键参数!";
			return ERROR;
		}
		untreatedInboxs = inboxService.findInboxsByOpenIdAndDealState(inbox);
		if (untreatedInboxs != null && !untreatedInboxs.isEmpty()) {
			for (Inbox inbox2 : untreatedInboxs) {
				inbox2.setInboxAttachments(inboxAttachmentService
						.getInboxAttachmentByInboxId(inbox2.getInboxId()));
			}
			inboxService.updateIsRead(inbox);
		}
		return SUCCESS;
	}

	/** 更新消息的处理状态 */
	@Action(value = "updateDealState", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateDealState() throws Exception {
		/*
		 * String[] openIdArray = analyzePopulationOtherIds();
		 * if(openIdArray==null||openIdArray.length==0){
		 * errorMessage="接入失败,接入用户为空!"; return errorMessage; }
		 */

		inboxService.updateDealState(openIds, inbox, oldDealState);

		return SUCCESS;
	}

	/** 查询单个用户发送的消息和社管回复的消息记录 */
	@Action(value = "findInboxsAndReplyMessages", results = {
			@Result(name = "success", type = "json", params = { "root",
					"inboxsAndReplyMessages", }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findInboxsAndReplyMessages() throws Exception {

		inboxsAndReplyMessages = inboxService.findInboxsAndReplyMessages(inbox);

		return SUCCESS;
	}

	/** 转出已接入消息（接入了并没有处理的消息） */
	@Action(value = "inboxRollOutByOpenIdAndDealState", results = { @Result(type = "json", name = "success", params = {
			"root", "true", "ignoreHierarchy", "false" }) })
	public String inboxRollOutByOpenIdAndDealState() {
		inboxService.inboxRollOutByOpenIdAndDealState(inbox);
		return SUCCESS;
	}

	/** 删除未处理消息 */
	@Action(value = "deleteInboxByOpenIdAndDealState", results = { @Result(type = "json", name = "success", params = {
			"root", "true", "ignoreHierarchy", "false" }) })
	public String deleteInboxByOpenIdAndDealState() {
		inboxService.deleteInboxsByOpenIdAndDealState(openIds, inbox);
		return SUCCESS;
	}

	public Inbox getInbox() {
		return inbox;
	}

	public void setInbox(Inbox inbox) {
		this.inbox = inbox;
	}

	public Long getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Long attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Long getInboxId() {
		return inboxId;
	}

	public void setInboxId(Long inboxId) {
		this.inboxId = inboxId;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public TextSendMessage getTextSendMessage() {
		return textSendMessage;
	}

	public void setTextSendMessage(TextSendMessage textSendMessage) {
		this.textSendMessage = textSendMessage;
	}

	public String getInboxIds() {
		return inboxIds;
	}

	public void setInboxIds(String inboxIds) {
		this.inboxIds = inboxIds;
	}

	public List<InboxAttachment> getInboxAttachmentList() {
		return inboxAttachmentList;
	}

	public void setInboxAttachmentList(List<InboxAttachment> inboxAttachmentList) {
		this.inboxAttachmentList = inboxAttachmentList;
	}

	public List<Inbox> getMap() {
		return map;
	}

	public void setMap(List<Inbox> map) {
		this.map = map;
	}

	public List<InboxAttachment> getMapAtt() {
		return mapAtt;
	}

	public void setMapAtt(List<InboxAttachment> mapAtt) {
		this.mapAtt = mapAtt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IssueManageStrategyFactory getFactory() {
		return factory;
	}

	public void setFactory(IssueManageStrategyFactory factory) {
		this.factory = factory;
	}

	public Map<String, List<IssueType>> getIssueTypes() {
		return issueTypes;
	}

	public void setIssueTypes(Map<String, List<IssueType>> issueTypes) {
		this.issueTypes = issueTypes;
	}

	public List<IssueTypeViewDefine> getIssueTypeNames() {
		return issueTypeNames;
	}

	public void setIssueTypeNames(List<IssueTypeViewDefine> issueTypeNames) {
		this.issueTypeNames = issueTypeNames;
	}

	public IssueManageStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(IssueManageStrategy strategy) {
		this.strategy = strategy;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public ReplyMessage getReplyMessage() {
		return replyMessage;
	}

	public void setReplyMessage(ReplyMessage replyMessage) {
		this.replyMessage = replyMessage;
	}

	public List<ReplyMessage> getReplyMessageList() {
		return replyMessageList;
	}

	public void setReplyMessageList(List<ReplyMessage> replyMessageList) {
		this.replyMessageList = replyMessageList;
	}

	public InboxAttachment getInboxAttachment() {
		return inboxAttachment;
	}

	public void setInboxAttachment(InboxAttachment inboxAttachment) {
		this.inboxAttachment = inboxAttachment;
	}

	public String getPlayMessageSound() {
		return playMessageSound;
	}

	public void setPlayMessageSound(String playMessageSound) {
		this.playMessageSound = playMessageSound;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<AutoCompleteData> getAutoCompletePersonnelDatas() {
		return autoCompletePersonnelDatas;
	}

	public void setAutoCompletePersonnelDatas(
			List<AutoCompleteData> autoCompletePersonnelDatas) {
		this.autoCompletePersonnelDatas = autoCompletePersonnelDatas;
	}

	public List<MyGroup> getMyGroups() {
		return myGroups;
	}

	public void setMyGroups(List<MyGroup> myGroups) {
		this.myGroups = myGroups;
	}

	public String getSendOrReply() {
		return sendOrReply;
	}

	public void setSendOrReply(String sendOrReply) {
		this.sendOrReply = sendOrReply;
	}

	public TextMessage getTextMessage() {
		return textMessage;
	}

	public Set<String> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(Set<String> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public void setTextMessage(TextMessage textMessage) {
		this.textMessage = textMessage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<WeChatGroup> getWeChatGroupList() {
		return weChatGroupList;
	}

	public void setWeChatGroupList(List<WeChatGroup> weChatGroupList) {
		this.weChatGroupList = weChatGroupList;
	}

	public PageInfo<Inbox> getUntreatedInboxList() {
		return untreatedInboxList;
	}

	public void setUntreatedInboxList(PageInfo<Inbox> untreatedInboxList) {
		this.untreatedInboxList = untreatedInboxList;
	}

	public String getOpenIds() {
		return openIds;
	}

	public void setOpenIds(String openIds) {
		this.openIds = openIds;
	}

	public List<Inbox> getInboxsAndReplyMessages() {
		return inboxsAndReplyMessages;
	}

	public void setInboxsAndReplyMessages(List<Inbox> inboxsAndReplyMessages) {
		this.inboxsAndReplyMessages = inboxsAndReplyMessages;
	}

	public IssueNew getIssueNew() {
		return issueNew;
	}

	public void setIssueNew(IssueNew issueNew) {
		this.issueNew = issueNew;
	}

	public String getCreateOrgByTencent() {
		return createOrgByTencent;
	}

	public void setCreateOrgByTencent(String createOrgByTencent) {
		this.createOrgByTencent = createOrgByTencent;
	}

	public List<Inbox> getUntreatedInboxs() {
		return untreatedInboxs;
	}

	public void setUntreatedInboxs(List<Inbox> untreatedInboxs) {
		this.untreatedInboxs = untreatedInboxs;
	}

	public Long getOldDealState() {
		return oldDealState;
	}

	public void setOldDealState(Long oldDealState) {
		this.oldDealState = oldDealState;
	}

}
