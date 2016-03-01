package com.tianque.platformMessage.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.mobile.vo.ReciveMessageVo;
import com.tianque.platformMessage.constants.PlatformMessageType;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.domain.PlatformMessageAttachFile;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.platformMessage.service.PlatformMessagesAttachFileService;

@Namespace("/interactive/inboxPlatformMessageManage")
@Controller("inboxPlatformMessageController")
@Scope("prototype")
@Transactional
public class InboxPlatformMessageController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(OutboxPlatformMessageController.class);
	@Autowired
	private PlatformMessageService platformMessageService;
	/** 平台消息类型 */
	private Set<Integer> platformMessageType;
	/** 平台消息实体类 */
	private PlatformMessage platformMessage;
	public static final String WORK_BENCH = "workBench";// 工作台操作返回的页面
	public static final String WORK_BENCH_FORWARD = "workBenchForward";// 工作台操作返回的页面
	private Integer isAdmin;
	private String ids;
	private String source;// 工作台的待办事项，是高层还是基层
	/** 附件名称 */
	private Set<String> attachFiles;
	@Autowired
	private PlatformMessagesAttachFileService platformMessagesAttachFileService;
	List<StoredFile> storedFileList = new ArrayList<StoredFile>();// 用于保存回复时添加的附件

	private Integer signFileRemind;
	private Integer sharedFileRemind;
	private String messageType;// 用于区分是签收还是共享

	/** 收件箱消息列表 系统消息 */
	@Actions({
			@Action(value = "findInboxPlatformMessageByUserId", results = { @Result(name = "success", location = "/interaction/platformMessage/inbox/simpleInboxPlatformMessageList.jsp") }),
			@Action(value = "findInboxPlatformMessageByUserIdForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String findInboxPlatformMessageByUserId() throws Exception {
		setPlatformMessageReceiverId();
		PageInfo<PlatformMessage> pageInfo = platformMessageService
				.findPlatformMessageFromInboxByUserId(
						platformMessage.getReceiverId(), isAdmin, page, rows,
						sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/** 收件箱消息列表 个人消息 */
	@Actions({ @Action(value = "findInboxPlatformSelfMessageByUserId", results = {
			@Result(name = "success", location = "/interaction/platformMessage/inbox/simpleInboxPlatformSelfMessageList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String findInboxPlatformSelfMessageByUserId() throws Exception {
		setPlatformMessageReceiverId();
		PageInfo<PlatformMessage> pageInfo = platformMessageService
				.findPlatformMessageFromInboxByUserId(
						platformMessage.getReceiverId(), isAdmin, page, rows,
						sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findInboxPlatformMessageByUserIdNew
	 * @Description: TODO手机端要求的精简版消息反馈
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-5-28 上午11:04:23
	 */
	@Action(value = "findInboxPlatformMessageByUserIdForMobileNew", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findInboxPlatformMessageByUserIdNew() throws Exception {
		setPlatformMessageReceiverId();
		PageInfo<PlatformMessage> pageInfo = platformMessageService
				.findPlatformMessageFromInboxByUserId(
						platformMessage.getReceiverId(), null, page, rows,
						sidx, sord);
		gridPage = new GridPage(pageInfo);
		List<ReciveMessageVo> list = new ArrayList<ReciveMessageVo>();
		List<PlatformMessage> messages = gridPage.getRows();
		for (PlatformMessage p : messages) {
			ReciveMessageVo rm = new ReciveMessageVo();
			rm.setId(p.getId());
			rm.setTitle(p.getTitle());
			rm.setSender(p.getSender());
			rm.setReadState(p.getReadState());

			list.add(rm);
		}
		gridPage.setRows(list);
		return SUCCESS;
	}

	// /** 收件箱未读消息列表 */
	// @Action(value = "findUnreadInboxPlatformMessage", results = {
	// @Result(name = "super", type = "json", params = { "root",
	// "gridPage", "ignoreHierarchy", "false" }),
	// @Result(name = "middle", type = "json", params = { "root",
	// "gridPage", "ignoreHierarchy", "false" }),
	// @Result(name = "lower", location =
	// "/workBench/messageUnreadOfWorkBench.jsp"),
	// @Result(name = "error", type = "json", params = { "root",
	// "errorMessage" }) })
	// public String findUnreadInboxPlatformMessage() throws Exception {
	// setPlatformMessageReceiverId();
	// PageInfo<PlatformMessage> pageInfo = platformMessageService
	// .findUnreadPlatformMessageFromInboxByUserId(
	// platformMessage.getReceiverId(), page, rows, sidx, sord);
	// gridPage = new GridPage(pageInfo);
	// return source;
	// }

	/** 收件箱未读消息列表 */
	@Action(value = "findUnreadInboxPlatformMessage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findUnreadInboxPlatformMessage() throws Exception {
		setPlatformMessageReceiverId();
		PageInfo<PlatformMessage> pageInfo = platformMessageService
				.findUnreadPlatformMessageFromInboxByUserId(
						platformMessage.getReceiverId(), page, rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	private PlatformMessage setPlatformMessageReceiverId() {
		if (platformMessage == null) {
			platformMessage = new PlatformMessage();
		}
		platformMessage.setReceiverId(ThreadVariable.getSession().getUserId());
		return platformMessage;
	}

	/***
	 * 根据传入的消息类表跳转
	 */
	@Actions({ @Action(value = "showMessageDetailByType", results = {
			@Result(name = "signFileRemind", location = "/interaction/platformMessage/inbox/signFileRemindList.jsp"),
			@Result(name = "sharedFileRemind", location = "/interaction/platformMessage/inbox/sharedFileRemindList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String showMessageDetailByType() throws Exception {
		if (!StringUtil.isStringAvaliable(messageType)
				|| !StringUtil.isStringAvaliable(ids)) {
			errorMessage = "参数未获得";
			return ERROR;
		}
		if (PlatformMessageType.SIGN_FILE_REMIND.equals(Integer
				.parseInt(messageType))) {
			// 文件签收提醒
			return DialogMode.SIGNFILEREMIND;
		} else {
			// 共享资料
			return DialogMode.SHAREDFILEREMIND;
		}
	}

	/**
	 * 查看收件箱消息
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "getInboxPlatformMessageById", results = {
					@Result(name = "view", location = "/interaction/platformMessage/inbox/platformMessageInboxAccordingContext.jsp"),
					@Result(name = "success", location = "/interaction/platformMessage/inbox/maintainPlatformMessageInboxDlg.jsp"),
					@Result(name = "inboxForward", location = "/interaction/platformMessage/outbox/maintainPlatformMessageOutBoxDlg.jsp"),
					@Result(name = "workBenchForward", location = "/interaction/platformMessage/outbox/maintainPlatformMessageOutBoxDlg.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "getInboxPlatformMessageOfBench", results = {
					@Result(name = "workBench", location = "/workBench/issueManage/platformMessageInboxAccordingContext.jsp"),
					@Result(name = "success", location = "/workBench/issueManage/maintainPlatformMessageInboxDlg.jsp"),
					@Result(name = "inboxForward", location = "/workBench/issueManage/maintainPlatformMessageOutBoxDlg.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "getInboxPlatformMessageByIdForMobile", results = {
					@Result(name = "view", type = "json", params = { "root",
							"platformMessage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }),
					@Result(name = "success", type = "json", params = { "root",
							"platformMessage" }) }) })
	public String getPlatformMessageFromInboxById() throws Exception {
		if (platformMessage == null || platformMessage.getId() == null) {
			errorMessage = "查看消息失败，请重试";
			return ERROR;
		}

		platformMessage = platformMessageService
				.getInboxPlatformMessageById(platformMessage.getId());

		markRead(platformMessage);

		if (mode.equals(DialogMode.VIEW_MODE)) {
			// 这两个变量用于前段判断消息类型使用
			signFileRemind = PlatformMessageType.SIGN_FILE_REMIND;
			sharedFileRemind = PlatformMessageType.SHARED_FILE_REMIND;
			return DialogMode.VIEW_MODE;

		} else if (mode.equals(DialogMode.MESSAGE_INBOXFORWARD)) {

			platformMessage.setTitle("转发:" + platformMessage.getTitle());

			return DialogMode.MESSAGE_INBOXFORWARD;
		}
		return SUCCESS;
	}

	/** 回复收件箱消息 */
	@Action(value = "replyPlatformMessage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"platformMessage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "restorePmInbox")
	public String replyPlatformMessage() throws Exception {
		if (!addPlatformMessageAttachFile()) {
			errorMessage = "回复附件添加失败";
			return ERROR;
		}

		if (platformMessage == null || platformMessage.getReceiverId() == null) {
			errorMessage = "回复消息失败，请重试!";
			return ERROR;
		}
		platformMessage = platformMessageService.replyPlatformMessage(
				platformMessage, storedFileList);

		return SUCCESS;
	}

	/** 添加附件 */
	private boolean addPlatformMessageAttachFile() throws Exception {
		/* 处理手机端业务 */
		if (attachFiles == null || attachFiles.size() == 0) {
			return true;
		}

		for (String fileNameAndId : attachFiles) {
			StoredFile storedFile = new StoredFile();
			String[] id_fileName = null;
			if (StringUtil.isStringAvaliable(fileNameAndId)) {
				String[] fileName = fileNameAndId.split(",");
				if (fileNameAndId.indexOf(",") == 0
						&& fileName[1].indexOf(".") == -1) {
					fileNameAndId = fileNameAndId.substring(1);
				}
				id_fileName = fileNameAndId.split(",");
				if (StringUtil.isStringAvaliable(id_fileName[0])) {
					PlatformMessageAttachFile platform = new PlatformMessageAttachFile();
					platform = platformMessagesAttachFileService
							.getPlatformMessageAttachFileById(Long
									.valueOf(id_fileName[0]));
					storedFile.setStoredTruthFileName(platform.getFileName());
					storedFile.setFileSize(platform.getFileSize());
					storedFile.setStoredFilePath(platform.getFileActualUrl()
							.substring(
									0,
									platform.getFileActualUrl().lastIndexOf(
											File.separator)));
					storedFile.setStoredFileName(platform.getFileActualUrl()
							.substring(
									platform.getFileActualUrl().lastIndexOf(
											File.separator) + 1));
				} else {

					storedFile = FileUtil.copyTmpFileToStoredFile(
							id_fileName[1], GridProperties.ISSUE_ATTACHFILE);
				}
				storedFileList.add(storedFile);

			}
		}

		return true;
	}

	/** 标记为已读 */
	@Action(value = "markReadPlatformMessageById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"platformMessage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "markReadPmInbox")
	public String markReadPlatformMessageById() throws Exception {
		if (ids == null) {
			errorMessage = "Id不能为空";
			return ERROR;
		}
		for (Long id : analyze(ids)) {
			platformMessage = platformMessageService
					.getInboxPlatformMessageById(id);
			markRead(platformMessage);
		}

		return SUCCESS;
	}

	private void markRead(PlatformMessage pm) {
		if (pm.getReadState() == null || pm.getReadState() == 0) {
			platformMessageService.markInboxPlatgformMessageRead(pm);
		}
	}

	/** 批量删除收件箱消息 */
	@Action(value = "deleteInboxPlatformMessageByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deletePmInbox")
	public String deleteInboxPlatformMessageByIds() throws Exception {
		if (ids == null) {
			errorMessage = "Id不能为空";
			return ERROR;
		}
		for (Long id : analyze(ids)) {
			platformMessage = platformMessageService
					.getInboxPlatformMessageById(id);
			if (platformMessage != null) {
				platformMessageService
						.deleteInboxPlatformMessage(platformMessage);
			}
		}
		return SUCCESS;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public PlatformMessage getPlatformMessage() {
		return platformMessage;
	}

	public void setPlatformMessage(PlatformMessage platformMessage) {
		this.platformMessage = platformMessage;
	}

	public Set<Integer> getPlatformMessageType() {
		return platformMessageType;
	}

	public void setPlatformMessageType(Set<Integer> platformMessageType) {
		this.platformMessageType = platformMessageType;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Set<String> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(Set<String> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getSignFileRemind() {
		return signFileRemind;
	}

	public void setSignFileRemind(Integer signFileRemind) {
		this.signFileRemind = signFileRemind;
	}

	public Integer getSharedFileRemind() {
		return sharedFileRemind;
	}

	public void setSharedFileRemind(Integer sharedFileRemind) {
		this.sharedFileRemind = sharedFileRemind;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getIds() {
		return ids;
	}

}
