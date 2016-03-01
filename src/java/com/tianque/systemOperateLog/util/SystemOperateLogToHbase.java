package com.tianque.systemOperateLog.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.systemOperateLog.dao.SystemOperateLogDao;
import com.tianque.systemOperateLog.domain.SystemOperateLog;

@Service("systemOperateLogToHbase")
public class SystemOperateLogToHbase {
	public static Logger logger = LoggerFactory
			.getLogger(SystemOperateLogToHbase.class);

	@Qualifier("systemOperateLogDao")
	@Autowired
	private SystemOperateLogDao systemOperateLogDao;
	@Qualifier("systemOperateLogHbaseDao")
	@Autowired
	private SystemOperateLogDao systemOperateLogHbaseDao;

	public void systemOperateLogToHbasess() throws Exception {
		logger.error("----------系统操作日志转移hbase开始----------");
		List<SystemOperateLog> results = null;
		long startM = System.currentTimeMillis();
		long start = 0L;
		int page = 1;
		long importCount = 0;
		do {
			try {
				start = System.currentTimeMillis();
				PageInfo<SystemOperateLog> SystemOperateLogs = systemOperateLogDao
						.findSystemLogsForPageImportToHbase(page, 1000,
								"operateDate", "asc");
				logger.error("查询1000条耗时:"
						+ (System.currentTimeMillis() - start));
				results = SystemOperateLogs.getResult();
				start = System.currentTimeMillis();
				// systemOperateLogHbaseDao.addSystemOperateLogsForBatch(results);
				logger.error("导入1000条耗时:"
						+ (System.currentTimeMillis() - start));
				page++;
				importCount += results.size();
			} catch (Exception e) {
				logger.error("导入出错:", e);
			}
		} while (results.size() == 1000);
		logger.error("系统操作日志转移hbase结束，总共导入" + importCount + "条，耗时："
				+ (System.currentTimeMillis() - startM));

	}

	public static void main(String[] args) throws Exception {
		new SystemOperateLogToHbase().systemOperateLogToHbasess();

	}
}
