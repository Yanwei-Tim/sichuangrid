package com.tianque.init.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.util.GlobalValue;
import com.tianque.init.ContextType;
import com.tianque.init.Initialization;
import com.tianque.init.MoveCompanyPlaceDatasInitialization;
import com.tianque.util.PropertiesLoader;

public class DatabaseToCompanyPlaveMoveTwoInitialization implements
		Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private static Logger moveDataLog = LoggerFactory
			.getLogger(MoveCompanyPlaceDatasInitialization.class);
	private ContextType contextType;
	private Map<String, List<String>> sqls = new HashMap<String, List<String>>();

	public DatabaseToCompanyPlaveMoveTwoInitialization(ContextType contextType) {
		this.contextType = contextType;
		logger.error("contextType:" + contextType);
	}

	/**
	 * 创建表
	 */
	@Override
	public void init() throws Exception {
		moveDataLog.error("MoveCompanyPlaceDatasTwoInitialization  init!");
		try {
			createTableMapForSqls(sqls);
			if (sqls.size() == 0) {
				logger.error("读取数据脚本失败!");
				moveDataLog.error("读取数据脚本失败!");
				throw new Exception("读取数据脚本失败!");
			}
		} catch (Exception e) {
			moveDataLog.error("错误信息：", e);
			logger.error("读取数据脚本：", e);
		}
		moveDataLog.error("数据表Rebuilder完成!");
	}

	public void maxValueCompanyPlace() {
		try {
			maxValueCompanyPlaceForSqls(sqls.get("maxValueCompanyPlace.sql"));
		} catch (Exception e) {
			moveDataLog.error("maxValueRecord错误：", e);
			logger.error("maxValueRecord错误：", e);
		}
	}

	public void alterTableToAdd() {
		try {
			alterTableToAddForSqls(sqls.get("alterTableToAdd.sql"));
		} catch (Exception e) {
			moveDataLog.error("alterTableToAdd错误：", e);
			logger.error("alterTableToAdd错误：", e);
		}
	}

	public void countMoveBefore() {
		try {
			countDataForSqls(sqls.get("countData.sql"));
		} catch (Exception e) {
			logger.error("countMoveBefore错误：", e);
			moveDataLog.error("countMoveBefore错误：", e);
		}
	}

	public void countDataManageMoveBefore() {
		try {
			countDataForSqls(sqls.get("countDataByDataManage.sql"));
		} catch (Exception e) {
			logger.error("countDataManageMoveBefore错误：", e);
			moveDataLog.error("countDataManageMoveBefore错误：", e);
		}
	}

	public void countDataManageMoveAfter() {
		try {
			countDataForSqls(sqls.get("countDataByDataManage.sql"));
		} catch (Exception e) {
			logger.error("countDataManageMoveAfter错误：", e);
			moveDataLog.error("countDataManageMoveAfter错误：", e);
		}
	}

	public void moveDataForTemp() {
		try {
			moveDataForTempForSqls(sqls.get("moveDataForTemp.sql"));
		} catch (Exception e) {
			moveDataLog.error("moveDataForTemp错误：", e);
			logger.error("moveDataForTemp错误：", e);
		}
	}

	public void moveDate() {
		try {
			moveDatasForSqls(sqls.get("moveData.sql"));
		} catch (Exception e) {
			moveDataLog.error("moveDate错误：", e);
			logger.error("moveDate错误：", e);
		}
	}

	public void moveDataManageForCompanyPlace() {
		try {
			moveDataManageForCompanyPlaceForSqls(sqls
					.get("dataManageForCompanyPlace.sql"));
		} catch (Exception e) {
			moveDataLog.error("moveDataManageForCompanyPlace错误：", e);
			logger.error("moveDataManageForCompanyPlace错误：", e);
		}
	}

	public void insertKeyplacesForGis() {
		try {
			insertKeyplacesForGisForSqls(sqls.get("insertKeyplacesForGis.sql"));
		} catch (Exception e) {
			moveDataLog.error("insertKeyplacesForGis错误：", e);
			logger.error("insertKeyplacesForGis错误：", e);
		}
	}

	public void alterTableToDrop() {
		try {
			alterTableToDropForSqls(sqls.get("alterTableToDrop.sql"));
		} catch (Exception e) {
			moveDataLog.error("alterTableToDrop错误：", e);
			logger.error("alterTableToDrop错误：", e);
		}
	}

	public void countMoveAfter() {
		try {
			countDataForSqls(sqls.get("countData.sql"));
		} catch (Exception e) {
			logger.error("countMoveAfter错误：", e);
			moveDataLog.error("countMoveAfter错误：", e);
		}
	}

	private void createTableMapForSqls(Map sqls) {
		try {
			File sqlFile = new File(
					GlobalValue.SQLS_TABLE_COMPANYPLACE_TWO_PATH);
			File[] files = sqlFile.listFiles();
			for (File file : files) {
				if (!file.isDirectory()) {
					sqls.put(file.getName(), loadSqlFile(file.getPath()));
				}
			}
		} catch (Exception e) {
			logger.error("初始化时读取sql文件出错：", e);
		}
	}

	private void alterTableToAddForSqls(List<String> list) throws Exception {
		moveDataLog.error("alterTableToAddForSqls");
		if (list == null || list.size() == 0) {
			throw new Exception("添加表字段脚本失败!");
		}
		Connection conn = null;
		Statement smt = null;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			for (int i = 0; i < list.size(); i = 0) {
				String sql = list.get(i);
				smt.execute(sql);
				moveDataLog.error(sql);
				list.remove(i);
			}
			smt.close();
			conn.close();
		} catch (Exception e) {
			throw new Exception("添加表字段失败" + e);
		} finally {
			if (smt != null) {
				smt.close();
				smt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}

	private void maxValueCompanyPlaceForSqls(List<String> list)
			throws Exception {
		moveDataLog.error("maxValueCompanyPlaceForSqls ");
		if (list == null || list.size() == 0) {
			throw new Exception("读取单位场所最大值及巡场情况服务记录最大值脚本失败!");
		}
		Connection conn = null;
		Statement smt = null;
		ResultSet rst = null;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			for (int i = 0; i < list.size(); i++) {
				String sql = list.get(i);
				rst = smt.executeQuery(sql);
				if (rst.next()) {
					moveDataLog.error(sql + "	最大值：" + rst.getString(1));
				}
			}
			rst.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			throw new Exception("统计单位场所最大值及巡场情况服务记录最大值失败" + e);
		} finally {
			if (rst != null) {
				rst.close();
				rst = null;
			}
			if (smt != null) {
				smt.close();
				smt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}

	private void alterTableToDropForSqls(List<String> list) throws Exception {
		moveDataLog.error("alterTableToDropForSqls");
		if (list == null || list.size() == 0) {
			throw new Exception("还原表结构脚本失败!");
		}
		Connection conn = null;
		Statement smt = null;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			for (int i = 0; i < list.size(); i = 0) {
				String sql = list.get(i);
				smt.execute(sql);
				moveDataLog.error(sql);
				list.remove(i);
			}
			smt.close();
			conn.close();
		} catch (Exception e) {
			throw new Exception("还原表结构失败" + e);
		} finally {
			if (smt != null) {
				smt.close();
				smt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}

	private void countDataForSqls(List<String> list) throws Exception {
		moveDataLog.error("countDataForSqls ");
		if (list == null || list.size() == 0) {
			throw new Exception("统计数据信息脚本获取失败!");
		}
		Connection conn = null;
		Statement smt = null;
		ResultSet rst = null;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			for (int i = 0; i < list.size(); i++) {
				String sql = list.get(i);
				rst = smt.executeQuery(sql);
				if (rst.next()) {
					moveDataLog.error(sql + "	条数：" + rst.getString(1));
				}
			}
			rst.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			throw new Exception("统计数据信息失败" + e);
		} finally {
			if (rst != null) {
				rst.close();
				rst = null;
			}
			if (smt != null) {
				smt.close();
				smt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}

	private void insertKeyplacesForGisForSqls(List<String> list)
			throws Exception {
		moveDataLog.error("insertKeyplacesForGisForSqls");
		if (list == null || list.size() == 0) {
			throw new Exception("读取建立新单位场所地图关联关系脚本失败!");
		}
		Connection conn = null;
		Statement smt = null;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			for (int i = 0; i < list.size(); i = 0) {
				String sql = list.get(i);
				moveDataLog.error(sql);
				smt.execute(sql);
				list.remove(i);
			}
			smt.close();
			conn.close();
		} catch (Exception e) {
			throw new Exception("建立新单位场所地图关联关系失败" + e);
		} finally {
			if (smt != null) {
				smt.close();
				smt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}

	private void moveDataForTempForSqls(List<String> list) throws Exception {
		moveDataLog.error("moveDataForTempForSqls");
		if (list == null || list.size() == 0) {
			throw new Exception("读取迁移数据至临时表脚本失败!");
		}
		Connection conn = null;
		Statement smt = null;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			for (int i = 0; i < list.size(); i = 0) {
				String sql = list.get(i);
				moveDataLog.error(sql);
				smt.execute(sql);
				list.remove(i);
			}
			smt.close();
			conn.close();
		} catch (Exception e) {
			throw new Exception("迁移数据至临时表失败" + e);
		} finally {
			if (smt != null) {
				smt.close();
				smt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}

	private void moveDatasForSqls(List<String> list) throws Exception {
		moveDataLog.error("moveDatasForSqls");
		if (list == null || list.size() == 0) {
			throw new Exception("读取迁移数据脚本失败!");
		}
		Connection conn = null;
		Statement smt = null;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			for (int i = 0; i < list.size(); i = 0) {
				String sql = list.get(i);
				moveDataLog.error(sql);
				smt.execute(sql);
				list.remove(i);
			}
			smt.close();
			conn.close();
		} catch (Exception e) {
			throw new Exception("迁移数据失败" + e);
		} finally {
			if (smt != null) {
				smt.close();
				smt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}

	private void moveDataManageForCompanyPlaceForSqls(List<String> list)
			throws Exception {
		moveDataLog.error("moveDataManageForCompanyPlaceForSqls");
		if (list == null || list.size() == 0) {
			throw new Exception("读取迁移数据脚本失败!");
		}
		Connection conn = null;
		Statement smt = null;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			for (int i = 0; i < list.size(); i = 0) {
				String sql = list.get(i);
				moveDataLog.error(sql);
				smt.execute(sql);
				list.remove(i);
			}
			smt.close();
			conn.close();
		} catch (Exception e) {
			throw new Exception("迁移数据失败" + e);
		} finally {
			if (smt != null) {
				smt.close();
				smt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
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
			sqlList.add(new String(sqls[i].getBytes(), "utf-8"));
		}
		return sqlList;
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
					moveDataLog.error("异常信息", e);
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

	private List<String> loadSqlFile(String fileName) throws Exception {
		return loadSqlFile(new FileInputStream(fileName));
	}

}
