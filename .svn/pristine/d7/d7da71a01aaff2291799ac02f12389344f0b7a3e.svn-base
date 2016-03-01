package com.tianque.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.systemLog.domain.SystemLog;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.dao.OperateLogDao;
import com.tianque.domain.OperateLog;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.OperateLogService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

@Service("operateLogService")
public class OperateLogServiceImpl extends AbstractBaseService implements
		OperateLogService {

	@Autowired
	private OperateLogDao operateLogDao;
	@Autowired
	protected OrganizationDubboService organizationDubboService;
	@Autowired
	private PermissionService permissionService;

	public OperateLog log(Long orgId, String operation, String moduleName,
			Integer operationType, String moduleType, String operateContent) {
		return log(orgId, SystemLog.INFO, moduleName, operationType, operation,
				moduleType, operateContent);
	}

	public OperateLog log(Long orgId, int logLevel, String moduleName,
			Integer operationType, String operation, String moduleType,
			String operateContent) {
		String userName = "";
		String clientIp = "";
		if (ThreadVariable.getSession() != null) {
			userName = ThreadVariable.getSession().getUserName();
			clientIp = ThreadVariable.getSession().getAccessIp();

		}
		return log(orgId, operation, operationType, moduleName, logLevel,
				userName, clientIp, moduleType, operateContent);
	}

	private OperateLog log(Long orgId, String operation, Integer operationType,
			String moduleName, int logLevel, String userName, String clientIp,
			String moduleType, String operationContext) {
		if (operation == null || "".equals(operation.trim())) {
			throw new BusinessValidationException("操作字符串不能为空");
		}
		if (moduleName == null || "".equals(moduleName.trim())) {
			throw new BusinessValidationException("模块名不能为空");
		}
		if (moduleType == null || "".equals(moduleType.trim())) {
			throw new BusinessValidationException("功能模块不能为空");
		}
		OperateLog log = createOperateLog(operation, operationType, moduleName,
				logLevel, userName, clientIp, moduleType, operationContext);
		Organization organization = new Organization();
		if (userName != null && !"".equals(userName)) {
			User user = permissionService.findUserByUserName(userName);
			if (user != null) {
				organization = organizationDubboService.getSimpleOrgById(orgId);
			}
		}
		log.setOrganization(organization);
		log.setOrgInternalCode(organization.getOrgInternalCode());
		return operateLogDao.addOperateLog(log);
	}

	private OperateLog createOperateLog(String operation,
			Integer operationType, String moduleName, int logLevel,
			String userName, String clientIp, String moduleType,
			String operationContext) {
		OperateLog log = new OperateLog();
		log.setLogLevel(logLevel);
		log.setOperateTime(Calendar.getInstance().getTime());
		log.setOperation(operation);

		log.setOperationType(operationType);
		log.setModuleName(moduleName);
		if (ThreadVariable.getSession() != null
				&& ThreadVariable.getSession().getUserId() != null) {
			log.setUserName(ThreadVariable.getSession().getUserName());
		}

		log.setClientIp(clientIp);
		log.setModuleType(moduleType);
		log.setOperationContent(operationContext);
		return log;
	}

	public int deleteOperateLogByDateRange(Date startDate, Date endDate) {
		return operateLogDao.deleteOperateLogsByDateRange(startDate, endDate);
	}

	public int statisticsOperateLogs(Long orgId, Integer operationType,
			String moduleType, int year, int month) {

		return operateLogDao.statisticsOperateLogs(organizationDubboService
				.getSimpleOrgById(orgId).getOrgInternalCode(), operationType,
				moduleType, CalendarUtil.getMonthStart(year, month),
				CalendarUtil.getNextMonthStart(year, month));
	}

	public Map<String, Integer> statisticsAllOperate(Long orgId,
			List<Integer> operateTypes, String moduleType, int year, int month) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<Map<String, Object>> list = operateLogDao.statisticsAllOperate(
				operateTypes, new ArrayList<String>(
						BaseInfoTables.keyTablesMaps.get(moduleType).keySet()),
				organizationDubboService.getSimpleOrgById(orgId)
						.getOrgInternalCode(), CalendarUtil.getMonthStart(year,
						month), CalendarUtil.getNextMonthStart(year, month));
		for (int i = 0; i < list.size(); i++) {
			Integer num = ((BigDecimal) list.get(i).get("NUM")).intValue();
			Integer operateType = ((BigDecimal) list.get(i).get("OPERATE"))
					.intValue();
			map.put(OperatorType.toString(operateType), num);
		}
		return map;
	}

	public Map<String, Integer> statisticsAllOperateByTableName(Long orgId,
			List<Integer> operateTypes, String tableName, int year, int month) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<String> tables = new ArrayList<String>();
		tables.add(tableName);
		List<Map<String, Object>> list = operateLogDao.statisticsAllOperate(
				operateTypes, tables, organizationDubboService
						.getSimpleOrgById(orgId).getOrgInternalCode(),
				CalendarUtil.getMonthStart(year, month), CalendarUtil
						.getNextMonthStart(year, month));
		for (int i = 0; i < list.size(); i++) {
			Integer num = ((BigDecimal) list.get(i).get("NUM")).intValue();
			Integer operateType = ((BigDecimal) list.get(i).get("OPERATE"))
					.intValue();
			map.put(OperatorType.toString(operateType), num);
		}
		return map;
	}
}
