package com.tianque.baseInfo.unsettledPopulation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.baseInfo.unsettledPopulation.service.UnsettledPopulationService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.service.ActualPopulationMutexService;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("unsettledPopulationController")
@Scope("prototype")
@Transactional
public class UnsettledPopulationController extends BaseAction {

	private UnsettledPopulation unsettledPopulation;
	private Organization ownerOrg;
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	private String keyType = "unsettledPopulation";
	@Autowired
	protected UnsettledPopulationService unsettledPopulationService;
	@Autowired
	private ActualPopulationMutexService actualPopulationMutexService;
	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;
	@Autowired
	private BaseInfoService baseInfoService;

	private String unsettledPopulationIds;
	private Long orgId;
	private Long death;
	private Long logOut;
	private String populationIds;

	private RentalHouse houseInfo;
	private UnsettledPopulation population;
	private Boolean hasDuplicateUnsettledPopulation;
	private long existedCount;
	private List<UnsettledPopulation> populationList;
	private String populationType;
	protected List<Long> populationIdList;
	private String dailogName;

	public String dispatch() throws Exception {
		unsettledPopulation = dispathBaseInfo(unsettledPopulation);
		return getRetunString();
	}

	@EncryptAnnotation
	public String dispatchByEncrypt() throws Exception {
		unsettledPopulation = dispathBaseInfo(unsettledPopulation);
		return getRetunString();
	}

	private UnsettledPopulation dispathBaseInfo(UnsettledPopulation population) {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		} else if (null != population && null != population.getId()) {
			population = getPopulationFetchOrgById(population.getId());
		} else {
			population = new UnsettledPopulation();
			population.setOrganization(new Organization());
			population.getOrganization().setId(orgId);
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(orgId,
							organizationDubboService));
		}
		return population;
	}

	private UnsettledPopulation getPopulationFetchOrgById(Long id) {
		UnsettledPopulation population = unsettledPopulationService
				.getUnsettledPopulationById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	public String maintainUnsettledPopulationBaseInfo() throws Exception {
		if (null != unsettledPopulation && null != unsettledPopulation.getId()) {
			unsettledPopulation = unsettledPopulationService
					.updateUnsettledPopulation(unsettledPopulation);
		} else {
			unsettledPopulation = unsettledPopulationService
					.addUnsettledPopulation(unsettledPopulation);
		}
		return SUCCESS;
	}

	public String maintainUnsettledPopulation() throws Exception {
		if (null != unsettledPopulation && null != unsettledPopulation.getId()) {
			unsettledPopulation = unsettledPopulationService
					.updateUnsettledPopulation(unsettledPopulation);
		} else {
			unsettledPopulation = unsettledPopulationService
					.addUnsettledPopulation(unsettledPopulation);
		}
		return SUCCESS;
	}

	public String viewUnsettledPopulations() throws Exception {
		if (unsettledPopulation == null || unsettledPopulation.getId() == null) {
			existedCount = 0;
		} else {
			unsettledPopulation = unsettledPopulationService
					.getUnsettledPopulationById(unsettledPopulation.getId());
			unsettledPopulation.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							unsettledPopulation.getOrganization().getId(),
							organizationDubboService));
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "addUnsettledPopulation")
	public String addUnsettledPopulation() throws Exception {
		unsettledPopulation = unsettledPopulationService
				.addUnsettledPopulation(unsettledPopulation);
		unsettledPopulation.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(
						unsettledPopulation.getOrganization(),
						organizationDubboService));
		return SUCCESS;
	}

	public String findUnsettledPopulations() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(new PageInfo<UnsettledPopulation>());
			return SUCCESS;
		} else {
			PageInfo<UnsettledPopulation> pageInfo = ControllerHelper
					.processAllOrgRelativeName(unsettledPopulationService
							.findUnsettledPopulationsForPageByOrgId(logOut,
									death, orgId, page, rows, sidx, sord),
							organizationDubboService,
							new String[] { "organization" }, orgId);
			gridPage = new GridPage(pageInfo);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateUnsettledPopulation")
	public String updateUnsettledPopulation() throws Exception {
		unsettledPopulation.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(
						unsettledPopulation.getOrganization(),
						organizationDubboService));
		unsettledPopulation = unsettledPopulationService
				.updateUnsettledPopulation(unsettledPopulation);
		return SUCCESS;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "deleteUnsettledPopulation")
	public String deleteUnsettledPopulation() throws Exception {
		String[] deleteId = unsettledPopulationIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		unsettledPopulationService.deleteUnsettledPopulationsByIdList(idList);
		return SUCCESS;
	}

	public String updateUnsettledPopulationById() throws Exception {
		UnsettledPopulation oldunsettledPopulation = unsettledPopulationService
				.getUnsettledPopulationById(unsettledPopulation.getId());
		unsettledPopulation = unsettledPopulationService
				.updateUnsettledPopulationById(oldunsettledPopulation);
		return SUCCESS;
	}

	public String hasDuplicateUnsettledPopulation() throws Exception {
		if (ownerOrg == null || ownerOrg.getId() == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		} else {
			hasDuplicateUnsettledPopulation = actualPopulationMutexService
					.isActualPopuationByOrgIdAndIdCardNoAndExcludeActualPopulationType(
							unsettledPopulation.getId(), ownerOrg.getId(),
							unsettledPopulation.getIdCardNo(),
							PopulationType.UNSETTLED_POPULATION);
			if (hasDuplicateUnsettledPopulation != true) {
				hasDuplicateUnsettledPopulation = unsettledPopulationService
						.hasDuplicateUnsettledPopulation(ownerOrg.getId(),
								unsettledPopulation.getIdCardNo(),
								unsettledPopulation.getId());
			}
			return SUCCESS;
		}
	}

	public String getUnsettledPopulationByIdCardNo() throws Exception {
		unsettledPopulation = unsettledPopulationService
				.getUnsettledPopulationByIdCardNo(unsettledPopulation
						.getIdCardNo(), unsettledPopulation.getOrganization()
						.getId());
		if (unsettledPopulation != null
				&& unsettledPopulation.getIsHaveHouse() != null
				&& unsettledPopulation.getIsHaveHouse()) {
			unsettledPopulation.setHouseId(managePopulationByHouseHelper
					.getPopulationLivingHouseId(
							PopulationCatalog.UNHOUSEHOULD_POPULATION,
							unsettledPopulation.getId()));
		}
		if (unsettledPopulation != null
				&& unsettledPopulation.getIdCardNo() != null) {
			Countrymen temp = baseInfoService.existBaseInfo(unsettledPopulation
					.getIdCardNo());
			if (temp != null) {
				unsettledPopulation.setBaseInfoId(temp.getId());
			}
		}
		return SUCCESS;
	}

	public String updateLogOutOfUnsettledPopulation() throws Exception {
		String[] updateId = unsettledPopulationIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		populationList = unsettledPopulationService
				.updateLogOutOfUnsettledPopulationByIdList(idList, logOut);
		return SUCCESS;
	}

	public String updateEmphasiseById() throws Exception {
		populationIdList = unsettledPopulationService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.UNSETTEDPOPULATION_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	/**
	 * ID加密，取消注销
	 * 
	 * @return
	 */
	@EncryptAnnotation
	public String updateEmphasiseByEncryptId() throws Exception {
		populationIdList = unsettledPopulationService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.UNSETTEDPOPULATION_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	private Long[] analyzePopulationIds() {
		String[] deleteId = populationIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		return ids;
	}

	public String updateDeathOfUnsettledPopulation() throws Exception {
		String[] updateId = unsettledPopulationIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		populationList = unsettledPopulationService
				.updateDeathOfUnsettledPopulationByIdList(idList,
						unsettledPopulation.isDeath());
		return SUCCESS;
	}

	/**
	 * id加密取消死亡
	 * 
	 * @return
	 */
	@EncryptAnnotation
	public String updateDeathOfUnsettledPopulationByEncryptId()
			throws Exception {
		String[] updateId = unsettledPopulationIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		populationList = unsettledPopulationService
				.updateDeathOfUnsettledPopulationByIdList(idList,
						unsettledPopulation.isDeath());
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

	public String updateEmphasiseUnsettledPopulationByIdCardNo()
			throws Exception {
		unsettledPopulation = unsettledPopulationService
				.getUnsettledPopulationByIdCardNo(unsettledPopulation
						.getIdCardNo(), unsettledPopulation.getOrganization()
						.getId());
		Long[] ids = { unsettledPopulation.getId() };
		populationIdList = unsettledPopulationService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.UNSETTEDPOPULATION_KEY, ids);
		return SUCCESS;
	}

	public String addUnsettledPopulationForMobile() throws Exception {
		unsettledPopulation = unsettledPopulationService
				.addUnsettledPopulation(unsettledPopulation);
		return SUCCESS;
	}

	public String updateUnsettledPopulationForMobile() throws Exception {
		unsettledPopulation = unsettledPopulationService
				.updateUnsettledPopulation(unsettledPopulation);
		return SUCCESS;
	}

	public String findUnsettledPopulationByIdCardNoAndOrgId() throws Exception {
		population = unsettledPopulationService
				.getUnsettledPopulationByIdCardNoAndOrgId(
						population.getIdCardNo(), orgId);
		return SUCCESS;
	}

	public UnsettledPopulation getUnsettledPopulation() {
		return unsettledPopulation;
	}

	public void setUnsettledPopulation(UnsettledPopulation unsettledPopulation) {
		this.unsettledPopulation = unsettledPopulation;
	}

	public String getUnsettledPopulationIds() {
		return unsettledPopulationIds;
	}

	public void setUnsettledPopulationIds(String unsettledPopulationIds) {
		this.unsettledPopulationIds = unsettledPopulationIds;
	}

	public Long getOrganizationId() {
		return orgId;
	}

	public void setOrganizationId(Long organizationId) {
		this.orgId = organizationId;
	}

	public Organization getOwnerOrg() {
		return ownerOrg;
	}

	public void setOwnerOrg(Organization ownerOrg) {
		this.ownerOrg = ownerOrg;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public Long getDeath() {
		return death;
	}

	public void setDeath(Long death) {
		this.death = death;
	}

	public Boolean getHasDuplicateUnsettledPopulation() {
		return hasDuplicateUnsettledPopulation;
	}

	public void setHasDuplicateUnsettledPopulation(
			Boolean hasDuplicateUnsettledPopulation) {
		this.hasDuplicateUnsettledPopulation = hasDuplicateUnsettledPopulation;
	}

	public long getExistedCount() {
		return existedCount;
	}

	public void setExistedCount(long existedCount) {
		this.existedCount = existedCount;
	}

	public Long getLogOut() {
		return logOut;
	}

	public void setLogOut(Long logOut) {
		this.logOut = logOut;
	}

	public List<UnsettledPopulation> getPopulationList() {
		return populationList;
	}

	public void setPopulationList(List<UnsettledPopulation> populationList) {
		this.populationList = populationList;
	}

	public RentalHouse getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(RentalHouse houseInfo) {
		this.houseInfo = houseInfo;
	}

	public UnsettledPopulation getPopulation() {
		return population;
	}

	public void setPopulation(UnsettledPopulation population) {
		this.population = population;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public List<Long> getPopulationIdList() {
		return populationIdList;
	}

	public void setPopulationIdList(List<Long> populationIdList) {
		this.populationIdList = populationIdList;
	}

	public String getPopulationIds() {
		return populationIds;
	}

	public void setPopulationIds(String populationIds) {
		this.populationIds = populationIds;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

}
