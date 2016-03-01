package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ConstructionUnit;

public interface ConstructionUnitService {

	public ConstructionUnit addConstructionUnit(ConstructionUnit constructionUnit);

	public ConstructionUnit getConstructionUnitById(Long id);

	public ConstructionUnit updateConstructionUnit(ConstructionUnit constructionUnit);

	public boolean deleteConstructionUnitById(Long id);

	public PageInfo<ConstructionUnit> findConstructionUnitForPage(Long orgId, Integer pageSize,
			Integer pageNum, String sidx, String sord, Long isEmphasis);

	public ConstructionUnit updateConstructionUnitByName(String name, Long id,
			ConstructionUnit domain);

	boolean hasDuplicateConstructionProjectName(Long ownerOrgId, String ConstructionProjectName,
			Long exceptedId);

	public ConstructionUnit updateConstructionUnitById(ConstructionUnit constructionUnit);

	public List<Long> deleteConstructionUnitById(List<Long> placeIds);

	public boolean hasRelatePlacce(List<Long> placeIds);

	public void shiftConstructionUnit(Long[] ids, Long orgId);
}
