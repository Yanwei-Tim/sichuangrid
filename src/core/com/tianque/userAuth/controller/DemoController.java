package com.tianque.userAuth.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.tianque.domain.User;
import com.tianque.userAuth.api.DemoDubboService;

@Namespace("/demo")
@Scope("prototype")
@Controller("demoController")
public class DemoController extends ActionSupport {

	@Autowired
	private DemoDubboService demoDubboService;

	@Action(value = "demo", results = { @Result(location = "/demo.jsp", name = SUCCESS) })
	public String demo() {

		User user = demoDubboService.keep(new User());
		System.out.println(user.getName());

		return SUCCESS;
	}
}
