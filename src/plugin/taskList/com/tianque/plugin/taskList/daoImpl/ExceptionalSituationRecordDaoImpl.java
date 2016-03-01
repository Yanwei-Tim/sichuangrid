package com.tianque.plugin.taskList.daoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.vo.PageInfo;
import com.tianque.dao.AbstractBaseDao;
import com.tianque.plugin.taskList.constants.Constants;
import com.tianque.plugin.taskList.dao.ExceptionalSituationRecordDao;
import com.tianque.plugin.taskList.domain.ExceptionalSituationRecord;
import com.tianque.plugin.taskList.domain.ExceptionalSituationRecordVo;

@Repository("exceptionalSituationRecordDao")
public class ExceptionalSituationRecordDaoImpl extends AbstractBaseDao implements
		ExceptionalSituationRecordDao {

	@Override
	public ExceptionalSituationRecord addExceptionalSituationRecord(
			ExceptionalSituationRecord exceptionalSituationRecord) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"exceptionalSituationRecord.addExceptionalSituationRecord",
				exceptionalSituationRecord);
		return getExceptionalSituationRecordById(id);
	}

	@Override
	public ExceptionalSituationRecord getExceptionalSituationRecordById(Long id) {
		return (ExceptionalSituationRecord) getSqlMapClientTemplate().queryForObject(
				"exceptionalSituationRecord.getExceptionalSituationRecordById", id);
	}

	@Override
	public PageInfo<ExceptionalSituationRecord> findExceptionalSituationRecords(
			ExceptionalSituationRecordVo exceptionalSituationRecordVo, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = creatExceptionalSituationRecordMap(exceptionalSituationRecordVo,
				sidx, sord);
		map.put("modulekey", Constants.REPLY_EXCEPTIONALSITUATIONRECORD_KEY);
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"exceptionalSituationRecord.countFindExceptionalSituationRecords", map);
		List<ExceptionalSituationRecord> list = new ArrayList<ExceptionalSituationRecord>();
		if (pageNum == null || pageSize == null) {
			list = getSqlMapClientTemplate().queryForList(
					"exceptionalSituationRecord.findExceptionalSituationRecords", map);
			return new PageInfo<ExceptionalSituationRecord>(1, countNum, countNum, list);
		} else {
			list = getSqlMapClientTemplate().queryForList(
					"exceptionalSituationRecord.findExceptionalSituationRecords", map,
					(pageNum - 1) * pageSize, pageSize);
			return new PageInfo<ExceptionalSituationRecord>(pageNum, pageSize, countNum, list);
		}
	}
	
	
	@Override
	public Integer countExceptionalSituationRecords(
			ExceptionalSituationRecord exceptionalSituationRecord) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"exceptionalSituationRecord.countFindExceptionalSituationRecords",
				exceptionalSituationRecord);
	}

	@Override
	public ExceptionalSituationRecord updateExceptionalSituationRecord(
			ExceptionalSituationRecord exceptionalSituationRecord) {
		getSqlMapClientTemplate().update(
				"exceptionalSituationRecord.updateExceptionalSituationRecord",
				exceptionalSituationRecord);
		return getExceptionalSituationRecordById(exceptionalSituationRecord.getId());
	}

	@Override
	public Integer deleteExceptionalSituationRecords(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		return getSqlMapClientTemplate().delete(
				"exceptionalSituationRecord.deleteExceptionalSituationRecord", map);
	}

	private Map<String, Object> creatExceptionalSituationRecordMap(
			ExceptionalSituationRecordVo exceptionalSituationRecordVo, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgCode", exceptionalSituationRecordVo.getOrgCode());
		map.put("address", exceptionalSituationRecordVo.getAddress());
		map.put("fastSearchCondition", exceptionalSituationRecordVo.getFastSearchCondition());
		map.put("signMemberName", exceptionalSituationRecordVo.getSignMemberName());
		map.put("status", exceptionalSituationRecordVo.getStatus());
		map.put("hasReplay", exceptionalSituationRecordVo.getHasReplay());
		if (exceptionalSituationRecordVo.getExceptionSituation() != null) {
			map.put("exceptionSituationId", exceptionalSituationRecordVo.getExceptionSituation()
					.getId());
		}
		map.put("recordEndDate", exceptionalSituationRecordVo.getRecordEndDate());
		map.put("recordStartDate", exceptionalSituationRecordVo.getRecordStartDate());
		map.put("signStartDate", exceptionalSituationRecordVo.getSignStartDate());
		map.put("signEndDate", exceptionalSituationRecordVo.getSignEndDate());
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("mode", exceptionalSituationRecordVo.getMode());
		map.put("funOrgId", exceptionalSituationRecordVo.getFunOrgId());
		return map;
	}

}
