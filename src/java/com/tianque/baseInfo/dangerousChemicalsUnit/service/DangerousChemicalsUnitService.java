package com.tianque.baseInfo.dangerousChemicalsUnit.service;

import com.tianque.baseInfo.dangerousChemicalsUnit.domain.DangerousChemicalsUnit;
import com.tianque.core.vo.PageInfo;

public interface DangerousChemicalsUnitService {
	public PageInfo<DangerousChemicalsUnit> findDangerChemUnitForPageByOrgInternalCode(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord, Boolean isEmphasis);

	public DangerousChemicalsUnit getDangerousChemicalsUnitById(Long id);

	public DangerousChemicalsUnit addDangerousChemicalsUnit(
			DangerousChemicalsUnit dangerousChemicalsUnit);

	public DangerousChemicalsUnit updateDangerousChemicalsUnit(
			DangerousChemicalsUnit dangerousChemicalsUnit);

	public void deleteDangerousChemicalsUnitByIds(Long[] ids);

	public void updateEmphasiseByIds(Long[] ids, DangerousChemicalsUnit location);

	public DangerousChemicalsUnit updateDangerousChemicalsUnitByUnitNameAndOrgId(String unitName,
			Long orgId, DangerousChemicalsUnit domain);

	public DangerousChemicalsUnit existDangerousChemicalsUnit(String unitName, Long orgId);

	/**
	 * 查询相同层级下是否存在相同的单位名称
	 * 
	 * @param orgId
	 *        组织id
	 * @param exceptedId
	 *        新增id
	 * @param unitName
	 *        单位名称
	 * @return false 不存在 true 存在
	 */
	public boolean hasDangerousChemicalsUnit(Long orgId, Long exceptedId, String unitName);

	/**
	 * 数据管理用
	 * 
	 * @param orgId
	 * @param placeName
	 * @return
	 */
	public DangerousChemicalsUnit hasDuplicateDangerousChemicalsUnit(Long orgId, String unitName);
}
