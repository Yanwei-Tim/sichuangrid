package com.tianque.gis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.domain.Layer;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.gis.dao.LayerDao;
import com.tianque.gis.service.LayerService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("layerService")
public class LayerServiceImpl extends AbstractBaseService implements
		LayerService {

	@Autowired
	private LayerDao layerDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public Layer addLayer(Layer layer) {
		List<Layer> list = layerDao.getLayerByOrganizationId(layer
				.getOrganization().getId());
		if (list != null && list.size() > 0) {
			layer.setId(list.get(0).getId());
			layer = layerDao.updateLayer(layer);
		} else {
			layer = layerDao.addLayer(layer);
		}
		return layer;
	}

	@Override
	public List<Layer> getLayerByOrganizationId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return layerDao.getLayerByOrganizationId(orgId);
	}

	@Override
	public boolean deleteLayerByOrganizationId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		int count = layerDao.deleteLayerByOrganizationId(orgId);
		if (count > 0)
			return true;
		else
			return false;
	}

	public String getSuperiorPointsByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (org.getParentOrg() != null && org.getParentOrg().getId() != null) {
			Long parOrgId = org.getParentOrg().getId();
			return layerDao.getSuperiorPointsByOrgId(parOrgId);
		} else {
			return "当前层级为最高层级";
		}

	}

	@Override
	public List<Layer> getDistrictLayerByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		List<Long> orgIdList = findOrgIds(org.getOrgInternalCode(), OrganizationLevel.DISTRICT);
		return layerDao.getDistrictLayerByOrgCode(orgIdList);
	}

	@Override
	public int checkLevel(Long orgId) {
		if (null == orgId) {
			throw new BusinessValidationException("参数错误");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		return checkLevel(propertyDictService.getPropertyDictById(org
				.getOrgLevel().getId()));
	}

	private int checkLevel(PropertyDict orgLevelDict) {
		if (orgLevelDict.getInternalId() < OrganizationLevel.DISTRICT) {
			return 1;// 区以下
		} else if (orgLevelDict.getInternalId() == OrganizationLevel.DISTRICT) {
			return 2;// 区
		} else if (orgLevelDict.getInternalId() == OrganizationLevel.CITY) {
			return 3;// 市
		} else if (orgLevelDict.getInternalId() == OrganizationLevel.PROVINCE) {
			return 4;// 省
		} else if (orgLevelDict.getInternalId() == OrganizationLevel.COUNTRY) {
			return 5;// 全国
		}
		return 0;
	}

	@Override
	public String getPointersByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Layer layer = layerDao.getPointersByOrgId(orgId);
		if (layer != null && null != layer.getPoints()) {
			return layer.getPoints();
		} else {
			return "";
		}
	}

	@Override
	public List<Layer> getVisibleLayerByOrgId(Long orgId) {
		if (null == orgId || orgId < 1L) {
			return new ArrayList<Layer>();
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		PropertyDict orgLevelDict = propertyDictService.getPropertyDictById(org
				.getOrgLevel().getId());
		int orgLevelInternalId = orgLevelDict.getInternalId();
		int level = checkLevel(orgLevelDict);
		if (level > 2) {
			orgLevelInternalId = OrganizationLevel.DISTRICT;
		}
		List<Long> orgIdList = findOrgIds(org.getOrgInternalCode(), orgLevelInternalId);
		return layerDao.getDistrictLayerByOrgCode(orgIdList);
	}

	private Long getOrglevel(String domainName, int internalId) {
		Long orglevel = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(domainName,
						internalId).get(0).getId();
		return orglevel;
	}
	
	private List<Long> findOrgIds(String orgInternalCode,Integer orgLevelInternalId){
		OrganizationVo orgVo = new OrganizationVo();
		orgVo.setOrgInternalCode(orgInternalCode);
		orgVo.setOrgLevelId(getOrglevel(PropertyTypes.ORGANIZATION_LEVEL,
				orgLevelInternalId));
		return organizationDubboService.findOrgIdsBySearchVo(orgVo);
	}
}
