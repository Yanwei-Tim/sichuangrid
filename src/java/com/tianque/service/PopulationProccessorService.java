package com.tianque.service;

import java.util.Map;

import com.tianque.baseInfo.base.domain.ActualPopulation;

public interface PopulationProccessorService {

	/**
	 * 约束2:人口信息是否维护人口业务数据
	 * 
	 * @param actualPopulation
	 *            实有人口对象
	 * @param populationType
	 *            人员类型
	 * @param population
	 *            人员集合
	 */
	public Long proccessPopulationSpecializedInfo(
			ActualPopulation actualPopulation, String[] populationType,
			Map<String, Object> population);

	/**
	 * 约束4:实有人口中是否可以查看业务数据
	 * 
	 * @param orgId
	 * @param idCardNo
	 * @return
	 */
	public Map<String, Map<String, Object>> getPopulationSpecializedInfoByOrgIdAndIdCardNo(
			Long orgId, String idCardNo);

	/**
	 * 修改人员基本信息
	 * 
	 * @param actualPopulation
	 */
	public void updatePopulationBaseInfo(ActualPopulation actualPopulation);

	/**
	 * 约束5:删除实有人口时，是否删除业务数据
	 * 
	 * @param idCardNo
	 * @param orgId
	 */
	public void deletePopulationByPopulationId(Long populationId);
	/**
	 * 根据id删除重复数据的记录
	 * 
	 * @param id
	 */
	public void deleteBusinessPopulationDuplicate(Long id);
}
