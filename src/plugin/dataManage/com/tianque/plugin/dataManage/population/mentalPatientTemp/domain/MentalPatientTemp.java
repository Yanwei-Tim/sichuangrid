package com.tianque.plugin.dataManage.population.mentalPatientTemp.domain;

import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;

public class MentalPatientTemp extends MentalPatient {
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
