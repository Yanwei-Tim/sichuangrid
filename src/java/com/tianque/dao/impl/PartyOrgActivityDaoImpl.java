package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.optimalObject.domain.OptimalObject;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.PartyOrgActivityDao;
import com.tianque.domain.PartyOrgActivity;
import com.tianque.domain.vo.SearchPartyOrgActivityVo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("partyOrgActivityDao")
public class PartyOrgActivityDaoImpl extends AbstractBaseDao implements
		PartyOrgActivityDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.addPartyOrgActivity#addOptimalObject(com.tianque.domain
	 * .PartyOrgActivity)
	 */
	@Override
	public PartyOrgActivity addPartyOrgActivity(
			PartyOrgActivity partyOrgActivity) {
		Long id = (Long) this.getSqlMapClientTemplate().insert(
				"partyOrgActivity.addPartyOrgActivity", partyOrgActivity);
		return getPartyOrgActivityById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgActivityDao#getPartyOrgActivityById(java.lang
	 * .Long)
	 */
	@Override
	public PartyOrgActivity getPartyOrgActivityById(Long id) {
		if (null == id || id < 1L) {
			throw new BusinessValidationException("查找方法中id为空,请检查...");
		}
		return (PartyOrgActivity) this.getSqlMapClientTemplate()
				.queryForObject("partyOrgActivity.getPartyOrgActivityById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgActivityDao#getPartyOrgActivityByPartyOrgId(java
	 * .lang.Long)
	 */
	@Override
	public List<PartyOrgActivity> getPartyOrgActivityByPartyOrgId(
			Long partyOrgId) {
		return this.getSqlMapClientTemplate().queryForList(
				"partyOrgActivity.getPartyOrgActivityByPartyOrgId", partyOrgId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgActivityDao#updatePartyOrgActivity(com.tianque
	 * .domain.PartyOrgActivity )
	 */
	@Override
	public PartyOrgActivity updatePartyOrgActivity(
			PartyOrgActivity partyOrgActivity) {
		this.getSqlMapClientTemplate().update(
				"partyOrgActivity.updatePartyOrgActivity", partyOrgActivity);
		return this.getPartyOrgActivityById(partyOrgActivity.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgActivityDao#deletePartyOrgActivityById(java.lang
	 * .Long)
	 */
	@Override
	public void deletePartyOrgActivityById(Long id) {
		this.getSqlMapClientTemplate().delete(
				"partyOrgActivity.deletePartyOrgActivityById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgActivityDao#findPartyOrgActivityForPageByOrgId
	 * (java.lang.Long, java.lang.Integer, java.lang.Integer, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public PageInfo<PartyOrgActivity> findPartyOrgActivityForPageByOrgId(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, String yearActivity) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("yearActivity", yearActivity);
		Integer countNum = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("partyOrgActivity.countPartyOrgActivitys", map);
		if (!StringUtils.isEmpty(sidx)) {
			map.put("sortField", sidx);
			map.put("order", sord);
		} else {
			map.put("sortField", "activityDate");
			map.put("order", "desc");
		}
		List<OptimalObject> list = this.getSqlMapClientTemplate().queryForList(
				"partyOrgActivity.findPartyOrgActivitys", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgActivityDao#searchPartyOrgActivitys(java.lang
	 * .Integer, java.lang.Integer,
	 * com.tianque.domain.vo.SearchPartyOrgActivityVo)
	 */
	@Override
	public PageInfo<PartyOrgActivity> searchPartyOrgActivitys(Integer pageNum,
			Integer pageSize, SearchPartyOrgActivityVo searchPartyOrgActivityVo) {
		if (null == searchPartyOrgActivityVo)
			return emptyPartyOrgActivityPage(pageSize);
		PageInfo<PartyOrgActivity> pageInfo = new PageInfo<PartyOrgActivity>();
		Integer countNum = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("partyOrgActivity.countSearchPartyOrgActivity",
						searchPartyOrgActivityVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<PartyOrgActivity> list = this.getSqlMapClientTemplate()
					.queryForList("partyOrgActivity.searchPartyOrgActivity",
							searchPartyOrgActivityVo, (pageNum - 1) * pageSize,
							pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<PartyOrgActivity>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgActivityDao#searchAllPartyOrgActivitys(com.tianque
	 * .domain.vo. SearchPartyOrgActivityVo)
	 */
	@Override
	public List<PartyOrgActivity> searchAllPartyOrgActivitys(
			SearchPartyOrgActivityVo searchPartyOrgActivityVo) {
		return getSqlMapClientTemplate().queryForList(
				"partyOrgActivity.searchPartyOrgActivity",
				searchPartyOrgActivityVo);
	}

	private PageInfo<PartyOrgActivity> emptyPartyOrgActivityPage(int pageSize) {
		PageInfo<PartyOrgActivity> pageInfo = new PageInfo<PartyOrgActivity>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PartyOrgActivity>());
		return pageInfo;
	}

	private PageInfo<PartyOrgActivity> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List list) {
		PageInfo<PartyOrgActivity> pageInfo = new PageInfo<PartyOrgActivity>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer getCount(SearchPartyOrgActivityVo searchPartyOrgActivityVo) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"partyOrgActivity.countSearchPartyOrgActivity",
				searchPartyOrgActivityVo);
	}
}
