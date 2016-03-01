package com.tianque.baseInfo.newSocietyOrganizations.dao;

import java.util.List;

import com.tianque.baseInfo.newSocietyOrganizations.domain.NewSocietyOrganizations;
import com.tianque.baseInfo.newSocietyOrganizations.domain.SearchNewSocietyOrganizationsVo;
import com.tianque.core.vo.PageInfo;

public interface SearchNewSocietyOrganizationsDao {

	public PageInfo<NewSocietyOrganizations> fastSearchNewSocietyOrganizations(
			SearchNewSocietyOrganizationsVo condition, int pageNum,
			int pageSize, String sortField, String order);

	public PageInfo<NewSocietyOrganizations> findNewSocietyOrganizationsByQueryCondition(
			SearchNewSocietyOrganizationsVo condition, int pageNum,
			int pageSize, String sortField, String order);

	public List<NewSocietyOrganizations> searchNewSocietyOrganizationsForExport(
			SearchNewSocietyOrganizationsVo condition, Integer pageNum,
			Integer pageSize, String sortField, String order);

	public Integer getCount(SearchNewSocietyOrganizationsVo condition);

}
