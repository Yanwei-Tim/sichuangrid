package com.tianque.analysis.api;

import java.util.List;

import com.tianque.plugin.judgmentAnalysis.domain.BusinessModelRealData;
import com.tianque.plugin.judgmentAnalysis.vo.PopulationSummaryVo;

/**
 * 获得统计数据
 * @author n-233
 *
 */
public interface BusinessModelRealDataDubboService {

	/**
	 * 根据业务模块key和orgId获得统计数据
	 * @param modelKey
	 * @param orgId
	 * @return
	 */
    public BusinessModelRealData getBusinessModelRealDataByOrgId(String modelKey,Long orgId);
    /**
     * 根据业务模块key和parentOrgId获得统计数据
     * @param modelKey
     * @param orgId
     * @return
     */
    public List<BusinessModelRealData> findBusinessModelRealDataByParentOrgId(String modelKey,Long orgId);
	/**
	 * 获得所有人口统计数据
	 * @param orgId
	 * @return
	 */
    public PopulationSummaryVo getPopulationSummaryByorgId(Long orgId);
    /**
     * 获得重点人口统计数据
     * @param orgId
     * @return
     */
	public PopulationSummaryVo getEmphasisSummaryByorgId(Long orgId);
	/**
	 * 获取关怀对象统计数据
	 * @param orgId
	 * @return
	 */
	public PopulationSummaryVo getConcernSummaryByorgId(Long orgId);
	/**
	 * 获取所有下辖org的人口统计数据
	 * @param orgId
	 * @return
	 */
	public List<PopulationSummaryVo> findPopulationSummaryByParentOrgId(Long orgId);
	/**
	 * 获取所有下辖org的重点人口统计数据
	 * @param orgId
	 * @return
	 */
	public List<PopulationSummaryVo> findEmphasisSummaryByParentOrgId(Long orgId);
	/**
	 * 获取所有下辖org的关怀对象统计数据
	 * @param orgId
	 * @return
	 */
	public List<PopulationSummaryVo> findConcernSummaryByParentOrgId(Long orgId);
	
	
	
	
	
}
