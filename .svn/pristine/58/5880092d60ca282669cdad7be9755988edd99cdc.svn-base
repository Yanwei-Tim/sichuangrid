package com.tianque.resourcePool.service;

import java.util.List;

import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.resourcePool.domain.UserHasMyProfilePermission;

public interface UserHasMyProfilePermissionService {

	public void addUserMyProfilePermission(
			List<UserHasMyProfilePermission> domains, List<Long> orgs,
			List<Long> list, Long sendMessage, List<MyProfile> files);

	public void deleteMyProfilePermissionByMyProfileId(Long myProfileId);

	public void deleteMyProfilePermissionByMyProfileIds(String[] myProfileIds);
}
