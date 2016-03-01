package com.tianque.baseInfo.fPersonnel.dao;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.fPersonnel.domain.FPersonnel;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchFPersonnelVo;

public interface SearchFPersonnelDao {

	public PageInfo<FPersonnel> searchFPersonnel(
			SearchFPersonnelVo queryQopulation, int pageNum, int pageSize,
			String sortField, String order);

	public List<FPersonnel> searchFPersonnelForExport(
			SearchFPersonnelVo queryQopulation, Integer page, Integer rows,
			String sidx, String sord);

	public int getCountFPersonnelByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map);

	public Integer getCount(SearchFPersonnelVo personnelVo);
}
