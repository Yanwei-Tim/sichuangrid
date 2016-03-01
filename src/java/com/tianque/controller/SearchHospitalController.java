package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
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
import com.tianque.domain.Hospital;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchHospitalVo;
import com.tianque.excel.definition.HospitalContext;
import com.tianque.service.HospitalService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.vladium.logging.Logger;

@SuppressWarnings("serial")
@Controller("searchHospitalController")
@Scope("prototype")
@Transactional
@Namespace("/baseinfo/searchHospital")
public class SearchHospitalController extends SearchBaseAction {

	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private SystemLogService systemLogService;
	private boolean pageOnly;
	private SearchHospitalVo searchHospitalVo;
	private Long organizationId;
	private Hospital location;

	@PermissionFilter(ename = "searchPublicPlace")
	@Action(value = "findHospitalsByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findHospitalsByQueryCondition() throws Exception {
		if (searchHospitalVo == null && organizationId == null) {
			this.errorMessage = "参数错误";
		} else {
			searchCommonality();
		}

		return SUCCESS;
	}

	private void searchCommonality() {
		Organization ownerOrg = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchHospitalVo = searchHospitalVo == null ? new SearchHospitalVo()
				: searchHospitalVo;
		searchHospitalVo.setOrgInternalCode(ownerOrg.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				hospitalService.searchHospitalForPage(page, rows, sidx, sord,
						searchHospitalVo), organizationDubboService,
				new String[] { "organization" }, organizationId));
	}

	@Action(value = "fastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "igonreHierarch", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String fastSearch() throws Exception {
		if (searchHospitalVo == null) {
			gridPage = new GridPage(emptyRectificativePersonPage(rows));
			return SUCCESS;
		} else {
			searchCommonality();
		}
		return SUCCESS;
	}

	private PageInfo<PublicPlace> emptyRectificativePersonPage(Integer pageSize) {
		PageInfo<PublicPlace> pageInfo = new PageInfo<PublicPlace>();
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PublicPlace>());
		pageInfo.setTotalRowSize(0);
		return pageInfo;
	}

	@PermissionFilter(ename = "downHospital")
	@Action(value = "downloadHospital")
	public String downloadHospital() throws Exception {
		if (null == organizationId) {
			this.errorMessage = "无法定位到需要查找的数据";
			return ERROR;
		} else {
			locationOrgCondtion();
			List<Hospital> records = getNeedExportDatas(page);
			String[][] excelColumArray = HospitalContext.getHospitalInfoArray();
			inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
					records);
			downloadFileName = new String("医院清单".getBytes("gbk"), "ISO8859-1")
					+ ".xls";

			systemLogService
					.log(Logger.INFO,
							ModuleConstants.PUBLICPLACE,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													organizationId,
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId()) + " 医院",
							ObjectToJSON.convertJSON(searchHospitalVo));
		}
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downHospital")
	@Action(value = "downloadHospitalAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchHospitalVo == null) {
			searchHospitalVo = new SearchHospitalVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchHospitalVo.setOrgInternalCode(organization.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = hospitalService.getCount(searchHospitalVo);
			String[][] excelDefines = HospitalContext.getHospitalInfoArray();
			exportDataAll(count, excelDefines, "医院清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.PUBLICPLACE,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 医院",
						ObjectToJSON.convertJSON(searchHospitalVo));
	}

	public List<Hospital> getNeedExportDatas(int page) {
		List<Hospital> list = new ArrayList<Hospital>();
		if (pageOnly) {
			list = hospitalService.searchHospitalForPage(page, rows, sidx,
					sord, searchHospitalVo).getResult();
		} else {
			list = hospitalService.searchHospitalForPage(null, rows, sidx,
					sord, searchHospitalVo).getResult();
		}
		return list;
	}

	private void locationOrgCondtion() {
		if (null == searchHospitalVo) {
			searchHospitalVo = new SearchHospitalVo();
		}
		if (null != organizationId) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null) {
				searchHospitalVo.setOrgInternalCode(org.getOrgInternalCode());
			} else {
				searchHospitalVo.setOrgInternalCode(null);
			}
		} else {
			searchHospitalVo.setOrgInternalCode(null);
		}
		if (null != location) {
			searchHospitalVo.setIsEmphasis(location.getIsEmphasis());
		}
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public SearchHospitalVo getSearchHospitalVo() {
		return searchHospitalVo;
	}

	public void setSearchHospitalVo(SearchHospitalVo searchHospitalVo) {
		this.searchHospitalVo = searchHospitalVo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Hospital getLocation() {
		return location;
	}

	public void setLocation(Hospital location) {
		this.location = location;
	}

}
