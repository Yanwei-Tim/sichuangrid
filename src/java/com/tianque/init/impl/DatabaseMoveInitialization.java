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
import com.tianque.init.MoveDatasInitialization;
import com.tianque.util.PropertiesLoader;
import com.tianque.util.SqlScriptExcuteUtil;

public class DatabaseMoveInitialization implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private static Logger moveDataLog = LoggerFactory
			.getLogger(MoveDatasInitialization.class);
	private ContextType contextType;
	private Map<String, List<String>> sqls = new HashMap<String, List<String>>();

	public DatabaseMoveInitialization(ContextType contextType) {
		this.contextType = contextType;
		logger.info("contextType:" + contextType);
	}

	/**
	 * 创建表
	 */
	@Override
	public void init() throws Exception {
		moveDataLog.info("DatabaseMoveInitialization  init!");
		try {
			createTableMapForSqls(sqls);
			if (sqls.size() == 0) {
				logger.error("读取数据脚本失败!");
				moveDataLog.info("读取数据脚本失败!");
				throw new Exception("读取数据脚本失败!");
			}
		} catch (Exception e) {
			moveDataLog.info("错误信息：", e);
			logger.info("读取数据脚本：", e);
			e.printStackTrace();
		}
		moveDataLog.info("数据表Rebuilder完成!");
	}

	public void dropTable() {
		try {
			System.out.println("dropTable");
			dropTableForSqls(sqls.get("dropTable.sql"));
		} catch (Exception e) {
			moveDataLog.info("dropTable错误：", e);
			e.printStackTrace();
			logger.debug("dropTable错误：", e.getMessage());
		}
	}

	public void createTable() {
		try {
			System.out.println("createTable");
			createTableForSqls(sqls.get("createTable.sql"));
		} catch (Exception e) {
			moveDataLog.info("createTable错误：", e);
			logger.debug("createTable错误：", e.getMessage());
			e.printStackTrace();
		}
	}

	public void insertDatas() {
		try {
			System.out.println("insertDatas");
			insertDatasForSqls(sqls.get("insertData.sql"));
		} catch (Exception e) {
			moveDataLog.info("insertDatas错误：", e);
			logger.debug("insertDatas错误：", e.getMessage());
			e.printStackTrace();
		}
	}

	public void addHouseholdstaffsDatas() {
		try {
			System.out.println("addHouseholdstaffsDatas");
			addHouseholdstaffsDatasForSqls(sqls
					.get("addHouseholdstaffsData.sql"));
		} catch (Exception e) {
			moveDataLog.info("addHouseholdstaffsDatas错误：", e);
			logger.debug("addHouseholdstaffsDatas错误：", e.getMessage());
			e.printStackTrace();
		}
	}

	public void updateDatas() {
		try {
			System.out.println("updateDatas");
			updateDatasForSqls(sqls.get("updateData.sql"));
		} catch (Exception e) {
			moveDataLog.info("updateDatas错误：", e);
			logger.debug("updateDatas错误：", e);
			e.printStackTrace();
		}
	}

	public void alterTable() {
		try {
			System.out.println("alterTable");
			alterTableForSqls(sqls.get("alterTable.sql"));
		} catch (Exception e) {
			moveDataLog.info("alterTable错误：", e);
			logger.debug("alterTable错误：", e);
			e.printStackTrace();
		}
	}

	public void countOldData() {
		try {
			System.out.println("countTempData");
			countDataForSqls(sqls.get("countTempData.sql"));
		} catch (Exception e) {
			logger.info("countTempData错误：", e);
			moveDataLog.info("countTempData错误：", e);
			e.printStackTrace();
		}
	}

	public void countNewData() {
		try {
			System.out.println("countNewData");
			countDataForSqls(sqls.get("countData.sql"));
		} catch (Exception e) {
			logger.info("countData错误：", e);
			moveDataLog.info("countData错误：", e);
			e.printStackTrace();
		}
	}

	public void checkDatas() {
		try {
			checkBaseinfoDatas();
		} catch (Exception e) {
			logger.info("checkDatas错误：", e);
			moveDataLog.info("checkDatas错误：", e);
			e.printStackTrace();
		}
	}

	public void checkSequence() {
		System.out.println("checkSequence");
		try {
			checkTableSequence("s_systemLogs", "systemlogs");
			checkTableSequence("s_houseTracks", "houseTracks");
			checkTableSequence("s_workDiarys", "workDiarys");
			checkTableSequence("s_censusregisterfamilys", "censusregisterfamilys");
		} catch (Exception e) {
			logger.info("checkSequence错误：", e);
			moveDataLog.info("checkSequence错误：", e);
			e.printStackTrace();
		}
	}

	private void checkTableSequence(String seqname, String table)
			throws Exception {
		try {
			int seq = getSequence(seqname);
			int maxseq = getMaxCurrentId(table);
			moveDataLog.info("序列" + seqname + "当前值为：" + seq + ",在表" + table
					+ "中id最大值" + maxseq);
			while (maxseq > seq) {
				seq = getSequence(seqname);
			}
			moveDataLog.info("修改后序列" + seqname + "当前值为：" + seq + ",在表" + table
					+ "中id最大值" + maxseq);
		} catch (Exception e) {
			throw new Exception("checkSequence错误" + e);
		}
	}

	private int getSequence(String seqname) throws Exception {
		int sequence = 0;
		String sql = "";
		Connection conn = null;
		Statement smt = null;
		ResultSet rst = null;
		try {
			sql = "select " + seqname + ".nextval  as seq from dual";
			conn = getDataSource().getConnection();
			smt = conn.prepareStatement(sql);
			rst = smt.executeQuery(sql);
			while (rst.next()) {
				sequence = rst.getInt("seq");
			}
			rst.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			logger.error("获取序列值时出错：", e);
			throw new Exception("获取序列值时出错" + e.getMessage());
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
		return sequence;
	}

	public int getMaxCurrentId(String table) throws Exception {
		int currentid = 0;
		String sql = "";
		Connection conn = null;
		Statement smt = null;
		ResultSet rst = null;
		try {
			sql = "select max(id) as maxSeq from  " + table;
			conn = getDataSource().getConnection();
			smt = conn.prepareStatement(sql);
			rst = smt.executeQuery(sql);
			while (rst.next()) {
				currentid = rst.getInt("maxSeq");
			}
			rst.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			logger.error("从表中获取id最大值时出错：", e);
			throw new Exception("从表中获取id最大值时出错" + e.getMessage());
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
		return currentid;
	}

	private void createTableMapForSqls(Map sqls) {
		try {
			File sqlFile = new File(GlobalValue.SQLS_TABLE_PEOPLE_PATH);
			File[] files = sqlFile.listFiles();
			for (File file : files) {
				if (!file.isDirectory()) {
					sqls.put(file.getName(), loadSqlFile(file.getPath()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("初始化时读取sql文件出错：", e);
		}
	}

	private void dropTableForSqls(List<String> list) throws Exception {
		if (list == null || list.size() == 0) {
			throw new Exception("读取删除数据脚本失败!");
		}
		try {
			SqlScriptExcuteUtil.executeBatchSql(list, getDataSource());
			moveDataLog.info("删除旧表数据");
		} catch (Exception e) {
			throw new Exception("删除旧表失败" + e.getMessage());
		}
	}

	private void createTableForSqls(List<String> list) throws Exception {
		if (list == null || list.size() == 0) {
			throw new Exception("读取新增数据表脚本失败!");
		}
		try {
			SqlScriptExcuteUtil.executeBatchSql(list, getDataSource());
			moveDataLog.info("新增新数据表成功");
		} catch (Exception e) {
			throw new Exception("新增数据表失败" + e.getMessage());
		}
	}

	private void alterTableForSqls(List<String> list) throws Exception {
		moveDataLog.info("alterTableForSqls");
		if (list == null || list.size() == 0) {
			throw new Exception("更新数据脚本失败!");
		}
		Connection conn = null;
		Statement smt = null;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			for (int i = 0; i < list.size(); i = 0) {
				String sql = list.get(i);
				smt.execute(sql);
				moveDataLog.info(sql);
				list.remove(i);
			}
			smt.close();
			conn.close();
		} catch (Exception e) {
			throw new Exception("更新数据失败" + e.getMessage());
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
		moveDataLog.info("countDataForSqls ");
		if (list == null || list.size() == 0) {
			throw new Exception("统计数据信息失败!");
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
					moveDataLog.info(sql + "	条数：" + rst.getString(1));
				}
			}
			rst.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			throw new Exception("统计数据信息失败" + e.getMessage());
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

	private void insertDatasForSqls(List<String> list) throws Exception {
		moveDataLog.info("insertDatasForSqls");
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
				moveDataLog.info(sql);
				smt.execute(sql);
				list.remove(i);
			}
			smt.close();
			conn.close();
		} catch (Exception e) {
			throw new Exception("迁移数据失败" + e.getMessage());
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

	private void addHouseholdstaffsDatasForSqls(List<String> list)
			throws Exception {
		moveDataLog.info("addHouseholdstaffsDatas");
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
				moveDataLog.info(sql);
				smt.execute(sql);
				list.remove(i);
			}
			smt.close();
			smt.close();
		} catch (Exception e) {
			throw new Exception("迁移数据失败" + e.getMessage());
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

	private void updateDatasForSqls(List<String> list) throws Exception {
		moveDataLog.info("updateDatasForSqls");
		if (list == null || list.size() == 0) {
			throw new Exception("读取更新数据脚本失败!");
		}
		Connection conn = null;
		Statement smt = null;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			for (int i = 0; i < list.size(); i = 0) {
				String sql = list.get(i);
				moveDataLog.info(sql);
				smt.execute(sql);
				list.remove(i);
			}
			smt.close();
			conn.close();
		} catch (Exception e) {
			throw new Exception("更新数据失败" + e.getMessage());
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

	private void checkBaseinfoDatas() throws Exception {
		System.out.println("开始检查数据");
		moveDataLog.info("检查数据");
		List<String> list = getCountrymensList();
		if (list == null || list.size() == 0) {
			moveDataLog.info("检查数据结束");
		} else {
			warnDatas(list);
		}
	}

	private List<String> getCountrymensList() throws Exception {
		moveDataLog.info("查询重复数据");
		String sql = "SELECT IDCARDNO FROM BASEINFO GROUP BY IDCARDNO HAVING COUNT(IDCARDNO)>1 ";
		Connection conn = null;
		Statement smt = null;
		ResultSet rst = null;
		List<String> list;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			rst = smt.executeQuery(sql);
			list = new ArrayList<String>();
			while (rst.next()) {
				list.add(rst.getString(1));
			}
			moveDataLog.info("重复数据条数:" + list.size());
			rst.close();
			smt.close();
			conn.close();
		} catch (Exception e) {
			throw new Exception("查询重复数据错误" + e.getMessage());
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
		return list;
	}

	private void warnDatas(List<String> list) throws Exception {
		System.out.println("---------------警告----------------");
		moveDataLog.info("---------------警告----------------");
		moveDataLog.info("基础信息中存在重复数据，请手动修复");
		System.out.println("基础信息中存在重复数据，请手动修复");
		moveDataLog.info(list.toString().replace("[", "").replace("]", ""));
		System.out.println(list.toString().replace("[", "").replace("]", ""));
		for (String idcardno : list) {
			moveDataLog.info("重复身份证:" + idcardno);
		}
		System.out.println("---------------请手动修复----------------");
		moveDataLog.info("---------------请手动修复----------------");
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
					moveDataLog.info("异常信息", e);
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
