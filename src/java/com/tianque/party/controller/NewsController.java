package com.tianque.party.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.constants.NewsType;
import com.tianque.party.domain.News;
import com.tianque.party.domain.PictureNews;
import com.tianque.party.service.NewsService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("pictureNewsController")
@Scope("prototype")
@Namespace("/partyBuilding/newsManage")
@Transactional
public class NewsController extends BaseAction {

	private News news;
	private PictureNews pictureNews;
	private List<PictureNews> pictureNewsList;
	private Long organizationId;
	private Long newsId;
	private List<News> newsList;
	private String newsType;// 新闻类型

	@Autowired
	private NewsService newsService;
	@Autowired
	protected OrganizationDubboService organizationDubboService;

	@Action(value = "dispatchOperate", results = {
			@Result(name = "maintantPicNews", location = "/partyBuilding/news/picNews/maintancePicNews.jsp"),
			@Result(name = "maintantNews", location = "/partyBuilding/news/addNews.jsp"),
			@Result(name = "viewPicNews", location = "/partyBuilding/news/picNews/viewPicNews.jsp"),
			@Result(name = "detail", location = "/partyBuilding/news/detail.jsp") })
	public String dispatch() throws Exception {
		// 修改
		if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			news = newsService.getNewsById(newsId);

			newsType = news.getNewsType();
			if (NewsType.NEWS.equals(newsType)) {
				return "maintantNews";
			} else if (NewsType.PIC_NEWS.equals(newsType)) {
				pictureNews = (PictureNews) news;
				return "maintantPicNews";
			}
			// 添加
		} else if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			if (NewsType.NEWS.equals(newsType)) {
				news = new News();
				news.setPublishDate(new Date());
				news.setPublishUser(ThreadVariable.getSession()
						.getUserRealName());
				return "maintantNews";
			} else if (NewsType.PIC_NEWS.equals(newsType)) {
				pictureNews = new PictureNews();
				pictureNews.setPublishDate(new Date());
				pictureNews.setPublishUser(ThreadVariable.getSession()
						.getUserRealName());
				return "maintantPicNews";
			}
			// 查看
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			pictureNews = (PictureNews) newsService.getNewsById(newsId);
			return "detail";
		}

		return ERROR;
	}

	@Action(value = "savePictrueNews", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String savePictrueNews() throws Exception {
		pictureNews
				.setPublishOrg(ThreadVariable.getSession().getOrganization());
		if ("add".equalsIgnoreCase(mode)) {
			pictureNews = (PictureNews) newsService.addPicNews(pictureNews);
		}
		if ("edit".equalsIgnoreCase(mode)) {
			pictureNews = (PictureNews) newsService.updatePicNews(pictureNews);
		}
		return "success";
	}

	@Action(value = "deleteNews", results = { @Result(name = "success", type = "json") })
	public String deleteNews() throws Exception {
		newsService.deleteNewsById(newsId);
		return SUCCESS;
	}

	/*
	 * @Action(value = "addNews", results = {
	 * 
	 * @Result(type = "json", name = "success", params = {"root", "true" }) })
	 * public String addNews() {
	 * news.setPublishOrg(ThreadVariable.getSession().getOrganization());
	 * news=newsService.addNews(news); return "success"; }
	 */

	@Action(value = "saveNews", results = {
			@Result(type = "json", name = "success", params = { "root", "true" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String saveNews() throws Exception {
		news.setPublishOrg(ThreadVariable.getSession().getOrganization());
		if ("add".equalsIgnoreCase(mode)) {
			news = newsService.addNews(news);
		}
		if ("edit".equalsIgnoreCase(mode)) {
			news = newsService.updateNews(news);
		}

		return SUCCESS;
	}

	@Action(value = "findPicNewsForHomePage", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findPicNewsForHomePage() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", organizationId);
		map.put("newsType", NewsType.PIC_NEWS);
		PageInfo<News> pageInfo = newsService.findNewsByMap(map, 1, 4);
		gridPage = new GridPage(pageInfo);
		return "success";
	}

	@Action(value = "list", results = {
	// @Result(type = "json", name = "success", params = {"root", "newsList",
	// "ignoreHierarchy",
	// "false" })
	@Result(name = "success", location = "/partyBuilding/news/newsListDlg.jsp") })
	public String list() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", organizationId);
		map.put("newsType", newsType);
		// map.put("sortField", "publishDate");
		// map.put("order", "desc");
		PageInfo<News> pageInfo;

		pageInfo = ControllerHelper.processAllOrgRelativeName(
				newsService.findNewsByMap(map, page, rows),
				organizationDubboService, new String[] { "organization" },
				organizationId);
		gridPage = new GridPage(pageInfo);
		return "success";
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public List<PictureNews> getPictureNewsList() {
		return pictureNewsList;
	}

	public void setPictureNewsList(List<PictureNews> pictureNewsList) {
		this.pictureNewsList = pictureNewsList;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public PictureNews getPictureNews() {
		return pictureNews;
	}

	public void setPictureNews(PictureNews pictureNews) {
		this.pictureNews = pictureNews;
	}

}
