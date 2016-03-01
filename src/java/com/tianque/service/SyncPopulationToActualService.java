package com.tianque.service;

import com.tianque.baseInfo.base.domain.AttentionPopulation;

public interface SyncPopulationToActualService {
	/**
	 * 维护人员关联关系
	 * 
	 * @param actualPopulation
	 *        实口信息
	 * @param populationId
	 *        业务主键
	 * @param populationType
	 *        业务类型
	 */
	public void maintainPopulationTypeRela(Long populationId, String populationType, Long orgId,
			String idCardNo);

	/**
	 * 新增或重新关注及取消注销业务的情况下对业务与实口间关联关系的维护
	 * 
	 * @param orgId
	 * @param idCardNo
	 */
	public void reBuildPopulationRela(Long populationId, String populationType, Long orgId,
			String idCardNo);

	/**
	 * 更新实口基本信息
	 * 
	 * @param population
	 * @return
	 */
	public String syncActualPopulationBaseInfo(AttentionPopulation population);
}
