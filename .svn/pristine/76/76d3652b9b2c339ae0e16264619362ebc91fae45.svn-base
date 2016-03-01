package com.tianque.partyBuilding.organizations.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.partyBuilding.organizations.domain.TownPartyOrg;
import com.tianque.partyBuilding.organizations.domain.vo.SearchTownPartyOrgVo;

/**
 * 街道社区党组织表:数据操作层接口
 * 
 * @author
 * @date 2014-01-14 14:35:25
 */
public interface TownPartyOrgDao extends
		BaseDao<TownPartyOrg, SearchTownPartyOrgVo> {

	public Integer countByOrg(List<Long> orgIdsList);

	public List<TownPartyOrg> findListBySearchVo(SearchTownPartyOrgVo searchVo);

	public TownPartyOrg getByOrgIdAndName(Long orgId, String name);

}
