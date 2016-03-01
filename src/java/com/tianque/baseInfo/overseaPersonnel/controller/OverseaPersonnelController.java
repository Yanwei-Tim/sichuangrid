package com.tianque.baseInfo.overseaPersonnel.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.familyInfo.service.GroupFamilyService;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.baseInfo.overseaPersonnel.service.OverseaPersonnelService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("overseaPersonnelController")
@Scope("prototype")
@Transactional
public class OverseaPersonnelController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(OverseaPersonnelController.class);
	@Autowired
	private OverseaPersonnelService overseaPersonnelService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private GroupFamilyService groupFamilyService;

	private OverseaPersonnel overseaPersonnel;
	private Long orgId;
	private Long organizationId;
	private String orgName;
	private long existedCount;
	private String modeType;
	private boolean flag;
	private String keyType = "overseaPersonnel";
	private String personTypeName = "境外人员";
	private String[] uuid;
	private String overseaPersonnelIds;
	private String deleteIds;
	private List<Long> noRelatePersonIds;
	private Long isHiddenPersonnelTrack; // 隐藏人员轨迹：0, 显示人员轨迹：1
	private Long houseType;
	private HouseInfo houseInfo;
	private RentalHouse rentalHouse;
	private String populationType;
	private OverseaPersonnel population;

	protected List<Long> populationIdList;
	private String populationIds;

	public Long getIsHiddenPersonnelTrack() {
		return isHiddenPersonnelTrack;
	}

	public void setIsHiddenPersonnelTrack(Long isHiddenPersonnelTrack) {
		this.isHiddenPersonnelTrack = isHiddenPersonnelTrack;
	}

	public String dispatch() throws Exception {
		overseaPersonnel = dispathBaseInfo(overseaPersonnel);
		return getRetunString();
	}

	@EncryptAnnotation
	public String dispatchByEncrypt() throws Exception {
		overseaPersonnel = dispathBaseInfo(overseaPersonnel);
		return getRetunString();
	}

	private OverseaPersonnel dispathBaseInfo(OverseaPersonnel population)
			throws Exception {
		if (null != id) {
			population = getPopulationFetchOrgById(id);
		} else if (null != population && null != population.getId()) {
			population = getPopulationFetchOrgById(population.getId());
		} else {
			population = new OverseaPersonnel();
			population.setOrganization(new Organization());
			population.getOrganization().setId(organizationId);
			population.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							organizationId, organizationDubboService));
		}
		return population;

	}

	private OverseaPersonnel getPopulationFetchOrgById(Long id) {
		OverseaPersonnel population = overseaPersonnelService
				.getOverseaPersonnelById(id);
		population.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(population
						.getOrganization().getId(), organizationDubboService));
		return population;
	}

	public String maintainOverseaPersonnelBaseInfo() throws Exception {
		if (null != overseaPersonnel && null != overseaPersonnel.getId()) {
			overseaPersonnel = overseaPersonnelService
					.updateOverseaPersonnel(overseaPersonnel);
		} else {
			overseaPersonnel = overseaPersonnelService
					.addOverseaPersonnel(overseaPersonnel);
		}
		return SUCCESS;
	}

	public String maintainOverseaPersonnel() throws Exception {
		if (null != overseaPersonnel && null != overseaPersonnel.getId()) {
			overseaPersonnel = overseaPersonnelService
					.updateOverseaPersonnel(overseaPersonnel);
		} else {
			overseaPersonnel = overseaPersonnelService
					.addOverseaPersonnel(overseaPersonnel);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "addOverseaPersonnel")
	public String addOverseaPersonnel() throws Exception {
		overseaPersonnel = overseaPersonnelService
				.addOverseaPersonnel(overseaPersonnel);
		overseaPersonnel
				.setOrganization(ControllerHelper.proccessRelativeOrgNameByOrg(
						overseaPersonnel.getOrganization(), organizationDubboService));
		return SUCCESS;
	}

	@PermissionFilter(ename = "addOverseaPersonnel")
	public String saveOverseaPersonnel() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			overseaPersonnel = overseaPersonnelService
					.addOverseaPersonnel(overseaPersonnel);
		}
		if (DialogMode.EDIT_MODE.equals(mode)) {
			overseaPersonnel = overseaPersonnelService
					.updateOverseaPersonnel(overseaPersonnel);
		}
		overseaPersonnel
				.setOrganization(ControllerHelper.proccessRelativeOrgNameByOrg(
						overseaPersonnel.getOrganization(), organizationDubboService));
		return SUCCESS;
	}

	public String shiftDispatch() throws Exception {
		if (orgId == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		overseaPersonnel = new OverseaPersonnel();
		overseaPersonnel.setOrganization(organizationDubboService
				.getSimpleOrgById(orgId));
		return SUCCESS;
	}

	public String shiftOverseaPersonnel() throws Exception {
		if (overseaPersonnelIds.equals(""))
			return ERROR;
		String[] id = overseaPersonnelIds.split(",");
		for (int i = 0; i < id.length; i++) {
			if (id[i].equals("")) {
				continue;
			}
			overseaPersonnel.setId(Long.valueOf(id[i]));
			overseaPersonnel.setOrgInternalCode(organizationDubboService
					.getSimpleOrgById(
							overseaPersonnel.getOrganization().getId())
					.getOrgInternalCode());
			overseaPersonnelService
					.updateOverseaPersonnelById(overseaPersonnel);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateOverseaPersonnel")
	public String updateOverseaPersonnel() throws Exception {

		overseaPersonnel = overseaPersonnelService
				.updateOverseaPersonnel(overseaPersonnel);
		overseaPersonnel
				.setOrganization(ControllerHelper.proccessRelativeOrgNameByOrg(
						overseaPersonnel.getOrganization(), organizationDubboService));
		return SUCCESS;
	}

	public String findOverseaPersonnelForListPage() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			if (null == overseaPersonnel) {
				overseaPersonnel = new OverseaPersonnel();
			}
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					overseaPersonnelService.findOverseaPersonnelForListPage(
							orgId, page, rows, sidx, sord,
							overseaPersonnel.getLogOut()), organizationDubboService,
					new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	public String updateOverseaPersonnelByIds() throws Exception {
		String[] updateId = overseaPersonnelIds.split(",");
		List<Long> idList;
		if (updateId[0].equals("")) {
			idList = initTargetId(updateId, 1);
		} else {
			idList = initTargetId(updateId, 0);
		}
		for (int i = 0; i < idList.size(); i++) {
			OverseaPersonnel oldOverseaPersonnel = overseaPersonnelService
					.getOverseaPersonnelById(idList.get(i));
			oldOverseaPersonnel.setLogOut(overseaPersonnel.getLogOut());
			if (overseaPersonnel.getLogOut() == 0L) {
				oldOverseaPersonnel.setIsHaveHouse(false);
				oldOverseaPersonnel.setRoom(null);
				oldOverseaPersonnel.setCurrentAddressType(null);
				oldOverseaPersonnel.setCurrentAddress(null);
				oldOverseaPersonnel.setCommunity(null);
				oldOverseaPersonnel.setUnit(null);
				oldOverseaPersonnel.setBlock(null);
				oldOverseaPersonnel.setHouseCode(null);
				oldOverseaPersonnel.setHouseId(null);
				oldOverseaPersonnel.setNoHouseReason("被取消注销，系统自动设置为无房");
				oldOverseaPersonnel.getLogoutDetail().setLogoutDate(null);
				oldOverseaPersonnel.getLogoutDetail().setLogoutReason("");
			}
			groupFamilyService
					.deleteGroupFamilyByPopulationIdAndPopulationTypeAndIdCardNo(
							idList.get(i), BaseInfoTables.OVERSEAPERSONNEL_KEY,
							null);
			overseaPersonnel = overseaPersonnelService
					.updateOverseaPersonnelById(oldOverseaPersonnel);
		}
		return SUCCESS;
	}

	public String updateEmphasiseById() throws Exception {
		populationIdList = overseaPersonnelService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.OVERSEAPERSONNEL_KEY,
						analyzePopulationIds());
		return SUCCESS;
	}

	/**
	 * ID加密 取消注销
	 * 
	 * @return
	 */
	@EncryptAnnotation
	public String updateEmphasiseByEncryptId() throws Exception {
		populationIdList = overseaPersonnelService
				.updateLogOutDetailAndCountByPopulationTypeAndIds(orgId,
						population.getLogoutDetail(),
						BaseInfoTables.OVERSEAPERSONNEL_KEY,
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

	public String hasDuplicateOverseaPersonnel() throws Exception {
		if (orgId == null) {
			return ERROR;
		} else {
			return overseaPersonnelService.hasDuplicateOverseaPersonnel(orgId,
					overseaPersonnel.getCertificateType().getId(),
					overseaPersonnel.getCertificateNo(),
					overseaPersonnel.getId()) ? SUCCESS : ERROR;
		}
		// return SUCCESS;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "deleteOverseaPerson")
	public String deleteOverseaPersonnel() throws Exception {
		String[] deleteId = deleteIds.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		noRelatePersonIds = overseaPersonnelService
				.deleteForOverseaPersonnelByIds(idList);
		return SUCCESS;

	}

	public String hasRelatePersons() throws Exception {
		if ("".equals(deleteIds)) {
			flag = false;
		} else {
			String[] deleteId = deleteIds.split(",");
			List<Long> idList;
			if (deleteId[0].equals("")) {
				idList = initTargetId(deleteId, 1);
			} else {
				idList = initTargetId(deleteId, 0);
			}
			flag = overseaPersonnelService.hasRelatePersons(idList);
		}
		return SUCCESS;
	}

	public String getOverseaPersonnelByIdCardNo() throws Exception {
		if (overseaPersonnel != null) {
			overseaPersonnel = overseaPersonnelService
					.getOverseaPersonnelByIdCardNo(overseaPersonnel
							.getIdCardNo(), overseaPersonnel.getOrganization()
							.getId());
		}
		return SUCCESS;
	}

	private PageInfo<OverseaPersonnel> emptyPage(int pageSize) {
		PageInfo<OverseaPersonnel> pageInfo = new PageInfo<OverseaPersonnel>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<OverseaPersonnel>());
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

	public String viewOverseaPopulation() throws Exception {
		if (null != overseaPersonnel && null != overseaPersonnel.getId()) {

			overseaPersonnel = overseaPersonnelService
					.getOverseaPersonnelById(overseaPersonnel.getId());
			orgId = overseaPersonnel.getOrganization().getId();
			orgName = ControllerHelper.getRelativeOrgNameByOrgId(orgId,
					organizationDubboService);
			overseaPersonnel.getOrganization().setOrgName(orgName);
		}
		return SUCCESS;
	}

	public String addOverseaPersonnelForMobile() throws Exception {
		overseaPersonnel = overseaPersonnelService
				.addOverseaPersonnel(overseaPersonnel);
		return SUCCESS;
	}

	public String updateOverseaPersonnelForMobile() throws Exception {
		overseaPersonnel = overseaPersonnelService
				.updateOverseaPersonnel(overseaPersonnel);
		return SUCCESS;
	}

	public String findOverseaPersonnelByIdCardNoAndOrgId() throws Exception {
		population = overseaPersonnelService
				.getOverseaPersonnelByIdCardNoAndOrgId(
						population.getIdCardNo(), orgId);

		return SUCCESS;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getModeType() {
		return modeType;
	}

	public Long getHouseType() {
		return houseType;
	}

	public void setHouseType(Long houseType) {
		this.houseType = houseType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public long getExistedCount() {
		return existedCount;
	}

	public void setExistedCount(long existedCount) {
		this.existedCount = existedCount;
	}

	public OverseaPersonnel getOverseaPersonnel() {
		return overseaPersonnel;
	}

	public void setOverseaPersonnel(OverseaPersonnel overseaPersonnel) {
		this.overseaPersonnel = overseaPersonnel;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public String getPersonTypeName() {
		return personTypeName;
	}

	public void setPersonTypeName(String personTypeName) {
		this.personTypeName = personTypeName;
	}

	public String[] getUuid() {
		return uuid;
	}

	public void setUuid(String[] uuid) {
		this.uuid = uuid;
	}

	public String getDeleteIds() {
		return deleteIds;
	}

	public String getOverseaPersonnelIds() {
		return overseaPersonnelIds;
	}

	public void setOverseaPersonnelIds(String overseaPersonnelIds) {
		this.overseaPersonnelIds = overseaPersonnelIds;
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

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public OverseaPersonnel getPopulation() {
		return population;
	}

	public void setPopulation(OverseaPersonnel population) {
		this.population = population;
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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	// fateson add 数据转移操作
	// current org id
	Long targetOrgId;
	private String currentClassName = "";
	private Organization organization = null;
	// need move peoples id
	String ids = "";

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getCurrentClassName() {
		return currentClassName;
	}

	public void setCurrentClassName(String currentClassName) {
		this.currentClassName = currentClassName;
	}

	public Long getTargetOrgId() {
		return targetOrgId;
	}

	public void setTargetOrgId(Long targetOrgId) {
		this.targetOrgId = targetOrgId;
	}

	public String shiftToDispatch() throws Exception {

		if (orgId == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(orgId);
		if (StringUtils.isBlank(currentClassName)) {
			errorMessage = "系统忙";
			logger.error("deal shiftDispatch occur Error，currentClassName is empty");
			return ERROR;
		}

		return SUCCESS;
	}

	String SEPARATOR = ".";

	/**
	 * 数据转移操作
	 * 
	 * @return
	 */

	public String shiftPerson() throws Exception {
		if (ids.equals("")) {
			errorMessage = "请选择要转移的数据";
			return ERROR;
		}

		if (orgId == 0 || targetOrgId == 0) {
			errorMessage = "请选择组织机构";
			return ERROR;
		}

		if (targetOrgId.equals(orgId)) {
			errorMessage = "请选择其他组织机构";
			return ERROR;
		}

		String[] moveids = ids.split(",");
		overseaPersonnelService.moveTempByIds(moveids, targetOrgId);
		return SUCCESS;
	}

}
