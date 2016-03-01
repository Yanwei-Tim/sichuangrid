package com.tianque.domain.vo;

import com.tianque.domain.Organization;
import com.tianque.domain.User;

public class WorkContactVo {

	/** 姓名 */
	private String name;
	/** 手机 */
	private String mobileNumber;

	private Organization organization;

	private String workPhone;

	public WorkContactVo(User user) {
		this.name = user.getName();
		this.mobileNumber = user.getMobile();
		this.organization = user.getOrganization();
		this.workPhone = user.getWorkPhone();
	}

	public WorkContactVo() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

}
