package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueStandardForFunOrgDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStandardForFunOrg;

@Repository("fourTeamsIssueStandardForFunOrgDao")
public class FourTeamsIssueStandardForFunOrgDaoImpl extends AbstractBaseDao
		implements FourTeamsIssueStandardForFunOrgDao {

	@Override
	public FourTeamsIssueStandardForFunOrg getIssueStandardForFunOrgById(Long id) {
		return (FourTeamsIssueStandardForFunOrg) getSqlMapClientTemplate()
				.queryForObject(
						"fourTeamsIssueStandardForFunOrg.getIssueStandardForFunOrgById",
						id);
	}

	@Override
	public FourTeamsIssueStandardForFunOrg addIssueStandardForFunOrg(
			FourTeamsIssueStandardForFunOrg issueStandardForFunOrg) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"fourTeamsIssueStandardForFunOrg.addIssueStandardForFunOrg",
				issueStandardForFunOrg);
		return getIssueStandardForFunOrgById(id);
	}

	@Override
	public FourTeamsIssueStandardForFunOrg updateIssueStandardForFunOrg(
			FourTeamsIssueStandardForFunOrg issueStandardForFunOrg) {
		getSqlMapClientTemplate().update(
				"fourTeamsIssueStandardForFunOrg.updateIssueStandardForFunOrg",
				issueStandardForFunOrg);
		return getIssueStandardForFunOrgById(issueStandardForFunOrg.getId());
	}

	@Override
	public PageInfo<FourTeamsIssueStandardForFunOrg> findIssueStandardForFunOrgsForList(
			Integer pageNum, Integer pageSize, String sidx, String sord,
			Long userLevel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userLevel", userLevel);
		map.put("orgId", ThreadVariable.getOrganization().getId());

		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"fourTeamsIssueStandardForFunOrg.countIssueStandardForFunOrgsForList",
						map);

		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);

		List<FourTeamsIssueStandardForFunOrg> list = getSqlMapClientTemplate()
				.queryForList(
						"fourTeamsIssueStandardForFunOrg.findIssueStandardForFunOrgsForList",
						map, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<FourTeamsIssueStandardForFunOrg> createPageInfo(
			int pageNum, int pageSize, Integer countNum, List list) {
		PageInfo<FourTeamsIssueStandardForFunOrg> pageInfo = new PageInfo<FourTeamsIssueStandardForFunOrg>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public boolean deleteIssueStandardForFunOrgByIds(Long[] ids) {
		return getSqlMapClientTemplate()
				.delete(
						"fourTeamsIssueStandardForFunOrg.deleteIssueStandardForFunOrgByIds",
						Arrays.asList(ids)) == 0;
	}

	@Override
	public FourTeamsIssueStandardForFunOrg getIssueStandardForFunOrgByOrgIdAndItemId(
			Long orgId, Long itemId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("itemId", itemId);
		return (FourTeamsIssueStandardForFunOrg) getSqlMapClientTemplate()
				.queryForObject(
						"fourTeamsIssueStandardForFunOrg.getIssueStandardForFunOrgByOrgIdAndItemId",
						map);
	}

	@Override
	public List<FourTeamsIssueStandardForFunOrg> findItemTypeByFunOrgId(
			Long funOrgId) {
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssueStandardForFunOrg.findItemTypeByFunOrgId",
				funOrgId);
	}

	@Override
	public boolean validateRepeatByOrgIdAndItemName(
			FourTeamsIssueStandardForFunOrg issueStandardForFunOrg) {
		Integer count = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"fourTeamsIssueStandardForFunOrg.validateRepeatByOrgIdAndItemName",
						issueStandardForFunOrg);
		return count == null || count == 0;
	}
}
