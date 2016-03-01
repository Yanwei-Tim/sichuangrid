package com.tianque.sysadmin.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.GlobalValue;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.ShardTableService;
import com.tianque.util.PropertiesLoader;

@Service("shardTableService")
public class ShardTableServiceImpl implements ShardTableService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	private static String DROP = "drop";
	private static String DELETE = "delete";
	private static String ALTER = "alter";
	private static String TRUNCATE = "truncate";

	@Override
	public boolean createShardTables() {
		try {
			List sqls = loadSqlFile(ShardTableServiceImpl.class
					.getClassLoader().getResourceAsStream("shard.sql"));
			executeBatchSql(sqls, getDataSource());
		} catch (Exception e) {
			if (e instanceof BusinessValidationException) {
				throw new BusinessValidationException(e.getMessage());
			}
			logger.error("创建分表出错:", e);
		}
		return true;
	}

	private void executeBatchSql(List<String> sqls, DataSource dataSource)
			throws SQLException {
		Connection con = dataSource.getConnection();
		con.setAutoCommit(false);
		Statement statement = con.createStatement();
		try {
			for (int i = 0; i < sqls.size(); i = 0) {
				String sql = sqls.get(i);
				sqls.remove(i);
				if (sql == null || "".equals(sql.trim())) {
					continue;
				}
				try {
					statement.execute(sql);
				} catch (Exception e) {
					logger.error("执行分表sql异常:", e);
				}
			}
			con.commit();
		} catch (Exception e) {
			logger.error("异常信息", e);
			con.rollback();
		} finally {
			statement.close();
			con.close();
		}
	}

	private List<String> loadSqlFile(InputStream inputStream) throws Exception {
		PropertyDict levelDict = propertyDictService
				.getPropertyDictByDomainName(OrganizationLevel.CITY_KEY);
		PropertyDict typeDict = propertyDictService
				.getPropertyDictByDomainName(OrganizationType.ADMINISTRATIVE_KEY);
		List<Organization> cityOrgs = organizationDubboService
				.getDepartmentNoByLevel(typeDict.getId(), levelDict.getId());
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		StringBuffer sqlSb = new StringBuffer();
		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			if (!str.startsWith("/*!") && !str.startsWith("--")
					&& str.indexOf("/*") <= 0 && str.trim().length() > 0) {
				sqlSb.append("\n" + str);
			}
		}
		bufferedReader.close();
		String[] sqls = sqlSb.toString().split(";");
		List<String> sqlList = new ArrayList<String>();
		for (Organization org : cityOrgs) {
			for (int i = 0; i < sqls.length; i++) {
				String startWord = sqls[i].trim().split(" ")[0];
				if (DROP.equalsIgnoreCase(startWord)
						|| DELETE.equalsIgnoreCase(startWord)
						|| ALTER.equalsIgnoreCase(startWord)
						|| TRUNCATE.equalsIgnoreCase(startWord))
					throw new BusinessValidationException(
							"创建分表失败,禁止在shard.sql中写drop、delete、alter、truncate语句");
				sqlList.add(new String(sqls[i]
						.replace("$shardCode$", org.getDepartmentNo()).getBytes(), "utf-8"));

			}
		}
		return sqlList;
	}

	protected DataSource getDataSource() {
		DataSource dataSource = new DataSource() {
			private String dirverClassName = (String) PropertiesLoader
					.get("jdbc.driverClassName");
			private String url = (String) PropertiesLoader
					.get(GlobalValue.environment + ".url");
			private String user = (String) PropertiesLoader
					.get(GlobalValue.environment + ".username");
			private String pswd = (String) PropertiesLoader
					.get(GlobalValue.environment + ".password");

			@Override
			public Connection getConnection(String username, String password)
					throws SQLException {
				try {
					Class.forName(dirverClassName);
				} catch (ClassNotFoundException e) {
					logger.error("异常信息", e);
				}
				return DriverManager.getConnection(url, username, password);
			}

			@Override
			public Connection getConnection() throws SQLException {
				try {
					Class.forName(dirverClassName);
				} catch (ClassNotFoundException e) {
					logger.error("异常信息", e);
				}
				return DriverManager.getConnection(url, user, pswd);
			}

			@Override
			public PrintWriter getLogWriter() throws SQLException {
				return null;
			}

			@Override
			public int getLoginTimeout() throws SQLException {
				return 0;
			}

			@Override
			public void setLogWriter(PrintWriter out) throws SQLException {

			}

			@Override
			public void setLoginTimeout(int seconds) throws SQLException {

			}

			@Override
			public boolean isWrapperFor(Class iface) throws SQLException {
				return false;
			}

			@Override
			public Class unwrap(Class iface) throws SQLException {
				return null;
			}

		};

		return dataSource;
	}
}
