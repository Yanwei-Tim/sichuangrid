package com.tianque.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.context.ApplicationContext;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.report.domain.AccountReport;
import com.tianque.xichang.working.report.service.AccountReportService;

/**
 * 三本台账报表统计线程
 * 
 * @author Administrator
 * 
 */

public class AccountReportJobHelperThread extends Thread {
	private AccountReportService accountReportService;
	private List<Organization> organizations;
	private Map<Integer, Long> orgLevelMap;
	private ApplicationContext appContext;
	private Session session;
	private CountDownLatch threadSignal;

	public AccountReportJobHelperThread(List<Organization> organizations,
			Map<Integer, Long> orgLevelMap, Session session,
			ApplicationContext appContext, CountDownLatch threadSignal) {
		this.organizations = organizations;
		this.orgLevelMap = orgLevelMap;
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
	private List<List<Organization>> splitList(List<Organization> list) {
		List<List<Organization>> result = new ArrayList<List<Organization>>();
		if (list != null && list.size() > 0) {
			int page = list.size() / AccountType.perPage + 1;
			for (int pageCount = 1; pageCount <= page; pageCount++) {
				List<Organization> resultList = new ArrayList<Organization>();
				for (int i = (pageCount - 1) * AccountType.perPage; i < pageCount
						* AccountType.perPage; i++) {
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
	}

	/**
	 * 初始的行政部门的AccountReport
	 * 
	 * @return
	 */
	private List<AccountReport> getAllAccountReport(
			List<Organization> organizations, Map<Integer, Long> orgLevelMap) {
		int year = CalendarUtil.getNowYear();
		int month = CalendarUtil.getNowMonth();
		List<AccountReport> accountReports = new ArrayList<AccountReport>();
		AccountReport accountReport;
		for (int i = 1; i <= AccountType.REPORTTOTAL; i++) {
			for (Organization organization : organizations) {

				for (int j = 0; j < AccountType.accountTypes.size(); j++) {

					accountReport = new AccountReport();
					accountReport.setOrganization(organization);
					accountReport.setOrgInternalCode(organization
							.getOrgInternalCode());
					accountReport.setReportYear(year);
					accountReport.setReportMonth(month);
					accountReport.setAccountType(AccountType.accountTypes
							.get(j));
					switch (i) {
					case 1:
						/** 县区级报表 */
						if (organization
								.getOrgLevel()
								.getId()
								.equals(orgLevelMap
										.get(OrganizationLevel.DISTRICT))) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					case 2:
						/** 乡镇1 */
						if (organization
								.getOrgLevel()
								.getId()
								.equals(orgLevelMap.get(OrganizationLevel.TOWN))) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					case 3:
						/** 乡镇2 */
						if (organization
								.getOrgLevel()
								.getId()
								.equals(orgLevelMap.get(OrganizationLevel.TOWN))) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					case 4:
						/** 村、社区报表 */
						if (organization
								.getOrgLevel()
								.getId()
								.equals(orgLevelMap
										.get(OrganizationLevel.VILLAGE))) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					case 5:
						/** 西昌1 */
						if (organization
								.getOrgLevel()
								.getId()
								.equals(orgLevelMap
										.get(OrganizationLevel.DISTRICT))
								&& AccountType.XICHANG.equals(organization
										.getOrgName())) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					case 6:
						/** 西昌2 */
						if (organization
								.getOrgLevel()
								.getId()
								.equals(orgLevelMap
										.get(OrganizationLevel.DISTRICT))
								&& AccountType.XICHANG.equals(organization
										.getOrgName())) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					case 7:
						/** 西昌3 */
						if (organization
								.getOrgLevel()
								.getId()
								.equals(orgLevelMap
										.get(OrganizationLevel.DISTRICT))
								&& AccountType.XICHANG.equals(organization
										.getOrgName())) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					default:
						accountReport.setReportType(0);
					}
					if (accountReport.getReportType() != null
							&& accountReport.getReportType() > 0) {
						accountReports.add(accountReport);
					}

				}
			}
		}
		return accountReports;
	}

	/**
	 * 根据accountReport查询出原有content数据
	 * 
	 * @param accountReport
	 * @return
	 */
	private AccountReport setAccountReportContent(AccountReport accountReport) {
		AccountReport result = accountReportService
				.getAccountReportByAccountReport(accountReport);
		return (result != null && result.getId() != null) ? result
				: accountReport;

	}

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
		// System.out.println("子线程睡5秒");
		// Thread.sleep(5000);
		List<List<Organization>> organizationsTemp = splitList(organizations);
		for (List<Organization> orgList : organizationsTemp) {
			List<AccountReport> accountReports = getAllAccountReport(orgList,
					orgLevelMap);
			for (AccountReport accountReport : accountReports) {
				accountReportService.editAccountReport(accountReport);
			}
		}
		this.threadSignal.countDown();// 线程结束时计数器减1
	}
}
