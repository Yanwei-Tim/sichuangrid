package com.tianque.baseInfo.otherAttentionPersonnel.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.otherAttentionPersonnel.domain.OtherAttentionPersonnel;
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
import com.tianque.domain.vo.SearchOtherAttentionPersonnelVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.SearchOtherAttentionPersonnelService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Namespace("/baseinfo/searchOtherAttentionPersonnel")
@Controller("searchOtherAttentionPersonnelController")
@Scope("prototype")
@Transactional(readOnly = true)
public class SearchOtherAttentionPersonnelController extends SearchBaseAction {
	@Autowired
	private SearchOtherAttentionPersonnelService service;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;

	private SearchOtherAttentionPersonnelVo searchOtherAttentionPersonnelVo;

	private Long organizationId;

	private OtherAttentionPersonnel population;

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	private boolean pageOnly;

	private InputStream inputStream;

	/*
	 * <result name="success" type="json"> <param name="root">gridPage</param>
	 * <param name="ignoreHierarchy">false</param> <param
	 * name="excludeNullProperties">true</param> </result> <result name="error"
	 * type="json"> <param name="root">errorMessage</param> </result>
	 */

	@Action(value = "findOtherAttentionPersonnelsByQueryCondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "searchOtherAttentionPersonnel")
	public String searchOtherAttentionPersonnels() throws Exception {
		if (null == searchOtherAttentionPersonnelVo) {
			searchOtherAttentionPersonnelVo = new SearchOtherAttentionPersonnelVo();
		}
		commonSearch();
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String quickSearchOtherAttentionPersonnels() throws Exception {

		if (organizationId == null) {
			this.errorMessage = "组织id不能为空";
			return ERROR;
		}

		if (null == searchOtherAttentionPersonnelVo) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		commonSearch();
		return SUCCESS;
	}

	private void commonSearch() {
		Organization org = organizationDubboService.getSimpleOrgById(organizationId);

		if (org != null) {
			searchOtherAttentionPersonnelVo.setOrgInternalCode(org
					.getOrgInternalCode());
		} else {
			throw new BusinessValidationException("根据organizationId"
					+ organizationId + ",没有查到该组织");
		}
		PageInfo<OtherAttentionPersonnel> pageInfoListInfo = service
				.searchOtherAttentionPersonnel(searchOtherAttentionPersonnelVo,
						page, rows, sidx, sord);
		PageInfo<OtherAttentionPersonnel> pageInfo = ControllerHelper
				.processAllOrgRelativeName(pageInfoListInfo,
						organizationDubboService, new String[] { "organization" },
						organizationId);
		gridPage = new GridPage(pageInfo);
	}

	@Action(value = "downloadOtherAttentionPersonnel")
	public String downloadOtherAttentionPersonnel() throws Exception {
		if (null == organizationId) {
			errorMessage = "所属网格为空，不能导出数据";
			return ERROR;
		}
		if (null == searchOtherAttentionPersonnelVo) {
			searchOtherAttentionPersonnelVo = new SearchOtherAttentionPersonnelVo();
		}
		Organization org = organizationDubboService.getSimpleOrgById(organizationId);
		searchOtherAttentionPersonnelVo.setOrgInternalCode(org
				.getOrgInternalCode());

		List<OtherAttentionPersonnel> queryQopulationList = getNeedExportDatas(page);

		inputStream = ExcelExportHelper.exportDateToExcel(
				service.getExportPopertyArray(), queryQopulationList);
		downloadFileName = new String("其他人口".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.OTHERATTENTION_PERSONNEL,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出其他人员",
						ObjectToJSON.convertJSON(new OtherAttentionPersonnel()));
		return STREAM_SUCCESS;
	}

	@Action(value = "downloadOtherAttentionPersonnelAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchOtherAttentionPersonnelVo == null) {
			searchOtherAttentionPersonnelVo = new SearchOtherAttentionPersonnelVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchOtherAttentionPersonnelVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = service.getCount(searchOtherAttentionPersonnelVo);
			String[][] excelDefines = service.getExportPopertyArray();
			exportDataAll(count, excelDefines, "其他人口清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.OTHERATTENTION_PERSONNEL,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出全部其他人员",
						ObjectToJSON.convertJSON(new OtherAttentionPersonnel()));
	}

	public List<OtherAttentionPersonnel> getNeedExportDatas(int page) {
		if (population != null) {
			searchOtherAttentionPersonnelVo.setIsEmphasis(population
					.getIsEmphasis());
		}
		if (pageOnly) {
			return service.searchOtherAttentionPersonnelForExport(
					searchOtherAttentionPersonnelVo, page, rows, sidx, sord);
		} else {
			return service.searchOtherAttentionPersonnelForExport(
					searchOtherAttentionPersonnelVo, null, null, sidx, sord);
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

	public SearchOtherAttentionPersonnelVo getSearchOtherAttentionPersonnelVo() {
		return searchOtherAttentionPersonnelVo;
	}

	public void setSearchOtherAttentionPersonnelVo(
			SearchOtherAttentionPersonnelVo searchOtherAttentionPersonnelVo) {
		this.searchOtherAttentionPersonnelVo = searchOtherAttentionPersonnelVo;
	}

	public OtherAttentionPersonnel getPopulation() {
		return population;
	}

	public void setPopulation(OtherAttentionPersonnel population) {
		this.population = population;
	}

}
