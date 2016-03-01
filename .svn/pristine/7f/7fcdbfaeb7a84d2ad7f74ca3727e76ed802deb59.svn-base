package com.tianque.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.FireSafetyEnterprise;
import com.tianque.domain.Organization;
import com.tianque.domain.PersonInCharges;
import com.tianque.domain.PropertyDict;
import com.tianque.service.FireSafetyEnterpriseService;
import com.tianque.service.PersonInChargesService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.validate.impl.FireSafetyEnterpriseValidateImpl;

@Namespace("/baseinfo/fireSafetyEnterpriseManage")
@Controller("fireSafetyEnterpriseController")
@SuppressWarnings("serial")
@Scope("prototype")
@Transactional
public class FireSafetyEnterpriseController extends BaseAction {
	private FireSafetyEnterprise enterprise;
	private Organization ownerOrg;
	private List<PropertyDict> comprehensiveManagementPosts;
	private int existedCount;
	private Long orgId;
	private String modeType;
	private boolean bol;
	private String keyType;// 重点类型
	@Autowired
	private FireSafetyEnterpriseService fireSafetyEnterpriseService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PersonInChargesService personInChargesService;
	private FireSafetyEnterpriseValidateImpl fireSafetyEnterPriseValidateImpl;
	@Autowired
	private ValidateHelper validateHelper;
	private String placeTypeName;
	private String[] uuid;
	private String enterPriseIds;
	private FireSafetyEnterprise location;
	private String locationIds;
	private String supervisorName; // 治安负责员名称
	private String locationType; // 场所类型名称
	private String superviceRecordName; // 巡场情况

	/**
	 * 解密调用
	 * 
	 * @return
	 */
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() throws Exception {
		dispatchOperateMethod();
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			enterprise = fireSafetyEnterpriseService
					.getFireSafetyEnterpriseById(location.getId());
			ownerOrg = enterprise.getOrganization();

			return "view";// 查看
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			enterprise = fireSafetyEnterpriseService
					.getFireSafetyEnterpriseById(location.getId());
			ownerOrg = enterprise.getOrganization();
			return "print";
		}
		return SUCCESS;
	}

	public String dispatchOperate() throws Exception {
		dispatchOperateMethod();
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			enterprise = fireSafetyEnterpriseService
					.getFireSafetyEnterpriseById(location.getId());
			ownerOrg = enterprise.getOrganization();
			return "view";// 查看
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			return "search";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			enterprise = fireSafetyEnterpriseService
					.getFireSafetyEnterpriseById(location.getId());
			ownerOrg = enterprise.getOrganization();
			return "print";
		}
		// comprehensiveManagementPosts = propertyDictService
		// .findPropertyDictByDomainName("综治岗位");
		return SUCCESS;
	}

	private void dispatchOperateMethod() throws Exception {
		if (keyType.equals("enterpriseKey")) {
			placeTypeName = "规上企业";
		} else if (keyType.equals("protectionKey")) {
			placeTypeName = "重点保障";
		} else if (keyType.equals("safetyProductionKey")) {
			placeTypeName = "安全生产重点";
		} else if (keyType.equals("fireSafetyKey")) {
			placeTypeName = "消防安全重点";
		} else if (keyType.equals("securityKey")) {
			placeTypeName = "治安重点";
		}
		if (null != id) {
			enterprise = fireSafetyEnterpriseService
					.getFireSafetyEnterpriseById(id);
			ownerOrg = enterprise.getOrganization();
		} else {
			if (ownerOrg == null) {
				ownerOrg = new Organization();
				ownerOrg.setId(orgId);
			}
			enterprise = new FireSafetyEnterprise();
			enterprise.setKeyType(keyType);
		}
	}

	@PermissionFilter(ename = "addFireSafetyEnterprise")
	public String addFireSafetyEnterprise() throws Exception {
		enterprise.setIsEmphasis(false);// 现在关注
		enterprise.setOrganization(ownerOrg);
		fireSafetyEnterPriseValidateImpl = new FireSafetyEnterpriseValidateImpl();
		fireSafetyEnterPriseValidateImpl
				.setOrganizationDubboService(organizationDubboService);
		fireSafetyEnterPriseValidateImpl.setValidateHelper(validateHelper);
		ValidateResult enterPriseValidator = ((DomainValidator<FireSafetyEnterprise>) fireSafetyEnterPriseValidateImpl)
				.validate(enterprise);
		if (enterPriseValidator.hasError()) {
			return ERROR;
		}
		enterprise = fireSafetyEnterpriseService
				.addFireSafetyEnterprise(enterprise);
		enterprise.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(enterprise.getOrganization(),
						organizationDubboService));
		if (uuid != null) {
			addPersonInCharges(enterprise);
		}
		return SUCCESS;
	}

	/**
	 * 验证场所名称唯一性
	 */
	public String hasDuplicateFireSafetyEnterpriseNameAndKeyType()
			throws Exception {
		if (ownerOrg == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		bol = fireSafetyEnterpriseService
				.hasDuplicateFireSafetyEnterpriseNameAndKeyType(
						ownerOrg.getId(), enterprise.getName(),
						enterprise.getId(), enterprise.getKeyType());
		return SUCCESS;
	}

	public String shiftDispatch() throws Exception {
		if (orgId == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		ownerOrg = organizationDubboService.getSimpleOrgById(orgId);
		return SUCCESS;
	}

	public String shiftFireSafetyEnterprise() throws Exception {
		String[] id = enterPriseIds.split(",");
		Long[] ids = new Long[id.length];
		for (int i = 0; i < id.length; i++) {
			if (id[i].equals("")) {
				continue;
			}
			ids[i] = Long.parseLong(id[i]);
		}
		fireSafetyEnterpriseService.shiftFireSafetyEnterprise(ids, enterprise
				.getOrganization().getId(), keyType);
		return SUCCESS;
	}

	@PermissionFilter(ename = "addFireSafetyKey")
	public String addFireSafetyEnterpriseAndPersonInCharges() throws Exception {
		enterprise.setOrganization(ownerOrg);
		fireSafetyEnterPriseValidateImpl = new FireSafetyEnterpriseValidateImpl();
		fireSafetyEnterPriseValidateImpl
				.setOrganizationDubboService(organizationDubboService);
		fireSafetyEnterPriseValidateImpl.setValidateHelper(validateHelper);
		ValidateResult enterPriseValidator = ((DomainValidator<FireSafetyEnterprise>) fireSafetyEnterPriseValidateImpl)
				.validate(enterprise);
		if (enterPriseValidator.hasError()) {
			return ERROR;
		}
		enterprise = fireSafetyEnterpriseService
				.addFireSafetyEnterprise(enterprise);
		enterprise.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(enterprise.getOrganization(),
						organizationDubboService));
		return SUCCESS;
	}

	private String addPersonInCharges(FireSafetyEnterprise enterprise)
			throws Exception {
		for (int i = 0; i < uuid.length; i++) {
			PersonInCharges personInCharges = (PersonInCharges) cacheService
					.get(uuid[i]);
			personInCharges.setPlaceType(enterprise.getKeyType());
			personInCharges.setPlaceId(enterprise.getId());
			personInChargesService.addPersonInCharges(personInCharges);

		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "fireSafetyKeyManagement")
	public String findFireSafetyEnterprisesForListPage() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(getRows()));
			return SUCCESS;
		} else {
			if (!StringUtil.isStringAvaliable(keyType)) {
				this.errorMessage = "参数错误";
				return ERROR;
			}
			gridPage = new GridPage(
					ControllerHelper.processAllOrgRelativeName(
							fireSafetyEnterpriseService
									.findFireSafetyEnterprisesForListPageByOrganizationIdAndKeyType(
											orgId, this.keyType, rows, page,
											sidx, sord,
											location.getIsEmphasis() ? 1L : 0L),
							organizationDubboService,
							new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	public String updateFireSafetyEnterprise() throws Exception {
		enterprise.setOrganization(ownerOrg);
		fireSafetyEnterPriseValidateImpl = new FireSafetyEnterpriseValidateImpl();
		fireSafetyEnterPriseValidateImpl
				.setOrganizationDubboService(organizationDubboService);
		fireSafetyEnterPriseValidateImpl.setValidateHelper(validateHelper);
		ValidateResult enterPriseValidator = ((DomainValidator<FireSafetyEnterprise>) fireSafetyEnterPriseValidateImpl)
				.validate(enterprise);
		if (enterPriseValidator.hasError()) {
			return ERROR;
		}
		enterprise = fireSafetyEnterpriseService
				.updateFireSafetyEnterprise(enterprise);
		enterprise.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(enterprise.getOrganization(),
						organizationDubboService));
		return SUCCESS;
	}

	public String updateEmphasis() throws Exception {
		FireSafetyEnterprise oldEnterprise = fireSafetyEnterpriseService
				.getFireSafetyEnterpriseById(enterprise.getId());
		oldEnterprise.setIsEmphasis(enterprise.getIsEmphasis());
		enterprise = fireSafetyEnterpriseService.updateEmphasis(oldEnterprise);
		return SUCCESS;
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		fireSafetyEnterpriseService.updateEmphasiseByIds(analyzeLocationIds(),
				location);
		return SUCCESS;
	}

	private Long[] analyzeLocationIds() {
		String[] deleteIds = locationIds.split(",");
		List<Long> idList;
		if (deleteIds[0].equals("")) {
			idList = initTargetId(deleteIds, 1);
		} else {
			idList = initTargetId(deleteIds, 0);
		}
		Long[] ids = new Long[idList.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idList.get(i);
		}
		return ids;
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

	@PermissionFilter(ename = "deleteFireSafetyKey")
	public String deleteFireSafetyEnterprise() throws Exception {
		if (enterprise == null || enterprise.getId() == null) {
			bol = false;
		} else {
			bol = fireSafetyEnterpriseService
					.deleteFireSafetyEnterpriseById(enterprise.getId());
		}
		return SUCCESS;
	}

	private PageInfo<FireSafetyEnterprise> emptyPage(int pageSize) {
		PageInfo<FireSafetyEnterprise> pageInfo = new PageInfo<FireSafetyEnterprise>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<FireSafetyEnterprise>());
		return pageInfo;
	}

	public String hasDuplicateFireSafetyEnterpriseName() throws Exception {
		if (enterprise == null || enterprise.getOrganization() == null
				|| enterprise.getOrganization().getId() == null) {
			return ERROR;
		} else {
			return fireSafetyEnterpriseService
					.hasDuplicateFireSafetyEnterpriseNameAndKeyType(enterprise
							.getOrganization().getId(), enterprise.getName(),
							enterprise.getId(), enterprise.getKeyType()) ? ERROR
					: SUCCESS;
		}
	}

	/**
	 * 查看
	 * 
	 * @return SUCCESS
	 */
	@EncryptAnnotation
	public String viewFireSafetyEnterprise() throws Exception {
		if (enterprise != null && enterprise.getId() != null) {
			enterprise = fireSafetyEnterpriseService
					.getFireSafetyEnterpriseById(enterprise.getId());
			enterprise.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(enterprise
							.getOrganization().getId(), organizationDubboService));
		}
		return SUCCESS;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public String getModeType() {
		return modeType;
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

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public Organization getOwnerOrg() {
		return ownerOrg;
	}

	public void setOwnerOrg(Organization ownerOrg) {
		this.ownerOrg = ownerOrg;
	}

	public List<PropertyDict> getComprehensiveManagementPosts() {
		return comprehensiveManagementPosts;
	}

	public void setComprehensiveManagementPosts(
			List<PropertyDict> comprehensiveManagementPosts) {
		this.comprehensiveManagementPosts = comprehensiveManagementPosts;
	}

	public int getExistedCount() {
		return existedCount;
	}

	public void setExistedCount(int existedCount) {
		this.existedCount = existedCount;
	}

	public String getPlaceTypeName() {
		return placeTypeName;
	}

	public void setPlaceTypeName(String placeTypeName) {
		this.placeTypeName = placeTypeName;
	}

	public String[] getUuid() {
		return uuid;
	}

	public void setUuid(String[] uuid) {
		this.uuid = uuid;
	}

	public String getEnterPriseIds() {
		return enterPriseIds;
	}

	public void setEnterPriseIds(String enterPriseIds) {
		this.enterPriseIds = enterPriseIds;
	}

	public FireSafetyEnterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(FireSafetyEnterprise enterprise) {
		this.enterprise = enterprise;
	}

	public FireSafetyEnterprise getLocation() {
		return location;
	}

	public void setLocation(FireSafetyEnterprise location) {
		this.location = location;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public String getLocationType() {
		return locationType;
	}

	public String getSuperviceRecordName() {
		return superviceRecordName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public void setSuperviceRecordName(String superviceRecordName) {
		this.superviceRecordName = superviceRecordName;
	}

}
