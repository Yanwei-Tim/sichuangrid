package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.PartyMemberInfoDao;
import com.tianque.domain.PartyMemberInfo;
import com.tianque.domain.vo.SearchPartyMemberInfoVo;

@Repository("partyMemberInfoDao")
public class PartyMemberInfoDaoImpl extends AbstractBaseDao implements
		PartyMemberInfoDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyMemberInfoDao#addPartyMemberInfo(com.tianque.domain
	 * .PartyMemberInfo)
	 */
	@Override
	public PartyMemberInfo addPartyMemberInfo(PartyMemberInfo partyMemberInfo) {
		Long id = (Long) this.getSqlMapClientTemplate().insert(
				"partyMemberInfo.addPartyMemberInfo", partyMemberInfo);
		return getPartyMemberInfoById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyMemberInfoDao#getPartyMemberInfoByIdAndIdCardAndOrgId
	 * (java.lang.Long, java.util.List, java.lang.Long)
	 */
	@Override
	public PartyMemberInfo getPartyMemberInfoByIdAndIdCardAndOrgId(Long id,
			List<String> idCardNoList, Long orgId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("id", id);
		query.put("orgId", orgId);
		query.put("idCardNoList", idCardNoList);
		return (PartyMemberInfo) this.getSqlMapClientTemplate().queryForObject(
				"partyMemberInfo.getPartyMemberInfoByIdAndIdCard", query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyMemberInfoDao#getPartyMemberInfoById(java.lang.Long)
	 */
	@Override
	public PartyMemberInfo getPartyMemberInfoById(Long id) {
		return (PartyMemberInfo) this.getSqlMapClientTemplate().queryForObject(
				"partyMemberInfo.getPartyMemberInfoById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyMemberInfoDao#getPartyMemberInfoByPartyOrgId(java
	 * .lang.Long)
	 */
	@Override
	public List<PartyMemberInfo> getPartyMemberInfoByPartyOrgId(Long partyOrgId) {
		return this.getSqlMapClientTemplate().queryForList(
				"partyMemberInfo.getPartyMemberInfoByPartyOrgId", partyOrgId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyMemberInfoDao#updatePartyMemberInfo(com.tianque.
	 * domain.PartyMemberInfo)
	 */
	@Override
	public PartyMemberInfo updatePartyMemberInfo(PartyMemberInfo partyMemberInfo) {
		this.getSqlMapClientTemplate().update(
				"partyMemberInfo.updatePartyMemberInfo", partyMemberInfo);
		return this.getPartyMemberInfoById(partyMemberInfo.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyMemberInfoDao#deletePartyMemberInfoById(java.lang
	 * .Long)
	 */
	@Override
	public void deletePartyMemberInfoById(Long id) {
		this.getSqlMapClientTemplate().delete(
				"partyMemberInfo.deletePartyMemberInfoById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyMemberInfoDao#getPartyMemberInfoByOrgIdAndCardNo
	 * (java.util.List, java.lang.Long)
	 */
	@Override
	public PartyMemberInfo getPartyMemberInfoByOrgIdAndCardNo(
			List<String> idCardNoList, Long orgId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("orgId", orgId);
		query.put("idCardNoList", idCardNoList);
		PartyMemberInfo partyMemberInfo = (PartyMemberInfo) getSqlMapClientTemplate()
				.queryForObject(
						"partyMemberInfo.getPartyMemberInfoByOrgIdAndCardNo",
						query);
		return partyMemberInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyMemberInfoDao#findPartyMemberInfoForPageByOrgId(
	 * java.lang.Long, java.lang.Integer, java.lang.Integer, java.lang.String,
	 * java.lang.String, java.lang.Long)
	 */
	@Override
	public PageInfo<PartyMemberInfo> findPartyMemberInfoForPageByOrgId(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("isEmphasis", isEmphasis);
		Integer countNum = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("partyMemberInfo.countPartyMemberInfos", map);
		if (!StringUtils.isEmpty(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<PartyMemberInfo> list = this.getSqlMapClientTemplate()
				.queryForList("partyMemberInfo.findPartyMemberInfos", map,
						(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyMemberInfoDao#searchPartyMemberInfos(java.lang.Integer
	 * , java.lang.Integer, com.tianque.domain.vo.SearchPartyMemberInfoVo)
	 */
	@Override
	public PageInfo<PartyMemberInfo> searchPartyMemberInfos(Integer pageNum,
			Integer pageSize, SearchPartyMemberInfoVo searchPartyMemberInfoVo) {
		if (null == searchPartyMemberInfoVo)
			return emptyPartyMemberInfoPage(pageSize);
		PageInfo<PartyMemberInfo> pageInfo = new PageInfo<PartyMemberInfo>();
		Integer countNum = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("partyMemberInfo.countSearchPartyMemberInfo",
						searchPartyMemberInfoVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<PartyMemberInfo> list = this.getSqlMapClientTemplate()
					.queryForList("partyMemberInfo.searchPartyMemberInfo",
							searchPartyMemberInfoVo, (pageNum - 1) * pageSize,
							pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<PartyMemberInfo>());
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
	 * com.tianque.dao.PartyMemberInfoDao#searchAllPartyMemberInfos(com.tianque
	 * .domain.vo. SearchPartyMemberInfoVo)
	 */
	@Override
	public List<PartyMemberInfo> searchAllPartyMemberInfos(
			SearchPartyMemberInfoVo searchPartyMemberInfoVo) {
		return getSqlMapClientTemplate().queryForList(
				"partyMemberInfo.searchPartyMemberInfo",
				searchPartyMemberInfoVo);
	}

	private PageInfo<PartyMemberInfo> emptyPartyMemberInfoPage(int pageSize) {
		PageInfo<PartyMemberInfo> pageInfo = new PageInfo<PartyMemberInfo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PartyMemberInfo>());
		return pageInfo;
	}

	private PageInfo<PartyMemberInfo> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<PartyMemberInfo> pageInfo = new PageInfo<PartyMemberInfo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer getCount(SearchPartyMemberInfoVo searchPartyMemberInfoVo) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"partyMemberInfo.countSearchPartyMemberInfo",
				searchPartyMemberInfoVo);
	}
}
