package com.tianque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.service.MergeOrganizationService;

@Scope("prototype")
@Transactional
@SuppressWarnings("serial")
@Controller("mergeOrganizationController")
public class MergeOrganizationController extends BaseAction {

	@Autowired
	private MergeOrganizationService mergeOrganizationDubboService;

	private Long orgId;
	private Long mergeOrgId;

	public String mergeOrganizationByOrgId() {
		if (orgId == null) {
			errorMessage = "没有选定组织机构";
			return ERROR;
		}
		mergeOrganizationDubboService.mergeOrganizationByOrgId(orgId,
				mergeOrgId);
		return SUCCESS;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getMergeOrgId() {
		return mergeOrgId;
	}

	public void setMergeOrgId(Long mergeOrgId) {
		this.mergeOrgId = mergeOrgId;
	}

}
