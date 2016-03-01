package com.tianque.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.mobile.mobileDictionary.service.MobileDictionaryService;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.PermissionService;
@Component("mobileDictionaryJob")
public class MobileDictionaryJob {
	private static Logger logger = LoggerFactory
	.getLogger(MobileDictionaryJob.class);
	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private MobileDictionaryService mobileDictionaryService;
	@Autowired
	private PermissionService permissionService;
	
	public void createMobileDictionary() {
		long startTime = System.currentTimeMillis();
		JobHelper.createMockAdminSession();
		ThreadVariable.setUser(permissionService.findUserByUserName("admin"));
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			//判断是否为当前月份第一天的0点，即日为1，小时为00，其他不判断
			if(CalendarUtil.isMothStartTime()){
				mobileDictionaryService.addOrUpdateMobileDictionary();
			}else{
				mobileDictionaryService.addOrUpdateMobileDictionaryIncrement();
			}
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "执行生成手机数据字典job！", true);
		} catch (Exception e) {
			logger.error("生成手机数据字典job执行错误：", e);
			jobMonitorService.updateJobMonitor(id,
					"生成手机数据字典job执行错误:" + e.getMessage(), false);
		}
	}
}
