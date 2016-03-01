package com.tianque.gis.service;

import java.util.List;

import com.tianque.gis.domain.vo.PopulationVo;

public interface GisPopulationService {

	public List<PopulationVo> queryPopultionsForGis(String populationType);

}
