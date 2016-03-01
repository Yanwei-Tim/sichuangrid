package com.tianque.baseInfo.companyPlace.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceBusiness;

@DynamicIbatisDAO(value = "CompanyPlaceBusinessDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("CompanyPlaceBusinessDao")
public interface CompanyPlaceBusinessDAO {
	public CompanyPlaceBusiness getCompanyPlaceBusiness(Long id);

	public CompanyPlaceBusiness getCompanyPlaceBusinessByMap(
			Map<String, Long> map);

	public void deleteCompanyPlaceBusiness(Long id);

	public void updateCompanyPlaceBusiness(
			CompanyPlaceBusiness companyPlaceBusiness);

	public Long addCompanyPlaceBusiness(
			CompanyPlaceBusiness companyPlaceBusiness);

	public List<CompanyPlaceBusiness> queryCompanyPlaceBusinessByCompanyPlaceIdForList(
			Long companyPlaceId);

	public void deleteCompanyPlaceBusinessByCompanyPalceId(Long companyPlaceId);
}
