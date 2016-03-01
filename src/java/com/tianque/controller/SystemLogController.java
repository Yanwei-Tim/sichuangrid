package com.tianque.controller;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.systemLog.domain.SystemLog;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModelType;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("systemLogController")
public class SystemLogController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(SystemLogController.class);

	@Autowired
	private SystemLogService systemLogService;

	private SystemLog systemLog = new SystemLog();

	// 查询条件属性
	private Date startDate; // 开始日期
	private Date endDate; // 结束日期
	private int successOrFailId;

	private Integer topLogType;
	private List<String> secondModels;

	public int getSuccessOrFailId() {
		return successOrFailId;
	}

	public void setSuccessOrFailId(int successOrFailId) {
		this.successOrFailId = successOrFailId;
	}

	public SystemLog getSystemLog() {
		return systemLog;
	}

	public void setSystemLog(SystemLog systemLog) {
		this.systemLog = systemLog;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = new Date(endDate.getTime() + 1 * 24 * 60 * 60 * 1000);
	}

	public Integer getTopLogType() {
		return topLogType;
	}

	public void setTopLogType(Integer topLogType) {
		this.topLogType = topLogType;
	}

	public List<String> getSecondModels() {
		return secondModels;
	}

	public void setSecondModels(List<String> secondModels) {
		this.secondModels = secondModels;
	}

	/**
	 * 查询系统日志
	 * 
	 * @return SUCCESS
	 */
	public String findSystemLogByOrgCode() {
		if(startDate==null){
			errorMessage = "开始时间必须输入";
			return ERROR;
		}
		if (systemLog.getModuleName().equals("")
				&& systemLog.getOperationType() == null
				&& (systemLog.getUserName() == null || systemLog.getUserName()
						.equals(""))) {
			PageInfo<SystemLog> pageInfo = systemLogService
					.findSystemLogByOrgCode(ThreadVariable.getSession()
							.getOrgInternalCode(), startDate, endDate, page,
							rows, sidx, sord);
			gridPage = new GridPage(pageInfo);

			return SUCCESS;
		}
		if (systemLog.getModuleName() != null
				|| systemLog.getOperationType() != null
				|| systemLog.getUserName() != null
				|| !systemLog.getUserName().equals("")) {
			PageInfo<SystemLog> pageInfo = systemLogService
					.findAllSystemLogsForPagebyQuery(ThreadVariable
							.getSession().getOrgInternalCode(), startDate,
							endDate, systemLog.getModuleName(), systemLog
									.getOperationType(), systemLog
									.getUserName(), page, rows, sidx, sord);
			gridPage = new GridPage(pageInfo);

			return SUCCESS;
		}
		return ERROR;
	}

	/**暂时未使用*/
	public String findSystemLogs() {
		try {
			if (successOrFailId == 0) {
				findAllSystemLogs();
			}
			if (successOrFailId == 1) {
				findSuccessSystemLog();
			}
			if (successOrFailId == 2) {
				findFailSystemLog();
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("异常信息", e);
		}
		return ERROR;
	}

	/**
	 * 查询所有登录的系统日志
	 * 
	 * @return SUCCESSANDFAIL
	 */
	private void findAllSystemLogs() {
		if ((startDate != null && endDate != null)
				|| (systemLog.getUserName() != null && !systemLog.getUserName()
						.equals(""))) {
			PageInfo<SystemLog> pageInfo = systemLogService
					.findAllSystemLogsForPage(ThreadVariable.getSession()
							.getOrgInternalCode(), startDate, endDate,
							systemLog.getUserName(), this.getPage(), this
									.getRows(), sidx, sord);
			gridPage = new GridPage(pageInfo);
		}
	}

	/**
	 * 查询登录成功的系统日志
	 * 
	 * @return SUCCESS
	 */
	private void findSuccessSystemLog() {
		if ((startDate != null && endDate != null)
				|| (systemLog.getUserName() != null && !systemLog.getUserName()
						.equals(""))) {
			PageInfo<SystemLog> pageInfo = systemLogService
					.findSuccessSystemLogsForPage(ThreadVariable.getSession()
							.getOrgInternalCode(), startDate, endDate,
							systemLog.getUserName(), this.getPage(), this
									.getRows(), sidx, sord);
			gridPage = new GridPage(pageInfo);
		}
	}

	/**
	 * 查询登录失败的系统日志
	 * 
	 * @return SUCCESS
	 */
	private void findFailSystemLog() {
		if (startDate != null && endDate != null) {
			PageInfo pageInfo = systemLogService.findFailSystemLogsForPage(
					startDate, endDate, this.getPage(), this.getRows(), sidx,
					sord);
			gridPage = new GridPage(pageInfo);
		}
	}

	// 获取第二级内容
	public String getSecondModelTypes() {
		if (topLogType == null) {
			return ERROR;
		}
		secondModels = ModelType.getSecondModelTypes(topLogType);
		return SUCCESS;
	}

}
