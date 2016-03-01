package com.tianque.baseInfo.goodSamaritan.service;

import java.util.List;

import com.tianque.baseInfo.goodSamaritan.domain.GoodSamaritan;
import com.tianque.baseInfo.goodSamaritan.domain.SearchGoodSamaritanVo;
import com.tianque.core.vo.PageInfo;

public interface SearchGoodSamaritanService {
	public PageInfo<GoodSamaritan> searchGoodSamaritan(
			SearchGoodSamaritanVo queryQopulation, int pageNum, int pageSize,
			String sortField, String order);

	public List<GoodSamaritan> searchGoodSamaritanForExport(
			SearchGoodSamaritanVo queryQopulation, Integer page, Integer rows,
			String sidx, String sord);

	public String[][] getExportPopertyArray();

	public Integer getCount(SearchGoodSamaritanVo personnelVo);
}
