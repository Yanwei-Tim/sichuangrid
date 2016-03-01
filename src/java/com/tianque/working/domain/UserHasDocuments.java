package com.tianque.working.domain;

import java.util.Date;

import com.tianque.core.base.BaseDomain;

public class UserHasDocuments extends BaseDomain {

	private Long documentId;
	private Long userId;
	private Long isSign;// 是否签收 0未签收
	private Date signDate;// 签收时间
	private Long signUserId;// 签收人
	private String signContent;// 签收回执
	private Long postObj;// 主送还是抄送 0主送
	private Integer readState;// 阅读状态 0:未阅读 1:阅读
	private Date readDate;// 阅读时间
	private String signer;// 签收人名称

	private String approvalOpinion;// 审批意见 邮件转发的时候，转发人可以增加审批意见，然后通过累加的方式存储

	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	public Integer getReadState() {
		return readState;
	}

	public void setReadState(Integer readState) {
		this.readState = readState;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getIsSign() {
		return isSign;
	}

	public void setIsSign(Long isSign) {
		this.isSign = isSign;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Long getSignUserId() {
		return signUserId;
	}

	public void setSignUserId(Long signUserId) {
		this.signUserId = signUserId;
	}

	public String getSignContent() {
		return signContent;
	}

	public void setSignContent(String signContent) {
		this.signContent = signContent;
	}

	public Long getPostObj() {
		return postObj;
	}

	public void setPostObj(Long postObj) {
		this.postObj = postObj;
	}

	public String getApprovalOpinion() {
		return approvalOpinion;
	}

	public void setApprovalOpinion(String approvalOpinion) {
		this.approvalOpinion = approvalOpinion;
	}

}
