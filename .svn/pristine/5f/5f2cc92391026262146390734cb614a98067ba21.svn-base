package com.tianque.baseInfo.actualCompany.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.datatransfer.DataType;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchActualCompanyVo;
import com.tianque.service.SearchActualCompanyService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.vladium.logging.Logger;

@Transactional
@Scope("prototype")
@Controller("searchActualCompanyController")
@Namespace("/baseinfo/searchActualCompany")
public class SearchActualCompanyController extends SearchBaseAction {
	@Autowired
	private SearchActualCompanyService searchActualCompanyService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;

	private Long organizationId;
	private SearchActualCompanyVo searchActualCompanyVo;
	private boolean pageOnly;
	private ActualCompany actualCompany;

	/**
	 * 根据查询条件分页查询实有单位
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "searchActualCompany")
	@Action(value = "findActualCompanysByQueryCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findDruggysByQueryCondition() {
		if (searchActualCompanyVo == null && organizationId == null) {
			this.errorMessage = "参数错误";
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		searchActualCompanyVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		PageInfo<ActualCompany> pageInfo = ControllerHelper
				.processAllOrgRelativeName(searchActualCompanyService
						.findActualCompanyByQueryCondition(
								searchActualCompanyVo, page, rows, sidx, sord),
						organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String fastSearch() {
		return findDruggysByQueryCondition();
	}

	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 */
	@PermissionFilter(ename = "downActualCompany")
	@Action(value = "downloadActualCompany")
	public String downloadActualCompany() throws Exception {
		if (searchActualCompanyVo == null && organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		} else {
			orgCondition();
			List<ActualCompany> records = getNeedExportDatas(page);
			String[][] excelColumArray = this.getActualCompanyPropertyArray();
			inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
					records);
			downloadFileName = new String("实有单位清单".getBytes("gbk"), "ISO8859-1")
					+ ".xls";
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
									+ " 导出实有单位", ObjectToJSON
									.convertJSON(searchActualCompanyVo));
		}
		return STREAM_SUCCESS;

	}

	/**
	 * 导出
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "downloadActualCompanyAll")
	@Action(value = "downloadActualCompanyAll")
	public void downloadActualCompanyAll() {
		if (organizationId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		} else {
			orgCondition();
			if (!pageOnly) {
				pageOnly = true;
				Integer count = searchActualCompanyService
						.getCount(searchActualCompanyVo);
				exportDataAll(count, getActualCompanyPropertyArray(), "实有单位清单");
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
															.getId())
									+ " 导出实有单位", ObjectToJSON
									.convertJSON(searchActualCompanyVo));
		}
	}

	// 被调用使用方法
	private void orgCondition() {
		if (searchActualCompanyVo == null) {
			searchActualCompanyVo = new SearchActualCompanyVo();
		}
		if (organizationId != null) {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null) {
				searchActualCompanyVo.setOrgInternalCode(org
						.getOrgInternalCode());
			} else {
				searchActualCompanyVo.setOrgInternalCode(null);
			}
		} else {
			searchActualCompanyVo.setOrgInternalCode(null);
		}
		if (actualCompany != null) {
			searchActualCompanyVo.setIsEmphasis(actualCompany.getIsEmphasis());
		}
	}

	public List<ActualCompany> getNeedExportDatas(int page) {
		if (pageOnly) {
			return searchActualCompanyService.searchActualCompanysForExport(
					searchActualCompanyVo, page, rows, sidx, sord);
		} else {
			return searchActualCompanyService.searchActualCompanysForExport(
					searchActualCompanyVo, null, null, sidx, sord);
		}
	}

	private String[][] getActualCompanyPropertyArray() {
		String[][] excelColumArray = {
				{ "0", "", "实有单位表", "", "", "true", "0", "28" },
				{ "0", "companyName", "单位名称", "", "", "true" },
				{ "1", "companyType", "单位类别", DataType.DATA_TYPE_DICT, "",
						"true" },
				{ "2", "corporateRepresentative", "法人代表", "", "", "true" },
				{ "3", "companyAddress", "单位地址", "", "", "true" },
				{ "4", "supervisoryLevel", "管理级别", DataType.DATA_TYPE_DICT, "",
						"true" },
				{ "5", "supervisoryDepartment", "管理部门", "", "", "true" },
				{ "6", "securityChief", "治安负责人", "", "", "true" },
				{ "7", "isKey", "是否重点单位", DataType.DATA_TYPE_BOOLEAN, "",
						"true" },
				{ "8", "fireFightingLevel", "消防等级", DataType.DATA_TYPE_DICT,
						"", "true" },
				{ "9", "organization", "所属网格", DataType.DATA_TYPE_ORG, "",
						"true" },
				{ "10", "idCardNo", "身份证号码", "", "", "true" },
				{ "11", "telephone", "单位电话", "", "", "true" },
				{ "12", "facsimile", "单位传真", "", "", "true" },
				{ "13", "businessLicenseNo", "营业执照号码", "", "", "true" },
				{ "14", "orgCode", "组织机构代码", "", "", "true" },
				{ "15", "registeredCapital", "注册资本(万元)", "", "", "true" },
				{ "16", "economicNature", "经济性质", DataType.DATA_TYPE_DICT, "",
						"true" },
				{ "17", "expiryDate", "有效期至", DataType.DATA_TYPE_DATE, "",
						"true" },
				{ "18", "registrationDate", "注册日期", DataType.DATA_TYPE_DATE,
						"", "true" },
				{ "19", "businessScope", "经营范围", "", "", "true" },
				{ "20", "registrationAddress", "注册地址", "", "", "true" },
				{ "21", "employeesNum", "从业人数", "", "", "true" },
				{ "22", "competentDepartment", "主管部门", "", "", "true" },
				{ "23", "hasServiceTeamMember", "有无治安负责人",
						DataType.DATA_TYPE_COUNTS, "", "true" },
				{ "24", "lastRecordTime", "最新巡场时间", DataType.DATA_TYPE_DATE,
						"", "true" },
				{ "25", "remark", "备注", "", "", "true" },
				{ "26", "isEmphasis", "是否取消关注", DataType.DATA_TYPE_BOOLEAN, "",
						"true" },
				{ "27", "logOutTime", "取消关注时间", DataType.DATA_TYPE_DATE, "",
						"true" },
				{ "28", "logOutReason", "取消关注原因", "", "", "true" } };

		return excelColumArray;
	}

	// set/get方法
	public SearchActualCompanyService getSearchActualCompanyService() {
		return searchActualCompanyService;
	}

	public void setSearchActualCompanyService(
			SearchActualCompanyService searchActualCompanyService) {
		this.searchActualCompanyService = searchActualCompanyService;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(
			OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public SystemLogService getSystemLogService() {
		return systemLogService;
	}

	public void setSystemLogService(SystemLogService systemLogService) {
		this.systemLogService = systemLogService;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public SearchActualCompanyVo getSearchActualCompanyVo() {
		return searchActualCompanyVo;
	}

	public void setSearchActualCompanyVo(
			SearchActualCompanyVo searchActualCompanyVo) {
		this.searchActualCompanyVo = searchActualCompanyVo;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public ActualCompany getActualCompany() {
		return actualCompany;
	}

	public void setActualCompany(ActualCompany actualCompany) {
		this.actualCompany = actualCompany;
	}

}
