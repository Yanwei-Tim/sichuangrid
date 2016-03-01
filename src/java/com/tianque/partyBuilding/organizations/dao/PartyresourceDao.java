package com.tianque.partyBuilding.organizations.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.partyBuilding.organizations.domain.Partyresource;
import com.tianque.partyBuilding.organizations.domain.vo.SearchPartyresourceVo;

/**
 * 区域内主要党组织资源:数据操作层接口
 * 
 * @author
 * @date 2014-01-15 09:22:20
 */
public interface PartyresourceDao extends
		BaseDao<Partyresource, SearchPartyresourceVo> {

	public Integer getTownAndVillagePartyResourceNum(List<Long> orgIdList);

}
