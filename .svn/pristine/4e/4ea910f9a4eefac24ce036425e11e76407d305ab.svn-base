package com.tianque.baseInfo.base.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;

@Repository("populationRelationDao")
public class PopulationRelationDaoImpl extends AbstractBaseDao implements
		PopulationRelationDao {

	@Override
	public void addPopulationRelation(Long actualId, String actualType,
			Long populationId, String populationType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("actualId", actualId);
		map.put("actualType", actualType);
		map.put("populationId", populationId);
		map.put("populationType", populationType);
		getSqlMapClientTemplate().insert(
				"populationRelation.addPopulationRelation", map);
	}

	@Override
	public void businessDeletePopulationRelation(Long populationId,
			String populationType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationId", populationId);
		map.put("populationType", populationType);
		getSqlMapClientTemplate().insert(
				"populationRelation.businessDeletePopulationRelation", map);

	}

	@Override
	public void actualDeletePopulationRelation(Long actualId, String actualType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("actualId", actualId);
		map.put("actualType", actualType);
		getSqlMapClientTemplate().insert(
				"populationRelation.actualDeletePopulationRelation", map);
	}

}
