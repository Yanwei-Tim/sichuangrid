package com.tianque.fourTeams.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.dao.FourTeamsIssueLogDaoNew;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;

@Repository("fourTeamsIssueLogDaoNew")
@SuppressWarnings("unchecked")
public class FourTeamsIssueLogDaoImplNew extends AbstractBaseDao implements
		FourTeamsIssueLogDaoNew {
	@Override
	public FourTeamsIssueLogNew findIssueLogNewByIssueIdAndTargeorgIdNotDealorgId(
			Long issueId, Long targeorgId, Long dealorgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("targeorgId", targeorgId);
		map.put("dealorgId", dealorgId);
		FourTeamsIssueLogNew issueLogNew = null;
		issueLogNew = (FourTeamsIssueLogNew) getSqlMapClientTemplate()
				.queryForObject(
						"fourTeamsIssueLogNew.findIssueLogNewByIssueIdAndTargeorgIdNotDealorgId",
						map);
		if (issueLogNew != null) {
			return issueLogNew;
		} else {
			return (FourTeamsIssueLogNew) getSqlMapClientTemplate()
					.queryForObject(
							"fourTeamsIssueLogNew.findIssueLogNewByIssueId",
							map);
		}
	}

	@Override
	public FourTeamsIssueLogNew addIssueLog(FourTeamsIssueLogNew issueLog) {
		if (issueLog == null) {
			throw new BusinessValidationException("参数错误");
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"fourTeamsIssueLogNew.addIssueLog", issueLog);

		return getIssueLogById(id);
	}

	@Override
	public FourTeamsIssueLogNew getIssueLogById(Long id) {
		return (FourTeamsIssueLogNew) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssueLogNew.getIssueLogById", id);

	}

	@Override
	public Long getDealMaxStepIndexByIssueId(Long issueId) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssueLogNew.getDealMaxStepIndexByIssueId", issueId);

	}

	@Override
	public List<FourTeamsIssueLogNew> findIssueLogsByIssueId(Long issueId) {
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssueLogNew.findIssueLogsByIssueId", issueId);
	}

	@Override
	public void updateIssueLogDealState(Long id, Long dealState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("dealState", dealState);
		getSqlMapClientTemplate().update(
				"fourTeamsIssueLogNew.updateIssueLogDealState", map);
	}

	@Override
	public void updateIssueLogLogCompleteTime(Long id, Date logCompleteTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("logCompleteTime", logCompleteTime);
		getSqlMapClientTemplate().update(
				"fourTeamsIssueLogNew.updateIssueLogLogCompleteTime", map);
	}

	@Override
	public void updateSupervisionState(Long id, Long supervisionState) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("id", id);
		parameter.put("supervisionState", supervisionState);
		getSqlMapClientTemplate().update(
				"fourTeamsIssueLogNew.updateSupervisionState", parameter);
	}

	@Override
	public FourTeamsIssueLogNew getCompleteIssueLogNewByIssueId(Long issueId) {
		return (FourTeamsIssueLogNew) getSqlMapClientTemplate()
				.queryForObject(
						"fourTeamsIssueLogNew.getCompleteIssueLogNewByIssueId",
						issueId);
	}

	@Override
	public FourTeamsIssueLogNew getLastOperationLog(Long issueId, Long orgId,
			Long state) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueId", issueId);
		map.put("orgId", orgId);
		map.put("state", state);
		return (FourTeamsIssueLogNew) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssueLogNew.getLastOperationLog", map);
	}

	@Override
	public FourTeamsIssueLogNew getIssueLogByIdAndSuperviseState(
			Long issueLogId, Long superviseState, Long doneState,
			Long completeState) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("id", issueLogId);
		map.put("superviseState", superviseState);
		map.put("doneState", doneState);
		map.put("completeState", completeState);
		return (FourTeamsIssueLogNew) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssueLogNew.getIssueLogByIdAndSuperviseState", map);
	}

	@Override
	public void deleteIssueLogByIssueId(Long id) {
		getSqlMapClientTemplate().delete(
				"fourTeamsIssueLogNew.deleteIssueLogByIssueId", id);
	}

	@Override
	public List<FourTeamsIssueLogNew> findIssueLogByOrgIdAndDate(Long orgId,
			Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targeOrgId", orgId);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssueLogNew.findIssueLogByOrgIdAndDate", map);
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
				"fourTeamsIssueLogNew.countOverTimeNotDoneByOverTimeLogTable",
				map);
	}

	@Override
	public List<FourTeamsIssueLogNew> findIssueLogByOrgInternalCodeAndDate(
			String orgInternalCode, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targeOrgInternalCode", orgInternalCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssueLogNew.findIssueLogByOrgInternalCodeAndDate",
				map);
	}

	@Override
	public List<FourTeamsIssueLogNew> findDealStatIssueLogByOrgInternalCodeAndDate(
			String orgInternalCode, Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targeOrgInternalCode", orgInternalCode);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return getSqlMapClientTemplate()
				.queryForList(
						"fourTeamsIssueLogNew.findDealStatIssueLogByOrgInternalCodeAndDate",
						map);
	}

}
