package com.tianque.plugin.weChat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WeChatType;
import com.tianque.plugin.weChat.domain.sendMessage.Article;
import com.tianque.plugin.weChat.domain.sendMessage.TextMessage;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.domain.user.WeChatSource;
import com.tianque.plugin.weChat.service.FanService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.WeChatGroupService;
import com.tianque.plugin.weChat.service.WeChatSourceService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Controller("tencentUserController")
@Scope("prototype")
@Transactional
@Namespace("/tencentUserManage")
public class TencentUserController extends BaseAction {
	private static Logger logger = LoggerFactory.getLogger(TencentUserController.class);

	@Autowired
	private TencentUserService tencentUserService;
	@Autowired
	private OrganizationDubboService organizationService;
	@Autowired
	private FanService fanService;
	@Autowired
	private WeChatGroupService weChatGroupService;
	@Autowired
	private WeChatSourceService weChatSourceService;
	// 获取字典条目
	@Autowired
	private PropertyDictDubboService propertyDictService;

	private TencentUser tencentUser;
	private boolean flag;
	private String idsStr;
	//判断是发送还是回复
	private String sendOrReply;
	/**发送的返回情况（null为成功，!null未成功）*/
	private String sendResult;
	/**主动发送消息对象*/
	/** 文本*/
	private TextMessage textMessage;
	//图文
	private Article article;

	/** 附件名称 */
	private Set<String> attachFiles;
	/** 粉丝id(用于群发) */
	private String openId;
	private List<WeChatSource> listWeChatSources;

	//素材类型
	private long sourceType;

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	@Action(value = "getTencentUserList", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false", "excludeNullProperties", "true" }) })
	public String getTencentUserList() {
		try {
			tencentUser = tencentUser == null ? new TencentUser() : tencentUser;
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(tencentUserService
					.getTencentUserList(tencentUser, page, rows, sidx, sord), organizationService,
					new String[] { "organization" }, tencentUser.getOrganization().getId()));
			List<TencentUser> list = new ArrayList<TencentUser>(gridPage.getRows());
			User user = ThreadVariable.getUser();
			for (TencentUser tencentUser : list) {
				if (user.getOrganization().getId().toString()
						.equals(tencentUser.getOrganization().getId().toString())) {
					tencentUser.setState(1L);
				}
			}
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("账号管理列表错误", e);
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/template/tencentUser/tencentUserDlg.ftl"),
			@Result(name = "sendTextMessage", location = "/wechat/sendTextMessageDlg.jsp"),
			@Result(name = "sendImageMessage", location = "/wechat/sendImageMessageDlg.jsp"),
			@Result(name = "sendNewsMessage", location = "/wechat/sendNewsMessageDlg.jsp"),
			@Result(name = "sendVoiceMessage", location = "/wechat/sendVoiceMessageDlg.jsp"),
			@Result(name = "search", location = "/template/tencentUser/searchTencentUser.ftl"),
			@Result(name = "autoMessage", location = "/wechat/autoMessage.jsp"),
			@Result(name = "autoNewsMessage", location = "/wechat/autoNewsMessage.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String dispatch() {
		try {
			if (DialogMode.ADD_MODE.equals(mode)) {
				this.procOrganization();
				return SUCCESS;
			} else if ("sendTextMessage".equals(mode)) {//文本
				tencentUser = tencentUserService.getTencentUserByTencentUserId(tencentUser
						.getTencentUserId());
				sendOrReply = "send";
				return "sendTextMessage";
			} else if ("sendImageMessage".equals(mode)) {//图片
				tencentUser = tencentUserService.getTencentUserByTencentUserId(tencentUser
						.getTencentUserId());
				sendOrReply = "send";
				return "sendImageMessage";
			} else if ("sendNewsMessage".equals(mode)) {//图文
				tencentUser = tencentUserService.getTencentUserByTencentUserId(tencentUser
						.getTencentUserId());
				sendOrReply = "send";
				return "sendNewsMessage";
			} else if ("sendVoiceMessage".equals(mode)) {//语音
				tencentUser = tencentUserService.getTencentUserByTencentUserId(tencentUser
						.getTencentUserId());
				sendOrReply = "send";
				return "sendVoiceMessage";
			} else if (DialogMode.EDIT_MODE.equals(mode)) {
				this.getTencentUserInfo();
				return SUCCESS;
			} else if ("autoMessage".equals(mode)) {
				tencentUser = tencentUserService.getTencentUserByTencentUserId(tencentUser
						.getTencentUserId());
				Organization organization = new Organization();
				organization.setId(tencentUser.getOrganization().getId());
				organization.setOrgName(ControllerHelper.getOrganizationRelativeName(
						organization.getId(), organizationService));
				tencentUser.setOrganization(organization);
				listWeChatSources = weChatSourceService.getWeChatSourceByOrgIdAndSourceType(
						tencentUser.getOrganization().getId(),
						getWeChatSourceType(WeChatType.MAP.get((int) sourceType)).getId());
				tencentUser.setSourceTypeDict(getWeChatSourceType(WeChatType.MAP
						.get((int) sourceType)));
				if ("图文".equals(WeChatType.MAP.get((int) sourceType))) {
					if (tencentUser.getSourceId() != null && !"".equals(tencentUser.getSourceId())) {
						if (tencentUser.getSourceId().indexOf(",") == -1) {
							tencentUser.setSourceId("");
							tencentUser.setSourceDescription("");
						}
						for (int j = 0; j < listWeChatSources.size(); j++) {
							if (tencentUser.getSourceId().indexOf(
									listWeChatSources.get(j).getId().toString()) > -1) {
								listWeChatSources.remove(j);
								j--;
							}
						}
					}
					return "autoNewsMessage";
				} else
					return "autoMessage";
			}

			else if (DialogMode.SEARCH_MODE.equals(mode)) {
				return "search";
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("账号管理跳转页面错误", e);
			return ERROR;
		}
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action(value = "addTencentUser", results = {
			@Result(name = "success", type = "json", params = { "root", "id", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String addTencentUser() {
		try {
			tencentUserService.addTencentUser(tencentUser);
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("保存微信账号错误", errorMessage);
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			this.errorMessage = "保存微信账号失败!";
			logger.error("保存微信账号错误!", e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 微信号绑定自动回复消息（素材）
	 * @return
	 */
	@Action(value = "updateTencentUser", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String updateTencentUser() {
		try {
			if (tencentUser.getSourceId() == null || "-1".equals(tencentUser.getSourceId())
					|| "".equals(tencentUser.getSourceId())) {
				tencentUser.setSourceId(null);
				tencentUser.setSourceDescription("");
				tencentUser.getSourceTypeDict().setId(null);
				tencentUser.setIsAppendKeyWord(null);
			}
			if (tencentUser.getSourceTypeDict().getId() != null) {
				PropertyDict propertyDict = propertyDictService.getPropertyDictById(tencentUser
						.getSourceTypeDict().getId());
				if (propertyDict.getDisplayName().equals("图片")
						|| propertyDict.getDisplayName().equals("语音"))
					tencentUser.setIsAppendKeyWord(2L);
			}
			int flag = tencentUserService.updateTencentUser(tencentUser);
			if (flag > 0) {
				return SUCCESS;
			} else {
				this.errorMessage = "自动回复消息绑定失败!";
				logger.error("自动回复消息绑定失败!");
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("自动回复消息绑定失败：", errorMessage);
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			this.errorMessage = "自动回复消息绑定失败!";
			logger.error("自动回复消息绑定失败!", e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "deleteTencentUser")
	@Action(value = "deleteTencentUser", results = { @Result(name = "success", type = "json", params = {
			"root", "flag" }) })
	public String deleteTencentUser() {
		try {
			List<Long> ids = new ArrayList<Long>();
			String[] idsArr = idsStr.split(",");
			for (int i = 0; i < idsArr.length; i++) {
				ids.add(Long.parseLong(idsArr[i]));
			}
			tencentUserService.deleteTencentUser(ids);
			flag = true;
		} catch (Exception e) {
			flag = false;
			this.errorMessage = e.getMessage();
			logger.error("删除微信账号错误", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**发送文本（前提是24小时与用户有交互）*/
	@Action(value = "sendTextMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "sendResult" }),
			@Result(type = "json", name = "error", params = { "root", "errorMessage" }) })
	public String sendTextMessage() {
		try {
			sendResult = tencentUserService.sendTextMessage(textMessage, tencentUser, openId);
			return SUCCESS;
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/**发送图片（前提是24小时与用户有交互）*/
	@Action(value = "sendImageMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "sendResult" }),
			@Result(type = "json", name = "error", params = { "root", "errorMessage" }) })
	public String sendImageMessage() {
		try {
			sendResult = tencentUserService.sendImageMessage(textMessage, tencentUser, attachFiles,
					openId);
			return SUCCESS;
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/**发送图文（前提是24小时与用户有交互）*/
	@Action(value = "sendNewsMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "sendResult" }),
			@Result(type = "json", name = "error", params = { "root", "errorMessage" }) })
	public String sendNewsMessage() {
		try {
			sendResult = tencentUserService.sendNewsMessage(textMessage, tencentUser, attachFiles,
					article, openId);
			return SUCCESS;
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/**发送语音（前提是24小时与用户有交互）*/
	@Action(value = "sendVoiceMessage", results = {
			@Result(type = "json", name = "success", params = { "root", "sendResult" }),
			@Result(type = "json", name = "error", params = { "root", "errorMessage" }) })
	public String sendVoiceMessage() {
		try {
			sendResult = tencentUserService.sendVoiceMessage(textMessage, tencentUser, attachFiles,
					openId);
			return SUCCESS;
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/**
	 * 验证当前层级下有没有微信号或是素材
	 * @return
	 */
	@Action(value = "validataSource", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String validataSource() {
		listWeChatSources = weChatSourceService.getWeChatSourceByOrgIdAndSourceType(tencentUser
				.getOrganization().getId(),
				getWeChatSourceType(WeChatType.MAP.get((int) sourceType)).getId());
		if (listWeChatSources.size() == 0) {
			this.errorMessage = "当前层级下还未添加" + WeChatType.MAP.get((int) sourceType) + "素材！";
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 验证该服务号是不是已经存在
	 * @return
	 */
	@Action(value = "validateWeChatUserId", results = { @Result(name = "success", type = "json", params = {
			"root", "tencentUser", "ignoreHierarchy", "false" }) })
	public String validateWeChatUserId() {
		try {
			tencentUser = tencentUserService.getTencentUserByWeChatUserId(tencentUser
					.getWeChatUserId());
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("微信账号重复性验证错误", e);
			return ERROR;
		}
		return SUCCESS;
	}

	private void procOrganization() {
		Organization organization = new Organization();
		organization.setId(tencentUser.getOrganization().getId());
		organization.setOrgName(ControllerHelper.getOrganizationRelativeName(organization.getId(),
				organizationService));
		tencentUser = new TencentUser();
		tencentUser.setOrganization(organization);
	}

	private void getTencentUserInfo() {
		tencentUser = this.tencentUserService.getTencentUserByTencentUserId(tencentUser
				.getTencentUserId());
		tencentUser.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(tencentUser.getOrganization().getId(),
						organizationService));
	}

	/**
	 * 根据名称获取素材类型对应实体
	 */
	private PropertyDict getWeChatSourceType(String name_CN) {
		PropertyDict propertyDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.WECHAT_TYPE, name_CN);
		if (propertyDict == null)
			return null;
		else
			return propertyDict;
	}

	public TencentUser getTencentUser() {
		return tencentUser;
	}

	public void setTencentUser(TencentUser tencentUser) {
		this.tencentUser = tencentUser;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getIdsStr() {
		return idsStr;
	}

	public void setIdsStr(String idsStr) {
		this.idsStr = idsStr;
	}

	public String getSendOrReply() {
		return sendOrReply;
	}

	public void setSendOrReply(String sendOrReply) {
		this.sendOrReply = sendOrReply;
	}

	public String getSendResult() {
		return sendResult;
	}

	public void setSendResult(String sendResult) {
		this.sendResult = sendResult;
	}

	public TextMessage getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(TextMessage textMessage) {
		this.textMessage = textMessage;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Set<String> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(Set<String> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public List<WeChatSource> getListWeChatSources() {
		return listWeChatSources;
	}

	public void setListWeChatSources(List<WeChatSource> listWeChatSources) {
		this.listWeChatSources = listWeChatSources;
	}

	public long getSourceType() {
		return sourceType;
	}

	public void setSourceType(long sourceType) {
		this.sourceType = sourceType;
	}
}
