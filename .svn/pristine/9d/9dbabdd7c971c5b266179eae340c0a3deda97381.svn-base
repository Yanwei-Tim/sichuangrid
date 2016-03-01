package com.tianque.service;

import java.util.List;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;

public interface HousesDependentService {

	/**
	 * 修改实有房屋时同步信息到出租房信息
	 * 
	 * @param rentalHouse
	 *            实有房屋信息 @
	 */
	public void synActualHouseToRentalHouseForUpdate(RentalHouse rentalHouse);

	/**
	 * 删除实有房屋时同步删除出租房信息
	 * 
	 * @param houseCode
	 *            房屋编号
	 * @param orgId
	 *            组织ID @
	 */
	public void synActualHouseToRentalHouseForDelete(String houseCode,
			Long orgId);

	/**
	 * 删除出租房时同步删除实有房屋信息
	 * 
	 * @param houseCode
	 *            房屋编号
	 * @param orgId
	 *            组织ID @
	 */
	public void synRentalHouseToActualHouseForDelete(String houseCode,
			Long orgId);

	/**
	 * 注销或取消注销出租房时对实有房屋的影响
	 * 
	 * @param houseId
	 *            出租房ID
	 * @param isEmphasis
	 *            注销状态 @
	 */
	public void synRentalHouseToActualHouseForIsEmphasis(Long houseId,
			Long isEmphasis);

	/**
	 * 删除实有房屋时同步删除出租房信息
	 * 
	 * @param id
	 *            房屋id
	 * @param id2
	 */
	public void synActualHouseToRentalHouseForDelete(List<HouseInfo> houseInfos);
}
