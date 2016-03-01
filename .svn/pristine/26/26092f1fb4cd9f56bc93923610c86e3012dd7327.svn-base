package com.tianque.baseInfo.nurturesWomen.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.nurturesWomen.dao.NurturesWomenDao;
import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
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

@Service("queryNurturesWomenForGis")
@Transactional
public class QueryNurturesWomenForGis extends CommonMark {

	@Autowired
	private NurturesWomenDao nurturesWomenDao;
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
		NurturesWomen nurturesWomen = nurturesWomenDao.get(populationId);
		PopulationVo populationVo = new PopulationVo();
		if (nurturesWomen.getIsHaveHouse() != null && nurturesWomen.getIsHaveHouse()) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(PopulationCatalog.NURTURES_WOMEN,
					nurturesWomen);
			if (null != nurturesWomen.getHouseId()) {
				HouseInfo houseInfo = actualHouseService.getHouseInfoById(nurturesWomen
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
			if (null != nurturesWomen.getNoHouseReason()) {
				populationVo.setNoHouseReason(nurturesWomen.getNoHouseReason());
			} else {
				populationVo.setNoHouseReason("暂未填写");
			}
		}
		String populationType = PopulationCatalog.NURTURES_WOMEN.getCatalog();
		PopulationTypeBean populationTypeBean = populationTypeDao
				.getPopulationTypeByPopulationIdAndType(nurturesWomen.getId(), populationType);
		populationVo.setKeyPersonType(populationTypeBean.getActualType());
		populationVo.setIsHaveHouse(nurturesWomen.getIsHaveHouse());
		populationVo.setGender(nurturesWomen.getGender());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER, nurturesWomen
				.getGender().getId()));
		populationVo.setIdCardNo(nurturesWomen.getIdCardNo());
		populationVo.setImgUrl(nurturesWomen.getImgUrl());
		populationVo.setName(nurturesWomen.getName());
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
