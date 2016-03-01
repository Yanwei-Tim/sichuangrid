package com.tianque.openLayersMap.service.impl;

// import java.lang.reflect.Field;
// import java.util.ArrayList;
// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;
import com.tianque.openLayersMap.service.Gis2DLayerService;
import com.tianque.openLayersMap.service.TownshipsUnderSearchService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 乡镇级别下统计
 * 
 * @date 2013-3-15
 */
public abstract class AbstractTownshipsUnderSearchService<T> extends
		AbstractTownshipsKeyPlaceService implements
		TownshipsUnderSearchService<T> {

	// 原来的dubboService包下的
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	@Autowired
	protected PropertyDictService propertyDictService;
	@Autowired
	protected Gis2DLayerService gis2DLayerService;

	/**
	 * 检查经纬度是否存为空
	 * 
	 * @param screenCoordinateVo
	 *            屏幕经纬度对象
	 */
	protected void checkForPoint(ScreenCoordinateVo screenCoordinateVo) {
		if (screenCoordinateVo.getMaxLon() == null) {
			throw new BusinessValidationException("最大经度不能为空");
		}
		if (screenCoordinateVo.getMinLon() == null) {
			throw new BusinessValidationException("最小经度不能为空");
		}
		if (screenCoordinateVo.getMaxLat() == null) {
			throw new BusinessValidationException("最大纬度不能为空");
		}
		if (screenCoordinateVo.getMinLat() == null) {
			throw new BusinessValidationException("最小纬度不能为空");
		}
	}

	protected String getOrgInternalCode(Long orgId) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		return organization != null ? organization.getOrgInternalCode() : null;
	}

	/*
	 * protected PageInfo<CommonEntityInfoVo> transforCommonEntity(PageInfo<T>
	 * pageInfo, String tableName, String functionType, Boolean isSerachMode,
	 * String childTableName, String keyType) { GisModuleVo gisModuleVo =
	 * sysModuleManageService.getModuleByTableName(tableName); if (gisModuleVo
	 * == null || gisModuleVo.getId() == null) { // this.errorMessage =
	 * "参数错误，请联系管理员!"; throw new BusinessValidationException("参数错误，请联系管理员!"); }
	 * GisFunction gisFunction = sysGisFunctionService
	 * .getGisFunctionByModuleIdAndFunctionType(gisModuleVo.getId(),
	 * functionType); Boolean isHasSonClass = gisModuleVo.getIsHasSonClass(); if
	 * (!isSerachMode && isHasSonClass) { GisTypeManage gisTypeManage =
	 * sysGisTypeManageService
	 * .getGisTypeManageByTableNameAndKeyType(childTableName,
	 * keyType.toUpperCase()); gisFunction =
	 * sysGisFunctionService.getGisFunctionBySonClassIdAndFunctionType
	 * (gisTypeManage.getId(), functionType); gisModuleVo =
	 * changeToGisModeleVo(gisTypeManage, gisModuleVo); }
	 * List<CommonEntityInfoVo> commonEntityInfoList = new
	 * ArrayList<CommonEntityInfoVo>(); List<T> list = pageInfo.getResult(); if
	 * (gisFunction != null) { for (int i = 0; i < list.size(); i++) { T
	 * objectVo = list.get(i); Field[] fields =
	 * objectVo.getClass().getDeclaredFields(); CommonEntityInfoVo
	 * commonEntityInfoVo = new CommonEntityInfoVo(); for (int j = 0; j <
	 * fields.length; j++) { fields[j].setAccessible(true); commonEntityInfoVo =
	 * setCommonEntityValue(gisFunction, commonEntityInfoVo, objectVo,
	 * fields[j]); }
	 * commonEntityInfoVo.setGisModuleManageId(gisFunction.getModuleId());
	 * commonEntityInfoVo.setModuleName(gisModuleVo.getModuleName());
	 * commonEntityInfoVo.setSonClassId(gisFunction.getSonClassId());
	 * commonEntityInfoVo.setDetailsUrl(gisFunction.getDetailsUrl());
	 * commonEntityInfoVo.setIconUrl(gisFunction.getIconUrl());
	 * commonEntityInfoVo.setIsViewIcon(gisFunction.getIsViewIcon());
	 * commonEntityInfoVo.setTableName(gisModuleVo.getTableName());
	 * commonEntityInfoVo
	 * .setBoundEventIsValid(gisFunction.getBoundEventIsValid());
	 * commonEntityInfoVo.setBoundEventName(gisFunction.getBoundEventName());
	 * commonEntityInfoVo
	 * .setUnBoundEventIsValid(gisFunction.getUnBoundEventIsValid());
	 * commonEntityInfoVo
	 * .setUnBoundEventName(gisFunction.getUnBoundEventName());
	 * commonEntityInfoVo.setEvent(gisFunction.getEvent() == null ? null :
	 * gisFunction.getEvent().getId());
	 * commonEntityInfoVo.setFunctionType(functionType);
	 * commonEntityInfoList.add(commonEntityInfoVo); } }
	 * PageInfo<CommonEntityInfoVo> pageInfo_ = new
	 * PageInfo<CommonEntityInfoVo>();
	 * pageInfo_.setCurrentPage(pageInfo.getCurrentPage());
	 * pageInfo_.setPerPageSize(pageInfo.getPerPageSize());
	 * pageInfo_.setResult(commonEntityInfoList);
	 * pageInfo_.setTotalRowSize(pageInfo.getTotalRowSize()); return pageInfo_;
	 * }
	 */

	// private GisModuleVo changeToGisModeleVo(GisTypeManage gisTypeManage,
	// GisModuleVo gisModuleVo)
	// {
	// gisModuleVo.setGisTypeManageId(gisTypeManage.getId());
	// gisModuleVo.setTableName(gisTypeManage.getTableName());
	// gisModuleVo.setModuleName(gisTypeManage.getName());
	// return gisModuleVo;
	// }
	//
	// private CommonEntityInfoVo setCommonEntityValue(GisFunction gisFunction,
	// CommonEntityInfoVo
	// commonEntityInfoVo, T objectVo,
	// Field field) {
	//
	// if (gisFunction != null && commonEntityInfoVo != null && objectVo != null
	// && field != null) {
	// Object value = getObjectField(objectVo, field);
	// if (field.getName().equals("id")) {
	// commonEntityInfoVo.setId(Long.parseLong(String.valueOf(value)));
	// } else if (field.getName().equals("issueId")) {
	// commonEntityInfoVo.setId(Long.parseLong(String.valueOf(value)));
	// } else if (field.getName().equals("typeId")) {
	// commonEntityInfoVo.setTypeId(value != null ? String.valueOf(value) :
	// null);
	// } else if (field.getName().equals(gisFunction.getTitleValue())) {
	// commonEntityInfoVo.setTitleName(gisFunction.getTitleName());
	// commonEntityInfoVo.setTitleValue(value != null ? String.valueOf(value) :
	// "");
	// } else if (field.getName().equals(gisFunction.getFieldA())) {
	// commonEntityInfoVo.setFieldNameA(gisFunction.getFieldNameA());
	// commonEntityInfoVo.setFieldA(value != null ? String.valueOf(value) : "");
	// } else if (field.getName().equals(gisFunction.getFieldB())) {
	// commonEntityInfoVo.setFieldNameB(gisFunction.getFieldNameB());
	// commonEntityInfoVo.setFieldB(value != null ? String.valueOf(value) : "");
	// } else if (field.getName().equals(gisFunction.getFieldC())) {
	// commonEntityInfoVo.setFieldNameC(gisFunction.getFieldNameC());
	// commonEntityInfoVo.setFieldC(value != null ? String.valueOf(value) : "");
	// } else if (field.getName().equals(gisFunction.getFieldD())) {
	// commonEntityInfoVo.setFieldNameD(gisFunction.getFieldNameD());
	// commonEntityInfoVo.setFieldD(value != null ? String.valueOf(value) : "");
	// } else if (field.getName().equals(gisFunction.getFieldE())) {
	// commonEntityInfoVo.setFieldNameE(gisFunction.getFieldNameE());
	// commonEntityInfoVo.setFieldE(value != null ? String.valueOf(value) : "");
	// } else if (field.getName().equals(gisFunction.getSearchFieldA())) {
	// commonEntityInfoVo.setSearchFieldAName(gisFunction.getSearchFieldAName());
	// commonEntityInfoVo.setSearchFieldA(String.valueOf(value));
	// } else if (field.getName().equals(gisFunction.getSearchFieldB())) {
	// commonEntityInfoVo.setSearchFieldBName(gisFunction.getSearchFieldBName());
	// commonEntityInfoVo.setSearchFieldB(String.valueOf(value));
	// } else if (field.getName().equals("featureId")) {
	// commonEntityInfoVo.setFeatureId(value != null ? String.valueOf(value) :
	// "");
	// } else if (field.getName().equals("lon") ||
	// field.getName().equals("centerLon") ||
	// field.getName().equals("centerX")) {
	// commonEntityInfoVo.setLon(value != null ? String.valueOf(value) : "");
	// } else if (field.getName().equals("lat") ||
	// field.getName().equals("centerLat") ||
	// field.getName().equals("centerY")) {
	// commonEntityInfoVo.setLat(value != null ? String.valueOf(value) : "");
	// } else if (field.getName().equals("type")) {
	// commonEntityInfoVo.setModuleType(String.valueOf(value));
	// } else if ((field.getName().equals("organization")) ||
	// field.getName().equals("occurOrg")) {
	// commonEntityInfoVo.setOrganization((Organization) value);
	// } else if (field.getName().equals("points")) {
	// commonEntityInfoVo.setPoints(String.valueOf(value));
	// }
	// }
	// return commonEntityInfoVo;
	// }

	// private Object getObjectField(T objectVo, Field field) {
	// if (objectVo == null || field == null) {
	// return null;
	// }
	// Object value = null;
	// try {
	// value = field.get(objectVo);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return value;
	// }
}
