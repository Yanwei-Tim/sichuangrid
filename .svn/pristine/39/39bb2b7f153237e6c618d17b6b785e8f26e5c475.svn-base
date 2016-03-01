package com.tianque.baseInfo.superiorVisit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.superiorVisit.dao.SuperiorVisitDao;
import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
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

@Service("querySuperiorVisitPopulationForGis")
@Transactional
public class QuerySuperiorVisitPopulationForGis extends CommonMark {

	@Autowired
	private SuperiorVisitDao superiorVisitDao;
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
		SuperiorVisit superiorVisit = superiorVisitDao.get(populationId);
		PopulationVo populationVo = new PopulationVo();
		if (superiorVisit.getIsHaveHouse() != null && superiorVisit.getIsHaveHouse()) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(PopulationCatalog.SUPERIORVISIT,
					superiorVisit);
			if (null != superiorVisit.getHouseId()) {
				HouseInfo houseInfo = actualHouseService.getHouseInfoById(superiorVisit
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
			if (null != superiorVisit.getNoHouseReason()) {
				populationVo.setNoHouseReason(superiorVisit.getNoHouseReason());
			} else {
				populationVo.setNoHouseReason("暂未填写");
			}
		}
		String populationType = PopulationCatalog.SUPERIORVISIT.getCatalog();
		PopulationTypeBean populationTypeBean = populationTypeDao
				.getPopulationTypeByPopulationIdAndType(superiorVisit.getId(), populationType);
		populationVo.setKeyPersonType(populationTypeBean.getActualType());
		populationVo.setIsHaveHouse(superiorVisit.getIsHaveHouse());
		populationVo.setGender(superiorVisit.getGender());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER, superiorVisit
				.getGender().getId()));
		populationVo.setIdCardNo(superiorVisit.getIdCardNo());
		populationVo.setImgUrl(superiorVisit.getImgUrl());
		populationVo.setName(superiorVisit.getName());
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
