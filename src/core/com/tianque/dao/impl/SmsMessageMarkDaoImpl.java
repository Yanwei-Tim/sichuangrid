package com.tianque.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SmsMessageMarkDao;
import com.tianque.domain.SmsMessageMark;

@Repository("smsMessageMarkDao")
public class SmsMessageMarkDaoImpl extends AbstractBaseDao implements
		SmsMessageMarkDao {
	@Override
	public SmsMessageMark getSimpleSmsMessageMarkById(Long id) {
		return (SmsMessageMark) getSqlMapClientTemplate().queryForObject(
				"smsMessageMark.getSimpleSmsMessageMarkById", id);
	}

	@Override
	public SmsMessageMark addSmsMessageMark(SmsMessageMark smsMessageMark) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"smsMessageMark.addSmsMessageMark", smsMessageMark);
		return this.getSimpleSmsMessageMarkById(id);
	}

	@Override
	public void deleteSmsMessageMarkById(Long id) {
		getSqlMapClientTemplate().delete(
				"smsMessageMark.deleteSmsMessageMarkById", id);
	}

	@Override
	public SmsMessageMark updateSmsMessageMark(SmsMessageMark smsMessageMark) {
		getSqlMapClientTemplate().update("smsMessageMark.updateSmsMessageMark",
				smsMessageMark);
		return getSimpleSmsMessageMarkById(smsMessageMark.getId());
	}

	@Override
	public PageInfo<SmsMessageMark> findSmsMessageMarks(Integer page,
			Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageInfo<SmsMessageMark> result = createSmsMessageMarkPageInfoInstance(
				countSmsMessageMark(), rows, page);
		map.put("sortField", sidx);
		map.put("order", sord);
		result.setResult(getSqlMapClientTemplate().queryForList(
				"smsMessageMark.findSmsMessageMarkList", map,
				(page - 1) * rows, rows));
		return result;

	}

	public int countSmsMessageMark() {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"smsMessageMark.countSmsMessageMark");
	}

	private PageInfo<SmsMessageMark> createSmsMessageMarkPageInfoInstance(
			int totalRecord, int pageSize, int pageIndex) {
		PageInfo<SmsMessageMark> result = new PageInfo<SmsMessageMark>();
		result.setTotalRowSize(totalRecord);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

	public SmsMessageMark getSimpleSmsMessageMarkByDealType(int dealType) {
		return (SmsMessageMark) getSqlMapClientTemplate().queryForObject(
				"smsMessageMark.getSimpleSmsMessageMarkByDealType", dealType);
	}

	public SmsMessageMark getSimpleSmsMessageMarkByOperationtType(
			int operationtType) {
		Long id = (Long) getSqlMapClientTemplate().queryForObject(
				"smsMessageMark.getSimpleSmsMessageMarkByOperationtType",
				operationtType);
		return this.getSimpleSmsMessageMarkById(id);
	}
}
