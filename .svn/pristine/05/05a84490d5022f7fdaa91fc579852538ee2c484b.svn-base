package com.tianque.party.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.party.dao.NewsDao;
import com.tianque.party.domain.News;
import com.tianque.party.service.NewsService;
import com.tianque.service.impl.LogableService;

@Service("newsServiceImpl")
public class NewsServiceImpl extends LogableService implements NewsService {

	@Resource(name = "newsDaoImpl")
	private NewsDao newsDaoImpl;

	@Override
	public PageInfo<News> findNewsByMap(Map<String, Object> map, Integer pageNum, Integer pageSize) {
		return newsDaoImpl.findNewsByMap(map, pageNum, pageSize);
	}

	@Override
	public News addNews(News news) {
		return newsDaoImpl.addNews(news);
	}

	@Override
	public News updateNews(News news) {
		return newsDaoImpl.updateNews(news);
	}

	@Override
	public News getNewsById(Long newsId) {
		return newsDaoImpl.getNewsById(newsId);
	}

	@Override
	public void deleteNewsById(Long newsId) {
		newsDaoImpl.deleteNewsById(newsId);
	}

	@Override
	public News addPicNews(News news) {
		return newsDaoImpl.addPicNews(news);
	}

	@Override
	public News updatePicNews(News news) {
		return newsDaoImpl.updateNews(news);
	}

}
