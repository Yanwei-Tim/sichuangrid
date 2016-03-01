package com.tianque.baseInfo.dangerousGoodsPractitioner.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.dangerousGoodsPractitioner.dao.DangerousGoodsPractitionerDao;
import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
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

@Service("queryDangerousGoodsPractitionerForGis")
@Transactional
public class QueryDangerousGoodsPractitionerForGis extends CommonMark {

	@Autowired
	private DangerousGoodsPractitionerDao dangerousGoodsPractitionerDao;
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
		DangerousGoodsPractitioner dangerousGoodsPractitioner = dangerousGoodsPractitionerDao
				.get(populationId);
		PopulationVo populationVo = new PopulationVo();
		if (dangerousGoodsPractitioner.getIsHaveHouse() != null
				&& dangerousGoodsPractitioner.getIsHaveHouse()) {
			managePopulationByHouseHelper.loadLivingHouseIfNecc(
					PopulationCatalog.DANGEROUS_GOODS_PRACTITIONER, dangerousGoodsPractitioner);
			if (null != dangerousGoodsPractitioner.getHouseId()) {
				HouseInfo houseInfo = actualHouseService
						.getHouseInfoById(dangerousGoodsPractitioner.getHouseId());
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
			if (null != dangerousGoodsPractitioner.getNoHouseReason()) {
				populationVo.setNoHouseReason(dangerousGoodsPractitioner.getNoHouseReason());
			} else {
				populationVo.setNoHouseReason("暂未填写");
			}
		}
		String populationType = PopulationCatalog.DANGEROUS_GOODS_PRACTITIONER.getCatalog();
		PopulationTypeBean populationTypeBean = populationTypeDao
				.getPopulationTypeByPopulationIdAndType(dangerousGoodsPractitioner.getId(),
						populationType);
		populationVo.setKeyPersonType(populationTypeBean.getActualType());
		populationVo.setIsHaveHouse(dangerousGoodsPractitioner.getIsHaveHouse());
		populationVo.setGender(dangerousGoodsPractitioner.getGender());
		populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER,
				dangerousGoodsPractitioner.getGender().getId()));
		populationVo.setIdCardNo(dangerousGoodsPractitioner.getIdCardNo());
		populationVo.setImgUrl(dangerousGoodsPractitioner.getImgUrl());
		populationVo.setName(dangerousGoodsPractitioner.getName());
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
