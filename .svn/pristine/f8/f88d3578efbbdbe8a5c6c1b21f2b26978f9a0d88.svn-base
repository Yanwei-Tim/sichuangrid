package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueApplyDelayDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueApplyDelay;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStandardForFunOrg;

@Repository("fourTeamsIssueApplyDelayDao")
public class FourTeamsIssueApplyDelayDaoImpl extends AbstractBaseDao implements
		FourTeamsIssueApplyDelayDao {

	@Override
	public FourTeamsIssueApplyDelay saveApplyDelay(
			FourTeamsIssueApplyDelay issueApplyDelay) {
		issueApplyDelay.setApplyDate(new Date());
		Long id = (Long) getSqlMapClientTemplate().insert(
				"issueApplyDelay.saveApplyDelay", issueApplyDelay);
		return getApplyDelayById(id);
	}

	@Override
	public FourTeamsIssueApplyDelay getApplyDelayById(Long id) {
		return (FourTeamsIssueApplyDelay) getSqlMapClientTemplate()
				.queryForObject("issueApplyDelay.getApplyDelayById", id);
	}

	@Override
	public PageInfo<FourTeamsIssueApplyDelay> findIssueDelayList(
			Long issueStepId, Integer page, Integer rows, String sidx,
			String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueApplyDelay.countIssueDelayList", issueStepId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueStepId", issueStepId);
		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);

		List<FourTeamsIssueStandardForFunOrg> list = getSqlMapClientTemplate()
				.queryForList("issueApplyDelay.findIssueDelayList", map,
						(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	private PageInfo<FourTeamsIssueApplyDelay> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<FourTeamsIssueApplyDelay> pageInfo = new PageInfo<FourTeamsIssueApplyDelay>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public FourTeamsIssueApplyDelay getIssueApplyDelayByIssueStepIdAndDelayState(
			Long issueStepId) {
		return (FourTeamsIssueApplyDelay) getSqlMapClientTemplate()
				.queryForObject(
						"issueApplyDelay.getIssueApplyDelayByIssueStepIdAndDelayState",
						issueStepId);
	}

	@Override
	public FourTeamsIssueApplyDelay auditDelay(
			FourTeamsIssueApplyDelay issueApplyDelay) {
		issueApplyDelay.setAuditDate(new Date());
		getSqlMapClientTemplate().update("issueApplyDelay.auditDelay",
				issueApplyDelay);
		return getApplyDelayById(issueApplyDelay.getId());
	}
}
