package com.tianque.baseInfo.floatingPopulation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.floatingPopulation.dao.FloatingPopulationDao;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.gis.domain.vo.HousePopulationVo;
import com.tianque.gis.domain.vo.PopulationVo;
import com.tianque.gis.service.CommonMark;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("queryFloatingPopulationForGis")
@Transactional
public class QueryFloatingPopulationForGis extends CommonMark {

	@Autowired
	private FloatingPopulationDao floatingPopulationDao;
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
		FloatingPopulation floatingPopulation = floatingPopulationDao
				.getFloatingPopulationById(populationId);
		PopulationVo populationVo = new PopulationVo();
		if (floatingPopulation.getIsHaveHouse() != null && floatingPopulation.getIsHaveHouse()) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(
					PopulationCatalog.FLOATING_POPULATION, floatingPopulation);
			if (null != floatingPopulation.getHouseId()) {
				HouseInfo houseInfo = actualHouseService.getHouseInfoById(floatingPopulation
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
			if (null != floatingPopulation.getNoHouseReason()) {
				populationVo.setNoHouseReason(floatingPopulation.getNoHouseReason());
			} else {
				populationVo.setNoHouseReason("暂未填写");
			}
		}
		populationVo.setKeyPersonType(PopulationType.FLOATING_POPULATION);
		populationVo.setIsHaveHouse(floatingPopulation.getIsHaveHouse());
		populationVo.setGender(floatingPopulation.getGender());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER, floatingPopulation
				.getGender().getId()));
		populationVo.setIdCardNo(floatingPopulation.getIdCardNo());
		populationVo.setImgUrl(floatingPopulation.getImgUrl());
		populationVo.setName(floatingPopulation.getName());
		populationVo.setPersonId(floatingPopulation.getId());
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
