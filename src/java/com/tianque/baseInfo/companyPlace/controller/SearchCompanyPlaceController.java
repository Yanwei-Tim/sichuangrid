package com.tianque.baseInfo.companyPlace.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceBase;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceVo;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBaseSevice;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceService;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.excel.definition.CompanyPlaceContext;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Scope("prototype")
@Controller("searchCompanyPlaceController")
@Namespace("/baseinfo/searchCompanyPlace")
public class SearchCompanyPlaceController extends SearchBaseAction {
	@Autowired
	private CompanyPlaceBaseSevice companyPlaceBaseService;
	@Autowired
	private CompanyPlaceService companyPlaceService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	private CompanyPlace companyPlace;// 实体对象
	private CompanyPlace location;
	private CompanyPlaceVo companyPlaceVo; // 查询对象

	private CompanyPlaceBase companyPlaceBase;

	private boolean pageOnly;
	private Long organizationId;
	private String locationIds;
	private String modulKey;
	private String modul;

	/**
	 * 修改关注状态
	 */

	@PermissionFilters(value = {
			@PermissionFilter(ename = "newCancelAttendedCompanyPlace", actionName = "updateEmphasiseById"),
			@PermissionFilter(ename = "newAttendedCompanyPlace", actionName = "updateEmphasiseById") })
	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		if (locationIds == null) {
			this.errorMessage = "参数信息错误！";
			return ERROR;
		}
		companyPlaceBaseService.updateCompanyPlaceIsEmphasis(
				analyzeLocationIds(), location, modulKey);
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "updateEmphasiseByEncryptId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseByEncryptId() throws Exception {
		if (locationIds == null) {
			this.errorMessage = "参数信息错误！";
			return ERROR;
		}
		companyPlaceBaseService.updateCompanyPlaceIsEmphasis(
				analyzeLocationIds(), location, modulKey);
		return SUCCESS;
	}

	/**
	 * 导出本页数据
	 */
	@Action(value = "downloadCompanyPlace")
	public String downloadActualCompany() throws Exception {
		this.setOrganizationId(companyPlaceVo.getOrg().getId());
		if (companyPlace == null && organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			orgCondition();
			List<CompanyPlace> records = getNeedExportDatas(page);
			String[][] excelColumArray = CompanyPlaceContext
					.getCompanyPlaceArray(modulKey);
			inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
					records);
			downloadFileName = new String(
					(getModulName() + "清单").getBytes("gbk"), "ISO8859-1")
					+ ".xls";
			systemLogService
					.log(Logger.INFO,
							ModulTypes.getLogType(modulKey),
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "导出"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId()) + "下的部分"
									+ ModulTypes.getCnNameByKey(modulKey),
							ObjectToJSON.convertJSON(companyPlace));
		}
		return STREAM_SUCCESS;

	}

	/**
	 * 导出全部
	 * 
	 * @return
	 * 
	 */
	@Action(value = "downloadCompanyPlaceAll")
	public void downloadCompanyPlaceAll() throws Exception {
		this.setOrganizationId(companyPlaceVo.getOrg().getId());
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		} else {
			orgCondition();
			if (!pageOnly) {
				pageOnly = true;
				Integer count = companyPlaceBaseService
						.queryCompanyPlaceForList(companyPlaceVo, modulKey)
						.size();
				exportDataAll(count,
						CompanyPlaceContext.getCompanyPlaceArray(modulKey),
						getModulName() + "清单");
			}

			systemLogService
					.log(Logger.INFO,
							ModulTypes.getLogType(modulKey),
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "导出"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId()) + "下全部的"
									+ ModulTypes.getCnNameByKey(modulKey),
							ObjectToJSON.convertJSON(companyPlace));
		}
		return;
	}

	// 被调用使用方法
	private void orgCondition() {
		if (companyPlace == null) {
			companyPlace = new CompanyPlace();
		}
		if (organizationId != null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null) {
				companyPlace.setOrginternalcode(org.getOrgInternalCode());
			} else {
				companyPlace.setOrginternalcode(null);
			}
		} else {
			companyPlace.setOrginternalcode(null);
		}
	}

	public List<CompanyPlace> getNeedExportDatas(int page) {
		if (pageOnly) {
			return companyPlaceBaseService.queryCompanyPlaceForPageResult(
					companyPlaceVo, modulKey, defaultSortAndPage())
					.getResultList();
		} else {
			return companyPlaceBaseService.queryCompanyPlaceForList(
					companyPlaceVo, modulKey);
		}
	}

	private List<Long> analyzeLocationIds() {
		String[] deleteId = locationIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		return idList;
	}

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	// 获取单位场所模块名称
	private String getModulName() {
		if (null == modul || "".equals(modul)) {
			return ModulTypes.CompanyPlace;
		} else {
			return ModulTypes.getTypeByName(modul);
		}
	}

	public CompanyPlace getCompanyPlace() {
		return companyPlace;
	}

	public void setCompanyPlace(CompanyPlace companyPlace) {
		this.companyPlace = companyPlace;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public CompanyPlaceVo getCompanyPlaceVo() {
		return companyPlaceVo;
	}

	public void setCompanyPlaceVo(CompanyPlaceVo companyPlaceVo) {
		this.companyPlaceVo = companyPlaceVo;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

	public CompanyPlaceBase getCompanyPlaceBase() {
		return companyPlaceBase;
	}

	public void setCompanyPlaceBase(CompanyPlaceBase companyPlaceBase) {
		this.companyPlaceBase = companyPlaceBase;
	}

	public CompanyPlace getLocation() {
		return location;
	}

	public void setLocation(CompanyPlace location) {
		this.location = location;
	}

	public String getModulKey() {
		return modulKey;
	}

	public void setModulKey(String modulKey) {
		this.modulKey = modulKey;
	}

	public String getModul() {
		return modul;
	}

	public void setModul(String modul) {
		this.modul = modul;
	}

}
