package com.tianque.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.IssueLogDaoNew;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.domain.IssueLogNew;

@Repository("issueLogDaoNew")
@SuppressWarnings("unchecked")
public class IssueLogDaoImplNew extends AbstractBaseDao implements
		IssueLogDaoNew {
	@Override
	public IssueLogNew findIssueLogNewByIssueIdAndTargeorgIdNotDealorgId(
			Long issueId, Long targeorgId, Long dealorgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("targeorgId", targeorgId);
		map.put("dealorgId", dealorgId);
		IssueLogNew issueLogNew = null;
		issueLogNew = (IssueLogNew) getSqlMapClientTemplate()
				.queryForObject(
						"issueLogNew.findIssueLogNewByIssueIdAndTargeorgIdNotDealorgId",
						map);
		if (issueLogNew != null) {
			return issueLogNew;
		} else {
			return (IssueLogNew) getSqlMapClientTemplate().queryForObject(
					"issueLogNew.findIssueLogNewByIssueId", map);
		}
	}

	@Override
	public IssueLogNew addIssueLog(IssueLogNew issueLog) {
		if (issueLog == null) {
			throw new BusinessValidationException("参数错误");
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"issueLogNew.addIssueLog", issueLog);

		return getIssueLogById(id);
	}

	@Override
	public IssueLogNew getIssueLogById(Long id) {
		return (IssueLogNew) getSqlMapClientTemplate().queryForObject(
				"issueLogNew.getIssueLogById", id);

	}

	@Override
	public Long getDealMaxStepIndexByIssueId(Long issueId) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"issueLogNew.getDealMaxStepIndexByIssueId", issueId);

	}

	@Override
	public List<IssueLogNew> findIssueLogsByIssueId(Long issueId) {
		List<IssueLogNew> ii = getSqlMapClientTemplate().queryForList(
				"issueLogNew.findIssueLogsByIssueId", issueId);
		return ii;
	}

	@Override
	public void updateIssueLogDealState(Long id, Long dealState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("dealState", dealState);
		getSqlMapClientTemplate().update("issueLogNew.updateIssueLogDealState",
				map);
	}

	@Override
	public void updateIssueLogLogCompleteTime(Long id, Date logCompleteTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("logCompleteTime", logCompleteTime);
		getSqlMapClientTemplate().update(
				"issueLogNew.updateIssueLogLogCompleteTime", map);
	}

	@Override
	public void updateSupervisionState(Long id, Long supervisionState) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("id", id);
		parameter.put("supervisionState", supervisionState);
		getSqlMapClientTemplate().update("issueLogNew.updateSupervisionState",
				parameter);
	}

	@Override
	public void changeIssueLogByIssueId(IssueLogNew log) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("dealUserName", log.getDealUserName());
		parameter.put("createUser", log.getCreateUser());
		parameter.put("dealOrg", log.getDealOrg());
		parameter.put("issueid", log.getIssue().getId());
		getSqlMapClientTemplate().update("issueLogNew.changeIssueLogByIssueId",
				parameter);
	}

	@Override
	public IssueLogNew getCompleteIssueLogNewByIssueId(Long issueId) {
		return (IssueLogNew) getSqlMapClientTemplate().queryForObject(
				"issueLogNew.getCompleteIssueLogNewByIssueId", issueId);
	}

	@Override
	public IssueLogNew getLastOperationLog(Long issueId, Long orgId, Long state) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("orgId", orgId);
		map.put("state", state);
		return (IssueLogNew) getSqlMapClientTemplate().queryForObject(
				"issueLogNew.getLastOperationLog", map);
	}

	@Override
	public IssueLogNew getIssueLogByIdAndSuperviseState(Long issueLogId,
			Long superviseState, Long doneState, Long completeState) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("id", issueLogId);
		map.put("superviseState", superviseState);
		map.put("doneState", doneState);
		map.put("completeState", completeState);
		return (IssueLogNew) getSqlMapClientTemplate().queryForObject(
				"issueLogNew.getIssueLogByIdAndSuperviseState", map);
	}

	@Override
	public void deleteIssueLogByIssueId(Long id) {
		getSqlMapClientTemplate().delete("issueLogNew.deleteIssueLogByIssueId",
				id);
	}

	@Override
	public List<IssueLogNew> findIssueLogByOrgIdAndDate(Long orgId,
			Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targeOrgId", orgId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate().queryForList(
				"issueLogNew.findIssueLogByOrgIdAndDate", map);
	}

	@Override
	public Long countOverTimeNotDoneByOverTimeLogTable(Long year, Long month,
			Date endDate, Long targeOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("endDate", endDate);
		map.put("targeOrgId", targeOrgId);
		return (Long) getSqlMapClientTemplate().queryForObject(
				"issueLogNew.countOverTimeNotDoneByOverTimeLogTable", map);
	}

	@Override
	public List<IssueLogNew> findIssueLogByOrgInternalCodeAndDate(
			String orgInternalCode, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targeOrgInternalCode", orgInternalCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate().queryForList(
				"issueLogNew.findIssueLogByOrgInternalCodeAndDate", map);
	}

	@Override
	public List<IssueLogNew> findDealStatIssueLogByOrgInternalCodeAndDate(
			String orgInternalCode, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targeOrgInternalCode", orgInternalCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate()
				.queryForList(
						"issueLogNew.findDealStatIssueLogByOrgInternalCodeAndDate",
						map);
	}

}
