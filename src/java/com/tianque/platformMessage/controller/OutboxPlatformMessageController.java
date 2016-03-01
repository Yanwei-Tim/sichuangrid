package com.tianque.platformMessage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.MyGroup;
import com.tianque.domain.Organization;
import com.tianque.domain.Role;
import com.tianque.domain.User;
import com.tianque.domain.WorkContacter;
import com.tianque.platformMessage.constants.ReceiverType;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.domain.PlatformMessageAttachFile;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.platformMessage.service.PlatformMessagesAttachFileService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.ContacterDubboService;

@Namespace("/interactive/outboxPlatformMessageManage")
@Scope("prototype")
@Controller("outboxPlatformMessageController")
@Transactional
public class OutboxPlatformMessageController extends BaseAction {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ContacterDubboService contacterDubboService;
	@Autowired
	private PlatformMessageService platformMessageService;
	@Autowired
	private PlatformMessagesAttachFileService platformMessagesAttachFileService;

	/** 附件名称 */
	private Set<String> attachFiles;
	/** 联系人列表 */
	private List<WorkContacter> workContacts;
	/** 当前登录用户的群组List */
	private List<MyGroup> myGroups;
	/** 部门id */
	private Long orgId;
	/** 收件人自动联想补全时输入的关键字 */
	private String tag;
	/** 根据输入的关键字返回的联想补全数据 */
	private List<AutoCompleteData> autoCompletePersonnelDatas = new ArrayList<AutoCompleteData>();
	/** 岗位信息 */
	private List<Role> roles;
	/** 岗位范围 ：仅限本级、直属下辖、所有下辖 */
	private Integer roleScope;
	/** 从页面传过来的user/group的id字符串 */
	private String userReceivers;
	/** 从页面传过来部门部门收件人id字符串 */
	private String orgReceivers;
	/** 从页面传过来的岗位收件人id字符串 */
	private String roleReceivers;
	/** 平台消息实体类 */
	private PlatformMessage platformMessage;
	/** 平台消息附件 */
	private PlatformMessageAttachFile pmAttachFile;
	/** 手机端附件名称 */
	private String attachFile;
	/** 是否是草稿箱 0不是，1是 */
	private Integer isDraft;
	/** 收件人类型为user的收件人ID */
	private String userIds;
	private String userNames;
	/** 收件人类型为org的组织机构ID */
	private String orgIds;
	private String orgNames;
	/** 收件人类型为role的岗位ID和OrgId */
	private String roleAndOrgIds;
	private String roleAndOrgNames;
	private String receiverNames;

	/** 页面请求分发 */
	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/interaction/platformMessage/outbox/selectPersonDlg.jsp"),
			@Result(name = "outboxForward", location = "/interaction/platformMessage/outbox/maintainPlatformMessageOutBoxDlg.jsp"),
			@Result(name = "draftboxForward", location = "/interaction/platformMessage/draftbox/maintainPlatformDraftMessage.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (mode.equals(DialogMode.MESSAGE_SELECTRECEIVER)) {

			myGroups = contacterDubboService
					.findMyGroupByOwnerId(ThreadVariable.getUser().getId());

		} else if (mode.equals(DialogMode.MESSAGE_OUTBOXFORWARD)) {

			platformMessage = platformMessageService
					.getOutboxPlatformMessageById(platformMessage.getId());
			platformMessage.setTitle("转发:" + platformMessage.getTitle());

			return DialogMode.MESSAGE_OUTBOXFORWARD;
		} else if (mode.equals(DialogMode.MESSAGE_DRAFTBOXFORWARD)) {
			platformMessage = platformMessageService
					.getOutboxPlatformMessageById(platformMessage.getId());
			analyticalReceverInfo(platformMessage.getRecipientInfo());
			return DialogMode.MESSAGE_DRAFTBOXFORWARD;
		}

		return SUCCESS;
	}

	/***
	 * 解析草稿箱收件人信息
	 */
	private void analyticalReceverInfo(String receverInfo) {
		StringBuffer sb = null;
		if (receverInfo != null) {
			String[] typle = receverInfo.split("\\&");
			if (typle != null) {
				String[] name = typle[1].split(":");
				String[] names = name[1].split(",");
				sb = new StringBuffer();
				for (int i = 0; i < names.length; i++) {
					if (names[i] == null || names[i].trim().length() == 0) {
						continue;
					}
					sb.append(names[i] + ",");
				}
				if (sb != null) {
					receiverNames = sb.toString();
				}
			}

			String[] recevers = typle[0].split("\\|");
			for (String recever : recevers) {
				if (recever == null || recever.trim().length() == 0) {
					continue;
				}
				String[] receverFirst = recever.split(":");
				String type = receverFirst[0];
				if (ReceiverType.USER.equals(type)) {
					sb = new StringBuffer();
					for (int i = 1; i < receverFirst.length; i++) {
						if (receverFirst[i] != null
								&& receverFirst[i].trim().length() != 0) {
							sb.append(receverFirst[i] + ",");
						}
					}
					if (sb.toString().trim().length() != 0) {
						userIds = sb.substring(0, sb.length() - 1);
					}
				}

				if (ReceiverType.ORG.equals(type)) {
					sb = new StringBuffer();
					for (int i = 1; i < receverFirst.length; i++) {
						if (receverFirst[i] != null
								&& receverFirst[i].trim().length() != 0) {
							sb.append(receverFirst[i] + ",");
						}
					}
					if (sb.toString().trim().length() != 0) {
						orgIds = sb.substring(0, sb.length() - 1);
					}
				}
				if (ReceiverType.ROLE.equals(type)) {
					sb = new StringBuffer();
					for (int i = 1; i < receverFirst.length; i++) {
						if (receverFirst[i] != null
								&& receverFirst[i].trim().length() != 0) {
							sb.append(receverFirst[i] + ",");
						}
					}
					if (sb.toString().trim().length() != 0) {
						roleAndOrgIds = sb.substring(0, sb.length() - 1);
					}
				}

			}
		}
	}

	/** 获取岗位信息 */
	@Action(value = "findRoles", results = {
			@Result(name = "success", type = "json", params = { "root",
					"roles", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findRoles() throws Exception {
		if (orgId == null) {
			errorMessage = "获取岗位信息出错，请重试";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (org != null && org.getOrgLevel() != null
				&& org.getOrgLevel().getId() != null) {
			roles = platformMessageService.getRolesByOrgLevelAndRoleScope(org
					.getOrgLevel().getId(), roleScope);
		}
		return SUCCESS;
	}

	/** 发件箱消息列表 */
	@Actions({
			@Action(value = "findOutboxPlatformMessageByUserId", results = { @Result(name = "success", location = "/interaction/platformMessage/outbox/simpleOutboxPlatformMessageList.jsp") }),
			@Action(value = "findOutboxPlatformMessageByUserIdForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"gridPage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String findOutboxPlatformMessageByUserId() throws Exception {
		setPlatformMessageSender();
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				platformMessageService.findPlatformMessageFromOutboxBySenderId(
						platformMessage.getSender().getId(), isDraft, page,
						rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, orgId));
		return SUCCESS;
	}

	/** 新增平台消息 、转发发件箱消息、转发收件箱消息 */
	@PermissionFilters(value = {
			@PermissionFilter(ename = "addPmOutbox", actionName = "sendPlatformMessage"),
			@PermissionFilter(ename = "forwardPmOutbox", actionName = "forwardOutboxPlatformMessage"),
			@PermissionFilter(ename = "forwardPmInbox", actionName = "forwardInboxPlatformMessage") })
	@Actions(value = {
			@Action(value = "sendPlatformMessage", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "forwardOutboxPlatformMessage", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "forwardInboxPlatformMessage", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String sendPlatformatMessage() throws Exception {

		if (platformMessage == null) {
			errorMessage = "操作失败，请重试";
			return ERROR;
		}
		if (!validatePlatformMessage()) {
			return ERROR;
		}

		if (!addPlatformMessageAttachFile()) {
			return ERROR;
		}

		setPlatformMessageSender();
		Map<String, String> receiverNamesMap = resolveReceiverNames(receiverNames);
		// 判断消息类型为草稿箱消息
		if (platformMessage.getIsDraft() != null
				&& platformMessage.getIsDraft() == 1) {
			// 分别得到三种类型的收件人ID
			List<Long> userReceiverIds = getUserReceiverIds(userReceivers);// userId
			// 将三种类型的收件人ID按照一定的规则保存到消息中;
			platformMessage.setReceiverNames(receiverNamesMap.get("user")
					+ receiverNamesMap.get("org")
					+ receiverNamesMap.get("role"));
			platformMessage = setPlatformMessageRecipientInfo(userReceivers,
					orgReceivers, roleReceivers, receiverNames, platformMessage);
			platformMessage = platformMessageService.sendPlatformMessage(
					userReceiverIds, ReceiverType.USER, platformMessage,
					storedFileList, null);
			return SUCCESS;
		}

		/*
		 * if (StringUtil.isStringAvaliable(userReceivers) &&
		 * !"-1".equals(userReceivers) && !"-2".equals(userReceivers))
		 */
		if (StringUtil.isStringAvaliable(userReceivers)
				&& !userReceivers.endsWith("-levelList")
				&& !userReceivers.endsWith("-roleList")) {
			List<Long> userReceiverIds = getUserReceiverIds(userReceivers);
			platformMessage.setReceiverNames(receiverNamesMap.get("user"));
			if (userReceiverIds != null && !userReceiverIds.isEmpty()) {
				platformMessage = platformMessageService.sendPlatformMessage(
						userReceiverIds, ReceiverType.USER, platformMessage,
						storedFileList, null);
			}
		}
		if (StringUtil.isStringAvaliable(orgReceivers)) {
			List<Long> orgReceiverIds = getOrgReceiverIds(orgReceivers);
			platformMessage.setReceiverNames(receiverNamesMap.get("org"));
			if (orgReceiverIds != null && !orgReceiverIds.isEmpty()) {
				platformMessage = platformMessageService.sendPlatformMessage(
						orgReceiverIds, ReceiverType.ORG, platformMessage,
						storedFileList, orgReceiverIds);
			}
		}
		if (StringUtil.isStringAvaliable(roleReceivers)) {
			List<Long> roleReceiverIds = getRoleReceiverIds(roleReceivers);
			platformMessage.setReceiverNames(receiverNamesMap.get("role"));
			if (roleReceiverIds != null && !roleReceiverIds.isEmpty()) {
				platformMessageService.sendPlatformMessageToRole(
						roleReceiverIds, platformMessage, storedFileList);
			}
		}

		return SUCCESS;
	}

	private Map<String, String> resolveReceiverNames(String receiverNames) {
		Map<String, String> result = new HashMap<String, String>();
		if (receiverNames == null) {
			return result;
		}
		String[] allReceiverNames = receiverNames.split(",");
		StringBuffer userReceiverNames = new StringBuffer();
		StringBuffer orgReceiverNames = new StringBuffer();
		StringBuffer roleReceiverNames = new StringBuffer();
		for (int i = 0; i < allReceiverNames.length; i++) {
			if (StringUtil.isStringAvaliable(allReceiverNames[i])) {
				if (allReceiverNames[i].indexOf("levelList") != -1) {
					orgReceiverNames.append(allReceiverNames[i]
							.substring(allReceiverNames[i].indexOf("=") + 1)
							+ "  ");
				} else if (allReceiverNames[i].indexOf("roleList") != -1) {
					roleReceiverNames.append(allReceiverNames[i]
							.substring(allReceiverNames[i].indexOf("=") + 1)
							+ "  ");
				} else {
					userReceiverNames.append(allReceiverNames[i]
							.substring(allReceiverNames[i].indexOf("=") + 1)
							+ "  ");
				}
			}
		}
		result.put("user", userReceiverNames.toString());
		result.put("org", orgReceiverNames.toString());
		result.put("role", roleReceiverNames.toString());
		return result;
	}

	/***
	 * 给草稿箱消息保存收件人信息
	 */
	private PlatformMessage setPlatformMessageRecipientInfo(
			String userReceivers, String orgReceivers, String roleReceivers,
			String receiverNames, PlatformMessage platformMessage) {
		StringBuffer sb = new StringBuffer();
		StringBuffer receversName = new StringBuffer();
		if (userReceivers != null) {
			sb.append("user:" + userReceivers);
		}
		if (orgReceivers != null && orgReceivers.trim().length() != 0) {
			if ("".equals(orgReceivers.split(",").length == 0 ? ""
					: orgReceivers.split(",")[0])) {
				orgReceivers = orgReceivers.substring(1, orgReceivers.length());
			}
			sb.append("|org:" + orgReceivers);
		}
		if (roleReceivers != null) {
			sb.append("|role:" + roleReceivers);
		}
		if (sb != null) {
			sb.append("&receiberNames:" + receiverNames);
		}

		if (sb != null) {
			platformMessage.setRecipientInfo(sb.toString());
		}
		if (receversName != null
				&& !StringUtil.isStringAvaliable(platformMessage
						.getReceiverNames())) {
			platformMessage.setReceiverNames(receversName.toString());
		}
		return platformMessage;
	}

	/***
	 * 查看消息详情
	 * 
	 * @return
	 */
	@Actions({
			@Action(value = "getOutboxPlatformMessageById", results = { @Result(name = "success", location = "/interaction/platformMessage/outbox/platformMessageOutboxAccordingContext.jsp") }),
			@Action(value = "getOutboxPlatformMessageByIdForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"platformMessage", "ignoreHierarchy", "false",
							"excludeNullProperties", "true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String getOutboxPlatformMessageById() throws Exception {
		if (platformMessage == null || platformMessage.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		platformMessage = platformMessageService
				.getOutboxPlatformMessageById(platformMessage.getId());
		return SUCCESS;
	}

	/**
	 * 删除消息
	 * 
	 * @return
	 */
	@Action(value = "deletePlatformMessageById", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	@PermissionFilter(ename = "deletePmOutbox")
	public String deletePlatformMessageById() throws Exception {
		if (deleteIds == null) {
			errorMessage = "删除信息Id不能为空";
			return ERROR;
		}
		Long[] ids = analyze(deleteIds);
		for (int i = 0; i < ids.length; i++) {
			platformMessageService.deletePlatformMessageFromOutboxById(ids[i]);
		}
		return SUCCESS;
	}

	/**
	 * 下载消息附件
	 * 
	 * @return
	 */
	@Action(value = "downloadPmAttachFileById")
	public String downloadPmAttachFile() throws Exception {
		if (pmAttachFile == null || pmAttachFile.getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		pmAttachFile = platformMessageService
				.getPlatformMessageAttachFileById(pmAttachFile.getId());

		if (pmAttachFile == null) {
			this.errorMessage = "未找到对应的附件";
			return ERROR;
		}
		inputStream = new FileInputStream(FileUtil.getWebRoot()
				+ File.separator + pmAttachFile.getFileActualUrl());
		downloadFileName = new String(pmAttachFile.getFileName()
				.getBytes("gbk"), "ISO8859-1");
		return STREAM_SUCCESS;
	}

	/** 校验消息 */
	private boolean validatePlatformMessage() {

		if (!StringUtil.isStringAvaliable(platformMessage.getTitle())) {
			errorMessage = "平台消息标题不能为空";
			return false;
		}
		if (platformMessage.getContent() == null) {
			errorMessage = "消息内容不能为空";
			return false;
		}
		if (!StringUtil.isStringAvaliable(userReceivers)
				&& !StringUtil.isStringAvaliable(orgReceivers)
				&& !StringUtil.isStringAvaliable(roleReceivers)) {
			errorMessage = "收件人不能为空";
			return false;
		}
		return true;

	}

	/** 获取所有站内联系人id */
	private List<Long> getUserReceiverIds(String userReceiverId) {

		String[] userReceiverIds = userReceiverId.split(",");

		Set<Long> list = new HashSet<Long>();

		for (String id : userReceiverIds) {
			if (id == null || "".equals(id) || id.endsWith("-levelList")
					|| id.endsWith("-roleList")) {
				continue;
			}
			List<WorkContacter> workContacters = contacterDubboService
					.findWorkContacterById(Long.parseLong(id));
			if (workContacters != null && workContacters.size() > 0) {
				for (WorkContacter workContacter : workContacters) {

					list.add(workContacter.getFromUser().getId());
				}
			}
		}
		List<Long> userReciverIds = new ArrayList<Long>();

		userReciverIds.addAll(list);

		return userReciverIds;
	}

	/** 获取部门收件人id */

	private List<Long> getOrgReceiverIds(String orgId_orgLevel_orgType_str) {
		// [20-2,20-1]
		String[] orgIdAndOrgLevelAndOrgTypes = orgId_orgLevel_orgType_str
				.split(",");

		List<Long> orgReceiverIds = new ArrayList<Long>();

		for (String orgIdAndOrgLevelAndOrgType : orgIdAndOrgLevelAndOrgTypes) {

			if (StringUtil.isStringAvaliable(orgIdAndOrgLevelAndOrgType)) {

				String[] orgId_orgLevel_orgType = orgIdAndOrgLevelAndOrgType
						.split("_");

				Long orgId = Long.valueOf(orgId_orgLevel_orgType[0]);

				Integer orgLevel = Integer.valueOf(orgId_orgLevel_orgType[1]);

				Integer orgType = Integer.valueOf(orgId_orgLevel_orgType[2]);

				List<Long> userIds = permissionService
						.findUserIdsByByOrgTypeAndOrgLevelAndOrgParentId(
								orgType, orgLevel, orgId);

				orgReceiverIds.addAll(userIds);

			}

		}
		// 用HashSet去重
		HashSet h = new HashSet(orgReceiverIds);

		orgReceiverIds.clear();

		orgReceiverIds.addAll(h);

		return orgReceiverIds;
	}

	/** 获取岗位收件人id */
	private List<Long> getRoleReceiverIds(String roleReceiverIdstr) {

		String[] arr = roleReceiverIdstr.split(",");

		List<Long> roelReceiverIds = new ArrayList<Long>();

		for (String roleId_OrgId_RoleScopeStr : arr) {
			if (!StringUtil.isStringAvaliable(roleId_OrgId_RoleScopeStr)) {
				continue;
			}
			String[] roleId_OrgId_RoleScope = roleId_OrgId_RoleScopeStr
					.split("_");
			Long roleId = Long.parseLong(roleId_OrgId_RoleScope[0]);
			Long orgId = Long.parseLong(roleId_OrgId_RoleScope[1]);
			List<Long> userIds = permissionService.findUserIdsByOrgIdAndRoleId(
					orgId, roleId);
			roelReceiverIds.addAll(userIds);
		}
		// 用HashSet去重
		HashSet h = new HashSet(roelReceiverIds);
		roelReceiverIds.clear();
		roelReceiverIds.addAll(h);
		return roelReceiverIds;
	}

	List<StoredFile> storedFileList = new ArrayList<StoredFile>();

	/**
	 * 添加附件
	 * 
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	private boolean addPlatformMessageAttachFile() throws Exception {
		/* 处理手机端业务 */
		if (null != attachFile && !"".equals(attachFile)) {
			String[] arrStr = attachFile.split(";");
			attachFiles = new HashSet();
			for (int i = 0; i < arrStr.length; i++) {
				if (null != arrStr[i] && !"".equals(arrStr[i])) {
					attachFiles.add(arrStr[i]);
				}
			}

		}
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

	/** 收件人自动联想补全 */
	@Action(value = "searchContacterForAutoComplete", results = {
			@Result(name = "success", type = "json", params = { "root",
					"autoCompletePersonnelDatas", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchContacterForAutoComplete() throws Exception {
		if (!StringUtil.isStringAvaliable(tag)) {
			return SUCCESS;
		}
		// 站内联系人
		List<WorkContacter> workContacterList = contacterDubboService
				.findWorkContactersByNameAndPinyin(tag);
		for (WorkContacter workContacter : workContacterList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(workContacter.getName());
			autoCompleteData.setValue(workContacter.getId().toString());
			autoCompletePersonnelDatas.add(autoCompleteData);
		}
		// 我的群组
		List<MyGroup> myGroupList = contacterDubboService
				.findMyGroupsByNameAndPinyinAndOwnerId(tag, ThreadVariable
						.getUser().getId());
		for (MyGroup myGroup : myGroupList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(myGroup.getName());
			autoCompleteData.setValue(myGroup.getId().toString());
			autoCompletePersonnelDatas.add(autoCompleteData);
		}
		return SUCCESS;
	}

	/** 根据orgId获取该部门下的所有平台内联系人 */
	@Action(value = "findWorkContacts", results = {
			@Result(name = "success", type = "json", params = { "root",
					"workContacts", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findWorkContacts() throws Exception {
		if (orgId != null) {
			workContacts = contacterDubboService
					.findWorkContactersByOrganizationId(orgId);
		} else {
			this.errorMessage = "orgId 不能为空";
			return ERROR;
		}
		return SUCCESS;
	}

	// 设置发件人
	private void setPlatformMessageSender() {

		User user = this.permissionService.getSimpleUserById(ThreadVariable
				.getUser().getId());

		if (platformMessage == null) {

			platformMessage = new PlatformMessage();
		}

		platformMessage.setSender(user);

	}

	public Set<String> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(Set<String> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public List<WorkContacter> getWorkContacts() {
		return workContacts;
	}

	public void setWorkContacts(List<WorkContacter> workContacts) {
		this.workContacts = workContacts;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public List<MyGroup> getMyGroups() {
		return myGroups;
	}

	public void setMyGroups(List<MyGroup> myGroups) {
		this.myGroups = myGroups;
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

	private String deleteIds;

	public String getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public Integer getRoleScope() {
		return roleScope;
	}

	public void setRoleScope(Integer roleScope) {
		this.roleScope = roleScope;
	}

	public PlatformMessage getPlatformMessage() {
		return platformMessage;
	}

	public void setPlatformMessage(PlatformMessage platformMessage) {
		this.platformMessage = platformMessage;
	}

	public String getUserReceivers() {
		return userReceivers;
	}

	public void setUserReceivers(String userReceivers) {
		this.userReceivers = userReceivers;
	}

	public String getOrgReceivers() {
		return orgReceivers;
	}

	public void setOrgReceivers(String orgReceivers) {
		this.orgReceivers = orgReceivers;
	}

	public String getRoleReceivers() {
		return roleReceivers;
	}

	public void setRoleReceivers(String roleReceivers) {
		this.roleReceivers = roleReceivers;
	}

	public PlatformMessageAttachFile getPmAttachFile() {
		return pmAttachFile;
	}

	public void setPmAttachFile(PlatformMessageAttachFile pmAttachFile) {
		this.pmAttachFile = pmAttachFile;
	}

	public String getAttachfile() {
		return attachFile;
	}

	public void setAttachfile(String attachFile) {
		this.attachFile = attachFile;
	}

	public Integer getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String getRoleAndOrgIds() {
		return roleAndOrgIds;
	}

	public void setRoleAndOrgIds(String roleAndOrgIds) {
		this.roleAndOrgIds = roleAndOrgIds;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getOrgNames() {
		return orgNames;
	}

	public void setOrgNames(String orgNames) {
		this.orgNames = orgNames;
	}

	public String getRoleAndOrgNames() {
		return roleAndOrgNames;
	}

	public void setRoleAndOrgNames(String roleAndOrgNames) {
		this.roleAndOrgNames = roleAndOrgNames;
	}

	public String getReceiverNames() {
		return receiverNames;
	}

	public void setReceiverNames(String receiverNames) {
		this.receiverNames = receiverNames;
	}

	public String getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String attachFile) {
		this.attachFile = attachFile;
	}

}
