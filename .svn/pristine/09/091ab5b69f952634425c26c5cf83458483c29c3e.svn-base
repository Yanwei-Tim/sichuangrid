package com.tianque.baseInfo.buildDatas.dao;


import java.util.List;

import com.tianque.baseInfo.buildDatas.domain.BuildLayout;
import com.tianque.baseInfo.buildDatas.domain.BuildLayoutCell;

public interface BuildLayoutDao {
	/**
	 * 新增楼宇布局
	 * @param buildLayout
	 */
	public BuildLayout addBuildLayout(BuildLayout buildLayout);
	/**
	 * 修改楼宇布局
	 * @param buildLayout
	 */
	public BuildLayout updateBuildLayout(BuildLayout buildLayout);
	/**
	 * 删除楼宇布局
	 * @param buildId
	 */
	public void deleteBuildLayoutByBuildId(Long buildId);
	public void deleteBuildLayoutById(Long id);
	/**
	 * 获取楼宇布局
	 * @param buildId
	 * @return
	 */
	public BuildLayout getBuildLayoutByBuildId(Long buildId);
	public BuildLayout getBuildLayoutById(Long id);
	/**
	 * 获取所有拥有楼宇布局的楼宇的ID
	 */
	public List<Long> findBuildIds();
	/**
	 * 根据楼宇ID获取楼宇布局所有单元格信息
	 */
	public List<BuildLayoutCell> findBuildLayoutCellByBuildId(Long buildId);
	
	/**
	 * 根据楼宇编号和用户类型（13种业务类型）查找所在楼宇中的个数
	 * 
	 * @param builddatasid
	 * @param personType
	 * @return
	 */
	public Long countKeyPersonInBuildingByBuilddatasidAndPersonType(Long builddatasid,
			String personType, String shardCode);

	/**
	 * 查询房屋是否有相应的用户类型在里面
	 * 
	 * @param personType
	 * @param houseId
	 * @return
	 */
	public Boolean existKeyPersonInHouseByPersonTypeAndHouseId(String personType,
			Long houseId);
}
