package com.tianque.partyBuilding.organizations.service;

import com.tianque.core.base.BaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.organizations.domain.TownPartyOrg;
import com.tianque.partyBuilding.organizations.domain.vo.SearchTownPartyOrgVo;

/**
 * 街道社区党组织表:业务逻辑层接口
 * 
 * @author
 * @date 2014-01-14 14:35:25
 */
public interface TownPartyOrgService extends
		BaseService<TownPartyOrg, SearchTownPartyOrgVo> {

	public PageInfo statisticBySearchVo(SearchTownPartyOrgVo searchVo,
			Integer page, Integer rows, String sidx, String sord);

	public Boolean hasDuplicate(SearchTownPartyOrgVo searchVo);

}
