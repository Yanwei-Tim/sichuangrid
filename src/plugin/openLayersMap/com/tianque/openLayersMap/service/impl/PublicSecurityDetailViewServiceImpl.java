package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.PublicSecurityTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.PublicSecurityInfoVo;
import com.tianque.openLayersMap.util.FunctionType;

/**
 * GIS 公共部件的详情信息
 * 
 * @author N73
 * 
 */
@Service("publicSecurityMapDetailViewService")
public class PublicSecurityDetailViewServiceImpl extends
		AbstractDetailViewService<PublicSecurityInfoVo> {

	@Autowired
	private PublicSecurityTwoDimensionMapDao publicSecurityTwoDimensionMapDao;

	@Override
	protected PublicSecurityInfoVo doGetViewPopupInfoByIdAndTableNameAndType(
			Long id, String tableName, String type, Long orgId) {
		GisTypeManage gisTypeManage = new GisTypeManage();
		gisTypeManage.setTableName(tableName);
		gisTypeManage = sysGisTypeManageService
				.getGisTypeManagesByTableName(gisTypeManage);
		PublicSecurityInfoVo publicSecurityInfoVo = publicSecurityTwoDimensionMapDao
				.getViewPopupInfoByIdAndTableName(id, tableName);
		if (publicSecurityInfoVo == null) {
			throw new BusinessValidationException("参数错误，请联系管理员!");
		}
		if (type.equals(FunctionType.REFINESEARCH)) {
			GisFunction gisFunction = sysGisFunctionService
					.getGisFunctionBySonClassIdAndFunctionType(
							gisTypeManage.getId(), type);
			if (gisFunction != null && publicSecurityInfoVo != null) {
				publicSecurityInfoVo.setViewUrl(gisFunction.getDetailsUrl());
			}

		}
		publicSecurityInfoVo.setType(tableName);
		publicSecurityInfoVo.setTypeName(gisTypeManage.getName());
		return publicSecurityInfoVo;
	}

	@Override
	protected boolean validateParams(Long id, String tableName, String type) {
		return id != null && tableName != null;
	}

}
