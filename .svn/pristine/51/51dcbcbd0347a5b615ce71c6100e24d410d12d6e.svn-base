package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.SystemKeyInfoDao;
import com.tianque.domain.SystemKeyInfo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("systemKeyInfoDao")
public class SystemKeyInfoDaoImpl extends AbstractBaseDao implements
		SystemKeyInfoDao {

	@Override
	public SystemKeyInfo addSystemKeyInfo(SystemKeyInfo systemKeyInfo) {
		if (!validate(systemKeyInfo))
			throw new BusinessValidationException();
		Long id = (Long) getSqlMapClientTemplate().insert(
				"systemKeyInfo.addSystemKeyInfo", systemKeyInfo);
		return getSimpleSystemKeyInfoById(id);
	}

	@Override
	public SystemKeyInfo getSimpleSystemKeyInfoById(long id) {
		return (SystemKeyInfo) getSqlMapClientTemplate().queryForObject(
				"systemKeyInfo.getSimpleSystemKeyInfoById", id);
	}

	@Override
	public SystemKeyInfo getSimpleSystemKeyInfoByName(String name) {
		if (name == null || "".equals(name.trim()))
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("sortField", null);
		map.put("order", null);
		List<SystemKeyInfo> systemKeyInfos = getSqlMapClientTemplate()
				.queryForList("systemKeyInfo.findSystemKeyInfos", map, 0, 1);
		if (systemKeyInfos != null && systemKeyInfos.size() > 0)
			return systemKeyInfos.get(0);
		return null;
	}

	@Override
	public SystemKeyInfo updateSystemKeyInfo(SystemKeyInfo systemKeyInfo) {
		if (systemKeyInfo.getName() == null
				|| "".equals(systemKeyInfo.getName().trim()))
			throw new BusinessValidationException();
		getSqlMapClientTemplate().update("systemKeyInfo.updateSystemKeyInfo",
				systemKeyInfo);
		return getSimpleSystemKeyInfoById(systemKeyInfo.getId());
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
