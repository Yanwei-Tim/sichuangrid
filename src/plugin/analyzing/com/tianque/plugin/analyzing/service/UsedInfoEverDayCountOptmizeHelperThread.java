package com.tianque.plugin.analyzing.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * @Description:网格化服务管理信息系统使用情况每天统计线程类
 * @author zhangyouwei@hztianque.com
 * @date: 2015-6-10 上午09:45:43
 */
public class UsedInfoEverDayCountOptmizeHelperThread extends Thread {
	private final static Logger logger = LoggerFactory
			.getLogger(UsedInfoEverDayCountOptmizeHelperThread.class);
	private UsedInfoService usedInfoService;
	private PropertyDictService propertyDictService;
	private List<Long> orgIdList;
	private ApplicationContext appContext;
	private Session session;
	private CountDownLatch threadSignal;

	public UsedInfoEverDayCountOptmizeHelperThread(List<Long> orgIdList,
			Session session, ApplicationContext appContext,
			CountDownLatch threadSignal) {
		this.orgIdList = orgIdList;
		this.session = session;
		this.appContext = appContext;
		this.threadSignal = threadSignal;
		init();
	}

	/**
	 * 实例化service
	 */
	private void init() {
		this.usedInfoService = (UsedInfoService) appContext
				.getBean("usedInfoService");
		this.propertyDictService = (PropertyDictService) appContext
				.getBean("propertyDictService");
	}

	private void createThreadUser() {
		ThreadVariable.setSession(session);
		User user = new User();
		user.setId(session.getUserId());
		user.setOrganization(session.getOrganization());
		ThreadVariable.setUser(user);
		ThreadVariable.setSourcesState(ConstantsProduct.SourcesState.IMPORT);
	}

	// public void run() {
	// createThreadUser();
	// int year = CalendarUtil.getNowYear();
	// int month = CalendarUtil.getNowMonth();
	// /** 上月开始 */
	// Date monthStartDate = CalendarUtil.getBeforMonthFirstDayByMonthAndYear(
	// year, month);
	// /** 上月结束 */
	// Date monthEndDate = CalendarUtil.getBeforMonthLastDayByMonthAndYear(
	// year, month);
	// /** 昨天开始 */
	// Date beforDayStartDate = CalendarUtil.getBeforDayStartDate(new Date());
	// /** 昨天结束 */
	// Date beforDayEndDate = CalendarUtil.getBeforDayEndDate(new Date());
	// /** 上周一 */
	// Date beforWeekMonday = CalendarUtil.getBeforWeekMonday(new Date());
	// /** 上周日 */
	// Date beforWeekSunday = CalendarUtil.getBeforWeekSunday(new Date());
	// PropertyDict adminOrgType = propertyDictService
	// .findPropertyDictByDomainNameAndInternalId(
	// PropertyTypes.ORGANIZATION_TYPE,
	// OrganizationType.ADMINISTRATIVE_REGION).get(0);
	// if (orgIdList != null && orgIdList.size() > 0) {
	// for (Long orgId : orgIdList) {
	// usedInfoService.createUsedInfoDataCacheByOrgId(
	// beforDayStartDate, beforDayEndDate, beforWeekMonday,
	// beforWeekSunday, monthStartDate, monthEndDate, orgId,
	// adminOrgType.getId());
	// }
	// }
	// this.threadSignal.countDown();// 线程结束时计数器减1
	// }

	public void run() {
		createThreadUser();

		/** 昨天开始 */
		Date beforDayStartDate = CalendarUtil.getBeforDayStartDate(new Date());
		/** 昨天结束 */
		Date beforDayEndDate = CalendarUtil.getBeforDayEndDate(new Date());

		PropertyDict adminOrgType = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.ADMINISTRATIVE_REGION).get(0);
		if (orgIdList != null && orgIdList.size() > 0) {
			for (Long orgId : orgIdList) {
				try {
					usedInfoService.createUsedInfoDataCacheByOrgIdToOptmize(
							beforDayStartDate, beforDayEndDate, orgId,
							adminOrgType.getId());
				} catch (Exception e) {
					logger.error("组织机构"+orgId+"系统使用情况生成失败："+e);
				}
			}
		}
		this.threadSignal.countDown();// 线程结束时计数器减1
	}
}
