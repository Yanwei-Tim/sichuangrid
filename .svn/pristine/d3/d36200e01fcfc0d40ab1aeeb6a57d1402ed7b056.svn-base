package com.tianque.baseInfo.dangerousChemicalsUnit.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.dangerousChemicalsUnit.dao.SearchDangerousChemicalsUnitDao;
import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
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
import com.tianque.domain.vo.SearchDangerousChemicalsUnitVo;
import com.tianque.excel.definition.DangerousChemicalUnitContext;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Transactional
@Scope("prototype")
@Controller("searchDangerousChemicalsUnitController")
@Namespace("/baseinfo/searchDangerousChemicalsUnit")
public class SearchDangerousChemicalsUnitController extends SearchBaseAction {

	private SearchDangerousChemicalsUnitVo searchDangerousChemicalsUnitVo;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SearchDangerousChemicalsUnitDao searchDangerousChemicalsUnitDao;
	private Long organizationId;
	private DangerousChemicalsUnit dangerousChemicalsUnit;
	private boolean pageOnly;
	@Autowired
	private SystemLogService systemLogService;

	/**
	 * 查询危险化学品单位
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "searchDangerousChemicalsUnit")
	@Action(value = "findDangerousChemicalsUnitsByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findDangerousChemicalsUnitsByQueryCondition()
			throws Exception {
		if (searchDangerousChemicalsUnitVo == null && organizationId == null) {
			this.errorMessage = "参数错误";
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchDangerousChemicalsUnitVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<DangerousChemicalsUnit> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchDangerousChemicalsUnitDao
						.findDangerousChemicalsUnitByQueryCondition(
								searchDangerousChemicalsUnitVo, page, rows,
								sidx, sord), organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearch() throws Exception {
		if (searchDangerousChemicalsUnitVo == null) {
			gridPage = new GridPage(emptyDangerousChemicalsUnitPage(rows));
			return SUCCESS;
		} else {
			fastSearchCommonality();
		}
		return SUCCESS;
	}

	private void fastSearchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchDangerousChemicalsUnitVo.setOrgInternalCode(ownerOrg
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchDangerousChemicalsUnitDao
						.fastSearchDangerousChemicalsUnit(
								searchDangerousChemicalsUnitVo, page, rows,
								sidx, sord), organizationDubboService,
				new String[] { "organization" }, organizationId));
	}

	private PageInfo<DangerousChemicalsUnit> emptyDangerousChemicalsUnitPage(
			int pageSize) {
		PageInfo<DangerousChemicalsUnit> pageInfo = new PageInfo<DangerousChemicalsUnit>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<DangerousChemicalsUnit>());
		return pageInfo;
	}

	/**
	 * 导出
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "downDangerousChemicalsUnit")
	@Action(value = "downloadDangerousChemicalsUnit")
	public String downloadDangerousChemicalsUnit() throws Exception {

		if (searchDangerousChemicalsUnitVo == null && organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			populateOrgCondition();
			List<DangerousChemicalsUnit> records = getNeedExportDatas(page);
			String[][] excelColumArray = DangerousChemicalUnitContext
					.getDangerousChemicalsUnitArray();
			inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
					records);
			downloadFileName = new String("危险化学品单位清单".getBytes("gbk"),
					"ISO8859-1") + ".xls";
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.DRUGGY,
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
									+ " 导出危险化学品",
							ObjectToJSON
									.convertJSON(searchDangerousChemicalsUnitVo));
		}
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downDangerousChemicalsUnit")
	@Action(value = "downloadDangerousChemicalsUnitAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchDangerousChemicalsUnitVo == null) {
			searchDangerousChemicalsUnitVo = new SearchDangerousChemicalsUnitVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchDangerousChemicalsUnitVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = searchDangerousChemicalsUnitDao
					.getCount(searchDangerousChemicalsUnitVo);
			String[][] excelDefines = DangerousChemicalUnitContext
					.getDangerousChemicalsUnitArray();
			exportDataAll(count, excelDefines, "危险化学品单位清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.DRUGGY,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 导出危险化学品",
						ObjectToJSON
								.convertJSON(searchDangerousChemicalsUnitVo));
	}

	private void populateOrgCondition() {
		if (searchDangerousChemicalsUnitVo == null) {
			searchDangerousChemicalsUnitVo = new SearchDangerousChemicalsUnitVo();
		}
		if (organizationId != null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null) {
				searchDangerousChemicalsUnitVo.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				searchDangerousChemicalsUnitVo.setOrgInternalCode(null);
			}
		} else {
			searchDangerousChemicalsUnitVo.setOrgInternalCode(null);
		}
		if (dangerousChemicalsUnit != null) {
			searchDangerousChemicalsUnitVo
					.setIsEmphasis(searchDangerousChemicalsUnitVo
							.getIsEmphasis());
		}
	}

	public List<DangerousChemicalsUnit> getNeedExportDatas(int page) {
		List<DangerousChemicalsUnit> list = new ArrayList<DangerousChemicalsUnit>();
		if (pageOnly) {
			list = searchDangerousChemicalsUnitDao
					.searchDangerousChemicalsUnitForExport(
							searchDangerousChemicalsUnitVo, page, rows, sidx,
							sord);
		} else {
			list = searchDangerousChemicalsUnitDao
					.searchDangerousChemicalsUnitForExport(
							searchDangerousChemicalsUnitVo, null, null, sidx,
							sord);
		}
		return list;
	}

	/**
	 * 序号|属性|中文名|属性类型|是否采用默认样式|合并行|合并列|
	 * 
	 * @return
	 */
	public SearchDangerousChemicalsUnitVo getSearchDangerousChemicalsUnitVo() {
		return searchDangerousChemicalsUnitVo;
	}

	public void setSearchDangerousChemicalsUnitVo(
			SearchDangerousChemicalsUnitVo searchDangerousChemicalsUnitVo) {
		this.searchDangerousChemicalsUnitVo = searchDangerousChemicalsUnitVo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public DangerousChemicalsUnit getDangerousChemicalsUnit() {
		return dangerousChemicalsUnit;
	}

	public void setDangerousChemicalsUnit(
			DangerousChemicalsUnit dangerousChemicalsUnit) {
		this.dangerousChemicalsUnit = dangerousChemicalsUnit;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

}
