package com.tianque.baseInfo.houseTrack.service;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.houseTrack.domain.HouseTracks;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;

public interface HouseTracksService {

	/**
	 * 轨迹页面列表
	 * 
	 * @param houseId
	 *            房屋id
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<HouseTracks> findHouseTracksByHouseId(Integer houseId,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 房屋轨迹记录
	 * 
	 * @param houseInfo
	 *            房屋对象
	 * @param houseType
	 *            房屋类型
	 * @param houseinitType
	 *            房屋内置类型
	 * @param oldOrg
	 *            房屋原组织对象
	 * @param operationType
	 *            操作类型
	 * @param operationContent
	 *            操作内容
	 */
	public void addHouseTracks(HouseInfo houseInfo, String houseType,
			Integer houseinitType, Organization oldOrg, Integer operationType,
			String operationContent);
}
