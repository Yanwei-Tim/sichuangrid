package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchSmsReceivedBoxDao;
import com.tianque.domain.SmsReceivedBox;
import com.tianque.domain.vo.SearchSmsReceivedBoxVo;

@Repository("searchSmsReceivedBoxDao")
public class SearchSmsReceivedBoxDaoImpl extends AbstractBaseDao implements
		SearchSmsReceivedBoxDao {

	@Override
	public PageInfo<SmsReceivedBox> searchSmsReceivedBox(
			SearchSmsReceivedBoxVo condition, int pageNum, int pageSize,
			String sortField, String order) {
		if (condition == null) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = getConditionMap(condition, sortField, order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchSmsReceivedBox.countSearchSmsReceivedBoxs", map);
		@SuppressWarnings("unchecked")
		List<SmsReceivedBox> list = getSqlMapClientTemplate().queryForList(
				"searchSmsReceivedBox.searchSmsReceivedBoxs", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public PageInfo<SmsReceivedBox> searchSmsReceivedBox(
			SearchSmsReceivedBoxVo condition, String date, int pageNum,
			int pageSize, String sortField, String order) {
		if (condition == null) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = getConditionMap(condition, sortField, order);
		map.put("date", date);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchSmsReceivedBox.countSearchSmsReceivedBoxsByDate", map);
		@SuppressWarnings("unchecked")
		List<SmsReceivedBox> list = getSqlMapClientTemplate().queryForList(
				"searchSmsReceivedBox.searchSmsReceivedBoxsByDate", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<SmsReceivedBox> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List<SmsReceivedBox> list) {
		PageInfo<SmsReceivedBox> pageInfo = new PageInfo<SmsReceivedBox>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<SmsReceivedBox> emptyPage(int pageSize) {
		PageInfo<SmsReceivedBox> pageInfo = new PageInfo<SmsReceivedBox>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SmsReceivedBox>());
		return pageInfo;
	}

	private Map<String, Object> getConditionMap(
			SearchSmsReceivedBoxVo condition, String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", condition.getOrgId());
		map.put("sourceMobile", condition.getSourceMobile());
		map.put("startReceiverTime", condition.getStartReceiverTime());
		map.put("endReceiverTime", condition.getEndReceiverTime());
		if (!StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		return map;
	}

}
