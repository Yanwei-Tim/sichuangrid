package com.tianque.xichang.working.report.service.impl;

import java.util.ArrayList;
import java.util.List;
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

import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.datatransfer.ThreadPool;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tableManage.service.TableManageService;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.report.domain.AccountReport;
import com.tianque.xichang.working.report.service.AccountReportJobOptmizeHelper;
import com.tianque.xichang.working.report.service.AccountReportService;
import com.tianque.xichang.working.report.service.AccountReportVillageJobHelperThread;

/**
 * @Description:三本台账报表统计优化的job帮助类
 * @author zhangyouwei@hztianque.com
 * @date: 2014-11-20 上午11:15:30
 */
@Service("accountReportJobOptmizeHelper")
public class AccountReportJobOptmizeHelperImpl implements
		AccountReportJobOptmizeHelper, ApplicationContextAware {
	private static Logger logger = LoggerFactory
			.getLogger(AccountReportJobOptmizeHelperImpl.class);

	private ApplicationContext appContext;
	@Autowired
	private TableManageService tableManageService;
	@Autowired
	private AccountReportService accountReportService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	/** 西昌市的常量 */
	private static final String XICHANG_ORGNAME = "西昌市";

	@Override
	public int executeDataByModelForAccountReportTown(Integer year,
			Integer month, List<Long> idModList, String taskParameter) {
		if (idModList == null || idModList.size() == 0
				|| !StringUtil.isStringAvaliable(taskParameter)) {
			return 0;
		}
		try {
			// 根据取模去删除原有的数据脏数据
			deleteDirtyDataByModel(idModList, taskParameter, year, month,
					OrganizationLevel.TOWN,
					OrganizationType.ADMINISTRATIVE_REGION);
			// 一次性拿出所有取模后对应的数据然后处理
			return executeDataOnPaging(idModList, taskParameter, year, month,
					OrganizationLevel.TOWN,
					OrganizationType.ADMINISTRATIVE_REGION);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类AccountReportJobOptmizeHelperImpl的executeDataByModelForAccountReportTown方法出现异常，原因：",
					"三本台账乡镇级报表job出现错误", e);
		}
	}

	@Override
	public int executeDataByModelForAccountReportDistrict(Integer year,
			Integer month, List<Long> idModList, String taskParameter) {
		if (idModList == null || idModList.size() == 0
				|| !StringUtil.isStringAvaliable(taskParameter)) {
			return 0;
		}
		try {
			// 根据取模去删除原有的数据脏数据
			// 因为县区级别有可维护的数据，所以不能够去删除原有数据
			tableManageService.createAnalyseTable(
					AnalyseUtil.ACCOUNT_REPORT_TABLE_NAME,
					AnalyseUtil.ACCOUNT_REPORT_TABLE_NAME_SQL, year, month);
			// deleteDirtyDataByModel(idModList, taskParameter, year, month,
			// OrganizationLevel.DISTRICT,
			// OrganizationType.ADMINISTRATIVE_REGION);
			// 一次性拿出所有取模后对应的数据然后处理
			return executeDataOnPaging(idModList, taskParameter, year, month,
					OrganizationLevel.DISTRICT,
					OrganizationType.ADMINISTRATIVE_REGION);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类AccountReportJobOptmizeHelperImpl的executeDataByModelForAccountReportDistrict方法出现异常，原因：",
					"三本台账县区级报表job出现错误", e);
		}
	}

	@Override
	public int executeDataByModelForAccountReportVillage(Integer year,
			Integer month, List<Long> idModList, String taskParameter) {
		if (idModList == null || idModList.size() == 0
				|| !StringUtil.isStringAvaliable(taskParameter)) {
			return 0;
		}
		try {
			// 根据取模去删除原有的数据脏数据
			deleteDirtyDataByModel(idModList, taskParameter, year, month,
					OrganizationLevel.VILLAGE,
					OrganizationType.ADMINISTRATIVE_REGION);
			// 社区级别的一次性拿出所有取模后对应的数据然后多线程处理
			return executeDataOnPagingForVillage(idModList, taskParameter,
					year, month);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类AccountReportJobOptmizeHelperImpl的executeDataByModelForAccountReportVillage方法出现异常，原因：",
					"三本台账社区级报表job出现错误", e);
		}
	}

	/**
	 * 乡镇和县区级别处理
	 * 
	 * @param idModList
	 * @param taskParameter
	 * @param year
	 * @param month
	 * @param orgLevelInternalId
	 * @param orgTypeInternalId
	 * @throws InterruptedException
	 */
	private int executeDataOnPaging(List<Long> idModList, String taskParameter,
			int year, int month, int orgLevelInternalId, int orgTypeInternalId)
			throws InterruptedException {
		List<Long> orgIds = organizationDubboService
				.queryOrgIdsByModelForAccountReport(idModList, taskParameter,
						orgLevelInternalId, orgTypeInternalId);
		if (orgLevelInternalId == OrganizationLevel.TOWN) {
			return createTownReportDatas(orgIds, year, month);
		} else if (orgLevelInternalId == OrganizationLevel.DISTRICT) {
			return createDistrictReportDatas(orgIds, year, month);
		}
		return 0;
	}

	/**
	 * 分页处理每次分1000条
	 * 
	 * @param orgIds
	 * @return
	 */
	/*
	 * private List<List<Long>> pagingOrgList(List<Long> orgIds) {
	 * List<List<Long>> result = new ArrayList<List<Long>>(); List<Long>
	 * resultList = null; if (orgIds != null && orgIds.size() > 0) {
	 * 
	 * int count = orgIds.size(); int pageSize =
	 * AccountType.ACCOUNT_TIMEOUTS_PAGE_SIZE; int page = 0; int model = count %
	 * pageSize; if (model == 0) { page = count / pageSize; } else { page =
	 * count / pageSize + 1; } for (int pageCount = 1; pageCount <= page;
	 * pageCount++) { resultList = new ArrayList<Long>(); for (int i =
	 * (pageCount - 1) * pageSize; i < pageCount pageSize; i++) { if (i >=
	 * orgIds.size() - 1) { i = orgIds.size() - 1;
	 * resultList.add(orgIds.get(i)); break; } resultList.add(orgIds.get(i)); }
	 * result.add(resultList); }
	 * 
	 * } return result; }
	 */

	/**
	 * 县区级别的数据
	 * 
	 * @param asList
	 * @param year
	 * @param month
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int createDistrictReportDatas(List<Long> districtOrgIds, int year,
			int month) {
		AccountReport accountReport;
		Organization org;
		List<AccountReport> accountReports = new ArrayList<AccountReport>();
		int size = 0;
		/** 四个县区级别的报表 */
		for (Integer districtAccountType : AccountType.DISTRICT_ACCOUNT_TYPES) {

			/** 循环三个类型的台账 */
			for (String accountType : AccountType.accountTypes) {
				/** 循环县区级的orgId */
				for (Long districtOrgId : districtOrgIds) {
					accountReport = new AccountReport();
					org = organizationDubboService
							.getSimpleOrgById(districtOrgId);
					if (districtAccountType != AccountType.DISTRICT_REPORT_TYPE
							&& org != null
							&& !XICHANG_ORGNAME.equals(org.getOrgName())) {
						continue;
					}
					accountReport.setAccountType(accountType);
					accountReport.setOrganization(org);
					accountReport.setOrgInternalCode(org.getOrgInternalCode());
					accountReport.setReportMonth(month);
					accountReport.setReportYear(year);
					accountReport.setReportType(districtAccountType);
					accountReports.add(accountReport);
					if (accountReports.size() > AccountType.ACCOUNT_TIMEOUTS_PAGE_SIZE) {
						accountReportService
								.batchDistrictAccountReportForJob(accountReports);
						size += accountReports.size();
						accountReports.clear();
					}
				}
			}
		}
		if (!accountReports.isEmpty()) {
			accountReportService
					.batchDistrictAccountReportForJob(accountReports);
			size += accountReports.size();
		}
		return size;
	}

	/**
	 * 乡镇级别的数据
	 * 
	 * @param asList
	 * @param year
	 * @param month
	 */
	private int createTownReportDatas(List<Long> townOrgIds, int year, int month) {
		AccountReport accountReport;
		Organization org;
		List<AccountReport> accountReports = new ArrayList<AccountReport>();
		int size = 0;
		/** 两个乡镇级别的报表 */
		for (Integer townAccountType : AccountType.TOWN_ACCOUNT_TYPES) {

			/** 循环三个类型的台账 */
			for (String accountType : AccountType.accountTypes) {
				/** 循环社乡镇的orgId */
				for (Long townOrgId : townOrgIds) {
					accountReport = new AccountReport();
					org = organizationDubboService.getSimpleOrgById(townOrgId);
					/** 由于根据orgId获取的值会放在缓存里面所以不会消耗很大的查询时间 */
					accountReport.setAccountType(accountType);
					accountReport.setOrganization(org);
					accountReport.setOrgInternalCode(org.getOrgInternalCode());
					accountReport.setReportMonth(month);
					accountReport.setReportYear(year);
					accountReport.setReportType(townAccountType);
					accountReports.add(accountReport);
					if (accountReports.size() > AccountType.ACCOUNT_TIMEOUTS_PAGE_SIZE) {
						accountReportService
								.batchTownAccountReportForJob(accountReports);
						size += accountReports.size();
						accountReports.clear();
					}
				}
			}
		}
		if (!accountReports.isEmpty()) {
			accountReportService.batchTownAccountReportForJob(accountReports);
			size += accountReports.size();
		}
		return size;

	}

	/**
	 * 调用线程
	 * 
	 * @param organizations
	 * @throws InterruptedException
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int createVillageReportDatas(List<Long> villageOrgIds, int year,
			int month) throws InterruptedException {
		List<List<Long>> resultList = analyticalList(villageOrgIds);
		List<AccountReportVillageJobHelperThread> temps = new ArrayList<AccountReportVillageJobHelperThread>();
		if (resultList != null && resultList.size() > 0) {
			CountDownLatch threadSignal = new CountDownLatch(resultList.size());// 初始化countDown
			for (List<Long> villageOrgIdList : resultList) {
				AccountReportVillageJobHelperThread accountReportVillageJobHelperThread = new AccountReportVillageJobHelperThread(
						villageOrgIdList, year, month,
						ThreadVariable.getSession(), appContext, threadSignal);
				temps.add(accountReportVillageJobHelperThread);
				ThreadPool.getInstance().execute(
						accountReportVillageJobHelperThread);
			}
			threadSignal.await();// 等待所有子线程执行完
		}
		int size = 0;
		for (AccountReportVillageJobHelperThread temp : temps) {
			size += temp.getSize();
		}
		return size;
	}

	/**
	 * 拆分组织机构(用于拆成多个线程)
	 * 
	 * @param organizations
	 * @return
	 */
	private List<List<Long>> analyticalList(List<Long> villageOrgIds) {
		List<List<Long>> result = new ArrayList<List<Long>>();
		if (villageOrgIds.size() <= 0) {
			return null;
		}
		if (villageOrgIds.size() <= AccountType.PER_PAGE_VILLAGE) {
			result.add(villageOrgIds);
		} else {
			int pageTotal = AccountType.PAGE_TOTAL_VILLAGE;// 4
			int pageSize = villageOrgIds.size() / pageTotal;
			int endNum = 0;
			for (int i = 0; i < pageTotal; i++) {
				endNum = (i + 1) * pageSize - 1;
				if (endNum > villageOrgIds.size()) {
					result.add(villageOrgIds.subList(i * pageSize,
							villageOrgIds.size() - 1));
				} else {
					result.add(villageOrgIds.subList(i * pageSize,
							((i + 1) * pageSize) - 1));
				}
			}
		}
		return result;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.appContext = context;
	}

	/**
	 * 社区级别的一次性拿出所有取模后对应的数据然后多线程处理
	 * 
	 * @param idModList
	 * @param taskParameter
	 * @param year
	 * @param month
	 */
	private int executeDataOnPagingForVillage(List<Long> idModList,
			String taskParameter, int year, int month) throws Exception {
		List<Long> villageOrgIds = organizationDubboService
				.queryOrgIdsByModelForAccountReport(idModList, taskParameter,
						OrganizationLevel.VILLAGE,
						OrganizationType.ADMINISTRATIVE_REGION);

		if (villageOrgIds != null && villageOrgIds.size() > 0) {
			return createVillageReportDatas(villageOrgIds, year, month);
		} else {
			return 0;
		}
	}

	/**
	 * 删除脏数据（只是当前应用中根据取模后能够生成的数据）
	 * 
	 * @param idModList
	 * @param taskParameter
	 * @param year
	 * @param month
	 * @param orgLevelInternalId
	 * @param orgTypeInternalId
	 */
	private void deleteDirtyDataByModel(List<Long> idModList,
			String taskParameter, int year, int month, int orgLevelInternalId,
			int orgTypeInternalId) {
		if (idModList == null || idModList.size() <= 0) {
			logger.error("类AccountReportJobOptmizeHelperImpl的deleteDirtyDataByModel方法错误原因是传入的参数错误");
			return;
		}
		boolean isCreate = tableManageService.createAnalyseTable(
				AnalyseUtil.ACCOUNT_REPORT_TABLE_NAME,
				AnalyseUtil.ACCOUNT_REPORT_TABLE_NAME_SQL, year, month);
		if (!isCreate) {
			int sum = accountReportService.countDirtyDataByModel(idModList,
					taskParameter, year, month, orgLevelInternalId,
					orgTypeInternalId);

			if (sum > 0) {
				accountReportService.deleteDirtyDataByModel(idModList,
						taskParameter, year, month, orgLevelInternalId,
						orgTypeInternalId);
			}
		}
	}

}
