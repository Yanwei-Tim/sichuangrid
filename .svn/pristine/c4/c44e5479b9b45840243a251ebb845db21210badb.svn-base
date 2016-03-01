package com.tianque.analysis.api;

import java.util.List;

import com.tianque.plugin.judgmentAnalysis.domain.HistoryCycle;
import com.tianque.plugin.judgmentAnalysis.vo.EmphasisIsHelpHistoryVo;
import com.tianque.plugin.judgmentAnalysis.vo.HistoryCycleSummaryVo;

public interface HistoryCycleDubboService {
	/**
	 * 根据业务模型名称、orgid、周期查询本组织机构的统计数量 modelKeyName 业务模型关键字（如户籍人口hstaff） mode
	 * 历史或准实时
	 * 
	 * @time: 2015-3-20 下午03:16:26
	 */
	HistoryCycle countOwnHistoryCycle(Long orgId, String modelKeyName,
			String mode, int year, int month);

	/**
	 * 根据业务模型名称、parentOrgid、周期查询下辖组织机构的统计数量。 modelKeyName 业务模型关键字（如户籍人口hstaff）
	 * mode 历史或准实时
	 * 
	 * @time: 2015-3-20 下午04:53:45
	 */
	List<HistoryCycle> countUnderHistoryCycle(Long parentOrgId,
			String modelKeyName, String mode, int year, int month);

	/**
	 * 查询重点人员历史记录的同比环比
	 * 
	 * @param compared
	 *            true为同比,false为环比
	 * @param parentOrgId
	 * @param year
	 * @param month
	 * @return
	 */
	public List<HistoryCycleSummaryVo> findEmphasisHistorySummary(
			boolean compared, Long parentOrgId, int year, int month);

	/**
	 * 查询重点人员走访和落实情况
	 * 
	 * @param parentOrgId
	 * @param year
	 * @param month
	 * @return
	 */
	public List<EmphasisIsHelpHistoryVo> findEmphasisIsHelpHistorySummary(
			Long parentOrgId, int year, int month);

	/**
	 * 查询重点人员排名情况
	 * 
	 * @param parentOrgId
	 * @param year
	 * @param month
	 * @return
	 */
	public List<HistoryCycle> findEmphasisHistoryDescSummary(Long parentOrgId,
			int year, int month);

}
