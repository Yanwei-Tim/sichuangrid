package com.tianque.plugin.dataManage.population.optimalObjectTemp.domain;

import com.tianque.baseInfo.optimalObject.domain.OptimalObject;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;

public class OptimalObjectTemp extends OptimalObject {
	private ClaimDetail claimDetail;

	public ClaimDetail getClaimDetail() {
		if (null == claimDetail) {
			claimDetail = new ClaimDetail();
		}
		return claimDetail;
	}

	public void setClaimDetail(ClaimDetail claimDetail) {
		this.claimDetail = claimDetail;
	}
}
