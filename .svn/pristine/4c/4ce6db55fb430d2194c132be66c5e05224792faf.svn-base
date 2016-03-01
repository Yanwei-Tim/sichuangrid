package com.tianque.sms.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.sms.domain.Smsquerycondition;
import com.tianque.sms.service.SmsqueryconditionService;

/**
 * 查询条件管理:服务前端控制类
 * 
 * @author
 * @date 2013-07-03 15:25:55
 */
@Namespace("/smsqueryconditionManage")
@Scope("request")
@Controller("smsqueryconditionController")
public class SmsqueryconditionController extends BaseAction {

	@Autowired
	private SmsqueryconditionService smsqueryconditionService;

	private Smsquerycondition smsquerycondition;
	private String ids;
	private Long id;
	private Long objectId;
	private String sql;
	private String fieldStr;
	private boolean bol;

	/**
	 * 查询条件管理操作分发
	 */
	@Actions({ @Action(value = "dispatchOperate", results = { @Result(name = "success", location = "/interaction/newSMS/smsqueryconditionManage/smsqueryconditionViewDlg.jsp") }) })
	public String dispatchOperate() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return SUCCESS;
		} else if (DialogMode.EDIT_MODE.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			smsquerycondition = smsqueryconditionService.get(id);
			objectId = smsquerycondition.getSmsSendObject().getId();
		}
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 */
	@Action(value = "addSmsquerycondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"smsquerycondition", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addSmsquerycondition() throws Exception {
		if (smsquerycondition == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		smsquerycondition = smsqueryconditionService.add(smsquerycondition);
		return SUCCESS;
	}

	/**
	 * 更新实体对象
	 */
	@Action(value = "updateSmsquerycondition", results = {
			@Result(name = "success", type = "json", params = { "root",
					"smsquerycondition", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updateSmsquerycondition() throws Exception {
		if (smsquerycondition == null || smsquerycondition.getId() == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		smsquerycondition = smsqueryconditionService.update(smsquerycondition);
		return SUCCESS;
	}

	/**
	 * 验证中间Key是否重复
	 */
	@Action(value = "validateKeyIsRepeat", results = {
			@Result(name = "success", type = "json", params = { "root", "bol" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String validateKeyIsRepeat() throws Exception {
		if (objectId == null || !StringUtil.isStringAvaliable(fieldStr)) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		bol = smsqueryconditionService.validateKey(id, objectId, fieldStr);
		return SUCCESS;
	}

	/**
	 * 验证描述是否重复
	 */
	@Action(value = "validateDescriptionIsRepeat", results = {
			@Result(name = "success", type = "json", params = { "root", "bol" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String validateDescriptionIsRepeat() throws Exception {
		if (objectId == null || !StringUtil.isStringAvaliable(fieldStr)) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		bol = smsqueryconditionService.validateDescription(id, objectId,
				fieldStr);
		return SUCCESS;
	}

	/**
	 * 根据ID删除实体对象
	 */
	@Action(value = "deleteSmsqueryconditionById", results = {
			@Result(name = "success", type = "json", params = { "root", "id" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteSmsqueryconditionById() throws Exception {
		if (id == null) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		smsqueryconditionService.delete(id);
		return SUCCESS;
	}

	/**
	 * 根据ID数组删除实体对象
	 */
	@Action(value = "deleteSmsqueryconditionByIds", results = {
			@Result(name = "success", type = "json", params = { "root", "ids" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteSmsqueryconditionByIds() throws Exception {
		if (ids == null || "".equals(ids.trim())) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要删除的记录!";
			return ERROR;
		}
		smsqueryconditionService.delete(idsLong);
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 */
	@Action(value = "findSmsqueryconditionPagerBySearchVo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSmsqueryconditionPagerBySearchVo() throws Exception {
		if (null == smsquerycondition) {
			smsquerycondition = new Smsquerycondition();
		}
		gridPage = new GridPage(smsqueryconditionService.findPagerBySearchVo(
				smsquerycondition, page, rows, sidx, sord));
		return SUCCESS;
	}

	public Smsquerycondition getSmsquerycondition() {
		return smsquerycondition;
	}

	public void setSmsquerycondition(Smsquerycondition smsquerycondition) {
		this.smsquerycondition = smsquerycondition;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getFieldStr() {
		return fieldStr;
	}

	public void setFieldStr(String fieldStr) {
		this.fieldStr = fieldStr;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

}
