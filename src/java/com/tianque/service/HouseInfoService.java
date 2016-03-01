package com.tianque.service;

import java.util.List;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.core.vo.PageInfo;
import com.tianque.search.result.HouseSimpleInfo;
import com.tianque.search.vo.SearchHouseByAddressLibCondition;

public interface HouseInfoService {
	public HouseInfo addHouseInfo(HouseInfo houseInfo);

	public void deleteInfrastructureByHouseInfoId(Long houseInfoId);

	public HouseInfo updateHouseInfo(HouseInfo houseInfo);

	public HouseInfo getSimpleHouseInfoById(Long id);

	public boolean hasDuplicateHouseInfo(Long orgId, String houseCode,
			Long exceptedId);

	public List<HouseSimpleInfo> findHousesForAutoComplete(
			SearchHouseByAddressLibCondition condition);

	public HouseInfo findGisHouseInfoById(Long houseId);

	public HouseInfo unBundPserson(Long id, String shardCode);

	PageInfo<HouseInfo> getPageInfoByQueryStrForSearchHouseInfo(
			String orgInternalCode, String queryStr, Integer page,
			Integer rows, String sidx, String sord);

	public List<HouseInfo> countActualHouseByOrgId(Long orgId);

	public PageInfo<HouseInfo> searchkeyHouseInfoListByorgId(
			String orgInternalCode, String houseType, Integer page,
			Integer rows, String sidx, String sord);

	public List<HouseInfo> findAllBindingHouseInfoByOrgInternalCode(
			final String orgInternalCode);

	public List<HouseInfo> findAllBindingHouseInfoByBuildingId(
			final Long buildingId);

	public List<HouseInfo> findHouseInfoByOrgIdAddress(Long orgId,
			String address);

	public boolean disposeHouseMegerInfo(HouseInfo houseInfo, Long megerId,
			Integer useRentalHouse);

}
