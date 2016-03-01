package com.tianque.baseInfo.mPersonnel.dao;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.mPersonnel.domain.MPersonnel;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchMPersonnelVo;

public interface SearchMPersonnelDao {

	public PageInfo<MPersonnel> searchMPersonnel(
			SearchMPersonnelVo queryQopulation, int pageNum, int pageSize,
			String sortField, String order);

	public List<MPersonnel> searchMPersonnelForExport(
			SearchMPersonnelVo queryQopulation, Integer page, Integer rows,
			String sidx, String sord);

	public int getCountMPersonnelByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map);

	public Integer getCount(SearchMPersonnelVo personnelVo);
}
