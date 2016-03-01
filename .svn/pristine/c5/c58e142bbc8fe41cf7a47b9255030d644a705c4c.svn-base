package com.tianque.partyBuilding.organizations.service;

import com.tianque.core.base.BaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.organizations.domain.RegionalPartyMember;
import com.tianque.partyBuilding.organizations.domain.vo.RegionalPartyMemberVo;

/**
 * 区域党委成员:业务逻辑接口
 * */
public interface RegionalPartyMemberService extends
		BaseService<RegionalPartyMember, RegionalPartyMemberVo> {

	/**
	 * @param regionalPartyMemberVo
	 * @param rows
	 * @param page
	 * @param sidx
	 * @param sord
	 *            根据regionalPartyMemberVo查询出该区域的区域党委成员，不包括下辖
	 * */
	public PageInfo findRegionalPartyMemberPagerBySearchVo(
			RegionalPartyMemberVo regionalPartyMemberVo, Integer pageNum,
			Integer pageSize, String sortField, String sord);

	/**
	 * @param internalId
	 * @param orgInternalCode
	 *            为领导视图提供统计的 根据orgInternalCode的值和 internalId的值的不同得到乡镇的统计和社区的统计
	 */
	public Integer countRegionalPartyMemberByOrgIdOrOrgInternalCode(Long internalId,
			String orgInternalCode);

}
