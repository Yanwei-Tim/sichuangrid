package com.tianque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.controller.mode.MoveMode;
import com.tianque.core.base.BaseAction;
import com.tianque.core.property.GridInternalProperty;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.service.IssueTypeDomainService;
import com.tianque.service.IssueTypeService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

@Transactional
@Scope("prototype")
@SuppressWarnings({ "serial", "deprecation" })
@Controller("issueTypeManageController")
public class IssueTypeManageController extends BaseAction {
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private String moveMode = "";
	private Long referIssueTypeId;

	private List<IssueType> issueTypes;

	private List<GridInternalProperty> gridInternalProperty;

	private IssueTypeDomain issueTypeDomain = new IssueTypeDomain();

	private IssueType issueType = new IssueType();

	private List<IssueTypeDomain> issueTypeDomains;

	private Session session;

	private User user;

	private Long orgId;

	private String domainName;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	@Autowired
	private IssueTypeDomainService issueTypeDomainService;

	@Autowired
	private IssueTypeService issueTypeService;

	public String ajaxFindIssueTypesByDomainId() throws Exception {
		if (issueType.getIssueTypeDomain().getId() == null) {
			this.errorMessage = "根据大类ID获取类别出错！";
			return ERROR;
		}
		issueTypes = issueTypeService.findIssueTypesByDomainId(issueType
				.getIssueTypeDomain().getId());
		return SUCCESS;
	}

	public String ajaxFindIssueTypesByDomainName() throws Exception {
		if (issueType.getIssueTypeDomain().getDomainName() == null
				|| "".equals(issueType.getIssueTypeDomain().getDomainName())) {
			this.errorMessage = "根据大类名称获取类别出错！";
			return ERROR;
		}
		issueTypes = issueTypeService.findIssueTypesByParentName(issueType
				.getIssueTypeDomain().getDomainName());
		return SUCCESS;
	}

	// 获得大类信息
	public String findIssueTypeDomains() throws Exception {
		issueTypeDomains = issueTypeDomainService.findIssueTypeDomains();
		this.setCurrentUser();
		return SUCCESS;
	}

	public String findIssueTypeDomainsByDomainName() throws Exception {
		issueTypeDomains = issueTypeDomainService
				.findIssueTypeDomainsByDomainName(domainName);
		this.setCurrentUser();
		return SUCCESS;
	}

	// 根据大类ID获得大类信息
	public String getIssueTypeDomainById() throws Exception {
		if (issueTypeDomain.getId() == null) {
			this.errorMessage = "请先选择大类！";
			return ERROR;
		}
		issueTypeDomain = issueTypeDomainService
				.getIssueTypeDoaminById(issueTypeDomain.getId());
		gridInternalProperty = issueTypeDomain.getInternaleProperties();
		setCurrentUser();
		return SUCCESS;
	}

	@PermissionFilter(ename = "addIssueType")
	public String addIssueType() throws Exception {
		if (!isAddPersonallized()) {
			this.errorMessage = "个性化定制只能在县区下创建！";
			return ERROR;
		}
		if (!issueTypeNameNotExised()) {
			this.errorMessage = "该服务类别已存在！";
			return ERROR;
		}
		issueType = issueTypeService.addIssueType(issueType);
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateIssueType")
	public String updateIssueType() throws Exception {
		// if (!issueTypeNameNotExised()) {
		// this.errorMessage = "该服务类别已存在！";
		// return ERROR;
		// }
		issueType = issueTypeService.updateIssueType(issueType);
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteIssueType")
	public String deleteIssueType() throws Exception {
		issueType = issueTypeService.getIssueTypeById(issueType
				.getIssueTypeDomain().getId(), issueType.getId());
		if (!issueTypeService.deleteIssueTypeById(issueType)) {
			this.errorMessage = "该类别已被应用，不能删除！";
			return ERROR;
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "moveIssueType")
	public String moveIssueType() throws Exception {
		if (moveMode == null || "".equals(moveMode)) {
			this.errorMessage = "请选择移动方式!";
			return ERROR;
		}
		issueType = issueTypeService.getIssueTypeById(issueType
				.getIssueTypeDomain().getId(), issueType.getId());
		if (MoveMode.TO_FIRST.equalsIgnoreCase(moveMode)) {
			issueTypeService.moveIssueTypeToFirst(issueType
					.getIssueTypeDomain().getId(), issueType.getId(), issueType
					.getIndexId());
		} else if (MoveMode.TO_END.equalsIgnoreCase(moveMode)) {
			issueTypeService.moveIssueTypeToEnd(issueType.getIssueTypeDomain()
					.getId(), issueType.getId(), issueType.getIndexId());
		} else if (MoveMode.TO_PREVIOUS.equalsIgnoreCase(moveMode)
				|| MoveMode.TO_NEXT.equalsIgnoreCase(moveMode)) {
			issueTypeService.moveIssueTypeToPreviousOrNext(issueType
					.getIssueTypeDomain().getId(), issueType.getId(), issueType
					.getIndexId(), referIssueTypeId);
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "issueTypesManagement")
	public String findIssueTypes() throws Exception {
		issueTypeDomain = issueTypeDomainService
				.getIssueTypeDoaminById(issueType.getIssueTypeDomain().getId());
		List<IssueType> pageInfo = null;
		if (issueType.getIssueTypeDomain().getId() == null) {
			gridPage = new GridPage(new PageInfo<IssueType>());
			return SUCCESS;
		}
		this.setCurrentUser();
		if (issueTypeDomain.isPersonalized()) {
			if (user.isAdmin()) {
				pageInfo = issueTypeService.findIssueTypesByDomainId(issueType
						.getIssueTypeDomain().getId());
			} else {
				Long orgId = user.getOrganization().getId();
				pageInfo = issueTypeService.findIssueTypesByDomainIdAndOrgId(
						issueType.getIssueTypeDomain().getId(), orgId);
			}
		} else {
			pageInfo = issueTypeService.findIssueTypesByDomainId(issueType
					.getIssueTypeDomain().getId());
		}
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	private void setCurrentUser() {
		session = ThreadVariable.getSession();
		user = this.permissionService.getSimpleUserById(session.getUserId());
	}

	public String dispatchIssueTypeOperate() throws Exception {
		issueTypeDomain = issueTypeDomainService
				.getIssueTypeDoaminById(issueType.getIssueTypeDomain().getId());
		gridInternalProperty = issueTypeDomain.getInternaleProperties();
		if (!DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			issueType = issueTypeService.getIssueTypeById(issueType
					.getIssueTypeDomain().getId(), issueType.getId());
		}
		issueType.setIssueTypeDomain(issueTypeDomain);
		this.setCurrentUser();
		return SUCCESS;
	}

	private boolean issueTypeNameNotExised() {
		try {
			issueTypeDomain = issueTypeDomainService
					.getIssueTypeDoaminById(issueType.getIssueTypeDomain()
							.getId());
			IssueType existedIssueType = issueTypeService
					.getIssueTypeByIssueTypeName(issueType.getIssueTypeName(),
							issueType.getIssueTypeDomain().getId());
			this.setCurrentUser();
			if (issueTypeDomain.isPersonalized()) {
				return this.issueTypeNameNotExisedByOrgId();
			} else {
				if (existedIssueType == null) {
					return true;
				}
			}
			if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
				if (existedIssueType.getId().equals(issueType.getId())) {
					return true;
				}
			}
		} catch (Exception e) {
			this.errorMessage = "检查类别是否被应用时系统错误!" + e.getMessage();
			return false;
		}
		return false;
	}

	private boolean isAddPersonallized() {
		issueTypeDomain = issueTypeDomainService
				.getIssueTypeDoaminById(issueType.getIssueTypeDomain().getId());
		this.setCurrentUser();
		if (issueTypeDomain.isPersonalized()) {
			if (user.isAdmin()) {
				Organization organization = organizationDubboService
						.getFullOrgById(orgId);
				if (organization.getOrgLevel().getInternalId() != OrganizationLevel.DISTRICT) {
					return false;
				}
			} else {
				Organization organization = organizationDubboService
						.getFullOrgById(user.getOrganization().getId());
				if (organization.getOrgLevel().getInternalId() != OrganizationLevel.DISTRICT) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean issueTypeNameNotExisedByOrgId() {
		Organization organization;
		if (user.isAdmin()) {
			organization = organizationDubboService.getSimpleOrgById(orgId);
		} else {
			organization = user.getOrganization();
		}
		IssueType existedIssueTypes = issueTypeService
				.getIssueTypeByIssueTypeNameAndOrgId(issueType
						.getIssueTypeName(), issueType.getIssueTypeDomain()
						.getId(), organization.getId());
		if (existedIssueTypes == null) {
			issueType.setOrg(organization);
			issueType.setOrgInternalCode(organization.getOrgInternalCode());
			issueType.setPersonalized(true);
			return true;
		}
		return false;
	}

	public String enabledOperate() throws Exception {
		if (issueType == null || issueType.getId() == null) {
			this.errorMessage = "请选择一个事件处理类别！";
			return ERROR;
		} else {
			issueTypeService.updateIsEnabledById(issueType.getId(),
					issueType.isEnabled());
		}
		return SUCCESS;
	}

	public String validateIssueTypeName() {
		if (issueTypeNameNotExised()) {
			return SUCCESS;
		}
		return ERROR;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public String getMoveMode() {
		return moveMode;
	}

	public void setMoveMode(String moveMode) {
		this.moveMode = moveMode;
	}

	public Long getReferIssueTypeId() {
		return referIssueTypeId;
	}

	public void setReferIssueTypeId(Long referIssueTypeId) {
		this.referIssueTypeId = referIssueTypeId;
	}

	public List<GridInternalProperty> getGridInternalProperty() {
		return gridInternalProperty;
	}

	public void setGridInternalProperty(
			List<GridInternalProperty> gridInternalProperty) {
		this.gridInternalProperty = gridInternalProperty;
	}

	public IssueTypeDomain getIssueTypeDomain() {
		return issueTypeDomain;
	}

	public void setIssueTypeDomain(IssueTypeDomain issueTypeDomain) {
		this.issueTypeDomain = issueTypeDomain;
	}

	public List<IssueType> getIssueTypes() {
		return issueTypes;
	}

	public void setIssueTypes(List<IssueType> issueTypes) {
		this.issueTypes = issueTypes;
	}

	public List<IssueTypeDomain> getIssueTypeDomains() {
		return issueTypeDomains;
	}

	public void setIssueTypeDomains(List<IssueTypeDomain> issueTypeDomains) {
		this.issueTypeDomains = issueTypeDomains;
	}

}
