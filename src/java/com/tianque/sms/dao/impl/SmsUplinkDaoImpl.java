package com.tianque.sms.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.sms.dao.SmsUplinkDao;
import com.tianque.sms.domain.SmsUplink;
import com.tianque.sms.domain.vo.SearchSmsUplinkVo;
import com.tianque.sms.domain.vo.SmsJobSqlVo;
import com.tianque.sms.domain.vo.SmsSendObjectVo;

/**
 * 短信发件箱:数据操作层
 * 
 * @author
 * @date 2013-07-02 09:53:32
 */
@Repository("smsUplinkDao")
public class SmsUplinkDaoImpl extends BaseDaoImpl<SmsUplink, SearchSmsUplinkVo>
		implements SmsUplinkDao {

	@Override
	public int countSmsSendObjectNumber(String sql) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"smsUplink.countSmsSendObject", sql);
	}

	@Override
	public List<SmsSendObjectVo> findSmsSendObject(String sql) {

		return (List<SmsSendObjectVo>) getSqlMapClientTemplate().queryForList(
				"smsUplink.findSmsSendObject", sql);
	}

	@Override
	public SmsJobSqlVo addSmsJobSqlVo(SmsJobSqlVo smsJobSqlVo) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"smsUplink.addSmsJobSqlVo", smsJobSqlVo);
		return getSmsJobSqlVoById(id);
	}

	@Override
	public SmsJobSqlVo getSmsJobSqlVoById(Long id) {
		return (SmsJobSqlVo) getSqlMapClientTemplate().queryForObject(
				"smsUplink.getSmsJobSqlVoById", id);
	}

	@Override
	public List<SmsJobSqlVo> findSmsJobSqlVoBySmsLevel() {
		return (List<SmsJobSqlVo>) getSqlMapClientTemplate().queryForList(
				"smsUplink.findSmsJobSqlVoBySmsLevel");
	}

	@Override
	public SmsJobSqlVo updateSmsJobSqlVo(SmsJobSqlVo smsJobSqlVo) {
		int count = getSqlMapClientTemplate().update(
				"smsUplink.updateSmsJobSqlVo", smsJobSqlVo);
		if (count > 0) {
			return getSmsJobSqlVoById(smsJobSqlVo.getId());
		} else {
			return null;

		}
	}

	@Override
	public void addSmsUplinkByBatch(List<SmsUplink> smsUplinkList) {
		super.batchInsertDate(smsUplinkList, "smsUplink.addSmsUplink");
	}

	@Override
	public void updateSmsUplinkStatusByBatch(List<SmsUplink> smsUplinkList) {
		batchUpdateStatus(smsUplinkList,
				"smsUplink.updateSmsUplinkStatusByBatch");

	}

	@Override
	public PageInfo<SmsUplink> findSubSmsUplinksBySearchVo(
			SearchSmsUplinkVo searchVo, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		searchVo.setSortField(sortField);
		searchVo.setOrder(order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"smsUplink.countSubSmsUplinksBySearchVo", searchVo);
		List<SmsUplink> resultList = getSqlMapClientTemplate().queryForList(
				"smsUplink.findSubSmsUplinksBySearchVo", searchVo,
				(pageNum - 1) * pageSize, pageSize);
		return new PageInfo<SmsUplink>(pageNum, pageSize, countNum, resultList);
	}

	@Override
	public SmsUplink getSmsUplinkByMinIdAndProcessed() {
		return (SmsUplink) getSqlMapClientTemplate().queryForObject(
				"smsUplink.getSmsUplinkByMinIdAndProcessed");
	}

	@Override
	public SmsUplink getSmsUplinkByMessageId(Long messageId) {
		return (SmsUplink) getSqlMapClientTemplate().queryForObject(
				"smsUplink.getSmsUplinkByMessageId", messageId);
	}

	@Override
	public List<SmsUplink> getSmsUplinkListBySendStatus(Integer number) {
		return (List<SmsUplink>) getSqlMapClientTemplate().queryForList(
				"smsUplink.getSmsUplinkListBySendStatus", number);
	}

	@Override
	public PageInfo<SmsSendObjectVo> findSmsSendPeopleInfoPager(String sql,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		map.put("sortField", sortField);
		map.put("order", order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"smsUplink.countSmsSendPeopleInfoPager", map);
		List<SmsSendObjectVo> resultList = getSqlMapClientTemplate()
				.queryForList("smsUplink.findSmsSendPeopleInfoPager", map,
						(pageNum - 1) * pageSize, pageSize);
		return new PageInfo<SmsSendObjectVo>(pageNum, pageSize, countNum,
				resultList);
	}

	@Override
	public void updateDeleteStatusSmsUplinkByReceiverMobile(
			String receiverMobile) {
		getSqlMapClientTemplate().delete(
				"smsUplink.updateDeleteStatusSmsUplinkByReceiverMobile",
				receiverMobile);

	}

	@Override
	public boolean updateDeleteStatusByIds(String ids) {
		return getSqlMapClientTemplate().update(
				"smsUplink.updateDeleteStatusByIds", ids) > 0;
	}

	private void batchUpdateStatus(List datas, String sqlAlias) {
		List batchList = new ArrayList();
		for (int index = 0; index < datas.size(); index++) {
			batchList.add(datas.get(index));
			if (index != 0 && index % 500 == 0) {
				batchUpdateFor500(batchList, sqlAlias);
				batchList.clear();
			}
		}
		if (batchList.size() > 0) {
			batchUpdateFor500(batchList, sqlAlias);
		}

	}

	private void batchUpdateFor500(final List datas, final String sqlAlias) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			@Override
			public Object doInSqlMapClient(SqlMapExecutor excutor)
					throws SQLException {
				excutor.startBatch();
				for (Object data : datas) {
					excutor.update(sqlAlias, data);
				}
				excutor.executeBatch();
				return null;
			}
		});
	}

	@Override
	public List<Map<String, Object>> findDifferPhoneTypeMessagesNumberByOrgIds(
			String orgIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgIds", orgIds);
		map.put("startDate", CalendarUtil.today());
		map.put("endDate", CalendarUtil.getTomorrow());
		return getSqlMapClientTemplate().queryForList(
				"smsUplink.findDifferPhoneTypeMessagesNumberByOrgIds", map);
	}

	@Override
	public boolean updateRestoreDeleteStatusById(Long id) {
		return getSqlMapClientTemplate().update(
				"smsUplink.updateRestoreDeleteStatusById", id) > 0;
	}

}
