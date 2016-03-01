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
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.domain.user.WeChatGroup;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.plugin.weChat.service.WeChatGroupService;

/**
 * 微信分组列表
 * 
 * @date 2014年5月13日
 */
@Namespace("/weChatGroupManage")
@Scope("prototype")
@Controller("weChatGroupController")
public class WeChatGroupController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(WeChatGroupController.class);
	@Autowired
	private WeChatGroupService weChatGroupService;
	@Autowired
	private TencentUserService tencentUserService;

	private WeChatGroup weChatGroup;
	private List<TencentUser> listTencentUser;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/wechat/group/weChatGroupDlg.jsp"),
			@Result(name = "search", location = "/wechat/group/searchWeChatGroup.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() {
		try {
			if (DialogMode.ADD_MODE.equals(mode)) {
				listTencentUser = tencentUserService
						.getTencentUserListByOrgId(weChatGroup.getOrg().getId());
				return SUCCESS;
			} else if (DialogMode.EDIT_MODE.equals(mode)) {
				weChatGroup = weChatGroupService
						.getWeChatGroupById(weChatGroup);
				return SUCCESS;
			} else if (DialogMode.SEARCH_MODE.equals(mode)) {
				listTencentUser = tencentUserService
						.getTencentUserListByOrgId(weChatGroup.getOrg().getId());
				return "search";
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("微信分组跳转页面错误", e);
			return ERROR;
		}
	}

	/**
	 * 分组列表
	 * 
	 * @return
	 */
	@Action(value = "findWeChatGroup", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findWeChatGroup() {
		try {
			if (weChatGroup.getWeChatUserId() != null
					&& !"".equals(weChatGroup.getWeChatUserId()))
				weChatGroup.setWeChatUserId("'" + weChatGroup.getWeChatUserId()
						+ "'");
			else {
				String weChatUserIdTemp = "";
				List<TencentUser> tencentUserList = tencentUserService
						.getTencentUserListByOrgId(weChatGroup.getOrg().getId());
				if (tencentUserList.size() > 0) {
					for (int i = 0; i < tencentUserList.size(); i++) {
						if (i == tencentUserList.size() - 1)
							weChatUserIdTemp += "'"
									+ tencentUserList.get(i).getWeChatUserId()
									+ "'";
						else
							weChatUserIdTemp += "'"
									+ tencentUserList.get(i).getWeChatUserId()
									+ "'" + ",";
					}
				} else {
					weChatUserIdTemp = "'no'";
				}
				weChatGroup.setWeChatUserId(weChatUserIdTemp);
			}
			PageInfo<WeChatGroup> weChatGroupList = weChatGroupService
					.findWeChatGroup(weChatGroup, page, rows, sidx, sord);
			gridPage = new GridPage(weChatGroupList);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("findWeChatGroupERROR", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	/**
	 * 新增分组
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "addWeChatGroup")
	@Action(value = "addWeChatGroup", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addWeChatGroup() {
		try {
			if (weChatGroup != null && weChatGroup.getWeChatUserId() != null
					&& weChatGroup.getName() != null) {

				WeChatGroup oldWeChatGroup = weChatGroupService
						.getGroupListByNameAndWeChatUserId(
								weChatGroup.getWeChatUserId(),
								weChatGroup.getName());
				if (oldWeChatGroup != null) {
					this.errorMessage = "分组名称重复,微信分组创建失败!";
					return ERROR;
				}
			}
			Long flag = weChatGroupService.addWeChatGroup(weChatGroup);
			if (flag > 0)
				return SUCCESS;
			else {
				this.errorMessage = "微信分组创建失败！";
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("微信分组创建失败：" + s.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("微信分组创建失败：" + e.getMessage());
			return ERROR;
		}

	}

	/**
	 * 修改分组
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "updateWeChatGroup")
	@Action(value = "updateWeChatGroup", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateWeChatGroup() {
		try {
			int flag = weChatGroupService.updateWeChatGroup(weChatGroup);
			if (flag > 0)
				return SUCCESS;
			else {
				this.errorMessage = "微信分组修改失败！";
				return ERROR;
			}
		} catch (ServiceException s) {
			this.errorMessage = s.getMessage();
			logger.error("微信分组修改失败：" + s.getMessage());
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("微信分组修改失败：" + e.getMessage());
			return ERROR;
		}

	}

	/**
	 * 验证当前层级下有没有微信号或是素材
	 * 
	 * @return
	 */
	@Action(value = "validataWeChatUserId", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String validataWeChatUserId() {
		listTencentUser = tencentUserService
				.getTencentUserListByOrgId(weChatGroup.getOrg().getId());
		if (listTencentUser.size() == 0) {
			this.errorMessage = "当前层级下还未添加微信公共账号！";
			return ERROR;
		}
		return SUCCESS;
	}

	public WeChatGroup getWeChatGroup() {
		return weChatGroup;
	}

	public void setWeChatGroup(WeChatGroup weChatGroup) {
		this.weChatGroup = weChatGroup;
	}

	public List<TencentUser> getListTencentUser() {
		return listTencentUser;
	}

	public void setListTencentUser(List<TencentUser> listTencentUser) {
		this.listTencentUser = listTencentUser;
	}
}
