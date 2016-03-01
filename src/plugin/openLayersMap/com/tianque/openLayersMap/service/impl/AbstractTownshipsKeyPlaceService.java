package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.openLayersMap.util.GisGlobalValueMap;

public abstract class AbstractTownshipsKeyPlaceService extends BaseService {

	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;

	/**
	 * 
	 * 转化组织场所类型列表
	 * 
	 */
	public List<String> getTypeList(String keyPlaceType) {
		List<String> typeList = new ArrayList<String>();
		GisTypeManage gisTypeManage = new GisTypeManage();
		if (keyPlaceType != null && !"".equals(keyPlaceType)) {
			gisTypeManage.setInnerKey(keyPlaceType);
			List<GisTypeManage> list = sysGisTypeManageService
					.getNeedGisTypeManagesByInnerType(gisTypeManage);
			if (null != list && list.size() > 0)
				for (GisTypeManage gtm : list) {
					typeList.add(gtm.getKey());
				}
		}
		return typeList;
	}

	/**
	 * 得到组织场所的所有子类信息
	 * 
	 * @return
	 */
	public List<GisTypeManage> getPlacesSubclassList() {

		List<GisTypeManage> companyPlaceBaseGisTypeManages = new ArrayList<GisTypeManage>();
		GisTypeManage companyPlaceBaseGisTypeManage = new GisTypeManage();
		companyPlaceBaseGisTypeManage
				.setInnerKey(GisGlobalValueMap.NEW_COMPANY_PLACE_MODE);
		companyPlaceBaseGisTypeManages = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(companyPlaceBaseGisTypeManage);// 场所

		List<GisTypeManage> unitPlaceBaseGisTypeManages = new ArrayList<GisTypeManage>();
		GisTypeManage unitPlaceBaseGisTypeManage = new GisTypeManage();
		unitPlaceBaseGisTypeManage
				.setInnerKey(GisGlobalValueMap.NEW_UNIT_PLACE_MODE);
		unitPlaceBaseGisTypeManages = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(unitPlaceBaseGisTypeManage);// 单位

		List<GisTypeManage> keyCompanyPlaceBaseGisTypeManages = new ArrayList<GisTypeManage>();
		GisTypeManage keyCompanyPlaceBaseGisTypeManage = new GisTypeManage();
		keyCompanyPlaceBaseGisTypeManage
				.setInnerKey(GisGlobalValueMap.KEY_COMPANY_PLACE_MODE);
		keyCompanyPlaceBaseGisTypeManages = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(keyCompanyPlaceBaseGisTypeManage);// 重点单位场所

		List<GisTypeManage> sizedEnterprisePlaceBaseGisTypeManages = new ArrayList<GisTypeManage>();
		GisTypeManage sizedEnterprisePlaceBaseGisTypeManage = new GisTypeManage();
		sizedEnterprisePlaceBaseGisTypeManage
				.setInnerKey(GisGlobalValueMap.SIZED_ENTERPRISE_PLACE_MODE);
		sizedEnterprisePlaceBaseGisTypeManages = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(sizedEnterprisePlaceBaseGisTypeManage);// 规模企业

		List<GisTypeManage> twoNewGroupGisTypeManages = new ArrayList<GisTypeManage>();
		GisTypeManage twoNewGroupGisTypeManage = new GisTypeManage();
		twoNewGroupGisTypeManage.setInnerKey(GisGlobalValueMap.TWO_NEWGROUP);
		twoNewGroupGisTypeManages = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(twoNewGroupGisTypeManage);// 两新组织子类

		List<GisTypeManage> scenicsManageGisTypeManages = new ArrayList<GisTypeManage>();
		GisTypeManage scenicsManageGisTypeManage = new GisTypeManage();
		scenicsManageGisTypeManage
				.setInnerKey(GisGlobalValueMap.SCENICS_MANAGE);
		scenicsManageGisTypeManages = sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(scenicsManageGisTypeManage);// 景区管理

		List<GisTypeManage> list = new ArrayList<GisTypeManage>();

		list.addAll(companyPlaceBaseGisTypeManages);
		list.addAll(unitPlaceBaseGisTypeManages);
		list.addAll(keyCompanyPlaceBaseGisTypeManages);
		list.addAll(sizedEnterprisePlaceBaseGisTypeManages);
		list.addAll(twoNewGroupGisTypeManages);
		list.addAll(scenicsManageGisTypeManages);
		return list;

	}
}
