package com.tianque.init.impl;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.init.ContextType;
import com.tianque.init.Initialization;
import com.tianque.init.MoveDatasInitialization;
import com.tianque.util.PropertiesLoader;

public class DatabaseToHouseHoldStaffInitialization implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private static Logger moveDataLog = LoggerFactory
			.getLogger(MoveDatasInitialization.class);
	private ContextType contextType;
	private FloatingPopulationService floatingPopulationService;

	public DatabaseToHouseHoldStaffInitialization(
			FloatingPopulationService floatingPopulationService,
			ContextType contextType) {
		this.contextType = contextType;
		this.floatingPopulationService = floatingPopulationService;
		logger.error("contextType:" + contextType);
		moveDataLog.info("contextType:" + contextType);
	}

	@Override
	public void init() throws Exception {
		try {
			System.out.println("开始查询同一网格下人口信息都存在的重复数据");
			moveDataLog.info("开始查询同一网格下人口信息都存在的重复数据");
			List<Long> list = getCountrymensList();
			System.out.println("流动人口转为户籍人口");
			moveDataLog.info("流动人口转为户籍人口");
			ExcelImportHelper.isImport.set(false);
			toHouseHoldStaffs(list);
			System.out.println("检查重复数据");
			moveDataLog.info("检查重复数据");
			List<Long> newlist = getCountrymensList();
			System.out.println("检查重复数据");
			moveDataLog.info("检查重复数据");
			checkCountrymensList(newlist);
		} catch (Exception e) {
			moveDataLog.info("错误信息：", e);
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("重复数据合并完成!");
		moveDataLog.info("重复数据合并完成!");
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

	private List<Long> getCountrymensList() throws Exception {
		List<Long> list = new ArrayList<Long>();
		moveDataLog.info("查询同一网格下人口信息都存在的重复数据");
		String sql = "SELECT B.ID FROM HOUSEHOLDSTAFFS A,FLOATINGPOPULATIONS B WHERE A.BASEINFOID = B.BASEINFOID AND B.ORGID = A.ORGID AND A.LOGOUT=0";
		Connection conn = null;
		Statement smt = null;
		ResultSet rst = null;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			rst = smt.executeQuery(sql);
			while (rst.next()) {
				list.add(rst.getLong(1));
			}
			moveDataLog.info("同一网格下重复数据条数:" + list.size());
		} catch (Exception e) {
			throw new Exception("查询同一网格下人口信息都存在的重复数据失败" + e.getMessage());
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

	private void toHouseHoldStaffs(List<Long> list) throws Exception {
		for (Long flaotId : list) {
			moveDataLog.info("处理流动人口重复数据ID:" + flaotId);
			floatingPopulationService.toHouseholdStaff(flaotId);
			moveDataLog.info("处理流动人口重复数据ID:" + flaotId + "  结束");
		}
	}

	private void checkCountrymensList(List<Long> list) throws Exception {
		if (list != null && list.size() > 0) {
			moveDataLog.info("同一网格下重复数据条数:" + list.size());
			for (Long flaotId : list) {
				moveDataLog.info("未处理流动人口重复数据ID:" + flaotId);
			}
			moveDataLog.info("请检查同一网格下重复数据");
			System.out.println("请检查同一网格下重复数据");
		}
	}

}
