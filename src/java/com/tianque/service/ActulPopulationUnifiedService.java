package com.tianque.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.HouseLivingPopulationInfo;
import com.tianque.search.vo.ActualPopulationSearchCondition;

public interface ActulPopulationUnifiedService {
	public PageInfo<HouseLivingPopulationInfo> findActualPopulations(
			ActualPopulationSearchCondition condition, Integer pageNum, Integer pageSize,
			String sidx, String sord);

}
