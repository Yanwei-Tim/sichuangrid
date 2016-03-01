package com.tianque.partyBuilding.organizations.service;

import com.tianque.core.base.BaseService;
import com.tianque.partyBuilding.organizations.domain.Partyresource;
import com.tianque.partyBuilding.organizations.domain.vo.SearchPartyresourceVo;

/**
 * 区域内主要党组织资源:业务逻辑层接口
 * 
 * @author
 * @date 2014-01-15 09:22:20
 */
public interface PartyresourceService extends
		BaseService<Partyresource, SearchPartyresourceVo> {

	/**
	 * 
	 * @param internalId
	 * @param orgCode
	 * @return 统计出 区域内主要党组织资源数量，根据组织机构的级别和组织机构code统计社区和乡镇
	 */
	public Integer getTownAndVillagePartyResourceNum(Long internalId, String orgCode);
}
