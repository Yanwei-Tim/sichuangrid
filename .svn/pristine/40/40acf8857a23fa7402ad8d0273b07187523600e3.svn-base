package com.tianque.party.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.party.dao.PartyAbilityPersonDao;
import com.tianque.party.domain.PartyAbilityPerson;

@Repository("partyAbilityPersonDao")
public class PartyAbilityPersonDaoImpl extends AbstractBaseDao implements PartyAbilityPersonDao {

	@Override
	public PartyAbilityPerson addPartyAbilityPerson(PartyAbilityPerson person) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"partyAbilityPerson.addPartyAbilityPerson", person);
		return getPartyAbilityPersonById(id);
	}

	@Override
	public PartyAbilityPerson getPartyAbilityPersonById(Long id) {
		if (id == null)
			return null;
		return (PartyAbilityPerson) getSqlMapClientTemplate().queryForObject(
				"partyAbilityPerson.getPartyAbilityPersonById", id);
	}

	@Override
	public PartyAbilityPerson updatePartyAbilityPerson(PartyAbilityPerson person) {
		getSqlMapClientTemplate().update("partyAbilityPerson.updatePartyAbilityPerson", person);
		person = getPartyAbilityPersonById(person.getId());
		return person;
	}

	@Override
	public void deletePartyAbilityPerson(Long id) {
		getSqlMapClientTemplate().delete("partyAbilityPerson.deletePartyAbilityPerson", id);

	}

	private PageInfo<PartyAbilityPerson> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<PartyAbilityPerson> pageInfo = new PageInfo<PartyAbilityPerson>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	@Override
	public PageInfo<PartyAbilityPerson> findPartyAbilityPersonByOrgId(Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("orgId", orgId);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"partyAbilityPerson.countPartyAbilityPersonsByOwnerOrgId", condition);
		if (isStrotFieldAvaliable(sidx)) {
			condition.put("sortField", sidx);
		}
		condition.put("order", sord);
		List<ElderlyPeople> list = getSqlMapClientTemplate().queryForList(
				"partyAbilityPerson.findPartyAbilityPersonsByOrgId", condition,
				(pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

}
