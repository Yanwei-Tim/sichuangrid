package com.tianque.newVillage.dao;

import java.util.List;

import com.tianque.newVillage.vo.NewVillageAssessmentVo;

public interface NewVillageAcceptanceDao {

	/***
	 * 查询所需的累计字段数据
	 * @param year
	 * @return
	 */
	public List<NewVillageAssessmentVo> findCumulativeNewVillageBasic(Integer year);
	
	/***
	 * 查询所需的最新数据字段
	 * @param year
	 * @return
	 */
	public List<NewVillageAssessmentVo> findcurrentNewVillageBasic(Integer year);
}
