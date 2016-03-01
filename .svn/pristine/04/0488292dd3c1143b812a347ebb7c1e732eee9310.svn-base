package com.tianque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.dao.SystemKeyInfoDao;
import com.tianque.domain.SystemKeyInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.SystemKeyInfoService;

@Service("systemKeyInfoService")
@Transactional
public class SystemKeyInfoServiceImpl extends AbstractBaseService implements
		SystemKeyInfoService {

	@Autowired
	private SystemKeyInfoDao systemKeyInfoDao;

	@Override
	public SystemKeyInfo addSystemKeyInfo(SystemKeyInfo systemKeyInfo) {
		if (!validate(systemKeyInfo))
			throw new BusinessValidationException();
		return systemKeyInfoDao.addSystemKeyInfo(systemKeyInfo);
	}

	@Override
	public SystemKeyInfo getSimpleSystemKeyInfoByName(String name) {
		return systemKeyInfoDao.getSimpleSystemKeyInfoByName(name);
	}

	@Override
	public SystemKeyInfo updateSystemKeyInfo(SystemKeyInfo systemKeyInfo) {
		if (systemKeyInfo.getName() == null
				|| "".equals(systemKeyInfo.getName().trim()))
			throw new BusinessValidationException();
		return systemKeyInfoDao.updateSystemKeyInfo(systemKeyInfo);
	}

	private boolean validate(SystemKeyInfo systemKeyInfo) {
		if (systemKeyInfo.getName() == null
				|| "".equals(systemKeyInfo.getName().trim()))
			return false;
		if (getSimpleSystemKeyInfoByName(systemKeyInfo.getName()) != null)
			return false;

		return true;
	}

}
