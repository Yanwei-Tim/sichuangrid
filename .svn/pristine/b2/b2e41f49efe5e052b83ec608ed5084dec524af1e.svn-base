package com.tianque.baseInfo.companyPlace.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceBase;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceVo;

@DynamicIbatisDAO(value = "CompanyPlaceBaseDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("CompanyPlaceBaseDAO")
public interface CompanyPlaceBaseDAO {

	public PageResult<CompanyPlace> queryCompanyPlaceForPageResult(
			CompanyPlaceVo companyPlaceVo, int pageNum, int pageSize);

	public void batchDeleteCompanyPlaceBaseByIds(List<Long> idList);

	public int updateCompanyPlaceIsEmphasis(Map map);

	public Integer getCount(CompanyPlaceBase companyPlaceBase);

	public Long addCompanyPlaceBase(CompanyPlace companyPlace);

	public String getCompanyPlaceBaseIdByNameAndOrgid(CompanyPlace companyPlace);

	public void updateCompanyPlaceBaseById(CompanyPlace companyPlace);

	public CompanyPlace getCompanyPlaceInfoByCid(Long cid);

	public void updateCompanyPlaceBaseEmphasisById(CompanyPlace companyPlace);

	public List<CompanyPlace> queryCompanyPlaceForList(
			CompanyPlaceVo companyPlaceVo);

	public void updateCompanyPlaceBaseOrgById(CompanyPlace companyPlace);

	public void updateCompanyPlaceScaleTypeByCid(Long cid);

	public void updateCompanyPlaceScaleTypeByCids(Map map);

	public List<CompanyPlaceBase> queryCompanyplaceInfoLikeNameForList(
			CompanyPlace companyPlace);

	public Integer getCompanyPlaceBaseIdExcIdByNameAndOrgid(
			CompanyPlace companyPlace);

	public void deleteCompanyPlaceBaseById(Long id);

	public Date getLastRecordTime(CompanyPlace companyPlace);
}
