package com.tianque.baseInfo.actualHouse.service;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;

public interface ActualHouseExternalService {

	public HouseInfo updateHouseInfoForPopulation(HouseInfo houseInfo);

	public HouseInfo addHouseInfoForPopulation(HouseInfo houseInfo);

	public HouseInfo getHouseInfoById(Long id);

}
