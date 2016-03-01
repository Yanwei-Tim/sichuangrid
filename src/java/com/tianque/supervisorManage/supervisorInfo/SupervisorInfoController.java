package com.tianque.supervisorManage.supervisorInfo;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("supervisorController")
@Namespace("/supervisorManage/supervisorInfoManage")
public class SupervisorInfoController extends BaseAction {

	private String dailogName;
	private SupervisorInfoVo population;
	protected Long organizationId;
	protected String populationIds;
	protected String supervisorName;
	private boolean pageOnly;
	@Autowired
	private SupervisorInfoService supervisorInfoService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Action(value = "dispatchSupervisorInfo", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/supervisorManage/supervisorInfo/supervisorInfoDlg.jsp"),
			@Result(name = "viewSupervisorOfpopulation", location = "/baseinfo/commonPopulation/supervisorManage/supervisorInfo/supervisorViewOfpopulation.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchSupervisor() throws Exception {
		if (DialogMode.VIEW_MODE.equals(mode)) {
			return "viewSupervisorOfpopulation";
		} else {
			population.setId(id);
			return SUCCESS;
		}
	}

	@Action(value = "emptyOperation", results = { @Result(type = "json", name = "success", params = {
			"root", "population" }) })
	public String emptyOperation() throws Exception {
		return SUCCESS;
	}

	/*
	 * 监护人列表
	 */
	@Action(value = "findSupervisorforList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findSupervisorforList() throws Exception {
		if (null == population || null == population.getPopulationId()
				|| null == population.getPopulationType()) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(
					supervisorInfoService.findSupervisorforList(population,
							page, rows, sidx, sord));
		}
		return SUCCESS;
	}

	@Action(value = "dispatchOperateSupervisor", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/supervisorManage/supervisorInfo/maintainSupervisor.jsp"),
			@Result(name = "view", location = "/baseinfo/commonPopulation/supervisorManage/supervisorInfo/supervisorView.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperateSupervisor() throws Exception {
		Organization organization = new Organization();
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (organizationId == null) {
				return ERROR;
			}
			organization = organizationDubboService.getFullOrgById(organizationId);
			if (population == null) {
				population = new SupervisorInfoVo();
			}
			population.setOrgInternalCode(organization.getOrgInternalCode());
		}
		if (DialogMode.VIEW_MODE.equals(mode)) {
			population = supervisorInfoService.viewSupervisor(population);
			Long orgId = (organizationDubboService
					.getOrganizationByOrgInternalCode(population
							.getOrgInternalCode())).getId();
			organization.setOrgName(ControllerHelper
					.getOrganizationRelativeName(orgId, organizationDubboService));
			population.setOrganization(organization);
			return "view";
		}
		return SUCCESS;
	}

	@Action(value = "addSupervisor", results = {
			@Result(type = "json", name = "success", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String addSupervisor() throws Exception {
		supervisorInfoService.addSupervisor(population);
		return SUCCESS;
	}

	@Action(value = "searchSupervisor", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String searchSupervisor() throws Exception {
		gridPage = new GridPage(supervisorInfoService.searchSupervisor(
				population, page, rows, sidx, sord));
		return SUCCESS;
	}

	@Action(value = "deleteSupervisors", results = {
			@Result(name = "success", type = "json", params = { "root",
					"deleteIds" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteSupervisorbyIds() throws Exception {
		supervisorInfoService.deleteSupervisors(population);
		return SUCCESS;
	}

	@Action(value = "downloadSupervisor")
	public String downloadSupervisor() throws Exception {
		if (population == null) {
			population = new SupervisorInfoVo();
		}
		List<SupervisorInfoVo> records = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				supervisorInfoService.getExportPopertyArray(supervisorName),
				records);
		downloadFileName = new String(supervisorName.getBytes("gbk"),
				"ISO8859-1") + ".xls";
		return STREAM_SUCCESS;
	}

	private List<SupervisorInfoVo> getNeedExportDatas(int page) throws Exception {
		if (pageOnly) {
			return supervisorInfoService.searchSupervisorsForExport(population,
					page, rows, sidx, sord);
		} else {
			return supervisorInfoService.searchSupervisorsForExport(population,
					null, null, sidx, sord);
		}
	}

	private PageInfo<SupervisorInfoVo> emptyPage(int pageSize) {
		PageInfo<SupervisorInfoVo> pageInfo = new PageInfo<SupervisorInfoVo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SupervisorInfoVo>());
		return pageInfo;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public SupervisorInfoVo getPopulation() {
		return population;
	}

	public void setPopulation(SupervisorInfoVo population) {
		this.population = population;
	}

	public String getPopulationIds() {
		return populationIds;
	}

	public void setPopulationIds(String populationIds) {
		this.populationIds = populationIds;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

}
