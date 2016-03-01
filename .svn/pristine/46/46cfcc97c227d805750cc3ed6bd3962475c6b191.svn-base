package com.tianque.service;

import java.util.List;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.base.domain.ActualPopulation;

public interface ActualPopulationService {
	public ActualPopulation getActualPopulationById(Long id);

	/**
	 * 根据组织ID和身份证号获取实有人口信息
	 * 
	 * @param excludePopulationId
	 *        实有人口ID
	 * @param orgId
	 *        组织ID
	 * @param idCardNo
	 *        身份证号码
	 * @return 实有人口集合
	 */
	public List<ActualPopulation> getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdList(
			Long excludePopulationId, Long orgId, String idCardNo);

	/**
	 * 根据组织ID和身份证号获取实有人口信息
	 * 
	 * @param excludePopulationId
	 *        实有人口ID
	 * @param orgId
	 *        组织ID
	 * @param idCardNo
	 *        身份证号码
	 * @return 实有人口信息
	 */
	public ActualPopulation getActualPopulationByOrgIdAndIdCardNoExcludePopulationId(
			Long excludePopulationId, Long orgId, String idCardNo);
	
	/**
	 * 根据组织ID和身份证号获取实有人口信息(包括死亡和注销)
	 * 
	 * @param excludePopulationId
	 *        实有人口ID
	 * @param orgId
	 *        组织ID
	 * @param idCardNo
	 *        身份证号码
	 * @return 实有人口信息
	 */
	public ActualPopulation getActualPopulationByOrgIdAndIdCardNoExcludePopulationIdIncludeLogout(
			Long excludePopulationId, Long orgId, String idCardNo);

	public ActualPopulation getActualPopulationHouseId(ActualPopulation actualPopulation);

	/**
	 * 更新实口房屋状态和无房原因信息
	 * 
	 * @param isHaveHouse
	 * @param noHouseReason
	 */
	public void syncActualPopulationByDeleteHousePopulationRela(Long populationId,
			Boolean isHaveHouse, String noHouseReason);

	public void sysActualPopulationByAddHousePopulationRela(Long populationId, String address);

	/**
	 * 根据人口id和房屋信息houseInfo信息更新实口的人口的住房信息
	 * 更新实口信息之后 发送jms消息，同步其他的信息
	 * 
	 * @param populationId
	 * @param houseInfo
	 */
	public void sysActualPopulationByAddHousePopulationRela(Long populationId, HouseInfo houseInfo);
}