package com.tianque.plugin.dataManage.population.dangerousGoodsPractitionerTemp.domain;

import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;

public class DangerousGoodsPractitionerTemp extends DangerousGoodsPractitioner {
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
