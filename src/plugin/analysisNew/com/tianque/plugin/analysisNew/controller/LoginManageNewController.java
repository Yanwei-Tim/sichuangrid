package com.tianque.plugin.analysisNew.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.vo.FullReportVo;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.plugin.analysisNew.domain.LoginManage;
import com.tianque.plugin.analysisNew.service.LoginManageNewService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("loginManageNewController")
@Namespace("/baseinfo/loginManageNew")
@Scope("prototype")
public class LoginManageNewController extends BaseAction {
	@Autowired
	private LoginManageNewService loginManageNewService;
	// 原来的dubboService包下的
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private final static String TYPEVALUE = "1";

	private List<LoginManage> loginManageList;
	private LoginManage loginManage;
	private String type;

	private FullReportVo fullReportVo;

	@Actions({ @Action(value = "findLoginManageForListPage", results = {
			@Result(type = "json", name = "success", params = { "root",
					"fullReportVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String findLoginManageForListPage() throws Exception {
		if (loginManage == null || loginManage.getOrganization() == null
				|| loginManage.getOrganization().getId() == null) {

			return ERROR;
		}
		fullReportVo = new FullReportVo();
		fullReportVo.setBigTitle(organizationDubboService.getSimpleOrgById(
				loginManage.getOrganization().getId()).getOrgName()
				+ " "
				+ loginManage.getYear()
				+ "年"
				+ loginManage.getMonth()
				+ "月" + "登录统计");
		if (!StringUtil.isStringAvaliable(type)) {
			type = TYPEVALUE;// 默认查询各层级直属下辖数据
		}
		if (TYPEVALUE.equals(type)) {
			loginManageList = loginManageNewService
					.queryLoginManageForList(loginManage);
		} else {
			loginManageList = loginManageNewService
					.queryLoginManageByOrgIdForList(loginManage);
		}
		fullReportVo.setObjectDataList(loginManageList);
		return SUCCESS;
	}

	public FullReportVo getFullReportVo() {
		return fullReportVo;
	}

	public void setFullReportVo(FullReportVo fullReportVo) {
		this.fullReportVo = fullReportVo;
	}

	public List<LoginManage> getLoginManageList() {
		return loginManageList;
	}

	public void setLoginManageList(List<LoginManage> loginManageList) {
		this.loginManageList = loginManageList;
	}

	public LoginManage getLoginManage() {
		return loginManage;
	}

	public void setLoginManage(LoginManage loginManage) {
		this.loginManage = loginManage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
