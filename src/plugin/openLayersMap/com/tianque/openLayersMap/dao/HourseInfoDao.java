package com.tianque.openLayersMap.dao;


import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.HourseInfo;
import com.tianque.openLayersMap.domian.vo.ScreenCoordinateVo;

public interface HourseInfoDao {
	/**
	 * 新增房屋信息
	 * 
	 * @param hourseInfo
	 * @return HourseInfo
	 */
	public HourseInfo addHourseInfo(HourseInfo hourseInfo);
	
	/**
	 * 修改房屋信息
	 * 
	 * @param hourseInfo
	 * @return HourseInfo
	 */
	public HourseInfo updateHourseInfo(HourseInfo hourseInfo);

	/**
	 * 通过ID查询房屋信息
	 * 
	 * @param id
	 * @return HourseInfo
	 */
	public HourseInfo getHourseInfoById(Long id);

	/**
	 * 通过ID删除房屋信息
	 * 
	 * @param id
	 */
	public void deleteHourseInfoById(Long id);

	/**
	 * 通过ID查询房屋信息和已绑定对象的数量
	 * 
	 * @param id
	 * @return HourseInfo
	 */
	public HourseInfo getHourseInfoAndBoundObjectNumById(Long id);

	/**
	 * 通过组织机构编号分页查询当前屏幕内的房屋信息
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<HourseInfo>
	 */
	public PageInfo<HourseInfo> findTwoDimensionMapHoursePageInfoByOrgCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 根据组织机构code统计房屋数据
	 * 
	 * @param orgInternalCode
	 * @return
	 */
	public Integer countHourseInfosTwoDimensionMapByOrgInternalCode(
			String orgInternalCode);

	/**
	 * 通过组织机构编码模糊查询某地图区域范围内的房屋集合对象
	 * 
	 * @param orgInternalCode
	 * @param screenCoordinateVo
	 * @return List<HourseInfo>
	 */
	public List<HourseInfo> findHourseInfosByOrgCodeAndScreenCoordinateVo(
			String orgInternalCode, ScreenCoordinateVo screenCoordinateVo);
	
	/**
	 * 根据组织机构id查找未绑定房屋
	 * @param orgId
	 * @return
	 */
	public List<HourseInfo> findUnboundHouseInfoByOrganizationId(Long orgId);
	
	/**
	 * 通过featureId查询房屋信息
	 * 
	 * @param featureId
	 * @return HourseInfo
	 */
	public HourseInfo getHourseInfoByFeatureId(String featureId);

}
