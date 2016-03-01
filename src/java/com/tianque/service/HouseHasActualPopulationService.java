package com.tianque.service;

import java.util.List;

import com.tianque.domain.HouseHasActualPopulation;

/**
 * 人房关联关系业务接口
 */
public interface HouseHasActualPopulationService {
	/**
	 * 新增人房关联关系信息
	 * 
	 * @param houseHasActualPopulation
	 *            人房关联关系信息 @
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
	 * @param houseOldCode
	 *            变换前房屋编号
	 * @param houseNewCode
	 *            变换后房屋编号
	 * @param orgId
	 *            组织ID @
	 */
	public void updateHouseHasActualPopulationByPopulationTypeAndHouseCodes(
			String populationType, Long populationId, String houseOldCode,
			String houseNewCode, Long orgId);

	/**
	 * 删除人房关联关系信息
	 * 
	 * @param populationType
	 *            实口类型
	 * @param houseId
	 *            房屋ID @
	 */
	public void deleteHouseHasActualPopulationByPopulationTypeAndHouseId(
			String populationType, Long houseId, Long populationId);

	/**
	 * 删除人房关联关系信息 [没有用的关联关系]
	 * 
	 * @param populationType
	 *            实口类型
	 * @param houseId
	 *            房屋ID @
	 */
	public void deleteHouseHasActualPopulationByPopulationTypeAndHouseInfosId(
			String populationType, Long houseId, Long populationId);

	/**
	 * 根据房屋ID获取人房关联关系信息
	 * 
	 * @param houseId
	 *            房屋ID
	 * @return @
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

	/**
	 * 根据房屋id 删除 房屋和人口的关系
	 * 
	 * @param houseId
	 */
	public void deleteHouseHasActualPopulationByHouseId(Long houseId);

	/**
	 * 根据房屋id 删除 房屋和业务人口的关系
	 * 
	 * @param houseId
	 */
	public void deleteHousehasimportantpopulationByHouseId(Long houseId);

	/**
	 * 根据房屋ID修改人房关联关系
	 */
	public void updateHouseHasActualPopulationByPopulationByHouseId(
			String populationType, Long populationId, Long houseOldCode,
			Long houseNewCode, Long orgId);

	// 房屋人数
	public Long getHouseIdByPopulationTypeAndPopulationId(
			String populationType, Long populationId);
}
