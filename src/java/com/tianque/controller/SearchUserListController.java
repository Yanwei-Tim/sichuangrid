package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.plugin.dataManage.util.Constants;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.SearchUserDubboService;

@SuppressWarnings("serial")
@Controller("searchUserListController")
@Scope("prototype")
@Transactional
public class SearchUserListController extends BaseAction {
	// public final static Logger logger =
	// LoggerFactory.getLogger(SearchUserListController.class);
	@Autowired
	private SearchUserDubboService searchUserDubboService;

	private List<Organization> organizations;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private User user;
	private User operateUser;
	private Long organizationId;
	private String searchLockStatus;

	private Long[] roleIds;

	public String prepareZoneSelection() {
		return SUCCESS;
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "searchUserListData", actionName = "searchUserList"),
			@PermissionFilter(ename = "searchUser", actionName = "searchUserList") })
	public String searchUserList() {
		// 判断部门ID
		if (organizationId == null) {
			gridPage = new GridPage(new PageInfo<User>());
			return SUCCESS;
		}
		// 查询组织
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		PageInfo<User> pageInfo = new PageInfo<User>();
		if ("all".equals(searchLockStatus)) {
			// 把查询的组织机构放到user当中
			user.setOrganization(organization);
			pageInfo = searchUserDubboService.findUserListDatasBylockStatus(
					user, getRoleIdList(), page, rows, null, null);
		} else {
			if ("true".equals(searchLockStatus))
				user.setLock(true);
			else if ("false".equals(searchLockStatus)) {
				user.setLock(false);
			} else if (Constants.UserState.WITHACTIVATION_STRING
					.equals(searchLockStatus)) {
				user.setState(Constants.UserState.WITHACTIVATION_STATE);
			}
			// 根据当前组织ID查询。 organization.getOrgInternalCode()获得组织ID
			pageInfo = searchUserDubboService.findUserListDatas(
					organization.getOrgInternalCode(), user, getRoleIdList(),
					page, rows, null, null);
		}
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public List<Long> getRoleIdList() {
		List<Long> result = new ArrayList<Long>();
		if (roleIds != null && roleIds.length > 0) {
			for (int i = 0; i < roleIds.length; i++) {
				result.add(new Long(roleIds[i]));
			}
		}
		return result;
	}

	// 用户列表
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public User getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(User operateUser) {
		this.operateUser = operateUser;
	}

	public String getSearchLockStatus() {
		return searchLockStatus;
	}

	public void setSearchLockStatus(String searchLockStatus) {
		this.searchLockStatus = searchLockStatus;
	}

}
