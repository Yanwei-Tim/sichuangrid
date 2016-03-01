package com.tianque.init;

import java.util.HashMap;

import com.tianque.domain.Permission;

public class TreeBuilderStrategy implements Strategy {

	public static boolean TREE_BUILT = false;

	private static HashMap<String, Node> map = new HashMap<String, Node>();

	public void addPermission(String permission, String parent) {
		String[] permissionProperties = permission.split(";");
		Permission permissionObj = createPermission(permissionProperties);
		Node node = new Node(permissionObj);
		if (parent != null) {
			Node parentNode = map.get(parent);
			parentNode.add(node);
			node.setParentTree(parentNode);
			node.setLevel(parentNode.getLevel() + 1);
		} else {
			node.setLevel(1);
		}
		map.put(permissionProperties[0], node);
	}

	public static HashMap<String, Node> getResult() {
		return map;
	}

	private Permission createPermission(String[] permissionProperties) {
		Permission permission = new Permission();
		permission.setEname(permissionProperties[0]);
		permission.setPermissionType(Integer.parseInt(permissionProperties[2]));
		permission.setCname(permissionProperties[1]);
		permission.setModuleName(permissionProperties[3]);
		permission.setDescription(permissionProperties[4]);
		return permission;
	}
}
