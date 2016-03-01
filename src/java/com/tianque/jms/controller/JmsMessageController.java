package com.tianque.jms.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.jms.domain.JmsMessage;
import com.tianque.jms.service.JmsMessageService;

@SuppressWarnings("serial")
@Controller("jmsMessageController")
@Namespace("/sysadmin/jmsMessageManager")
@Scope("prototype")
public class JmsMessageController extends BaseAction{
	
	@Autowired
	private JmsMessageService jmsMessageService;
	
	private List<JmsMessage> jmsMessages;
	private JmsMessage jmsMessage;
	private Long jmsMessageId;
	
	@Action(value = "queryJmsMessageList", results = {
			@Result(name = "success", location = "/sysadmin/sysGlobalSetting/maintainJmsMessage.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String queryJmsMessageList() throws Exception{
		jmsMessages = jmsMessageService.queryJmsMessageForList();
		return SUCCESS;
	}
	
	@Action(value = "maintainJmsMessage", results = { @Result(name = "success", type = "json", params = {
			"root", "jmsMessage" }) })
	public String maintainJmsMessage() throws Exception{
		if(jmsMessage.getId() == null){
			jmsMessage = jmsMessageService.addJmsMessage(jmsMessage);
		}else{
			jmsMessage = jmsMessageService.updateJmsMessage(jmsMessage);
		}
		return SUCCESS;
	}
	
	@Action(value = "deleteJmsMessage", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteJmsMessage() throws Exception{
		if(jmsMessageId == null){
			return SUCCESS;
		}
		jmsMessageService.deleteJmsMessage(jmsMessageId);
		return SUCCESS;
	}

	public List<JmsMessage> getJmsMessages() {
		return jmsMessages;
	}

	public void setJmsMessages(List<JmsMessage> jmsMessages) {
		this.jmsMessages = jmsMessages;
	}

	public JmsMessage getJmsMessage() {
		return jmsMessage;
	}

	public void setJmsMessage(JmsMessage jmsMessage) {
		this.jmsMessage = jmsMessage;
	}

	public Long getJmsMessageId() {
		return jmsMessageId;
	}

	public void setJmsMessageId(Long jmsMessageId) {
		this.jmsMessageId = jmsMessageId;
	}

}
