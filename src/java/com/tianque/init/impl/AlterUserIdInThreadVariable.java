package com.tianque.init.impl;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.User;
import com.tianque.init.Initialization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

public class AlterUserIdInThreadVariable implements Initialization {
	PermissionService permissionService;
	OrganizationDubboService organizationDubboService;

	public AlterUserIdInThreadVariable(PermissionService _permissionService,
			OrganizationDubboService _organizationDubboService) {
		permissionService = _permissionService;
		organizationDubboService = _organizationDubboService;
	}

	@Override
	public void init() throws Exception {
		User user = permissionService.findUserByUserName(ThreadVariable
				.getSession().getUserName());
		ThreadVariable.getSession().setUserId(user.getId());
		ThreadVariable.getSession().setOrganization(
				organizationDubboService.getSimpleOrgById(user
						.getOrganization().getId()));
	}

}
