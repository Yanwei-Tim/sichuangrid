package com.tianque.sms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.lf5.util.DateFormatManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tianque.sms.dao.MobileShortMessageDAO;
import com.tianque.sms.domain.DownlinkMobileMessage;
import com.tianque.sms.domain.MobileShortMessage;
import com.tianque.sms.domain.SMSConfig;
import com.tianque.sms.domain.SMSTables;
import com.tianque.sms.service.MessageTableService;

@SuppressWarnings("unchecked")
@Repository("mobileShortMessageDAO")
public class MobileShortMessageDAOImpl implements MobileShortMessageDAO {

	private SMSConfig smsconfig;

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	private int mobileType;

	@Autowired
	private MessageTableService messageTableService;

	public void setMobileType(int mobileType) {
		this.mobileType = mobileType;
	}

	public int getMobileType() {
		return mobileType;
	}

	public void setSmsconfig(SMSConfig smsconfig) {
		this.smsconfig = smsconfig;
	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private static final String SEQUENCES_ID = "SELECT S_MobileShortMessage.NEXTVAL as id from dual";

	/**
	 * 从序列中获取一个值作为新纪录的ID
	 */
	public Long fetchIdBySequences() {
		Long id = 0L;
		id = jdbcTemplate.queryForLong(SEQUENCES_ID);
		return id;
	}

	@Override
	public MobileShortMessage add(MobileShortMessage message)
			throws SQLException {
		Long id = message.getId() == null ? fetchIdBySequences() : message
				.getId();
		String insertTableName = getNewTableName(id);
		message.setId(id);
		Object[] params = encapsulationParams(message);
		addBackUp(insertTableName, params, id);
		return this.getMobileShortMessage(insertTableName, id);
	}

	@Override
	public MobileShortMessage delete(Long id) {
		String insertTableName = getNewTableName(id);
		MobileShortMessage message = getMobileShortMessage(insertTableName, id);
		if (message != null) {
			deleteNewTable(insertTableName, id);
		}
		return message;
	}

	@Override
	public MobileShortMessage get(Long id) {
		String insertTableName = getNewTableName(id);
		String sql = "select * from " + insertTableName + " where id = ?";
		List<MobileShortMessage> list = this.jdbcTemplate.query(sql,
				new Object[] { id }, new MessageMapper());
		if (list.size() == 0) {
			return null;
		}
		return (MobileShortMessage) list.get(0);
	}

	@Override
	public MobileShortMessage update(MobileShortMessage message) {
		Object[] params = new Object[] { message.getFromId(),
				message.getFromSystem(), message.getServerId(),
				message.getMessage(), message.isRequestReport() ? 1 : 0,
				message.getSender(), message.getReceiver(),
				message.getStatus(), message.getSentTime(),
				message.getCreateTime(), message.getPriority(), message.getId() };
		String updateTableName = getNewTableName(message.getId().longValue());
		return updateNewTable(updateTableName, params, message.getId());
	}

	private final static class MessageMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			if (rs == null) {
				return null;
			}
			MobileShortMessage message = new MobileShortMessage();
			message.setId(rs.getLong("id"));
			message.setFromId(rs.getLong("fromId"));
			message.setFromSystem(rs.getString("fromSystem"));
			message.setMessage(rs.getString("message"));
			message.setRequestReport(rs.getInt("requestReport") == 1);
			message.setSender(rs.getString("sender"));
			message.setReceiver(rs.getString("receiver"));
			message.setStatus(rs.getInt("status"));
			try {
				if (rs.getDate("sentTime") != null)
					message.setSentTime(new DateFormatManager().parse(
							rs.getString("sentTime"), "yyyy-MM-dd HH:mm:ss"));
				if (rs.getDate("createTime") != null)
					message.setCreateTime(new DateFormatManager().parse(
							rs.getString("createTime"), "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
			}
			message.setPriority(rs.getInt("priority"));
			message.setType(rs.getInt("type"));
			return message;
		}
	}

	private String getNewTableName(long id) {
		String insertTableName = "";
		try {
			insertTableName = messageTableService.getInsertTableName(id, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertTableName;
	}

	/**
	 * 封装参数
	 */
	private Object[] encapsulationParams(MobileShortMessage message) {
		Object[] params = new Object[] { message.getId(), message.getFromId(),
				message.getFromSystem(), message.getServerId(),
				message.getMessage(), message.isRequestReport() ? 1 : 0,
				message.getSender(), message.getReceiver(),
				message.getStatus(), message.getSentTime(),
				message.getCreateTime(), message.getPriority(),
				message.getType() };
		return params;
	}

	/**
	 * 将数据插入到新表
	 */
	public MobileShortMessage addBackUp(String insertTableName,
			Object[] params, long id) {
		String sql = "INSERT INTO "
				+ insertTableName
				+ "(ID,fromId,fromSystem,serverId,message,requestReport,sender,receiver,status,sentTime,createTime, priority,type) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		this.jdbcTemplate.update(sql, params);
		return this.getMobileShortMessage(insertTableName, id);
	}

	/**
	 * 根据id查找备份表中的数据
	 * 
	 * @param insertTableName
	 *            备份表明
	 */
	public MobileShortMessage getMobileShortMessage(String insertTableName,
			Long id) {

		String sql = "select * from " + insertTableName + " where id = ?";
		List<MobileShortMessage> list = this.jdbcTemplate.query(sql,
				new Object[] { id }, new MessageMapper());
		if (list.size() == 0) {
			return null;
		}
		return (MobileShortMessage) list.get(0);
	}

	private MobileShortMessage updateNewTable(String updateTableName,
			Object[] params, long id) {
		StringBuffer sb = new StringBuffer();
		sb.append("update " + updateTableName + " set ")
				.append("fromId = ?, fromSystem = ?, serverId = ?, message = ?, ")
				.append("requestReport = ?,sender = ?, receiver = ?,status = ?, sentTime = ?,")
				.append("createTime = ?, priority = ? ").append("where id = ?");

		if (this.jdbcTemplate.update(sb.toString(), params) > 0)
			return getMobileShortMessage(updateTableName, id);
		else
			return null;
	}

	private MobileShortMessage deleteNewTable(String deleteTableName, Long id) {
		MobileShortMessage message = getMobileShortMessage(deleteTableName, id);
		if (message != null) {
			StringBuffer sb = new StringBuffer();
			sb.append("delete from " + deleteTableName + " where id = ?");
			Object[] params = new Object[] { id };
			this.jdbcTemplate.update(sb.toString(), params);
		}
		return message;
	}

	/**
	 * 根据表明查询未发送成功的数据
	 */
	@Override
	public List<MobileShortMessage> getListByStatus(SMSTables smsTables,
			int start, int end, int status) {
		String sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select * from "
				+ smsTables.getTabName()
				+ " where status ="
				+ status
				+ " order by priority asc) A WHERE ROWNUM <= "
				+ end
				+ ") WHERE RN >= " + start;
		List<MobileShortMessage> list = this.jdbcTemplate.query(sql,
				new MessageMapper());
		return list;
	}

	@Override
	public List<MobileShortMessage> getNoSendTableList(List<SMSTables> list,
			int status) throws Exception {
		List<MobileShortMessage> mobileShortMessageList = new ArrayList<MobileShortMessage>();
		if (list != null) {
			if (list.size() > 0) {
				SMSTables smsTables = (SMSTables) list.get(list.size() - 1);
				mobileShortMessageList = this.getListByStatus(smsTables, 0,
						smsconfig.getSmsLoaderCount(), status);
			}
		}
		return mobileShortMessageList;
	}

	@Override
	public int getCountByStatus(String insertTableName) {
		String sql = "select count(*) from " + insertTableName
				+ " where status = " + MobileShortMessage.WAITING_TO_SEND
				+ " or status = " + MobileShortMessage.IS_SENDING;
		int count = this.jdbcTemplate.queryForInt(sql);
		return count;
	}

	@Override
	public List<MobileShortMessage> getSendTableList(String tabName,
			Long fromId, int status) throws Exception {
		int end = 1000;
		String sql = "SELECT * FROM (select * from " + tabName
				+ " where status > " + status + " and fromId >=" + fromId
				+ " order by id asc) A WHERE ROWNUM <= " + end;
		return jdbcTemplate.query(sql, new MessageMapper());
	}

	private final class DownMessageMapper implements RowMapper {

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			DownlinkMobileMessage message = new DownlinkMobileMessage();
			message.setId(rs.getLong("id"));
			message.setToId(rs.getLong("toId"));
			message.setToSystem(rs.getString("toSystem"));
			message.setServerId(rs.getString("serverId"));
			message.setMessage(rs.getString("message"));
			message.setSender(rs.getString("sender"));
			message.setReceiver(rs.getString("receiver"));
			try {
				if (rs.getDate("receiveTime") != null)
					message.setReceiveTime(new DateFormatManager().parse(
							rs.getString("receiveTime"), "yyyy-MM-dd HH:mm:ss"));
				if (rs.getDate("startDealTime") != null)
					message.setStartDealTime(new DateFormatManager().parse(
							rs.getString("startDealTime"),
							"yyyy-MM-dd HH:mm:ss"));
				if (rs.getDate("processTime") != null)
					message.setProcessTime(new DateFormatManager().parse(
							rs.getString("processTime"), "yyyy-MM-dd HH:mm:ss"));

			} catch (ParseException e) {
				;
			}
			return message;
		}
	}

	@Override
	public List<DownlinkMobileMessage> getSmsDownlinkList(Long bigId,
			String tableName) throws Exception {
		// StringBuffer sb = new StringBuffer();
		// sb.append("select * from  " + tableName + " where id >= " + bigId
		// + " and ROWNUM <= 1000 order by id asc ");
		String sql = "SELECT * FROM (select * from " + tableName
				+ " where  id >" + bigId
				+ " order by id asc) A WHERE ROWNUM <= 1000 ";
		return jdbcTemplate.query(sql, new DownMessageMapper());
	}
}
