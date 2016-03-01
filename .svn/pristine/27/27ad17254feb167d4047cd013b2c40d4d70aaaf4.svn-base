package com.tianque.partyBuilding.members.controller;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.Pager;
import com.tianque.domain.Organization;
import com.tianque.partyBuilding.members.constant.MemberType;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.members.service.MemberAssociatePartyOrgService;
import com.tianque.partyBuilding.members.service.MemberService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("request")
@Namespace("/partyBuildng/memberManage")
@Controller("memberController")
public class MemberController extends BaseAction {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberAssociatePartyOrgService memberAssociatePartyOrgService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Member member;
	private String ids;
	private Map<String, Object> resultMap;
	private Integer memberCount;
	private Long orgId;
	private Integer partyOrgType;
	private String partyOrg;
	private Organization organization;

	private String modeType;

	@Action(value = "editMember", results = { @Result(name = "success", type = "json", params = {
			"root", "member", "ignoreHierarchy", "false" }) })
	public String editMember() throws Exception {
		if (member == null || member.getPartyOrgType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}

		if (member.getPartyOrgType().intValue() != MemberType.DOUBLE_REG_ACTIVITIES
				&& !StringUtil.isStringAvaliable(member.getPartyOrg())) {
			errorMessage = "所在党支部不能为空！";
			return ERROR;
		}
		member = memberService.editMember(member);
		return SUCCESS;
	}

	@Action(value = "dispatch", results = { @Result(name = "success", location = "/partyBuilding/members/maintainMemberDlg.jsp") })
	public String dispatch() throws Exception {
		if (member == null || member.getPartyOrgType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		if (DialogMode.ADD_MODE.equals(mode)) {
			member.setPartyOrg((java.net.URLDecoder.decode(
					member.getPartyOrg(), "UTF-8")));
		} else if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			member = memberService.getMemberByPartyOrgTypeAndId(member);
			if (member.getReportOrganization() != null) {
				organization = organizationDubboService.getFullOrgById(member
						.getReportOrganization().getId());
			}
		}
		return SUCCESS;
	}

	@Action(value = "findMembersForPageByCondition", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findMembersForPageByCondition() throws Exception {
		if (member == null || member.getPartyOrgType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		if (member.getOrgId() == null) {
			member.setOrgId(ThreadVariable.getOrganization().getId());
		}
		gridPage = new GridPage<Member>(memberService.findMembersForPage(
				member, new Pager(page, rows, sidx, sord)));
		return SUCCESS;
	}

	@Action(value = "deleteMemberByIds", results = { @Result(name = "success", type = "json", params = {
			"root", "opreationResult", "ignoreHierarchy", "false" }) })
	public String deleteMemberByIds() throws Exception {
		if (!StringUtil.isStringAvaliable(ids) || member == null
				|| member.getPartyOrgType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		opreationResult = memberService.deleteMemberByIds(
				member.getPartyOrgType(), analyzeToList(ids));
		return SUCCESS;
	}

	@Action(value = "exsistedIdCard", results = { @Result(name = "success", type = "json", params = {
			"root", "resultMap", "ignoreHierarchy", "false" }) })
	public String exsistedIdCard() throws Exception {
		if (member == null || member.getPartyOrgType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		resultMap = memberService.exsistedIdCard(member);
		return SUCCESS;
	}

	@Action(value = "exsistedMember", results = { @Result(name = "success", type = "json", params = {
			"root", "memberCount", "ignoreHierarchy", "false" }) })
	public String exsistedMember() throws Exception {
		memberCount = 0;
		if (!"".equals(partyOrg) && null != partyOrg) {
			String[] partyOrgArray = partyOrg.split(",");
			for (String partyOrgName : partyOrgArray) {
				memberCount += memberAssociatePartyOrgService
						.countByPartyOrgTypeAndPartyOrgAndOrgId(partyOrgType,
								orgId, partyOrgName);
			}
		}
		return SUCCESS;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getPartyOrgType() {
		return partyOrgType;
	}

	public void setPartyOrgType(Integer partyOrgType) {
		this.partyOrgType = partyOrgType;
	}

	public String getPartyOrg() {
		return partyOrg;
	}

	public void setPartyOrg(String partyOrg) {
		this.partyOrg = partyOrg;
	}

	public String getModeType() {
		return modeType;
	}

	public void setModeType(String modeType) {
		this.modeType = modeType;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
