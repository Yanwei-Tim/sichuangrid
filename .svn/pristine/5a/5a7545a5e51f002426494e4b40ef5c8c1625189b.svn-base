package com.tianque.plugin.weChat.controller;

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
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.redEnvelopeManagement.RedEnvelope;
import com.tianque.plugin.weChat.domain.user.Fan;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.service.FanService;
import com.tianque.plugin.weChat.service.RedEnvelopeService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.WeChatService;
import com.tianque.plugin.weChat.vo.RedEnvelopeVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 微信红包
 */
@Namespace("/redEnvelopeManage")
@Scope("prototype")
@Controller("redEnvelopeController")
public class RedEnvelopeController extends BaseAction {
	private static Logger logger = LoggerFactory.getLogger(RedEnvelopeController.class);

	@Autowired
	private RedEnvelopeService redEnvelopeService;
	@Autowired
	private FanService fanService;
	@Autowired
	private TencentUserService tencentUserService;
	@Autowired
	private WeChatService weChatService;
	@Autowired
	private OrganizationDubboService organizationService;
	private RedEnvelope redEnvelope;
	private RedEnvelopeVo redEnvelopeVo;
	private Fan fan;
	//配置完成的红包id
	private Long redEnvelopeId;
	//接受收红包的用户 用户在wxappid下的openid
	private String re_openid;
	//微信公众服务号
	private String weChatUserId;
	//微信公众号信息（账号管理）
	private TencentUser tencentUser;
	//获取微信返回的信息
	private String return_msg;
	private String redEnvelopeIds;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/template/redEnvelopeManagement/redEnvelopesDlg.ftl"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String dispatch() {
		try {
			if (redEnvelopeId == null) {
				errorMessage = "获取参数出错,请刷新重试";
				return ERROR;
			}
			redEnvelope = redEnvelopeService.getRedEnvelopeById(redEnvelopeId);
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("跳转页面错误", e);
			this.errorMessage = "跳转页面出错";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 创建红包(红包的基本配置)
	 */
	@Action(value = "addRedEnvelope", results = {
			@Result(name = "success", type = "json", params = { "root", "redEnvelope",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "addRedEnvelope")
	public String addRedEnvelope() {
		try {
			if (redEnvelope == null || redEnvelope.getOrg() == null
					|| redEnvelope.getOrg().getId() == null) {
				errorMessage = "创建红包出错,请刷新后重试!";
				return ERROR;
			}
			redEnvelope = redEnvelopeService.addRedEnvelope(redEnvelope);
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("类RedEnvelopeController的addRedEnvelope方法出现异常，原因：", e);
			this.errorMessage = "创建红包出错";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 修改红包(红包的基本配置)
	 */
	@Action(value = "updateRedEnvelope", results = {
			@Result(name = "success", type = "json", params = { "root", "redEnvelope",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage",
					"ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "updateRedEnvelope")
	public String updateRedEnvelope() {
		try {
			if (redEnvelope.getId() == null) {
				errorMessage = "修改红包出错,请刷新后重试!";
				return ERROR;
			}
			redEnvelope = redEnvelopeService.updateRedEnvelope(redEnvelope);
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("类RedEnvelopeController的updateRedEnvelope方法出现异常，原因：", e);
			this.errorMessage = "修改红包出错";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 获取红包(配置信息)分页列表
	 */
	@Action(value = "findRedEnvelopeByPage", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findRedEnvelopeByPage() {
		try {
			if (redEnvelopeVo == null || redEnvelopeVo.getOrg() == null
					|| redEnvelopeVo.getOrg().getId() == null) {
				errorMessage = "获取红包分页列表出错,请刷新后重试!";
				return ERROR;
			}
			PageInfo<RedEnvelopeVo> redEnvelopeVoList = redEnvelopeService.findRedEnvelopeByPage(
					redEnvelopeVo, page, rows, sidx, sord);
			gridPage = new GridPage(redEnvelopeVoList);
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("类RedEnvelopeController的findRedEnvelopeByPage方法出现异常，原因：", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 获取红包(配置信息)分页列表(用于消息管理里面的发放红包，将依据粉丝所在的公众号，以及当前部门进行查询)
	 */
	@Action(value = "findRedEnvelopeForInboxByPage", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findRedEnvelopeForInboxByPage() {
		try {
			if (fan == null || fan.getFanId() == null || redEnvelopeVo == null) {
				errorMessage = "获取红包分页列表出错,请刷新后重试!";
				return ERROR;
			}
			PageInfo<RedEnvelopeVo> redEnvelopeVoList = redEnvelopeService
					.findRedEnvelopeForInboxByPage(fan, redEnvelopeVo, page, rows, sidx, sord);
			gridPage = new GridPage(redEnvelopeVoList);
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("类RedEnvelopeController的findRedEnvelopeForInboxByPage方法出现异常，原因：", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 根据(Fan中的服务号)获取粉丝分页列表(发送红包时，提供选择对应公众号粉丝)
	 */
	@Action(value = "findFanListByWeChatUserIdForPage", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findFanListByWeChatUserIdForPage() {
		try {
			if (fan == null || !StringUtil.isStringAvaliable(fan.getWeChatUserId())) {
				errorMessage = "获取粉丝分页列表出错,请刷新后重试!";
				return ERROR;
			}
			PageInfo<Fan> fanForWeChatUserIdList = fanService.findFanListByWeChatUserIdForPage(fan,
					page, rows, sidx, sord);
			gridPage = new GridPage(fanForWeChatUserIdList);
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("类RedEnvelopeController的findFanListByWeChatUserIdForPage方法出现异常，原因：", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 根据部门id获取账号列表(创建红包时，提供选择对应的账号)
	 */
	@Action(value = "findTencentUserList", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String findTencentUserList() {
		try {
			if (redEnvelope == null || redEnvelope.getOrg() == null
					|| redEnvelope.getOrg().getId() == null) {
				errorMessage = "获取账号管理列表出错,请刷新后重试!";
				return ERROR;
			}
			//设置部门id
			TencentUser tencentUser = new TencentUser();
			tencentUser.setOrganization(organizationService.getFullOrgById(redEnvelope.getOrg()
					.getId()));
			//获取账号管理列表
			PageInfo<TencentUser> tencentUserList = tencentUserService.getTencentUserList(
					tencentUser, page, rows, sidx, sord);
			gridPage = new GridPage(tencentUserList);
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = "获取账号管理列表错误";
			logger.error("类RedEnvelopeController的findTencentUserList方法出现异常，原因：", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 发送红包
	 */
	@Action(value = "sendRedEnvelope", results = {
			@Result(name = "success", type = "json", params = { "root", "return_msg",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "null", "ignoreHierarchy",
					"false" }) })
	@PermissionFilter(ename = "sendRedEnvelope")
	public String sendRedEnvelope() {
		if (redEnvelopeId == null || !StringUtil.isStringAvaliable(re_openid)
				|| !StringUtil.isStringAvaliable(weChatUserId)) {
			errorMessage = "发送红包出错,请刷新后重试!";
			return ERROR;
		}
		try {
			return_msg = weChatService.sendRedEnvelope(redEnvelopeId, re_openid, weChatUserId);
		} catch (ServiceException e) {
			return ERROR;
		} catch (Exception e) {
			logger.error("类RedEnvelopeController的sendRedEnvelope方法出现异常，原因：", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 删除红包配置信息
	 */
	@Action(value = "deleteRedEnvelopeConfigurationById", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	@PermissionFilter(ename = "deleteRedEnvelope")
	public String deleteRedEnvelopeConfigurationById() {
		try {
			if (!StringUtil.isStringAvaliable(redEnvelopeIds)) {
				errorMessage = "请选择要删除的数据！";
				return ERROR;
			}
			Long[] ids = analyze(redEnvelopeIds);
			redEnvelopeService.deleteRedEnvelopeById(ids);
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("类RedEnvelopeController的deleteRedEnvelopeConfigurationById方法出现异常，原因：", e);
			this.errorMessage = "删除红包配置信息出错";
			return ERROR;
		}
		return SUCCESS;
	}

	public RedEnvelope getRedEnvelope() {
		return redEnvelope;
	}

	public void setRedEnvelope(RedEnvelope redEnvelope) {
		this.redEnvelope = redEnvelope;
	}

	public RedEnvelopeVo getRedEnvelopeVo() {
		return redEnvelopeVo;
	}

	public void setRedEnvelopeVo(RedEnvelopeVo redEnvelopeVo) {
		this.redEnvelopeVo = redEnvelopeVo;
	}

	public Long getRedEnvelopeId() {
		return redEnvelopeId;
	}

	public void setRedEnvelopeId(Long redEnvelopeId) {
		this.redEnvelopeId = redEnvelopeId;
	}

	public String getWeChatUserId() {
		return weChatUserId;
	}

	public void setWeChatUserId(String weChatUserId) {
		this.weChatUserId = weChatUserId;
	}

	public TencentUser getTencentUser() {
		return tencentUser;
	}

	public void setTencentUser(TencentUser tencentUser) {
		this.tencentUser = tencentUser;
	}

	public String getRe_openid() {
		return re_openid;
	}

	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public Fan getFan() {
		return fan;
	}

	public void setFan(Fan fan) {
		this.fan = fan;
	}

	public String getRedEnvelopeIds() {
		return redEnvelopeIds;
	}

	public void setRedEnvelopeIds(String redEnvelopeIds) {
		this.redEnvelopeIds = redEnvelopeIds;
	}

}
