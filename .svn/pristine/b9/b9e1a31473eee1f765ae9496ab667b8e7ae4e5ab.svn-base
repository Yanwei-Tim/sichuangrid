package com.tianque.baseInfo.leaderViewCache.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.leaderViewCache.dao.LeaderViewCacheDAO;
import com.tianque.baseInfo.leaderViewCache.domain.LeaderViewCache;
import com.tianque.baseInfo.leaderViewCache.service.LeaderViewCacheService;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;

@Service("leaderViewCacheService")
@Transactional
public class LeaderViewCacheServiceImpl implements LeaderViewCacheService {

	@Autowired
	private LeaderViewCacheDAO leaderViewCacheDAO;

	@Autowired
	private CacheService cacheService;

	private static int EXPRIED = 36000;

	@Override
	public void addLeaderViewCahce(LeaderViewCache leaderViewCache, Class clazz) {
		addLeaderViewCahce(leaderViewCache, clazz, EXPRIED);
	}

	@Override
	public LeaderViewCache getLeaderViewCacheByCacheKey(String cacheKey) {
		try {
			return leaderViewCacheDAO.getLeaderViewCacheByCacheKey(cacheKey);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类LeaderViewCacheServiceImpl的方法getLeaderViewCacheByCacheKey出错:",
					"获取领导视图缓存数据失败", e);
		}
	}

	/**
	 * 
	 * @Title: deleteLeaderViewCacheByCacheKey
	 * @Description: TODO通过cacheKey删除领导视图缓存数据
	 * @param @param map 设定文件
	 * @return void 返回类型
	 * @author wanggz
	 * @date 2014-11-6 上午08:44:55
	 */
	public void deleteLeaderViewCacheByCacheKey(String cacheKey) {
		try {
			leaderViewCacheDAO.deleteLeaderViewCacheByCacheKey(cacheKey);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类LeaderViewCacheServiceImpl的方法deleteLeaderViewCacheByCacheKey出错:",
					"删除领导视图缓存数据失败", e);
		}
	}

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
			LeaderViewCache leaderViewCache, Class clazz) {
		addOrUpdateCacheByKey(nameSpace, leaderViewCache, clazz, EXPRIED);
	}

	@Override
	public void addOrUpdateCacheByKey(String nameSpace,
			LeaderViewCache leaderViewCache, Class clazz, int expried) {
		try {
			if (leaderViewCacheDAO.getLeaderViewCacheByCacheKey(leaderViewCache
					.getCacheKey()) == null) {
				leaderViewCacheDAO.addLeaderViewCahce(leaderViewCache);
			} else {
				leaderViewCacheDAO.updateLeaderViewCahce(leaderViewCache);
			}
			cacheService.set(nameSpace, leaderViewCache.getCacheKey(), expried,
					leaderViewCache.getDatas(clazz));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类LeaderViewCacheServiceImpl的方法addAndDeleteCacheByKey出错:",
					"新增或更新缓存数据出错：", e);
		}
	}

	@Override
	public void updateLeaderViewCahce(LeaderViewCache leaderViewCache,
			Class clazz) {
		updateLeaderViewCahce(leaderViewCache, clazz, EXPRIED);
	}

	@Override
	public void addLeaderViewCahce(LeaderViewCache leaderViewCache,
			Class clazz, int expried) {
		try {
			leaderViewCacheDAO.addLeaderViewCahce(leaderViewCache);
			cacheService.set(leaderViewCache.getCacheKey(), EXPRIED,
					leaderViewCache.getDatas(clazz));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类LeaderViewCacheServiceImpl的方法addLeaderViewCahce出错:",
					"新增领导视图缓存数据失败", e);
		}
	}

	@Override
	public void updateLeaderViewCahce(LeaderViewCache leaderViewCache,
			Class clazz, int expried) {
		try {
			leaderViewCacheDAO.updateLeaderViewCahce(leaderViewCache);
			cacheService.set(leaderViewCache.getCacheKey(), expried,
					leaderViewCache.getDatas(clazz));
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类LeaderViewCacheServiceImpl的方法addAndDeleteCacheByKey出错:",
					"更新缓存数据出错：", e);
		}

	}

	@Override
	public List getDatasByCacheKeyForJob(String nameSpace, String cacheKey,
			Class clazz) {
		List datas = (List) cacheService.get(nameSpace, cacheKey);
		if (datas == null) {
			LeaderViewCache leaderViewCache = leaderViewCacheDAO
					.getLeaderViewCacheByCacheKey(cacheKey);
			// 只要缓存表中的数据存在 不判断其是否过期
			if (leaderViewCache != null) {
				return leaderViewCache.getDatas(clazz);
			}
		}
		return datas;
	}

	@Override
	public List getDatasByCacheKey(String nameSpace, String cacheKey,
			Class clazz) {
		List datas = (List) cacheService.get(nameSpace, cacheKey);
		if (datas == null) {
			LeaderViewCache leaderViewCache = leaderViewCacheDAO
					.getLeaderViewCacheByCacheKey(cacheKey);
			// 判断其是否过期
			if (leaderViewCache != null
					&& (CalendarUtil.transferStringDateToLong(
							"yyyy-MM-dd HH:mm:ss", 0) < leaderViewCache
							.getInvalidateTime())) {
				return leaderViewCache.getDatas(clazz);
			}
		}
		return datas;
	}

	@Override
	public CacheService getCacheService() {
		return cacheService;
	}

	@Override
	public void deleteLeaderViewCache(Integer cacheType) {
		if (cacheType == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			leaderViewCacheDAO.deleteLeaderViewCache(cacheType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类LeaderViewCacheServiceImpl的方法deleteLeaderViewCache出错:",
					"根据类型删除缓存数据出错：", e);
		}
	}

}
