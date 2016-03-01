package com.tianque.versionCheck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.versionCheck.dao.VersionCheckDao;
import com.tianque.versionCheck.domain.UserReadedVersion;

@Service("versionCheckService")
@Transactional
public class VersionCheckServiceImpl implements VersionCheckService {
	@Autowired
	private VersionCheckDao versionCheckDao;

	@Override
	public boolean getReadedVersion(UserReadedVersion userRV) {
		return versionCheckDao.getReadedVersion(userRV);
	}

	@Override
	public void addVersionReaded(UserReadedVersion userRV) {
		versionCheckDao.addVersionReaded(userRV);
	}
}
