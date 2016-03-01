package com.tianque.openLayersMap.service;

import java.util.List;

import com.tianque.openLayersMap.domian.HourseInfo;

/**
 * 二维地图房屋service接口
 * @date 2013-3-15
 */
public interface HourseInfoService {
	/**
	 * 新增房屋信息
	 * 
	 * @param hourseInfo
	 * @return
	 */
	public HourseInfo addHourseInfo(HourseInfo hourseInfo, String gisType);

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
	 * 通过id查询房屋信息
	 * @param featureId
	 * @return
	 */
	public HourseInfo getHourseInfoById(Long id);

	/**
	 * 根据组织机构id查找未绑定房屋
	 * @param orgId
	 * @return
	 */
	public List<HourseInfo> findUnboundHouseInfoByOrganizationId(Long orgId);
	/**
	 * 通过featureId查询房屋信息
	 * @param featureId
	 * @return
	 */
	public HourseInfo getHourseInfoByFeatureId(String featureId);
	
}
