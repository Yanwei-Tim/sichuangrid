package com.tianque.plugin.weChat.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.exception.ServiceException;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.weChat.domain.redEnvelopeManagement.RedEnvelopesPaymentRecords;
import com.tianque.plugin.weChat.service.RedEnvelopesPaymentRecordsService;

/**
 * 微信红包发放记录
 */
@Namespace("/redEnvelopesPaymentRecordsManage")
@Scope("prototype")
@Controller("redEnvelopesPaymentRecordsController")
public class RedEnvelopesPaymentRecordsController extends BaseAction {
	private static Logger logger = LoggerFactory
			.getLogger(RedEnvelopesPaymentRecordsController.class);

	@Autowired
	private RedEnvelopesPaymentRecordsService redEnvelopesPaymentRecordsService;
	private RedEnvelopesPaymentRecords redEnvelopesPaymentRecords;

	/**
	 * 获取微信红包发放记录分页列表
	 */
	@Action(value = "findRedEnvelopesPaymentRecordsByPage", results = { @Result(type = "json", name = "success", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findRedEnvelopesPaymentRecordsByPage() {
		try {
			if (redEnvelopesPaymentRecords == null || redEnvelopesPaymentRecords.getOrg() == null
					|| redEnvelopesPaymentRecords.getOrg().getId() == null) {
				errorMessage = "获取红包分页列表出错,请刷新后重试!";
				return ERROR;
			}
			PageInfo<RedEnvelopesPaymentRecords> redRrcordList = redEnvelopesPaymentRecordsService
					.findRedEnvelopesPaymentRecordsByPage(redEnvelopesPaymentRecords, page, rows,
							sidx, sord);
			gridPage = new GridPage(redRrcordList);
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = "获取红包分页列表出错,请刷新后重试!";
			logger.error(
					"类redEnvelopesPaymentRecordsController的findRedEnvelopesPaymentRecordsByPage方法出现异常，原因：",
					e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 获取红包发放记录详情
	 */
	@Action(value = "viewRedEnvelopesPaymentRecords", results = {
			@Result(name = "success", location = "/template/redEnvelopeManagement/redEnvelopesPaymentRecordsView.ftl"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	@PermissionFilter(ename = "viewRedEnvelopesPaymentRecords")
	public String viewRedEnvelopesPaymentRecords() {
		try {
			if (id == null) {
				errorMessage = "获取红包发放记录详情出错,请刷新后重试!";
				return ERROR;
			}
			redEnvelopesPaymentRecords = redEnvelopesPaymentRecordsService
					.getRedEnvelopesPaymentRecordsById(id);
			redEnvelopesPaymentRecords = redEnvelopesPaymentRecordsService
					.viewRedEnvelopesPaymentRecords(redEnvelopesPaymentRecords);
		} catch (ServiceException e) {
			this.errorMessage = e.getMessage();
			return ERROR;
		} catch (Exception e) {
			this.errorMessage = "获取红包发放记录详情出错";
			logger.error(
					"类redEnvelopesPaymentRecordsController的viewRedEnvelopesPaymentRecords方法出现异常，原因：",
					e);
			return ERROR;
		}
		return SUCCESS;

	}

	public RedEnvelopesPaymentRecords getRedEnvelopesPaymentRecords() {
		return redEnvelopesPaymentRecords;
	}

	public void setRedEnvelopesPaymentRecords(RedEnvelopesPaymentRecords redEnvelopesPaymentRecords) {
		this.redEnvelopesPaymentRecords = redEnvelopesPaymentRecords;
	}

}
