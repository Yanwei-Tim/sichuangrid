package com.tianque.baseInfo.elderlyPeople.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.baseInfo.elderlyPeople.service.SearchElderlyPeopleService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchElderlyPeopleVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Namespace("/baseinfo/searchElderlyPeople")
@SuppressWarnings("serial")
@Controller("searchElderlyPeopleController")
@Scope("prototype")
@Transactional
public class SearchElderlyPeopleController extends SearchBaseAction {
	private SearchElderlyPeopleVo searchElderlyPeopleVo;
	@Autowired
	private SystemLogService systemLogService;
	private Long organizationId;
	@Autowired
	private SearchElderlyPeopleService searchElderlyPeopleService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private String tag;
	private Long orgId;
	private boolean pageOnly;
	private ElderlyPeople population;

	@PermissionFilter(ename = "searchElderlyPeople")
	@Actions({
			@Action(value = "findElderlyPeoplesByQueryCondition", results = { @Result(type = "json", name = "success", params = {
					"root", "gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }) }),
			@Action(value = "fastSearch", results = { @Result(type = "json", name = "success", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }) })
	public String searchElderlyPeople() throws Exception {
		if (searchElderlyPeopleVo == null) {
			searchElderlyPeopleVo = new SearchElderlyPeopleVo();
		}
		searchCommonality();
		return SUCCESS;
	}

	private void searchCommonality() {
		if (orgId == null) {
			orgId = organizationId;
		}
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		searchElderlyPeopleVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<ElderlyPeople> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchElderlyPeopleService
						.searchElderlyPeople(searchElderlyPeopleVo, page, rows,
								sidx, sord), organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
	}

	@PermissionFilter(ename = "downElderlyPeople")
	@Action(value = "downloadElderlyPeople")
	public String downloadElderlyPeople() throws Exception {
		if (searchElderlyPeopleVo == null && organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			populateOrgCondition();
			Organization organization = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (organization != null) {
				searchElderlyPeopleVo.setOrgInternalCode(organization
						.getOrgInternalCode());
			}
			List<ElderlyPeople> records = getNeedExportDatas(page);
			inputStream = ExcelExportHelper
					.exportDateToExcel(
							searchElderlyPeopleService.getExportPopertyArray(),
							records);
			downloadFileName = new String("老年人清单".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
			systemLogService
					.log(Logger.INFO,
							ModelType.BASE,
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
									+ " 导出老年人", ObjectToJSON
									.convertJSON(searchElderlyPeopleVo));

		}
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downElderlyPeople")
	@Action(value = "downloadElderlyPeopleAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchElderlyPeopleVo == null) {
			searchElderlyPeopleVo = new SearchElderlyPeopleVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchElderlyPeopleVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = searchElderlyPeopleService
					.getCount(searchElderlyPeopleVo);
			String[][] excelDefines = searchElderlyPeopleService
					.getExportPopertyArray();
			exportDataAll(count, excelDefines, "老年人清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModelType.BASE,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 导出老年人",
						ObjectToJSON.convertJSON(searchElderlyPeopleVo));
	}

	public List<ElderlyPeople> getNeedExportDatas(int page) {
		List<ElderlyPeople> list = new ArrayList<ElderlyPeople>();
		if (population != null) {
			searchElderlyPeopleVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			list = searchElderlyPeopleService.searchElderlyPeopleForExport(
					searchElderlyPeopleVo, page, rows, sidx, sord);
		} else {
			list = searchElderlyPeopleService.searchElderlyPeopleForExport(
					searchElderlyPeopleVo, null, null, sidx, sord);
		}
		return list;
	}

	private void populateOrgCondition() {
		if (searchElderlyPeopleVo == null) {
			searchElderlyPeopleVo = new SearchElderlyPeopleVo();
		}
		if (orgId != null) {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			if (org != null) {
				searchElderlyPeopleVo.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				searchElderlyPeopleVo.setOrgInternalCode(null);
			}
		} else {
			searchElderlyPeopleVo.setOrgInternalCode(null);
		}

	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public SearchElderlyPeopleVo getSearchElderlyPeopleVo() {
		return searchElderlyPeopleVo;
	}

	public void setSearchElderlyPeopleVo(
			SearchElderlyPeopleVo searchElderlyPeopleVo) {
		this.searchElderlyPeopleVo = searchElderlyPeopleVo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public ElderlyPeople getPopulation() {
		return population;
	}

	public void setPopulation(ElderlyPeople population) {
		this.population = population;
	}
}
