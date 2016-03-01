package com.tianque.plugin.weChat.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.weChat.service.SmsSendGroupService;

/**
 * 三级响应：短信响应
 * @ClassName: SmsResponseController 
 * @author: he.simin
 * @date: 2015年11月6日 下午5:03:11
 */
@Namespace("/smsResponseManage")
@Scope("prototype")
@Controller
public class SmsSendCallBackController extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(SmsSendCallBackController.class);
	@Autowired
	private SmsSendGroupService smsSendGroupService;
	private String name;
	private String pwd;
	private String sendid;
	private String time;
	private String mobile;
	private String state;

	@Action(value = "smsSendCallBack", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root", "false" }) })
	public String smsSendCallBack() {
		try {
			boolean res = smsSendGroupService.smsSendCallBack(name, pwd, sendid, time, mobile,
					state);
			if (res) {
				return SUCCESS;
			} else {
				return ERROR;
			}
		} catch (BusinessValidationException e) {
			logger.error("短信结果回调通知错误【name=" + name + "】", e.getMessage());
			return ERROR;
		} catch (Exception e) {
			logger.error("短信结果回调通知错误【name=" + name + "】", e);
			return ERROR;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSendid() {
		return sendid;
	}

	public void setSendid(String sendid) {
		this.sendid = sendid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
