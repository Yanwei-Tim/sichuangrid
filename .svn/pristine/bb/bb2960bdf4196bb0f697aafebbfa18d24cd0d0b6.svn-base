package com.tianque.core.redis.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.cache.CacheNameSpaceEnum;
import com.tianque.cache.PageInfoCacheHelper;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.redis.connection.RedisConnectionFactory;
import com.tianque.core.redis.core.RedisTemplate;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.plugin.transfer.util.TransferUtil;

/**
 * @ClassName: RedisUtils
 * @Description: redis工具类
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年1月9日 上午9:26:10
 */
public class RedisDefaultPageUtils<T extends BaseDomain> {

	private static Logger logger = LoggerFactory
			.getLogger(RedisDefaultPageUtils.class);
	private static RedisTemplate redisTemplate;
	private static PageInfoCacheHelper pageCountCacheHelper;

	private static CacheNameSpaceEnum namespace = CacheNameSpaceEnum.PEOPLE_DEFAULTPAGE;

	/**
	 * @Description: 获取每页记录数。如果redis开启，并且配置的redis获取数据量大于0
	 *               查询第一页（默认进入页面）时返回配置的每页记录数 否则返回默认页数
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param pageNum
	 *            当前页
	 * @param defaultPageSize
	 *            每页记录数
	 * @return 缓存记录数
	 * @throws
	 */
	public static int getRedisPageSize(int pageNum, int defaultPageSize) {
		return RedisConnectionFactory.isUseRedis()
				&& GridProperties.REDIS_DEFAULT_PAGE_SIZE > 0 && pageNum == 1 ? GridProperties.REDIS_DEFAULT_PAGE_SIZE
				: defaultPageSize;
	}

	/**
	 * @Description: 获取缓存集合
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param moduleName
	 *            模块名
	 * @param orgId
	 *            组织机构id
	 * @param pageNum
	 *            当前页
	 * @param pageSize
	 *            每页记录数
	 * @return 缓存集合
	 * @throws
	 */
	public static List<?> getPageInfo(String moduleName, Long orgId,
			Integer pageNum, Integer pageSize, Class classzs) {
		if (!RedisConnectionFactory.isUseRedis()) {
			return null;
		}
		try {
			String key = getCacheKey(moduleName, orgId);
			long size = getRedisTemplate().getListSize(key, namespace);
			if (size != 0 && size < pageSize) {
				getRedisTemplate().del(key, namespace);
				return null;
			}
			// 超过总页数，则返回空，查询数据库
			if (pageNum > size / pageSize) {
				return null;
			}
			return getRedisTemplate().getListForPage(key, namespace, pageNum,
					pageSize, classzs);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * @throws Exception
	 * @Description: 缓存页面集合
	 * @author wangxiaohu wsmalltiger@163.com
	 * @param pageNum
	 *            当前页
	 * @param moduleName
	 *            模块名称
	 * @param orgId
	 *            组织机构id
	 * @param pageList
	 *            集合
	 * @throws
	 */
	public static <T extends BaseDomain> void setPageInfo(int pageNum,
			int pageSize, String moduleName, Long orgId, List<T> pageList)
			throws Exception {
		if (RedisConnectionFactory.isUseRedis()
				&& !CollectionUtils.isEmpty(pageList) && pageNum == 1
				&& pageList.size() >= GridProperties.REDIS_DEFAULT_PAGE_SIZE) {
			getRedisTemplate().createList(getCacheKey(moduleName, orgId),
					namespace, pageList);
		}
		for (int i = pageSize; pageList != null && i < pageList.size(); i++) {
			pageList.remove(i--);
		}
	}

	public static void addToList(String module, Long orgId, Integer orgLevel,
			BaseDomain baseDomain) throws Exception {
		if (baseDomain != null && baseDomain.getId() != null) {
			List<Long> orgList = getOrgIdForList(orgId, orgLevel);
			for (Long id : orgList) {
				String key = getCacheKey(module, id);
				long listSize = getRedisTemplate().getListSize(key, namespace);
				if (listSize == 0L) {
					continue;
				}
				getRedisTemplate().addListFirst(key, namespace, baseDomain);
				delCacheWhenOutSize(key, baseDomain.getClass());
			}
		}
	}

	private static void delCacheWhenOutSize(String key, Class classzs)
			throws Exception {
		long size = getRedisTemplate().getListSize(key, namespace);
		if (size > GridProperties.REDIS_DEFAULT_PAGE_SIZE) {
			getRedisTemplate().delListLast(key, namespace, classzs);
		}
	}

	public static void delFromList(String module, Long orgId, Integer orgLevel,
			BaseDomain baseDomain) throws Exception {
		if (baseDomain != null && baseDomain.getId() != null) {
			List<Long> orgList = getOrgIdForList(orgId, orgLevel);
			for (Long id : orgList) {
				String key = getCacheKey(module, id);
				Integer index = getBaseDomainIndex(key, baseDomain.getId(),
						baseDomain.getClass());
				if (index != null) {
					getRedisTemplate().removeList(key, namespace, index);
				}
			}
		}
	}

	public static void clearList(String modelName, Long orgId) throws Exception {
		getRedisTemplate().del(getCacheKey(modelName, orgId), namespace);
	}

	public static void updateList(String module, Long orgId, Integer orgLevel,
			BaseDomain baseDomain) throws Exception {
		if (baseDomain != null && baseDomain.getId() != null) {
			List<Long> orgList = getOrgIdForList(orgId, orgLevel);
			for (Long id : orgList) {
				String key = getCacheKey(module, id);
				Integer index = getBaseDomainIndex(key, baseDomain.getId(),
						baseDomain.getClass());
				if (index != null) {
					getRedisTemplate().updateList(key, namespace, baseDomain,
							index);
				}
			}
		}
	}

	public static void addClearListKey(String module, Long orgId) {
		getRedisTemplate().addClearListKey(getCacheKey(module, orgId),
				namespace);
	}

	private static Integer getBaseDomainIndex(String key, Long domainId,
			Class classzs) throws Exception {
		List<BaseDomain> list = (List<BaseDomain>) getRedisTemplate().getList(
				key, namespace, classzs);
		for (int i = 0; list != null && i < list.size(); i++) {
			BaseDomain baseDomain = list.get(i);
			if (baseDomain != null && baseDomain.getId() != null
					&& baseDomain.getId().longValue() == domainId.longValue()) {
				return i;
			}
		}
		return null;
	}

	private static List<Long> getOrgIdForList(Long orgId, Integer orgLevel) {
		List<Long> orgIds = new ArrayList<Long>();
		getPageInfoCacheHelper().initOrgIds(orgId, orgLevel, orgIds);
		return orgIds;
	}

	private static RedisTemplate getRedisTemplate() {
		if (redisTemplate == null) {
			redisTemplate = (RedisTemplate) SpringBeanUtil
					.getBeanFromSpringByBeanName("redisTemplate");
		}
		return redisTemplate;
	}

	public static String getCacheKey(String moduleName, Long orgId) {
		return getPageInfoCacheHelper().getKey(moduleName, orgId);
	}

	private static PageInfoCacheHelper getPageInfoCacheHelper() {
		if (pageCountCacheHelper == null) {
			pageCountCacheHelper = (PageInfoCacheHelper) SpringBeanUtil
					.getBeanFromSpringByBeanName("pageInfoCacheHelper");
		}
		return pageCountCacheHelper;
	}

	public static People getPeopleByPopulationTypeAndId(String populationType,
			Long id, String modelName, Long orgId) {
		People people = null;
		if (!RedisConnectionFactory.isUseRedis()) {
			people = new People();
			Organization organization = new Organization();
			organization.setId(orgId);
			people.setOrganization(organization);
			return people;
		}
		String methodName = "get" + populationType + "ById";
		populationType = StringUtil.firstCharLowCase(populationType);
		try {
			// PopulationProccessorService populationProccessorService =
			// (PopulationProccessorService) SpringBeanUtil
			// .getBeanFromSpringByBeanName(populationType + "Service");
			// Method[] methods = populationProccessorService.getClass()
			// .getMethods();
			// boolean matchMethod = false;
			// for (Method method : methods) {
			// Class[] paramClasses = method.getParameterTypes();
			// if (methodName.toUpperCase().equals(
			// method.getName().toUpperCase())
			// && paramClasses != null && paramClasses.length == 1) {
			// people = (People) method.invoke(
			// populationProccessorService, id);
			// matchMethod = true;
			// break;
			// }
			// }
			// if (!matchMethod) {
			// throw new RuntimeException("关注时同步至缓存失败，未找到对应方法[" + methodName
			// + "]！");
			// }
			// 转移的时候，根据ID获取
			people = TransferUtil.getPeopleByPopulationTypeAndId(
					populationType, id);
			if (people == null || people.getId() == null) {
				people = new People();
				Organization organization = new Organization();
				organization.setId(orgId);
				people.setOrganization(organization);
				return people;
			}
		} catch (Exception e) {
			logger.error("关注时同步至缓存失败，未找到对应方法[" + methodName + "]！", e);
			try {
				RedisDefaultPageUtils.clearList(modelName, orgId);
			} catch (Exception e1) {
				String key = RedisDefaultPageUtils
						.getCacheKey(modelName, orgId);
				getRedisTemplate().addClearListKey(key, namespace);
			}
		}
		return people;
	}
}
