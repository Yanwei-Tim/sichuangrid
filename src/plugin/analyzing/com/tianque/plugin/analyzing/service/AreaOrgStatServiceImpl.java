package com.tianque.plugin.analyzing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.account.property.PropertyTypes;
import com.tianque.plugin.analyzing.dao.AreaOrgStatDao;
import com.tianque.plugin.analyzing.domain.AreaOrgStatDataVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("areaOrgStatService")
public class AreaOrgStatServiceImpl implements AreaOrgStatService {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private AreaOrgStatDao areaOrgStatDao;
	@Autowired
	private CacheService cacheService;

	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public List<AreaOrgStatDataVo> getAreaOrgStatDateList(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构信息未获得");
		}
		String key = MemCacheConstant.getCachKeyForThreeData(
				MemCacheConstant.ORGANIZATIONSTATISTICS_CACHKEY, orgId,
				ModulTypes.STATISTICSTABLELIST);
		List<AreaOrgStatDataVo> listData = null;
		if (key != null) {
			listData = (List<AreaOrgStatDataVo>) cacheService.get(
					MemCacheConstant.ANALYSE_ORGANIZATIONSTATISTICS_NAMESPACE,
					key);
			if (listData != null) {
				return listData;
			}
		}
		Long provincePropertyDomainId = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.PROVINCE).get(0).getId();
		Long cityPropertyDomainId = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.CITY).get(0).getId();
		Long districtPropertyDomainId = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.DISTRICT).get(0).getId();
		Long townPropertyDomainId = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.TOWN).get(0).getId();
		Long villagePropertyDomainId = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.VILLAGE).get(0).getId();
		Long gridPropertyDomainId = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.GRID).get(0).getId();
		Long gridPropertyDomainIdByType = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE, OrganizationLevel.GRID)
				.get(0).getId();
		// List<Organization> organizationsList = organizationDubboService
		// .findAdminOrgsByParentId(orgId);
		// List<AreaOrgStatDataVo> resultList = new
		// ArrayList<AreaOrgStatDataVo>();
		// AreaOrgStatDataVo areaOrgStatDataVo;
		// for (Organization org : organizationsList) {
		// areaOrgStatDataVo = new AreaOrgStatDataVo();
		// areaOrgStatDataVo.setOrg(org);
		// Map<String, Object> map = areaOrgStatDao.getAreaOrgStatDate(
		// org.getOrgInternalCode(), provincePropertyDomainId,
		// cityPropertyDomainId, districtPropertyDomainId,
		// townPropertyDomainId, villagePropertyDomainId,
		// gridPropertyDomainId, gridPropertyDomainIdByType);
		//
		// areaOrgStatDataVo.setProvinceCount((Integer) map
		// .get("provinceCount"));
		// areaOrgStatDataVo.setCityCount((Integer) map.get("cityCount"));
		// areaOrgStatDataVo.setDistrictCount((Integer) map
		// .get("districtCount"));
		// areaOrgStatDataVo.setTownCount((Integer) map.get("townCount"));
		// areaOrgStatDataVo
		// .setVillageCount((Integer) map.get("villageCount"));
		// areaOrgStatDataVo.setGridCount((Integer) map.get("gridCount"));
		// resultList.add(areaOrgStatDataVo);
		// }
		List<AreaOrgStatDataVo> resultList = areaOrgStatDao
				.getAreaOrgStatDateByParentId(orgId, provincePropertyDomainId,
						cityPropertyDomainId, districtPropertyDomainId,
						townPropertyDomainId, villagePropertyDomainId,
						gridPropertyDomainId, gridPropertyDomainIdByType);

		listData = buildTotalCount(resultList);
		if (key != null) {
			cacheService.set(
					MemCacheConstant.ANALYSE_ORGANIZATIONSTATISTICS_NAMESPACE,
					key, ModulTypes.CACHETIME, listData);
		}
		return listData;
	}

	private List<AreaOrgStatDataVo> buildTotalCount(
			List<AreaOrgStatDataVo> resultList) {
		Organization org = new Organization();
		org.setOrgName("合计");
		int totalProvinceCount = 0;
		int totalCityCount = 0;
		int totalDistrictCount = 0;
		int totalTownCount = 0;
		int totalVillageCount = 0;
		int totalGridCount = 0;
		AreaOrgStatDataVo totalVo = new AreaOrgStatDataVo();
		for (AreaOrgStatDataVo vo : resultList) {
			totalProvinceCount += vo.getProvinceCount();
			totalCityCount += vo.getCityCount();
			totalDistrictCount += vo.getDistrictCount();
			totalTownCount += vo.getTownCount();
			totalVillageCount += vo.getVillageCount();
			totalGridCount += vo.getGridCount();
		}
		totalVo.setProvinceCount(totalProvinceCount);
		totalVo.setCityCount(totalCityCount);
		totalVo.setDistrictCount(totalDistrictCount);
		totalVo.setTownCount(totalTownCount);
		totalVo.setVillageCount(totalVillageCount);
		totalVo.setGridCount(totalGridCount);

		totalVo.setOrg(org);

		resultList.add(totalVo);
		return resultList;
	}

}
