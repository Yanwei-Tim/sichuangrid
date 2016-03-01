package com.tianque.plugin.weChat.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.weChat.domain.sms.SmsAccount;
import com.tianque.plugin.weChat.service.SmsAccountService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 短信账号管理
 * @ClassName: SmsAccountController 
 * @author: he.simin
 * @date: 2015年11月6日 下午5:03:11
 */
@Namespace("/smsAccountManage")
@Scope("prototype")
@Controller
public class SmsAccountController extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(SmsAccountController.class);
	@Autowired
	private SmsAccountService smsAccountService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private SmsAccount smsAccount;

	@Action(value = "findSmsAccount", results = {
			@Result(name = "success", type = "json", params = { "root", "gridPage" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findSmsAccount() {
		if (smsAccount == null || smsAccount.getOrg() == null
				|| smsAccount.getOrg().getId() == null) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		smsAccount.setOrg(organizationDubboService.getSimpleOrgById(smsAccount.getOrg().getId()));
		PageInfo<SmsAccount> pageInfo = smsAccountService.findSmsAccount(smsAccount, page, rows,
				sidx, sord);
		ControllerHelper.processAllOrgName(pageInfo.getResult(), organizationDubboService,
				new String[] { "org" });
		gridPage = new GridPage(pageInfo);
		return "success";
	}

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/wechat/smsAccount/smsAccountDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String dispatch() {
		try {
			if ("add".equals(mode)) {
				smsAccount = new SmsAccount();
				smsAccount.setOrg(ThreadVariable.getOrganization());
				return SUCCESS;
			} else if ("update".equals(mode)) {
				smsAccount = smsAccountService.getById(id);
				if (!ThreadVariable.getOrganization().getOrgInternalCode()
						.equals(smsAccount.getOrg().getOrgInternalCode())) {
					this.errorMessage = "只能修改自己层级的账户";
					return ERROR;
				}
				smsAccount.setOrg(organizationDubboService.getSimpleOrgById(smsAccount.getOrg()
						.getId()));
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	@Action(value = "addSmsAccount", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	@PermissionFilter(ename = "addSmsAccount")
	public String addSmsAccount() {
		try {
			if (smsAccount != null) {
				smsAccount.setOrg(ThreadVariable.getOrganization());
			}
			smsAccountService.saveSmsAccount(smsAccount);
			return SUCCESS;
		} catch (BusinessValidationException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("error:", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	@Action(value = "updateSmsAccount", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	@PermissionFilter(ename = "updateSmsAccount")
	public String updateSmsAccount() {
		try {
			if (smsAccount != null) {
				smsAccount.setOrg(ThreadVariable.getOrganization());
			}
			smsAccountService.updateSmsAccount(smsAccount);
			return SUCCESS;
		} catch (BusinessValidationException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			logger.error("error:", e);
			this.errorMessage = e.getMessage();
			return ERROR;
		}
	}

	public SmsAccount getSmsAccount() {
		return smsAccount;
	}

	public void setSmsAccount(SmsAccount smsAccount) {
		this.smsAccount = smsAccount;
	}

}
