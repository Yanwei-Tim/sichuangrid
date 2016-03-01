package com.tianque.partyBuilding.systemPartys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.partyBuilding.systemPartys.domain.SystemParty;

@Repository("systemPartyDao")
public class SystemPartyDaoImpl extends BaseDaoImpl<SystemParty> implements
		SystemPartyDao {

	@Override
	public SystemParty addSystemParty(SystemParty systemParty) {
		return add(systemParty);
	}

	@Override
	public SystemParty updateSystemParty(SystemParty systemParty) {
		return update(systemParty);
	}

	@Override
	public SystemParty getSystemPartyById(Long id) {
		return get(id);
	}

	@Override
	public PageInfo<SystemParty> findSystemPartysForPage(
			SystemParty systemParty, Pager pager) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", ThreadVariable.getOrganization().getId());
		map.put("partyOrgType", systemParty.getPartyOrgType());
		map.put("partyName", systemParty.getPartyName());
		return findPagerBySearchVo(map, pager, "SystemPartysForPage");
	}

	@Override
	public boolean deleteSystemPartyByIds(Integer partyOrgType, List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("partyOrgType", partyOrgType);
		map.put("ids", ids);
		return delete(map, "deleteSystemPartyByIds");
	}

	@Override
	public boolean isExsistedSystemPartyOrg(String partyOrgName,
			Integer partyOrgType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", ThreadVariable.getOrganization().getId());
		map.put("partyName", partyOrgName);
		map.put("partyOrgType", partyOrgType);
		Integer count = countForObject(map, "isExsistedSystemPartyOrg");
		return count != null && count.intValue() > 0;
	}
}
