package com.tianque.publicSecurity.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.publicSecurity.dao.SkynetDao;
import com.tianque.publicSecurity.domain.Skynet;
import com.tianque.publicSecurity.domain.vo.SearchSkynetVo;

/**
 * 天网表:数据操作层
 * 
 * @author
 * @date 2014-02-10 14:21:16
 */
@Repository("skynetDao")
public class SkynetDaoImpl extends BaseDaoImpl<Skynet, SearchSkynetVo>
		implements SkynetDao {

	@Override
	public Skynet getSkynetBySkynetNo(String skynetNo, Long orgId) {
		if (skynetNo == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("skynetNo", skynetNo);
		map.put("orgId", orgId);
		return (Skynet) getSqlMapClientTemplate().queryForObject(
				"skynet.getSkynetBySkynetNo", map);
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
		getSqlMapClientTemplate().update("skynet.updateEmphasiseById", map);
	}

	@Override
	public void updateByParam(Long id, Long toOrgId, String orgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("toOrgId", toOrgId);
		map.put("orgInternalCode", orgInternalCode);
		map.putAll(getUpdateDateAndUser());
		getSqlMapClientTemplate().update("skynet.updateByParam", map);
	}

	@Override
	public Integer countByCode(String code, Long toOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("toOrgId", toOrgId);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"skynet.countByCode", map);
	}

}
