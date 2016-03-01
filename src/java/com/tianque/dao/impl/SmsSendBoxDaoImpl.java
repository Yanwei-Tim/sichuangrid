package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SmsSendBoxDao;
import com.tianque.domain.Contacter;
import com.tianque.domain.SmsSendBox;
import com.tianque.exception.base.BusinessValidationException;

@Repository("smsSendBoxDao")
public class SmsSendBoxDaoImpl extends AbstractBaseDao implements SmsSendBoxDao {

	@Override
	public SmsSendBox getSmsSendBoxById(Long id) {
		if (id == null)
			return null;
		return (SmsSendBox) getSqlMapClientTemplate().queryForObject(
				"smsSendBox.getSmsSendBoxById", id);
	}

	@Override
	public SmsSendBox addSmsSendBox(SmsSendBox smsSendBox) {
		if (!validateNotNull(smsSendBox))
			throw new BusinessValidationException("参数错误");
		Long id = (Long) getSqlMapClientTemplate().insert(
				"smsSendBox.addSmsSendBox", smsSendBox);
		return getSmsSendBoxById(id);
	}

	@Override
	public void addSmsSendTargets(Long smsId, Contacter contacter) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smsId", smsId);
		map.put("contactId", contacter.getId());
		map.put("type", contacter.getBelongClass());
		getSqlMapClientTemplate().insert("smsSendBox.addSmsSendTargets", map);
	}

	@Override
	public SmsSendBox updateSmsSendBox(SmsSendBox smsSendBox) {
		if (!validateNotNull(smsSendBox) || smsSendBox.getId() == null)
			throw new BusinessValidationException("参数错误");
		getSqlMapClientTemplate().update("smsSendBox.updateSmsSendBox",
				smsSendBox);
		return getSmsSendBoxById(smsSendBox.getId());
	}

	@Override
	public int deleteSmsSendBoxById(Long id) {
		if (id == null)
			return 0;
		return getSqlMapClientTemplate().delete(
				"smsSendBox.deleteSmsSendBoxById", id);
	}

	@Override
	public int deleteSmsSendTargets(Long smsId) {
		if (smsId == null)
			return 0;
		return getSqlMapClientTemplate().delete(
				"smsSendBox.deleteSmsSendTargets", smsId);
	}

	@Override
	public PageInfo<SmsSendBox> findSmsSendBoxByOwnerId(Long ownerId,
			int pageNum, int pageSize, String sortField, String order) {
		if (ownerId == null || ownerId == 0L) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ownerId", ownerId);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"smsSendBox.countSmsSendBoxsForPage", ownerId);
		if (StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		@SuppressWarnings("unchecked")
		List<SmsSendBox> list = getSqlMapClientTemplate().queryForList(
				"smsSendBox.findSmsSendBoxsForPage", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	public PageInfo<SmsSendBox> findSmsSendBoxByOwnerId(Long ownerId,
			String date, int pageNum, int pageSize, String sortField,
			String order) {
		if (ownerId == null || ownerId == 0L) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ownerId", ownerId);
		map.put("date", date);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"smsSendBox.countSmsSendBoxsForPageByDate", map);
		if (StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		@SuppressWarnings("unchecked")
		List<SmsSendBox> list = getSqlMapClientTemplate().queryForList(
				"smsSendBox.findSmsSendBoxsForPageByDate", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<SmsSendBox> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List<SmsSendBox> list) {
		PageInfo<SmsSendBox> pageInfo = new PageInfo<SmsSendBox>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<SmsSendBox> emptyPage(int pageSize) {
		PageInfo<SmsSendBox> pageInfo = new PageInfo<SmsSendBox>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SmsSendBox>());
		return pageInfo;
	}

	private boolean validateNotNull(SmsSendBox smsSendBox) {
		if (smsSendBox == null || smsSendBox.getReceiver() == null
				|| "".equals(smsSendBox.getReceiver().trim())) {
			return false;
		}
		if (smsSendBox.getSender() == null
				|| "".equals(smsSendBox.getSender().trim())) {
			return false;
		}
		if (smsSendBox.getSendMobile() == null
				|| "".equals(smsSendBox.getReceiver().trim())) {
			return false;
		}
		if (smsSendBox.getSendUser() == null) {
			return false;
		}
		if (smsSendBox.getSmsContent() == null
				|| "".equals(smsSendBox.getReceiver().trim())) {
			return false;
		}
		return true;
	}

}
