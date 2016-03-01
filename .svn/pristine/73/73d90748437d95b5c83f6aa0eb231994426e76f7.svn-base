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

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.user.Fan;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.domain.user.WeChatGroup;
import com.tianque.plugin.weChat.service.FanService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.WeChatGroupService;
import com.tianque.plugin.weChat.util.DateUtil;

/**
 * 微信粉丝
 * @author Administrator
 *
 */
@Namespace("/fanManage")
@Scope("prototype")
@Controller("fanController")
public class FanController extends BaseAction {
	private static Logger logger = LoggerFactory.getLogger(FanController.class);
	@Autowired
	private FanService fanService;
	@Autowired
	private TencentUserService tencentUserService;
	@Autowired
	private WeChatGroupService weChatGroupService;
	private Fan fan;
	private List<TencentUser> listTencentUser;
	private List<WeChatGroup> listWeChatGroup;
	private String fanIds;
	private List<Fan> fanLists;
	private TencentUser tencentUser;
	private List<WeChatGroup> weChatGroupList;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/wechat/fan/fanDlg.jsp"),
			@Result(name = "removeFan", location = "/wechat/fan/removeFanDlg.jsp"),
			@Result(name = "search", location = "/wechat/fan/searchFan.jsp"),
			@Result(name = "selectFanOrGroup", location = "/wechat/selectPersonDlg.jsp"),
			@Result(name = "fanManagement", location = "/wechat/fan/maintainFanManageDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String dispatch() {
		try {
			if ("removeFan".equals(mode)) {
				listWeChatGroup = weChatGroupService
						.getGroupListWeChatUserId(fan.getWeChatUserId());
				return "removeFan";
			} else if (DialogMode.EDIT_MODE.equals(mode)) {
				fan = fanService.getFanById(fan);
				return SUCCESS;
			} else if (DialogMode.SEARCH_MODE.equals(mode)) {
				listTencentUser = tencentUserService
						.getTencentUserListByOrgId(fan.getOrg().getId());
				return "search";
			} else if ("selectFanOrGroup".equals(mode)) {
				if (null == tencentUser.getWeChatUserId()
						|| "".equals(tencentUser.getWeChatUserId())) {
					this.errorMessage = "微信服务号不能为空！";
					return ERROR;
				} else {
					weChatGroupList = weChatGroupService.getGroupListWeChatUserId(tencentUser
							.getWeChatUserId());
					return "selectFanOrGroup";
				}
			} else if ("fanManagement".equals(mode)) {
				if (fan.getFanId() == null || !StringUtil.isStringAvaliable(fan.getWeChatUserId())) {
					this.errorMessage = "微信服务号或者粉丝不能为空！";
					return ERROR;
				}
				fan = fanService.getFanById(fan);
				listWeChatGroup = weChatGroupService
						.getGroupListWeChatUserId(fan.getWeChatUserId());
				return "fanManagement";
			} else {
				return ERROR;
			}
		} catch (Exception e) {

			this.errorMessage = e.getMessage();
			logger.error("微信粉丝跳转页面错误", e);
			return ERROR;
		}
	}

	/**
	 * 菜单列表
	 * @return
	 */
	@Action(value = "findFan", results = {
			@Result(type = "json", name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findFan() {
		try {
			if (fan.getWeChatUserId() != null && !"".equals(fan.getWeChatUserId()))
				fan.setWeChatUserId("'" + fan.getWeChatUserId() + "'");
			else {
				String weChatUserIdTemp = "";
				List<TencentUser> tencentUserList = tencentUserService
						.getTencentUserListByOrgId(fan.getOrg().getId());
				if (tencentUserList.size() > 0) {
					for (int i = 0; i < tencentUserList.size(); i++) {
						if (i == tencentUserList.size() - 1){
							weChatUserIdTemp += "'" + tencentUserList.get(i).getWeChatUserId()
									+ "'";
						}else
							weChatUserIdTemp += "'" + tencentUserList.get(i).getWeChatUserId()
									+ "'" + ",";
					}
				} else {
					weChatUserIdTemp = "'no'";
				}
				fan.setWeChatUserId(weChatUserIdTemp);
			}
			PageInfo<Fan> weChatGroupList = fanService.findFan(fan, page, rows, sidx, sord);
			gridPage = new GridPage(weChatGroupList);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("findWeChatGroupERROR", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/**
	 * 修改分组
	 * @return
	 */
	@PermissionFilter(ename = "updateFan")
	@Action(value = "updateFan", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String updateFan() {
		try {
			int flag = fanService.updateFanById(fan);
			if (flag > 0)
				return SUCCESS;
			else {
				this.errorMessage = "微信粉丝昵称修改失败！";
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("微信粉丝昵称修改失败：" + s.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("微信粉丝昵称修改失败：" + e.getMessage());
			return ERROR;
		}

	}

	/**
	 * 移动粉丝
	 * @return
	 */
	@PermissionFilter(ename = "removeFan")
	@Action(value = "removeFan", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String removeFan() {
		try {
			int flag = fanService.removeFan(fan, fanIds);
			if (flag > 0)
				return SUCCESS;
			else {
				this.errorMessage = "微信粉丝移动分组失败！";
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("微信粉丝移动分组失败：" + s.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("微信粉丝移动分组失败：" + e.getMessage());
			return ERROR;
		}

	}

	@Action(value = "getweChatGroupByWeChatUserId", results = {
			@Result(type = "json", name = "success", params = { "root", "listWeChatGroup" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getweChatGroupByWeChatUserId() {
		try {
			listWeChatGroup = weChatGroupService.getGroupListWeChatUserId(fan.getWeChatUserId());
			//listWeChatGroup =new ArrayList<WeChatGroup>();
			return SUCCESS;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("微信获取分组失败：" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 获取粉丝列表(48小时内有互动的)
	 * @return
	 */
	@Action(value = "getFanList", results = {
			@Result(name = "success", type = "json", params = { "root", "fanLists" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getFanList() {
		if (tencentUser.getWeChatUserId() == null || "".equals(tencentUser.getWeChatUserId())) {
			this.errorMessage = "微信服务号不能为空";
			return ERROR;
		} else {
			fanLists = fanService.getFanListByGroupIdAndWeChatUserIdAndBeforDate(fan.getGroupId(),
					tencentUser.getWeChatUserId(), DateUtil.beforeNowDate(2));
			return SUCCESS;
		}

	}

	/**
	 * 获取粉丝列表(不限制48小时内有互动的，全部开放)
	 */
	@Action(value = "getAllFanList", results = {
			@Result(name = "success", type = "json", params = { "root", "fanLists" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getAllFanList() {
		if (tencentUser.getWeChatUserId() == null || "".equals(tencentUser.getWeChatUserId())) {
			this.errorMessage = "微信服务号不能为空";
			return ERROR;
		} else {
			fanLists = fanService.getFanListByGroupIdAndWeChatUserId(fan.getGroupId(),
					tencentUser.getWeChatUserId());
			return SUCCESS;
		}

	}

	/**
	 * 消息管理--粉丝管理操作
	 */
	@PermissionFilter(ename = "fanManagementByInbox")
	@Action(value = "updateAndRemoveFan", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String updateAndRemoveFan() {
		try {
			int updateFlag = fanService.updateFanById(fan);
			int removeFlag = fanService.removeFan(fan, fanIds);
			if (updateFlag > 0 && removeFlag > 0)
				return SUCCESS;
			else {
				this.errorMessage = "微信粉丝修改粉丝昵称和移动分组失败！";
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("微信粉丝修改粉丝昵称和移动分组失败：" + s.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("微信粉丝修改粉丝昵称和移动分组失败：" + e.getMessage());
			return ERROR;
		}
	}

	/**
	 * 查询粉丝对象
	 */
	@Action(value = "getFanObject", results = {
			@Result(type = "json", name = "success", params = { "root", "fan" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getFanObject() {
		if (fan == null || fan.getFanId() == null) {
			errorMessage = "获取粉丝失败，请刷新重试";
			return ERROR;
		}
		try {
			fan = fanService.getFanById(fan);
		} catch (Exception e) {
			logger.error("获取粉丝失败：" + e.getMessage());
			errorMessage = "获取粉丝失败，请刷新重试";
			return ERROR;
		}
		return SUCCESS;

	}

	public List<WeChatGroup> getWeChatGroupList() {
		return weChatGroupList;
	}

	public void setWeChatGroupList(List<WeChatGroup> weChatGroupList) {
		this.weChatGroupList = weChatGroupList;
	}

	public List<Fan> getFanLists() {
		return fanLists;
	}

	public void setFanLists(List<Fan> fanLists) {
		this.fanLists = fanLists;
	}

	public TencentUser getTencentUser() {
		return tencentUser;
	}

	public void setTencentUser(TencentUser tencentUser) {
		this.tencentUser = tencentUser;
	}

	public Fan getFan() {
		return fan;
	}

	public void setFan(Fan fan) {
		this.fan = fan;
	}

	public List<TencentUser> getListTencentUser() {
		return listTencentUser;
	}

	public void setListTencentUser(List<TencentUser> listTencentUser) {
		this.listTencentUser = listTencentUser;
	}

	public List<WeChatGroup> getListWeChatGroup() {
		return listWeChatGroup;
	}

	public void setListWeChatGroup(List<WeChatGroup> listWeChatGroup) {
		this.listWeChatGroup = listWeChatGroup;
	}

	public String getFanIds() {
		return fanIds;
	}

	public void setFanIds(String fanIds) {
		this.fanIds = fanIds;
	}
}
