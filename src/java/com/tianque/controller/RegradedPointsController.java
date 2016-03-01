package com.tianque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.annotation.PermissionFilters;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.RegradedPoints;
import com.tianque.domain.vo.WorkContacterRegradedReason;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.RegradedPointService;
import com.tianque.statRegrad.service.DefalutStatRegradedPointsService;
import com.tianque.state.RegradedType;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("regradedPointsController")
@Scope("prototype")
@Transactional
public class RegradedPointsController extends BaseAction {

	@Autowired
	private RegradedPointService regradedPointService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DefalutStatRegradedPointsService defalutStatRegradedPointsService;

	public String dispatch() throws Exception {
		if (orgId == null) {
			errorMessage = "请选择一个部门!";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(orgId);
		return SUCCESS;
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "addAdministrativeRegradedPoint", actionName = "regradedPoints"),
			@PermissionFilter(ename = "addFunctionalRegradedPoint", actionName = "regradedPoints") })
	public String regradedPoints() throws Exception {
		Organization targeOrg;
		if (regradedPoint != null && regradedPoint.getTargeOrg() != null
				&& regradedPoint.getTargeOrg().getId() != null) {
			targeOrg = organizationDubboService.getSimpleOrgById(regradedPoint
					.getTargeOrg().getId());
		} else {
			throw new BusinessValidationException("参数错误");
		}
		if(regradedPoint.getApplicationDate() == null){
			throw new BusinessValidationException("应用时间不能为空");
		}
		WorkContacterRegradedReason regradedReason = new WorkContacterRegradedReason();
		regradedReason.setContent(regradedPoint.getContent());
		if (regradedPoint.getPointType() == 1) {
			regradedPoint = regradedPointService.ManualBonusPoints(targeOrg,
					RegradedType.REGRADEDBYPERSON, regradedReason,
					regradedPoint.getPoints(),
					regradedPoint.getApplicationDate());
		} else {
			regradedPoint = regradedPointService.ManualDeductPoints(targeOrg,
					RegradedType.REGRADEDBYPERSON, regradedReason,
					regradedPoint.getPoints(),
					regradedPoint.getApplicationDate());
		}
		defalutStatRegradedPointsService.refreshRegradedPointStatTableByOrgId(
				regradedPoint.getApplicationDate(), regradedPoint.getTargeOrg()
						.getId());
		return SUCCESS;
	}

	@PermissionFilters(value = {
			@PermissionFilter(ename = "viewAdministrativeRegradedPoints", actionName = "viewSubDetail"),
			@PermissionFilter(ename = "viewAdministrativeRegradedPoints", actionName = "viewSubDetail") })
	public String viewSubDetail() throws Exception {
		if (orgId == null)
			return ERROR;
		// gridPage = new
		// GridPage(ControllerHelper.processAllOrgRelativeName(regradedPointService
		// .findRegradedPointsByOrgIdAndStatDate(orgId, statDate, page, rows,
		// sidx, sord),
		// organizationDubboService, new String[] { "regradedOrg", "targeOrg" },
		// orgId));
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				regradedPointService.findRegradedPointsByOrgIdAndStatDate(
						orgId, reoprtDateType, year, month, page, rows, sidx,
						sord), organizationDubboService, new String[] {
						"regradedOrg", "targeOrg" }, orgId));
		return SUCCESS;
	}

	private Long orgId;
	private String statDate;
	private List<RegradedPoints> regradedPoints;
	private int internalId;
	private Organization organization;
	private RegradedPoints regradedPoint;
	private PropertyDict reoprtDateType;
	private Integer year;
	private Integer month;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public RegradedPoints getRegradedPoint() {
		return regradedPoint;
	}

	public void setRegradedPoint(RegradedPoints regradedPoint) {
		this.regradedPoint = regradedPoint;
	}

	public int getInternalId() {
		return internalId;
	}

	public void setInternalId(int internalId) {
		this.internalId = internalId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getStatDate() {
		return statDate;
	}

	public void setStatDate(String statDate) {
		this.statDate = statDate;
	}

	public List<RegradedPoints> getRegradedPoints() {
		return regradedPoints;
	}

	public void setRegradedPoints(List<RegradedPoints> regradedPoints) {
		this.regradedPoints = regradedPoints;
	}

	public PropertyDict getReoprtDateType() {
		return reoprtDateType;
	}

	public void setReoprtDateType(PropertyDict reoprtDateType) {
		this.reoprtDateType = reoprtDateType;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}
