package com.tianque.baseInfo.emergencyShelter.dao;

import com.tianque.baseInfo.emergencyShelter.domain.EmergencyShelter;
import com.tianque.core.vo.PageInfo;

public interface EmergencyShelterDao {
	/**
	 * 新增
	 */
	public EmergencyShelter addEmergencyShelter(EmergencyShelter emergencyShelter);

	/**
	 * 根据id获取实有单位对象
	 */
	public EmergencyShelter getEmergencyShelterById(Long id);

	/**
	 * 删除
	 */
	public void deleteEmergencyShelter(Long id);

	/**
	 * 批量删除
	 */
	public void deleteEmergencyShelterByIds(String[] ids);

	/**
	 * 修改
	 */
	public EmergencyShelter updateEmergencyShelter(EmergencyShelter emergencyShelter);

	/**
	 * 查找数据用于显示
	 */
	public PageInfo<EmergencyShelter> findEmergencyShelterForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize, String sortField,
			String sord, Boolean logOut);

	/**
	 * 根据场所名称获取场所
	 */
	public EmergencyShelter getEmergencyShelterByNameAndOrganizationId(String name, Long orgId);

}
