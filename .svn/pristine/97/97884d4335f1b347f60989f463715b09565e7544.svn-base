package com.tianque.analysis.api;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.judgmentAnalysis.domain.DimensionCombination;

public interface DimensionCombinationDubboService {

	public DimensionCombination getDimensionCombinationById(Long id);

	public DimensionCombination addDimensionCombination(
			DimensionCombination dimensionCombination);

	public DimensionCombination updateDimensionCombination(
			DimensionCombination dimensionCombination);

	public int deleteDimensionCombinationByIds(List<Long> ids);

	public PageInfo<DimensionCombination> findDimensionCombinationForPage(
			int pageNum, int pageSize, String sortField, String order,
			DimensionCombination DimensionCombination);

}
