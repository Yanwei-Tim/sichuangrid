package com.tianque.state;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.core.util.ThreadVariable;
import com.tianque.dao.IssueLogDaoNew;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

@Scope("prototype")
@Component("targeOrgManager")
public class TargeOrgManager {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PermissionService permissionService;
	// @Autowired
	// private OrganizationDao organizationDao;
	// TODO 转移到service
	@Autowired
	private IssueLogDaoNew issueLogDao;

	private Organization loginedOrg;

	public Organization getSendbackTargeOrg(Long issueId, Long logid) {
		if (logid == null || logid.intValue() == 0L) {
			throw new BusinessValidationException("参数错误");
		}
		// IssueLogNew forIssueLog =
		// issueLogDao.getUnDoIssueLogByIssueIdAndTargeOrgId(issueId,
		// getCurrentLoginedOrg().getId());
		IssueLogNew forIssueLog = issueLogDao.getIssueLogById(logid);
		if (forIssueLog.getBackIssueLog() == null) {
			return null;
		}

		return organizationDubboService.getSimpleOrgById(issueLogDao
				.getIssueLogById(forIssueLog.getBackIssueLog().getId())
				.getDealOrg().getId());
	}

	private Organization getCurrentLoginedOrg() {
		if (loginedOrg == null) {
			loginedOrg = organizationDubboService
					.getFullOrgById(permissionService
							.getFullUserById(
									ThreadVariable.getSession().getUserId())
							.getOrganization().getId());
		}
		return loginedOrg;
	}

	private Organization getAdminParentOrg() {
		return getCurrentLoginedOrg().getParentOrg();
	}

	public List<Organization> getDispatchTargeOrgs(Long issueId) {
		if (issueId == null || issueId.intValue() == 0L) {
			throw new BusinessValidationException("参数错误");
		}
		return organizationDubboService
				.findOrganizationsByParentId(getCurrentLoginedOrg().getId());
	}

	public List<Organization> getTargeOrg(Long issueDealType, String tag) {
		if (issueDealType == null) {
			throw new BusinessValidationException("参数不对");
		}
		List<Organization> orgList = new ArrayList<Organization>();
		if (issueDealType.intValue() == IssueDealType.ASSIGN_TO_ADMIN
				.intValue()) {
			orgList = this.getAssignTargeAdminOrgs(tag);
		} else if (issueDealType.intValue() == IssueDealType.ASSIGN_TO_FUNCTION
				.intValue()) {
			orgList = this.getAssignTargeFunOrgs(tag);
		} else if (issueDealType.intValue() == IssueDealType.TELL_ASSIGN_TO_FUNCTION) {
			orgList = this.getAssignTargeFunOrgs(tag);
		} else if (issueDealType.intValue() == IssueDealType.SUBMIT_FORWARD_ADMIN_TO_FUNCTION) {
			orgList = this.getSubmitForwardTargeFunOrgs(tag);
		} else if (issueDealType.intValue() == IssueDealType.TELL_SUBMIT_FORWARD_ADMIN_TO_FUNCTION) {
			orgList = this.getSubmitForwardTargeFunOrgs(tag);
			orgList.add(getAdminParentOrg());
		} else if (issueDealType.intValue() == IssueDealType.TELL_SUBMIT_FORWARD_ADMIN_TO_ADMIN) {
			orgList = this.getSubmitForwardTargeFunOrgs(tag);
		}
		return orgList;
	}

	private List<Organization> getSubmitForwardTargeFunOrgs(String tag) {
		if (getCurrentLoginedOrg().getOrgType().getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
			return organizationDubboService.findFunOrgsByParentIdAndName(
					getCurrentLoginedOrg().getParentOrg().getParentOrg()
							.getId(), tag);
		} else {
			return organizationDubboService.findFunOrgsByParentIdAndName(
					getAdminParentOrg().getId(), tag);
		}
	}

	private List<Organization> getAssignTargeFunOrgs(String tag) {
		if (getCurrentLoginedOrg().getOrgType().getInternalId() == OrganizationType.ADMINISTRATIVE_REGION) {
			return organizationDubboService.findFunOrgsByParentIdAndName(
					getCurrentLoginedOrg().getId(), tag);
		} else {
			List<Organization> list = organizationDubboService
					.findAdminOrgsByParentIdAndName(
							getParentAdminOrganization().getId(), tag);
			List<Organization> newlist = new ArrayList<Organization>();
			for (Organization org : list) {
				for (Organization orgs : organizationDubboService
						.findFunOrgsByParentIdAndName(org.getId(), tag)) {
					newlist.add(orgs);
				}
			}
			return newlist;
		}
	}

	private List<Organization> getAssignTargeAdminOrgs(String tag) {
		List<Organization> lists = new ArrayList<Organization>();
		List<Organization> list = organizationDubboService
				.findAdminOrgsByParentIdAndName(getCurrentLoginedOrg().getId(),
						tag);
		int size = list.size();
		if (getCurrentLoginedOrg().getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
			for (int i = 0; i < size; i++) {
				lists.add(list.get(i));
				List<Organization> organizations = organizationDubboService
						.findAdminOrgsByParentIdAndName(list.get(i).getId(),
								tag);
				if (organizations != null) {
					for (int j = 0; j < organizations.size(); j++) {
						lists.add(organizations.get(j));
					}
				}
			}
		} else {
			lists = list;
		}
		return lists;
	}

	public Organization getSingleForwardOrg(Long issueDealType) {
		if (forwardToFunOrganization(issueDealType)) {
			return getParentFunOrganization();
		} else {
			return getParentAdminOrganization();
		}
	}

	private Organization getParentAdminOrganization() {
		if (isBaseAdminOrganization()) {
			return getTownAdminOrganization();
		} else {
			return getCurrentLoginedOrg().getParentOrg();
		}
	}

	private Organization getTownAdminOrganization() {
		Long townOrgId = organizationDubboService
				.getTownOrganizationId(getCurrentLoginedOrg().getId());
		return organizationDubboService.getSimpleOrgById(townOrgId);
	}

	private boolean isBaseAdminOrganization() {
		return organizationDubboService
				.isGridOrganization(getCurrentLoginedOrg().getId());
	}

	private Organization getParentFunOrganization() {
		return getCurrentLoginedOrg().getParentFunOrg();
	}

	private boolean forwardToFunOrganization(Long issueDealType) {
		return issueDealType.intValue() == IssueDealType.SUBMIT_FORWARD_FUNCTION_TO_FUNCTION;
	}

	// public Organization getParentOrg(Long issueDealType) {
	// if(issueDealType.intValue() ==
	// IssueDealType.SUBMIT_FORWARD_FUNCTION_TO_FUNCTION){
	// return this.getOrg().getParentFunOrg();
	// }else{
	// return this.getOrg().getParentOrg();
	// }
	// }

}
