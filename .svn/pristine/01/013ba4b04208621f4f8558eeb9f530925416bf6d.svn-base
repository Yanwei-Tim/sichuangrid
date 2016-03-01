package com.tianque.baseInfo.scenicManage.scenicEquipment.dao;

import java.util.Date;

import com.tianque.baseInfo.scenicManage.scenicEquipment.domain.ScenicEquipment;
import com.tianque.baseInfo.scenicManage.scenicEquipment.vo.SearchScenicEquipmentVo;
import com.tianque.core.vo.PageInfo;

public interface ScenicEquipmentDao {

	public ScenicEquipment addScenicEquipment(ScenicEquipment scenicEquipment);

	public ScenicEquipment getScenicEquipmentById(Long id);

	public void deleteScenicEquipmentById(Long id);

	public ScenicEquipment updateScenicEquipment(ScenicEquipment scenicEquipment);

	public void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logoutReason, Date logoutDate);

	public PageInfo<ScenicEquipment> searchScenicEquipmentForPage(
			Integer pageNum, Integer pageSize, String sortField, String order,
			SearchScenicEquipmentVo searchScenicEquipmentVo);

	public Integer getCount(SearchScenicEquipmentVo searchScenicEquipmentVo);

}
