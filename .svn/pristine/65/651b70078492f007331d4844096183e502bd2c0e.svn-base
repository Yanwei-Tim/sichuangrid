package com.tianque.baseInfo.optimalObject.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.optimalObject.domain.OptimalObject;
import com.tianque.baseInfo.optimalObject.service.OptimalObjectService;
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
import com.tianque.domain.vo.SearchOptimalObjectVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Transactional
@Scope("prototype")
@Controller("searchOptimalObjectController")
@Namespace("/baseinfo/searchOptimalObject")
public class SearchOptimalObjectController extends SearchBaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private OptimalObjectService optimalObjectService;

	@Autowired
	private SystemLogService systemLogService;

	private Long organizationId;

	private SearchOptimalObjectVo searchOptimalObjectVo;

	private boolean pageOnly;
	private OptimalObject population;

	/**
	 * 根据查询条件分页查询优抚对象
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "searchOptimalObject")
	@Action(value = "findOptimalObjectsByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findOptimalObjectsByQueryCondition() throws Exception {
		if (organizationId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (searchOptimalObjectVo == null) {
			searchOptimalObjectVo = new SearchOptimalObjectVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchOptimalObjectVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				optimalObjectService.searchOptimalObjects(
						searchOptimalObjectVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				organizationId));
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearch() throws Exception {
		if (searchOptimalObjectVo == null) {
			gridPage = new GridPage(new PageInfo<OptimalObject>());
			return SUCCESS;
		} else {
			fastSearchCommonality();
		}
		return SUCCESS;
	}

	private void fastSearchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchOptimalObjectVo.setOrgInternalCode(ownerOrg.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				optimalObjectService.fastSearchOptimalObject(
						searchOptimalObjectVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				organizationId));
	}

	@PermissionFilter(ename = "downOptimalObject")
	@Action(value = "downloadOptimalObject")
	public String downloadOptimalObject() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		if (searchOptimalObjectVo == null) {
			searchOptimalObjectVo = new SearchOptimalObjectVo();
		}

		Organization org = organizationDubboService.getSimpleOrgById(organizationId);
		if (organizationId != null) {

			if (org != null) {
				searchOptimalObjectVo.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				searchOptimalObjectVo.setOrgInternalCode(null);
			}
		} else {
			searchOptimalObjectVo.setOrgInternalCode(null);
		}

		List<OptimalObject> records = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				optimalObjectService.getExportPopertyArray(), records);
		downloadFileName = new String("优抚对象清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.OPTIMALOBJECT,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 导出优抚对象",
						ObjectToJSON.convertJSON(searchOptimalObjectVo));
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downOptimalObject")
	@Action(value = "downloadOptimalObjectAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchOptimalObjectVo == null) {
			searchOptimalObjectVo = new SearchOptimalObjectVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchOptimalObjectVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = optimalObjectService
					.getCount(searchOptimalObjectVo);
			String[][] excelDefines = optimalObjectService
					.getExportPopertyArray();
			exportDataAll(count, excelDefines, "优抚对象清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.OPTIMALOBJECT,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 导出优抚对象",
						ObjectToJSON.convertJSON(searchOptimalObjectVo));
	}

	public List<OptimalObject> getNeedExportDatas(int page) {
		List<OptimalObject> list = new ArrayList<OptimalObject>();
		if (population != null) {
			searchOptimalObjectVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			list = optimalObjectService.searchOptimalObjectsForExport(
					searchOptimalObjectVo, page, rows, sidx, sord);
		} else {
			list = optimalObjectService.searchOptimalObjectsForExport(
					searchOptimalObjectVo, null, null, sidx, sord);
		}
		return list;
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

	public SearchOptimalObjectVo getSearchOptimalObjectVo() {
		return searchOptimalObjectVo;
	}

	public void setSearchOptimalObjectVo(
			SearchOptimalObjectVo searchOptimalObjectVo) {
		this.searchOptimalObjectVo = searchOptimalObjectVo;
	}

	public OptimalObject getPopulation() {
		return population;
	}

	public void setPopulation(OptimalObject population) {
		this.population = population;
	}
}
