package com.tianque.baseInfo.idleYouth.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.idleYouth.dao.SearchIdleYouthDao;
import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.baseInfo.idleYouth.service.IdleYouthService;
import com.tianque.baseInfo.idleYouth.service.SearchIdleYouthService;
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
import com.tianque.domain.vo.SearchIdleYouthVo;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@SuppressWarnings("serial")
@Transactional
@Controller("searchIdleYouthController")
@Scope("prototype")
public class SearchIdleYouthController extends SearchBaseAction {
	private SearchIdleYouthVo searchIdleYouthVo;
	@Autowired
	private IdleYouthService idleYouthService;
	@Autowired
	private SearchIdleYouthDao searchIdleYouthDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SearchIdleYouthService searchIdleYouthService;

	private List<AutoCompleteData> autoCompleteDatas = new ArrayList<AutoCompleteData>();
	private String tag;
	private Long organizationId;
	private String issueSerch = "";
	private boolean pageOnly;
	private IdleYouth idleYouth;
	private String staffTypeIds;
	private IdleYouth population;
	@Autowired
	private SystemLogService systemLogService;

	private Organization org;

	public String searchIdleYouth() throws Exception {
		if (searchIdleYouthVo == null) {
			searchIdleYouthVo = new SearchIdleYouthVo();
		}
		searchCommonality();
		return SUCCESS;
	}

	private void searchCommonality() throws Exception {
		if (issueSerch.equalsIgnoreCase("issueSerch")) {
			organizationId = organizationDubboService
					.getTownOrganizationId(organizationId);
		}
		if (staffTypeIds != null && !"".equals(staffTypeIds)) {
			String[] ids = staffTypeIds.split(",");
			searchIdleYouthVo.setStaffTypeId(Arrays.asList(ids));
		}

		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchIdleYouthVo.setOrgInternalCode(organization.getOrgInternalCode());
		PageInfo<IdleYouth> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchIdleYouthDao.searchIdleYouth(
						searchIdleYouthVo, page, rows, sidx, sord),
						organizationDubboService, new String[] { "organization" },
						organizationId);
		gridPage = new GridPage(pageInfo);
	}

	@PermissionFilter(ename = "downIdleYouth")
	public String downloadIdleYouth() throws Exception {
		if (searchIdleYouthVo == null && organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			populateOrgCondition();
			List<IdleYouth> records = getNeedExportDatas(page);
			inputStream = ExcelExportHelper.exportDateToExcel(
					idleYouthService.getExportPopertyArray(), records);
			downloadFileName = new String("重点青少年清单".getBytes("gbk"),
					"ISO8859-1") + ".xls";
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.IDLEYOUTH,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													org.getId(),
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId())
									+ "导出重点青少年", ObjectToJSON
									.convertJSON(searchIdleYouthVo));
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "downIdleYouth")
	public void downloadExcelExportAll() throws Exception {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (!pageOnly) {
			populateOrgCondition();
			Integer count = searchIdleYouthService.getCount(searchIdleYouthVo);
			String[][] excelDefines = idleYouthService.getExportPopertyArray();
			pageOnly = true;
			exportDataAll(count, excelDefines, "重点青少年清单");
			systemLogService
					.log(Logger.INFO,
							ModuleConstants.IDLEYOUTH,
							OperatorType.EXPORT,
							ThreadVariable.getSession().getUserName()
									+ "在"
									+ organizationDubboService
											.getOrganizationRelativeNameByRootOrgIdAndOrgId(
													org.getId(),
													OrganizationServiceHelper
															.getRootOrg(
																	organizationDubboService)
															.getId())
									+ "导出重点青少年", ObjectToJSON
									.convertJSON(searchIdleYouthVo));
		}
	}

	public List<IdleYouth> getNeedExportDatas(int page) {
		List<IdleYouth> list = new ArrayList<IdleYouth>();
		if (population != null) {
			searchIdleYouthVo.setIsEmphasis(population.getIsEmphasis());
		}
		if (pageOnly) {
			list = searchIdleYouthService.findIdleYouthsForExport(
					searchIdleYouthVo, page, rows, sidx, sord);
		} else {
			return searchIdleYouthDao.searchIdleYouthsForExport(
					searchIdleYouthVo, null, null, sidx, sord);
		}
		return list;
	}

	private void populateOrgCondition() {
		if (searchIdleYouthVo == null) {
			searchIdleYouthVo = new SearchIdleYouthVo();
		}
		if (organizationId != null) {
			org = organizationDubboService.getSimpleOrgById(organizationId);
			if (org != null) {
				searchIdleYouthVo.setOrgInternalCode(org.getOrgInternalCode());
			} else {
				searchIdleYouthVo.setOrgInternalCode(null);
			}
		} else {
			searchIdleYouthVo.setOrgInternalCode(null);
		}
		if (idleYouth != null) {
			searchIdleYouthVo.setIsEmphasis(idleYouth.getIsEmphasis());
		}

	}

	public String searchIdleYouthForAutoComplete() throws Exception {
		if (organizationId == null) {
			organizationId = ThreadVariable.getUser().getOrganization().getId();
		}
		organizationId = organizationDubboService
				.getTownOrganizationId(organizationId);
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		List<IdleYouth> idleYouthList = searchIdleYouthDao
				.findIdleYouthNameAndPinyinAndOrgInternalCode(tag,
						organization.getOrgInternalCode());
		for (IdleYouth idleYouth : idleYouthList) {
			AutoCompleteData autoCompleteData = new AutoCompleteData();
			autoCompleteData.setLabel(idleYouth.getName());
			autoCompleteData.setValue(idleYouth.getId().toString());
			autoCompleteDatas.add(autoCompleteData);
		}
		return SUCCESS;
	}

	public String getIssueSerch() {
		return issueSerch;
	}

	public void setIssueSerch(String issueSerch) {
		this.issueSerch = issueSerch;
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

	public IdleYouth getIdleYouth() {
		return idleYouth;
	}

	public void setIdleYouth(IdleYouth idleYouth) {
		this.idleYouth = idleYouth;
	}

	public SearchIdleYouthVo getSearchIdleYouthVo() {
		return searchIdleYouthVo;
	}

	public void setSearchIdleYouthVo(SearchIdleYouthVo searchIdleYouthVo) {
		this.searchIdleYouthVo = searchIdleYouthVo;
	}

	public String getStaffTypeIds() {
		return staffTypeIds;
	}

	public void setStaffTypeIds(String staffTypeIds) {
		this.staffTypeIds = staffTypeIds;
	}

	public IdleYouth getPopulation() {
		return population;
	}

	public void setPopulation(IdleYouth population) {
		this.population = population;
	}

}
