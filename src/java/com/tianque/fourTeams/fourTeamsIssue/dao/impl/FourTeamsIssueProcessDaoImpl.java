package com.tianque.fourTeams.fourTeamsIssue.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueProcessDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueMap;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueStep;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueStepGroup;

@Repository("fourTeamsIssueProcessDao")
public class FourTeamsIssueProcessDaoImpl extends AbstractBaseDao implements
		FourTeamsIssueProcessDao {

	@Override
	public FourTeamsIssueStep addIssueStep(FourTeamsIssueStep step) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"fourTeamsIssueStep.addIssueStep", step);
		return getSimpleIssueStepById(id);
	}

	@Override
	public boolean deleteIssueStepsByIssueId(Long issueId) {
		getSqlMapClientTemplate().delete(
				"fourTeamsIssueStep.deleteIssueStepsByIssueId", issueId);
		return true;
	}

	@Override
	public FourTeamsIssueStep getSimpleIssueStepById(Long id) {
		return (FourTeamsIssueStep) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssueStep.loadSimpleStep", id);
	}

	@Override
	public FourTeamsIssueStep updateIssueStepExceptOrg(FourTeamsIssueStep step) {
		getSqlMapClientTemplate().update(
				"fourTeamsIssueStep.updateIssueStepExceptOrg", step);
		return getSimpleIssueStepById(step.getId());
	}

	@Override
	public PageInfo<AutoCompleteData> findChildOrgsByOrgcodeAndNameAndType(
			PropertyDict orgType, String orgCode, String tag, Long[] exceptIds,
			int pageSize, int pageIndex) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgCode", orgCode);
		params.put("tag", tag);
		if (!isEmptyLongArray(exceptIds)) {
			params.put("exceptIds", exceptIds);
		}
		if (orgType != null) {
			params.put("orgType", orgType);
		}
		Integer totalCount = (Integer) getSqlMapClientTemplate()
				.queryForObject("fourTeamsIssueStep.countChildOrgs", params);
		PageInfo<AutoCompleteData> result = createAutoCompleteDataPageInfoInstance(
				totalCount, needPageResult(pageSize, pageIndex) ? pageSize
						: totalCount,
				needPageResult(pageSize, pageIndex) ? pageIndex : 1);
		if (needPageResult(pageSize, pageIndex)) {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"fourTeamsIssueStep.findChildOrgs", params,
					(pageIndex - 1) * pageSize, pageSize));
		} else {
			result.setResult(getSqlMapClientTemplate().queryForList(
					"fourTeamsIssueStep.findChildOrgs", params));
		}
		return result;
	}

	@Override
	public PageInfo<AutoCompleteData> findChildFunOrgsByParentFunIdAndName(
			Long parentId, String tag, Long[] exceptIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("tag", tag);
		if (!isEmptyLongArray(exceptIds)) {
			params.put("exceptIds", exceptIds);
		}
		List<AutoCompleteData> datas = getSqlMapClientTemplate().queryForList(
				"fourTeamsIssueStep.findChildFunOrgsByParentFunIdAndName",
				params);
		PageInfo<AutoCompleteData> result = createAutoCompleteDataPageInfoInstance(
				datas.size(), datas.size(), 1);
		result.setResult(datas);
		return result;
	}

	@Override
	public PageInfo<AutoCompleteData> findChildOrgsByParentIdAndName(
			PropertyDict orgType, Long parentId, String tag, Long[] exceptIds,
			int page, int rows) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("tag", tag);
		if (!isEmptyLongArray(exceptIds)) {
			params.put("exceptIds", exceptIds);
		}
		if (orgType != null) {
			params.put("orgType", orgType);
		}
		Integer totalCount = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"fourTeamsIssueStep.countfindChildOrgsByParentIdAndNameAndType",
						params);
		List<AutoCompleteData> datas = getSqlMapClientTemplate().queryForList(
				"fourTeamsIssueStep.findChildOrgsByParentIdAndNameAndType",
				params, (page - 1) * rows, rows);
		PageInfo<AutoCompleteData> result = createAutoCompleteDataPageInfoInstance(
				totalCount, page, rows);
		result.setResult(datas);
		return result;
	}

	@Override
	public PageInfo<AutoCompleteData> findParentFunOrgsByIdAndName(Long id,
			String tag, Long[] exceptIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("tag", tag);
		if (!isEmptyLongArray(exceptIds)) {
			params.put("exceptIds", exceptIds);
		}
		List<AutoCompleteData> datas = getSqlMapClientTemplate().queryForList(
				"fourTeamsIssueStep.findParentFunOrgsByIdAndName", params);
		PageInfo<AutoCompleteData> result = createAutoCompleteDataPageInfoInstance(
				datas.size(), datas.size(), 1);
		result.setResult(datas);
		return result;
	}

	private boolean needPageResult(int pageSize, int pageIndex) {
		return pageSize > 0 & pageIndex > 0;
	}

	private boolean isEmptyLongArray(Long[] array) {
		return array == null || array.length == 0;
	}

	private PageInfo<AutoCompleteData> createAutoCompleteDataPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<AutoCompleteData> result = new PageInfo<AutoCompleteData>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageSize);
		result.setPerPageSize(pageIndex == 0 ? 1 : pageIndex);
		return result;
	}

	@Override
	public boolean updateGroupId(FourTeamsIssueStep step) {
		getSqlMapClientTemplate().update(
				"fourTeamsIssueStep.updateGroupIdAndKeyStep", step);
		return true;
	}

	@Override
	public FourTeamsIssueStepGroup addIssueStepGroup(
			FourTeamsIssueStepGroup issueStepGroup) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"fourTeamsIssueStep.addIssueStepGroup", issueStepGroup);
		return getSimpleIssueStepGroupById(id);
	}

	@Override
	public FourTeamsIssueStepGroup getSimpleIssueStepGroupById(Long id) {
		return (FourTeamsIssueStepGroup) getSqlMapClientTemplate()
				.queryForObject("fourTeamsIssueStep.loadSimpleStepGroup", id);
	}

	@Override
	public List<FourTeamsIssueStepGroup> getIssueStepGroupByIssueId(Long id) {
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssueStep.getIssueStepGroupByIssueId", id);
	}

	@Override
	public boolean updateOutLog(FourTeamsIssueStepGroup issueStepGroup) {
		getSqlMapClientTemplate().update("fourTeamsIssueStep.updateOutLog",
				issueStepGroup);
		return true;
	}

	@Override
	public FourTeamsIssueMap getIssueMapByStepGroup(
			FourTeamsIssueStepGroup issueStepGroup) {
		return (FourTeamsIssueMap) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssueStep.getIssueMapByStepGroup", issueStepGroup);
	}

	@Override
	public List<FourTeamsIssueStep> findIssueStepListByIssueState(
			int[] issueStates) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("issueStates", issueStates);
		return getSqlMapClientTemplate().queryForList(
				"fourTeamsIssueStep.findIssueStepListByIssueState", map);
	}

	@Deprecated
	@Override
	public void updateDelayStateOrLastEndDateById(Long id, Integer delayState,
			Date lastEndDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);

		if (delayState != null) {
			map.put("delayState", delayState);
		}

		if (lastEndDate != null) {
			map.put("lastEndDate", lastEndDate);
		}

		getSqlMapClientTemplate().update(
				"fourTeamsIssueStep.updateDelayStateOrLastEndDateById", map);
	}

	@Override
	public void updateDelayStateOrDelayDateByIssueStepId(Long issueStepId,
			Integer delayState, Integer delayWorkday) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", issueStepId);
		if (delayState != null) {
			map.put("delayState", delayState);
		}
		if (delayWorkday != null) {
			map.put("delayWorkday", delayWorkday);
		}
		getSqlMapClientTemplate().update(
				"fourTeamsIssueStep.updateDelayStateOrDelayDateByIssueStepId",
				map);
	}

	@Override
	public Integer getIssueStepCountByIssueId(Long issueId) {

		return (Integer) getSqlMapClientTemplate().queryForObject(
				"fourTeamsIssueStep.getIssueStepCountByIssueId", issueId);
	}

}
