package com.tianque.cache;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.redis.connection.RedisConnectionFactory;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.SystemUtilException;
import com.tianque.jms.msg.RedisCacheMsg;
import com.tianque.jms.sender.ActiveMQMessageSender;

public final class PageInfoCacheThreadLocal implements Serializable {
	private String module;
	private Long orgId;
	private Integer orgLevel;
	private Integer count;
	private CacheOperate operate;
	private UpdateType updateType;

	private BaseDomain baseDomain;

	private static ActiveMQMessageSender  activeMQMessageSender;

	private static Map<String, ?> cacheKeyMap = new HashMap<String, Object>();

	private static final ThreadLocal<List<PageInfoCacheThreadLocal>> cache = new ThreadLocal<List<PageInfoCacheThreadLocal>>();

	private PageInfoCacheThreadLocal(String module, Long orgId,
			Integer orgLevel, Integer count, CacheOperate operate,
			BaseDomain baseDomain) {
		this.module = module;
		this.orgId = orgId;
		this.orgLevel = orgLevel;
		this.count = count;
		this.operate = operate;
		this.baseDomain = baseDomain;
	}

	private PageInfoCacheThreadLocal(String module, Long orgId,
			Integer orgLevel, UpdateType updateType, BaseDomain baseDomain) {
		this.module = module;
		this.orgId = orgId;
		this.orgLevel = orgLevel;
		this.operate = CacheOperate.UPDATE;
		this.baseDomain = baseDomain;
		this.updateType = updateType;
	}

	private static void createPageInfoCacheThreadLocal(String module,
			Integer orgLevel, BaseDomain baseDomain, Integer count,
			CacheOperate operate) {
		PageInfoCacheThreadLocal pageInfoCacheThreadLocal = new PageInfoCacheThreadLocal(
				module, getOrgIdByBaseDomain(baseDomain), orgLevel, count,
				operate, baseDomain);
		String cacheKey = getCacheKeyToMap(pageInfoCacheThreadLocal);
		if (cacheKeyMap.containsKey(cacheKey)) {
			return;
		}
		List<PageInfoCacheThreadLocal> caches = cache.get();
		if (caches == null) {
			caches = new ArrayList<PageInfoCacheThreadLocal>();
		}
		caches.add(pageInfoCacheThreadLocal);
		cache.set(caches);
		cacheKeyMap.put(cacheKey, null);
	}

	private static void createPageInfoCacheThreadLocal(String module,
			Integer orgLevel, BaseDomain baseDomain, UpdateType updateType) {
		PageInfoCacheThreadLocal pageInfoCacheThreadLocal = new PageInfoCacheThreadLocal(
				module, getOrgIdByBaseDomain(baseDomain), orgLevel, updateType,
				baseDomain);
		List<PageInfoCacheThreadLocal> caches = cache.get();
		if (caches == null) {
			caches = new ArrayList<PageInfoCacheThreadLocal>();
		}
		caches.add(pageInfoCacheThreadLocal);
		cache.set(caches);
	}

	private static String getCacheKeyToMap(
			PageInfoCacheThreadLocal pageInfoCacheThreadLocal) {
		String splitStr = "_";
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(pageInfoCacheThreadLocal.getModule());
		stringBuffer.append(splitStr);
		stringBuffer.append(pageInfoCacheThreadLocal.getOperate());
		stringBuffer.append(splitStr);
		stringBuffer.append(pageInfoCacheThreadLocal.getOrgId());
		stringBuffer.append(splitStr);
		stringBuffer.append(pageInfoCacheThreadLocal.getBaseDomain().getId());
		return stringBuffer.toString();
	}

	private static Long getOrgIdByBaseDomain(BaseDomain baseDomain) {
		try {
			Class clazz = baseDomain.getClass();
			Method method = clazz.getMethod("getOrganization");
			Organization org = (Organization) method.invoke(baseDomain);
			if (org == null || org.getId() == null) {
				throw new SystemUtilException("组织机构为空");
			}
			return org.getId();
		} catch (Exception e) {
			throw new SystemUtilException("不支持的对象类型！" + baseDomain, e);
		}
	}

	/**
	 * 
	 * @param module
	 * @param orgId
	 * @param count
	 */
	public static void increment(String module, BaseDomain baseDomain,
			Integer count) {
		createPageInfoCacheThreadLocal(module, OrganizationLevel.TOWN,
				baseDomain, count, CacheOperate.INCREMENT);
	}

	public static void decrease(String module, BaseDomain baseDomain,
			Integer count) {
		createPageInfoCacheThreadLocal(module, OrganizationLevel.TOWN,
				baseDomain, count, CacheOperate.DECREASE);
	}

	/**
	 * 更新redis缓存
	 * 
	 * @param module
	 *            模块
	 * @param baseDomain
	 *            对象
	 * @param updateType
	 *            更新类型（基本信息，业务信息，地址信息）
	 */
	public static void update(String module, BaseDomain baseDomain,
			UpdateType updateType) {
		createPageInfoCacheThreadLocal(module, OrganizationLevel.TOWN,
				baseDomain, updateType);
	}

	public static void increment(String module, Integer orgLevel,
			BaseDomain baseDomain, Integer count) {
		createPageInfoCacheThreadLocal(module, orgLevel, baseDomain, count,
				CacheOperate.INCREMENT);
	}

	public static void decrease(String module, Integer orgLevel,
			BaseDomain baseDomain, Integer count) {
		createPageInfoCacheThreadLocal(module, orgLevel, baseDomain, count,
				CacheOperate.DECREASE);
	}

	public static void commit() throws Exception {
		List<PageInfoCacheThreadLocal> pageInfoCacheThreadLocals = cache.get();
		if (CollectionUtils.isEmpty(pageInfoCacheThreadLocals)) {
			return;
		}
		PageInfoCacheHelper pageInfoCacheHelper = (PageInfoCacheHelper) SpringBeanUtil
				.getBeanFromSpringByBeanName("pageInfoCacheHelper");
		for (PageInfoCacheThreadLocal pageInfoCacheThreadLocal : pageInfoCacheThreadLocals) {
			try {
				dealRedisCache(pageInfoCacheThreadLocal);
				switch (pageInfoCacheThreadLocal.getOperate()) {
				case INCREMENT:
					pageInfoCacheHelper.incrementCounter(
							pageInfoCacheThreadLocal.getModule(),
							pageInfoCacheThreadLocal.getOrgId(),
							pageInfoCacheThreadLocal.getOrgLevel(),
							pageInfoCacheThreadLocal.getCount());
					break;
				case DECREASE:
					pageInfoCacheHelper.decreaseCounter(
							pageInfoCacheThreadLocal.getModule(),
							pageInfoCacheThreadLocal.getOrgId(),
							pageInfoCacheThreadLocal.getOrgLevel(),
							pageInfoCacheThreadLocal.getCount());
					break;
				default:
					break;
				}
			} finally {
				cacheKeyMap.remove(getCacheKeyToMap(pageInfoCacheThreadLocal));
			}
		}
		cache.remove();
	}

	private static void dealRedisCache(
			PageInfoCacheThreadLocal pageInfoCacheThreadLocal) {
		if (RedisConnectionFactory.isUseRedis()) {
			getActiveMQMessageSender().send(
					new RedisCacheMsg(pageInfoCacheThreadLocal));
		}
	}

	public static ActiveMQMessageSender getActiveMQMessageSender() {
		if (activeMQMessageSender == null) {
			activeMQMessageSender = (ActiveMQMessageSender) SpringBeanUtil
					.getBeanFromSpringByBeanName("activeMQMessageSender");
		}
		return activeMQMessageSender;
	}

	public static void rollback() {
		cache.remove();
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public CacheOperate getOperate() {
		return operate;
	}

	public void setOperate(CacheOperate operate) {
		this.operate = operate;
	}

	public Integer getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}

	public BaseDomain getBaseDomain() {
		return baseDomain;
	}

	public void setBaseDomain(BaseDomain baseDomain) {
		this.baseDomain = baseDomain;
	}

	public UpdateType getUpdateType() {
		return updateType;
	}

	public void setUpdateType(UpdateType updateType) {
		this.updateType = updateType;
	}
}
