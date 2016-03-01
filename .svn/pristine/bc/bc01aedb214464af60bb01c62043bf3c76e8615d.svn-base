package com.tianque.baseInfo.dangerousGoodsPractitioner.dao;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.dangerousGoodsPractitioner.domain.DangerousGoodsPractitioner;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchDangerousGoodsPractitioner;

public interface SearchDangerousGoodsPractitionerDao {

	public PageInfo<DangerousGoodsPractitioner> searchDangerousGoodsPractitioner(
			SearchDangerousGoodsPractitioner queryQopulation, int pageNum,
			int pageSize, String sortField, String order);

	public List<DangerousGoodsPractitioner> searchDangerousGoodsPractitionerForExport(
			SearchDangerousGoodsPractitioner queryQopulation, Integer page,
			Integer rows, String sidx, String sord);

	public int getCountDangerousGoodsPractitionerByOrgInternalCodeAndMap(
			String orgInternalCode, Map<String, Object> map);

	public Integer getCount(SearchDangerousGoodsPractitioner practitionerVo);
}
