package com.tianque.baseInfo.leaderViewCache.service;

import java.util.List;

import com.tianque.baseInfo.leaderViewCache.domain.LeaderViewCache;
import com.tianque.core.cache.service.CacheService;

public interface LeaderViewCacheService<T> {

	/**
	 * 
	 * @Title: addLeaderViewCahce
	 * @Description: TODO增加领导视图缓存
	 * @param @param leaderViewCache 设定文件
	 * @return void 返回类型
	 * @author wanggz
	 * @date 2014-11-5 下午05:31:00
	 */
	public void addLeaderViewCahce(LeaderViewCache leaderViewCache,
			Class<T> clazz);

	public void updateLeaderViewCahce(LeaderViewCache leaderViewCache,
			Class<T> clazz);

	public void addLeaderViewCahce(LeaderViewCache leaderViewCache,
			Class<T> clazz, int expried);

	public void updateLeaderViewCahce(LeaderViewCache leaderViewCache,
			Class<T> clazz, int expried);

	/**
	 * 
	 * @Title: getLeaderViewCacheByCacheKey
	 * @Description: TODO通过cacheKey获取领导视图缓存数据
	 * @param @param map
	 * @param @return 设定文件
	 * @return LeaderViewCache 返回类型
	 * @author wanggz
	 * @date 2014-11-5 下午05:31:20
	 */
	public LeaderViewCache getLeaderViewCacheByCacheKey(String cacheKey);

	/**
	 * 给不跑job的领导视图用
	 * 
	 * @param nameSpace
	 * @param cacheKey
	 * @param clazz
	 * @return
	 */
	public List<T> getDatasByCacheKey(String nameSpace, String cacheKey,
			Class<T> clazz);

	/**
	 * 给跑job的用
	 * 
	 * @param nameSpace
	 * @param cacheKey
	 * @param clazz
	 * @return
	 */
	public List<T> getDatasByCacheKeyForJob(String nameSpace, String cacheKey,
			Class<T> clazz);

	/**
	 * 
	 * @Title: deleteLeaderViewCacheByCacheKey
	 * @Description: TODO通过cacheKey删除领导视图缓存数据
	 * @param @param map 设定文件
	 * @return void 返回类型
	 * @author wanggz
	 * @date 2014-11-6 上午08:44:55
	 */
	public void deleteLeaderViewCacheByCacheKey(String cacheKey);

	/**
	 * 
	 * @Title: addAndDeleteCacheByKey
	 * @Description: TODO新增同时删除之前key的cache数据
	 * @param @param orgId
	 * @param @param moduleName
	 * @param @param invalidateTime
	 * @param @param cacheKey
	 * @param @param cacheValue 设定文件
	 * @return void 返回类型
	 * @author wanggz
	 * @date 2014-11-6 上午08:52:07
	 */
	public void addOrUpdateCacheByKey(String nameSpace,
			LeaderViewCache leaderViewCache, Class<T> clazz);

	public void addOrUpdateCacheByKey(String nameSpace,
			LeaderViewCache leaderViewCache, Class<T> clazz, int expried);

	public CacheService getCacheService();

	/**
	 * 清除缓存表的数据（当传入0L时删除的是除领导视图历史月份数据外所有的数据）
	 * 
	 * @param cacheType
	 */

	public void deleteLeaderViewCache(Integer cacheType);

}
