package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.PartyOrgInfoDao;
import com.tianque.domain.PartyMemberInfo;
import com.tianque.domain.PartyOrgInfo;
import com.tianque.domain.vo.SearchPartyOrgInfoVo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("partyOrgInfoDao")
public class PartyOrgInfoDaoImpl extends AbstractBaseDao implements
		PartyOrgInfoDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.PartyOrgInfoDao#addPartyOrgInfo(com.tianque.domain.
	 * PartyOrgInfo)
	 */
	@Override
	public void addPartyOrgInfo(PartyOrgInfo partyOrgInfo) {
		this.getSqlMapClientTemplate().insert("partyOrgInfo.addPartyOrgInfo",
				partyOrgInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.PartyOrgInfoDao#getPartyOrgInfoById(java.lang.Long)
	 */
	@Override
	public PartyOrgInfo getPartyOrgInfoById(Long id) {
		if (null == id || id < 1L) {
			throw new BusinessValidationException("查找方法中id为空,请检查...");
		}
		return (PartyOrgInfo) this.getSqlMapClientTemplate().queryForObject(
				"partyOrgInfo.getPartyOrgInfoById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgInfoDao#getPartyOrgInfoByName(java.lang.String)
	 */
	@Override
	public PartyOrgInfo getPartyOrgInfoByNameAndOrgId(String partyBranchName,
			Long orgId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("partyBranchName", partyBranchName);
		query.put("orgId", orgId);
		return (PartyOrgInfo) this.getSqlMapClientTemplate().queryForObject(
				"partyOrgInfo.getPartyOrgInfoByName", query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgInfoDao#getPartyOrgInfoByIdAndOrgId(java.lang
	 * .Long, java.lang.Long)
	 */
	@Override
	public PartyOrgInfo getPartyOrgInfoByIdAndOrgId(Long orgId, Long id) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("id", id);
		PartyOrgInfo partyOrgInfo = (PartyOrgInfo) getSqlMapClientTemplate()
				.queryForObject("partyOrgInfo.getPartyOrgInfoByidAndOrgId",
						query);
		return partyOrgInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgInfoDao#updatePartyOrgInfo(com.tianque.domain
	 * .PartyOrgInfo)
	 */
	@Override
	public void updatePartyOrgInfo(PartyOrgInfo partyOrgInfo) {
		this.getSqlMapClientTemplate().update(
				"partyOrgInfo.updatePartyOrgInfo", partyOrgInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgInfoDao#findPartyOrgInfoForPageByOrgInternalCode
	 * (java.lang.String, java.lang.Integer, java.lang.Integer,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public PageInfo<PartyOrgInfo> findPartyOrgInfoForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("orgInternalCode", orgInternalCode);
		Integer countNum = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("partyOrgInfo.countPartyOrgInfos", map);
		if (!StringUtils.isEmpty(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<PartyMemberInfo> list = this.getSqlMapClientTemplate()
				.queryForList("partyOrgInfo.findPartyOrgInfos", map,
						(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgInfoDao#searchPartyOrgInfos(java.lang.Integer,
	 * java.lang.Integer, com.tianque.domain.vo.SearchPartyOrgInfoVo)
	 */
	@Override
	public PageInfo<PartyOrgInfo> searchPartyOrgInfos(Integer pageNum,
			Integer pageSize, SearchPartyOrgInfoVo searchPartyOrgInfoVo) {
		if (null == searchPartyOrgInfoVo)
			return emptyPartyMemberInfoPage(pageSize);
		PageInfo<PartyOrgInfo> pageInfo = new PageInfo<PartyOrgInfo>();
		Integer countNum = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("partyOrgInfo.countSearchPartyOrgInfo",
						searchPartyOrgInfoVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<PartyOrgInfo> list = this.getSqlMapClientTemplate()
					.queryForList("partyOrgInfo.searchPartyOrgInfo",
							searchPartyOrgInfoVo, (pageNum - 1) * pageSize,
							pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<PartyOrgInfo>());
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
	 * com.tianque.dao.PartyOrgInfoDao#searchAllPartyOrgInfos(com.tianque.domain
	 * .vo.SearchPartyOrgInfoVo )
	 */
	@Override
	public List<PartyOrgInfo> searchAllPartyOrgInfos(
			SearchPartyOrgInfoVo searchPartyOrgInfoVo) {
		return getSqlMapClientTemplate().queryForList(
				"partyOrgInfo.searchPartyOrgInfo", searchPartyOrgInfoVo);
	}

	private PageInfo<PartyOrgInfo> emptyPartyMemberInfoPage(int pageSize) {
		PageInfo<PartyOrgInfo> pageInfo = new PageInfo<PartyOrgInfo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PartyOrgInfo>());
		return pageInfo;
	}

	private PageInfo<PartyOrgInfo> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<PartyOrgInfo> pageInfo = new PageInfo<PartyOrgInfo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer getCount(SearchPartyOrgInfoVo searchPartyOrgInfoVo) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"partyOrgInfo.countSearchPartyOrgInfo", searchPartyOrgInfoVo);
	}
}
