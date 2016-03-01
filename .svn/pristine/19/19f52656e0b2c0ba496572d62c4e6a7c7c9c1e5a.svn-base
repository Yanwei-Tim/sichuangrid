package com.tianque.xichang.working.report.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.datatransfer.ThreadPool;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.job.AccountReportJobHelperThread;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.report.domain.AccountReport;
import com.tianque.xichang.working.report.service.AccountReportJobHelper;
import com.tianque.xichang.working.report.service.AccountReportService;

@Service("accountReportJobHelper")
public class AccountReportJobHelperImpl extends Thread implements AccountReportJobHelper,
		ApplicationContextAware {
	private static Logger logger = LoggerFactory.getLogger(AccountReportJobHelperImpl.class);

	@Autowired
	OrganizationDubboService organizationDubboService;
	@Autowired
	AccountReportService accountReportService;
	@Autowired
	PropertyDictService propertyDictService;
	private ApplicationContext appContext;

	/**
	 * job调用县统计
	 */
	@Override
	// @Transactional(propagation = Propagation.REQUIRES_NEW)
	public void accountReportDistrict() {
		try {
			List<Organization> organizations = findAllOrganizationExcludeCountry(AccountType.ORGCODEFINDLEVEL_DISTRICT);
			// createReportData(organizations);
			createReportDatas(organizations);
		} catch (Exception e) {
			throw new ServiceValidationException("accountReportDistrict方法出错", "异常信息", e);
		}
	}

	/**
	 * job调用乡镇统计
	 */
	@Override
	// @Transactional(propagation = Propagation.REQUIRES_NEW)
	public void accountReportTown() {
		try {
			List<Organization> organizations = findAllOrganizationExcludeCountry(AccountType.ORGCODEFINDLEVEL_TOWN);
			// createReportData(organizations);
			createReportDatas(organizations);
		} catch (Exception e) {
			throw new ServiceValidationException("accountReportTown方法出错", "异常信息", e);
		}
	}

	/**
	 * job调用社区统计
	 */
	@Override
	// @Transactional(propagation = Propagation.REQUIRES_NEW)
	public void accountReportVillage() {
		try {
			List<Organization> organizations = findAllOrganizationExcludeCountry(AccountType.ORGCODEFINDLEVEL_VILLAGE);
			// createReportData(organizations);
			createReportDatas(organizations);
		} catch (Exception e) {
			throw new ServiceValidationException("accountReportVillage方法出错", "异常信息", e);
		}
	}

	/**
	 * 调用线程
	 * 
	 * @param organizations
	 * @throws InterruptedException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void createReportDatas(List<Organization> organizations) throws InterruptedException {
		List<List<Organization>> resultList = analyticalList(organizations);
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		Map<Integer, Long> orgLevelMap = new HashMap<Integer, Long>();
		if (orgLevels != null && orgLevels.size() > 0) {
			for (PropertyDict orgLevel : orgLevels) {
				orgLevelMap.put(orgLevel.getInternalId(), orgLevel.getId());
			}
		}
		if (resultList != null && resultList.size() > 0) {
			CountDownLatch threadSignal = new CountDownLatch(resultList.size());// 初始化countDown
			for (List<Organization> orgList : resultList) {
				// System.out.println("开始调用线程");
				AccountReportJobHelperThread accountReportJobHelperThread = new AccountReportJobHelperThread(
						orgList, orgLevelMap, ThreadVariable.getSession(), appContext, threadSignal);
				ThreadPool.getInstance().execute(accountReportJobHelperThread);
			}
			threadSignal.await();// 等待所有子线程执行完
			// System.out.println("调用线程结束");
		}

	}

	/**
	 * 拆分组织机构(用于拆成多个线程)
	 * 
	 * @param organizations
	 * @return
	 */
	private List<List<Organization>> analyticalList(List<Organization> organizations) {
		List<List<Organization>> result = new ArrayList<List<Organization>>();
		if (organizations.size() <= 0) {
			return null;
		}
		if (organizations.size() <= AccountType.perPage) {
			result.add(organizations);
		} else {
			int page = organizations.size() / 2;
			List<Organization> orgPageOne = organizations.subList(0, page);
			List<Organization> orgPageTwo = organizations.subList(page + 1,
					organizations.size() - 1);
			result.add(orgPageOne);
			result.add(orgPageTwo);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void createReportData(List<Organization> organizations) {
		List<List<Organization>> resultList = splitList(organizations);
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.ORGANIZATION_LEVEL);
		Map<Integer, Long> orgLevelMap = new HashMap<Integer, Long>();
		if (orgLevels != null && orgLevels.size() > 0) {
			for (PropertyDict orgLevel : orgLevels) {
				orgLevelMap.put(orgLevel.getInternalId(), orgLevel.getId());
			}
		}
		// int i = 1;
		for (List<Organization> orgList : resultList) {
			// System.out.println("循环了" + i + "次组织机构，其中本次共循环了" + orgList.size()
			// + "个组织机构,总共有" + resultList.size() + "组组织机构");
			// long startTime = System.currentTimeMillis();
			List<AccountReport> accountReports = getAllAccountReport(orgList, orgLevelMap);
			for (AccountReport accountReport : accountReports) {
				accountReportService.editAccountReport(accountReport);
			}
			// System.out.println("本次执行完成用了"
			// + (System.currentTimeMillis() - startTime) + "一共有"
			// + accountReports.size() + "个台账");
			// i++;
		}
	}

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

	/** 查询出县级及以下行政部门 */
	private List<Organization> findAllOrganizationExcludeCountry(String orgCodeFindLevel)
			throws Exception {

		/** 行政部门 */
		Organization rootOrg = organizationDubboService.getRootOrganization();

		List<Organization> organizations = null;

		organizations = organizationDubboService.findDistrictAdminOrgsByOrgType(rootOrg.getOrgType()
				.getId(), rootOrg.getOrgInternalCode(), orgCodeFindLevel);
		return organizations;
	}

	/**
	 * 初始的行政部门的AccountReport
	 * 
	 * @return
	 */
	private List<AccountReport> getAllAccountReport(List<Organization> organizations,
			Map<Integer, Long> orgLevelMap) {
		int year = CalendarUtil.getNowYear();
		int month = CalendarUtil.getNowMonth();
		List<AccountReport> accountReports = new ArrayList<AccountReport>();
		AccountReport accountReport;
		for (int i = 1; i <= AccountType.REPORTTOTAL; i++) {
			for (Organization organization : organizations) {

				for (int j = 0; j < AccountType.accountTypes.size(); j++) {

					accountReport = new AccountReport();
					accountReport.setOrganization(organization);
					accountReport.setOrgInternalCode(organization.getOrgInternalCode());
					accountReport.setReportYear(year);
					accountReport.setReportMonth(month);
					accountReport.setAccountType(AccountType.accountTypes.get(j));
					switch (i) {
					case 1:
						/** 县区级报表 */
						if (organization.getOrgLevel().getId()
								.equals(orgLevelMap.get(OrganizationLevel.DISTRICT))) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					case 2:
						/** 乡镇1 */
						if (organization.getOrgLevel().getId()
								.equals(orgLevelMap.get(OrganizationLevel.TOWN))) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					case 3:
						/** 乡镇2 */
						if (organization.getOrgLevel().getId()
								.equals(orgLevelMap.get(OrganizationLevel.TOWN))) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					case 4:
						/** 村、社区报表 */
						if (organization.getOrgLevel().getId()
								.equals(orgLevelMap.get(OrganizationLevel.VILLAGE))) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					case 5:
						/** 西昌1 */
						if (organization.getOrgLevel().getId()
								.equals(orgLevelMap.get(OrganizationLevel.DISTRICT))
								&& AccountType.XICHANG.equals(organization.getOrgName())) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					case 6:
						/** 西昌2 */
						if (organization.getOrgLevel().getId()
								.equals(orgLevelMap.get(OrganizationLevel.DISTRICT))
								&& AccountType.XICHANG.equals(organization.getOrgName())) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					case 7:
						/** 西昌3 */
						if (organization.getOrgLevel().getId()
								.equals(orgLevelMap.get(OrganizationLevel.DISTRICT))
								&& AccountType.XICHANG.equals(organization.getOrgName())) {
							accountReport.setReportType(i);
							accountReport = setAccountReportContent(accountReport);
						}
						break;
					default:
						accountReport.setReportType(0);
					}
					if (accountReport.getReportType() != null && accountReport.getReportType() > 0) {
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
		AccountReport result = accountReportService.getAccountReportByAccountReport(accountReport);
		return (result != null && result.getId() != null) ? result : accountReport;

	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.appContext = context;
	}
}
