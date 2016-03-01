package com.tianque.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

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
import com.tianque.domain.OtherLocale;
import com.tianque.service.OtherLocaleService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.validate.impl.OtherlocalValidateImpl;

@Controller("otherLocaleController")
@Scope("prototype")
@Transactional
@SuppressWarnings("serial")
public class OtherLocaleController extends BaseAction {
	@Autowired
	private OtherLocaleService otherLocaleService;
	@Autowired
	protected OrganizationDubboService organizationDubboService;

	private OtherLocale otherLocale;
	private Organization organization;
	private long existedCount;
	private boolean bol;
	private Long orgId;
	private String modeType;
	private OtherlocalValidateImpl otherlocalValidateImpl;
	@Autowired
	private ValidateHelper validateHelper;
	private String otherLocaleIds;
	private List<Long> placeId;
	private String locationIds;
	private OtherLocale location;

	public String dispatch() throws Exception {
		if (null != id) {
			otherLocale = otherLocaleService.getOtherLocaleById(id);
			organization = otherLocale.getOrganization();
		} else {
			if (organization == null) {
				organization = new Organization();
				organization.setId(orgId);
			}
			otherLocale = new OtherLocale();
		}
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			otherLocale = otherLocaleService.getOtherLocaleById(location
					.getId());
			organization = otherLocale.getOrganization();
			return "view"; // 查看
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			return "search";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			otherLocale = otherLocaleService.getOtherLocaleById(location
					.getId());
			organization = otherLocale.getOrganization();
			return "print";
		}
		return SUCCESS;
	}

	/**
	 * 解密調用
	 * 
	 * @return
	 */
	@EncryptAnnotation
	public String dispatchByEncrypt() throws Exception {
		if (null != id) {
			otherLocale = otherLocaleService.getOtherLocaleById(id);
			organization = otherLocale.getOrganization();
		} else {
			if (organization == null) {
				organization = new Organization();
				organization.setId(orgId);
			}
			otherLocale = new OtherLocale();
		}
		if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			otherLocale = otherLocaleService.getOtherLocaleById(location
					.getId());
			organization = otherLocale.getOrganization();
			return "view"; // 查看
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			otherLocale = otherLocaleService.getOtherLocaleById(location
					.getId());
			organization = otherLocale.getOrganization();
			return "print";
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "addOtherLocale")
	public String addOtherLocale() throws Exception {
		otherLocale.setIsEmphasis(false); // 现在关注
		otherLocale.setOrganization(organization);
		otherlocalValidateImpl = new OtherlocalValidateImpl();
		otherlocalValidateImpl.setValidateHelper(validateHelper);
		otherlocalValidateImpl.setOrganizationDubboService(organizationDubboService);
		ValidateResult otherLocaleValidator = ((DomainValidator<OtherLocale>) otherlocalValidateImpl)
				.validate(otherLocale);
		if (otherLocaleValidator.hasError()) {
			errorMessage = otherLocaleValidator.getErrorMessages();
			return ERROR;
		}
		otherLocale = otherLocaleService.addOtherLocale(otherLocale);
		otherLocale.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(otherLocale.getOrganization(),
						organizationDubboService));

		return SUCCESS;
	}

	public String shiftDispatch() throws Exception {
		if (orgId == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(orgId);
		return SUCCESS;
	}

	public String shiftOtherLocale() throws Exception {
		if (otherLocaleIds.equals(""))
			return ERROR;
		String[] id = otherLocaleIds.split(",");
		Long[] ids = new Long[id.length];
		for (int i = 0; i < id.length; i++) {
			if (id[i].equals("")) {
				continue;
			}
			ids[i] = Long.parseLong(id[i]);
		}
		otherLocaleService.shiftOtherLocale(ids, otherLocale.getOrganization()
				.getId());
		return SUCCESS;
	}

	public String getOtherLocaleById() throws Exception {
		if (null == otherLocale || null == otherLocale.getId()) {
			this.errorMessage = "Id不得为空!";
			return ERROR;
		}
		otherLocale = otherLocaleService
				.getOtherLocaleById(otherLocale.getId());
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateOtherLocale")
	public String updateOtherLocale() throws Exception {
		otherLocale.setOrganization(organization);
		otherlocalValidateImpl = new OtherlocalValidateImpl();
		otherlocalValidateImpl.setValidateHelper(validateHelper);
		otherlocalValidateImpl.setOrganizationDubboService(organizationDubboService);
		ValidateResult otherLocaleValidator = ((DomainValidator<OtherLocale>) otherlocalValidateImpl)
				.validate(otherLocale);
		if (otherLocaleValidator.hasError()) {
			return ERROR;
		}
		otherLocale = otherLocaleService.updateOtherLocale(otherLocale);
		otherLocale.setOrganization(ControllerHelper
				.proccessRelativeOrgNameByOrg(otherLocale.getOrganization(),
						organizationDubboService));
		return SUCCESS;
	}

	@EncryptAnnotation
	@PermissionFilter(ename = "deleteOtherLocale")
	public String deleteOtherLocaleById() throws Exception {
		String[] place = locationIds.split(",");
		Set<Long> set = new HashSet<Long>();
		for (int i = 0; i < place.length; i++) {
			if (place[i].equals("")) {
				continue;
			}
			set.add(Long.valueOf(place[i]));
		}
		List<Long> list = new ArrayList<Long>(set);
		placeId = otherLocaleService.deleteOtherLocaleById(list);
		return SUCCESS;
	}

	@PermissionFilter(ename = "otherLocaleManagement")
	public String findOtherLocalesForPage() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		PageInfo pageInfo = ControllerHelper.processAllOrgRelativeName(
				otherLocaleService.findOtherLocalesForPage(orgId, rows, page,
						sidx, sord, location.getIsEmphasis() ? 1L : 0L),
				organizationDubboService, new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String hasDuplicateOtherLocaleName() throws Exception {
		if (otherLocale == null || otherLocale.getOrganization() == null
				|| otherLocale.getOrganization().getId() == null) {
			return ERROR;
		} else {
			return otherLocaleService.hasDuplicateOtherLocaleName(otherLocale
					.getOrganization().getId(), otherLocale.getName(),
					otherLocale.getId()) ? ERROR : SUCCESS;
		}
	}

	public String updateOtherLocaleById() throws Exception {
		otherLocale = otherLocaleService.updateOtherLocaleById(otherLocale);
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
		bol = otherLocaleService.hasRelatePlacce(list);
		return SUCCESS;
	}

	private PageInfo<OtherLocale> emptyPage(int pageSize) {
		PageInfo<OtherLocale> pageInfo = new PageInfo<OtherLocale>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<OtherLocale>());
		return pageInfo;
	}

	public String updateEmphasiseById() throws Exception {
		otherLocaleService.updateEmphasiseByIds(analyzeLocationIds(), location);
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

	/**
	 * 查看
	 * 
	 * @return SUCCESS
	 */
	@EncryptAnnotation
	public String viewOtherLocale() throws Exception {
		if (otherLocale != null && otherLocale.getId() != null) {
			otherLocale = otherLocaleService.getOtherLocaleById(otherLocale
					.getId());
			otherLocale.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(otherLocale
							.getOrganization().getId(), organizationDubboService));
		}
		return SUCCESS;
	}

	public OtherLocale getOtherLocale() {
		return otherLocale;
	}

	public void setOtherLocale(OtherLocale otherLocale) {
		this.otherLocale = otherLocale;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public long getExistedCount() {
		return existedCount;
	}

	public void setExistedCount(long existedCount) {
		this.existedCount = existedCount;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public String getOtherLocaleIds() {
		return otherLocaleIds;
	}

	public void setOtherLocaleIds(String otherLocaleIds) {
		this.otherLocaleIds = otherLocaleIds;
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

	public OtherLocale getLocation() {
		return location;
	}

	public void setLocation(OtherLocale location) {
		this.location = location;
	}

}