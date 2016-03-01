package com.tianque.plugin.analyzing.service;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.analyzing.domain.DepartmentPartyColumnVo;
import com.tianque.plugin.analyzing.domain.StatisticSearchVo;

public interface DepartmentPartyService {
	/**
	 * 组织机构-党委部门报表数据
	 * 
	 * @return
	 */
	public List<DepartmentPartyColumnVo> queryDepartmentPartyDataByYearMonthColumnVoForList(
			Long orgId, int year, int month, Integer orgLevelDistance);

	/**
	 * 根据年和月份生产统计数据，
	 */
	public void createHistoryStatisticByType(int year, int month, String type,
			String orgInternalCode);

	/******************************************************************************************************************/
	/***
	 * 组织机构数据统计
	 */
	public void addDepartmentParty(String type, Integer year, Integer month);

	/**
	 * 查询 图表数据
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> queryDepartmentPartyListFromHistoryForList(
			StatisticSearchVo statisticSearchVo);
}
