package com.tianque.plugin.dataManage.population.unsettledPopulationTemp.domain;

import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;

public class UnsettledPopulationTemp extends UnsettledPopulation {
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
