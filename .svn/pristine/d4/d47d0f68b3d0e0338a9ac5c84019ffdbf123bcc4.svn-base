package com.tianque.openLayersMap.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.domian.CommonEntityInfoVo;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisModuleVo;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.DetailViewService;
import com.tianque.openLayersMap.service.SysGisFunctionService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.openLayersMap.service.SysModuleManageService;
import com.tianque.openLayersMap.util.FunctionType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 详情查看抽象类
 * 
 * @date 2013-3-15
 * @param <T>
 */
public abstract class AbstractDetailViewService<T> extends BaseService
		implements DetailViewService<T> {

	@Autowired
	protected SysGisTypeManageService sysGisTypeManageService;
	@Autowired
	protected SysGisFunctionService sysGisFunctionService;
	@Autowired
	protected SysModuleManageService sysModuleManageService;
	@Autowired
	protected PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public List<Object> getViewPopupInfoByIdAndTableNameAndType(Long id,
			String tableName, String type, String childTableName,
			Boolean isSerachMode, String functionType, Long orgId) {
		List<Object> objectList = new ArrayList<Object>();
		CommonEntityInfoVo commonEntityInfoVo = new CommonEntityInfoVo();
		if (id == null && "eventSource".equals(tableName)) {
			objectList.add(commonEntityInfoVo);
		} else {
			if (!validateParams(id, tableName, type)) {
				throw new BusinessValidationException("参数错误!");
			}
			T vo = doGetViewPopupInfoByIdAndTableNameAndType(id,
					childTableName, type, orgId);
			commonEntityInfoVo = transforCommonEntity(vo, tableName, type,
					childTableName, isSerachMode, functionType);
			objectList.add(commonEntityInfoVo);
		}

		List<PropertyDict> partTypes = propertyDictService
				.findPropertyDictByDomainName("部件类型");
		objectList.add(partTypes);

		return objectList;
	}

	protected abstract T doGetViewPopupInfoByIdAndTableNameAndType(Long id,
			String childTableName, String type, Long orgId);

	protected abstract boolean validateParams(Long id, String childTableName,
			String type);

	private CommonEntityInfoVo transforCommonEntity(T vo, String tableName,
			String type, String childTableName, Boolean isSerachMode,
			String functionType) {
		GisModuleVo gisModuleVo = sysModuleManageService
				.getModuleByTableName(tableName);
		if (gisModuleVo == null || gisModuleVo.getId() == null) {
			throw new BusinessValidationException("参数错误，请联系管理员!");
		}
		GisFunction gisFunction = null;
		Boolean isHasSonClass = gisModuleVo.getIsHasSonClass();
		if (!isSerachMode && isHasSonClass) {
			GisTypeManage gisTypeManage = sysGisTypeManageService
					.getGisTypeManageByTableNameAndKeyType(childTableName,
							type.toUpperCase());
			gisFunction = sysGisFunctionService
					.getGisFunctionBySonClassIdAndFunctionType(
							gisTypeManage.getId(), functionType);
			gisModuleVo = changeToGisModeleVo(gisTypeManage, gisModuleVo);
		} else {
			gisFunction = sysGisFunctionService
					.getGisFunctionByModuleIdAndFunctionType(
							gisModuleVo.getId(), functionType);
		}

		CommonEntityInfoVo commonEntityInfoVo = null;
		if (gisFunction != null) {
			Field[] fields = vo.getClass().getDeclaredFields();
			commonEntityInfoVo = new CommonEntityInfoVo();
			for (int j = 0; j < fields.length; j++) {
				fields[j].setAccessible(true);
				commonEntityInfoVo = setCommonEntityValue(gisFunction,
						commonEntityInfoVo, vo, fields[j]);
			}
			if (commonEntityInfoVo.getDetailsUrl() == null)
				commonEntityInfoVo.setDetailsUrl(gisFunction.getDetailsUrl());
			commonEntityInfoVo.setGisModuleManageId(gisFunction.getModuleId());
			commonEntityInfoVo.setModuleName(gisModuleVo.getModuleName());
			commonEntityInfoVo.setSonClassId(gisFunction.getSonClassId());
			commonEntityInfoVo.setTableName(gisModuleVo.getTableName());
			commonEntityInfoVo.setFunctionType(functionType);
			String orgInternalCode = commonEntityInfoVo.getOrgInternalCode();
			if (commonEntityInfoVo.getOrganization() != null
					&& (!StringUtil.isStringAvaliable(orgInternalCode) || "null"
							.equals(orgInternalCode))) {
				Long orgId = commonEntityInfoVo.getOrganization().getId();
				commonEntityInfoVo.setOrgInternalCode(organizationDubboService
						.getSimpleOrgById(orgId).getOrgInternalCode());
			}
		}
		return commonEntityInfoVo;
	}

	private GisModuleVo changeToGisModeleVo(GisTypeManage gisTypeManage,
			GisModuleVo gisModuleVo) {
		gisModuleVo.setGisTypeManageId(gisTypeManage.getId());
		gisModuleVo.setTableName(gisTypeManage.getTableName());
		gisModuleVo.setModuleName(gisTypeManage.getName());
		return gisModuleVo;
	}

	private CommonEntityInfoVo setCommonEntityValue(GisFunction gisFunction,
			CommonEntityInfoVo commonEntityInfoVo, Object objectVo, Field field) {
		if (gisFunction != null && commonEntityInfoVo != null
				&& objectVo != null && field != null) {
			Object value = getObjectField(objectVo, field);
			if (field.getName().equals("id")) {
				commonEntityInfoVo.setId(Long.parseLong(String.valueOf(value)));
			} else if (field.getName().equals("issueId")) {
				commonEntityInfoVo.setId(Long.parseLong(String.valueOf(value)));
			} else if (field.getName().equals("issueLogId")) {
				if (null != value)
					commonEntityInfoVo.setIssueLogId(Long.parseLong(String
							.valueOf(value)));
			} else if (field.getName().equals("partName")) {
				commonEntityInfoVo.setDustbinType(String.valueOf(value));
			} else if (field.getName().equals(gisFunction.getDetailFieldA())) {
				commonEntityInfoVo.setDetailFieldNameA(gisFunction
						.getDetailFieldNameA());
				commonEntityInfoVo.setDetailFieldA(value != null ? String
						.valueOf(value) : null);
			} else if (field.getName().equals(gisFunction.getDetailFieldB())) {
				commonEntityInfoVo.setDetailFieldNameB(gisFunction
						.getDetailFieldNameB());
				commonEntityInfoVo.setDetailFieldB(value != null ? String
						.valueOf(value) : null);
			} else if (field.getName().equals(gisFunction.getDetailFieldC())) {
				commonEntityInfoVo.setDetailFieldNameC(gisFunction
						.getDetailFieldNameC());
				commonEntityInfoVo.setDetailFieldC(value != null ? String
						.valueOf(value) : null);
			} else if (field.getName().equals(gisFunction.getDetailFieldD())) {
				commonEntityInfoVo.setDetailFieldNameD(gisFunction
						.getDetailFieldNameD());
				commonEntityInfoVo.setDetailFieldD(value != null ? String
						.valueOf(value) : null);
			} else if (field.getName().equals(gisFunction.getDetailFieldE())) {
				commonEntityInfoVo.setDetailFieldNameE(gisFunction
						.getDetailFieldNameE());
				commonEntityInfoVo.setDetailFieldE(value != null ? String
						.valueOf(value) : null);
			} else if (field.getName().equals("viewUrl")
					&& gisFunction.getFunctionType().equals(
							FunctionType.REFINESEARCH)) {
				if (value != null)
					commonEntityInfoVo.setDetailsUrl(String.valueOf(value));
				else if (gisFunction.getDetailsUrl() != null)
					commonEntityInfoVo.setDetailsUrl(gisFunction
							.getDetailsUrl());
			} else if (field.getName().equals("orgInternalCode")) {
				commonEntityInfoVo.setOrgInternalCode(String.valueOf(value));
			} else if ((field.getName().equals("organization"))
					|| field.getName().equals("occurOrg")) {
				commonEntityInfoVo.setOrganization((Organization) value);
			}
		}

		return commonEntityInfoVo;
	}

	private Object getObjectField(Object objectVo, Field field) {
		Object value = null;
		try {
			value = field.get(objectVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

}
