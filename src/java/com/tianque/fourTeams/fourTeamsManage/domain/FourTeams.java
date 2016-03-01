package com.tianque.fourTeams.fourTeamsManage.domain;

import java.util.List;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class FourTeams extends BaseDomain {

	private static final long serialVersionUID = 1L;

	/** 网格ID */
	private Organization organization;
	/** 所属网格编号 */
	private String orgCode;
	/** 队伍名 */
	private String names;
	/** 主队伍名 */
	private String parentNames;
	/** 队伍名全拼 */
	private String fullPinyin;
	/** 队伍名简拼 */
	private String simplePinyin;
	/** 排序号 */
	private Integer indexId;
	/** 父队伍号 */
	private Long parentTeamId;
	/** 子队伍数 */
	private Long subTeamNumber;
	/** 子队伍数FORM */
	private Long subTeamNumberMin;
	/** 子队伍数TO */
	private Long subTeamNumberMax;
	/** 成员数 */
	private Long memberNumber;
	/** 成员数FORM */
	private Long memberNumberMin;
	/** 成员数TO */
	private Long memberNumberMax;
	/** 队伍类型 */
	private String teamType;
	/** 备注 */
	private String comments;
	/** 主队伍备注 */
	private String parentComments;
	/** 队伍所属部门的ID */
	private Long departementId;

	/** 队伍所属部门名称 */
	private String departementName;

	/** 主管部门 */
	private PropertyDict competentDepartment;
	/** 服务范围 */
	private String serviceArea;
	/** String元素为orgId集合的字符串表达式，如'1,2,3,4,5' */
	private List<String> orgIdsList;

	public PropertyDict getCompetentDepartment() {
		return competentDepartment;
	}

	public void setCompetentDepartment(PropertyDict competentDepartment) {
		this.competentDepartment = competentDepartment;
	}

	public String getServiceArea() {
		return serviceArea;
	}

	public void setServiceArea(String serviceArea) {
		this.serviceArea = serviceArea;
	}

	public String getDepartementName() {
		return departementName;
	}

	public void setDepartementName(String departementName) {
		this.departementName = departementName;
	}

	public Long getDepartementId() {
		return departementId;
	}

	public void setDepartementId(Long departementId) {
		this.departementId = departementId;
	}

	public Integer getIndexId() {
		return indexId;
	}

	public void setIndexId(Integer indexId) {
		this.indexId = indexId;
	}

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
		if (fullPinyin != null && fullPinyin.length() > 30) {
			fullPinyin = fullPinyin.substring(0, 30);
		}
		this.fullPinyin = fullPinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	public void setSimplePinyin(String simplePinyin) {
		if (simplePinyin != null && simplePinyin.length() > 10) {
			simplePinyin = simplePinyin.substring(0, 10);
		}
		this.simplePinyin = simplePinyin;
	}

	public Long getParentTeamId() {
		return parentTeamId;
	}

	public void setParentTeamId(Long parentTeamId) {
		this.parentTeamId = parentTeamId;
	}

	public Long getSubTeamNumber() {
		return subTeamNumber;
	}

	public void setSubTeamNumber(Long subTeamNumber) {
		this.subTeamNumber = subTeamNumber;
	}

	public Long getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(Long memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getTeamType() {
		return teamType;
	}

	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getParentNames() {
		return parentNames;
	}

	public void setParentNames(String parentNames) {
		this.parentNames = parentNames;
	}

	public String getParentComments() {
		return parentComments;
	}

	public void setParentComments(String parentComments) {
		this.parentComments = parentComments;
	}

	public Long getSubTeamNumberMin() {
		return subTeamNumberMin;
	}

	public void setSubTeamNumberMin(Long subTeamNumberMin) {
		this.subTeamNumberMin = subTeamNumberMin;
	}

	public Long getSubTeamNumberMax() {
		return subTeamNumberMax;
	}

	public void setSubTeamNumberMax(Long subTeamNumberMax) {
		this.subTeamNumberMax = subTeamNumberMax;
	}

	public Long getMemberNumberMin() {
		return memberNumberMin;
	}

	public void setMemberNumberMin(Long memberNumberMin) {
		this.memberNumberMin = memberNumberMin;
	}

	public Long getMemberNumberMax() {
		return memberNumberMax;
	}

	public void setMemberNumberMax(Long memberNumberMax) {
		this.memberNumberMax = memberNumberMax;
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(super.getId(), null,
				null);
	}

	public List<String> getOrgIdsList() {
		return orgIdsList;
	}

	public void setOrgIdsList(List<String> orgIdsList) {
		this.orgIdsList = orgIdsList;
	}

}
