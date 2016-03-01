package com.tianque.baseInfo.overseaPersonnel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.overseaPersonnel.dao.OverseaPersonnelDao;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.gis.domain.vo.HousePopulationVo;
import com.tianque.gis.domain.vo.PopulationVo;
import com.tianque.gis.service.CommonMark;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("queryOverseaStaffPopulationForGis")
@Transactional
public class QueryOverseaPersonnelPopulationForGis extends CommonMark {

	@Autowired
	private OverseaPersonnelDao overseaPersonnelDao;
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
		OverseaPersonnel overseaPersonnel = overseaPersonnelDao
				.getOverseaPersonnelById(populationId);
		PopulationVo populationVo = new PopulationVo();
		if (overseaPersonnel.getIsHaveHouse() != null && overseaPersonnel.getIsHaveHouse()) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(
					PopulationCatalog.OVERSEA_POPULATION, overseaPersonnel);
			if (null != overseaPersonnel.getHouseId()) {
				HouseInfo houseInfo = actualHouseService.getHouseInfoById(overseaPersonnel
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
			if (null != overseaPersonnel.getNoHouseReason()) {
				populationVo.setNoHouseReason(overseaPersonnel.getNoHouseReason());
			} else {
				populationVo.setNoHouseReason("暂未填写");
			}
		}
		populationVo.setKeyPersonType(PopulationType.OVERSEA_STAFF);
		populationVo.setIsHaveHouse(overseaPersonnel.getIsHaveHouse());
		populationVo.setGender(overseaPersonnel.getGender());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER, overseaPersonnel
				.getGender().getId()));
		populationVo.setIdCardNo(overseaPersonnel.getIdCardNo());
		populationVo.setImgUrl(overseaPersonnel.getImgUrl());
		populationVo.setName(overseaPersonnel.getName());
		populationVo.setPersonId(overseaPersonnel.getId());
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
