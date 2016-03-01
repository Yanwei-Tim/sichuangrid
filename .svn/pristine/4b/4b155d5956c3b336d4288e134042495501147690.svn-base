package com.tianque.plugin.analysisNew.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.analysisNew.dao.OrgLoginStanalsNewDao;
import com.tianque.plugin.analysisNew.domain.OrgLoginStanals;
import com.tianque.plugin.analysisNew.util.OrgLoginStanalsBuilderNew;
import com.tianque.service.WorkCalendarService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.tableManage.service.TableManageService;
import com.tianque.ticket.service.TicketService;

@Service("orgLoginStanalsNewService")
@Scope("prototype")
public class OrgLoginStanalsNewServiceImpl extends AbstractBaseService
		implements OrgLoginStanalsNewService {
	@Autowired
	private OrgLoginStanalsNewDao orgLoginStanalsNewDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private WorkCalendarService workCalendarService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private TableManageService tableService;
	@Autowired
	private CacheService cacheService;
	private TicketService ticketService;
	private String ticketId;

	@Override
	public void setTicketService(TicketService ticketService, String ticketId) {
		this.ticketService = ticketService;
		this.ticketId = ticketId;
	}

	@Override
	public void reCreateOrgLoginStanals(int year, int month) {
		Set<Long> set;
		if (month == 1)
			set = workCalendarService.getWorkCalendarByYear(Long
					.valueOf(year - 1));
		else if (month == 12)
			set = workCalendarService.getWorkCalendarByYear(Long
					.valueOf(year + 1));
		else
			set = workCalendarService.getWorkCalendarByYear(Long.valueOf(year));

		if (set == null || set.size() == 0) {
			if (month == 1)
				throw new BusinessValidationException("请初始化" + (year - 1)
						+ "年度的工作日历!");
			else if (month == 12)
				throw new BusinessValidationException("请初始化" + (year + 1)
						+ "年度的工作日历!");
			else
				throw new BusinessValidationException("请初始化" + (year)
						+ "年度的工作日历!");
		} else {
			OrgLoginStanalsBuilderNew builder = new OrgLoginStanalsBuilderNew(
					workCalendarService, propertyDictService,
					orgLoginStanalsNewDao, ticketService, tableService);
			try {
				builder.updateTicketInfo(ticketId, "{msg:'正在清理数据...'}");
				builder.clearExistedStatData(year, month);
				builder.updateTicketInfo(ticketId, "{msg:'开始生成数据...'}");
				builder.createOrgLoginStatData(year, month);
			} catch (Exception e) {
				throw new ServiceValidationException(e.toString(), e);
			}
		}
	}

	/*
	 * private Organization getNeedReCreateStatOrganization(Long orgId) { if
	 * (orgId != null) { return organizationService.getSimpleOrgById(orgId); }
	 * return organizationService.findOrganizationsByParentId(null).get(0); }
	 */

	@Override
	public List<OrgLoginStanals> getOrgLoginStanalsForList(int year, int month,
			Long orgId, int internalId) {
		if (orgId == null) {
			throw new BusinessValidationException("组织机构信息未获得");
		}
		String key = MemCacheConstant.getOrgLoginStatisticsCachKey(
				MemCacheConstant.ORGLOGINSTATISTICS_CACHKEY, orgId, internalId,
				year, month, ModulTypes.STATISTICSTABLELIST);

		List<OrgLoginStanals> list = null;
		if (key != null) {
			list = (List<OrgLoginStanals>) cacheService.get(
					MemCacheConstant.ANALYSE_ORGLOGINSTATISTICS_NAMESPACE, key);
			if (list != null) {
				return list;
			}
		}
		List<PropertyDict> orgTypes = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						OrganizationType.ORG_TYPE_KEY, internalId);
		List<Long> organizationId = organizationDubboService
				.findOrganizationsByParentIdAndType(orgId, orgTypes.get(0)
						.getId().intValue());
		if (organizationId != null && organizationId.size() != 0) {
			list = orgLoginStanalsNewDao.getOrgLoginStanalsForList(year, month,
					organizationId);
		} else {
			list = new ArrayList<OrgLoginStanals>();
		}
		/*
		 * OrgLoginStanals root = orgLoginStanalsNewDao
		 * .getRootOrgLoginStanalsForList(year, month, orgId, orgTypes
		 * .get(0).getId().intValue()); if (root != null && root.getOrgName() !=
		 * null) list.add(0, root);
		 */
		if (key != null) {
			cacheService.set(
					MemCacheConstant.ANALYSE_ORGLOGINSTATISTICS_NAMESPACE, key,
					ModulTypes.CACHETIME, list);
		}
		return list;
	}

	@Override
	public void addMonthOrgLoginStanalssJob(int year, int month) {
		Set<Long> set;
		if (month == 1) {
			set = workCalendarService.getWorkCalendarByYear(Long
					.valueOf(year - 1));
		} else if (month == 12) {
			set = workCalendarService.getWorkCalendarByYear(Long
					.valueOf(year + 1));

		} else {
			set = workCalendarService.getWorkCalendarByYear(Long.valueOf(year));
		}
		if (set == null || set.size() == 0) {
			if (month == 1)
				throw new BusinessValidationException("请初始化" + (year - 1)
						+ "年度的工作日历!");
			else if (month == 12)
				throw new BusinessValidationException("请初始化" + (year + 1)
						+ "年度的工作日历!");
			else
				throw new BusinessValidationException("请初始化" + (year)
						+ "年度的工作日历!");
		} else {
			OrgLoginStanalsBuilderNew builder = new OrgLoginStanalsBuilderNew(
					workCalendarService, propertyDictService,
					orgLoginStanalsNewDao, ticketService, tableService);
			try {
				builder.clearExistedStatData(year, month);
				builder.createOrgLoginStatData(year, month);
			} catch (Exception e) {
				throw new ServiceValidationException(e.toString(), e);
			}
		}

	}

	@Override
	public void addOrgLoginStanals(OrgLoginStanals orgLoginStanals) {
		orgLoginStanalsNewDao.addOrgLoginStanals(orgLoginStanals);
	}

	@Override
	public void addMonthOrgLoginStanals() {
		logger.info("执行登录统计job");
		try {
			// 生成登录日志
			addMonthOrgLoginStanalssJob(CalendarUtil.getLastMonthYearValue(),
					CalendarUtil.getLastMonth());

			logger.info("顺利完成");
		} catch (Exception ex) {
			logger.error("执行登录统计job出错" + ex);
		}
	}

	@Override
	public void deleteOrgLoginStanalsByOrgId(Long orgId) {
		orgLoginStanalsNewDao.deleteOrgLoginStanalsByOrgId(orgId);
	}

}
