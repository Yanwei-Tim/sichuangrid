package com.tianque.baseInfo.enamePermissionCache.service.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.enamePermissionCache.dao.EnamePermissionCacheDao;
import com.tianque.baseInfo.enamePermissionCache.domain.EnamePermissionCache;
import com.tianque.baseInfo.enamePermissionCache.service.EnamePermissionCacheService;
import com.tianque.core.cache.service.CacheService;
import com.tianque.domain.Role;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.vo.RoleVo;

@Service("enamePermissionCacheService")
@Transactional
public class EnamePermissionCacheServiceImpl implements EnamePermissionCacheService {

	@Autowired
	private EnamePermissionCacheDao enamePermissionCacheDao;

	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private PermissionService permissionService;
	
	private static int EXPRIED = 36000;
	
	
	private static final String KEY="ROLE_";
	private static final String HAS_PERMISSION_=KEY+"HAS_PERMISSION_";

	@Override
	public void addEnamePermissionCache(EnamePermissionCache enamePermissionCache) {
		try {
			enamePermissionCacheDao.addEnamePermissionCache(enamePermissionCache);
			List<String> enames=enamePermissionCache.getDatas(String.class);
			cacheService.set(enamePermissionCache.getCacheKey(),EXPRIED,enames);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类EnamePermissionCacheServiceImpl的方法addEnamePermissionCache出错:",
					"新增用户权限缓存数据失败", e);
		}
	}
	
	@Override
	public EnamePermissionCache getEnamePermissionCacheByKey(String cachekey){
		try {
			return enamePermissionCacheDao.getEnamePermissionCacheByKey(cachekey);
			
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类EnamePermissionCacheServiceImpl的方法getEnamePermissionCacheByKey出错:",
					"获取用户权限缓存数据失败", e);
		}
		
	}
	
	@Override
	public List<String> getEnamePermissionCacheListByCacheKey(String cachekey){
		List<String> strs = (List<String>) JSONArray.toCollection(
				    JSONArray.fromObject(cacheService.get(cachekey)), String.class);
		if(strs == null){
			try {
				EnamePermissionCache enamePermissionCache = enamePermissionCacheDao.getEnamePermissionCacheByKey(cachekey);
				if(enamePermissionCache != null){
			    strs = enamePermissionCache.getDatas(String.class);
			    cacheService.set(enamePermissionCache.getCacheKey(),EXPRIED,strs);	
				}
				
			} catch (Exception e) {
				throw new ServiceValidationException(
						"类EnamePermissionCacheServiceImpl的方法getEnamePermissionCacheListByCacheKey出错:",
						"获取用户权限缓存数据失败", e);
			}
			
		}
		return strs;
	}
	
	
	@Override
	public void deleteEnamePermissionCache(String cachekey){
		
		try {
			enamePermissionCacheDao.deleteEnamePermissionCache(cachekey);
			cacheService.remove(cachekey);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类EnamePermissionCacheServiceImpl的方法deleteEnamePermissionCache出错:",
					"删除用户权限缓存数据失败", e);
		}
	}

	@Override
	public void handleEnamePermissionCache() {
		List<Role> roles = permissionService.findAllRoles();
		for(int i=0;i<roles.size();i++){
			Long roleId = roles.get(i).getId();
			List<Long> addPermissionIds = permissionService.getRolePermissionByRoleId(roleId);
			List<String> addPermissionName = permissionService.getRolePermissionEnameByRoleId(addPermissionIds);
			String  cachekey = HAS_PERMISSION_+roleId;
			deleteEnamePermissionCache(cachekey);
		    addEnamePermissionCache(new EnamePermissionCache<RoleVo>(cachekey,addPermissionName));
		    cacheService.remove(cachekey);
		    cacheService.set(cachekey,EXPRIED,addPermissionName);
		} 
    	
	}

	@Override
	public void updateEnamePermissionCache(
			String cachekey,String cacheValue,String updateUser,Date updateDate) {
		
		try {
			enamePermissionCacheDao.updateEnamePermissionCache(cachekey,cacheValue,updateUser,updateDate);
			cacheService.set(cachekey,EXPRIED,cacheValue);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类EnamePermissionCacheServiceImpl的方法updateEnamePermissionCache出错:",
					"修改用户权限缓存数据失败", e);
		}
		
	}
	
}
