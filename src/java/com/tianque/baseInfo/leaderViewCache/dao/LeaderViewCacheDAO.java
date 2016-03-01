package com.tianque.baseInfo.leaderViewCache.dao;

import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.leaderViewCache.domain.LeaderViewCache;

@DynamicIbatisDAO(value = "LeaderViewCacheDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("LeaderViewCacheDAO")
public interface LeaderViewCacheDAO {

	/**
	 * 
	 * @Title: addLeaderViewCahce
	 * @Description: TODO增加领导视图缓存
	 * @param @param leaderViewCache 设定文件
	 * @return void 返回类型
	 * @author wanggz
	 * @date 2014-11-5 下午05:31:00
	 */
	public void addLeaderViewCahce(LeaderViewCache leaderViewCache);

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
	 * 
	 * @Title: deleteLeaderViewCacheByCacheKey
	 * @Description: TODO通过cacheKey删除领导视图缓存数据
	 * @param @param map 设定文件
	 * @return void 返回类型
	 * @author wanggz
	 * @date 2014-11-6 上午08:44:55
	 */
	public void deleteLeaderViewCacheByCacheKey(String cacheKey);

	public void updateLeaderViewCahce(LeaderViewCache leaderViewCache);

	/**
	 * 清除所有缓存数据
	 * 
	 * @param cacheType
	 */
	public void deleteLeaderViewCache(Integer cacheType);
}
