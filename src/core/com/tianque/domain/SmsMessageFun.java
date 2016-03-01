package com.tianque.domain;

import com.tianque.core.base.BaseDomain;

public class SmsMessageFun extends BaseDomain {
	/** 短信功能是否开启　 */
	private int isOpenSms;
	/** 组织机构 */
	private Organization org;
	/** 组织机构code */
	private String orgCode;

	public int getIsOpenSms() {
		return isOpenSms;
	}

	public void setIsOpenSms(int isOpenSms) {
		this.isOpenSms = isOpenSms;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}
