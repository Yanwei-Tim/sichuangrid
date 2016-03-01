package com.tianque.plugin.analysisNew.service;

import java.util.List;

import com.tianque.plugin.analysisNew.domain.HighchartColumnVo;
import com.tianque.plugin.analysisNew.domain.PopulationAreaDataVo;

public interface StatisticsPopulationNewService {

	public List<PopulationAreaDataVo> getCurrentAreaDate(Long orgId,
			String populationType, Integer orgLevelDistance);

	/**
	 * 获取当前月的数据统计 实时从各个表中取（实有人口总况）
	 */
	public List<PopulationAreaDataVo> getPopulationAreaDataByOrgId(Long orgId,
			Integer orgLevelDistance);

	/**
	 * 获取当前月的数据统计 从选定表中去取
	 * 
	 * @param orgId
	 * @param populationType
	 * @return
	 */
	public List<PopulationAreaDataVo> getPopulationAreaDataByOrgIdAndPopulationType(
			Long orgId, String populationType);

	/**
	 * 选择统计的时间生成统计数据存入统计报表
	 * 
	 * @param year
	 * @param month
	 */
	public void addpopulationStat(Integer year, Integer month, Long orgId,
			String populationType);

	/**
	 * 定时统计
	 */
	public void addpopulationStat();

	/**
	 * 
	 */
	public void addpopulationStatForYearAndMonth(Integer year, Integer month);

	/**
	 * 总况区域分布图
	 * 
	 * @param orgId
	 * @return
	 */
	public HighchartColumnVo getPopulationColumnByOrgId(Long orgId);

	/**
	 * 按时间和orgId和type统计总况区域分布图
	 * 
	 * @param orgId
	 * @param type
	 * @return
	 */
	public HighchartColumnVo getPopulationColumnByTime(Long orgId, String type,
			int year, int month);

	/**
	 * 各个模块区域分布图
	 * 
	 * @param orgId
	 * @param populationType
	 * @return
	 */
	public HighchartColumnVo getPopulationColumnByOrgIdAndType(Long orgId,
			String populationType);

	/**
	 * 总况当前月份的类型统计图
	 * 
	 * @param orgId
	 * @param populationType
	 * @return
	 */
	public List<Object[]> getPopulationPieByOrgId(Long orgId,
			String populationType);

	/**
	 * 总况往月的类型统计图
	 * 
	 * @param orgId
	 * @param populationType
	 * @param year
	 * @param month
	 * @return
	 */
	public List<Object[]> getPopulationPieByOrgIdAndMonth(Long orgId,
			String populationType, int year, int month);

	List<PopulationAreaDataVo> getAreaDateByDate(Long orgId,
			String populationType, int year, int month, Integer orgLevelDistance);

	/**
	 * 从统计表中根据条件取出数据（实有人口总况）
	 */
	List<PopulationAreaDataVo> getPopulationAreaDataByOrgIdAndMonth(Long orgId,
			int year, int month, Integer orgLevelDistance);

	/**
	 * 从统计表中取出数据（各个实有人口下的人口信息）
	 * 
	 * @param orgId
	 * @param typeName
	 * @param year
	 * @param month
	 * @return
	 */
	List<PopulationAreaDataVo> getPopulationAreaDataByOrgIdAndMonthAndPopulationType(
			Long orgId, String populationType, int year, int month);

	public int getCountByOrgId(Long orgId, String tableType);

	/**
	 * 户籍人员往月的类型统计图
	 * */
	public List<Object[]> getPopulationPieInfo(int year, int month, Long orgId,
			String populationType);

	/**
	 * 定时统计类型分布图
	 */
	public void addHouseholdStaffPopulationStat();

	/**
	 * 定时统计青少年
	 */
	public void addYouthPopulationStat(int year, int month);

	/***
	 * 流动人口涉疆涉藏数据清洗
	 */
	public void addFloatingPopulationStatForYearAndMonth(Integer year,
			Integer month);

}
