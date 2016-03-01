package com.tianque.sysadmin.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.sysadmin.service.ShardTableService;

@Namespace("/sysadmin/shardTable")
@Controller("shardTableController")
@Scope("prototype")
public class ShardTableController extends BaseAction {
	@Autowired
	private ShardTableService shardTableService;

	private boolean state;

	@Action(value = "createShardTables", results = { @Result(name = "success", type = "json", params = {
			"root", "state", "ignoreHierarchy", "false" }) })
	public String createShardTables() {
		state = shardTableService.createShardTables();
		return SUCCESS;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean getState() {
		return state;
	}
}
