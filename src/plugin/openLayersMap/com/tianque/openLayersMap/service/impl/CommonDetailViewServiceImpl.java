package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.gis.util.GisGlobalValue;
import com.tianque.openLayersMap.dao.CommonTwoDimensionMapManageDao;
import com.tianque.openLayersMap.domian.CommonEntityInfoVo;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisModuleVo;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.service.SysGisFunctionService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.openLayersMap.service.SysModuleManageService;

/**
 * 公共绑定实现类
 * 
 * @date 2013-3-18
 */
@Service("commonMapDetailViewService")
public class CommonDetailViewServiceImpl extends
		AbstractCommonDetailViewService<CommonEntityInfoVo> {

	@Autowired
	@Qualifier("commonTwoDimensionMapManageDao")
	private CommonTwoDimensionMapManageDao commonTwoDimensionMapManageDao;
	@Autowired
	private SysModuleManageService sysModuleManageService;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;
	@Autowired
	private SysGisFunctionService sysGisFunctionService;

	@Override
	public List<Object> getViewPopupInfoByIdAndTableNameAndType(Long id,
			String tableName, String type, String childTableName,
			Boolean isSerachMode, String functionType, Long orgId) {
		if (id == null || tableName == null) {
			throw new BusinessValidationException("参数错误");
		}
		List<Object> objectList = new ArrayList<Object>();
		CommonEntityInfoVo returnValue = getCommonEntityInfoByTableNameAndFields(
				id, tableName, type, functionType);
		objectList.add(returnValue);

		GisTypeManage gisTypeManage = new GisTypeManage();
		gisTypeManage.setInnerKey(GisGlobalValue.PERSON_MODE);
		objectList.add(sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(gisTypeManage));// 返回选中的人员类型

		gisTypeManage.setInnerKey(GisGlobalValue.PLACE_MODE);
		objectList.add(sysGisTypeManageService
				.getNeedGisTypeManagesByInnerType(gisTypeManage));// 返回选中的重点场所类型
		return objectList;
	}

	private CommonEntityInfoVo getCommonEntityInfoByTableNameAndFields(Long id,
			String tableName, String type, String functionType) {
		try {
			CommonEntityInfoVo returnValue = new CommonEntityInfoVo();
			Map<String, Object> map = new HashMap<String, Object>();
			if (type == null || type.trim().equals("")) {
				GisModuleVo gisModuleVo = sysModuleManageService
						.getModuleByTableName(tableName);
				GisFunction gisFunction = sysGisFunctionService
						.getGisFunctionByModuleIdAndFunctionType(
								gisModuleVo.getId(), functionType);
				map = constructGisModuleMap(id, tableName, gisFunction,
						gisModuleVo);
			} else {
				GisTypeManage gisTypeManage = sysGisTypeManageService
						.getGisTypeManageByTableNameAndKeyType(tableName,
								type.toUpperCase());
				GisFunction gisFunction = sysGisFunctionService
						.getGisFunctionBySonClassIdAndFunctionType(
								gisTypeManage.getId(), functionType);
				map = constructGisTypeMap(id, tableName, gisFunction,
						gisTypeManage, type.toUpperCase());
			}
			if (map == null) {
				return null;
			}
			returnValue = (CommonEntityInfoVo) commonTwoDimensionMapManageDao
					.getDomainByTableNameAndFields(map);
			returnValue.setDetailsUrl((String) map.get("detailsUrl"));
			returnValue.setIconUrl((String) map.get("iconUrl"));
			returnValue.setModuleName((String) map.get("moduleName"));
			return returnValue;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceValidationException();
		}
	}

	private Map<String, Object> constructGisModuleMap(Long id,
			String tableName, GisFunction domain, GisModuleVo gisModuleVo) {
		if (domain == null)
			return null;
		Map<String, Object> map = constructCommonGisMap(id, tableName, domain);
		map.put("moduleName", gisModuleVo.getModuleName());
		return map;
	}

	private Map<String, Object> constructGisTypeMap(Long id, String tableName,
			GisFunction domain, GisTypeManage gisTypeManage, String type) {
		if (domain == null)
			return null;
		Map<String, Object> map = constructCommonGisMap(id, tableName, domain);
		map.put("moduleName", gisTypeManage.getName());
		if (type != null && !type.trim().equals("")) {
			map.put("type", type);
		}
		return map;

	}

	private Map<String, Object> constructCommonGisMap(Long id,
			String tableName, GisFunction domain) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("tableName", tableName);
		map.put("fieldA", domain.getFieldA());
		map.put("fieldNameA", domain.getFieldNameA());
		map.put("fieldB", domain.getFieldB());
		map.put("fieldNameB", domain.getFieldNameB());
		map.put("fieldC", domain.getFieldC());
		map.put("fieldNameC", domain.getFieldNameC());
		map.put("fieldD", domain.getFieldD());
		map.put("fieldNameD", domain.getFieldNameD());
		map.put("fieldE", domain.getFieldE());
		map.put("fieldNameE", domain.getFieldNameE());
		map.put("titleName", domain.getTitleName());
		map.put("titleValue", domain.getTitleValue());
		map.put("detailsUrl", domain.getDetailsUrl());
		map.put("iconUrl", domain.getIconUrl());
		map.put("detailFieldA", domain.getDetailFieldA());
		map.put("detailFieldNameA", domain.getDetailFieldNameA());
		map.put("detailFieldB", domain.getDetailFieldB());
		map.put("detailFieldNameB", domain.getDetailFieldNameB());
		map.put("detailFieldC", domain.getDetailFieldC());
		map.put("detailFieldNameC", domain.getDetailFieldNameC());
		map.put("detailFieldD", domain.getDetailFieldD());
		map.put("detailFieldNameD", domain.getDetailFieldNameD());
		map.put("detailFieldE", domain.getDetailFieldE());
		map.put("detailFieldNameE", domain.getDetailFieldNameE());
		return map;
	}

}
