package com.tianque.gis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.actualCompany.service.ActualCompanyService;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.HouseLivingPopulationInfo;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.gis.domain.vo.HouseInfoVo;
import com.tianque.gis.domain.vo.LocationVo;
import com.tianque.gis.domain.vo.PopulationVo;
import com.tianque.gis.service.GisService;
import com.tianque.gis.service.HouseService;
import com.tianque.gis.service.SearchGisPopulationService;
import com.tianque.gis.util.GisGlobalValue;
import com.tianque.issue.domain.IssueNew;
import com.tianque.service.HouseInfoService;
import com.tianque.service.HousePopulationApplyService;
import com.tianque.service.IssueNewService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("gisService")
@Transactional
public class GisServiceImpl implements GisService {
	@Autowired
	private HouseInfoService hosueInfoService;
	@Autowired
	private ActualCompanyService actualCompanyService;
	@Autowired
	private IssueNewService issueNewService;
	@Autowired
	private HouseService houseService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private HousePopulationApplyService housePopulationApplyService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private RentalHouseService rentalHouseService;
	@Autowired
	private Map<String, SearchGisPopulationService> searchPopulationServiceMap;
	@Autowired
	private ShardConversion shardConversion;

	@Override
	public PopulationVo bindPersonOnMap(PopulationVo populationVo) {
		HouseInfo houseInfo = new HouseInfo();
		try {
			houseInfo = houseService.updateHouseInfoForGis(
					populationVo.getHouseId(), populationVo.getGisInfo());
		} catch (Exception e) {
			throw new ServiceValidationException("出错啦！", e);
		}

		populationVo.setHouseId(houseInfo.getId());
		populationVo.setOrgId(houseInfo.getOrganization().getId());
		return populationVo;
	}

	@Override
	public PopulationVo bindPartyWorkOnMap(PopulationVo populationVo) {
		Organization org = new Organization();
		try {
			org = organizationDubboService.updateOrganizationForGis(
					populationVo.getHouseId(), populationVo.getGisInfo());
		} catch (Exception e) {
			throw new ServiceValidationException("出错啦！", e);
		}
		// TODO 返回这些数据有何用途
		populationVo.setHouseId(org.getId());
		return populationVo;
	}

	@Override
	public PopulationVo bindAcualCompanyOnMap(PopulationVo populationVo) {
		ActualCompany actualCompany = new ActualCompany();
		try {
			actualCompany = actualCompanyService.updateactualCompanyInfoForGis(
					populationVo.getHouseId(), populationVo.getGisInfo());
		} catch (Exception e) {
			throw new ServiceValidationException("出错了，检查一下吧！", e);
		}
		populationVo.setOrgId(actualCompany.getOrganization().getId());
		populationVo.setHouseId(actualCompany.getId());
		return populationVo;
	}

	@Override
	public PopulationVo bindIssueNewsOnMap(PopulationVo populationVo) {
		IssueNew issueNew = new IssueNew();
		try {
			issueNew = issueNewService.updateIssueNewsInfoForGis(
					populationVo.getHouseId(), populationVo.getGisInfo());
		} catch (Exception e) {
			throw new ServiceValidationException("出错了，检查一下吧！", e);
		}
		populationVo.setOrgId(issueNew.getOccurOrg().getId());
		populationVo.setHouseId(issueNew.getId());
		return populationVo;
	}

	@Override
	public GisInfo boundGisToLocation(LocationVo locationVo) {
		System.out.println("boundGisToLocation");
		return null;
	}

	@Override
	public HouseInfoVo getHouseById(Long id) {
		System.out.println("getHouseById");
		return null;
	}

	@Override
	public HouseInfoVo getKeyPlaceByIdAndType(Long id, String type) {
		System.out.println("getKeyPlaceByIdAndType");
		return null;
	}

	/**
	 * 查询Gis人员信息
	 */
	@Override
	public PageInfo<PopulationVo> searchPersonByName(Long orgId,
			String queryStr, Integer page, Integer rows, String sidx,
			String sord) {
		PageInfo<HouseLivingPopulationInfo> hPageInfo = housePopulationApplyService
				.findGisPopulationInfosByQueryStrAndOrgId(orgId, queryStr,
						page, rows, sidx, sord);
		return exchangeHouseLivingPopulationInfoToPopulationVoPageInfo(hPageInfo);
	}

	private PageInfo<PopulationVo> exchangeHouseLivingPopulationInfoToPopulationVoPageInfo(
			PageInfo<HouseLivingPopulationInfo> hPageInfo) {
		List<HouseLivingPopulationInfo> houseLivingPopulationInfos = hPageInfo
				.getResult();

		List<PopulationVo> populationVos = new ArrayList<PopulationVo>();
		PageInfo<PopulationVo> populationVoPageInfo = new PageInfo<PopulationVo>();
		for (HouseLivingPopulationInfo houseLivingPopulationInfo : houseLivingPopulationInfos) {
			populationVos.add(searchPopulationServiceMap.get(
					getServiceName(houseLivingPopulationInfo
							.getTypeCatalogName())).shiftGisPopulationById(
					houseLivingPopulationInfo.getId()));
		}
		populationVoPageInfo.setResult(populationVos);
		populationVoPageInfo.setTotalRowSize(hPageInfo.getTotalRowSize());
		populationVoPageInfo.setCurrentPage(hPageInfo.getCurrentPage());
		return populationVoPageInfo;
	}

	@Override
	public Boolean unboundGisToLocation(LocationVo locationVo) {
		return null;
	}

	@Override
	public HouseInfoVo getHouseInfoByHouseId(Long houseId) {
		HouseInfo houseInfo = hosueInfoService.findGisHouseInfoById(houseId);
		return exchangeHouseInfoToHouseInfoVo(houseInfo, houseInfo
				.getOrganization().getId());
	}

	private HouseInfoVo exchangeHouseInfoToHouseInfoVo(HouseInfo houseInfo,
			Long orgId) {
		HouseInfoVo houseInfoVo = new HouseInfoVo();
		houseInfoVo.setHouseId(houseInfo.getId());
		houseInfoVo.setCurrentAddress(houseInfo.getAddress());
		houseInfoVo.setHouseCode(houseInfo.getHouseCode());
		houseInfoVo.setHouseOwner(houseInfo.getHouseOwner());
		houseInfoVo.setHouseArea(houseInfo.getHouseArea());
		houseInfoVo.setBuildingName(houseInfo.getBuildingName());
		// 人员总数
		houseInfoVo.setPersonNum(housePopulationApplyService
				.countActualPopulationByHouseId(houseInfo.getId(), orgId));
		if (null != houseInfo.getIsRentalHouse()
				&& houseInfo.getIsRentalHouse()) {
			houseInfoVo.setIsRentalHouse(HouseInfoVo.ISRENTALHOUSE);
			try {
				houseInfoVo.setHiddenDangerLevel(getPropertyDictText(
						PropertyTypes.HIDDEN_TROUBLE_LEVEL,
						rentalHouseService
								.serchRentalHouseByHouseCode(
										houseInfo.getHouseCode(), orgId)
								.getHiddenDangerLevel().getId()));
			} catch (Exception e) {
				throw new ServiceValidationException("获取gis促租房危险等级时异常：", e);
			}
		} else {
			houseInfoVo.setIsRentalHouse(HouseInfoVo.NONERENTALHOUSE);
		}
		return houseInfoVo;
	}

	@Override
	public PopulationVo unBindPersonOnMap(PopulationVo populationVos) {
		HouseInfo houseInfo = hosueInfoService.unBundPserson(
				populationVos.getHouseId(),
				shardConversion.getShardCode(populationVos.getOrgId()));
		PopulationVo populationVo = new PopulationVo();
		populationVo.setGisInfo(houseInfo.getGisInfo());
		return populationVo;
	}

	@Override
	public PopulationVo unBindOrgOnMap(PopulationVo populationVo) {
		Organization org = organizationDubboService.unBundOrgToGis(populationVo
				.getHouseId());

		PopulationVo populationVos = new PopulationVo();
		populationVos.setGisInfo(org.getGisInfo());
		return populationVos;
	}

	@Override
	public PopulationVo unBindActualCompanyOnMap(PopulationVo populationVo) {
		ActualCompany actualCompany = actualCompanyService
				.unBindActualCompanyOnMap(populationVo.getHouseId());

		PopulationVo populationVos = new PopulationVo();
		populationVos.setGisInfo(actualCompany.getGisInfo());
		return populationVos;
	}

	@Override
	public PopulationVo unBindIssueNewsOnMap(PopulationVo populationVo) {
		IssueNew issueNew = new IssueNew();
		issueNew = issueNewService.unBindIssueNewsOnMap(populationVo
				.getHouseId());

		PopulationVo populationVos = new PopulationVo();
		populationVos.setGisInfo(issueNew.getGisInfo());
		return populationVos;
	}

	// 通过字段名，以及值，读取字典表中对应的中文名。
	private String getPropertyDictText(String domainName, Long id) {
		if (null == id) {
			return "";
		} else {
			PropertyDict dict = propertyDictService
					.getPropertyDictById(id);
			return dict == null ? "" : dict.getDisplayName();
		}
	}

	// 房屋内的人员详细信息
	@Override
	public PageInfo<PopulationVo> getPopulationInfosByHouseId(Long houseId,
			Integer page, Integer rows, String sidx, String sord) {
		List<HouseLivingPopulationInfo> houseLivingPopulationInfos = housePopulationApplyService
				.findActulaPopulationInHouseByHouseIdForGis(houseId, page,
						rows, sidx, sord);
		return exchangeHouseLiveingPopulationInfoToPopulationVoPageInfo(
				houseId, houseLivingPopulationInfos);
	}

	private PageInfo<PopulationVo> exchangeHouseLiveingPopulationInfoToPopulationVoPageInfo(
			Long houseId,
			List<HouseLivingPopulationInfo> houseLivingPopulationInfos) {

		HouseInfo houseInfo = actualHouseService.getHouseInfoById(houseId);
		PageInfo<PopulationVo> pageInfo = new PageInfo<PopulationVo>();
		List<PopulationVo> populationVos = new ArrayList<PopulationVo>();

		for (HouseLivingPopulationInfo houseLivingPopulationInfo : houseLivingPopulationInfos) {
			PopulationVo populationVo = new PopulationVo();
			if (null != houseInfo && null != houseInfo.getGisInfo()) {
				populationVo.setEnableBind(true);
				populationVo.setGisInfo(houseInfo.getGisInfo());
			} else {
				populationVo.setEnableBind(false);
			}
			// 种类
			// populationVo.setCertificateType(houseLivingPopulationInfo.getCertificateType());
			populationVo.setCertificateType(getPropertyDictText(
					PropertyTypes.CERTIFICATE_TYPE, houseLivingPopulationInfo
							.getCertificateType().getId()));
			populationVo.setOrgId(houseInfo.getOrganization().getId());
			populationVo.setPersonId(houseLivingPopulationInfo.getId());
			populationVo.setAddress(houseInfo.getAddress());
			populationVo.setName(houseLivingPopulationInfo.getPersonName());
			populationVo.setGenderName(getPropertyDictText(
					PropertyTypes.GENDER,
					houseLivingPopulationInfo.getGenderId()));
			populationVo.setHouseId(houseInfo.getId());
			populationVo.setIdCardNo(houseLivingPopulationInfo
					.getCertificateNumber());
			populationVo.setKeyPersonType(houseLivingPopulationInfo
					.getTypeCatalogName());
			// TODO 房屋内人员列表
			System.out.println(houseLivingPopulationInfo.getTypeCatalogName());
			populationVos.add(populationVo);
		}
		pageInfo.setResult(populationVos);
		pageInfo.setTotalRowSize(populationVos.size());
		return pageInfo;
	}

	@Override
	public PageInfo<PopulationVo> searchActulaPersonByName(Long orgId,
			Integer page, Integer rows, String sidx, String sord) {
		PageInfo<HouseLivingPopulationInfo> hPageInfo = housePopulationApplyService
				.searchActulaPersonByName(orgId, page, rows, sidx, sord);
		return exchangeHouseLivingPopulationInfoToPopulationVoPageInfo(hPageInfo);
	}

	@Override
	public void bindHouseOnMap(Long houseId, GisInfo gisInfo) {
		try {
			houseService.updateHouseInfoForGis(houseId, gisInfo);
		} catch (Exception e) {
			throw new ServiceValidationException("出错啦！", e);
		}
	}

	@Override
	public void unBindHouseOnMap(Long houseId) {
		try {
			houseService.unbindHousePropertyById(houseId);
		} catch (Exception e) {
			throw new ServiceValidationException("出错啦！", e);
		}
	}

	@Override
	public void bindIssueOnMap(Long issueId, GisInfo gisInfo) {
		try {
			issueNewService.updateIssueNewsInfoForGis(issueId, gisInfo);
		} catch (Exception e) {
			throw new ServiceValidationException("出错了，检查一下吧！", e);
		}
	}

	@Override
	public void unBindIssueOnMap(Long issueId) {
		issueNewService.unBindIssueNewsOnMap(issueId);
	}

	/**
	 * 单位绑定
	 */
	@Override
	public void bindActualUnitOnMap(Long unitId, GisInfo gisInfo) {
		try {
			actualCompanyService.updateactualCompanyInfoForGis(unitId, gisInfo);
		} catch (Exception e) {
			throw new ServiceValidationException("单位绑定出错了，检查一下吧！", e);
		}

	}

	/**
	 * 单位解绑
	 */
	@Override
	public void unBindActualUnitOnMap(Long unitId) {
		try {
			actualCompanyService.unBindActualCompanyOnMap(unitId);
		} catch (Exception e) {
			throw new ServiceValidationException("出错啦！", e);
		}
	}

	private String getServiceName(String type) {
		if (type.equals(PopulationCatalog.ALL_ACTUAL_POPULATION)
				|| type.equals(GisGlobalValue.IMPORTANT_POPULATION)
				|| type.equals(GisGlobalValue.NURTURES_WOMAN)
				|| type.equals(GisGlobalValue.CAREED_POPULATION)) {
			type = "searchPopulation";
			return type + "Service";
		} else {
			return type + "Service";
		}
	}

}
