package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.LowerPartyMemberInfoDao;
import com.tianque.domain.PartyMemberInfo;
import com.tianque.domain.vo.SearchPartyMemberInfoVo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("lowerPartyMemberInfoDaoImpl")
public class LowerPartyMemberInfoDaoImpl extends AbstractBaseDao implements
		LowerPartyMemberInfoDao {

	@Override
	public PartyMemberInfo getPartyMemberInfoById(Long id) {
		if (null == id || id < 1L) {
			throw new BusinessValidationException("查找方法中id为空,请检查...");
		}
		return (PartyMemberInfo) this.getSqlMapClientTemplate().queryForObject(
				"partyMemberInfo.getPartyMemberInfoById", id);
	}

	@Override
	public PageInfo<PartyMemberInfo> findPartyMemberInfoForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgInternalCode", orgInternalCode);
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
	public List<PartyMemberInfo> searchAllPartyMemberInfos(
			SearchPartyMemberInfoVo searchPartyMemberInfoVo) {
		return this.getSqlMapClientTemplate().queryForList(
				"partyMemberInfo.searchPartyMemberInfo",
				searchPartyMemberInfoVo);

	}

	@Override
	public Integer getCount(SearchPartyMemberInfoVo searchPartyMemberInfoVo) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(
				"partyMemberInfo.countSearchPartyMemberInfo",
				searchPartyMemberInfoVo);
	}

}
