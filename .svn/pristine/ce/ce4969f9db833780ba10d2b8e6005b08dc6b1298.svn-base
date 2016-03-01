package com.tianque.service;

import java.util.List;

import com.tianque.baseInfo.base.domain.ActualPopulation;

public interface ActualPopulationProcessorService {
	/**
	 * 约束6：查看在户籍人口、流动人口、未落户人口中身份证号码是否存在
	 * 
	 * @param orgId
	 *        组织ID
	 * @param idCardNo
	 *        身份证号码
	 * @return 实有人口信息
	 */
	public ActualPopulation getActualPopulationIncludeUnsettledPopulationByOrgIdAndIdCardNo(
			Long orgId, String idCardNo);

	/**
	 * 约束6：业务数据新增、修改依赖实有人口的方式
	 * 
	 * @param orgId
	 *        组织ID
	 * @param idCardNo
	 *        身份证号码
	 * @return 实有人口信息
	 */
	public ActualPopulation getActualPopulationbyOrgIdAndIdCardNo(Long orgId, String idCardNo);
	
	/**
	 * 包括死亡和注销
	 * 
	 * @param orgId
	 *        组织ID
	 * @param idCardNo
	 *        身份证号码
	 * @return 实有人口信息
	 */
	public ActualPopulation getActualPopulationbyOrgIdAndIdCardNoIncludeLogout(Long orgId, String idCardNo);
	
	public String getActualPopulationbyOrgIdAndIdCardNoExistedMessage(Long orgId, String idCardNo,String populationType,Long populationId);

	/**
	 * 根据身份证号查询所有实口人员信息
	 * 
	 * @param orgId
	 *        组织ID
	 * @param idCardNo
	 *        身份证号码
	 * @return 实有人口集合
	 */
	public List<ActualPopulation> getActualPopulationbyOrgIdAndIdCardNoList(Long orgId,
			String idCardNo);

	/**
	 * 约束7: 人口业务数据进行重新关注时是否依赖人口信息状态
	 * 
	 * @param orgId
	 *        组织ID
	 * @param idCardNo
	 *        身份证号码
	 * @return true|false 允许|不允许重新关注
	 */
	public boolean isActualPopulationDeathOrEmphasisByIdCardNoAndOrgId(Long orgId, String idCardNo);

	/**
	 * 根据身份证号查询所有流动和户籍人员信息
	 * 
	 * @param orgId
	 *        组织ID
	 * @param idCardNo
	 *        身份证号码
	 * @return 实有人口集合
	 */
	public List<ActualPopulation> getActualPopulationByOrgIdAndIdCardNoForList(Long orgId,
			String idCardNo);
}
