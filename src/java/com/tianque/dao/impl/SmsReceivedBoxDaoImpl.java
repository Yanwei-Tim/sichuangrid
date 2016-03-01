package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SmsReceivedBoxDao;
import com.tianque.domain.SmsReceivedBox;
import com.tianque.exception.base.BusinessValidationException;

@Repository("smsReceivedBoxDao")
public class SmsReceivedBoxDaoImpl extends AbstractBaseDao implements
		SmsReceivedBoxDao {

	@Autowired
	private CacheService cacheService;

	@Override
	public SmsReceivedBox getSmsReceivedBoxById(Long id) {
		if (id == null)
			return null;
		return (SmsReceivedBox) getSqlMapClientTemplate().queryForObject(
				"smsReceivedBox.getSmsReceivedBoxById", id);
	}

	@Override
	public SmsReceivedBox addSmsReceivedBox(SmsReceivedBox smsReceivedBox) {
		if (!validateNotNull(smsReceivedBox))
			throw new BusinessValidationException("参数错误");
		Long id = (Long) getSqlMapClientTemplate().insert(
				"smsReceivedBox.addSmsReceivedBox", smsReceivedBox);
		smsReceivedBox = getSmsReceivedBoxById(id);

		if (null != smsReceivedBox.getHandleUser()
				&& null != smsReceivedBox.getHandleUser().getId()) {
			String key = "countUnprocessSmsReceivedBoxByOwnerId_"
					+ smsReceivedBox.getHandleUser().getId();
			cacheService.remove(key);
		}
		return smsReceivedBox;
	}

	@Override
	public SmsReceivedBox updateSmsReceivedBox(SmsReceivedBox smsReceivedBox) {
		if (!processValidateNotNull(smsReceivedBox)
				|| smsReceivedBox.getId() == null)
			throw new BusinessValidationException("参数错误");
		getSqlMapClientTemplate().update("smsReceivedBox.updateSmsReceivedBox",
				smsReceivedBox);
		smsReceivedBox = getSmsReceivedBoxById(smsReceivedBox.getId());
		if (null != smsReceivedBox.getHandleUser()
				&& null != smsReceivedBox.getHandleUser().getId()) {
			String key = "countUnprocessSmsReceivedBoxByOwnerId_"
					+ smsReceivedBox.getHandleUser().getId();
			cacheService.remove(key);
		}
		return smsReceivedBox;
	}

	@Override
	public int deleteSmsReceivedBoxById(Long id) {
		if (id == null)
			return 0;
		SmsReceivedBox smsReceivedBox = getSmsReceivedBoxById(id);
		if (null != smsReceivedBox.getHandleUser()
				&& null != smsReceivedBox.getHandleUser().getId()) {
			String key = "countUnprocessSmsReceivedBoxByOwnerId_"
					+ smsReceivedBox.getHandleUser().getId();
			cacheService.remove(key);
		}
		return getSqlMapClientTemplate().delete(
				"smsReceivedBox.deleteSmsReceivedBoxById", id);
	}

	@Override
	public PageInfo<SmsReceivedBox> findSmsReceivedBoxByOwnerId(Long orgId,
			int pageNum, int pageSize, String sortField, String order) {
		if (orgId == null || orgId == 0L) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"smsReceivedBox.countSmsReceivedBoxsForPage", orgId);
		if (StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		@SuppressWarnings("unchecked")
		List<SmsReceivedBox> list = getSqlMapClientTemplate().queryForList(
				"smsReceivedBox.findSmsReceivedBoxsForPage", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	public PageInfo<SmsReceivedBox> findSmsReceivedBoxByOwnerId(Long orgId,
			String date, int pageNum, int pageSize, String sortField,
			String order) {
		if (orgId == null || orgId == 0L) {
			return emptyPage(pageSize);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("date", date);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"smsReceivedBox.countSmsReceivedBoxsForPageByDate", map);
		if (StringUtil.isStringAvaliable(sortField)) {
			map.put("sortField", sortField);
		}
		map.put("order", order);
		@SuppressWarnings("unchecked")
		List<SmsReceivedBox> list = getSqlMapClientTemplate().queryForList(
				"smsReceivedBox.findSmsReceivedBoxsForPageByDate", map,
				(pageNum - 1) * pageSize, pageSize);
		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	private PageInfo<SmsReceivedBox> createPageInfo(int pageNum, int pageSize,
			Integer countNum, List<SmsReceivedBox> list) {
		PageInfo<SmsReceivedBox> pageInfo = new PageInfo<SmsReceivedBox>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<SmsReceivedBox> emptyPage(int pageSize) {
		PageInfo<SmsReceivedBox> pageInfo = new PageInfo<SmsReceivedBox>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SmsReceivedBox>());
		return pageInfo;
	}

	private boolean validateNotNull(SmsReceivedBox smsReceivedBox) {
		if (smsReceivedBox == null || smsReceivedBox.getSourceMobile() == null
				|| "".equals(smsReceivedBox.getSourceMobile().trim())) {
			return false;
		}
		if (smsReceivedBox.getSourceMobile() == null
				|| "".equals(smsReceivedBox.getSourceMobile().trim())) {
			return false;
		}
		if (smsReceivedBox.getTargetMobile() == null
				|| "".equals(smsReceivedBox.getTargetMobile().trim())) {
			return false;
		}
		if (smsReceivedBox.getSmsContent() == null
				|| "".equals(smsReceivedBox.getSmsContent().trim())) {
			return false;
		}
		return true;
	}

	private boolean processValidateNotNull(SmsReceivedBox smsReceivedBox) {
		if (smsReceivedBox == null || smsReceivedBox.getDisposition() == null
				|| "".equals(smsReceivedBox.getDisposition().trim())) {
			return false;
		}
		return true;
	}

	@Override
	public int countUnprocessSmsReceivedBoxByOwnerId(Long userId) {
		if (null == userId) {
			return 0;
		}
		String key = "countUnprocessSmsReceivedBoxByOwnerId_" + userId;
		Integer countNum = (Integer) cacheService.get(key);
		if (null == countNum) {
			countNum = (Integer) getSqlMapClientTemplate().queryForObject(
					"smsReceivedBox.countUnprocessSmsReceivedBox", userId);
			cacheService.set(key, countNum);
		}
		return countNum;
	}
}
