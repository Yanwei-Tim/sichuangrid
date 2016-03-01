package com.tianque.analysis.api;

import java.util.List;

import com.tianque.plugin.judgmentAnalysis.domain.DimensionCombinationCycle;

public interface DimensionCombinationCycleDubboService {
	/**
	 * 根据业务模型名称、orgid、单维度、周期查询本组织机构该维度的统计数量 dimensionKeyName 单维度（如sex）
	 * modelKeyName 业务模型关键字（如户籍人口hstaff） mode 历史或准实时 dimensionKeyName 维度关键字
	 * （如age、sex）
	 * 
	 * @time: 2015-3-23 上午10:36:30
	 */
	List<DimensionCombinationCycle> countOwnDimensionCombinationCycle(
			Long orgId, String modelKeyName, String dimensionKeyName, int year,
			int month);

	/**
	 * 根据业务模型名称、orgid、维度1的具体值、维度2、周期查询本组织机构的维度1情况下的所有维度2统计数量。 dimension1 维度1
	 * keyName（如sex） dimension2 维度2 keyName（如age） dimensionName1 维度1具体值(如男/女)
	 * dimensionKeyName 需要显示的名称(如根据性别为女 查询出年龄)
	 * 
	 * @time: 2015-3-23 下午02:13:51
	 */
	List<DimensionCombinationCycle> countUnderDimensionCombinationCycle(
			String dimension1, String dimension2, String dimensionName1,
			Long orgId, String modelKeyName, String dimensionKeyName, int year,
			int month);

	public List<DimensionCombinationCycle> findEmphasisSingleDimension(
			Long orgId, String modelKeyName, String dimension1, int year,
			int month);

	public List<DimensionCombinationCycle> findEmphasisMoreDimension(
			Long orgId, String dimension1, String dimension2,
			String dimensionName1, String modelKeyName,
			String dimensionKeyName, int year, int month);

}
