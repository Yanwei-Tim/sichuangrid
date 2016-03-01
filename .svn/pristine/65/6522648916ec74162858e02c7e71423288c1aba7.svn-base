package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.dao.CityComponentsTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisModuleVo;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.CityComponentsInfoVo;
import com.tianque.openLayersMap.service.SysGisFunctionService;
import com.tianque.openLayersMap.service.SysModuleManageService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("cityComponentsMapCircumSearchService")
public class CityComponentsMapCircumSearchServiceImpl extends
		AbstractCircumSearchService<CityComponentsInfoVo> {
	@Autowired
	private CityComponentsTwoDimensionMapDao cityComponentsTwoDimensionMapDao;
	@Autowired
	private SysModuleManageService sysModuleManageService;
	@Autowired
	private SysGisFunctionService sysGisFunctionService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	protected PageInfo<CityComponentsInfoVo> doFindCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		PageInfo<CityComponentsInfoVo> pageInfo = cityComponentsTwoDimensionMapDao
				.findTwoDimensionMapCircumInfoByRangeAndCenterLonLat(
						circumInfoVo, pageNum, pageSize, sidx, sord);
		CityComponentsInfoVo cityComponentsInfoVo = null;
		List<CityComponentsInfoVo> list = new ArrayList<CityComponentsInfoVo>();
		List<CityComponentsInfoVo> dustbinList = pageInfo.getResult();
		for (int i = 0; i < dustbinList.size(); i++) {// 得到cityComponentsInfoVo的typeName
			GisModuleVo gisModuleVo = sysModuleManageService
					.getModuleByTableName("dustbin");
			GisFunction gisFunction = sysGisFunctionService
					.getGisFunctionByModuleIdAndFunctionType(
							gisModuleVo.getId(), circumInfoVo.getFunctionType());
			cityComponentsInfoVo = new CityComponentsInfoVo();

			cityComponentsInfoVo.setTypeName(propertyDictService
					.getPropertyDictById(
							dustbinList.get(i).getPartName().getId())
					.getDisplayName());
			cityComponentsInfoVo.setDistance(dustbinList.get(i).getDistance());
			cityComponentsInfoVo.setDustbinCode(dustbinList.get(i)
					.getDustbinCode());
			cityComponentsInfoVo.setAddress(dustbinList.get(i).getAddress());
			cityComponentsInfoVo.setType(gisModuleVo.getTableName());
			cityComponentsInfoVo.setViewUrl(gisFunction.getDetailsUrl());
			cityComponentsInfoVo.setId(dustbinList.get(i).getId());
			cityComponentsInfoVo.setLat(dustbinList.get(i).getLat().toString());
			cityComponentsInfoVo.setLon(dustbinList.get(i).getLon().toString());
			cityComponentsInfoVo.setRange(circumInfoVo.getRange());
			cityComponentsInfoVo.setOrganization(dustbinList.get(i)
					.getOrganization());
			cityComponentsInfoVo.setLat2(dustbinList.get(i).getLat2()
					.toString());
			cityComponentsInfoVo.setLon2(dustbinList.get(i).getLon2()
					.toString());
			String orgInternalCode = cityComponentsInfoVo.getOrgInternalCode();
			if (cityComponentsInfoVo.getOrganization() != null
					&& (!StringUtil.isStringAvaliable(orgInternalCode) || "null"
							.equals(orgInternalCode))) {
				Long orgId = cityComponentsInfoVo.getOrganization().getId();
				cityComponentsInfoVo
						.setOrgInternalCode(organizationDubboService
								.getSimpleOrgById(orgId).getOrgInternalCode());
			}
			list.add(cityComponentsInfoVo);
		}
		return new PageInfo<CityComponentsInfoVo>(pageNum, pageSize,
				list.size(), list);
	}
}
