package com.tianque.baseInfo.companyPlace.dao;

import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;

@DynamicIbatisDAO(value = "CompanyPlaceDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("CompanyPlaceDAO")
public interface CompanyPlaceDAO {
	public Long addCompanyPlace(CompanyPlace companyPlace);

	public int deleteCompanyPlace(Long baseId);

	public int updateCompanyPlace(CompanyPlace companyPlace);

	public CompanyPlace readeCompanyPlace(Long id);

	public void batchDeleteCompanyPlace(List<Long> ids);

	public void batchDeleteCompanyPlaceBaseByIds(List<Long> ids);

	public PageResult<CompanyPlace> queryCompanyPlaceForPageResult(
			CompanyPlace companyPlace, int pageNum, int pageSize);

	public List<CompanyPlace> queryCompanyPlaceForList(CompanyPlace companyPlace);

	public Integer getCount(CompanyPlace companyPlace);

	public void updateCompanyPlaceByCid(CompanyPlace companyPlace);

	public void addCompanyPlaceForRecover(CompanyPlace companyPlace);

	public CompanyPlace getPlaceByPlaceName(Map map);

	/** 同步company表中的org */
	//public void updateCompanyPlaceOrgByCid(CompanyPlace companyPlace);

}
