package com.tianque.party.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.party.dao.NewsDao;
import com.tianque.party.domain.News;

@Repository("newsDaoImpl")
public class NewsDaoImpl extends AbstractBaseDao implements NewsDao {

	@Override
	public PageInfo<News> findNewsByMap(Map<String, Object> map, Integer pageNum, Integer pageSize) {
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"news.countNewsByMap", map);
		int pageCount = 0;
		if ((countNum % pageSize) == 0) {
			pageCount = countNum / pageSize;
		} else {
			pageCount = countNum / pageSize + 1;
		}
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		List<News> list = getSqlMapClientTemplate().queryForList("news.getNewsByMap", map,
				(pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<News> createPageInfo(int pageNum, int pageSize, Integer countNum, List list) {
		PageInfo<News> pageInfo = new PageInfo<News>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public News addNews(News news) {
		Long id = (Long) getSqlMapClientTemplate().insert("news.addNews", news);
		return getNewsById(id);
	}

	@Override
	public News updateNews(News news) {
		getSqlMapClientTemplate().update("news.updateNews", news);
		return getNewsById(news.getId());
	}

	@Override
	public News getNewsById(Long newsId) {
		News news = (News) getSqlMapClientTemplate().queryForObject("news.getNewsById", newsId);
		// if(news.getNewsType().equals(NewsType.news)){
		// return (News) getSqlMapClientTemplate().queryForObject("news.getNewsById", newsId);
		// }else if(news.getNewsType().equals(NewsType.pictureNews)){
		// return (PictureNews)
		// getSqlMapClientTemplate().queryForObject("news.getPicNewsById",newsId);
		// }else {
		// return null;
		// }
		// return (News) getSqlMapClientTemplate().queryForObject("news.getNewsById", newsId);
		return news;
	}

	@Override
	public void deleteNewsById(Long newsId) {
		getSqlMapClientTemplate().delete("news.deleteNewsById", newsId);
	}

	@Override
	public News addPicNews(News news) {
		Long id = (Long) getSqlMapClientTemplate().insert("news.addPicNews", news);
		return getNewsById(id);
	}

}
