package com.tianque.baseInfo.aidNeedPopulation.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.aidNeedPopulation.domain.AidNeedPopulation;
import com.tianque.baseInfo.aidNeedPopulation.service.SearchAidNeedPopulationService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchAidNeedPopulationVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@SuppressWarnings("serial")
@Controller("searchAidNeedPopulationController")
@Namespace("/baseinfo/searchAidNeedPopulation")
@Scope("prototype")
public class SearchAidNeedPopulationController extends SearchBaseAction {

	private SearchAidNeedPopulationVo searchAidNeedPopulationVo;
	@Autowired
	private SearchAidNeedPopulationService searchAidNeedPopulationService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;

	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();

	private Long organizationId;

	private String issueSerch = "";

	private boolean pageOnly;
	private AidNeedPopulation population;

	@PermissionFilter(ename = "searchAidNeedPopulation")
	@Action(value = "findAidNeedPopulationsByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String searchAidNeedPopulation() throws Exception {
		if (searchAidNeedPopulationVo == null) {
			searchAidNeedPopulationVo = new SearchAidNeedPopulationVo();
		}
		searchCommonality();
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearch() throws Exception {
		if (searchAidNeedPopulationVo == null) {
			gridPage = new GridPage(new PageInfo<AidNeedPopulation>());
			return SUCCESS;
		} else {
			searchCommonality();
		}
		return SUCCESS;
	}

	private void searchCommonality() {
		if (issueSerch.equalsIgnoreCase("issueSerch")) {
			organizationId = organizationDubboService
					.getTownOrganizationId(organizationId);
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchAidNeedPopulationVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<AidNeedPopulation> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchAidNeedPopulationService
						.searchAidNeedPopulation(searchAidNeedPopulationVo,
								page, rows, sidx, sord), organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
	}

	@PermissionFilter(ename = "downAidNeedPopulation")
	@Action(value = "downloadAidNeedPopulation")
	public String downloadAidNeedPopulation() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		if (searchAidNeedPopulationVo == null) {
			searchAidNeedPopulationVo = new SearchAidNeedPopulationVo();
		}
		Organization org = organizationDubboService.getSimpleOrgById(organizationId);
		searchAidNeedPopulationVo.setOrgInternalCode(org.getOrgInternalCode());
		List<AidNeedPopulation> records = getNeedExportDatas(page);
		inputStream = ExcelExportHelper
				.exportDateToExcel(
						searchAidNeedPopulationService.getExportPopertyArray(),
						records);
		downloadFileName = new String("需救助人员清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.HOUSEHOLDSTAFF,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出需救助人员",
						ObjectToJSON.convertJSON(searchAidNeedPopulationVo));
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downAidNeedPopulation")
	@Action(value = "downloadAidNeedPopulationAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchAidNeedPopulationVo == null) {
			searchAidNeedPopulationVo = new SearchAidNeedPopulationVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchAidNeedPopulationVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = searchAidNeedPopulationService
					.getCount(searchAidNeedPopulationVo);
			String[][] excelDefines = searchAidNeedPopulationService
					.getExportPopertyArray();
			exportDataAll(count, excelDefines, "需救助人员清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.HOUSEHOLDSTAFF,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出需救助人员",
						ObjectToJSON.convertJSON(searchAidNeedPopulationVo));
	}

	public List<AidNeedPopulation> getNeedExportDatas(int page) {
		List<AidNeedPopulation> list = new ArrayList<AidNeedPopulation>();
		if (population != null) {
			searchAidNeedPopulationVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			list = searchAidNeedPopulationService
					.searchAidNeedPopulationForExport(
							searchAidNeedPopulationVo, page, rows, sidx, sord);
		} else {
			list = searchAidNeedPopulationService
					.searchAidNeedPopulationForExport(
							searchAidNeedPopulationVo, null, null, sidx, sord);
		}
		return list;
	}

	public String getIssueSerch() {
		return issueSerch;
	}

	public void setIssueSerch(String issueSerch) {
		this.issueSerch = issueSerch;
	}

	public List<AutoCompleteData> getAutoCompleteDatas() {
		return autoCompleteDatas;
	}

	public void setAutoCompleteDatas(List<AutoCompleteData> autoCompleteDatas) {
		this.autoCompleteDatas = autoCompleteDatas;
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

	public SearchAidNeedPopulationVo getSearchAidNeedPopulationVo() {
		return searchAidNeedPopulationVo;
	}

	public void setSearchAidNeedPopulationVo(
			SearchAidNeedPopulationVo searchAidNeedPopulationVo) {
		this.searchAidNeedPopulationVo = searchAidNeedPopulationVo;
	}

	public AidNeedPopulation getPopulation() {
		return population;
	}

	public void setPopulation(AidNeedPopulation population) {
		this.population = population;
	}

}
