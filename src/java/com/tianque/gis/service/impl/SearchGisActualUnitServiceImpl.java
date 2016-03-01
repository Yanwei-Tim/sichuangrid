package com.tianque.gis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.actualCompany.service.ActualCompanyService;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.gis.domain.vo.ActualUnitVo;
import com.tianque.gis.domain.vo.GisCountNumVo;
import com.tianque.gis.service.SearchGisActualUnitService;
import com.tianque.gis.util.GisGlobalValue;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("searchActualUnitService")
public class SearchGisActualUnitServiceImpl implements SearchGisActualUnitService {
	@Autowired
	private ActualCompanyService actualCompanyService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public List<GisCountNumVo> countActualHouseByOrgId(Long orgId) {
		List<ActualCompany> list = actualCompanyService.countActualCompanyByOrgId(orgId);
		List<GisCountNumVo> gisCountNumVos = new ArrayList<GisCountNumVo>();
		for (ActualCompany actualCompany : list) {
			GisCountNumVo gisCountNumVo = new GisCountNumVo();
			gisCountNumVo.setGiscountNum(actualCompany.getGiscountNum());
			gisCountNumVo.setDisplayName(GisGlobalValue.getActualHOuseNameByType(actualCompany
					.getGisSearchType()));
			gisCountNumVo.setTypeCatalogName(actualCompany.getGisSearchType());
			gisCountNumVos.add(gisCountNumVo);
		}
		return gisCountNumVos;
	}

	/**
	 * gis搜索
	 */
	@Override
	public PageInfo<ActualUnitVo> searchActualUnitforGisByorgIdAndQueryStr(String queryStr,
			Long orgId, Integer page, Integer rows, String sidx, String sord) {
		PageInfo<ActualCompany> actualCompanysPageInfo = actualCompanyService
				.searchActualUnitforGisByorgIdAndQueryStr(queryStr, orgId, page, rows, sidx, sord);
		return exchangeActualCompanyToActualUnitVO(actualCompanysPageInfo);
	}

	/**
	 * gis列表
	 */
	@Override
	public PageInfo<ActualUnitVo> searchKeyUnitListSearchByOrgId(Long orgId, Integer page,
			Integer rows, String sidx, String sord) {
		PageInfo<ActualCompany> actualCompanysPageInfo = actualCompanyService
				.searchKeyUnitListSearchByOrgId(orgId, page, rows, sidx, sord);
		return exchangeActualCompanyToActualUnitVO(actualCompanysPageInfo);
	}

	private PageInfo<ActualUnitVo> exchangeActualCompanyToActualUnitVO(
			PageInfo<ActualCompany> actualCompanysPageInfo) {
		List<ActualCompany> actualCompanys = actualCompanysPageInfo.getResult();
		List<ActualUnitVo> actualUnitVos = new ArrayList<ActualUnitVo>();
		shiftViewActualCompanyToActualUnitVo(actualUnitVos, actualCompanys);
		PageInfo<ActualUnitVo> pageInfo = new PageInfo<ActualUnitVo>();
		pageInfo.setResult(actualUnitVos);
		pageInfo.setTotalRowSize(actualCompanysPageInfo.getTotalRowSize());
		return pageInfo;
	}

	private void shiftViewActualCompanyToActualUnitVo(List<ActualUnitVo> actualUnitVos,
			List<ActualCompany> actualCompanys) {
		for (ActualCompany actualCompany : actualCompanys) {
			ActualUnitVo actualUnitVo = new ActualUnitVo();
			actualUnitVo.setBusinessLicenseNo(actualCompany.getBusinessLicenseNo());
			actualUnitVo.setCompanyAddress(actualCompany.getCompanyAddress());
			actualUnitVo.setCompanyName(actualCompany.getCompanyName());
			actualUnitVo.setKeyPersonType(actualCompany.getKeyPersonType());
			actualUnitVo.setOrgId(actualCompany.getOrganization().getId());
			actualUnitVo.setUnitId(actualCompany.getId());
			if (actualCompany.getGisInfo() != null
					&& actualCompany.getGisInfo().getCenterX() != null) {
				actualUnitVo.setGisInfo(actualCompany.getGisInfo());
				actualUnitVo.setEnableBind(true);
			} else {
				actualUnitVo.setEnableBind(false);
			}
			actualUnitVos.add(actualUnitVo);
		}
	}

	@Override
	public ActualUnitVo getActualUnitDatialInfoByUnitId(Long unitId, Long orgId) {
		ActualCompany actualCompany = actualCompanyService.getActualUnitDatialInfoByUnitId(unitId,
				orgId);
		ActualUnitVo actualUnitVo = new ActualUnitVo();
		actualUnitVo.setUnitId(actualCompany.getId());
		actualUnitVo.setCompanyName(actualCompany.getCompanyName());
		actualUnitVo.setCompanyAddress(actualCompany.getCompanyAddress());
		actualUnitVo.setBusinessLicenseNo(actualCompany.getBusinessLicenseNo());
		if (actualCompany.getCompanyType() != null) {
			actualUnitVo
					.setCompanyType(getPropertyDictText(PropertyTypes.ACTUALCOMPANY_COMPANYTYPE,
							actualCompany.getCompanyType().getId()));
		}
		if (actualCompany.getEconomicNature() != null) {
			actualUnitVo.setEconomicNature(getPropertyDictText(
					PropertyTypes.ACTUALCOMPANY_ECONOMICNATURE, actualCompany.getEconomicNature()
							.getId()));
		}
		// 法人代表
		actualUnitVo.setCorporateRepresentative(actualCompany.getCorporateRepresentative());
		actualUnitVo.setKeyPersonType(actualCompany.getKeyPersonType());
		return actualUnitVo;

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
	public List<ActualUnitVo> listAllBindingActualUnitVo(Long orgId) {
		List<ActualCompany> actualCompanys = actualCompanyService
				.findAllBindingActualUnitByOrgInternalCodeForGis(orgId);
		List<ActualUnitVo> actualUnitVos = new ArrayList<ActualUnitVo>();
		shiftViewActualCompanyToActualUnitVo(actualUnitVos, actualCompanys);
		return actualUnitVos;
	}
}
