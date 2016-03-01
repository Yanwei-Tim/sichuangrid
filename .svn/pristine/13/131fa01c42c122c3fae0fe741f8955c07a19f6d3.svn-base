package com.tianque.plugin.weChat.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.constant.MessageTemplateConstant;
import com.tianque.plugin.weChat.domain.inbox.Inbox;
import com.tianque.plugin.weChat.domain.messageTemplate.MessageTemplate;
import com.tianque.plugin.weChat.domain.messageTemplate.TemplateMessageRecord;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.domain.user.WeChatGroup;
import com.tianque.plugin.weChat.service.InboxService;
import com.tianque.plugin.weChat.service.MessageTemplateService;
import com.tianque.plugin.weChat.service.WeChatGroupService;
import com.tianque.plugin.weChat.service.WeChatService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 消息模板
 */
@Namespace("/messageTemplateManage")
@Scope("prototype")
@Controller("messageTemplateController")
public class MessageTemplateController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(MessageTemplateController.class);
	@Autowired
	private WeChatService weChatService;
	@Autowired
	private MessageTemplateService messageTemplateService;
	@Autowired
	private InboxService inboxService;
	@Autowired
	private WeChatGroupService weChatGroupService;
	@Autowired
	private OrganizationDubboService organizationService;
	private Integer templateType;
	private MessageTemplate messageTemplate;
	/** 收件箱对象 */
	private Inbox inbox;
	/** 粉丝号 */
	private String openIdStr;
	private TencentUser tencentUser;
	private List<WeChatGroup> weChatGroupList;
	private TemplateMessageRecord templateMessageRecord;
	private String templateMessageRecordIds;

	@Action(value = "dispatch", results = {
			@Result(name = "reportProgressNotice", location = "/template/messageTemplate/reportProgressNoticeDlg.ftl"),
			@Result(name = "noticeToBeProcessed", location = "/template/messageTemplate/questionBackCompletionNoticeDlg.ftl"),
			@Result(name = "selectFanOrGroup", location = "/template/messageTemplate/selectPersonDlg.ftl"),
			@Result(name = "success", location = "/template/messageTemplate/templateMessageRecordDlg.ftl"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() {
		try {
			if (MessageTemplateConstant.SELECTFANORGROUP.equals(mode)) {
				if (null == tencentUser.getWeChatUserId()
						|| "".equals(tencentUser.getWeChatUserId())) {
					this.errorMessage = "微信服务号不能为空！";
					return ERROR;
				} else {
					weChatGroupList = weChatGroupService
							.getGroupListWeChatUserId(tencentUser
									.getWeChatUserId());
					return MessageTemplateConstant.SELECTFANORGROUP;
				}
			} else if (DialogMode.ADD_MODE.equals(mode)) {
				templateMessageRecord = new TemplateMessageRecord();
				templateMessageRecord.setOrg(ThreadVariable.getOrganization());
				return SUCCESS;
			} else if (DialogMode.EDIT_MODE.equals(mode)) {
				if (templateMessageRecord == null
						|| templateMessageRecord.getId() == null) {
					this.errorMessage = "获取模板记录id出错！";
					return ERROR;
				}
				templateMessageRecord = messageTemplateService
						.getTemplateMessageRecordById(templateMessageRecord
								.getId());
				templateMessageRecord.setOrg(ThreadVariable.getOrganization());
				return SUCCESS;
			}
			if (inbox == null || inbox.getInboxId() == null) {
				errorMessage = "模板消息跳转出错,请刷新后重试!";
				return ERROR;
			}
			inbox = inboxService.getInboxById(inbox.getInboxId());
			if (templateType.intValue() == MessageTemplateConstant.REPORT_PROGRESS_NOTICE_VALUE) {
				return MessageTemplateConstant.REPORT_PROGRESS_NOTICE;
			} else if (templateType.intValue() == MessageTemplateConstant.NOTICE_TO_BE_PROCESSED_VALUE) {
				return MessageTemplateConstant.NOTICE_TO_BE_PROCESSED;
			} else {
				errorMessage = "模板消息跳转出错,请刷新后重试!";
				return ERROR;
			}
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("跳转页面错误", e);
			this.errorMessage = "跳转页面出错";
			return ERROR;
		}
	}

	/**
	 * 模板消息发送
	 */
	@Action(value = "sendTemplateMessage", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "sendTemplateMessage")
	public String sendTemplateMessage() {
		try {
			if (messageTemplate == null || templateType == null) {
				errorMessage = "模板消息发送错误,请刷新后重试!";
				return ERROR;
			}
			selectionMethod();
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("模板消息发送错误", e);
			this.errorMessage = "模板消息发送出错";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 消息模板记录-列表分页
	 */
	@Action(value = "findTemplateMessageRecordByPage", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findTemplateMessageRecordByPage() {
		try {
			if (templateMessageRecord == null
					|| templateMessageRecord.getOrg() == null
					|| templateMessageRecord.getOrg().getId() == null) {
				errorMessage = "获取消息模板列表出错,请刷新后重试!";
				return ERROR;
			}
			PageInfo<TemplateMessageRecord> templateRecordList = messageTemplateService
					.findTemplateMessageRecordByPage(templateMessageRecord,
							page, rows, sidx, sord);
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					templateRecordList, organizationService,
					new String[] { "org" }, templateMessageRecord.getOrg()
							.getId()));
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("获取消息模板列表错误", e);
			this.errorMessage = "获取消息模板列表错误";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 消息模板-新增
	 */
	@Action(value = "addTemplateMessageRecord", results = {
			@Result(name = "success", type = "json", params = { "root",
					"templateMessageRecord", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addTemplateMessageRecord")
	public String addTemplateMessageRecord() {
		try {
			if (templateMessageRecord == null
					|| templateMessageRecord.getOrg() == null
					|| templateMessageRecord.getOrg().getId() == null) {
				errorMessage = "新增消息模板出错,请刷新后重试!";
				return ERROR;
			}
			templateMessageRecord = messageTemplateService
					.addTemplateMessageRecord(templateMessageRecord);
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("新增消息模板出错", e);
			this.errorMessage = "新增消息模板出错";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 消息模板-修改
	 */
	@Action(value = "updateTemplateMessageRecord", results = { @Result(name = "success", type = "json", params = {
			"root", "templateMessageRecord", "ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "updateTemplateMessageRecord")
	public String updateTemplateMessageRecord() {
		try {
			if (templateMessageRecord == null
					|| templateMessageRecord.getId() == null) {
				errorMessage = "修改消息模板出错,请刷新后重试!";
				return ERROR;
			}
			templateMessageRecord = messageTemplateService
					.updateTemplateMessageRecord(templateMessageRecord);
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("修改消息模板出错", e);
			this.errorMessage = "修改消息模板出错";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 删除消息模板
	 */
	@Action(value = "deleteTemplateMessageRecordById", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteTemplateMessageRecord")
	public String deleteTemplateMessageRecordById() {
		try {
			if (!StringUtil.isStringAvaliable(templateMessageRecordIds)) {
				errorMessage = "请选择要删除的数据！";
				return ERROR;
			}
			Long[] ids = analyze(templateMessageRecordIds);
			messageTemplateService.deleteTemplateMessageRecordById(ids);
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("删除消息模板", e);
			this.errorMessage = "删除消息模板出错";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 根据不同的模板类型，调用不同的service方法
	 */
	private void selectionMethod() {
		// 原本区分回复模板还是转发模板（现已经全部默认转发式模板）
		// int templateTypeValue = templateType.intValue();
		// if (templateTypeValue ==
		// MessageTemplateConstant.REPORT_PROGRESS_NOTICE_VALUE) {
		// messageTemplateService.sendTemplateMessage(templateType,
		// messageTemplate);
		// } else if (templateTypeValue ==
		// MessageTemplateConstant.NOTICE_TO_BE_PROCESSED_VALUE) {
		// messageTemplateService.forwardTemplateMessage(templateType,
		// messageTemplate, openIdStr);
		// }
		messageTemplateService.forwardTemplateMessage(templateType,
				messageTemplate, openIdStr);
	}

	public MessageTemplate getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(MessageTemplate messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public Inbox getInbox() {
		return inbox;
	}

	public void setInbox(Inbox inbox) {
		this.inbox = inbox;
	}

	public String getOpenIdStr() {
		return openIdStr;
	}

	public void setOpenIdStr(String openIdStr) {
		this.openIdStr = openIdStr;
	}

	public TencentUser getTencentUser() {
		return tencentUser;
	}

	public void setTencentUser(TencentUser tencentUser) {
		this.tencentUser = tencentUser;
	}

	public List<WeChatGroup> getWeChatGroupList() {
		return weChatGroupList;
	}

	public void setWeChatGroupList(List<WeChatGroup> weChatGroupList) {
		this.weChatGroupList = weChatGroupList;
	}

	public TemplateMessageRecord getTemplateMessageRecord() {
		return templateMessageRecord;
	}

	public void setTemplateMessageRecord(
			TemplateMessageRecord templateMessageRecord) {
		this.templateMessageRecord = templateMessageRecord;
	}

	public String getTemplateMessageRecordIds() {
		return templateMessageRecordIds;
	}

	public void setTemplateMessageRecordIds(String templateMessageRecordIds) {
		this.templateMessageRecordIds = templateMessageRecordIds;
	}

}
