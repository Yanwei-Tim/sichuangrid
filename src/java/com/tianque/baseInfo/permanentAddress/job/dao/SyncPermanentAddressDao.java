package com.tianque.baseInfo.permanentAddress.job.dao;

import java.util.Date;

import com.tianque.baseInfo.permanentAddress.domain.PermanentAddressLog;
import com.tianque.baseInfo.permanentAddress.domain.TableMapColumn;

public interface SyncPermanentAddressDao {
	public void syncProvince(TableMapColumn tableMapColumn,
			PermanentAddressLog permanentAddressLog, Date updateDate);

	public void syncCity(TableMapColumn tableMapColumn,
			PermanentAddressLog permanentAddressLog, Date updateDate);

	public void syncDistrict(TableMapColumn tableMapColumn,
			PermanentAddressLog permanentAddressLog, Date updateDate);
}
