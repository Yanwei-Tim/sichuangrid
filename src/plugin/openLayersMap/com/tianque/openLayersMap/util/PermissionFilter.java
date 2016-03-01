package com.tianque.openLayersMap.util;

import java.util.List;

import com.tianque.core.util.ThreadVariable;
import com.tianque.openLayersMap.domian.PermissionVo;
import com.tianque.sysadmin.service.PermissionService;

public class PermissionFilter {

	public static <T extends PermissionVo> void filterPermission(
			List<T> resultList, Long userId, PermissionService permissionService) {
		if (!ThreadVariable.getUser().isAdmin()) {
			for (int i = 0; i < resultList.size(); i++) {
				T result = resultList.get(i);
				if (!isHasPermission(ThreadVariable.getUser().getId(),
						result.getPermissionName(), permissionService)) {
					resultList.remove(i);
					i--;
				}
			}
		}
	}

	public static boolean isHasPermission(Long userId, String ename,
			PermissionService permissionService) {
		if (!ThreadVariable.getUser().isAdmin()) {
			if (ThreadVariable.getPermissionEnameList() != null) {
				return ThreadVariable.getPermissionEnameList().contains(ename);
			} else {
				return permissionService.isUserHasPermission(userId, ename);
			}
		}
		return true;
	}

}
