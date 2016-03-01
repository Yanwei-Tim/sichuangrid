package com.tianque.plugin.dataManage.location.fireSafetyEnterpriseTemp.domain;

import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.location.enterpriseTemp.domain.EnterpriseTemp;

/**
 * 消防安全重点
 */
public class FireSafetyEnterpriseTemp extends EnterpriseTemp {
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
