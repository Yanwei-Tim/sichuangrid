package com.tianque.gis.dao;

import java.util.List;

import com.tianque.domain.BuildingData;

/**
 * 楼宇数据库操作接口
 */
public interface BuildingDataDao {

	/**
	 * 添加楼宇信息
	 * 
	 * @param buildingData
	 *            楼宇对象
	 * @return buildingData 楼宇对象
	 */
	public BuildingData addBuildingData(BuildingData buildingData);

	/**
	 * 根据ID获取楼宇信息
	 * 
	 * @param buildingId
	 *            楼宇ID
	 * @return buildingData 楼宇对象
	 */
	public BuildingData getBuildingDataByBuildingId(Long buildingId);

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
	 * @param buildingId
	 *            楼宇ID
	 */
	public void deleteBuildingDataById(Long id);

	/**
	 * 根据ID获取楼宇信息
	 * 
	 * @param buildingId
	 *            楼宇ID
	 * @return buildingData 楼宇对象
	 */
	public BuildingData getBuildingDataById(Long id);

	/**
	 * 批量删除操作
	 * 
	 * @param personIds
	 */
	public void deleteBuildingDataByIds(List<Long> personIds);

}
