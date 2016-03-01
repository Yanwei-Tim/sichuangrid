package com.tianque.baseInfo.householdStaff.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.base.controller.PopulationControllerAdapter;
import com.tianque.baseInfo.base.service.PeopleDuplicateRemovalHelperService;
import com.tianque.baseInfo.familyInfo.domain.HouseFamily;
import com.tianque.baseInfo.householdStaff.commLog.CommonLog;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.vo.HouseholdStaffVo;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.CensusRegisterFamily;
import com.tianque.domain.Organization;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.ActualPopulationMutexService;
import com.tianque.service.CensusRegisterFamilyService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.service.util.PopulationType;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.sysadmin.service.OrganizationDubboService;

@SuppressWarnings("serial")
@Namespace("/baseinfo/householdStaff")
@Controller("householdStaffController")
@Scope("prototype")
@Transactional
public class HouseholdStaffController extends
		PopulationControllerAdapter<HouseholdStaff> {
	@Autowired
	private HouseholdStaffService householdStaffService;
	@Autowired
	private CensusRegisterFamilyService censusRegisterFamilyService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private ActualPopulationMutexService actualPopulationMutexService;
	@Autowired
	private PeopleDuplicateRemovalHelperService peopleDuplicateRemovalHelperService;
	/**
	 * 在以房管人中，为指定房屋增加户籍人口时，将不能修改该人员的住房信息。
	 */
	private boolean livingHouseChangeAbled;
	private CensusRegisterFamily censusRegisterFamily;
	private HouseholdStaffVo householdStaffVo;
	private HouseholdStaff population;
	private Organization organization;
	private Long orgId;
	private String dailogName;
	private long existedCount;
	private String householdStaffIds;
	private boolean pageOnly;
	private HouseInfo houseInfo;
	private RentalHouse rentalHouse;
	private List<HouseholdStaff> populationList;
	private List<Long> houseMarchTypeList;

	private Boolean hasDuplicateHouseholdStaff;

	/** 查询户籍家庭的户籍人员 */
	private HouseFamily houseFamily;
	/** 户籍家庭的家庭称号 */
	private List<HouseholdStaff> householdStaffs;
	/** 是否包含户主 */
	private Boolean exceptHead;

	private boolean hasBusinessTypes;

	private CommonLog commonLog;
	/** 人口业务去重表名 */
	private String peopleDuplicateRemovalTable;

	private Integer beginId;

	@Action(value = "peopleDuplicateRemoval", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String peopleDuplicateRemoval() throws Exception {
		if ("all".equals(peopleDuplicateRemovalTable)
				|| !StringUtil.isStringAvaliable(peopleDuplicateRemovalTable)) {
			peopleDuplicateRemovalHelperService.removalDuplicatePeople();
		} else {
			peopleDuplicateRemovalHelperService
					.removalBusinessPopulationDuplicateByTableName(peopleDuplicateRemovalTable);
		}
		return SUCCESS;
	}

	@Action(value = "dispathHouseholdInfoForBusinessPopulation", results = { @Result(name = "success", location = "/baseinfo/householdPopulation/householdStaffDlg.jsp") })
	public String dispathHouseholdInfoForBusinessPopulation() throws Exception {
		population = householdStaffService
				.getHouseholdStaffByIdAndBusinessType(id, type, orgId);
		if (population != null) {
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(),
							organizationDubboService));
		}
		return SUCCESS;
	}

	@Action(value = "reflushHouseHoldData", results = { @Result(name = "success", type = "json", params = {
			"root", "population" }) })
	public String reflushHouseHoldData() throws Exception {
		population.setHouseMarchType(householdStaffService
				.getHouseFamilyTypeList(population));
		return SUCCESS;
	}

	@Action(value = "moveToShardTable", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String moveToShardTable() throws Exception {
		householdStaffService.moveToShardTable();
		return SUCCESS;
	}

	@Action(value = "toFloatingPopulation", results = { @Result(name = "success", type = "json", params = {
			"root", "population" }) })
	public String toFloatingPopulation() throws Exception {
		householdStaffService.toFloatingPopulation(population.getId());
		return SUCCESS;
	}

	@Action(value = "toFloatingPopulationByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String toFloatingPopulationByIds() throws Exception {
		if (!StringUtil.isStringAvaliable(populationIds)) {
			errorMessage = "参数错误！";
			return ERROR;
		}
		householdStaffService.toFloatingPopulationByIds(StringUtil
				.parseLong(populationIds));
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "toFloatingPopulationByEncryptId", results = { @Result(name = "success", type = "json", params = {
			"root", "population" }) })
	public String toFloatingPopulationByEncryptId() throws Exception {
		householdStaffService.toFloatingPopulation(population.getId());
		return SUCCESS;
	}

	@Action(value = "maintainHouseholdInfoForBusinessPopulation", results = { @Result(name = "success", type = "json", params = {
			"root", "population" }) })
	public String maintainHouseholdInfoForBusinessPopulation() throws Exception {
		population = householdStaffService.updateHousePopulationBusinessInfo(
				population, houseMarchTypeList);
		if (population != null && population.getId() != null) {
			householdStaffService.regrantFamilyHouse(population,
					houseMarchTypeList);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "downHouseholdStaff")
	@Action("downloadHouseholdStaff")
	public String downloadHouseholdStaff() throws Exception {
		if (null == orgId || null == householdStaffVo) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(orgId);
		householdStaffVo.setOrgInternalCode(organization.getOrgInternalCode());
		List<HouseholdStaff> householdStaffList = getNeedExportDatas(page);
		inputStream = ExcelExportHelper.exportDateToExcel(
				SpecialGroupsContext.getHouseholdStaffPropertyArray(),
				householdStaffList);
		downloadFileName = new String("户籍人口清单".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(com.tianque.core.logger.Logger.INFO,
						ModuleConstants.HOUSEHOLDSTAFF,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出户籍人口",
						ObjectToJSON.convertJSON(new HouseholdStaff()));

		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downHouseholdStaff")
	@Action(value = "downloadHouseholdStaffAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() throws Exception {
		if (orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (householdStaffVo == null) {
			householdStaffVo = new HouseholdStaffVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		householdStaffVo.setOrgInternalCode(organization.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = householdStaffService.getCount(householdStaffVo);
			String[][] excelDefines = SpecialGroupsContext
					.getHouseholdStaffPropertyArray();
			exportDataAll(count, excelDefines, "户籍人口清单");
		}
		systemLogService
				.log(com.tianque.core.logger.Logger.INFO,
						ModuleConstants.HOUSEHOLDSTAFF,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出户籍人口",
						ObjectToJSON.convertJSON(new HouseholdStaff()));
	}

	@Action(value = "whetherHousehold", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "isHouseHolder", type = "json", params = { "root",
					"true" }) })
	public String whetherHousehold() throws Exception {
		if (population != null && population.getRelationShipWithHead() != null
				&& population.getRelationShipWithHead().getId() != null) {
			if (householdStaffService.whetherHousehold(population
					.getRelationShipWithHead().getId())) {
				return "isHouseHolder";
			} else {
				return ERROR;
			}
		}
		return SUCCESS;
	}

	@Action(value = "checkCardNoIsRepeat", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String checkCardNoIsRepeat() throws Exception {
		if (population != null && organizationId != null) {
			if (actualPopulationMutexService
					.isExistByOrgIdAndIdCardNoAndExcludeActualPopulationType(
							organizationId, population.getIdCardNo(),
							PopulationType.HOUSEHOLD_STAFF)) {
				return SUCCESS;
			}
		}
		return ERROR;
	}

	@EncryptAnnotation
	@Action(value = "checkCardNoIsRepeatByEncryptId", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String checkCardNoIsRepeatByEncryptId() throws Exception {
		if (population != null && organizationId != null) {
			if (actualPopulationMutexService
					.isExistByOrgIdAndIdCardNoAndExcludeActualPopulationType(
							organizationId, population.getIdCardNo(),
							PopulationType.HOUSEHOLD_STAFF)) {
				return SUCCESS;
			}
		}
		return ERROR;
	}

	@EncryptAnnotation
	@Actions({ @Action(value = "dispathByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/householdPopulation/maintainHouseholdStaffDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/householdPopulation/householdstaffViewDlg.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp") }) })
	public String dispatchOperateByEncrypt() throws Exception {
		ajaxUrl = "hasDuplicateHouseholdStaff";
		actionName = "maintainHouseholdStaffBaseInfo";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	@Actions({
			@Action(value = "dispath", results = {
					@Result(name = "success", location = "/baseinfo/householdPopulation/maintainHouseholdStaffDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/householdPopulation/householdstaffViewDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp") }),
			@Action(value = "dispathForGis", results = { @Result(name = "success", location = "/baseinfo/householdPopulation/householdstaffViewDlg_gis.jsp") }),
			@Action(value = "dispathForWorkBench", results = { @Result(name = "success", location = "/workBench/issueManage/maintainHouseholdStaffDlg.jsp") }),
			@Action(value = "dispathHouseholdStaffBaseInfo", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/populationBaseInfo.jsp") }) })
	public String dispatchOperate() throws Exception {
		ajaxUrl = "hasDuplicateHouseholdStaff";
		actionName = "maintainHouseholdStaffBaseInfo";
		population = dispathBaseInfo(population);
		return getRetunString();
	}

	protected HouseholdStaff dispathBaseInfo(HouseholdStaff population) {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		} else if (null != population && null != population.getId()) {
			population = getPopulationFetchOrgById(population.getId());
		} else {
			String idCardNo = population == null ? "" : population
					.getIdCardNo();
			population = new HouseholdStaff();
			population.setOrganization(new Organization());
			population.getOrganization().setId(organizationId);
			population.setIdCardNo(idCardNo);
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							organizationId, organizationDubboService));
		}
		return population;

	}

	protected HouseholdStaff getPopulationFetchOrgById(Long id) {
		HouseholdStaff population = householdStaffService
				.getHouseholdStaffById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	@Action(value = "maintainHouseholdStaffBaseInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "population" }) })
	public String maintainBaseInfo() throws Exception {
		if (null != population && null != population.getId()) {
			population = householdStaffService
					.updateHouseholdStaffBaseInfo(population);
		} else {
			population = householdStaffService
					.addHouseholdStaffBaseInfo(population);
		}
		return SUCCESS;
	}

	@Action(value = "hasDuplicateHouseholdStaff", results = { @Result(name = "success", type = "json", params = {
			"root", "hasDuplicateHouseholdStaff" }) })
	public String hasDuplicatePopulation() throws Exception {
		if (population == null || organizationId == null) {
			errorMessage = "参数错误";
			return ERROR;
		} else {
			hasDuplicateHouseholdStaff = actualPopulationMutexService
					.isActualPopuationByOrgIdAndIdCardNoAndExcludeActualPopulationType(
							population.getId(), organizationId,
							population.getIdCardNo(),
							PopulationType.HOUSEHOLD_STAFF);
			if (hasDuplicateHouseholdStaff != true) {
				// 如果是新增，去数据库中查是否存在该身份证的已注销人员，如果有，不允许新增
				if (DialogMode.ADD_MODE.equals(mode)) {
					population = householdStaffService
							.findHouseholdStaffByCardNoAndOrgId(
									population.getIdCardNo(), organizationId);
					if (population != null
							&& IsEmphasis.IsNotEmphasis.equals(population
									.getLogOut())) {
						hasDuplicateHouseholdStaff = true;
					}
				}
			}
		}
		return SUCCESS;
	}

	@Action(value = "getCensusRegisterFamilyByOrgIdAndAccountNumber", results = { @Result(name = "success", type = "json", params = {
			"root", "censusRegisterFamily" }) })
	public String getCensusRegisterFamilyByOrgIdAndAccountNumber()
			throws Exception {
		if (householdStaffVo == null
				|| householdStaffVo.getAccountNumber() == null
				|| "".equals(householdStaffVo.getAccountNumber().trim())) {
			return SUCCESS;
		}
		censusRegisterFamily = censusRegisterFamilyService
				.getCensusRegisterFamilyByOrgIdAndAccountNumber(
						householdStaffVo.getAccountNumber(), orgId);
		return SUCCESS;
	}

	@Action(value = "prepareSearchHouseholdStaff", results = { @Result(name = "success", location = "/baseinfo/householdPopulation/searchHouseholdStaffDlg.jsp") })
	public String prepareSearchHouseholdStaff() throws Exception {
		return SUCCESS;
	}

	@Action(value = "dispathInHouseholdStaffInfomation", results = { @Result(name = "success", location = "/baseinfo/householdPopulation/householdStaffDlg.jsp") })
	public String dispatchBusiness() throws Exception {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Actions({
			@Action(value = "viewCommonPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewCommonPopulation.jsp") }),
			@Action(value = "viewHouseholdStaff", results = {
					@Result(name = "success", location = "/baseinfo/householdPopulation/householdstaffView.jsp"),
					@Result(name = "print", location = "/baseinfo/householdPopulation/householdstaffPrint.jsp") }) })
	public String viewInfo() throws Exception {
		if (population == null || population.getId() == null) {
			existedCount = 0;
		} else {
			population = householdStaffService.getHouseholdStaffById(population
					.getId());
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(),
							organizationDubboService));
			population.setAttentionPopulationType(population.getActualPopulationType());
		}
		if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	@Action(value = "getHouseholdStaffByIdForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "population" }) })
	public String viewInfoForMoblie() throws Exception {
		if (population == null || population.getId() == null) {
			existedCount = 0;
		} else {
			population = householdStaffService.getHouseholdStaffById(population
					.getId());
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(),
							organizationDubboService));
		}
		return SUCCESS;
	}

	@Action(value = "fastSearch", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage" }) })
	public String fastSearch() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					householdStaffService.fastFindHouseholdStaffForPageByOrgId(
							householdStaffVo, orgId, page, rows, sidx, sord),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	@Action(value = "findHouseholdStaffByOrgId", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage" }) })
	public String findGridPageByOrgIdAndIsEmphasis() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					householdStaffService.findHouseholdStaffForPageByOrgId(
							householdStaffVo, orgId, page, rows, sidx, sord),
					organizationDubboService, new String[] { "organization" },
					orgId));
		}
		return SUCCESS;
	}

	@Action(value = "findHouseholdStaffByOrgIdDefaultList", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage" }) })
	public String findHouseholdStaffByOrgIdDefaultList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					householdStaffService.findHouseholdStaffByOrgIdDefaultList(
							householdStaffVo, orgId, page, rows, sidx, sord),
					organizationDubboService, new String[] { "organization" },
					orgId));
		}
		return SUCCESS;
	}

	@Action(value = "findHouseholdStaffByOrgIdDefaultListTest", results = { @Result(name = "success", type = "json", params = {
			"root", "commonLog" }) })
	public String findHouseholdStaffByOrgIdDefaultListTest() throws Exception {
		long start = System.currentTimeMillis();
		commonLog = new CommonLog();
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					householdStaffService
							.findHouseholdStaffByOrgIdDefaultListTest(
									householdStaffVo, orgId, page, rows, sidx,
									sord, commonLog), organizationDubboService,
					new String[] { "organization" }, orgId));
		}
		commonLog.setControlTime(System.currentTimeMillis() - start);
		return SUCCESS;
	}

	@Action(value = "findHouseholdStaffByOrgIdAndId", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage" }) })
	public String findHouseholdStaffByOrgIdAndId() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					householdStaffService
							.findHouseholdStaffForPageByOrgIdAndId(
									householdStaffVo, orgId,
									houseFamily.getId(), exceptHead, page,
									rows, sidx, sord),
					organizationDubboService, new String[] { "organization" },
					orgId));
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "deleteHouseholdStaff", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteByIds() throws Exception {
		if (householdStaffVo == null || householdStaffVo.getIdStr() == null) {
			this.errorMessage = "HouseholdStaff不能为空!";
			return ERROR;
		}
		String[] str = householdStaffVo.getIdStr().split(",");
		Long[] id = new Long[str.length];
		for (int i = 0; i < str.length; i++) {
			id[i] = Long.parseLong(str[i]);
		}
		householdStaffService.deleteHouseholdStaffByIds(id);
		return SUCCESS;
	}

	@Action(value = "maintainHouseholdStaffBusinessInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population" }),
			@Result(name = "input", type = "json", params = { "root", "cacheId" }) })
	public String maintainBusinessInfo() throws Exception {
		population = householdStaffService.updateHousePopulationBusinessInfo(
				population, houseMarchTypeList);
		return SUCCESS;
	}

	@Action(value = "updateEmphasiseById", results = { @Result(name = "success", type = "json", params = {
			"root", "populationIdList" }) })
	public String updateEmphasiseById() throws Exception {
		String[] updateId = populationIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		populationIdList = householdStaffService
				.updateLogOutOfHouseholdStaffByIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.HOUSEHOLDSTAFF_KEY,
						idList.toArray(new Long[0]));
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "updateEmphasiseByEncryptId", results = { @Result(name = "success", type = "json", params = {
			"root", "populationIdList" }) })
	public String updateEmphasiseByEncryptId() throws Exception {
		String[] updateId = populationIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		populationIdList = householdStaffService
				.updateLogOutOfHouseholdStaffByIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.HOUSEHOLDSTAFF_KEY,
						idList.toArray(new Long[0]));
		return SUCCESS;
	}

	@Action(value = "updateDeathOfHouseholdStaff", results = { @Result(name = "success", type = "json", params = {
			"root", "populationIdList" }) })
	public String updateDeathOfHouseholdStaff() throws Exception {
		String[] updateId = householdStaffIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		populationList = householdStaffService
				.updateDeathOfHouseholdStaffByIdList(idList,
						population.isDeath());
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "updateDeathOfHouseholdStaffByEncryptId", results = { @Result(name = "success", type = "json", params = {
			"root", "populationIdList" }) })
	public String updateDeathOfHouseholdStaffByEncryptId() throws Exception {
		String[] updateId = householdStaffIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		populationList = householdStaffService
				.updateDeathOfHouseholdStaffByIdList(idList,
						population.isDeath());
		return SUCCESS;
	}

	@Action(value = "findHouseholdStaffByIdCardNoAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findHouseholdStaffByIdCardNoAndOrgId() throws Exception {
		if (population == null || population.getOrganization() == null) {
			errorMessage = "参数错误";
		}
		population = householdStaffService.findHouseholdStaffByCardNoAndOrgId(
				population.getIdCardNo(), population.getOrganization().getId());
		if (population != null)
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(),
							organizationDubboService));
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "findHouseholdStaffByIdCardNoAndOrgIdEncrypt", results = {
			@Result(name = "success", type = "json", params = { "root",
					"population" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findHouseholdStaffByIdCardNoAndOrgIdEncrypt()
			throws Exception {
		if (population == null || population.getOrganization() == null) {
			errorMessage = "参数错误";
		}
		population = householdStaffService.findHouseholdStaffByCardNoAndOrgId(
				population.getIdCardNo(), population.getOrganization().getId());
		if (population != null)
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(population
							.getOrganization().getId(),
							organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "addHouseholdStaffForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "population" }) })
	public String addHouseholdStaffForMobile() throws Exception {
		population = householdStaffService
				.addHouseholdStaffForMobile(population);

		return SUCCESS;

	}

	@Action(value = "updateHouseholdStaffForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "population" }) })
	public String updateHouseholdStaffForMobile() throws Exception {
		population = householdStaffService.updateHouseholdStaff(population);
		return SUCCESS;
	}

	private PageInfo<HouseholdStaff> emptyPage(int pageSize) {
		PageInfo<HouseholdStaff> pageInfo = new PageInfo<HouseholdStaff>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<HouseholdStaff>());
		return pageInfo;
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

	@Action(value = "getActualPopulationHasTypes", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hasBusinessTypes" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getActualPopulationHasTypes() throws Exception {
		hasBusinessTypes = householdStaffService
				.getActualPopulationHasTypes(population.getId());
		return SUCCESS;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public HouseholdStaffVo getHouseholdStaffVo() {
		return householdStaffVo;
	}

	public void setHouseholdStaffVo(HouseholdStaffVo householdStaffVo) {
		this.householdStaffVo = householdStaffVo;
	}

	public CensusRegisterFamily getCensusRegisterFamily() {
		return censusRegisterFamily;
	}

	public void setCensusRegisterFamily(
			CensusRegisterFamily censusRegisterFamily) {
		this.censusRegisterFamily = censusRegisterFamily;
	}

	public HouseholdStaff getPopulation() {
		return population;
	}

	public void setPopulation(HouseholdStaff population) {
		this.population = population;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public long getExistedCount() {
		return existedCount;
	}

	public void setExistedCount(long existedCount) {
		this.existedCount = existedCount;
	}

	public boolean isLivingHouseChangeAbled() {
		return livingHouseChangeAbled;
	}

	public void setLivingHouseChangeAbled(boolean livingHouseChangeAbled) {
		this.livingHouseChangeAbled = livingHouseChangeAbled;
	}

	public String getHouseholdStaffIds() {
		return householdStaffIds;
	}

	public void setHouseholdStaffIds(String householdStaffIds) {
		this.householdStaffIds = householdStaffIds;
	}

	public List<HouseholdStaff> getPopulationList() {
		return populationList;
	}

	public void setPopulationList(List<HouseholdStaff> populationList) {
		this.populationList = populationList;
	}

	public HouseInfo getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}

	public RentalHouse getRentalHouse() {
		return rentalHouse;
	}

	public void setRentalHouse(RentalHouse rentalHouse) {
		this.rentalHouse = rentalHouse;
	}

	public Boolean getHasDuplicateHouseholdStaff() {
		return hasDuplicateHouseholdStaff;
	}

	public void setHasDuplicateHouseholdStaff(Boolean hasDuplicateHouseholdStaff) {
		this.hasDuplicateHouseholdStaff = hasDuplicateHouseholdStaff;
	}

	public List<Long> getHouseMarchTypeList() {
		return houseMarchTypeList;
	}

	public void setHouseMarchTypeList(List<Long> houseMarchTypeList) {
		this.houseMarchTypeList = houseMarchTypeList;
	}

	public HouseFamily getHouseFamily() {
		return houseFamily;
	}

	public void setHouseFamily(HouseFamily houseFamily) {
		this.houseFamily = houseFamily;
	}

	public List<HouseholdStaff> getHouseholdStaffs() {
		return householdStaffs;
	}

	public void setHouseholdStaffs(List<HouseholdStaff> householdStaffs) {
		this.householdStaffs = householdStaffs;
	}

	public Boolean getExceptHead() {
		return exceptHead;
	}

	public void setExceptHead(Boolean exceptHead) {
		this.exceptHead = exceptHead;
	}

	public boolean isHasBusinessTypes() {
		return hasBusinessTypes;
	}

	public void setHasBusinessTypes(boolean hasBusinessTypes) {
		this.hasBusinessTypes = hasBusinessTypes;
	}

	@Override
	public List getNeedExportDatas(int page) {
		List<HouseholdStaff> householdStaffList = new ArrayList<HouseholdStaff>();
		if (pageOnly) {
			householdStaffList = householdStaffService
					.findHouseholdStaffForPageByOrgId(householdStaffVo, orgId,
							page, rows, sidx, sord).getResult();
		} else {
			householdStaffList = householdStaffService.findHouseholdStaffList(
					householdStaffVo, orgId, sidx, sord);

		}
		return householdStaffList;
	}

	public void setCommonLog(CommonLog commonLog) {
		this.commonLog = commonLog;
	}

	public CommonLog getCommonLog() {
		return commonLog;
	}

	public String getPeopleDuplicateRemovalTable() {
		return peopleDuplicateRemovalTable;
	}

	public void setPeopleDuplicateRemovalTable(
			String peopleDuplicateRemovalTable) {
		this.peopleDuplicateRemovalTable = peopleDuplicateRemovalTable;
	}

	public Integer getBeginId() {
		return beginId;
	}

	public void setBeginId(Integer beginId) {
		this.beginId = beginId;
	}

}
