package com.tianque.plugin.dataManage.population.aidNeedPopulationTemp.domain;

import com.tianque.baseInfo.aidNeedPopulation.domain.AidNeedPopulation;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;

public class AidNeedPopulationTemp extends AidNeedPopulation {
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
