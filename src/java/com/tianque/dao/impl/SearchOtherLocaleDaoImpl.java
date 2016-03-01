package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SearchOtherLocaleDao;
import com.tianque.domain.OtherLocale;
import com.tianque.domain.vo.SearchOtherLocaleVo;

@Repository("searchOtherLocaleDao")
public class SearchOtherLocaleDaoImpl extends AbstractBaseDao implements
		SearchOtherLocaleDao {

	@SuppressWarnings("unchecked")
	@Override
	public PageInfo<OtherLocale> searchOtherLocale(
			SearchOtherLocaleVo searchOtherLocaleVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		if (null == searchOtherLocaleVo) {
			return emptyOtherLocalePage(pageSize);
		}
		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("name", searchOtherLocaleVo.getName()); map.put("address",
		 * searchOtherLocaleVo.getAddress()); map.put("typeId",
		 * searchOtherLocaleVo.getTypeId()); map.put("contactPerson",
		 * searchOtherLocaleVo.getContactPerson()); map.put("telephone",
		 * searchOtherLocaleVo.getTelephone()); map.put("mobileNumber",
		 * searchOtherLocaleVo.getMobileNumber()); map.put("orgInternalCode",
		 * searchOtherLocaleVo.getOrgInternalCode()); map.put("sortField",
		 * sidx); map.put("order", sord); map.put("isEmphasis",
		 * searchOtherLocaleVo.getIsEmphasis());
		 */

		searchOtherLocaleVo.setSortField(sidx);
		searchOtherLocaleVo.setOrder(sord);
		PageInfo<OtherLocale> pageInfo = new PageInfo<OtherLocale>();
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"searchOtherLocale.countOtherLocales", searchOtherLocaleVo);

		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;

		if (countNum > 0) {
			List<OtherLocale> list = getSqlMapClientTemplate().queryForList(
					"searchOtherLocale.searchOtherLocales",
					searchOtherLocaleVo, (pageNum - 1) * pageSize, pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<OtherLocale>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<OtherLocale> emptyOtherLocalePage(int pageSize) {
		PageInfo<OtherLocale> pageInfo = new PageInfo<OtherLocale>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<OtherLocale>());
		return pageInfo;
	}

	@Override
	public List<OtherLocale> searchOtherLocaleForExport(
			SearchOtherLocaleVo searchOtherLocaleVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("name", searchOtherLocaleVo.getName()); map.put("address",
		 * searchOtherLocaleVo.getAddress()); map.put("typeId",
		 * searchOtherLocaleVo.getTypeId()); map.put("contactPerson",
		 * searchOtherLocaleVo.getContactPerson()); map.put("telephone",
		 * searchOtherLocaleVo.getTelephone()); map.put("mobileNumber",
		 * searchOtherLocaleVo.getMobileNumber()); map.put("orgInternalCode",
		 * searchOtherLocaleVo.getOrgInternalCode()); map.put("sortField",
		 * sidx); map.put("order", sord); map.put("isEmphasis",
		 * searchOtherLocaleVo.getIsEmphasis()); map.put("attentionExtentId",
		 * searchOtherLocaleVo.getAttentionExtentId());
		 */
		searchOtherLocaleVo.setSortField(sidx);
		searchOtherLocaleVo.setOrder(sord);
		if (pageNum == null) {
			return getSqlMapClientTemplate()
					.queryForList("searchOtherLocale.searchOtherLocales",
							searchOtherLocaleVo);
		} else {
			return getSqlMapClientTemplate().queryForList(
					"searchOtherLocale.searchOtherLocales",
					searchOtherLocaleVo, (pageNum - 1) * pageSize, pageSize);
		}
	}

	public List findOtherLocaleNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode) {
		Map map = new HashMap();
		map.put("name", name);
		map.put("orgInternalCode", orgInternalCode);
		return getSqlMapClientTemplate()
				.queryForList(
						"searchOtherLocale.findOtherLocalesNameAndPinyinAndOrgInternalCode",
						map);
	}

	@Override
	public Integer getCount(SearchOtherLocaleVo searchOtherLocaleVo) {
		// TODO Auto-generated method stub
		if (null == searchOtherLocaleVo) {
			return 0;
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"searchOtherLocale.countOtherLocales", searchOtherLocaleVo);
	}
}
