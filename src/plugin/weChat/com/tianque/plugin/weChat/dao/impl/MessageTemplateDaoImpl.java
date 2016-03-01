package com.tianque.plugin.weChat.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.dao.MessageTemplateDao;
import com.tianque.plugin.weChat.domain.messageTemplate.TemplateMessageRecord;
import com.tianque.plugin.weChat.vo.RedEnvelopeVo;

@Repository("messageTemplateDao")
public class MessageTemplateDaoImpl extends AbstractBaseDao implements MessageTemplateDao {

	@Override
	public TemplateMessageRecord addTemplateMessageRecord(TemplateMessageRecord templateRecord) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"templateMessageRecord.addTemplateMessageRecord", templateRecord);
		return getTemplateMessageRecordById(id);
	}

	@Override
	public TemplateMessageRecord updateTemplateMessageRecord(TemplateMessageRecord templateRecord) {
		getSqlMapClientTemplate().update("templateMessageRecord.updateTemplateMessageRecord",
				templateRecord);
		return getTemplateMessageRecordById(templateRecord.getId());
	}

	@Override
	public TemplateMessageRecord getTemplateMessageRecordById(Long id) {
		return (TemplateMessageRecord) getSqlMapClientTemplate().queryForObject(
				"templateMessageRecord.getTemplateMessageRecordById", id);
	}

	@Override
	public void deleteTemplateMessageRecordById(Long id) {
		getSqlMapClientTemplate().delete("templateMessageRecord.deleteTemplateMessageRecordById",
				id);
	}

	@Override
	public PageInfo<TemplateMessageRecord> findTemplateMessageRecordByPage(
			TemplateMessageRecord templateRecord, int pageNum, int pageSize, String sortField,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("templateMessageRecord", templateRecord);
		map.put("sortField", sortField);
		map.put("order", order);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"templateMessageRecord.countTemplateMessageRecordList", map);
		List<RedEnvelopeVo> list = getSqlMapClientTemplate().queryForList(
				"templateMessageRecord.findTemplateMessageRecordList", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<TemplateMessageRecord> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List list) {
		PageInfo<TemplateMessageRecord> pageInfo = new PageInfo<TemplateMessageRecord>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public TemplateMessageRecord getTemplateMessageRecordByWidAndTid(String weChatUserId,
			String templateId, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weChatUserId", weChatUserId);
		map.put("templateId", templateId);
		map.put("orgId", orgId);
		return (TemplateMessageRecord) getSqlMapClientTemplate().queryForObject(
				"templateMessageRecord.getTemplateMessageRecordByWidAndTid", map);
	}

	@Override
	public TemplateMessageRecord getTemplateMessageRecordByWidAndTnum(String weChatUserId,
			String templateNum, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weChatUserId", weChatUserId);
		map.put("templateNum", templateNum);
		map.put("orgId", orgId);
		return (TemplateMessageRecord) getSqlMapClientTemplate().queryForObject(
				"templateMessageRecord.getTemplateMessageRecordByWidAndTnum", map);
	}

	@Override
	public TemplateMessageRecord getTemplateMessageRecordByWidAndTitle(String weChatUserId,
			String title, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weChatUserId", weChatUserId);
		map.put("title", title);
		map.put("orgId", orgId);
		return (TemplateMessageRecord) getSqlMapClientTemplate().queryForObject(
				"templateMessageRecord.getTemplateMessageRecordByWidAndTitle", map);
	}
}
