package com.tianque.service.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.domain.vo.HouseLivingPopulationInfo;
import com.tianque.domain.vo.HouseSimpleInfoForSearch;
import com.tianque.service.HousePopulationApplyService;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("managePopulationByHouseHelper")
public class ManagePopulationByHouseHelper {
	@Autowired
	private HousePopulationMaintanceService housePopulationMaintanceService;
	@Autowired
	private HousePopulationApplyService housePopulationApplyService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ShardConversion shardConversion;

	/**
	 * 新增人房关联
	 * 
	 * @param peopleType
	 * @param people
	 * @param houseId
	 */
	public void bindHouseIfNecc(PopulationCatalog peopleType, People people,
			Long houseId) {
		if (managePopulationByHouse()) {
			housePopulationMaintanceService.bindHouse(peopleType,
					people.getId(), houseId);
		}
	}

	/**
	 * 修改人房关联，先删除原有的人房关联，在重建人房关联
	 * 
	 * @param peopleType
	 * @param people
	 * @param houseId
	 */
	public void reBindHouseIfNecc(PopulationCatalog peopleType, People people,
			Long houseId) {
		if (managePopulationByHouse()) {
			Long oldHouseId = housePopulationMaintanceService
					.getPopulationLivingHouseId(peopleType, people.getId());
			if (houseId != null && !sameHouse(oldHouseId, houseId)) {
				housePopulationMaintanceService.releaseHouse(peopleType,
						people.getId(), oldHouseId);
				housePopulationMaintanceService.bindHouse(peopleType,
						people.getId(), houseId);
			}

		}
	}

	public void loadHouseIdForGis(PopulationCatalog peopleType,
			HouseLivingPopulationInfo houseLivingPopulationInfo) {
		if (managePopulationByHouse()) {
			HouseSimpleInfoForSearch house = housePopulationMaintanceService
					.getPopulationLivingHouseInfo(peopleType,
							houseLivingPopulationInfo.getId(), shardConversion
									.getShardCode(houseLivingPopulationInfo
											.getOrgId()));
			if (house != null) {
				houseLivingPopulationInfo.setHouseId(house.getMaxId());
			}
		}
	}

	public void loadLivingHouseIfNecc(PopulationCatalog peopleType,
			People people) {
		if (managePopulationByHouse()) {
			if (people != null) {
				HouseSimpleInfoForSearch house = housePopulationMaintanceService
						.getPopulationLivingHouseInfo(peopleType, people
								.getId(), shardConversion.getShardCode(people
								.getOrganization()));

				if (house != null && house.getMaxId() != null) {
					people.setHouseId(house.getMaxId());
					people.setHouseCode(house.getHouseCode());
					people.setCommunity(house.getComnunity());
					people.setBlock(house.getBlock());
					people.setUnit(house.getUnit());
					people.setRoom(house.getRoom());
					people.setCurrentAddress(house.getAddress());
					people.setHouseCode(house.getHouseCode());
					if (house.getAddressType() != null) {
						people.setCurrentAddressType(propertyDictService
								.getPropertyDictById(house.getAddressType()
										.getId()));
					}
				}
			}

		}
	}

	public void releasePopulationBindedHouses(PopulationCatalog peopleType,
			Long populationId) {
		if (managePopulationByHouse()) {
			housePopulationMaintanceService.releasePopulationAllHouse(
					peopleType, populationId);
		}
	}

	public void releaseAllPopulationsBindedHouse(PopulationCatalog peopleType,
			Long[] populationIds) {
		if (managePopulationByHouse()) {
			housePopulationMaintanceService.releasePopulationsAllHouse(
					peopleType, populationIds);
		}
	}

	public void bindActualPopulationHouseIgnoreDefaultLiving(
			PopulationCatalog[] types, Long[] peoples, Long houseId) {
		if (managePopulationByHouse()) {
			List<HouseLivingPopulationInfo> popuulations = housePopulationApplyService
					.findLivingHousePopulationInfos(
							PopulationCatalog.ACTUAL_POPULATION, houseId);
			List<Integer> exsited = new ArrayList<Integer>();
			for (HouseLivingPopulationInfo popuulation : popuulations) {
				int index = findLivingIndex(popuulation, types, peoples);
				if (index == -1) {
					housePopulationMaintanceService
							.releaseHouse(popuulation.getType(),
									popuulation.getId(), houseId);
				} else {
					exsited.add(index);
				}
			}
			for (int index = 0; index < peoples.length; index++) {
				if (!exsited.contains(Integer.valueOf(index))
						&& types[index] != null && peoples[index] != null) {
					housePopulationMaintanceService
							.bindHouseIgnoreDefaultLiving(types[index],
									peoples[index], houseId);
				}
			}
		}
	}

	private int findLivingIndex(HouseLivingPopulationInfo popuulation,
			PopulationCatalog[] types, Long[] peoples) {
		for (int index = 0; index < peoples.length; index++) {
			if (popuulation.getId().equals(peoples[index])
					&& popuulation.getType().equals(types[index])) {
				return index;
			}
		}
		return -1;
	}

	private boolean sameHouse(Long oldHouse, Long newHouse) {
		if (oldHouse == null && newHouse == null) {
			return Boolean.TRUE;
		} else if (oldHouse == null || newHouse == null) {
			return Boolean.FALSE;
		} else {
			return oldHouse.equals(newHouse);
		}
	}

	/**
	 * 钩子 返回true
	 * 
	 * @return
	 */
	private boolean managePopulationByHouse() {
		return true;
	}

	public Long getPopulationLivingHouseId(PopulationCatalog catalog, Long id) {
		return housePopulationMaintanceService.getPopulationLivingHouseId(
				catalog, id);
	}
}
