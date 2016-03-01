package com.tianque.publicSecurity.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.domain.Organization;
import com.tianque.plugin.transfer.util.TransferUtil;
import com.tianque.plugin.transfer.vo.ErrorMessageVo;
import com.tianque.publicSecurity.service.PublicSecurityTransferService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/publicSecurity/transferManage")
@Transactional
@Scope("request")
@Controller("publicSecurityTransferController")
public class PublicSecurityTransferController extends BaseAction {

	@Autowired
	private PublicSecurityTransferService publicSecurityTransferService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	/** 原类型 */
	private String type;
	/** 大类型 */
	private String bigType;
	/** 原ids */
	private String ids;
	/** 目标网格id */
	private Long toOrgId;
	/** 原网格id */
	private Long orgId;
	/** 定位网格 */
	private Organization organization;
	/** 错误信息结果集 */
	private List<ErrorMessageVo> resultList;

	@Action(value = "transfer", results = {
			@Result(name = "success", type = "json", params = { "root",
					"resultList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String transfer() throws Exception {
		List<Long> moveIds = new ArrayList<Long>();
		String[] idStrs = ids.split(",");
		for (String idStr : idStrs) {
			moveIds.add(new Long(idStr));
		}
		resultList = publicSecurityTransferService.transfer(type, moveIds,
				toOrgId);
		bigType = TransferUtil.getBigType(type);
		return SUCCESS;
	}

	@Action(value = "transferDispatch", results = {
			@Result(name = "success", location = "/digitalCity/publicSecurity/shiftTree.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String transferDispatch() throws Exception {
		if (orgId == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		if (StringUtils.isBlank(type)) {
			errorMessage = "系统忙";
			return ERROR;
		}
		if (ids == null) {
			errorMessage = "系统忙";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(orgId);
		return SUCCESS;
	}

	/**
	 * ID加密转移
	 * 
	 * @return
	 */
	@EncryptAnnotation
	@Action(value = "transferDispatchByEncrypt", results = {
			@Result(name = "success", location = "/digitalCity/publicSecurity/shiftTree.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String transferDispatchByEncrypt() throws Exception {
		if (orgId == null) {
			errorMessage = "组织机构不能为空";
			return ERROR;
		}
		if (StringUtils.isBlank(type)) {
			errorMessage = "系统忙";
			return ERROR;
		}
		if (ids == null) {
			errorMessage = "系统忙";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(orgId);
		return SUCCESS;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getToOrgId() {
		return toOrgId;
	}

	public void setToOrgId(Long toOrgId) {
		this.toOrgId = toOrgId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<ErrorMessageVo> getResultList() {
		return resultList;
	}

	public void setResultList(List<ErrorMessageVo> resultList) {
		this.resultList = resultList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getBigType() {
		return bigType;
	}

	public void setBigType(String bigType) {
		this.bigType = bigType;
	}

}
