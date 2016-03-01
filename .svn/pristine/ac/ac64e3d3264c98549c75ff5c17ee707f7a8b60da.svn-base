package com.tianque.openLayersMap.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.openLayersMap.dao.KeyPersonTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisModuleVo;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.GpsInfoVo;
import com.tianque.openLayersMap.domian.vo.KeyPersonInfoVo;
import com.tianque.openLayersMap.service.CircumSearchService;
import com.tianque.openLayersMap.service.SysGisFunctionService;
import com.tianque.openLayersMap.service.SysModuleManageService;
import com.tianque.openLayersMap.util.GisProperties;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 周边搜索(重点人员)
 * 
 * @date 2013-3-15
 */
@Service("keyPersonMapCircumSearchService")
public class KeyPersonCircumSearchServiceImpl extends
		AbstractCircumSearchService<KeyPersonInfoVo> {

	@Autowired
	private KeyPersonTwoDimensionMapDao keyPersonTwoDimensionMapDao;
	@Autowired
	private SysGisFunctionService sysGisFunctionService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private SysModuleManageService sysModuleManageService;
	@Autowired
	@Qualifier("gpsMapCircumSearchService")
	private CircumSearchService gpsMapCircumSearchService;

	@Autowired
	private ShardConversion shardConversion;

	@Override
	public PageInfo<KeyPersonInfoVo> doFindCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		PageInfo<KeyPersonInfoVo> pageInfo = keyPersonTwoDimensionMapDao
				.findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat(
						circumInfoVo, pageNum, pageSize, sidx, sord,
						shardConversion.getShardCode(circumInfoVo.getOrgId()));
		// 设置性别
		setGender(pageInfo.getResult());
		for (int i = 0; i < pageInfo.getResult().size(); i++) {// 得到KeyPersonInfoVo的typeName和type和详情查看url
			GisTypeManage gisTypeManage = new GisTypeManage();
			String keyType = pageInfo.getResult().get(i).getType();
			if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0")) {
				keyType = keyType.toUpperCase();
			}
			gisTypeManage.setKey(keyType);

			gisTypeManage = sysGisTypeManageService
					.getGisTypeManagesByKey(gisTypeManage);
			GisFunction gisFunction = sysGisFunctionService
					.getGisFunctionBySonClassIdAndFunctionType(
							gisTypeManage.getId(),
							circumInfoVo.getFunctionType());

			String typeName = gisTypeManage.getName();
			pageInfo.getResult().get(i).setTypeName(typeName);
			pageInfo.getResult().get(i).setType(gisTypeManage.getTableName());
			pageInfo.getResult().get(i).setViewUrl(gisFunction.getDetailsUrl());

		}
		pageInfo = doFindGpsCircumInfoByElementsAndRangeAndCenterLonLat(
				pageInfo, circumInfoVo, pageNum, pageSize, sidx, sord);
		return pageInfo;
	}

	private PageInfo<KeyPersonInfoVo> doFindGpsCircumInfoByElementsAndRangeAndCenterLonLat(
			PageInfo<KeyPersonInfoVo> personPageInfo,
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (personPageInfo.getResult().size() < pageSize
				&& circumInfoVo.getElements().contains("deviceInformation")) {
			circumInfoVo.setElements("deviceInformation");
			PageInfo<GpsInfoVo> pageInfo = gpsMapCircumSearchService
					.findCircumInfoByElementsAndRangeAndCenterLonLat(
							circumInfoVo, pageNum, pageSize, sidx, sord);
			KeyPersonInfoVo keyPersonInfoVo = null;
			GpsInfoVo gpsInfoVo = null;
			List<KeyPersonInfoVo> list = personPageInfo.getResult();
			GisModuleVo gisModuleVo = sysModuleManageService
					.getModuleByTableName("deviceInformation");
			GisFunction gisFunction = sysGisFunctionService
					.getGisFunctionByModuleIdAndFunctionType(
							gisModuleVo.getId(), circumInfoVo.getFunctionType());
			for (int i = 0; i < pageInfo.getResult().size(); i++) {// 得到keyPlaceInfoVo的typeName
				gpsInfoVo = pageInfo.getResult().get(i);
				keyPersonInfoVo = new KeyPersonInfoVo();
				keyPersonInfoVo.setDistance(gpsInfoVo.getDistance());
				keyPersonInfoVo.setName(gpsInfoVo.getUserName());
				keyPersonInfoVo.setAddress(gpsInfoVo.getOrgName());
				keyPersonInfoVo.setTypeName(gisModuleVo.getModuleName());
				keyPersonInfoVo.setType(gisModuleVo.getTableName());
				keyPersonInfoVo.setViewUrl(gisFunction.getDetailsUrl());
				keyPersonInfoVo.setId(gpsInfoVo.getId());
				keyPersonInfoVo.setLat(gpsInfoVo.getLat());
				keyPersonInfoVo.setLon(gpsInfoVo.getLon());
				keyPersonInfoVo.setLat2(gpsInfoVo.getLat2());
				keyPersonInfoVo.setLon2(gpsInfoVo.getLon2());
				list.add(keyPersonInfoVo);
			}
			personPageInfo.setResult(list);
			personPageInfo.setTotalRowSize(list.size());
		}
		return personPageInfo;
	}

	private void setGender(List<KeyPersonInfoVo> infos) {
		if (infos == null || infos.isEmpty()) {
			return;
		}
		for (Iterator<KeyPersonInfoVo> it = infos.iterator(); it.hasNext();) {
			KeyPersonInfoVo info = it.next();
			PropertyDict gender = info.getGender();
			if (gender == null) {
				return;
			}
			Long id = info.getGender().getId();
			PropertyDict dict = propertyDictService.getPropertyDictById(id);
			if (dict != null) {
				info.setGender(dict);
				info.setGenderName(dict.getDisplayName());
			}
		}
	}

}
