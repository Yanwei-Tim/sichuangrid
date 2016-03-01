package com.tianque.mobile.baseInfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.controller.ActualHouseController;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchHouseInfoVo;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.baseInfo.ActualHouseMobileAdapter;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 手机端：住房信息
 */
@Transactional
@Scope("request")
@Controller("actualHouseMobileAdapter")
public class ActualHouseMobileAdapterImpl extends BaseMobileAction implements
		ActualHouseMobileAdapter {
	@Autowired
	private ActualHouseController actualHouseController;
	private SearchHouseInfoVo searchHouseInfoVo;
	private Long orgId;
	private String houseIds;
	private HouseInfo houseInfo;
	private RentalHouse rentalHouse;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public String addActualHouse() throws Exception {
		houseInfo.setOrganization(organizationDubboService.getFullOrgById(orgId));
		actualHouseController.setHouseInfo(houseInfo);
		actualHouseController.setRental(rentalHouse);
		actualHouseController.addHouseInfoForMobile();
		houseInfo = actualHouseController.getHouseInfo();
		return SUCCESS;
	}

	@Override
	public String deleteHouseInfo() throws Exception {
		actualHouseController.setHouseIds(houseIds);
		actualHouseController.deleteHouseInfo();
		return SUCCESS;
	}

	@Override
	public String updateActualHouse() throws Exception {
		houseInfo.setOrganization(organizationDubboService.getFullOrgById(orgId));
		actualHouseController.setHouseInfo(houseInfo);
		actualHouseController.setRental(rentalHouse);
		actualHouseController.updateHouseInfoForMobile();
		houseInfo = actualHouseController.getHouseInfo();
		return SUCCESS;
	}

	@Override
	public String findActualHouseList() throws Exception {
		if (null == orgId) {
			gridPage = new GridPage(new PageInfo<HouseInfo>());
			return SUCCESS;
		}
		if (searchHouseInfoVo == null) {
			searchHouseInfoVo = new SearchHouseInfoVo();
		}
		setControllerProperties();
		actualHouseController.searchHouseInfo();
		gridPage = actualHouseController.getGridPage();
		return SUCCESS;
	}

	private void setControllerProperties() {
		actualHouseController.setSearchHouseInfoVo(searchHouseInfoVo);
		actualHouseController.setOrgId(orgId);
		actualHouseController.setPage(page);
		actualHouseController.setRows(rows);
		actualHouseController.setSidx(sidx);
		actualHouseController.setSord(sord);
	}

	public HouseInfo getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}

	public String getHouseIds() {
		return houseIds;
	}

	public void setHouseIds(String houseIds) {
		this.houseIds = houseIds;
	}

	public SearchHouseInfoVo getSearchHouseInfoVo() {
		return searchHouseInfoVo;
	}

	public void setSearchHouseInfoVo(SearchHouseInfoVo searchHouseInfoVo) {
		this.searchHouseInfoVo = searchHouseInfoVo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public RentalHouse getRentalHouse() {
		return rentalHouse;
	}

	public void setRentalHouse(RentalHouse rentalHouse) {
		this.rentalHouse = rentalHouse;
	}
}
