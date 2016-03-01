package com.tianque.plugin.tqSearch.dubboService;

import java.util.List;
import java.util.Map;

import com.tianque.plugin.tqSearch.domain.TqSearchHisHot;
import com.tianque.plugin.tqSearch.domain.TqSearchResults;
import com.tianque.plugin.tqSearch.domain.TqSearchVo;

public interface TqSearchService {
	
	/**
	 * 搜索结果集
	 */
	public TqSearchResults queryForResult(TqSearchVo searchVo);
	
	/**
	 * 搜索列表
	 */
	public List<Map<String, Object>> queryForList(TqSearchVo searchVo);
	
	/**
	 * 搜索对象
	 */
	public Object queryForObject(TqSearchVo searchVo);
	
	/**
	 * 统计数量
	 */
	public Object queryForCount(TqSearchVo searchVo);

	/**
	 * 检索热点
	 */
	public List<TqSearchHisHot> queryForHot(String searchType);

	/**
	 * 获得检索历史 userName
	 */
	public List<TqSearchHisHot> queryForHis(String userName);
	
	public void deleteIndex(String indexType ,Object keyId);
}
