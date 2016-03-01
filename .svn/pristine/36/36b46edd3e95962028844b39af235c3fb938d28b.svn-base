package com.tianque.baseInfo.newSocietyOrganizations.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.newSocietyOrganizations.dao.SearchNewSocietyOrganizationsDao;
import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.baseInfo.newSocietyOrganizations.domain.SearchNewSocietyOrganizationsVo;
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
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Namespace("/baseinfo/searchNewSocietyOrganizations")
@SuppressWarnings("serial")
@Controller("searchNewSocietyOrganizationsController")
@Scope("prototype")
public class SearchNewSocietyOrganizationsController extends SearchBaseAction {
	private SearchNewSocietyOrganizationsVo searchNewSocietyOrganizationsVo;
	private Long organizationId;
	private boolean pageOnly;
	private Organization organization;

	@Autowired
	private SearchNewSocietyOrganizationsDao searchNewSocietyOrganizationsDao;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private NewSocietyOrganizations location;

	public NewSocietyOrganizations getLocation() {
		return location;
	}

	public void setLocation(NewSocietyOrganizations location) {
		this.location = location;
	}

	/**
	 * 查询
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "searchNewSocietyOrganizations")
	@Action(value = "findNewSocietyOrganizationssByQueryCondition", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findNewSocietyOrganizationssByQueryCondition()
			throws Exception {
		if (searchNewSocietyOrganizationsVo == null && organizationId == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchNewSocietyOrganizationsVo.setOrgInternalCode(ownerOrg
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchNewSocietyOrganizationsDao
						.findNewSocietyOrganizationsByQueryCondition(
								searchNewSocietyOrganizationsVo, page, rows,
								sidx, sord), organizationDubboService,
				new String[] { "organization" }, organizationId));

		return SUCCESS;
	}

	/**
	 * 检索
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "searchNewSocietyOrganizations")
	@Action(value = "fastSearch", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String searchNewSocietyFederations() throws Exception {
		if (null == searchNewSocietyOrganizationsVo || null == organizationId) {
			gridPage = new GridPage(emptyNewSocietyOrganizationsPage(rows));
		} else {
			fastSearchCommonality();
		}
		return SUCCESS;
	}

	private PageInfo<NewSocietyOrganizations> emptyNewSocietyOrganizationsPage(
			int pageSize) {
		PageInfo<NewSocietyOrganizations> pageInfo = new PageInfo<NewSocietyOrganizations>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<NewSocietyOrganizations>());
		return pageInfo;
	}

	private void fastSearchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchNewSocietyOrganizationsVo.setOrgInternalCode(ownerOrg
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchNewSocietyOrganizationsDao
						.fastSearchNewSocietyOrganizations(
								searchNewSocietyOrganizationsVo, page, rows,
								sidx, sord), organizationDubboService,
				new String[] { "organization" }, organizationId));
	}

	/**
	 * 导出
	 * 
	 * @return STREAM_SUCCESS
	 */
	@PermissionFilter(ename = "downNewSocietyOrganizations")
	@Action(value = "downloadNewSocietyOrganizations")
	public String downloadNewSocietyFederation() throws Exception {
		if (searchNewSocietyOrganizationsVo == null && organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			orgCondition();
			List records = getNeedExportDatas(page);
			String[][] excelColumArray = SpecialGroupsContext
					.getNewSocietyOrganizationsArray();
			inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
					records);
			downloadFileName = new String("社会组织".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.NEWSOCIETY_FEDERATION,
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
									+ " 导出社会组织",
							ObjectToJSON
									.convertJSON(searchNewSocietyOrganizationsVo));
		}
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downNewSocietyOrganizations")
	@Action(value = "downloadNewSocietyOrganizationsAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchNewSocietyOrganizationsVo == null) {
			searchNewSocietyOrganizationsVo = new SearchNewSocietyOrganizationsVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchNewSocietyOrganizationsVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = searchNewSocietyOrganizationsDao
					.getCount(searchNewSocietyOrganizationsVo);
			String[][] excelDefines = SpecialGroupsContext
					.getNewSocietyOrganizationsArray();
			exportDataAll(count, excelDefines, "社会组织");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.NEWSOCIETY_FEDERATION,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 导出社会组织",
						ObjectToJSON
								.convertJSON(searchNewSocietyOrganizationsVo));
	}

	private void orgCondition() {
		if (searchNewSocietyOrganizationsVo == null) {
			searchNewSocietyOrganizationsVo = new SearchNewSocietyOrganizationsVo();
		}
		if (organizationId != null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null) {
				searchNewSocietyOrganizationsVo.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				searchNewSocietyOrganizationsVo.setOrgInternalCode(null);
			}
		} else {
			searchNewSocietyOrganizationsVo.setOrgInternalCode(null);
		}

		if (null != location) {
			searchNewSocietyOrganizationsVo.setIsEmphasis(location
					.getIsEmphasis());
		}

	}

	public List<NewSocietyOrganizations> getNeedExportDatas(int page) {
		List<NewSocietyOrganizations> list = new ArrayList<NewSocietyOrganizations>();
		if (pageOnly) {
			list = searchNewSocietyOrganizationsDao
					.searchNewSocietyOrganizationsForExport(
							searchNewSocietyOrganizationsVo, page, rows, sidx,
							sord);
		} else {
			list = searchNewSocietyOrganizationsDao
					.searchNewSocietyOrganizationsForExport(
							searchNewSocietyOrganizationsVo, null, null, sidx,
							sord);
		}
		return list;
	}

	public Organization getOrganization() {
		return organization;
	}

	public SearchNewSocietyOrganizationsVo getSearchNewSocietyOrganizationsVo() {
		return searchNewSocietyOrganizationsVo;
	}

	public void setSearchNewSocietyOrganizationsVo(
			SearchNewSocietyOrganizationsVo searchNewSocietyOrganizationsVo) {
		this.searchNewSocietyOrganizationsVo = searchNewSocietyOrganizationsVo;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

}
