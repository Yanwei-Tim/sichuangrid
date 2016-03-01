
package com.tianque.baseInfo.enamePermissionCache.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.enamePermissionCache.dao.EnamePermissionCacheDao;
import com.tianque.baseInfo.enamePermissionCache.domain.EnamePermissionCache;
import com.tianque.core.base.AbstractBaseDao;

/**
 * @author weiminglong
 *2015年11月3日上午10:30:55
 */

@Repository("enamePermissionCacheDao")
public class EnamePermissionCacheDaoImpl extends AbstractBaseDao implements EnamePermissionCacheDao{

	
	@Override
	public void addEnamePermissionCache(EnamePermissionCache enamePermissionCache) {
		getSqlMapClientTemplate().insert(
				"enamePermissionCache.addEnamePermissionCache", enamePermissionCache);
	}

	@Override
	public  EnamePermissionCache getEnamePermissionCacheByKey(String cachekey) {
		return  (EnamePermissionCache) getSqlMapClientTemplate().queryForObject(
				"enamePermissionCache.getEnamePermissionCacheByKey",cachekey
				);
	}

	
	@Override
	public void deleteEnamePermissionCache(String cachekey) {
		getSqlMapClientTemplate().delete(
				"enamePermissionCache.deleteEnamePermissionCache", cachekey);
	}

	
	@Override
	public void updateEnamePermissionCache(
			String cachekey,String cacheValue,String updateUser,Date updateDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cachekey", cachekey);
		map.put("cacheValue", cacheValue);
		map.put("updateUser", updateUser);
		map.put("updateDate", updateDate);
		getSqlMapClientTemplate().update(
				"enamePermissionCache.updateEnamePermissionCache",map);
	}
	
	
}
