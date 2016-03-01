package com.tianque.service;

import java.util.List;

import com.tianque.baseInfo.qPersonnel.domain.QPersonnel;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchQPersonnelVo;

public interface SearchQPersonnelService {

	public PageInfo<QPersonnel> searchQPersonnel(
			SearchQPersonnelVo queryQopulation, int pageNum, int pageSize,
			String sortField, String order);

	public List<QPersonnel> searchQPersonnelForExport(
			SearchQPersonnelVo queryQopulation, Integer page, Integer rows,
			String sidx, String sord);

	public String[][] getExportPopertyArray();

	public Integer getCount(SearchQPersonnelVo personnelVo);

}
