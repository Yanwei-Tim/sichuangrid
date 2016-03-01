package com.tianque.sms.dao;

import java.util.List;

import com.tianque.sms.domain.SMSTables;

/**
 * 短信管理总表DAO
 */
public interface SMSTablesDAO {
	public SMSTables add(SMSTables smstables) throws Exception;

	public SMSTables update(SMSTables message) throws Exception;

	public SMSTables get(int id) throws Exception;

	public SMSTables delete(int id) throws Exception;

	public List<SMSTables> findSenderMessageTablesWithSMID(Long smsID)
			throws Exception;

	public List<SMSTables> findReceiveMessageTablesWithSMID(Long smsID)
			throws Exception;

	public void createTableBySmsTable(String tableName, int type)
			throws Exception;

	public void dropTableByName(String tableName) throws Exception;

	public boolean isExistsNewTable(String tableName);

	public List<?> getWaitingTableName() throws Exception;

	public List<SMSTables> getWaitingDealTableNameForUpMobileMessage()
			throws Exception;

	public void makeTableIsDealCompleteByTableNameForUpMobileMessage(
			String tablename);
}
