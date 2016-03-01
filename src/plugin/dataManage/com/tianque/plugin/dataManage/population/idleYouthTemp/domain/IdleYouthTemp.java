package com.tianque.plugin.dataManage.population.idleYouthTemp.domain;

import com.tianque.baseInfo.idleYouth.domain.IdleYouth;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;

public class IdleYouthTemp extends IdleYouth {
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
