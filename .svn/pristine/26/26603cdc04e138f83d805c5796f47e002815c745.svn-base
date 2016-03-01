package com.tianque.sysadmin.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.sysadmin.dao.UserHasMultizoneLocalDao;

@Repository("userHasMultizoneLocalDao")
public class UserHasMultizoneLocalDaoImpl extends AbstractBaseDao implements
		UserHasMultizoneLocalDao {
	@Override
	public void deleteMultizoneByOrgId(Long id) {
		getSqlMapClientTemplate().delete(
				"userHasMultizoneLocal.deleteMultizoneByOrgId", id);
	}
}
