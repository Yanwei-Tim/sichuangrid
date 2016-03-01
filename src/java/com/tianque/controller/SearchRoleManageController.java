package com.tianque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Role;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.userAuth.api.SearchRoleDubboService;

@Controller("searchRoleManageController")
@Scope("prototype")
@Transactional(readOnly = true)
public class SearchRoleManageController extends BaseAction {
	private Role role;
	@Autowired
	private SearchRoleDubboService searchRoleDubboService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private OrganizationDubboService OrganizationDubboService;

	@PermissionFilter(ename = "searchRoles")
	public String searchRoles() {
		if (role.getUseInLevel().getId() == null) {
			Session session = ThreadVariable.getSession();
			User user = permissionService
					.getSimpleUserById(session.getUserId());
			Organization org = OrganizationDubboService.getFullOrgById(user
					.getOrganization().getId());
			role.setUseInLevel(org.getOrgLevel());
			PageInfo<Role> pageInfo = searchRoleDubboService.searchRolesLike(
					role, page, rows, sidx, sord);
			gridPage = new GridPage(pageInfo);
		} else {
			PageInfo<Role> pageInfo = searchRoleDubboService.searchRoles(role,
					page, rows, sidx, sord);
			gridPage = new GridPage(pageInfo);
		}
		return SUCCESS;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
