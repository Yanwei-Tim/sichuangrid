package com.tianque.report.builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.IssueTypeStanal;
import com.tianque.domain.Organization;
import com.tianque.report.queryVo.OrgGroupedObject;
import com.tianque.service.IssueTypeDomainService;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;

public class OrgIssueTypeStatBuilder {

	private OrganizationDubboService orgService;

	private IssueTypeDomainService typeDomainService;

	private IssueTypeService typeService;

	private Map<Long, List<IssueTypeStanal>> resultMaps = new HashMap<Long, List<IssueTypeStanal>>();

	private Map<Long, Organization> cacheOrgs = new HashMap<Long, Organization>();

	private Map<Long, IssueType> cacheIssueTypes = new HashMap<Long, IssueType>();

	private Map<Long, IssueTypeDomain> cacheIssueTypeDomains = new HashMap<Long, IssueTypeDomain>();

	private Date periodStartDate;

	private Date periodEndDate;

	public OrgIssueTypeStatBuilder(OrganizationDubboService orgService,
			IssueTypeDomainService typeDomainService, IssueTypeService typeService, int year,
			int month) {
		this.orgService = orgService;
		this.typeDomainService = typeDomainService;
		this.typeService = typeService;
		periodStartDate = CalendarUtil.getMonthStart(year, month);
		periodEndDate = CalendarUtil.getMonthEnd(year, month);
		Calendar tmp = Calendar.getInstance();
		tmp.setTime(periodEndDate);
		tmp.add(Calendar.DAY_OF_MONTH, -1);
		periodEndDate = tmp.getTime();
	}

	public void addOrgGroupedObject(OrgGroupedObject info) {
		List<IssueTypeStanal> typeCountList = lookupTypeCountMapByOrgId(info.getOrgId());
		typeCountList.add(createIssueTypeStanalFromOrgGroupedObject(info));
	}

	public List<IssueTypeStanal> getResult() {
		cacheIssueTypeDomains.clear();
		cacheIssueTypes.clear();
		cacheOrgs.clear();
		List<IssueTypeStanal> result = new ArrayList<IssueTypeStanal>();
		for (List<IssueTypeStanal> subResult : resultMaps.values()) {
			result.addAll(subResult);
		}
		return result;
	}

	private List<IssueTypeStanal> lookupTypeCountMapByOrgId(Long orgId) {
		if (resultMaps.containsKey(orgId)) {
			return resultMaps.get(orgId);
		} else {
			List<IssueTypeStanal> result = new ArrayList<IssueTypeStanal>();
			resultMaps.put(orgId, result);
			return result;
		}
	}

	private Organization lookupOrgById(Long orgId) {
		if (cacheOrgs.containsKey(orgId)) {
			return cacheOrgs.get(orgId);
		} else {
			Organization result = orgService.getSimpleOrgById(orgId);
			cacheOrgs.put(orgId, result);
			return result;
		}
	}

	private IssueTypeDomain lookupIssueTypeDomainById(Long typedomainId) {
		if (cacheIssueTypeDomains.containsKey(typedomainId)) {
			return cacheIssueTypeDomains.get(typedomainId);
		} else {
			IssueTypeDomain result = typeDomainService.getIssueTypeDoaminById(typedomainId);
			cacheIssueTypeDomains.put(typedomainId, result);
			return result;
		}
	}

	private IssueType lookupIssueTypeById(Long typedomainId, Long typeId) {
		if (cacheIssueTypes.containsKey(typeId)) {
			return cacheIssueTypes.get(typeId);
		} else {
			IssueType result = typeService.getIssueTypeById(typedomainId, typeId);
			cacheIssueTypes.put(typeId, result);
			return result;
		}
	}

	private IssueTypeStanal createIssueTypeStanalFromOrgGroupedObject(OrgGroupedObject statinfo) {
		IssueTypeStanal result = new IssueTypeStanal();
		result.setCount(statinfo.getCountresult1().intValue());
		Organization org = lookupOrgById(statinfo.getOrgId());
		IssueTypeDomain typedomain = lookupIssueTypeDomainById(statinfo.getCountresult2());
		IssueType type = lookupIssueTypeById(typedomain.getId(), statinfo.getSubgroupid1());
		result.setDomainName(typedomain.getDomainName());
		result.setEndDate(periodEndDate);
		result.setIssueType(type);
		result.setIssueTypeDomain(typedomain);
		result.setOrg(org);
		result.setOrgInternalCode(org.getOrgInternalCode());
		result.setStartDate(periodStartDate);
		return result;
	}

}
