package com.tianque.party.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.party.domain.PartyAbilityPerson;

public interface PartyAbilityPersonDao {

	public PartyAbilityPerson addPartyAbilityPerson(PartyAbilityPerson person);

	public PartyAbilityPerson getPartyAbilityPersonById(Long id);

	public PartyAbilityPerson updatePartyAbilityPerson(PartyAbilityPerson person);

	public void deletePartyAbilityPerson(Long id);

	public PageInfo<PartyAbilityPerson> findPartyAbilityPersonByOrgId(Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord);
}
