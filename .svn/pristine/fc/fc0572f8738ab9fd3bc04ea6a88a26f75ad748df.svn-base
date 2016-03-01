package com.tianque.plugin.analyzing.service;

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

import com.tianque.core.util.ThreadVariable;
import com.tianque.datatransfer.ThreadPool;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.xichang.working.flow.constant.AccountType;

/**
 * @Description:网格化服务管理信息系统使用情况每天统计优化job帮助类实现类
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-10 上午09:15:54
 */
@Service("usedInfoEverDayCountOptmizeHelper")
public class UsedInfoEverDayCountOptmizeHelperImpl implements
		UsedInfoEverDayCountOptmizeHelper, ApplicationContextAware {
	private static Logger logger = LoggerFactory
			.getLogger(UsedInfoEverDayCountOptmizeHelperImpl.class);
	private ApplicationContext appContext;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private UsedInfoService usedInfoService;

	@Override
	public void executeDataByModelForUsedInfo() {
		try {
			usedInfoService.createUserSignEveryDay();
			List<Long> idModList = new ArrayList<Long>();
			List<Long> districtIdModList = getGridOrgUpOrgIdList(OrganizationLevel.DISTRICT);
			List<Long> villageIdModList = getGridOrgUpOrgIdList(OrganizationLevel.VILLAGE);
			List<Long> townIdModList = getGridOrgUpOrgIdList(OrganizationLevel.TOWN);
			List<Long> cityIdModList = getGridOrgUpOrgIdList(OrganizationLevel.CITY);
			List<Long> provinceIdModList = getGridOrgUpOrgIdList(OrganizationLevel.PROVINCE);
			List<Long> countryIdModList = getGridOrgUpOrgIdList(OrganizationLevel.COUNTRY);
			idModList.addAll(districtIdModList);
			idModList.addAll(villageIdModList);
			idModList.addAll(townIdModList);
			idModList.addAll(cityIdModList);
			idModList.addAll(provinceIdModList);
			idModList.addAll(countryIdModList);

			List<List<Long>> resultList = analyticalList(idModList);
			List<UsedInfoEverDayCountOptmizeHelperThread> temps = new ArrayList<UsedInfoEverDayCountOptmizeHelperThread>();
			if (resultList != null && resultList.size() > 0) {
				logger.error("开始调用线程统计");
				CountDownLatch threadSignal = new CountDownLatch(
						resultList.size());// 初始化countDown
				for (List<Long> orgIdList : resultList) {
					UsedInfoEverDayCountOptmizeHelperThread usedInfoEverDayCountOptmizeHelperThread = new UsedInfoEverDayCountOptmizeHelperThread(
							orgIdList, ThreadVariable.getSession(), appContext,
							threadSignal);
					temps.add(usedInfoEverDayCountOptmizeHelperThread);
					ThreadPool.getInstance().execute(
							usedInfoEverDayCountOptmizeHelperThread);
				}
				threadSignal.await();// 等待所有子线程执行完
				logger.error("所有线程执行完成");
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类UsedInfoEverDayCountOptmizeHelperImpl的executeDataByModelForUsedInfo方法出现异常，原因：",
					"网格化服务管理信息系统使用情况每天统计job出现错误", e);
		}
	}

	/**
	 * 拆分组织机构(用于拆成多个线程)
	 * 
	 * @param organizations
	 * @return
	 */
	private List<List<Long>> analyticalList(List<Long> orgIds) {
		List<List<Long>> result = new ArrayList<List<Long>>();
		if (orgIds.size() <= 0) {
			return null;
		}
		if (orgIds.size() <= AccountType.PER_PAGE_VILLAGE) {
			result.add(orgIds);
		} else {
			int pageTotal = AccountType.PAGE_TOTAL_VILLAGE;// 4
			int pageSize = orgIds.size() / pageTotal;
			int endNum = 0;
			for (int i = 0; i < pageTotal; i++) {
				endNum = (i + 1) * pageSize - 1;
				if (endNum > orgIds.size()) {
					result.add(orgIds.subList(i * pageSize, orgIds.size() - 1));
				} else {
					result.add(orgIds.subList(i * pageSize,
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
	 * 根据组织机构层级获取组织机构的行政部门
	 * 
	 * @return
	 */
	private List<Long> getGridOrgUpOrgIdList(int orgLevel) {
		PropertyDict gridOrgLevel = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_LEVEL, orgLevel).get(0);

		PropertyDict adminOrgType = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.ADMINISTRATIVE_REGION).get(0);
		List<Long> orgIdList = organizationDubboService
				.getOrganizationsByLevel(adminOrgType.getId(),
						gridOrgLevel.getId());
		return orgIdList;
	}

}
