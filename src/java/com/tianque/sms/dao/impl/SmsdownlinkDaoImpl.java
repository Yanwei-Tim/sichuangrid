package com.tianque.sms.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.sms.dao.SmsdownlinkDao;
import com.tianque.sms.domain.Smsdownlink;
import com.tianque.sms.domain.vo.SearchSmsdownlinkVo;

/**
 * 短信收件箱:数据操作层
 * 
 * @author
 * @date 2013-07-08 17:27:15
 */
@Repository("smsdownlinkDao")
public class SmsdownlinkDaoImpl extends
		BaseDaoImpl<Smsdownlink, SearchSmsdownlinkVo> implements SmsdownlinkDao {

	@Override
	public void addSmsdownlinkByBatch(List<Smsdownlink> datas) {
		super.batchInsertDate(datas, "smsdownlink.addSmsdownlink");
	}

	@Override
	public void addSmsdownlinkBigId(Long id) {
		getSqlMapClientTemplate().insert("smsdownlink.addSmsdownlinkBigId", id);
	}

	@Override
	public void updateSmsdownlinkBigId(Long id) {
		getSqlMapClientTemplate().update("smsdownlink.updateSmsdownlinkBigId",
				id);
	}

	@Override
	public Long getSmsdownlinkBigId() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"smsdownlink.getSmsdownlinkBigId");
	}

	@Override
	public List<Smsdownlink> findSmsdownlinkBySender(Long isRead,
			String sender, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("sender", sender);
		map.put("isRead", isRead);

		return getSqlMapClientTemplate().queryForList(
				"smsdownlink.findSmsdownlinkBySender", map,
				(pageNum - 1) * pageSize, pageSize);
	}

	@Override
	public Integer countSmsdownlinkBySender(String sender) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"smsdownlink.countSmsdownlinkBySender", sender);
	}

	@Override
	public List<Smsdownlink> getSmsdownlinkBySender(String sender) {
		return getSqlMapClientTemplate().queryForList(
				"smsdownlink.getSmsdownlinkBySender", sender);
	}

	@Override
	public List<Smsdownlink> findSmsContentBySender(String phone) {

		return getSqlMapClientTemplate().queryForList(
				"smsdownlink.getSmsContentByPhone", phone);
	}

	@Override
	public void updateDeleteStatusSmsdownlinkBySender(String sender) {
		getSqlMapClientTemplate().delete(
				"smsdownlink.updateDeleteStatusSmsdownlinkBySender", sender);

	}

	@Override
	public void updateSmsDownLinkBySender(Long isRead, String sender) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isRead", isRead);
		map.put("sender", sender);
		getSqlMapClientTemplate().update(
				"smsdownlink.updateSmsDownLinkBySender", map);

	}

	@Override
	public boolean updateDeleteStatusByIds(String ids) {
		return getSqlMapClientTemplate().update(
				"smsdownlink.updateDeleteStatusByIds", ids) > 0;
	}

	@Override
	public boolean updateRestoreDeleteStatusById(Long id) {
		return getSqlMapClientTemplate().update(
				"smsdownlink.updateRestoreDeleteStatusById", id) > 0;
	}

}
