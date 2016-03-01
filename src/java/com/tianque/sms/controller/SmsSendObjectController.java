package com.tianque.sms.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.sms.domain.SmsSendObject;
import com.tianque.sms.domain.Smsquerycondition;
import com.tianque.sms.domain.vo.SmsSendObjectVo;
import com.tianque.sms.service.SmsSendObjectService;
import com.tianque.sms.service.SmsqueryconditionService;

/**
 * 发送对象:服务前端控制类
 */
@Namespace("/smsSendObjectManage")
@Scope("request")
@Controller("smsSendObjectController")
public class SmsSendObjectController extends BaseAction {

	@Autowired
	private SmsSendObjectService smsSendObjectService;
	@Autowired
	private SmsqueryconditionService smsqueryconditionService;

	private SmsSendObject smsSendObject;
	private SmsSendObjectVo smsSendObjectVo;
	private Long id;
	private String ids;
	private boolean bol;
	private String dialogName;

	/**
	 * 发送对象操作分发
	 */
	@Actions({ @Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/interaction/newSMS/sendObjectManagement/sendObjectViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String dispatchOperate() throws Exception {
		if ("add".equals(mode)) {

		} else if ("update".equals(mode) || "view".equals(mode)) {
			smsSendObject = smsSendObjectService.get(id);
			createSmsSendObjectVo(smsSendObject);
		}
		return SUCCESS;
	}

	/**
	 * 保存实体对象
	 */
	@Action(value = "addSmsSendObject", results = {
			@Result(name = "success", type = "json", params = { "root",
					"smsSendObject", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "addSmsSendObject")
	public String addSmsSendObject() throws Exception {
		if (smsSendObject == null) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		smsSendObject = smsSendObjectService.add(smsSendObject);
		if (smsSendObject == null) {
			this.errorMessage = "操作失败，请联系管理员！";
			return ERROR;
		}
		createSmsSendObjectVo(smsSendObject);
		return SUCCESS;
	}

	@Action(value = "deleteSmsSendObjectById", results = {
			@Result(name = "success", type = "json", params = { "root", "bol" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "deleteSmsSendObject")
	public String deleteSmsSendObjectById() throws Exception {
		if (!StringUtil.isStringAvaliable(ids)) {
			this.errorMessage = "参数无效!";
			return ERROR;
		}
		String[] strIds = ids.split(",");
		Long[] longIds = new Long[strIds.length];
		for (int i = 0; i < strIds.length; i++) {
			if (StringUtil.isStringAvaliable(strIds[i])) {
				longIds[i] = Long.valueOf(strIds[i]);
			}
		}
		smsSendObjectService.delete(longIds);
		bol = true;
		return SUCCESS;
	}

	/**
	 * 更新发送对象信息
	 */
	@Action(value = "updateSmsSendObject", results = {
			@Result(name = "success", type = "json", params = { "root",
					"smsSendObject", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@PermissionFilter(ename = "updateSmsSendObject")
	public String updateSmsSendObject() throws Exception {
		if (null == smsSendObject || null == smsSendObject.getId()) {
			this.errorMessage = "参数无效！";
			return ERROR;
		}
		smsSendObject = smsSendObjectService.update(smsSendObject);
		if (smsSendObject == null) {
			this.errorMessage = "操作失败，请联系管理员！";
			return ERROR;
		}
		createSmsSendObjectVo(smsSendObject);
		return SUCCESS;
	}

	/**
	 * 更改发送对象是否启用
	 */
	@Action(value = "enableSmsSendObject", results = {
			@Result(name = "success", type = "json", params = { "root",
					"smsSendObject", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String enableSmsSendObject() throws Exception {
		if (null == id) {
			this.errorMessage = "参数无效！";
			return ERROR;
		}
		smsSendObject = smsSendObjectService.get(id);
		smsSendObject.setEnable(bol);
		smsSendObject = smsSendObjectService.update(smsSendObject);
		return SUCCESS;
	}

	/**
	 * 根据SearchVo进行查询(提供分页、查找、排序功能)
	 */
	@Action(value = "findSmsSendObjectPager", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findSmsSendObjectPager() throws Exception {
		if (null == smsSendObject) {
			smsSendObject = new SmsSendObject();
		}
		gridPage = new GridPage(smsSendObjectService.findPagerBySearchVo(
				smsSendObject, page, rows, sidx, sord));
		return SUCCESS;
	}

	private void createSmsSendObjectVo(SmsSendObject sendObj) {
		List<Smsquerycondition> sqclist = smsqueryconditionService
				.findSmsqueryconditionsBySmsSendObjectId(sendObj.getId());
		smsSendObjectVo = new SmsSendObjectVo();
		smsSendObjectVo.setId(sendObj.getId());
		smsSendObjectVo.setName(sendObj.getName());
		smsSendObjectVo.setDescription(sendObj.getDescription());
		smsSendObjectVo.setSqcList(sqclist);
		setSmsSendObjectVo(smsSendObjectVo);
	}

	public SmsSendObject getSmsSendObject() {
		return smsSendObject;
	}

	public void setSmsSendObject(SmsSendObject smsSendObject) {
		this.smsSendObject = smsSendObject;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public SmsSendObjectVo getSmsSendObjectVo() {
		return smsSendObjectVo;
	}

	public void setSmsSendObjectVo(SmsSendObjectVo smsSendObjectVo) {
		this.smsSendObjectVo = smsSendObjectVo;
	}

	public String getDialogName() {
		return dialogName;
	}

	public void setDialogName(String dialogName) {
		this.dialogName = dialogName;
	}

}
