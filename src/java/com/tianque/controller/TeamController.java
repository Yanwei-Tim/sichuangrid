package com.tianque.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;

@Namespace("/team/teamManage")
@Scope("request")
@Controller("teamController")
public class TeamController extends BaseAction {

	@Action(value = "findTeamInfoForHomePage", results = {
			// @Result(name = "success", location =
			// "/baseinfo/partyConstruction/PartyConstructionFiles/partyConstructionFilesDlg.jsp"),
			@Result(name = "success", type = "json", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findTeamInfoForHomePage() {
		gridPage = new GridPage();
		return SUCCESS;
	}

}
