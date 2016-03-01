package com.tianque.service;

import com.tianque.domain.SystemKeyInfo;

public interface SystemKeyInfoService {

	public SystemKeyInfo addSystemKeyInfo(SystemKeyInfo systemKeyInfo);

	public SystemKeyInfo updateSystemKeyInfo(SystemKeyInfo systemKeyInfo);

	public SystemKeyInfo getSimpleSystemKeyInfoByName(String name);

}
