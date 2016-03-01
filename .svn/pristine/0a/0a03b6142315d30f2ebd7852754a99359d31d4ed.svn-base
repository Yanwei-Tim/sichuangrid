package com.tianque.baseInfo.aidNeedPopulation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.aidNeedPopulation.dao.AidNeedPopulationDao;
import com.tianque.baseInfo.aidNeedPopulation.domain.AidNeedPopulation;
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

@Service("queryAidNeedPopulationForGis")
@Transactional
public class QueryAidNeedPopulationForGis extends CommonMark {

	@Autowired
	private AidNeedPopulationDao aidNeedPopulationDao;
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
		AidNeedPopulation aidNeedPopulation = aidNeedPopulationDao.get(populationId);
		PopulationVo populationVo = new PopulationVo();
		if (aidNeedPopulation.getIsHaveHouse() != null && aidNeedPopulation.getIsHaveHouse()) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(
					PopulationCatalog.AID_NEED_POPULATION, aidNeedPopulation);
			if (null != aidNeedPopulation.getHouseId()) {
				HouseInfo houseInfo = actualHouseService.getHouseInfoById(aidNeedPopulation
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
			if (null != aidNeedPopulation.getNoHouseReason()) {
				populationVo.setNoHouseReason(aidNeedPopulation.getNoHouseReason());
			} else {
				populationVo.setNoHouseReason("暂未填写");
			}
		}
		String populationType = PopulationCatalog.AID_NEED_POPULATION.getCatalog();
		PopulationTypeBean populationTypeBean = populationTypeDao
				.getPopulationTypeByPopulationIdAndType(aidNeedPopulation.getId(), populationType);
		populationVo.setKeyPersonType(populationTypeBean.getActualType());
		populationVo.setIsHaveHouse(aidNeedPopulation.getIsHaveHouse());
		populationVo.setGender(aidNeedPopulation.getGender());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER, aidNeedPopulation
				.getGender().getId()));
		populationVo.setIdCardNo(aidNeedPopulation.getIdCardNo());
		populationVo.setImgUrl(aidNeedPopulation.getImgUrl());
		populationVo.setName(aidNeedPopulation.getName());
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
