package com.tianque.rolelog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.domain.Permission;
import com.tianque.domain.Role;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.rolelog.dao.RoleUpdateLogDAO;
import com.tianque.rolelog.domain.RoleUpdateLog;
import com.tianque.rolelog.service.RoleUpdateLogService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.vo.RoleVo;

@Service("roleUpdateLogService")
@Transactional
public class RoleUpdateLogServiceImpl implements RoleUpdateLogService {
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RoleUpdateLogDAO roleLogDao;

	@Override
	public void addRoleUpdateLog(List<RoleVo> deletePermissionId,
			Date operateDate, Integer operateType) {
		try {
			if (deletePermissionId != null && deletePermissionId.size() != 0) {
				RoleUpdateLog log = new RoleUpdateLog();
				for (RoleVo roleVo : deletePermissionId) {
					Permission permission = permissionService
							.getPermissionByPermissionId(roleVo
									.getPermissionId());
					Role role = permissionService.findRoleById(roleVo
							.getRoleId());
					log.setRole(role);
					log.setPermission(permission);
					log.setOperateDate(operateDate);
					log.setOperateType(operateType);
					roleLogDao.addRoleUpdateLog(log);
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("岗位修改权限日志记录出现错误!", e);
		}
	}

}
