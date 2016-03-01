package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.dao.EntourageDao;
import com.tianque.domain.Entourage;
import com.tianque.exception.base.BusinessValidationException;

@Repository("entourageDao")
@SuppressWarnings("unchecked")
public class EntourageDaoImpl extends AbstractBaseDao implements EntourageDao {

	@Override
	public Entourage addEntourage(Entourage entourage) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"entourage.addEntourage", entourage);
		return getEntourageById(id);
	}

	@Override
	public Entourage getEntourageById(Long id) {
		Entourage entourage = (Entourage) getSqlMapClientTemplate()
				.queryForObject("entourage.getEntourageById", id);
		return entourage;
	}

	@Override
	public List<Entourage> findEntourageForPageByTrampResidentId(
			Long trampResidentId, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("trampResidentId", trampResidentId.toString());
		if (StringUtil.isStringAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		return getSqlMapClientTemplate().queryForList(
				"entourage.findEntourages", map);
	}

	@Override
	public Entourage updateEntourage(Entourage entourage) {
		if (entourage == null || entourage.getId() == null)
			throw new BusinessValidationException("参数错误");
		getSqlMapClientTemplate()
				.update("entourage.updateEntourage", entourage);
		return getEntourageById(entourage.getId());
	}

	@Override
	public int deleteEntourageById(Long id) {
		if (id == null) {
			return 0;
		}
		int count = getSqlMapClientTemplate().delete(
				"entourage.deleteEntourageById", id);
		return count;

	}

	@Override
	public Entourage getEntourageByIdCard(List<String> list,
			Long trampResidentId) {
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("trampResidentId", trampResidentId);
		query.put("idCardNoList", list);
		Entourage entourage = (Entourage) getSqlMapClientTemplate()
				.queryForObject("entourage.getEntourageByIdCard", query);
		return entourage;
	}

}
