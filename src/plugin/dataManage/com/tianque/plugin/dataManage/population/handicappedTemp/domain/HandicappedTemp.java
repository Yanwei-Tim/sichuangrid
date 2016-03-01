package com.tianque.plugin.dataManage.population.handicappedTemp.domain;

import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;

public class HandicappedTemp extends Handicapped {
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
