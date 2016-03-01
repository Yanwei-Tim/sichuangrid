package com.tianque.report.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.dao.IssueNewDao;
import com.tianque.domain.IssueInvestigationMediate;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.service.IssueTypeService;
import com.tianque.state.IssueDealState;
import com.tianque.state.IssueDealType;
import com.tianque.sysadmin.service.OrganizationDubboService;

public class IssueInvestigationMediateMonthBuilder {

	private OrganizationDubboService organizationDubboService;
	private IssueTypeService issueTypeService;

	private Map<Long, IssueNew> issueCache = new HashMap<Long, IssueNew>();
	private Map<Long, Organization> fullorgCache = new HashMap<Long, Organization>();
	private Map<Long, IssueType> issueTypeCache = new HashMap<Long, IssueType>();

	private List<IssueInvestigationMediate> exsitedItems = new ArrayList<IssueInvestigationMediate>();
	private List<IssueInvestigationMediate> exsitedFixItems = new ArrayList<IssueInvestigationMediate>();

	private int year;
	private int month;
	private Date nextMonthStart;
	private Date statEndTime;
	private Date now;
	private Date monthStart;
	private IssueNewDao issueDao;
	private IssueTypeDomain investigation;

	public IssueInvestigationMediateMonthBuilder(Organization rootOrg,
			int year, int month,
			OrganizationDubboService organizationDubboService,
			IssueNewDao issueDao, IssueTypeService issueTypeService) {
		this.year = year;
		this.month = month;
		this.organizationDubboService = organizationDubboService;
		this.issueDao = issueDao;
		this.issueTypeService = issueTypeService;
		nextMonthStart = CalendarUtil.getNextMonthStart(year, month);
		if (nextMonthStart.before(new Date())) {
			statEndTime = CalendarUtil.getMonthEnd(year, month);
		} else {
			statEndTime = CalendarUtil.today();
		}
		now = CalendarUtil.now("yyyy-MM-dd HH:mm:ss");
		monthStart = CalendarUtil.getMonthStart(year, month);
	}

	public void addIssueLog(IssueLogNew log) {
		if (isAddLog(log) && isAdministrateOrg(log.getDealOrg())) {
			processAddLog(log);
		} else if (isDealedLog(log) && isAdministrateOrg(log.getDealOrg())) {
			processIssueDealeddLog(log);
		}
	}

	private boolean isAdministrateOrg(Organization org) {
		Organization fullOrg = lookupFullOrganization(org.getId());
		return fullOrg.getOrgType().getInternalId() == OrganizationType.ADMINISTRATIVE_REGION;
	}

	// 新增
	private boolean isAddLog(IssueLogNew log) {
		return log.getDealType().equals(IssueDealType.Add);
	}

	private boolean isDealedLog(IssueLogNew log) {
		return (isStepCompletedLog(log) && !stepcompletedOverStatPeriod(log))
				|| (isIssueCompletedLog(log) && !completedOverStatPeriod(log));
	}

	// 本部门已经处理完成
	private boolean isStepCompletedLog(IssueLogNew log) {
		return log.getDealState().equals(IssueDealState.DONE)
				&& (log.getDealType().equals(IssueDealType.CONCEPT)
						|| log.getDealType().equals(IssueDealType.COMMENT) || log
						.getDealType().equals(IssueDealType.SEND_BACK));
	}

	// 办结
	private boolean isIssueCompletedLog(IssueLogNew log) {
		return log.getDealState().equals(IssueDealState.COMPLETE);
	}

	private boolean completedOverStatPeriod(IssueLogNew log) {
		return log.getDealTime().after(nextMonthStart)
				|| log.getDealTime().after(now)
				|| log.getDealTime().equals(nextMonthStart)
				|| log.getDealTime().equals(now);
	}

	private boolean stepcompletedOverStatPeriod(IssueLogNew log) {
		return (log.getLogCompleteTime().equals(nextMonthStart)
				|| log.getLogCompleteTime().after(nextMonthStart)
				|| log.getLogCompleteTime().equals(now) || log
				.getLogCompleteTime().after(now));
	}

	public void setInvestigation(IssueTypeDomain investigation) {
		this.investigation = investigation;
	}

	public List<IssueInvestigationMediate> getResult() {
		List<IssueInvestigationMediate> result = new ArrayList<IssueInvestigationMediate>();
		result.addAll(exsitedItems);
		result.addAll(exsitedFixItems);
		return result;
	}

	private void processIssueDealeddLog(IssueLogNew log) {
		boolean processedImport = false;
		IssueNew issue = loadIssue(log.getIssue().getId());
		IssueType type = issue.getIssueType();
		// for (IssueType type : issue.getIssueTypes()) {
		if (needGenerateItem(type)) {
			IssueInvestigationMediate item = lookupIssueInvestigationMediateByOrgAndIssueTypeAndSubset(
					log.getDealOrg(), type, IssueInvestigationMediate.DEAL_ITEM);
			increaseIssueCount(item, log.getDealOrg());
			if (!processedImport) {
				processImportantInputItem(log, issue,
						IssueInvestigationMediate.DEAL_ITEM);
				processedImport = true;
			}
		}
		// }
	}

	private void processAddLog(IssueLogNew log) {
		boolean hasAddToFix = false;
		IssueNew issue = loadIssue(log.getIssue().getId());
		IssueType type = issue.getIssueType();
		// for (IssueType type : issue.getIssueTypes()) {
		if (needGenerateItem(type)) {
			IssueInvestigationMediate item = lookupIssueInvestigationMediateByOrgAndIssueTypeAndSubset(
					log.getDealOrg(), type,
					IssueInvestigationMediate.INPUT_ITEM);
			increaseIssueCount(item, log.getDealOrg());
			if (!hasAddToFix) {
				processFixItem(log, issue.getRelatePeopleCount());
				processImportantInputItem(log, issue,
						IssueInvestigationMediate.INPUT_ITEM);
				hasAddToFix = true;
			}
		}
		// }
	}

	private void processImportantInputItem(IssueLogNew log, IssueNew issue,
			String subsetname) {
		if (issue.getImportant()) {
			IssueInvestigationMediate item = lookupIssueInvestigationMediateByOrgAndTypeNameAndSubset(
					log.getDealOrg(), IssueInvestigationMediate.IMPORTANT_ITEM,
					subsetname);
			increaseIssueCount(item, log.getDealOrg());
		}
	}

	private IssueInvestigationMediate lookupIssueInvestigationMediateByOrgAndTypeNameAndSubset(
			Organization org, String typename, String subset) {
		for (IssueInvestigationMediate item : exsitedItems) {
			if (item.getOrg().getId().equals(org.getId())
					&& item.getTypeSubsetName().equals(subset)
					&& item.getTypeName().equals(typename)) {
				return item;
			}
		}
		IssueInvestigationMediate result = createIssueInvestigationMediate(org);
		result.setTypeName(typename);
		result.setTypeSubsetName(subset);
		exsitedItems.add(result);
		return result;
	}

	private void processFixItem(IssueLogNew log, Integer relatePeopleCount) {
		String keyString = getKeyStringByPeopleCount(relatePeopleCount);
		IssueInvestigationMediate item = lookupFixIssueInvestigationMediateByOrgAndFixKey(
				log.getDealOrg(), keyString);
		increaseIssueCount(item, log.getDealOrg());
	}

	private IssueInvestigationMediate lookupFixIssueInvestigationMediateByOrgAndFixKey(
			Organization org, String keyString) {
		for (IssueInvestigationMediate item : exsitedFixItems) {
			if (item.getOrg().getId().equals(org.getId())
					&& item.getTypeName().equals(keyString)) {
				return item;
			}
		}
		IssueInvestigationMediate result = createIssueInvestigationMediate(org);
		result.setTypeName(keyString);
		result.setTypeSubsetName(IssueInvestigationMediate.FIX_SUBSET);
		exsitedFixItems.add(result);
		return result;
	}

	private String getKeyStringByPeopleCount(Integer relatePeopleCount) {
		if (relatePeopleCount < 50) {
			return IssueInvestigationMediate.BELOW_FIFTY_PERSON;
		} else if (relatePeopleCount < 100) {
			return IssueInvestigationMediate.FIFTY_TO_ONEHUNDRED_PERSON;
		} else if (relatePeopleCount < 500) {
			return IssueInvestigationMediate.ONEHUNDRED_TO_FIVEHUNDRED_PERSON;
		} else {
			return IssueInvestigationMediate.OVER_FIVEHUNDRED_PERSON;
		}
	}

	private void increaseIssueCount(IssueInvestigationMediate item,
			Organization org) {
		Organization fullOrg = lookupFullOrganization(org.getId());
		if (isCity(fullOrg)) {
			item.setCityCount(item.getCityCount() + 1);
		} else if (isDistrict(fullOrg)) {
			item.setDistrictCount(item.getDistrictCount() + 1);
		} else if (isTown(fullOrg)) {
			item.setTownCount(item.getTownCount() + 1);
		} else if (isBaseGrid(fullOrg)) {
			item.setVillageCount(item.getVillageCount() + 1);
		}
	}

	private boolean isBaseGrid(Organization fullOrg) {
		int internalCode = fullOrg.getOrgLevel().getInternalId();
		return internalCode == OrganizationLevel.VILLAGE
				|| internalCode == OrganizationLevel.GRID;
	}

	private boolean isTown(Organization fullOrg) {
		return fullOrg.getOrgLevel().getInternalId() == OrganizationLevel.TOWN;
	}

	private boolean isDistrict(Organization fullOrg) {
		return fullOrg.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT;
	}

	private boolean isCity(Organization fullOrg) {
		return fullOrg.getOrgLevel().getInternalId() == OrganizationLevel.CITY;
	}

	private boolean needGenerateItem(IssueType type) {
		return investigation.getId().equals(type.getIssueTypeDomain().getId());
	}

	private IssueInvestigationMediate lookupIssueInvestigationMediateByOrgAndIssueTypeAndSubset(
			Organization org, IssueType type, String subset) {
		IssueType fulltype = lookupIssueType(type.getId());
		IssueInvestigationMediate result = lookupIssueInvestigationMediateByOrgAndTypeNameAndSubset(
				org, fulltype.getIssueTypeName(), subset);
		result.setIssueType(type);
		return result;
	}

	private IssueInvestigationMediate createIssueInvestigationMediate(
			Organization org) {
		IssueInvestigationMediate result = new IssueInvestigationMediate();
		result.setCreateDate(new Date());
		result.setCreateUser(ThreadVariable.getUser().getName());
		result.setEndDate(statEndTime);
		result.setMonth(month);
		result.setStartDate(monthStart);
		result.setSearchEndDate(statEndTime);
		result.setTypes("月报");
		result.setYear(year);
		result.setCityCount(0L);
		result.setDistrictCount(0L);
		result.setTownCount(0L);
		result.setVillageCount(0L);
		result.setOrg(lookupFullOrganization(org.getId()));
		return result;
	}

	private IssueType lookupIssueType(Long id) {
		if (!issueTypeCache.containsKey(id)) {
			IssueType result = issueTypeService.getIssueTypeById(
					investigation.getId(), id);
			issueTypeCache.put(id, result);
		}
		return issueTypeCache.get(id);
	}

	private Organization lookupFullOrganization(Long id) {
		if (!fullorgCache.containsKey(id)) {
			Organization result = organizationDubboService.getFullOrgById(id);
			fullorgCache.put(id, result);
		}
		return fullorgCache.get(id);
	}

	private IssueNew loadIssue(Long id) {
		if (!issueCache.containsKey(id)) {
			IssueNew result = issueDao.getFullIssueById(id);
			issueCache.put(id, result);
		}
		return issueCache.get(id);
	}

}
