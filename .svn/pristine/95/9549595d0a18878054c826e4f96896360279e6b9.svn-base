package com.tianque.baseInfo.actualCompany.dao;

import java.util.List;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchActualCompanyVo;

public interface SearchActualCompanyDao {
	/**
	 * 根据查询条件分页查询实有人口
	 */
	public PageInfo<ActualCompany> findActualCompanyByQueryCondition(
			SearchActualCompanyVo searchActualCompanyVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public List<ActualCompany> searchActualCompanysForExport(
			SearchActualCompanyVo searchActualCompanyVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public List<ActualCompany> findIssueRelateObjectListByQueryCondition(
			SearchActualCompanyVo searchActualCompanyVo, String sidx,
			String sord);

	public Integer getCount(SearchActualCompanyVo searchVo);
}
