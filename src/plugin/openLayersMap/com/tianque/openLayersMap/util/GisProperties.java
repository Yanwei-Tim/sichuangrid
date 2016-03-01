package com.tianque.openLayersMap.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.tianque.core.util.GridProperties;
import com.tianque.exception.base.SystemUtilException;

public class GisProperties {
	private static Map properties = new HashMap();

	private static Map getGisProperties() {
		if (properties.size() == 0) {
			synchronized (properties) {
				properties = new HashMap();
				try {
					InputStream input = GisProperties.class.getClassLoader()
							.getResourceAsStream("config/gis.yaml");
					Yaml yaml = new Yaml();
					properties = (Map) yaml.load(input);
				} catch (Exception e) {
					throw new SystemUtilException("加载gis.yaml出错！", e);
				}
			}
		}
		return properties;
	}

	private static Map getLayerOption() {
		String mapType = (String) getGisProperties().get("mapType");
		Map map = (Map) getGisProperties().get(mapType);
		if (map.get("production") != null) {
			if (GridProperties.IS_GIS_LOCAL) {
				map.putAll( (Map) map.get("development"));
			} else {
				map.putAll( (Map) map.get("production"));
			}
		}
		return map;
	}

	public static final String GIS_INDEX_MAP = String.valueOf(getGisProperties().get("gis.indexmap"));
	public static final String GIS_BOUND_BUILD_DATA = String.valueOf(getGisProperties().get("gis.boundBuildData"));
	public static final String RESOURCE = String.valueOf(getGisProperties().get("resource"));
	public static final String INTEGRATION_VERSIONS = String.valueOf(getGisProperties().get("integrationVersions"));

	/**  是否使用统一搜索查询数据 */
	public static final Boolean ISUSE_TQSEARCH =  (Boolean)getGisProperties().get("isUseTqSearch");
	/** 是否进行地图数据升级，隐藏地图并暂停坐标数据的录入，包括绑定、区域、GPS等 */
	public static final Boolean UPGRADE = (Boolean) getGisProperties().get("upgrade");
	/** 通过这个开关  加载不同的地图 */
	public static final String MAPTYPE = String.valueOf(getGisProperties().get("mapType"));

	public static final Map WFS_OPTION = (Map) getLayerOption().get("WFS");
	public static final Map M2D_OPTION = (Map) getLayerOption().get("M2D");
	public static final Map M3D_OPTION = (Map) getLayerOption().get("M3D");
	public static final Map MS_OPTION = (Map) getLayerOption().get("MS");
	public static final String CENTERLON = String.valueOf(getLayerOption().get("lon"));
	public static final String CENTERLAT = String.valueOf(getLayerOption().get("lat"));

	public static final String VIDEOSERVICEPATH = String.valueOf(getGisProperties().get("videoServicePath"));
	/** 需要进行坐标转换的组织机构国标集合，以逗号隔开 */
	public static final String TRANSFORMAT_DEPARTMENTNO = String.valueOf(getGisProperties().get("transformatDepartmentNo"));
}
