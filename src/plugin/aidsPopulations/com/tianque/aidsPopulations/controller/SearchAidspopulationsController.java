package com.tianque.aidsPopulations.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.aidsPopulations.domain.Aidspopulations;
import com.tianque.aidsPopulations.domain.vo.SearchAidspopulationsVo;
import com.tianque.aidsPopulations.service.SearchAidspopulationsService;
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
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Transactional
@Scope("prototype")
@Controller("searchAidspopulationsController")
@Namespace("/baseinfo/searchAidspopulations")
public class SearchAidspopulationsController extends SearchBaseAction {

	@Autowired
	private SearchAidspopulationsService searchAidspopulationsService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;

	private Long organizationId;

	private SearchAidspopulationsVo searchAidspopulationsVo;

	private boolean pageOnly;

	private Aidspopulations population;

	/**
	 * 根据查询条件分页查询艾滋病人员，即高级搜索
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "searchAidspopulations")
	@Action(value = "findAidspopulationssByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findAidspopulationsByQueryCondition() throws Exception {
		if (organizationId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (searchAidspopulationsVo == null) {
			searchAidspopulationsVo = new SearchAidspopulationsVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchAidspopulationsVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<Aidspopulations> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchAidspopulationsService
						.searchAidspopulations(searchAidspopulationsVo, page,
								rows, sidx, sord), organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 快速检索 ，即搜索
	 * 
	 * @return
	 */
	@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearch() throws Exception {
		if (searchAidspopulationsVo == null) {
			gridPage = new GridPage(emptyAidspopulationsPage(rows));
			return SUCCESS;
		} else {
			fastSearchCommonality();
		}
		return SUCCESS;
	}

	/**
	 * 导出艾滋病人员
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "downAidspopulations")
	@Action(value = "downloadAidspopulations")
	public String downloadAidspopulations() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		if (searchAidspopulationsVo == null) {
			searchAidspopulationsVo = new SearchAidspopulationsVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchAidspopulationsVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		List<Aidspopulations> records = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				searchAidspopulationsService.getExportPopertyArray(), records);
		downloadFileName = new String("艾滋病人员清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.AIDSPOPULATIONS,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 导出艾滋病人员",
						ObjectToJSON.convertJSON(searchAidspopulationsVo));
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downAidspopulations")
	@Action(value = "downloadAidspopulationsAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchAidspopulationsVo == null) {
			searchAidspopulationsVo = new SearchAidspopulationsVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchAidspopulationsVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = searchAidspopulationsService
					.getCount(searchAidspopulationsVo);
			String[][] excelDefines = searchAidspopulationsService
					.getExportPopertyArray();
			exportDataAll(count, excelDefines, "艾滋病人员清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.AIDSPOPULATIONS,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 导出艾滋病人员",
						ObjectToJSON.convertJSON(searchAidspopulationsVo));
	}

	/**
	 * 获取要导出的数据
	 * 
	 * @return
	 */
	public List<Aidspopulations> getNeedExportDatas(int page) {
		List<Aidspopulations> list = new ArrayList<Aidspopulations>();
		if (population != null) {
			searchAidspopulationsVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			list = searchAidspopulationsService.searchAidspopulationsForExport(
					searchAidspopulationsVo, page, rows, sidx, sord);
		} else {
			list = searchAidspopulationsService.searchAidspopulationsForExport(
					searchAidspopulationsVo, null, null, sidx, sord);
		}
		return list;
	}

	private void fastSearchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchAidspopulationsVo.setOrgInternalCode(ownerOrg
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchAidspopulationsService.searchAidspopulations(
						searchAidspopulationsVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				organizationId));
	}

	private PageInfo<Aidspopulations> emptyAidspopulationsPage(int pageSize) {
		PageInfo<Aidspopulations> pageInfo = new PageInfo<Aidspopulations>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Aidspopulations>());
		return pageInfo;
	}

	public SearchAidspopulationsVo getSearchAidspopulationsVo() {
		return searchAidspopulationsVo;
	}

	public void setSearchAidspopulationsVo(
			SearchAidspopulationsVo searchAidspopulationsVo) {
		this.searchAidspopulationsVo = searchAidspopulationsVo;
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

	public Aidspopulations getPopulation() {
		return population;
	}

	public void setPopulation(Aidspopulations population) {
		this.population = population;
	}
}
