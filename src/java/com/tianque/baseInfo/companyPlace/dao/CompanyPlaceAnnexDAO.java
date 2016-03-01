package com.tianque.baseInfo.companyPlace.dao;

import java.util.List;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceAnnex;

@DynamicIbatisDAO(value = "CompanyPlaceAnnexDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("CompanyPlaceAnnexDao")
public interface CompanyPlaceAnnexDAO {
	public Long addCompanyPlaceAnnex(CompanyPlaceAnnex companyPlaceAnnex);

	public CompanyPlaceAnnex getCompanyPlaceAnnex(Long id);

	public void updateCompanyPlaceAnnex(CompanyPlaceAnnex companyPlaceAnnex);

	public void deleteCompanyPlaceAnnex(Long id);

	public void deleteCompanyPlaceAnnexForBusinessId(Long businessId);

	public void batchDeleteCompanyPlaceAnnex(List<Long> ids);

	public List<CompanyPlaceAnnex> queryCompanyPlaceAnnexByBusinessForList(
			Long businessId);
}
