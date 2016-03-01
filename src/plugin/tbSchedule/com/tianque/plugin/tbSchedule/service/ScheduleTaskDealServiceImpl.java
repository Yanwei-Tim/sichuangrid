package com.tianque.plugin.tbSchedule.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taobao.pamirs.schedule.ScheduleUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.tbSchedule.dao.ScheduleTaskDealDAO;
import com.tianque.plugin.tbSchedule.domain.IdCardNoExpLog;
import com.tianque.plugin.tbSchedule.domain.TBScheduleLog;
import com.tianque.plugin.tbSchedule.domain.TBScheduleLogException;

/**
 * @ClassName: ScheduleTaskDealServiceImpl
 * @Description: 任务调度处理接口实现类
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年11月14日 下午3:37:13
 */
@Service
@Transactional
public class ScheduleTaskDealServiceImpl implements ScheduleTaskDealService {
	Logger logger = LoggerFactory.getLogger(ScheduleTaskDealServiceImpl.class);
	@Autowired
	private ScheduleTaskDealDAO scheduleTaskDealDAO;

	private static final Integer EXCEPTION_MESSAGE_LENGTH = 3000;

	@Override
	public void addTBScheduleLogForException(Long id, Throwable exception) {
		// logger.error(exception.getMessage(), exception);
		if (id == null) {
			return;
		}
		try {
			TBScheduleLogException tbScheduleLogException = new TBScheduleLogException();
			TBScheduleLog tbScheduleLog = new TBScheduleLog();
			tbScheduleLog.setId(id);
			tbScheduleLogException.setTbScheduleLog(tbScheduleLog);
			String errorMsg = getExceptionStackTrace(exception);
			errorMsg = errorMsg.length() > EXCEPTION_MESSAGE_LENGTH ? errorMsg
					.substring(0, EXCEPTION_MESSAGE_LENGTH) : errorMsg;
			tbScheduleLogException.setErrorMsg(errorMsg);
			tbScheduleLogException.setErrorTime(new Date());
			scheduleTaskDealDAO
					.addTBScheduleLogForException(tbScheduleLogException);
			scheduleTaskDealDAO.updateTBScheduleLogIsException(tbScheduleLog);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private String getExceptionStackTrace(Throwable t) throws IOException {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			t.printStackTrace(pw);
			return sw.toString();
		} finally {
			sw.close();
			pw.close();
		}
	}

	@Override
	public void updateTBScheduleLogIsEnd(Long id, Integer listSize) {
		if (id == null) {
			return;
		}
		try {
			TBScheduleLog tbScheduleLog = new TBScheduleLog();
			tbScheduleLog.setId(id);
			tbScheduleLog.setEndTime(new Date());
			tbScheduleLog.setListSize(listSize);
			scheduleTaskDealDAO.updateTBScheduleLogIsEnd(tbScheduleLog);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public Long saveTBScheduleLog(String jobName, String taskParameter,
			String ownSign, int taskItemNum, String taskItems,
			int eachFetchDataNum, String reMark) {
		try {
			TBScheduleLog tbScheduleLog = new TBScheduleLog();
			tbScheduleLog.setJobName(jobName);
			tbScheduleLog.setTaskParameter(taskParameter);
			tbScheduleLog.setOwnSign(ownSign);
			tbScheduleLog.setTaskItemNum(taskItemNum);
			tbScheduleLog.setTaskItems(taskItems);
			tbScheduleLog.setEachFetchDataNum(eachFetchDataNum);
			tbScheduleLog.setStartTime(new Date());
			tbScheduleLog.setAppName(ScheduleUtil.getLocalHostName());
			tbScheduleLog.setRemark(reMark);
			return scheduleTaskDealDAO.saveTBScheduleLog(tbScheduleLog);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public GridPage queryTBScheduleLogForPage(TBScheduleLog tbSchedule,
			Integer page, Integer rows, String sidx, String sord) {
		return scheduleTaskDealDAO.queryTBScheduleLogForPage(tbSchedule, page,
				rows, sidx, sord);
	}

	@Override
	public TBScheduleLog getFullTBScheduleLogForView(TBScheduleLog tbSchedule) {
		if (tbSchedule == null || tbSchedule.getId() == null) {
			throw new BusinessValidationException("参数错误，ID不能为空！");
		}
		return scheduleTaskDealDAO.getFullTBScheduleLogForView(tbSchedule
				.getId());
	}

	@Override
	public void addIdCardNoExpLog(String idCardNo, String msg) {
		if (scheduleTaskDealDAO.checkExsitForIdCardNo(idCardNo)) {
			IdCardNoExpLog cardNoExpLog = new IdCardNoExpLog(idCardNo, msg);
			try {
				scheduleTaskDealDAO.addIdCardNoExpLog(cardNoExpLog);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public GridPage queryIdCardNoExpLogForPage(IdCardNoExpLog idCardNoExpLog,
			Integer page, Integer rows, String sidx, String sord) {
		return scheduleTaskDealDAO.queryIdCardNoExpLogForPage(idCardNoExpLog,
				page, rows, sidx, sord);
	}

	@Override
	public List<IdCardNoExpLog> queryIdCardNoExpLog(
			IdCardNoExpLog idCardNoExpLog, String sidx, String sord) {
		return scheduleTaskDealDAO.queryIdCardNoExpLogForPage(idCardNoExpLog,
				sidx, sord);
	}

	@Override
	public GridPage queryExceptionForPage(Long logId, Integer page,
			Integer rows, String sidx, String sord) {
		// TODO Auto-generated method stub
		return scheduleTaskDealDAO.queryExceptionForPage(logId, page, rows,
				sidx, sord);
	}
}
