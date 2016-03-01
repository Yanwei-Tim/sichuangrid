package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.PersonTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.PersonInfoVo;
import com.tianque.openLayersMap.util.FunctionType;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.openLayersMap.util.GisProperties;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 人口信息详情查看实现类
 * 
 * @date 2013-3-18
 */
@Service("personMapDetailViewService")
public class PersonDetailViewServiceImpl extends
		AbstractDetailViewService<PersonInfoVo> {

	@Autowired
	private PersonTwoDimensionMapDao personTwoDimensionMapDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	protected PersonInfoVo doGetViewPopupInfoByIdAndTableNameAndType(Long id,
			String tableName, String type, Long orgId) {
		PersonInfoVo personInfoVo = new PersonInfoVo();
		if (type.trim().equals("null") || type.trim().equals("")) {
			personInfoVo = personTwoDimensionMapDao
					.getViewPopupInfoByIdAndTableName(id, tableName, type,
							shardConversion.getShardCode(orgId));
			setDisplayName(personInfoVo);
		} else {
			GisTypeManage gisTypeManage = new GisTypeManage();
			if (GisGlobalValueMap.personalType.get(type) != null) {
				type = GisGlobalValueMap.personalType.get(type);
			}
			gisTypeManage.setKey(type);
			gisTypeManage = sysGisTypeManageService
					.getGisTypeManagesByKey(gisTypeManage);
			if (GisProperties.INTEGRATION_VERSIONS.equals("product3.0")) {
				tableName = GisGlobalValueMap.keyPersonalType.get(type);// 根据类型得到表名
			} else {
				tableName = GisGlobalValueMap.PersonType.get(type);
			}
			personInfoVo = personTwoDimensionMapDao
					.getViewPopupInfoByIdAndTableName(id, tableName, type,
							shardConversion.getShardCode(orgId));
			if (personInfoVo == null) {
				throw new BusinessValidationException("参数错误，请联系管理员!");
			}
			setDisplayName(personInfoVo);
			GisFunction gisFunction = sysGisFunctionService
					.getGisFunctionBySonClassIdAndFunctionType(
							gisTypeManage.getId(), FunctionType.REFINESEARCH);
			if (gisFunction != null && personInfoVo != null) {
				personInfoVo.setViewUrl(gisFunction.getDetailsUrl());
				personInfoVo.setPersonType(type);
				personInfoVo.setPersonTypeName(gisTypeManage.getName());
			}
		}

		return personInfoVo;
	}

	private void setDisplayName(PersonInfoVo info) {
		if (info == null) {
			return;
		}
		PropertyDict dict = null;
		if (info.getGenderId() != null && info.getGenderId().getId() != null) {
			dict = propertyDictService.getPropertyDictById(info.getGenderId()
					.getId());
		}
		PropertyDict dict2 = null;
		if (info.getCertificateTypeId() != null
				&& info.getCertificateTypeId().getId() != null) {
			dict2 = propertyDictService.getPropertyDictById(info
					.getCertificateTypeId().getId());
		}
		if (dict != null) {
			info.setGender(dict.getDisplayName());
			info.setGenderId(dict);
		}
		if (dict2 != null) {
			info.setCertificateType(dict2.getDisplayName());
			info.setCertificateTypeId(dict2);
		}
	}

	@Override
	protected boolean validateParams(Long id, String tableName, String type) {
		return id != null && type != null;
	}

}
