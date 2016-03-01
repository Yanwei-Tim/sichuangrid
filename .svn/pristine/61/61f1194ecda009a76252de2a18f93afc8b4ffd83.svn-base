package com.tianque.baseInfo.rentalHouse.service;

import java.util.List;

import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;

public interface RentalHouseExternalService {

	public RentalHouse updateRentalHouseForPopulation(RentalHouse houseInfo);

	public RentalHouse addRentalHouseForPopulation(RentalHouse houseInfo);

	public RentalHouse getHouseInfoByHouseCodeAndOrgId(String houseCode, Long id);

	/**
	 * 通过orgId，houseCode和logoutType查询出租房信息
	 * 
	 * @param houseCode
	 * @param id
	 * @param logoutType
	 * @return
	 */
	public RentalHouse getHouseInfoByHouseCodeAndOrgId(String houseCode,
			Long id, Long logoutType);

	public void updateEmphasiseByIds(List<Long> houseIds, Long isEmphasis);

	public RentalHouse getHouseInfoByHouseId(Long id, Long emphasis);
}
