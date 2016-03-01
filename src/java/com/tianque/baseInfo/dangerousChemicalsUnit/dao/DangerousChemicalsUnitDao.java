package com.tianque.baseInfo.dangerousChemicalsUnit.dao;

import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.core.vo.PageInfo;

public interface DangerousChemicalsUnitDao {
	public PageInfo<DangerousChemicalsUnit> findDangerChemUnitForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize, String sidx, String sord,
			Boolean isEmphasis);

	public DangerousChemicalsUnit getDangerousChemicalsUnitById(Long id);

	public DangerousChemicalsUnit addDangerousChemicalsUnit(
			DangerousChemicalsUnit dangerousChemicalsUnit);

	public DangerousChemicalsUnit updateDangerousChemicalsUnit(
			DangerousChemicalsUnit dangerousChemicalsUnit);

	public void deleteDangerousChemicalsUnitById(Long id);

	public void updateEmphasiseById(Long id, Boolean isEmphasis, String logOutReason);

	DangerousChemicalsUnit getDangerousChemicalsUnitByUnitName(String unitName, Long orgId);

	/**
	 * 通过名字和orgId查找危险化学品单位
	 * 
	 * @param placeName
	 * @param id
	 * @return
	 */
	public DangerousChemicalsUnit getDangerousChemicalsUnitByUnitNameAndOrgId(String unitName,
			Long id);
}
