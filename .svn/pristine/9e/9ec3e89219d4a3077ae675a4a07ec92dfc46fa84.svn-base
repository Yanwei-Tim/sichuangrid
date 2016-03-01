package com.tianque.baseInfo.internetBar.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.internetBar.domain.InternetBar;
import com.tianque.baseInfo.internetBar.service.InternetBarService;
import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchInternetBarVo;
import com.tianque.excel.definition.InternetBarContext;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Transactional
@Scope("prototype")
@Controller("searchInternetBarController")
@Namespace("/baseinfo/searchInternetBar")
public class SearchInternetBarController extends SearchBaseAction {

	@Autowired
	private InternetBarService internetBarService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	private Long organizationId;
	private SearchInternetBarVo searchInternetBarVo;
	private boolean pageOnly;
	private InternetBar location;

	@PermissionFilter(ename = "searchPublicPlace")
	@Action(value = "findInternetBarsByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findPublicPlacesByQueryCondition() throws Exception {
		if (searchInternetBarVo == null && organizationId == null) {
			this.errorMessage = "参数错误";
		} else {
			searchCommonality();
		}

		return SUCCESS;
	}

	@Action(value = "fastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "igonreHierarch", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String fastSearch() throws Exception {
		if (searchInternetBarVo == null) {
			gridPage = new GridPage(emptyRectificativePersonPage(rows));
			return SUCCESS;
		} else {
			searchCommonality();
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "downInternetBar")
	@Action(value = "downloadInternetBar")
	public String downloadInternetBar() throws Exception {
		if (null == organizationId) {
			this.errorMessage = "无法定位到需要查找的数据";
			return ERROR;
		} else {

			locationOrgCondtion();
			List<InternetBar> records = getNeedExportDatas(page);
			String[][] excelColumArray = InternetBarContext
					.getInternetBarInfoArray();
			inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
					records);
			downloadFileName = new String("网吧清单".getBytes("gbk"), "ISO8859-1")
					+ ".xls";

			systemLogService
					.log(Logger.INFO,
							ModuleConstants.PUBLICPLACE,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId()) + " 网吧",
							ObjectToJSON.convertJSON(searchInternetBarVo));
		}
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downInternetBar")
	@Action(value = "downloadInternetBarAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchInternetBarVo == null) {
			searchInternetBarVo = new SearchInternetBarVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchInternetBarVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = internetBarService.getCount(searchInternetBarVo);
			String[][] excelDefines = InternetBarContext
					.getInternetBarInfoArray();
			exportDataAll(count, excelDefines, "网吧清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.PUBLICPLACE,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 网吧",
						ObjectToJSON.convertJSON(searchInternetBarVo));
	}

	public List<InternetBar> getNeedExportDatas(int page) {
		List<InternetBar> list = new ArrayList<InternetBar>();
		if (pageOnly) {
			list = internetBarService.searchInternetBarForPage(page, rows,
					sidx, sord, searchInternetBarVo).getResult();
		} else {
			list = internetBarService.searchInternetBarForPage(null, rows,
					sidx, sord, searchInternetBarVo).getResult();
		}
		return list;
	}

	private void locationOrgCondtion() {
		if (null == searchInternetBarVo) {
			searchInternetBarVo = new SearchInternetBarVo();
		}
		if (null != organizationId) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null) {
				searchInternetBarVo
						.setOrgInternalCode(org.getOrgInternalCode());
			} else {
				searchInternetBarVo.setOrgInternalCode(null);
			}
		} else {
			searchInternetBarVo.setOrgInternalCode(null);
		}
		if (null != location) {
			searchInternetBarVo.setIsEmphasis(location.getIsEmphasis());
		}
	}

	private void searchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchInternetBarVo.setOrgInternalCode(ownerOrg.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				internetBarService.searchInternetBarForPage(page, rows, sidx,
						sord, searchInternetBarVo), organizationDubboService,
				new String[] { "organization" }, organizationId));
	}

	private PageInfo<PublicPlace> emptyRectificativePersonPage(Integer pageSize) {
		PageInfo<PublicPlace> pageInfo = new PageInfo<PublicPlace>();
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PublicPlace>());
		pageInfo.setTotalRowSize(0);
		return pageInfo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public SearchInternetBarVo getSearchInternetBarVo() {
		return searchInternetBarVo;
	}

	public void setSearchInternetBarVo(SearchInternetBarVo searchInternetBarVo) {
		this.searchInternetBarVo = searchInternetBarVo;
	}

	public InternetBar getLocation() {
		return location;
	}

	public void setLocation(InternetBar location) {
		this.location = location;
	}

}
