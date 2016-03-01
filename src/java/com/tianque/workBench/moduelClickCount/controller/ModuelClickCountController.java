package com.tianque.workBench.moduelClickCount.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.userAuth.api.ModuelClickCountDubboService;
import com.tianque.workBench.moduelClickCount.domain.ModuelClick;

@Namespace("/workBench/moduelClickCount")
@Transactional
@Scope("request")
@Controller("moduelClickCountController")
public class ModuelClickCountController extends BaseAction {
	@Autowired
	private ModuelClickCountDubboService moduelClickCountDubboService;
	private List<ModuelClick> moduelClicks;

	@Actions({
			@Action(value = "findMyFeaturesByUserId", results = {
					@Result(type = "json", name = "success", params = { "root",
							"gridPage", "ignoreHierarchy", "false" }),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }),
			@Action(value = "findModuelClickCountByUserId", results = {
					@Result(name = "success", location = "/workBench/common/informationTrain.jsp"),
					@Result(name = "error", type = "json", params = { "root",
							"errorMessage" }) }) })
	public String findModuelClickCountByUserId() throws Exception {
		Long userId = ThreadVariable.getSession().getUserId();
		moduelClicks = moduelClickCountDubboService
				.findModuelClickCountByUserId(userId, 8);
		return SUCCESS;
	}

	public List<ModuelClick> getModuelClicks() {
		return moduelClicks;
	}

	public void setModuelClicks(List<ModuelClick> moduelClicks) {
		this.moduelClicks = moduelClicks;
	}

}
