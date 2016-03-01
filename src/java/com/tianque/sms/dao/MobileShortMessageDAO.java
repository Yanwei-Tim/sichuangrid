package com.tianque.sms.dao;

import java.util.List;

import com.tianque.sms.domain.MobileShortMessage;
import com.tianque.sms.domain.SMSTables;
import com.tianque.sms.domain.DownlinkMobileMessage;

public interface MobileShortMessageDAO {
	public MobileShortMessage add(MobileShortMessage message) throws Exception;

	public MobileShortMessage update(MobileShortMessage message)
			throws Exception;

	public MobileShortMessage get(Long id) throws Exception;

	public MobileShortMessage delete(Long id) throws Exception;

	/**
	 * 根据表明查询未发送成功的数据
	 */
	public List<MobileShortMessage> getListByStatus(SMSTables smsTables,
			int start, int end, int status) throws Exception;

	/**
	 * 从已发送的列表中挑取数据
	 */
	public List<MobileShortMessage> getSendTableList(String tabName, Long id,
			int status) throws Exception;

	/**
	 * 从未发送的列表中挑取数据
	 */
	public List<MobileShortMessage> getNoSendTableList(List<SMSTables> list,
			int status) throws Exception;

	/**
	 * 根据表名返回该表下是否有未发送的数据条数
	 */
	public int getCountByStatus(String insertTableName);

	public List<DownlinkMobileMessage> getSmsDownlinkList(Long bigId, String tableName)
			throws Exception;
}
