package com.tianque.mobile.sysadmin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.permanentAddress.domain.PermanentAddress;
import com.tianque.baseInfo.permanentAddress.service.PermanentAddressService;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.sysadmin.AreaMobileAdapter;

@Controller("areaMobileAdapter")
public class AreaMobileAdapterImpl extends BaseMobileAction implements
		AreaMobileAdapter {

	// 参数 省，市
	private String parentName;

	private String returnString;

	@Autowired
	private PermanentAddressService permanentAddressService;

	@Override
	public String getAllCity() throws Exception {
		if (parentName == null || "".equals(parentName)) {
			errorMessage = "参数错误！";
			return ERROR;
		}
		List<PermanentAddress> permanentAddressList = permanentAddressService
				.getPermanentAddressByParentName(parentName);
		returnString = "";
		for (PermanentAddress permanentAddress : permanentAddressList) {
			returnString += permanentAddress.getAddressName() + ",";
		}
		return SUCCESS;
	}

	@Override
	public String getAllCounty() throws Exception {
		if (parentName == null || "".equals(parentName)) {
			errorMessage = "参数错误！";
			return ERROR;
		}
		List<PermanentAddress> permanentAddressList = permanentAddressService
				.getPermanentAddressByParentNameAndPId(parentName);
		returnString = "";
		for (PermanentAddress permanentAddress : permanentAddressList) {
			returnString += permanentAddress.getAddressName() + ",";
		}
		return SUCCESS;
	}

	@Override
	public String getAllProvince() throws Exception {
		List<PermanentAddress> permanentAddressList = permanentAddressService
				.getPermanentAddressByLevel("1");
		returnString = "";
		for (PermanentAddress permanentAddress : permanentAddressList) {
			returnString += permanentAddress.getAddressName() + ",";
		}
		return SUCCESS;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getReturnString() {
		return returnString;
	}
}
