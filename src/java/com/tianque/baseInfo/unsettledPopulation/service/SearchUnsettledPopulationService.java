package com.tianque.baseInfo.unsettledPopulation.service;

import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.BaseUnsettledPopulationSearchCondition;

public interface SearchUnsettledPopulationService {

	public PageInfo<UnsettledPopulation> searchUnsettledPopulation(
			BaseUnsettledPopulationSearchCondition condition, int pageNum, int pageSize,
			String sortField, String order);

	public PageInfo<UnsettledPopulation> fastSearchemptyUnsettledPopulationPage(
			BaseUnsettledPopulationSearchCondition condition, int pageNum, int pageSize,
			String sortField, String order);

}
