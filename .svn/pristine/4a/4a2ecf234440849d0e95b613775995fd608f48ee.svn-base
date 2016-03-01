package com.tianque.baseInfo.superiorVisit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.baseInfo.superiorVisit.service.SearchSuperiorVisitService;
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
import com.tianque.domain.vo.SearchSuperiorVisitVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/searchSuperiorVisit")
@SuppressWarnings("serial")
@Controller("searchSuperiorVisitController")
@Scope("prototype")
@Transactional
public class SearchSuperiorVisitController extends SearchBaseAction {

	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private SearchSuperiorVisitService searchSuperiorVisitService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private SearchSuperiorVisitVo searchSuperiorVisitVo;

	private Long organizationId;

	private boolean pageOnly;

	private Map<Long, String> map = new HashMap<Long, String>();

	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();

	private String tag;

	private SuperiorVisit population;

	@Action(value = "findSuperiorVisitsByQueryCondition", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String searchSuperiorVisit() throws Exception {
		if (searchSuperiorVisitVo == null) {
			searchSuperiorVisitVo = new SearchSuperiorVisitVo();
		}
		/**
		 * if (issueSerch.equalsIgnoreCase("issueSerch")) { organizationId =
		 * organizationDubboService.getTownOrganizationId(organizationId); }
		 * //what happened...
		 */
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchSuperiorVisitVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (null != searchSuperiorVisitVo.getIdCardNo()) {
			searchSuperiorVisitVo.setIdCardNo(searchSuperiorVisitVo
					.getIdCardNo().toUpperCase());
		}
		PageInfo<SuperiorVisit> pageInfo = new PageInfo<SuperiorVisit>();
		pageInfo = ControllerHelper.processAllOrgRelativeName(
				searchSuperiorVisitService.searchAttentionPersonnel(
						searchSuperiorVisitVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@PermissionFilter(ename = "searchSuperiorVisit")
	@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearch() throws Exception {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchSuperiorVisitVo.setOrgInternalCode(ownerOrg.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchSuperiorVisitService.fastSearchSuperiorVisit(
						searchSuperiorVisitVo, page, rows, sidx, sord),
				organizationDubboService, new String[] { "organization" },
				organizationId));
		return SUCCESS;
	}

	@PermissionFilter(ename = "downSuperiorVisit")
	@Action(value = "downloadSuperiorVisit")
	public String downloadSuperiorVisit() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		if (searchSuperiorVisitVo == null) {
			searchSuperiorVisitVo = new SearchSuperiorVisitVo();
		}
		if (pageOnly) {

		}

		Organization org = organizationDubboService
				.getSimpleOrgById(organizationId);
		if (organizationId != null) {

			if (org != null) {
				searchSuperiorVisitVo.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				searchSuperiorVisitVo.setOrgInternalCode(null);
			}
		} else {
			searchSuperiorVisitVo.setOrgInternalCode(null);
		}
		List<SuperiorVisit> records = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				searchSuperiorVisitService.getExportPopertyArray(), records);
		downloadFileName = new String("重点上访人员清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(com.tianque.core.logger.Logger.INFO,
						ModuleConstants.SUPERIORVISIT,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												org.getId(),
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出重点上访人员",
						ObjectToJSON.convertJSON(searchSuperiorVisitVo));
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downSuperiorVisit")
	@Action(value = "downloadSuperiorVisitAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (null == searchSuperiorVisitVo) {
			searchSuperiorVisitVo = new SearchSuperiorVisitVo();
		}

		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchSuperiorVisitVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = searchSuperiorVisitService
					.getCount(searchSuperiorVisitVo);
			String[][] excelDefines = searchSuperiorVisitService
					.getExportPopertyArray();
			exportDataAll(count, excelDefines, "重点上访人员清单");
		}
		systemLogService
				.log(com.tianque.core.logger.Logger.INFO,
						ModuleConstants.SUPERIORVISIT,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organization.getId(),
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出重点上访人员",
						ObjectToJSON.convertJSON(searchSuperiorVisitVo));
	}

	public List<SuperiorVisit> getNeedExportDatas(int page) {
		List<SuperiorVisit> list = new ArrayList<SuperiorVisit>();
		if (population != null) {
			searchSuperiorVisitVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			list = searchSuperiorVisitService.searchSuperiorVisitsForExport(
					searchSuperiorVisitVo, page, rows, sidx, sord);
		} else {
			list = searchSuperiorVisitService.searchSuperiorVisitsForExport(
					searchSuperiorVisitVo, null, null, sidx, sord);
		}
		return list;
	}

	public String searchSuperiorVsitForAutoComplete() {
		if (organizationId == null) {
			organizationId = ThreadVariable.getUser().getOrganization().getId();
		}
		organizationId = organizationDubboService
				.getTownOrganizationId(organizationId);
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		List<SuperiorVisit> superiorVisitsList = searchSuperiorVisitService
				.findSuperiorVisitByNameAndPinyinAndOrgInternalCode(tag,
						organization.getOrgInternalCode());
		for (SuperiorVisit SuperiorVisit : superiorVisitsList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(SuperiorVisit.getName());
			autoCompleteData.setDesc(SuperiorVisit.getCurrentAddress());
			autoCompleteData.setValue(SuperiorVisit.getId().toString());
			autoCompleteDatas.add(autoCompleteData);
		}
		return SUCCESS;
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

	public List<AutoCompleteData> getAutoCompleteDatas() {
		return autoCompleteDatas;
	}

	public void setAutoCompleteDatas(List<AutoCompleteData> autoCompleteDatas) {
		this.autoCompleteDatas = autoCompleteDatas;
	}

	public Map<Long, String> getMap() {
		return map;
	}

	public void setMap(Map<Long, String> map) {
		this.map = map;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public SearchSuperiorVisitVo getSearchSuperiorVisitVo() {
		return searchSuperiorVisitVo;
	}

	public void setSearchSuperiorVisitVo(
			SearchSuperiorVisitVo searchSuperiorVisitVo) {
		this.searchSuperiorVisitVo = searchSuperiorVisitVo;
	}

	public SuperiorVisit getPopulation() {
		return population;
	}

	public void setPopulation(SuperiorVisit population) {
		this.population = population;
	}
}
