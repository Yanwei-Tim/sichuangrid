package com.tianque.baseInfo.scenicManage.scenicEquipment.service;

import java.util.List;

import com.tianque.baseInfo.scenicManage.scenicEquipment.domain.ScenicEquipment;
import com.tianque.baseInfo.scenicManage.scenicEquipment.vo.SearchScenicEquipmentVo;
import com.tianque.core.vo.PageInfo;

public interface ScenicEquipmentService {

	public ScenicEquipment addScenicEquipment(ScenicEquipment scenicEquipment);

	public ScenicEquipment getScenicEquipmentById(Long id);

	public void deleteScenicEquipmentByIds(Long[] ids);

	public ScenicEquipment updateScenicEquipment(ScenicEquipment scenicEquipment);

	public ScenicEquipment updateEmphasiseById(Long id, ScenicEquipment location);

	public List<ScenicEquipment> updateEmphasisByIdList(Long[] idList,
			ScenicEquipment location);

	public PageInfo<ScenicEquipment> searchScenicEquipmentForPage(
			Integer pageNum, Integer pageSize, String sortField, String order,
			SearchScenicEquipmentVo searchScenicEquipmentVo);

	public ScenicEquipment updateScenicEquipmentForImport(
			ScenicEquipment scenicEquipment);

	public Integer getCount(SearchScenicEquipmentVo searchScenicEquipmentVo);

}
