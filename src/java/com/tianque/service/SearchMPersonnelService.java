package com.tianque.service;

import java.util.List;

import com.tianque.baseInfo.mPersonnel.domain.MPersonnel;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchMPersonnelVo;

public interface SearchMPersonnelService {

	public PageInfo<MPersonnel> searchMPersonnel(
			SearchMPersonnelVo queryQopulation, int pageNum, int pageSize,
			String sortField, String order);

	public List<MPersonnel> searchMPersonnelForExport(
			SearchMPersonnelVo queryQopulation, Integer page, Integer rows,
			String sidx, String sord);

	public String[][] getExportPopertyArray();

	public Integer getCount(SearchMPersonnelVo personnelVo);

}
