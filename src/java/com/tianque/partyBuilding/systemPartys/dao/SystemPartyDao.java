package com.tianque.partyBuilding.systemPartys.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.partyBuilding.systemPartys.domain.SystemParty;

public interface SystemPartyDao {
	SystemParty addSystemParty(SystemParty systemParty);

	SystemParty updateSystemParty(SystemParty systemParty);

	SystemParty getSystemPartyById(Long id);

	PageInfo<SystemParty> findSystemPartysForPage(SystemParty systemParty,
			Pager pager);

	public boolean deleteSystemPartyByIds(Integer partyOrgType, List<Long> ids);

	boolean isExsistedSystemPartyOrg(String partyOrgName, Integer partyOrgType);
}
