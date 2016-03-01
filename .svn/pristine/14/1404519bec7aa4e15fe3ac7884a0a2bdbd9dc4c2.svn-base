package com.tianque.publicSecurity.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.publicSecurity.dao.BayonetDao;
import com.tianque.publicSecurity.domain.Bayonet;
import com.tianque.publicSecurity.domain.vo.SearchBayonetVo;

/**
 * 卡口表:数据操作层
 * 
 * @author
 * @date 2014-02-11 10:44:36
 */
@Repository("bayonetDao")
public class BayonetDaoImpl extends BaseDaoImpl<Bayonet, SearchBayonetVo>
		implements BayonetDao {

	@Override
	public Bayonet getBayonetByBayonetNo(String bayonetNo, Long orgId) {
		if (bayonetNo == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bayonetNo", bayonetNo);
		map.put("orgId", orgId);
		return (Bayonet) getSqlMapClientTemplate().queryForObject(
				"bayonet.getBayonetByBayonetNo", map);
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
		getSqlMapClientTemplate().update("bayonet.updateEmphasiseById", map);
	}

	@Override
	public void updateByParam(Long id, Long toOrgId, String orgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("toOrgId", toOrgId);
		map.put("orgInternalCode", orgInternalCode);
		map.putAll(getUpdateDateAndUser());
		getSqlMapClientTemplate().update("bayonet.updateByParam", map);

	}

	@Override
	public Integer countByCode(String bayonetNo, Long toOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bayonetNo", bayonetNo);
		map.put("toOrgId", toOrgId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"bayonet.countByCode", map);
	}
}
