package com.tianque.dao;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.HouseLivingPopulationInfo;
import com.tianque.domain.vo.HouseLivingTotalInfo;
import com.tianque.domain.vo.HouseSimpleInfoForSearch;
import com.tianque.search.vo.QuickFilterType;
import com.tianque.service.util.PopulationCatalog;

public interface HousePopulationDao {

	boolean bindActualPopulationHouse(PopulationCatalog catalog,
			Long populationId, Long houseId, boolean ignoreDefaultLiving,
			String shardCode);

	boolean bindImpotantPopulationHouse(PopulationCatalog catalog,
			Long populationId, Long houseId, boolean ignoreDefaultLiving);

	boolean releaseActualPopulationHouse(PopulationCatalog catalog,
			Long populationId, Long houseId, String shardCode);

	boolean releaseImpotantPopulationHouse(PopulationCatalog catalog,
			Long populationId, Long houseId);

	Long getActualPopulationLivingHouseId(PopulationCatalog catalog, Long id);

	Long getImportantPopulationLivingHouseId(PopulationCatalog catalog, Long id);

	void releaseActualPopulationAllHouse(PopulationCatalog catalog,
			Long populationId);

	void releaseImportantPopulationAllHouse(PopulationCatalog catalog,
			Long populationId);

	void releaseActualPopulationsAllHouse(PopulationCatalog catalog,
			Long[] populationIds);

	void releaseImportantPopulationsAllHouse(PopulationCatalog catalog,
			Long[] populationIds);

	HouseSimpleInfoForSearch getActualPopulationLivingHouseInfo(
			PopulationCatalog catalog, Long populationId, String shardCode);

	HouseSimpleInfoForSearch getImportantPopulationLivingHouseInfo(
			PopulationCatalog catalog, Long populationId, String shardCode);

	List<HouseLivingTotalInfo> findLivingHouseAllPopulationTotalInfos(
			Long houseId);

	List<HouseLivingTotalInfo> findLivingHouseAllActualPopulationTotalInfos(
			Long houseId);

	List<HouseLivingTotalInfo> findLivingHouseAllAttentionPopulationTotalInfos(
			Long houseId);

	List<HouseLivingTotalInfo> findLivingHouseActualPopulationTotalInfos(
			PopulationCatalog catalog, Long houseId);

	List<HouseLivingTotalInfo> findLivingHouseAttentionPopulationTotalInfos(
			PopulationCatalog catalog, Long houseId);

	List<HouseLivingPopulationInfo> findLivingHouseAllActualPopulationInfos(
			Long houseId, String shardCode);

	Integer countLivingHouseAllActualPopulationInfos(Long houseId,
			String shardCode);

	List<HouseLivingPopulationInfo> findLivingHouseAllAttentionPopulationInfos(
			Long houseId);

	List<HouseLivingPopulationInfo> findLivingHouseActualPopulationInfos(
			PopulationCatalog catalog, Long houseId);

	List<HouseLivingPopulationInfo> findLivingHouseAttentionPopulationInfos(
			PopulationCatalog catalog, Long houseId);

	PageInfo<HouseLivingPopulationInfo> findNotLivingHouseAllActualPopulationInfos(
			Long houseId, String shardCode, String orgInternalCode,
			String certificationNo, String name, QuickFilterType deathStatus,
			QuickFilterType registeStatus, Integer gender,
			List<Long> baseInfoIds, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	PageInfo<HouseLivingPopulationInfo> findGisPopulationInfosByQueryStrAndOrgcode(
			String orgInternalCode, String queryStr, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	PageInfo<HouseLivingPopulationInfo> searchActulaPersonByName(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	PageInfo<HouseLivingPopulationInfo> findFurtherStepGisPersonInfoSearchByPersonTypeAndOrgId(
			String orgInternalCode, String personType, String queryStr,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	PageInfo<HouseLivingPopulationInfo> findFurtherStepGisPersonInfoSearchByPersonTypeListAndOrgId(
			String orgInternalCode, List personType, String queryStr,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	List<HouseLivingTotalInfo> findGisActualPersonTotalInfosByorgInternalCode(
			String orgInternalCode);

	int countGisAttenPopulationByOrgIdAndKinds(String orgInternalCode,
			List<String> kinds);

	List<HouseLivingTotalInfo> findGisActualPersonTotalInfosByOrgs(
			List<Organization> orgs, String actulaType);

	int countActualPopulationByHouseId(Map<String, Object> params);

	List<HouseLivingPopulationInfo> findActulaPopulationInHouseByHouseIdForGis(
			Long houseId, Integer page, Integer rows, String sidx, String sord);

	public Map<String, Integer> getMapForSpecialPopulation(Long houseId);

	public Map<String, Integer> getMapForActualPopulation(Long houseId,
			String shardCode);

	/**
	 * （分页）根据房屋id查询房屋房客人数
	 * 
	 * @param houseId
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	PageInfo<HouseLivingPopulationInfo> findHouseholdStaffForPage(Long houseId,
			Integer page, Integer rows, String sidx, String sord,
			String shardCode);

}
