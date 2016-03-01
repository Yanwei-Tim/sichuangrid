package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.datatransfer.DataType;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.PartyMemberInfo;
import com.tianque.domain.PartyOrgActivity;
import com.tianque.domain.PartyOrgInfo;
import com.tianque.domain.vo.SearchPartyOrgInfoVo;
import com.tianque.service.PartyMemberInfoService;
import com.tianque.service.PartyOrgActivityService;
import com.tianque.service.PartyOrgInfoService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

@SuppressWarnings("serial")
@Controller("partyOrgInfoController")
@Scope("prototype")
public class PartyOrgInfoController extends SearchBaseAction {
	@Autowired
	private PartyOrgInfoService partyOrgInfoService;

	@Autowired
	private PartyMemberInfoService partyMemberInfoService;

	@Autowired
	protected OrganizationDubboService organizationDubboService;

	@Autowired
	private PartyOrgActivityService partyOrgActivityService;

	@Autowired
	private SystemLogService systemLogService;

	private PartyOrgInfo partyOrgInfo;

	private SearchPartyOrgInfoVo searchPartyOrgInfoVo;

	private Long orgId;

	private Long partyOrgId;

	private boolean pageOnly;

	public String dispatch() throws Exception {
		if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			partyOrgInfo = this.partyOrgInfoService
					.getPartyOrgInfoById(partyOrgInfo.getId());
			Organization organization = organizationDubboService
					.getSimpleOrgById(orgId);
			partyOrgInfo.setOrganization(organization);
			return "view";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			partyOrgInfo = this.partyOrgInfoService
					.getPartyOrgInfoByOrgId(orgId);
			if (null == partyOrgInfo) {
				partyOrgInfo = new PartyOrgInfo();
			}
			partyOrgInfo.setOrganization(organizationDubboService
					.getSimpleOrgById(orgId));
			return "edit";
		} else if ("list".equals(mode)) {
			return "list";
		}
		return SUCCESS;
	}

	/**
	 * 分页查询下级党建党组织信息
	 * 
	 * @return SUCCESS
	 */
	public String findPartyOrgInfosByOrgInternalCode() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(
					ControllerHelper.processAllOrgRelativeName(
							this.partyOrgInfoService
									.findPartyOrgInfoForPageByOrgInternalCode(
											orgId, page, rows, sidx, sord),
							organizationDubboService,
							new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	/**
	 * 根据查询条件查询下级党建党组织信息
	 * 
	 * @return
	 */
	public String searchPartyOrgInfo() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(new PageInfo<PartyMemberInfo>());
			return SUCCESS;
		}
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		searchPartyOrgInfoVo.setOrganization(organization);
		searchPartyOrgInfoVo.setSortField(sidx);
		searchPartyOrgInfoVo.setOrder(sord);
		PageInfo<PartyOrgInfo> pageInfo = ControllerHelper
				.processAllOrgRelativeName(partyOrgInfoService
						.searchPartyOrgInfos(page, rows, searchPartyOrgInfoVo),
						organizationDubboService, new String[] { "organization" },
						orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 获取下级组织下的所有党员信息
	 * 
	 * @return
	 */
	public String findPartyOrgMemberInfos() throws Exception {
		PageInfo<PartyMemberInfo> populations = new PageInfo<PartyMemberInfo>();
		populations.setCurrentPage(1);
		populations.setResult(this.partyMemberInfoService
				.getPartyMemberInfoByPartyOrgId(partyOrgId));
		gridPage = new GridPage(populations);
		return SUCCESS;
	}

	public String findPartyOrgFoMemberInfos() {
		return SUCCESS;
	}

	/**
	 * 获取本级党建组织信息
	 * 
	 * @return
	 */
	public String findPartyOrgInfo() {
		return SUCCESS;
	}

	/**
	 * 编辑本级党建组织信息
	 * 
	 * @return
	 */
	public String addPartyOrgInfo() throws Exception {
		if (null != orgId) {
			PartyOrgInfo partyOrgInfos = this.partyOrgInfoService
					.getPartyOrgInfoByOrgId(orgId);
			if (null == partyOrgInfos) {
				partyOrgInfos = new PartyOrgInfo();
				BeanUtils.copyProperties(partyOrgInfo, partyOrgInfos);
				Organization organization = organizationDubboService
						.getSimpleOrgById(orgId);
				partyOrgInfos.setOrganization(organization);
				this.partyOrgInfoService.addPartyOrgInfo(partyOrgInfos);
			} else {
				PartyOrgInfo oldPartyOrgInfo = partyOrgInfoService
						.getPartyOrgInfoById(partyOrgInfo.getId());
				BeanUtils.copyProperties(partyOrgInfo, partyOrgInfos);
				Organization organization = organizationDubboService
						.getSimpleOrgById(orgId);
				partyOrgInfo.setOrganization(organization);
				this.partyOrgInfoService.updatePartyOrgInfo(partyOrgInfo,
						oldPartyOrgInfo.getPartyBranchName());
			}
			partyOrgInfo = this.partyOrgInfoService
					.getPartyOrgInfoById(partyOrgInfos.getId());
		}
		return SUCCESS;
	}

	public String searchPartyOrgInfoById() throws Exception {
		if (null != orgId) {
			partyOrgInfo = this.partyOrgInfoService
					.getPartyOrgInfoByOrgId(orgId);
			Organization organization = organizationDubboService
					.getSimpleOrgById(orgId);
			if (null == partyOrgInfo) {
				partyOrgInfo = new PartyOrgInfo();
			}
			partyOrgInfo.setOrganization(organization);
		}
		return SUCCESS;
	}

	/**
	 * 获取下级党建所有党组织活动记录
	 * 
	 * @return SUCCESS
	 */
	public String findPartyOrgActivitys() throws Exception {
		PageInfo<PartyOrgActivity> populations = new PageInfo<PartyOrgActivity>();
		populations.setCurrentPage(1);
		populations.setResult(this.partyOrgActivityService
				.getPartyOrgActivityByPartyOrgId(partyOrgId));
		gridPage = new GridPage(populations);
		return SUCCESS;
	}

	public String partyOrgActivityList() {
		return SUCCESS;
	}

	/**
	 * 导出
	 * 
	 * @return
	 */
	public String downloadPartyOrgInfo() throws Exception {
		if (null == orgId) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		searchPartyOrgInfoVo = new SearchPartyOrgInfoVo();
		Organization organization = this.organizationDubboService
				.getSimpleOrgById(orgId);
		searchPartyOrgInfoVo.setOrganization(organization);
		searchPartyOrgInfoVo.setSortField(sidx);
		searchPartyOrgInfoVo.setOrder(sord);
		List<PartyOrgInfo> partyOrgInfoList = new ArrayList<PartyOrgInfo>();
		if (pageOnly) {
			partyOrgInfoList = this.partyOrgInfoService.searchPartyOrgInfos(
					page, rows, searchPartyOrgInfoVo).getResult();
		} else {
			partyOrgInfoList = this.partyOrgInfoService
					.searchAllPartyOrgInfos(searchPartyOrgInfoVo);
		}
		String[][] excelColumArray = this.getOptimalObjectPropertyArray();
		inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
				partyOrgInfoList);
		downloadFileName = new String(excelColumArray[0][2].getBytes("gbk"),
				"ISO8859-1") + ".xls";
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.LOWERPARTORGINFO,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出党组织信息",
						ObjectToJSON.convertJSON(new HouseholdStaff()));
		return SUCCESS;
	}

	/**
	 * 导出
	 * 
	 * @return
	 */
	public void downloadPartyOrgInfoAll() throws Exception {
		if (null == orgId) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		searchPartyOrgInfoVo = new SearchPartyOrgInfoVo();
		Organization organization = this.organizationDubboService
				.getSimpleOrgById(orgId);
		searchPartyOrgInfoVo.setOrganization(organization);
		searchPartyOrgInfoVo.setSortField(sidx);
		searchPartyOrgInfoVo.setOrder(sord);
		if (!pageOnly) {
			pageOnly = true;
			Integer count = partyOrgInfoService.getCount(searchPartyOrgInfoVo);
			String[][] excelDefines = getOptimalObjectPropertyArray();
			exportDataAll(count, excelDefines, excelDefines[0][2]);
		}
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.LOWERPARTORGINFO,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出党组织信息",
						ObjectToJSON.convertJSON(new HouseholdStaff()));
		return;
	}

	public List<PartyOrgInfo> getNeedExportDatas(int page) {
		List<PartyOrgInfo> partyOrgInfoList = new ArrayList<PartyOrgInfo>();
		if (pageOnly) {
			partyOrgInfoList = partyOrgInfoService.searchPartyOrgInfos(page,
					rows, searchPartyOrgInfoVo).getResult();
		}
		return partyOrgInfoList;
	}

	/**
	 * 序号|属性|中文名|属性类型|是否采用默认样式|合并行|合并列|
	 * 
	 * @return
	 */
	private String[][] getOptimalObjectPropertyArray() {
		String[][] excelColumArray = {
				{ "0", "", "党员组织信息", "", "", "true", "0", "14" },
				{ "0", "partyBranchName", "党支部名称", "", "", "true" },
				{ "1", "partyBranchSecretary", "党支部书记", "", "", "true" },
				{ "2", "organization", "所属网格", DataType.DATA_TYPE_ORG, "",
						"true" },
				{ "3", "idCardNo", "身份证号码", "", "", "true" },
				{ "4", "mobileNumber", "联系手机", "", "", "true" },
				{ "5", "telephone", "联系电话", "", "", "true" },
				{ "6", "partyNumbers", "党员人数", "", "", "true" },
				{ "7", "partyBranchAddr", "党组织地址 ", "", "", "true" },
				{ "8", "establishedDate", "党支部成立时间", DataType.DATA_TYPE_DATE,
						"", "true" } };
		return excelColumArray;
	}

	private PageInfo<PartyOrgInfo> emptyPage(int pageSize) {
		PageInfo<PartyOrgInfo> pageInfo = new PageInfo<PartyOrgInfo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PartyOrgInfo>());
		return pageInfo;
	}

	public Long getPartyOrgId() {
		return partyOrgId;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public void setPartyOrgId(Long partyOrgId) {
		this.partyOrgId = partyOrgId;
	}

	public SearchPartyOrgInfoVo getSearchPartyOrgInfoVo() {
		return searchPartyOrgInfoVo;
	}

	public void setSearchPartyOrgInfoVo(
			SearchPartyOrgInfoVo searchPartyOrgInfoVo) {
		this.searchPartyOrgInfoVo = searchPartyOrgInfoVo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public PartyOrgInfo getPartyOrgInfo() {
		return partyOrgInfo;
	}

	public void setPartyOrgInfo(PartyOrgInfo partyOrgInfo) {
		this.partyOrgInfo = partyOrgInfo;
	}

}