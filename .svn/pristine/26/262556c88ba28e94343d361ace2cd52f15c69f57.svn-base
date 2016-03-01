package com.tianque.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PersonInCharges;
import com.tianque.domain.School;
import com.tianque.service.PersonInChargesService;
import com.tianque.service.SchoolService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.validate.impl.SchoolValidatorImpl;

@Namespace("/baseinfo/schoolManage")
@Controller("schoolController")
@Scope("prototype")
@Transactional
public class SchoolController extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PersonInChargesService personInChargesService;
	@Autowired
	private CacheService cacheService;
	private School school;
	private Long orgId;
	private String modeType;
	private boolean bol;
	@Autowired
	private ValidateHelper validateHelper;
	private SchoolValidatorImpl schoolValidatorImpl;
	private String keyType = "school";
	private String placeTypeName = "学校";
	private String[] uuid;
	private String schoolIds;
	private List<Long> placeId;
	private String locationIds;
	private School location;
	private String supervisorName; // 治安负责员名称
	private String locationType; // 场所类型名称
	private String superviceRecordName; // 巡场情况

	@PermissionFilter(ename = "schoolManageMent")
	public String findSchoolByOrgId() throws Exception {
		if (this.orgId == null) {
			gridPage = new GridPage(new PageInfo<School>());
			return SUCCESS;
		} else {
			getSelectSchoolAndOrganizatById(this.orgId);
			location.setOrganization(this.constructOrganization());
			gridPage = new GridPage(
					ControllerHelper.processAllOrgRelativeName(schoolService
							.finallSchoolList(location, page, rows, sidx, sord,
									location.getIsEmphasis() ? 1L : 0L),
							organizationDubboService,
							new String[] { "organization" }, orgId));
		}
		return SUCCESS;
	}

	private School getSelectSchoolAndOrganizatById(Long orgId) {
		organization = organizationDubboService.getSimpleOrgById(orgId);
		location.setOrganization(organization);
		location.setOrgInternalCode(organization.getOrgInternalCode());
		return location;
	}

	public String prepareSchool() throws Exception {
		// 修改
		if (null != id) {
			school = this.schoolService.getSchoolById(id);
			this.orgId = school.getOrganization().getId();
		}
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(this.mode)
				|| DialogMode.PRINT_MODE.equalsIgnoreCase(this.mode)) {
			school = schoolService.getSchoolById(location.getId());
			school.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(school
							.getOrganization().getId(), organizationDubboService));
			return this.mode; // 查看
		} /*
			* else if (DialogMode.PRINT_MODE.equals(this.mode)) { school =
			* this.schoolService.getSchoolById(location.getId()); this.orgId =
			* school.getOrganization().getId(); return "print"; // 打印 }
			*/else if (DialogMode.SEARCH_MODE.equals(this.mode)) {
			return "search"; // 查询
		}
		return SUCCESS;
	}

	// 解密调用
	@EncryptAnnotation
	public String dispatchOperateByEncrypt() throws Exception {
		// 修改
		if (null != id) {
			school = this.schoolService.getSchoolById(id);
			this.orgId = school.getOrganization().getId();
		}
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(this.mode)
				|| DialogMode.PRINT_MODE.equalsIgnoreCase(this.mode)) {
			school = schoolService.getSchoolById(location.getId());
			school.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(school
							.getOrganization().getId(), organizationDubboService));
			return this.mode; // 查看
		}
		return SUCCESS;
	}

	public String shiftDispatch() throws Exception {
		if (orgId == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		school = new School();
		school.setOrganization(organizationDubboService.getSimpleOrgById(orgId));
		return SUCCESS;
	}

	public String shiftSchool() throws Exception {
		String[] id = locationIds.split(",");
		Long[] ids = new Long[id.length];
		for (int i = 0; i < id.length; i++) {
			if (id[i].equals("")) {
				continue;
			}
			ids[i] = Long.parseLong(id[i]);
		}
		schoolService.shiftSchool(ids, school.getOrganization().getId());
		return SUCCESS;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "viewSchool")
	public String lookSchool() throws Exception {
		school = this.schoolService.getSchoolById(school.getId());
		school.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(school
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	@PermissionFilter(ename = "addSchool")
	public String addSchool() throws Exception {
		school.setIsEmphasis(false); // 现在关注
		school.setOrganization(this.constructOrganization());
		school = schoolService.addSchool(school);
		school.setOrganization(ControllerHelper.proccessRelativeOrgNameByOrg(
				school.getOrganization(), organizationDubboService));
		if (uuid != null && 0 != uuid.length) {
			addPersonInCharges(school);
		}
		return SUCCESS;
	}

	private Organization constructOrganization() {
		Organization result = new Organization();
		result.setId(orgId);
		return result;
	}

	@PermissionFilter(ename = "updateSchool")
	public String updateSchool() throws Exception {
		school.setOrganization(this.constructOrganization());
		schoolValidatorImpl = new SchoolValidatorImpl();
		schoolValidatorImpl.setOrganizationDubboService(organizationDubboService);
		schoolValidatorImpl.setValidateHelper(validateHelper);
		ValidateResult schoolValidator = ((DomainValidator<School>) schoolValidatorImpl)
				.validate(school);
		if (schoolValidator.hasError()) {
			return ERROR;
		}

		school = schoolService.updateSchool(school);
		school.setOrganization(ControllerHelper.proccessRelativeOrgNameByOrg(
				school.getOrganization(), organizationDubboService));
		return SUCCESS;
	}

	public String updateSchoolByEmphasis() throws Exception {
		School oldSchool = schoolService.getSchoolById(school.getId());
		oldSchool.setIsEmphasis(school.getIsEmphasis());
		school = schoolService.updateSchoolByEmphasis(oldSchool);
		return SUCCESS;
	}

	private void addPersonInCharges(School school) {
		for (int i = 0; i < uuid.length; i++) {

			PersonInCharges personInCharges = (PersonInCharges) cacheService
					.get(uuid[i]);
			personInCharges.setPlaceId(school.getId());
			personInChargesService.addPersonInCharges(personInCharges);
		}
	}

	@PermissionFilter(ename = "deleteSchool")
	@EncryptAnnotation
	public String deleteSchool() throws Exception {
		String[] place = locationIds.split(",");
		Set<Long> set = new HashSet<Long>();
		for (int i = 0; i < place.length; i++) {
			if (place[i].equals("")) {
				continue;
			}
			set.add(Long.valueOf(place[i]));
		}
		List<Long> list = new ArrayList<Long>(set);
		placeId = schoolService.deleteSchoolById(list);
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
		bol = schoolService.hasRelatePlacce(list);
		return SUCCESS;
	}

	public String hasDuplicateSchoolName() throws Exception {
		if (school == null || school.getOrganization() == null
				|| school.getOrganization().getId() == null) {
			this.errorMessage = "参数不对";
			return ERROR;
		} else {
			return schoolService.hasDuplicateSchoolName(school
					.getOrganization().getId(), school.getChineseName(), school
					.getId()) ? SUCCESS : ERROR;
		}
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		schoolService.updateEmphasiseByIds(analyzeLocationIds(), location);
		return SUCCESS;
	}

	@Action(value = "getSchoolById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"school", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getSchoolById() throws Exception {
		if (id == null) {
			errorMessage = "参数错误，不能为空";
			return ERROR;
		}
		school = schoolService.getSchoolById(id);
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

	private Organization organization;

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

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
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

	public String getSchoolIds() {
		return schoolIds;
	}

	public void setSchoolIds(String schoolIds) {
		this.schoolIds = schoolIds;
	}

	public List<Long> getPlaceId() {
		return placeId;
	}

	public void setPlaceId(List<Long> placeId) {
		this.placeId = placeId;
	}

	public String getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(String locationIds) {
		this.locationIds = locationIds;
	}

	public School getLocation() {
		return location;
	}

	public void setLocation(School location) {
		this.location = location;
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
