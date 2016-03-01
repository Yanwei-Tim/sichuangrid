package com.tianque.plugin.transfer.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.service.util.PopulationType;

@Repository("houseDao")
public class HouseDaoImpl extends AbstractBaseDao implements HouseDao {
	// 通过业务人员的id和type来查询实口Id和实口类型
	@Override
	public Object getByPopulationIdAndPopulationType(Long populationId, String populationType) {
		Map map = new HashMap();
		map.put("populationId", populationId);
		map.put("populationType", createPopulationType(populationType));
		return getSqlMapClientTemplate().queryForObject(
				"populationType.getPopulationTypeByPopulationIdAndType", map);
	}

	// 通过业务人员id和type来获取其关联的houseid
	@Override
	public Object getByImportantIdAndImportantType(Long populationId, String populationType) {
		Map map = new HashMap();
		map.put("populationType", createPopulationType(populationType));
		map.put("populationId", populationId);
		return getSqlMapClientTemplate().queryForObject(
				"houseHasActualPopulations.getByImportantIdAndImportantType", map);
	}

	// 通过实口id和type来获取其关联的houseid
	@Override
	public Object getByActualIdAndActualType(Long populationId, String populationType) {
		Map map = new HashMap();
		map.put("populationType", createPopulationType(populationType));
		map.put("populationId", populationId);
		return getSqlMapClientTemplate().queryForObject(
				"houseHasActualPopulations.getByActualIdAndActualType", map);
	}

	// 更新业务人员的人房关联
	@Override
	public void updateImportantHouseId(Long populationId, String populationType, Long oldHouseId,
			Long newHouseId) {
		Map map = new HashMap();
		map.put("newHouseId", newHouseId);
		map.put("populationType", createPopulationType(populationType));
		map.put("populationId", populationId);
		map.put("oldHouseId", oldHouseId);
		getSqlMapClientTemplate().update("houseHasActualPopulations.updateImportantHouseId", map);

	}

	// 删除各类人员的人房关联
	@Override
	public void deleteRelationByPopulationTypeAndPopulationId(String str, Long populationId,
			String populationType) {
		Map map = new HashMap();
		map.put("populationType", createPopulationType(populationType));
		map.put("populationId", populationId);
		if ("true".equals(str)) {
			// 实口人房关联去除
			getSqlMapClientTemplate()
					.delete("houseHasActualPopulation.deleteHouseHasActualPopulationByPopulationTypeAndPopulationId",
							map);
		} else {
			// 业务人口人房关联去除
			getSqlMapClientTemplate()
					.delete("houseHasActualPopulations.deleteHouseHasImportantPopulationByPopulationTypeAndPopulationId",
							map);
		}
	}

	// 根据实口id和type来获取其对应的所有业务人员
	@Override
	public List<PopulationTypeBean> getAllImportantByActualIdAndType(Long actualId,
			String actualType) {
		Map map = new HashMap();
		map.put("actualId", actualId);
		map.put("actualType", actualType);
		return getSqlMapClientTemplate().queryForList(
				"populationType.getPopulationTypeByActualIdAndType", map);

	}

	// 给业务人员增加人房关联关系
	@Override
	public void addHouseRelation(String personType, String populationType, Long houseId,
			Long populationId, boolean defaultLivingHouse) {
		Map map = new HashMap();
		map.put("personType", personType);
		map.put("populationType", createPopulationType(populationType));
		map.put("houseId", houseId);
		map.put("populationId", populationId);
		map.put("defaultLivingHouse", defaultLivingHouse);
		getSqlMapClientTemplate().insert(
				"houseHasActualPopulations.addHouseHasImportantPopulation", map);
	}

	private String createPopulationType(String populationType){
		if(PopulationType.AIDSPOPULATIONS_KEY.equals(populationType)){
			return PopulationType.AIDSPOPULATIONS;
		}
		return populationType;
	}
}
