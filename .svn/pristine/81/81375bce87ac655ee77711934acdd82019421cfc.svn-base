package com.tianque.plugin.weChat.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.dao.RedEnvelopesPaymentRecordsDao;
import com.tianque.plugin.weChat.domain.redEnvelopeManagement.RedEnvelopesPaymentRecords;

@Repository("redEnvelopesPaymentRecordsDao")
public class RedEnvelopesPaymentRecordsDaoImpl extends AbstractBaseDao implements
		RedEnvelopesPaymentRecordsDao {

	@Override
	public RedEnvelopesPaymentRecords addRedEnvelopesPaymentRecords(
			RedEnvelopesPaymentRecords redEnvelopesPaymentRecords) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"redEnvelopesPaymentRecords.addRedEnvelopesPaymentRecords",
				redEnvelopesPaymentRecords);
		return getRedEnvelopesPaymentRecordsById(id);
	}

	@Override
	public RedEnvelopesPaymentRecords getRedEnvelopesPaymentRecordsById(Long id) {
		return (RedEnvelopesPaymentRecords) getSqlMapClientTemplate().queryForObject(
				"redEnvelopesPaymentRecords.getRedEnvelopesPaymentRecordsById", id);
	}

	@Override
	public void deleteRedEnvelopesPaymentRecordsById(Long id) {

	}

	@Override
	public PageInfo<RedEnvelopesPaymentRecords> findRedEnvelopesPaymentRecordsByPage(
			RedEnvelopesPaymentRecords redEnvelopesPaymentRecords, int pageNum, int pageSize,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("redEnvelopesPaymentRecords", redEnvelopesPaymentRecords);
		map.put("sortField", sortField);
		map.put("order", order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"redEnvelopesPaymentRecords.countRedEnvelopesPaymentRecordsList", map);
		List<RedEnvelopesPaymentRecords> list = getSqlMapClientTemplate().queryForList(
				"redEnvelopesPaymentRecords.findRedEnvelopesPaymentRecordsList", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<RedEnvelopesPaymentRecords> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<RedEnvelopesPaymentRecords> pageInfo = new PageInfo<RedEnvelopesPaymentRecords>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

}
