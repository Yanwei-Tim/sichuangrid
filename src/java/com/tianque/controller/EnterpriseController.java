package com.tianque.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Enterprise;
import com.tianque.domain.Organization;
import com.tianque.domain.PersonInCharges;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.service.EnterpriseService;
import com.tianque.service.PersonInChargesService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Namespaces({ @Namespace("/baseinfo/safetyProductionKeyManage"),
		@Namespace("/baseinfo/enterpriseManage") })
@Controller("enterpriseController")
@SuppressWarnings("serial")
@Scope("prototype")
@Transactional
public class EnterpriseController extends BaseAction {
	private Enterprise enterprise;
	private Organization ownerOrg;
	private List<PropertyDict> comprehensiveManagementPosts;
	private int existedCount;
	private Long orgId;
	private String modeType;
	private boolean bol;
	private String keyType;// 重点类型
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PersonInChargesService personInChargesService;
	@Qualifier("enterPriseValidator")
	@Autowired
	private DomainValidator domainValidator;

	private String placeTypeName;
	private String[] uuid;
	private String enterPriseIds;
	private List<Long> placeId;
	private Enterprise location;
	protected String locationIds;
	private String supervisorName; // 治安负责员名称
	private String locationType; // 场所类型名称
	private String superviceRecordName; // 巡场情况
	public static final Logger logger = Logger
			.getLogger(EnterpriseController.class);

	public String dispatchOperate() throws Exception {
		dispatchOperateMethod();
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			enterprise = enterpriseService.getEnterpriseById(location.getId());
			ownerOrg = enterprise.getOrganization();
			return "view";// 查看
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			if (keyType.equals("securityKey")) {
				return "securitySearch";
			}
			return "search";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			enterprise = enterpriseService.getEnterpriseById(location.getId());
			ownerOrg = enterprise.getOrganization();
			/*
			 * if (keyType.equals("securityKey")) { return "securityPrint"; }
			 */
			return "print";
		}

		if (keyType.equals("securityKey")) {
			return "securityKey";
		}
		if (keyType.equals("fireSafetyKey")) {
			return "fireSafetyKey";
		}
		return SUCCESS;
	}

	/**
	 * 解密(调用)
	 * 
	 * @return
	 */
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() throws Exception {
		dispatchOperateMethod();

		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			enterprise = enterpriseService.getEnterpriseById(location.getId());
			ownerOrg = enterprise.getOrganization();
			return "view";// 查看
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			enterprise = enterpriseService.getEnterpriseById(location.getId());
			ownerOrg = enterprise.getOrganization();
			return "print";
		} else if (keyType.equals("securityKey")) {
			return "securityKey";
		}
		return SUCCESS;
	}

	private void dispatchOperateMethod() throws Exception {
		if (keyType.equals("enterpriseKey")) {
			placeTypeName = "规上企业";
		} else if (keyType.equals("enterpriseDownKey")) {
			placeTypeName = "规下企业";
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
			enterprise = enterpriseService.getEnterpriseById(id);
			ownerOrg = enterprise.getOrganization();
		} else {
			if (ownerOrg == null) {
				ownerOrg = new Organization();
				ownerOrg.setId(orgId);
			}
			enterprise = new Enterprise();
			enterprise.setKeyType(keyType);
		}
	}

	public String addEnterprise() throws Exception {
		enterprise.setOrganization(ownerOrg);
		if (enterprise.getKeyType() != null
				&& enterprise.getKeyType().equals("enterpriseKey")) {
			List<PropertyDict> propertyDictList = propertyDictService
					.findPropertyDictByDomainName(PropertyTypes.ENTERPRISE_TYPE);
			for (int i = 0; i < propertyDictList.size(); i++) {
				PropertyDict domain = propertyDictList.get(i);
				if (domain.getDisplayName().equals("规上企业")) {
					enterprise.setType(domain);
				}
			}
		} else if (enterprise.getKeyType() != null
				&& enterprise.getKeyType().equals("enterpriseDownKey")) {
			List<PropertyDict> propertyDictList = propertyDictService
					.findPropertyDictByDomainName(PropertyTypes.ENTERPRISE_TYPE);
			for (int i = 0; i < propertyDictList.size(); i++) {
				PropertyDict domain = propertyDictList.get(i);
				if (domain.getDisplayName().equals("规下企业")) {
					enterprise.setType(domain);
				}
			}

		}

		enterprise = enterpriseService.addEnterprise(enterprise);
		enterprise.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(enterprise.getOrganization(),
						organizationDubboService));

		if (uuid != null) {
			addPersonInCharges(enterprise);
		}
		return SUCCESS;
	}

	/**
	 * 验证企业名称唯一性
	 */
	public String hasDuplicateEnterpriseNameAndKeyType() throws Exception {
		if (ownerOrg == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		bol = enterpriseService.hasDuplicateEnterpriseNameAndKeyType(
				ownerOrg.getId(), enterprise.getName(), enterprise.getId(),
				enterprise.getKeyType());
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

	public String shiftEnterprise() throws Exception {
		String[] id = enterPriseIds.split(",");
		Long[] ids = new Long[id.length];
		for (int i = 0; i < id.length; i++) {
			if (id[i].equals("")) {
				continue;
			}
			ids[i] = Long.parseLong(id[i]);
		}
		enterpriseService.shiftEnterprise(ids, enterprise.getOrganization()
				.getId(), keyType);
		return SUCCESS;
	}

	public String addEnterpriseAndPersonInCharges() throws Exception {
		enterprise.setOrganization(ownerOrg);
		ValidateResult validateResult = ((DomainValidator<Enterprise>) domainValidator)
				.validate(enterprise);
		if (validateResult.hasError()) {
			return ERROR;
		}
		enterprise = enterpriseService.addEnterprise(enterprise);
		enterprise.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(enterprise.getOrganization(),
						organizationDubboService));
		return SUCCESS;
	}

	private String addPersonInCharges(Enterprise enterprise) {
		for (int i = 0; i < uuid.length; i++) {
			PersonInCharges personInCharges = (PersonInCharges) cacheService
					.get(uuid[i]);
			personInCharges.setPlaceType(enterprise.getKeyType());
			personInCharges.setPlaceId(enterprise.getId());
			personInChargesService.addPersonInCharges(personInCharges);

		}
		return SUCCESS;
	}

	public String findEnterprisesForListPage() throws Exception {
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
							enterpriseService
									.findEnterprisesForListPageByOrganizationIdAndKeyType(
											orgId, this.keyType, rows, page,
											sidx, sord,
											location.getIsEmphasis() ? 1L : 0L),
							organizationDubboService,
							new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	public String updateEnterprise() throws Exception {
		enterprise.setOrganization(ownerOrg);
		Enterprise enterpriseNew = enterpriseService
				.getEnterpriseByNameAndOrgIdAndKeyType(enterprise.getName(),
						enterprise.getOrganization().getId(),
						enterprise.getKeyType());
		if (enterpriseNew != null
				&& !enterprise.getId().equals(enterpriseNew.getId())) {
			this.errorMessage = "不能存在相同的规上企业名称!";
			return ERROR;
		}
		enterprise = enterpriseService.updateEnterprise(enterprise);
		enterprise.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(enterprise.getOrganization(),
						organizationDubboService));
		return SUCCESS;
	}

	public String updateEmphasis() throws Exception {
		if (null != locationIds) {
			for (Long id : analyzeLocationIds()) {
				Enterprise oldEnterprise = enterpriseService
						.getEnterpriseById(id);
				oldEnterprise.setIsEmphasis(enterprise.getIsEmphasis());
				enterprise = enterpriseService.updateEmphasis(oldEnterprise);
			}
			return SUCCESS;
		}
		return ERROR;
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		enterpriseService.updateEmphasiseByIds(analyzeLocationIds(), location);
		return SUCCESS;
	}

	@Action(value = "getEnterpriseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"enterprise", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getEnterpriseById() throws Exception {
		if (enterprise == null || enterprise.getId() == null) {
			errorMessage = "参数错误,不能为空";
			return ERROR;
		}
		enterprise = enterpriseService.getEnterpriseById(enterprise.getId());
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

	// 删除（解密）
	@EncryptAnnotation
	public String deleteEnterprise() throws Exception {
		String[] place = locationIds.split(",");
		Set<Long> set = new HashSet<Long>();
		for (int i = 0; i < place.length; i++) {
			if (place[i].equals("")) {
				continue;
			}
			set.add(Long.valueOf(place[i]));
		}
		List<Long> list = new ArrayList<Long>(set);
		placeId = enterpriseService.deleteEnterpriseById(list);
		return SUCCESS;
	}

	public String hasRelatePlace() throws Exception {
		String[] place = locationIds.split(",");
		List<Long> list = new ArrayList<Long>();
		for (int i = 0; i < place.length; i++) {
			if (place[i].equals("")) {
				continue;
			}
			list.add(Long.valueOf(place[i]));
		}
		bol = enterpriseService.hasRelatePlacce(list);
		return SUCCESS;
	}

	private PageInfo<Enterprise> emptyPage(int pageSize) {
		PageInfo<Enterprise> pageInfo = new PageInfo<Enterprise>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<Enterprise>());
		return pageInfo;
	}

	public String hasDuplicateEnterpriseName() throws Exception {
		if (enterprise == null || enterprise.getOrganization() == null
				|| enterprise.getOrganization().getId() == null) {
			return ERROR;
		} else {
			return enterpriseService.hasDuplicateEnterpriseNameAndKeyType(
					enterprise.getOrganization().getId(), enterprise.getName(),
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
	public String viewEnterprise() throws Exception {

		if (enterprise != null && enterprise.getId() != null) {
			enterprise = enterpriseService
					.getEnterpriseById(enterprise.getId());
			enterprise.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(enterprise
							.getOrganization().getId(), organizationDubboService));
		}
		if ("securityKey".equals(keyType.split(",")[0])) {
			return "viewSecurityKey";
		}
		return SUCCESS;
	}

	public String viewEnterpriseForMobile() throws Exception {
		if (enterprise != null && enterprise.getId() != null) {
			enterprise = enterpriseService
					.getEnterpriseById(enterprise.getId());
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

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
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

	public List<Long> getPlaceId() {
		return placeId;
	}

	public void setPlaceId(List<Long> placeId) {
		this.placeId = placeId;
	}

	public Enterprise getLocation() {
		return location;
	}

	public void setLocation(Enterprise location) {
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

	public String getSuperviceRecordName() {
		return superviceRecordName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public void setSuperviceRecordName(String superviceRecordName) {
		this.superviceRecordName = superviceRecordName;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

}
