package com.tianque.peopleLog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.SearchPeopleLogVo;

@Repository("searchPeopleLogDao")
public class SearchPeopleLogDaoImpl extends AbstractBaseDao implements SearchPeopleLogDao {

	@Override
	public PageInfo<PeopleLog> findPeopleLogByQueryCondition(SearchPeopleLogVo searchPeopleLogVo,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		searchPeopleLogVo.setSortField(sidx);
		searchPeopleLogVo.setOrder(sord);
		if (searchPeopleLogVo.getCommentLog() != null
				&& "".equals(searchPeopleLogVo.getCommentLog().get("comments"))
				&& "".equals(searchPeopleLogVo.getCommentLog().get("commentUser"))) {
			searchPeopleLogVo.setCommentLog(null);
		}
		List<PeopleLog> list = getSqlMapClientTemplate().queryForList("searchPeopleLog.search",
				searchPeopleLogVo, (pageNum - 1) * pageSize, pageSize);
		Integer countNum =(Integer) getSqlMapClientTemplate().queryForObject("searchPeopleLog.countSearch",
				searchPeopleLogVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<PeopleLog> createPageInfo(int pageNum, int pageSize, Integer countNum,
			List list) {
		PageInfo<PeopleLog> pageInfo = new PageInfo<PeopleLog>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

}
