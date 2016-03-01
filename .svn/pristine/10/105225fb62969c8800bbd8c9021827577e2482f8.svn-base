package com.tianque.plugin.dataManage.location.rentalHouseTemp.domain;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.location.actualHouseTemp.domain.ActualHouseTemp;

/**
 * 出租房
 */
public class RentalHouseTemp extends ActualHouseTemp {
	private ClaimDetail claimDetail;

	public RentalHouseTemp() {
		setLocationType(BaseInfoTables.RENTALHOUSE_KEY);
	}

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
