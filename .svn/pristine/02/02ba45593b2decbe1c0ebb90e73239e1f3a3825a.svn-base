package com.tianque.baseInfo.qPersonnel.dao;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.qPersonnel.domain.QPersonnel;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchQPersonnelVo;

public interface SearchQPersonnelDao {

	public PageInfo<QPersonnel> searchQPersonnel(
			SearchQPersonnelVo queryQopulation, int pageNum, int pageSize,
			String sortField, String order);

	public List<QPersonnel> searchQPersonnelForExport(
			SearchQPersonnelVo queryQopulation, Integer page, Integer rows,
			String sidx, String sord);

	public int getCountQPersonnelByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map);

	public Integer getCount(SearchQPersonnelVo personnelVo);
}
