package com.tianque.plugin.dataManage.population.floatingPopulationTemp.domain;

import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;

public class FloatingPopulationTemp extends FloatingPopulation {
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
