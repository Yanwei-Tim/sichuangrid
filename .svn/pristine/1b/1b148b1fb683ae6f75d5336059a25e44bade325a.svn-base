package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ConstructionUnit;
import com.tianque.domain.vo.SearchConstructionUnitVo;

public interface SearchConstructionUnitDao {
	public PageInfo<ConstructionUnit> searchConstructionUnit(
			SearchConstructionUnitVo searchConstructionUnitVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public List<ConstructionUnit> searchConstructionUnitForExport(
			SearchConstructionUnitVo searchConstructionUnitVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

	public List findConstructionUnitNameAndPinyinAndOrgInternalCode(String name,
			String orgInternalCode);
}
