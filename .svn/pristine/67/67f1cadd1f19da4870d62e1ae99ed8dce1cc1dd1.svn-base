package com.tianque.core.redis.connection;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import com.tianque.core.redis.utils.RedisExecutor;
import com.tianque.core.util.GridProperties;
import com.tianque.exception.base.SystemUtilException;

/**
 * @ClassName: RedisConnectionFactory
 * @Description: redis 链接工厂
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2015年1月8日 上午10:23:37
 */
public class RedisConnectionFactory {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private JedisPoolConfig poolConfig;
	private String address;
	private int port;
	private int timeout = 3000;
	private JedisPool jedisPool;
	private long reConnection = 3000;
	private Jedis jedis = null;
	private static boolean threadIsStart = false;

	private static List<String> clearListKey = new ArrayList<String>();

	public void addClearListKey(String key) {
		if (!clearListKey.contains(key) && isUseRedis()) {
			clearListKey.add(key);
			dealClearList();
		}
	}

	public static List<String> getClearListKey() {
		return clearListKey;
	}

	public void init() {
		if (isUseRedis()) {
			testRedisConnection();
			logger.error("jedis 实例已启动！");
		}
	}

	private void dealClearList() {
		if (!threadIsStart) {
			threadIsStart = true;
			new Thread(new Runnable() {

				@Override
				public void run() {
					testRedisConnection();
					logger.error("jedis链接恢复，数据一致性处理成功！");
					threadIsStart = false;
				}
			}).start();
		}
	}

	private void testRedisConnection() {
		try {
			execute(new RedisExecutor() {
				@Override
				public Object execute(JedisCommands jedis) {
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("jedis[" + address + ":" + port + "]链接失败，尝试重连...");
			try {
				Thread.sleep(reConnection < 3000 ? 3000 : reConnection);
			} catch (InterruptedException e1) {
				logger.error("", e1);
			}
			testRedisConnection();
		}
	}

	public JedisPool getJedisPool() {
		if (jedisPool == null) {
			jedisPool = new JedisPool(poolConfig, address, port, timeout);
		}
		return jedisPool;
	}

	public Object execute(RedisExecutor executor) throws Exception {
		Jedis jedis = null;
		JedisPool pool = null;
		try {
			pool = getJedisPool();
			jedis = pool.getResource();
			List<String> clearCacheKey = getClearListKey();
			for (int i = 0; i < clearCacheKey.size(); i++) {
				String key = clearCacheKey.get(i);
				jedis.del(key);
				clearCacheKey.remove(i--);
			}
			return executor.execute(jedis);
		} catch (JedisConnectionException e) {
			returnBrokenResource(pool, jedis);
			throw new SystemUtilException("jedis 初始化失败，请确保服务[" + address + ":"
					+ port + "]正常开启。", e);
		} catch (Exception e) {
			returnBrokenResource(pool, jedis);
			throw new SystemUtilException("jedis 发生异常", e);
		} finally {
			returnResource(pool, jedis);
		}
	}

	public void returnResource(JedisPool pool, Jedis redis) {
		if (pool != null && redis != null) {
			pool.returnResource(redis);
		}
	}

	public void returnBrokenResource(JedisPool pool, Jedis redis) {
		if (pool != null && redis != null) {
			pool.returnBrokenResource(redis);
		}
	}

	public void destroy() {
		if (jedisPool != null) {
			returnResource(jedisPool, jedis);
			jedisPool.destroy();
		}
	}

	public void setPoolConfig(JedisPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setReConnection(long reConnection) {
		this.reConnection = reConnection;
	}

	public static boolean isUseRedis() {
		return GridProperties.REDIS_OPEN != null
				&& GridProperties.REDIS_OPEN.booleanValue();
	}
}
