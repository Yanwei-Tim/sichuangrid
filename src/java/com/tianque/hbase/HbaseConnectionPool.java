package com.tianque.hbase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbaseConnectionPool {
	private static Logger logger = LoggerFactory
			.getLogger(HbaseConnectionPool.class);
	private List<Connection> pool;
	private String driverName;
	private String jdbcUrl;
	private int poolSize = 10;

	public HbaseConnectionPool() {
		init();
	}

	private void init() {
		pool = new ArrayList<Connection>();
		Connection conn = null;
		for (int i = 0; i < poolSize; i++) {
			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(jdbcUrl);
				pool.add(conn);
			} catch (ClassNotFoundException e) {
				logger.error("没找到驱动：", e);
			} catch (SQLException e) {
				logger.error("初始Hbase连接失败：", e);
			}
		}
	}

	public synchronized Connection getConnection() {
		if (pool.size() > 0) {
			Connection conn = pool.get(0);
			pool.remove(conn);
			return conn;
		} else {
			return null;
		}
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public int getPoolSize() {
		return poolSize;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

}
