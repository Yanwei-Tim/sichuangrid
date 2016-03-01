package com.tianque.util;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.base.excel.BaseDaoTest;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.FileUtil;
import com.tianque.sysadmin.dao.OrganizationDaoTest;

/**
 * @描述:
 * @版权:
 * @公司:
 * @作者:王熙斌
 * @创建时间：2012-12-21 下午2:40:44
 **/
public class CreateExcelUtil {
	protected final static Logger logger = LoggerFactory.getLogger(CreateExcelUtil.class);

	/* 数据库连接配置 */
	private static String driverClassName = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static String userName = "sheguan";
	private static String password = "123321";

	/** 测试类 */
	private static Class<? extends BaseDaoTest> testClass = OrganizationDaoTest.class;
	/** 所对应的表，可多个 */
	private static String[] tableNames = { "Organizations" };

	private Class<? extends BaseDomain> domainClass;
	private DataSource dataSource;

	/**
	 * @功能：
	 * @说明：
	 * @作者：王熙斌
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) {
		try {
			CreateExcelUtil createExcelUtil = new CreateExcelUtil();
			createExcelUtil.createExcel();
		} catch (Exception e) {
			logger.error("出错了" + e);
		}
	}

	public CreateExcelUtil() throws SQLException {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setInitialSize(50);
		dataSource.setMaxActive(100);
		dataSource.setMaxIdle(30);
		dataSource.setMaxWait(10000);
		this.dataSource = dataSource;
		Type type = testClass.getGenericSuperclass();
		Type[] params = ((ParameterizedType) type).getActualTypeArguments();
		domainClass = (Class<? extends BaseDomain>) params[0];
	}

	public void createExcel() throws Exception {

		Field[] fields = getDeclaredField(domainClass);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet addSheet = workbook.createSheet("add" + domainClass.getSimpleName() + "s");

		HSSFRow row = addSheet.createRow((short) 0);
		String path = "src/test/resource/" + testClass.getPackage().getName().replace(".", "/")
				+ "/";
		File pathFile = new File(path);
		if (!pathFile.exists()) {
			logger.info(pathFile.getPath() + "目录不存在，自动创建！");
			pathFile.mkdirs();
		}
		// testClass.getResource("").getPath();

		String className = testClass.getSimpleName();
		int index = 0;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			int mod = field.getModifiers();
			if ((mod & Modifier.FINAL) != 0) {
				continue;
			}

			HSSFCell cell = row.createCell(index);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(field.getName());
			Class<?> fieldType = field.getType();
			if (BaseDomain.class.isAssignableFrom(fieldType)) {
				cell.setCellValue(field.getName() + ".id");
			} else if ((Boolean.class == fieldType || fieldType.getName().equals("boolean"))
					&& field.getName().startsWith("is")) {
				String fieldName = field.getName().substring(2);
				fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
				cell.setCellValue(fieldName);
			}
			addSheet.setColumnWidth(index, 5000);
			index++;
		}

		workbook.cloneSheet(0);
		workbook.setSheetName(1, "updata" + domainClass.getSimpleName() + "s");

		String fileName = className + ".beans" + ".xls";
		File file = new File(path + fileName);
		if (file.exists()) {
			logger.error(file.getPath() + "文件已经存在！");
		} else {
			FileOutputStream fOut = new FileOutputStream(file);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		}

		HSSFWorkbook workbookT = new HSSFWorkbook();
		for (String tableName : tableNames) {
			Table table = getTableInfo(tableName);

			HSSFSheet sheetT = workbookT.createSheet(tableName);
			HSSFRow rowT = sheetT.createRow((short) 0);
			for (int i = 0; i < table.getColumns().size(); i++) {
				Column column = table.getColumns().get(i);
				HSSFCell cell = rowT.createCell(i);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(column.getColumnName());
				sheetT.setColumnWidth(i, 5000);
			}
		}

		fileName = className + ".xls";
		file = new File(path + fileName);
		if (file.exists()) {
			logger.error(file.getPath() + "文件已经存在！");
		} else {
			FileOutputStream fOut = new FileOutputStream(file);
			fOut = new FileOutputStream(file);
			workbookT.write(fOut);
			fOut.flush();
			fOut.close();
		}
		// 复制三份
		fileName = className + ".addTest-result.xls";
		File resultFile = new File(path + fileName);
		if (resultFile.exists()) {
			logger.error(resultFile.getPath() + "文件已经存在！");
		} else {
			FileUtil.copyFile(file, resultFile);
		}

		fileName = className + ".updateTest-result.xls";
		resultFile = new File(path + fileName);
		if (resultFile.exists()) {
			logger.error(resultFile.getPath() + "文件已经存在！");
		} else {
			FileUtil.copyFile(file, resultFile);
		}

	}

	private Field[] getDeclaredField(Class<?> clazz) {
		Class<?> superClass = clazz.getSuperclass();
		Field[] superFfields = new Field[0];
		Field[] fields = new Field[0];
		Field[] result = new Field[0];
		if (superClass != Object.class) {
			superFfields = getDeclaredField(superClass);
		}
		fields = clazz.getDeclaredFields();
		result = new Field[superFfields.length + fields.length];
		System.arraycopy(superFfields, 0, result, 0, superFfields.length);
		System.arraycopy(fields, 0, result, superFfields.length, fields.length);
		return result;
	}

	private Table getTableInfo(String tableName) throws Exception {
		Table table = new Table();
		table.setTabName(tableName);
		String sql = "select   column_name,data_type,data_length   from   cols     WHERE   TABLE_name=upper('"
				+ tableName + "')  order by column_name";
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Column> columns = new ArrayList<Column>();
		while (resultSet.next()) {
			Column column = new Column();
			column.setColumnName(resultSet.getString("column_name"));
			column.setDataLength(Integer.parseInt(resultSet.getString("data_length")));
			column.setDataType(resultSet.getString("data_type"));
			columns.add(column);
		}
		table.setColumns(columns);
		return table;
	}

	class Table {
		private String tabName;
		private List<Column> columns;

		public String getTabName() {
			return tabName;
		}

		public void setTabName(String tabName) {
			this.tabName = tabName;
		}

		public List<Column> getColumns() {
			return columns;
		}

		public void setColumns(List<Column> columns) {
			this.columns = columns;
		}
	}

	class Column {
		private String columnName;
		private String dataType;
		private Integer dataLength;

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public String getDataType() {
			return dataType;
		}

		public void setDataType(String dataType) {
			this.dataType = dataType;
		}

		public Integer getDataLength() {
			return dataLength;
		}

		public void setDataLength(Integer dataLength) {
			this.dataLength = dataLength;
		}
	}
}
