package com.tianque.partyBuilding.organizations.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.partyBuilding.organizations.domain.NewPartyOrg;
import com.tianque.partyBuilding.organizations.domain.vo.SearchNewPartyOrgVo;

/**
 * 两新组织党组织表:数据操作层接口
 * 
 * @author
 * @date 2014-01-14 15:44:09
 */
public interface NewPartyOrgDao extends BaseDao<NewPartyOrg, SearchNewPartyOrgVo> {

	public Integer countByOrg(List<Long> orgIdList);

	public List<NewPartyOrg> findListBySearchVo(SearchNewPartyOrgVo searchVo);

	public NewPartyOrg getByOrgIdAndName(Long orgId, String name);

}
