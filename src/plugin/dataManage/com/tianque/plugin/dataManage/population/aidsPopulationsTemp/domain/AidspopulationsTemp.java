package com.tianque.plugin.dataManage.population.aidsPopulationsTemp.domain;

import com.tianque.aidsPopulations.domain.Aidspopulations;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;

public class AidspopulationsTemp extends Aidspopulations {
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
