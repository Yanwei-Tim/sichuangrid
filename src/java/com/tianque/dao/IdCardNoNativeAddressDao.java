package com.tianque.dao;

import com.tianque.domain.vo.IdCardNoNativeAddress;

public interface IdCardNoNativeAddressDao {
	public IdCardNoNativeAddress addIdCardNoNativeAddress(
			IdCardNoNativeAddress idCardNoNativeAddress);

	public IdCardNoNativeAddress getIdCardNoNativeAddressBySixthIdCardNo(
			IdCardNoNativeAddress idCardNoNativeAddress);
}
