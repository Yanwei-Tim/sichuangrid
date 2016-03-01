package com.tianque.service;

import java.util.List;

import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchDangerousGoodsPractitioner;

public interface SearchDangerousGoodsPractitionerService {

	public PageInfo<DangerousGoodsPractitioner> searchDangerousGoodsPractitioner(
			SearchDangerousGoodsPractitioner queryQopulation, int pageNum,
			int pageSize, String sortField, String order);

	public List<DangerousGoodsPractitioner> searchDangerousGoodsPractitionerForExport(
			SearchDangerousGoodsPractitioner queryQopulation, Integer page,
			Integer rows, String sidx, String sord);

	public String[][] getExportPopertyArray();

	public Integer getCount(SearchDangerousGoodsPractitioner practitionerVo);

}
