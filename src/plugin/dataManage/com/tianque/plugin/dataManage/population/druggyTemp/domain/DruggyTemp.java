package com.tianque.plugin.dataManage.population.druggyTemp.domain;

import com.tianque.baseInfo.druggy.domain.Druggy;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;

public class DruggyTemp extends Druggy {
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
