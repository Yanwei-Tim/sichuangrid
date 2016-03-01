package com.tianque.hbase;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HbaseDataSource implements DataSource {
	private static Logger logger = LoggerFactory
			.getLogger(HbaseDataSource.class);
	private String driverName;
	private String jdbcUrl;
	private int poolSize = 10;

	private List<Connection> connections;

	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(jdbcUrl);
		} catch (ClassNotFoundException e) {
			logger.error("初始Hbase连接失败：", e);
		}
		return connection;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		Connection connection = null;
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(jdbcUrl);
		} catch (ClassNotFoundException e) {
			logger.error("初始Hbase连接失败：", e);
		}
		return connection;
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

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	public int getPoolSize() {
		return poolSize;
	}

}
