package com.tianque.gis.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.gis.service.HouseService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("request")
@SuppressWarnings("serial")
@Controller("houseController")
@Namespace("/gis/houseManage")
public class HouseController extends BaseAction {

	@Autowired
	private HouseService houseService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Long orgId;

	private String address;

	private String houseIds;

	private List<Long> houseInfoIds;

	private Long buildingId;

	private Double pointX;

	private Double pointY;

	private Long houseId;

	@Action(value = "houseInfoGisList", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findHouseInfosForGisPage() throws Exception {
		orgId = ThreadVariable.getSession().getOrganization().getId();
		if (null == orgId) {
			gridPage = new GridPage(new PageInfo());
			return SUCCESS;
		}
		PageInfo pageInfo = ControllerHelper.processAllOrgRelativeName(
				houseService.findHouseInfosForPage(orgId, address, page, rows,
						sidx, sord, "1"), organizationDubboService,
				new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "updateHouseGisInfo", results = { @Result(type = "json", name = "success", params = {
			"root", "houseInfoIds", "ignoreHierarchy", "false" }) })
	public String updateHouseGisInfo() throws Exception {
		if (houseIds == null || houseIds == "") {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		String[] houseId = houseIds.split(",");
		List<Long> idList;
		if (houseId[0].equals("")) {
			idList = initTargetId(houseId, 1);
		} else {
			idList = initTargetId(houseId, 0);
		}
		houseInfoIds = houseService.updateHouseGisInfo(idList, buildingId,
				pointX, pointY);
		return SUCCESS;
	}

	@Action(value = "findHouseInfosByBuildingIdForPage", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findHouseInfosByBuildingIdForPage() throws Exception {
		if (null == buildingId || buildingId < 1L) {
			gridPage = new GridPage(new PageInfo());
			return SUCCESS;
		}
		PageInfo pageInfo = ControllerHelper.processAllOrgRelativeName(
				houseService.findHouseInfosByBuildingIdForPage(buildingId,
						page, rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, orgId);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	@Action(value = "unbindHousePropertyById", results = { @Result(type = "json", name = "success", params = {
			"root", "true", "ignoreHierarchy", "false" }) })
	public String unbindHousePropertyById() throws Exception {
		if (houseId == null) {
			this.errorMessage = "参数错误";
		}
		houseService.unbindHousePropertyById(houseId);
		return SUCCESS;
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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHouseIds() {
		return houseIds;
	}

	public void setHouseIds(String houseIds) {
		this.houseIds = houseIds;
	}

	public List<Long> getHouseInfoIds() {
		return houseInfoIds;
	}

	public void setHouseInfoIds(List<Long> houseInfoIds) {
		this.houseInfoIds = houseInfoIds;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public Double getPointX() {
		return pointX;
	}

	public void setPointX(Double pointX) {
		this.pointX = pointX;
	}

	public Double getPointY() {
		return pointY;
	}

	public void setPointY(Double pointY) {
		this.pointY = pointY;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

}
