package com.tianque.fourTeams.fourTeamsIssue.domain;

import java.io.Serializable;

import org.apache.struts2.json.JSONUtil;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.domain.PropertyDict;

/**
 * 四支队伍事件类型越级规则设置:实体类(FOURTEAMS_ISSUE_SKIPRULE)
 * 
 * @author
 * 
 */
public class FourTeamsIssueSkiprule extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 是否短信提醒(0否1是)(MESSAGE_FLAG) */
	private String messageFlag = "0";
	/** 所属网格(ORG_ID) */
	private Long orgId;
	/** 事件所属类型(小类)id(ISSUE_TYPE_ID) */
	private Long issueTypeId;
	private String issueTypeName;
	/** 事件所属类型(大类)id(ISSUE_TYPE_DOMAIN_ID) */
	private Long issueTypeDomainId;
	private String issueTypeDomainName;
	/** 审核流程(重大紧急等级)(ISSUE_URGENT_LEVEL) */
	private PropertyDict issueUrgentLevel;
	/** 上报单位 */
	private Long submitOrgId;
	private Long submitOrgLevel;
	private String submitOrgName;
	/** 所属网格编号(ORG_INTERNAL_CODE) */
	private String orgInternalCode;
	/** 抄送单位id数组(CC_ORG_IDS) */
	private String ccOrgIds;
	private String ccOrgNames;
	/** 上报层级 */
	private PropertyDict submitLevel;

	public FourTeamsIssueSkiprule() {

	}

	public FourTeamsIssueSkiprule(String messageFlag, Long orgId,
			Long issueTypeId, Long issueTypeDomainId,
			PropertyDict issueUrgentLevel, Long submitOrgId,
			String orgInternalCode, String ccOrgIds, PropertyDict submitLevel) {
		this.messageFlag = messageFlag;
		this.orgId = orgId;
		this.issueTypeId = issueTypeId;
		this.issueTypeDomainId = issueTypeDomainId;
		this.issueUrgentLevel = issueUrgentLevel;
		this.submitOrgId = submitOrgId;
		this.orgInternalCode = orgInternalCode;
		this.ccOrgIds = ccOrgIds;
		this.submitLevel = submitLevel;
	}

	/** get 是否短信提醒(0否1是)(messageFlag) */
	public String getMessageFlag() {
		return messageFlag;
	}

	/** set 是否短信提醒(0否1是)(MESSAGE_FLAG) */
	public void setMessageFlag(String messageFlag) {
		this.messageFlag = messageFlag;
	}

	/** get 所属网格(orgId) */
	public Long getOrgId() {
		return orgId;
	}

	/** set 所属网格(ORG_ID) */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/** get 事件所属类型(小类)id(issueTypeId) */
	public Long getIssueTypeId() {
		return issueTypeId;
	}

	/** set 事件所属类型(小类)id(ISSUE_TYPE_ID) */
	public void setIssueTypeId(Long issueTypeId) {
		this.issueTypeId = issueTypeId;
	}

	/** get 事件所属类型(大类)id(issueTypeDomainId) */
	public Long getIssueTypeDomainId() {
		return issueTypeDomainId;
	}

	/** set 事件所属类型(大类)id(ISSUE_TYPE_DOMAIN_ID) */
	public void setIssueTypeDomainId(Long issueTypeDomainId) {
		this.issueTypeDomainId = issueTypeDomainId;
	}

	/** get 审核流程(重大紧急等级)(issueUrgentLevel) */
	public PropertyDict getIssueUrgentLevel() {
		return issueUrgentLevel;
	}

	/** set 审核流程(重大紧急等级)(ISSUE_URGENT_LEVEL) */
	public void setIssueUrgentLevel(PropertyDict issueUrgentLevel) {
		this.issueUrgentLevel = issueUrgentLevel;
	}

	/** get 上报单位id(submitOrgId) */
	public Long getSubmitOrgId() {
		return submitOrgId;
	}

	/** set 上报单位id(SUBMIT_ORG_ID) */
	public void setSubmitOrgId(Long submitOrgId) {
		this.submitOrgId = submitOrgId;
	}

	/** get 所属网格编号(orgInternalCode) */
	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	/** set 所属网格编号(ORG_INTERNAL_CODE) */
	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	/** get 抄送单位id数组(ccOrgIds) */
	public String getCcOrgIds() {
		return ccOrgIds;
	}

	/** set 抄送单位id数组(CC_ORG_IDS) */
	public void setCcOrgIds(String ccOrgIds) {
		this.ccOrgIds = ccOrgIds;
	}

	public String getIssueTypeName() {
		return issueTypeName;
	}

	public void setIssueTypeName(String issueTypeName) {
		this.issueTypeName = issueTypeName;
	}

	public String getIssueTypeDomainName() {
		return issueTypeDomainName;
	}

	public void setIssueTypeDomainName(String issueTypeDomainName) {
		this.issueTypeDomainName = issueTypeDomainName;
	}

	public String getSubmitOrgName() {
		return submitOrgName;
	}

	public void setSubmitOrgName(String submitOrgName) {
		this.submitOrgName = submitOrgName;
	}

	public String getCcOrgNames() {
		return ccOrgNames;
	}

	public void setCcOrgNames(String ccOrgNames) {
		this.ccOrgNames = ccOrgNames;
	}

	public PropertyDict getSubmitLevel() {
		return submitLevel;
	}

	public void setSubmitLevel(PropertyDict submitLevel) {
		this.submitLevel = submitLevel;
	}

	public Long getSubmitOrgLevel() {
		return submitOrgLevel;
	}

	public void setSubmitOrgLevel(Long submitOrgLevel) {
		this.submitOrgLevel = submitOrgLevel;
	}

	public String toString() {
		try {
			return JSONUtil.serialize(this);
		} catch (Exception e) {
			return super.toString();
		}
	}

	public String getEncryptId() {
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), null, null);
	}
}
