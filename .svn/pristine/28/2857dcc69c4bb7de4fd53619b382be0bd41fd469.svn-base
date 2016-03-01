package com.tianque.versionCheck.dao;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.versionCheck.domain.UserReadedVersion;

@Repository("VersionCheckDao")
public class VersionCheckDaoImpl extends AbstractBaseDao implements VersionCheckDao {
	@Override
	public boolean getReadedVersion(UserReadedVersion userRV) {
		Long counts = (Long) getSqlMapClientTemplate().queryForObject(
				"userReadedVersion.getUserReaded", userRV);
		return counts > 0;
	}

	// FIXME 单击调用时,如果频繁点击会重复增加,产生垃圾数据.
	@Override
	public void addVersionReaded(UserReadedVersion userRV) {
		getSqlMapClientTemplate().insert("userReadedVersion.addUserReaded", userRV);
	}

}
