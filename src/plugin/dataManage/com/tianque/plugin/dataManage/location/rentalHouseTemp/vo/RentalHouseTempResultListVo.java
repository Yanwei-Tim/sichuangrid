package com.tianque.plugin.dataManage.location.rentalHouseTemp.vo;

import java.util.List;

public class RentalHouseTempResultListVo {

	private List<RentalHouseTempResultVo> successList;

	private List<RentalHouseTempResultVo> errorList;

	private Long orgId;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public List<RentalHouseTempResultVo> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<RentalHouseTempResultVo> errorList) {
		this.errorList = errorList;
	}

	public List<RentalHouseTempResultVo> getSuccessList() {
		return successList;
	}

	public void setSuccessList(List<RentalHouseTempResultVo> successList) {
		this.successList = successList;
	}
}
