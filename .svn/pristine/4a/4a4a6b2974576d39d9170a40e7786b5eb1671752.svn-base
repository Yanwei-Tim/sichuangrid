package com.tianque.working.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.working.dao.SecurityPropagandaDao;
import com.tianque.working.domain.SecurityPropaganda;

@Repository("securityPropagandaDao")
public class SecurityPropagandaDaoImpl extends AbstractBaseDao implements SecurityPropagandaDao {

	@Override
	public SecurityPropaganda getSecurityPropagandaById(Long id) {
		return (SecurityPropaganda) getSqlMapClientTemplate().queryForObject(
				"securityPropaganda.getSecurityPropaganda", id);
	}

	@Override
	public SecurityPropaganda addSecurityPropaganda(SecurityPropaganda securityPropaganda) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"securityPropaganda.addSecurityPropaganda", securityPropaganda);
		return getSecurityPropagandaById(id);
	}

	@Override
	public void deleteSecurityPropagandaById(Long id) {
		getSqlMapClientTemplate().delete("securityPropaganda.deleteSecurityPropaganda", id);
	}

	@Override
	public SecurityPropaganda updateSecurityPropaganda(SecurityPropaganda securityPropaganda) {
		getSqlMapClientTemplate().update("securityPropaganda.updateSecurityPropaganda",
				securityPropaganda);
		return getSecurityPropagandaById(securityPropaganda.getId());
	}

	@Override
	public PageInfo<SecurityPropaganda> findSecurityPropagandasForPageByOrgIdAndDailyDirectoryId(
			String dailyDirectoryIds, Long orgId, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dailyDirectoryId", dailyDirectoryIds);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"securityPropaganda.countForPageByOrgId", map);

		List<SecurityPropaganda> resultList = getSqlMapClientTemplate().queryForList(
				"securityPropaganda.findForPageByOrgId", map, (page - 1) * rows, rows);

		return new PageInfo<SecurityPropaganda>(page, rows, countNum, resultList);
	}

}
