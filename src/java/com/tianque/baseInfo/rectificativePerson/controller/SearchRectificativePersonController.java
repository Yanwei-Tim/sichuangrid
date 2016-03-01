package com.tianque.baseInfo.rectificativePerson.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.baseInfo.rectificativePerson.service.SearchRectificativePersonService;
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
import com.tianque.domain.vo.SearchRectificativePersonVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("searchRectificativePersonController")
@Namespace("/baseinfo/searchRectificativePerson")
public class SearchRectificativePersonController extends SearchBaseAction {
	@Autowired
	private SearchRectificativePersonService searchRectificativePersonService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private SearchRectificativePersonVo searchRectificativePersonVo;
	private Long organizationId;
	private boolean pageOnly;
	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();
	private String tag;
	private RectificativePerson rectificativePerson;
	private RectificativePerson population;

	@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearch() throws Exception {
		if (searchRectificativePersonVo == null) {
			gridPage = new GridPage(emptyRectificativePersonPage(rows));
			return SUCCESS;
		} else {
			searchCommonality();
		}
		return SUCCESS;
	}

	@Action(value = "findRectificativePersonsByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findRectificativePersonsByQueryCondition() throws Exception {
		if (searchRectificativePersonVo == null) {
			searchRectificativePersonVo = new SearchRectificativePersonVo();
		}
		searchCommonality();

		return SUCCESS;
	}

	@PermissionFilter(ename = "downRectificativePerson")
	@Action(value = "downloadRectificativePerson")
	public String downloadRectificativePerson() throws Exception {
		if (searchRectificativePersonVo == null && organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			populateOrgCondition();
			List<RectificativePerson> records = getNeedExportDatas(page);
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (organizationId != null) {
				if (org != null) {
					searchRectificativePersonVo.setOrgInternalCode(org
							.getOrgInternalCode());
				} else {
					searchRectificativePersonVo.setOrgInternalCode(null);
				}
			} else {
				searchRectificativePersonVo.setOrgInternalCode(null);
			}
			inputStream = ExcelExportHelper.exportDateToExcel(
					searchRectificativePersonService.getExportPopertyArray(),
					records);
			downloadFileName = new String("社区矫正人员清单".getBytes("gbk"),
					"ISO8859-1") + ".xls";
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.RECTIFICATIVE_PERSON,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													org.getId(),
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId())
									+ " 导出社区矫正人员", ObjectToJSON
									.convertJSON(searchRectificativePersonVo));
		}
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downRectificativePerson")
	@Action(value = "downloadRectificativePersonAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchRectificativePersonVo == null) {
			searchRectificativePersonVo = new SearchRectificativePersonVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchRectificativePersonVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = searchRectificativePersonService
					.getCounts(searchRectificativePersonVo);
			String[][] excelDefines = searchRectificativePersonService
					.getExportPopertyArray();
			exportDataAll(count, excelDefines, "社区矫正人员清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.RECTIFICATIVE_PERSON,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organization.getId(),
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 导出社区矫正人员",
						ObjectToJSON.convertJSON(searchRectificativePersonVo));
	}

	public List<RectificativePerson> getNeedExportDatas(int page) {
		List<RectificativePerson> list = new ArrayList<RectificativePerson>();
		if (rectificativePerson != null) {
			searchRectificativePersonVo.setIsEmphasis(rectificativePerson
					.getIsEmphasis());
		}
		if (population != null) {
			searchRectificativePersonVo.setIsEmphasis(population
					.getIsEmphasis());
		}
		if (pageOnly) {
			list = searchRectificativePersonService
					.searchRectificativePersonForExport(
							searchRectificativePersonVo, page, rows, sidx, sord);
		} else {
			list = searchRectificativePersonService
					.searchRectificativePersonForExport(
							searchRectificativePersonVo, null, null, sidx, sord);
		}
		return list;
	}

	private PageInfo<RectificativePerson> emptyRectificativePersonPage(
			int pageSize) {
		PageInfo<RectificativePerson> pageInfo = new PageInfo<RectificativePerson>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<RectificativePerson>());
		return pageInfo;
	}

	private void populateOrgCondition() {
		if (searchRectificativePersonVo == null) {
			searchRectificativePersonVo = new SearchRectificativePersonVo();
		}
		if (organizationId != null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null) {
				searchRectificativePersonVo.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				searchRectificativePersonVo.setOrgInternalCode(null);
			}
		} else {
			searchRectificativePersonVo.setOrgInternalCode(null);
		}
	}

	@Action(value = "searchRectificativePersonForAutoComplete", results = { @Result(name = "success", type = "json", params = {
			"root", "autoCompleteDatas", "ignoreHierarchy", "false" }) })
	public String searchRectificativePersonForAutoComplete() throws Exception {
		if (organizationId == null) {
			organizationId = ThreadVariable.getUser().getOrganization().getId();
		}
		organizationId = organizationDubboService
				.getTownOrganizationId(organizationId);
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		List<RectificativePerson> rectificativePersonList = searchRectificativePersonService
				.findRectificativePersonNameAndPinyinAndOrgInternalCode(tag,
						organization.getOrgInternalCode());
		for (RectificativePerson rectificativePerson : rectificativePersonList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(rectificativePerson.getName());
			autoCompleteData.setDesc(rectificativePerson.getCurrentAddress());
			autoCompleteData.setValue(rectificativePerson.getId().toString());
			autoCompleteDatas.add(autoCompleteData);
		}
		return SUCCESS;
	}

	private void searchCommonality() {
		if (organizationId == null) {
			organizationId = ThreadVariable.getUser().getOrganization().getId();
		}
		// organizationId =
		// organizationDubboService.getTownOrganizationId(organizationId);
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchRectificativePersonVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchRectificativePersonService.searchRectificativePerson(
						searchRectificativePersonVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				organizationId));
	}

	public List<AutoCompleteData> getAutoCompleteDatas() {
		return autoCompleteDatas;
	}

	public void setAutoCompleteDatas(List<AutoCompleteData> autoCompleteDatas) {
		this.autoCompleteDatas = autoCompleteDatas;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public RectificativePerson getRectificativePerson() {
		return rectificativePerson;
	}

	public void setRectificativePerson(RectificativePerson rectificativePerson) {
		this.rectificativePerson = rectificativePerson;
	}

	public SearchRectificativePersonVo getSearchRectificativePersonVo() {
		return searchRectificativePersonVo;
	}

	public void setSearchRectificativePersonVo(
			SearchRectificativePersonVo searchRectificativePersonVo) {
		this.searchRectificativePersonVo = searchRectificativePersonVo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public RectificativePerson getPopulation() {
		return population;
	}

	public void setPopulation(RectificativePerson population) {
		this.population = population;
	}
}
