package com.tianque.partyBuilding.organizations.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.organizations.domain.RegionalPartyMember;
import com.tianque.partyBuilding.organizations.domain.vo.RegionalPartyMemberVo;

/**
 * 区域党委成员:dao接口
 * */
public interface RegionalPartyMemberDao extends
		BaseDao<RegionalPartyMember, RegionalPartyMemberVo> {

	/**
	 * @param regionalPartyMemberVo
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param sord
	 *            根据regionalPartyMemberVo分页查询区域党委成员
	 * */
	public PageInfo<RegionalPartyMember> findRegionalPartyMemberPagerBySearchVo(
			RegionalPartyMemberVo regionalPartyMemberVo, Integer pageNum,
			Integer pageSize, String sortField, String sord);

	/**
	 * @param orgId
	 * @param orgInternalCode
	 *            为领导视图提供统计的 根据orgInternalCode的值和 internalId的值的不同得到乡镇的统计和社区的统计
	 */
	public Integer countRegionalPartyMemberByOrgIdOrOrgInternalCode(
			List<Long> orgIdsList);

}
