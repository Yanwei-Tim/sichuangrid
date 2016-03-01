package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Enterprise;
import com.tianque.domain.vo.SearchEnterpriseVo;

public interface SearchEnterpriseDao {
	public PageInfo<Enterprise> searchEnterprise(
			SearchEnterpriseVo enterpriseSearchCondition, Integer pageNum,
			Integer pageSize, String sortField, String order);

	public List<Enterprise> searchEnterpriseForExport(
			SearchEnterpriseVo enterpriseSearchCondition, Integer pageNum,
			Integer pageSize, String sortField, String order);

	public List findEnterpriseByNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode);

	public Integer getCount(SearchEnterpriseVo searchVo);
}
