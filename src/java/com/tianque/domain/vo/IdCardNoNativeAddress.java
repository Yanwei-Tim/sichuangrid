package com.tianque.domain.vo;

import java.util.HashMap;
import java.util.Map;

public class IdCardNoNativeAddress {
	private String sixthIdCardNo;
	private String address;

	public IdCardNoNativeAddress(String _sixthIdCardNo, String _address) {
		sixthIdCardNo = _sixthIdCardNo;
		address = _address;
	}

	public IdCardNoNativeAddress() {

	}

	public String getSixthIdCardNo() {
		return sixthIdCardNo;
	}

	public void setSixthIdCardNo(String sixthIdCardNo) {
		this.sixthIdCardNo = sixthIdCardNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Map<String, IdCardNoNativeAddress> getIdCardNoNativeAddressByIdCardNo(
			final String _idCardNo) {
		if (_idCardNo.length() != 15 && _idCardNo.length() != 18) {
			return null;
		}
		Map<String, IdCardNoNativeAddress> map = new HashMap<String, IdCardNoNativeAddress>();
		setDistrictInMap(_idCardNo, map);
		setCityInMap(_idCardNo, map);
		setProvinceInMap(_idCardNo, map);
		return map;
	}

	private void setDistrictInMap(final String _idCardNo, Map<String, IdCardNoNativeAddress> map) {
		String firstSixIdCardNo = _idCardNo.substring(0, 6);
		IdCardNoNativeAddress district = new IdCardNoNativeAddress();
		district.setSixthIdCardNo(firstSixIdCardNo);
		map.put("district", district);
	}

	private void setCityInMap(final String _idCardNo, Map<String, IdCardNoNativeAddress> map) {
		String firstSixIdCardNo = _idCardNo.substring(0, 4) + "00";
		IdCardNoNativeAddress city = new IdCardNoNativeAddress();
		city.setSixthIdCardNo(firstSixIdCardNo);
		map.put("city", city);
	}

	private void setProvinceInMap(final String _idCardNo, Map<String, IdCardNoNativeAddress> map) {
		String firstSixIdCardNo = _idCardNo.substring(0, 2) + "0000";
		IdCardNoNativeAddress province = new IdCardNoNativeAddress();
		province.setSixthIdCardNo(firstSixIdCardNo);
		map.put("province", province);
	}

}
