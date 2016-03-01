package com.tianque.service;

import java.util.List;

import com.tianque.baseInfo.fPersonnel.domain.FPersonnel;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchFPersonnelVo;

public interface SearchFPersonnelService {

	public PageInfo<FPersonnel> searchFPersonnel(
			SearchFPersonnelVo queryQopulation, int pageNum, int pageSize,
			String sortField, String order);

	public List<FPersonnel> searchFPersonnelForExport(
			SearchFPersonnelVo queryQopulation, Integer page, Integer rows,
			String sidx, String sord);

	public String[][] getExportPopertyArray();

	public Integer getCount(SearchFPersonnelVo personnelVo);

}
