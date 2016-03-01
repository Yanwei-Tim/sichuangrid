package com.tianque.partyBuilding.organsParty.dao;

import com.tianque.core.base.BaseDao;
import com.tianque.domain.PropertyDict;
import com.tianque.partyBuilding.organsParty.domain.OrgansParty;
import com.tianque.partyBuilding.organsParty.domain.vo.SearchOrgansPartyVo;

/**
 * 机关党组织表:数据操作层接口
 * 
 * @author
 * @date 2014-02-10 16:00:06
 */
public interface OrgansPartyDao extends
		BaseDao<OrgansParty, SearchOrgansPartyVo> {

	public Integer getOrgansPartyCountByOrgIdAndSuperiorAndType(Long orgId,
			Long superior, PropertyDict type);

	public Long getOrgansPartyByName(String name, Long orgId);

}
