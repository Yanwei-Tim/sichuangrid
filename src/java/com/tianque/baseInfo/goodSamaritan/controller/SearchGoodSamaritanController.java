package com.tianque.baseInfo.goodSamaritan.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.fPersonnel.domain.FPersonnel;
import com.tianque.baseInfo.goodSamaritan.domain.GoodSamaritan;
import com.tianque.baseInfo.goodSamaritan.domain.SearchGoodSamaritanVo;
import com.tianque.baseInfo.goodSamaritan.service.SearchGoodSamaritanService;
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

@Namespace("/baseinfo/searchGoodSamaritan")
@Controller("searchGoodSamaritanController")
@Scope("prototype")
public class SearchGoodSamaritanController extends SearchBaseAction {
	@Autowired
	private SearchGoodSamaritanService service;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;

	private SearchGoodSamaritanVo searchGoodSamaritanVo;

	private Long organizationId;

	private GoodSamaritan population;

	private boolean pageOnly;

	private InputStream inputStream;

	@Action(value = "findGoodSamaritansByQueryCondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "searchGoodSamaritan")
	public String searchGoodSamaritans() {
		if (null == searchGoodSamaritanVo) {
			searchGoodSamaritanVo = new SearchGoodSamaritanVo();
		}
		commonSearch();
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String quickSearchGoodSamaritans() {

		try {
			if (organizationId == null) {
				this.errorMessage = "组织id不能为空";
				return ERROR;
			}

			if (null == searchGoodSamaritanVo) {
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
		Organization org = organizationDubboService
				.getSimpleOrgById(organizationId);

		if (org != null) {
			searchGoodSamaritanVo.setOrgInternalCode(org.getOrgInternalCode());
		} else {
			throw new RuntimeException("根据organizationId" + organizationId
					+ ",没有查到该组织");
		}
		PageInfo<GoodSamaritan> pageInfoListInfo = service.searchGoodSamaritan(
				searchGoodSamaritanVo, page, rows, sidx, sord);
		PageInfo<GoodSamaritan> pageInfo = ControllerHelper
				.processAllOrgRelativeName(pageInfoListInfo,
						organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
	}

	@Action(value = "downloadGoodSamaritan", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String downloadGoodSamaritan() {
		if (null == organizationId) {
			errorMessage = "所属网格为空，不能导出数据";
			return ERROR;
		}
		if (null == searchGoodSamaritanVo) {
			searchGoodSamaritanVo = new SearchGoodSamaritanVo();
		}
		Organization org = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchGoodSamaritanVo.setOrgInternalCode(org.getOrgInternalCode());

		List<GoodSamaritan> queryQopulationList = getNeedExportDatas(page);

		try {

			inputStream = ExcelExportHelper.exportDateToExcel(
					service.getExportPopertyArray(), queryQopulationList);
			downloadFileName = new String("见义勇为人口".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.GOOD_SAMARITAN,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId())
									+ "导出见义勇为", ObjectToJSON
									.convertJSON(new GoodSamaritan()));
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
			return ERROR;
		}
		return STREAM_SUCCESS;
	}

	@Action(value = "downloadGoodSamaritanAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchGoodSamaritanVo == null) {
			searchGoodSamaritanVo = new SearchGoodSamaritanVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchGoodSamaritanVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		try {
			if (!pageOnly) {
				pageOnly = true;
				Integer count = service.getCount(searchGoodSamaritanVo);
				String[][] excelDefines = service.getExportPopertyArray();
				exportDataAll(count, excelDefines, "见义勇为清单");
			}
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.GOOD_SAMARITAN,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId())
									+ "导出全部见义勇为", ObjectToJSON
									.convertJSON(new FPersonnel()));
		} catch (Exception e) {
			errorMessage = e.getMessage();
			e.printStackTrace();
			return;
		}
	}

	public List<GoodSamaritan> getNeedExportDatas(int page) {
		if (population != null) {
			searchGoodSamaritanVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			return service.searchGoodSamaritanForExport(searchGoodSamaritanVo,
					page, rows, sidx, sord);
		} else {
			return service.searchGoodSamaritanForExport(searchGoodSamaritanVo,
					null, null, sidx, sord);
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

	public SearchGoodSamaritanVo getSearchGoodSamaritanVo() {
		return searchGoodSamaritanVo;
	}

	public void setSearchGoodSamaritanVo(
			SearchGoodSamaritanVo searchGoodSamaritanVo) {
		this.searchGoodSamaritanVo = searchGoodSamaritanVo;
	}

	public GoodSamaritan getPopulation() {
		return population;
	}

	public void setPopulation(GoodSamaritan population) {
		this.population = population;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
}
