package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.KeyPlaceTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.openLayersMap.util.FunctionType;

@Service("keyPlaceMapDetailViewService")
public class KeyPlaceDetailViewServiceImpl extends
		AbstractDetailViewService<KeyPlaceInfoVo> {

	@Autowired
	private KeyPlaceTwoDimensionMapDao keyPlaceTwoDimensionMapDao;

	@Override
	protected KeyPlaceInfoVo doGetViewPopupInfoByIdAndTableNameAndType(Long id,
			String childTableName, String type, Long orgId) {
		GisTypeManage gisTypeManage = new GisTypeManage();
		gisTypeManage.setKey(type);
		gisTypeManage = sysGisTypeManageService
				.getGisTypeManagesByKey(gisTypeManage);
		KeyPlaceInfoVo keyPlaceInfoVo = keyPlaceTwoDimensionMapDao
				.getViewPopupInfoByIdAndType(id, type);
		if (keyPlaceInfoVo == null) {
			throw new BusinessValidationException("参数错误，请联系管理员!");
		}
		if (childTableName.trim().equals("")) {
			GisFunction gisFunction = sysGisFunctionService
					.getGisFunctionBySonClassIdAndFunctionType(
							gisTypeManage.getId(), FunctionType.REFINESEARCH);
			if (gisFunction != null && keyPlaceInfoVo != null) {
				keyPlaceInfoVo.setViewUrl(gisFunction.getDetailsUrl());
			}
		}
		keyPlaceInfoVo.setType(type);
		keyPlaceInfoVo.setTypeName(gisTypeManage.getName());
		return keyPlaceInfoVo;
	}

	@Override
	protected boolean validateParams(Long id, String tableName, String type) {
		return id != null && type != null;
	}

}
