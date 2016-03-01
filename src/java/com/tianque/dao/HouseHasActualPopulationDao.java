package com.tianque.dao;

import java.util.List;

import com.tianque.domain.HouseHasActualPopulation;

/**
 * 人房关联关系数据访问接口
 */
public interface HouseHasActualPopulationDao {
	/**
	 * 新增人房关联关系信息
	 * 
	 * @param houseHasActualPopulation
	 *            人房关联关系信息
	 */
	public void addHouseHasActualPopulation(
			HouseHasActualPopulation houseHasActualPopulation);

	/**
	 * 修改人房关联关系信息
	 * 
	 * @param populationType
	 *            人员类型
	 * @param populationId
	 *            人员ID
	 * @param oldHouseId
	 *            变换前房屋ID
	 * @param newHouseId
	 *            变换后房屋ID
	 */
	public void updateHouseHasActualPopulationByPopulationTypeAndHouseId(
			String populationType, Long populationId, Long oldHouseId,
			Long newHouseId);

	/**
	 * 删除人房关联关系信息
	 * 
	 * @param populationType
	 *            实口类型
	 * @param houseId
	 *            房屋ID
	 */
	public void deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
			String populationType, Long houseId, Long populationId);

	/**
	 * 根据房屋ID获取人房关联关系信息
	 * 
	 * @param houseId
	 *            房屋ID
	 * @return
	 */
	public List<HouseHasActualPopulation> getHouseHasActualPopulationByHouseId(
			Long houseId);

	/**
	 * 根据实口ID删除人房关联关系
	 * 
	 * @param populationType
	 * @param populationId
	 */
	public void deleteHouseHasActualPopulationByPopulationTypeAndPopulationId(
			String populationType, Long populationId);

	public void deleteHouseHasActualPopulationByHouseId(Long houseId);

	public void deleteHousehasimportantpopulationByHouseId(Long houseId);

	// 房屋人数
	public Long getHouseIdByPopulationTypeAndPopulationId(
			String populationType, Long populationId);
}
