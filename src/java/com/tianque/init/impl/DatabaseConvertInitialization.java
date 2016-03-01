package com.tianque.init.impl;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.init.ContextType;
import com.tianque.init.Initialization;
import com.tianque.init.MoveDatasInitialization;
import com.tianque.util.PropertiesLoader;

public class DatabaseConvertInitialization implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private static Logger moveDataLog = LoggerFactory
			.getLogger(MoveDatasInitialization.class);
	private ContextType contextType;
	private BaseInfoService baseinfoService;
	private Map<Long, String> idMap = null;

	public DatabaseConvertInitialization(BaseInfoService baseinfoService,
			ContextType contextType) {
		this.contextType = contextType;
		this.baseinfoService = baseinfoService;
		moveDataLog.info("contextType:" + contextType);
	}

	@Override
	public void init() throws Exception {
		try {
			System.out.println("开始处理全角身份证X");
			moveDataLog.info("开始处理全角身份证X");
			convertIdCardNoX();
			System.out.println("开始合并重复数据");
			moveDataLog.info("开始合并重复数据");
			idMap = new HashMap<Long, String>();
			convertCountrymens();
			System.out.println("更新基础信息ID");
			moveDataLog.info("更新基础信息ID");
			updateBaseInfoId();
			System.out.println("删除重复数据");
			moveDataLog.info("删除重复数据");
			deleteBaseInfoId();
		} catch (Exception e) {
			moveDataLog.info("错误信息：", e);
			logger.error("错误信息：", e);
			e.printStackTrace();
		}
		logger.info("重复数据合并完成!");
		moveDataLog.info("重复数据合并完成!");
	}

	private void idCardNoIllegalCharX() throws Exception {
		String sql = "select id, idcardno from baseinfo where idcardno like '%Ⅹ'";
		Connection conn = null;
		Statement smt = null;
		ResultSet rst = null;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			rst = smt.executeQuery(sql);
			while (rst.next()) {
				moveDataLog.info("id:" + rst.getLong(1) + ";  idcardno:"
						+ rst.getString(2));
			}
		} catch (Exception e) {
			throw new Exception("查询身份证错误信息X" + e.getMessage());
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

	private void convertIdCardNoX() throws Exception {
		moveDataLog.info("开始处理全角身份证X");
		idCardNoIllegalCharX();
		updateIdCardNoX();
	}

	private void updateIdCardNoX() throws Exception {
		moveDataLog.info("开始更新全角身份证X");
		String sql = "update baseinfo set idcardno = substr(idcardno,1,17)||'X' where idcardno like '%Ⅹ'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getDataSource().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception("更新全角身份证X错误" + e.getMessage());
		} finally {
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
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

	private List<String> getCountrymensList() throws Exception {
		moveDataLog.info("查询重复数据");
		String sql = "SELECT IDCARDNO FROM BASEINFO GROUP BY IDCARDNO HAVING COUNT(IDCARDNO)>1 ";
		List<String> list;
		Connection conn = null;
		Statement smt = null;
		ResultSet rst = null;
		try {
			conn = getDataSource().getConnection();
			smt = conn.createStatement();
			rst = smt.executeQuery(sql);
			list = new ArrayList<String>();
			while (rst.next()) {
				list.add(rst.getString(1));
			}
			moveDataLog.info("重复数据条数:" + list.size());
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

	private void updateBaseInfoId() throws Exception {
		String sqlh = "update householdstaffs set baseinfoid= ";
		moveDataLog.info("开始更新户籍表基础信息数据");
		updateId(sqlh);
		String sqlf = "update floatingpopulations set baseinfoid=";
		moveDataLog.info("开始更新流口表基础信息数据");
		updateId(sqlf);
	}

	private void updateId(String sql) throws Exception {
		Connection conn = null;
		Statement smt = null;
		try {
			conn = getDataSource().getConnection();
			conn.setAutoCommit(false);
			smt = conn.createStatement();
			String ids;
			String sqlTemp;
			for (Long id : idMap.keySet()) {
				ids = (String) idMap.get(id);
				sqlTemp = sql + id + " where baseinfoid in (" + ids + ")";
				smt.executeUpdate(sqlTemp);
				moveDataLog.info("更新基础信息数据：" + sqlTemp);
			}
			conn.commit();
		} catch (Exception e) {
			if (conn != null) {
				conn.rollback();
			}
			throw new Exception("更新基础信息数据失败" + e.getMessage());
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

	private void deleteBaseInfoId() throws Exception {
		moveDataLog.info("开始删除重复基础信息");
		Connection conn = null;
		Statement smt = null;
		try {
			String sql = "delete baseinfo where id in";
			conn = getDataSource().getConnection();
			conn.setAutoCommit(false);
			smt = conn.createStatement();
			String ids;
			String sqlTemp;
			for (Long id : idMap.keySet()) {
				ids = (String) idMap.get(id);
				sqlTemp = sql + " ( " + ids + ")";
				smt.executeUpdate(sqlTemp);
				moveDataLog.info("删除重复基础信息：" + sqlTemp);
			}
			conn.commit();
		} catch (Exception e) {
			if (conn != null) {
				conn.rollback();
			}
			throw new Exception("删除重复基础信息失败" + e.getMessage());
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

	private void convertCountrymens() throws Exception {
		List<String> idcardnolist = getCountrymensList();
		if (idcardnolist != null && idcardnolist.size() > 0) {
			for (String idcardno : idcardnolist) {
				getCountrymensByIdcardno(idcardno);
			}
		}

	}

	private void getCountrymensByIdcardno(String idcardno) throws Exception {
		List<Countrymen> list = baseinfoService
				.getBaseInfosByIdcardno(idcardno);
		if (list != null && list.size() > 0) {
			mergertCountrymen(list);
		}
	}

	private void mergertCountrymen(List<Countrymen> list) throws Exception {
		moveDataLog.info("开始合并重复基础信息");
		Countrymen countrymen = new Countrymen();
		List idlist = new ArrayList();
		int i = 0;
		boolean changeFlag = false;
		for (Countrymen countrymenTemp : list) {
			if (i == 0) {
				countrymen = countrymenTemp;
				i++;
				moveDataLog.info("基础信息：" + countrymen.getId() + "	idcardno:	"
						+ countrymen.getIdCardNo() + "	Name:	"
						+ countrymen.getName());
				continue;
			}
			idlist.add(countrymenTemp.getId());
			boolean flag = mergertBeans(countrymen, countrymenTemp);
			if (flag) {
				changeFlag = true;
			}
		}
		Long newId = countrymen.getId();
		if (changeFlag) {
			countrymen.setBaseInfoId(countrymen.getId());
			addCountrymen(countrymen);
		}
		if (newId != null) {
			idMap.put(newId, idlist.toString().replace("[", "")
					.replace("]", ""));
			moveDataLog.info(changeFlag + "合并为基础信息：" + newId + " 	idcardno:	"
					+ countrymen.getIdCardNo());
		}
	}

	private Long addCountrymen(Countrymen countrymen) throws Exception {
		return baseinfoService.save(countrymen);
	}

	private boolean mergertBeans(Countrymen countrymen,
			Countrymen countrymenTemp) {
		boolean flag = false;
		if (countrymen.getName() == null || "".equals(countrymen.getName())) {
			if (countrymenTemp.getName() != null) {
				countrymen.setName(countrymenTemp.getName());
				moveDataLog.info("字段：name 数据更新为：" + countrymenTemp.getName());
				flag = true;
			}
		}
		if (countrymen.getFullPinyin() == null
				|| "".equals(countrymen.getFullPinyin())) {
			if (countrymenTemp.getFullPinyin() != null) {
				countrymen.setFullPinyin(countrymenTemp.getFullPinyin());
				moveDataLog.info("字段：FullPinyin 数据更新为："
						+ countrymenTemp.getFullPinyin());
				flag = true;
			}
		}
		if (countrymen.getSimplePinyin() == null
				|| "".equals(countrymen.getSimplePinyin())) {
			if (countrymenTemp.getSimplePinyin() != null) {
				countrymen.setSimplePinyin(countrymenTemp.getSimplePinyin());
				moveDataLog.info("字段：SimplePinyin 数据更新为："
						+ countrymenTemp.getSimplePinyin());
				flag = true;
			}
		}
		if (countrymen.getUsedName() == null
				|| "".equals(countrymen.getUsedName())) {
			if (countrymenTemp.getUsedName() != null) {
				countrymen.setUsedName(countrymenTemp.getUsedName());
				moveDataLog.info("字段：UsedName 数据更新为："
						+ countrymenTemp.getUsedName());
				flag = true;
			}
		}
		if (countrymen.getIdCardNo() == null
				|| "".equals(countrymen.getIdCardNo())) {
			if (countrymenTemp.getIdCardNo() != null) {
				countrymen.setIdCardNo(countrymenTemp.getIdCardNo());
				moveDataLog.info("字段：IdCardNo 数据更新为："
						+ countrymenTemp.getIdCardNo());
				flag = true;
			}
		}
		if (countrymen.getTelephone() == null
				|| "".equals(countrymen.getTelephone())) {
			if (countrymenTemp.getTelephone() != null) {
				countrymen.setTelephone(countrymenTemp.getTelephone());
				moveDataLog.info("字段：Telephone 数据更新为："
						+ countrymenTemp.getTelephone());
				flag = true;
			}
		}
		if (countrymen.getMobileNumber() == null
				|| "".equals(countrymen.getMobileNumber())) {
			if (countrymenTemp.getMobileNumber() != null) {
				countrymen.setMobileNumber(countrymenTemp.getMobileNumber());
				moveDataLog.info("字段：MobileNumber 数据更新为："
						+ countrymenTemp.getMobileNumber());
				flag = true;
			}
		}
		if (countrymen.getBirthday() == null) {
			if (countrymenTemp.getBirthday() != null) {
				countrymen.setBirthday(countrymenTemp.getBirthday());
				moveDataLog.info("字段：Birthday 数据更新为："
						+ countrymenTemp.getBirthday());
				flag = true;
			}
		}
		if (countrymen.getGender() == null
				|| countrymen.getGender().getId() == null) {
			if (countrymenTemp.getGender() != null) {
				countrymen.setGender(countrymenTemp.getGender());
				moveDataLog.info("字段：Gender 数据更新为："
						+ countrymenTemp.getGender());
				flag = true;
			}
		}
		if (countrymen.getWorkUnit() == null
				|| "".equals(countrymen.getWorkUnit())) {
			if (countrymenTemp.getWorkUnit() != null) {
				countrymen.setWorkUnit(countrymenTemp.getWorkUnit());
				moveDataLog.info("字段：WorkUnit 数据更新为："
						+ countrymenTemp.getWorkUnit());
				flag = true;
			}
		}
		if (countrymen.getImgUrl() == null || "".equals(countrymen.getImgUrl())) {
			if (countrymenTemp.getImgUrl() != null) {
				countrymen.setImgUrl(countrymenTemp.getImgUrl());
				moveDataLog.info("字段：ImgUrl 数据更新为："
						+ countrymenTemp.getImgUrl());
				flag = true;
			}
		}
		if (countrymen.getEmail() == null || "".equals(countrymen.getEmail())) {
			if (countrymenTemp.getEmail() != null) {
				countrymen.setEmail(countrymenTemp.getEmail());
				moveDataLog.info("字段：Email 数据更新为：" + countrymenTemp.getEmail());
				flag = true;
			}
		}
		if (countrymen.getNation() == null
				|| countrymen.getNation().getId() == null) {
			if (countrymenTemp.getNation() != null) {
				countrymen.setNation(countrymenTemp.getNation());
				moveDataLog.info("字段：Nation 数据更新为："
						+ countrymenTemp.getNation().getId());
				flag = true;
			}
		}
		if (countrymen.getPoliticalBackground() == null
				|| countrymen.getPoliticalBackground().getId() == null) {
			if (countrymenTemp.getPoliticalBackground() != null) {
				countrymen.setPoliticalBackground(countrymenTemp
						.getPoliticalBackground());
				moveDataLog.info("字段：PoliticalBackground 数据更新为："
						+ countrymenTemp.getPoliticalBackground().getId());
				flag = true;
			}
		}
		if (countrymen.getSchooling() == null
				|| countrymen.getSchooling().getId() == null) {
			if (countrymenTemp.getSchooling() != null) {
				countrymen.setSchooling(countrymenTemp.getSchooling());
				moveDataLog.info("字段：Schooling 数据更新为："
						+ countrymenTemp.getSchooling().getId());
				flag = true;
			}
		}
		if (countrymen.getCareer() == null
				|| countrymen.getCareer().getId() == null) {
			if (countrymenTemp.getCareer() != null) {
				countrymen.setCareer(countrymenTemp.getCareer());
				moveDataLog.info("字段：Career 数据更新为："
						+ countrymenTemp.getCareer().getId());
				flag = true;
			}
		}
		if (countrymen.getMaritalState() == null
				|| countrymen.getMaritalState().getId() == null) {
			if (countrymenTemp.getMaritalState() != null) {
				countrymen.setMaritalState(countrymenTemp.getMaritalState());
				moveDataLog.info("字段：MaritalState 数据更新为："
						+ countrymenTemp.getMaritalState().getId());
				flag = true;
			}
		}
		if (countrymen.getBloodType() == null
				|| countrymen.getBloodType().getId() == null) {
			if (countrymenTemp.getBloodType() != null) {
				countrymen.setBloodType(countrymenTemp.getBloodType());
				moveDataLog.info("字段：BloodType 数据更新为："
						+ countrymenTemp.getBloodType().getId());
				flag = true;
			}
		}
		if (countrymen.getFaith() == null
				|| countrymen.getFaith().getId() == null) {
			if (countrymenTemp.getFaith() != null) {
				countrymen.setFaith(countrymenTemp.getFaith());
				moveDataLog.info("字段：Faith 数据更新为："
						+ countrymenTemp.getFaith().getId());
				flag = true;
			}
		}
		if (countrymen.getStature() == null) {
			if (countrymenTemp.getStature() != null) {
				countrymen.setStature(countrymenTemp.getStature());
				moveDataLog.info("字段：Stature 数据更新为："
						+ countrymenTemp.getStature());
				flag = true;
			}
		}
		if (countrymen.getProvince() == null
				|| "".equals(countrymen.getProvince())) {
			if (countrymenTemp.getProvince() != null) {
				countrymen.setProvince(countrymenTemp.getProvince());
				moveDataLog.info("字段：Province 数据更新为："
						+ countrymenTemp.getProvince());
				flag = true;
			}
		}
		if (countrymen.getCity() == null || "".equals(countrymen.getCity())) {
			if (countrymenTemp.getCity() != null) {
				countrymen.setCity(countrymenTemp.getCity());
				moveDataLog.info("字段：City 数据更新为：" + countrymenTemp.getCity());
				flag = true;
			}
		}
		if (countrymen.getDistrict() == null
				|| "".equals(countrymen.getDistrict())) {
			if (countrymenTemp.getDistrict() != null) {
				countrymen.setDistrict(countrymenTemp.getDistrict());
				moveDataLog.info("字段：District 数据更新为："
						+ countrymenTemp.getDistrict());
				flag = true;
			}
		}
		if (countrymen.getNativePlaceAddress() == null
				|| "".equals(countrymen.getNativePlaceAddress())) {
			if (countrymenTemp.getNativePlaceAddress() != null) {
				countrymen.setNativePlaceAddress(countrymenTemp
						.getNativePlaceAddress());
				moveDataLog.info("字段：nativePlaceAddress 数据更新为："
						+ countrymenTemp.getNativePlaceAddress());
				flag = true;
			}
		}
		if (countrymen.getNativePoliceStation() == null
				|| "".equals(countrymen.getNativePoliceStation())) {
			if (countrymenTemp.getNativePoliceStation() != null) {
				countrymen.setNativePoliceStation(countrymenTemp
						.getNativePoliceStation());
				moveDataLog.info("字段：NativePoliceStation 数据更新为："
						+ countrymenTemp.getNativePoliceStation());
				flag = true;
			}
		}
		if (countrymen.getCreateUser() == null
				|| "".equals(countrymen.getCreateUser())) {
			if (countrymenTemp.getCreateUser() != null) {
				countrymen.setCreateUser(countrymenTemp.getCreateUser());
				moveDataLog.info("字段：CreateUser 数据更新为："
						+ countrymenTemp.getCreateUser());
				flag = true;
			}
		}
		if (countrymen.getUpdateUser() == null
				|| "".equals(countrymen.getUpdateUser())) {
			if (countrymenTemp.getUpdateUser() != null) {
				countrymen.setUpdateUser(countrymenTemp.getUpdateUser());
				moveDataLog.info("字段：UpdateUser 数据更新为："
						+ countrymenTemp.getUpdateUser());
				flag = true;
			}
		}
		if (countrymen.getCreateDate() == null) {
			if (countrymenTemp.getCreateDate() != null) {
				countrymen.setCreateDate(countrymenTemp.getCreateDate());
				moveDataLog.info("字段：CreateDate 数据更新为："
						+ countrymenTemp.getCreateDate());
				flag = true;
			}
		}
		if (countrymen.getUpdateDate() == null) {
			if (countrymenTemp.getUpdateDate() != null) {
				countrymen.setUpdateDate(countrymenTemp.getUpdateDate());
				moveDataLog.info("字段：UpdateDate 数据更新为："
						+ countrymenTemp.getUpdateDate());
				flag = true;
			}
		}
		return flag;
	}

}
