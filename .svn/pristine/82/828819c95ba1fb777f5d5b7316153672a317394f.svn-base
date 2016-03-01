package com.tianque.core.redis.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisCommands;

import com.tianque.cache.CacheNameSpaceEnum;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.redis.connection.JedisSentinelShardPool;
import com.tianque.core.redis.utils.RedisCacheJsonUtils;
import com.tianque.core.redis.utils.RedisExecutor;
import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.BusinessValidationException;

/**
 * @ClassName: RedisTemplate
 * @Description: redis操作模板
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年1月8日 上午9:06:02
 */
@Component("redisTemplate")
public class RedisTemplateImpl implements RedisTemplate<BaseDomain> {
	//@Autowired
	//private RedisConnectionFactory connectionFactory;
	@Autowired
	private JedisSentinelShardPool JedisSentinelShardPool;

	@Override
	public long del(final String key, final CacheNameSpaceEnum namespace)
			throws Exception {
		return (Long) JedisSentinelShardPool.execute(new RedisExecutor() {

			@Override
			public Long execute(JedisCommands jedis) throws Exception {
				String cacheKey = dealKey(key, namespace);
				Integer index = getStringValue(CACHE_INDEX_KEY_SUFFIX,
						namespace, key);
				if (index != null) {
					removeList(CACHE_INDEX_KEY_SUFFIX, namespace, index);
				}
				return jedis.del(cacheKey);
			}
		});
	}

	@Override
	public void createList(final String key,
			final CacheNameSpaceEnum namespace, final List<BaseDomain> list)
			throws Exception {
		JedisSentinelShardPool.execute(new RedisExecutor() {

			@Override
			public Object execute(JedisCommands jedis) throws Exception {
				String cacheKey = dealKey(key, namespace);
				jedis.rpush(dealKey(CACHE_INDEX_KEY_SUFFIX, namespace), key);
				jedis.del(cacheKey);
				for (int i = 0; jedis != null && list != null
						&& i < list.size(); i++) {
					BaseDomain value = list.get(i);
					if (value != null) {
						jedis.rpush(cacheKey, dealValue(value));
					}
				}
				return null;
			}
		});
	}

	@Override
	public List<BaseDomain> getList(String key,
			final CacheNameSpaceEnum namespace, final Class classzs)
			throws Exception {
		return getList(key, namespace, 0, 0, true, classzs);
	}

	@Override
	public List<BaseDomain> getListForPage(String key,
			final CacheNameSpaceEnum namespace, int pageNum, int pageSize,
			final Class classzs) throws Exception {
		return getList(key, namespace, pageNum, pageSize, false, classzs);
	}

	@Override
	public long getListSize(final String key, final CacheNameSpaceEnum namespace)
			throws Exception {
		return (Long) JedisSentinelShardPool.execute(new RedisExecutor() {

			@Override
			public Long execute(JedisCommands jedis) {
				return jedis.llen(dealKey(key, namespace));
			}
		});
	}

	private List<BaseDomain> getList(final String key,
			final CacheNameSpaceEnum namespace, final long pageNum,
			final long pageSize, final boolean isAll, final Class classzs)
			throws Exception {
		return (List<BaseDomain>) JedisSentinelShardPool
				.executeRead(new RedisExecutor() {

					@Override
					public List<Object> execute(JedisCommands jedis)
							throws Exception {
						long start = 0, size = getListSize(key, namespace), end = size;
						if (!isAll) {
							long newPageNum = pageNum, newPageSize = pageSize;
							newPageNum = newPageNum < 1 ? 1 : newPageNum;
							newPageSize = newPageSize < 1 ? 1 : newPageSize;
							long maxPage = size % newPageSize == 0 ? size
									/ newPageSize : size / newPageSize + 1;
							newPageNum = newPageNum > maxPage ? maxPage
									: newPageNum;
							start = (newPageNum - 1) * newPageSize;
							end = newPageNum * newPageSize;
							end = end > size ? size : end;
						}
						List<Object> returnList = new ArrayList<Object>();
						List<String> results = jedis.lrange(
								dealKey(key, namespace), start, end - 1);
						for (String result : results) {
							returnList.add(dealReturnValue(result, classzs));
						}
						return returnList;
					}
				});
	}

	@Override
	public void addListFirst(final String key,
			final CacheNameSpaceEnum namespace, final Object value)
			throws Exception {
		validateCacheObject(value);
		JedisSentinelShardPool.execute(new RedisExecutor() {

			@Override
			public Object execute(JedisCommands jedis) throws Exception {
				jedis.lpush(dealKey(key, namespace), dealValue(value));
				return null;
			}
		});
	}

	@Override
	public void addListLast(final String key,
			final CacheNameSpaceEnum namespace, final Object value)
			throws Exception {
		validateCacheObject(value);
		JedisSentinelShardPool.execute(new RedisExecutor() {

			@Override
			public Object execute(JedisCommands jedis) throws Exception {
				jedis.rpush(dealKey(key, namespace), dealValue(value));
				return null;
			}
		});
	}

	@Override
	public void removeList(final String key,
			final CacheNameSpaceEnum namespace, final int... indexs)
			throws Exception {
		JedisSentinelShardPool.execute(new RedisExecutor() {

			@Override
			public Object execute(JedisCommands jedis) {
				List<Integer> indexList = new ArrayList<Integer>();
				for (int i = 0; i < indexs.length; i++) {
					if (!indexList.contains(indexs[i])) {
						indexList.add(indexs[i]);
					}
				}
				Collections.sort(indexList, new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return o1 - o2;
					}
				});
				String cacheKey = dealKey(key, namespace);
				for (int i = 0; i < indexList.size(); i++) {
					String delObj = jedis.lindex(cacheKey, indexList.get(i) - i);
					if (delObj != null) {
						jedis.lrem(cacheKey, 1, delObj);
					}
				}
				return null;
			}
		});
	}

	@Override
	public boolean set(final String key, final CacheNameSpaceEnum namespace,
			final Object value) throws Exception {
		validateCacheObject(value);
		return (Boolean) JedisSentinelShardPool.execute(new RedisExecutor() {

			@Override
			public Boolean execute(JedisCommands jedis) throws Exception {
				jedis.rpush(dealKey(CACHE_INDEX_KEY_SUFFIX, namespace), key);
				String result = jedis.set(dealKey(key, namespace),
						dealValue(value));
				return REDIS_SET_SUCCESS_RESULT.equals(result);
			}
		});
	}

	@Override
	public boolean set(final String key, final CacheNameSpaceEnum namespace,
			final int seconds, final Object value) throws Exception {
		validateCacheObject(value);
		return (Boolean) JedisSentinelShardPool.execute(new RedisExecutor() {

			@Override
			public Boolean execute(JedisCommands jedis) throws Exception {
				String result = jedis.setex(dealKey(key, namespace), seconds,
						dealValue(value));
				return REDIS_SET_SUCCESS_RESULT.equals(result);
			}
		});
	}

	@Override
	public Long setWhenNotExists(final String key,
			final CacheNameSpaceEnum namespace, final Object value)
			throws Exception {
		validateCacheObject(value);
		return (Long) JedisSentinelShardPool.execute(new RedisExecutor() {

			@Override
			public Long execute(JedisCommands jedis) throws Exception {
				Long isExists = jedis.setnx(dealKey(key, namespace),
						dealValue(value));
				if (!isExists.equals(0L)) {
					jedis.rpush(dealKey(CACHE_INDEX_KEY_SUFFIX, namespace), key);
				}
				return isExists;
			}
		});
	}

	@Override
	public String get(final String key, final CacheNameSpaceEnum namespace)
			throws Exception {
		return (String) get(key, namespace, null);
	}

	@Override
	public Object get(final String key, final CacheNameSpaceEnum namespace,
			final Class classzs) throws Exception {
		return JedisSentinelShardPool.executeRead(new RedisExecutor() {

			@Override
			public Object execute(JedisCommands jedis) {
				String result = jedis.get(dealKey(key, namespace));
				return dealReturnValue(result, classzs);
			}
		});
	}

	@Override
	public Object delListLast(final String key,
			final CacheNameSpaceEnum namespace, final Class classzs)
			throws Exception {
		return JedisSentinelShardPool.execute(new RedisExecutor() {

			@Override
			public Object execute(JedisCommands jedis) {
				String delObject = jedis.rpop(dealKey(key, namespace));
				return dealReturnValue(delObject, classzs);
			}
		});
	}

	@Override
	public boolean updateList(final String key,
			final CacheNameSpaceEnum namespace, final Object value,
			final int index) throws Exception {
		validateCacheObject(value);
		return (Boolean) JedisSentinelShardPool.execute(new RedisExecutor() {

			@Override
			public Boolean execute(JedisCommands jedis) throws Exception {
				String result = jedis.lset(dealKey(key, namespace), index,
						dealValue(value));
				return REDIS_SET_SUCCESS_RESULT.equals(result);
			}
		});
	}

	/*public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}*/

	private void validateCacheObject(Object value) {
		if (value == null) {
			throw new BusinessValidationException("不能缓存空对象!");
		}
	}

	private String dealKey(final String key, CacheNameSpaceEnum namespace) {
		namespace = namespace == null ? CacheNameSpaceEnum.DEFAULT : namespace;
		return namespace.toString()
				+ (StringUtil.isStringAvaliable(key) ? key
						: "REDIS_DEFAULT_KEY");
	}

	private String dealValue(Object value) throws Exception {
		if (value == null) {
			throw new BusinessValidationException("缓存对象不能为空！");
		}
		return RedisCacheJsonUtils.toJson(value);
	}

	private Object dealReturnValue(String result, Class classzs) {
		return RedisCacheJsonUtils.fromJson(result, classzs);
	}

	private Integer getStringValue(String key, CacheNameSpaceEnum namespace,
			String value) throws Exception {
		List<BaseDomain> list = getList(key, namespace, null);
		for (int i = 0; list != null && i < list.size(); i++) {
			Object object = list.get(i);
			if (object != null && object.toString().equals(value)) {
				return i;
			}
		}
		return null;
	}

	@Override
	public void addClearListKey(String key, CacheNameSpaceEnum namespace) {
		JedisSentinelShardPool.addClearKey(dealKey(key, namespace));
	}
}
