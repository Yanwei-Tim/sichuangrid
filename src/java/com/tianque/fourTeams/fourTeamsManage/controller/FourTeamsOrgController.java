package com.tianque.fourTeams.fourTeamsManage.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeamsOrg;
import com.tianque.fourTeams.fourTeamsManage.service.FourTeamsOrgService;
import com.tianque.fourTeams.fourTeamsManage.util.FourteamsUitl;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/fourTeamsOrgManage")
@Scope("request")
@Controller("fourTeamsOrgController")
public class FourTeamsOrgController extends BaseAction {

	@Autowired
	private FourTeamsOrgService fourTeamsOrgService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private List<FourTeamsOrg> fourTeamsOrgList; // 页面显示List
	private FourTeamsOrg fourTeamsOrg; // 四支队伍组织机构
	private Long orgId;
	private String orgLevel;

	@Action(value = "queryFourTeamsOrgForList", results = {
			@Result(name = "success", location = "/fourTeamsManage/fourTeamsOrgChar.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String queryFourTeamsOrgForList() throws Exception {
		if (fourTeamsOrg == null || fourTeamsOrg.getOrganization() == null
				|| fourTeamsOrg.getOrganization().getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		fourTeamsOrgList = fourTeamsOrgService
				.queryFourTeamsOrgForList(fourTeamsOrg);
		if (fourTeamsOrgList == null || fourTeamsOrgList.size() <= 0) {
			// 没有找到创建县级视图
			fourTeamsOrgList = fourTeamsOrgService
					.initFourTeamsOrg(fourTeamsOrg);
		}
		if (null == orgLevel || "".equals(orgLevel)) {
			orgLevel = setTeamOrgLevel(fourTeamsOrg.getOrganization().getId());
		}
		return SUCCESS;
	}

	@Action(value = "getOrgLevelType", results = {
			@Result(name = "success", type = "json", params = { "root",
					"orgLevel" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String getOrgLevelType() throws Exception {
		if (orgId == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		orgLevel = setTeamOrgLevel(orgId);
		return SUCCESS;
	}

	private String setTeamOrgLevel(Long orgId) {
		Organization org = organizationDubboService.getFullOrgById(orgId);
		if (org == null) {
			throw new BusinessValidationException("参数错误");
		}
		int levelNumber = org.getOrgLevel().getInternalId();
		if (levelNumber >= OrganizationLevel.CITY) {
			orgLevel = FourteamsUitl.FOURTEAM_LEADER_LEVLE;
		} else if (levelNumber == OrganizationLevel.DISTRICT) {
			orgLevel = FourteamsUitl.FOURTEAM_DISTRICT_LEVLE;
		} else if (levelNumber < OrganizationLevel.DISTRICT) {
			orgLevel = FourteamsUitl.FOURTEAM_BASIC_LEVLE;
		}
		return orgLevel;
	}

	@Action(value = "getFourTeamsOrgObject", results = {
			@Result(name = "success", type = "json", params = { "root",
					"fourTeamsOrg" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String getFourTeamsOrgObject() throws Exception {
		if (fourTeamsOrg == null || fourTeamsOrg.getId() == null) {
			this.errorMessage = "参数错误！";
			return ERROR;
		}
		fourTeamsOrg = fourTeamsOrgService
				.getFourTeamsOrgByPrimaryKey(fourTeamsOrg.getId());
		return SUCCESS;
	}

	@Action(value = "editFourTeamsOrg", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "editOrganizationChar")
	public String editFourTeamsOrg() throws Exception {
		if (fourTeamsOrg == null) {
			this.errorMessage = "参数错误！";
			return ERROR;
		}
		fourTeamsOrgService
				.updateFourTeamsOrgByPrimaryKeySelective(fourTeamsOrg);
		return SUCCESS;
	}

	@Action(value = "emptyFourTeamsOrg", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "emptyOrganizationChar")
	public String emptyFourTeamsOrg() throws Exception {
		if (fourTeamsOrg == null || fourTeamsOrg.getId() == null) {
			this.errorMessage = "参数错误！";
			return ERROR;
		}
		fourTeamsOrg = fourTeamsOrgService
				.getFourTeamsOrgByPrimaryKey(fourTeamsOrg.getId());
		fourTeamsOrgService.updateFourTeamsOrgByPrimaryKey(fourTeamsOrg);
		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/fourTeamsManage/fourTeamsOrgDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (DialogMode.EDIT_MODE.equals(mode)) {
			if (fourTeamsOrg == null || fourTeamsOrg.getId() == null) {
				this.errorMessage = "参数错误！";
				return ERROR;
			}
			fourTeamsOrg = fourTeamsOrgService
					.getFourTeamsOrgByPrimaryKey(fourTeamsOrg.getId());
		}
		return SUCCESS;
	}

	public FourTeamsOrg getFourTeamsOrg() {
		return fourTeamsOrg;
	}

	public void setFourTeamsOrg(FourTeamsOrg fourTeamsOrg) {
		this.fourTeamsOrg = fourTeamsOrg;
	}

	public List<FourTeamsOrg> getFourTeamsOrgList() {
		return fourTeamsOrgList;
	}

	public void setFourTeamsOrgList(List<FourTeamsOrg> fourTeamsOrgList) {
		this.fourTeamsOrgList = fourTeamsOrgList;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}

}
