package com.tianque.baseInfo.unsettledPopulation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.baseInfo.unsettledPopulation.service.SearchUnsettledPopulationService;
import com.tianque.baseInfo.unsettledPopulation.service.UnsettledPopulationService;
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
import com.tianque.domain.vo.BaseUnsettledPopulationSearchCondition;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Scope("prototype")
@Transactional(readOnly = true)
@SuppressWarnings("serial")
@Controller("unsettledPopulationSearchController")
public class UnsettledPopulationSearchController extends SearchBaseAction {

	private BaseUnsettledPopulationSearchCondition unsettledPopulationCondition;

	private String issueSerch = "";

	private Long orgId;

	private Organization organization;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SearchUnsettledPopulationService searchUnsettledPopulationServuce;
	private boolean pageOnly;
	@Autowired
	private UnsettledPopulationService unsettledPopulationService;
	@Autowired
	private SystemLogService systemLogService;

	private Long death;
	private Long isSearch;

	@PermissionFilter(ename = "searchUnsettledPopulation")
	public String searchUnsettledPopulation() throws Exception {
		if (unsettledPopulationCondition == null) {
			unsettledPopulationCondition = new BaseUnsettledPopulationSearchCondition();
			this.errorMessage = "参数错误";
		}
		if (issueSerch.equalsIgnoreCase("issueSerch")) {
			orgId = organizationDubboService.getTownOrganizationId(orgId);
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);

		unsettledPopulationCondition.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<UnsettledPopulation> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchUnsettledPopulationServuce
						.searchUnsettledPopulation(
								unsettledPopulationCondition, page, rows, sidx,
								sord), organizationDubboService,
						new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * copy一份为手机端提供
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchUnsettledPopulationForMobile() throws Exception {
		if (unsettledPopulationCondition == null) {
			unsettledPopulationCondition = new BaseUnsettledPopulationSearchCondition();
			this.errorMessage = "参数错误";
		}
		if (issueSerch.equalsIgnoreCase("issueSerch")) {
			orgId = organizationDubboService.getTownOrganizationId(orgId);
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);

		unsettledPopulationCondition.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<UnsettledPopulation> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchUnsettledPopulationServuce
						.searchUnsettledPopulation(
								unsettledPopulationCondition, page, rows, sidx,
								sord), organizationDubboService,
						new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String fastSearchUnsettledPopulation() throws Exception {
		if (unsettledPopulationCondition == null) {
			gridPage = new GridPage(emptyUnsettledPopulationPage(rows));
			return SUCCESS;
		} else {
			fastSearchCommonality();
		}
		return SUCCESS;
	}

	private void fastSearchCommonality() {
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		unsettledPopulationCondition.setOrgInternalCode(organization
				.getOrgInternalCode());
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				searchUnsettledPopulationServuce
						.fastSearchemptyUnsettledPopulationPage(
								unsettledPopulationCondition, page, rows, sidx,
								sord), organizationDubboService,
				new String[] { "organization" }, orgId));
	}

	private PageInfo<UnsettledPopulation> emptyUnsettledPopulationPage(
			int pageSize) {
		PageInfo<UnsettledPopulation> pageInfo = new PageInfo<UnsettledPopulation>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<UnsettledPopulation>());
		return pageInfo;
	}

	public String downloadUnsettledPopulation() throws Exception {
		if (orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		if (unsettledPopulationCondition == null) {
			unsettledPopulationCondition = new BaseUnsettledPopulationSearchCondition();
			unsettledPopulationCondition.setLogOut(0L);
		}
		// if (death != null) {
		// unsettledPopulationCondition.setIsDeath(death);
		// }
		// if (unsettledPopulationCondition.getIsDeath() == null
		// && unsettledPopulationCondition.getLogOut() == null) {
		// unsettledPopulationCondition.setLogOut(0L);
		// }
		// if (null == unsettledPopulationCondition.getIsDeath()) {
		// unsettledPopulationCondition.setIsDeath(0L);
		// }
		organization = organizationDubboService.getSimpleOrgById(orgId);
		unsettledPopulationCondition.setOrgInternalCode(organization
				.getOrgInternalCode());
		List<UnsettledPopulation> unsettledPopulationList = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				SpecialGroupsContext.getUnsettledPopulationPropertyArray(),
				unsettledPopulationList);
		downloadFileName = new String("未落户人口清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.UNSETTEDPOPULATION,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出未落户人口",
						ObjectToJSON.convertJSON(new UnsettledPopulation()));
		return SUCCESS;
	}

	public void downloadExcelExportAll() throws Exception {
		if (orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (null == unsettledPopulationCondition) {
			unsettledPopulationCondition = new BaseUnsettledPopulationSearchCondition();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		unsettledPopulationCondition.setOrgInternalCode(organization
				.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = unsettledPopulationService
					.getCount(unsettledPopulationCondition);
			String[][] excelDefines = SpecialGroupsContext
					.getUnsettledPopulationPropertyArray();
			exportDataAll(count, excelDefines, "未落户人口清单");
		}
		systemLogService
				.log(Logger.INFO,
						ModuleConstants.UNSETTEDPOPULATION,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出未落户人口",
						ObjectToJSON.convertJSON(new UnsettledPopulation()));
	}

	public BaseUnsettledPopulationSearchCondition getUnsettledPopulationCondition() {
		return unsettledPopulationCondition;
	}

	public void setUnsettledPopulationCondition(
			BaseUnsettledPopulationSearchCondition unsettledPopulationCondition) {
		this.unsettledPopulationCondition = unsettledPopulationCondition;
	}

	public String getIssueSerch() {
		return issueSerch;
	}

	public void setIssueSerch(String issueSerch) {
		this.issueSerch = issueSerch;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public Long getDeath() {
		return death;
	}

	public void setDeath(Long death) {
		this.death = death;
	}

	@Override
	public List getNeedExportDatas(int page) {
		List<UnsettledPopulation> list = new ArrayList<UnsettledPopulation>();
		if (isSearch == null || isSearch != 1) {
			if (unsettledPopulationCondition.getIsDeath() == null) {
				unsettledPopulationCondition.setIsDeath(0L);
			}
			if (unsettledPopulationCondition.getLogOut() == null) {
				unsettledPopulationCondition.setLogOut(0L);
			}
		}
		if (pageOnly) {
			list = unsettledPopulationService
					.findUnsettledPopulationForPageByOrgId(
							unsettledPopulationCondition, orgId, page, rows,
							sidx, sord).getResult();
		} else {
			list = unsettledPopulationService.findUnsettledPopulationList(
					unsettledPopulationCondition, orgId, sidx, sord);
		}
		return list;
	}

	public Long getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(Long isSearch) {
		this.isSearch = isSearch;
	}

}
