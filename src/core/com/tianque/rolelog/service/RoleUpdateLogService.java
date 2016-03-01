package com.tianque.rolelog.service;

import java.util.Date;
import java.util.List;

import com.tianque.sysadmin.vo.RoleVo;

public interface RoleUpdateLogService {

	/***
	 * 岗位删除权限时，记录日志
	 * @param roleLog
	 */
	public void addRoleUpdateLog(List<RoleVo> deletePermissionId,Date OperateDate,Integer opearteType);
}
