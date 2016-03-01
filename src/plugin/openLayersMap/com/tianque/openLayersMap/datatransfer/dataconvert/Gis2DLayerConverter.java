package com.tianque.openLayersMap.datatransfer.dataconvert;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.excel.definition.HouseContext;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.openLayersMap.datatransfer.domain.Gis2DLayerConvertVo;
import com.tianque.openLayersMap.domian.Gis2DLayer;
import com.tianque.openLayersMap.service.Gis2DLayerService;
import com.tianque.openLayersMap.util.GisOrgZoom;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("gis2DLayerConverter")
public class Gis2DLayerConverter extends
		AbstractDataConverter<Gis2DLayerConvertVo> {

	@Autowired
	private Gis2DLayerService gis2DLayerService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public boolean isRepeatData(Gis2DLayerConvertVo domain) {
		return false;
	}

	@Override
	public Gis2DLayerConvertVo persistenceDomain(Gis2DLayerConvertVo domain) {
		logger.error("重置地图区域："
				+ (domain != null ? ("id=" + domain.getId()
						+ "  &  departmentNo=" + domain.getDepartmentNo()
						+ "  &  name=" + domain.getCity() + ">"
						+ domain.getDistrict() + ">" + domain.getTown())
						: "对象为空"));
		try {
			Organization org = null;
			Gis2DLayer gis2dLayer = null;
			int zoom = -1;
			domain.setIsTransformat(false);
			if (domain.getId() == null) {
				if (StringUtil.isStringAvaliable(domain.getDepartmentNo())) {
					org = organizationDubboService.getOrgByDepartmentNo(domain
							.getDepartmentNo());
				} else if (StringUtil.isStringAvaliable(domain.getTown())) {
					org = getOrgByName(domain, OrganizationLevel.TOWN);
					zoom = GisOrgZoom
							.getOrgZoomByOrgLevel(OrganizationLevel.TOWN);
				} else if (StringUtil.isStringAvaliable(domain.getDistrict())) {
					org = getOrgByName(domain, OrganizationLevel.DISTRICT);
					zoom = GisOrgZoom
							.getOrgZoomByOrgLevel(OrganizationLevel.DISTRICT);
				} else if (StringUtil.isStringAvaliable(domain.getCity())) {
					org = getOrgByName(domain, OrganizationLevel.CITY);
					zoom = GisOrgZoom
							.getOrgZoomByOrgLevel(OrganizationLevel.CITY);
				} else if (StringUtil.isStringAvaliable(domain.getProvince())) {
					org = getOrgByName(domain, OrganizationLevel.PROVINCE);
					zoom = GisOrgZoom
							.getOrgZoomByOrgLevel(OrganizationLevel.PROVINCE);
				}
				if (org == null) {
					return null;
				}
				if (zoom == -1) {
					zoom = GisOrgZoom.getZoomByOrgLevelId(org.getOrgLevel()
							.getId(), propertyDictService);
				}
				gis2dLayer = gis2DLayerService.getByOrgId(org.getId(), null);
				if (gis2dLayer == null) {
					domain.setOrganization(org);
					domain.setRemark(org.getOrgName());
					domain.setZoom(zoom);
					gis2DLayerService.addGis2DLayer(domain);
					return domain;
				}
				domain.setId(gis2dLayer.getId());
			}
			gis2DLayerService.updatePoint(domain);
			return domain;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"Gis2DLayerConverter.persistenceDomain报错", e);
		}
	}

	private Organization getOrgByName(Gis2DLayerConvertVo domain, int orgLevel) {
		String orgName = null;
		String parentOrgName = null;
		int nextOrgLevel = 0;
		if (orgLevel == OrganizationLevel.TOWN) {
			orgName = domain.getTown();
			parentOrgName = domain.getDistrict();
			nextOrgLevel = OrganizationLevel.DISTRICT;
		} else if (orgLevel == OrganizationLevel.DISTRICT) {
			orgName = domain.getDistrict();
			parentOrgName = domain.getCity();
			nextOrgLevel = OrganizationLevel.CITY;
		} else if (orgLevel == OrganizationLevel.CITY) {
			orgName = domain.getCity();
			parentOrgName = domain.getProvince();
			nextOrgLevel = OrganizationLevel.PROVINCE;
		} else if (orgLevel == OrganizationLevel.PROVINCE) {
			orgName = domain.getProvince();
		}
		List<Organization> orgList = (orgName == null) ? null
				: organizationDubboService.findOrganizationsByOrgName(orgName);
		if (orgList == null || orgList.size() == 0) {
			return null;
		} else if (orgList.size() == 1) {
			return orgList.get(0);
		}
		Organization districtOrg = (parentOrgName == null) ? null
				: getOrgByName(domain, nextOrgLevel);
		if (districtOrg != null) {
			for (int i = 0; i < orgList.size(); i++) {
				if (districtOrg.getId().equals(
						orgList.get(i).getParentOrg().getId())) {
					return orgList.get(i);
				}
			}
		}
		return null;
	}

	@Override
	public Gis2DLayerConvertVo updateDomain(Gis2DLayerConvertVo domain) {
		return domain;
	}

	@Override
	public ValidateResult validate(Gis2DLayerConvertVo actualhouse, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		// return validator.validate(actualhouse);
		return null;
	}

	@Override
	public Gis2DLayerConvertVo convertToDomain(String[] cellValues,
			ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		Gis2DLayerConvertVo gis2DLayer = new Gis2DLayerConvertVo();
		String[][] excelColumArray = HouseContext.getHouseInfoImportArray();
		ExcelImportHelper.procImportDate(excelColumArray, cellValues,
				getUploadOrganization(), gis2DLayer, vr);
		gis2DLayer.setUpdateDate(new Date());
		return gis2DLayer;
	}
}
