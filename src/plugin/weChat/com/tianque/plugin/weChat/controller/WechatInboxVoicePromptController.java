package com.tianque.plugin.weChat.controller;

import java.io.FileNotFoundException;
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
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.plugin.weChat.domain.inbox.Inbox;
import com.tianque.plugin.weChat.domain.inbox.PreciseInbox;
import com.tianque.plugin.weChat.domain.inbox.WechatInboxVoicePrompt;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.service.InboxService;
import com.tianque.plugin.weChat.service.PreciseInboxService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.WechatInboxVoicePromptService;
import com.tianque.plugin.weChat.util.Constants;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 微信消息提示语音设置
 * 
 * @author Administrator
 *
 */
@Namespace("/wechatInboxVoicePromptManage")
@Scope("prototype")
@Controller("wechatInboxVoicePromptController")
public class WechatInboxVoicePromptController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(WechatInboxVoicePromptController.class);

	@Autowired
	private WechatInboxVoicePromptService wechatInboxVoicePromptService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private TencentUserService tencentUserService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private InboxService inboxService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	PreciseInboxService preciseInboxService;

	private WechatInboxVoicePrompt wechatInboxVoicePrompt;

	private WechatInboxVoicePrompt newwechatInboxVoicePrompt;

	@Action(value = "dispatch", results = { @Result(name = "edit", location = "/wechat/wechatInboxVoicePrompt/editWechatInboxVoicePrompt.jsp") })
	public String dispatch() throws Exception {

		if (mode.equals("edit")) {

			newwechatInboxVoicePrompt = wechatInboxVoicePromptService
					.findWechatInboxVoicePrompt(wechatInboxVoicePrompt);

			return "edit";
		}
		return ERROR;
	}

	/**
	 * 列表查询
	 * 
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	@Action(value = "findWechatInboxVoicePrompts", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findWechatInboxVoicePrompts() {
		try {
			wechatInboxVoicePrompt.setOrg(organizationDubboService
					.getSimpleOrgById(wechatInboxVoicePrompt.getOrg().getId()));
			PageInfo<WechatInboxVoicePrompt> pageInfo = wechatInboxVoicePromptService
					.findwechatInboxVoicePrompts(wechatInboxVoicePrompt, page,
							rows, sidx, sord);
			ControllerHelper.processAllOrgName(pageInfo.getResult(),
					organizationDubboService, new String[] { "org" });
			gridPage = new GridPage(pageInfo);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("findwechatInboxVoicePromptsERROR", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/**
	 * 查询当前层级是否有微信公众号,有则绑定初始化微信消息语音提示数据
	 * 
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */
	@Action(value = "findVoicePromptStatusAndInit", results = {
			@Result(type = "json", name = "success", params = { "root",
					"wechatInboxVoicePrompt", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String findVoicePromptStatusAndInit() {

		if (wechatInboxVoicePrompt == null
				|| wechatInboxVoicePrompt.getOrg() == null
				|| wechatInboxVoicePrompt.getOrg().getId() == null) {
			return ERROR;
		}
		User user = ThreadVariable.getUser();
		if (user == null) {
			return ERROR;
		}
		if (user.isAdmin()) {
			return ERROR;
		}

		// 判断当前用户是否拥有微信模糊消息权限
		if (!permissionService
				.findUserAllPermissionByUserIdAndPermissionEnames(user.getId(),
						"fuzzyMessageManagement")) {
			return ERROR;
		}

		List<TencentUser> tencentUserList = tencentUserService
				.getTencentUserListByOrgId(wechatInboxVoicePrompt.getOrg()
						.getId());
		if (tencentUserList == null || tencentUserList.isEmpty()) {
			return ERROR;
		}
		// 只取第一个
		TencentUser tencentUser = tencentUserList.get(0);
		wechatInboxVoicePrompt.setToUserName(tencentUser.getWeChatUserId());
		WechatInboxVoicePrompt oldWechatInboxVoicePrompt = wechatInboxVoicePromptService
				.findWechatInboxVoicePrompt(wechatInboxVoicePrompt);

		if (oldWechatInboxVoicePrompt != null) {
			Inbox inbox = new Inbox();

			inbox.setOrg(organizationDubboService
					.getSimpleOrgById(oldWechatInboxVoicePrompt.getOrg()
							.getId()));
			inbox.setDealState(Constants.NOT_ACCEPT);
			inbox.setIsRead(Constants.NOT_READ);
			// 查看是否有新的未读消息(模糊消息)
			List<Inbox> oldInbox = inboxService
					.findInboxsByOpenIdAndDealState(inbox);

			if (oldInbox != null && !oldInbox.isEmpty()) {
				oldWechatInboxVoicePrompt.setIsNewInbox(Constants.NEWINBOX);
			}

			PreciseInbox preciseInbox = new PreciseInbox();
			preciseInbox.setOrg(organizationDubboService
					.getSimpleOrgById(oldWechatInboxVoicePrompt.getOrg()
							.getId()));
			preciseInbox.setDealState(Constants.NOT_ACCEPT);
			preciseInbox.setIsRead(Constants.NOT_READ);
			// 查看是否有新的未读消息(精确消息(流动人口消息、治安隐患消息、综合服务消息))
			List<PreciseInbox> preciseInboxList = preciseInboxService.findPreciseInboxsByDealStateAndIsRead(preciseInbox);
			
			if (preciseInboxList != null && !preciseInboxList.isEmpty()) {
				oldWechatInboxVoicePrompt.setIsNewInbox(Constants.NEWINBOX);
			}
			
			wechatInboxVoicePrompt = oldWechatInboxVoicePrompt;
			return SUCCESS;
		} else {

			// 如果等于空就设置默认的语音路径，默认关闭语音提示
			PropertyDict propertyType = propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.WECHAT_INBOX_VOICE_PROMPT_TYPE, "关闭");

			wechatInboxVoicePrompt.setVoicePromptStatus(propertyType);
			wechatInboxVoicePrompt
					.setVoiceUrl(GridProperties.DEFAULT_WECHAT_INBOX_VOICE_PROMPT_FLIE);
			wechatInboxVoicePromptService
					.addWechatInboxVoicePrompt(wechatInboxVoicePrompt);
		}
		return SUCCESS;
	}

	// 更新
	@Action(value = "updateWechatInboxVoicePromptById", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateWechatInboxVoicePromptById() {
		if (wechatInboxVoicePrompt == null) {
			errorMessage = "修改失败,未获取关键参数";
			return ERROR;
		}
		wechatInboxVoicePromptService
				.updateWechatInboxVoicePromptById(wechatInboxVoicePrompt);
		return SUCCESS;
	}

	public WechatInboxVoicePrompt getWechatInboxVoicePrompt() {
		return wechatInboxVoicePrompt;
	}

	public void setWechatInboxVoicePrompt(
			WechatInboxVoicePrompt wechatInboxVoicePrompt) {
		this.wechatInboxVoicePrompt = wechatInboxVoicePrompt;
	}

	public WechatInboxVoicePrompt getNewwechatInboxVoicePrompt() {
		return newwechatInboxVoicePrompt;
	}

	public void setNewwechatInboxVoicePrompt(
			WechatInboxVoicePrompt newwechatInboxVoicePrompt) {
		this.newwechatInboxVoicePrompt = newwechatInboxVoicePrompt;
	}

}
