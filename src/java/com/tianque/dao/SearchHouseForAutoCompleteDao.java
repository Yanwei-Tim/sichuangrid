package com.tianque.dao;

import java.util.List;

import com.tianque.domain.vo.HouseSimpleInfoForSearch;

public interface SearchHouseForAutoCompleteDao {

	public List<HouseSimpleInfoForSearch> searchHouseInfoByAddress(String orgCode, String address,
			Long houseType, int rows);

	public List<HouseSimpleInfoForSearch> searchHouseInfoByHouseCode(String orgCode,
			String houseCode, Long houseType, int rows);

	public List<HouseSimpleInfoForSearch> searchHouseInfoByCommunity(String orgCode,
			String community, Long houseType, int rows);

	public List<HouseSimpleInfoForSearch> searchHouseInfoByCommunityAndBlock(String orgCode,
			String community, String block, Long houseType, int rows);

	public List<HouseSimpleInfoForSearch> searchHouseInfoByCommunityAndBlockAndUnit(String orgCode,
			String community, String block, String unit, Long houseType, int rows);

	public List<HouseSimpleInfoForSearch> searchHouseInfoByCommunityAndBlockAndUnitAndRoom(
			String orgCode, String community, String block, String unit, String room,
			Long houseType, int rows);
}
