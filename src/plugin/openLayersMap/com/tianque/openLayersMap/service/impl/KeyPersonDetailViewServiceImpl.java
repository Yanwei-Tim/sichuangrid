package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.KeyPersonTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.KeyPersonInfoVo;
import com.tianque.openLayersMap.util.FunctionType;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("keyPersonMapDetailViewService")
public class KeyPersonDetailViewServiceImpl extends
		AbstractDetailViewService<KeyPersonInfoVo> {

	@Autowired
	private KeyPersonTwoDimensionMapDao keyPersonTwoDimensionMapDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	protected KeyPersonInfoVo doGetViewPopupInfoByIdAndTableNameAndType(
			Long id, String tableName, String type, Long orgId) {
		GisTypeManage gisTypeManage = new GisTypeManage();
		gisTypeManage.setTableName(tableName);
		gisTypeManage = sysGisTypeManageService
				.getGisTypeManagesByTableName(gisTypeManage);
		KeyPersonInfoVo keyPersonInfoVo = keyPersonTwoDimensionMapDao
				.getViewPopupInfoByIdAndTableName(id, tableName,
						shardConversion.getShardCode(orgId));
		// 设置性别字典
		List<KeyPersonInfoVo> infos = new ArrayList<KeyPersonInfoVo>();
		infos.add(keyPersonInfoVo);
		setGender(infos);
		if (keyPersonInfoVo == null) {
			throw new BusinessValidationException("参数错误，请联系管理员!");
		}
		if (type.equals(FunctionType.REFINESEARCH)) {
			GisFunction gisFunction = sysGisFunctionService
					.getGisFunctionBySonClassIdAndFunctionType(
							gisTypeManage.getId(), type);
			if (gisFunction != null && keyPersonInfoVo != null) {
				keyPersonInfoVo.setViewUrl(gisFunction.getDetailsUrl());
			}

		}
		keyPersonInfoVo.setType(tableName);
		keyPersonInfoVo.setTypeName(gisTypeManage.getName());
		return keyPersonInfoVo;
	}

	@Override
	protected boolean validateParams(Long id, String tableName, String type) {
		return id != null && tableName != null;
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
