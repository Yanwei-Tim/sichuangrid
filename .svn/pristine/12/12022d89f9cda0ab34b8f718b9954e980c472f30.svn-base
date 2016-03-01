package com.tianque.baseInfo.mentalPatient.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.mentalPatient.dao.MentalPatientDao;
import com.tianque.baseInfo.mentalPatient.domain.MentalPatient;
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

@Service("queryMentalPatientForGis")
@Transactional
public class QueryMentalPatientForGis extends CommonMark {

	@Autowired
	private MentalPatientDao mentalPatientDao;
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
		MentalPatient mentalPatient = mentalPatientDao.get(populationId);
		PopulationVo populationVo = new PopulationVo();
		if (mentalPatient.getIsHaveHouse() != null && mentalPatient.getIsHaveHouse()) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(PopulationCatalog.MENTAL,
					mentalPatient);
			if (null != mentalPatient.getHouseId()) {
				HouseInfo houseInfo = actualHouseService.getHouseInfoById(mentalPatient
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
			if (null != mentalPatient.getNoHouseReason()) {
				populationVo.setNoHouseReason(mentalPatient.getNoHouseReason());
			} else {
				populationVo.setNoHouseReason("暂未填写");
			}
		}
		String populationType = PopulationCatalog.MENTAL.getCatalog();
		PopulationTypeBean populationTypeBean = populationTypeDao
				.getPopulationTypeByPopulationIdAndType(mentalPatient.getId(), populationType);
		populationVo.setKeyPersonType(populationTypeBean.getActualType());
		populationVo.setIsHaveHouse(mentalPatient.getIsHaveHouse());
		populationVo.setGender(mentalPatient.getGender());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER, mentalPatient
				.getGender().getId()));
		populationVo.setIdCardNo(mentalPatient.getIdCardNo());
		populationVo.setImgUrl(mentalPatient.getImgUrl());
		populationVo.setName(mentalPatient.getName());
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
