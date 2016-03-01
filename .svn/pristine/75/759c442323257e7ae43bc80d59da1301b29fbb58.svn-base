package com.tianque.baseInfo.orgLocationTrack.controter;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.orgLocationTrack.domain.OrgLocationTracks;
import com.tianque.baseInfo.orgLocationTrack.service.OrgLocationTracksService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("prototype")
@Controller("orgLocationTracksController")
@Namespace("/baseinfo/orgLocationTracksManage")
@Transactional
public class OrgLocationTracksController extends BaseAction {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private OrgLocationTracksService orgLocationTracksService;
	private Integer orgLocationOrgId;
	private String orgLocationName;

	@Action(value = "findOrgLocationTracksByOrgIdAndName", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findOrgLocationTracksByOrgIdAndName() throws Exception {
		PageInfo<OrgLocationTracks> pageInfo = ControllerHelper
				.processAllOrgRelativeName(orgLocationTracksService
						.findOrgLocationTracksByOrgIdAndName(orgLocationOrgId,
								orgLocationName, page, rows, sidx, sord),
						organizationDubboService, new String[] {
								"orgLocationOrganization",
								"oldOrgLocationOrganization",
								"operationOrganization" }, null);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public Integer getOrgLocationOrgId() {
		return orgLocationOrgId;
	}

	public void setOrgLocationOrgId(Integer orgLocationOrgId) {
		this.orgLocationOrgId = orgLocationOrgId;
	}

	public String getOrgLocationName() {
		return orgLocationName;
	}

	public void setOrgLocationName(String orgLocationName) {
		this.orgLocationName = orgLocationName;
	}

}