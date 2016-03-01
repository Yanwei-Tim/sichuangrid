package com.tianque.init.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.GlobalValue;
import com.tianque.init.ContextType;
import com.tianque.init.Initialization;
import com.tianque.util.PropertiesLoader;
import com.tianque.util.SqlScriptExcuteUtil;

public class TaskJobInit implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private ContextType contextType;

	public TaskJobInit(ContextType contextType) {
		this.contextType = contextType;
		logger.error("contextType:" + contextType);
	}

	@Override
	public void init() throws Exception {
		createTaskJobForSqls();
	}

	private void createTaskJobForSqls() {
		try {
			File sqlFile = new File(GlobalValue.TASK_JOB_SQLS_PATH);
			File[] file = sqlFile.listFiles();
			List<String> sqls;
			for (File files : file) {
				if (!files.isDirectory()) {
					logger.error("初始化时读取TaskJobInit sql文件：" + files.getName());
					sqls = loadSqlFile(files.getPath());
					SqlScriptExcuteUtil.executeBatchSql(sqls, getDataSource());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化时读取TaskJobInit  sql文件出错：", e);
		}
	}

	protected List<String> loadSqlFile(InputStream inputStream)
			throws Exception {

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
		for (int i = 0; i < sqls.length; i++) {
			sqlList.add(new String(sqls[i]));
		}
		return sqlList;
	}

	private List<String> loadSqlFile(String fileName) throws Exception {
		return loadSqlFile(new FileInputStream(fileName));
	}

	protected DataSource getDataSource() {
		DataSource dataSource = new DataSource() {
			private String dirverClassName = (String) PropertiesLoader
					.get("jdbc.driverClassName");
			private String url = (String) PropertiesLoader.get(contextType
					.name() + ".url");
			private String user = (String) PropertiesLoader.get(contextType
					.name() + ".username");
			private String pswd = (String) PropertiesLoader.get(contextType
					.name() + ".password");

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
