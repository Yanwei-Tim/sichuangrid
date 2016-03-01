package com.tianque.plugin.tbSchedule.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.tbSchedule.domain.IdCardNoExpLog;
import com.tianque.plugin.tbSchedule.domain.TBScheduleLog;
import com.tianque.plugin.tbSchedule.domain.TBScheduleLogException;

/**
 * @ClassName: ScheduleTaskDealDAOImpl
 * @Description: 淘宝任务调度DAO实现类
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年11月14日 下午5:53:01
 */
@Repository
public class ScheduleTaskDealDAOImpl extends AbstractBaseDao implements
		ScheduleTaskDealDAO {

	@Override
	public Long saveTBScheduleLog(TBScheduleLog tbScheduleLog) {
		return (Long) getSqlMapClientTemplate().insert(
				"tbScheduleLog.saveTBScheduleLog", tbScheduleLog);
	}

	@Override
	public void addTBScheduleLogForException(
			TBScheduleLogException tbScheduleLogException) {
		getSqlMapClientTemplate().insert(
				"tbScheduleLog.addTBScheduleLogForException",
				tbScheduleLogException);
	}

	@Override
	public void updateTBScheduleLogIsEnd(TBScheduleLog tbScheduleLog) {
		getSqlMapClientTemplate().update(
				"tbScheduleLog.updateTBScheduleLogIsEnd", tbScheduleLog);
	}

	@Override
	public GridPage queryTBScheduleLogForPage(TBScheduleLog tbSchedule,
			Integer page, Integer rows, String sidx, String sord) {
		tbSchedule = tbSchedule == null ? new TBScheduleLog() : tbSchedule;
		tbSchedule.setSortField(sidx);
		tbSchedule.setOrder(sord);
		return new GridPage(new PageInfo(page, rows,
				(Integer) getSqlMapClientTemplate().queryForObject(
						"tbScheduleLog.queryTBScheduleLogForPageCount",
						tbSchedule), getSqlMapClientTemplate().queryForList(
						"tbScheduleLog.queryTBScheduleLogForPage", tbSchedule,
						(page - 1) * rows, rows)));
	}

	@Override
	public TBScheduleLog getFullTBScheduleLogForView(Long id) {
		return (TBScheduleLog) getSqlMapClientTemplate().queryForObject(
				"tbScheduleLog.getFullTBScheduleLogForView", id);
	}

	@Override
	public void updateTBScheduleLogIsException(TBScheduleLog tbScheduleLog) {
		getSqlMapClientTemplate().update(
				"tbScheduleLog.updateTBScheduleLogIsException", tbScheduleLog);
	}

	@Override
	public void addIdCardNoExpLog(IdCardNoExpLog idCardNoExpLog) {
		getSqlMapClientTemplate().insert("tbScheduleLog.addIdCardNoExpLog",
				idCardNoExpLog);
	}

	@Override
	public boolean checkExsitForIdCardNo(String idCardNo) {
		Map map = new HashMap();
		map.put("expIdCardNo", idCardNo);
		Object obj = getSqlMapClientTemplate().queryForObject(
				"tbScheduleLog.checkExsitForIdCardNo", map);
		return null == obj;
	}

	@Override
	public GridPage queryIdCardNoExpLogForPage(IdCardNoExpLog idCardNoExpLog,
			Integer page, Integer rows, String sidx, String sord) {

		idCardNoExpLog = idCardNoExpLog == null ? new IdCardNoExpLog()
				: idCardNoExpLog;
		idCardNoExpLog.setSortField(sidx);
		idCardNoExpLog.setOrder(sord);

		return new GridPage(new PageInfo(page, rows,
				(Integer) getSqlMapClientTemplate().queryForObject(
						"tbScheduleLog.countIdCardNoExpLogForList",
						idCardNoExpLog), getSqlMapClientTemplate()
						.queryForList(
								"tbScheduleLog.findIdCardNoExpLogForList",
								idCardNoExpLog, (page - 1) * rows, rows)));
	}

	@Override
	public List<IdCardNoExpLog> queryIdCardNoExpLogForPage(
			IdCardNoExpLog idCardNoExpLog, String sidx, String sord) {
		idCardNoExpLog = idCardNoExpLog == null ? new IdCardNoExpLog()
				: idCardNoExpLog;
		idCardNoExpLog.setSortField(sidx);
		idCardNoExpLog.setOrder(sord);
		return getSqlMapClientTemplate().queryForList(
				"tbScheduleLog.findIdCardNoExpLogForList", idCardNoExpLog);
	}

	@Override
	public GridPage queryExceptionForPage(Long logId, Integer page,
			Integer rows, String sidx, String sord) {

		return new GridPage(new PageInfo(page, rows,
				(Integer) getSqlMapClientTemplate().queryForObject(
						"tbScheduleLog.countTBScheduleLogExceptionListByLogId",
						logId), getSqlMapClientTemplate().queryForList(
						"tbScheduleLog.getTBScheduleLogExceptionListByLogId",
						logId, (page - 1) * rows, rows)));
	}
}
