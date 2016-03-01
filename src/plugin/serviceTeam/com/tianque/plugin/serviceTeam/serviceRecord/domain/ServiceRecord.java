package com.tianque.plugin.serviceTeam.serviceRecord.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/** 服务记录 **/
public class ServiceRecord extends BaseDomain {

	/** 所属网格 **/
	private Organization organization;

	/** 登陆者组织机构id(用于新增记录时的工作日志添加) */
	private Long userOrgId;

	/** 服务团队id **/
	private Long teamId;

	/** 服务时间 **/
	private Date occurDate;

	/** 服务地点 **/
	private String occurPlace;

	/** 服务成员(记录所属人) **/
	private String serviceMembers;

	/** 参与人员 **/
	private String serviceJoiners;

	/** 服务对象 **/
	private String serviceObjects;

	/** 记录类型（排查类、整改类等） **/
	private Long recordType;

	/** 服务内容 **/
	private String serviceContent;

	/** 附件名称（数组） **/
	private String[] attachFileNames;

	/** 服务对象id（数组） **/
	private Long[] objectIds;

	/** 服务对象名称 **/
	private String objectNames;

	/** 服务对象类型 **/
	private String objectType;
	/** 所属人id */
	private Long[] memberIds;
	/** 所属人姓名 */
	private String memberNames;

	public Long[] getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(Long[] memberIds) {
		this.memberIds = memberIds;
	}

	public String getMemberNames() {
		return memberNames;
	}

	public void setMemberNames(String memberNames) {
		this.memberNames = memberNames;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}

	public Long[] getObjectIds() {
		return objectIds;
	}

	public void setObjectIds(Long[] objectIds) {
		this.objectIds = objectIds;
	}

	public String getObjectNames() {
		return objectNames;
	}

	public void setObjectNames(String objectNames) {
		this.objectNames = objectNames;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getServiceJoiners() {
		return serviceJoiners;
	}

	public void setServiceJoiners(String serviceJoiners) {
		this.serviceJoiners = serviceJoiners;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getOccurDate() {
		return occurDate;
	}

	public String getOccurPlace() {
		return occurPlace;
	}

	public String getServiceMembers() {
		return serviceMembers;
	}

	public String getServiceObjects() {
		return serviceObjects;
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public void setOccurPlace(String occurPlace) {
		this.occurPlace = occurPlace;
	}

	public void setServiceMembers(String serviceMembers) {
		this.serviceMembers = serviceMembers;
	}

	public void setServiceObjects(String serviceObjects) {
		this.serviceObjects = serviceObjects;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getRecordType() {
		return recordType;
	}

	public void setRecordType(Long recordType) {
		this.recordType = recordType;
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

}
