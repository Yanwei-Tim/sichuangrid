package com.tianque.xichang.working.report.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.context.ApplicationContext;

import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.report.domain.AccountReport;

/**
 * 三本台账报表统计线程
 * 
 * @author Administrator
 * 
 */

public class AccountReportVillageJobHelperThread extends Thread {
	private AccountReportService accountReportService;
	private List<Long> villageOrgIdList;
	private ApplicationContext appContext;
	private Session session;
	private CountDownLatch threadSignal;
	private int year;
	private int month;
	private int size;

	public AccountReportVillageJobHelperThread(List<Long> villageOrgIdList,
			int year, int month, Session session,
			ApplicationContext appContext, CountDownLatch threadSignal) {
		this.year = year;
		this.month = month;
		this.villageOrgIdList = villageOrgIdList;
		this.session = session;
		this.appContext = appContext;
		this.threadSignal = threadSignal;
		init();

	}

	/**
	 * 实例化service
	 */
	private void init() {
		this.accountReportService = (AccountReportService) appContext
				.getBean("accountReportService");
	}

	/**
	 * 拆分组织机构
	 * 
	 * @param list
	 * @return
	 */
	/*private List<List<Long>> splitList(List<Long> list) {
		List<List<Long>> result = new ArrayList<List<Long>>();
		List<Long> resultList = null;
		if (list != null && list.size() > 0) {
			int count = list.size();
			int pageSize = AccountType.ACCOUNT_TIMEOUTS_PAGE_SIZE;
			int page = 0;
			int model = count % pageSize;
			if (model == 0) {
				page = count / pageSize;
			} else {
				page = count / pageSize + 1;
			}
			for (int pageCount = 1; pageCount <= page; pageCount++) {
				resultList = new ArrayList<Long>();
				for (int i = (pageCount - 1) * pageSize; i < pageCount
						* pageSize; i++) {
					if (i >= list.size() - 1) {
						i = list.size() - 1;
						resultList.add(list.get(i));
						break;
					}
					resultList.add(list.get(i));
				}
				result.add(resultList);
			}

		}
		return result;
	}*/

	/**
	 * 初始的行政部门的AccountReport
	 * 
	 * @return
	 */
	/*private List<AccountReport> getAllVillageAccountReport(
			List<Long> villageOrgIds) {
		List<AccountReport> accountReports = new ArrayList<AccountReport>();
		AccountReport accountReport;
		Organization org;
		*//** 循环三个类型的台账 */
	/*
	for (String accountType : AccountType.accountTypes) {
	*//** 循环社区级的orgId */
	/*
	for (Long villageOrgId : villageOrgIds) {
	accountReport = new AccountReport();
	org = new Organization();
	*//** 由于根据orgId获取的值会放在缓存里面所以不会消耗很大的查询时间 */
	/*
	org.setId(villageOrgId);
	accountReport.setAccountType(accountType);
	accountReport.setOrganization(org);
	accountReport.setOrgInternalCode(org.getOrgInternalCode());
	accountReport.setReportMonth(month);
	accountReport.setReportYear(year);
	*//** 由于社区的台账的报表只有一张并且类型都是相同的为4 */
	/*
	accountReport.setReportType(AccountType.VILLAGE_REPORT_TYPE);
	*//** 每次创建后就放到list里面 */
	/*
	accountReports.add(accountReport);
	}

	}
	return accountReports;
	}*/

	private void createThreadUser() {
		ThreadVariable.setSession(session);
		User user = new User();
		user.setId(session.getUserId());
		user.setOrganization(session.getOrganization());
		ThreadVariable.setUser(user);
		ThreadVariable.setSourcesState(ConstantsProduct.SourcesState.IMPORT);
	}

	public void run() {
		createThreadUser();

		List<AccountReport> accountReports = new ArrayList<AccountReport>();
		AccountReport accountReport;
		Organization org;
		/** 循环三个类型的台账 */
		for (String accountType : AccountType.accountTypes) {
			/** 循环社区级的orgId */
			for (Long villageOrgId : villageOrgIdList) {
				accountReport = new AccountReport();
				org = new Organization();
				/** 由于根据orgId获取的值会放在缓存里面所以不会消耗很大的查询时间 */
				org.setId(villageOrgId);
				accountReport.setAccountType(accountType);
				accountReport.setOrganization(org);
				accountReport.setOrgInternalCode(org.getOrgInternalCode());
				accountReport.setReportMonth(month);
				accountReport.setReportYear(year);
				/** 由于社区的台账的报表只有一张并且类型都是相同的为4 */
				accountReport.setReportType(AccountType.VILLAGE_REPORT_TYPE);
				/** 每次创建后就放到list里面 */
				accountReports.add(accountReport);
				if (accountReports.size() > AccountType.ACCOUNT_TIMEOUTS_PAGE_SIZE) {
					accountReportService
							.batchAddVillageAccountReportForJob(accountReports);
					size += accountReports.size();
					accountReports.clear();
				}
			}

		}

		if (!accountReports.isEmpty()) {
			accountReportService
					.batchAddVillageAccountReportForJob(accountReports);
			size += accountReports.size();
		}

		this.threadSignal.countDown();// 线程结束时计数器减1
	}

	public int getSize() {
		return size;
	}
}
