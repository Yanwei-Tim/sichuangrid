package com.tianque.baseInfo.newSocietyOrganizations.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.baseInfo.newSocietyOrganizations.domain.SearchNewSocietyOrganizationsVo;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;

@Repository("searchNewSocietyOrganizationsDao")
public class SearchNewSocietyOrganizationsDaoImpl extends AbstractBaseDao
		implements SearchNewSocietyOrganizationsDao {

	@Override
	public PageInfo<NewSocietyOrganizations> fastSearchNewSocietyOrganizations(
			SearchNewSocietyOrganizationsVo searchNewSocietyOrganizationsVo,
			int pageNum, int pageSize, String sortField, String order) {
		if (searchNewSocietyOrganizationsVo == null) {
			return emptyPage(pageSize);
		}
		// 排序字段非空
		if (StringUtil.isStringAvaliable(sortField)) {
			searchNewSocietyOrganizationsVo.setSortField(sortField);
		}
		searchNewSocietyOrganizationsVo.setOrder(order);
		Integer countNum = 0;
		countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchNewSocietyOrganizations.countFastSearchNewSocietyOrganizations",
						searchNewSocietyOrganizationsVo);
		List<NewSocietyOrganizations> list = getSqlMapClientTemplate()
				.queryForList(
						"searchNewSocietyOrganizations.fastSearchNewSocietyOrganizations",
						searchNewSocietyOrganizationsVo,
						(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<NewSocietyOrganizations> emptyPage(int pageSize) {
		PageInfo<NewSocietyOrganizations> pageInfo = new PageInfo<NewSocietyOrganizations>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<NewSocietyOrganizations>());
		return pageInfo;
	}

	/*
	 * private Map getFastConditionMap( SearchNewSocietyOrganizationsVo
	 * searchNewSocietyOrganizationsVo, String sortField, String order) {
	 * Map<String, Object> map = new HashMap<String, Object>(); if
	 * (searchNewSocietyOrganizationsVo == null) { return map; }
	 * map.put("orgInternalCode",
	 * searchNewSocietyOrganizationsVo.getOrgInternalCode());
	 * map.put("unitName", searchNewSocietyOrganizationsVo.getUnitName());
	 * map.put("isEmphasis", searchNewSocietyOrganizationsVo.getIsEmphasis());
	 * map.put("objectType", "NEWSOCIETYORGANIZATIONS"); // 排序字段非空 if
	 * (StringUtil.isStringAvaliable(sortField)) { map.put("sortField",
	 * sortField); } map.put("order", order); return map; }
	 */

	private PageInfo<NewSocietyOrganizations> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<NewSocietyOrganizations> pageInfo = new PageInfo<NewSocietyOrganizations>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	public PageInfo<NewSocietyOrganizations> findNewSocietyOrganizationsByQueryCondition(
			SearchNewSocietyOrganizationsVo condition, int pageNum,
			int pageSize, String sortField, String order) {
		condition.setSortField(sortField);
		condition.setOrder(order);
		Integer countNum = (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchNewSocietyOrganizations.countSearchNewSocietyOrganizations",
						condition);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<Druggy> list = getSqlMapClientTemplate().queryForList(
				"searchNewSocietyOrganizations.searchNewSocietyOrganizations",
				condition, (pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public List<NewSocietyOrganizations> searchNewSocietyOrganizationsForExport(
			SearchNewSocietyOrganizationsVo condition, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		if (condition != null) {
			condition.setSortField(sortField);
			condition.setOrder(order);
		}
		if (pageNum == null) {
			return getSqlMapClientTemplate()
					.queryForList(
							"searchNewSocietyOrganizations.searchNewSocietyOrganizations",
							condition);
		} else {
			return getSqlMapClientTemplate()
					.queryForList(
							"searchNewSocietyOrganizations.searchNewSocietyOrganizations",
							condition, (pageNum - 1) * pageSize, pageSize);
		}
	}

	@Override
	public Integer getCount(
			SearchNewSocietyOrganizationsVo searchNewSocietyOrganizationsVo) {
		// TODO Auto-generated method stub
		if (null == searchNewSocietyOrganizationsVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate()
				.queryForObject(
						"searchNewSocietyOrganizations.countFastSearchNewSocietyOrganizations",
						searchNewSocietyOrganizationsVo);
	}
}
