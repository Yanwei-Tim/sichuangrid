package com.tianque.gis.service;

import java.util.List;

import com.tianque.domain.BuildingData;

/**
 * 楼宇数据库操作接口
 */
public interface BuildingDataService {

	public Integer countHouseByBuildingId(Long buildingId);

	/**
	 * 根据ID获取楼宇信息
	 * 
	 * @param buildingId
	 *            楼宇ID
	 * @return buildingData 楼宇对象
	 */
	public BuildingData getBuildingDataByBuildingId(final Long buildingId);

	/**
	 * 修改楼宇信息
	 * 
	 * @param buildingData
	 *            楼宇对象
	 * @return buildingData 楼宇对象
	 */
	public BuildingData updateBuildingData(BuildingData buildingData);

	/**
	 * 根据ID删除楼宇信息
	 * 
	 * @param id
	 *            ID
	 */
	public void deleteBuildingDataById(Long id);

	/**
	 * 根据ID删除楼宇信息
	 * 
	 * @param buildingId
	 *            楼宇ID
	 */
	public List<Long> deleteBuildingsByIds(List<Long> personIds);

}
