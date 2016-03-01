package com.tianque.init.impl;

import java.util.ArrayList;

import com.tianque.core.util.ConvertXmlAndBean;
import com.tianque.core.util.FileUtil;
import com.tianque.domain.Permission;
import com.tianque.domain.PermissionConfig;
import com.tianque.domain.PermissionObject;
import com.tianque.init.Initialization;
import com.tianque.sysadmin.service.PermissionService;

public class PermissionXmlInitForInstal implements Initialization {

	private PermissionService permissionService;
	private Long count = 0L;

	public PermissionXmlInitForInstal(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	@Override
	public void init() throws Exception {
		initPermission();
		permissionService.setIndexIdPermission(-1L);
	}

	/**
	 * use the PermissionConfig Object from xml init it into the database
	 * author:fuhao
	 * 
	 * @throws Exception
	 */
	public void initPermission() throws Exception {
		PermissionConfig permissionConfig = getPermissionConfig();
		if (null == permissionConfig) {
			throw new Exception("permissionConfig为空");
		}
		createDataInDB(permissionConfig);

	}

	/**
	 * set data in DB,parentid is null;
	 */
	public void createDataInDB(PermissionConfig permissionConfig) {
		ArrayList permissionObjects = permissionConfig.getPermission();
		for (int i = 0; i < permissionObjects.size(); i++) {
			PermissionObject permissionObject = (PermissionObject) permissionObjects
					.get(i);
			Permission permission = new Permission();
			permission.setCname(permissionObject.getCname());
			permission.setEname(permissionObject.getEname());
			permission.setPermissionType(permissionObject.getPermissionType());
			permission.setModuleName(permissionObject.getModuleName());
			permission.setEnable(true);
			permission = permissionService.addPermission(permission);
			count++;
			insertData(permissionObject.getPermissionObject(), permission);
		}
	}

	/**
	 * set data in DB,parentid is not null;
	 */
	public void insertData(ArrayList<PermissionObject> permissionObjects,
			Permission parent) {
		for (int i = 0; i < permissionObjects.size(); i++) {
			PermissionObject permissionObject = permissionObjects.get(i);
			Permission permission = new Permission();
			permission.setCname(permissionObject.getCname());
			permission.setEname(permissionObject.getEname());
			permission.setParent(parent);
			permission.setModuleName(permissionObject.getModuleName());
			permission.setEnable(true);
			permission.setPermissionType(permissionObject.getPermissionType());
			permission = permissionService.addPermission(permission);
			count++;
			if (null == permissionObject.getPermissionObject()) {
				continue;
			}
			insertData(permissionObject.getPermissionObject(), permission);
		}
	}

	/**
	 * @return permissionConfig object
	 */
	public PermissionConfig getPermissionConfig() {
		try {
			ConvertXmlAndBean xmlToBean = new ConvertXmlAndBean();
			PermissionConfig permissionConfig = (PermissionConfig) xmlToBean
					.xmlToBean(FileUtil.getWebRoot()
							+ "/WEB-INF/classes/permissionConfigForInstal.xml",
							PermissionConfig.class, FileUtil.getWebRoot()
									+ "/WEB-INF/classes/mapInputPermission.xml");
			return permissionConfig;
		} catch (Exception e) {
			System.err.println("problem in execute!");
			System.out.println(e.getCause().toString());
			return null;
		}
	}

}
