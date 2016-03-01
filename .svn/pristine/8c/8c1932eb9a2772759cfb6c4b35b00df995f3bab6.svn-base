package com.tianque.newVillage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.AbstractBaseDao;
import com.tianque.domain.Organization;
import com.tianque.newVillage.dao.LeadingEnterpriseDao;
import com.tianque.newVillage.domain.LeadingEnterprise;

@Repository("leadingEnterpriseDao")
public class LeadingEnterpriseDaoImpl extends AbstractBaseDao implements
		LeadingEnterpriseDao {

	@Override
	public LeadingEnterprise addLeadingEnterprise(
			LeadingEnterprise leadingEnterprise) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"leadingEnterprise.addLeadingEnterprise", leadingEnterprise);
		return getLeadingEnterpriseById(id);
	}

	@Override
	public LeadingEnterprise updateLeadingEnterprise(
			LeadingEnterprise leadingEnterprise) {
		getSqlMapClientTemplate().update(
				"leadingEnterprise.updateLeadingEnterprise", leadingEnterprise);
		return getLeadingEnterpriseById(leadingEnterprise.getId());
	}

	@Override
	public void deleteLeadingEnterpriseByIds(String[] ids) {
		getSqlMapClientTemplate().delete(
				"leadingEnterprise.deleteLeadingEnterpriseByIds", ids);
	}

	@Override
	public LeadingEnterprise getLeadingEnterpriseById(Long id) {
		return (LeadingEnterprise) getSqlMapClientTemplate().queryForObject(
				"leadingEnterprise.getLeadingEnterpriseById", id);
	}

	@Override
	public PageInfo<LeadingEnterprise> findLeadingEnterpriseForPageInfo(
			String enterpriseName, Long searchType, Organization organization,
			Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enterpriseName", enterpriseName);
		map.put("searchType", searchType);
		map.put("orgId", organization.getId());
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("orgCode", organization.getOrgInternalCode());
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"leadingEnterprise.countFindLeadingEnterpriseForPageInfo", map);

		List<LeadingEnterprise> list = getSqlMapClientTemplate().queryForList(
				"leadingEnterprise.findLeadingEnterpriseForPageInfo", map,
				(page - 1) * rows, rows);
		PageInfo<LeadingEnterprise> pageInfo = new PageInfo<LeadingEnterprise>(
				page, rows, countNum, list);
		return pageInfo;
	}

}
