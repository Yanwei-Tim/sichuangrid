package com.tianque.service;

import java.util.List;

import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchActualCompanyVo;

public interface SearchActualCompanyService {
	/**
	 * 根据查询条件分页查询实有单位
	 */
	public PageInfo<ActualCompany> findActualCompanyByQueryCondition(
			SearchActualCompanyVo searchActualCompanyVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 事件处理的涉及单位的接口
	 * 
	 * @param searchActualCompanyVo
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public List<ActualCompany> findIssueRelateObjectListByQueryCondition(
			SearchActualCompanyVo searchActualCompanyVo, String sidx,
			String sord);

	public List<ActualCompany> searchActualCompanysForExport(
			SearchActualCompanyVo searchActualCompanyVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public Integer getCount(SearchActualCompanyVo searchVo);

}
