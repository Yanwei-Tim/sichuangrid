package com.tianque.controller;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SystemhighLog;
import com.tianque.service.SystemhighLogService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("systemhighLogController")
public class SystemhighLogController extends BaseAction {

	@Autowired
	private SystemhighLogService systemhighLogService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private SystemhighLog log = new SystemhighLog();

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

	private Date startDate; // 开始日期
	private Date endDate; // 结束日期

	public SystemhighLogService getSystemhighLogService() {
		return systemhighLogService;
	}

	public void setSystemhighLogService(
			SystemhighLogService systemhighLogService) {
		this.systemhighLogService = systemhighLogService;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public String findSystemhighLogByOrgCode() throws Exception {

		if ((startDate != null && endDate != null)
				&& ((log.getModelName() == null)
						&& (log.getOperationType() == null) && (log
						.getOperationUserName() == null && log
						.getOperationUserName().equals("")))) {
			PageInfo<SystemhighLog> pageInfo = ControllerHelper
					.processAllOrgRelativeName(
							systemhighLogService.findSystemhighLogByOrgCode(
									ThreadVariable.getSession()
											.getOrgInternalCode(), startDate,
									endDate, page, rows, sidx, sord),
							organizationDubboService,
							new String[] { "organization" }, null);// 100是随意参数
			gridPage = new GridPage(pageInfo);

			return SUCCESS;
		}
		if ((startDate != null && endDate != null)
				|| ((log.getModelName() != null)
						|| (log.getOperationType() != null) || (log
						.getOperationUserName() != null || !log
						.getOperationUserName().equals("")))) {
			PageInfo<SystemhighLog> pageInfo = ControllerHelper
					.processAllOrgRelativeName(systemhighLogService
							.findSystemhighLogByOrgCode(ThreadVariable
									.getSession().getOrgInternalCode(),
									startDate, endDate, log.getModelName(), log
											.getOperationType(), log
											.getOperationUserName(), page,
									rows, sidx, sord), organizationDubboService,
							new String[] { "organization" }, null);
			gridPage = new GridPage(pageInfo);

			return SUCCESS;
		}
		return ERROR;
	}

	public SystemhighLog getLog() {
		return log;
	}

	public void setLog(SystemhighLog log) {
		this.log = log;
	}
}
