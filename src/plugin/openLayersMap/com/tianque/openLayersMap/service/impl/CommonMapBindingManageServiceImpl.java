package com.tianque.openLayersMap.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.openLayersMap.dao.CommonTwoDimensionMapManageDao;
import com.tianque.openLayersMap.domian.CommonEntityInfoVo;
import com.tianque.openLayersMap.domian.GisFunction;
import com.tianque.openLayersMap.domian.GisModuleVo;
import com.tianque.openLayersMap.domian.GisTypeManage;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;
import com.tianque.openLayersMap.domian.vo.GisBoundVo;
import com.tianque.openLayersMap.service.BuildDataService;
import com.tianque.openLayersMap.service.SysGisFunctionService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;
import com.tianque.openLayersMap.service.SysModuleManageService;
import com.tianque.openLayersMap.util.GisTransformatUtil;
import com.tianque.openLayersMap.util.GisType;

/**
 * 公共绑定实现类
 * 
 * @author 张忠祥(zhangzhongxiang@hztianque.com)
 * @date 2013-3-18
 */
@Service("commonMapBindingManageService")
public class CommonMapBindingManageServiceImpl extends
		AbstractCommonMapBindingManageService<CommonEntityInfoVo> {

	@Autowired
	@Qualifier("commonTwoDimensionMapManageDao")
	private CommonTwoDimensionMapManageDao commonTwoDimensionMapManageDao;
	@Autowired
	private SysModuleManageService sysModuleManageService;
	@Autowired
	private SysGisTypeManageService sysGisTypeManageService;
	@Autowired
	private SysGisFunctionService sysGisFunctionService;
	@Autowired
	private BuildDataService buildDataService;

	@Override
	public CommonEntityInfoVo boundTwoDimensionMap(GisBoundVo gisBoundVo,
			String type) {
		if (gisBoundVo == null || gisBoundVo.getIds() == null
				|| gisBoundVo.getIds().length < 1 || tableName == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Boolean result = true;
			Map<String, Object> paramMap;
			BuildDataInfoVo buildDataInfoVo = new BuildDataInfoVo();
			Long buildingId = null;
			Long buildDataId = gisBoundVo.getBuildDataId();
			String[] ids = gisBoundVo.getIds();
			if (buildDataId != null) {// buildDataId是建筑物id，buildingId是楼宇id
				buildDataInfoVo = buildDataService
						.getBuildDataInfoByBuildingid(buildDataId);
				if (buildDataInfoVo != null && buildDataInfoVo.getId() != null) {
					buildingId = buildDataInfoVo.getId();
				}
			}
			GisTransformatUtil.autoFillGisBound(gisBoundVo);

			paramMap = assemblyCommonParams(tableName, gisBoundVo.getLon(),
					gisBoundVo.getLat(), event, type, buildDataId, buildingId);
			paramMap.put("lon2", gisBoundVo.getLon2());
			paramMap.put("lat2", gisBoundVo.getLat2());
			for (String id : ids) {
				paramMap.put("id", id);
				if (ModulTypes.allCompanyPlaceMapKey.contains(type)) {
					paramMap.remove("type");
					paramMap.put("allType", ModulTypes.allCompanyPlaceMapKey);
					result = commonTwoDimensionMapManageDao
							.updateDomainByTableName(paramMap);
				} else {
					result = commonTwoDimensionMapManageDao
							.updateDomainByTableName(paramMap);
				}
			}
			return result ? getCommonEntityInfoByTableNameAndFields(
					Long.parseLong(ids[0]), tableName, type, functionType)
					: null;
		} catch (Exception e) {
			throw new ServiceValidationException("数据更新失败", e);
		}
	}

	@Override
	public CommonEntityInfoVo boundTwoDimensionMap(String[] ids, String lon,
			String lat, String type, Long buildDataId, String gisType) {
		if (ids == null || ids.length < 1 || tableName == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Boolean result = true;
			Map<String, Object> paramMap;
			BuildDataInfoVo buildDataInfoVo = new BuildDataInfoVo();
			Long buildingId = null;
			if (buildDataId != null) {// buildDataId是建筑物id，buildingId是楼宇id
				buildDataInfoVo = buildDataService
						.getBuildDataInfoByBuildingid(buildDataId);
				if (buildDataInfoVo != null && buildDataInfoVo.getId() != null) {
					buildingId = buildDataInfoVo.getId();
				}
			}
			if (GisType.M3D.equals(gisType)) {
				paramMap = assemblyCommonParams(tableName, lon, lat, event,
						type, buildDataId, buildingId);
				String[] lonlat2 = GisTransformatUtil.to2DPoint(lon, lat);
				paramMap.put("lon2", lonlat2[0]);
				paramMap.put("lat2", lonlat2[1]);
			} else {
				String[] lonlat3 = GisTransformatUtil.to25DPoint(lon, lat);
				paramMap = assemblyCommonParams(tableName, lonlat3[0],
						lonlat3[1], event, type, buildDataId, buildingId);
				paramMap.put("lon2", lon);
				paramMap.put("lat2", lat);
			}

			for (String id : ids) {
				paramMap.put("id", id);
				result = commonTwoDimensionMapManageDao
						.updateDomainByTableName(paramMap);
			}
			return result ? getCommonEntityInfoByTableNameAndFields(
					Long.parseLong(ids[0]), tableName, type, functionType)
					: null;
		} catch (Exception e) {
			throw new ServiceValidationException("数据更新失败", e);
		}
	}

	@Override
	public CommonEntityInfoVo unBoundTwoDimensionMap(Long id, String type,
			Long orgId) {
		if (id == null || tableName == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			Boolean result = true;

			Map<String, Object> paramMap = assemblyCommonParams(tableName,
					null, null, event, type, null, null);
			paramMap.put("id", id);
			paramMap.put("lon2", "");
			paramMap.put("lat2", "");
			if (ModulTypes.allCompanyPlaceMapKey.contains(type)) {
				paramMap.remove("type");
				paramMap.put("allType", ModulTypes.allCompanyPlaceMapKey);
				paramMap.put("buildDataId", "");
				paramMap.put("buildingId", "");
				result = commonTwoDimensionMapManageDao
						.updateDomainByTableName(paramMap);
			} else {
				result = commonTwoDimensionMapManageDao
						.updateDomainByTableName(paramMap);
			}

			return result ? getCommonEntityInfoByTableNameAndFields(id,
					tableName, type, functionType) : null;
		} catch (Exception e) {
			throw new ServiceValidationException("数据更新失败", e);
		}
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
			throw new ServiceValidationException();
		}
	}

	private Map<String, Object> assemblyCommonParams(String tableName,
			String lon, String lat, String event, String type,
			Long buildDataId, Long buildingId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tableName", tableName);
		paramMap.put("lon", lon);
		paramMap.put("lat", lat);
		if (type != null && !type.trim().equals("")) {
			paramMap.put("type", type.toUpperCase());
		}
		if (event != null && event.trim().endsWith("bindInBuild")) {
			paramMap.put("buildDataId", buildDataId);
			paramMap.put("buildingId", buildingId);

		}
		return paramMap;
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
		map.put("type", type);
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
