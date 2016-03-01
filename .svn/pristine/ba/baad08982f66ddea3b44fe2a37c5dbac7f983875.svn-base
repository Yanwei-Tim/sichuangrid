package com.tianque.plugin.analyzing.service;

import java.util.List;

import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.PrimaryOrganizationDataColumnVo;

public interface PrimaryOrganizationsStatisticsService {

	/**
	 * 根据组织机构和type标示查询该组织机构往下一级的区域分布图数据
	 * 
	 * @param type
	 * @param orgId
	 * @return
	 */
	public HighchartColumnVo getPrimaryOrgnizationsStisticsArealDistributionList(
			String type, Long orgId, int year, int month);

	/**
	 * 组织机构报表数据
	 * 
	 * @return
	 */
	public List<PrimaryOrganizationDataColumnVo> getPrimaryOrganizationDataByYearMonthColumnVoList(
			Long orgId, int year, int month);

	/**
	 * 趋势图数据
	 * 
	 * @param type
	 * @param orgInternalCode
	 * @return
	 */

	public HighchartColumnVo findTendencyChartForPositiveinfo(String type,
			String orgInternalCode);

	/***
	 * 组织机构区域分布图加载历史数据
	 * 
	 * @param orgId
	 * @param type
	 * @param year
	 * @param month
	 * @return
	 */
	public HighchartColumnVo getArealDistributionListFromHistory(Long orgId,
			String type, int year, int month);

	/***
	 * 组织机构数据统计
	 */
	public void addPrimaryOrganizationsStat(String type, Integer year,
			Integer month);

	/**
	 * 根据年和月份生产统计数据，
	 */
	public void createHistoryStatisticByType(int year, int month, String type,
			String orgInternalCode);

}
