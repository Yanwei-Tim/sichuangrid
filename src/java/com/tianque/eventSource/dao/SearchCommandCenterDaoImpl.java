package com.tianque.eventSource.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.issue.state.IssueState;
import com.tianque.issue.vo.IssueViewObject;

@Repository("searchCommandCenterDao")
public class SearchCommandCenterDaoImpl extends AbstractBaseDao implements SearchCommandCenterDao {

	public PageInfo<IssueViewObject> searchCommandCenterIssueForPageBySearchIssueVoNew(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows, String sidx, String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countSearchCommandCenterIssues", searchIssueVo);
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> list = getSqlMapClientTemplate().queryForList(
				"searchIssueNew.searchCommandCenterIssues", searchIssueVo, (page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> searchDoneIssues(SearchIssueVoNew searchIssueVo, Integer page,
			Integer rows, String sidx, String sord) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchIssueNew.countDoneIssuesforCommandCenter",
				getIssueVoMap(searchIssueVo, page, rows));
		int pageCount = 0;
		if ((countNum % rows) == 0) {
			pageCount = countNum / rows;
		} else {
			pageCount = countNum / rows + 1;
		}
		page = page > pageCount ? pageCount : page;
		List<IssueViewObject> issues = getSqlMapClientTemplate().queryForList(
				"searchIssueNew.searchDoneIssuesforCommandCenter",
				getIssueVoMap(searchIssueVo, page, rows), (page - 1) * rows, rows);
		PageInfo<IssueViewObject> pageInfo = new PageInfo<IssueViewObject>();
		pageInfo.setResult(issues);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page > pageCount ? pageCount : page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	private Map getIssueVoMap(SearchIssueVoNew searchIssueVo, Integer page, Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (searchIssueVo.getStatus() != null && searchIssueVo.getStatus() == -1) {
			searchIssueVo.setStatus(null);
		}
		map.put("targeOrgId", searchIssueVo.getTargeOrgId());
		map.put("occurFrom", searchIssueVo.getOccurFrom());
		map.put("occurEnd", searchIssueVo.getOccurEnd());
		map.put("subject", searchIssueVo.getSubject());
		map.put("status", searchIssueVo.getStatus());
		map.put("dealState", searchIssueVo.getDealState());
		map.put("completeCode", IssueState.STEPCOMPLETE_CODE);
		map.put("sourceMobile", searchIssueVo.getSourceMobile());
		map.put("serialNumber", searchIssueVo.getSerialNumber());
		map.put("issueContent", searchIssueVo.getIssueContent());
		map.put("sortField", page);
		map.put("order", rows);
		return map;
	}
}
