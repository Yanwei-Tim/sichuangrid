package com.tianque.baseInfo.youths.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.youths.constants.YouthsType;
import com.tianque.baseInfo.youths.domain.Youths;
import com.tianque.baseInfo.youths.service.YouthsService;
import com.tianque.baseInfo.youths.vo.SearchYouthsVo;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/youthsManage")
@Scope("request")
@Controller("youthsController")
public class YouthsController extends YouthsBaseAction {
	private Long organizationId;
	private String keyType;
	@Autowired
	private YouthsService youthsService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	private boolean pageOnly;

	private SearchYouthsVo searchYouthsVo;

	@Action(value = "dispatchOperate", results = {
			@Result(name = "search", location = "/baseinfo/youths/searchYouthsDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() {

		return "search";
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "youngstersManagement", actionName = "findYouthsList"),
			@PermissionFilter(ename = "youngpioneerManagement", actionName = "findYouthsList"),
			@PermissionFilter(ename = "leaguememberManagement", actionName = "findYouthsList") })
	@Action(value = "findYouthsList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findGridPageByOrgId() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			searchYouthsVo.setOrganization(organizationDubboService
					.getSimpleOrgById(organizationId));
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					youthsService.findYouthsForPage(page, rows, sidx, sord,
							searchYouthsVo), organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "searchYoungsters", actionName = "searchYouthsList"),
			@PermissionFilter(ename = "searchYoungpioneer", actionName = "searchYouthsList"),
			@PermissionFilter(ename = "searchLeaguemember", actionName = "searchYouthsList") })
	@Action(value = "searchYouthsList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String searchGridPageByOrgId() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			searchYouthsVo.setOrganization(organizationDubboService
					.getSimpleOrgById(organizationId));
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					youthsService.findYouthsForPage(page, rows, sidx, sord,
							searchYouthsVo), organizationDubboService,
					new String[] { "organization" }, organizationId));
		}
		return SUCCESS;
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "downYoungsters", actionName = "downloadYouths"),
			@PermissionFilter(ename = "downYoungpioneer", actionName = "downloadYouths"),
			@PermissionFilter(ename = "downLeaguemember", actionName = "downloadYouths") })
	@Action("downloadYouths")
	public String downloadYouths() throws Exception {
		if (null == organizationId || null == searchYouthsVo) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		String title = YouthsType.getTypeName(keyType);
		searchYouthsVo.setOrganization(organizationDubboService
				.getSimpleOrgById(organizationId));
		List<Youths> youthsList = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				SpecialGroupsContext.getYouthsPropertyArray(), youthsList);
		downloadFileName = new String((title + "清单").getBytes("gbk"),
				"ISO8859-1") + ".xls";
		systemLogService
				.log(com.tianque.core.logger.Logger.INFO,
						"青少年",
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出"
								+ title, ObjectToJSON.convertJSON(new Youths()));
		return STREAM_SUCCESS;
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "downYoungsters", actionName = "downloadYouthsAll"),
			@PermissionFilter(ename = "downYoungpioneer", actionName = "downloadYouthsAll"),
			@PermissionFilter(ename = "downLeaguemember", actionName = "downloadYouthsAll") })
	@Action(value = "downloadYouthsAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadYouthsAll() throws Exception {
		if (null == organizationId || null == searchYouthsVo) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		String title = YouthsType.getTypeName(keyType);
		searchYouthsVo.setOrganization(organizationDubboService
				.getSimpleOrgById(organizationId));
		if (!pageOnly) {
			pageOnly = true;
			Integer count = youthsService.getYouthsCount(searchYouthsVo);
			String[][] excelDefines = SpecialGroupsContext
					.getYouthsPropertyArray();
			exportDataAll(count, excelDefines, title + "清单");
		}
		systemLogService
				.log(com.tianque.core.logger.Logger.INFO,
						"青少年",
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出"
								+ title, ObjectToJSON.convertJSON(new Youths()));
	}

	@Override
	public List<Youths> getNeedExportDatas(int page) {
		List<Youths> youthsList = new ArrayList<Youths>();
		if (pageOnly) {
			youthsList = youthsService.findYouthsForPage(page, rows, sidx,
					sord, searchYouthsVo).getResult();
		} else {
			youthsList = youthsService.findYouthsForPage(null, 0, sidx, sord,
					searchYouthsVo).getResult();
		}
		return youthsList;
	}

	private PageInfo<Youths> emptyPage(int pageSize) {
		PageInfo<Youths> pageInfo = new PageInfo<Youths>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Youths>());
		return pageInfo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public SearchYouthsVo getSearchYouthsVo() {
		return searchYouthsVo;
	}

	public void setSearchYouthsVo(SearchYouthsVo searchYouthsVo) {
		this.searchYouthsVo = searchYouthsVo;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

}
