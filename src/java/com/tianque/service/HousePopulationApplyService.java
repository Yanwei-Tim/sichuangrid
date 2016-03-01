package com.tianque.service;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.HouseLivingPopulationInfo;
import com.tianque.domain.vo.HouseLivingTotalInfo;
import com.tianque.search.vo.ActualPopulationSearchCondition;
import com.tianque.service.util.PopulationCatalog;

public interface HousePopulationApplyService {
	/**
	 * @param type
	 * @param houseId
	 * @return 查询某房屋的人员居住总况
	 */
	public List<HouseLivingTotalInfo> findLivingHouseTotalInfos(
			PopulationCatalog type, Long houseId);

	public List<HouseLivingTotalInfo> findGisActualPersonTotalInfosByOrgId(
			Long orgId);

	public int countGisAttenPopulationByOrgIdAndKinds(Long orgId,
			List<String> kinds);

	/**
	 * 统计房屋中居住实口总人数
	 * 
	 * @param houseId
	 *            房屋主键
	 * @return
	 */
	public int countActualPopulationByHouseId(Long houseId, Long orgId);

	/**
	 * @param type
	 * @param houseId
	 * @return 查询某房屋的居住人员简单信息
	 */
	public List<HouseLivingPopulationInfo> findLivingHousePopulationInfos(
			PopulationCatalog type, Long houseId);

	/**
	 * gis列表查询 用于查找同一个房屋下所有居住人
	 * 
	 * @param houseId
	 * @return
	 */
	public List<HouseLivingPopulationInfo> findActulaPopulationInHouseByHouseIdForGis(
			Long houseId, Integer page, Integer rows, String sidx, String sord);

	/**
	 * 查询可选住户信息
	 * 
	 * @param houseId
	 * @param condition
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<HouseLivingPopulationInfo> findNotLivingInHousePopulationInfos(
			Long houseId, ActualPopulationSearchCondition condition,
			Integer page, Integer rows, String sidx, String sord);

	public PageInfo<HouseLivingPopulationInfo> findGisPopulationInfosByQueryStrAndOrgId(
			Long orgId, String queryStr, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public PageInfo<HouseLivingPopulationInfo> searchActulaPersonByName(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	public PageInfo<HouseLivingPopulationInfo> findFurtherStepGisPersonInfoSearchByPersonTypeAndOrgId(
			Long orgId, String personType, String queryStr, Integer page,
			Integer rows, String sidx, String sord);

	public PageInfo<HouseLivingPopulationInfo> findFurtherStepGisPersonInfoSearchByPersonTypeListAndOrgId(
			Long orgId, List personType, String queryStr, Integer page,
			Integer rows, String sidx, String sord);

	public List<HouseLivingTotalInfo> findGisActualPersonTotalInfosByOrgs(
			List<Organization> orgs, String actulaType);

	public Map<String, Integer> getMapForActualPopulation(final Long houseId,
			Long orgId);

	public Map<String, Integer> getMapForSpecialPopulation(final Long houseId);

	/**
	 * 分页查询户籍人口信息
	 * 
	 * @param actualPopulation
	 * @param houseId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 */
	public PageInfo<HouseLivingPopulationInfo> findLivingInHousePopulationInfos(
			PopulationCatalog actualPopulation, Long houseId, Integer page,
			Integer rows, String sidx, String sord);

}
