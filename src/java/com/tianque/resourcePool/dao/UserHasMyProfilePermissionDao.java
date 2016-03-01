package com.tianque.resourcePool.dao;

import java.util.List;

import com.tianque.resourcePool.domain.UserHasMyProfilePermission;

public interface UserHasMyProfilePermissionDao {

	public Long addUserMyProfilePermission(UserHasMyProfilePermission domain);

	public void deleteMyProfilePermissionByMyProfileId(Long myProfileId);

	public void deleteMyProfilePermissionByMyProfileIds(String[] myProfileIds);

	/**
	 * 批量更新共享资料权限
	 * 
	 * @param domains
	 */
	public void batchAddUserMyProfilePermission(
			List<UserHasMyProfilePermission> domains);

}
