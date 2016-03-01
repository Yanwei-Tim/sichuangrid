package com.tianque.baseInfo.companyPlace.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.service.CompanyPlaceTransferService;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.plugin.transfer.vo.ErrorMessageVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/baseinfo/companyPlaceTransfer")
@Transactional
@Scope("request")
@Controller("companyPlaceTransferController")
public class CompanyPlaceTransferController extends BaseAction {

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
	private String modulKey;
	/** 所需要转移的数据的orgId */
	private String orgIds;

	@Autowired
	private CompanyPlaceTransferService companyPlaceTransferService;

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
		if (!StringUtil.isStringAvaliable(type) || toOrgId == null) {
			errorMessage = "参数有误";
			return ERROR;
		}
		resultList = companyPlaceTransferService.transfer(type, moveIds,
				toOrgId, modulKey);
		return SUCCESS;
	}

	@Action(value = "transferDispatch", results = {
			@Result(name = "success", location = "/baseinfo/companyPlace/shiftTree.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String transferDispatch() throws Exception {
		if (orgId == null) {
			errorMessage = "组织机构参数有误";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(orgId);
		return SUCCESS;
	}

	@EncryptAnnotation
	@Action(value = "transferDispatchByEncrypt", results = {
			@Result(name = "success", location = "/baseinfo/companyPlace/shiftTree.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String transferDispatchByEncrypt() throws Exception {
		if (orgId == null) {
			errorMessage = "组织机构参数有误";
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

	public String getModulKey() {
		return modulKey;
	}

	public void setModulKey(String modulKey) {
		this.modulKey = modulKey;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

}
