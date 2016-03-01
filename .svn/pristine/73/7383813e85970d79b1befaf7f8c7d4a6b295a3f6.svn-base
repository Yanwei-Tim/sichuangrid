package com.tianque.baseInfo.rectificativePerson.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.rectificativePerson.dao.RectificativePersonDao;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
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

@Service("queryRectificativePersonForGis")
@Transactional
public class QueryRectificativePersonForGis extends CommonMark {

	@Autowired
	private RectificativePersonDao rectificativePersonDao;
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
		RectificativePerson rectificativePerson = rectificativePersonDao.get(populationId);
		PopulationVo populationVo = new PopulationVo();
		if (rectificativePerson.getIsHaveHouse() != null && rectificativePerson.getIsHaveHouse()) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(PopulationCatalog.RECTIFICATIVE,
					rectificativePerson);
			if (null != rectificativePerson.getHouseId()) {
				HouseInfo houseInfo = actualHouseService.getHouseInfoById(rectificativePerson
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
			if (null != rectificativePerson.getNoHouseReason()) {
				populationVo.setNoHouseReason(rectificativePerson.getNoHouseReason());
			} else {
				populationVo.setNoHouseReason("暂未填写");
			}
		}
		String populationType = PopulationCatalog.RECTIFICATIVE.getCatalog();
		PopulationTypeBean populationTypeBean = populationTypeDao
				.getPopulationTypeByPopulationIdAndType(rectificativePerson.getId(), populationType);
		populationVo.setKeyPersonType(populationTypeBean.getActualType());
		populationVo.setIsHaveHouse(rectificativePerson.getIsHaveHouse());
		populationVo.setGender(rectificativePerson.getGender());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER, rectificativePerson
				.getGender().getId()));
		populationVo.setIdCardNo(rectificativePerson.getIdCardNo());
		populationVo.setImgUrl(rectificativePerson.getImgUrl());
		populationVo.setName(rectificativePerson.getName());
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
