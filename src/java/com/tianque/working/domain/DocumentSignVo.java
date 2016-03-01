package com.tianque.working.domain;

import java.util.Date;

public class DocumentSignVo {

	private Long documentId;// 公文ID
	private Long userId;// 收件人ID
	private String documentTitle;// 公文标题
	private String userName;// 收公文用户
	private Integer receviesType;// 收件人类型 0:主送 1：抄送
	private Integer isSign;// 是否签收 0:未签收 1：已签收
	private String signContent;// 签收回执
	private Date signDate;// 签收时间
	private String sinerUserName;// 签收人
	private Integer isReminder;// 是否催收

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

	public String getDocumentTitle() {
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getReceviesType() {
		return receviesType;
	}

	public void setReceviesType(Integer receviesType) {
		this.receviesType = receviesType;
	}

	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}

	public String getSignContent() {
		return signContent;
	}

	public void setSignContent(String signContent) {
		this.signContent = signContent;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getSinerUserName() {
		return sinerUserName;
	}

	public void setSinerUserName(String sinerUserName) {
		this.sinerUserName = sinerUserName;
	}

	public Integer getIsReminder() {
		return isReminder;
	}

	public void setIsReminder(Integer isReminder) {
		this.isReminder = isReminder;
	}

}
