package com.tianque.working.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

@SuppressWarnings("serial")
public class WorkDiary extends BaseDomain {
	/** 工作时间 */
	private Date workTime;
	/** 工作地点 */
	private String workPlace;
	/** 所属网格 */
	private Organization organization;
	/** 网格内置码 */
	private String orgInternalCode;
	/** 日志类型 */
	private PropertyDict diaryType;
	/** 工作内容 */
	private String workContent;
	/** 录入人员姓名 */
	private String workUserName;
	/** 对应的类型名称 */
	private String objectType;
	/** 对应的类型id */
	private Long objectId;

	@JSON(format = "yyyy-MM-dd")
	public Date getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public PropertyDict getDiaryType() {
		return diaryType;
	}

	public void setDiaryType(PropertyDict diaryType) {
		this.diaryType = diaryType;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public String getWorkUserName() {
		return workUserName;
	}

	public void setWorkUserName(String workUserName) {
		this.workUserName = workUserName;
	}
}
