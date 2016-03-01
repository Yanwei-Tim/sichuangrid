package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.HouseHasActualPopulationDao;
import com.tianque.domain.HouseHasActualPopulation;

@Repository("houseHasActualPopulationDao")
public class HouseHasActualPopulationDaoImpl extends AbstractBaseDao implements
		HouseHasActualPopulationDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.HouseHasActualPopulationDao#addHouseHasActualPopulation
	 * (com.tianque.domain.HouseHasActualPopulation)
	 */
	@Override
	public void addHouseHasActualPopulation(
			HouseHasActualPopulation houseHasActualPopulation) {
		getSqlMapClientTemplate().insert(
				"houseHasActualPopulation.addHouseHasActualPopulation",
				houseHasActualPopulation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.HouseHasActualPopulationDao#
	 * updateHouseHasActualPopulationByPopulationTypeAndHouseId
	 * (java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateHouseHasActualPopulationByPopulationTypeAndHouseId(
			String populationType, Long populationId, Long oldHouseId,
			Long newHouseId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationType", populationType);
		map.put("populationId", populationId);
		map.put("oldHouseId", oldHouseId);
		map.put("newHouseId", newHouseId);
		getSqlMapClientTemplate()
				.update("houseHasActualPopulation.updateHouseHasActualPopulationByPopulationTypeAndHouseId",
						map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.HouseHasActualPopulationDao#
	 * deleteHouseHasActualPopulationByPopulationTypeAndHouseId
	 * (java.lang.String, java.lang.Long, java.lang.Long)
	 */
	@Override
	public void deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
			String populationType, Long houseId, Long populationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationType", populationType);
		map.put("houseId", houseId);
		map.put("populationId", populationId);
		getSqlMapClientTemplate()
				.delete("houseHasActualPopulation.deleteHouseHasActualPopulationByPopulationTypeAndHouseId",
						map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.HouseHasActualPopulationDao#
	 * deleteHouseHasActualPopulationByPopulationTypeAndPopulationId
	 * (java.lang.String, java.lang.Long)
	 */
	@Override
	public void deleteHouseHasActualPopulationByPopulationTypeAndPopulationId(
			String populationType, Long populationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationType", populationType);
		map.put("populationId", populationId);
		getSqlMapClientTemplate()
				.delete("houseHasActualPopulation.deleteHouseHasActualPopulationByPopulationTypeAndPopulationId",
						map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.HouseHasActualPopulationDao#
	 * getHouseHasActualPopulationByHouseId(java.lang.Long)
	 */
	@Override
	public List<HouseHasActualPopulation> getHouseHasActualPopulationByHouseId(
			Long houseId) {
		return getSqlMapClientTemplate()
				.queryForList(
						"houseHasActualPopulation.getHouseHasActualPopulationByHouseId",
						houseId);
	}

	@Override
	public void deleteHouseHasActualPopulationByHouseId(Long houseId) {
		getSqlMapClientTemplate()
				.delete("houseHasActualPopulation.deleteHousehasactualpopulationByHouseId",
						houseId);
	}

	@Override
	public void deleteHousehasimportantpopulationByHouseId(Long houseId) {
		getSqlMapClientTemplate()
				.delete("houseHasActualPopulation.deleteHousehasimportantpopulationByHouseId",
						houseId);
	}

	// 房屋人数
	@Override
	public Long getHouseIdByPopulationTypeAndPopulationId(
			String populationType, Long populationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("populationType", populationType);
		map.put("populationId", populationId);
		return (Long) getSqlMapClientTemplate()
				.queryForObject(
						"houseHasActualPopulation.getHouseIdByPopulationTypeAndPopulationId",
						map);
	}
}
