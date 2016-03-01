package com.tianque.task.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import com.tianque.exception.base.SystemUtilException;

/**
 * 数据库操作类
 * 
 * @author welloncn
 * @version 1.2 2013-5-13
 * */
public class SqlHelper {
	/**
	 * 属性
	 * */
	private String url;
	private String user;
	private String password;
	private Statement sm;
	private PreparedStatement ps;
	private CallableStatement callSm;
	private ResultSet rs;
	private boolean isReset;
	private boolean isPool;
	private String poolName;
	private String type;

	private static Hashtable<String, String> drivers;
	private static Connection con;
	private static InitialContext initContext;
	private static DataSource ds;
	private static SqlHelper sh;
	static {
		sh = new SqlHelper();
		drivers = new Hashtable<String, String>();
		drivers.put("mysql", "com.mysql.jdbc.Driver");
		drivers.put("oracle", "oracle.jdbc.driver.OracleDriver");
	}

	/**
	 * 默认构造函数 私有化阻止外部实例化
	 * */
	private SqlHelper() {
	}

	/**
	 * 获取实例
	 * 
	 * @param url
	 *            String url地址
	 * @param user
	 *            String 数据库用户名
	 * @param password
	 *            String 密码
	 * @param type
	 *            String 数据库驱动类型
	 * @return SqlHelper
	 * @throws ClassNotFoundException
	 * */
	public static SqlHelper getInstance(String url, String user,
			String password, String type) {
		try {
			sh.url = url;
			sh.user = user;
			sh.password = password;
			if (drivers.containsKey(type)) {
				Class.forName(drivers.get(type));
				sh.type = type;
			} else {
				drivers.put(type.split("\\.")[1], type);
				Class.forName(type);
				sh.type = type.split("\\.")[1];
			}
			sh.isPool = false;
			return sh;
		} catch (Exception e) {
			throw new SystemUtilException("获取实例失败", e);
		}
	}

	/**
	 * 获取实例
	 * 
	 * @param poolName
	 *            String 连接池名称
	 * */
	public static SqlHelper getInstance(String poolName) {
		sh.poolName = poolName;
		sh.isPool = true;
		return sh;
	}

	/**
	 * 获取连接对象 内部可见
	 * 
	 * @return Connection 接口对象实例
	 * @throws SQLException
	 *             , NamingException
	 * */
	protected Connection getCon() throws SQLException, NamingException {
		if (con != null && !isReset) {
			return con;
		}
		if (isPool) {
			if (ds == null) {
				if (initContext == null) {
					initContext = new InitialContext();
				}
				ds = (DataSource) initContext.lookup("java:comp/env/"
						+ poolName);
			}
			con = ds.getConnection();
		} else {
			con = DriverManager.getConnection(url, user, password);
		}
		return con;
	}

	/**
	 * 获取链接对象 外部可见
	 * 
	 * @throws NamingException
	 * @throws SQLException
	 * */
	public Connection getConnection() {
		try {
			return getCon();
		} catch (Exception e) {
			throw new SystemUtilException("获取连接失败", e);
		}
	}

	/**
	 * 设置url
	 * 
	 * @param url
	 *            String url地址,形如：jdbc:mysql://localhost:3306/database;
	 * */
	public void setUrl(String url) {
		this.url = url;
		this.isPool = false;
	}

	/**
	 * 设置user
	 * 
	 * @param user
	 *            String 数据库用户名
	 * */
	public void setUser(String user) {
		this.user = user;
		this.isPool = false;
	}

	/**
	 * 设置password
	 * 
	 * @param password
	 *            String 密码
	 * */
	public void setPassword(String password) {
		this.password = password;
		this.isPool = false;
	}

	/**
	 * 设置连接池
	 * 
	 * @param poolName
	 *            String 连接池名称
	 * */
	public void setPoolName(String poolName) {
		this.poolName = poolName;
		this.isPool = true;
	}

	/**
	 * 设置是否重设
	 * 
	 * @param reset
	 *            boolean 是否重置
	 * */
	public void setReset(boolean isReset) {
		this.isReset = isReset;
	}

	/**
	 * 设置是否从连接池获取链接
	 * 
	 * @param isPool
	 *            boolean
	 * */
	public void setPool(boolean isPool) {
		this.isPool = isPool;
	}

	/**
	 * 设置数据库类型
	 * 
	 * @param String
	 *            type 数据库
	 * */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取数据库类型
	 * 
	 * @return String
	 * */
	public String getType() {
		return type;
	}

	/**
	 * 获取Statement
	 * 
	 * @return Statement 对象实例
	 * @throws NamingException
	 * */
	public Statement getSm() {
		try {
			getCon();
			if (sm == null || isReset) {
				if (sm != null) {
					sm.close();
					sm = null;
				}
				sm = con.createStatement();
			}
			return sm;
		} catch (Exception e) {
			throw new SystemUtilException("获取Statement失败", e);
		}
	}

	/**
	 * 获取PreparedStatement
	 * 
	 * @param sql
	 *            String sql语句
	 * @return PreparedStatement对象
	 * @throws NamingException
	 * */
	public PreparedStatement getPs(String sql) {
		try {
			getCon();
			if (ps != null) {
				ps.close();
				ps = null;
			}
			ps = con.prepareStatement(sql);
			return ps;
		} catch (Exception e) {
			throw new SystemUtilException("获取PreparedStatement失败", e);
		}
	}

	/**
	 * 获取创建的PreparedStatement
	 * */
	public PreparedStatement getPs() {
		return ps;
	}

	/**
	 * 获取CallableStatement
	 * 
	 * @param sql
	 *            sql语句
	 * @throws NamingException
	 * @throws SQLException
	 * */
	public CallableStatement getCallsm(String sql) {
		try {
			con = getCon();
			if (callSm != null) {
				callSm.close();
				callSm = null;
			}
			callSm = con.prepareCall(sql);
			return callSm;
		} catch (Exception e) {
			throw new SystemUtilException("获取CallableStatement失败", e);
		}
	}

	/**
	 * 获取创建的CallableStatement
	 * */
	public CallableStatement getCallsm() {
		return callSm;
	}

	/**
	 * 获取ResultSet
	 * 
	 * @return ResultSet
	 * */
	public ResultSet getRs() {
		return rs;
	}

	/**
	 * 关闭
	 * 
	 * @throws NamingException
	 * */
	public void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (sm != null) {
				sm.close();
				sm = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
			if (initContext != null) {
				initContext.close();
				initContext = null;
			}
			if (callSm != null) {
				callSm.close();
				callSm = null;
			}
			ds = null;
			drivers = null;
			System.gc();
		} catch (Exception e) {
			throw new SystemUtilException("关闭连接失败", e);
		}
	}

	/**
	 * 使用Statement 执行sql语句
	 * 
	 * @param sql
	 *            String 要执行的sql语句
	 * @return boolean true if the first result is a ResultSet object; false if
	 *         it is an update count or there are no results
	 * @throws SQLException
	 * @throws NamingException
	 * */
	public boolean execute(String sql) {
		try {
			return getSm().execute(sql);
		} catch (Exception e) {
			throw new SystemUtilException("SQL执行失败", e);
		}
	}

	/**
	 * 使用Statement 执行sql语句update,insert,delete
	 * 
	 * @param sql
	 *            String 要执行的sql语句
	 * @return int either (1) the row count for SQL Data Manipulation Language
	 *         (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 * @throws NamingException
	 * */
	public int executeUpdate(String sql) {
		try {
			return getSm().executeUpdate(sql);
		} catch (SQLException e) {
			throw new SystemUtilException("SQL执行失败", e);
		}
	}

	/**
	 * 使用PreparedStatement 执行sql语句
	 * 
	 * @param sql
	 *            String 要执行的sql语句
	 * @param args
	 *            Object 可变参数
	 * @return boolean true if the first result is a ResultSet object; false if
	 *         the first result is an update count or there is no result
	 * @throws SQLException
	 * @throws NamingException
	 * */
	public boolean prepareExecute(String sql, Object... args) {
		try {
			getPs(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setString(i + 1, args[i].toString());
			}
			return ps.execute();
		} catch (Exception e) {
			throw new SystemUtilException("SQL执行失败", e);
		}
	}

	/**
	 * 使用PreparedStatement 执行sql语句update,insert,delete
	 * 
	 * @param sql
	 *            String 要执行的sql语句
	 * @param args
	 *            Object 可变参数
	 * @return int either (1) the row count for SQL Data Manipulation Language
	 *         (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws SQLException
	 * @throws NamingException
	 * */
	public int prepareExecuteUpdate(String sql, Object... args) {
		try {
			getPs(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setString(i + 1, args[i].toString());
			}
			return ps.executeUpdate();
		} catch (Exception e) {
			throw new SystemUtilException("SQL执行失败", e);
		}
	}

	/**
	 * 使用Statement查询单个值
	 * 
	 * @param sql
	 *            String sql语句
	 * @return Object null if there is no ResultSet, first object if thers is a
	 *         ResultSet
	 * @throws SQLException
	 * @throws NamingException
	 * */
	public Object executeScaler(String sql) {
		try {
			rs = getSm().executeQuery(sql);
			if (rs.next()) {
				return rs.getObject(1);
			}
			return null;
		} catch (Exception e) {
			throw new SystemUtilException("SQL查询失败", e);
		}
	}

	/**
	 * 使用PreparedStatement查询单个值
	 * 
	 * @param sql
	 *            String sql语句
	 * @param args
	 *            Object 可变参数
	 * @return Object null if there is no ResultSet, first object if thers is a
	 *         ResultSet
	 * @throws SQLException
	 * @throws NamingException
	 * */
	public Object prepareExecuteScaler(String sql, Object... args) {
		try {
			ps = getPs(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getObject(1);
			}
			return null;
		} catch (Exception e) {
			throw new SystemUtilException("SQL查询失败", e);
		}
	}

	/**
	 * 使用Statement查询记录集
	 * 
	 * @param sql
	 *            String 查询语句
	 * @return ResultSet
	 * @throws SQLException
	 * @throws NamingException
	 * */
	public ResultSet executeQuery(String sql) {
		try {
			if (sm == null) {
				sm = getSm();
			}
			rs = sm.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			throw new SystemUtilException("SQL执行失败", e);
		}
	}

	/**
	 * 使用PreparedStatement查询记录集
	 * 
	 * @param sql
	 *            String sql语句
	 * @param args
	 *            Object 可变参数
	 * @return ResultSet
	 * @throws SQLException
	 * @throws NamingException
	 * */
	public ResultSet prepareExecuteQuery(String sql, Object... args) {
		try {
			ps = getPs(sql);
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			throw new SystemUtilException("SQL执行失败", e);
		}
	}

	/**
	 * 事务处理 Statement
	 * 
	 * @param sqls
	 *            String[] 一起执行的sql数组
	 * @return int[] an array of update counts containing one element for each
	 *         command in the batch. The elements of the array are ordered
	 *         according to the order in which commands were added to the batch.
	 * @throws SQLException
	 * @throws NamingException
	 * */
	public int[] transaction(String[] sqls) {
		try {
			getCon();
			con.setAutoCommit(false);
			getSm();
			for (String sql : sqls) {
				sm.addBatch(sql);
			}
			int[] re = sm.executeBatch();
			con.commit();
			return re;
		} catch (SQLException e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				throw new SystemUtilException("处理事务失败", e);
			}
			throw new SystemUtilException("处理事务失败", e);
		} catch (Exception e) {
			throw new SystemUtilException("处理事务失败", e);
		}
	}

	/**
	 * 事务处理
	 * 
	 * @param sqls
	 *            String[] 一起执行的sql数组
	 * @param args
	 *            ArrayList<Object[]> sql参数
	 * @return int[] an array of update counts containing one element for each
	 *         command in the batch. The elements of the array are ordered
	 *         according to the order in which commands were added to the batch.
	 * @throws SQLException
	 * @throws NamingException
	 * */
	public int[] prepareTransaction(String[] sqls, ArrayList<Object[]> args) {
		try {
			if (sqls.length <= 0) {
				return null;
			}
			getCon();
			con.setAutoCommit(false);
			ps = con.prepareStatement(sqls[0]);
			for (int i = 0; i < args.size(); i++) {
				Object[] os = args.get(i);
				if (i < sqls.length) {
					String sql = getPrepareSql(sqls[i], os);
					ps.addBatch(sql);
				} else {
					for (int j = 0; j < os.length; j++) {
						ps.setString(j + 1, String.valueOf(os[j]));
					}
					ps.addBatch();
				}
			}
			int[] re = ps.executeBatch();
			con.commit();
			return re;
		} catch (SQLException e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				throw new SystemUtilException("处理事务失败", e);
			}
			throw new SystemUtilException("处理事务失败", e);
		} catch (Exception e) {
			throw new SystemUtilException("处理事务失败", e);
		}
	}

	/**
	 * 获取prepareStatement拼接的sql语句
	 * 
	 * @param sql
	 *            String sql语句 形如 insert into table values(?,?,?)
	 * @param args
	 *            Object 可变参数数组
	 * */
	public String getPrepareSql(String sql, Object... args) {
		int i = sql.indexOf('?');
		int index = 0;
		while (i != -1 && index < args.length) {
			sql = sql.replaceFirst("\\?", "'" + args[index].toString() + "'");
			i = sql.indexOf('?');
			index++;
		}
		return sql;
	}

	/**
	 * 执行存储过程
	 * 
	 * @param procName
	 *            String 存储过程名称
	 * @param args
	 *            Object 可变参数
	 * @return boolean
	 * @throws SQLException
	 * @throws NamingException
	 * */
	public boolean procExecute(String procName, Object... args) {
		try {
			for (int i = 0; i < args.length; i++) {
				if (procName.indexOf("(") == -1) {
					procName += "(";
				}
				procName += "?,";
			}
			if (procName.endsWith(",")) {
				procName = procName.substring(0, procName.length() - 1) + ")";
			}
			procName = "{call " + procName + "}";
			getCallsm(procName);
			for (int i = 0; i < args.length; i++) {
				callSm.setObject(i + 1, args[i]);
			}
			callSm.execute();
			return true;
		} catch (Exception e) {
			throw new SystemUtilException("执行存储过程失败", e);
		}
	}

	/**
	 * 执行存储过程
	 * 
	 * @param procName
	 *            String 存储过程名称
	 * @param args
	 *            Object 可变参数
	 * @return ResultSet
	 * @throws SQLException
	 * @throws NamingException
	 * */
	public ResultSet procExecuteQuery(String procName, Object... args) {
		try {
			for (int i = 0; i < args.length; i++) {
				if (procName.indexOf("(") == -1) {
					procName += "(";
				}
				procName += "?,";
			}
			if (procName.endsWith(",")) {
				procName = procName + "?)";
			} else {
				procName = procName + "(?)";
			}
			procName = "{call " + procName + "}";
			getCallsm(procName);
			for (int i = 0; i < args.length; i++) {
				callSm.setObject(i + 1, args[i]);
			}
			int index = args.length + 1;
			callSm.registerOutParameter(index, OracleTypes.CURSOR);
			callSm.execute();
			rs = (ResultSet) callSm.getObject(index);
			return rs;
		} catch (Exception e) {
			throw new SystemUtilException("执行存储过程失败", e);
		}
	}

	/**
	 * 执行函数
	 * 
	 * @param funName
	 *            String 函数名称
	 * @param args
	 *            Object 可变参数
	 * @return Object
	 * @throws SQLException
	 * @throws NamingException
	 * */
	public Object funExecute(String funName, Object... args) {
		try {
			for (int i = 0; i < args.length; i++) {
				if (funName.indexOf("(") == -1) {
					funName += "(";
				}
				funName += "?,";
			}
			if (funName.endsWith(",")) {
				funName = funName.substring(0, funName.length() - 1) + ")";
			}
			funName = "{? = call " + funName + " }";
			getCallsm(funName);
			callSm.registerOutParameter(1, OracleTypes.VARCHAR);
			for (int i = 0; i < args.length; i++) {
				callSm.setObject(i + 2, args[i]);
			}
			callSm.executeUpdate();
			return callSm.getObject(1);
		} catch (Exception e) {
			throw new SystemUtilException("执行数据库函数失败", e);
		}
	}
}
