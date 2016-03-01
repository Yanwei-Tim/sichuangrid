package com.tianque.baseInfo.companyPlace.domain;

import com.tianque.core.base.BaseDomain;

public class CompanyPlaceAnnex extends BaseDomain {
	/* 业务 */
	private CompanyPlaceBusiness businessId;
	/* 附件名称 */
	private String fileName;
	/* 附件地址 */
	private String annexAddress;

	public CompanyPlaceBusiness getBusinessId() {
		return businessId;
	}

	public void setBusinessId(CompanyPlaceBusiness businessId) {
		this.businessId = businessId;
	}

	public String getAnnexAddress() {
		return annexAddress;
	}

	public void setAnnexAddress(String annexAddress) {
		this.annexAddress = annexAddress;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}