package com.tianque.gis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.actualCompany.service.ActualCompanyService;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.HouseLivingTotalInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.gis.domain.vo.BuildingHouseVo;
import com.tianque.gis.domain.vo.BuildingInfoVo;
import com.tianque.gis.domain.vo.GisCountNumVo;
import com.tianque.gis.domain.vo.HouseInfoVo;
import com.tianque.gis.domain.vo.RoundBuildingInfo;
import com.tianque.gis.service.SearchGisHouseInfoService;
import com.tianque.gis.util.GisCountTypeMapUtil;
import com.tianque.gis.util.GisGlobalValue;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.service.IssueService;
import com.tianque.service.HouseInfoService;
import com.tianque.service.HousePopulationApplyService;
import com.tianque.service.IssueNewService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("searchHouseInfo")
public class SearchGisHouseInfoServiceImpl implements SearchGisHouseInfoService {
	@Autowired
	private HouseInfoService houseInfoService;
	@Autowired
	private ActualCompanyService actualCompanyService;
	@Autowired
	private ActualHouseService houseACInfoService;
	@Autowired
	private IssueNewService issueNewService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private HousePopulationApplyService housePopulationApplyService;

	@Autowired
	private IssueService defaultIssueService;

	// Gis搜索
	@Override
	public PageInfo<HouseInfoVo> getPageInfoByQueryStrForSearchHouseInfo(
			Long orgId, String queryStr, Integer page, Integer rows,
			String sidx, String sord) {
		if (orgId == null || orgId < 0L) {
			throw new BusinessValidationException("orgId获取失败");
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			PageInfo<HouseInfo> pageinfo = houseInfoService
					.getPageInfoByQueryStrForSearchHouseInfo(
							org.getOrgInternalCode(), queryStr, page, rows,
							sidx, sord);
			return changeHouseInfoListToHouseInfoVoPageInfo(pageinfo);
		}
	}

	// Gis列表
	@Override
	public PageInfo<HouseInfoVo> searchkeyHouseInfoListByorgId(Long orgId,
			String houseType, Integer page, Integer rows, String sidx,
			String sord) {
		if (orgId == null || orgId < 0L) {
			throw new BusinessValidationException("orgId获取失败");
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			PageInfo<HouseInfo> pageinfo = houseInfoService
					.searchkeyHouseInfoListByorgId(org.getOrgInternalCode(),
							houseType, page, rows, sidx, sord);
			return changeHouseInfoListToHouseInfoVoPageInfo(pageinfo);
		}
	}

	private PageInfo<HouseInfoVo> changeHouseInfoListToHouseInfoVoPageInfo(
			PageInfo<HouseInfo> psInfo) {
		List<HouseInfo> houseInfos = psInfo.getResult();
		List<HouseInfoVo> houseInfoVos = new ArrayList<HouseInfoVo>();
		shiftViewFromHouseInfoToHouseInfoVo(houseInfos, houseInfoVos);
		PageInfo<HouseInfoVo> pageInfo = new PageInfo<HouseInfoVo>();
		pageInfo.setResult(houseInfoVos);
		pageInfo.setTotalRowSize(psInfo.getTotalRowSize());
		pageInfo.setCurrentPage(psInfo.getCurrentPage());
		pageInfo.setPerPageSize(psInfo.getPerPageSize());

		return pageInfo;
	}

	private void shiftViewFromHouseInfoToHouseInfoVo(
			List<HouseInfo> houseInfos, List<HouseInfoVo> houseInfoVos) {
		for (HouseInfo houseInfo : houseInfos) {
			HouseInfoVo houseInfoVo = new HouseInfoVo();
			houseInfoVo.setHouseId(houseInfo.getId());
			houseInfoVo.setOrgId(houseInfo.getOrganization().getId());
			houseInfoVo.setCurrentAddress(houseInfo.getAddress());
			houseInfoVo.setHouseCode(houseInfo.getHouseCode());
			houseInfoVo.setBuildingName(houseInfo.getBuildingName());
			if (null != houseInfo && null != houseInfo.getGisInfo()) {
				houseInfoVo.setGisInfo(houseInfo.getGisInfo());
				houseInfoVo.setEnableBind(true);
			} else {
				houseInfoVo.setEnableBind(false);
			}
			houseInfoVos.add(houseInfoVo);
		}
	}

	/**
	 * gis 搜索的业务处理，将返回来的16大小类，按需求封装分类返回。 actualCatalog
	 * 是对父类的分类处理，actulChildCatalog是对子类的分类处理
	 */

	@Override
	public List<GisCountNumVo> countActualPopulationByOrgIdAndActulaType(
			Long orgId) {
		List<GisCountNumVo> gisCountNumVos = new ArrayList<GisCountNumVo>();
		if (null == orgId) {
			throw new BusinessValidationException("gis辖区统计获取orgId异常,检查一下吧！");
		} else {
			List<HouseLivingTotalInfo> houseLivingPopulationInfos = housePopulationApplyService
					.findGisActualPersonTotalInfosByOrgId(orgId);

			formGisCountNumVosFromHouseLivingTotalInfos(gisCountNumVos,
					houseLivingPopulationInfos);
			gisCountNumVos
					.addAll(getGisCountNumVoFromAttenionPopulation(orgId));

		}
		return gisCountNumVos;
	}

	/**
	 * 封装实有人口
	 * 
	 * @param gisCountNumVos
	 *            需要返回页面的对象
	 * @param houseLivingPopulationInfos
	 *            实有人口原始数据
	 */
	private void formGisCountNumVosFromHouseLivingTotalInfos(
			List<GisCountNumVo> gisCountNumVos,
			List<HouseLivingTotalInfo> houseLivingPopulationInfos) {
		gisCountNumVos
				.add(formGisCountNumVoFromCatalogAndCount(
						PopulationCatalog.ACTUAL_POPULATION,
						getTotalActualPopulationFromHouseLivingTotalInfos(houseLivingPopulationInfos))); // 封装实有人口
		getSpecialHouseLivingTotalInfo(PopulationCatalog.HOUSEHOULD_POPULATION,
				houseLivingPopulationInfos, gisCountNumVos); // 封装户籍人口
		getSpecialHouseLivingTotalInfo(PopulationCatalog.FLOATING_POPULATION,
				houseLivingPopulationInfos, gisCountNumVos); // 封装流动人口
		getSpecialHouseLivingTotalInfo(
				PopulationCatalog.UNHOUSEHOULD_POPULATION,
				houseLivingPopulationInfos, gisCountNumVos); // 封装未落户人口
		getSpecialHouseLivingTotalInfo(PopulationCatalog.OVERSEA_POPULATION,
				houseLivingPopulationInfos, gisCountNumVos); // 封装境外人员
	}

	private void getSpecialHouseLivingTotalInfo(
			PopulationCatalog populationCatalog,
			List<HouseLivingTotalInfo> houseLivingPopulationInfos,
			List<GisCountNumVo> gisCountNumVos) {
		for (HouseLivingTotalInfo ho : houseLivingPopulationInfos) {
			if (ho.getTypeCatalogName().equals(populationCatalog.getCatalog())) {
				gisCountNumVos.add(formGisCountNumVoFromCatalogAndCount(
						PopulationCatalog.parse(ho.getTypeCatalogName()),
						ho.getPopulationCount()));
				break;
			}
		}
	}

	private int getTotalActualPopulationFromHouseLivingTotalInfos(
			List<HouseLivingTotalInfo> houseLivingPopulationInfos) {
		int count = 0;
		for (HouseLivingTotalInfo info : houseLivingPopulationInfos) {
			count += info.getPopulationCount();
		}
		return count;
	}

	private GisCountNumVo formGisCountNumVoFromCatalogAndCount(
			PopulationCatalog catalog, int count) {
		GisCountNumVo countNumVo = new GisCountNumVo();
		countNumVo.setDisplayName(catalog.getDisplayName());
		countNumVo.setTypeCatalogName(catalog.getCatalog());
		countNumVo.setGiscountNum(count);
		return countNumVo;
	}

	private List<GisCountNumVo> getGisCountNumVoFromAttenionPopulation(
			Long orgId) {
		List<GisCountNumVo> gisCountNumVos = new ArrayList<GisCountNumVo>();
		gisCountNumVos.add(formGisCountNumVoFromCatalogAndCount(
				PopulationCatalog.ATTENTION_POPULATION,
				housePopulationApplyService
						.countGisAttenPopulationByOrgIdAndKinds(orgId,
								formFilterKinds(PopulationCatalog
										.getAllAttentionPopulationCatalog()))));
		gisCountNumVos.add(formGisCountNumVoFromCatalogAndCount(
				PopulationCatalog.BIRTH_POPULATION, housePopulationApplyService
						.countGisAttenPopulationByOrgIdAndKinds(orgId,
								formFilterKinds(PopulationCatalog
										.getAllBirthPopulationCatalog()))));
		gisCountNumVos.add(formGisCountNumVoFromCatalogAndCount(
				PopulationCatalog.CIVIL_POPULATION, housePopulationApplyService
						.countGisAttenPopulationByOrgIdAndKinds(orgId,
								formFilterKinds(PopulationCatalog
										.getAllCivilPopulationCatalog()))));
		return gisCountNumVos;
	}

	private List<String> formFilterKinds(List<PopulationCatalog> catalogs) {
		List<String> kinds = new ArrayList<String>();
		for (PopulationCatalog catalog : catalogs) {
			kinds.add(catalog.getCatalog());
		}
		return kinds;
	}

	/**
	 * 计算父类的人口数量
	 * 
	 * @param houseLivingPopulationInfos
	 * @param map
	 * @param parentCataLogCountNumVo
	 */
	private void sumPopulaionCountNumByParentCatalog(
			List<HouseLivingTotalInfo> houseLivingPopulationInfos,
			Map<String, GisCountNumVo> map,
			GisCountNumVo parentCataLogCountNumVo) {
		for (HouseLivingTotalInfo houseLivingTotalInfo : houseLivingPopulationInfos) {
			if (parentCataLogCountNumVo.getTypeCatalogName().equals(
					PopulationCatalog.parse(
							houseLivingTotalInfo.getTypeCatalogName())
							.getParentCatalog())) {
				if (!PopulationType.OVERSEA_STAFF.equals(houseLivingTotalInfo
						.getTypeCatalogName())) {
					parentCataLogCountNumVo
							.setGiscountNum(parentCataLogCountNumVo
									.getGiscountNum()
									+ houseLivingTotalInfo.getPopulationCount());
				}
			}
		}
	}

	/**
	 * 组装父类和子类的人口统计
	 * 
	 * @param houseLivingPopulationInfos
	 * @param map
	 */
	private void packagingParentCatalogAndChildCatalogCountNumVO(
			List<HouseLivingTotalInfo> houseLivingPopulationInfos,
			Map<String, GisCountNumVo> map) {
		sumPopulaionCountNumByParentCatalog(
				houseLivingPopulationInfos,
				map,
				createParentCatalogCountNumVo(map,
						PopulationCatalog.ACTUAL_POPULATION));
		packagingChildPopulationCountNumVo(houseLivingPopulationInfos, map);
		sumPopulaionCountNumByParentCatalog(
				houseLivingPopulationInfos,
				map,
				createParentCatalogCountNumVo(map,
						PopulationCatalog.ATTENTION_POPULATION));
		sumPopulaionCountNumByParentCatalog(
				houseLivingPopulationInfos,
				map,
				createParentCatalogCountNumVo(map,
						PopulationCatalog.BIRTH_POPULATION));
		sumPopulaionCountNumByParentCatalog(
				houseLivingPopulationInfos,
				map,
				createParentCatalogCountNumVo(map,
						PopulationCatalog.CIVIL_POPULATION));
	}

	/**
	 * 筛选实口中的子类的人口统计
	 * 
	 * @param houseLivingPopulationInfos
	 * @param map
	 */
	private void packagingChildPopulationCountNumVo(
			List<HouseLivingTotalInfo> houseLivingPopulationInfos,
			Map<String, GisCountNumVo> map) {
		createChildPopulationCountNumVo(map, PopulationType.HOUSEHOLD_STAFF,
				houseLivingPopulationInfos);
		createChildPopulationCountNumVo(map,
				PopulationType.FLOATING_POPULATION, houseLivingPopulationInfos);
		createChildPopulationCountNumVo(map,
				PopulationType.UNSETTLED_POPULATION, houseLivingPopulationInfos);
		createChildPopulationCountNumVo(map, PopulationType.OVERSEA_STAFF,
				houseLivingPopulationInfos);
	}

	/**
	 * 组装子类的人口统计
	 * 
	 * @param map
	 * @param typeCatalog
	 * @param houseLivingPopulationInfos
	 */
	private void createChildPopulationCountNumVo(
			Map<String, GisCountNumVo> map, String typeCatalog,
			List<HouseLivingTotalInfo> houseLivingPopulationInfos) {
		GisCountNumVo childCatalogCountNumVo = new GisCountNumVo();
		for (HouseLivingTotalInfo houseLivingTotalInfo : houseLivingPopulationInfos) {
			if (typeCatalog.equals(houseLivingTotalInfo.getTypeCatalogName())) {
				childCatalogCountNumVo.setDisplayName(PopulationCatalog.parse(
						houseLivingTotalInfo.getTypeCatalogName())
						.getDisplayName());
				childCatalogCountNumVo.setTypeCatalogName(houseLivingTotalInfo
						.getTypeCatalogName());
				childCatalogCountNumVo.setGiscountNum(houseLivingTotalInfo
						.getPopulationCount());

				map.put(houseLivingTotalInfo.getTypeCatalogName(),
						childCatalogCountNumVo);
			}
		}
	}

	/**
	 * 组装父类的人口统计，并初始化数量为0
	 * 
	 * @param map
	 * @param catalog
	 * @return
	 */
	private GisCountNumVo createParentCatalogCountNumVo(
			Map<String, GisCountNumVo> map, PopulationCatalog catalog) {
		GisCountNumVo countNumVo = new GisCountNumVo();
		countNumVo.setDisplayName(catalog.getDisplayName());
		countNumVo.setTypeCatalogName(catalog.getCatalog());
		countNumVo.setGiscountNum(0);
		map.put(catalog.getCatalog(), countNumVo);
		return countNumVo;
	}

	public List<HouseLivingTotalInfo> countActualPopulationByParentOrgIdAndActulaType(
			Long orgId, String actulaType) {
		if (actulaType.equals(PopulationCatalog.ALL_ACTUAL_POPULATION)
				|| actulaType
						.equals(PopulationCatalog.ALL_ATTENTION_POPULATION)
				|| actulaType.equals(PopulationCatalog.ALL_BIRTH_POPULATION)
				|| actulaType.equals(PopulationCatalog.ALL_CIVIL_POPULATION)) {
			List<PopulationCatalog> actualCatalogs = getCatalogs(actulaType);
			Map<String, HouseLivingTotalInfo> resultMap = getHouseLivingTotalInfoMap(
					orgId, actualCatalogs);
			return new ArrayList<HouseLivingTotalInfo>(resultMap.values());
		} else if (actulaType.equals(GisGlobalValue.ACTUALHOUSE)
				|| actulaType.equals(GisGlobalValue.RENTALHOUSE)) {
			return countHouseByParentOrgIdAndActulaType(orgId, actulaType);
		} else if (actulaType.equals(GisGlobalValue.ACTUALUNIT)) {
			return countUnitByParentOrgIdAndActulaType(orgId, actulaType);
			// actulaType.equals(GisGlobalValue.ISSUENEWS) ||
		} else if (actulaType.equals("contradictionIssue")
				|| actulaType.equals("peopleliveIssue")
				|| actulaType.equals("securityIssue")
				|| actulaType.equals("completeIssue")
				|| actulaType.equals("uncompleteIssue")
				|| actulaType.equals("isemergency")
				|| actulaType.equals("important")) {
			return countIssuenewsByParentOrgIdAndActulaType(orgId, actulaType);
		} else {
			return countPopulationByParentOrgIdAndActulaType(orgId, actulaType);
		}
	}

	private Map<String, HouseLivingTotalInfo> getHouseLivingTotalInfoMap(
			Long orgId, List<PopulationCatalog> actualCatalogs) {
		Map<String, HouseLivingTotalInfo> resultMap = new HashMap<String, HouseLivingTotalInfo>();
		for (PopulationCatalog populationCatalog : actualCatalogs) {
			if (populationCatalog.getCatalog().equals(
					PopulationType.OVERSEA_STAFF))
				continue;
			List<HouseLivingTotalInfo> temp = countPopulationByParentOrgIdAndActulaType(
					orgId, populationCatalog.getCatalog());
			for (HouseLivingTotalInfo info : temp) {
				if (resultMap.get(info.getOrgCode()) == null) {
					resultMap.put(info.getOrgCode(), info);
				} else {
					info.setPopulationCount(resultMap.get(info.getOrgCode())
							.getPopulationCount() + info.getPopulationCount());
					resultMap.put(info.getOrgCode(), info);
				}
			}
		}
		return resultMap;
	}

	private List<PopulationCatalog> getCatalogs(String actulaType) {
		List<PopulationCatalog> actualCatalogs = new ArrayList<PopulationCatalog>();
		if (actulaType.equals(PopulationCatalog.ALL_ACTUAL_POPULATION)) {
			actualCatalogs = PopulationCatalog.getAllActualPopulationCatalog();
		} else if (actulaType
				.equals(PopulationCatalog.ALL_ATTENTION_POPULATION)) {
			actualCatalogs = PopulationCatalog
					.getAllAttentionPopulationCatalog();
		} else if (actulaType.equals(PopulationCatalog.ALL_BIRTH_POPULATION)) {
			actualCatalogs = PopulationCatalog.getAllBirthPopulationCatalog();
		} else if (actulaType.equals(PopulationCatalog.ALL_CIVIL_POPULATION)) {
			actualCatalogs = PopulationCatalog.getAllCivilPopulationCatalog();
		}
		return actualCatalogs;
	}

	private List<HouseLivingTotalInfo> countPopulationByParentOrgIdAndActulaType(
			Long orgId, String actulaType) {
		List<Organization> orgs = organizationDubboService
				.findOrganizationsByParentId(orgId);
		if (orgs != null && orgs.size() > 0) {
			return housePopulationApplyService
					.findGisActualPersonTotalInfosByOrgs(orgs, actulaType);
		}
		return new ArrayList<HouseLivingTotalInfo>();
	}

	private List<HouseLivingTotalInfo> countHouseByParentOrgIdAndActulaType(
			Long parentOrgId, String actulaType) {
		List<HouseLivingTotalInfo> houseLivingTotalInfos = new ArrayList<HouseLivingTotalInfo>();
		List<Organization> orgs = organizationDubboService
				.findOrganizationsByParentId(parentOrgId);
		if (orgs != null && orgs.size() > 0) {
			for (Organization org : orgs) {
				HouseLivingTotalInfo houseLivingTotalInfo = new HouseLivingTotalInfo();
				List<HouseInfo> list = houseInfoService
						.countActualHouseByOrgId(org.getId());
				int count = -1;
				for (HouseInfo houseInfo : list) {
					if (houseInfo.getGisSearchType() == null
							|| (houseInfo.getGisSearchType() != null && (houseInfo
									.getGisSearchType().equals("") || !houseInfo
									.getGisSearchType().equals(actulaType))))
						continue;
					if (houseInfo.getGiscountNum() > -1)
						count = houseInfo.getGiscountNum();
				}
				if (count < 0)
					count = 0;
				houseLivingTotalInfo.setOrgCode(org.getDepartmentNo());
				houseLivingTotalInfo.setOrgName(org.getOrgName());
				houseLivingTotalInfo.setPopulationCount(count);
				houseLivingTotalInfo.setTypeCatalogName(actulaType);
				houseLivingTotalInfos.add(houseLivingTotalInfo);
			}
			return houseLivingTotalInfos;
		}
		return new ArrayList<HouseLivingTotalInfo>();
	}

	private List<HouseLivingTotalInfo> countUnitByParentOrgIdAndActulaType(
			Long parentOrgId, String actulaType) {
		List<HouseLivingTotalInfo> houseLivingTotalInfos = new ArrayList<HouseLivingTotalInfo>();
		List<Organization> orgs = organizationDubboService
				.findOrganizationsByParentId(parentOrgId);
		if (orgs != null && orgs.size() > 0) {
			for (Organization org : orgs) {
				HouseLivingTotalInfo houseLivingTotalInfo = new HouseLivingTotalInfo();
				List<ActualCompany> list = actualCompanyService
						.countActualCompanyByOrgId(org.getId());
				int count = -1;
				for (ActualCompany actualCompany : list) {
					if (actualCompany.getGisSearchType() == null
							|| (actualCompany.getGisSearchType() != null && (actualCompany
									.getGisSearchType().equals("") || !actualCompany
									.getGisSearchType().equals(actulaType))))
						continue;
					if (actualCompany.getGiscountNum() > -1)
						count = actualCompany.getGiscountNum();
				}
				if (count < 0)
					count = 0;
				houseLivingTotalInfo.setOrgCode(org.getDepartmentNo());
				houseLivingTotalInfo.setOrgName(org.getOrgName());
				houseLivingTotalInfo.setPopulationCount(count);
				houseLivingTotalInfo.setTypeCatalogName(actulaType);
				houseLivingTotalInfos.add(houseLivingTotalInfo);
			}
			return houseLivingTotalInfos;
		}
		return new ArrayList<HouseLivingTotalInfo>();
	}

	private List<HouseLivingTotalInfo> countIssuenewsByParentOrgIdAndActulaType(
			Long parentOrgId, String actulaType) {

		/*
		 * List<IssueNew> list = issueNewService.countActualHouseByOrgId(orgId);
		 * return countIssuenewsByParentOrgIdAndActulaType(orgId, actulaType);
		 */
		List<HouseLivingTotalInfo> houseLivingTotalInfos = new ArrayList<HouseLivingTotalInfo>();
		List<Organization> orgs = organizationDubboService
				.findOrganizationsByParentId(parentOrgId);
		if (orgs != null && orgs.size() > 0) {
			for (Organization org : orgs) {
				HouseLivingTotalInfo houseLivingTotalInfo = new HouseLivingTotalInfo();
				List<IssueNew> list = issueNewService
						.countActualHouseByOrgId(org.getId());
				int count = -1;
				for (IssueNew actualCompany : list) {
					if (actualCompany.getGisSearchType() == null
							|| (actualCompany.getGisSearchType() != null && (actualCompany
									.getGisSearchType().equals("") || !GisCountTypeMapUtil
									.getGisCountType(
											actualCompany.getGisSearchType())
									.equals(actulaType))))
						continue;
					if (actualCompany.getGiscountNum() > -1)
						count = actualCompany.getGiscountNum();
				}
				if (count < 0)
					count = 0;
				houseLivingTotalInfo.setOrgCode(org.getDepartmentNo());
				houseLivingTotalInfo.setOrgName(org.getOrgName());
				houseLivingTotalInfo.setPopulationCount(count);
				houseLivingTotalInfo.setTypeCatalogName(actulaType);
				houseLivingTotalInfos.add(houseLivingTotalInfo);
			}
			return houseLivingTotalInfos;
		}
		return new ArrayList<HouseLivingTotalInfo>();
	}

	@Override
	public List<GisCountNumVo> countActualHouseByOrgId(Long orgId) {
		List<HouseInfo> list = houseInfoService.countActualHouseByOrgId(orgId);
		List<GisCountNumVo> houseInfoVos = new ArrayList<GisCountNumVo>();
		for (HouseInfo houseInfo : list) {
			GisCountNumVo gisCountNumVo = new GisCountNumVo();
			gisCountNumVo.setGiscountNum(houseInfo.getGiscountNum());
			gisCountNumVo.setDisplayName(GisGlobalValue
					.getActualHOuseNameByType(houseInfo.getGisSearchType()));
			gisCountNumVo.setTypeCatalogName(houseInfo.getGisSearchType());
			houseInfoVos.add(gisCountNumVo);
		}
		return houseInfoVos;
	}

	@Override
	public List<HouseInfoVo> findAllBindingHouseInfoBy(Long orgId) {
		if (orgId == null || orgId < 0L) {
			throw new BusinessValidationException("orgId获取失败");
		} else {
			Organization org = organizationDubboService.getSimpleOrgById(orgId);
			List<HouseInfo> houseInfos = houseInfoService
					.findAllBindingHouseInfoByOrgInternalCode(org
							.getOrgInternalCode());
			return changeHouseInfoListToHouseInfoVoPageInfo(houseInfos);
		}
	}

	private List<HouseInfoVo> changeHouseInfoListToHouseInfoVoPageInfo(
			List<HouseInfo> houseInfos) {
		List<HouseInfoVo> houseInfoVos = new ArrayList<HouseInfoVo>();
		shiftViewFromHouseInfoToHouseInfoVo(houseInfos, houseInfoVos);
		return houseInfoVos;
	}

	@Override
	public BuildingInfoVo getBuildingInfoVoByBuildingId(Long buildingId) {
		List<HouseInfo> houses = houseInfoService
				.findAllBindingHouseInfoByBuildingId(buildingId);
		List<BuildingHouseVo> vos = null;
		if (null != houses && houses.size() > 0) {
			vos = new ArrayList<BuildingHouseVo>();
			for (HouseInfo house : houses) {
				vos.add(satisticBuildingHouseVoFromHouseInfo(house));
			}
		}
		return new BuildingInfoVo(buildingId,
				actualCompanyService
						.countBindingActualUnitBybuildingIdForGis(buildingId),
				vos);
	}

	private BuildingHouseVo satisticBuildingHouseVoFromHouseInfo(
			HouseInfo houseInfo) {
		BuildingHouseVo vo = new BuildingHouseVo(
				houseInfo.getId(),
				houseInfo.getHouseCode(),
				houseInfo.getIsRentalHouse(),
				(null != houseInfo.getUnit() && null != houseInfo.getRoom()) ? new StringBuffer()
						.append(houseInfo.getUnit()).append("-")
						.append(houseInfo.getRoom()).toString()
						: houseInfo.getAddress());
		Map<String, Integer> aps = housePopulationApplyService
				.getMapForActualPopulation(houseInfo.getId(), houseInfo
						.getOrganization().getId());
		if (null != aps) {
			if (aps.keySet().contains(PopulationType.HOUSEHOLD_STAFF))
				vo.setHouseholdstaffCount(aps
						.get(PopulationType.HOUSEHOLD_STAFF));
			if (aps.keySet().contains(PopulationType.FLOATING_POPULATION))
				vo.setFloatingPopulationCount(aps
						.get(PopulationType.FLOATING_POPULATION));
			if (aps.keySet().contains(PopulationType.UNSETTLED_POPULATION))
				vo.setUnsettledPopulationCount(aps
						.get(PopulationType.UNSETTLED_POPULATION));
			if (aps.keySet().contains(PopulationType.OVERSEA_STAFF))
				vo.setOverseaPersonnelCount(aps
						.get(PopulationType.OVERSEA_STAFF));
		}
		return statisticSpecialPopulationFromBuildingHouseVo(vo);
	}

	private BuildingHouseVo statisticSpecialPopulationFromBuildingHouseVo(
			BuildingHouseVo buildingHouseVo) {
		Map<String, Integer> sps = housePopulationApplyService
				.getMapForSpecialPopulation(buildingHouseVo.getHouseId());
		buildingHouseVo.setNurturesWomenCount(countSameTypePopulations(sps,
				PopulationCatalog.getAllBirthPopulationCatalog()));
		buildingHouseVo.setEmphasisPopulationCount(countSameTypePopulations(
				sps, PopulationCatalog.getAllAttentionPopulationCatalog()));
		buildingHouseVo.setSolicitudeObjectCount(countSameTypePopulations(sps,
				PopulationCatalog.getAllCivilPopulationCatalog()));
		return buildingHouseVo;
	}

	private int countSameTypePopulations(Map<String, Integer> sps,
			List<PopulationCatalog> populationCatalogs) {
		int count = 0;
		for (PopulationCatalog populationCatalog : populationCatalogs) {
			if (sps.keySet().contains(populationCatalog.getCatalog())) {
				count += sps.get(populationCatalog.getCatalog());
			}
		}
		return count;
	}

	@Override
	public RoundBuildingInfo getRoundHouseInfo(GisInfo minOption,
			GisInfo maxOption) {
		RoundBuildingInfo roundBuildingInfo = new RoundBuildingInfo();
		List<HouseInfo> houses = houseACInfoService.searchMapScope(minOption,
				maxOption);
		List<BuildingHouseVo> vos = null;
		if (null != houses && houses.size() > 0) {
			vos = new ArrayList<BuildingHouseVo>(64);
			for (HouseInfo house : houses) {
				vos.add(satisticBuildingHouseVoFromHouseInfo(house));
			}
		}
		roundBuildingInfo.setBuildingHouseVos(vos);
		roundBuildingInfo.setAllCompanyList(actualCompanyService
				.searchAllRoundCompany(minOption, maxOption));
		roundBuildingInfo.setIssueNewList(defaultIssueService
				.searchAllRoundIssues(minOption, maxOption));
		roundBuildingInfo.countALLData();
		return roundBuildingInfo;
	}
}