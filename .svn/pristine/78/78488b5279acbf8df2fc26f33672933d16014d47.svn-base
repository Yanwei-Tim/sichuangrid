package com.tianque.partyBuilding.organizations.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.organizations.dao.RegionalPartyMemberDao;
import com.tianque.partyBuilding.organizations.domain.RegionalPartyMember;
import com.tianque.partyBuilding.organizations.domain.vo.RegionalPartyMemberVo;
import com.tianque.util.ParametersConvertUtil;

/**
 * 区域党委成员:dao实现
 * */
@Repository("regionalPartyMemberDao")
public class RegionalPartyMemberDaoImpl extends
		BaseDaoImpl<RegionalPartyMember, RegionalPartyMemberVo> implements
		RegionalPartyMemberDao {

	@Override
	public PageInfo<RegionalPartyMember> findRegionalPartyMemberPagerBySearchVo(
			RegionalPartyMemberVo regionalPartyMemberVo, Integer pageNum,
			Integer pageSize, String sortField, String sord) {

		PageInfo<RegionalPartyMember> pageInfo = new PageInfo<RegionalPartyMember>();
		Integer countNum = (Integer) this.getSqlMapClientTemplate()
				.queryForObject("regionalPartyMember.countRegionalPartyMember",
						regionalPartyMemberVo);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			List<RegionalPartyMember> list = this.getSqlMapClientTemplate()
					.queryForList(
							"regionalPartyMember.findAllRegionalPartyMember",
							regionalPartyMemberVo, (pageNum - 1) * pageSize,
							pageSize);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<RegionalPartyMember>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public Integer countRegionalPartyMemberByOrgIdOrOrgInternalCode(
			List<Long> orgIdList) {
		if (orgIdList == null || orgIdList.size() == 0) {
			return 0;
		} else {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("orgIdsList", ParametersConvertUtil.convertToListString(orgIdList));
			Integer result = (Integer) this
					.getSqlMapClientTemplate()
					.queryForObject(
							"regionalPartyMember.countRegionalPartyMemberByOrgIdOrOrgInternalCode",
							map);
			return result == null ? 0 : result;
		}

	}
}
