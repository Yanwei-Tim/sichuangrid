package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.MergeOrganizationDao;
import com.tianque.exception.base.BusinessValidationException;

@Repository("mergeOrganizationDao")
public class MergeOrganizationDaoImpl extends AbstractBaseDao implements
		MergeOrganizationDao {
	@Override
	public void updatObject(Long orgId, String orgInternalCode,
			String tableName, Long mergeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mergeId", mergeId);
		map.put("tableName", tableName);
		map.put("orgInternalCode", orgInternalCode);
		if (tableName.equals("users")) {
			map.put("organizationid", orgId);
		} else {
			map.put("orgId", orgId);
		}
		getSqlMapClientTemplate().update("merge.updateMergeOrg", map);
	}

	@Override
	public void deleteUserRoleRelasByRoleId(Long id) {
		getSqlMapClientTemplate().delete("merge.deleteUserRoleRelasByRoleId",
				id);
	}

	@Override
	public void deleteUserRoleRelasByRoleIds(List<String> ids) {
		if (ids == null || ids.size() < 0) {
			throw new BusinessValidationException("参数错误");
		}
		getSqlMapClientTemplate().delete("merge.deleteUserRoleRelasByRoleIds",
				ids);
	}

}
