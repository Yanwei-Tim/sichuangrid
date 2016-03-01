package com.tianque.openLayersMap.service;

import java.util.List;

import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.domian.vo.SearchInfoVo;
import com.tianque.openLayersMap.domian.vo.StatisticInfoVo;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;

/**
 * 乡镇级别上统计
 * 
 * @date 2013-3-15
 */
public interface TownshipsUpStatisticService {

	/**
	 * 应用于图层（Layer）统计搜索
	 * 
	 * @param searchVo
	 * @return List<StatisticInfoVo>
	 */
	public List<StatisticInfoVo> statisticInfoForLayerBySearchVo(SearchInfoVo searchVo);

	/**
	 * 应用于视野内（Screen）统计搜索
	 * 
	 * @param searchVo
	 * @return List<StatisticInfoVo>
	 */
	public List<StatisticInfoVo> statisticInfoForScreenBySearchVo(SearchInfoVo searchVo);
	
	/**
	 * 应用于辖区分布（Area）统计搜索
	 * 
	 * @param searchVo
	 * @return List<StatisticInfoVo>
	 */
	public List<StatisticInfoVo> statisticInfoForAreaBySearchVo(SearchInfoVo searchVo);

	/**
	 * 通过组织机构ID统计二维地图数据（主要应用于辖区分布）
	 * 
	 * @param orgId
	 * @return List<StatisticInfoVo>
	 */
	public List<StatisticInfoVo> statisticInfoByOrgId(Long orgId);

	/**
	 * 通过组织机构ID统计二维地图数据（主要应用于辖区分布 总类别统计）
	 * 
	 * @param orgId
	 * @return List<StatisticInfoVo>
	 */
	public List<StatisticInfoVo> statisticGeneralCategoryInfoByOrgId(Long orgId);

	/**
	 * 柱状图（ColumnChart）统计搜索
	 * 
	 * @param orgId
	 * @return HighchartColumnVo
	 */
	public HighchartColumnVo statisticColumnChartInfoByOrgId(Long orgId,
			String typeName);

	/**
	 * 通过组织机构ID和坐标集合获取二维地图数据（主要应用于画区域统计）
	 * 
	 * @param screenCoordinateVo
	 * @return Integer
	 */
	public Integer statisticInfoByOrgIdAndPoints(
			ScreenCoordinateVo screenCoordinateVo, Long id);

	/**
	 * 通过组织机构ID获取二维地图数据总数（主要应用于画区域统计）
	 * 
	 * @param orgId
	 * @return Integer
	 */
	public Integer statisticInfoSumByOrgId(Long orgId);

	/**
	 * 饼状图（PieChart）统计搜索
	 * 
	 * @param orgId
	 * @param typeName
	 * @return List<Object[]>
	 */
	public List<Object[]> getStatisticPieChartInfo(Long orgId, String typeName);
}
