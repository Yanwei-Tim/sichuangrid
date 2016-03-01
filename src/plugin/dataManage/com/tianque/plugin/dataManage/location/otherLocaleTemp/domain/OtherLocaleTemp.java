package com.tianque.plugin.dataManage.location.otherLocaleTemp.domain;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.PropertyDict;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.location.placeCommon.domain.PlaceCommonTemp;

/**
 * 其他场所
 */
public class OtherLocaleTemp extends PlaceCommonTemp {
	public OtherLocaleTemp() {
		setLocationType(BaseInfoTables.OTHERLOCALE_KEY);
	}

	private ClaimDetail claimDetail;
	/** 场所类型 */
	private PropertyDict type;
	/** 联系电话 */
	private String telephone;
	/** 联系手机 */
	private String mobileNumber;
	/** 从业人员数 */
	private Long practitionerNumber;

	public Long getPractitionerNumber() {
		return practitionerNumber;
	}

	public void setPractitionerNumber(Long practitionerNumber) {
		this.practitionerNumber = practitionerNumber;
	}

	public PropertyDict getType() {
		return type;
	}

	public void setType(PropertyDict type) {
		this.type = type;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
