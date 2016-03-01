package com.tianque.openLayersMap.service;

import java.util.Map;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;

/**
 * 二维地图 重点场所个性化的接口
 * 
 * @author jiangjinling
 * 
 */
public interface KeyPlaceService {

	/**
	 * 通过组织机构ID和类型分页查询未绑定重点场所数据
	 * 
	 * @param orgId
	 *            组织机构ID
	 * @param type
	 *            子类类型
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param queryStr
	 *            查询条件
	 * @return PageInfo<KeyPlaceInfoVo>
	 */
	public PageInfo<KeyPlaceInfoVo> findUnBoundKeyPlaceByOrgIdAndType(
			Long orgId, String type, Integer page, Integer rows, String sidx,
			String sord, String queryStr);

	/**
	 * 通过组织机构code和场所类型统计场所数据（主要应用于辖区分布）
	 * 
	 * @param orgInternalCode
	 *            组织机构code
	 * @param typeName
	 *            子类类型名
	 * @return Integer
	 */
	public Integer countTwoDimensionMapPageInfoByOrgInternalCodeAndTypeName(
			String orgInternalCode, String typeName);

	/**
	 * 根据经纬度坐标获取各类重点场所数量
	 * 
	 * @param centerLon
	 * @param centerLat
	 * @return
	 */
	public Map<String, Integer> countKeyPlacesByCenterLonLat(String centerLon,
			String centerLat, String gisType);

	/**
	 * 通过组织机构ID和类型分页查询已绑定的重点场所数据
	 * 
	 * @param orgId
	 *            组织机构ID
	 * @param typeName
	 *            子类类型名
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return PageInfo<KeyPlaceInfoVo>
	 */
	public PageInfo<KeyPlaceInfoVo> findBoundedKeyPalcesByOrgIdAndTypeName(
			Long orgId, Long buildDataId, String typeName, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 根据建筑物id统计场所数量
	 * 
	 * @param centerLon
	 * @param centerLat
	 * @return
	 */
	public Map<String, Integer> countKeyPlacesByHourseId(Long hourseId);

}
