package com.tianque.plugin.weChat.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.primaryOrg.domain.RedCuffTeam;
import com.tianque.baseInfo.primaryOrg.service.RedCuffTeamService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.PropertyDict;
import com.tianque.job.JobHelper;
import com.tianque.plugin.weChat.domain.sendMessage.TextMessage;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.domain.user.WeChatGroup;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.WeChatGroupService;
import com.tianque.plugin.weChat.service.WeChatService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 微信中心红袖套认证
 * 
 * @author Administrator
 *
 */
@Namespace("/authenticationRedCuffTeamManage")
@Scope("prototype")
@Controller("authenticationRedCuffTeamController")
public class AuthenticationRedCuffTeamController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(AuthenticationRedCuffTeamController.class);

	@Autowired
	private RedCuffTeamService redCuffTeamService;
	@Autowired
	private TencentUserService tencentUserService;
	@Autowired
	private WeChatGroupService weChatGroupService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private WeChatService weChatService;
	/** 文本 */
	private TextMessage textMessage;
	/** 组织id */
	private Long orgId;
	/** 电话号码 */
	private String phoneNumber;
	/** 姓名 */
	private String name;
	/** 用户openId */
	private String openId;
	/** 微信公众号 */
	private String weChatUserId;

	@Action(value = "dispatch", results = {
			@Result(name = "authentication", location = "/wechat/redCuffTeam/authenticationRedCuffTeam.jsp"),
			@Result(name = "error", location = "/wechat/redCuffTeam/authenticationFailure.jsp") })
	public String dispatch() throws Exception {
		if (ThreadVariable.getSession() == null) {// 页面pop:OptionTag标签需要做session验证，微信上是未登录的伪造一个。
			JobHelper.createMockAdminSession();
		}
		if (mode == null) {
			return "请求参数错误";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		if (!StringUtil.isStringAvaliable(ThreadVariable.getWeChatOpenId())) {
			// 用户openId获取失败
			errorMessage = "获取openId失败，请返回重新输入!";
			request.setAttribute("errorMessage", errorMessage);
			return ERROR;
		}
		RedCuffTeam redCuffTeam = redCuffTeamService
				.getRedCuffTeamByWechatno(ThreadVariable.getWeChatOpenId());
		if (redCuffTeam != null) {
			// 用户已经认证过
			errorMessage = "你已经认证过，不需再次认证!";
			request.setAttribute("errorMessage", errorMessage);
			return ERROR;
		}
		if (mode.equals("authentication")) {
			request.setAttribute("orgId", orgId);
			request.setAttribute("weChatUserId", weChatUserId);
			request.setAttribute("openId", ThreadVariable.getWeChatOpenId());
			return "authentication";
		}
		errorMessage = "菜单参数配置错误，请检测菜单配置!";
		request.setAttribute("errorMessage", errorMessage);
		return ERROR;
	}

	/**
	 * 红袖套认证
	 */
	@Action(value = "authenticationRedCuffTeam", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String authenticationRedCuffTeam() {
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletActionContext.getResponse().setContentType("text/json");

		if (ThreadVariable.getSession() == null) {// 页面pop:OptionTag标签需要做session验证，微信上是未登录的伪造一个。
			JobHelper.createMockAdminSession();
		}
		if (!StringUtil.isStringAvaliable(phoneNumber)
				|| !StringUtil.isStringAvaliable(name)) {
			// 未接收到参数
			errorMessage = "认证失败，未获得关键参数";
			request.setAttribute("errorMessage", errorMessage);
			return ERROR;
		}
		RedCuffTeam redCuffTeam = redCuffTeamService
				.getRedCuffTeamByPhoneAndName(phoneNumber, name);

		if (redCuffTeam == null) {
			// 未找到该成员
			errorMessage = "你不是红袖套成员";
			request.setAttribute("errorMessage", errorMessage);
			return ERROR;
		} else if (redCuffTeam != null
				&& redCuffTeam.getIsCertification() != null
				&& redCuffTeam.getIsCertification() == 1) {
			// 已经经过验证
			errorMessage = "你已经认证过，请勿重复认证!";
			request.setAttribute("errorMessage", errorMessage);
			return ERROR;
		} else {
			if (!StringUtil.isStringAvaliable(openId)) {
				// 用户openId获取失败
				errorMessage = "获取openId失败，请返回重新输入!";
				request.setAttribute("errorMessage", errorMessage);
				return ERROR;
			}
			Integer number = redCuffTeamService.weChatBindingRedCuffTeam(
					openId, redCuffTeam.getId());

			// 红袖套认证分组 暂时屏蔽
			if (number != null) {

				try {
					if (redCuffTeam.getSubTeamType() != null
							&& redCuffTeam.getSubTeamType().getId() != null
							&& weChatUserId != null) {

						PropertyDict propertyDict = propertyDictService
								.getPropertyDictById(redCuffTeam
										.getSubTeamType().getId());
						// 获取公众账号对象
						TencentUser tencentUser = tencentUserService
								.getTencentUserByWeChatUserId(weChatUserId);
						if (tencentUser != null
								&& propertyDict.getDisplayName() != null) {
							WeChatGroup weChatGroup = weChatGroupService
									.getGroupListByNameAndWeChatUserId(
											weChatUserId,
											propertyDict.getDisplayName());
							if (weChatGroup != null) {

								if (weChatService.getFanByOpenId(openId,
										tencentUser) != null) {
									weChatGroupService.removeUserForGroup(
											tencentUser, openId, weChatGroup
													.getGroupId().intValue());
									weChatGroupService.updateAllRelatedGroups(
											tencentUser,
											weChatGroup.getGroupId(), openId);
								}
							}
						}
					}
				} catch (Exception e) {
					logger.error("认证分组异常", e);
					// 分组异常不影响认证操作
					return SUCCESS;
				}

			}
		}

		return SUCCESS;
	}

	public TextMessage getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(TextMessage textMessage) {
		this.textMessage = textMessage;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getWeChatUserId() {
		return weChatUserId;
	}

	public void setWeChatUserId(String weChatUserId) {
		this.weChatUserId = weChatUserId;
	}

}
