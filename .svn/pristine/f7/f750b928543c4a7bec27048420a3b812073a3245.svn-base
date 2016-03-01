package com.tianque.issue.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.issue.dao.IssueStandardForFunOrgDao;
import com.tianque.issue.domain.IssueStandardForFunOrg;

@Repository("issueStandardForFunOrgDao")
public class IssueStandardForFunOrgDaoImpl extends AbstractBaseDao implements
		IssueStandardForFunOrgDao {

	@Override
	public IssueStandardForFunOrg getIssueStandardForFunOrgById(Long id) {
		return (IssueStandardForFunOrg) getSqlMapClientTemplate()
				.queryForObject(
						"issueStandardForFunOrg.getIssueStandardForFunOrgById",
						id);
	}

	@Override
	public IssueStandardForFunOrg addIssueStandardForFunOrg(
			IssueStandardForFunOrg issueStandardForFunOrg) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"issueStandardForFunOrg.addIssueStandardForFunOrg",
				issueStandardForFunOrg);
		return getIssueStandardForFunOrgById(id);
	}

	@Override
	public IssueStandardForFunOrg updateIssueStandardForFunOrg(
			IssueStandardForFunOrg issueStandardForFunOrg) {
		getSqlMapClientTemplate().update(
				"issueStandardForFunOrg.updateIssueStandardForFunOrg",
				issueStandardForFunOrg);
		return getIssueStandardForFunOrgById(issueStandardForFunOrg.getId());
	}

	@Override
	public PageInfo<IssueStandardForFunOrg> findIssueStandardForFunOrgsForList(
			Integer pageNum, Integer pageSize, String sidx, String sord,
			Long userLevel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userLevel", userLevel);
		map.put("orgId", ThreadVariable.getOrganization().getId());

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueStandardForFunOrg.countIssueStandardForFunOrgsForList",
				map);

		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);

		List<IssueStandardForFunOrg> list = getSqlMapClientTemplate()
				.queryForList(
						"issueStandardForFunOrg.findIssueStandardForFunOrgsForList",
						map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<IssueStandardForFunOrg> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<IssueStandardForFunOrg> pageInfo = new PageInfo<IssueStandardForFunOrg>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public boolean deleteIssueStandardForFunOrgByIds(Long[] ids) {
		return getSqlMapClientTemplate().delete(
				"issueStandardForFunOrg.deleteIssueStandardForFunOrgByIds",
				Arrays.asList(ids)) == 0;
	}

	@Override
	public IssueStandardForFunOrg getIssueStandardForFunOrgByOrgIdAndItemId(
			Long orgId, Long itemId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("itemId", itemId);
		return (IssueStandardForFunOrg) getSqlMapClientTemplate()
				.queryForObject(
						"issueStandardForFunOrg.getIssueStandardForFunOrgByOrgIdAndItemId",
						map);
	}

	@Override
	public List<IssueStandardForFunOrg> findItemTypeByFunOrgId(Long funOrgId) {
		return getSqlMapClientTemplate().queryForList(
				"issueStandardForFunOrg.findItemTypeByFunOrgId", funOrgId);
	}

	@Override
	public boolean validateRepeatByOrgIdAndItemName(
			IssueStandardForFunOrg issueStandardForFunOrg) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueStandardForFunOrg.validateRepeatByOrgIdAndItemName",
				issueStandardForFunOrg);
		return count == null || count == 0;
	}
}
