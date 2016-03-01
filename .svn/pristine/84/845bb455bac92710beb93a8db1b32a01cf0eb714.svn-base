package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.controller.annotation.PermissionFilter;
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
import com.tianque.domain.PartyOrgInfo;
import com.tianque.domain.vo.SearchPartyMemberInfoVo;
import com.tianque.service.PartyMemberInfoService;
import com.tianque.service.PartyOrgInfoService;
import com.tianque.service.TemporaryPopulationService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;

@SuppressWarnings("serial")
@Controller("partyMemberInfoController")
@Scope("prototype")
public class PartyMemberInfoController extends SearchBaseAction {

	@Autowired
	private PartyMemberInfoService partyMemberInfoService;

	@Autowired
	protected OrganizationDubboService organizationDubboService;

	private Long orgId;

	private Long partyOrgId;

	private PartyMemberInfo partyMemberInfo;

	private PartyMemberInfo population;

	private boolean bol;

	private boolean pageOnly;

	private Long organizationId;

	private String deleteIds;

	private List<Long> noRelatePersonIds;

	private List<PartyMemberInfo> partyMemberInfoList;

	private SearchPartyMemberInfoVo searchPartyMemberInfoVo;

	@Autowired
	private TemporaryPopulationService temporaryPopulationService;

	private Boolean hasDuplicatePartyMemberInfoPopulation;

	@Autowired
	private PartyOrgInfoService partyOrgInfoService;

	@Autowired
	private SystemLogService systemLogService;

	public String dispatch() throws Exception {
		actionName = "maintainPartyMemberBaseInfo";
		ajaxUrl = "hasDuplicatePartyMemberInfoPopulation";
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (null != cacheId && null != cacheId.get("id")) {
				population = (PartyMemberInfo) temporaryPopulationService
						.getPopulationById(cacheId.get("id"));
			} else {
				this.procOrganization();
			}
			population.setPartyOrgInfo(this.partyOrgInfoService
					.getPartyOrgInfoByOrgId(population.getOrganization()
							.getId()));
		} else if (DialogMode.EDIT_MODE.endsWith(mode)) {
			if (null != cacheId && null != cacheId.get("id")) {
				population = (PartyMemberInfo) temporaryPopulationService
						.getPopulationById(cacheId.get("id"));
			} else if (null != population && null != population.getId()) {
				population = this.partyMemberInfoService
						.getPartyMemberInfoById(population.getId());
				population.getOrganization().setOrgName(
						ControllerHelper
								.getOrganizationRelativeName(population
										.getOrganization().getId(),
										organizationDubboService));
			}
		} else if (DialogMode.SEARCH_MODE.equals(mode)) {
			this.procOrganization();
			return "search";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		} else if ("list".equals(mode)) {
			return "list";
		}
		return SUCCESS;
	}

	public String findPartyOrgInfoByOrgId() throws Exception {
		if (null != orgId) {
			PartyOrgInfo partyOrgInfo = this.partyOrgInfoService
					.getPartyOrgInfoByOrgId(orgId);
			if (null != partyOrgInfo) {
				partyOrgId = partyOrgInfo.getId();
			}
		}
		return SUCCESS;
	}

	/**
	 * 根据网格内置编码分页查询本级党建党员信息
	 * 
	 * @return SUCCESS
	 */
	public String findPartyMemberInfosByOrgInternalCode() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(
					ControllerHelper.processAllOrgRelativeName(
							this.partyMemberInfoService
									.findPartyMemberInfoForPageByOrgId(orgId,
											page, rows, sidx, sord,
											partyMemberInfo.getIsEmphasis()),
							organizationDubboService,
							new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	/**
	 * 根据查询条件查询本级党建党员信息
	 * 
	 * @return
	 */
	public String searchPartyMemberInfo() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(new PageInfo<PartyMemberInfo>());
			return SUCCESS;
		}
		if (null == searchPartyMemberInfoVo.getOrganization()) {
			Organization org = new Organization();
			searchPartyMemberInfoVo.setOrganization(org);
		}
		searchPartyMemberInfoVo.getOrganization().setId(orgId);
		searchPartyMemberInfoVo.setSortField(sidx);
		searchPartyMemberInfoVo.setOrder(sord);
		PageInfo<PartyMemberInfo> pageInfo = ControllerHelper
				.processAllOrgRelativeName(partyMemberInfoService
						.searchPartyMemberInfos(page, rows,
								searchPartyMemberInfoVo), organizationDubboService,
						new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 跳转到党员组织信息页面
	 * 
	 * @return
	 */
	public String getPartyMemberInfoOrg() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			if (null != cacheId && null != cacheId.get("id")) {
				population = (PartyMemberInfo) temporaryPopulationService
						.getPopulationById(cacheId.get("id"));
			}
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			if (null != population && null != population.getId()) {
				population = this.partyMemberInfoService
						.getPartyMemberInfoById(population.getId());
				population.getOrganization().setOrgName(
						ControllerHelper
								.getOrganizationRelativeName(population
										.getOrganization().getId(),
										organizationDubboService));
			}
			return SUCCESS;
		}
		return ERROR;
	}

	/**
	 * 校验该组织下是否存在该身份证号码
	 * 
	 * @return
	 */
	public String hasDuplicatePartyMemberInfoPopulation() throws Exception {
		if (null == organizationId) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		hasDuplicatePartyMemberInfoPopulation = this.partyMemberInfoService
				.hasDuplicatePartyMemberInfo(organizationId,
						population.getIdCardNo(), population.getId());
		return SUCCESS;
	}

	/**
	 * 新增和修改本级党建党员信息
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "addPartyMemberInfos")
	public String addPartyMemberInfo() throws Exception {
		population = this.addPartyMemberInfoOrg(population);
		if (DialogMode.ADD_MODE.equals(mode)) {
			population = this.partyMemberInfoService
					.addPartyMemberInfo(population);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			population = this.partyMemberInfoService
					.updatePartyMemberInfo(population);
		}
		return SUCCESS;
	}

	/**
	 * 获取人员基本信息
	 * 
	 * @return
	 */
	public String maintainPartyMemberBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			PartyMemberInfo partyMemberInfo = this.partyMemberInfoService
					.getPartyMemberInfoById(population.getId());
			population = this.copyPartyMemberInfosFromPartyMemberInfoOrg(
					partyMemberInfo, population);
			population = this.partyMemberInfoService
					.updatePartyMemberInfo(population);
			return SUCCESS;
		} else {
			String id = null;
			if (null != cacheId) {
				id = cacheId.get("id");
			}
			population.setPartyOrgInfo(this.partyOrgInfoService
					.getPartyOrgInfoByOrgId(population.getOrganization()
							.getId()));
			cacheId = temporaryPopulationService.addPopulationToTemporary(id,
					population);
			population = this.addPartyMemberInfoOrg(population);
			return "addcache";
		}
	}

	/**
	 * 删除本级党建党员信息
	 * 
	 * @return SUCCESS
	 */
	@PermissionFilter(ename = "deletePartyMemberInfos")
	public String deletePartyMemberInfo() throws Exception {
		List<Long> idList;
		String[] deleteId = deleteIds.split(",");
		if (StringUtils.isEmpty(deleteId[0])) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		noRelatePersonIds = this.partyMemberInfoService
				.deletePartyMemberInfoById(idList);
		return SUCCESS;
	}

	/**
	 * 查看本级党建党员信息
	 * 
	 * @return SUCCESS
	 */
	public String viewPartyMemberInfo() throws Exception {
		population = this.partyMemberInfoService
				.getPartyMemberInfoById(population.getId());
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	/**
	 * 注销或取消注销本级党建党员信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "abolishPartyMemberInfos")
	public String emphasisePartyMemberInfo() throws Exception {
		PartyMemberInfo olPartyMemberInfo = this.partyMemberInfoService
				.getPartyMemberInfoById(partyMemberInfo.getId());
		olPartyMemberInfo.setIsEmphasis(partyMemberInfo.getIsEmphasis());
		olPartyMemberInfo = this.partyMemberInfoService
				.updatePartyMemberInfo(olPartyMemberInfo);
		return SUCCESS;
	}

	public String hasRelatePersons() throws Exception {
		if (StringUtils.isEmpty(deleteIds)) {
			bol = false;
		} else {
			String[] deleteId = deleteIds.split(",");
			List<Long> idList;
			if (deleteId[0].equals("")) {
				idList = initTargetId(deleteId, 1);
			} else {
				idList = initTargetId(deleteId, 0);
			}
			bol = this.partyMemberInfoService.hasRelatePersons(idList);
		}
		return SUCCESS;
	}

	/**
	 * 导出本级党建党员信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "downloadPartyMemberInfos")
	public String downloadPartyMemberInfo() throws Exception {
		if (null == orgId || null == searchPartyMemberInfoVo) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		Organization organization = new Organization();
		organization.setId(orgId);
		searchPartyMemberInfoVo.setOrganization(organization);
		searchPartyMemberInfoVo.setSortField(sidx);
		searchPartyMemberInfoVo.setOrder(sord);
		List<PartyMemberInfo> partyMemberInfoList = new ArrayList<PartyMemberInfo>();
		if (pageOnly) {
			partyMemberInfoList = this.partyMemberInfoService
					.searchPartyMemberInfos(page, rows, searchPartyMemberInfoVo)
					.getResult();
		} else {
			partyMemberInfoList = this.partyMemberInfoService
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
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出优抚对象",
						ObjectToJSON.convertJSON(new HouseholdStaff()));
		return SUCCESS;
	}

	/**
	 * 导出本级党建党员信息
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "downloadPartyMemberInfos")
	public void downloadPartyMemberInfoAll() throws Exception {
		if (null == orgId || null == searchPartyMemberInfoVo) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		Organization organization = new Organization();
		organization.setId(orgId);
		searchPartyMemberInfoVo.setOrganization(organization);
		searchPartyMemberInfoVo.setSortField(sidx);
		searchPartyMemberInfoVo.setOrder(sord);
		if (!pageOnly) {
			pageOnly = true;
			Integer count = partyMemberInfoService
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
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出优抚对象",
						ObjectToJSON.convertJSON(new HouseholdStaff()));
		return;
	}

	public List<PartyMemberInfo> getNeedExportDatas(int page) {
		List<PartyMemberInfo> partyMemberInfoList = new ArrayList<PartyMemberInfo>();
		if (pageOnly) {
			partyMemberInfoList = partyMemberInfoService
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
				{ "0", "", "本级党建党员信息", "", "", "true", "0", "14" },
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

	private PageInfo<PartyMemberInfo> emptyPage(int pageSize) {
		PageInfo<PartyMemberInfo> pageInfo = new PageInfo<PartyMemberInfo>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<PartyMemberInfo>());
		return pageInfo;
	}

	private PartyMemberInfo addPartyMemberInfoOrg(
			PartyMemberInfo partyMemberInfoBussiness) {
		PartyMemberInfo partyMemberInfos = null;
		if (null != cacheId && null != cacheId.get("id")
				&& cacheId.get("id").length() > 0) {
			partyMemberInfos = (PartyMemberInfo) temporaryPopulationService
					.getPopulationById(cacheId.get("id"));
		}
		if (null == partyMemberInfos
				&& null != partyMemberInfoBussiness.getId()
				&& partyMemberInfoBussiness.getId() > 0L) {
			partyMemberInfos = this.partyMemberInfoService
					.getPartyMemberInfoById(partyMemberInfoBussiness.getId());
		}
		if (null != partyMemberInfos) {
			return copyPartyMemberInfosFromPartyMemberInfoOrg(
					partyMemberInfoBussiness, partyMemberInfos);
		}
		return partyMemberInfoBussiness;
	}

	private PartyMemberInfo copyPartyMemberInfosFromPartyMemberInfoOrg(
			PartyMemberInfo partyMemberInfoOrg, PartyMemberInfo partyMemberInfo) {
		partyMemberInfo.setPartyOrgInfo(partyMemberInfoOrg.getPartyOrgInfo());
		partyMemberInfo.setJoinPartyBranchDate(partyMemberInfoOrg
				.getJoinPartyBranchDate());
		partyMemberInfo.setPartyMemberType(partyMemberInfoOrg
				.getPartyMemberType());
		partyMemberInfo.setJoinPartyDate(partyMemberInfoOrg.getJoinPartyDate());
		partyMemberInfo.setJoinPartyBranch(partyMemberInfoOrg
				.getJoinPartyBranch());
		partyMemberInfo.setBecomesDate(partyMemberInfoOrg.getBecomesDate());
		partyMemberInfo.setBecomesState(partyMemberInfoOrg.getBecomesState());
		partyMemberInfo.setPartyPosition(partyMemberInfoOrg.getPartyPosition());
		partyMemberInfo.setOfficeDate(partyMemberInfoOrg.getOfficeDate());
		partyMemberInfo.setTrainingState(partyMemberInfoOrg.getTrainingState());
		partyMemberInfo.setRewardsPunishment(partyMemberInfoOrg
				.getRewardsPunishment());
		partyMemberInfo.setExpertise(partyMemberInfoOrg.getExpertise());
		partyMemberInfo.setIsFlowPartyCard(partyMemberInfoOrg
				.getIsFlowPartyCard());
		partyMemberInfo.setFlowPartyBranch(partyMemberInfoOrg
				.getFlowPartyBranch());
		partyMemberInfo.setFlowPartyBranchDate(partyMemberInfoOrg
				.getFlowPartyBranchDate());
		partyMemberInfo.setPartyBranchContacts(partyMemberInfoOrg
				.getPartyBranchContacts());
		partyMemberInfo.setBranchTelephone(partyMemberInfoOrg
				.getBranchTelephone());
		partyMemberInfo.setBranchMobileNumber(partyMemberInfoOrg
				.getBranchMobileNumber());
		partyMemberInfo.setImgUrl(partyMemberInfoOrg.getImgUrl());
		return partyMemberInfo;
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

	private void procOrganization() {
		Organization organization = new Organization();
		organization.setId(orgId);
		organization.setOrgName(ControllerHelper.getOrganizationRelativeName(
				organization.getId(), organizationDubboService));
		population = new PartyMemberInfo();
		population.setOrganization(organization);
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

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public Long getPartyOrgId() {
		return partyOrgId;
	}

	public void setPartyOrgId(Long partyOrgId) {
		this.partyOrgId = partyOrgId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getDeleteIds() {
		return deleteIds;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	public List<Long> getNoRelatePersonIds() {
		return noRelatePersonIds;
	}

	public void setNoRelatePersonIds(List<Long> noRelatePersonIds) {
		this.noRelatePersonIds = noRelatePersonIds;
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

	public Boolean getHasDuplicatePartyMemberInfoPopulation() {
		return hasDuplicatePartyMemberInfoPopulation;
	}

	public void setHasDuplicatePartyMemberInfoPopulation(
			Boolean hasDuplicatePartyMemberInfoPopulation) {
		this.hasDuplicatePartyMemberInfoPopulation = hasDuplicatePartyMemberInfoPopulation;
	}

}