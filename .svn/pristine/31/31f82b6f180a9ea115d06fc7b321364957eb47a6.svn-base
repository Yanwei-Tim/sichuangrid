package com.tianque.gis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.gis.domain.vo.HousePopulationVo;
import com.tianque.service.util.PopulationType;

@Repository("serachHousePopulationInfoDao")
@SuppressWarnings("unchecked")
public class SerachHousePopulationInfoDaoImpl extends AbstractBaseDao implements
		SerachHousePopulationInfoDao {

	@Override
	public List<HousePopulationVo> serachHousePopulationInfo(String populationType) {
		String searchType;
		if (populationType.equals(PopulationType.HOUSEHOLD_STAFF)
				|| populationType.equals(PopulationType.FLOATING_POPULATION)
				|| populationType.equals(PopulationType.UNSETTLED_POPULATION)
				|| populationType.equals(PopulationType.OVERSEA_STAFF)) {
			searchType = "searchActualPopulation";
		} else {
			searchType = "searchImportPopulation";
		}
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("populationType", populationType);
		query.put("searchType", searchType);
		return getSqlMapClientTemplate().queryForList(
				"housePopulations.findHousePopulationInfoByPopulationTypeForGis", query);
	}
}
