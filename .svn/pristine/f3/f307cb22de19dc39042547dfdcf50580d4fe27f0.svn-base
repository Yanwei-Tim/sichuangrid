package com.tianque.gis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.HouseLivingPopulationInfo;
import com.tianque.gis.domain.vo.PopulationVo;
import com.tianque.gis.service.SearchGisPopulationService;
import com.tianque.service.HousePopulationApplyService;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("searchPopulationService")
public class SearchGisPopulationServiceImpl implements SearchGisPopulationService {
	@Autowired
	private HousePopulationApplyService housePopulationApplyService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;

	@Override
	public List<PopulationVo> findPopulationByPersonId(Long personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<PopulationVo> findPopulationsByOrgId(Long orgId, Integer page, Integer rows,
			String sidx, String sord) {
		return null;
	}

	/**
	 * 单次过滤
	 */
	@Override
	public PageInfo<PopulationVo> getFurtherStepGisPopulationInfoByPersonType(Long orgId,
			String personType, String queryStr, Integer page, Integer rows, String sidx, String sord) {
		PageInfo<HouseLivingPopulationInfo> hPageInfo = housePopulationApplyService
				.findFurtherStepGisPersonInfoSearchByPersonTypeAndOrgId(orgId, personType,
						queryStr, page, rows, sidx, sord);
		return exchangeHouseLivingPopulationInfoToPopulationVoPageInfo(hPageInfo);
	}

	/**
	 * 数据交换
	 * 
	 * @param hPageInfo
	 *        源数据
	 * @return pageInfo
	 */
	private PageInfo<PopulationVo> exchangeHouseLivingPopulationInfoToPopulationVoPageInfo(
			PageInfo<HouseLivingPopulationInfo> hPageInfo) {
		List<HouseLivingPopulationInfo> houseLivingPopulationInfos = hPageInfo.getResult();
		List<PopulationVo> populationVos = new ArrayList<PopulationVo>();
		PageInfo<PopulationVo> pageInfo = new PageInfo<PopulationVo>();
		for (HouseLivingPopulationInfo houseLivingPopulationInfo : houseLivingPopulationInfos) {
			PopulationVo populationVo = new PopulationVo();
			// 判断 isHaveHouse 是否为真，如果false,则读取无房原因，不读取 gisInfo(),且该人员的房屋相关信息无法操作
			if (null != houseLivingPopulationInfo.getIsHaveHouse()
					&& houseLivingPopulationInfo.getIsHaveHouse()) {
				managePopulationByHouseHelper.loadHouseIdForGis(PopulationCatalog
						.populationCatalog(houseLivingPopulationInfo.getTypeCatalogName()),
						houseLivingPopulationInfo);
				if (houseLivingPopulationInfo.getHouseId() != null) {
					HouseInfo houseInfo = actualHouseService
							.getHouseInfoById(houseLivingPopulationInfo.getHouseId());
					populationVo.setHouseId(houseLivingPopulationInfo.getHouseId());
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
				if (null != houseLivingPopulationInfo.getNoHouseReason()) {
					populationVo.setNoHouseReason(houseLivingPopulationInfo.getNoHouseReason());
				} else {
					populationVo.setNoHouseReason("暂未填写");
				}
			}
			// populationVo.setPopulationType(houseLivingPopulationInfo.getType().getDisplayName());
			populationVo.setKeyPersonType(houseLivingPopulationInfo.getTypeCatalogName());
			populationVo.setIsHaveHouse(houseLivingPopulationInfo.getIsHaveHouse());
			populationVo.setPersonId(houseLivingPopulationInfo.getId());
			populationVo.setIdCardNo(houseLivingPopulationInfo.getCertificateNumber());
			populationVo.setName(houseLivingPopulationInfo.getPersonName());
			populationVo.setGenderName(getPropertyDictText(PropertyTypes.GENDER,
					houseLivingPopulationInfo.getGenderId()));
			populationVos.add(populationVo);
		}
		pageInfo.setResult(populationVos);
		pageInfo.setTotalRowSize(hPageInfo.getTotalRowSize());
		pageInfo.setCurrentPage(hPageInfo.getCurrentPage());
		pageInfo.setPerPageSize(hPageInfo.getPerPageSize());
		return pageInfo;
	}

	private String getPropertyDictText(String domainName, Long id) {
		if (null == id) {
			return "";
		} else {
			PropertyDict dict = propertyDictService.getPropertyDictById(id);
			return dict == null ? "" : dict.getDisplayName();
		}
	}

	/**
	 * 
	 */
	@Override
	public PageInfo<PopulationVo> getFurtherStepGisPopulationInfoByPersonTypeList(Long orgId,
			List<String> personType, String queryStr, Integer page, Integer rows, String sidx,
			String sord) {
		PageInfo<HouseLivingPopulationInfo> hPageInfo = housePopulationApplyService
				.findFurtherStepGisPersonInfoSearchByPersonTypeListAndOrgId(orgId, personType,
						queryStr, page, rows, sidx, sord);
		return exchangeHouseLivingPopulationInfoToPopulationVoPageInfo(hPageInfo);
	}

	@Override
	public List<PopulationVo> findGisPopulationByOrgid(Long orgid) {
		return null;
	}

	@Override
	public PopulationVo shiftGisPopulationById(Long id) {
		return null;
	}
}
