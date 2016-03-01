package com.tianque.core.redis.connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

import com.tianque.core.redis.utils.RedisExecutor;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StringUtil;
import com.tianque.exception.base.SystemUtilException;

/**
 * 支持读写分离和容灾，使用Sentinel监控redis集群的主备状态，master写入数据，slave读取数据，如果没有slave读写都用master，
 * 集群中发生宕机，会自动更新读连接,支持多个slave
 * 
 * @author zeng_limin@163.com
 * @since 2015年2月4日 上午10:52:54
 *
 */
public class JedisSentinelShardPool {

	private static final Logger logger = LoggerFactory
			.getLogger(JedisSentinelShardPool.class);

	private ShardedJedisPool shardedJedisPool;

	private JedisSentinelPool jedisSentinelPool;

	private GenericObjectPoolConfig poolConfig;

	private int timeout = Protocol.DEFAULT_TIMEOUT;

	private String password;

	private int database = Protocol.DEFAULT_DATABASE;

	private Set<String> sentinels;

	private Thread clearThread;

	private boolean noSlave;

	private static LinkedBlockingQueue<String> clearKeyQueue = new LinkedBlockingQueue<String>();

	private Set<SlaveListener> slaveListeners = new HashSet<SlaveListener>();

	public JedisSentinelShardPool(String masterName, Set<String> sentinels,
			final GenericObjectPoolConfig poolConfig) {
		this(masterName, sentinels, poolConfig, Protocol.DEFAULT_TIMEOUT, null,
				Protocol.DEFAULT_DATABASE);
	}

	public JedisSentinelShardPool(String masterName, Set<String> sentinels) {
		this(masterName, sentinels, new GenericObjectPoolConfig(),
				Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE);
	}

	public JedisSentinelShardPool(String masterName, Set<String> sentinels,
			String password) {
		this(masterName, sentinels, new GenericObjectPoolConfig(),
				Protocol.DEFAULT_TIMEOUT, password);
	}

	public JedisSentinelShardPool(String masterName, Set<String> sentinels,
			final GenericObjectPoolConfig poolConfig, int timeout,
			final String password) {
		this(masterName, sentinels, poolConfig, timeout, password,
				Protocol.DEFAULT_DATABASE);
	}

	public JedisSentinelShardPool(String masterName, Set<String> sentinels,
			final GenericObjectPoolConfig poolConfig, final int timeout) {
		this(masterName, sentinels, poolConfig, timeout, null,
				Protocol.DEFAULT_DATABASE);
	}

	public JedisSentinelShardPool(String masterName, Set<String> sentinels,
			final GenericObjectPoolConfig poolConfig, final String password) {
		this(masterName, sentinels, poolConfig, Protocol.DEFAULT_TIMEOUT,
				password);
	}

	public JedisSentinelShardPool(String masterName, Set<String> sentinels,
			final GenericObjectPoolConfig poolConfig, int timeout,
			final String password, final int database) {
		this.poolConfig = poolConfig;
		this.sentinels = sentinels;
		this.timeout = timeout;
		this.password = StringUtil.isStringAvaliable(password) ? password
				: null;
		this.database = database;
		initShardedJedisPool(masterName);
		listeners(masterName);
		startClear();
	}

	private void startClear() {
		clearThread = new Thread() {
			public void run() {
				//移除需要清除的数据
				while (true) {
					try {
						final String clearKey = clearKeyQueue.take();
						execute(new RedisExecutor() {
							@Override
							public Object execute(JedisCommands jedis)
									throws Exception {
								return jedis.del(clearKey);
							}
						});
					} catch (Exception e) {
						//logger.error(e.getMessage(), e);
					}
				}
			};
		};
		clearThread.start();
	}

	public Jedis getWriteSource() {
		return jedisSentinelPool.getResource();
	}

	public ShardedJedis getReadSource() {
		return shardedJedisPool.getResource();
	}

	public Object execute(RedisExecutor executor) throws Exception {
		Jedis jedis = null;
		try {
			jedis = jedisSentinelPool.getResource();
			return executor.execute(jedis);
		} catch (JedisConnectionException e) {
			jedisSentinelPool.returnBrokenResource(jedis);
			throw new SystemUtilException("jedis 初始化失败", e);
		} catch (Exception e) {
			if (jedis != null)
				jedisSentinelPool.returnBrokenResource(jedis);
			throw new SystemUtilException("jedis 发生异常", e);
		} finally {
			if (jedis != null)
				jedisSentinelPool.returnResource(jedis);
		}
	}

	public Object executeWrite(RedisExecutor executor) throws Exception {
		return execute(executor);
	}

	public Object executeRead(RedisExecutor executor) throws Exception {
		if (noSlave) {
			return execute(executor);
		} else {
			ShardedJedis jedis = null;
			try {
				jedis = shardedJedisPool.getResource();
				return executor.execute(jedis);
			} catch (JedisConnectionException e) {
				shardedJedisPool.returnBrokenResource(jedis);
				throw new SystemUtilException("jedis 初始化失败", e);
			} catch (Exception e) {
				if (jedis != null)
					shardedJedisPool.returnBrokenResource(jedis);
				throw new SystemUtilException("jedis 发生异常", e);
			} finally {
				if (jedis != null)
					shardedJedisPool.returnResource(jedis);
			}
		}
	}

	public void destroy() {
		for (SlaveListener s : slaveListeners) {
			s.shutdown();
		}
		if (clearThread != null) {
			clearThread.interrupt();
		}
		jedisSentinelPool.destroy();
	}

	private void initShardedJedisPool(final String masterName) {
		if (jedisSentinelPool == null) {
			try {
				jedisSentinelPool = new JedisSentinelPool(masterName,
						sentinels, poolConfig, timeout, this.password, database);
			} catch (JedisException e) {
				logger.warn(e.getMessage());
			}
		}
		for (String sentinel : sentinels) {
			final HostAndPort hap = toHostAndPort(Arrays.asList(sentinel
					.split(":")));
			logger.info("Connecting to Sentinel " + hap);
			Jedis jedis = null;
			try {
				jedis = new Jedis(hap.getHost(), hap.getPort());

				//查询所有的slave
				List<Map<String, String>> slaveHost = jedis
						.sentinelSlaves(masterName);
				List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
				for (Map<String, String> map : slaveHost) {
					JedisShardInfo jedisShardInfo = new JedisShardInfo(
							map.get("ip"), Integer.valueOf(map.get("port")));
					Jedis test = new Jedis(jedisShardInfo.getHost(),
							jedisShardInfo.getPort());
					if (test.ping().equals("PONG")) {
						logger.info("slave:" + jedisShardInfo);
						shards.add(jedisShardInfo);
						test.close();
					}
				}
				//如果未查询到slave则使用master
				if (shards.isEmpty()) {
					logger.warn("no slave!");
					noSlave = true;
				} else {
					noSlave = false;
					shardedJedisPool = new ShardedJedisPool(poolConfig, shards);
				}
				break;
			} catch (JedisConnectionException e) {
				logger.info("Cannot connect to sentinel running @ " + hap
						+ ". Trying next one.");
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		}
	}

	private void listeners(final String masterName) {
		for (String sentinel : sentinels) {
			final HostAndPort hap = toHostAndPort(Arrays.asList(sentinel
					.split(":")));
			SlaveListener slaveListener = new SlaveListener(masterName,
					hap.getHost(), hap.getPort());
			slaveListeners.add(slaveListener);
			slaveListener.start();
		}
	}

	private HostAndPort toHostAndPort(List<String> getMasterAddrByNameResult) {
		String host = getMasterAddrByNameResult.get(0);
		int port = Integer.parseInt(getMasterAddrByNameResult.get(1));
		return new HostAndPort(host, port);
	}

	class SlaveListener extends Thread {

		private String masterName;
		private String host;
		private int port;
		private long subscribeRetryWaitTimeMillis = 5000;
		private Jedis j;
		private AtomicBoolean running = new AtomicBoolean(false);

		public SlaveListener() {
		}

		public SlaveListener(String masterName, String host, int port) {
			this.masterName = masterName;
			this.host = host;
			this.port = port;
		}

		public SlaveListener(String masterName, String host, int port,
				long subscribeRetryWaitTimeMillis) {
			this(masterName, host, port);
			this.subscribeRetryWaitTimeMillis = subscribeRetryWaitTimeMillis;
		}

		public void run() {
			running.set(true);
			while (running.get()) {
				j = new Jedis(host, port);
				try {
					j.subscribe(new JedisPubSub() {
						@Override
						public void onMessage(String channel, String message) {
							logger.info("Sentinel " + host + ":" + port
									+ " published: " + channel + " " + message
									+ ".");
							initShardedJedisPool(masterName);
						}
					}, "+switch-master", "+slave", "+sdown", "-sdown",
							"+odown", "-odown");

				} catch (JedisConnectionException e) {
					if (running.get()) {
						logger.info("Lost connection to Sentinel at " + host
								+ ":" + port + ". Sleeping "
								+ subscribeRetryWaitTimeMillis
								+ "ms and retrying.");
						try {
							Thread.sleep(subscribeRetryWaitTimeMillis);
						} catch (InterruptedException e1) {
							logger.error(e1.getMessage(), e);
						}
					} else {
						logger.info("Unsubscribing from Sentinel at " + host
								+ ":" + port);
					}
				}
			}
		}

		public void shutdown() {
			try {
				logger.info("Shutting down listener on " + host + ":" + port);
				running.set(false);
				// This isn't good, the Jedis object is not thread safe
				j.disconnect();
			} catch (Exception e) {
				logger.error("Caught exception while shutting down: ", e);
			}
		}
	}

	/**
	 * @param dealKey
	 */
	public void addClearKey(String key) {
		if (!clearKeyQueue.contains(key) && isUseRedis()) {
			try {
				clearKeyQueue.put(key);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isUseRedis() {
		return GridProperties.REDIS_OPEN != null
				&& GridProperties.REDIS_OPEN.booleanValue();
	}

}
