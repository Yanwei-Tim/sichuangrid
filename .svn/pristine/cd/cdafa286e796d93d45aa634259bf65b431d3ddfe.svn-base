package com.tianque.service;

public interface ActualPopulationMutexService {
	/**
	 * 约束1：实有人口类型是否互斥
	 * 
	 * @param populationId
	 *        人员ID
	 * @param orgId
	 *        所属组织
	 * @param idCardNo
	 *        身份证号
	 * @param actualPopulationType
	 *        实口类型
	 * @return true|false 存在|不存在
	 */
	public Boolean isActualPopuationByOrgIdAndIdCardNoAndExcludeActualPopulationType(
			Long populationId, Long orgId, String idCardNo, String actualPopulationType);

	/**
	 * 判断除actualPopulationType以外的实口中是否存在
	 * 
	 * @param orgId
	 *        所属组织
	 * @param idCardNo
	 *        身份证号
	 * @param actualPopulationType
	 *        实口类型
	 * @return true|false 存在|不存在
	 */
	public Boolean isExistByOrgIdAndIdCardNoAndExcludeActualPopulationType(Long orgId,
			String idCardNo, String actualPopulationType);
}
