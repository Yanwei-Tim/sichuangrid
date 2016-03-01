package com.tianque.plugin.serviceTeam.serviceObject.vo;

import com.tianque.domain.PropertyDict;

public class ServiceObjectPopulationVo extends ServiceObjectVo {
	/** 性别 */
	private PropertyDict gender;
	/** 联系手机 */
	private String mobileNumber;

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
