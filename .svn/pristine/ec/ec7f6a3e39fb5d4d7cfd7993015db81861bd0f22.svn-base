package com.tianque.baseInfo.druggy.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.druggy.domain.Druggy;
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
import com.tianque.domain.vo.SearchDruggyVo;
import com.tianque.service.SearchDruggyService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Transactional
@Scope("prototype")
@Controller("searchDruggyController")
@Namespace("/baseinfo/searchDruggy")
public class SearchDruggyController extends SearchBaseAction {

	@Autowired
	private SearchDruggyService searchDruggyService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;

	private Long organizationId;

	private SearchDruggyVo searchDruggyVo;

	private boolean pageOnly;

	private Druggy population;

	/**
	 * 根据查询条件分页查询吸毒人员
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "searchDruggy")
	@Action(value = "findDruggysByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findDruggysByQueryCondition() throws Exception {
		if (organizationId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (searchDruggyVo == null) {
			searchDruggyVo = new SearchDruggyVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchDruggyVo.setOrgInternalCode(organization.getOrgInternalCode());
		PageInfo<Druggy> pageInfo = ControllerHelper.processAllOrgRelativeName(
				searchDruggyService.findDruggysByQueryCondition(searchDruggyVo,
						page, rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearch() throws Exception {
		if (searchDruggyVo == null) {
			gridPage = new GridPage(emptyDruggyPage(rows));
			return SUCCESS;
		} else {
			fastSearchCommonality();
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "downDruggy")
	@Action(value = "downloadDruggy")
	public String downloadDruggy() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		if (searchDruggyVo == null) {
			searchDruggyVo = new SearchDruggyVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchDruggyVo.setOrgInternalCode(organization.getOrgInternalCode());
		List<Druggy> records = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				searchDruggyService.getExportPopertyArray(), records);
		downloadFileName = new String("吸毒人员清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.DRUGGY,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 导出吸毒人员",
						ObjectToJSON.convertJSON(searchDruggyVo));
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downDruggy")
	@Action(value = "downloadDruggyAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (null == searchDruggyVo) {
			searchDruggyVo = new SearchDruggyVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchDruggyVo.setOrgInternalCode(organization.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = searchDruggyService.getCount(searchDruggyVo);
			String[][] excelDefines = searchDruggyService
					.getExportPopertyArray();
			exportDataAll(count, excelDefines, "吸毒人员清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.DRUGGY,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 导出吸毒人员",
						ObjectToJSON.convertJSON(searchDruggyVo));
	}

	public List<Druggy> getNeedExportDatas(int page) {
		List<Druggy> records = new ArrayList<Druggy>();
		if (population != null) {
			searchDruggyVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			records = searchDruggyService.searchDruggysForExport(
					searchDruggyVo, page, rows, sidx, sord);
		} else {
			records = searchDruggyService.searchDruggysForExport(
					searchDruggyVo, null, null, sidx, sord);
		}
		return records;
	}

	private void fastSearchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchDruggyVo.setOrgInternalCode(ownerOrg.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchDruggyService.fastSearchDruggy(searchDruggyVo, page,
						rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, organizationId));
	}

	private PageInfo<Druggy> emptyDruggyPage(int pageSize) {
		PageInfo<Druggy> pageInfo = new PageInfo<Druggy>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Druggy>());
		return pageInfo;
	}

	// ----------------------------------

	// ----------------------------------

	public SearchDruggyVo getSearchDruggyVo() {
		return searchDruggyVo;
	}

	public void setSearchDruggyVo(SearchDruggyVo searchDruggyVo) {
		this.searchDruggyVo = searchDruggyVo;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Druggy getPopulation() {
		return population;
	}

	public void setPopulation(Druggy population) {
		this.population = population;
	}
}
