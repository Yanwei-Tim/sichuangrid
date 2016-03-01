package com.tianque.workBench.patelConfiger.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.service.util.PatelConfiger;
import com.tianque.service.util.TabPatel;
import com.tianque.workBench.patelConfiger.service.PatelService;

@Namespace("/patel/patelManage")
@Transactional
@Scope("prototype")
@Controller("patelController")
public class PatelController extends BaseAction {

	@Autowired
	private PatelService patelService;
	private String tabKeyName;
	private List<PatelConfiger> configers;
	private String keyName;
	private List<TabPatel> tabPatels;
	private String keyNames;

	private List<String> configerskeyNames;

	@Action(value = "getPatelConfiger", results = {
			@Result(name = "success", location = "/workBench/common/list.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getPatelConfiger() {
		configers = patelService.getPatelConfiger();
		return SUCCESS;
	}

	@Action(value = "getPatelConfigerKeyName", results = {
			@Result(name = "success", location = "/workBench/common/list.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getPatelConfigerKeyName() {
		configerskeyNames = patelService.getPatelConfigerKeyName();
		return SUCCESS;
	}

	@Action(value = "buildConfiguration", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String buildConfiguration() {
		if (DialogMode.ADD_MODE.equals(mode)) {
			patelService.addConfiguration(keyName);
		} else if (DialogMode.DEL_MODE.equals(mode)) {
			patelService.deleteConfiguration(keyName);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			patelService.updateConfigurationIndex(keyNames.split(","));
		}
		return SUCCESS;
	}

	@Action(value = "getTabPatelConfiger", results = {
			@Result(name = "success", location = "/workBench/common/tabsList.jsp"),
			@Result(name = "informationTrain", location = "/workBench/common/informationTrain.jsp"),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String getTabPatelConfiger() {
		tabPatels = patelService.getTabConfiger(keyName);
		if (PatelConfiger.INFORMATIONTRAIN.equals(keyName)) {
			return "informationTrain";
		}
		return SUCCESS;
	}

	@Action(value = "buildTabConfiguration", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String buildTabConfiguration() {
		if (DialogMode.ADD_MODE.equals(mode)) {
			patelService.addTabConfiguration(keyName, tabKeyName);
		} else if (DialogMode.DEL_MODE.equals(mode)) {
			patelService.deleteTabConfiguration(keyName, tabKeyName);
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			patelService.updateTabConfigurationIndex(keyName, keyNames.split(","));
		}
		return SUCCESS;
	}

	public List<PatelConfiger> getConfigers() {
		return configers;
	}

	public void setConfigers(List<PatelConfiger> configers) {
		this.configers = configers;
	}

	public String getTabKeyName() {
		return tabKeyName;
	}

	public void setTabKeyName(String tabKeyName) {
		this.tabKeyName = tabKeyName;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public List<TabPatel> getTabPatels() {
		return tabPatels;
	}

	public void setTabPatels(List<TabPatel> tabPatels) {
		this.tabPatels = tabPatels;
	}

	public String getKeyNames() {
		return keyNames;
	}

	public void setKeyNames(String keyNames) {
		this.keyNames = keyNames;
	}

	public List<String> getConfigerskeyNames() {
		return configerskeyNames;
	}

	public void setConfigerskeyNames(List<String> configerskeyNames) {
		this.configerskeyNames = configerskeyNames;
	}

}
