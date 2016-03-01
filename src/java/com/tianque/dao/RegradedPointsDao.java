package com.tianque.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.RegradedPoints;

public interface RegradedPointsDao {

	RegradedPoints addRegradedPoints(RegradedPoints regradedPoints);

	RegradedPoints getRegradedPointsById(Long id);

	PageInfo<RegradedPoints> findRegradedPointsByOrgIdAndStatDate(Long orgId,
			String statDate, Integer pageNum, Integer pageSize,
			String sortField, String order);

	PageInfo<RegradedPoints> findRegradedPointsByOrgIdAndStatDate(Long orgId,
			Date startDate, Date endDate, Integer pageNum, Integer pageSize,
			String sortField, String order);

	List<RegradedPoints> queryRegradedPointsForListByIds(List<Long> idList);

	void deleteRegradedPointByLogId(Long logId);

	GridPage queryIssueGradeHistoryForPageResultByIssueId(Long keyId,
			Integer page, Integer rows, String sidx, String sord);

	String issueGradeIsVisabled(Long keyId);

	public GridPage queryIssueGradeHistoryForPageResultByHistoryIssueId(
			Long keyId, Integer page, Integer rows, String sidx, String sord);

}
