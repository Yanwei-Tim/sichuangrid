package com.tianque.eventSource.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.issue.constants.IssueConstants;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.vo.IssueViewObject;

@Repository("commandCenterDao")
public class CommandCenterDaoImpl extends AbstractBaseDao implements CommandCenterDao {

	@Override
	public PageInfo<IssueViewObject> findCommandCenterIssueForPageByTargeOrgIdAndDealState(
			Long targeOrgId, Long dealState, Integer page, Integer rows, String sidx, String sord,
			List<PropertyDict> sourcekind) {
		Integer countNum = (Integer) getCommandCenterCount(targeOrgId, sourcekind);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targeOrgId", targeOrgId);
		map.put("dealState", IssueState.STEPCOMPLETE_CODE);
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("sourcekindList", sourcekind);
		List<IssueViewObject> list = getSqlMapClientTemplate().queryForList(
				"searchIssueNew.findCommandCenterIssues", map, (page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public Integer getCommandCenterCount(Long orgId, List<PropertyDict> sourcekind) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("targeOrgId", orgId);
		map.put("dealState", IssueState.STEPCOMPLETE_CODE);
		map.put("sourcekindList", sourcekind);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countCommandCenterIssues", map);
	}

	@Override
	public List<String> getIssueType(Long issueId) {
		return getSqlMapClientTemplate().queryForList("searchIssueNew.getIssueType", issueId);
	}

	@Override
	public PageInfo<IssueViewObject> findMyDone(Long orgId, IssueNew issue, Integer page,
			Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("status",
				issue == null ? null : (issue.getStatus() == -1 ? null : issue.getStatus()));
		map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
		map.put("domainname", PropertyTypes.SOURCE_KIND);
		map.put("smsInput", IssueConstants.SMS_INPUT);
		map.put("webInput", IssueConstants.WEB_INPUT);
		map.put("hotlinePhone", IssueConstants.TELEPHONE_HOTLINE);
		map.put("sortField", sidx);
		map.put("order", sord);
		PageInfo<IssueViewObject> result = createIssueVoPageInfoInstance(getMyDoneCount(map), rows,
				page);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"issue.findMyDoneIssuesForCommandCenter", map, (page - 1) * rows, rows));
		return result;
	}

	private int getMyDoneCount(Map params) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"issue.countMyDoneforCommandCenter", params);
	}

	private PageInfo<IssueViewObject> createIssueVoPageInfoInstance(int totalRecord, int pageSize,
			int pageIndex) {
		PageInfo<IssueViewObject> result = new PageInfo<IssueViewObject>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}
}
