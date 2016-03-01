package com.tianque.plugin.weChat.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.vo.StatisticAnalysisDetailVo;
import com.tianque.plugin.weChat.vo.StatisticAnalysisVo;

public interface StatisticAnalysisDao {
	public List<StatisticAnalysisDetailVo> findStatisticAnalysisDetails(
			StatisticAnalysisVo statisticAnalysisVo);
	
	public PageInfo<StatisticAnalysisDetailVo> findFansStatisticAnalysisDetails(
			StatisticAnalysisVo statisticAnalysisVo, Integer pageNum, Integer pageSize, String sidx, String sord);
	
	public List<StatisticAnalysisDetailVo> findStatisticAnalysisToIssueTypeDomain(
			StatisticAnalysisVo statisticAnalysisVo);
	
	public List<StatisticAnalysisDetailVo> findStatisticAnalysisToIssueType(
			StatisticAnalysisVo statisticAnalysisVo);
}
