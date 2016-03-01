package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchDisputEsexamineDao;
import com.tianque.domain.vo.SearchDisputEsexamine;
import com.tianque.domain.workingRecord.DisputEsexamine;

@Repository("searchDisputEsexamineDao")
public class SearchDisputEsexamineDaoImpl extends AbstractBaseDao implements
		SearchDisputEsexamineDao {

	@Override
	public PageInfo<DisputEsexamine> searchDisputEsexamine(
			SearchDisputEsexamine searchDisputEsexamine, Integer pageNum, Integer pageSize,
			String sortField, String order, String allDailyDirectoryId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seachReportTimeStart", searchDisputEsexamine.getSeachReportTimeStart());
		map.put("seachReportTimeEnd", searchDisputEsexamine.getSeachReportTimeEnd());
		map.put("dailyDirectoryId", allDailyDirectoryId);
		map.put("orgId", searchDisputEsexamine.getOrgId());
		map.put("order", order);
		map.put("sortField", sortField);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchDisputEsexamine.countDisputEsexamine", map);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<DisputEsexamine> list = getSqlMapClientTemplate().queryForList(
				"searchDisputEsexamine.searchDisputEsexamineByDate", map, (pageNum - 1) * pageSize,
				pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<DisputEsexamine> createPageInfo(int pageNum, int pageSize, Integer countNum,
			List list) {
		PageInfo<DisputEsexamine> pageInfo = new PageInfo<DisputEsexamine>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

}
