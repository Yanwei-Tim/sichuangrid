package com.tianque.plugin.tqSearch.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.domain.TqSearchVo;
import com.tianque.plugin.tqSearch.dubboService.TqSearchService;
import com.tianque.plugin.tqSearch.service.TqQueryService;
import com.tianque.plugin.tqSearch.sqlMap.SolrSqlMap;
import com.tianque.plugin.tqSearch.syncSolrIndex.SyncSolrIndexForMQ;

@Service("tqQueryService")
public class TqQueryServiceImpl implements TqQueryService {
	@Autowired
	private TqSearchService tqSearchService;
	@Autowired
	private SyncSolrIndexForMQ syncSolrIndexForMQ;

	@Override
	public List queryForList(String statementName, Map parameterObject) {
		TqSearchVo searchVo = SolrSqlMap.getSearchVo(statementName, parameterObject);
		List<Map<String, Object>> mapList =  tqSearchService.queryForList(searchVo);
		return SolrSqlMap.toResult(statementName, mapList,searchVo);
	}

	@Override
	public Object queryForObject(String statementName, Map parameterObject) {
		TqSearchVo searchVo = SolrSqlMap.getSearchVo(statementName, parameterObject);
		Map<String,Object> map = (Map<String, Object>) tqSearchService.queryForObject(searchVo);
		return SolrSqlMap.toResult(statementName, map, searchVo);
	}

	@Override
	public Object queryForCount(String statementName, Map parameterObject) {
		TqSearchVo searchVo = SolrSqlMap.getSearchVo(statementName, parameterObject);
		return tqSearchService.queryForCount(searchVo);
	}

	@Override
	public Object updateIndex(String statementName, Object parameterObject) {
		syncSolrIndexForMQ.sendMessage(parameterObject, OperateMode.EDIT,statementName);
		return true;
	}
	
	@Override
	public Object deleteIndex(String key, Object parameterObject) {
//		SolrSqlMap sqlMap = SolrSqlMap.getDeleteEnum(key);
//		Object deleteVo = SolrSqlMap.getDeleteVo(key, parameterObject);
//		tqSearchService.deleteIndex(sqlMap.getSearchType(), deleteVo);
		syncSolrIndexForMQ.sendMessage(parameterObject, OperateMode.DELETE,key);
		return true;
	}
}
