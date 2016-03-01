package com.tianque.issue.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.issue.dao.IssueApplyDelayDao;
import com.tianque.issue.domain.IssueApplyDelay;
import com.tianque.issue.domain.IssueStandardForFunOrg;

@Repository("issueApplyDelayDao")
public class IssueApplyDelayDaoImpl extends AbstractBaseDao implements
		IssueApplyDelayDao {

	@Override
	public IssueApplyDelay saveApplyDelay(IssueApplyDelay issueApplyDelay) {
		issueApplyDelay.setApplyDate(new Date());
		Long id = (Long) getSqlMapClientTemplate().insert(
				"issueApplyDelay.saveApplyDelay", issueApplyDelay);
		return getApplyDelayById(id);
	}

	@Override
	public IssueApplyDelay getApplyDelayById(Long id) {
		return (IssueApplyDelay) getSqlMapClientTemplate().queryForObject(
				"issueApplyDelay.getApplyDelayById", id);
	}

	@Override
	public PageInfo<IssueApplyDelay> findIssueDelayList(Long issueStepId,
			Integer page, Integer rows, String sidx, String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"issueApplyDelay.countIssueDelayList", issueStepId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueStepId", issueStepId);
		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);

		List<IssueStandardForFunOrg> list = getSqlMapClientTemplate()
				.queryForList("issueApplyDelay.findIssueDelayList", map,
						(page - 1) * rows, rows);
		return createPageInfo(page, rows, countNum, list);
	}

	private PageInfo<IssueApplyDelay> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<IssueApplyDelay> pageInfo = new PageInfo<IssueApplyDelay>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public IssueApplyDelay getIssueApplyDelayByIssueStepIdAndDelayState(
			Long issueStepId) {
		return (IssueApplyDelay) getSqlMapClientTemplate().queryForObject(
				"issueApplyDelay.getIssueApplyDelayByIssueStepIdAndDelayState",
				issueStepId);
	}

	@Override
	public IssueApplyDelay auditDelay(IssueApplyDelay issueApplyDelay) {
		issueApplyDelay.setAuditDate(new Date());
		getSqlMapClientTemplate().update("issueApplyDelay.auditDelay",
				issueApplyDelay);
		return getApplyDelayById(issueApplyDelay.getId());
	}

}
