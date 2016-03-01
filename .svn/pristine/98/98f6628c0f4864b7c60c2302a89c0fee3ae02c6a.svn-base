package com.tianque.plugin.tqSearch.service;

import java.util.List;
import java.util.Map;

public interface TqQueryService {
	/**
	 * 搜索列表
	 */
	public List queryForList(String statementName, Map parameterObject);
	
	/**
	 * 搜索对象
	 */
	public Object queryForObject(String statementName, Map parameterObject);
	
	/**
	 * 统计数量
	 */
	public Object queryForCount(String statementName, Map parameterObject);
	
	/**
	 * 更新索引
	 */
	public Object updateIndex(String statementName, Object parameterObject);
	
	/**
	 * 删除索引
	 */
	public Object deleteIndex(String indexType ,Object parameterObject);

}
