package com.tianque.baseInfo.enamePermissionCache.service;

import java.util.Date;
import java.util.List;

import com.tianque.baseInfo.enamePermissionCache.domain.EnamePermissionCache;

public interface EnamePermissionCacheService{

	/***
	 * @title 把用户权限增加到缓存中
	 * @author weiminglong
	 * @Description 角色权限缓存
	 * @date 2015-11-2 下午04:31:00
	 * 
	 */
	
	/***
	 * 增加用户权限缓存
	 * @param enamePermissionCache
	 */
	public void addEnamePermissionCache(EnamePermissionCache enamePermissionCache);
	

	/***
	 * 根据cachekey得到用户权限缓存
	 * @param cachekey
	 * @return
	 */
	public EnamePermissionCache getEnamePermissionCacheByKey(String cachekey);
	
	/***
	 * 根据cachekey得到用户权限缓存
	 * @param cachekey
	 * @return
	 */
	public List<String> getEnamePermissionCacheListByCacheKey(String cachekey);
	
	/***
	 * 修改用户权限缓存
	 * @param cachekey
	 * @param cacheValue
	 * @param updateUser
	 * @param updateDate
	 */
	public void updateEnamePermissionCache(String cachekey,String cacheValue,String updateUser,Date updateDate);
	
	
	/***
	 * 根据cachekey删除用户权限缓存
	 * @param cachekey
	 */
	public void deleteEnamePermissionCache(String cachekey);
	
	/**
	 * 手动执行job方法
	 */
	public void handleEnamePermissionCache();

}
