package com.tianque.init.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.ConvertXmlAndBean;
import com.tianque.core.util.FileUtil;
import com.tianque.domain.PermissionObject;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Role;
import com.tianque.domain.RoleObject;
import com.tianque.domain.RolesConfig;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.init.Initialization;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 将role从xml转化为bean存到数据库
 * 
 * @author fuhao
 */
public class RoleXmlInit implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private PropertyDictService propertyDictService;
	private PermissionService permissionService;

	public RoleXmlInit(PropertyDictService propertyDictService,
			PermissionService permissionService) {
		this.propertyDictService = propertyDictService;
		this.permissionService = permissionService;
	}

	public void init() {
		addRoles(getRolesConfigFromXml());
		logger.info("用户角色初始化完毕!");
	}

	public RolesConfig getRolesConfigFromXml() {
		try {
			ConvertXmlAndBean convert = new ConvertXmlAndBean();
			RolesConfig config = (RolesConfig) convert.xmlToBean(
					FileUtil.getWebRoot() + "/WEB-INF/classes/rolesConfig.xml",
					RolesConfig.class, FileUtil.getWebRoot()
							+ "/WEB-INF/classes/mapInputRoles.xml");
			return config;
		} catch (Exception e) {
			logger.error("初始化错误：", e);
			System.err
					.println("找不到rolesManage.xml or mapoutputRoles.xml or rolesManage.xml,mapoutputRoles.xml's format is not right!");
			return null;
		}
	}

	public boolean addRoles(RolesConfig config) {
		try {
			ArrayList<RoleObject> configs = config.getRole();
			for (RoleObject roleObject : configs) {
				Role role = new Role();
				role.setUseInLevel(getUserInLevel(roleObject.getLevel()));
				role.setCreateDate(Calendar.getInstance().getTime());
				role.setRoleName(roleObject.getRname());
				role.setDescription(roleObject.getDescription());
				permissionService.addRole(role,
						getArrayPermissions(roleObject.getPermission()));
			}
			return true;
		} catch (Exception e) {
			logger.error("异常信息", e);
			return false;
		}
	}

	public String[] getArrayPermissions(ArrayList<PermissionObject> permissions) {
		try {
			List<String> enames = getEnames(permissions);
			String[] enameArray = new String[enames.size()];
			for (int i = 0; i < enames.size(); i++) {
				enameArray[i] = enames.get(i);
			}
			return enameArray;
		} catch (Exception e) {
			logger.error("异常信息", e);
			return null;
		}
	}

	public List<String> getEnames(ArrayList<PermissionObject> permissions) {
		List<String> enames = new ArrayList<String>();
		for (int i = 0; i < permissions.size(); i++) {
			PermissionObject permissionObject = permissions.get(i);
			enames.add(permissionObject.getEname());
			if (null != permissionObject.getPermissionObject()) {
				addEnameRecursion(permissionObject.getPermissionObject(),
						enames);
			}
		}
		return enames;
	}

	public List<String> addEnameRecursion(
			ArrayList<PermissionObject> permissions, List<String> enames) {
		for (PermissionObject permission : permissions) {
			enames.add(permission.getEname());
			if (null != permission.getPermissionObject()) {
				addEnameRecursion(permission.getPermissionObject(), enames);
			}
		}
		return enames;
	}

	public PropertyDict getUserInLevel(int level) {
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationLevel.ORG_LEVEL_KEY, level);
		if (null != orgLevels && orgLevels.size() > 0) {
			PropertyDict propertyDict = orgLevels.get(0);
			return propertyDict;
		}
		return null;
	}

}