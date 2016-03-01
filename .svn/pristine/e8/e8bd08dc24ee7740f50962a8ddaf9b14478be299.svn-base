package com.tianque.sms.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.lf5.util.DateFormatManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.sms.dao.SMSTablesDAO;
import com.tianque.sms.domain.SMSTables;
import com.tianque.sms.service.MessageTableService;
import com.tianque.sms.util.SmsGlobalValue;

/**
 * @description 短信发送总表管理
 */
@Repository("messageTableService")
public class MessageTableServiceImpl implements MessageTableService {

	@Autowired
	private SMSTablesDAO smsTablesDAO;

	/**
	 * @description 根据预插入短信的ID和短信类型来判断是否需要生成新的指定类型表
	 * @param id需要插入信息的ID
	 * @param type
	 *            需要插入信息的类型
	 * @return tableName 不需要生成新的表,则返回适合此ID的表名
	 * @return tableName 插入数据到返回的名为tableName表
	 */
	@Override
	public String getInsertTableName(Long id, int type) throws Exception {
		String tableName = "";

		if (type == 1) {
			List<SMSTables> SenderList = smsTablesDAO
					.findSenderMessageTablesWithSMID(id);
			if (SenderList.size() <= 0) {
				SMSTables newSMSTable = smsTablesDAO.add(createNewSMSTable(id,
						type));
				tableName = newSMSTable.getTabName();
				smsTablesDAO.createTableBySmsTable(tableName, type);
			} else {
				tableName = SenderList.get(0).getTabName();
			}
		}
		if (type == 2) {
			List<SMSTables> ReceiverList = smsTablesDAO
					.findReceiveMessageTablesWithSMID(id);
			if (ReceiverList.size() <= 0) {
				SMSTables newSMSTable = smsTablesDAO.add(createNewSMSTable(id,
						type));
				tableName = newSMSTable.getTabName();
				smsTablesDAO.createTableBySmsTable(tableName, type);
			} else {
				tableName = ReceiverList.get(0).getTabName();
			}
		}

		return tableName;
	}

	/**
	 * @description 获得新增表的bean
	 */
	private SMSTables createNewSMSTable(Long id, int type)
			throws ParseException {
		Object[] range = getIDRange(id);
		int number = Integer.parseInt(range[0].toString());
		Long smsID_begin = (Long) range[1];
		Long smsID_end = (Long) range[2];
		Date now = new Timestamp(System.currentTimeMillis());
		Date createTime = new DateFormatManager().parse(now.toString(),
				"yyyy-MM-dd HH:mm:ss");
		String oldTableName = getTableNameFromType(type);
		SMSTables newSMSTable = new SMSTables();

		newSMSTable.setTabName(oldTableName + "_" + number);
		newSMSTable.setSmsID_begin(smsID_begin);
		newSMSTable.setSmsID_end(smsID_end);
		newSMSTable.setType(type);
		newSMSTable.setCreateTime(createTime);
		return newSMSTable;
	}

	/**
	 * @description 根据短信类型判断短信表名，可用此表名生成带号码的表
	 * @param type
	 *            短信类型
	 * @return oldTableName 表名
	 */
	private String getTableNameFromType(int type) {
		String oldTableName = "";
		if (type == 1) {
			oldTableName = SmsGlobalValue.SENDER_TABLENAME;
		}
		if (type == 2) {
			oldTableName = SmsGlobalValue.RECEIVE_TABLENAME;
		}
		return oldTableName;
	}

	/**
	 * @description 根据短信ID确认此ID以一定长度为标准所在的ID范围
	 * @param id
	 *            传入的短信ID
	 * @return range 返回此ID所处的范围
	 */
	private Object[] getIDRange(Long id) {
		Object[] range = new Object[3];
		Long rangeValue = SmsGlobalValue.SMSTABLES_MAXVALUE;
		int number = 0;
		int count = 0;
		Long begin = 0L;
		Long end = 0L;
		if (id < SmsGlobalValue.SMSTABLES_MAXVALUE) {
			number = 1;
			begin = 1L;
			end = SmsGlobalValue.SMSTABLES_MAXVALUE;
		} else {
			count = (int) Math.floor(id / rangeValue);
			number = count;
			begin = (Long.parseLong(String.valueOf(count * rangeValue)) + 1);
			end = (Long.parseLong(String.valueOf(count * rangeValue)))
					+ rangeValue;
		}
		range[0] = number;
		range[1] = begin;
		range[2] = end;
		return range;
	}
}
