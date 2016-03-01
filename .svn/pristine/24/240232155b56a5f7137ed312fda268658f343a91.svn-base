package com.tianque.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;

@Controller("dispatchController")
@Scope("prototype")
public class DispatchController extends BaseAction {

	private String requestType;
	private String url;

	public String dispatch() {
		if (url == null) {
			errorMessage = "跳转地址不能为空!";
			return ERROR;
		}
		url += "?sessionId=" + ThreadVariable.getSession().getSessionId();
		if (requestType != null)
			url += "&requestType=" + requestType;
		return SUCCESS;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

}
