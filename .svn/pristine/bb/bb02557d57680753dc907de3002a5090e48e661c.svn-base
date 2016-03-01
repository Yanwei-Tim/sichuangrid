package com.tianque.baseInfo.permanentAddress.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.permanentAddress.domain.PermanentAddressLog;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;

@Repository("permanentAddressLogDao")
public class PermanentAddressLogDaoImpl extends AbstractBaseDao implements
		PermanentAddressLogDao {

	@Override
	public PermanentAddressLog addPermanentAddressLog(
			PermanentAddressLog permanentAddressLog) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"permanentAddressLog.addPermanentAddressLog",
				permanentAddressLog);
		return getPermanentAddressLogById(id);
	}

	@Override
	public boolean deletePermanentAddressLog(Long id) {
		int commit = getSqlMapClientTemplate().delete(
				"permanentAddressLog.deletePermanentAddressLog", id);
		return commit > 0;
	}

	@Override
	public PermanentAddressLog updatePermanentAddressLog(
			PermanentAddressLog permanentAddressLog) {
		getSqlMapClientTemplate().update(
				"permanentAddressLog.updatePermanentAddressLog",
				permanentAddressLog);
		return getPermanentAddressLogById(permanentAddressLog.getId());
	}

	@Override
	public PermanentAddressLog getPermanentAddressLogById(Long id) {
		return (PermanentAddressLog) getSqlMapClientTemplate().queryForObject(
				"permanentAddressLog.getPermanentAddressLogById", id);
	}

	@Override
	public List<PermanentAddressLog> findPermanentAddressLogByJobstatus(
			Integer jobState) {
		return getSqlMapClientTemplate().queryForList(
				"permanentAddressLog.findPermanentAddressLogByJobstatus",
				jobState);
	}

	@Override
	public void updatePermanentAddressLogJobstatus(Long id, Integer jobState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("jobState", jobState);
		getSqlMapClientTemplate().update(
				"permanentAddressLog.updatePermanentAddressLogJobstatus", map);
	}

	@Override
	public PageInfo<PermanentAddressLog> findAllPermanentAddressLog(
			Integer page, Integer rows, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"permanentAddressLog.countFindAllPermanentAddressLog", map);
		List<PermanentAddressLog> list = getSqlMapClientTemplate()
				.queryForList("permanentAddressLog.findAllPermanentAddressLog",
						map, (page - 1) * rows, rows);

		return new PageInfo<PermanentAddressLog>(page, rows, countNum, list);
	}

	@Override
	public void deleteLogByIds(String[] id) {
		getSqlMapClientTemplate().delete("permanentAddressLog.deleteLogByIds",
				id);
	}

	@Override
	public List<PermanentAddressLog> findPermanentAddressLogByJobstatusAndIds(
			String[] ids) {
		return getSqlMapClientTemplate().queryForList(
				"permanentAddressLog.findPermanentAddressLogByJobstatusAndIds",
				ids);
	}
}
