package com.tianque.baseInfo.permanentAddress.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PermanentAddress implements Serializable {

	private String addressNo;
	private String addressName;
	private String parentid;
	private String addressLevel;
	private String newAddressName;
	private String proviceName;
	private String cityName;
	private String district;

	public PermanentAddress() {

	}

	public Map<String, PermanentAddress> getPermanentAddressByIdCardNo(
			final String _idCardNo) {
		if (_idCardNo.length() != 15 && _idCardNo.length() != 18) {
			return null;
		}
		Map<String, PermanentAddress> map = new HashMap<String, PermanentAddress>();
		setDistrictInMap(_idCardNo, map);
		setCityInMap(_idCardNo, map);
		setProvinceInMap(_idCardNo, map);
		return map;
	}

	private void setDistrictInMap(final String _idCardNo,
			Map<String, PermanentAddress> map) {
		String firstAddressNo = _idCardNo.substring(0, 6);
		PermanentAddress district = new PermanentAddress();
		district.setAddressNo(firstAddressNo);
		map.put("district", district);
	}

	private void setCityInMap(final String _idCardNo,
			Map<String, PermanentAddress> map) {
		String firstAddressNo = _idCardNo.substring(0, 4) + "00";
		PermanentAddress city = new PermanentAddress();
		city.setAddressNo(firstAddressNo);
		map.put("city", city);
	}

	private void setProvinceInMap(final String _idCardNo,
			Map<String, PermanentAddress> map) {
		String firstAddressNo = _idCardNo.substring(0, 2) + "0000";
		PermanentAddress province = new PermanentAddress();
		province.setAddressNo(firstAddressNo);
		map.put("province", province);
	}

	public String getProviceName() {
		return proviceName;
	}

	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAddressNo() {
		return addressNo;
	}

	public void setAddressNo(String addressNo) {
		this.addressNo = addressNo;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getAddressLevel() {
		return addressLevel;
	}

	public void setAddressLevel(String addressLevel) {
		this.addressLevel = addressLevel;
	}

	public String getNewAddressName() {
		return newAddressName;
	}

	public void setNewAddressName(String newAddressName) {
		this.newAddressName = newAddressName;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
