package com.tianque.working.domain;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

public class DocumentsHasOrg {
	private Long index;// 为了解决在页面上同一个组织机构多选的问题
	// 关联表主键
	private Long documentsHasOrgId;
	private Long documentId;

	private Long organizationId;

	private String signState;

	private Date signDate;

	private String signer;
	private String receiptContent;
	// 发送类型：主送或者抄送
	private String status;
	// 用以判断职能部门或行政部门
	private String orgStatus;
	// 部门orgname
	private String orgName;
	/** String元素为orgId集合的字符串表达式，如'1,2,3,4,5' */
	private List<String> orgIdsList;

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	public String getReceiptContent() {
		return receiptContent;
	}

	public void setReceiptContent(String receiptContent) {
		this.receiptContent = receiptContent;
	}

	public String getSignState() {
		return signState;
	}

	public void setSignState(String signState) {
		this.signState = signState;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrgStatus() {
		return orgStatus;
	}

	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getDocumentsHasOrgId() {
		return documentsHasOrgId;
	}

	public void setDocumentsHasOrgId(Long documentsHasOrgId) {
		this.documentsHasOrgId = documentsHasOrgId;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public List<String> getOrgIdsList() {
		return orgIdsList;
	}

	public void setOrgIdsList(List<String> orgIdsList) {
		this.orgIdsList = orgIdsList;
	}

}
