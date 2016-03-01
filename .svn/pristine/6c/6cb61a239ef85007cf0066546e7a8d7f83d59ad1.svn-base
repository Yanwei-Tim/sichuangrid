package com.tianque.init.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.Chinese2pinyin;
import com.tianque.domain.Organization;
import com.tianque.domain.Permission;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Role;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkBenchType;
import com.tianque.init.Initialization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

public class UserRoleInitialization implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private PermissionService permissionService;
	private PropertyDictService propertyDictService;
	private OrganizationDubboService organizationDubboService;

	public UserRoleInitialization(PermissionService permissionService,
			OrganizationDubboService organizationDubboService,
			PropertyDictService propertyDictService) {
		this.permissionService = permissionService;
		this.organizationDubboService = organizationDubboService;
		this.propertyDictService = propertyDictService;
	}

	@Override
	public void init() {
		addRole(getAllPermissionEname());
		addUser();
		logger.info("默认用户创建完成!");
	}

	private String[] getAllPermissionEname() {
		List<Permission> permissions = permissionService
				.findAllPermissionsForPage(1, Integer.MAX_VALUE).getResult();
		String[] result = new String[permissions.size()];
		for (int i = 0; i < permissions.size(); i++) {
			Permission permission = permissions.get(i);
			result[i] = permission.getEname();
		}
		return result;
	}

	//
	private Role addRole(String[] permissions) {
		Role role = new Role();
		role.setRoleName("admin");
		role.setDescription("系统管理员");
		role.setCreateDate(Calendar.getInstance().getTime());
		List<PropertyDict> roles = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.WORKBENCH_TYPE, WorkBenchType.SUPER_LEVEL);
		if (roles != null && roles.size() > 0) {
			role.setWorkBenchType(roles.get(0));
		}
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationLevel.ORG_LEVEL_KEY, OrganizationLevel.CITY);
		if (orgLevels != null && orgLevels.size() > 0) {
			role.setUseInLevel(orgLevels.get(0));
		}
		role.setCreateUser("admin");
		permissionService.addRole(role, permissions);
		return role;
	}

	private Organization getRootOrganization() {
		return organizationDubboService.findOrganizationsByParentId(null)
				.get(0);
	}

	private User addUser() {

		User user = new User();
		user.setAdmin(true);
		user.setLock(false);
		user.setCreateDate(Calendar.getInstance().getTime());
		user.setCreateUser("admin");
		user.setUserName("admin");
		user.setName("超级管理员");
		user.setHasNewMessage(false);
		user.setMobile("13988888888");
		user.setWorkPhone("0571-88102930");

		user.setCredits1(0L);
		user.setCredits2(0L);
		user.setCreateUser("admin");
		user.setCreateDate(Calendar.getInstance().getTime());
		Organization rootOrg = getRootOrganization();
		user.setOrganization(rootOrg);
		user.setOrgInternalCode(rootOrg.getOrgInternalCode());
		Map<String, String> map = Chinese2pinyin.changeChinese2Pinyin(user
				.getName());
		user.setFullPinyin(map.get("fullPinyin"));
		user.setSimplePinyin(map.get("simplePinyin"));

		user.setChangePassword(false);
		user.setPassword("admin");
		user.setPcusable(true);
		user.setMobileusable(true);
		user.setState(2L);
		user.setActivationTime(Calendar.getInstance().getTime());

		User exitsUser = permissionService.findUserByUserName(user
				.getUserName());

		if (exitsUser == null) {
			exitsUser = permissionService.addUser(user);
			// permissionService.addUserRoleRela(exitsUser.getId(),
			// role.getId());
		}
		return exitsUser;
	}

}
