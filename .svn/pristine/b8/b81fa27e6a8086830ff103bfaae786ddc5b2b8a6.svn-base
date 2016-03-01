package com.tianque.openLayersMap.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;

/**
 * 楼宇个性化操作service接口
 * 
 * @date 2013-3-18
 */
public interface BuildDataService {

	/**
	 * 通过地图房屋Id查询楼宇信息
	 * 
	 * @param hourseId
	 * @return BuildDataInfoVo
	 */
	public BuildDataInfoVo getBuildDataInfoByBuildingid(Long hourseId);

	/**
	 * 通过orgId、queryStr查询未绑定楼宇
	 * 
	 * @param orgId
	 * @param queryStr
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return PageInfo<BuildDataInfoVo>
	 */
	public PageInfo<BuildDataInfoVo> findUnBoundBuilddatasByStr(
			String queryStr, Integer pageNum, Integer pageSize,
			String sortField, String order, Long orgId);

	/**
	 * 根据经纬度坐标获取建筑物信息
	 * 
	 * @param centerLon
	 * @param centerLat
	 * @return
	 */
	public BuildDataInfoVo getBuildDataInfoVoByCenterLonLat(String centerLon,
			String centerLat, String gisType);

	/**
	 * 根据建筑物id得到楼宇信息
	 */
	public BuildDataInfoVo getBuildDataInfoVoByHourseInfoId(
			BuildDataInfoVo buildDataInfoVo, Long orgId);
}
