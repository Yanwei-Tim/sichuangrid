package com.tianque.controller.vo;

import java.io.Serializable;

public class UserMessageVo implements Serializable {
	private int smsReceivedBoxNum;
	private int myNeedDoNum;
	private int personnelMessageNum;
	private int messageNum;
	private int myAuditDelayNum;
	private int myVerification;// 待验证
	private int myCheckGrade;// 待评分

	public int getSmsReceivedBoxNum() {
		return smsReceivedBoxNum;
	}

	public void setSmsReceivedBoxNum(int smsReceivedBoxNum) {
		this.smsReceivedBoxNum = smsReceivedBoxNum;
	}

	public int getMyNeedDoNum() {
		return myNeedDoNum;
	}

	public void setMyNeedDoNum(int myNeedDoNum) {
		this.myNeedDoNum = myNeedDoNum;
	}

	public int getPersonnelMessageNum() {
		return personnelMessageNum;
	}

	public void setPersonnelMessageNum(int personnelMessageNum) {
		this.personnelMessageNum = personnelMessageNum;
	}

	public int getMessageNum() {
		return messageNum;
	}

	public void setMessageNum(int messageNum) {
		this.messageNum = messageNum;
	}

	public void setMyAuditDelayNum(int myAuditDelayNum) {
		this.myAuditDelayNum = myAuditDelayNum;
	}

	public int getMyAuditDelayNum() {
		return myAuditDelayNum;
	}

	public int getMyVerification() {
		return myVerification;
	}

	public void setMyVerification(int myVerification) {
		this.myVerification = myVerification;
	}

	public int getMyCheckGrade() {
		return myCheckGrade;
	}

	public void setMyCheckGrade(int myCheckGrade) {
		this.myCheckGrade = myCheckGrade;
	}
}
