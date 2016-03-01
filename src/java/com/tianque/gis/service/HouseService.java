package com.tianque.gis.service;

import java.util.List;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;

public interface HouseService {

	public static String NO_HOUSE_REASON_FOR_CANCEL_LOGOUT = "操作原因系统自动设置为无房";

	PageInfo findHouseInfosForPage(Long orgId, String address, Integer page, Integer rows,
			String sidx, String sord, String string);

	List<Long> updateHouseGisInfo(List<Long> houseIds, Long buildingId, Double pointX, Double pointY);

	public HouseInfo updateHouseInfoForGis(Long id, GisInfo gisInfo);

	PageInfo findHouseInfosByBuildingIdForPage(Long buildingId, Integer page, Integer rows,
			String sidx, String sord);

	void unbindHousePropertyById(Long houseId);
}
