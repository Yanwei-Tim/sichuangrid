package com.tianque.gis.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.gis.dao.SerachHousePopulationInfoDao;
import com.tianque.gis.domain.vo.HousePopulationVo;
import com.tianque.gis.domain.vo.PopulationVo;

public abstract class CommonMark {
	@Autowired
	private SerachHousePopulationInfoDao serachHousePopulationInfoDao;

	protected final static Logger logger = LoggerFactory
			.getLogger(CommonMark.class);

	public List<HousePopulationVo> serachHousePopulationInfo(
			String populationType) {
		List<HousePopulationVo> housePopulation = new ArrayList<HousePopulationVo>();
		try {
			housePopulation = serachHousePopulationInfoDao
					.serachHousePopulationInfo(populationType);
		} catch (Exception e) {
			logger.error("CommonMark查找人房信息出错", e);
		}
		return housePopulation;
	}

	public abstract List<PopulationVo> findPopulationVosByHousePopulations(
			List<HousePopulationVo> housePopulations);

	public abstract PopulationVo getPopulationVoByPopulationIdAndHouseId(
			Long populationId, Long houseId);

}
