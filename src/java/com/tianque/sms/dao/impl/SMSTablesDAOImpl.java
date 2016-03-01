package com.tianque.sms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.lf5.util.DateFormatManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tianque.sms.dao.SMSTablesDAO;
import com.tianque.sms.domain.SMSTables;

@Repository("smsTablesDAO")
public class SMSTablesDAOImpl implements SMSTablesDAO {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	private static final String INSERT_SQL = "INSERT INTO SMSTables(tabName,smsID_begin,smsID_end,type,createTime,status,id) values (?,?,?,?,?,?,?)";

	private static final String SEQUENCES_ID = "SELECT S_SMSTables.NEXTVAL as id from dual";

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 从序列中获取一个值作为新纪录的ID
	 */
	public Integer fetchIdBySequences() {
		int id = 0;
		id = jdbcTemplate.queryForInt(SEQUENCES_ID);
		return id;
	}

	@Override
	public SMSTables add(SMSTables smstables) throws Exception {
		int id = fetchIdBySequences();
		smstables.setId(id);
		Object[] parameters = encapsulationParams(smstables);
		this.jdbcTemplate.update(INSERT_SQL, parameters);
		return this.get(smstables.getId());

	}

	private Object[] encapsulationParams(SMSTables smstables) {
		Object[] parameters = new Object[] { smstables.getTabName(),
				smstables.getSmsID_begin(), smstables.getSmsID_end(),
				smstables.getType(), smstables.getCreateTime(),
				smstables.getStatus(), smstables.getId() };
		return parameters;
	}

	@Override
	public SMSTables update(SMSTables smstables) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update SMSTables set ")
				.append("tabName = ?, smsID_begin = ?, smsID_end = ?, type = ?, ")
				.append("createTime = ?,status = ?").append(" where id = ?");

		Object[] params = encapsulationParams(smstables);

		if (this.jdbcTemplate.update(sb.toString(), params) > 0)
			return get(smstables.getId());
		else
			return null;

	}

	@Override
	public SMSTables get(int id) throws Exception {
		String sql = "select * from SMSTables where id = ?";
		@SuppressWarnings("unchecked")
		List<SMSTables> list = this.jdbcTemplate.query(sql,
				new Object[] { id }, new TableMapper());
		if (list.size() == 0) {
			return null;
		}
		return (SMSTables) list.get(0);

	}

	@Override
	public SMSTables delete(int id) throws Exception {
		SMSTables smstables = get(id);
		if (smstables != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("delete from SMSTables where id = ?");
			Object[] params = new Object[] { id };
			this.jdbcTemplate.update(sb.toString(), params);
		}
		return smstables;

	}

	@Override
	public List<SMSTables> findSenderMessageTablesWithSMID(Long smsID)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from SMSTables where type = 1 and smsID_begin <= ? and smsID_end >= ?");
		@SuppressWarnings("unchecked")
		List<SMSTables> list = this.jdbcTemplate.query(sb.toString(),
				new Object[] { smsID, smsID }, new TableMapper());
		return list;
	}

	@Override
	public List<SMSTables> findReceiveMessageTablesWithSMID(Long smsID)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from SMSTables where type = 2 and smsID_begin <= ? and smsID_end >= ?");
		@SuppressWarnings("unchecked")
		List<SMSTables> list = this.jdbcTemplate.query(sb.toString(),
				new Object[] { smsID, smsID }, new TableMapper());
		return list;
	}

	private static final class TableMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			if (rs == null) {
				return null;
			}
			SMSTables smstables = new SMSTables();
			smstables.setId(rs.getInt("id"));
			smstables.setTabName(rs.getString("tabName"));
			smstables.setSmsID_begin(rs.getLong("smsID_begin"));
			smstables.setSmsID_end(rs.getLong("smsID_end"));
			smstables.setType(rs.getInt("type"));
			smstables.setStatus(rs.getInt("status"));
			smstables.setIsenddeal(rs.getInt("isenddeal"));
			try {
				if (rs.getDate("createTime") != null)
					smstables.setCreateTime(new DateFormatManager().parse(
							rs.getString("createTime"), "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
			}
			return smstables;
		}
	}

	@Override
	public void createTableBySmsTable(String newTableName, int type) {
		boolean mark = isExistsNewTable(newTableName);
		if (!mark) {
			String crateTableSql = "";
			if (type == 1) {
				crateTableSql = this.createMobileShortMessageSql(newTableName);
			} else if (type == 2) {
				crateTableSql = this.createUpMobileMessageSql(newTableName);
			}
			this.jdbcTemplate.execute(crateTableSql);
		}
	}

	public String createMobileShortMessageSql(String newTableName) {
		String createMSMSql = "create table "
				+ newTableName
				+ " (ID NUMBER not null,fromId  NUMBER,fromSystem VARCHAR2(50),serverId VARCHAR2(20),message CLOB,requestReport CHAR(1) default 0,sender VARCHAR2(32),receiver VARCHAR2(32),status NUMBER default 1,sentTime DATE,createTime DATE default sysdate,priority NUMBER default 8,type NUMBER)";
		return createMSMSql;
	}

	public String createUpMobileMessageSql(String newTableName) {
		String createUMMSql = "create table "
				+ newTableName
				+ "(ID NUMBER not null,TOID NUMBER,TOSYSTEM VARCHAR2(32),SERVERID VARCHAR2(32),MESSAGE CLOB,SENDER VARCHAR2(32) not null,RECEIVER VARCHAR2(32),RECEIVETIME DATE not null,STARTDEALTIME DATE,PROCESSTIME DATE)";
		return createUMMSql;
	}

	@Override
	public void dropTableByName(String tableName) {
		this.jdbcTemplate.execute("drop table " + tableName
				+ " cascade constraints");
	}

	@Override
	public boolean isExistsNewTable(String tableName) {
		boolean mark = false;
		int ifExists = this.jdbcTemplate
				.queryForInt("select count(*) from user_tables where TABLE_NAME='"
						+ tableName.toUpperCase() + "'");
		if (ifExists > 0) {
			mark = true;
		}
		return mark;
	}

	/**
	 * 获得未发送完成列表
	 */
	@Override
	public List<SMSTables> getWaitingTableName() throws Exception {
		String sql = "select * from SMSTables where type = 1 and status !=1 order by CREATETIME desc";
		@SuppressWarnings("unchecked")
		List<SMSTables> list = this.jdbcTemplate.query(sql, new TableMapper());
		return list;
	}

	@Override
	public List<SMSTables> getWaitingDealTableNameForUpMobileMessage()
			throws Exception {
		String sql = "select * from SMSTables where type = 2 and isenddeal =0 order by CREATETIME desc";
		@SuppressWarnings("unchecked")
		List<SMSTables> list = this.jdbcTemplate.query(sql, new TableMapper());
		return list;
	}

	@Override
	public void makeTableIsDealCompleteByTableNameForUpMobileMessage(
			String tablename) {
		this.jdbcTemplate
				.execute("update SMSTables set isenddeal=1 where tabName= '"
						+ tablename + "' and status=1 ");
	}
}
