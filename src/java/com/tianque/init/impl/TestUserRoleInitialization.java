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
import com.tianque.init.Initialization;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 给每个层级组织机构添加用户，方便测试
 */
public class TestUserRoleInitialization implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private PermissionService permissionService;
	private PropertyDictService propertyDictService;
	private OrganizationDubboService organizationDubboService;

	public TestUserRoleInitialization(PermissionService permissionService,
			OrganizationDubboService organizationDubboService,
			PropertyDictService propertyDictService) {
		this.permissionService = permissionService;
		this.organizationDubboService = organizationDubboService;
		this.propertyDictService = propertyDictService;
		logger.info("创建测试 UserRoleInitialization 对象");
	}

	@Override
	public void init() {
		Role cityRole = addCityRole(getAllPermissionEname());
		addTestUser(cityRole, getOrganizationById(1L), "city", "市综治管理员");

		Role districtRole = addDistrictRole(getAllPermissionEname());
		addTestUser(districtRole, getOrganizationById(2L), "district1",
				"一区综治管理员");
		addTestUser(districtRole, getOrganizationById(3L), "district2",
				"二区综治管理员");
		addTestUser(districtRole, getOrganizationById(4L), "district3",
				"三县综治管理员");
		addTestUser(districtRole, getOrganizationById(5L), "district4",
				"四市综治管理员");

		Role townRole = addTwonRole(getAllPermissionEname());
		addTestUser(townRole, getOrganizationById(6L), "town1", "一镇综治管理员");
		addTestUser(townRole, getOrganizationById(9L), "town2", "二街道综治管理员");
		addTestUser(townRole, getOrganizationById(12L), "town3", "三县街道综治管理员");
		addTestUser(townRole, getOrganizationById(15L), "town4", "四市综治管理员");

		Role villageRole = addVillageRole(getAllPermissionEname());
		addTestUser(villageRole, getOrganizationById(7L), "village1", "一村综治管理员");
		addTestUser(villageRole, getOrganizationById(10L), "village2",
				"二社区综治管理员");
		addTestUser(villageRole, getOrganizationById(13L), "village3",
				"三村综治管理员");
		addTestUser(villageRole, getOrganizationById(16L), "village4",
				"四村综治管理员");

		Role gridRole = addGridRole(getAllPermissionEname());
		addTestUser(gridRole, getOrganizationById(8L), "grid1", "网格一信息员");
		addTestUser(gridRole, getOrganizationById(11L), "grid2", "网格二信息员");
		addTestUser(gridRole, getOrganizationById(14L), "grid3", "网格三信息员");
		addTestUser(gridRole, getOrganizationById(17L), "grid4", "网格四信息员");
		logger.info("默认测试用户创建完成!");
	}

	private String[] getAllPermissionEname() {
		List<Permission> permissions = permissionService
				.findAllPermissionsForPage(1, Integer.MAX_VALUE).getResult();
		String[] result = new String[permissions.size()];
		for (int i = 0; i < permissions.size(); i++) {
			Permission permission = permissions.get(i);
			result[i] = permission.getEname();
		}
		logger.info("getAllPermissionEname!");
		return result;
	}

	private Role addCityRole(String[] permissions) {
		logger.info("addRole,permissions.length:" + permissions.length);
		Role role = new Role();
		role.setRoleName("cityTest");
		role.setDescription("市综治管理员");
		role.setCreateDate(Calendar.getInstance().getTime());
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

	private Role addDistrictRole(String[] permissions) {
		logger.info("addRole,permissions.length:" + permissions.length);
		Role role = new Role();
		role.setRoleName("districtTest");
		role.setDescription("县综治管理员");
		role.setCreateDate(Calendar.getInstance().getTime());
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationLevel.ORG_LEVEL_KEY,
						OrganizationLevel.DISTRICT);
		if (orgLevels != null && orgLevels.size() > 0) {
			role.setUseInLevel(orgLevels.get(0));
		}
		role.setCreateUser("admin");
		permissionService.addRole(role, permissions);
		return role;
	}

	private Role addTwonRole(String[] permissions) {
		logger.info("addRole,permissions.length:" + permissions.length);
		Role role = new Role();
		role.setRoleName("twonTest");
		role.setDescription("镇综治管理员");
		role.setCreateDate(Calendar.getInstance().getTime());
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationLevel.ORG_LEVEL_KEY, OrganizationLevel.TOWN);
		if (orgLevels != null && orgLevels.size() > 0) {
			role.setUseInLevel(orgLevels.get(0));
		}
		role.setCreateUser("admin");
		permissionService.addRole(role, permissions);
		return role;
	}

	private Role addVillageRole(String[] permissions) {
		logger.info("addRole,permissions.length:" + permissions.length);
		Role role = new Role();
		role.setRoleName("villageTest");
		role.setDescription("村综治管理员");
		role.setCreateDate(Calendar.getInstance().getTime());
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationLevel.ORG_LEVEL_KEY,
						OrganizationLevel.VILLAGE);
		if (orgLevels != null && orgLevels.size() > 0) {
			role.setUseInLevel(orgLevels.get(0));
		}
		role.setCreateUser("admin");
		permissionService.addRole(role, permissions);
		return role;
	}

	private Role addGridRole(String[] permissions) {
		logger.info("addRole,permissions.length:" + permissions.length);
		Role role = new Role();
		role.setRoleName("gridTest");
		role.setDescription("网格信息员");
		role.setCreateDate(Calendar.getInstance().getTime());
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationLevel.ORG_LEVEL_KEY, OrganizationLevel.GRID);
		if (orgLevels != null && orgLevels.size() > 0) {
			role.setUseInLevel(orgLevels.get(0));
		}
		role.setCreateUser("admin");
		permissionService.addRole(role, permissions);
		return role;
	}

	private Organization getOrganizationById(Long id) {
		return organizationDubboService.getFullOrgById(id);
	}

	private User addTestUser(Role role, Organization org, String userName,
			String name) {

		User user = new User();
		user.setAdmin(false);
		user.setLock(false);
		user.setCreateDate(Calendar.getInstance().getTime());
		user.setCreateUser("admin");
		user.setUserName(userName);
		user.setName(name);
		user.setHasNewMessage(false);
		user.setMobile("13988888888");
		user.setWorkPhone("0571-88102930");

		user.setCredits1(0L);
		user.setCredits2(0L);
		user.setCreateUser("admin");
		user.setCreateDate(Calendar.getInstance().getTime());
		user.setOrganization(org);
		user.setOrgInternalCode(org.getOrgInternalCode());
		Map<String, String> map = Chinese2pinyin.changeChinese2Pinyin(user
				.getName());
		user.setFullPinyin(map.get("fullPinyin"));
		user.setSimplePinyin(map.get("simplePinyin"));

		user.setChangePassword(false);
		user.setPassword("111111");

		User exitsUser = permissionService.findUserByUserName(user
				.getUserName());

		if (exitsUser == null) {
			exitsUser = permissionService.addUser(user);
			permissionService.addUserRoleRela(exitsUser.getId(), role.getId());
		}
		return exitsUser;
	}

}
