package com.tianque.baseInfo.orgLocationTrack.domain;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.LocationBaseInfo;

public class LocationTracksEntity extends BaseDomain {

	private LocationBaseInfo locationBaseInfo;

	private String locationName;

	private String locationOrgInternalCode;

	/**
	 * 组织场所轨迹临时类
	 * 
	 * @param locationBaseInfo
	 *            组织场所对象
	 * @param locationName
	 *            组织场所名称
	 * @param locationOrgInternalCode
	 *            组织场所所在网格code
	 */
	public LocationTracksEntity(LocationBaseInfo locationBaseInfo,
			String locationName, String locationOrgInternalCode) {
		super();
		this.locationBaseInfo = locationBaseInfo;
		this.locationName = locationName;
		this.locationOrgInternalCode = locationOrgInternalCode;
	}

	public LocationBaseInfo getLocationBaseInfo() {
		return locationBaseInfo;
	}

	public String getLocationName() {
		return locationName;
	}

	public String getLocationOrgInternalCode() {
		return locationOrgInternalCode;
	}

}
