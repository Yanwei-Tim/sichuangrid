package com.tianque.openLayersMap.service;

import com.tianque.openLayersMap.domian.vo.GisBoundVo;

/**
 * 地图绑定-解绑
 * 
 * @date 2013-3-15
 * @param <T>
 */
public interface MapBindingManageService<T> {

	/**
	 * 二维地图绑定
	 * 
	 * @param ids
	 * @param lon
	 *            经度
	 * @param lat
	 *            纬度
	 * @param type
	 *            子类类型
	 * @param buildDataId
	 *            要绑定的建筑物id
	 * @return T
	 */
	public T boundTwoDimensionMap(String[] ids, String lon, String lat,
			String type, Long buildDataId, String gisType);

	public T boundTwoDimensionMap(GisBoundVo gisBoundVo, String type);

	/**
	 * 二维地图解除绑定
	 * 
	 * @param id
	 * @param type
	 *            子类类型
	 * @return T
	 */
	public T unBoundTwoDimensionMap(Long id, String type, Long orgId);
}
