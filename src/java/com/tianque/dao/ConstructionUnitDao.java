package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ConstructionUnit;

public interface ConstructionUnitDao {

	public ConstructionUnit addConstructionUnit(ConstructionUnit constructionUnit);

	public ConstructionUnit getConstructionUnitById(Long id);

	public ConstructionUnit updateConstructionUnit(ConstructionUnit constructionUnit);

	public int deleteConstructionUnitById(Long id);

	public PageInfo<ConstructionUnit> findConstructionUnitForPageByOrgInternalCode(
			String orgInternalCode, Integer pageSize, Integer pageNum, String sidx, String sord,
			Long isEmphasis);

	public ConstructionUnit getConstructionUnitByProjectName(String name, Long id);

	public ConstructionUnit updateConstructionUnitById(ConstructionUnit constructionUnit);
}
