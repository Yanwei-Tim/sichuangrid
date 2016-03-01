package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PersonInCharges;

public interface PersonInChargesService {
	public PersonInCharges getPersonInChargesById(Long id);

	public PersonInCharges addPersonInCharges(PersonInCharges personInCharges);

	public PersonInCharges updatePersonInCharges(PersonInCharges personInCharges);

	public PageInfo<PersonInCharges> findPersonInCharges(Long palceId, int pageNum, int pageSize,
			String sortField, String order, String placeType);

	public List<PersonInCharges> findperPersonInChargesForList(Long placeId, String type, String tag);

	public void deletePersonInCharges(Long placeId, String placeType);

	public List<PersonInCharges> getPerPersonInChargesForList(Long placeId, String type);

	public void deletePersonInChargesById(Long id);

}
