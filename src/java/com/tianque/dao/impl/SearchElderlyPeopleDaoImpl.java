package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.elderlyPeople.dao.SearchElderlyPeopleDao;
import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchElderlyPeopleVo;

@Repository("searchElderlyPeopleDao")
public class SearchElderlyPeopleDaoImpl extends AbstractBaseDao implements
		SearchElderlyPeopleDao {

	@Override
	public PageInfo<ElderlyPeople> searchElderlyPeople(
			SearchElderlyPeopleVo elderlyPeopleCondition, int pageNum,
			int pageSize, String sortField, String order) {
		if (elderlyPeopleCondition == null
				|| !StringUtil.isStringAvaliable(elderlyPeopleCondition
						.getShardCode()))
			return emptyElderlyPeoplePage(pageSize);

		PageInfo<ElderlyPeople> pageInfo = new PageInfo<ElderlyPeople>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchElderlyPeople.countSearchElderlyPeople",
				getConditionMap(elderlyPeopleCondition, sortField, order));
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		// pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<ElderlyPeople> list = getSqlMapClientTemplate().queryForList(
					"searchElderlyPeople.searchElderlyPeople",
					getConditionMap(elderlyPeopleCondition, sortField, order),
					(pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<ElderlyPeople>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);

		return pageInfo;
	}

	@Override
	public List<ElderlyPeople> searchElderlyPeopleForExport(
			SearchElderlyPeopleVo elderlyPeopleCondition, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		elderlyPeopleCondition.setSortField(sortField);
		elderlyPeopleCondition.setOrder(order);
		if (pageNum == null) {
			return getSqlMapClientTemplate().queryForList(
					"searchElderlyPeople.searchElderlyPeople",
					elderlyPeopleCondition);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchElderlyPeople.searchElderlyPeople",
					elderlyPeopleCondition, (pageNum - 1) * pageSize, pageSize);
		}
	}

	private SearchElderlyPeopleVo getConditionMap(
			SearchElderlyPeopleVo elderlyPeopleCondition, String sortField,
			String order) {
		elderlyPeopleCondition.setSortField(sortField);
		elderlyPeopleCondition.setOrder(order);
		return elderlyPeopleCondition;
	}

	private PageInfo<ElderlyPeople> emptyElderlyPeoplePage(int pageSize) {
		PageInfo<ElderlyPeople> pageInfo = new PageInfo<ElderlyPeople>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<ElderlyPeople>());
		return pageInfo;
	}

	@Override
	public Integer getCount(SearchElderlyPeopleVo elderlyPeopleCondition) {
		if (elderlyPeopleCondition == null
				|| !StringUtil.isStringAvaliable(elderlyPeopleCondition
						.getShardCode())){
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchElderlyPeople.countSearchElderlyPeople",
				getConditionMap(elderlyPeopleCondition, null, null));
	}
}
