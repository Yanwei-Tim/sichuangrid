package com.tianque.plugin.dataManage.location.companyPlaceTemp.domain;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;

public class CompanyPlaceTemp extends CompanyPlace {

	private ClaimDetail claimDetail;

	private String searchNameOrAddress;// 增加临时字段，用以快速查询

	public ClaimDetail getClaimDetail() {
		if (null == claimDetail) {
			claimDetail = new ClaimDetail();
		}
		return claimDetail;
	}

	public void setClaimDetail(ClaimDetail claimDetail) {
		this.claimDetail = claimDetail;
	}

	public String getSearchNameOrAddress() {
		return searchNameOrAddress;
	}

	public void setSearchNameOrAddress(String searchNameOrAddress) {
		this.searchNameOrAddress = searchNameOrAddress;
	}

}
