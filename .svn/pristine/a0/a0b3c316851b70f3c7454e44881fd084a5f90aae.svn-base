package com.tianque.util.init;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CrateTableExcel {

	/* 数据库连接配置 */
	private static String driverClassName = "oracle.jdbc.driver.OracleDriver";
	private static String url = null;
	private static String userName = null;
	private static String password = null;
	private static Connection conn = null;
	private static Statement stmt = null;

	public static void main(String[] args) throws Exception {
		try {
			CrateTableExcel crateTableExcel = new CrateTableExcel();
			crateTableExcel.crateTableExcel(
					"src/test/resource/com/tianque/baseInfo/positiveInfo/dao/",
					"PositiveInfoDaoTest", "positiveInfos");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param filePath
	 *        文件路径
	 * @param testDaoName
	 *        要生成测试DAO名称
	 * @param tableName
	 *        表名
	 * @throws Exception
	 */
	public void crateTableExcel(String filePath, String testDaoName, String tableName)
			throws Exception {
		crateFile(filePath);
		getConnection();
		stmt = conn.createStatement();
		ResultSet rs = stmt
				.executeQuery("select column_name,data_type from user_tab_columns where table_name = upper('"
						+ tableName + "')");
		new CrateTableExcel().createExcel(filePath, testDaoName + ".xls", tableName, rs, true);
		rs = stmt
				.executeQuery("select column_name,data_type from user_tab_columns where table_name = upper('"
						+ tableName + "')");
		new CrateTableExcel().createExcel(filePath, testDaoName + ".addTest-result.xls", tableName,
				rs, false);
		rs = stmt
				.executeQuery("select column_name,data_type from user_tab_columns where table_name = upper('"
						+ tableName + "')");
		new CrateTableExcel().createExcel(filePath, testDaoName + ".updateTest-result.xls",
				tableName, rs, false);
		rs.close();
		stmt.close();
		conn.close();
	}

	private void crateFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	private void getConnection() throws Exception {
		InputStream is = CrateTableExcel.class.getClassLoader().getResourceAsStream(
				"unitils-local.properties");
		Properties properties = new Properties();
		properties.load(is);
		url = properties.getProperty("database.url");
		userName = properties.getProperty("database.userName");
		password = properties.getProperty("database.password");
		Class.forName(driverClassName);
		conn = DriverManager.getConnection(url, userName, password);
	}

	/**
	 * @param filePath
	 *        文件路径
	 * @param fileName
	 *        文件名称
	 * @param table_name
	 *        表名
	 * @param rt
	 *        表列数据
	 * @param boo
	 *        是否生成 id,createDate等
	 * @throws Exception
	 */
	private void createExcel(String filePath, String fileName, String table_name, ResultSet rt,
			boolean boo) throws Exception {
		HSSFWorkbook workbookT = new HSSFWorkbook();

		HSSFSheet sheetT = workbookT.createSheet(table_name);
		HSSFRow rowT = sheetT.createRow((short) 0);
		int i = 0;
		int j = 0;
		/**
		 * 生成当前表列
		 */
		String orderColumnName = "";
		Map<Integer, String> map = new HashMap<Integer, String>();// 记录生成那些列
		while (rt.next()) {
			// 是否生成id等
			if (!"CREATEUSER".equals(rt.getString(1)) && !"UPDATEUSER".equals(rt.getString(1))
					&& !"CREATEDATE".equals(rt.getString(1))
					&& !"UPDATEDATE".equals(rt.getString(1)) && !"ID".equals(rt.getString(1))
					|| boo) {
				HSSFCell cell = rowT.createCell(j);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(rt.getString(1));
				sheetT.setColumnWidth(i, 5000);
				map.put(j, rt.getString(1));
				j++;
			}
			if (i == 0) {
				orderColumnName = rt.getString(1);
			}
			i++;
		}

		i = 0;
		ResultSet resultSet = new CrateTableExcel().getTableData(table_name, orderColumnName);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int columnCount = rsmd.getColumnCount(); // 获取列个数
		/**
		 * 生成当前表数据
		 */
		while (resultSet.next()) {
			rowT = sheetT.createRow((short) i + 1);
			for (int k = 1; k <= columnCount; k++) {
				if (map.get(k - 1) != null && !"".equals(map.get(k - 1))) {
					HSSFCell cell = rowT.createCell(k - 1);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(resultSet.getString(map.get(k - 1)));
					sheetT.setColumnWidth(k - 1, 5000);
				}
			}
			i++;
		}

		File file = new File(filePath + fileName);
		if (file.exists()) {
			file.delete();
		}
		FileOutputStream fOut = new FileOutputStream(file);
		fOut = new FileOutputStream(file);
		workbookT.write(fOut);
		fOut.flush();
		fOut.close();
	}

	/**
	 * 获取表数据
	 * 
	 * @param table_name
	 * @return
	 * @throws Exception
	 */
	private ResultSet getTableData(String table_name, String orderColumnName) throws Exception {
		stmt = conn.createStatement();
		if ("GLOBALSETTINGS".equals(table_name)) {
			return stmt.executeQuery("select * from " + table_name + " ");
		}
		return stmt.executeQuery("select * from " + table_name + " order by " + orderColumnName
				+ " ");
	}
}
