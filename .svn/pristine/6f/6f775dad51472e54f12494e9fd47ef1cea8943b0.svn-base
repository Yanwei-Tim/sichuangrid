package com.tianque.gis.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.gis.domain.vo.IssueNewsVo;
import com.tianque.gis.service.GisService;
import com.tianque.gis.service.SearchGisIssueNewsService;

@Controller("gisIssueNewsController")
@Scope("request")
@Transactional
@Namespace("/gis/gisIssueNewsManager")
public class GisIssueNewsController extends BaseAction {
	@Autowired
	private SearchGisIssueNewsService searchGisIssueNewsService;
	@Autowired
	private GisService gisService;
	private String queryStr;
	private Long orgId;
	private Long issueId;
	private String issueNewsType;
	private String issueNewsState;
	private PageInfo<IssueNewsVo> issuePage;
	private List<IssueNewsVo> issueNewsList;
	private IssueNewsVo issueNewsVo;
	private GisInfo gisInfo;
	private String currentTime;
	private Boolean showed;

	@Action(value = "bindIssueOnMap", results = { @Result(name = "success", params = { "root",
			"true", "ignoreHierarchy", "false" }, type = "json") })
	public String bindIssueOnMap() {
		gisService.bindIssueOnMap(issueId, gisInfo);
		return SUCCESS;
	}

	@Action(value = "unBindIssueOnMap", results = {
			@Result(name = "success", params = { "root", "true", "ignoreHierarchy", "false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String unBindIssueOnMap() {
		gisService.unBindIssueOnMap(issueId);
		return SUCCESS;
	}

	/**
	 * 根据orgId和查询条件查询事件处理
	 * 
	 * @return success
	 */
	@Action(value = "issueNewsListSearchByQueryStrAndOrgId", results = {
			@Result(name = "success", params = { "root", "gridPage", "ignoreHierarchy", "flase",
					"excludeNullProperties", "true" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String issueNewsListSearchByQueryStrAndOrgId() {
		issuePage = searchGisIssueNewsService.issueNewsListSearchByQueryStrAndOrgId(orgId,
				queryStr, page, rows, sidx, sord);
		gridPage = new GridPage<IssueNewsVo>(issuePage);
		return SUCCESS;
	}

	/**
	 * 根据orgId和事件类型查找事件处理
	 * return success
	 */
	@Action(value = "searchKeyIssueNewsListByOrgId", results = {
			@Result(name = "success", params = { "root", "gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "type" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String searchKeyIssueNewsListByOrgId() {
		issuePage = searchGisIssueNewsService.searchKeyIssueNewsListByOrgId(orgId, issueNewsType,
				page, rows, sidx, sord);
		gridPage = new GridPage<IssueNewsVo>(issuePage);
		return SUCCESS;
	}

	/**
	 * gis事件处理二次过滤
	 * 
	 * @return
	 */
	@Action(value = "gisIssueNewsFutureSearchByorgId", results = {
			@Result(name = "success", params = { "root", "gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "type" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String gisIssueNewsFutureSearchByorgId() {
		issuePage = searchGisIssueNewsService.gisIssueNewsFutureSearchByOrgId(orgId, issueNewsType,
				issueNewsState, page, rows, sidx, sord);
		gridPage = new GridPage<IssueNewsVo>(issuePage);
		return SUCCESS;
	}

	/**
	 * 根据orgId和issueId查询事件详细信息
	 * 
	 * @return
	 */
	@Action(value = "getIssueNewsDatialInfoByIssueId", results = {
			@Result(name = "success", location = "/gis3D/issues/issueViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String IssueNewsDatialInfoByIssueId() {
		if (null != orgId) {
			issueNewsList = searchGisIssueNewsService.getIssueNewsDatialInfoByIssueId(orgId,
					issueId);
			issueNewsVo = issueNewsList.get(0);
			issueNewsVo.setIssueId(issueId);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	/**
	 * 根据orgId获取所有事件在同层上显示
	 * 
	 * @return
	 */
	@Action(value = "listAllBindingissueNewsVo", results = {
			@Result(name = "success", params = { "root", "issueNewsList", "ignoreHierarchy",
					"false" }, type = "json"),
			@Result(name = "error", params = { "root", "errorMessage" }, type = "json") })
	public String listAllBindingissueNewsVo() {
		if (null == orgId) {
			errorMessage = "请选择组织机构";
			return ERROR;
		}
		issueNewsList = searchGisIssueNewsService.findAllBindingIssueNewsByOrgId(orgId);
		return SUCCESS;
	}

	public String getQueryStr() {
		return queryStr;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public PageInfo<IssueNewsVo> getIssuePage() {
		return issuePage;
	}

	public void setIssuePage(PageInfo<IssueNewsVo> issuePage) {
		this.issuePage = issuePage;
	}

	public List<IssueNewsVo> getIssueNewsList() {
		return issueNewsList;
	}

	public void setIssueNewsList(List<IssueNewsVo> issueNewsList) {
		this.issueNewsList = issueNewsList;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public String getIssueNewsType() {
		return issueNewsType;
	}

	public void setIssueNewsType(String issueNewsType) {
		this.issueNewsType = issueNewsType;
	}

	public IssueNewsVo getIssueNewsVo() {
		return issueNewsVo;
	}

	public void setIssueNewsVo(IssueNewsVo issueNewsVo) {
		this.issueNewsVo = issueNewsVo;
	}

	public String getIssueNewsState() {
		return issueNewsState;
	}

	public void setIssueNewsState(String issueNewsState) {
		this.issueNewsState = issueNewsState;
	}

	public GisInfo getGisInfo() {
		return gisInfo;
	}

	public void setGisInfo(GisInfo gisInfo) {
		this.gisInfo = gisInfo;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setShowed(Boolean showed) {
		this.showed = showed;
	}

	public Boolean getShowed() {
		return showed;
	}
}
