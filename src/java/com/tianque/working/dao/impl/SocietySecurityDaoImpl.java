package com.tianque.working.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.working.dao.SocietySecurityDao;
import com.tianque.working.domain.SocietySecurity;

@Repository("societySecurityDao")
public class SocietySecurityDaoImpl extends AbstractBaseDao implements SocietySecurityDao {

	@Override
	public SocietySecurity getSocietySecurityById(Long id) {
		return (SocietySecurity) getSqlMapClientTemplate().queryForObject(
				"societySecurity.getSocietySecurity", id);
	}

	@Override
	public SocietySecurity addSocietySecurity(SocietySecurity societySecurity) {
		Long id = (Long) getSqlMapClientTemplate().insert("societySecurity.addSocietySecurity",
				societySecurity);
		return getSocietySecurityById(id);
	}

	@Override
	public void deleteSocietySecurityById(Long id) {
		getSqlMapClientTemplate().delete("societySecurity.deleteSocietySecurity", id);
	}

	@Override
	public SocietySecurity updateSocietySecurity(SocietySecurity societySecurity) {
		getSqlMapClientTemplate().update("societySecurity.updateSocietySecurity", societySecurity);
		return getSocietySecurityById(societySecurity.getId());
	}

	@Override
	public PageInfo<SocietySecurity> findSocietySecuritysForPageByOrgIdAndDailyDirectoryId(
			String dailyDirectoryIds, Long orgId, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dailyDirectoryId", dailyDirectoryIds);
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"societySecurity.countForPageByOrgId", map);

		List<SocietySecurity> resultList = getSqlMapClientTemplate().queryForList(
				"societySecurity.findForPageByOrgId", map, (page - 1) * rows, rows);

		return new PageInfo<SocietySecurity>(page, rows, countNum, resultList);
	}

}
