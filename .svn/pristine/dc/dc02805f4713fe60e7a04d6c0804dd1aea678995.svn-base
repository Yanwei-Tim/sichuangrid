package com.tianque.baseInfo.positiveInfo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.controller.ControllerHelper;
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
import com.tianque.domain.vo.SearchPositiveInfoVo;
import com.tianque.service.SearchPositiveInfosService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@SuppressWarnings("serial")
@Controller("searchPositiveInfoController")
@Scope("prototype")
public class SearchPositiveInfoController extends SearchBaseAction {
	@Autowired
	private SystemLogService systemLogService;

	public SystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(SystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	@Autowired
	private SearchPositiveInfosService searchPositiveInfosService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private SearchPositiveInfoVo searchPositiveInfoVo;
	private Long organizationId;
	private boolean pageOnly;
	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();
	private String tag;
	private String issueSerch = "";
	private PositiveInfo positiveInfo;
	private PositiveInfo population;

	public String searchPositiveInfo() throws Exception {
		if (searchPositiveInfoVo == null) {
			searchPositiveInfoVo = new SearchPositiveInfoVo();
		}
		searchCommonality();
		return SUCCESS;
	}

	private void searchCommonality() {
		if (issueSerch.equalsIgnoreCase("issueSerch")) {
			organizationId = organizationDubboService
					.getTownOrganizationId(organizationId);
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchPositiveInfoVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (searchPositiveInfoVo.getIdCardNo() != null
				&& !searchPositiveInfoVo.getIdCardNo().equals("")) {
			searchPositiveInfoVo.setIdCardNo(searchPositiveInfoVo.getIdCardNo()
					.toUpperCase());
		}
		PageInfo<PositiveInfo> trampResidents = ControllerHelper
				.processAllOrgRelativeName(searchPositiveInfosService
						.searchAttentionPersonnel(searchPositiveInfoVo, page,
								rows, sidx, sord), organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(trampResidents);
	}

	public String downloadPostiveInfoPersonnel() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		if (searchPositiveInfoVo == null) {
			searchPositiveInfoVo = new SearchPositiveInfoVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchPositiveInfoVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		List<PositiveInfo> records = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				searchPositiveInfosService.getExportPopertyArray(), records);
		downloadFileName = new String("刑释人员清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.POSIVITEINFO,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + " 导出刑事解教人员",
						ObjectToJSON.convertJSON(searchPositiveInfoVo));
		return STREAM_SUCCESS;
	}

	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchPositiveInfoVo == null) {
			searchPositiveInfoVo = new SearchPositiveInfoVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchPositiveInfoVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = searchPositiveInfosService
					.getCount(searchPositiveInfoVo);
			String[][] excelDefines = searchPositiveInfosService
					.getExportPopertyArray();
			exportDataAll(count, excelDefines, "刑释人员清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.POSIVITEINFO,
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
								+ " 导出全部刑事解教人员", ObjectToJSON
								.convertJSON(searchPositiveInfoVo));
	}

	public List<PositiveInfo> getNeedExportDatas(int page) {
		List<PositiveInfo> list = new ArrayList<PositiveInfo>();
		if (population != null) {
			searchPositiveInfoVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			list = searchPositiveInfosService.searchPositiveInfoForExport(
					searchPositiveInfoVo, page, rows, sidx, sord);
		} else {
			list = searchPositiveInfosService.searchPositiveInfoForExport(
					searchPositiveInfoVo, null, null, sidx, sord);
		}
		return list;
	}

	public String getIssueSerch() {
		return issueSerch;
	}

	public void setIssueSerch(String issueSerch) {
		this.issueSerch = issueSerch;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public List<AutoCompleteData> getAutoCompleteDatas() {
		return autoCompleteDatas;
	}

	public void setAutoCompleteDatas(List<AutoCompleteData> autoCompleteDatas) {
		this.autoCompleteDatas = autoCompleteDatas;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public SearchPositiveInfoVo getSearchPositiveInfoVo() {
		return searchPositiveInfoVo;
	}

	public void setSearchPositiveInfoVo(
			SearchPositiveInfoVo searchPositiveInfoVo) {
		this.searchPositiveInfoVo = searchPositiveInfoVo;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public PositiveInfo getPositiveInfo() {
		return positiveInfo;
	}

	public void setPositiveInfos(PositiveInfo positiveInfo) {
		this.positiveInfo = positiveInfo;
	}

	public PositiveInfo getPopulation() {
		return population;
	}

	public void setPopulation(PositiveInfo population) {
		this.population = population;
	}

}
