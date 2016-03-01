package com.tianque.partyBuilding.volunteerTeam.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.domain.Organization;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.volunteerTeam.domain.VolunteerMember;
import com.tianque.partyBuilding.volunteerTeam.domain.vo.SearchVolunteerMemberVo;
import com.tianque.partyBuilding.volunteerTeam.service.VolunteerMemberService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 党员志愿者队伍成员表:服务前端控制类
 * 
 * @author
 * @date 2014-02-12 10:48:16
 */
@Namespace("/volunteerMemberManage")
@Scope("request")
@Controller("volunteerMemberController")
public class VolunteerMemberController extends BaseAction {

	@Autowired
	private VolunteerMemberService volunteerMemberService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private VolunteerMember volunteerMember;
	private Member member;
	private SearchVolunteerMemberVo searchVo;
	private String ids;
	private String memberIds;
	private Long teamId;
	private Long id;
	private Long orgId;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/partyBuilding/volunteerTeam/volunteerMemberDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/volunteerTeam/maintainMemberDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "partyMemberVolunteerTeamManagement")
	public String dispatch() throws Exception {
		if (memberIds != null) {
			getMemberById();
		}
		if (mode.equals("view")) {
			return "view";
		}
		return SUCCESS;
	}

	/**
	 * 根据ID获取实体对象
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	@Action(value = "getMemberById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"member", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getMemberById() throws Exception {
		if (memberIds == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		member = volunteerMemberService
				.getMemberById(Long.parseLong(memberIds));
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	@Action(value = "addVolunteerMember", results = {
			@Result(name = "success", type = "json", params = { "root",
					"volunteerMember", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addVolunteerTeamMember")
	public String addVolunteerMember() throws Exception {
		if (volunteerMember == null || volunteerMember.getOrgId() == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(volunteerMember.getOrgId());
		volunteerMember.setOrgInternalCode(organization.getOrgInternalCode());
		volunteerMember.setOrganization(organization);
		if (StringUtil.isStringAvaliable(memberIds)) {
			String[] memberIdsA = memberIds.split(",");
			for (int i = 0; i < memberIdsA.length; i++) {
				volunteerMember.setMemberId(Long.parseLong(memberIdsA[i]));
				volunteerMember = volunteerMemberService.add(volunteerMember);
			}
		}
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 *            对象
	 */
	@Action(value = "updateVolunteerMember", results = {
			@Result(name = "success", type = "json", params = { "root",
					"volunteerMember", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateVolunteerTeamMember")
	public String updateVolunteerMember() throws Exception {
		if (volunteerMember == null || volunteerMember.getId() == null
				|| !checkOrganization(volunteerMember.getOrganization())) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		volunteerMember = volunteerMemberService.update(volunteerMember);
		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 * 
	 * @param id
	 *            记录ID
	 */
	@Action(value = "deleteByTeamIdAndMemberId", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteVolunteerTeamMember")
	public String deleteByTeamIdAndMemberId() throws Exception {
		if (memberIds == null) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] memberIdsA = memberIds.split(",");
		for (int i = 0; i < memberIdsA.length; i++) {
			volunteerMemberService.deleteByTeamIdAndMemberId(teamId,
					Long.parseLong(memberIdsA[i]), orgId);
		}
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 * 
	 * @param ids
	 *            ID数组
	 */
	@Action(value = "deleteVolunteerMemberByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteVolunteerTeamMember")
	public String deleteVolunteerMemberByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		volunteerMemberService.delete(idsLong);
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 */
	@Action(value = "findMemberPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "partyMemberVolunteerTeamManagement")
	public String findMemberPagerBySearchVo() throws Exception {
		if (searchVo == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (searchVo.getOrgId() != null) {
			Organization organization = organizationDubboService
					.getSimpleOrgById(searchVo.getOrgId());
			searchVo.setOrgInternalCode(organization.getOrgInternalCode());
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				volunteerMemberService.findPagerBySearchVo(searchVo, page,
						rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, orgId));
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 * 
	 */
	@Action(value = "findMembersByOrgCodeAndTeamId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "partyMemberVolunteerTeamManagement")
	public String findMembersByOrgCodeAndTeamId() throws Exception {
		if (searchVo == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		if (searchVo.getOrgId() != null) {
			Organization organization = organizationDubboService
					.getSimpleOrgById(searchVo.getOrgId());
			searchVo.setOrgInternalCode(organization.getOrgInternalCode());
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				volunteerMemberService.findMembersByOrgCodeAndTeamId(searchVo,
						page, rows, sidx, sord), organizationDubboService,
				new String[] { "organization" }, orgId));
		return SUCCESS;
	}

	private boolean checkOrganization(Organization org) {
		return (org != null && org.getId() != null);
	}

	public VolunteerMember getVolunteerMember() {
		return volunteerMember;
	}

	public void setVolunteerMember(VolunteerMember volunteerMember) {
		this.volunteerMember = volunteerMember;
	}

	public SearchVolunteerMemberVo getSearchVo() {
		return searchVo;
	}

	public void setSearchVo(SearchVolunteerMemberVo searchVo) {
		this.searchVo = searchVo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(String memberIds) {
		this.memberIds = memberIds;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
