package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.tianque.domain.vo.SearchPartyMemberInfoVo;
import com.tianque.service.LowerPartyMemberInfoService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

@SuppressWarnings("serial")
@Controller("lowerMemberInfoController")
@Scope("prototype")
public class LowerMemberInfoController extends SearchBaseAction {

	@Autowired
	private LowerPartyMemberInfoService lowerPartyMemberInfoService;

	@Autowired
	protected OrganizationDubboService organizationDubboService;

	private PartyMemberInfo partyMemberInfo;

	private PartyMemberInfo population;

	private Long organizationId;
	private Long partyOrgInfoId;// 党组织机构的id

	private boolean pageOnly;

	private List<PartyMemberInfo> partyMemberInfoList;

	private SearchPartyMemberInfoVo searchPartyMemberInfoVo;

	@Autowired
	private SystemLogService systemLogService;

	/**
	 * 页面列表的初始显示
	 * 
	 * @return
	 */
	public String getlowerMemberInfo() throws Exception {

		if (null == organizationId) {
			gridPage = new GridPage(new PageInfo<PartyMemberInfo>());
			return SUCCESS;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);

		PageInfo<PartyMemberInfo> pageInfo = lowerPartyMemberInfoService
				.findPartyMemberInfoForPageByOrgInternalCode(
						organization.getOrgInternalCode(), page, rows, sidx,
						sord, searchPartyMemberInfoVo.getIsEmphasis());
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 查看党员详细信息的方法
	 * 
	 * @return
	 */
	public String viewMemberInfo() throws Exception {
		population = this.lowerPartyMemberInfoService
				.getPartyMemberInfoById(population.getId());
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	public String dispatch() throws Exception {
		if (DialogMode.VIEW_MODE.equals(mode))
			return "view";

		if (DialogMode.SEARCH_MODE.equals(mode)) {
			this.procOrganization();
			return "search";
		}

		return SUCCESS;
	}

	private void procOrganization() {
		Organization organization = new Organization();
		organization.setId(organizationId);
		organization.setOrgName(ControllerHelper.getOrganizationRelativeName(
				organization.getId(), organizationDubboService));
		population = new PartyMemberInfo();
		population.setOrganization(organization);
	}

	/**
	 * 根据查询条件查询下辖党建党员信息
	 * 
	 * @return
	 */
	public String searchPartyMemberInfo() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(new PageInfo<PartyMemberInfo>());
			return SUCCESS;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(organizationId);
		if (null == searchPartyMemberInfoVo.getOrganization()) {
			Organization org = new Organization();
			searchPartyMemberInfoVo.setOrganization(org);
		}
		searchPartyMemberInfoVo.getOrganization().setId(organizationId);
		searchPartyMemberInfoVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		searchPartyMemberInfoVo.setSortField(sidx);
		searchPartyMemberInfoVo.setOrder(sord);
		PageInfo<PartyMemberInfo> pageInfo = ControllerHelper
				.processAllOrgRelativeName(lowerPartyMemberInfoService
						.searchPartyMemberInfos(page, rows,
								searchPartyMemberInfoVo), organizationDubboService,
						new String[] { "organization" }, organizationId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 导出党员信息
	 * 
	 * @return
	 */
	public String downloadPartyMemberInfo() throws Exception {
		if (null == organizationId || null == searchPartyMemberInfoVo) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		Organization organization = this.organizationDubboService
				.getSimpleOrgById(organizationId);
		searchPartyMemberInfoVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		searchPartyMemberInfoVo.setSortField(sidx);
		searchPartyMemberInfoVo.setOrder(sord);
		List<PartyMemberInfo> partyMemberInfoList = new ArrayList<PartyMemberInfo>();
		if (pageOnly) {
			partyMemberInfoList = lowerPartyMemberInfoService
					.searchPartyMemberInfos(page, rows, searchPartyMemberInfoVo)
					.getResult();
		} else {
			partyMemberInfoList = this.lowerPartyMemberInfoService
					.searchAllPartyMemberInfos(searchPartyMemberInfoVo);
		}
		String[][] excelColumArray = this.getPartyMemberInfoPropertyArray();
		inputStream = ExcelExportHelper.exportDateToExcel(excelColumArray,
				partyMemberInfoList);
		downloadFileName = new String(excelColumArray[0][2].getBytes("gbk"),
				"ISO8859-1") + ".xls";
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.PARTYMEMBERINFO,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出优抚对象",
						ObjectToJSON.convertJSON(new HouseholdStaff()));
		return SUCCESS;
	}

	/**
	 * 导出党员信息
	 * 
	 * @return
	 */
	public void downloadPartyMemberInfoAll() throws Exception {
		if (null == organizationId || null == searchPartyMemberInfoVo) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		Organization organization = this.organizationDubboService
				.getSimpleOrgById(organizationId);
		searchPartyMemberInfoVo.setOrgInternalCode(organization
				.getOrgInternalCode());
		searchPartyMemberInfoVo.setSortField(sidx);
		searchPartyMemberInfoVo.setOrder(sord);
		if (!pageOnly) {
			pageOnly = true;
			Integer count = lowerPartyMemberInfoService
					.getCount(searchPartyMemberInfoVo);
			String[][] excelDefines = getPartyMemberInfoPropertyArray();
			exportDataAll(count, excelDefines, excelDefines[0][2]);
		}
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModuleConstants.PARTYMEMBERINFO,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												organizationId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "党建党员信息",
						ObjectToJSON.convertJSON(new HouseholdStaff()));
		return;
	}

	public List<PartyMemberInfo> getNeedExportDatas(int page) {
		List<PartyMemberInfo> partyMemberInfoList = new ArrayList<PartyMemberInfo>();
		if (pageOnly) {
			partyMemberInfoList = lowerPartyMemberInfoService
					.searchPartyMemberInfos(page, rows, searchPartyMemberInfoVo)
					.getResult();
		}
		return partyMemberInfoList;
	}

	/**
	 * 序号|属性|中文名|属性类型|是否采用默认样式|合并行|合并列|
	 * 
	 * @return
	 */
	private String[][] getPartyMemberInfoPropertyArray() {
		String[][] excelColumArray = {
				{ "0", "", "下辖党建党员信息", "", "", "true", "0", "14" },
				{ "0", "name", "姓名", "", "", "true" },
				{ "1", "organization", "所属网格", DataType.DATA_TYPE_ORG, "",
						"true" },
				{ "2", "gender", "性别", DataType.DATA_TYPE_DICT, "", "true" },
				{ "3", "birthday", "出生日期", DataType.DATA_TYPE_DATE, "", "true" },
				{ "4", "idCardNo", "身份证号码", "", "", "true" },
				{ "5", "partyOrgInfo.partyBranchName", "所属党支部", "", "", "true" },
				{ "6", "joinPartyBranchDate", "进入党支部时间",
						DataType.DATA_TYPE_DATE, "", "true" },
				{ "7", "partyMemberType", "党员类型", DataType.DATA_TYPE_DICT, "",
						"true" },
				{ "8", "joinPartyDate", "入党时间", DataType.DATA_TYPE_DATE, "",
						"true" },
				{ "9", "joinPartyBranch", "入党时所在党支部", "", "", "true" },
				{ "10", "becomesDate", "转正时间", DataType.DATA_TYPE_DATE, "",
						"true" },
				{ "11", "becomesState", "转正情况", DataType.DATA_TYPE_DICT, "",
						"true" },
				{ "12", "partyPosition", "党内主要职务", "", "", "true" },
				{ "13", "officeDate", "任职日期", DataType.DATA_TYPE_DATE, "",
						"true" },
				{ "14", "trainingState", "培训情况", DataType.DATA_TYPE_DICT, "",
						"true" },
				{ "15", "rewardsPunishment", "奖惩情况", "", "", "true" },
				{ "16", "expertise", "专长", "", "", "true" },
				{ "17", "flowPartyBranch", "流入地（单位）党支部", "", "", "true" },
				{ "18", "flowPartyBranchDate", "流入时间", DataType.DATA_TYPE_DATE,
						"", "true" },
				{ "19", "partyBranchContacts", "党支部联系人", "", "", "true" },
				{ "20", "branchTelephone", "固定电话（党支部联系人）", "", "", "true" },
				{ "21", "branchMobileNumber", "联系手机（党支部联系人)", "", "", "true" },
				{ "22", "isFlowPartyCard", "是否持流动党员证",
						DataType.DATA_TYPE_BOOLEAN, "", "true" },

				{ "23", "", "户籍地", "", "", "true", "1", "25" },
				{ "23", "province", "省", "", "", "true" },
				{ "24", "city", "市", "", "", "true" },
				{ "25", "district", "县", "", "", "true" },

				{ "26", "nativePlaceAddress", "户籍详细地址", "", "", "true" },
				{ "27", "currentAddress", "常住地址", "", "", "true" },
				{ "28", "community", "小区/地址", "", "", "true" },
				{ "29", "block", "幢", "", "", "true" },
				{ "30", "unit", "单元", "", "", "true" },
				{ "31", "room", "室", "", "", "true" },
				{ "32", "otherAddress", "临时居所", "", "", "true" },
				{ "33", "usedName", "曾用名/别名", "", "", "true" },
				{ "34", "workUnit", "工作单位或就读学校", "", "", "true" },
				{ "35", "telephone", "固定电话", "", "", "true" },
				{ "36", "mobileNumber", "联系手机", "", "", "true" },
				{ "37", "email", "电子邮箱", "", "", "true" },
				{ "38", "nation", "民族", DataType.DATA_TYPE_DICT, "", "true" },
				{ "39", "politicalBackground", "政治面貌", DataType.DATA_TYPE_DICT,
						"", "true" },
				{ "40", "schooling", "文化程度", DataType.DATA_TYPE_DICT, "",
						"true" },
				{ "41", "career", "职业", DataType.DATA_TYPE_DICT, "", "true" },
				{ "42", "maritalState", "婚姻状况", DataType.DATA_TYPE_DICT, "",
						"true" },
				{ "43", "bloodType", "血型", DataType.DATA_TYPE_DICT, "", "true" },
				{ "44", "faith", "宗教信仰", DataType.DATA_TYPE_DICT, "", "true" },
				{ "45", "stature", "身高(CM)", "", "", "true" },
				{ "46", "remark", "备注", "", "", "true" }

		};
		return excelColumArray;
	}

	public PartyMemberInfo getPartyMemberInfo() {
		return partyMemberInfo;
	}

	public void setPartyMemberInfo(PartyMemberInfo partyMemberInfo) {
		this.partyMemberInfo = partyMemberInfo;
	}

	public PartyMemberInfo getPopulation() {
		return population;
	}

	public void setPopulation(PartyMemberInfo population) {
		this.population = population;
	}

	public List<PartyMemberInfo> getPartyMemberInfoList() {
		return partyMemberInfoList;
	}

	public void setPartyMemberInfoList(List<PartyMemberInfo> partyMemberInfoList) {
		this.partyMemberInfoList = partyMemberInfoList;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public SearchPartyMemberInfoVo getSearchPartyMemberInfoVo() {
		return searchPartyMemberInfoVo;
	}

	public void setSearchPartyMemberInfoVo(
			SearchPartyMemberInfoVo searchPartyMemberInfoVo) {
		this.searchPartyMemberInfoVo = searchPartyMemberInfoVo;
	}

	public Long getPartyOrgInfoId() {
		return partyOrgInfoId;
	}

	public void setPartyOrgInfoId(Long partyOrgInfoId) {
		this.partyOrgInfoId = partyOrgInfoId;
	}

	public LowerPartyMemberInfoService getLowerPartyMemberInfoService() {
		return lowerPartyMemberInfoService;
	}

	public void setLowerPartyMemberInfoService(
			LowerPartyMemberInfoService lowerPartyMemberInfoService) {
		this.lowerPartyMemberInfoService = lowerPartyMemberInfoService;
	}

	public GridPage getGridPage() {
		return gridPage;
	}

	public void setGridPage(GridPage gridPage) {
		this.gridPage = gridPage;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

}