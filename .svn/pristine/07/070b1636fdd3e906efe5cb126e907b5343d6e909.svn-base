package com.tianque.baseInfo.unemployedPeople.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.unemployedPeople.dao.UnemployedPeopleDao;
import com.tianque.baseInfo.unemployedPeople.domain.UnemployedPeople;
import com.tianque.dao.PopulationTypeDao;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.gis.domain.vo.HousePopulationVo;
import com.tianque.gis.domain.vo.PopulationVo;
import com.tianque.gis.service.CommonMark;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("queryUnemployedPeopleForGis")
@Transactional
public class QueryUnemployedPeopleForGis extends CommonMark {

	@Autowired
	private UnemployedPeopleDao unemployedPeopleDao;
	@Autowired
	private PopulationTypeDao populationTypeDao;
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
		UnemployedPeople unemployedPeople = unemployedPeopleDao.get(populationId);
		PopulationVo populationVo = new PopulationVo();
		if (unemployedPeople.getIsHaveHouse() != null && unemployedPeople.getIsHaveHouse()) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(
					PopulationCatalog.UNEMPLOYED_PEOPLE, unemployedPeople);
			if (null != unemployedPeople.getHouseId()) {
				HouseInfo houseInfo = actualHouseService.getHouseInfoById(unemployedPeople
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
			if (null != unemployedPeople.getNoHouseReason()) {
				populationVo.setNoHouseReason(unemployedPeople.getNoHouseReason());
			} else {
				populationVo.setNoHouseReason("暂未填写");
			}
		}
		String populationType = PopulationCatalog.UNEMPLOYED_PEOPLE.getCatalog();
		PopulationTypeBean populationTypeBean = populationTypeDao
				.getPopulationTypeByPopulationIdAndType(unemployedPeople.getId(), populationType);
		populationVo.setKeyPersonType(populationTypeBean.getActualType());
		populationVo.setIsHaveHouse(unemployedPeople.getIsHaveHouse());
		populationVo.setGender(unemployedPeople.getGender());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER, unemployedPeople
				.getGender().getId()));
		populationVo.setIdCardNo(unemployedPeople.getIdCardNo());
		populationVo.setImgUrl(unemployedPeople.getImgUrl());
		populationVo.setName(unemployedPeople.getName());
		populationVo.setPersonId(populationTypeBean.getActualId());
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
