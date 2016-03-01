package com.tianque.plugin.analysisNew.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.plugin.analysisNew.domain.PopulationStatType;
import com.tianque.plugin.analysisNew.domain.SearchPrimaryOrganizationDataColumnVo;
import com.tianque.plugin.analysisNew.domain.StatisticSearchVo;

public interface PrimaryOrganizationsStatisticsNewDao {

	/***
	 * 
	 * @param statisticSearchVo
	 * @return
	 */
	public List<Map<String, Object>> getStatisticList(
			StatisticSearchVo statisticSearchVo);

	/** 查询社会组织和非公有制经济组织 */
	public List<Map<String, Object>> getNewSocietyStatisticList(
			StatisticSearchVo statisticSearchVo);

	/***
	 * 根据年月查询所有符合条件的组织机构信息
	 */
	public List<SearchPrimaryOrganizationDataColumnVo> findPrimaryOrganizationDataColumnByDate(
			int year, int month, Long orgId);

	/**
	 * 查询组织机构统计数据，用来组成趋势图需要的数据值（数据来源于统计报表历史表中）
	 * 
	 * @param typeName
	 * @param type
	 * @param timeStr
	 * @return
	 */
	public Integer countPrimaryOrgByMap(String typeId, String timeStr,
			Long orgId);

	public List<Map<String, Object>> getPrimaryOrganizationsListFromHistory(
			StatisticSearchVo statisticSearchVo);

	/***
	 * 清除组织机构分布表数据
	 */
	public void deletePrimaryStatType(int year, int month);

	/***
	 * 将组织机构的数据插入对应的分布表中
	 */
	public void addPrimaryOrganizationsStat(PopulationStatType populationType,
			String tableName, Date createDate, Date nextMonthStart,
			String orgCode);

	/***
	 * 查询得到组织机构下的总人数
	 */
	public List<SearchPrimaryOrganizationDataColumnVo> getPrimaryOrganizarionMember(
			int teamClass, Long orgId, Date startDate, Date endDate);

}
