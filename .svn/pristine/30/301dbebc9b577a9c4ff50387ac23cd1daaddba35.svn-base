package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.dao.PublicSecurityTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.domian.vo.PublicSecurityInfoVo;
import com.tianque.openLayersMap.service.SysGisFunctionService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.openLayersMap.util.GisProperties;

/**
 * 周边搜索(公共部件)
 * 
 */
@Service("publicSecurityMapCircumSearchService")
public class publicSecurityCircumSearchServiceImpl extends
		AbstractCircumSearchService<PublicSecurityInfoVo> {

	@Autowired
	private PublicSecurityTwoDimensionMapDao publicSecurityTwoDimensionMapDao;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;
	@Autowired
	private SysGisFunctionService sysGisFunctionService;

	@Override
	protected PageInfo<PublicSecurityInfoVo> doFindCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		PageInfo<PublicSecurityInfoVo> pageInfo = publicSecurityTwoDimensionMapDao
				.findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat(
						circumInfoVo, pageNum, pageSize, sidx, sord);
		for (int i = 0; i < pageInfo.getResult().size(); i++) { // 得到PublicSecurityInfoVo的typeName和type和详情查看url
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
		return pageInfo;
	}
}
