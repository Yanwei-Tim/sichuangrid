package com.tianque.partyBuilding.organizations.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.partyBuilding.organizations.domain.DistrictPartyStatistics;

public interface DistrictPartyService {

	public PageInfo<DistrictPartyStatistics> findDistrictPartyStatistics(
			Long organizationId, Integer rows, Integer page);
}
