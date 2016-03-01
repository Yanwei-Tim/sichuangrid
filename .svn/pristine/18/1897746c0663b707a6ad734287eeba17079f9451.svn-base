package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchSmsSendBoxDao;
import com.tianque.domain.SmsSendBox;
import com.tianque.domain.vo.SearchSmsSendBoxVo;

@Repository("searchSmsSendBoxDao")
public class SearchSmsSendBoxDaoImpl extends AbstractBaseDao implements
		SearchSmsSendBoxDao {

	@Override
	public PageInfo<SmsSendBox> searchSmsSendBox(SearchSmsSendBoxVo condition,
			int pageNum, int pageSize, String sortField, String order) {
		if (condition == null) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = getConditionMap(condition, sortField, order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchSmsSendBox.countSearchSmsSendBoxs", map);
		@SuppressWarnings("unchecked")
		List<SmsSendBox> list = getSqlMapClientTemplate().queryForList(
				"searchSmsSendBox.searchSmsSendBoxs", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	public PageInfo<SmsSendBox> searchSmsSendBox(
			SearchSmsSendBoxVo searchSmsSendBoxVo, String date, int pageNum,
			int pageSize, String sortField, String order) {
		if (searchSmsSendBoxVo == null) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = getConditionMap(searchSmsSendBoxVo,
				sortField, order);
		map.put("date", date);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchSmsSendBox.countSearchSmsSendBoxsByDate", map);
		@SuppressWarnings("unchecked")
		List<SmsSendBox> list = getSqlMapClientTemplate().queryForList(
				"searchSmsSendBox.searchSmsSendBoxsByDate", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<SmsSendBox> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List<SmsSendBox> list) {
		PageInfo<SmsSendBox> pageInfo = new PageInfo<SmsSendBox>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<SmsSendBox> emptyPage(int pageSize) {
		PageInfo<SmsSendBox> pageInfo = new PageInfo<SmsSendBox>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SmsSendBox>());
		return pageInfo;
	}

	private Map<String, Object> getConditionMap(SearchSmsSendBoxVo condition,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", condition.getUserId());
		map.put("receiver", condition.getReceiver());
		map.put("startCreateTime", condition.getStartCreateTime());
		map.put("endCreateTime", condition.getEndCreateTime());
		if (!StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		return map;
	}

}
