package com.tianque.plugin.analyzing.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.domain.UsedInfo;
import com.tianque.util.DateFormat;

@Repository("usedInfoDao")
public class UsedInfoDaoImpl extends AbstractBaseDao implements UsedInfoDao {

	@Override
	public List<UsedInfo> getUsedLoginInfoForUsedInfo(Date beforDayStartDate,
			Date beforDayEndDate, Date beforWeekMonday, Date beforWeekSunday,
			Date monthStartDate, Date monthEndDate, Long orgId, Long orgTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beforDayStartDate", beforDayStartDate);
		map.put("beforDayEndDate", beforDayEndDate);
		map.put("beforWeekMonday", beforWeekMonday);
		map.put("beforWeekSunday", beforWeekSunday);
		map.put("monthStartDate", monthStartDate);
		map.put("monthEndDate", monthEndDate);
		map.put("orgId", orgId);
		map.put("orgTypeId", orgTypeId);
		return getSqlMapClientTemplate().queryForList(
				"usedInfo.getUsedLoginInfoForUsedInfo", map);
	}

	@Override
	public List<UsedInfo> getUsedInfoDayData(Date beforDayStartDate,
			Date beforDayEndDate, Long orgId, Long orgTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", beforDayStartDate);
		map.put("endDate", beforDayEndDate);
		map.put("orgId", orgId);
		map.put("orgTypeId", orgTypeId);
		return getSqlMapClientTemplate().queryForList(
				"usedInfo.getUsedInfoDayData", map);
	}
	
	@Override
	public void dropUserSignEveryDay() {
		getSqlMapClientTemplate().update(
				"usedInfo.dropUserSignEveryDay");
	}
	
	@Override
	public void createUserSignEveryDay(Date beforDayStartDate,
			Date beforDayEndDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", DateFormat.toString(beforDayStartDate,DateFormat.DEFAULT_DATETIME_FORMAT_SEC));
		map.put("endDate", DateFormat.toString(beforDayEndDate,DateFormat.DEFAULT_DATETIME_FORMAT_SEC));
		getSqlMapClientTemplate().update(
				"usedInfo.createUserSignEveryDay", map);
	}
	
	@Override
	public void createUserSignEveryDayIndex() {
		getSqlMapClientTemplate().update(
				"usedInfo.createUserSignEveryDayIndex");
	}
	
}
