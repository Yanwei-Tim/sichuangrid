package com.tianque.baseInfo.dangerousGoodsPractitioner.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
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
import com.tianque.domain.vo.SearchDangerousGoodsPractitioner;
import com.tianque.service.SearchDangerousGoodsPractitionerService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Controller("searchDangerousGoodsPractitionerController")
@Scope("prototype")
@Transactional(readOnly = true)
public class SearchDangerousGoodsPractitionerController extends
		SearchBaseAction {
	@Autowired
	private SearchDangerousGoodsPractitionerService service;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;

	private SearchDangerousGoodsPractitioner searchDangerousGoodsPractitionerVo;

	private Long organizationId;

	private DangerousGoodsPractitioner population;

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	private boolean pageOnly;

	private InputStream inputStream;

	@PermissionFilter(ename = "searchDangerousGoodsPractitioner")
	public String searchDangerousGoodsPractitioners() throws Exception {
		if (null == searchDangerousGoodsPractitionerVo) {
			searchDangerousGoodsPractitionerVo = new SearchDangerousGoodsPractitioner();
		}
		commonSearch();
		return SUCCESS;
	}

	public String quickSearchDangerousGoodsPractitioners() throws Exception {
		if (null == searchDangerousGoodsPractitionerVo) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		commonSearch();
		return SUCCESS;
	}

	private void commonSearch() {
		Organization org = organizationDubboService.getSimpleOrgById(organizationId);
		searchDangerousGoodsPractitionerVo.setOrgInternalCode(org
				.getOrgInternalCode());
		PageInfo<DangerousGoodsPractitioner> pageInfo = ControllerHelper
				.processAllOrgRelativeName(service
						.searchDangerousGoodsPractitioner(
								searchDangerousGoodsPractitionerVo, page, rows,
								sidx, sord), organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
	}

	public String downloadDangerousGoodsPractitioner() throws Exception {
		if (null == organizationId) {
			errorMessage = "所属网格为空，不能导出数据";
			return ERROR;
		}
		if (null == searchDangerousGoodsPractitionerVo) {
			searchDangerousGoodsPractitionerVo = new SearchDangerousGoodsPractitioner();
		}
		Organization org = organizationDubboService.getSimpleOrgById(organizationId);
		searchDangerousGoodsPractitionerVo.setOrgInternalCode(org
				.getOrgInternalCode());
		List<DangerousGoodsPractitioner> queryQopulationList = getNeedExportDatas(page);

		inputStream = ExcelExportHelper.exportDateToExcel(
				service.getExportPopertyArray(), queryQopulationList);
		downloadFileName = new String("危险品从业人口".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.DANGEROUSEGOODS_PRACTITIONER,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出危险品人口",
						ObjectToJSON
								.convertJSON(new DangerousGoodsPractitioner()));
		return SUCCESS;
	}

	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchDangerousGoodsPractitionerVo == null) {
			searchDangerousGoodsPractitionerVo = new SearchDangerousGoodsPractitioner();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchDangerousGoodsPractitionerVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = service
					.getCount(searchDangerousGoodsPractitionerVo);
			String[][] excelDefines = service.getExportPopertyArray();
			exportDataAll(count, excelDefines, "危险品从业人口");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.DANGEROUSEGOODS_PRACTITIONER,
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
								+ "导出全部危险品从业人口", ObjectToJSON
								.convertJSON(new DangerousGoodsPractitioner()));
	}

	public SearchDangerousGoodsPractitioner getSearchDangerousGoodsPractitionerVo() {
		return searchDangerousGoodsPractitionerVo;
	}

	public void setSearchDangerousGoodsPractitionerVo(
			SearchDangerousGoodsPractitioner searchDangerousGoodsPractitionerVo) {
		this.searchDangerousGoodsPractitionerVo = searchDangerousGoodsPractitionerVo;
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

	public DangerousGoodsPractitioner getPopulation() {
		return population;
	}

	public void setPopulation(DangerousGoodsPractitioner population) {
		this.population = population;
	}

	@Override
	public List getNeedExportDatas(int page) {
		// TODO Auto-generated method stub
		List<DangerousGoodsPractitioner> list = new ArrayList<DangerousGoodsPractitioner>();
		if (population != null) {
			searchDangerousGoodsPractitionerVo.setIsEmphasis(population
					.getIsEmphasis());
		}
		if (pageOnly) {
			list = service.searchDangerousGoodsPractitionerForExport(
					searchDangerousGoodsPractitionerVo, page, rows, sidx, sord);
		} else {
			list = service.searchDangerousGoodsPractitionerForExport(
					searchDangerousGoodsPractitionerVo, null, null, sidx, sord);
		}
		return list;
	}
}
