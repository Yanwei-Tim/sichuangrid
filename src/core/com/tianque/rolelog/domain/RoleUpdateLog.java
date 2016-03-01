package com.tianque.rolelog.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Permission;
import com.tianque.domain.Role;
/***
 * 岗位修改日志记录类
 * @author N-223
 *
 */
public class RoleUpdateLog extends BaseDomain {

	private Role role;//包含岗位中文名以及岗位ID
	private Permission permission;//包含权限中文名以及权限ID
	private Integer operateType;//操作类别 0：删除 1：新增
	private Date operateDate;//操作时间
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public Integer getOperateType() {
		return operateType;
	}
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
	
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	
	
}
