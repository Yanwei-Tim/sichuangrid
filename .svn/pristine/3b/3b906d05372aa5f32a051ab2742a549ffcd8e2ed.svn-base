package com.tianque.newVillage.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.ThreadVariable;
import com.tianque.newVillage.dao.NewVillageDao;
import com.tianque.newVillage.domain.NewVillage;

/**
 * @ClassName: NewVillageDaoImpl
 * @Description: 新农村建设
 */
@Repository("newVillageDao")
public class NewVillageDaoImpl extends AbstractBaseDao implements NewVillageDao {

	@Override
	public NewVillage addNewVillage(NewVillage newVillage) {
		if (newVillage != null) {
			newVillage.setCreateUser(ThreadVariable.getUser().getName());
			newVillage.setSourcesState(0L);
		}
		Long id = (Long) getSqlMapClientTemplate().insert(
				"newVillage.addNewVillage", newVillage);
		return getNewVillageById(id);
	}

	@Override
	public NewVillage getNewVillageById(Long id) {
		return (NewVillage) getSqlMapClientTemplate().queryForObject(
				"newVillage.getNewVillage", id);
	}

	@Override
	public void deleteNewVillageById(Long id) {
		getSqlMapClientTemplate().delete("newVillage.deleteNewVillage", id);
	}

	@Override
	public NewVillage updateNewVillage(NewVillage newVillage) {
		if (newVillage != null) {
			newVillage.setUpdateUser(ThreadVariable.getUser().getName());
		}
		getSqlMapClientTemplate().update("newVillage.updateNewVillage",
				newVillage);
		return getNewVillageById(newVillage.getId());
	}

	@Override
	public NewVillage getNewVillageByBasicId(Long basicId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("basicId", basicId);
		return (NewVillage) getSqlMapClientTemplate().queryForObject(
				"newVillage.getNewVillageByBasicId", map);
	}
}
