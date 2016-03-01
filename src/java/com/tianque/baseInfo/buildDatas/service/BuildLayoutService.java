package com.tianque.baseInfo.buildDatas.service;

import java.util.List;

import com.tianque.baseInfo.buildDatas.domain.BuildLayout;
import com.tianque.baseInfo.buildDatas.domain.Builddatas;
import com.tianque.baseInfo.buildDatas.domain.vo.LayoutTagVo;

public interface BuildLayoutService {
	/**
	 * 新增楼宇布局
	 * @param buildLayout
	 * @param builddatas
	 */
	public void addBuildLayout(BuildLayout buildLayout,Builddatas builddatas);
	/**
	 * 修改楼宇布局
	 * @param buildLayout
	 */
	public void updateBuildLayout(BuildLayout buildLayout);
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
	/**
	 * 获取所有房屋、重点人员类型的颜色等数据
	 * 
	 * @param long1
	 */
	public List<Object> findBuildLayoutTypeData(Long builddatasid, Long orgId);

	/** 在给定一些的房屋id中，查找居住人员中有符合给定人员类型的房屋id */
	public List<Long> findHouseIdByPersonTypeAndHouseIds(String personType,
			String[] split);

	public List<LayoutTagVo> findLayoutAllTags(Long orgId, Long builddatasId);
	/***
	 * 因为修改表结构，修改布局的数据存储
	 */
	public void changeLayoutData();
}
