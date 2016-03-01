package com.tianque.baseInfo.laborEmploymentUnit.service;

import java.util.List;

import com.tianque.baseInfo.laborEmploymentUnit.domain.LaborEmploymentUnit;
import com.tianque.core.vo.PageInfo;

public interface LaborEmploymentUnitService {
	/**
	 * 新增劳动用工单位
	 * 
	 * @param laborEmploymentUnit
	 * @return laborEmploymentUnit
	 */
	public LaborEmploymentUnit addLaborEmploymentUnit(LaborEmploymentUnit laborEmploymentUnit);

	/**
	 * 根据id获取劳动用工单位对象
	 * 
	 * @param id
	 * @return laborEmploymentUnit
	 */
	public LaborEmploymentUnit getLaborEmploymentUnitById(Long id);

	/**
	 * 删除劳动用工单位根据idList
	 * 
	 * @param idList0
	 */
	public void deleteLaborEmploymentUnit(List<Long> idList);

	/**
	 * 修改劳动用工单位
	 * 
	 * @param laborEmploymentUnit
	 * @return laborEmploymentUnit
	 */
	public LaborEmploymentUnit updateLaborEmploymentUnit(LaborEmploymentUnit laborEmploymentUnit);

	public LaborEmploymentUnit updateBusinessData(LaborEmploymentUnit laborEmploymentUnit);

	/**
	 * 查找数据用于显示
	 */
	public PageInfo<LaborEmploymentUnit> findLaborEmploymentUnitForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sortField, String sord,
			Boolean logOut);

	/**
	 * 判断劳动用工单位是否重复，根据单位名称
	 * 
	 * @param orgId
	 * @param computerName
	 * @param exceptedId
	 * @return boolean
	 */
	public boolean hasDuplicateLaborEmploymentUnit(Long orgId, String computerName, Long exceptedId);

	/**
	 * 修改劳动用工单位，根据idList
	 * 
	 * @param idList
	 * @param isEmphasis
	 * @return
	 */
	public List<LaborEmploymentUnit> updateEmphasisByIdList(List<Long> idList,
			LaborEmploymentUnit laborEmploymentUnit);

}
