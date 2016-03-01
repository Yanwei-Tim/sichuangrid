package com.tianque.mobile.baseInfo.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.controller.ActualHouseController;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.householdStaff.controller.HouseholdStaffController;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.controller.vo.HouseholdStaffVo;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.ActualHouseMobileAdapter;
import com.tianque.mobile.baseInfo.HouseholdStaffMobileAdapter;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 手机端：户籍人口
 */
@Transactional
@Scope("request")
@Controller("householdStaffMobileAdapter")
public class HouseholdStaffMobileAdapterImpl extends BaseMobileAction implements
		HouseholdStaffMobileAdapter {

	private HouseholdStaffVo householdStaffVo;
	@Autowired
	private HouseholdStaffController householdStaffController;
	private Long orgId;

	@Autowired
	private ActualHouseMobileAdapter actualHouseMobileAdapter;

	private HouseInfo houseInfo;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ActualHouseController actualHouseController;
	private RentalHouse rentalHouse;
	private HouseholdStaff population;
	@Autowired
	private HouseholdStaffService householdStaffService;

	@Override
	public String findHouseholdStaffList() throws Exception {
		if (orgId == null) {
			gridPage = new GridPage(emptyPage(rows));
		} else {
			if (householdStaffVo == null) {
				householdStaffVo = new HouseholdStaffVo();
			}
			setControllerProperties();
			householdStaffController.findGridPageByOrgIdAndIsEmphasis();
			gridPage = householdStaffController.getGridPage();
		}
		return SUCCESS;
	}

	private void setControllerProperties() {
		householdStaffController.setHouseholdStaffVo(householdStaffVo);
		householdStaffController.setOrgId(orgId);
		householdStaffController.setPage(page);
		householdStaffController.setRows(rows);
		householdStaffController.setSidx(sidx);
		householdStaffController.setSord(sord);
	}

	private PageInfo<HouseholdStaff> emptyPage(int pageSize) {
		PageInfo<HouseholdStaff> pageInfo = new PageInfo<HouseholdStaff>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<HouseholdStaff>());
		return pageInfo;
	}

	@Override
	public String addHouseholdStaffForMobile() throws Exception {
		if (population.getIsHaveHouse() && houseInfo.getId() == null) {
			houseInfo
					.setOrganization(organizationDubboService.getFullOrgById(orgId));
			actualHouseController.setHouseInfo(houseInfo);
			actualHouseController.setRental(rentalHouse);
			actualHouseController.addHouseInfoForMobile();
			houseInfo = actualHouseController.getHouseInfo();
		}
		if (houseInfo != null && houseInfo.getId() != null) {
			population.setHouseId(houseInfo.getId());
		}

		population = householdStaffService
				.addHouseholdStaffForMobile(population);
		return SUCCESS;
	}

	@Override
	public String updateHouseholdStaffForMobile() throws Exception {
		if (population.getIsHaveHouse() && houseInfo.getId() != null
				&& houseInfo.getId() > 0) {
			houseInfo
					.setOrganization(organizationDubboService.getFullOrgById(orgId));
			actualHouseController.setHouseInfo(houseInfo);
			actualHouseController.setRental(rentalHouse);
			actualHouseController.updateHouseInfoForMobile();
			houseInfo = actualHouseController.getHouseInfo();
		}
		population = householdStaffService.updateHouseholdStaff(population);
		return SUCCESS;
	}

	public HouseholdStaffVo getHouseholdStaffVo() {
		return householdStaffVo;
	}

	public void setHouseholdStaffVo(HouseholdStaffVo householdStaffVo) {
		this.householdStaffVo = householdStaffVo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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

	public HouseholdStaff getPopulation() {
		return population;
	}

	public void setPopulation(HouseholdStaff population) {
		this.population = population;
	}
}
