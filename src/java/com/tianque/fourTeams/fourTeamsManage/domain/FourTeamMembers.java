package com.tianque.fourTeams.fourTeamsManage.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class FourTeamMembers extends BaseDomain {

	private static final long serialVersionUID = 1L;

	/** 网格ID */
	private Organization Organization;
	/** 所属网格编号 */
	private String orgCode;
	/** 服务网格名称 */
	private String serviceName;
	/** 队伍名 */
	private String names;
	/** 队伍名全拼 */
	private String fullPinyin;
	/** 队伍名简拼 */
	private String simplePinyin;
	/** 队伍号 */
	private Long teamId;
	/** 职务 */
	private String duty;
	/** 性别 */
	private PropertyDict gender;
	/** 出生年月 */
	private Date birthday;
	/** 手机号 */
	private String mobile;
	/** 电话号 */
	private String telephone;
	/** 特长 */
	private String specialty;
	/** 网格管理员名 */
	private String orgAdminName;
	/** 政治面貌 */
	private PropertyDict politics;
	/** 网格管理员电话号 */
	private String orgAdminTelephone;
	/** 备注 */
	private String comments;

	/** 队伍成员所在单位 */
	private String memberDepartement;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getFullPinyin() {
		return fullPinyin;
	}

	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Organization getOrganization() {
		return Organization;
	}

	public void setOrganization(Organization organization) {
		Organization = organization;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getOrgAdminName() {
		return orgAdminName;
	}

	public void setOrgAdminName(String orgAdminName) {
		this.orgAdminName = orgAdminName;
	}

	public String getOrgAdminTelephone() {
		return orgAdminTelephone;
	}

	public void setOrgAdminTelephone(String orgAdminTelephone) {
		this.orgAdminTelephone = orgAdminTelephone;
	}

	public String getComments() {
		return comments;
	}

	public PropertyDict getPolitics() {
		return politics;
	}

	public void setPolitics(PropertyDict politics) {
		this.politics = politics;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}


	public String getMemberDepartement() {
		return memberDepartement;
	}

	public void setMemberDepartement(String memberDepartement) {
		this.memberDepartement = memberDepartement;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
