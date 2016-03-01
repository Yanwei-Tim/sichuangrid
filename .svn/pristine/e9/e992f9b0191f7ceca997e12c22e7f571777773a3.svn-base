package com.tianque.baseInfo.overseaPersonnel.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.baseInfo.overseaPersonnel.service.OverseaPersonnelService;
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
import com.tianque.domain.vo.SearchOverseaPersonnelVo;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Controller("overseaPersonnelSearchController")
@Scope("prototype")
@Transactional
public class OverseaPersonnelSearchController extends SearchBaseAction {

	@Autowired
	private OverseaPersonnelService overseaPersonnelService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private PropertyDictService propertyDictService;
	private SearchOverseaPersonnelVo searchOverseaPersonnelVo;
	private Long orgId;
	private boolean pageOnly;
	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();
	private String tag;
	private String issueSerch = "";
	private OverseaPersonnel overseaPersonnel;
	private Long organizationId;

	@PermissionFilter(ename = "searchOverseaPerson")
	public String searchOverseaPersonnel() throws Exception {
		if (searchOverseaPersonnelVo == null) {
			searchOverseaPersonnelVo = new SearchOverseaPersonnelVo();
		}
		if (issueSerch.equalsIgnoreCase("issueSerch")) {
			orgId = organizationDubboService.getTownOrganizationId(orgId);
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		searchOverseaPersonnelVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<OverseaPersonnel> pageInfo = ControllerHelper
				.processAllOrgRelativeName(overseaPersonnelService
						.searchOverseaPersonnel(searchOverseaPersonnelVo, page,
								rows, sidx, sord), organizationDubboService,
						new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String fastSearchOverseaPersonnel() throws Exception {
		if (searchOverseaPersonnelVo == null) {
			this.errorMessage = "参数错误";
		}

		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		searchOverseaPersonnelVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<OverseaPersonnel> pageInfo = ControllerHelper
				.processAllOrgRelativeName(overseaPersonnelService
						.fastSearchOverseaPersonnel(searchOverseaPersonnelVo,
								page, rows, sidx, sord),
						organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@PermissionFilter(ename = "downOverseaPerson")
	public String downloadOverseaPersonnel() throws Exception {
		if (searchOverseaPersonnelVo == null && orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		Organization organization = this.organizationDubboService
				.getSimpleOrgById(orgId);
		// 给对象设置基本参数
		if (searchOverseaPersonnelVo == null) {
			searchOverseaPersonnelVo = new SearchOverseaPersonnelVo();
		}
		searchOverseaPersonnelVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		List<OverseaPersonnel> list = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				SpecialGroupsContext.getOverseaPersonnelPropertyArray(), list);
		downloadFileName = new String("境外人员清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.OPTIMALOBJECT,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出境外人员",
						ObjectToJSON.convertJSON(new HouseholdStaff()));
		return SUCCESS;
	}

	@PermissionFilter(ename = "downOverseaPerson")
	public void downloadExcelExportAll() throws Exception {
		if (orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (null == searchOverseaPersonnelVo) {
			searchOverseaPersonnelVo = new SearchOverseaPersonnelVo();
		}
		if (searchOverseaPersonnelVo.getLogOut() == null) {
			searchOverseaPersonnelVo.setLogOut(0L);
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		searchOverseaPersonnelVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = overseaPersonnelService
					.getCount(searchOverseaPersonnelVo);
			String[][] excelDefines = SpecialGroupsContext
					.getOverseaPersonnelPropertyArray();
			exportDataAll(count, excelDefines, "境外人员清单");
		}
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.OPTIMALOBJECT,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出境外人员",
						ObjectToJSON.convertJSON(new HouseholdStaff()));
	}

	public String searchOverseaPersonnelForAutoComplete() throws Exception {
		if (orgId == null) {
			orgId = ThreadVariable.getUser().getOrganization().getId();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		List<OverseaPersonnel> overseaPersonnel = overseaPersonnelService
				.findOverseaPersonnelByNameAndPinyinAndOrgInternalCode(tag,
						organization.getOrgInternalCode());
		for (OverseaPersonnel overseaPersonnelObject : overseaPersonnel) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(overseaPersonnelObject.getName());
			autoCompleteData
					.setDesc(overseaPersonnelObject.getCurrentAddress());
			autoCompleteData
					.setValue(overseaPersonnelObject.getId().toString());
			autoCompleteDatas.add(autoCompleteData);
		}
		return SUCCESS;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(
			OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public PropertyDictService getPropertyDictService() {
		return propertyDictService;
	}

	public void setPropertyDictService(PropertyDictService propertyDictService) {
		this.propertyDictService = propertyDictService;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public List<AutoCompleteData> getAutoCompleteDatas() {
		return autoCompleteDatas;
	}

	public void setAutoCompleteDatas(List<AutoCompleteData> autoCompleteDatas) {
		this.autoCompleteDatas = autoCompleteDatas;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getIssueSerch() {
		return issueSerch;
	}

	public void setIssueSerch(String issueSerch) {
		this.issueSerch = issueSerch;
	}

	public OverseaPersonnel getOverseaPersonnel() {
		return overseaPersonnel;
	}

	public void setOverseaPersonnel(OverseaPersonnel overseaPersonnel) {
		this.overseaPersonnel = overseaPersonnel;
	}

	public SearchOverseaPersonnelVo getSearchOverseaPersonnelVo() {
		return searchOverseaPersonnelVo;
	}

	public void setSearchOverseaPersonnelVo(
			SearchOverseaPersonnelVo searchOverseaPersonnelVo) {
		this.searchOverseaPersonnelVo = searchOverseaPersonnelVo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	@Override
	public List getNeedExportDatas(int page) {
		// TODO Auto-generated method stub
		List<OverseaPersonnel> list = new ArrayList<OverseaPersonnel>();
		if (pageOnly) {
			list = this.overseaPersonnelService
					.searchOverseaPersonnelForExport(searchOverseaPersonnelVo,
							page, rows, sidx, sord);
		} else {
			list = this.overseaPersonnelService
					.searchOverseaPersonnelForExport(searchOverseaPersonnelVo,
							null, rows, sidx, sord);
		}
		return list;
	}

}
