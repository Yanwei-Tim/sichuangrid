package com.tianque.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.DialogMode;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ConstructionUnit;
import com.tianque.domain.Organization;
import com.tianque.service.ConstructionUnitService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.validate.impl.ConstructionUnitValidateImpl;

@Controller("constructionUnitController")
@Scope("prototype")
@Transactional
@SuppressWarnings("serial")
public class ConstructionUnitController extends BaseAction {
	@Autowired
	private ConstructionUnitService constructionUnitService;
	@Autowired
	protected OrganizationDubboService organizationDubboService;

	private ConstructionUnit constructionUnit;
	private Organization organization;
	private long existedCount;
	private boolean bol;
	private Long orgId;
	private String modeType;
	private ConstructionUnitValidateImpl constructionUnitValidateImpl;
	@Autowired
	private ValidateHelper validateHelper;
	private String constructionUnitIds;
	private List<Long> placeId;

	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			if (organization == null) {
				organization = new Organization();
				organization.setId(orgId);
			}
			constructionUnit = new ConstructionUnit();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			constructionUnit = constructionUnitService
					.getConstructionUnitById(constructionUnit.getId());
			organization = constructionUnit.getOrganization();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			constructionUnit = constructionUnitService
					.getConstructionUnitById(constructionUnit.getId());
			organization = constructionUnit.getOrganization();
		} else if (DialogMode.SEARCH_MODE.equalsIgnoreCase(mode)) {
			return "search";
		} else if (DialogMode.PRINT_MODE.equalsIgnoreCase(mode)) {
			constructionUnit = constructionUnitService
					.getConstructionUnitById(constructionUnit.getId());
			organization = constructionUnit.getOrganization();
			return "print";
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "addConstructionUnit")
	public String addConstructionUnit() throws Exception {

		constructionUnit.setOrganization(organization);

		constructionUnitValidateImpl = new ConstructionUnitValidateImpl();
		constructionUnitValidateImpl.setValidateHelper(validateHelper);
		constructionUnitValidateImpl
				.setOrganizationDubboService(organizationDubboService);
		ValidateResult constructionUnitValidator = ((DomainValidator<ConstructionUnit>) constructionUnitValidateImpl)
				.validate(constructionUnit);
		if (constructionUnitValidator.hasError()) {
			errorMessage = constructionUnitValidator.getErrorMessages();
			return ERROR;
		}

		constructionUnit = constructionUnitService
				.addConstructionUnit(constructionUnit);
		constructionUnit
				.setOrganization(ControllerHelper.proccessRelativeOrgNameByOrg(
						constructionUnit.getOrganization(), organizationDubboService));

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

	public String shiftConstructionUnit() throws Exception {
		if (constructionUnitIds.equals(""))
			return ERROR;
		String[] id = constructionUnitIds.split(",");
		Long[] ids = new Long[id.length];
		for (int i = 0; i < id.length; i++) {
			if (id[i].equals("")) {
				continue;
			}
			ids[i] = Long.parseLong(id[i]);
		}
		constructionUnitService.shiftConstructionUnit(ids, constructionUnit
				.getOrganization().getId());
		return SUCCESS;
	}

	public String getConstructionUnitById() throws Exception {
		if (null == constructionUnit || null == constructionUnit.getId()) {
			this.errorMessage = "Id不得为空!";
			return ERROR;
		}
		constructionUnit = constructionUnitService
				.getConstructionUnitById(constructionUnit.getId());
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateConstructionUnit")
	public String updateConstructionUnit() throws Exception {
		constructionUnit.setOrganization(organization);

		constructionUnitValidateImpl = new ConstructionUnitValidateImpl();
		constructionUnitValidateImpl.setValidateHelper(validateHelper);
		constructionUnitValidateImpl
				.setOrganizationDubboService(organizationDubboService);
		ValidateResult constructionUnitValidator = ((DomainValidator<ConstructionUnit>) constructionUnitValidateImpl)
				.validate(constructionUnit);
		if (constructionUnitValidator.hasError()) {
			errorMessage = constructionUnitValidator.getErrorMessages();
			return ERROR;
		}

		constructionUnit = constructionUnitService
				.updateConstructionUnit(constructionUnit);
		constructionUnit
				.setOrganization(ControllerHelper.proccessRelativeOrgNameByOrg(
						constructionUnit.getOrganization(), organizationDubboService));
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteConstructionUnit")
	public String deleteConstructionUnitById() throws Exception {
		String[] place = constructionUnitIds.split(",");
		Set<Long> set = new HashSet<Long>();
		for (int i = 0; i < place.length; i++) {
			if (place[i].equals("")) {
				continue;
			}
			set.add(Long.valueOf(place[i]));
		}
		List<Long> list = new ArrayList<Long>(set);
		placeId = constructionUnitService.deleteConstructionUnitById(list);
		return SUCCESS;
	}

	@PermissionFilter(ename = "constructionUniteManagement")
	public String findConstructionUnitsForPage() throws Exception {
		if (null == organization || null == organization.getId()) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		}
		PageInfo pageInfo = ControllerHelper.processAllOrgRelativeName(
				constructionUnitService.findConstructionUnitForPage(
						organization.getId(), rows, page, sidx, sord,
						constructionUnit.getIsEmphasis()), organizationDubboService,
				new String[] { "organization" }, organization.getId());
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/***
	 * 唯一性验证
	 * 
	 * @return
	 */
	public String hasDuplicateProjectName() throws Exception {
		if (constructionUnit == null
				|| constructionUnit.getOrganization() == null
				|| constructionUnit.getOrganization().getId() == null) {
			return ERROR;
		} else {
			String rs = constructionUnitService
					.hasDuplicateConstructionProjectName(constructionUnit
							.getOrganization().getId(), constructionUnit
							.getProjectName(), constructionUnit.getId()) ? ERROR
					: SUCCESS;
			return rs;
		}
	}

	public String updateConstructionUnitById() throws Exception {
		constructionUnit = constructionUnitService
				.updateConstructionUnitById(constructionUnit);
		return SUCCESS;
	}

	public String hasRelatePlace() throws Exception {
		String[] place = constructionUnitIds.split(",");
		List<Long> list = new ArrayList<Long>();
		for (int i = 0; i < place.length; i++) {
			if (place[i].equals("")) {
				continue;
			}
			list.add(Long.valueOf(place[i]));
		}
		bol = constructionUnitService.hasRelatePlacce(list);
		return SUCCESS;
	}

	private PageInfo<ConstructionUnit> emptyPage(int pageSize) {
		PageInfo<ConstructionUnit> pageInfo = new PageInfo<ConstructionUnit>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<ConstructionUnit>());
		return pageInfo;
	}

	public ConstructionUnit getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstructionUnit(ConstructionUnit constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	public String getConstructionUnitIds() {
		return constructionUnitIds;
	}

	public void setConstructionUnitIds(String constructionUnitIds) {
		this.constructionUnitIds = constructionUnitIds;
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

}