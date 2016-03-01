package com.tianque.partyBuilding.organizations.service;

import com.tianque.core.base.BaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.organizations.domain.NewPartyOrg;
import com.tianque.partyBuilding.organizations.domain.StatisticParty;
import com.tianque.partyBuilding.organizations.domain.vo.SearchNewPartyOrgVo;

/**
 * 两新组织党组织表:业务逻辑层接口
 * 
 * @author
 * @date 2014-01-14 15:44:08
 */
public interface NewPartyOrgService extends
		BaseService<NewPartyOrg, SearchNewPartyOrgVo> {

	public PageInfo<StatisticParty> statisticBySearchVo(
			SearchNewPartyOrgVo searchVo, Integer page, Integer rows,
			String sidx, String sord);

	public Boolean hasDuplicate(SearchNewPartyOrgVo searchVo);

}
