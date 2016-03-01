package com.tianque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.ServicePerson;
import com.tianque.service.ServicePersonService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("servicePersonController")
@Scope("prototype")
@Transactional
public class ServicePersonController extends BaseAction {
	/**
	 * @author niuziyang
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ServicePersonService servicePersonService;

	@Autowired
	private OrganizationDubboService organizationDubboService;
	/**
	 * 删除 id
	 */
	private Long deleteId;
	/**
	 * 服务队员，用于增加/修改操作
	 */
	private ServicePerson servicePerson;
	private Organization organization;

	// @PermissionFilter(ename = "addServicePerson")
	public String addServicePerson() {
		if (null == servicePerson.getName() || "".equals(servicePerson.getName().trim())) {
			errorMessage = "姓名不能为空，请输入。";
			return ERROR;
		}
		if (null == servicePerson.getOrganization()
				|| null == servicePerson.getOrganization().getId()) {
			errorMessage = "系统组织编号注入有误。";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(servicePerson.getOrganization()
				.getId());
		servicePerson.setOrgInternalCode(org.getOrgInternalCode());
		servicePerson = servicePersonService.addServicePerson(servicePerson);
		return SUCCESS;
	}

	// @PermissionFilter(ename = "updateServicePerson")
	public String updateServicePerson() {
		if (null == servicePerson.getName() || "".equals(servicePerson.getName().trim())) {
			errorMessage = "姓名不能为空，请输入。";
			return ERROR;
		}
		if (null == servicePerson.getOrganization()
				|| null == servicePerson.getOrganization().getId()) {
			errorMessage = "系统组织编号注入有误。";
			return ERROR;
		}
		Organization org = organizationDubboService.getSimpleOrgById(servicePerson.getOrganization()
				.getId());
		servicePerson.setOrgInternalCode(org.getOrgInternalCode());
		servicePerson = servicePersonService.updateServicePerson(servicePerson);
		return SUCCESS;
	}

	// @PermissionFilter(ename = "deleteServicePerson")
	public String deleteServicePerson() {
		if (null == deleteId) {
			this.errorMessage = "所删除行的编号为空。";
			return ERROR;
		}
		boolean flag = servicePersonService.deleteServicePersonById(deleteId);
		if (flag) {
			return SUCCESS;
		} else {
			this.errorMessage = "删除中出现错误";
			return ERROR;
		}
	}

	public String dispatch() {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			return prepareAddServicePerson();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			return prepareUpdateServicePerson();
		}
		return SUCCESS;
	}

	public String prepareUpdateServicePerson() {
		if (servicePerson.getId() == null) {
			errorMessage = "服务人员编号有误";
			return ERROR;
		}
		servicePerson = servicePersonService.getSimpleServicePersonById(servicePerson.getId());
		organization.setId(organization.getId());
		return SUCCESS;
	}

	public String prepareAddServicePerson() {
		servicePerson = new ServicePerson();
		servicePerson.setOrganization(organizationDubboService.getSimpleOrgById(organization.getId()));
		organization.setId(organization.getId());
		return SUCCESS;
	}

	// @PermissionFilter(ename = "gridIntroductionManagement")
	public String servicePersonList() {
		String orgInternalCode = organizationDubboService.getSimpleOrgById(organization.getId())
				.getOrgInternalCode();
		PageInfo<ServicePerson> pageInfo = servicePersonService.findServicePersonForPage(page,
				rows, sidx, sord, orgInternalCode);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public Long getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(Long deleteId) {
		this.deleteId = deleteId;
	}

	public ServicePerson getServicePerson() {
		return servicePerson;
	}

	public void setServicePerson(ServicePerson servicePerson) {
		this.servicePerson = servicePerson;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
