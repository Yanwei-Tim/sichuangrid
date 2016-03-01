package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchEmphasisSafetyDetailDao;
import com.tianque.domain.vo.SearchEmphasisSafetyDetail;
import com.tianque.domain.workingRecord.EmphasisSafetyDetail;

@Repository("searchEmphasisSafetyDetailDao")
public class SearchEmphasisSafetyDetailDaoImpl extends AbstractBaseDao implements
		SearchEmphasisSafetyDetailDao {

	@Override
	public PageInfo<EmphasisSafetyDetail> searchEmphasisSafetyDetail(
			SearchEmphasisSafetyDetail searchEmphasisSafetyDetail, Integer pageNum,
			Integer pageSize, String sortField, String order, String allDailyDirectoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seachReportTimeStart", searchEmphasisSafetyDetail.getSeachReportTimeStart());
		map.put("seachReportTimeEnd", searchEmphasisSafetyDetail.getSeachReportTimeEnd());
		map.put("dailyDirectoryId", allDailyDirectoryId);
		map.put("orgId", searchEmphasisSafetyDetail.getOrgId());
		map.put("order", order);
		map.put("sortField", sortField);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchEmphasisSafetyDetail.countEmphasisSafetyDetail", map);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<EmphasisSafetyDetail> list = getSqlMapClientTemplate().queryForList(
				"searchEmphasisSafetyDetail.searchEmphasisSafetyDetailByDate", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<EmphasisSafetyDetail> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<EmphasisSafetyDetail> pageInfo = new PageInfo<EmphasisSafetyDetail>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

}
