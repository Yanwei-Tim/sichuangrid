package com.tianque.plugin.analysisNew.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;

/**
 * @Description:单位场所领导视图和研判分析dao
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-19 下午03:41:16
 */
@DynamicIbatisDAO(value = "CompanyPlaceLeaderViewNewDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("CompanyPlaceLeaderViewNewDAO")
public interface CompanyPlaceLeaderViewNewDAO {

	/**
	 * 单位场所的统计领导视图当月的数据
	 * 
	 * @param map
	 * @return
	 */
	public List<StatisticsBaseInfoAddCountVo> queryStatisticsBaseInfoCurrentByKeyForList(
			Map<String, Object> map);

	public List<StatisticsBaseInfoAddCountVo> queryStatisticsBaseInfoAddCurrentByKeyForList(
			Map<String, Object> map);

	/**
	 * 单位场所的统计领导视图历史月的数据
	 * 
	 * @param map
	 * @return
	 */
	public StatisticsBaseInfoAddCountVo getStatisticsSummary(
			Map<String, Object> map);

	/**
	 * 新增某个月的某个类别的统计数据
	 * 
	 * @param map
	 */
	public void addCompanyPlaceAnalyzStatisticsByTimeAndModuleKey(
			Map<String, Object> map);

	/**
	 * 删除统计的数据
	 * 
	 * @param map
	 */
	public void deleteCompanyPlaceAnalyzStatistics(Map<String, Object> map);

	/**
	 * 实时统计单位场所的各个区域分布图
	 * 
	 * @param columnDateMap
	 * @return
	 */
	public List<Map<String, Object>> queryColumnDateByMapForList(
			Map<String, Object> columnDateMap);

	/**
	 * 统计单位场所的各个区域分布图历史
	 * 
	 * @param columnDateMap
	 * @return
	 */
	public List<Map<String, Object>> queryColumnDateByTimeMapForList(
			Map<String, Object> columnDateMap);

	/**
	 * 趋势图
	 * 
	 * @param map
	 * @return
	 */
	public Integer getTendencyChartToal(Map<String, Object> map);

	/**
	 * 类型分布图本月的（只查询单位和场所）
	 * 
	 * @param map
	 * @return
	 */
	public Integer getCompanyplaceCountSumByOrgInternalCode(
			Map<String, Object> companyPlacePieMap);

	/**
	 * 单个类型分布图本月的
	 * 
	 * @param companyPlacePieMapToAloneMold
	 * @return
	 */
	public Integer getCompanyplaceCountSumByOrgInternalCodeToAloneMold(
			Map<String, Object> companyPlacePieMapToAloneMold);

	/**
	 * 单个类型分布图历史月份
	 * 
	 * @param companyPlacePieMap
	 * @return
	 */
	public Integer getCompanyPlacePieToAloneMold(
			Map<String, Object> companyPlacePieMap);

}
