package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.dao.IssueTypeStanalDao;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.IssueTypeStanal;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.report.builder.OrgIssueTypeStatBuilder;
import com.tianque.report.queryVo.OrgGroupedObject;
import com.tianque.service.IssueTypeDomainService;
import com.tianque.service.IssueTypeService;
import com.tianque.service.IssueTypeStanalService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("issueTypeStanalService")
public class IssueTypeStanalServiceImpl extends AbstractBaseService implements
		IssueTypeStanalService {
	@Autowired
	private IssueTypeStanalDao issueTypeStanalDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private IssueTypeDomainService typeDomainService;
	@Autowired
	private IssueTypeService typeService;

	@Override
	public List<IssueTypeStanal> getPeriodIssueTypeStatInfoListByOrgGroupByTypeDomain(
			Long orgId, Date startDate, Date endDate, Long level) {
		List<OrgGroupedObject> list = new ArrayList<OrgGroupedObject>();
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (level == 1) {
			list = issueTypeStanalDao
					.getPeriodIssueTypeStatInfoListByOrgCodeGroupByTypeDomain(
							org.getOrgInternalCode(), startDate, endDate);
		} else {
			list = issueTypeStanalDao
					.getPeriodIssueTypeStatInfoListByOrgIdGroupByTypeDomain(
							startDate, endDate, org.getId());
		}

		List<IssueTypeStanal> result = new ArrayList<IssueTypeStanal>();
		for (OrgGroupedObject info : list) {
			IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
			issueTypeStanal.setCount(info.getCountresult1().intValue());
			issueTypeStanal.setOrg(org);
			issueTypeStanal.setOrgInternalCode(org.getOrgInternalCode());
			IssueTypeDomain domain = typeDomainService
					.getIssueTypeDoaminById(info.getSubgroupid1());
			issueTypeStanal.setIssueTypeDomain(domain);
			issueTypeStanal.setDomainName(domain.getDomainName());
			issueTypeStanal.setId(domain.getId());
			result.add(issueTypeStanal);
		}
		List<IssueTypeDomain> allDomains = issueTypeStanalDao
				.findIssueTypeDomains();
		for (IssueTypeDomain domain : allDomains) {
			boolean dataexsited = false;
			for (IssueTypeStanal exsited : result) {
				if (exsited.getIssueTypeDomain().getId().equals(domain.getId())) {
					dataexsited = true;
					break;
				}
			}
			if (!dataexsited) {
				IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
				issueTypeStanal.setCount(0);
				issueTypeStanal.setOrg(org);
				issueTypeStanal.setOrgInternalCode(org.getOrgInternalCode());
				issueTypeStanal.setIssueTypeDomain(domain);
				issueTypeStanal.setDomainName(domain.getDomainName());
				issueTypeStanal.setId(domain.getId());
				result.add(issueTypeStanal);
			}
		}
		return result;
	}

	@Override
	public List<List<IssueTypeStanal>> getPeriodIssueTypeStatInfoByOrgGroupByTypeDomain(
			Long orgId, Date startDate, Date endDate, Long level) {
		Calendar can = Calendar.getInstance();
		int minYear = 0;
		int minMonth = 0;
		int maxYear = 0;
		int maxMonth = 0;
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(startDate);
		minYear = startCalendar.get(Calendar.YEAR);
		minMonth = (startCalendar.get(Calendar.MONTH)) + 1;
		can.setTime(endDate);
		maxYear = can.get(Calendar.YEAR);
		maxMonth = (can.get(Calendar.MONTH)) + 1;

		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		List<List<IssueTypeStanal>> result = new ArrayList<List<IssueTypeStanal>>();
		List<OrgGroupedObject> list = new ArrayList<OrgGroupedObject>();

		List<IssueTypeDomain> allDomains = issueTypeStanalDao
				.findIssueTypeDomains();

		if (level == 1) {
			for (int i = minYear, k = 0; i <= maxYear; i++) {
				if (k == 0 && maxYear - minYear > 0) {
					for (int j = minMonth; j <= 12; j++) {
						List<IssueTypeStanal> newList = new ArrayList<IssueTypeStanal>();
						list = issueTypeStanalDao
								.getPeriodIssueTypeStatInfoByOrgCodeGroupByTypeDomain(
										org.getOrgInternalCode(),
										Long.valueOf(i), Long.valueOf(j));
						for (OrgGroupedObject info : list) {
							IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
							issueTypeStanal.setCount(info.getCountresult1()
									.intValue());
							issueTypeStanal.setOrg(org);
							issueTypeStanal.setOrgInternalCode(org
									.getOrgInternalCode());
							IssueTypeDomain domain = typeDomainService
									.getIssueTypeDoaminById(info
											.getSubgroupid1());
							issueTypeStanal.setIssueTypeDomain(domain);
							issueTypeStanal.setDomainName(domain
									.getDomainName());
							issueTypeStanal.setId(domain.getId());
							issueTypeStanal.setYear(i);
							issueTypeStanal.setMonth(j);
							newList.add(issueTypeStanal);
						}

						for (IssueTypeDomain domain : allDomains) {
							boolean dataexsited = false;
							for (IssueTypeStanal exsited : newList) {
								if (exsited.getIssueTypeDomain().getId()
										.equals(domain.getId())) {
									dataexsited = true;
									break;
								}
							}
							if (!dataexsited) {
								IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
								issueTypeStanal.setCount(0);
								issueTypeStanal.setOrg(org);
								issueTypeStanal.setOrgInternalCode(org
										.getOrgInternalCode());
								issueTypeStanal.setIssueTypeDomain(domain);
								issueTypeStanal.setDomainName(domain
										.getDomainName());
								issueTypeStanal.setId(domain.getId());
								issueTypeStanal.setYear(i);
								issueTypeStanal.setMonth(j);
								newList.add(issueTypeStanal);
							}
						}
						result.add(newList);
					}
				} else if (k == 0 && maxYear - minYear == 0) {
					for (int j = minMonth; j <= maxMonth; j++) {
						List<IssueTypeStanal> newList = new ArrayList<IssueTypeStanal>();
						list = issueTypeStanalDao
								.getPeriodIssueTypeStatInfoByOrgCodeGroupByTypeDomain(
										org.getOrgInternalCode(),
										Long.valueOf(i), Long.valueOf(j));
						for (OrgGroupedObject info : list) {
							IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
							issueTypeStanal.setCount(info.getCountresult1()
									.intValue());
							issueTypeStanal.setOrg(org);
							issueTypeStanal.setOrgInternalCode(org
									.getOrgInternalCode());
							IssueTypeDomain domain = typeDomainService
									.getIssueTypeDoaminById(info
											.getSubgroupid1());
							issueTypeStanal.setIssueTypeDomain(domain);
							issueTypeStanal.setDomainName(domain
									.getDomainName());
							issueTypeStanal.setId(domain.getId());
							issueTypeStanal.setYear(i);
							issueTypeStanal.setMonth(j);
							newList.add(issueTypeStanal);
						}

						for (IssueTypeDomain domain : allDomains) {
							boolean dataexsited = false;
							for (IssueTypeStanal exsited : newList) {
								if (exsited.getIssueTypeDomain().getId()
										.equals(domain.getId())) {
									dataexsited = true;
									break;
								}
							}
							if (!dataexsited) {
								IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
								issueTypeStanal.setCount(0);
								issueTypeStanal.setOrg(org);
								issueTypeStanal.setOrgInternalCode(org
										.getOrgInternalCode());
								issueTypeStanal.setIssueTypeDomain(domain);
								issueTypeStanal.setDomainName(domain
										.getDomainName());
								issueTypeStanal.setId(domain.getId());
								issueTypeStanal.setYear(i);
								issueTypeStanal.setMonth(j);
								newList.add(issueTypeStanal);
							}
						}
						result.add(newList);
					}
				} else if (maxYear - minYear == k) {
					for (int j = 1; j <= maxMonth; j++) {
						List<IssueTypeStanal> newList = new ArrayList<IssueTypeStanal>();
						list = issueTypeStanalDao
								.getPeriodIssueTypeStatInfoByOrgCodeGroupByTypeDomain(
										org.getOrgInternalCode(),
										Long.valueOf(i), Long.valueOf(j));
						for (OrgGroupedObject info : list) {
							IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
							issueTypeStanal.setCount(info.getCountresult1()
									.intValue());
							issueTypeStanal.setOrg(org);
							issueTypeStanal.setOrgInternalCode(org
									.getOrgInternalCode());
							IssueTypeDomain domain = typeDomainService
									.getIssueTypeDoaminById(info
											.getSubgroupid1());
							issueTypeStanal.setIssueTypeDomain(domain);
							issueTypeStanal.setDomainName(domain
									.getDomainName());
							issueTypeStanal.setId(domain.getId());
							issueTypeStanal.setYear(i);
							issueTypeStanal.setMonth(j);
							newList.add(issueTypeStanal);
						}

						for (IssueTypeDomain domain : allDomains) {
							boolean dataexsited = false;
							for (IssueTypeStanal exsited : newList) {
								if (exsited.getIssueTypeDomain().getId()
										.equals(domain.getId())) {
									dataexsited = true;
									break;
								}
							}
							if (!dataexsited) {
								IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
								issueTypeStanal.setCount(0);
								issueTypeStanal.setOrg(org);
								issueTypeStanal.setOrgInternalCode(org
										.getOrgInternalCode());
								issueTypeStanal.setIssueTypeDomain(domain);
								issueTypeStanal.setDomainName(domain
										.getDomainName());
								issueTypeStanal.setId(domain.getId());
								issueTypeStanal.setYear(i);
								issueTypeStanal.setMonth(j);
								newList.add(issueTypeStanal);
							}
						}
						result.add(newList);
					}
				} else {
					for (int j = 1; j <= 12; j++) {
						List<IssueTypeStanal> newList = new ArrayList<IssueTypeStanal>();
						list = issueTypeStanalDao
								.getPeriodIssueTypeStatInfoByOrgCodeGroupByTypeDomain(
										org.getOrgInternalCode(),
										Long.valueOf(i), Long.valueOf(j));
						for (OrgGroupedObject info : list) {
							IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
							issueTypeStanal.setCount(info.getCountresult1()
									.intValue());
							issueTypeStanal.setOrg(org);
							issueTypeStanal.setOrgInternalCode(org
									.getOrgInternalCode());
							IssueTypeDomain domain = typeDomainService
									.getIssueTypeDoaminById(info
											.getSubgroupid1());
							issueTypeStanal.setIssueTypeDomain(domain);
							issueTypeStanal.setDomainName(domain
									.getDomainName());
							issueTypeStanal.setId(domain.getId());
							issueTypeStanal.setYear(i);
							issueTypeStanal.setMonth(j);
							newList.add(issueTypeStanal);
						}

						for (IssueTypeDomain domain : allDomains) {
							boolean dataexsited = false;
							for (IssueTypeStanal exsited : newList) {
								if (exsited.getIssueTypeDomain().getId()
										.equals(domain.getId())) {
									dataexsited = true;
									break;
								}
							}
							if (!dataexsited) {
								IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
								issueTypeStanal.setCount(0);
								issueTypeStanal.setOrg(org);
								issueTypeStanal.setOrgInternalCode(org
										.getOrgInternalCode());
								issueTypeStanal.setIssueTypeDomain(domain);
								issueTypeStanal.setDomainName(domain
										.getDomainName());
								issueTypeStanal.setId(domain.getId());
								issueTypeStanal.setYear(i);
								issueTypeStanal.setMonth(j);
								newList.add(issueTypeStanal);
							}
						}
						result.add(newList);
					}
				}
				k++;
			}
		} else {
			for (int i = minYear, k = 0; i <= maxYear; i++) {
				if (k == 0 && maxYear - minYear > 0) {
					for (int j = minMonth; j <= 12; j++) {
						List<IssueTypeStanal> newList = new ArrayList<IssueTypeStanal>();
						list = issueTypeStanalDao
								.getPeriodIssueTypeStatInfoByOrgIdGroupByTypeDomain(
										Long.valueOf(i), Long.valueOf(j),
										org.getId());
						for (OrgGroupedObject info : list) {
							IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
							issueTypeStanal.setCount(info.getCountresult1()
									.intValue());
							issueTypeStanal.setOrg(org);
							issueTypeStanal.setOrgInternalCode(org
									.getOrgInternalCode());
							IssueTypeDomain domain = typeDomainService
									.getIssueTypeDoaminById(info
											.getSubgroupid1());
							issueTypeStanal.setIssueTypeDomain(domain);
							issueTypeStanal.setDomainName(domain
									.getDomainName());
							issueTypeStanal.setId(domain.getId());
							issueTypeStanal.setYear(i);
							issueTypeStanal.setMonth(j);
							newList.add(issueTypeStanal);
						}

						for (IssueTypeDomain domain : allDomains) {
							boolean dataexsited = false;
							for (IssueTypeStanal exsited : newList) {
								if (exsited.getIssueTypeDomain().getId()
										.equals(domain.getId())) {
									dataexsited = true;
									break;
								}
							}
							if (!dataexsited) {
								IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
								issueTypeStanal.setCount(0);
								issueTypeStanal.setOrg(org);
								issueTypeStanal.setOrgInternalCode(org
										.getOrgInternalCode());
								issueTypeStanal.setIssueTypeDomain(domain);
								issueTypeStanal.setDomainName(domain
										.getDomainName());
								issueTypeStanal.setId(domain.getId());
								issueTypeStanal.setYear(i);
								issueTypeStanal.setMonth(j);
								newList.add(issueTypeStanal);
							}
						}
						result.add(newList);
					}
				} else if (k == 0 && maxYear - minYear == 0) {
					for (int j = minMonth; j <= maxMonth; j++) {
						List<IssueTypeStanal> newList = new ArrayList<IssueTypeStanal>();
						list = issueTypeStanalDao
								.getPeriodIssueTypeStatInfoByOrgIdGroupByTypeDomain(
										Long.valueOf(i), Long.valueOf(j),
										org.getId());
						for (OrgGroupedObject info : list) {
							IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
							issueTypeStanal.setCount(info.getCountresult1()
									.intValue());
							issueTypeStanal.setOrg(org);
							issueTypeStanal.setOrgInternalCode(org
									.getOrgInternalCode());
							IssueTypeDomain domain = typeDomainService
									.getIssueTypeDoaminById(info
											.getSubgroupid1());
							issueTypeStanal.setIssueTypeDomain(domain);
							issueTypeStanal.setDomainName(domain
									.getDomainName());
							issueTypeStanal.setId(domain.getId());
							issueTypeStanal.setYear(i);
							issueTypeStanal.setMonth(j);
							newList.add(issueTypeStanal);
						}

						for (IssueTypeDomain domain : allDomains) {
							boolean dataexsited = false;
							for (IssueTypeStanal exsited : newList) {
								if (exsited.getIssueTypeDomain().getId()
										.equals(domain.getId())) {
									dataexsited = true;
									break;
								}
							}
							if (!dataexsited) {
								IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
								issueTypeStanal.setCount(0);
								issueTypeStanal.setOrg(org);
								issueTypeStanal.setOrgInternalCode(org
										.getOrgInternalCode());
								issueTypeStanal.setIssueTypeDomain(domain);
								issueTypeStanal.setDomainName(domain
										.getDomainName());
								issueTypeStanal.setId(domain.getId());
								issueTypeStanal.setYear(i);
								issueTypeStanal.setMonth(j);
								newList.add(issueTypeStanal);
							}
						}
						result.add(newList);
					}
				} else if (maxYear - minYear == k) {
					for (int j = 1; j <= maxMonth; j++) {
						List<IssueTypeStanal> newList = new ArrayList<IssueTypeStanal>();
						list = issueTypeStanalDao
								.getPeriodIssueTypeStatInfoByOrgIdGroupByTypeDomain(
										Long.valueOf(i), Long.valueOf(j),
										org.getId());
						for (OrgGroupedObject info : list) {
							IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
							issueTypeStanal.setCount(info.getCountresult1()
									.intValue());
							issueTypeStanal.setOrg(org);
							issueTypeStanal.setOrgInternalCode(org
									.getOrgInternalCode());
							IssueTypeDomain domain = typeDomainService
									.getIssueTypeDoaminById(info
											.getSubgroupid1());
							issueTypeStanal.setIssueTypeDomain(domain);
							issueTypeStanal.setDomainName(domain
									.getDomainName());
							issueTypeStanal.setId(domain.getId());
							issueTypeStanal.setYear(i);
							issueTypeStanal.setMonth(j);
							newList.add(issueTypeStanal);
						}

						for (IssueTypeDomain domain : allDomains) {
							boolean dataexsited = false;
							for (IssueTypeStanal exsited : newList) {
								if (exsited.getIssueTypeDomain().getId()
										.equals(domain.getId())) {
									dataexsited = true;
									break;
								}
							}
							if (!dataexsited) {
								IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
								issueTypeStanal.setCount(0);
								issueTypeStanal.setOrg(org);
								issueTypeStanal.setOrgInternalCode(org
										.getOrgInternalCode());
								issueTypeStanal.setIssueTypeDomain(domain);
								issueTypeStanal.setDomainName(domain
										.getDomainName());
								issueTypeStanal.setId(domain.getId());
								issueTypeStanal.setYear(i);
								issueTypeStanal.setMonth(j);
								newList.add(issueTypeStanal);
							}
						}
						result.add(newList);
					}
				} else {
					for (int j = 1; j <= 12; j++) {
						List<IssueTypeStanal> newList = new ArrayList<IssueTypeStanal>();
						list = issueTypeStanalDao
								.getPeriodIssueTypeStatInfoByOrgIdGroupByTypeDomain(
										Long.valueOf(i), Long.valueOf(j),
										org.getId());
						for (OrgGroupedObject info : list) {
							IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
							issueTypeStanal.setCount(info.getCountresult1()
									.intValue());
							issueTypeStanal.setOrg(org);
							issueTypeStanal.setOrgInternalCode(org
									.getOrgInternalCode());
							IssueTypeDomain domain = typeDomainService
									.getIssueTypeDoaminById(info
											.getSubgroupid1());
							issueTypeStanal.setIssueTypeDomain(domain);
							issueTypeStanal.setDomainName(domain
									.getDomainName());
							issueTypeStanal.setId(domain.getId());
							issueTypeStanal.setYear(i);
							issueTypeStanal.setMonth(j);
							newList.add(issueTypeStanal);
						}
						for (IssueTypeDomain domain : allDomains) {
							boolean dataexsited = false;
							for (IssueTypeStanal exsited : newList) {
								if (exsited.getIssueTypeDomain().getId()
										.equals(domain.getId())) {
									dataexsited = true;
									break;
								}
							}
							if (!dataexsited) {
								IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
								issueTypeStanal.setCount(0);
								issueTypeStanal.setOrg(org);
								issueTypeStanal.setOrgInternalCode(org
										.getOrgInternalCode());
								issueTypeStanal.setIssueTypeDomain(domain);
								issueTypeStanal.setDomainName(domain
										.getDomainName());
								issueTypeStanal.setId(domain.getId());
								issueTypeStanal.setYear(i);
								issueTypeStanal.setMonth(j);
								newList.add(issueTypeStanal);
							}
						}
						result.add(newList);
					}
				}
				k++;
			}
		}
		return result;
	}

	@Override
	public List<IssueTypeStanal> getPeriodIssueTypeStatDetailInfoByOrgAndTypeDomain(
			Long orgId, Long domainid, Date startDate, Date endDate, Long level) {
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		IssueTypeDomain domain = typeDomainService
				.getIssueTypeDoaminById(domainid);
		List<OrgGroupedObject> list = new ArrayList<OrgGroupedObject>();
		if (level == 1) {
			list = issueTypeStanalDao
					.getPeriodIssueTypeStatDetailInfoByOrgAndTypeDomain(
							org.getOrgInternalCode(), domainid, startDate,
							endDate);
		} else {
			list = issueTypeStanalDao
					.getPeriodIssueTypeStatDetailInfoByOrgIdAndTypeDomain(
							org.getId(), domainid, startDate, endDate);
		}

		List<IssueTypeStanal> result = new ArrayList<IssueTypeStanal>();
		for (OrgGroupedObject info : list) {
			IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
			issueTypeStanal.setCount(info.getCountresult1().intValue());
			issueTypeStanal.setOrg(org);
			issueTypeStanal.setOrgInternalCode(org.getOrgInternalCode());
			issueTypeStanal.setIssueTypeDomain(domain);
			issueTypeStanal.setDomainName(domain.getDomainName());
			issueTypeStanal.setId(domain.getId());
			IssueType type = typeService.getIssueTypeById(domainid,
					info.getSubgroupid1());
			issueTypeStanal.setIssueType(type);
			issueTypeStanal.setTypeName(type.getIssueTypeName());
			result.add(issueTypeStanal);
		}
		List<IssueType> allTypes = typeService
				.findIssueTypesByDomainId(domainid);
		for (IssueType type : allTypes) {
			boolean dataexsited = false;
			for (IssueTypeStanal exsited : result) {
				if (exsited.getIssueType().getId().equals(type.getId())) {
					dataexsited = true;
					break;
				}
			}
			if (!dataexsited) {
				IssueTypeStanal issueTypeStanal = new IssueTypeStanal();
				issueTypeStanal.setCount(0);
				issueTypeStanal.setOrg(org);
				issueTypeStanal.setOrgInternalCode(org.getOrgInternalCode());
				issueTypeStanal.setIssueTypeDomain(domain);
				issueTypeStanal.setDomainName(domain.getDomainName());
				issueTypeStanal.setId(domain.getId());
				issueTypeStanal.setIssueType(type);
				issueTypeStanal.setTypeName(type.getIssueTypeName());
				result.add(issueTypeStanal);
			}
		}
		return result;
	}

	@Override
	public void reCreateMonthIssueTypeStanalsByOrgId(Long orgId, int year,
			int month) {
		Organization rootOrg = getNeedReCreateStatOrganization(orgId);
		clearExistedStatData(rootOrg, year, month);
		createIssueStatData(rootOrg, year, month);
	}

	private void createIssueStatData(Organization rootOrg, int year, int month) {
		Date startDate = CalendarUtil.getMonthStart(year, month);
		Date endDate = CalendarUtil.getNextMonthStart(year, month);
		OrgIssueTypeStatBuilder builder = new OrgIssueTypeStatBuilder(
				organizationDubboService, typeDomainService, typeService, year,
				month);
		List<OrgGroupedObject> statCounts = issueTypeStanalDao
				.statIssueCountByTimeGroupOrgAndType(
						rootOrg.getOrgInternalCode(), startDate, endDate);
		for (OrgGroupedObject info : statCounts) {
			builder.addOrgGroupedObject(info);
		}
		List<IssueTypeStanal> statResults = builder.getResult();
		for (IssueTypeStanal statResult : statResults) {
			statResult.setYear(year);
			statResult.setMonth(month);
			issueTypeStanalDao.addIssueTypeStanal(statResult);
		}
	}

	private void clearExistedStatData(Organization rootOrg, int year, int month) {
		Date startDate = CalendarUtil.getMonthStart(year, month);
		Date endDate = CalendarUtil.getNextMonthStart(year, month);
		issueTypeStanalDao.deleteIssueTypeStanalByStartEndDateOrgCode(
				startDate, endDate, rootOrg.getOrgInternalCode());
	}

	private Organization getNeedReCreateStatOrganization(Long orgId) {
		if (orgId == null) {
			return organizationDubboService.getSimpleOrgById(orgId);
		}
		return organizationDubboService.findOrganizationsByParentId(null).get(0);
	}

	// job生成
	@Override
	public void addMonthIssueTypeStanals(int year, int month) {
		if (issueTypeStanalDao.getIssueStartTime() == null) {
			throw new BusinessValidationException("开始时间不正确");
		}
		Organization orgrganization = organizationDubboService
				.findOrganizationsByParentId(null).get(0);
		Date starTime = CalendarUtil.getMonthStart(year, month);
		Date endTime = CalendarUtil.getNextMonthStart(year, month);
		issueTypeStanalDao.deleteIssueTypeStanalByStartEndDateOrgCode(starTime,
				endTime, orgrganization.getOrgInternalCode());
		List<Organization> orgList = issueTypeStanalDao.findOrganizations();
		for (Organization org : orgList) {
			stanalIssueTypeDomains(org, starTime, endTime, year, month);
			stanalIssueTypes(org, starTime, endTime, year, month);
		}
	}

	private void stanalIssueTypeDomains(Organization org, Date startDate,
			Date endDate, int year, int month) {
		List<IssueTypeStanal> list = issueTypeStanalDao.stanalIssueTypeDomains(
				org.getId(), startDate, endDate);
		for (IssueTypeStanal its : list) {
			its.setOrg(org);
			its.setOrgInternalCode(org.getOrgInternalCode());
			its.setStartDate(startDate);
			its.setEndDate(CalendarUtil.getMonthEnd(year, month));
			its.setYear(year);
			its.setMonth(month);
			issueTypeStanalDao.addIssueTypeStanal(its);
		}
	}

	private void stanalIssueTypes(Organization org, Date startDate,
			Date endDate, int year, int month) {
		List<IssueTypeDomain> domains = issueTypeStanalDao
				.findIssueTypeDomains();
		for (IssueTypeDomain domain : domains) {
			List<IssueTypeStanal> list = issueTypeStanalDao.stanalIssueTypes(
					org.getId(), domain.getId(), startDate, endDate);
			for (IssueTypeStanal its : list) {
				its.setOrg(org);
				its.setOrgInternalCode(org.getOrgInternalCode());
				its.setIssueTypeDomain(domain);
				its.setDomainName(domain.getDomainName());
				its.setStartDate(startDate);
				its.setEndDate(CalendarUtil.getMonthEnd(year, month));
				its.setYear(year);
				its.setMonth(month);
				issueTypeStanalDao.addIssueTypeStanal(its);
			}
		}
	}

	@Override
	public List<IssueTypeDomain> findIssueTypeDomains() {
		return issueTypeStanalDao.findIssueTypeDomains();
	}

	@Override
	public void deleteAllStatIssueByOrgId(long orgId) {
		issueTypeStanalDao.deleteAllStatIssueByOrgId(orgId);
	}

}