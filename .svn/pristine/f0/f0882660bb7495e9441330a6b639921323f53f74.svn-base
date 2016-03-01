package com.tianque.baseInfo.laborEmploymentUnit.dao;

import com.tianque.baseInfo.laborEmploymentUnit.domain.LaborEmploymentUnit;
import com.tianque.core.vo.PageInfo;

public interface LaborEmploymentUnitDao {
	/**
	 * 新增
	 */
	public LaborEmploymentUnit addLaborEmploymentUnit(LaborEmploymentUnit laborEmploymentUnit);

	/**
	 * 根据id获取实有单位对象
	 */
	public LaborEmploymentUnit getLaborEmploymentUnitById(Long id);

	/**
	 * 删除
	 */
	public void deleteLaborEmploymentUnit(Long id);

	/**
	 * 修改
	 */
	public LaborEmploymentUnit updateLaborEmploymentUnit(LaborEmploymentUnit laborEmploymentUnit);

	/**
	 * 更新业务数据
	 */
	public LaborEmploymentUnit updateBusinessData(LaborEmploymentUnit laborEmploymentUnit);

	/**
	 * 查找数据用于显示
	 */
	public PageInfo<LaborEmploymentUnit> findLaborEmploymentUnitForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize, String sortField,
			String sord, Boolean logOut);

	public LaborEmploymentUnit getLaborEmploymentUnitByCompanyNameAndOrganizationId(
			String companyName, Long orgId);

}
