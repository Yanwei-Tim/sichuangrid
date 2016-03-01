package com.tianque.baseInfo.enamePermissionCache.dao;

import java.util.Date;
import java.util.List;

import com.tianque.baseInfo.enamePermissionCache.domain.EnamePermissionCache;

public interface EnamePermissionCacheDao {
	
	/***
	 * @author weiminglong
	 * @param enamePermissionCache
	 */

	/***
	 * 增加用户权限缓存
	 * @param enamePermissionCache
	 */
	public void addEnamePermissionCache(EnamePermissionCache enamePermissionCache);
	
	/***
	 * 根据cachekey获得用户权限缓存
	 * @param cachekey
	 * @return
	 */
	public EnamePermissionCache getEnamePermissionCacheByKey(String cachekey);
	
	/***
	 * 删除用户权限缓存
	 * @param cachekey
	 */
	public void deleteEnamePermissionCache(String cachekey);
	
	
	/****
	 * 修改用户权限缓存
	 * @param cachekey
	 * @param cacheValue
	 * @param updateUser
	 * @param updateDate
	 */
	public void updateEnamePermissionCache(String cachekey,String cacheValue,String updateUser,Date updateDate);
	
}
