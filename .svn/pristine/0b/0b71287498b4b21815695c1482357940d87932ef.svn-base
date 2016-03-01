package com.tianque.baseInfo.scenicManage.scenicEquipment.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.scenicManage.scenicEquipment.domain.ScenicEquipment;
import com.tianque.baseInfo.scenicManage.scenicEquipment.service.ScenicEquipmentService;
import com.tianque.baseInfo.scenicManage.scenicEquipment.vo.SearchScenicEquipmentVo;
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

@Scope("prototype")
@Controller("searchScenicEquipmentController")
@Namespace("/baseinfo/searchScenicEquipment")
public class SearchScenicEquipmentController extends SearchBaseAction {

	@Autowired
	private ScenicEquipmentService ScenicEquipmentService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	private Long organizationId;
	private SearchScenicEquipmentVo searchScenicEquipmentVo;
	private boolean pageOnly;
	private ScenicEquipment location;

	@PermissionFilter(ename = "searchScenicEquipment")
	@Action(value = "findScenicEquipmentsByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findScenicEquipmentsByQueryCondition() throws Exception {
		if (searchScenicEquipmentVo == null && organizationId == null) {
			this.errorMessage = "参数错误";
		} else {
			searchCommonality();
		}

		return SUCCESS;
	}

	@Action(value = "fastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "igonreHierarch", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String fastSearch() throws Exception {
		if (searchScenicEquipmentVo == null) {
			gridPage = new GridPage(emptyScenicEquipmentPage(rows));
			return SUCCESS;
		} else {
			searchCommonality();
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "downScenicEquipment")
	@Action(value = "downloadScenicEquipment")
	public String downloadScenicEquipment() throws Exception {
		if (null == organizationId) {
			this.errorMessage = "无法定位到需要查找的数据";
			return ERROR;
		} else {

			locationOrgCondtion();
			List<ScenicEquipment> records = getNeedExportDatas(page);
			String[][] excelColumArray = SpecialGroupsContext
					.getScenicEquipmentImportArray();
			inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
					records);
			downloadFileName = new String("配套设施清单".getBytes("gbk"), "ISO8859-1")
					+ ".xls";

			systemLogService
					.log(Logger.INFO,
							ModuleConstants.SCENICSEQUIPMENT,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId()) + " 配套设施",
							ObjectToJSON.convertJSON(searchScenicEquipmentVo));
		}
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downScenicEquipment")
	@Action(value = "downloadScenicEquipmentAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchScenicEquipmentVo == null) {
			searchScenicEquipmentVo = new SearchScenicEquipmentVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchScenicEquipmentVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = ScenicEquipmentService
					.getCount(searchScenicEquipmentVo);
			String[][] excelDefines = SpecialGroupsContext
					.getScenicEquipmentImportArray();
			exportDataAll(count, excelDefines, "配套设施清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.SCENICSEQUIPMENT,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 配套设施",
						ObjectToJSON.convertJSON(searchScenicEquipmentVo));
	}

	public List<ScenicEquipment> getNeedExportDatas(int page) {
		List<ScenicEquipment> list = new ArrayList<ScenicEquipment>();
		if (pageOnly) {
			list = ScenicEquipmentService.searchScenicEquipmentForPage(page,
					rows, sidx, sord, searchScenicEquipmentVo).getResult();
		} else {
			list = ScenicEquipmentService.searchScenicEquipmentForPage(null,
					rows, sidx, sord, searchScenicEquipmentVo).getResult();
		}
		return list;
	}

	private void locationOrgCondtion() {
		if (null == searchScenicEquipmentVo) {
			searchScenicEquipmentVo = new SearchScenicEquipmentVo();
		}
		if (null != organizationId) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null) {
				searchScenicEquipmentVo.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				searchScenicEquipmentVo.setOrgInternalCode(null);
			}
		} else {
			searchScenicEquipmentVo.setOrgInternalCode(null);
		}
		if (null != location) {
			searchScenicEquipmentVo.setIsEmphasis(location.getIsEmphasis());
		}
	}

	private void searchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchScenicEquipmentVo.setOrgInternalCode(ownerOrg
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				ScenicEquipmentService.searchScenicEquipmentForPage(page, rows,
						sidx, sord, searchScenicEquipmentVo),
				organizationDubboService, new String[] { "organization" },
				organizationId));
	}

	private PageInfo<ScenicEquipment> emptyScenicEquipmentPage(Integer pageSize) {
		PageInfo<ScenicEquipment> pageInfo = new PageInfo<ScenicEquipment>();
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<ScenicEquipment>());
		pageInfo.setTotalRowSize(0);
		return pageInfo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public SearchScenicEquipmentVo getSearchScenicEquipmentVo() {
		return searchScenicEquipmentVo;
	}

	public void setSearchScenicEquipmentVo(
			SearchScenicEquipmentVo searchScenicEquipmentVo) {
		this.searchScenicEquipmentVo = searchScenicEquipmentVo;
	}

	public ScenicEquipment getLocation() {
		return location;
	}

	public void setLocation(ScenicEquipment location) {
		this.location = location;
	}

}
