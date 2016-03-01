package com.tianque.job;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.plugin.analyzing.service.IssueStatService;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("issueDealStatJob")
public class IssueDealStatJob implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IssueStatService statService;
	@Autowired
	private OrganizationDubboService orgService;
	@Autowired
	private JobMonitorService jobMonitorService;

	/**
	 * 服务办事办理统计
	 */
	public void addMonthOrgLoginStanals() {
		JobHelper.createMockAdminSession();
		Long id = jobMonitorService.addJobMonitor(this.getClass().getName());
		try {
			long startTime = System.currentTimeMillis();
			Organization rootOrg = orgService.findOrganizationsByParentId(null).get(0);
			statService.buildIssueDealStatsByMonth(rootOrg.getId(),
					CalendarUtil.getLastMonthYearValue(), CalendarUtil.getLastMonth());
			jobMonitorService.updateJobMonitor(id, "花了" + (System.currentTimeMillis() - startTime)
					+ "执行服务办事办理统计job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id, "服务办事办理统计job出现异常：" + e.getMessage(), false);
		}
	}

}
