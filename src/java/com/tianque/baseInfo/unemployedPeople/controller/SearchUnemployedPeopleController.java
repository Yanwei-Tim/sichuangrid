package com.tianque.baseInfo.unemployedPeople.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.unemployedPeople.domain.UnemployedPeople;
import com.tianque.baseInfo.unemployedPeople.service.UnemployedPeopleService;
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
import com.tianque.domain.vo.SearchUnemployedPeopleVo;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/searchUnemployedPeople")
@Transactional
@Scope("request")
@Controller("searchUnemployedPeopleController")
public class SearchUnemployedPeopleController extends SearchBaseAction {

	@Autowired
	protected ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private UnemployedPeopleService unemployedPeopleService;
	@Autowired
	private SystemLogService systemLogService;

	private SearchUnemployedPeopleVo searchUnemployedPeopleVo;

	private Long organizationId;

	private boolean pageOnly;

	private String trainingIntentionIds;

	private UnemployedPeople population;

	private String employmentIntentionIds;

	@PermissionFilter(ename = "searchUnemployedPeople")
	@Actions({
			@Action(value = "findUnemployedPeoplesByQueryCondition", results = { @Result(type = "json", name = "success", params = {
					"root", "gridPage", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }) }),
			@Action(value = "fastSearch", results = { @Result(type = "json", name = "success", params = {
					"root", "gridPage", "ignoreHierarchy", "false" }) }) })
	public String searchUnemployedPeople() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(new PageInfo<UnemployedPeople>());
			return SUCCESS;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchUnemployedPeopleVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<UnemployedPeople> pageInfo = ControllerHelper
				.processAllOrgRelativeName(unemployedPeopleService
						.searchUnemployedPeoples(page, rows, sidx, sord,
								searchUnemployedPeopleVo,
								coventStringToList(employmentIntentionIds),
								coventStringToList(trainingIntentionIds)),
						organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@PermissionFilter(ename = "downUnemployedPeople")
	@Action(value = "downloadUnemployedPeople")
	public String downloadUnemployedPeople() throws Exception {
		if (null == organizationId) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		if (null == searchUnemployedPeopleVo) {
			searchUnemployedPeopleVo = new SearchUnemployedPeopleVo();
		}
		if (population != null) {
			searchUnemployedPeopleVo.setIsEmphasis(population.getIsEmphasis());
		}
		Organization organization = this.organizationDubboService
				.getSimpleOrgById(organizationId);
		searchUnemployedPeopleVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		List<UnemployedPeople> unemployedPeopleList = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				unemployedPeopleService.getExportPopertyArray(),
				unemployedPeopleList);
		downloadFileName = new String("失业人员清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.ELDERLYPEOPLE,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出失业人员",
						ObjectToJSON.convertJSON(new HouseholdStaff()));

		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downUnemployedPeople")
	@Action(value = "downloadUnemployedPeopleAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchUnemployedPeopleVo == null) {
			searchUnemployedPeopleVo = new SearchUnemployedPeopleVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchUnemployedPeopleVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = unemployedPeopleService
					.getCount(searchUnemployedPeopleVo);
			String[][] excelDefines = unemployedPeopleService
					.getExportPopertyArray();
			exportDataAll(count, excelDefines, "失业人员");
		}
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.ELDERLYPEOPLE,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出失业人员",
						ObjectToJSON.convertJSON(new HouseholdStaff()));
	}

	private List<String> coventStringToList(String ids) {
		if (ids == null || "".equals(ids)) {
			return null;
		}
		String[] temp = ids.split(",");
		return Arrays.asList(temp);
	}

	public SearchUnemployedPeopleVo getSearchUnemployedPeopleVo() {
		return searchUnemployedPeopleVo;
	}

	public void setSearchUnemployedPeopleVo(
			SearchUnemployedPeopleVo searchUnemployedPeopleVo) {
		this.searchUnemployedPeopleVo = searchUnemployedPeopleVo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getTrainingIntentionIds() {
		return trainingIntentionIds;
	}

	public void setTrainingIntentionIds(String trainingIntentionIds) {
		this.trainingIntentionIds = trainingIntentionIds;
	}

	public String getEmploymentIntentionIds() {
		return employmentIntentionIds;
	}

	public void setEmploymentIntentionIds(String employmentIntentionIds) {
		this.employmentIntentionIds = employmentIntentionIds;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public UnemployedPeople getPopulation() {
		return population;
	}

	public void setPopulation(UnemployedPeople population) {
		this.population = population;
	}

	@Override
	public List getNeedExportDatas(int page) {
		// TODO Auto-generated method stub
		List<UnemployedPeople> list = new ArrayList<UnemployedPeople>();
		if (pageOnly) {
			list = this.unemployedPeopleService.searchUnemployedPeoples(page,
					rows, sidx, sord, searchUnemployedPeopleVo,
					coventStringToList(employmentIntentionIds),
					coventStringToList(trainingIntentionIds)).getResult();
		} else {
			list = this.unemployedPeopleService.searchAllUnemployedPeoples(
					sidx, sord, searchUnemployedPeopleVo,
					coventStringToList(employmentIntentionIds),
					coventStringToList(trainingIntentionIds));
		}
		return list;
	}

}
