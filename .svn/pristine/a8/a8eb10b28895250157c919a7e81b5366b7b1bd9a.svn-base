package com.tianque.baseInfo.companyPlace.service;

import java.util.List;

import org.oproject.framework.orm.PageResult;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;

public interface CompanyPlaceService {

	public CompanyPlace addCompanyPlace(CompanyPlace companyPlace);

	public Long addCompanyPlaceToBase(CompanyPlace companyPlace);

	public boolean deleteCompanyPlace(Long id);

	public void deleteCompanyPlaceByIds(Long[] ids);

	public CompanyPlace updateCompanyPlace(CompanyPlace companyPlace);

	public CompanyPlace readeCompanyPlace(Long baseId);

	public void batchDeleteCompanyPlace(List<Long> ids);

	public PageResult<CompanyPlace> queryCompanyPlaceForPageResult(
			CompanyPlace companyPlace, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public void updateCompanyPlaceByCid(CompanyPlace companyPlace);

	public List<CompanyPlace> queryCompanyPlaceForList(CompanyPlace companyPlace);

	public int getCount(CompanyPlace companyPlace);

	public CompanyPlace hasDuplicateCompanyPlace(Long orgId, String placeName,
			Long typeId);

	public void recoverCompanyPlaceForRecover(CompanyPlace companyPlace);

	/** 同步company表中的org */
	// public void updateCompanyPlaceOrgByCid(CompanyPlace companyPlace);

}
