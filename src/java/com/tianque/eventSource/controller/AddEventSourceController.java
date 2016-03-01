package com.tianque.eventSource.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.eventSource.domain.EventSource;
import com.tianque.eventSource.service.EventSourceService;

@Namespace("/commandCenterManage")
@Transactional
@Scope("request")
@Controller("addEventSourceController")
public class AddEventSourceController extends BaseAction {
	private EventSource eventSource;
	@Autowired
	private EventSourceService eventSourceService;

	@Action(value = "addEventSource", results = { @Result(name = "success", type = "json", params = {
			"root", "true", "ignoreHierarchy", "false" }) })
	public String addEventSource() {
		eventSourceService.addEventSource(eventSource);
		return SUCCESS;
	}

	public EventSource getEventSource() {
		return eventSource;
	}

	public void setEventSource(EventSource eventSource) {
		this.eventSource = eventSource;
	}

}
