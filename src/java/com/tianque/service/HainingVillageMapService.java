package com.tianque.service;

import com.tianque.domain.HainingVillageMap;

public interface HainingVillageMapService {
	/**
	 * 通过departmentNo获取HainingVillageMap
	 * 
	 * @param departmentNo
	 * @return
	 */
	public HainingVillageMap getHainingVillageMapByDepartmentNo(String departmentNo);

	/**
	 * 新增
	 * 
	 * @param hainingVillageMap
	 * @return
	 */
	public HainingVillageMap addHainingVillageMap(HainingVillageMap hainingVillageMap);

	/**
	 * 修改
	 * 
	 * @param hainingVillageMap
	 * @return
	 */
	public HainingVillageMap updateHainingVillageMap(HainingVillageMap hainingVillageMap);

	/**
	 * 删除图片
	 * 
	 * @param hainingVillageMap
	 * @return
	 */
	public boolean deleteImg(HainingVillageMap hainingVillageMap);
}
