/**
 * Copyright (c) 2015 by 杭州天阙科技有限公司
 */
package com.tianque.job.persistenceJob;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.job.JobHelper;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.tableManage.service.TableManageService;

/**
 * 
 * @deprecated: 收信箱月末分表job
 * @author 曾利民
 * @version 1.0.0
 * @since 2015年3月24日 下午3:29:25
 */
@Component("smsReceivedboxsDispatch")
public class SmsReceivedboxsDispatch implements Serializable {
	private final static Logger LOG = LoggerFactory
			.getLogger(SmsReceivedboxsDispatch.class);
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private TableManageService tableService;
	@Autowired
	private JobMonitorService jobMonitorService;

	private static final String TABLE_NAME = "SMSRECEIVEDBOXS_%s";

	private static final String SMSRECEIVEDBOXS_TABLE_NAME_SQL = "CREATE TABLE "
			+ TABLE_NAME
			+ " AS SELECT * FROM SMSRECEIVEDBOXS WHERE TO_CHAR(RECEIVERTIME,'YYYYMM') = '%s'";

	private static final String CREATE_PRIMARY = "ALTER TABLE " + TABLE_NAME
			+ " ADD CONSTRAINT PK" + TABLE_NAME + " PRIMARY KEY (ID)";

	private static final String CREATE_INDEX = "CREATE INDEX I_" + TABLE_NAME
			+ "_USER ON " + TABLE_NAME + " (USERID )";

	public void executeRec() {
		long startTime = System.currentTimeMillis();
		Date lastDay = CalendarUtil.getBeforeMonthLastDay();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMM");
		String lastMon = sd.format(lastDay);
		String createTableSql = String.format(SMSRECEIVEDBOXS_TABLE_NAME_SQL,
				lastMon, lastMon);
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {

			boolean isExist = tableService.IsCreateTable(String.format(
					TABLE_NAME, lastMon).toUpperCase());
			if (isExist) {
				jobMonitorService.updateJobMonitor(id,
						String.format(TABLE_NAME, lastMon) + "已经存在！", true);
			} else {
				tableService.createTable(createTableSql);
				tableService.crateIndex(String.format(CREATE_PRIMARY, lastMon,
						lastMon));
				tableService.crateIndex(String.format(CREATE_INDEX, lastMon,
						lastMon));
				jobMonitorService.updateJobMonitor(id,
						"花了" + (System.currentTimeMillis() - startTime)
								+ "收信箱月末分表job！", true);
			}

		} catch (Exception e) {
			LOG.error("发信箱月末分表job执行错误：", e);
			jobMonitorService.updateJobMonitor(id,
					"发信箱月末分表job执行错误:" + e.getMessage(), false);
		}
	}
}
