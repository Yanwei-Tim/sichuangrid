package com.tianque.init;

import java.util.StringTokenizer;

import com.tianque.domain.Permission;
import com.tianque.sysadmin.service.PermissionService;

public class PersistentStrategy implements Strategy {

	private PermissionService permissionService;

	public PersistentStrategy(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public void addPermission(String permission, String parent) {
		String[] permissionProperties = cutString(permission, ";");
		Permission permissionObj = createPermission(permissionProperties,
				parent);
		if (!isPermissionExist(permissionProperties)) {
			permissionService.addPermission(permissionObj);
		}
	}

	private String[] cutString(String str, String flag) {
		StringTokenizer stringTokenizer = new StringTokenizer(str, flag);
		String[] strArray = new String[stringTokenizer.countTokens()];
		for (int i = 0; stringTokenizer.hasMoreTokens(); i++) {
			strArray[i] = stringTokenizer.nextToken();
		}
		return strArray;
	}

	private Permission createPermission(String[] permissionProperties,
			String parentId) {
		Permission permission = new Permission();
		permission.setEname(permissionProperties[0]);
		permission.setCname(permissionProperties[1]);
		permission.setPermissionType(Integer.valueOf(permissionProperties[2]));
		permission.setModuleName(permissionProperties[3]);
		permission.setDescription(permissionProperties[4]);
		permission.setParent(permissionService
				.findPermissionByEname(parentId));
		permission.setEnable(true);
		return permission;
	}

	private boolean isPermissionExist(String[] permissionProperties) {
		Permission permission = permissionService
				.findPermissionByEname(permissionProperties[0]);
		if (permission != null) {
			return true;
		}
		return false;
	}

}
