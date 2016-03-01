package com.tianque.service;

import java.util.Map;

import com.tianque.baseInfo.base.domain.ActualPopulation;

public interface SyncActualPopulationToBusinessPopulationService {

	/**
	 * 新增实口信息是同步业务信息及关联关系
	 * 
	 * @param populationType
	 * @param population
	 * @return
	 */
	public ActualPopulation proccessPopulationSpecializedInfo(
			String[] populationType, Map<String, Object> population);

	public Map<String, Map<String, Object>> getPopulationSpecializedInfoByOrgIdAndIdCardNo(
			Long actualId, Long orgId, String idCardNo, String[] populationType);

	public Map<String, Map<String, Object>> getAllPopulationSpecializedInfoByOrgIdAndIdCardNo(
			Long orgId, String idCardNo, String populationSpecializedType);

	/**
	 * 删除业务信息
	 * 
	 * @param orgId
	 * @param idCardNo
	 */
	public void deletePopulationByOrgIdAndIdCardNo(Long orgId, String idCardNo);

	/**
	 * 更新基本信息字段
	 * 
	 * @param actualPopulation
	 */
	public void updatePopulationBaseInfo(ActualPopulation actualPopulation);
}
