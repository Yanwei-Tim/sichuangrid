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

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.datatransfer.ThreadPool;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.xichang.working.flow.constant.AccountType;
import com.tianque.xichang.working.report.service.AccountReportService;
import com.tianque.xichang.working.report.service.AccountReportVillageJobHelper;
import com.tianque.xichang.working.report.service.AccountReportVillageJobHelperThread;

/**
 * @Description:三本台账社区级报表job统计帮助实现类
 * @author zhangyouwei@hztian.com
 * @date: 2014-8-27 下午03:20:35
 */
@Service("accountReportVillageJobHelper")
public class AccountReportVillageJobHelperImpl implements AccountReportVillageJobHelper,
		ApplicationContextAware {
	private static Logger logger = LoggerFactory.getLogger(AccountReportVillageJobHelperImpl.class);

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private AccountReportService accountReportService;

	private ApplicationContext appContext;

	@Override
	public void accountReportVillage() {
		try {
			int year = CalendarUtil.getNowYear();
			int month = CalendarUtil.getNowMonth();
			List<Long> villageOrgIds = findAllVillageOrgIds(AccountType.ORGCODEFINDLEVEL_VILLAGE);
			createReportDatas(villageOrgIds, year, month);
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
	public void createReportDatas(List<Long> villageOrgIds, int year, int month)
			throws InterruptedException {
		// accountReportService.deleteAllVillageAccountReport(year, month,
		// AccountType.VILLAGE_REPORT_TYPE,
		// AccountType.ORGCODEFINDLEVEL_VILLAGE);
		List<List<Long>> resultList = analyticalList(villageOrgIds);
		if (resultList != null && resultList.size() > 0) {
			CountDownLatch threadSignal = new CountDownLatch(resultList.size());// 初始化countDown
			for (List<Long> villageOrgIdList : resultList) {
				AccountReportVillageJobHelperThread accountReportVillageJobHelperThread = new AccountReportVillageJobHelperThread(
						villageOrgIdList, year, month, ThreadVariable.getSession(), appContext,
						threadSignal);
				ThreadPool.getInstance().execute(accountReportVillageJobHelperThread);
			}
			threadSignal.await();// 等待所有子线程执行完
		}

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
					result.add(villageOrgIds.subList(i * pageSize, villageOrgIds.size() - 1));
				} else {
					result.add(villageOrgIds.subList(i * pageSize, ((i + 1) * pageSize) - 1));
				}
			}
		}
		return result;
	}

	/**
	 * 查询所有社区级别组织机构
	 * 
	 * @param orgcodefindlevelVillage
	 * @return
	 */
	private List<Long> findAllVillageOrgIds(String orgcodefindlevelVillage) {
		Long orgTypeId = getPropertyDictIdByDomainNameAndInternalId(OrganizationType.ORG_TYPE_KEY,
				OrganizationType.ADMINISTRATIVE_REGION);
		Long orgLevelId = getPropertyDictIdByDomainNameAndInternalId(
				OrganizationLevel.ORG_LEVEL_KEY, OrganizationLevel.VILLAGE);
		return organizationDubboService.getOrganizationsByLevel(orgTypeId, orgLevelId);

	}

	private Long getPropertyDictIdByDomainNameAndInternalId(String domainName,
			Integer orgTypeInternalId) {
		if (null == domainName || "".equals(domainName) || null == orgTypeInternalId) {
			return null;
		}
		List<PropertyDict> dicts = propertyDictService.findPropertyDictByDomainNameAndInternalId(
				domainName, orgTypeInternalId);
		if (null == dicts || dicts.size() == 0) {
			return null;
		}
		return dicts.get(0).getId();
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.appContext = context;
	}

}
