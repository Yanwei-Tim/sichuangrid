package com.tianque.openLayersMap.util;

import java.util.List;

import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.sysadmin.service.PropertyDictService;

public enum GisOrgZoom {
	ORG_COUNTRY(OrganizationLevel.COUNTRY, 0, 0), 
	ORG_PROVINCE(OrganizationLevel.PROVINCE, 2, 0), 
	ORG_CITY(OrganizationLevel.CITY, 4, 0), 
	ORG_DISTRICT(OrganizationLevel.DISTRICT, 6, 2), 
	ORG_TOWN(OrganizationLevel.TOWN, 9, 4), 
	ORG_VILLAGE(OrganizationLevel.VILLAGE, 12, 6), 
	ORG_GRID(OrganizationLevel.GRID, 15, 8);
	
	/**组织机构层级*/
	private Integer orgLevel;
	/**行政区划代码长度*/
	private Integer departmentNoLength;
	/**地图层级*/
	private Integer zoom;

	private GisOrgZoom(Integer orgLevel,
			Integer departmentNoLength, Integer zoom) {
		this.zoom = zoom;
		this.orgLevel = orgLevel;
		this.departmentNoLength = departmentNoLength;
	}
	
	public static String substrDepartmentNo(String departmentNo,Integer orgLevel) {
		for (GisOrgZoom gisOrgZoom : GisOrgZoom.values()) {
			int level = gisOrgZoom.getOrgLevel();
			if (level == orgLevel) {
				int length = gisOrgZoom.getDepartmentNoLength();
				departmentNo = departmentNo.substring(0, length);
				break;
			}
		}
		return departmentNo;
	}

	public static int getOrgZoomByOrgLevel(int orgLevel) {
		for (GisOrgZoom gisOrgZoom : GisOrgZoom.values()) {
			int level = gisOrgZoom.getOrgLevel();
			if (level == orgLevel) {
				return gisOrgZoom.getZoom();
			}
		}
		return 0;
	}

	public static int getZoomByOrgLevelId(long orgLevelId,
			PropertyDictService propertyDictService) {
		int orgLevel = 0;
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		for (PropertyDict domain : orgLevels) {
			if (domain.getId().longValue() == orgLevelId) {
				orgLevel = domain.getInternalId();
				break;
			}
		}
		return getOrgZoomByOrgLevel(orgLevel);
	}

	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}

	public Integer getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

	public Integer getDepartmentNoLength() {
		return departmentNoLength;
	}

	public void setDepartmentNoLength(Integer departmentNoLength) {
		this.departmentNoLength = departmentNoLength;
	}
}
