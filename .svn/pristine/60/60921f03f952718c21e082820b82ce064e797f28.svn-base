package com.tianque.baseInfo.goodSamaritan.dao;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.goodSamaritan.domain.GoodSamaritan;
import com.tianque.baseInfo.goodSamaritan.domain.SearchGoodSamaritanVo;
import com.tianque.core.vo.PageInfo;

public interface SearchGoodSamaritanDao {
	public PageInfo<GoodSamaritan> searchGoodSamaritan(
			SearchGoodSamaritanVo queryQopulation, int pageNum, int pageSize,
			String sortField, String order);

	public List<GoodSamaritan> searchGoodSamaritanForExport(
			SearchGoodSamaritanVo queryQopulation, Integer page, Integer rows,
			String sidx, String sord);

	public int getCountGoodSamaritanByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map);

	public Integer getCount(SearchGoodSamaritanVo personnelVo);
}
