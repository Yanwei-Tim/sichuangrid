package com.tianque.publicSecurity.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.publicSecurity.dao.SnapshotSystemDao;
import com.tianque.publicSecurity.domain.SnapshotSystem;
import com.tianque.publicSecurity.domain.vo.SearchSnapshotSystemVo;

/**
 * 抓拍系统表:数据操作层
 * 
 * @author
 * @date 2014-02-11 15:12:12
 */
@Repository("snapshotSystemDao")
public class SnapshotSystemDaoImpl extends
		BaseDaoImpl<SnapshotSystem, SearchSnapshotSystemVo> implements
		SnapshotSystemDao {

	@Override
	public SnapshotSystem getSnapshotSystemBySnapshotNo(String snapshotNo,
			Long orgId) {
		if (snapshotNo == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("snapshotNo", snapshotNo);
		map.put("orgId", orgId);
		return (SnapshotSystem) getSqlMapClientTemplate().queryForObject(
				"snapshotSystem.getSnapshotSystemBySnapshotNo", map);
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis,
			String logoutReason, Date logoutTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		map.put("logoutReason", logoutReason);
		map.put("logoutTime", logoutTime);
		map.putAll(getUpdateDateAndUser());
		getSqlMapClientTemplate().update("snapshotSystem.updateEmphasiseById",
				map);

	}

	@Override
	public void updateByParam(Long id, Long toOrgId, String orgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("toOrgId", toOrgId);
		map.put("orgInternalCode", orgInternalCode);
		map.putAll(getUpdateDateAndUser());
		getSqlMapClientTemplate().update("snapshotSystem.updateByParam", map);

	}

	@Override
	public Integer countByCode(String code, Long toOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("toOrgId", toOrgId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"snapshotSystem.countByCode", map);
	}

}
