package com.tianque.baseInfo.mPersonnel.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.mPersonnel.domain.MPersonnel;
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
import com.tianque.domain.vo.SearchMPersonnelVo;
import com.tianque.service.SearchMPersonnelService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Namespace("/baseinfo/searchMPersonnel")
@Controller("searchMPersonnelController")
@Scope("prototype")
public class SearchMPersonnelController extends SearchBaseAction {
	@Autowired
	private SearchMPersonnelService service;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;

	private SearchMPersonnelVo searchMPersonnelVo;

	private Long organizationId;

	private MPersonnel population;

	private boolean pageOnly;

	private InputStream inputStream;

	@Action(value = "findMPersonnelsByQueryCondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "searchMPersonnel")
	public String searchMPersonnels() {
		if (null == searchMPersonnelVo) {
			searchMPersonnelVo = new SearchMPersonnelVo();
		}
		commonSearch();
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String quickSearchMPersonnels() {

		try {
			if (organizationId == null) {
				this.errorMessage = "组织id不能为空";
				return ERROR;
			}

			if (null == searchMPersonnelVo) {
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
			searchMPersonnelVo.setOrgInternalCode(org.getOrgInternalCode());
		} else {
			throw new RuntimeException("根据organizationId" + organizationId
					+ ",没有查到该组织");
		}
		PageInfo<MPersonnel> pageInfoListInfo = service.searchMPersonnel(
				searchMPersonnelVo, page, rows, sidx, sord);
		PageInfo<MPersonnel> pageInfo = ControllerHelper
				.processAllOrgRelativeName(pageInfoListInfo,
						organizationDubboService, new String[] { "organization" },
						organizationId);
		gridPage = new GridPage(pageInfo);
	}

	@Action(value = "downloadMPersonnel")
	public String downloadMPersonnel() {
		if (null == organizationId) {
			errorMessage = "所属网格为空，不能导出数据";
			return ERROR;
		}
		if (null == searchMPersonnelVo) {
			searchMPersonnelVo = new SearchMPersonnelVo();
		}
		Organization org = organizationDubboService.getSimpleOrgById(organizationId);
		searchMPersonnelVo.setOrgInternalCode(org.getOrgInternalCode());

		List<MPersonnel> queryQopulationList = getNeedExportDatas(page);

		try {

			inputStream = ExcelExportHelper.exportDateToExcel(
					service.getExportPopertyArray(), queryQopulationList);
			downloadFileName = new String("M人口".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.M_PERSONNEL,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId()) + "导出M",
							ObjectToJSON.convertJSON(new MPersonnel()));
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
			return ERROR;
		}
		return STREAM_SUCCESS;
	}

	@Action(value = "downloadMPersonnelAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchMPersonnelVo == null) {
			searchMPersonnelVo = new SearchMPersonnelVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchMPersonnelVo
				.setOrgInternalCode(organization.getOrgInternalCode());
		try {
			if (!pageOnly) {
				pageOnly = true;
				Integer count = service.getCount(searchMPersonnelVo);
				String[][] excelDefines = service.getExportPopertyArray();
				exportDataAll(count, excelDefines, "M清单");
			}
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.M_PERSONNEL,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId()) + "导出全部M",
							ObjectToJSON.convertJSON(new MPersonnel()));
		} catch (Exception e) {
			errorMessage = e.getMessage();
			e.printStackTrace();
			return;
		}
	}

	public List<MPersonnel> getNeedExportDatas(int page) {
		if (population != null) {
			searchMPersonnelVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			return service.searchMPersonnelForExport(searchMPersonnelVo, page,
					rows, sidx, sord);
		} else {
			return service.searchMPersonnelForExport(searchMPersonnelVo, null,
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

	public SearchMPersonnelVo getSearchMPersonnelVo() {
		return searchMPersonnelVo;
	}

	public void setSearchMPersonnelVo(SearchMPersonnelVo searchMPersonnelVo) {
		this.searchMPersonnelVo = searchMPersonnelVo;
	}

	public MPersonnel getPopulation() {
		return population;
	}

	public void setPopulation(MPersonnel population) {
		this.population = population;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
