package com.tianque.service;

import com.tianque.domain.vo.HouseSimpleInfoForSearch;
import com.tianque.service.util.PopulationCatalog;

public interface HousePopulationMaintanceService {

	/**
	 * 绑定人房关联
	 * 
	 * @param catalog
	 * @param populationId
	 * @param houseId
	 * @return
	 */
	boolean bindHouse(PopulationCatalog catalog, Long populationId, Long houseId);

	/**
	 * 绑定人房关联（不知道和bindHouse有什么区别）
	 * 
	 * @param catalog
	 * @param populationId
	 * @param houseId
	 * @return
	 */
	boolean bindHouseIgnoreDefaultLiving(PopulationCatalog catalog,
			Long populationId, Long houseId);

	/**
	 * 删除人房关联关系
	 * 
	 * @param catalog
	 * @param populationId
	 * @param houseId
	 * @return
	 */
	boolean releaseHouse(PopulationCatalog catalog, Long populationId,
			Long houseId);

	/**
	 * 根据人口信息，取人口居住的房屋的id
	 * 
	 * @param catalog
	 * @param id
	 * @return
	 */
	Long getPopulationLivingHouseId(PopulationCatalog catalog, Long id);

	/**
	 * 根据人口信息，取人口居住的房屋的HouseSimpleInfoForSearch信息
	 * 
	 * @param catalog
	 * @param id
	 * @return
	 */
	HouseSimpleInfoForSearch getPopulationLivingHouseInfo(
			PopulationCatalog catalog, Long populationId, String shardCode);

	/**
	 * 删除人房关联关系，不指定房屋id
	 * 
	 * @param catalog
	 * @param populationId
	 */
	void releasePopulationAllHouse(PopulationCatalog catalog, Long populationId);

	/**
	 * 删除人房关联关系，不指定房屋id
	 * 
	 * @param catalog
	 * @param populationId
	 */
	void releasePopulationsAllHouse(PopulationCatalog catalog,
			Long[] populationIds);

}
