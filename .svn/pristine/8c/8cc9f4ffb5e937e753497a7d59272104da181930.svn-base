package com.tianque.job;

import java.util.Date;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.job.realization.CorrectionExpire;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.tableManage.service.TableManageService;

@Component("systemLogsTableCreateJob")
public class SystemLogsTableCreateJob implements Job {
	private static Logger logger = LoggerFactory
			.getLogger(CorrectionExpire.class);
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private TableManageService tableService;
	@Autowired
	private JobMonitorService jobMonitorService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		createSystemLogTable();
	}

	public void createSystemLogTable() {
		logger.error("系统操作日志下一个月建表JOB开始执行");
		Date nextMonth = CalendarUtil.getNextMonthFirstDay();
		int year = CalendarUtil.getYear(nextMonth);
		int month = CalendarUtil.getMonth(nextMonth);
		long startTime = System.currentTimeMillis();
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {

			tableService.createAnalyseTable(AnalyseUtil.SYSTEMLOGS_TABLE_NAME,
					AnalyseUtil.SYSTEMLOGS_TABLE_NAME_SQL, year, month);
			createSystemLogTableIndex(year, month);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行系统操作日志下一个月建表job！", true);
		} catch (Exception e) {
			logger.error("系统操作日志下一个月建表job执行错误：", e);
			jobMonitorService.updateJobMonitor(id,
					"系统操作日志下一个月建表job执行错误:" + e.getMessage(), false);
		}
	}

	/**
	 * 创建systemLog表的索引
	 * 
	 * @param year
	 * @param month
	 */
	private void createSystemLogTableIndex(int year, int month) {
		String indexName = "";
		boolean isCreatIndex = false;
		for (Map.Entry<String, String> entry : AnalyseUtil.SYSTEMLOG_INDEX_MAP
				.entrySet()) {
			indexName = "idx_syslog_" + year + "_" + month + "_"
					+ entry.getValue();
			isCreatIndex = tableService.isCreateIndexByIndexName(indexName);
			if (!isCreatIndex) {
				tableService.crateIndex(assemblyIndexSql(year, month,
						entry.getKey(), entry.getValue()));
			}
		}

	}

	/**
	 * 拼接出一个创建索引的sql
	 * 
	 * @param year
	 * @param month
	 * @param column
	 * @return
	 */
	private String assemblyIndexSql(int year, int month, String column,
			String columnName) {
		String indexSql = "";
		if (StringUtil.isStringAvaliable(column)) {
			indexSql = "create index idx_syslog_" + year + "_" + month + "_"
					+ columnName + " on Systemlogs_" + year + "_" + month + "("
					+ column + ")";
		}
		return indexSql;
	}
}
