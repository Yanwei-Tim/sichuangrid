package com.tianque.gis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.gis.domain.vo.HousePopulationVo;
import com.tianque.gis.domain.vo.PopulationVo;
import com.tianque.gis.service.CommonMark;
import com.tianque.gis.service.GisPopulationService;
import com.tianque.service.util.PopulationType;

@Service("gisPopulationService")
@Transactional
public class GisPopulationServiceImpl implements GisPopulationService {
	@Autowired
	private Map<String, CommonMark> querypopultionsForGisMap;

	@Override
	public List<PopulationVo> queryPopultionsForGis(String populationType) {
		List<PopulationVo> populationList = new ArrayList<PopulationVo>();
		List<HousePopulationVo> housePopulations = querypopultionsForGisMap.get(
				getServiceName(populationType)).serachHousePopulationInfo(populationType);
		if (!housePopulations.isEmpty()) {
			populationList.addAll(querypopultionsForGisMap.get(getServiceName(populationType))
					.findPopulationVosByHousePopulations(housePopulations));
		}
		return populationList;
	}

	private String getServiceName(String type) {
		String populationType = type.substring(0, 1).toUpperCase() + type.substring(1);
		if (type.equals(PopulationType.AID_NEED_POPULATION)
				|| type.equals(PopulationType.FLOATING_POPULATION)
				|| type.equals(PopulationType.UNSETTLED_POPULATION)
				|| type.equals(PopulationType.DANGEROUS_GOODS_PRACTITIONER)
				|| type.equals(PopulationType.ELDERLY_PEOPLE)
				|| type.equals(PopulationType.MENTAL_PATIENT)
				|| type.equals(PopulationType.NURTURES_WOMEN)
				|| type.equals(PopulationType.RECTIFICATIVE_POPULATION)
				|| type.equals(PopulationType.UNEMPLOYED_PEOPLE)) {
			return "query" + populationType + "ForGis";
		} else {
			return "query" + populationType + "PopulationForGis";
		}
	}

}
