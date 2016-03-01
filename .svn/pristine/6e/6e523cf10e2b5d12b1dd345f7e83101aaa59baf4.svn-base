package com.tianque.plugin.weChat.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.dao.StatisticAnalysisDao;
import com.tianque.plugin.weChat.vo.StatisticAnalysisDetailVo;
import com.tianque.plugin.weChat.vo.StatisticAnalysisVo;

@Repository("statisticAnalysisDao")
public class StatisticAnalysisDaoImpl extends AbstractBaseDao implements StatisticAnalysisDao {

	@Override
	public List<StatisticAnalysisDetailVo> findStatisticAnalysisDetails(
			StatisticAnalysisVo statisticAnalysisVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statisticAnalysisVo", statisticAnalysisVo);
		return getSqlMapClientTemplate().queryForList(
				"statisticAnalysis.findStatisticAnalysisDetails", map);
	}
	
	@Override
	public PageInfo<StatisticAnalysisDetailVo> findFansStatisticAnalysisDetails(
			StatisticAnalysisVo statisticAnalysisVo, Integer pageNum, Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("statisticAnalysisVo", statisticAnalysisVo);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"statisticAnalysis.countFansStatisticAnalysisDetails", map);
		PageInfo<StatisticAnalysisDetailVo> pageInfo = new PageInfo<StatisticAnalysisDetailVo>();
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		if (countNum > 0) {
			map.put("pageForm", (pageNum - 1) * pageSize);
			map.put("pageTo", pageNum * pageSize);
//			statisticAnalysisVo.setPageFrom((pageNum - 1) * pageSize);
//			statisticAnalysisVo.setPageTo(pageNum * pageSize);
			List<StatisticAnalysisDetailVo> list = getSqlMapClientTemplate().queryForList(
					"statisticAnalysis.findFansStatisticAnalysisDetails", map);
			pageInfo.setResult(list);
		} else {
			pageInfo.setResult(new ArrayList<StatisticAnalysisDetailVo>());
		}
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		
		return pageInfo;
	}
	
	@Override
	public List<StatisticAnalysisDetailVo> findStatisticAnalysisToIssueTypeDomain(
			StatisticAnalysisVo statisticAnalysisVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statisticAnalysisVo", statisticAnalysisVo);
		return getSqlMapClientTemplate().queryForList(
				"statisticAnalysis.findIssueTypeStatisticAnalysisDetails", map);
	}
	
	@Override
	public List<StatisticAnalysisDetailVo> findStatisticAnalysisToIssueType(
			StatisticAnalysisVo statisticAnalysisVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statisticAnalysisVo", statisticAnalysisVo);
		return getSqlMapClientTemplate().queryForList(
				"statisticAnalysis.findStatisticAnalysisToIssueType", map);
	}
	
	private PageInfo<StatisticAnalysisDetailVo> createPageInfo(int pageNum, int pageSize, Integer countNum,
			List list) {
		PageInfo<StatisticAnalysisDetailVo> pageInfo = new PageInfo<StatisticAnalysisDetailVo>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}
	
}
