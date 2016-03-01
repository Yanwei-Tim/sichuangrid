package com.tianque.party.dao;

import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.party.domain.News;

public interface NewsDao {
	/**
	 * 根据map条件查询News
	 * 
	 * @param map
	 *        map中的key orgId 组织机构id
	 *        sortField 排序字段
	 *        order 排序方式
	 * @param pageNum
	 *        页码
	 * @param pageSize
	 *        每页的条目数量
	 * @return
	 */
	public PageInfo<News> findNewsByMap(Map<String, Object> map, Integer pageNum, Integer pageSize);

	/**
	 * 新增一条新闻信息
	 * 
	 * @param news
	 * @return
	 */
	public News addNews(News news);

	/**
	 * 新增一条图片新闻信息
	 * 
	 * @param news
	 * @return
	 */
	public News addPicNews(News news);

	/**
	 * 修改一条新闻信息
	 * 
	 * @param news
	 * @return
	 */
	public News updateNews(News news);

	/**
	 * 通过id查找新闻
	 * 
	 * @param newsId
	 * @return
	 */
	public News getNewsById(Long newsId);

	/**
	 * 通过id删除新闻
	 * 
	 * @param newsId
	 */
	public void deleteNewsById(Long newsId);

}
