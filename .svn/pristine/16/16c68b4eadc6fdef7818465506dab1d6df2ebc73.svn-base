package com.tianque.base;

import java.sql.Connection;
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

import com.tianque.init.ContextType;
import com.tianque.init.impl.DatabaseInitialization;
import com.tianque.plugin.init.DatabasePluginInitialization;
import com.tianque.util.SqlScriptExcuteUtil;

public class TestDatabaseInit {
	public final static Logger logger = LoggerFactory.getLogger(TestDatabaseInit.class);

	public static void rebuildDatabase() throws Exception {
		DatabaseInitialization databaseInitialization = new DatabaseInitialization(ContextType.test);
		DatabasePluginInitialization databasePluginInitialization = new DatabasePluginInitialization(
				ContextType.test);
		databaseInitialization.init();
		databasePluginInitialization.init();

		databaseInitialization.alterTableCache();

	}

	public static void init(DataSource dataSource) throws Exception {
		disableConstraint(dataSource);
		clearOldTables(dataSource);
		enableConstraint(dataSource);
		logger.info("数据表Rebuilder完成!");
	}

	private static void disableConstraint(DataSource dataSource) throws Exception {
		List<String> sqls = new ArrayList<String>();
		if (null != TestDatabaseInit.map.get("disableConstraint")
				&& TestDatabaseInit.map.get("disableConstraint").length > 0) {
			Object[] sqlArray = TestDatabaseInit.map.get("disableConstraint");
			for (int i = 0; i < sqlArray.length; i++) {
				sqls.add((String) sqlArray[i]);
			}
		} else {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery(" select 'alter table '||table_name||' disable constraint '||constraint_name from user_constraints where constraint_type='R'");
			while (resultSet.next()) {
				sqls.add(resultSet.getString(1));
			}
			statement.close();
			connection.close();
			TestDatabaseInit.map.put("disableConstraint", sqls.toArray());
		}
		SqlScriptExcuteUtil.executeBatchSql(sqls, dataSource);
	}

	private static void enableConstraint(DataSource dataSource) throws Exception {
		List<String> sqls = new ArrayList<String>();
		if (null != TestDatabaseInit.map.get("enableConstraint")
				&& TestDatabaseInit.map.get("enableConstraint").length > 0) {
			Object[] sqlArray = TestDatabaseInit.map.get("enableConstraint");
			for (int i = 0; i < sqlArray.length; i++) {
				sqls.add((String) sqlArray[i]);
			}
		} else {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select 'alter table '||table_name||' enable constraint '||constraint_name from user_constraints where constraint_type='R'");
			while (resultSet.next()) {
				sqls.add(resultSet.getString(1));
			}
			statement.close();
			connection.close();
			TestDatabaseInit.map.put("enableConstraint", sqls.toArray());
		}
		SqlScriptExcuteUtil.executeBatchSql(sqls, dataSource);
	}

	private static void clearOldTables(DataSource dataSource) throws Exception {
		List<String> sqls = new ArrayList<String>();
		if (null != TestDatabaseInit.map.get("clearOldTables")
				&& TestDatabaseInit.map.get("clearOldTables").length > 0) {
			Object[] sqlArray = TestDatabaseInit.map.get("clearOldTables");
			for (int i = 0; i < sqlArray.length; i++) {
				sqls.add((String) sqlArray[i]);
			}
		} else {
			sqls = loadSqlFile();
			TestDatabaseInit.map.put("clearOldTables", sqls.toArray());
		}
		SqlScriptExcuteUtil.executeBatchSql(sqls, dataSource);
	}

	private static List<String> loadSqlFile() throws Exception {
		DataSource dataSource = SqlScriptExcuteUtil.getDataSource(ContextType.test);
		Connection connection = dataSource.getConnection();
		List<String> tables = getOracleObjectNames("table", connection);
		connection.close();

		List<String> sqlList = new ArrayList<String>();
		for (String table : tables) {
			if (table.toLowerCase().indexOf("c3p0") < 0)
				sqlList.add("delete from " + table);
		}
		connection.close();
		return sqlList;
	}

	private static List<String> getOracleObjectNames(String objectType, Connection connection)
			throws SQLException {
		String sql = "select " + objectType + "_name from user_" + objectType + "s ";
		List<String> oracleObjects = new ArrayList<String>();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			oracleObjects.add(resultSet.getString(objectType + "_name"));
		}
		statement.close();
		return oracleObjects;
	}

	public static Map<String, Object[]> map = new HashMap<String, Object[]>();

}
