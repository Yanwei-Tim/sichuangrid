package com.tianque.newVillage.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.newVillage.dao.CapitalInvestedDao;
import com.tianque.newVillage.domain.CapitalInvested;

/**
 * @author yangfan
 * @date 2015年10月9日
 */
@Repository("capitalInvestedDao")
public class CapitalInvestedDaoImpl extends AbstractBaseDao implements
		CapitalInvestedDao {

	@Override
	public CapitalInvested addCapitalInvested(CapitalInvested capitalInvested) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"capitalInvested.addCapitalInvested", capitalInvested);
		return getCapitalInvestedById(id);
	}

	@Override
	public CapitalInvested getCapitalInvestedById(Long id) {
		return (CapitalInvested) getSqlMapClientTemplate().queryForObject(
				"capitalInvested.getCapitalInvested", id);
	}

	@Override
	public void deleteCapitalInvestedById(String[] id) {
		getSqlMapClientTemplate().delete(
				"capitalInvested.deleteCapitalInvested", id);
	}

	@Override
	public CapitalInvested updateCapitalInvested(CapitalInvested capitalInvested) {
		getSqlMapClientTemplate().update(
				"capitalInvested.updateCapitalInvested", capitalInvested);
		return getCapitalInvestedById(capitalInvested.getId());
	}

	@Override
	public CapitalInvested getCapitalInvestedByBasicId(Long basicId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basicId", basicId);
		return (CapitalInvested) getSqlMapClientTemplate().queryForObject(
				"capitalInvested.getCapitalInvestedByBasicId", map);
	}

}
