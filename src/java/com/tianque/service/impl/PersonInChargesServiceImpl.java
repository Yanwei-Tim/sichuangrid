package com.tianque.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.PersonInChargesDao;
import com.tianque.domain.PersonInCharges;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.PersonInChargesService;

@Service("personInChargesService")
public class PersonInChargesServiceImpl extends AbstractBaseService implements
		PersonInChargesService {
	@Autowired
	private PersonInChargesDao personInChargesDao;

	@Override
	public PersonInCharges addPersonInCharges(PersonInCharges personInCharges) {
		if (personInCharges.getName() != null) {
			setChinesePinyin(personInCharges);
			return personInChargesDao.addPersonInCharges(personInCharges);
		}
		return null;
	}

	@Override
	public PageInfo<PersonInCharges> findPersonInCharges(Long palceId,
			int pageNum, int pageSize, String sortField, String order,
			String placeType) {
		return personInChargesDao.findPersonInCharges(palceId, pageNum,
				pageSize, sortField, order, placeType);
	}

	@Override
	public PersonInCharges updatePersonInCharges(PersonInCharges personInCharges) {
		if (personInCharges.getName() != null) {
			setChinesePinyin(personInCharges);
		}
		return personInChargesDao.updatePersonInCharges(personInCharges);
	}

	private void setChinesePinyin(PersonInCharges personInCharges) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(personInCharges.getName());
		personInCharges.setFullPinyin((String) pinyin.get("fullPinyin"));
		personInCharges.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	@Override
	public List<PersonInCharges> findperPersonInChargesForList(Long placeId,
			String type, String tag) {
		return personInChargesDao.findperPersonInChargesForList(placeId, type,
				tag);
	}

	@Override
	public void deletePersonInCharges(Long placeId, String placeType) {
		personInChargesDao.deletePersonInCharges(placeId, placeType);
	}

	@Override
	public PersonInCharges getPersonInChargesById(Long id) {
		if (id == null || id.longValue() == 0L) {
			throw new BusinessValidationException("id不能为空!");
		}
		return personInChargesDao.getPersonInCharges(id);
	}

	@Override
	public List<PersonInCharges> getPerPersonInChargesForList(Long placeId,
			String type) {
		return personInChargesDao.getPerPersonInChargesForList(placeId, type);
	}

	@Override
	public void deletePersonInChargesById(Long id) {
		personInChargesDao.deletePersonInChargesById(id);
	}

}
