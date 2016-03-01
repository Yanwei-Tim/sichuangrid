package com.tianque.baseInfo.houseTrack.controler;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.houseTrack.domain.HouseTracks;
import com.tianque.baseInfo.houseTrack.service.HouseTracksService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("prototype")
@Controller("houseTracksController")
@Namespace("/baseinfo/houseTracksManage")
@Transactional
public class HouseTracksController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private HouseTracksService houseTracksService;
	private Integer houseId;

	@Action(value = "findHouseTracksByHouseId", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findHouseTracksByHouseId() throws Exception {
		PageInfo<HouseTracks> pageInfo = ControllerHelper
				.processAllOrgRelativeName(houseTracksService
						.findHouseTracksByHouseId(houseId, page, rows, sidx,
								sord), organizationDubboService, new String[] {
						"houseOrganization", "oldHouseOrganization",
						"operationOrganization" }, null);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

}