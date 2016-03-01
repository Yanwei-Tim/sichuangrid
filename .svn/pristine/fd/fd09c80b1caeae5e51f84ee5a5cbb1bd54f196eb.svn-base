package com.tianque.baseInfo.scenicManage.scenicSpot.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.scenicManage.praiseScenicSpot.domain.PraiseScenicSpot;
import com.tianque.baseInfo.scenicManage.praiseScenicSpot.service.PraiseScenicSpotService;
import com.tianque.baseInfo.scenicManage.scenicSpot.domain.ScenicSpot;
import com.tianque.baseInfo.scenicManage.scenicSpot.service.ScenicSpotService;
import com.tianque.baseInfo.scenicManage.scenicSpot.validator.ScenicSpotValidatorImpl;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.DialogMode;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/scenicSpotManage")
@Controller("scenicSpotController")
@Scope("prototype")
@Transactional
public class ScenicSpotController extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ScenicSpotService scenicSpotService;
	@Autowired
	private PraiseScenicSpotService praiseScenicSpotService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ValidateHelper validateHelper;
	private ScenicSpotValidatorImpl scenicSpotValidatorImpl;
	private Long organizationId;
	private boolean bol;
	private List<Long> placeId;
	private String locationIds;
	private ScenicSpot location;
	private String dailogName;
	private PraiseScenicSpot praiseScenicSpot;
	private Long orgId;
	private String scenicSpotIds;
	private String modeType;
	private String[] uuid;
	private ScenicSpot scenicSpot;
	private String keyType = "scenicSpot";
	private String placeTypeName = "旅游景点";
	private String supervisorName;
	private String locationType;
	private String superviceRecordName;

	@PermissionFilter(ename = "scenicSpotManagement")
	@Action(value = "scenicSpotList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findScenicSpotList() throws Exception {
		if (organizationId == null) {
			gridPage = new GridPage(new PageInfo<ScenicSpot>());
			return SUCCESS;
		} else {
			getSelectScenicSpotAndOrganizatById(organizationId);
			gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
					scenicSpotService.findScenicSpotList(location, page, rows,
							sidx, sord, location.getIsEmphasis()),
					organizationDubboService, new String[] { "organization" },
					organizationId));
		}
		return SUCCESS;
	}

	private ScenicSpot getSelectScenicSpotAndOrganizatById(Long orgId) {
		organization = organizationDubboService.getSimpleOrgById(orgId);
		if (location == null)
			location = new ScenicSpot();
		location.setOrganization(organization);
		location.setOrgInternalCode(organization.getOrgInternalCode());
		return location;
	}

	@Actions(@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/baseinfo/scenicManage/scenicSpot/scenicSpotDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/scenicManage/scenicSpot/commonView.jsp"),
			@Result(name = "search", location = "/baseinfo/scenicManage/scenicSpot/scenicSpotSearchDlg.jsp"),
			@Result(name = "praise", location = "/baseinfo/scenicManage/scenicSpot/praiseScenicSpotDlg.jsp"),
			@Result(name = "allSearch", location = "/baseinfo/scenicManage/scenicSpot/searchScenicSpotListDlg.jsp") }))
	public String prepareScenicSpot() throws Exception {
		ajaxUrl = "hasDuplicateScenicSpotName";
		if (id == null && location != null && location.getId() != null) {
			id = location.getId();
		}
		if (null != id) {
			location = scenicSpotService.getScenicSpotById(id);
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)
				|| DialogMode.SEARCH_MODE.equals(mode)
				|| DialogMode.ALLSEARCH_MODE.equals(mode)
				|| "praise".equals(mode)) {
			return mode;
		}
		return SUCCESS;
	}

	/**
	 * 解密調用
	 * 
	 * @return
	 */
	@Actions(@Action(value = "dispatchOperateByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/scenicManage/scenicSpot/scenicSpotDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/scenicManage/scenicSpot/commonView.jsp"),
			@Result(name = "print", location = "/baseinfo/commonPopulation/printTabPreviewDlg.jsp"),
			@Result(name = "praise", location = "/baseinfo/scenicManage/scenicSpot/praiseScenicSpotDlg.jsp") }))
	@EncryptAnnotation
	public String prepareScenicSpotByEncrypt() throws Exception {
		ajaxUrl = "hasDuplicateScenicSpotName";
		if (id == null && location != null && location.getId() != null) {
			id = location.getId();
		}
		if (null != id) {
			location = scenicSpotService.getScenicSpotById(id);
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		}
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)
				|| DialogMode.SEARCH_MODE.equals(mode)
				|| DialogMode.ALLSEARCH_MODE.equals(mode)
				|| "praise".equals(mode)) {
			return mode;
		} else if (DialogMode.PRINT_MODE.equals(mode)) {
			return "print";
		}
		return SUCCESS;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "viewScenicSpot")
	@Action(value = "viewScenicSpot", results = {
			@Result(name = "viewBase", location = "/baseinfo/scenicManage/scenicSpot/scenicSpotView.jsp"),
			@Result(name = "viewPraise", location = "/baseinfo/scenicManage/scenicSpot/praiseScenicSpotView.jsp") })
	public String viewScenicSpot() throws Exception {
		if (null == location || null == location.getId()) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if ("viewBase".equals(mode)) {
			location = scenicSpotService.getScenicSpotById(location.getId());
			location.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(location
							.getOrganization().getId(), organizationDubboService));
		} else if ("viewPraise".equals(mode)) {
			return "viewPraise";
		}
		return "viewBase";
	}

	@Action(value = "viewScenicSpotForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"location", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String viewScenicSpotForMobile() throws Exception {
		if (null == location || null == location.getId()) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		location = scenicSpotService.getScenicSpotById(location.getId());
		location.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(location
						.getOrganization().getId(), organizationDubboService));
		return SUCCESS;
	}

	@Action(value = "findPraiseScenicSpotList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPraiseScenicSpotList() throws Exception {
		gridPage = new GridPage(
				praiseScenicSpotService.findPraiseScenicSpotList(
						praiseScenicSpot, page, rows, sidx, sord));
		return SUCCESS;
	}

	@PermissionFilter(ename = "addScenicSpot")
	@Action(value = "addScenicSpot", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addScenicSpot() throws Exception {
		location.setIsEmphasis(false);
		location = scenicSpotService.addScenicSpot(location);
		location.setOrganization(ControllerHelper.proccessRelativeOrgNameByOrg(
				location.getOrganization(), organizationDubboService));
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateScenicSpot")
	@Action(value = "updateScenicSpot", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateScenicSpot() throws Exception {
		scenicSpotValidatorImpl = new ScenicSpotValidatorImpl();
		scenicSpotValidatorImpl.setOrganizationDubboService(organizationDubboService);
		scenicSpotValidatorImpl.setValidateHelper(validateHelper);
		ValidateResult scenicSpotValidator = ((DomainValidator<ScenicSpot>) scenicSpotValidatorImpl)
				.validate(location);
		if (scenicSpotValidator.hasError()) {
			return ERROR;
		}
		location = scenicSpotService.updateScenicSpot(location);
		location.setOrganization(ControllerHelper.proccessRelativeOrgNameByOrg(
				location.getOrganization(), organizationDubboService));
		return SUCCESS;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "deleteScenicSpot")
	@Action(value = "deleteScenicSpotByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"placeId" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteScenicSpot() throws Exception {
		String[] place = locationIds.split(",");
		Set<Long> set = new HashSet<Long>();
		for (int i = 0; i < place.length; i++) {
			if (place[i].equals("")) {
				continue;
			}
			set.add(Long.valueOf(place[i]));
		}
		List<Long> list = new ArrayList<Long>(set);
		placeId = scenicSpotService.deleteScenicSpotByIds(list);
		return SUCCESS;
	}

	@Action(value = "hasDuplicateScenicSpotName", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String hasDuplicateScenicSpotName() throws Exception {
		if (location == null || organizationId == null) {
			this.errorMessage = "参数不对";
			return ERROR;
		} else {
			return scenicSpotService.hasDuplicateScenicSpotName(organizationId,
					location.getName(), location.getId()) ? SUCCESS : ERROR;
		}
	}

	@Action(value = "updateEmphasiseById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"locationIds", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateEmphasiseById() throws Exception {
		scenicSpotService.updateEmphasiseByIds(analyzeLocationIds(), location);
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

	@Action(value = "getScenicSpotById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"location", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getScenicSpotById() throws Exception {
		if (id == null) {
			errorMessage = "参数错误，不能为空";
			return ERROR;
		}
		location = scenicSpotService.getScenicSpotById(id);
		return SUCCESS;
	}

	@Actions({
			@Action(value = "addPraiseScenicSpot", results = {
					@Result(name = "success", type = "json", params = { "root",
							"praiseScenicSpot" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "addPraiseScenicSpotForMobile", results = {
					@Result(name = "success", type = "json", params = { "root",
							"true" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String addPraiseScenicSpot() throws Exception {
		praiseScenicSpot = praiseScenicSpotService
				.addPraiseScenicSpot(praiseScenicSpot);
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

	public ScenicSpot getScenicSpot() {
		return scenicSpot;
	}

	public void setScenicSpot(ScenicSpot scenicSpot) {
		this.scenicSpot = scenicSpot;
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

	public String getScenicSpotIds() {
		return scenicSpotIds;
	}

	public void setScenicSpotIds(String scenicSpotIds) {
		this.scenicSpotIds = scenicSpotIds;
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

	public ScenicSpot getLocation() {
		return location;
	}

	public void setLocation(ScenicSpot location) {
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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public PraiseScenicSpot getPraiseScenicSpot() {
		return praiseScenicSpot;
	}

	public void setPraiseScenicSpot(PraiseScenicSpot praiseScenicSpot) {
		this.praiseScenicSpot = praiseScenicSpot;
	}

	public String getDailogName() {
		return dailogName;
	}

	public void setDailogName(String dailogName) {
		this.dailogName = dailogName;
	}

}
