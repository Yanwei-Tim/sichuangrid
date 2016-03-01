package com.tianque.plugin.weChat.domain.sms;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 短信发送记录
 * @ClassName: SmsSendGroup 
 * @author: he.simin
 * @date: 2015年11月5日 下午2:40:32
 */
public class SmsSendGroup extends BaseDomain {
	private Organization org;
	private String smsSendId;
	private Long senderAccountId;
	private String senderAccountName;
	private Long senderUserId;
	private String senderUserName;
	private String smsContent;
	private String receiverRedCuffTeamType;
	private String receiverMobile;
	private Integer totalNumber;
	private Integer successNumber;
	private Integer failNumber;
	private Date lastResultDate;

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getSmsSendId() {
		return smsSendId;
	}

	public void setSmsSendId(String smsSendId) {
		this.smsSendId = smsSendId;
	}

	public Long getSenderAccountId() {
		return senderAccountId;
	}

	public void setSenderAccountId(Long senderAccountId) {
		this.senderAccountId = senderAccountId;
	}

	public String getSenderAccountName() {
		return senderAccountName;
	}

	public void setSenderAccountName(String senderAccountName) {
		this.senderAccountName = senderAccountName;
	}

	public Long getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(Long senderUserId) {
		this.senderUserId = senderUserId;
	}

	public String getSenderUserName() {
		return senderUserName;
	}

	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getReceiverRedCuffTeamType() {
		return receiverRedCuffTeamType;
	}

	public void setReceiverRedCuffTeamType(String receiverRedCuffTeamType) {
		this.receiverRedCuffTeamType = receiverRedCuffTeamType;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getSuccessNumber() {
		return successNumber;
	}

	public void setSuccessNumber(Integer successNumber) {
		this.successNumber = successNumber;
	}

	public Integer getFailNumber() {
		return failNumber;
	}

	public void setFailNumber(Integer failNumber) {
		this.failNumber = failNumber;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLastResultDate() {
		return lastResultDate;
	}

	public void setLastResultDate(Date lastResultDate) {
		this.lastResultDate = lastResultDate;
	}

}
