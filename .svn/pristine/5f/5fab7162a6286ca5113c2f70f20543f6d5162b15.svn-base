package com.tianque.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.LowerPartyMemberInfoDao;
import com.tianque.domain.PartyMemberInfo;
import com.tianque.domain.vo.SearchPartyMemberInfoVo;
import com.tianque.service.LowerPartyMemberInfoService;

@Service("lowerPartyMemberInfoServiceImpl")
public class LowerPartyMemberInfoServiceImpl extends LogableService implements
		LowerPartyMemberInfoService {

	@Autowired
	private LowerPartyMemberInfoDao lowerPartyMemberInfoDao;

	@Override
	public PartyMemberInfo getPartyMemberInfoById(Long id) {
		return lowerPartyMemberInfoDao.getPartyMemberInfoById(id);
	}

	@Override
	public PageInfo<PartyMemberInfo> findPartyMemberInfoForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis) {
		return lowerPartyMemberInfoDao
				.findPartyMemberInfoForPageByOrgInternalCode(orgInternalCode,
						pageNum, pageSize, sidx, sord, isEmphasis);
	}

	@Override
	public PageInfo<PartyMemberInfo> searchPartyMemberInfos(Integer pageNum,
			Integer pageSize, SearchPartyMemberInfoVo searchPartyMemberInfoVo) {
		return lowerPartyMemberInfoDao.searchPartyMemberInfos(pageNum,
				pageSize, searchPartyMemberInfoVo);
	}

	@Override
	public List<PartyMemberInfo> searchAllPartyMemberInfos(
			SearchPartyMemberInfoVo searchPartyMemberInfoVo) {

		return lowerPartyMemberInfoDao
				.searchAllPartyMemberInfos(searchPartyMemberInfoVo);
	}

	@Override
	public Integer getCount(SearchPartyMemberInfoVo searchPartyMemberInfoVo) {
		return lowerPartyMemberInfoDao.getCount(searchPartyMemberInfoVo);
	}

}
