package com.tianque.sysadmin.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.AccountLoginDetails;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.AccountLoginDetailsService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("request")
@Controller("accountLoginDetailsController")
@Namespace("/sysadmin/accountLoginDetailsManage")
public class AccountLoginDetailsController extends SearchBaseAction {

	@Autowired
	private AccountLoginDetailsService accountLoginDetailsService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	/** 年份 */
	private Long year;
	/** 月份 */
	private Long month;
	/** 组织机构ID */
	private Long orgId;
	/** 查询类别 */
	private Integer searchType;
	/** 导出本页或全部标识 */
	private boolean pageOnly;

	@Action(value = "getAccountLoginDetailsList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getAccountLoginDetailsList() throws Exception {
		if (year == null || month == null || orgId == null
				|| searchType == null) {
			errorMessage = "查询账号登陆详情失败，未获得正确参数";
			return ERROR;
		}
		PageResult<AccountLoginDetails> pageInfo = ControllerHelper
				.processAllOrgRelativeName(accountLoginDetailsService
						.getAccountLoginDetailsList(year, month, orgId,
								searchType, page, rows, "orgCode", "asc"),
						organizationDubboService,
						new String[] { "organization" }, null);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "createAccountDetails", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String createAccountDetails() throws Exception {
		if (year == null || month == null) {
			errorMessage = "查询账号登陆详情失败，未获得正确参数";
			return ERROR;
		}
		accountLoginDetailsService.createAccountDetails(year, month);
		return SUCCESS;
	}

	public List<AccountLoginDetails> getNeedExportDatas(int page) {
		if (orgId == null || searchType == null) {
			return null;
		}
		if (pageOnly) {
			return ControllerHelper.processAllOrgRelativeName(
					accountLoginDetailsService.getAccountLoginDetailsList(year,
							month, orgId, searchType, page, rows, "orgCode",
							"asc"), organizationDubboService,
					new String[] { "organization" }, null).getResultList();
		} else {
			return ControllerHelper.processAllOrgRelativeName(
					accountLoginDetailsService.getAccountLoginDetailsList(year,
							month, orgId, searchType, 1, Integer.MAX_VALUE,
							"orgCode", "asc"), organizationDubboService,
					new String[] { "organization" }, null).getResultList();
		}
	}

	@Action(value = "downloadAccountLoginDetailsAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadAccountLoginDetailsAll() throws Exception {
		if (orgId == null) {
			errorMessage = "无法定位需要导出的数据";
		} else {
			if (!pageOnly) {
				pageOnly = true;
				Integer count = accountLoginDetailsService
						.getAccountLoginDetailsList(year, month, orgId,
								searchType, 1, Integer.MAX_VALUE, sidx, sord)
						.getResultList().size();
				String[][] excelDefines = SpecialGroupsContext
						.getAccountLoginDetailsPropertyArray();
				exportDataAll(count, excelDefines, "账号统计清单");
			}
			systemLogService.log(com.vladium.logging.Logger.INFO,
					ModelType.USERCOUNTMANAGE, OperatorType.EXPORT,
					ThreadVariable.getSession().getUserName() + "在"
							+ getCurrenOrg() + " 导出全部账号统计清单", null);
		}
	}

	@Action(value = "downloadAccountLoginDetails", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String downloadAccountLoginDetails() throws Exception {
		if (orgId == null || year == null || month == null
				|| searchType == null) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		List<AccountLoginDetails> records = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				SpecialGroupsContext.getAccountLoginDetailsPropertyArray(),
				records);
		downloadFileName = new String("账号登录详情统计".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService.log(com.vladium.logging.Logger.INFO,
				ModelType.USERCOUNTMANAGE, OperatorType.EXPORT, ThreadVariable
						.getSession().getUserName()
						+ "在"
						+ getCurrenOrg()
						+ " 导出当前页账号统计清单", null);
		return STREAM_SUCCESS;
	}

	// 日志获取当前用户操作层级
	private String getCurrenOrg() {
		return organizationDubboService
				.getOrganizationRelativeNameByRootOrgIdAndOrgId(
						orgId,
						OrganizationServiceHelper.getRootOrg(
								organizationDubboService).getId());
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

}
