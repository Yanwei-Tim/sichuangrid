/**
 * tianque-com.tianque.core.user.controllor-PermissionControllor.java Created on Mar 19, 2010
 * Copyright (c) 2010 by 杭州天阙科技有限公司
 */
package com.tianque.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.Permission;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Role;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkBenchType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.init.Node;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.vo.RoleVo;

/**
 * Title: ***<br>
 * 
 * @author <a href=mailto:nifeng@hztianque.com>倪峰</a><br>
 * @description ***<br/>
 * @version 1.0
 */
@Controller("roleManageController")
@Scope("prototype")
@Transactional
public class RoleManageController extends BaseAction {
	@Autowired
	private PermissionService permissionService;

	@Autowired
	private OrganizationDubboService OrganizationDubboService;

	@Autowired
	private PropertyDictService propertyDictService;

	/**
	 * 选中的Role列表
	 */
	private Map<String, Boolean> permissionsMap = new HashMap<String, Boolean>();

	/**
	 * 编辑、修改、查看所需要的role对象
	 */
	private Role role = new Role();
	private Long dictUseLevelId;
	private Long roleId;
	private Long orgId;
	private String selectIds;
	private Long useInLevelId;
	private String type;
	private List<Role> roles;
	private User user;

	private List<PropertyDict> orgLevelDict = new ArrayList<PropertyDict>();
	private PropertyDict propertyDict;

	public PropertyDict getPropertyDict() {
		return propertyDict;
	}

	public void setPropertyDict(PropertyDict propertyDict) {
		this.propertyDict = propertyDict;
	}

	@PermissionFilter(ename = "addRole")
	public String addRole() throws Exception {
		if (ERROR.equals(validateRoleName())) {
			this.errorMessage = "岗位名已经存在";
			return ERROR;
		}
		role = permissionService.addRolePermissionRelasByRoleVos(role,
				getRoleVoList());
		return SUCCESS;
	}

	private String roleJson;

	private List<RoleVo> getRoleVoList() throws Exception {
		boolean flg = false;
		List<Long> rolePermissionIds = null;
		List<RoleVo> roleVoList = new ArrayList<RoleVo>();
		if (roleJson == null || "".equals(roleJson)) {
			return roleVoList;
		}
		if (DialogMode.ADD_LEANDER_VIEW.equals(mode)) {
			rolePermissionIds = permissionService
					.getRolePermissionByRoleId(role.getId());// 根据岗位ID查询该岗位下所有的权限ID
		}
		for (String str : roleJson.split(",")) {
			flg = false;
			if (str == null || "".equals(str)) {
				continue;
			}
			String[] arr = str.split(":");
			if (arr[0] == null || "".equals(arr[0]) || arr[0].equals("null")
					|| arr[1] == null || "".equals(arr[1])
					|| arr[1].equals("null")) {
				continue;
			}
			if (DialogMode.ADD_LEANDER_VIEW.equals(mode)) {
				if (rolePermissionIds != null && rolePermissionIds.size() != 0) {
					if (rolePermissionIds.contains(Long.valueOf(arr[0].trim()))
							&& Boolean.valueOf(arr[1].trim())) {// 如果新增的权限ID在已有岗位中存在则不管
						flg = true;
					}
				}
			}
			if (!flg) {
				RoleVo vo = new RoleVo();
				vo.setPermissionId(Long.valueOf(arr[0].trim()));
				vo.setStstus(Boolean.valueOf(arr[1].trim()));
				roleVoList.add(vo);
			}
		}
		return roleVoList;
	}

	private boolean checkNoRoleAssigned() {
		if (role == null || ((BaseDomain) role).getId() == null) {
			this.errorMessage = "系统错误,请联系管理员!";
			return false;
		}
		return true;
	}

	@PermissionFilter(ename = "deleteRole")
	public String deleteRole() throws Exception {
		if (!checkNoRoleAssigned())
			return ERROR;
		if (this.permissionService.isRoleUsed(role.getId())) {
			this.errorMessage = "岗位已经被分配给用户，无法删除！";
			return ERROR;
		}
		permissionService.deleteRoleById(role.getId());
		return SUCCESS;
	}

	public String dispatchOperate() {
		if (null == dictUseLevelId) {
			errorMessage = "参数不能为空";
			return ERROR;
		}
		PropertyDict propertyDict = propertyDictService
				.getPropertyDictById(dictUseLevelId);
		roles = permissionService.findRolesByUserInLevel(propertyDict.getId());
		role = permissionService.findRoleById(roleId);
		if (null != role) {
			roles.remove(role);
		}
		return SUCCESS;
	}

	public String findUsersList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(new PageInfo<User>());
			return SUCCESS;
		}
		gridPage = new GridPage(
				ControllerHelper.processAllOrgRelativeName(permissionService
						.findUsersForPageInfoListByOrgInternalCodeAndRoleId(
								OrganizationDubboService
										.getOrgInternalCodeById(orgId), roleId,
								page, rows, sidx, sord),
						OrganizationDubboService,
						new String[] { "organization" }, null));
		return SUCCESS;
	}

	public String isTrueSelected() throws Exception {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误！");
		}
		Organization organization = OrganizationDubboService
				.getSimpleOrgById(orgId);
		if (organization.getOrgLevel().getId() < useInLevelId) {
			return ERROR;
		} else {
			return SUCCESS;
		}
	}

	public String updateUsersByUserId() throws Exception {
		String[] deleteId = selectIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		for (Long userId : idList) {
			permissionService.deleteUserRoleRelasByUserId(userId);
			permissionService.addUserRoleRela(userId, roleId);
		}
		return SUCCESS;
	}

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	@SuppressWarnings("unchecked")
	@PermissionFilter(ename = "roleManagement")
	public String findRolesByUseInLevel() throws Exception {
		Session session = ThreadVariable.getSession();
		User user = permissionService.getSimpleUserById(session.getUserId());
		Organization org = OrganizationDubboService.getFullOrgById(user
				.getOrganization().getId());
		PageInfo pageInfo = permissionService.findAllRolesByUseInLevelForPage(
				this.getPage(), this.getRows(), org.getOrgLevel().getId(),
				sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private List getCheckedPareTreeByCurrentId(String nodeId, Integer n_lv)
			throws Exception {
		List<Node> permissionNodes = permissionService.getMenuPermissionTree(
				nodeId, n_lv);
		if (DialogMode.ADD_MODE.equals(this.mode)) {// 此处也要进行修改，添加时候给的权限
			return permissionNodes;
		}
		List<Permission> permissions = this.permissionService
				.findAllPermissionsByRoleId(role.getId());
		permissionNodes = permissionService.checkPermissionTree(permissions,
				permissionNodes);
		return permissionNodes;
	}

	public Map<String, Boolean> getPermissionsMap() {
		return permissionsMap;
	}

	public Role getRole() {
		return role;
	}

	@PermissionFilter(ename = "roleManagement")
	public String preparePermissionTree() throws Exception {
		// wangxiaohu 20130902 岗位分配权限界面采用异步加载方式
		String nodeId = ServletActionContext.getRequest()
				.getParameter("nodeid");
		Integer n_level = -1;
		try {
			n_level = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("n_level"));
		} catch (Exception e) {
		}
		gridPage = new GridPage();
		// 新增两个参数 上级id，上级层级
		gridPage.setRows(getCheckedPareTreeByCurrentId(nodeId, n_level));
		// wangxiaohu end
		return SUCCESS;
	}

	@PermissionFilter(ename = "roleManagement")
	public String prepareLeaderViewPermissionTree() throws Exception {
		// wangxiaohu 20130902 岗位分配权限界面采用异步加载方式
		String nodeId = ServletActionContext.getRequest()
				.getParameter("nodeid");
		Integer n_level = -1;
		try {
			n_level = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("n_level"));
		} catch (Exception e) {
		}
		gridPage = new GridPage();
		// 新增两个参数 上级id，上级层级
		List<Node> permissionNodes = permissionService
				.getMenuLeaderPermissionTree(nodeId, n_level);
		List<Permission> permissions = this.permissionService
				.findAllPermissionsByRoleId(role.getId());
		permissionNodes = permissionService.checkPermissionTree(permissions,
				permissionNodes);
		gridPage.setRows(permissionNodes);
		// gridPage.setRows(getCheckedPareTreeByCurrentId(nodeId, n_level));
		// wangxiaohu end
		return SUCCESS;
	}

	@PermissionFilter(ename = "roleManagement")
	public String prepareRole() throws Exception {
		if (DialogMode.ADD_MODE.equals(this.mode)) {
			role = new Role();

		} else if (DialogMode.COPY_MODE.equals(this.mode)) {
			role = this.permissionService.findRoleById(role.getId());

			// role.setId(null);
		} else {
			role = this.permissionService.findRoleById(role.getId());
			propertyDict = propertyDictService.getPropertyDictById(role
					.getUseInLevel().getId());
			role.setUseInLevel(propertyDict);
		}

		int[] internalIds = getOrgLevelDownInternalidsByCurrentUerOrgLevel();
		orgLevelDict = propertyDictService
				.findPropertyDictByDomainNameAndInternalIds(
						PropertyTypes.ORGANIZATION_LEVEL, internalIds);

		return SUCCESS;
	}

	private int[] getOrgLevelDownInternalidsByCurrentUerOrgLevel() {
		Session session = ThreadVariable.getSession();
		User user = permissionService.getSimpleUserById(session.getUserId());
		Organization org = OrganizationDubboService.getFullOrgById(user
				.getOrganization().getId());
		int userInLevle = org.getOrgLevel().getInternalId();

		int[] internalIds = new int[userInLevle + 1];
		for (int i = 0; i < userInLevle + 1; i++) {
			internalIds[i] = i;
		}
		return internalIds;
	}

	public void setPermissionsMap(Map<String, Boolean> permissionsMap) {
		this.permissionsMap = permissionsMap;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@PermissionFilter(ename = "updateRole")
	public String updateRole() throws Exception {
		if (!checkNoRoleAssigned())
			return ERROR;
		if (!DialogMode.ADD_LEANDER_VIEW.equals(mode)) {
			if (ERROR.equals(validateRoleName())) {
				this.errorMessage = "岗位名已经存在!";
				return ERROR;
			}
		}
		role = permissionService.updateRolePermissionRelasByRoleVos(role,
				getRoleVoList());
		return SUCCESS;
	}

	@PermissionFilter(ename = "roleManagement")
	public String validateRoleName() throws Exception {
		if (role == null) {
			return ERROR;
		} else {
			if (role.getRoleName() == null) {
				return ERROR;
			}
			if (role.getUseInLevel().getId() == null
					|| role.getUseInLevel().getId().longValue() == 0L) {
				return SUCCESS;
			}
			Role checkRole = this.permissionService
					.findRoleByRoleNameAndUserInLevel(role.getRoleName(), role
							.getUseInLevel().getId());
			if (checkRole == null) {
				return SUCCESS;
			}
			if (DialogMode.EDIT_MODE.equals(this.mode) && checkRole != null
					&& checkRole.getId().equals(role.getId())) {
				return SUCCESS;
			}
		}
		return ERROR;
	}

	@PermissionFilter(ename = "copyRole")
	public String copyRole() throws Exception {
		if (ERROR.equals(validateRoleName())) {
			this.errorMessage = "岗位名已经存在";
			return ERROR;
		}
		role = permissionService.copyRole(role);
		return SUCCESS;
	}

	@PermissionFilter(ename = "roleManagement")
	public String checkWorkBenchType() throws Exception {
		if (ThreadVariable.getUser() != null
				&& ThreadVariable.getUser().isAdmin()) {
			PropertyDict workBenchType = new PropertyDict();
			workBenchType.setInternalId(WorkBenchType.SUPER_LEVEL);
			workBenchType.setDisplayName(WorkBenchType.SUPER_LEVEL_NAME);
			role.setWorkBenchType(workBenchType);
		} else {
			// 根据用户Id获取第一个角色实体
			role = permissionService.findRolesByUserId(
					ThreadVariable.getSession().getUserId()).get(0);
			PropertyDict workBenchType = propertyDictService
					.getPropertyDictById(role.getWorkBenchType().getId());
			role.setWorkBenchType(workBenchType);
		}
		return SUCCESS;
	}

	@Action(value = "usersConfigure", results = {
			@Result(name = "success", location = "/sysadmin/roleManage/usersConfigure.jsp")})
	public String usersConfigure() {
//		if (null == dictUseLevelId) {
//			errorMessage = "参数不能为空";
//			return ERROR;
//		}
//		PropertyDict propertyDict = propertyDictService
//				.getPropertyDictById(dictUseLevelId);
//		roles = permissionService.findRolesByUserInLevel(propertyDict.getId());
//		if (null != role) {
//			roles.remove(role);
//		}
		return SUCCESS;
	}
	
//	@Action(value = "addRoleToSelectUser", results = {
//			@Result(name = "success", type = "json", params = {"root", "true",
//					"ignoreHierarchy", "false", "excludeNullProperties", "true"})})
	public String addRoleToSelectUser() {
		permissionService.addRoleToSelectUser(user, roleId,type,selectIds);
		return SUCCESS;
	}
	
	public List<PropertyDict> getOrgLevelDict() {
		return orgLevelDict;
	}

	public void setOrgLevelDict(List<PropertyDict> orgLevelDict) {
		this.orgLevelDict = orgLevelDict;
	}

	public Long getDictUseLevelId() {
		return dictUseLevelId;
	}

	public void setDictUseLevelId(Long dictUseLevelId) {
		this.dictUseLevelId = dictUseLevelId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getSelectIds() {
		return selectIds;
	}

	public void setSelectIds(String selectIds) {
		this.selectIds = selectIds;
	}

	public Long getUseInLevelId() {
		return useInLevelId;
	}

	public void setUseInLevelId(Long useInLevelId) {
		this.useInLevelId = useInLevelId;
	}

	public String getRoleJson() {
		return roleJson;
	}

	public void setRoleJson(String roleJson) {
		this.roleJson = roleJson;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
