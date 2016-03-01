package com.tianque.baseInfo.qPersonnel.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.qPersonnel.domain.QPersonnel;
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
import com.tianque.domain.vo.SearchQPersonnelVo;
import com.tianque.service.SearchQPersonnelService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Namespace("/baseinfo/searchQPersonnel")
@Controller("searchQPersonnelController")
@Scope("prototype")
public class SearchQPersonnelController extends SearchBaseAction {
	@Autowired
	private SearchQPersonnelService service;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;

	private SearchQPersonnelVo searchQPersonnelVo;

	private Long organizationId;

	private QPersonnel population;

	private boolean pageOnly;

	private InputStream inputStream;

	@Action(value = "findQPersonnelsByQueryCondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "searchQPersonnel")
	public String searchQPersonnels() {
		if (null == searchQPersonnelVo) {
			searchQPersonnelVo = new SearchQPersonnelVo();
		}
		commonSearch();
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String quickSearchQPersonnels() {

		try {
			if (organizationId == null) {
				this.errorMessage = "组织id不能为空";
				return ERROR;
			}

			if (null == searchQPersonnelVo) {
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
			searchQPersonnelVo.setOrgInternalCode(org.getOrgInternalCode());
		} else {
			throw new RuntimeException("根据organizationId" + organizationId
					+ ",没有查到该组织");
		}
		PageInfo<QPersonnel> pageInfoListInfo = service.searchQPersonnel(
				searchQPersonnelVo, page, rows, sidx, sord);
		PageInfo<QPersonnel> pageInfo = ControllerHelper
				.processAllOrgRelativeName(pageInfoListInfo,
						organizationDubboService, new String[] { "organization" },
						organizationId);
		gridPage = new GridPage(pageInfo);
	}

	@Action(value = "downloadQPersonnel")
	public String downloadQPersonnel() {
		if (null == organizationId) {
			errorMessage = "所属网格为空，不能导出数据";
			return ERROR;
		}
		if (null == searchQPersonnelVo) {
			searchQPersonnelVo = new SearchQPersonnelVo();
		}
		Organization org = organizationDubboService.getSimpleOrgById(organizationId);
		searchQPersonnelVo.setOrgInternalCode(org.getOrgInternalCode());

		List<QPersonnel> queryQopulationList = getNeedExportDatas(page);

		try {

			inputStream = ExcelExportHelper.exportDateToExcel(
					service.getExportPopertyArray(), queryQopulationList);
			downloadFileName = new String("Q人口".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.Q_PERSONNEL,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId()) + "导出Q",
							ObjectToJSON.convertJSON(new QPersonnel()));
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
			return ERROR;
		}
		return STREAM_SUCCESS;
	}

	@Action(value = "downloadQPersonnelAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchQPersonnelVo == null) {
			searchQPersonnelVo = new SearchQPersonnelVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchQPersonnelVo
				.setOrgInternalCode(organization.getOrgInternalCode());
		try {
			if (!pageOnly) {
				pageOnly = true;
				Integer count = service.getCount(searchQPersonnelVo);
				String[][] excelDefines = service.getExportPopertyArray();
				exportDataAll(count, excelDefines, "Q清单");
			}
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.Q_PERSONNEL,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId()) + "导出全部Q",
							ObjectToJSON.convertJSON(new QPersonnel()));
		} catch (Exception e) {
			errorMessage = e.getMessage();
			e.printStackTrace();
			return;
		}
	}

	public List<QPersonnel> getNeedExportDatas(int page) {
		if (population != null) {
			searchQPersonnelVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			return service.searchQPersonnelForExport(searchQPersonnelVo, page,
					rows, sidx, sord);
		} else {
			return service.searchQPersonnelForExport(searchQPersonnelVo, null,
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

	public SearchQPersonnelVo getSearchQPersonnelVo() {
		return searchQPersonnelVo;
	}

	public void setSearchQPersonnelVo(SearchQPersonnelVo searchQPersonnelVo) {
		this.searchQPersonnelVo = searchQPersonnelVo;
	}

	public QPersonnel getPopulation() {
		return population;
	}

	public void setPopulation(QPersonnel population) {
		this.population = population;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
