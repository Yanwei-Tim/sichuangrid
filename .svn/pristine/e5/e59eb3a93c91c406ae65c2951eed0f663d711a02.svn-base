package com.tianque.plugin.analyzing.dao;

import java.util.Date;
import java.util.List;

import com.tianque.domain.UsedInfo;

public interface UsedInfoDao {

	public List<UsedInfo> getUsedLoginInfoForUsedInfo(Date beforDayStartDate,
			Date beforDayEndDate, Date beforWeekMonday, Date beforWeekSunday,
			Date monthStartDate, Date monthEndDate, Long orgId, Long orgTypeId);

	public List<UsedInfo> getUsedInfoDayData(Date beforDayStartDate,
			Date beforDayEndDate, Long orgId, Long orgTypeId);
	
	public void dropUserSignEveryDay();
	
	public void createUserSignEveryDay(Date beforDayStartDate,
			Date beforDayEndDate);
	
	public void createUserSignEveryDayIndex();
	
}
