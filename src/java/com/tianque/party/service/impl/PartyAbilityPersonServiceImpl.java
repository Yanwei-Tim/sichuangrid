package com.tianque.party.service.impl;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.party.dao.PartyAbilityPersonDao;
import com.tianque.party.domain.PartyAbilityPerson;
import com.tianque.party.service.PartyAbilityPersonService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("partyAbilityPersonService")
@Transactional
public class PartyAbilityPersonServiceImpl extends AbstractBaseService
		implements PartyAbilityPersonService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PartyAbilityPersonDao partyAbilityPersonDao;

	@Override
	public PartyAbilityPerson addPartyAbilityPerson(PartyAbilityPerson person) {
		person.getOwnerOrg().setOrgInternalCode(
				organizationDubboService.getSimpleOrgById(
						person.getOwnerOrg().getId()).getOrgInternalCode());
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(person
				.getName());
		person.setSimplePinyin(pinyin.get("simplePinyin"));
		person.setFullPinyin(pinyin.get("fullPinyin"));
		return partyAbilityPersonDao.addPartyAbilityPerson(person);
	}

	@Override
	public PageInfo<PartyAbilityPerson> findPartyAbilityPersonsByOrgId(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org == null) {
				return constructEmptyPageInfo();
			} else {
				return partyAbilityPersonDao.findPartyAbilityPersonByOrgId(
						orgId, pageNum, pageSize, sidx, sord);
			}
		}

	}

	private PageInfo<PartyAbilityPerson> constructEmptyPageInfo() {
		PageInfo<PartyAbilityPerson> result = new PageInfo<PartyAbilityPerson>();
		result.setResult(new ArrayList<PartyAbilityPerson>());
		return result;
	}

	@Override
	public PartyAbilityPerson updatePartyAbilityPerson(PartyAbilityPerson person) {
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(person
				.getName());
		person.setSimplePinyin(pinyin.get("simplePinyin"));
		person.setFullPinyin(pinyin.get("fullPinyin"));
		return partyAbilityPersonDao.updatePartyAbilityPerson(person);
	}

	@Override
	public boolean deletePartyAbilityPersonById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id不能为空");
		}
		PartyAbilityPerson person = partyAbilityPersonDao
				.getPartyAbilityPersonById(id);
		if (person != null) {
			partyAbilityPersonDao.deletePartyAbilityPerson(id);
		}
		return true;
	}

	@Override
	public PartyAbilityPerson getPartyAbilityPersonById(Long personId) {
		return partyAbilityPersonDao.getPartyAbilityPersonById(personId);
	}

}
