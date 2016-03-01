package com.tianque.openLayersMap.positioningTrajectory.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.positioningTrajectory.domian.PositioningTrajectory;
import com.tianque.openLayersMap.positioningTrajectory.domian.vo.SearchPositioningTrajectoryVo;

public interface PositioningTrajectoryService {
	public PositioningTrajectory addPositioningTrajectory(
			PositioningTrajectory positioningTrajectory);

	public boolean deletePositioningTrajectoryByDeviceNumber(String deviceNumber);

	public PositioningTrajectory getPositioningTrajectoryByDeviceNumber(Long id);

	public PageInfo<PositioningTrajectory> findPositioningTrajectoryByDeviceNumberAndLocateDate(
			SearchPositioningTrajectoryVo searchPositioningTrajectoryVo,
			Integer pageNum, Integer pageSize, String sortField, String order);

	/**
	 * 根据id获取定位信息，包括设备信息和用户信息
	 * 
	 * @param id
	 * @return
	 */
	public PositioningTrajectory getPositioningTrajectoryById(Long id);

	public void updateLonlatById(Long id, String centerLon, String centerLat,
			String centerLon2, String centerLat2);
}
