package com.tianque.baseInfo.householdStaff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.householdStaff.dao.HouseholdStaffDao;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.gis.domain.vo.HousePopulationVo;
import com.tianque.gis.domain.vo.PopulationVo;
import com.tianque.gis.service.CommonMark;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("queryHouseholdStaffPopulationForGis")
@Transactional
public class QueryHouseholdStaffPopulationForGis extends CommonMark {

	@Autowired
	private HouseholdStaffDao householdStaffDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;
	@Autowired
	private ActualHouseService actualHouseService;

	@Override
	public List<PopulationVo> findPopulationVosByHousePopulations(
			List<HousePopulationVo> housePopulations) {
		List<PopulationVo> Populations = new ArrayList<PopulationVo>();
		for (HousePopulationVo housePopulation : housePopulations) {
			Populations.add(getPopulationVoByPopulationIdAndHouseId(
					housePopulation.getPopulationId(),
					housePopulation.getHouseId()));
		}
		return Populations;
	}

	@Override
	public PopulationVo getPopulationVoByPopulationIdAndHouseId(
			Long populationId, Long houseId) {
		HouseholdStaff householdStaff = householdStaffDao
				.getHouseholdStaffById(populationId);
		PopulationVo populationVo = new PopulationVo();
		if (householdStaff.getIsHaveHouse() != null
				&& householdStaff.getIsHaveHouse()) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(
					PopulationCatalog.HOUSEHOULD_POPULATION, householdStaff);
			if (null != householdStaff.getHouseId()) {
				HouseInfo houseInfo = actualHouseService
						.getHouseInfoById(householdStaff.getHouseId());
				populationVo.setHouseId(houseId);
				populationVo.setOrgId(houseInfo.getOrganization().getId());
				populationVo.setAddress(houseInfo.getAddress());
				if (null != houseInfo && null != houseInfo.getGisInfo()) {
					populationVo.setGisInfo(houseInfo.getGisInfo());
					populationVo.setEnableBind(true);
				} else {
					populationVo.setEnableBind(false);
				}
			}
		} else {
			populationVo.setEnableBind(false);
			if (null != householdStaff.getNoHouseReason()) {
				populationVo
						.setNoHouseReason(householdStaff.getNoHouseReason());
			} else {
				populationVo.setNoHouseReason("暂未填写");
			}
		}
		populationVo.setKeyPersonType(PopulationType.HOUSEHOLD_STAFF);
		populationVo.setIsHaveHouse(householdStaff.getIsHaveHouse());
		populationVo.setGender(householdStaff.getGender());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER,
				householdStaff.getGender().getId()));
		populationVo.setIdCardNo(householdStaff.getIdCardNo());
		populationVo.setImgUrl(householdStaff.getImgUrl());
		populationVo.setName(householdStaff.getName());
		populationVo.setPersonId(householdStaff.getId());
		return populationVo;
	}

	private String getPropertyDictText(String domainName, Long id) {
		if (null == id) {
			return "";
		} else {
			PropertyDict dict = propertyDictService.getPropertyDictById(id);
			return dict == null ? "" : dict.getDisplayName();
		}
	}

}
