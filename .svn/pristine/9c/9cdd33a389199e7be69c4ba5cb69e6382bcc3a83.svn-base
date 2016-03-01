package com.tianque.baseInfo.actualHouse.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.controller.vo.HouseInfoForPopulationHelp;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseExternalService;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.context.service.BaseInfoContextService;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.baseInfo.overseaPersonnel.service.OverseaPersonnelService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseExternalService;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.baseInfo.unsettledPopulation.service.UnsettledPopulationService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.domain.HouseHasActualPopulation;
import com.tianque.domain.Organization;
import com.tianque.service.HouseHasActualPopulationService;
import com.tianque.service.HousePopulationApplyService;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.PropertyUtil;

@Controller("houseInfoForPopulationController")
@Scope("prototype")
@Transactional
@Namespace("/baseinfo/houseInfoForPopulation")
public class HouseInfoForPopulationController extends BaseAction {

	@Autowired
	private ActualHouseExternalService actualHouseExternalService;
	@Autowired
	private RentalHouseExternalService rentalHouseExternalService;
	@Autowired
	private HousePopulationApplyService housePopulationApplyService;
	@Autowired
	private BaseInfoContextService baseInfoContextService;
	@Autowired
	protected FloatingPopulationService floatingPopulationService;
	@Autowired
	private HouseholdStaffService householdStaffService;
	@Autowired
	protected UnsettledPopulationService unsettledPopulationService;
	@Autowired
	private OverseaPersonnelService overseaPersonnelService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;
	@Autowired
	private HousePopulationMaintanceService housePopulationMaintanceService;
	@Autowired
	private HouseHasActualPopulationService hasActualPopulationService;

	@Autowired
	private RentalHouseService rentalHouseService;

	@Autowired
	private ActualHouseService houseInfoService;

	private RentalHouse houseInfo;
	private ActualPopulation population;
	private OverseaPersonnel overseaPersonnel;
	private String populationType;
	private HouseInfo houseInfos;
	protected Long organizationId;
	private String dailogName;
	private Long houseInfosId;

	@Action(value = "dispathHouseInfoForPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/commonActualHouseForPopulation.jsp") })
	public String dispathHouseInfoForPopulation() throws Exception {
		if (null != id) {
			houseInfos = getHouseInfosByIdAndPopulationType();
			if (houseInfos != null) {
				if (houseInfos.getOrganization() == null
						|| houseInfos.getOrganization().getId() == null) {
					houseInfos.setOrganization(new Organization());
					houseInfos.getOrganization().setId(organizationId);
					houseInfos.getOrganization().setOrgName(
							ControllerHelper.getOrganizationRelativeName(
									organizationId, organizationDubboService));
				} else {
					houseInfos.getOrganization().setOrgName(
							ControllerHelper.getOrganizationRelativeName(
									houseInfos.getOrganization().getId(),
									organizationDubboService));
				}

				if (houseInfos.getIsRentalHouse() != null
						&& houseInfos.getIsRentalHouse()) {
					houseInfo = rentalHouseService
							.getHouseInfoByHouseId(houseInfos.getId());
					houseInfo.setOrganization(houseInfos.getOrganization());
				}
			}
		}
		return SUCCESS;
	}

	@Action(value = "maintainHouseInfoForPopulation", results = { @Result(name = "success", type = "json", params = {
			"root", "houseInfos" }) })
	public String maintainBaseInfo() {
		// 如果输入的房屋编号已经存在 则特殊处理
		if (null != houseInfos && null != houseInfos.getId()
				&& houseInfosId != null
				&& !houseInfosId.equals(houseInfos.getId())) {
			List<HouseHasActualPopulation> hasActualPopulations = hasActualPopulationService
					.getHouseHasActualPopulationByHouseId(houseInfosId);
			HouseHasActualPopulation hasActualPopulation = null;
			for (int i = 0; i < hasActualPopulations.size(); i++) {
				hasActualPopulation = hasActualPopulations.get(i);
				hasActualPopulationService
						.deleteHouseHasActualPopulationByPopulationTypeAndHouseInfosId(
								hasActualPopulation.getPopulationType(),
								houseInfosId,
								hasActualPopulation.getPopulationId());
			}
			hasActualPopulationService
					.deleteHousehasimportantpopulationByHouseId(houseInfosId);
		}
		Long houseId;
		if (null != houseInfos && null != houseInfos.getId()) {
			// 由于前台基本信息保存时必然建立人房关系，所以不论住房信息 新增、修改均走此方法
			houseId = houseInfos.getId();
			houseInfos.setHouseOperateSource(type);
			houseInfos = houseInfoService.updateHouseInfo(houseInfos);
			if (houseInfos.getIsRentalHouse() != null
					&& houseInfos.getIsRentalHouse()) {
				RentalHouse rentalHouse = rentalHouseService
						.getHouseInfoByHouseId(houseId);
				if (rentalHouse == null) {
					houseInfo.setHouseOperateSource(type);
					houseInfo.setHouseId(houseId);
					PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
							houseInfo, houseInfos, new String[] { "id",
									"houseOperateSource" });
					houseInfo = rentalHouseExternalService
							.addRentalHouseForPopulation(houseInfo);
				} else {
					houseInfo.setHouseOperateSource(type);
					houseInfo.setId(rentalHouse.getId());
					houseInfo.setHouseId(houseId);
					PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
							houseInfo, houseInfos, new String[] { "id",
									"houseOperateSource" });

					rentalHouseExternalService
							.updateRentalHouseForPopulation(houseInfo);
				}

			}else{
				RentalHouse rentalHouse = rentalHouseService
						.getHouseInfoByHouseId(houseId);
				if (rentalHouse != null) {
					List<Long> idList = new ArrayList<Long>();
					idList.add(rentalHouse.getId());
					// rentalHouseService.deleteRentalHousesByIdList(idList);
					rentalHouseService.deleteRentalHouseByIds(parseToLong(idList));
				}
			}

			// if (dailogName.equals("householdStaffPopulationDialog")) {
			// housePopulationMaintanceService.releaseHouse(
			// PopulationCatalog.HOUSEHOULD_POPULATION, id,
			// houseId);
			// housePopulationMaintanceService.bindHouse(
			// PopulationCatalog.HOUSEHOULD_POPULATION, id,
			// houseId);
			// } else if (dailogName.equals("floatingPopulationDialog")) {
			// housePopulationMaintanceService.releaseHouse(
			// PopulationCatalog.FLOATING_POPULATION, id, houseId);
			// housePopulationMaintanceService.bindHouse(
			// PopulationCatalog.FLOATING_POPULATION, id, houseId);
			// } else if (dailogName
			// .equals("unsettledPopulationMaintanceDialog")) {
			// housePopulationMaintanceService.releaseHouse(
			// PopulationCatalog.UNHOUSEHOULD_POPULATION, id,
			// houseId);
			// housePopulationMaintanceService.bindHouse(
			// PopulationCatalog.UNHOUSEHOULD_POPULATION, id,
			// houseId);
			// } else if
			// (dailogName.equals("overseaPersonnelMaintanceDialog")) {
			// housePopulationMaintanceService.releaseHouse(
			// PopulationCatalog.OVERSEA_POPULATION, id, houseId);
			// housePopulationMaintanceService.bindHouse(
			// PopulationCatalog.OVERSEA_POPULATION, id, houseId);
			// }
			if (isSettledPopulation(dailogName, id)) {
				return SUCCESS;
			}
			housePopulationMaintanceService.releaseHouse(
					HouseInfoForPopulationHelp.getCatalogs(dailogName), id,
					houseId);
			housePopulationMaintanceService.bindHouse(
					HouseInfoForPopulationHelp.getCatalogs(dailogName), id,
					houseId);
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

	public HouseInfo getHouseInfosByIdAndPopulationType() {
		if (null != id) {
			// if (dailogName.equals("householdStaffPopulationDialog")) {
			// houseInfos = actualHouseExternalService
			// .getHouseInfoById(managePopulationByHouseHelper
			// .getPopulationLivingHouseId(
			// PopulationCatalog.HOUSEHOULD_POPULATION,
			// id));
			// } else if (dailogName.equals("floatingPopulationDialog")) {
			// houseInfos = actualHouseExternalService
			// .getHouseInfoById(managePopulationByHouseHelper
			// .getPopulationLivingHouseId(
			// PopulationCatalog.FLOATING_POPULATION,
			// id));
			// } else if
			// (dailogName.equals("unsettledPopulationMaintanceDialog")) {
			// houseInfos = actualHouseExternalService
			// .getHouseInfoById(managePopulationByHouseHelper
			// .getPopulationLivingHouseId(
			// PopulationCatalog.UNHOUSEHOULD_POPULATION,
			// id));
			// } else if (dailogName.equals("overseaPersonnelMaintanceDialog"))
			// {
			// houseInfos = actualHouseExternalService
			// .getHouseInfoById(managePopulationByHouseHelper
			// .getPopulationLivingHouseId(
			// PopulationCatalog.OVERSEA_POPULATION,
			// id));
			// }
			// 在未落户人员落户时，房屋关联，有未落户人口转换为户籍人口
			if (isSettledPopulation(dailogName, id)) {
				houseInfos = actualHouseExternalService
						.getHouseInfoById(managePopulationByHouseHelper
								.getPopulationLivingHouseId(
										PopulationCatalog.HOUSEHOULD_POPULATION,
										id));
				return houseInfos;

			}
			houseInfos = actualHouseExternalService
					.getHouseInfoById(managePopulationByHouseHelper
							.getPopulationLivingHouseId(
									HouseInfoForPopulationHelp
											.getCatalogs(dailogName), id));

		}
		return houseInfos;
	}

	@Action(value = "maintainRentalHouseForPopulation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"cacheId", "ignoreHierarchy", "false" }),
			@Result(name = "unsettledPopulation", type = "redirectAction", params = {
					"namespace", "/baseinfo/unsettledPopulationManage",
					"actionName", "maintainUnsettledPopulation", "contextId",
					"${contextId}" }),
			@Result(name = "overseaStaff", type = "redirectAction", params = {
					"namespace", "/baseinfo/overseaPersonnelManage",
					"actionName", "maintainOverseaPersonnel", "contextId",
					"${contextId}" }) })
	public String maintainRentalHouseForPopulation() {
		if (null != baseInfoContextService.getHouseIdFromContext(contextId)) {
			actualHouseExternalService.updateHouseInfoForPopulation(houseInfo);
			if (null != houseInfo.getIsRentalHouse()
					&& houseInfo.getIsRentalHouse()) {
				processRentalHouse();
			} else {
				RentalHouse rentalHouse = rentalHouseExternalService
						.getHouseInfoByHouseCodeAndOrgId(houseInfo
								.getHouseCode(), houseInfo.getOrganization()
								.getId());
				if (null != rentalHouse) {
					List<Long> idList = new ArrayList<Long>();
					idList.add(rentalHouse.getId());
					rentalHouseExternalService.updateEmphasiseByIds(idList,
							Long.valueOf(1));
				}
			}
		} else {
			HouseInfo actualHouse = actualHouseExternalService
					.addHouseInfoForPopulation(houseInfo);
			this.procRentalHouseInfoForPopulation(houseInfo);
			baseInfoContextService.putHouseIdToContext(contextId,
					actualHouse.getId());
		}
		return processResultType();
	}

	private String processResultType() {
		if (null != populationType
				&& populationType.equals(PopulationType.UNSETTLED_POPULATION)) {
			return PopulationType.UNSETTLED_POPULATION;
		}
		if (null != populationType
				&& populationType.equals(PopulationType.OVERSEA_STAFF)) {
			return PopulationType.OVERSEA_STAFF;
		}
		return SUCCESS;
	}

	private void processRentalHouse() {
		RentalHouse rentalHouse = this.rentalHouseExternalService
				.getHouseInfoByHouseCodeAndOrgId(houseInfo.getHouseCode(),
						houseInfo.getOrganization().getId());
		if (null != rentalHouse) {
			houseInfo.setId(rentalHouse.getId());
			this.rentalHouseExternalService
					.updateRentalHouseForPopulation(houseInfo);
			List<Long> idList = new ArrayList<Long>();
			idList.add(rentalHouse.getId());
			rentalHouseExternalService.updateEmphasiseByIds(idList,
					Long.valueOf(0));
		} else {
			this.procRentalHouseInfoForPopulation(houseInfo);
		}
	}

	private void procRentalHouseInfoForPopulation(RentalHouse houseInfo) {
		if (null != houseInfo.getIsRentalHouse()
				&& houseInfo.getIsRentalHouse()) {
			rentalHouseExternalService.addRentalHouseForPopulation(houseInfo);
		}
	}

	@Action(value = "viewHouseInfoForFloatingPopulation", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/viewHouseInfo.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/viewHouseInfoPrint.jsp") })
	public String viewHouseInfoForFloatingPopulation() throws Exception {
		if (null != population && null != population.getId()) {
			population = floatingPopulationService
					.getFloatingPopulationById(population.getId());
			if (null != population.getIsHaveHouse()
					&& population.getIsHaveHouse()
					&& null != population.getHouseId()) {

			}
			houseInfo = getHouseInfoById(population.getHouseId());
		}
		if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "viewHouseInfoForHouseholdStaff", results = {
			@Result(name = "success", location = "/baseinfo/commonPopulation/viewHouseInfo.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/viewHouseInfoPrint.jsp") })
	public String viewHouseInfoForHouseholdStaff() throws Exception {
		if (null != population && null != population.getId()) {
			population = householdStaffService.getHouseholdStaffById(population
					.getId());
			if (null != population.getIsHaveHouse()
					&& population.getIsHaveHouse()
					&& null != population.getHouseId()) {
				houseInfo = getHouseInfoById(population.getHouseId());
			}
			if (DialogMode.PRINT_MODE.equals(mode)) {
				return "print";
			}
		}
		return SUCCESS;
	}

	@Action(value = "viewHouseInfoForUnsettledPopulation", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewHouseInfo.jsp") })
	public String viewHouseInfoForUnsettledPopulation() throws Exception {
		if (null != population && null != population.getId()) {
			population = unsettledPopulationService
					.getUnsettledPopulationById(population.getId());
			if (null != population.getIsHaveHouse()
					&& population.getIsHaveHouse()
					&& null != population.getHouseId()) {

			}
			houseInfo = getHouseInfoById(population.getHouseId());
		}
		return SUCCESS;
	}

	@Action(value = "viewHouseInfoForOverseaPersonnel", results = { @Result(name = "success", location = "/baseinfo/overseaPersonnel/viewHouseInfo.jsp") })
	public String viewHouseInfoForOverseaPersonnel() throws Exception {
		if (null != population && null != population.getId()) {
			overseaPersonnel = overseaPersonnelService
					.getOverseaPersonnelById(population.getId());
			/*
			 * if (null != overseaPersonnel.getIsHaveHouse() &&
			 * overseaPersonnel.getIsHaveHouse() && null !=
			 * overseaPersonnel.getHouseId()) { }
			 */
			houseInfo = getHouseInfoById(overseaPersonnel.getHouseId());
		}
		return SUCCESS;
	}

	@Action(value = "getHouseInfoForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"houseInfo", "ignoreHierarchy", "false",
					"excludeNullProperties", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getHouseInfoForMobile() {
		houseInfo = getHouseInfoById(population.getHouseId());
		return SUCCESS;
	}

	@Action(value = "viewHouseInfoForImportantPersonnel", results = { @Result(name = "success", location = "/baseinfo/commonPopulation/viewHouseInfo.jsp") })
	public String viewHouseInfoForImportantPersonnel() throws Exception {
		if (null != population && null != population.getHouseId()) {
			houseInfo = getHouseInfoById(population.getHouseId());
		}
		return SUCCESS;
	}

	private RentalHouse getHouseInfoById(Long houseId) {
		HouseInfo savedHouseInfo = null;
		if (null != houseId) {
			savedHouseInfo = actualHouseExternalService
					.getHouseInfoById(houseId);
		}
		if (null != savedHouseInfo) {
			RentalHouse houseInfo = rentalHouseExternalService
					.getHouseInfoByHouseId(savedHouseInfo.getId(),
							IsEmphasis.Emphasis);
			if (null != houseInfo) {
				houseInfo.setIsRentalHouse(true);
				houseInfo.setId(savedHouseInfo.getId());
			} else {
				houseInfo = new RentalHouse();
				BeanUtils.copyProperties(savedHouseInfo, houseInfo);
				houseInfo.setIsRentalHouse(false);
			}
			houseInfo.setPersonNum(housePopulationApplyService
					.countActualPopulationByHouseId(houseId, savedHouseInfo
							.getOrganization().getId()));
			houseInfo.setOrganization(ControllerHelper
					.proccessRelativeOrgNameByOrg(houseInfo.getOrganization(),
							organizationDubboService));
			return houseInfo;
		}
		return null;
	}

	/**
	 * 判断是否在做落户操作，根据populationId在新增时，判断此时在未落人口中是否存在 不存在时说明正在进行落户操作
	 * */
	private boolean isSettledPopulation(String addDailogName, Long populationId) {
		if (HouseInfoForPopulationHelp.UNSETTLEDPOPULATIONMAINTANCEDIALOG
				.equals(addDailogName)) {
			UnsettledPopulation temp = unsettledPopulationService
					.getUnsettledPopulationById(populationId);
			if (temp == null) {
				return true;
			}
		}
		return false;
	}

	public RentalHouse getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(RentalHouse houseInfo) {
		this.houseInfo = houseInfo;
	}

	public ActualPopulation getPopulation() {
		return population;
	}

	public void setPopulation(ActualPopulation population) {
		this.population = population;
	}

	public OverseaPersonnel getOverseaPersonnel() {
		return overseaPersonnel;
	}

	public void setOverseaPersonnel(OverseaPersonnel overseaPersonnel) {
		this.overseaPersonnel = overseaPersonnel;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public HouseInfo getHouseInfos() {
		return houseInfos;
	}

	public void setHouseInfos(HouseInfo houseInfos) {
		this.houseInfos = houseInfos;
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

	public Long getHouseInfosId() {
		return houseInfosId;
	}

	public void setHouseInfosId(Long houseInfosId) {
		this.houseInfosId = houseInfosId;
	}

	// public String getHouseOpertyType() {
	// return houseOpertyType;
	// }
	//
	// public void setHouseOpertyType(String houseOpertyType) {
	// this.houseOpertyType = houseOpertyType;
	// }

}
