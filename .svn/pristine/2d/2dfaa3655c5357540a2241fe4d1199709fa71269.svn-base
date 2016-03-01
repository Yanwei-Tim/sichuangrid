package com.tianque.baseInfo.permanentAddress.job.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.permanentAddress.domain.PermanentAddressLog;
import com.tianque.baseInfo.permanentAddress.domain.TableMapColumn;
import com.tianque.core.base.AbstractBaseDao;

@Repository("syncPermanentAddressDao")
public class SyncPermanentAddressDaoImpl extends AbstractBaseDao implements
		SyncPermanentAddressDao {

	@Override
	public void syncProvince(TableMapColumn tableMapColumn,
			PermanentAddressLog permanentAddressLog, Date updateDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableMapColumn.getTableName());
		map.put("provinceColumnName", tableMapColumn.getProvinceColumnName());
		map.put("province", permanentAddressLog.getProvince());
		map.put("changedName", permanentAddressLog.getChangedName());
		map.put("updateDate", updateDate);
		getSqlMapClientTemplate().update("syncPermanentAddress.syncProvince",
				map);
	}

	@Override
	public void syncCity(TableMapColumn tableMapColumn,
			PermanentAddressLog permanentAddressLog, Date updateDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableMapColumn.getTableName());
		map.put("provinceColumnName", tableMapColumn.getProvinceColumnName());
		map.put("cityColumnName", tableMapColumn.getCityColumnName());
		map.put("province", permanentAddressLog.getProvince());
		map.put("city", permanentAddressLog.getCity());
		map.put("changedName", permanentAddressLog.getChangedName());
		map.put("updateDate", updateDate);
		getSqlMapClientTemplate().update("syncPermanentAddress.syncCity", map);
	}

	@Override
	public void syncDistrict(TableMapColumn tableMapColumn,
			PermanentAddressLog permanentAddressLog, Date updateDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableMapColumn.getTableName());
		map.put("provinceColumnName", tableMapColumn.getProvinceColumnName());
		map.put("cityColumnName", tableMapColumn.getCityColumnName());
		map.put("districtColumnName", tableMapColumn.getDistrictColumnName());
		map.put("province", permanentAddressLog.getProvince());
		map.put("city", permanentAddressLog.getCity());
		map.put("district", permanentAddressLog.getDistrict());
		map.put("changedName", permanentAddressLog.getChangedName());
		map.put("updateDate", updateDate);
		getSqlMapClientTemplate().update("syncPermanentAddress.syncDistrict",
				map);
	}
}
