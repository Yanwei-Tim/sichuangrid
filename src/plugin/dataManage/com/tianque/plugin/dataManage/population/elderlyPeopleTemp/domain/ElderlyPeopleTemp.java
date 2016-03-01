package com.tianque.plugin.dataManage.population.elderlyPeopleTemp.domain;

import com.tianque.baseInfo.elderlyPeople.domain.ElderlyPeople;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;

public class ElderlyPeopleTemp extends ElderlyPeople {
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
