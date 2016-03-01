package com.tianque.plugin.weChat.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.vo.StatisticAnalysisDetailVo;
import com.tianque.plugin.weChat.vo.StatisticAnalysisVo;

public interface StatisticAnalysisService {
	/**统计列表*/
	public List<StatisticAnalysisVo> findStatisticAnalysis(StatisticAnalysisVo statisticAnalysisVo);
	
	/**
	 * 统计粉丝
	 * @param statisticAnalysisVo
	 * @return
	 */
	public PageInfo<StatisticAnalysisDetailVo> findStatisticAnalysisToFans(
			StatisticAnalysisVo statisticAnalysisVo, Integer page, Integer rows, String sidx, String sord); 
	
	/**
	 * 统计事件(大类)
	 * @param statisticAnalysisVo
	 * @return
	 */
	public List<StatisticAnalysisVo> findStatisticAnalysisToIssueTypeDomain(StatisticAnalysisVo statisticAnalysisVo);
	
	/**
	 * 统计事件(子类)
	 * @param statisticAnalysisVo
	 * @return
	 */
	public List<StatisticAnalysisVo> findStatisticAnalysisToIssueType(StatisticAnalysisVo statisticAnalysisVo);
}