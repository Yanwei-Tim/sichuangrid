package com.tianque.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.IdCardNoNativeAddressDao;
import com.tianque.domain.vo.IdCardNoNativeAddress;

@Repository("idCardNoNativeAddressDao")
public class IdCardNoNativeAddressDaoImpl extends AbstractBaseDao implements
		IdCardNoNativeAddressDao {
	@Override
	public IdCardNoNativeAddress addIdCardNoNativeAddress(
			IdCardNoNativeAddress idCardNoNativeAddress) {
		if (null != idCardNoNativeAddress.getAddress()
				&& null != idCardNoNativeAddress.getSixthIdCardNo()) {
			getSqlMapClientTemplate().insert("idCardNoNativeAddress.addIdCardNoNativeAddress",
					idCardNoNativeAddress);
			return idCardNoNativeAddress;
		}
		return null;
	}

	@Override
	public IdCardNoNativeAddress getIdCardNoNativeAddressBySixthIdCardNo(
			IdCardNoNativeAddress idCardNoNativeAddress) {
		if (null == idCardNoNativeAddress.getSixthIdCardNo()) {
			return null;
		}
		return (IdCardNoNativeAddress) getSqlMapClientTemplate().queryForObject(
				"idCardNoNativeAddress.getIdCardNoNativeAddressBySixthIdCardNo",
				idCardNoNativeAddress);
	}
}
