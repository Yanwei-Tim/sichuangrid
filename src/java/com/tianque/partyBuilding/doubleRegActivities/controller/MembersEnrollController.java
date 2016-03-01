package com.tianque.partyBuilding.doubleRegActivities.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.tianque.partyBuilding.doubleRegActivities.constant.YesOrNoType;
import com.tianque.partyBuilding.doubleRegActivities.domain.MemberHasVolunteerJobs;
import com.tianque.partyBuilding.doubleRegActivities.domain.MembersEnroll;
import com.tianque.partyBuilding.doubleRegActivities.domain.VolunteerJobs;
import com.tianque.partyBuilding.doubleRegActivities.service.MemberHasVolunteerJobsService;
import com.tianque.partyBuilding.doubleRegActivities.service.MembersEnrollService;
import com.tianque.partyBuilding.doubleRegActivities.service.VolunteerJobsService;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.members.service.MemberService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("request")
@Namespace("/partyBuildng/doubleRegActivities")
@Controller("membersEnrollController")
public class MembersEnrollController extends BaseAction {
	@Autowired
	private MembersEnrollService membersEnrollService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberHasVolunteerJobsService memberHasVolunteerJobsService;
	@Autowired
	private VolunteerJobsService volunteerJobsService;

	private MembersEnroll membersEnroll;
	private Long orgId;
	private String ids;
	private String regActivitiesType;

	private Map<String, String> memberVolunteerJobsMap;

	private MemberHasVolunteerJobs memberHasVolunteerJobs;

	private String memberVolunteerJobsIds;

	private Long id;
	private Long logoutStatus;
	private Long isEnroll;

	// 查询
	@Action(value = "findMembersEnrollForPageByCondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findMembersEnrollForPageByCondition() throws Exception {
		if (membersEnroll == null
				|| membersEnroll.getRegActivitiesType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		if (orgId == null) {
			membersEnroll.setOrganization(ThreadVariable.getOrganization());
			membersEnroll.setOrgInternalCode(ThreadVariable.getOrganization()
					.getOrgInternalCode());
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(orgId);
			if (organization == null) {
				errorMessage = "网格不存在";
				return ERROR;
			}
			membersEnroll.setOrganization(organization);
			membersEnroll.setOrgInternalCode(organization.getOrgInternalCode());
		}

		gridPage = new GridPage<MembersEnroll>(
				membersEnrollService.findMembersEnrollForPage(membersEnroll,
						new Pager(page, rows, sidx, sord)));

		return SUCCESS;
	}

	@Action(value = "dispatch", results = {
			@Result(name = "edit", location = "/partyBuilding/doubleRegActivities/memberEnroll/memberEnrollDlg.jsp"),
			@Result(name = "view", location = "/partyBuilding/doubleRegActivities/memberEnroll/memberEnrollView.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (membersEnroll == null
				|| membersEnroll.getRegActivitiesType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			membersEnroll = membersEnrollService
					.getMembersEnroll(membersEnroll);
			if (DialogMode.EDIT_MODE.equals(mode)
					|| YesOrNoType.YES_VALUE
							.equals(membersEnroll.getIsEnroll())) {
				memberVolunteerJobsMap = getMemberHasVolunteerJobsName(membersEnroll
						.getId());
				return mode;
			}
			errorMessage = "该党员未双报到，不能查看详情!";
			return ERROR;
		}
		return DialogMode.EDIT_MODE;
	}

	// 保存
	@Action(value = "editMember", results = {
			@Result(name = "success", type = "json", params = { "root",
					"membersEnroll", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String editMember() throws Exception {
		if (membersEnroll == null
				|| membersEnroll.getRegActivitiesType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		if (membersEnroll != null && membersEnroll.getId() != null) {
			membersEnrollService.editUpdateMember(membersEnroll);
			memberHasVolunteerJobs = splitMemberHasVolunteerJobsName(membersEnroll);// 重新添加
		} else {
			membersEnrollService.editAddMember(membersEnroll);
			memberHasVolunteerJobs = splitMemberHasVolunteerJobsName(membersEnroll);
		}
		return SUCCESS;
	}

	// 删除
	@Action(value = "deleteByIds", results = {
			@Result(name = "success", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String delete() throws Exception {
		if (!StringUtil.isStringAvaliable(ids)
				|| membersEnroll.getRegActivitiesType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		boolean bool = membersEnrollService.deleteMembersEnroll(
				analyzeToList(ids), membersEnroll.getRegActivitiesType());
		if (bool != true) {
			errorMessage = "删除失败!";
			return ERROR;
		}
		return SUCCESS;
	}

	// 报到
	@Action(value = "enroll", results = {
			@Result(name = "success", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String enroll() throws Exception {
		if (membersEnroll == null
				|| membersEnroll.getRegActivitiesType() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		MembersEnroll enroll = membersEnrollService
				.getMembersEnrollById(membersEnroll.getId());
		if (enroll != null && enroll.getLogoutStatus() != null
				&& enroll.getLogoutStatus().equals(YesOrNoType.NO_VALUE)) {
			errorMessage = "该党员已注销，不能进行报到!";
			return ERROR;
		} else if (enroll != null && enroll.getIsEnroll() != null
				&& enroll.getIsEnroll().equals(YesOrNoType.YES_VALUE)) {
			errorMessage = "该党员已报到，不能重复报到!";
			return ERROR;
		} else {
			membersEnrollService.update(membersEnroll);
		}
		return SUCCESS;
	}

	// 验证
	@Action(value = "exsistedIdCard", results = {
			@Result(name = "success", type = "json", params = { "root",
					"membersEnroll", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String exsistedIdCard() throws Exception {
		if (membersEnroll == null
				|| membersEnroll.getMember().getIdCardNo() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}

		MembersEnroll enroll = membersEnrollService
				.getMembersEnrollByIdCardNo(membersEnroll.getMember()
						.getIdCardNo());
		if (null != enroll
				&& (YesOrNoType.NO_VALUE.equals(enroll.getLogoutStatus()) || YesOrNoType.YES_VALUE
						.equals(enroll.getIsEnroll()))) {
			membersEnroll = new MembersEnroll();
		} else {
			Member member = memberService.findByIdCardNo(membersEnroll
					.getMember().getIdCardNo());
			if (member != null) {
				if (enroll != null) {
					enroll.setIsEnroll(YesOrNoType.YES_VALUE);
					membersEnroll = enroll;
				}
				membersEnroll.setMember(member);
			}
		}
		return SUCCESS;
	}

	// 注销/取消注销
	@Action(value = "logout", results = {
			@Result(name = "success", type = "json", params = { "root",
					"membersEnroll", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String logout() throws Exception {
		if (id == null || logoutStatus == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		membersEnrollService.logoutMemberEnroll(id, logoutStatus, isEnroll);
		return SUCCESS;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	private Map<String, String> getMemberHasVolunteerJobsName(Long id) {
		Map<String, String> memberVolunteerJobsMap = new HashMap<String, String>();
		List<MemberHasVolunteerJobs> list = memberHasVolunteerJobsService
				.getMemberHasVolunteerJobsById(id);
		if (list == null || list.size() == 0) {
			return memberVolunteerJobsMap;
		}
		StringBuffer memberVolunteerJobsIds = new StringBuffer();
		StringBuffer memberVolunteerJobsNames = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			VolunteerJobs volunteerJobs = volunteerJobsService.get(list.get(i)
					.getVolunteerJobsId());
			if (volunteerJobs != null) {
				memberVolunteerJobsNames.append(volunteerJobs.getName() + ",");
				memberVolunteerJobsIds.append(volunteerJobs.getId() + ",");
			}
		}
		memberVolunteerJobsMap.put(
				"memberVolunteerJobsIds",
				new String(memberVolunteerJobsIds.substring(0,
						memberVolunteerJobsIds.length() - 1)));
		memberVolunteerJobsMap.put(
				"memberVolunteerJobsNames",
				new String(memberVolunteerJobsNames.substring(0,
						memberVolunteerJobsNames.length() - 1)));
		return memberVolunteerJobsMap;
	}

	/**
	 * 解析字符串添加关联信息
	 * 
	 * @return
	 */
	public MemberHasVolunteerJobs splitMemberHasVolunteerJobsName(
			MembersEnroll membersEnroll) {
		MemberHasVolunteerJobs hasVolunteerJobs = new MemberHasVolunteerJobs();
		if (StringUtils.isBlank(memberVolunteerJobsIds)) {
			return hasVolunteerJobs;
		}
		String[] volunteerJobsIds = memberVolunteerJobsIds.split(",");
		if (volunteerJobsIds != null && volunteerJobsIds.length > 0) {
			for (String id : volunteerJobsIds) {
				hasVolunteerJobs.setVolunteerJobsId(Long.valueOf(id));
				hasVolunteerJobs.setMembersId(membersEnroll.getId());
				memberHasVolunteerJobsService
						.addMemberHasVolunteerJobs(hasVolunteerJobs);
			}
		}
		return hasVolunteerJobs;
	}

	public MembersEnroll getMembersEnroll() {
		return membersEnroll;
	}

	public void setMembersEnroll(MembersEnroll membersEnroll) {
		this.membersEnroll = membersEnroll;
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

	public String getRegActivitiesType() {
		return regActivitiesType;
	}

	public void setRegActivitiesType(String regActivitiesType) {
		this.regActivitiesType = regActivitiesType;
	}

	public MemberHasVolunteerJobs getMemberHasVolunteerJobs() {
		return memberHasVolunteerJobs;
	}

	public void setMemberHasVolunteerJobs(
			MemberHasVolunteerJobs memberHasVolunteerJobs) {
		this.memberHasVolunteerJobs = memberHasVolunteerJobs;
	}

	public Map<String, String> getMemberVolunteerJobsMap() {
		return memberVolunteerJobsMap;
	}

	public void setMemberVolunteerJobsMap(
			Map<String, String> memberVolunteerJobsMap) {
		this.memberVolunteerJobsMap = memberVolunteerJobsMap;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLogoutStatus() {
		return logoutStatus;
	}

	public void setLogoutStatus(Long logoutStatus) {
		this.logoutStatus = logoutStatus;
	}

	public Long getIsEnroll() {
		return isEnroll;
	}

	public void setIsEnroll(Long isEnroll) {
		this.isEnroll = isEnroll;
	}

	public String getMemberVolunteerJobsIds() {
		return memberVolunteerJobsIds;
	}

	public void setMemberVolunteerJobsIds(String memberVolunteerJobsIds) {
		this.memberVolunteerJobsIds = memberVolunteerJobsIds;
	}
}
