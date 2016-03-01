package com.tianque.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.dao.IdCardNoNativeAddressDao;
import com.tianque.domain.vo.IdCardNoNativeAddress;
import com.tianque.service.IdCardNoNativeAddressService;

@Service("idCardNoNativeAddressService")
public class IdCardNoNativeAddressServiceImpl implements IdCardNoNativeAddressService {
	@Autowired
	IdCardNoNativeAddressDao idCardNoNativeAddressDao;

	@Override
	public IdCardNoNativeAddress addIdCardNoNativeAddress(
			IdCardNoNativeAddress idCardNoNativeAddress) {
		return idCardNoNativeAddressDao.addIdCardNoNativeAddress(idCardNoNativeAddress);
	}

	@Override
	public IdCardNoNativeAddress getIdCardNoNativeAddressBySixthIdCardNo(
			IdCardNoNativeAddress idCardNoNativeAddress) {
		return idCardNoNativeAddressDao
				.getIdCardNoNativeAddressBySixthIdCardNo(idCardNoNativeAddress);
	}

}
