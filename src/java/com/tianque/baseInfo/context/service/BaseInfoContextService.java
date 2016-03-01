package com.tianque.baseInfo.context.service;

public interface BaseInfoContextService {
	/**
	 * 用于基础信息——新增页面，根据页面中的contextId获取当前页面的populationId
	 * 
	 * @param contextId
	 *        上下文Id
	 * @return
	 */
	public Long getActualPopulationIdFromContext(String contextId);

	public Long getPopulationIdFromContext(String contextId);

	/**
	 * 用于基础信息——新增页面，根据页面中的contextId获取当前页面中保存在缓存中的populationId
	 * 
	 * @param contextId
	 *        上下文Id
	 * @return
	 */
	public String getActualPopulationCacheIdFromContext(String contextId);

	public String getPopulationCacheIdFromContext(String contextId);

	/**
	 * 用于基础信息——新增页面，根据页面中的contextId获取当前页面的houseId
	 * 
	 * @param contextId
	 *        上下文Id
	 * @return
	 */
	public Long getHouseIdFromContext(String contextId);

	/**
	 * 用于基础信息——新增页面，根据页面中的contextId保存当前页面的populationId
	 * 
	 * @param contextId
	 *        上下文Id
	 * @return
	 */
	public void putActualPopulationIdToContext(String contextId, Long populationId);

	public void putPopulationIdToContext(String contextId, Long populationId);

	/**
	 * 用于基础信息——新增页面，根据页面中的contextId保存当前页面的保存在缓存中的populationId
	 * 
	 * @param contextId
	 *        上下文Id
	 * @return
	 */
	public void putActualPopulationCacheIdToContext(String contextId, String populationCacheId);

	public void putPopulationCacheIdToContext(String contextId, String populationCacheId);

	/**
	 * 用于基础信息——新增页面，根据页面中的contextId保存当前页面的houseId
	 * 
	 * @param contextId
	 *        上下文Id
	 * @return
	 */
	public void putHouseIdToContext(String contextId, Long houseId);

	public void removeHouseIdFromContext(String contextId);

	public void removeActualPopulationIdFromContext(String contextId);

}
