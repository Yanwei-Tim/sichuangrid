package com.tianque.baseInfo.fPersonnel.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.fPersonnel.domain.FPersonnel;
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
import com.tianque.domain.vo.SearchFPersonnelVo;
import com.tianque.service.SearchFPersonnelService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Namespace("/baseinfo/searchFPersonnel")
@Controller("searchFPersonnelController")
@Scope("prototype")
public class SearchFPersonnelController extends SearchBaseAction {
	@Autowired
	private SearchFPersonnelService service;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;

	private SearchFPersonnelVo searchFPersonnelVo;

	private Long organizationId;

	private FPersonnel population;

	private boolean pageOnly;

	private InputStream inputStream;

	@Action(value = "findFPersonnelsByQueryCondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "searchFPersonnel")
	public String searchFPersonnels() {
		if (null == searchFPersonnelVo) {
			searchFPersonnelVo = new SearchFPersonnelVo();
		}
		commonSearch();
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String quickSearchFPersonnels() {

		try {
			if (organizationId == null) {
				this.errorMessage = "组织id不能为空";
				return ERROR;
			}

			if (null == searchFPersonnelVo) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			commonSearch();
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
		return SUCCESS;
	}

	private void commonSearch() {
		Organization org = organizationDubboService.getSimpleOrgById(organizationId);

		if (org != null) {
			searchFPersonnelVo.setOrgInternalCode(org.getOrgInternalCode());
		} else {
			throw new RuntimeException("根据organizationId" + organizationId
					+ ",没有查到该组织");
		}
		PageInfo<FPersonnel> pageInfoListInfo = service.searchFPersonnel(
				searchFPersonnelVo, page, rows, sidx, sord);
		PageInfo<FPersonnel> pageInfo = ControllerHelper
				.processAllOrgRelativeName(pageInfoListInfo,
						organizationDubboService, new String[] { "organization" },
						organizationId);
		gridPage = new GridPage(pageInfo);
	}

	@Action(value = "downloadFPersonnel")
	public String downloadFPersonnel() {
		if (null == organizationId) {
			errorMessage = "所属网格为空，不能导出数据";
			return ERROR;
		}
		if (null == searchFPersonnelVo) {
			searchFPersonnelVo = new SearchFPersonnelVo();
		}
		Organization org = organizationDubboService.getSimpleOrgById(organizationId);
		searchFPersonnelVo.setOrgInternalCode(org.getOrgInternalCode());

		List<FPersonnel> queryQopulationList = getNeedExportDatas(page);

		try {

			inputStream = ExcelExportHelper.exportDateToExcel(
					service.getExportPopertyArray(), queryQopulationList);
			downloadFileName = new String("F人口".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.F_PERSONNEL,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId()) + "导出F",
							ObjectToJSON.convertJSON(new FPersonnel()));
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
			return ERROR;
		}
		return STREAM_SUCCESS;
	}

	@Action(value = "downloadFPersonnelAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchFPersonnelVo == null) {
			searchFPersonnelVo = new SearchFPersonnelVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchFPersonnelVo
				.setOrgInternalCode(organization.getOrgInternalCode());
		try {
			if (!pageOnly) {
				pageOnly = true;
				Integer count = service.getCount(searchFPersonnelVo);
				String[][] excelDefines = service.getExportPopertyArray();
				exportDataAll(count, excelDefines, "F清单");
			}
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.F_PERSONNEL,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId()) + "导出全部F",
							ObjectToJSON.convertJSON(new FPersonnel()));
		} catch (Exception e) {
			errorMessage = e.getMessage();
			e.printStackTrace();
			return;
		}
	}

	public List<FPersonnel> getNeedExportDatas(int page) {
		if (population != null) {
			searchFPersonnelVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			return service.searchFPersonnelForExport(searchFPersonnelVo, page,
					rows, sidx, sord);
		} else {
			return service.searchFPersonnelForExport(searchFPersonnelVo, null,
					null, sidx, sord);
		}
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public SearchFPersonnelVo getSearchFPersonnelVo() {
		return searchFPersonnelVo;
	}

	public void setSearchFPersonnelVo(SearchFPersonnelVo searchFPersonnelVo) {
		this.searchFPersonnelVo = searchFPersonnelVo;
	}

	public FPersonnel getPopulation() {
		return population;
	}

	public void setPopulation(FPersonnel population) {
		this.population = population;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
