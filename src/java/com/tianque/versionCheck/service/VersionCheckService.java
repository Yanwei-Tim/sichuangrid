package com.tianque.versionCheck.service;

import com.tianque.versionCheck.domain.UserReadedVersion;

public interface VersionCheckService {
	/**
	 * 根据当前版本号查用户是否已读
	 * 
	 * @param currentVersion
	 *        当前版本
	 * @return
	 */
	public boolean getReadedVersion(UserReadedVersion userRV);

	/**
	 * 插入用户已读版本号
	 * 
	 * @param userRV
	 *        用户信息和版本号
	 */
	public void addVersionReaded(UserReadedVersion userRV);
}
