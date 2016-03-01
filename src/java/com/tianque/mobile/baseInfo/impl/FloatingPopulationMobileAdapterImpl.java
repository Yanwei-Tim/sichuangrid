package com.tianque.mobile.baseInfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.controller.ActualHouseController;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.floatingPopulation.controller.FloatingPopulationController;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchFloatingPopulationVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.FloatingPopulationMobileAdapter;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 手机端：流入人口
 */
@Transactional
@Scope("request")
@Controller("floatingPopulationMobileAdapter")
public class FloatingPopulationMobileAdapterImpl extends BaseMobileAction
		implements FloatingPopulationMobileAdapter {

	@Autowired
	protected FloatingPopulationController floatingPopulationController;
	private SearchFloatingPopulationVo searchFloatingPopulationVo;
	private Long orgId;
	private HouseInfo houseInfo;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ActualHouseController actualHouseController;
	private RentalHouse rentalHouse;
	private FloatingPopulation population;
	@Autowired
	protected FloatingPopulationService floatingPopulationService;

	@Override
	public String findFloatingPopulationList() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(new PageInfo<FloatingPopulation>());
			return SUCCESS;
		}
		if (searchFloatingPopulationVo == null) {
			searchFloatingPopulationVo = new SearchFloatingPopulationVo();
		}
		setControllerProperties();
		floatingPopulationController.searchFloatingPopulation();
		gridPage = floatingPopulationController.getGridPage();
		return SUCCESS;
	}

	private void setControllerProperties() {
		floatingPopulationController
				.setSearchFloatingPopulationVo(searchFloatingPopulationVo);
		floatingPopulationController.setOrganizationId(orgId);
		floatingPopulationController.setPage(page);
		floatingPopulationController.setRows(rows);
		floatingPopulationController.setSidx(sidx);
		floatingPopulationController.setSord(sord);
	}

	@Override
	public String addFloatingPopulationForMobile() throws Exception {
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
		population = floatingPopulationService
				.addFloatingPopulationForMobile(population);
		return SUCCESS;
	}

	@Override
	public String updateFloatingPopulationForMobile() throws Exception {
		if (population.getIsHaveHouse() && houseInfo.getId() != null
				&& houseInfo.getId() > 0) {
			houseInfo
					.setOrganization(organizationDubboService.getFullOrgById(orgId));
			actualHouseController.setHouseInfo(houseInfo);
			actualHouseController.setRental(rentalHouse);
			actualHouseController.updateHouseInfoForMobile();
			houseInfo = actualHouseController.getHouseInfo();
		}
		population = floatingPopulationService
				.updateFloatingPopulation(population);
		return SUCCESS;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public SearchFloatingPopulationVo getSearchFloatingPopulationVo() {
		return searchFloatingPopulationVo;
	}

	public void setSearchFloatingPopulationVo(
			SearchFloatingPopulationVo searchFloatingPopulationVo) {
		this.searchFloatingPopulationVo = searchFloatingPopulationVo;
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

	public FloatingPopulation getPopulation() {
		return population;
	}

	public void setPopulation(FloatingPopulation population) {
		this.population = population;
	}
}
