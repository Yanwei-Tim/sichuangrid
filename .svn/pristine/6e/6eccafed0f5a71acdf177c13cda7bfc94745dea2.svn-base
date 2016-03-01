package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.core.util.CollectionUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.HousePopulationDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.HouseLivingPopulationInfo;
import com.tianque.domain.vo.HouseLivingTotalInfo;
import com.tianque.domain.vo.HouseSimpleInfoForSearch;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.transfer.util.TransferUtil;
import com.tianque.search.vo.ActualPopulationSearchCondition;
import com.tianque.service.HouseHasActualPopulationService;
import com.tianque.service.HousePopulationApplyService;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.shard.util.IdConversionShardUtil;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("housePopulationService")
@Transactional
public class HousePopulationServiceImpl extends LogableService implements
		HousePopulationMaintanceService, HousePopulationApplyService {
	@Autowired
	private HousePopulationDao housePopulationDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService orgService;
	@Autowired
	private ShardConversion shardConversion;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private HouseHasActualPopulationService houseHasActualPopulationService;
	@Autowired
	private HouseholdStaffService householdStaffService;

	private boolean isActualPopulation(PopulationCatalog catalog) {
		return PopulationCatalog.ALL_ACTUAL_POPULATION.equals(catalog
				.getParentCatalog());
	}

	private boolean isAllPopulation(PopulationCatalog catalog) {
		return PopulationCatalog.ALL_POPULATION.equals(catalog.getCatalog());
	}

	private boolean isAllActualPopulation(PopulationCatalog catalog) {
		return PopulationCatalog.ALL_ACTUAL_POPULATION.equals(catalog
				.getCatalog());
	}

	private boolean isAllAttentionActualPopulation(PopulationCatalog catalog) {
		return PopulationCatalog.ALL_ATTENTION_POPULATION.equals(catalog
				.getCatalog());
	}

	@Override
	public boolean bindHouse(PopulationCatalog catalog, Long populationId,
			Long houseId) {
		String shardCode = getShardCode(catalog, populationId, houseId);
		if (isActualPopulation(catalog)) {
			// return housePopulationDao.bindActualPopulationHouse(catalog,
			// populationId, houseId, Boolean.TRUE, shardCode);
			// 房屋人数
			Boolean fag = housePopulationDao.bindActualPopulationHouse(catalog,
					populationId, houseId, Boolean.TRUE, shardCode);
			Long logOut = actualHouseService
					.getLogOutByPopulationTypeAndPopulationId(shardCode,
							catalog.getCatalog(), populationId);
			if (logOut != null && logOut.equals(IsEmphasis.Emphasis)) {
				actualHouseService.updateHouseInfoMemberNum(shardCode, houseId,
						1);
			}
			return fag;
		} else {
			return housePopulationDao.bindImpotantPopulationHouse(catalog,
					populationId, houseId, Boolean.TRUE);
		}
	}

	private String getShardCode(PopulationCatalog catalog, Long populationId,
			Long houseId) {
		String shardCode = null;
		if (houseId != null) {
			shardCode = IdConversionShardUtil.getShardCodeById(houseId);
		} else {
			People people = TransferUtil.getPeopleByPopulationTypeAndId(
					catalog.getCatalog(), populationId);
			shardCode = shardConversion.getShardCodeByOrgCode(people
					.getOrgInternalCode());
		}
		return shardCode;
	}

	@Override
	public boolean releaseHouse(PopulationCatalog catalog, Long populationId,
			Long houseId) {
		String shardCode = getShardCode(catalog, populationId, houseId);
		if (isActualPopulation(catalog)) {
			// return housePopulationDao.releaseActualPopulationHouse(catalog,
			// populationId, houseId, shardCode);
			// 房屋人数
			Long tempHouseId = houseHasActualPopulationService
					.getHouseIdByPopulationTypeAndPopulationId(
							catalog.getCatalog(), populationId);
			Boolean fag = housePopulationDao.releaseActualPopulationHouse(
					catalog, populationId, houseId, shardCode);
			if (tempHouseId != null && tempHouseId.equals(houseId)) {
				actualHouseService.updateHouseInfoMemberNum(shardCode, houseId,
						-1);
			}
			return fag;
		} else {
			return housePopulationDao.releaseImpotantPopulationHouse(catalog,
					populationId, houseId);
		}
	}

	@Override
	public Long getPopulationLivingHouseId(PopulationCatalog catalog, Long id) {
		if (isActualPopulation(catalog)) {
			return housePopulationDao.getActualPopulationLivingHouseId(catalog,
					id);
		} else {
			return housePopulationDao.getImportantPopulationLivingHouseId(
					catalog, id);
		}
	}

	@Override
	public void releasePopulationAllHouse(PopulationCatalog catalog,
			Long populationId) {
		if (isActualPopulation(catalog)) {
			// housePopulationDao.releaseActualPopulationAllHouse(catalog,
			// populationId);
			// 房屋人数
			Long houseId = housePopulationDao.getActualPopulationLivingHouseId(
					catalog, populationId);
			housePopulationDao.releaseActualPopulationAllHouse(catalog,
					populationId);
			if (houseId != null) {
				String shardCode = IdConversionShardUtil
						.getShardCodeById(houseId);
				Long logOut = actualHouseService
						.getLogOutByPopulationTypeAndPopulationId(shardCode,
								catalog.getCatalog(), populationId);
				if (logOut != null && logOut.equals(IsEmphasis.Emphasis)) {
					actualHouseService.updateHouseInfoMemberNum(shardCode,
							houseId, -1);
				}
			}
		} else {
			housePopulationDao.releaseImportantPopulationAllHouse(catalog,
					populationId);
		}
	}

	@Override
	public void releasePopulationsAllHouse(PopulationCatalog catalog,
			Long[] populationIds) {
		if (isActualPopulation(catalog)) {
			housePopulationDao.releaseActualPopulationsAllHouse(catalog,
					populationIds);
		} else {
			housePopulationDao.releaseImportantPopulationsAllHouse(catalog,
					populationIds);
		}
	}

	@Override
	public HouseSimpleInfoForSearch getPopulationLivingHouseInfo(
			PopulationCatalog catalog, Long populationId, String shardCode) {
		if (isActualPopulation(catalog)) {
			return housePopulationDao.getActualPopulationLivingHouseInfo(
					catalog, populationId, shardCode);
		} else {
			return housePopulationDao.getImportantPopulationLivingHouseInfo(
					catalog, populationId, shardCode);
		}
	}

	@Override
	public List<HouseLivingTotalInfo> findLivingHouseTotalInfos(
			PopulationCatalog catalog, Long houseId) {
		try {
			if (isAllPopulation(catalog)) {
				return housePopulationDao
						.findLivingHouseAllPopulationTotalInfos(houseId);
			} else if (isAllActualPopulation(catalog)) {
				return housePopulationDao
						.findLivingHouseAllActualPopulationTotalInfos(houseId);
			} else if (isAllAttentionActualPopulation(catalog)) {
				return housePopulationDao
						.findLivingHouseAllAttentionPopulationTotalInfos(houseId);
			} else if (isActualPopulation(catalog)) {
				return housePopulationDao
						.findLivingHouseActualPopulationTotalInfos(catalog,
								houseId);
			} else {
				return housePopulationDao
						.findLivingHouseAttentionPopulationTotalInfos(catalog,
								houseId);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("查询某房屋的人员居住总况出现错误", e);
		}
	}

	@Override
	public List<HouseLivingPopulationInfo> findLivingHousePopulationInfos(
			PopulationCatalog type, Long houseId) {
		try {
			List<HouseLivingPopulationInfo> result = null;
			HouseInfo houseInfo = actualHouseService.getHouseInfoById(houseId);
			if (isAllPopulation(type)) {
				result = housePopulationDao
						.findLivingHouseAllActualPopulationInfos(houseId,
								shardConversion.getShardCode(houseInfo
										.getOrganization().getId()));
			} else if (isAllActualPopulation(type)) {
				result = housePopulationDao
						.findLivingHouseAllActualPopulationInfos(houseId,
								shardConversion.getShardCode(houseInfo
										.getOrganization().getId()));
			} else if (isAllAttentionActualPopulation(type)) {
				result = housePopulationDao
						.findLivingHouseAllAttentionPopulationInfos(houseId);
			} else if (isActualPopulation(type)) {
				result = housePopulationDao
						.findLivingHouseActualPopulationInfos(type, houseId);
			} else {
				result = housePopulationDao
						.findLivingHouseAttentionPopulationInfos(type, houseId);
			}
			fillPropertyDictName(result);
			return result;
		} catch (Exception e) {
			throw new ServiceValidationException("查询某房屋的居住人员简单信息出现错误", e);
		}
	}

	private void fillPropertyDictName(List<HouseLivingPopulationInfo> list) {
		for (HouseLivingPopulationInfo info : list) {
			info.setActive(info.isActive());
			info.setEducationName(getPropertyDictText(PropertyTypes.SCHOOLING,
					info.getEducationId()));
			info.setGenderName(getPropertyDictText(PropertyTypes.GENDER,
					info.getGenderId()));
			info.setNationName(getPropertyDictText(PropertyTypes.NATION,
					info.getNationId()));
			info.setProfessionName(getPropertyDictText(PropertyTypes.CAREER,
					info.getProfessionId()));
		}
	}

	private String getPropertyDictText(String domainName, Long id) {
		if (null == id) {
			return "";
		} else {
			PropertyDict dict = propertyDictService.getPropertyDictById(id);
			return dict == null ? "" : dict.getDisplayName();
		}
	}

	@Override
	public boolean bindHouseIgnoreDefaultLiving(PopulationCatalog catalog,
			Long populationId, Long houseId) {
		String shardCode = getShardCode(catalog, populationId, houseId);
		if (isActualPopulation(catalog)) {
			// return housePopulationDao.bindActualPopulationHouse(catalog,
			// populationId, houseId, Boolean.FALSE, shardCode);
			// 房屋人数
			Boolean fag = housePopulationDao.bindActualPopulationHouse(catalog,
					populationId, houseId, Boolean.FALSE, shardCode);
			actualHouseService.updateHouseInfoMemberNum(shardCode, houseId, 1);
			return fag;
		} else {
			return housePopulationDao.bindImpotantPopulationHouse(catalog,
					populationId, houseId, Boolean.FALSE);
		}
	}

	@Override
	public PageInfo<HouseLivingPopulationInfo> findNotLivingInHousePopulationInfos(
			Long houseId, ActualPopulationSearchCondition condition,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		try {
			Organization rootOrg = getSearchedRootOrg(condition.getRootOrgId());
			String shardCode = shardConversion.getShardCode(rootOrg.getId());
			List<Long> baseInfoIds = null;
			if (StringUtil.isStringAvaliable(condition.getAccountNumber())) {
				baseInfoIds = householdStaffService
						.findBaseinfoIdByAccountNumber(shardCode,
								condition.getAccountNumber());
				if (!CollectionUtil.isAvaliable(baseInfoIds)) {
					return new PageInfo<HouseLivingPopulationInfo>();
				}
			}
			PageInfo<HouseLivingPopulationInfo> results = housePopulationDao
					.findNotLivingHouseAllActualPopulationInfos(houseId,
							shardCode, getSearchedRootOrgInternalCode(rootOrg),
							condition.getCertificationNumber(),
							condition.getName(), condition.getDeathStatus(),
							condition.getRegisteStatus(),
							condition.getGender(), baseInfoIds, pageNum,
							pageSize, sidx, sord);
			return results;
		} catch (Exception e) {
			throw new ServiceValidationException("查询实有房屋信息出现错误", e);
		}
	}

	@Override
	public PageInfo<HouseLivingPopulationInfo> findGisPopulationInfosByQueryStrAndOrgId(
			Long orgId, String queryStr, Integer page, Integer rows,
			String sidx, String sord) {
		try {
			if (null == orgId) {
				return constructEmptyPageInfo(rows);
			} else {
				Organization org = orgService.getSimpleOrgById(orgId);
				return housePopulationDao
						.findGisPopulationInfosByQueryStrAndOrgcode(
								org.getOrgInternalCode(), queryStr, page, rows,
								sidx, sord);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("Gis查询人口信息时出错", e);
		}

	}

	private Organization getSearchedRootOrg(Long id) {
		return null == id ? null : orgService.getSimpleOrgById(id);
	}

	private String getSearchedRootOrgInternalCode(Organization org) {
		if (null == org) {
			return ThreadVariable.getSession().getOrgInternalCode();
		} else {
			return org.getOrgInternalCode();
		}
	}

	private PageInfo<HouseLivingPopulationInfo> constructEmptyPageInfo(
			int PageSize) {
		PageInfo<HouseLivingPopulationInfo> result = new PageInfo<HouseLivingPopulationInfo>();
		result.setTotalRowSize(0);
		result.setCurrentPage(0);
		result.setPerPageSize(PageSize);
		result.setResult(new ArrayList<HouseLivingPopulationInfo>());
		return result;
	}

	@Override
	public PageInfo<HouseLivingPopulationInfo> findFurtherStepGisPersonInfoSearchByPersonTypeAndOrgId(
			Long orgId, String personType, String queryStr, Integer page,
			Integer rows, String sidx, String sord) {
		try {
			// || null ==queryStr || queryStr.trim().equals("")
			if (null == orgId) {
				return constructEmptyPageInfo(rows);
			} else {
				Organization org = orgService.getSimpleOrgById(orgId);
				return housePopulationDao
						.findFurtherStepGisPersonInfoSearchByPersonTypeAndOrgId(
								org.getOrgInternalCode(), personType, queryStr,
								page, rows, sidx, sord);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("Gis人口二次搜索时出错：", e);
		}

	}

	/**
	 * 对象处理 ，复杂对象之间的处理方式
	 */
	@Override
	public PageInfo<HouseLivingPopulationInfo> findFurtherStepGisPersonInfoSearchByPersonTypeListAndOrgId(
			Long orgId, List personType, String queryStr, Integer page,
			Integer rows, String sidx, String sord) {
		try {
			if (null == orgId)
				return constructEmptyPageInfo(rows);
			Organization org = orgService.getSimpleOrgById(orgId);
			PageInfo<HouseLivingPopulationInfo> hInfo = housePopulationDao
					.findFurtherStepGisPersonInfoSearchByPersonTypeListAndOrgId(
							org.getOrgInternalCode(), personType, queryStr,
							page, rows, sidx, sord);
			return hInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("Gis人口二次搜索时出错：", e);
		}
	}

	private Map<String, Object> constructPopulationTableParams(
			PopulationCatalog catalogs) {
		Map<String, Object> catalogParam = new HashMap<String, Object>();
		catalogParam.put("tableName", catalogs.getTableName());
		catalogParam.put("type", catalogs.getCatalog());
		return catalogParam;
	}

	@Override
	public List<HouseLivingTotalInfo> findGisActualPersonTotalInfosByOrgId(
			Long orgId) {
		if (orgId == null) {
			return new ArrayList<HouseLivingTotalInfo>();
		} else {
			return housePopulationDao
					.findGisActualPersonTotalInfosByorgInternalCode(orgService
							.getSimpleOrgById(orgId).getOrgInternalCode());
		}
	}

	@Override
	public int countGisAttenPopulationByOrgIdAndKinds(Long orgId,
			List<String> kinds) {
		if (orgId == null) {
			return 0;
		} else {
			return housePopulationDao.countGisAttenPopulationByOrgIdAndKinds(
					orgService.getSimpleOrgById(orgId).getOrgInternalCode(),
					kinds);
		}
	}

	@Override
	public List<HouseLivingTotalInfo> findGisActualPersonTotalInfosByOrgs(
			List<Organization> orgs, String actulaType) {
		List<HouseLivingTotalInfo> qq = housePopulationDao
				.findGisActualPersonTotalInfosByOrgs(orgs, actulaType);
		return qq;
	}

	@Override
	public int countActualPopulationByHouseId(Long houseId, Long orgId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("houseId", houseId);
		List<Map<String, Object>> catalogsParams = new ArrayList<Map<String, Object>>();
		List<PopulationCatalog> catalogs = PopulationCatalog
				.getAllActualPopulationCatalog();
		for (PopulationCatalog populationCatalog : catalogs) {
			catalogsParams
					.add(constructPopulationTableParams(populationCatalog));
		}
		params.put("catalogs", catalogsParams);
		params.put("shardCode", shardConversion.getShardCode(orgId));
		return this.housePopulationDao.countActualPopulationByHouseId(params);
	}

	@Override
	public PageInfo<HouseLivingPopulationInfo> searchActulaPersonByName(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		try {
			if (null == orgId) {
				return constructEmptyPageInfo(pageSize);
			} else {
				Organization org = orgService.getSimpleOrgById(orgId);
				return housePopulationDao
						.searchActulaPersonByName(org.getOrgInternalCode(),
								pageNum, pageSize, sidx, sord);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("Gis查询人口信息时出错", e);
		}
	}

	@Override
	public List<HouseLivingPopulationInfo> findActulaPopulationInHouseByHouseIdForGis(
			Long houseId, Integer page, Integer rows, String sidx, String sord) {
		try {
			List<HouseLivingPopulationInfo> houseList = housePopulationDao
					.findActulaPopulationInHouseByHouseIdForGis(houseId, page,
							rows, sidx, sord);
			return houseList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceValidationException("Gis同一房屋下查询人口信息时出错", e);
		}
	}

	@Override
	public Map<String, Integer> getMapForActualPopulation(Long houseId,
			Long orgId) {
		String shardCode = shardConversion.getShardCode(orgId);
		return housePopulationDao.getMapForActualPopulation(houseId, shardCode);
	}

	@Override
	public Map<String, Integer> getMapForSpecialPopulation(Long houseId) {
		return housePopulationDao.getMapForSpecialPopulation(houseId);
	}

	@Override
	public PageInfo<HouseLivingPopulationInfo> findLivingInHousePopulationInfos(
			PopulationCatalog type, Long houseId, Integer page, Integer rows,
			String sidx, String sord) {
		PageInfo<HouseLivingPopulationInfo> hhsPage = constructEmptyPageInfo();
		List<HouseLivingPopulationInfo> result = null;
		HouseInfo houseInfo = actualHouseService.getHouseInfoById(houseId);
		if (isAllPopulation(type)) {
			hhsPage = housePopulationDao.findHouseholdStaffForPage(houseId,
					page, rows, sidx, sord, shardConversion
							.getShardCode(houseInfo.getOrganization().getId()));
			result = hhsPage.getResult();
		} else if (isAllActualPopulation(type)) {
			// 已经顺利解决了 查看户籍人口中的分页显示 问题
			hhsPage = housePopulationDao.findHouseholdStaffForPage(houseId,
					page, rows, sidx, sord, shardConversion
							.getShardCode(houseInfo.getOrganization().getId()));
			result = hhsPage.getResult();
		} else if (isAllAttentionActualPopulation(type)) {
			// 以后再解决 这里不正确的分页显示 （不知道这里的什么业务 不好改）
			result = housePopulationDao
					.findLivingHouseAllAttentionPopulationInfos(houseId);
			hhsPage.setTotalRowSize(result.size());
			hhsPage.setResult(result);

		} else if (isActualPopulation(type)) {
			// 以后再解决 以后再解决 这里不正确的分页显示 （不知道这里的什么业务 不好改）
			result = housePopulationDao.findLivingHouseActualPopulationInfos(
					type, houseId);
			hhsPage.setTotalRowSize(result.size());
			hhsPage.setResult(result);
		} else {
			// 以后再解决 这里不正确的分页显示
			result = housePopulationDao
					.findLivingHouseAttentionPopulationInfos(type, houseId);
			hhsPage.setTotalRowSize(result.size());
			hhsPage.setResult(result);
			hhsPage.setTotalRowSize(result.size());
			hhsPage.setResult(result);
		}

		fillPropertyDictName(result);

		return hhsPage;

	}

	private PageInfo<HouseLivingPopulationInfo> constructEmptyPageInfo() {
		PageInfo<HouseLivingPopulationInfo> result = new PageInfo<HouseLivingPopulationInfo>();
		result.setResult(new ArrayList<HouseLivingPopulationInfo>());
		return result;
	}

}
