package com.tianque.baseInfo.emergencyShelter.service;

import java.util.List;

import com.tianque.baseInfo.emergencyShelter.domain.EmergencyShelter;
import com.tianque.core.vo.PageInfo;

/**
 * 应急避难场所
 */
public interface EmergencyShelterService {
	/**
	 * 新增应急避难场所
	 */
	public EmergencyShelter addEmergencyShelter(EmergencyShelter emergencyShelter);

	/**
	 * 根据id获取应急避难场所对象
	 */
	public EmergencyShelter getEmergencyShelterById(Long id);

	/**
	 * 删除应急避难场所根据idList
	 */
	public void deleteEmergencyShelter(List<Long> idList);

	/**
	 * 修改应急避难场所
	 */
	public EmergencyShelter updateEmergencyShelter(EmergencyShelter emergencyShelter);

	/**
	 * 查找数据用于显示
	 */
	public PageInfo<EmergencyShelter> findEmergencyShelterForPageByOrgInternalCode(Long orgId,
			Integer pageNum, Integer pageSize, String sortField, String sord, Boolean logOut);

	/**
	 * 判断应急避难场所是否重复，根据单位名称
	 */
	public boolean hasDuplicateEmergencyShelter(Long orgId, String computerName, Long exceptedId);

	/**
	 * 修改应急避难场所，根据idList
	 */
	public List<EmergencyShelter> updateEmphasisByIdList(List<Long> idList,
			EmergencyShelter emergencyShelter);

}
