package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.cache.util.CacheKeyGenerator;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.PersonInChargesDao;
import com.tianque.domain.PersonInCharges;

@Repository("personInChargesDao")
public class PersonInChargesDaoImpl extends AbstractBaseDao implements PersonInChargesDao {
	@Autowired
	private CacheService cacheService;

	@Override
	public PersonInCharges addPersonInCharges(PersonInCharges personInCharges) {
		if (personInCharges.getPlaceId() == 0) {
			String key = CacheKeyGenerator.generateCacheKeyFromString(PersonInCharges.class, UUID
					.randomUUID().toString());
			cacheService.set(key, personInCharges);
			personInCharges.setPersonInChargesUuid(key);
			return personInCharges;
		}
		Long id = (Long) getSqlMapClientTemplate().insert("personInChages.addPersonInChages",
				personInCharges);
		return getPersonInCharges(id);
	}

	@Override
	public PageInfo<PersonInCharges> findPersonInCharges(Long placeId, int pageNum, int pageSize,
			String sortField, String order, String placeType) {
		PageInfo<PersonInCharges> pageInfo = new PageInfo<PersonInCharges>();
		Map<String, Object> query = new HashMap<String, Object>();
		if (StringUtil.isStringAvaliable(sortField)) {
			query.put("sortField", sortField);
			query.put("order", order);
		}
		query.put("placeType", placeType);
		query.put("placeId", placeId);
		List list = getSqlMapClientTemplate().queryForList("personInChages.findPersonInChages",
				query, (pageNum - 1) * pageSize, pageSize);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"personInChages.countPersonInChages", query);
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public PersonInCharges updatePersonInCharges(PersonInCharges personInCharges) {
		getSqlMapClientTemplate().update("personInChages.updatePersonInChages", personInCharges);
		return getPersonInCharges(personInCharges.getId());
	}

	@Override
	public PersonInCharges getPersonInCharges(Long id) {
		return (PersonInCharges) getSqlMapClientTemplate().queryForObject(
				"personInChages.getPersonInChagesById", id);
	}

	@Override
	public List<PersonInCharges> findperPersonInChargesForList(Long placeId, String type, String tag) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("placeId", placeId);
		map.put("type", type);
		map.put("tag", tag);
		List<PersonInCharges> perList = (List<PersonInCharges>) getSqlMapClientTemplate()
				.queryForList("personInChages.findperPersonInChargesForList", map);
		return perList;

	}

	@Override
	public void deletePersonInCharges(Long placeId, String placeType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("placeId", placeId);
		map.put("placeType", placeType);
		getSqlMapClientTemplate().delete("personInChages.deletePersonInChages", map);
	}

	@Override
	public List<PersonInCharges> getPerPersonInChargesForList(Long placeId, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("placeId", placeId);
		map.put("type", type);
		List<PersonInCharges> perList = (List<PersonInCharges>) getSqlMapClientTemplate()
				.queryForList("personInChages.getPerPersonInChargesForList", map);
		return perList;
	}

	@Override
	public void deletePersonInChargesById(Long id) {
		getSqlMapClientTemplate().delete("personInChages.deletePersonInChargesById", id);
	}

}
