package com.tianque.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SmsMessageMark;
import com.tianque.domain.property.SmsMessageMarkType;
import com.tianque.service.SmsMessageMarkService;

@SuppressWarnings("serial")
@Controller("smsMessageMarkController")
@Scope("prototype")
@Namespace("/smsMessageMarkManage")
public class SmsMessageMarkController extends BaseAction {
	@Autowired
	private SmsMessageMarkService smsMessageMarkService;

	private SmsMessageMark smsMessageMark;
	private List<SmsMessageMarkType> operatesTypeList;
	private List<SmsMessageMarkType> dealTypeList;

	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@Actions({ @Action(value = "dispatch", results = {
			@Result(name = "add", location = "/interaction/smsMessageMark/maintainSmsMessageMarkDlg.jsp"),
			@Result(name = "edit", location = "/interaction/smsMessageMark/maintainSmsMessageMarkDlg.jsp"),
			@Result(name = "view", location = "/interaction/smsMessageMark/viewSmsMessageMarkDlg.jsp") }) })
	public String dispatch() {
		operatesTypeList = SmsMessageMarkType.findOperatesList();
		dealTypeList = SmsMessageMarkType.findDealList();
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			smsMessageMark = new SmsMessageMark();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)
				|| DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			if (smsMessageMark != null) {
				smsMessageMark = smsMessageMarkService
						.getSimpleSmsMessageMarkById(smsMessageMark.getId());
			}
		}

		return mode;
	}

	/***************************************************************************
	 * 新增短信提示语模版
	 * 
	 * @return
	 */
	@Action(value = "addSmsMessageMark", results = {
			@Result(name = "success", type = "json", params = { "root",
					"smsMessageMark", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "addSmsMessageMark")
	public String addSmsMessageMark() {
		if (!validate(smsMessageMark)) {
			return ERROR;
		}
		if (!checkSmsMessge(smsMessageMark)) {
			return ERROR;
		}
		smsMessageMark = smsMessageMarkService
				.addSmsMessageMark(smsMessageMark);

		return SUCCESS;
	}

	/***************************************************************************
	 * 修改短信提示语模版
	 * 
	 * @return
	 */
	@Action(value = "updateSmsMessageMark", results = {
			@Result(name = "success", type = "json", params = { "root",
					"smsMessageMark", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarch", "false" }) })
	@PermissionFilter(ename = "updateSmsMessageMark")
	public String updateSmsMessageMark() {
		if (!validate(smsMessageMark)) {
			return ERROR;
		}
		smsMessageMark = smsMessageMarkService
				.updateSmsMessageMark(smsMessageMark);
		return SUCCESS;
	}

	/***************************************************************************
	 * 删除短信提示语模版
	 * 
	 * @return
	 */
	@Action(value = "deleteSmsMessageMark", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	@PermissionFilter(ename = "deleteSmsMessageMark")
	public String deleteSmsMessageMark() {
		if (smsMessageMark != null) {
			if (!smsMessageMarkService.deleteSmsMessageMarkById(smsMessageMark
					.getId())) {
				this.errorMessage = "该信息已在其他地方引用，不能删除!";
				return ERROR;
			}
		} else {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		return SUCCESS;
	}

	/***************************************************************************
	 * 短信提示语模版列表
	 * 
	 * @return
	 */
	@Action(value = "findSmsMessageMarks", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String findSmsMessageMarks() {
		PageInfo<SmsMessageMark> smsMessageMarks = smsMessageMarkService
				.findSmsMessageMarks(page, rows, sidx, sord);

		gridPage = new GridPage(smsMessageMarks);
		return SUCCESS;
	}

	// /***************************************************************************
	// * 短信提示语模版列表高级搜索
	// *
	// * @return
	// */
	// @Action(value = "searchSmsMessageMark", results = {
	// @Result(name = "success", type = "json", params = { "root",
	// "gridPage", "ignoreHierarchy", "false" }),
	// @Result(name = "error", type = "json", params = { "root",
	// "errorMessage", "ignoreHierarchy", "false" }) })
	// @PermissionFilter(ename = "searchSmsMessageMark")
	// public String searchSmsMessageMark() {
	// PageInfo<SmsMessageMark> smsMessageMarks = smsMessageMarkService
	// .searchSmsMessageMark(smsMessageMark, page, rows, sidx, sord);
	// gridPage = new GridPage(smsMessageMarks);
	// return SUCCESS;
	// }

	private boolean validate(SmsMessageMark smsMessageMark) {
		if (smsMessageMark == null) {
			this.errorMessage = "短信提示语模版不能为空!";
			return false;
		}
		if (smsMessageMark.getOperationtType() == null
				|| "".equals(smsMessageMark.getModel())) {
			this.errorMessage = "短信提示语的操作类型不能为空";
			return false;
		}
		if (SmsMessageMarkType.DEAL_CODE == smsMessageMark.getOperationtType()) {
			if (smsMessageMark.getDealType() == null
					|| "".equals(smsMessageMark.getDealType())) {
				this.errorMessage = "短信提示语的处理类型不能为空";
				return false;
			}
		}
		String[] modelStrings = StringUtils.split(smsMessageMark.getModel(),
				"#");
		if (!"user".equals(modelStrings[1]) || !"time".equals(modelStrings[3])) {
			this.errorMessage = "短信提示语的模版定义出差，格式为：user";
			return false;
		}
		return true;
	}

	private boolean checkSmsMessge(SmsMessageMark smsMessageMark) {
		SmsMessageMark sms = new SmsMessageMark();
		if (smsMessageMark.getOperationtType() == SmsMessageMarkType.DEAL_CODE) {
			sms = smsMessageMarkService
					.getSimpleSmsMessageMarkByDealType(smsMessageMark
							.getDealType());
			if (sms != null && sms.getId() != null) {
				this.errorMessage = "操作类型为'办理'，办理类型为'"
						+ SmsMessageMarkType.dealOperates(
								smsMessageMark.getDealType()).getName()
						+ "'的短信模版定义已定义，您只能进行修改操作，不能重复定义！";
				return false;
			}

		} else {
			sms = smsMessageMarkService
					.getSimpleSmsMessageMarkByOperationtType(smsMessageMark
							.getOperationtType());
			if (sms != null && sms.getId() != null) {
				this.errorMessage = "操作类型为'"
						+ SmsMessageMarkType.allOperates(
								smsMessageMark.getOperationtType()).getName()
						+ "'的短信模版定义已定义，您只能进行修改操作，不能重复定义！";
				return false;
			}
		}
		return true;
	}

	public List<SmsMessageMarkType> getOperatesList() {
		return SmsMessageMarkType.findOperatesList();
	}

	public SmsMessageMark getSmsMessageMark() {
		return smsMessageMark;
	}

	public void setSmsMessageMark(SmsMessageMark smsMessageMark) {
		this.smsMessageMark = smsMessageMark;
	}

	public List<SmsMessageMarkType> getOperatesTypeList() {
		return operatesTypeList;
	}

	public void setOperatesTypeList(List<SmsMessageMarkType> operatesTypeList) {
		this.operatesTypeList = operatesTypeList;
	}

	public List<SmsMessageMarkType> getDealTypeList() {
		return dealTypeList;
	}

	public void setDealTypeList(List<SmsMessageMarkType> dealTypeList) {
		this.dealTypeList = dealTypeList;
	}

}
