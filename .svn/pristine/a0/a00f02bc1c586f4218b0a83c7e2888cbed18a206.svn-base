package com.tianque.baseInfo.actualHouse.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.domain.PackageHouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.GisInfo;
import com.tianque.domain.HouseHasActualPopulation;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.SearchHouseInfoVo;
import com.tianque.excel.definition.HouseContext;
import com.tianque.service.HouseHasActualPopulationService;
import com.tianque.service.HouseInfoService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.util.PropertyUtil;

@Controller("actualHouseController")
@Scope("prototype")
@Transactional
@Namespace("/baseinfo/actualHouseManage")
public class ActualHouseController extends SearchBaseAction {

	@Autowired
	private ActualHouseService houseInfoService;
	@Autowired
	private HouseInfoService houseInfoServices;
	@Autowired
	private RentalHouseService rentalHouseService;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService properTypeDictService;
	@Autowired
	private SystemLogService systemLogService;

	@Autowired
	private HouseHasActualPopulationService houseHasActualPopulationService;

	private HouseInfo houseInfo;
	private HouseInfo megerHouseInfo;
	private RentalHouse rentalHouse;

	private RentalHouse rental;

	private Map map;
	private Long orgId;
	private String address;

	private String houseIds;
	private Organization organization;

	private Long houseId;
	private Long megerId;// 被合并房屋信息的ID
	private List<Long> houseInfoIds;
	private List<HouseInfo> houseList;
	private PageInfo<HouseInfo> houseListForMobile;
	private List<PackageHouseInfo> packageHouseInfo;
	private Map<String, List<PackageHouseInfo>> houseInfoMap;
	private Integer useRentalHouse;

	private SearchHouseInfoVo searchHouseInfoVo;

	private Boolean hasDuplicateHouseInfo;
	private Boolean hasActualPopulation = true;

	private boolean pageOnly;
	private String isUseFrom;

	private GisInfo minOption;

	private GisInfo maxOption;
	private HouseHasActualPopulation houseHasActualPopulation;
	/** 原房屋编号 */
	private String oldHouseCode;
	// 新增修改默认
	private Long organizationId;
	private String dailogName;
	/** id加密对S action 标签的处理 */
	private String encryptId;
	private String ids;
	private String operateSource;
	private String orgIds;

	/** 人房关联比例 */
	private String housePeopleProportion;
	/** 房屋类别 */
	private String houseType;

	@Actions({
			@Action(value = "dispatchOperate", results = {
					@Result(name = "success", location = "/baseinfo/houseInfo/actualHouse/actualHouseDlg.jsp"),
					@Result(name = "merge", location = "/baseinfo/houseInfo/actualHouse/mergeActualHouseInfoDlg.jsp"),
					@Result(name = "view", location = "/baseinfo/houseInfo/actualHouse/viewActualHouse.jsp"),
					@Result(name = "statistic", location = "/baseinfo/houseInfo/actualHouse/statisticListActualHouse.jsp"),
					@Result(name = "change", location = "/baseinfo/houseInfo/actualHouse/changeHouseCodeDlg.jsp"),
					@Result(name = "search", location = "/baseinfo/houseInfo/actualHouse/searchActualHouseDlg.jsp"),
					@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "dispatchOperateForGis", results = { @Result(name = "view", location = "/baseinfo/houseInfo/actualHouse/viewActualHouseDlg_gis.jsp") }) })
	public String dispatch() {
		isUseFrom = "actualHouse";
		if (DialogMode.VIEW_MODE.equals(mode)) {
			houseInfo = this.houseInfoService.getHouseInfoById(houseInfo
					.getId());
			houseInfo.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(houseInfo
							.getOrganization().getId(),
							organizationDubboService));
		} else if (DialogMode.MERGE.equals(mode)) {
			houseInfo = this.houseInfoService.getHouseInfoById(houseInfo
					.getId());
			houseInfo.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(houseInfo
							.getOrganization().getId(),
							organizationDubboService));
		}

		return getMode();
	}

	/**
	 * ID加密 合并
	 */
	@EncryptAnnotation
	@Actions({ @Action(value = "dispatchByEncryptId", results = {
			@Result(name = "merge", location = "/baseinfo/houseInfo/actualHouse/mergeActualHouseInfoDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchByEncryptId() {
		if (DialogMode.MERGE.equals(mode)) {
			houseInfo = this.houseInfoService.getHouseInfoById(houseInfo
					.getId());
			houseInfo.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(houseInfo
							.getOrganization().getId(),
							organizationDubboService));
		}

		return getMode();
	}

	/*** id加密解密【查看】 */
	@EncryptAnnotation
	@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "view", location = "/baseinfo/houseInfo/actualHouse/viewActualHouse.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperateByEncrypt() {
		isUseFrom = "actualHouse";
		if (DialogMode.VIEW_MODE.equals(mode)) {
			if (houseInfo == null) {
				houseInfo = new HouseInfo();
			}
			houseInfo = this.houseInfoService.getHouseInfoById(houseInfo
					.getId());
			houseInfo.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(houseInfo
							.getOrganization().getId(),
							organizationDubboService));
		}
		return getMode();
	}

	@Actions({ @Action(value = "prepareDispatch", results = {
			@Result(name = "success", location = "/baseinfo/houseInfo/actualHouse/actualHouseDlg.jsp"),
			@Result(name = "workBench", location = "/workBench/issueManage/actualHouseDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String prepareDispatch() {
		isUseFrom = "actualHouse";
		// 通过type判断当前页面的id是实有房屋还是出租房
		if (BaseInfoTables.ACTUALHOUSE_KEY.equals(type)) {
			houseInfo = this.houseInfoService.getHouseInfoById(id);
		} else if (BaseInfoTables.RENTALHOUSE_KEY.equals(type)) {
			RentalHouse rh = new RentalHouse();
			rh = this.rentalHouseService.getHouseInfoById(id);
			houseInfo = this.houseInfoService.getHouseInfoById(rh.getHouseId());
		} else {
			// 点击左边标签没有Type的情况,id即为实有房屋
			houseInfo = this.houseInfoService.getHouseInfoById(id);
		}
		if (null == houseInfo) {
			houseInfo = new HouseInfo();
			houseInfo.setOrganization(new Organization());
			houseInfo.getOrganization().setId(organizationId);
		}
		houseInfo.setOrganization(organizationDubboService
				.getFullOrgById(houseInfo.getOrganization().getId()));

		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(houseInfo
						.getOrganization().getId(), organizationDubboService));
		if ("workBench".equals(mode)) {
			return mode;
		}
		return SUCCESS;
	}

	/*** id加密解密【修改】 */
	@EncryptAnnotation
	@Actions({ @Action(value = "prepareDispatchByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/houseInfo/actualHouse/actualHouseDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String prepareDispatchByEncrypt() {
		isUseFrom = "actualHouse";
		// 通过type判断当前页面的id是实有房屋还是出租房
		if (BaseInfoTables.ACTUALHOUSE_KEY.equals(type)) {
			houseInfo = this.houseInfoService.getHouseInfoById(id);
		} else if (BaseInfoTables.RENTALHOUSE_KEY.equals(type)) {
			RentalHouse rh = new RentalHouse();
			rh = this.rentalHouseService.getHouseInfoById(id);
			houseInfo = this.houseInfoService.getHouseInfoById(rh.getHouseId());
		} else {
			// 点击左边标签没有Type的情况,id即为实有房屋
			houseInfo = this.houseInfoService.getHouseInfoById(id);
		}
		if (null == houseInfo) {
			houseInfo = new HouseInfo();
			houseInfo.setOrganization(new Organization());
			houseInfo.getOrganization().setId(organizationId);
		}
		houseInfo.setOrganization(organizationDubboService
				.getFullOrgById(houseInfo.getOrganization().getId()));

		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(houseInfo
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	@PermissionFilter(ename = "changeHouseCode")
	@Action(value = "changeHouseCode", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String changeHouseCode() {
		houseInfoService.changeHouseCode(orgId, oldHouseCode,
				houseInfo.getHouseCode());
		return SUCCESS;
	}

	@Action(value = "exist", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String exist() {
		int count = houseInfoService.exist(orgId, houseInfo.getHouseCode());
		return count > 0 ? SUCCESS : ERROR;
	}

	@Action(value = "searchMapScope", results = {
			@Result(name = "success", type = "json", params = { "root",
					"success" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String searchMapScope() {
		houseInfoService.searchMapScope(minOption, maxOption);
		return SUCCESS;
	}

	@Action(value = "maintainHouseInfo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"houseInfo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String maintainHouseInfo() throws Exception {
		if (null != houseInfo && null != houseInfo.getId()) {
			return updateHouseInfo();
		} else {
			return addHouseInfo();
		}
	}

	/**
	 * 新增实有房屋
	 * 
	 * @return
	 * @throws Exception
	 */
	@PermissionFilter(ename = "addActualHouse")
	@Action(value = "addHouseInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "houseInfo", "ignoreHierarchy", "false" }) })
	public String addHouseInfo() throws Exception {
		if ("rentalHouseMaintanceDialog".equals(dailogName)) {
			ThreadVariable
					.setSourcesState(ConstantsProduct.SourcesState.SYNCHRO);
		}
		rentalHouse = new RentalHouse();
		HouseInfo houseInfoTemp = houseInfo;
		houseInfo.setHouseOperateSource(operateSource);
		// rentalHouse.setRentalPerson(houseInfoTemp.getRentalPerson());
		// rentalHouse.setLessorAddress(houseInfoTemp.getLessorAddress());
		// rentalHouse.setRentalMobileNumber(houseInfoTemp
		// .getRentalMobileNumber());
		// rentalHouse.setUsage(houseInfoTemp.getUsage());
		// rentalHouse.setHiddenDangerLevel(houseInfoTemp
		// .getHiddenDangerLevel());
		// rentalHouse.setRentalType(houseInfoTemp.getRentalType());
		// rentalHouse.setRentalTelephone(houseInfoTemp.getRentalTelephone());
		// rentalHouse.setRentalHouseProperty(houseInfoTemp
		// .getRentalHouseProperty());
		houseInfo = this.houseInfoService.addHouseInfo(houseInfo);

		if (null != houseInfo && null != houseInfo.getIsRentalHouse()
				&& houseInfo.getIsRentalHouse().booleanValue()) {
			houseInfo.setIsRentalHouse(true);
			PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
					rentalHouse, houseInfoTemp, new String[] { "id" });
			if ("actualHouseMaintanceDialog".equals(dailogName)) {
				ThreadVariable
						.setSourcesState(ConstantsProduct.SourcesState.SYNCHRO);
			} else {
				ThreadVariable
						.setSourcesState(ConstantsProduct.SourcesState.ADDED);
			}
			rentalHouse.setHouseId(houseInfo.getId());
			this.rentalHouseService.addHouseInfo(rentalHouse);
		}
		houseInfo.getOrganization().setOrgName(
				organizationDubboService.getSimpleOrgById(
						houseInfo.getOrganization().getId()).getOrgName());
		return SUCCESS;
	}

	@Action(value = "addHouseInfoForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "houseInfo", "ignoreHierarchy", "false" }) })
	public String addHouseInfoForMobile() throws Exception {
		if ("rentalHouseMaintanceDialog".equals(dailogName)) {
			ThreadVariable
					.setSourcesState(ConstantsProduct.SourcesState.SYNCHRO);
		}
		rentalHouse = new RentalHouse();
		HouseInfo houseInfoTemp = houseInfo;
		if (rental != null) {
			if (rental.getRentalPerson() != null) {
				rentalHouse.setRentalPerson(rental.getRentalPerson());
			}
			if (rental.getLessorAddress() != null) {
				rentalHouse.setLessorAddress(rental.getLessorAddress());
			}
			if (rental.getRentalMobileNumber() != null) {
				rentalHouse.setRentalMobileNumber(rental
						.getRentalMobileNumber());
			}
			if (rental.getUsage() != null) {
				rentalHouse.setUsage(rental.getUsage());
			}
			if (rental.getHiddenDangerLevel() != null) {
				rentalHouse.setHiddenDangerLevel(rental.getHiddenDangerLevel());
			}
			if (rental.getRentalType() != null) {
				rentalHouse.setRentalType(rental.getRentalType());
			}
			if (rental.getRentalTelephone() != null) {
				rentalHouse.setRentalTelephone(rental.getRentalTelephone());
			}
			if (rental.getRentalHouseProperty() != null) {
				rentalHouse.setRentalHouseProperty(rental
						.getRentalHouseProperty());
			}
			if (rental.getHouseFileNum() != null) {
				rentalHouse.setHouseFileNum(rental.getHouseFileNum());
			}
			if (rental.getLessorType() != null) {
				rentalHouse.setLessorType(rental.getLessorType());
			}
			if (rental.getRentalCertificateType() != null) {
				rentalHouse.setRentalCertificateType(rental
						.getRentalCertificateType());
			}
			if (rental.getRentalCertificateNumbe() != null) {
				rentalHouse.setRentalCertificateNumbe(rental
						.getRentalCertificateNumbe());
			}
			if (rental.getRentalTelephone() != null) {
				rentalHouse.setRentalTelephone(rental.getRentalTelephone());
			}
			if (rental.getRentalMobileNumber() != null) {
				rentalHouse.setRentalMobileNumber(rental
						.getRentalMobileNumber());
			}
			if (rental.getMangerTypes() != null) {
				rentalHouse.setMangerTypes(rental.getMangerTypes());
			}
			if (rental.getRegistDate() != null) {
				rentalHouse.setRegistDate(rental.getRegistDate());
			}
			if (rental.getLessorDate() != null) {
				rentalHouse.setLessorDate(rental.getLessorDate());
			}
			if (rental.getRoomNumber() != null) {
				rentalHouse.setRoomNumber(rental.getRoomNumber());
			}
			if (rental.getLimitPersons() != null) {
				rentalHouse.setLimitPersons(rental.getLimitPersons());
			}
			if (rental.getRentMonth() != null) {
				rentalHouse.setRentMonth(rental.getRentMonth());
			}
			if (rental.getHiddenRectification() != null) {
				rentalHouse.setHiddenRectification(rental
						.getHiddenRectification());
			}
			if (rental.getIsSignGuarantee() != null) {
				rentalHouse.setIsSignGuarantee(rental.getIsSignGuarantee());
			}
			if (rental.getIsSafetyChannel() != null) {
				rentalHouse.setIsSafetyChannel(rental.getIsSafetyChannel());
			}
			if (rental.getIsFireChannels() != null) {
				rentalHouse.setIsFireChannels(rental.getIsFireChannels());
			}
		}
		houseInfo = this.houseInfoService.addHouseInfo(houseInfo);

		if (null != houseInfo.getIsRentalHouse()
				&& houseInfo.getIsRentalHouse().booleanValue()) {
			houseInfo.setIsRentalHouse(true);
			PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
					rentalHouse, houseInfoTemp, new String[] { "id" });
			if ("actualHouseMaintanceDialog".equals(dailogName)) {
				ThreadVariable
						.setSourcesState(ConstantsProduct.SourcesState.SYNCHRO);
			} else {
				ThreadVariable
						.setSourcesState(ConstantsProduct.SourcesState.ADDED);
			}
			rentalHouse.setHouseId(houseInfo.getId());
			this.rentalHouseService.addHouseInfo(rentalHouse);
		}
		houseInfo.getOrganization().setOrgName(
				organizationDubboService.getSimpleOrgById(
						houseInfo.getOrganization().getId()).getOrgName());
		return SUCCESS;
	}

	@Action(value = "dispatchHouseInfoByHouseId", results = { @Result(name = "success", location = "/baseinfo/houseInfo/actualHouse/viewActualHouse.jsp") })
	public String dispatchHouseInfoByHouseId() {
		houseInfo = houseInfoService.getHouseInfoById(houseInfo.getId());
		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(houseInfo
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	/**
	 * id加密查看基础信息
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "dispatchHouseInfoByHouseIdByEncrypt", results = { @Result(name = "success", location = "/baseinfo/houseInfo/actualHouse/viewActualHouse.jsp") })
	public String dispatchHouseInfoByHouseIdByEncrypt() {
		houseInfo = houseInfoService.getHouseInfoById(houseInfo.getId());
		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(houseInfo
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "dispatchactualHousePopulationByOrgIdAndHouseId", results = {
			@Result(name = "success", location = "/baseinfo/houseInfo/actualHouse/actualHousePopulationList.jsp"),
			@Result(name = "print", location = "/baseinfo/houseInfo/actualHouse/actualHousePopulationListPrint.jsp") })
	public String dispatchactualHousePopulationByOrgIdAndHouseId() {
		houseInfo = houseInfoService.getHouseInfoById(houseInfo.getId());
		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(houseInfo
						.getOrganization().getId(), organizationDubboService));
		if ("print".equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	/**
	 * id加密查看人员信息
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "dispatchactualHousePopulationByOrgIdAndHouseIdByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/houseInfo/actualHouse/actualHousePopulationList.jsp"),
			@Result(name = "print", location = "/baseinfo/houseInfo/actualHouse/actualHousePopulationListPrint.jsp") })
	public String dispatchactualHousePopulationByOrgIdAndHouseIdByEncrypt() {
		houseInfo = houseInfoService.getHouseInfoById(houseInfo.getId());
		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(houseInfo
						.getOrganization().getId(), organizationDubboService));
		if ("print".equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	@Action(value = "deleteHouseHasActualPopulationByPopulationTypeAndHouseId", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteHouseHasActualPopulationByPopulationTypeAndHouseId() {
		houseHasActualPopulationService
				.deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
						houseHasActualPopulation.getPopulationType(),
						houseHasActualPopulation.getHouseId(),
						houseHasActualPopulation.getPopulationId());
		return SUCCESS;
	}

	@Action(value = "deleteHouseHasActualPopulationByPopulationTypeAndHouseEncryptId", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	@EncryptAnnotation
	public String deleteHouseHasActualPopulationByPopulationTypeAndHouseEncryptId() {
		houseHasActualPopulationService
				.deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
						houseHasActualPopulation.getPopulationType(),
						houseHasActualPopulation.getHouseId(),
						houseHasActualPopulation.getPopulationId());
		return SUCCESS;
	}

	@Action(value = "addHouseHasActualPopulation", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String addHouseHasActualPopulation() {
		houseHasActualPopulationService
				.deleteHouseHasActualPopulationByPopulationTypeAndPopulationId(
						houseHasActualPopulation.getPopulationType(),
						houseHasActualPopulation.getPopulationId());
		houseHasActualPopulationService
				.addHouseHasActualPopulation(houseHasActualPopulation);
		return SUCCESS;
	}

	@Action(value = "addHouseHasActualPopulationByEncrypt", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	@EncryptAnnotation
	public String addHouseHasActualPopulationByEncrypt() {
		houseHasActualPopulationService
				.deleteHouseHasActualPopulationByPopulationTypeAndPopulationId(
						houseHasActualPopulation.getPopulationType(),
						houseHasActualPopulation.getPopulationId());
		houseHasActualPopulationService
				.addHouseHasActualPopulation(houseHasActualPopulation);
		return SUCCESS;
	}

	/**
	 * 修改实有房屋
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "updateActualHouse")
	@Action(value = "updateHouseInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "houseInfo", "ignoreHierarchy", "false" }) })
	public String updateHouseInfo() {
		houseInfo.setCommunity(houseInfo.getAddress());
		HouseInfo houseInfoTemp = houseInfo;
		// 更新实有房屋
		houseInfo.setHouseOperateSource(operateSource);
		houseInfoTemp.setHouseOperateSource(operateSource);
		houseInfo = houseInfoService.updateHouseInfo(houseInfo);
		// 查询出租房
		RentalHouse rentalHouse = rentalHouseService
				.getHouseInfoByHouseId(houseInfo.getId());
		// 判断是否有出租房
		if (houseInfo.getIsRentalHouse() == null
				|| !houseInfo.getIsRentalHouse()) {
			if (rentalHouse != null) {
				List<Long> idList = new ArrayList<Long>();
				idList.add(rentalHouse.getId());
				// rentalHouseService.deleteRentalHousesByIdList(idList);
				rentalHouseService.deleteRentalHouseByIds(parseToLong(idList));
			}
		} else {
			// 判断出租房是否存在，不存在新增，存在更新
			if (rentalHouse != null) {
				// if (houseInfoTemp.getRentalPerson() != null) {
				// rentalHouse.setRentalPerson(houseInfoTemp
				// .getRentalPerson());
				// rentalHouse.setLessorAddress(houseInfoTemp
				// .getLessorAddress());
				// rentalHouse.setRentalMobileNumber(houseInfoTemp
				// .getRentalMobileNumber());
				// rentalHouse.setUsage(houseInfoTemp.getUsage());
				// rentalHouse.setHiddenDangerLevel(houseInfoTemp
				// .getHiddenDangerLevel());
				// rentalHouse
				// .setRentalType(houseInfoTemp.getRentalType());
				// rentalHouse.setRentalTelephone(houseInfoTemp
				// .getRentalTelephone());
				// rentalHouse.setRentalHouseProperty(houseInfoTemp
				// .getRentalHouseProperty());
				// }
				// 关联到实有房的时候实有房 修改 出租房也相应修改 fateson add
				PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
						rentalHouse, houseInfo, new String[] { "id" });
				rentalHouse.setHouseOperateSource(operateSource);
				rentalHouseService.updateHouseBaseInfo(rentalHouse);
			} else {
				rentalHouse = new RentalHouse();
				// rentalHouse
				// .setRentalPerson(houseInfoTemp.getRentalPerson());
				// rentalHouse.setLessorAddress(houseInfoTemp
				// .getLessorAddress());
				// rentalHouse.setRentalMobileNumber(houseInfoTemp
				// .getRentalMobileNumber());
				// rentalHouse.setUsage(houseInfoTemp.getUsage());
				// rentalHouse.setHiddenDangerLevel(houseInfoTemp
				// .getHiddenDangerLevel());
				// rentalHouse.setRentalType(houseInfoTemp.getRentalType());
				// rentalHouse.setRentalTelephone(houseInfoTemp
				// .getRentalTelephone());
				// rentalHouse.setRentalHouseProperty(houseInfoTemp
				// .getRentalHouseProperty());
				houseInfo.setIsRentalHouse(true);
				PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
						rentalHouse, houseInfo, new String[] { "id" });
				rentalHouse.setHouseId(houseInfo.getId());
				this.rentalHouseService.addHouseInfo(rentalHouse);
			}
		}
		return SUCCESS;
	}

	private Long[] parseToLong(List<Long> idList) {
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		return ids;
	}

	@Action(value = "updateHouseInfoForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "houseInfo", "ignoreHierarchy", "false" }) })
	public String updateHouseInfoForMobile() {
		houseInfo.setCommunity(houseInfo.getAddress());
		HouseInfo houseInfoTemp = houseInfo;
		// 更新实有房屋
		houseInfo.setHouseOperateSource(operateSource);
		houseInfoTemp.setHouseOperateSource(operateSource);
		houseInfo = houseInfoService.updateHouseInfo(houseInfo);
		// 查询出租房
		RentalHouse rentalHouse = rentalHouseService
				.getHouseInfoByHouseId(houseInfo.getId());
		// 判断是否有出租房
		if (houseInfo.getIsRentalHouse() == null
				|| !houseInfo.getIsRentalHouse()) {
			if (rentalHouse != null) {
				List<Long> idList = new ArrayList<Long>();
				idList.add(rentalHouse.getId());
				rentalHouseService.deleteRentalHousesByIdList(idList);
			}
		} else {
			// 判断出租房是否存在，不存在新增，存在更新
			if (rentalHouse != null && rental != null) {
				if (rental.getRentalPerson() != null) {
					rentalHouse.setRentalPerson(rental.getRentalPerson());
				}
				if (rental.getLessorAddress() != null) {
					rentalHouse.setLessorAddress(rental.getLessorAddress());
				}
				if (rental.getRentalMobileNumber() != null) {
					rentalHouse.setRentalMobileNumber(rental
							.getRentalMobileNumber());
				}
				if (rental.getUsage() != null) {
					rentalHouse.setUsage(rental.getUsage());

				}
				if (rental.getHiddenDangerLevel() != null) {
					rentalHouse.setHiddenDangerLevel(rental
							.getHiddenDangerLevel());
				}
				if (rental.getRentalType() != null) {
					rentalHouse.setRentalType(rental.getRentalType());
				}
				if (rental.getRentalTelephone() != null) {
					rentalHouse.setRentalTelephone(rental.getRentalTelephone());
				}
				if (rental.getRentalHouseProperty() != null) {
					rentalHouse.setRentalHouseProperty(rental
							.getRentalHouseProperty());
				}

				if (rental.getHouseFileNum() != null) {
					rentalHouse.setHouseFileNum(rental.getHouseFileNum());
				}
				if (rental.getLessorType() != null) {
					rentalHouse.setLessorType(rental.getLessorType());
				}
				if (rental.getRentalCertificateType() != null) {
					rentalHouse.setRentalCertificateType(rental
							.getRentalCertificateType());
				}
				if (rental.getRentalCertificateNumbe() != null) {
					rentalHouse.setRentalCertificateNumbe(rental
							.getRentalCertificateNumbe());
				}
				if (rental.getRentalTelephone() != null) {
					rentalHouse.setRentalTelephone(rental.getRentalTelephone());
				}
				if (rental.getRentalMobileNumber() != null) {
					rentalHouse.setRentalMobileNumber(rental
							.getRentalMobileNumber());
				}
				if (rental.getMangerTypes() != null) {
					rentalHouse.setMangerTypes(rental.getMangerTypes());
				}
				if (rental.getRegistDate() != null) {
					rentalHouse.setRegistDate(rental.getRegistDate());
				}
				if (rental.getLessorDate() != null) {
					rentalHouse.setLessorDate(rental.getLessorDate());
				}
				if (rental.getRoomNumber() != null) {
					rentalHouse.setRoomNumber(rental.getRoomNumber());
				}
				if (rental.getLimitPersons() != null) {
					rentalHouse.setLimitPersons(rental.getLimitPersons());
				}
				if (rental.getRentMonth() != null) {
					rentalHouse.setRentMonth(rental.getRentMonth());
				}
				if (rental.getHiddenRectification() != null) {
					rentalHouse.setHiddenRectification(rental
							.getHiddenRectification());
				}
				if (rental.getIsSignGuarantee() != null) {
					rentalHouse.setIsSignGuarantee(rental.getIsSignGuarantee());
				}
				if (rental.getIsSafetyChannel() != null) {
					rentalHouse.setIsSafetyChannel(rental.getIsSafetyChannel());
				}
				if (rental.getIsFireChannels() != null) {
					rentalHouse.setIsFireChannels(rental.getIsFireChannels());
				}

				// 关联到实有房的时候实有房 修改 出租房也相应修改 fateson add
				PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
						rentalHouse, houseInfo, new String[] { "id" });
				rentalHouse.setHouseOperateSource(operateSource);
				rentalHouseService.updateHouseBaseInfoForMobile(rentalHouse);
			} else {
				rentalHouse = new RentalHouse();
				if (rental != null) {
					if (rental.getRentalPerson() != null) {
						rentalHouse.setRentalPerson(rental.getRentalPerson());
					}
					if (rental.getLessorAddress() != null) {
						rentalHouse.setLessorAddress(rental.getLessorAddress());
					}
					if (rental.getRentalMobileNumber() != null) {
						rentalHouse.setRentalMobileNumber(rental
								.getRentalMobileNumber());
					}
					if (rental.getUsage() != null) {
						rentalHouse.setUsage(rental.getUsage());
					}
					if (rental.getHiddenDangerLevel() != null) {
						rentalHouse.setHiddenDangerLevel(rental
								.getHiddenDangerLevel());
					}
					if (rental.getRentalType() != null) {
						rentalHouse.setRentalType(rental.getRentalType());
					}
					if (rental.getRentalTelephone() != null) {
						rentalHouse.setRentalTelephone(rental
								.getRentalTelephone());
					}
					if (rental.getRentalHouseProperty() != null) {
						rentalHouse.setRentalHouseProperty(rental
								.getRentalHouseProperty());
					}

					if (rental.getHouseFileNum() != null) {
						rentalHouse.setHouseFileNum(rental.getHouseFileNum());
					}
					if (rental.getLessorType() != null) {
						rentalHouse.setLessorType(rental.getLessorType());
					}
					if (rental.getRentalCertificateType() != null) {
						rentalHouse.setRentalCertificateType(rental
								.getRentalCertificateType());
					}
					if (rental.getRentalCertificateNumbe() != null) {
						rentalHouse.setRentalCertificateNumbe(rental
								.getRentalCertificateNumbe());
					}
					if (rental.getRentalTelephone() != null) {
						rentalHouse.setRentalTelephone(rental
								.getRentalTelephone());
					}
					if (rental.getRentalMobileNumber() != null) {
						rentalHouse.setRentalMobileNumber(rental
								.getRentalMobileNumber());
					}
					if (rental.getMangerTypes() != null) {
						rentalHouse.setMangerTypes(rental.getMangerTypes());
					}
					if (rental.getRegistDate() != null) {
						rentalHouse.setRegistDate(rental.getRegistDate());
					}
					if (rental.getLessorDate() != null) {
						rentalHouse.setLessorDate(rental.getLessorDate());
					}
					if (rental.getRoomNumber() != null) {
						rentalHouse.setRoomNumber(rental.getRoomNumber());
					}
					if (rental.getLimitPersons() != null) {
						rentalHouse.setLimitPersons(rental.getLimitPersons());
					}
					if (rental.getRentMonth() != null) {
						rentalHouse.setRentMonth(rental.getRentMonth());
					}
					if (rental.getHiddenRectification() != null) {
						rentalHouse.setHiddenRectification(rental
								.getHiddenRectification());
					}
					if (rental.getIsSignGuarantee() != null) {
						rentalHouse.setIsSignGuarantee(rental
								.getIsSignGuarantee());
					}
					if (rental.getIsSafetyChannel() != null) {
						rentalHouse.setIsSafetyChannel(rental
								.getIsSafetyChannel());
					}
					if (rental.getIsFireChannels() != null) {
						rentalHouse.setIsFireChannels(rental
								.getIsFireChannels());
					}
				}
				houseInfo.setIsRentalHouse(true);
				PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
						rentalHouse, houseInfo, new String[] { "id" });
				rentalHouse.setHouseId(houseInfo.getId());
				this.rentalHouseService.addHouseInfo(rentalHouse);
			}
		}
		return SUCCESS;
	}

	@Action(value = "houseHasActualPopulation", results = { @Result(name = "success", type = "json", params = {
			"root", "hasActualPopulation" }) })
	public String houseHasActualPopulation() {
		String[] deleteId = houseIds.split(",");
		for (int i = 0; i < deleteId.length; i++) {
			List<HouseHasActualPopulation> houseHasActualPopulations = houseHasActualPopulationService
					.getHouseHasActualPopulationByHouseId(Long
							.valueOf(deleteId[i]));
			if (null != houseHasActualPopulations
					&& houseHasActualPopulations.size() != 0) {
				hasActualPopulation = true;
				break;
			} else {
				hasActualPopulation = false;
			}
		}
		return SUCCESS;
	}

	/**
	 * 根据房屋ID和准确地址查询房屋信息
	 */
	@Action(value = "getHouseInfoById", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage" }) })
	public String getHouseInfoById() {
		if (houseInfo == null || houseInfo.getId() == null) {
			errorMessage = "操作失败，请重试";
			return ERROR;
		}
		houseInfo = houseInfoService.getHouseInfoById(houseInfo.getId());
		houseList = new ArrayList<HouseInfo>();
		if (houseInfo != null) {
			houseList.add(houseInfo);
		}
		gridPage = new GridPage<HouseInfo>(houseList);
		return SUCCESS;
	}

	/**
	 * 根据房屋的准确地址和网格ID查询房屋信息
	 */
	@Action(value = "findHouseInfoByAddressAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findHouseInfoByAddressAndOrgId() {
		if (houseInfo == null) {
			errorMessage = "信息查询错误";
			return ERROR;
		}
		if (houseInfo.getAddress() == null || orgId == null) {
			errorMessage = "信息查询错误";
			return ERROR;
		}
		gridPage = new GridPage<HouseInfo>(
				houseInfoService.findHouseInfoByHouseAddressAndOrgId(page,
						rows, sidx, sord, houseInfo.getAddress(), orgId,
						houseInfo.getId()));
		return SUCCESS;
	}

	/**
	 * 点击合并，将选中的房屋信息查询得到，然后返回到信息对比页面
	 */
	@EncryptAnnotation
	@Action(value = "getMegerHouseInfo", results = {
			@Result(name = "success", location = "/baseinfo/houseInfo/actualHouse/megerHouseDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getMegerHouseInfo() {
		if (houseId == null || megerId == null) {
			errorMessage = "房屋信息为空";
			return ERROR;
		}
		houseInfo = houseInfoService.getHouseInfoById(houseId);
		megerHouseInfo = houseInfoService.getHouseInfoById(megerId);
		// 通过方法将houseInfo转换成需要的信息格式
		// packageHouseInfo = transformationHouseInfo(houseInfo,
		// megerHouseInfo);
		houseInfoMap = transformationHouseInfo(houseInfo, megerHouseInfo);
		return SUCCESS;
	}

	@Action(value = "shiftDispatch", results = {
			@Result(name = "success", location = "/common/shiftTree.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String shiftDispatch() {
		if (orgId == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		ids = houseIds;
		houseInfo = new RentalHouse();
		houseInfo.setOrganization(organizationDubboService
				.getSimpleOrgById(orgId));
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "shiftDispatchByEncryptId", results = {
			@Result(name = "success", location = "/common/shiftTree.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String shiftDispatchByEncryptId() {
		if (orgId == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		ids = houseIds;
		houseInfo = new RentalHouse();
		organization = organizationDubboService.getSimpleOrgById(orgId);
		houseInfo.setOrganization(organization);
		return SUCCESS;
	}

	@Action(value = "combineHouseInfo", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String combineHouseInfo() {
		if (houseInfo == null || megerId == null) {
			errorMessage = "合并失败";
			return ERROR;
		}

		// 先将是否出租屋转换成boolean
		if (houseInfo.getRentalHouse() != null
				&& houseInfo.getRentalHouse().equals("是")) {
			houseInfo.setIsRentalHouse(true);
		} else {
			houseInfo.setIsRentalHouse(false);
		}
		// 将合并后的房屋信息以及被合并房屋信息传到后台处理
		houseInfoServices.disposeHouseMegerInfo(houseInfo, megerId,
				useRentalHouse);

		return SUCCESS;
	}

	private boolean validateHouseInfo(Object mainObj, Object MegerObj) {
		if (MegerObj != null && MegerObj.equals(mainObj)) {
			return true;
		}
		if (MegerObj == null && mainObj == null) {
			return true;
		}
		if (mainObj != null && MegerObj == null) {
			return true;
		}
		return false;

	}

	private static PackageHouseInfo setPageInfoValue(
			PackageHouseInfo packageInfo, String mainVal, String megerVal) {
		packageInfo.setMainValue(mainVal);
		packageInfo.setMegerValue(megerVal);
		return packageInfo;
	}

	/**
	 * 将两个房屋信息封装成前台需要的简单个事
	 * 
	 * @param houseInfo
	 * @param megerHouseInfo
	 * @return
	 */
	private Map<String, List<PackageHouseInfo>> transformationHouseInfo(
			HouseInfo houseInfo, HouseInfo megerHouseInfo) {
		Map<String, List<PackageHouseInfo>> map = new HashMap<String, List<PackageHouseInfo>>();
		List<PackageHouseInfo> packageHouseInfo = new ArrayList<PackageHouseInfo>();
		List<PackageHouseInfo> packageHouseInfoDifrent = new ArrayList<PackageHouseInfo>();
		PackageHouseInfo packageInfo = new PackageHouseInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		packageInfo.setHouseInfoId(houseInfo.getId());
		packageInfo.setcName("住房编号");
		packageInfo.setEnName("houseCode");
		if (!validateHouseInfo(houseInfo.getHouseCode(),
				megerHouseInfo.getHouseCode())) {
			packageInfo = setPageInfoValue(packageInfo,
					houseInfo.getHouseCode(), megerHouseInfo.getHouseCode());
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getHouseCode());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();

		packageInfo.setcName("是否出租房");
		packageInfo.setEnName("rentalHouse");
		String mainIsrental = "";
		String megerIsrental = "";
		if (houseInfo.getIsRentalHouse()) {
			mainIsrental = "是";
		} else {
			mainIsrental = "否";
		}
		if (megerHouseInfo.getIsRentalHouse()) {
			megerIsrental = "是";
		} else {
			megerIsrental = "否";
		}
		if (validateHouseInfo(houseInfo.getIsRentalHouse(),
				megerHouseInfo.getIsRentalHouse())) {
			if (houseInfo.getIsRentalHouse()
					&& megerHouseInfo.getIsRentalHouse()) {
				packageInfo = setPageInfoValue(packageInfo, mainIsrental,
						megerIsrental);
				packageInfo.setIsRentalVlaue("出租房信息");
				packageHouseInfoDifrent.add(packageInfo);
			} else {
				packageInfo.setMainValue(mainIsrental);
				packageHouseInfo.add(packageInfo);
			}
		} else {
			packageInfo.setMainValue(mainIsrental);
			packageInfo.setMegerValue(megerIsrental);
			packageHouseInfoDifrent.add(packageInfo);

		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("房屋准确地址");
		packageInfo.setEnName("address");
		if (!validateHouseInfo(houseInfo.getAddress(),
				megerHouseInfo.getAddress())) {
			packageInfo = setPageInfoValue(packageInfo, houseInfo.getAddress(),
					megerHouseInfo.getAddress());
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getAddress());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("房产证地址");
		packageInfo.setEnName("houseAddress");

		if (!validateHouseInfo(houseInfo.getHouseAddress(),
				megerHouseInfo.getHouseAddress())) {
			packageInfo = setPageInfoValue(packageInfo,
					houseInfo.getHouseAddress(),
					megerHouseInfo.getHouseAddress());
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getHouseAddress());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("建筑物名称");
		packageInfo.setEnName("buildingName");

		if (!validateHouseInfo(houseInfo.getBuildingName(),
				megerHouseInfo.getBuildingName())) {
			packageInfo = setPageInfoValue(packageInfo,
					houseInfo.getBuildingName(),
					megerHouseInfo.getBuildingName());
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getBuildingName());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("建筑物用途");
		packageInfo.setEnName("buildingUses.displayName");
		packageInfo.setMainPropertyName("buildingUses");
		Long mainId = null;
		Long megerId = null;
		if (houseInfo.getBuildingUses() != null
				&& houseInfo.getBuildingUses().getId() != null) {
			mainId = houseInfo.getBuildingUses().getId();
		}
		if (megerHouseInfo.getBuildingUses() != null
				&& megerHouseInfo.getBuildingUses().getId() != null) {
			megerId = megerHouseInfo.getBuildingUses().getId();
		}
		if (!validateHouseInfo(mainId, megerId)) {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			if (megerId != null) {
				packageInfo.setMegerValue(getPropertyName(megerId));
				packageInfo.setMegerPropertyDictId(megerId);
			}
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("物管单位名称");
		packageInfo.setEnName("propertyName");

		if (!validateHouseInfo(houseInfo.getPropertyName(),
				megerHouseInfo.getPropertyName())) {
			packageInfo = setPageInfoValue(packageInfo,
					houseInfo.getPropertyName(),
					megerHouseInfo.getPropertyName());
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getBuildingName());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();

		packageInfo.setcName("代表人/业主");
		packageInfo.setEnName("houseOwner");
		if (!validateHouseInfo(houseInfo.getHouseOwner(),
				megerHouseInfo.getHouseOwner())) {
			packageInfo = setPageInfoValue(packageInfo,
					houseInfo.getHouseOwner(), megerHouseInfo.getHouseOwner());
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getHouseOwner());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();

		packageInfo.setcName("身份证号码");
		packageInfo.setEnName("houseOwnerIdCardNo");
		if (!validateHouseInfo(houseInfo.getHouseOwnerIdCardNo(),
				megerHouseInfo.getHouseOwnerIdCardNo())) {
			packageInfo = setPageInfoValue(packageInfo,
					houseInfo.getHouseOwnerIdCardNo(),
					megerHouseInfo.getHouseOwnerIdCardNo());
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getHouseOwnerIdCardNo());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();

		packageInfo.setcName("代表人电话");
		packageInfo.setEnName("telephone");
		if (!validateHouseInfo(houseInfo.getTelephone(),
				megerHouseInfo.getTelephone())) {
			packageInfo = setPageInfoValue(packageInfo,
					houseInfo.getTelephone(), megerHouseInfo.getTelephone());
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getTelephone());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("房屋户型");
		packageInfo.setEnName("houseDoorModel");
		if (!validateHouseInfo(houseInfo.getHouseDoorModel(),
				megerHouseInfo.getHouseDoorModel())) {
			packageInfo = setPageInfoValue(packageInfo,
					houseInfo.getHouseDoorModel(),
					megerHouseInfo.getHouseDoorModel());
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getHouseDoorModel());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("建成年份");
		packageInfo.setEnName("builtYear");
		if (!validateHouseInfo(houseInfo.getBuiltYear(),
				megerHouseInfo.getBuiltYear())) {
			packageInfo = setPageInfoValue(packageInfo,
					houseInfo.getBuiltYear(), megerHouseInfo.getBuiltYear());
			// packageInfo.setMainValue(houseInfo.getBuiltYear());
			// packageInfo.setMegerValue(megerHouseInfo.getBuiltYear());
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getBuiltYear());
			packageHouseInfo.add(packageInfo);
		}
		packageInfo = new PackageHouseInfo();

		packageInfo.setcName("本户建筑面积");
		packageInfo.setEnName("houseArea");
		if (!validateHouseInfo(houseInfo.getHouseArea(),
				megerHouseInfo.getHouseArea())) {
			packageInfo.setMainValue(houseInfo.getHouseArea() != null ? String
					.valueOf(houseInfo.getHouseArea()) : null);
			packageInfo
					.setMegerValue(megerHouseInfo.getHouseArea() != null ? String
							.valueOf(megerHouseInfo.getHouseArea()) : null);
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getHouseArea() != null ? String
					.valueOf(houseInfo.getHouseArea()) : null);
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("房屋结构");
		packageInfo.setEnName("houseStructure.displayName");
		packageInfo.setMainPropertyName("houseStructure");

		mainId = null;
		megerId = null;
		if (houseInfo.getHouseStructure() != null
				&& houseInfo.getHouseStructure().getId() != null) {
			mainId = houseInfo.getHouseStructure().getId();
		}
		if (megerHouseInfo.getHouseStructure() != null
				&& megerHouseInfo.getHouseStructure().getId() != null) {
			megerId = megerHouseInfo.getHouseStructure().getId();
		}
		if (!validateHouseInfo(mainId, megerId)) {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			if (megerId != null) {
				packageInfo.setMegerValue(getPropertyName(megerId));
				packageInfo.setMegerPropertyDictId(megerId);
			}

			packageHouseInfoDifrent.add(packageInfo);
		} else {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("房屋用途");
		packageInfo.setEnName("houseUses.displayName");
		packageInfo.setMainPropertyName("houseUses");
		mainId = null;
		megerId = null;
		if (houseInfo.getHouseUses() != null
				&& houseInfo.getHouseUses().getId() != null) {
			mainId = houseInfo.getHouseUses().getId();
		}
		if (megerHouseInfo.getHouseUses() != null
				&& megerHouseInfo.getHouseUses().getId() != null) {
			megerId = megerHouseInfo.getHouseUses().getId();
		}
		if (!validateHouseInfo(mainId, megerId)) {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			if (megerId != null) {
				packageInfo.setMegerValue(getPropertyName(megerId));
				packageInfo.setMegerPropertyDictId(megerId);
			}

			packageHouseInfoDifrent.add(packageInfo);
		} else {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("房屋来源");
		packageInfo.setEnName("houseSource.displayName");
		packageInfo.setMainPropertyName("houseSource");
		mainId = null;
		megerId = null;
		if (houseInfo.getHouseSource() != null
				&& houseInfo.getHouseSource().getId() != null) {
			mainId = houseInfo.getHouseSource().getId();
		}
		if (megerHouseInfo.getHouseSource() != null
				&& megerHouseInfo.getHouseSource().getId() != null) {
			megerId = megerHouseInfo.getHouseSource().getId();
		}
		if (!validateHouseInfo(mainId, megerId)) {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			if (megerId != null) {
				packageInfo.setMegerValue(getPropertyName(megerId));
				packageInfo.setMegerPropertyDictId(megerId);
			}

			packageHouseInfoDifrent.add(packageInfo);
		} else {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();

		packageInfo.setcName("自有产权");
		packageInfo.setEnName("ownProperty.displayName");
		packageInfo.setMainPropertyName("ownProperty");
		mainId = null;
		megerId = null;
		if (houseInfo.getOwnProperty() != null
				&& houseInfo.getOwnProperty().getId() != null) {
			mainId = houseInfo.getOwnProperty().getId();
		}
		if (megerHouseInfo.getOwnProperty() != null
				&& megerHouseInfo.getOwnProperty().getId() != null) {
			megerId = megerHouseInfo.getOwnProperty().getId();
		}
		if (!validateHouseInfo(mainId, megerId)) {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			if (megerId != null) {
				packageInfo.setMegerValue(getPropertyName(megerId));
				packageInfo.setMegerPropertyDictId(megerId);
			}

			packageHouseInfoDifrent.add(packageInfo);
		} else {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();

		packageInfo.setcName("房屋凭证");
		packageInfo.setEnName("housingVouchers.displayName");
		packageInfo.setMainPropertyName("housingVouchers");

		mainId = null;
		megerId = null;
		if (houseInfo.getHousingVouchers() != null
				&& houseInfo.getHousingVouchers().getId() != null) {
			mainId = houseInfo.getHousingVouchers().getId();
		}
		if (megerHouseInfo.getHousingVouchers() != null
				&& megerHouseInfo.getHousingVouchers().getId() != null) {
			megerId = megerHouseInfo.getHousingVouchers().getId();
		}
		if (!validateHouseInfo(mainId, megerId)) {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			if (megerId != null) {
				packageInfo.setMegerValue(getPropertyName(megerId));
				packageInfo.setMegerPropertyDictId(megerId);
			}

			packageHouseInfoDifrent.add(packageInfo);
		} else {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("房屋权证号");
		packageInfo.setEnName("houseRightNumber");
		if (!validateHouseInfo(houseInfo.getHouseRightNumber(),
				megerHouseInfo.getHouseRightNumber())) {
			packageInfo
					.setMainValue(houseInfo.getHouseRightNumber() != null ? houseInfo
							.getHouseRightNumber

							() : null);
			packageInfo
					.setMegerValue(megerHouseInfo.getHouseRightNumber() != null ?

					megerHouseInfo.getHouseRightNumber()
							: null);
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getHouseRightNumber());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("发证时间");
		packageInfo.setEnName("houseRightNumberDate");
		if (!validateHouseInfo(houseInfo.getHouseRightNumberDate(),
				megerHouseInfo.getHouseRightNumberDate())) {
			packageInfo
					.setMainValue(houseInfo.getHouseRightNumberDate() != null ? sdf
							.format(houseInfo.getHouseRightNumberDate()) : null);
			packageInfo
					.setMegerValue(megerHouseInfo.getHouseRightNumberDate() != null ? sdf
							.format(megerHouseInfo.getHouseRightNumberDate())
							: null);
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo
					.setMainValue(houseInfo.getHouseRightNumberDate() != null ? sdf
							.format(houseInfo.getHouseRightNumberDate()) : null);
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("土地凭证");
		packageInfo.setEnName("landDocuments.displayName");
		packageInfo.setMainPropertyName("landDocuments");
		mainId = null;
		megerId = null;
		if (houseInfo.getLandDocuments() != null
				&& houseInfo.getLandDocuments().getId() != null) {
			mainId = houseInfo.getLandDocuments().getId();
		}
		if (megerHouseInfo.getLandDocuments() != null
				&& megerHouseInfo.getLandDocuments().getId() != null) {
			megerId = megerHouseInfo.getLandDocuments().getId();
		}
		if (!validateHouseInfo(mainId, megerId)) {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			if (megerId != null) {
				packageInfo.setMegerValue(getPropertyName(megerId));
				packageInfo.setMegerPropertyDictId(megerId);
			}

			packageHouseInfoDifrent.add(packageInfo);
		} else {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("土地权证号");
		packageInfo.setEnName("landRightsNumbe");
		if (!validateHouseInfo(houseInfo.getLandRightsNumbe(),
				megerHouseInfo.getLandRightsNumbe())) {
			packageInfo
					.setMainValue(houseInfo.getLandRightsNumbe() != null ? houseInfo
							.getLandRightsNumbe() : null);
			packageInfo
					.setMegerValue(megerHouseInfo.getLandRightsNumbe() != null ?

					megerHouseInfo.getLandRightsNumbe() : null);
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getLandRightsNumbe());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("土地权证发证时间");
		packageInfo.setEnName("landRightsNumbeDate");
		if (!validateHouseInfo(houseInfo.getLandRightsNumbeDate(),
				megerHouseInfo.getLandRightsNumbeDate())) {
			packageInfo
					.setMainValue(houseInfo.getLandRightsNumbeDate() != null ? sdf
							.format(houseInfo.getLandRightsNumbeDate()) : null);
			packageInfo
					.setMegerValue(megerHouseInfo.getLandRightsNumbeDate() != null ? sdf
							.format(megerHouseInfo.getLandRightsNumbeDate())
							: null);
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo
					.setMainValue(houseInfo.getLandRightsNumbeDate() != null ? sdf
							.format(houseInfo.getLandRightsNumbeDate()) : null);
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("产权人类型");
		packageInfo.setEnName("propertyTypes.displayName");
		packageInfo.setMainPropertyName("propertyTypes");
		mainId = null;
		megerId = null;
		if (houseInfo.getPropertyTypes() != null
				&& houseInfo.getPropertyTypes().getId() != null) {
			mainId = houseInfo.getPropertyTypes().getId();
		}
		if (megerHouseInfo.getPropertyTypes() != null
				&& megerHouseInfo.getPropertyTypes().getId() != null) {
			megerId = megerHouseInfo.getPropertyTypes().getId();
		}
		if (!validateHouseInfo(mainId, megerId)) {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			if (megerId != null) {
				packageInfo.setMegerValue(getPropertyName(megerId));
				packageInfo.setMegerPropertyDictId(megerId);
			}

			packageHouseInfoDifrent.add(packageInfo);
		} else {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("产权人姓名");
		packageInfo.setEnName("name");
		if (!validateHouseInfo(houseInfo.getName(), megerHouseInfo.getName())) {
			packageInfo.setMainValue(houseInfo.getName() != null ? houseInfo
					.getName() : null);
			packageInfo
					.setMegerValue(megerHouseInfo.getName() != null ? megerHouseInfo
							.getName() : null);
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getName());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("证件类型");
		packageInfo.setEnName("certificateType.displayName");
		packageInfo.setMainPropertyName("certificateType");
		mainId = null;
		megerId = null;
		if (houseInfo.getCertificateType() != null
				&& houseInfo.getCertificateType().getId() != null) {
			mainId = houseInfo.getCertificateType().getId();
		}
		if (megerHouseInfo.getCertificateType() != null
				&& megerHouseInfo.getCertificateType().getId() != null) {
			megerId = megerHouseInfo.getCertificateType().getId();
		}
		if (!validateHouseInfo(mainId, megerId)) {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			if (megerId != null) {
				packageInfo.setMegerValue(getPropertyName(megerId));
				packageInfo.setMegerPropertyDictId(megerId);
			}

			packageHouseInfoDifrent.add(packageInfo);
		} else {
			if (mainId != null) {
				packageInfo.setMainValue(getPropertyName(mainId));
				packageInfo.setMainPropertyDictId(mainId);
			}
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("证件号码");
		packageInfo.setEnName("certificateNumbe");
		if (!validateHouseInfo(houseInfo.getCertificateNumbe(),
				megerHouseInfo.getCertificateNumbe())) {
			packageInfo
					.setMainValue(houseInfo.getCertificateNumbe() != null ? houseInfo
							.getCertificateNumbe

							() : null);
			packageInfo
					.setMegerValue(megerHouseInfo.getCertificateNumbe() != null ?

					megerHouseInfo.getCertificateNumbe()
							: null);
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getCertificateNumbe());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("产权人电话");
		packageInfo.setEnName("propertyPersonTel");
		if (!validateHouseInfo(houseInfo.getPropertyPersonTel(),
				megerHouseInfo.getPropertyPersonTel())) {
			packageInfo
					.setMainValue(houseInfo.getPropertyPersonTel() != null ? houseInfo
							.getPropertyPersonTel

							() : null);
			packageInfo
					.setMegerValue(megerHouseInfo.getPropertyPersonTel() != null ?

					megerHouseInfo.getPropertyPersonTel()
							: null);
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getPropertyPersonTel());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("联系手机");
		packageInfo.setEnName("propertyPersonMobile");
		if (!validateHouseInfo(houseInfo.getPropertyPersonMobile(),
				megerHouseInfo.getPropertyPersonMobile())) {
			packageInfo
					.setMainValue(houseInfo.getPropertyPersonMobile() != null ?

					houseInfo.getPropertyPersonMobile() : null);
			packageInfo
					.setMegerValue(megerHouseInfo.getPropertyPersonMobile() != null ?

					megerHouseInfo.getPropertyPersonMobile()
							: null);
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getPropertyPersonMobile());
			packageHouseInfo.add(packageInfo);
		}

		packageInfo = new PackageHouseInfo();
		packageInfo.setcName("备注");
		packageInfo.setEnName("remark");
		if (!validateHouseInfo(houseInfo.getRemark(),
				megerHouseInfo.getRemark())) {
			packageInfo.setMainValue(houseInfo.getRemark() != null ? houseInfo
					.getRemark() : null);
			packageInfo
					.setMegerValue(megerHouseInfo.getRemark() != null ? megerHouseInfo
							.getRemark() : null);
			packageHouseInfoDifrent.add(packageInfo);
		} else {
			packageInfo.setMainValue(houseInfo.getRemark());
			packageHouseInfo.add(packageInfo);
		}
		map.put("packageHouseInfo", packageHouseInfo);
		map.put("packageHouseInfoDifrent", packageHouseInfoDifrent);
		return map;
	}

	/**
	 * 根据属性字典ID查询名称
	 */
	private String getPropertyName(Long id) {
		if (properTypeDictService.getPropertyDictById(id) != null) {
			return properTypeDictService.getPropertyDictById(id)
					.getDisplayName();
		} else {
			return "";
		}
	}

	/**
	 * 删除实有房屋
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@PermissionFilter(ename = "deleteActualHouse")
	@Action(value = "deleteHouseInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteHouseInfo() {
		String[] deleteId = houseIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		houseInfoService.deleteHouseInfosByIdList(idList);
		return SUCCESS;
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

	/**
	 * 实有房屋信息查询
	 * 
	 * @return
	 */
	@Action(value = "searchHouseInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String searchHouseInfo() {
		if (null == orgId) {
			gridPage = new GridPage(new PageInfo<HouseInfo>());
			return SUCCESS;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		if (null == searchHouseInfoVo) {
			searchHouseInfoVo = new SearchHouseInfoVo();
		}
		searchHouseInfoVo.setOrgInternalCode(organization.getOrgInternalCode());
		PageInfo<HouseInfo> pageInfo = ControllerHelper
				.processAllOrgRelativeName(houseInfoService.searchHouseInfos(
						page, rows, sidx, sord, searchHouseInfoVo),
						organizationDubboService,
						new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 根据房屋编号查询实有房屋信息
	 * 
	 * @return
	 */
	@Action(value = "getHouseInfoByHouseCode", results = { @Result(name = "success", type = "json", params = {
			"root", "houseInfo", "ignoreHierarchy", "false" }) })
	public String getHouseInfoByHouseCode() {
		if (null != searchHouseInfoVo
				&& !StringUtils.isEmpty(searchHouseInfoVo.getHouseCode())) {
			houseInfo = this.houseInfoService.getHouseInfoByHouseCodeAndOrgId(
					searchHouseInfoVo.getHouseCode(), searchHouseInfoVo
							.getOrganization().getId());
		}
		return SUCCESS;
	}

	@Action(value = "findHouseInfosByHouseCodeAndOrgId", results = { @Result(name = "success", type = "json", params = {
			"root", "houseList", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findHouseInfosByHouseCodeAndOrgId() {
		houseList = houseInfoService
				.findHouseInfosByHouseCodeAndOrganizationId(houseInfo
						.getHouseCode(), houseInfo.getOrganization().getId());
		return SUCCESS;
	}

	@Action(value = "checkIsHasHouseByHouseCodeAndOrgId", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "false",
					"ignoreHierarchy", "false" }) })
	public String checkIsHasHouseByHouseCodeAndOrgId() {
		houseList = houseInfoService.checkIsHasHouseByHouseCodeAndOrgId(
				houseInfo.getHouseCode(), houseInfo.getOrganization().getId());
		if (houseList != null && houseList.size() != 0) {
			return SUCCESS;
		}
		return ERROR;
	}

	@Action(value = "findHouseInfosByHouseAddressAndOrgId", results = { @Result(name = "success", type = "json", params = {
			"root", "houseList", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findHouseInfosByHouseAddressAndOrgId() {
		if (houseInfo.getOrganization() != null) {
			houseList = houseInfoService
					.findHouseInfosByHouseAddressAndOrgId(houseInfo
							.getAddress(), houseInfo.getOrganization().getId());
			List<HouseInfo> houseInfoList = new ArrayList<HouseInfo>();
			for (HouseInfo houseInfos : houseList) {
				if (houseInfos.getIsRentalHouse() != null
						&& houseInfos.getIsRentalHouse()) {
					HouseInfo houseInfosTemp = houseInfos;
					houseInfos = rentalHouseService
							.getHouseInfoByHouseId(houseInfos.getId());
					if (houseInfos == null) {
						houseInfos = houseInfosTemp;
					} else {
						houseInfos.setOrganization(houseInfosTemp
								.getOrganization());
						houseInfos.setIsRentalHouse(houseInfosTemp
								.getIsRentalHouse());
					}
				}
				houseInfoList.add(houseInfos);
			}
			houseList = houseInfoList;
		}
		return SUCCESS;
	}

	@Action(value = "findHouseInfosByHouseAddressAndOrgIdForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "houseList", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findHouseInfosByHouseAddressAndOrgIdForMobile() {
		if (houseInfo.getOrganization() != null) {
			houseListForMobile = houseInfoService
					.findHouseInfosByHouseAddressAndOrgIdForMobile(houseInfo
							.getAddress(), houseInfo.getOrganization().getId());
			List<HouseInfo> houseInfoList = new ArrayList<HouseInfo>();
			for (HouseInfo houseInfos : houseListForMobile.getResult()) {
				if (houseInfos.getIsRentalHouse() != null
						&& houseInfos.getIsRentalHouse()) {
					HouseInfo houseInfosTemp = houseInfos;
					houseInfos = rentalHouseService
							.getHouseInfoByHouseId(houseInfos.getId());
					if (houseInfos == null) {
						houseInfos = houseInfosTemp;
					} else {
						houseInfos.setOrganization(houseInfosTemp
								.getOrganization());
						houseInfos.setIsRentalHouse(houseInfosTemp
								.getIsRentalHouse());
					}
				}
				houseInfoList.add(houseInfos);
			}
			houseList = houseInfoList;
		}
		return SUCCESS;
	}

	/**
	 * 分页查询实有房屋信息
	 * 
	 * @return
	 */
	@Action(value = "houseInfoList", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false",
			"excludeNullProperties", "true" }) })
	public String findHouseInfosForPage() {
		if (null == orgId) {
			gridPage = new GridPage(new PageInfo<HouseInfo>());
			return SUCCESS;
		}
		PageInfo<HouseInfo> pageInfo;
		if (null != houseId) {
			pageInfo = ControllerHelper.processAllOrgRelativeName(
					houseInfoService.findHouseInfosForPage(orgId, houseId,
							page, rows, sidx, sord), organizationDubboService,
					new String[] { "organization" }, orgId);

		} else {
			pageInfo = ControllerHelper.processAllOrgRelativeName(
					houseInfoService.findHouseInfosForPage(orgId, page, rows,
							sidx, sord), organizationDubboService,
					new String[] { "organization" }, orgId);
		}

		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 校验房屋编号是否存在
	 * 
	 * @return
	 */
	@Action(value = "hasDuplicateHouseInfo", results = { @Result(name = "success", type = "json", params = {
			"root", "hasDuplicateHouseInfo" }) })
	public String hasDuplicateHouseInfo() {
		if (null == orgId) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}

		hasDuplicateHouseInfo = houseInfoService.hasDuplicateHouseInfo(orgId,
				houseInfo.getHouseCode(), houseInfo.getId());
		return SUCCESS;
	}

	@Action(value = "hasDuplicateHouseInfoForAddress", results = { @Result(name = "success", type = "json", params = {
			"root", "hasDuplicateHouseInfo" }) })
	public String hasDuplicateHouseInfoForAddress() throws Exception{
		if (null == orgId) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		}
		// 这里使用 房屋地址 查询 不是 房屋标号
		hasDuplicateHouseInfo = houseInfoService
				.hasDuplicateHouseInfoForAddress(orgId, houseInfo.getAddress(),
						houseInfo.getId());

		return SUCCESS;
	}

	/**
	 * 导出实有房屋信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@PermissionFilter(ename = "downloadActualHouse")
	@Action(value = "downloadHouseInfo")
	public String downloadHouseInfo() throws Exception {
		if (null == orgId) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		if (null == searchHouseInfoVo) {
			searchHouseInfoVo = new SearchHouseInfoVo();
			searchHouseInfoVo.setOrgInternalCode(organizationDubboService
					.getSimpleOrgById(orgId).getOrgInternalCode());
		}
		List<HouseInfo> houseInfoList = getNeedExportData(page, orgId);
		List<RentalHouse> rentalHouses = parseToRentalHouse(houseInfoList);
		inputStream = ExcelExportHelper.exportDateToExcel(
				HouseContext.getHouseInfoPropertyArray(), rentalHouses);
		downloadFileName = new String("实有房屋信息".getBytes("gbk"), "ISO8859-1")
				+ ".xls";
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModelType.ACTUALHOUSE,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出部分"
								+ ModelType.ACTUALHOUSE, ObjectToJSON
								.convertJSON(new HouseInfo()));
		return STREAM_SUCCESS;
	}

	@PermissionFilter(ename = "downloadActualHouse")
	@Action(value = "downloadHouseInfoAll", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public void downloadExcelExportAll() {
		if (orgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		if (searchHouseInfoVo == null) {
			searchHouseInfoVo = new SearchHouseInfoVo();
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		searchHouseInfoVo.setOrgInternalCode(organization.getOrgInternalCode());
		if (!pageOnly) {
			pageOnly = true;
			Integer count = houseInfoService.getCount(searchHouseInfoVo);
			String[][] excelDefines = HouseContext.getHouseInfoPropertyArray();
			exportDataAll(count, excelDefines, "实有房屋信息");
		}
		systemLogService
				.log(com.vladium.logging.Logger.INFO,
						ModelType.ACTUALHOUSE,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												orgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId()) + "导出全部的"
								+ ModelType.ACTUALHOUSE, ObjectToJSON
								.convertJSON(new HouseInfo()));
	}

	@Action(value = "getActualHouseInfoByIdForMobile", results = { @Result(name = "success", type = "json", params = {
			"root", "map", "ignoreHierarchy", "false", "excludeNullProperties",
			"true" }) })
	public String getActualHouseInfoByIdForMobile() {
		map = new HashMap();
		houseInfo = houseInfoService.getHouseInfoById(houseInfo.getId());
		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(houseInfo
						.getOrganization().getId(), organizationDubboService));
		map.put("houseInfo", houseInfo);

		rentalHouse = this.rentalHouseService.getHouseInfoByHouseId(houseInfo
				.getId());
		if (null != rentalHouse) {
			rentalHouse.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(houseInfo
							.getOrganization().getId(),
							organizationDubboService));

			map.put("rentalHouse", rentalHouse);
		}
		return SUCCESS;
	}

	/***
	 * 查询获取住宅总数和人房关联总数
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "getHouseCountAndProportionCount", results = {
			@Result(name = "success", type = "json", params = { "root",
					"housePeopleProportion" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String getHouseCountAndProportionCount() throws Exception{
		if (orgId == null || !StringUtil.isStringAvaliable(houseType)) {
			errorMessage = "查询失败，未获得正确参数信息";
			return ERROR;
		}
		housePeopleProportion = houseInfoService.dealHousePeopleProportion(
				orgId, houseType);
		return SUCCESS;
	}

	@Action(value = "getActualHouseByIdForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "map",
					"ignoreHierarchy", "false", "excludeNullProperties", "true" }),
			@Result(name = "addcache", type = "json", params = { "root",
					"cacheId" }) })
	public String getActualHouseByIdForMobile() {
		map = new HashMap();
		houseInfo = houseInfoService.getHouseInfoByHouseCodeAndOrgId(
				houseInfo.getHouseCode(), houseInfo.getOrganization().getId());
		houseInfo.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(houseInfo
						.getOrganization().getId(), organizationDubboService));
		map.put("houseInfo", houseInfo);

		rentalHouse = this.rentalHouseService.getHouseInfoByHouseCodeAndOrgId(
				houseInfo.getHouseCode(), houseInfo.getOrganization().getId());
		if (null != rentalHouse) {
			rentalHouse.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(houseInfo
							.getOrganization().getId(),
							organizationDubboService));

			map.put("rentalHouse", rentalHouse);
		}

		return SUCCESS;
	}

	/** 初始导入mongodb只用一次 */
	@Action(value = "moveToMongodb")
	public String moveToMongodb() {
		houseInfoService.moveToMongodb();
		return SUCCESS;
	}

	/** 删除mongodb中有，oracle中被删除的数据，只用一次 */
	@Action(value = "deleteMongodb")
	public String deleteMongodb() {
		houseInfoService.deleteMongodb();
		return SUCCESS;
	}

	private List getNeedExportData(int page, Long orgId) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		if (organization != null) {
			searchHouseInfoVo.setOrganization(organization);
		}
		List<HouseInfo> houseInfoList = new ArrayList<HouseInfo>();
		if (pageOnly) {
			houseInfoList = houseInfoService.searchHouseInfos(page, rows, sidx,
					sord, searchHouseInfoVo).getResult();
		} else {
			houseInfoList = houseInfoService
					.searchAllHouseInfos(searchHouseInfoVo);
		}
		return houseInfoList;
	}

	@Override
	public List getNeedExportDatas(int page) {
		return parseToRentalHouse(getNeedExportData(page, orgId));
	}

	private List<RentalHouse> parseToRentalHouse(List<HouseInfo> houseInfoList) {
		List<RentalHouse> rentalHouses = new ArrayList<RentalHouse>();
		for (HouseInfo houseInfo : houseInfoList) {
			RentalHouse rentalHouse = new RentalHouse();
			if (null != houseInfo.getIsRentalHouse()
					&& houseInfo.getIsRentalHouse()) {
				// fateson update 使用房屋id 查询
				rentalHouse = rentalHouseService.getHouseInfoByHouseIdAndOrgId(
						houseInfo.getId(), houseInfo.getOrganization().getId());
				if (rentalHouse == null) {
					rentalHouse = new RentalHouse();
					PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
							rentalHouse, houseInfo);
					rentalHouse.setIsRentalHouse(false);
				} else {
					rentalHouse.setMemberNum(houseInfo.getMemberNum());
					rentalHouse.setIsRentalHouse(true);
				}
			} else {
				PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
						rentalHouse, houseInfo);
				rentalHouse.setIsRentalHouse(false);
			}
			rentalHouses.add(rentalHouse);
		}
		return rentalHouses;
	}

	public Boolean getHasDuplicateHouseInfo() {
		return hasDuplicateHouseInfo;
	}

	public void setHasDuplicateHouseInfo(Boolean hasDuplicateHouseInfo) {
		this.hasDuplicateHouseInfo = hasDuplicateHouseInfo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHouseIds() {
		return houseIds;
	}

	public void setHouseIds(String houseIds) {
		this.houseIds = houseIds;
	}

	public List<Long> getHouseInfoIds() {
		return houseInfoIds;
	}

	public void setHouseInfoIds(List<Long> houseInfoIds) {
		this.houseInfoIds = houseInfoIds;
	}

	public SearchHouseInfoVo getSearchHouseInfoVo() {
		return searchHouseInfoVo;
	}

	public void setSearchHouseInfoVo(SearchHouseInfoVo searchHouseInfoVo) {
		this.searchHouseInfoVo = searchHouseInfoVo;
	}

	public boolean isPageOnly() {
		return pageOnly;
	}

	public void setPageOnly(boolean pageOnly) {
		this.pageOnly = pageOnly;
	}

	public HouseInfo getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}

	public String getIsUseFrom() {
		return isUseFrom;
	}

	public void setIsUseFrom(String isUseFrom) {
		this.isUseFrom = isUseFrom;
	}

	public Boolean getHasActualPopulation() {
		return hasActualPopulation;
	}

	public void setHasActualPopulation(Boolean hasActualPopulation) {
		this.hasActualPopulation = hasActualPopulation;
	}

	public HouseHasActualPopulation getHouseHasActualPopulation() {
		return houseHasActualPopulation;
	}

	public void setHouseHasActualPopulation(
			HouseHasActualPopulation houseHasActualPopulation) {
		this.houseHasActualPopulation = houseHasActualPopulation;
	}

	public GisInfo getMinOption() {
		return minOption;
	}

	public void setMinOption(GisInfo minOption) {
		this.minOption = minOption;
	}

	public GisInfo getMaxOption() {
		return maxOption;
	}

	public void setMaxOption(GisInfo maxOption) {
		this.maxOption = maxOption;
	}

	public String getOldHouseCode() {
		return oldHouseCode;
	}

	public void setOldHouseCode(String oldHouseCode) {
		this.oldHouseCode = oldHouseCode;
	}

	public RentalHouse getRentalHouse() {
		return rentalHouse;
	}

	public void setRentalHouse(RentalHouse rentalHouse) {
		this.rentalHouse = rentalHouse;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List<HouseInfo> getHouseList() {
		return houseList;
	}

	public void setHouseList(List<HouseInfo> houseList) {
		this.houseList = houseList;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

	public Long getMegerId() {
		return megerId;
	}

	public void setMegerId(Long megerId) {
		this.megerId = megerId;
	}

	public void setMegerHouseInfo(HouseInfo megerHouseInfo) {
		this.megerHouseInfo = megerHouseInfo;
	}

	public List<PackageHouseInfo> getPackageHouseInfo() {
		return packageHouseInfo;
	}

	public void setPackageHouseInfo(List<PackageHouseInfo> packageHouseInfo) {
		this.packageHouseInfo = packageHouseInfo;
	}

	public Map<String, List<PackageHouseInfo>> getHouseInfoMap() {
		return houseInfoMap;
	}

	public void setHouseInfoMap(Map<String, List<PackageHouseInfo>> houseInfoMap) {
		this.houseInfoMap = houseInfoMap;
	}

	public Integer getUseRentalHouse() {
		return useRentalHouse;
	}

	public void setUseRentalHouse(Integer useRentalHouse) {
		this.useRentalHouse = useRentalHouse;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOperateSource() {
		return operateSource;
	}

	public void setOperateSource(String operateSource) {
		this.operateSource = operateSource;
	}

	public String getEncryptId() {
		return encryptId;
	}

	public void setEncryptId(String encryptId) {
		this.encryptId = encryptId;
	}

	public RentalHouse getRental() {
		return rental;
	}

	public void setRental(RentalHouse rental) {
		this.rental = rental;
	}

	public PageInfo<HouseInfo> getHouseListForMobile() {
		return houseListForMobile;
	}

	public void setHouseListForMobile(PageInfo<HouseInfo> houseListForMobile) {
		this.houseListForMobile = houseListForMobile;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getHousePeopleProportion() {
		return housePeopleProportion;
	}

	public void setHousePeopleProportion(String housePeopleProportion) {
		this.housePeopleProportion = housePeopleProportion;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

}
