package com.tianque.sysadmin.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tianque.domain.Permission;
import com.tianque.sysadmin.vo.PermissionTree;

public class PermissionTreeUtil {

	public static final Long PARENT_ID = -1L;

	public static void convertPermissions(PermissionTree permissionTree,
			List<Permission> permissions) {
		Map<Long, List<Permission>> map = new HashMap<Long, List<Permission>>();
		List<Permission> permissonTemp;
		for (Permission permission : permissions) {
			Long key = null;
			if (permission.getParent() != null) {
				key = permission.getParent().getId();
			} else {
				key = PARENT_ID;
			}
			permissonTemp = map.get(key);
			if (permissonTemp == null) {
				permissonTemp = new ArrayList<Permission>();
			}
			permissonTemp.add(permission);
			map.put(key, permissonTemp);
		}
		sortPermissionTreeMap(map);
		convertPermissionTree(permissionTree, PARENT_ID, map);
	}

	private static void sortPermissionTreeMap(Map<Long, List<Permission>> map) {
		for (Entry<Long, List<Permission>> entry : map.entrySet()) {
			Collections.sort(entry.getValue(), new Comparator() {
				public int compare(Object o1, Object o2) {
					Permission per1 = (Permission) o1;
					Permission per2 = (Permission) o2;
					return per1.getIndexId() > per2.getIndexId() ? 1 : -1;
				}
			});
		}
	}

	private static void convertPermissionTree(PermissionTree permissionTree,
			Long parentId, Map<Long, List<Permission>> map) {
		PermissionTree tree;
		for (Permission permission : map.get(parentId)) {
			tree = new PermissionTree(permission);
			if (map.get(permission.getId()) != null) {
				convertPermissionTree(tree, permission.getId(), map);
			}
			permissionTree.getChildren().add(tree);
		}
	}

	public static void checkUserHasPermissions(List<PermissionTree> treeList,
			List<String> permissionList) {
		for (int i = 0; i < treeList.size(); i++) {
			PermissionTree permissionTree = treeList.get(i);
			if (!permissionList.contains(permissionTree.getEname())) {
				treeList.remove(permissionTree);
				i--;
				continue;
			}
			if (permissionTree.getChildren().size() > 0) {
				checkUserHasPermissions(permissionTree.getChildren(),
						permissionList);
			}
		}
	}
}
