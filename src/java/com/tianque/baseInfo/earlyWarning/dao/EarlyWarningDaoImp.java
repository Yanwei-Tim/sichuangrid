package com.tianque.baseInfo.earlyWarning.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.earlyWarning.domain.EarlyWarning;
import com.tianque.core.base.AbstractBaseDao;

@Repository("earlyWarningDao")
public class EarlyWarningDaoImp extends AbstractBaseDao implements
		EarlyWarningDao {

	@Override
	public EarlyWarning getEarlyWarningByName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		return (EarlyWarning) getSqlMapClientTemplate().queryForObject(
				"earlyWarning.getEarlyWarningByName", map);

	}
}
