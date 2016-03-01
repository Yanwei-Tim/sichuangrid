package com.tianque.openLayersMap.positioningTrajectory.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.positioningTrajectory.domian.PositioningTrajectory;
import com.tianque.openLayersMap.positioningTrajectory.domian.vo.SearchPositioningTrajectoryVo;

public interface PositioningTrajectoryDao {
	PositioningTrajectory addPositioningTrajectory(PositioningTrajectory positioningTrajectory);
	
	PositioningTrajectory getPositioningTrajectoryById(Long id);
	
	void deletePositioningTrajectoryByDeviceNumber(String deviceNumber);
	
	PageInfo<PositioningTrajectory> findPositioningTrajectoryByDeviceNumberAndLocateDate(SearchPositioningTrajectoryVo searchPositioningTrajectoryVo,Integer pageNum, Integer pageSize,
			String sortField, String order);

	void updateLonlatById(Long id, String centerLon, String centerLat,
			String centerLon2, String centerLat2);
}
