package com.tianque.dao;

import java.util.List;

import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOverseaPersonnelVo;

public interface SearchOverseaPersonnelDao {

	public PageInfo<OverseaPersonnel> searchOverseaPersonnel(
			SearchOverseaPersonnelVo searchOverseaPersonnelVo, int pageNum,
			int pageSize, String sortField, String order);

	public List<OverseaPersonnel> findOverseaPersonnelByNameAndPinyinAndOrgInternalCode(
			String name, String orgInternalCode);

	public PageInfo<OverseaPersonnel> fastSearchOverseaPersonnel(
			SearchOverseaPersonnelVo overseaPersonnelVo, int pageNum,
			int pageSize, String sortField, String order);

	public List<OverseaPersonnel> searchOverseaPersonnelForExport(
			SearchOverseaPersonnelVo overseaPersonnelCondition, Integer page,
			Integer rows, String sidx, String sord);

	public Integer getCount(SearchOverseaPersonnelVo searchOverseaPersonnelVo);

}
