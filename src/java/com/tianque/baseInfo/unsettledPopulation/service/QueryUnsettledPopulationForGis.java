package com.tianque.baseInfo.unsettledPopulation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.unsettledPopulation.dao.UnsettledPopulationDao;
import com.tianque.baseInfo.unsettledPopulation.domain.UnsettledPopulation;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.gis.domain.vo.HousePopulationVo;
import com.tianque.gis.domain.vo.PopulationVo;
import com.tianque.gis.service.CommonMark;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("queryUnsettledPopulationForGis")
@Transactional
public class QueryUnsettledPopulationForGis extends CommonMark {

	@Autowired
	private UnsettledPopulationDao unsettledPopulationDao;
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
					housePopulation.getPopulationId(), housePopulation.getHouseId()));
		}
		return Populations;
	}

	@Override
	public PopulationVo getPopulationVoByPopulationIdAndHouseId(Long populationId, Long houseId) {
		UnsettledPopulation unsettledPopulation = unsettledPopulationDao
				.getUnsettledPopulationById(populationId);
		PopulationVo populationVo = new PopulationVo();
		if (unsettledPopulation.getIsHaveHouse() != null && unsettledPopulation.getIsHaveHouse()) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(
					PopulationCatalog.UNHOUSEHOULD_POPULATION, unsettledPopulation);
			if (null != unsettledPopulation.getHouseId()) {
				HouseInfo houseInfo = actualHouseService.getHouseInfoById(unsettledPopulation
						.getHouseId());
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
			if (null != unsettledPopulation.getNoHouseReason()) {
				populationVo.setNoHouseReason(unsettledPopulation.getNoHouseReason());
			} else {
				populationVo.setNoHouseReason("暂未填写");
			}
		}
		populationVo.setKeyPersonType(PopulationType.UNSETTLED_POPULATION);
		populationVo.setIsHaveHouse(unsettledPopulation.getIsHaveHouse());
		populationVo.setGender(unsettledPopulation.getGender());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER, unsettledPopulation
				.getGender().getId()));
		populationVo.setIdCardNo(unsettledPopulation.getIdCardNo());
		populationVo.setImgUrl(unsettledPopulation.getImgUrl());
		populationVo.setName(unsettledPopulation.getName());
		populationVo.setPersonId(unsettledPopulation.getId());
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
