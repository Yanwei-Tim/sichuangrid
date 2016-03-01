package com.tianque.workBench.tableConfig.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Role;
import com.tianque.domain.property.WorkBenchType;
import com.tianque.service.util.TabPatel;
import com.tianque.service.util.WorkBenchTabConfiger;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.workBench.tableConfig.service.TableService;

@Namespace("/workBence/tableConfigManage")
@Transactional
@Scope("prototype")
@Controller("tableController")
public class TableController extends BaseAction {

	@Autowired
	private TableService tableService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private PropertyDictService propertyDictService;
	private String tabKeyName;
	private String keyName;
	int switchover = 1;
	private List<TabPatel> tabPatels;
	private String personalWorkBenchType;

	@Action(value = "getWorkBenchTabPatelConfiger", results = {
			@Result(name = "success", location = "/workBench/common/tabsList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getWorkBenchTabPatelConfiger() throws Exception {
		PropertyDict workBenchType = getWorkBenchType();
		Map<String, WorkBenchTabConfiger> map = WorkBenchTabConfiger.allWorkBenchTabConfiger;
		for (String o : map.keySet()) {
			String cName = map.get(o).getCname();
			if (workBenchType.getDisplayName().equals(cName)) {
				keyName = map.get(o).getEname();
				if (ThreadVariable.getUser().getIsFristWorkBench()) {
					// 如果是用户却换模式 不需要 更新 第一次登陆字段
					if (switchover != 0) {
						init(map.get(o).getTabPatels());
						permissionService.initWorkBench(ThreadVariable
								.getUser().getId(), false);
					}
				}
			}
		}
		tabPatels = tableService.getTabConfiger(keyName);
		return SUCCESS;
	}

	@Action(value = "getMyAttention", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getMyAttention() throws Exception {
		if (ThreadVariable.getUser() != null
				&& ThreadVariable.getUser().isAdmin()) {
			personalWorkBenchType = "super";
		} else {
			PropertyDict workBenchType = getWorkBenchType();
			if (workBenchType.getDisplayName().equals(
					WorkBenchType.LOWER_LEVEL_NAME)) {
				personalWorkBenchType = "lower";
			} else if (workBenchType.getDisplayName().equals(
					WorkBenchType.MIDDLE_LEVEL_NAME)) {
				personalWorkBenchType = "middle";
			} else {
				personalWorkBenchType = "super";
			}
		}
		return SUCCESS;
	}

	private void init(ArrayList<TabPatel> tabPatels) {
		tableService.addConfiguration(keyName);
		for (TabPatel tabPatel : tabPatels) {
			tableService.addTabConfiguration(keyName, tabPatel.getKeyName());
		}
	}

	private PropertyDict getWorkBenchType() {
		PropertyDict workBenchType = new PropertyDict();
		if (ThreadVariable.getUser() != null
				&& ThreadVariable.getUser().isAdmin()) {
			workBenchType.setInternalId(WorkBenchType.SUPER_LEVEL);
			workBenchType.setDisplayName(WorkBenchType.SUPER_LEVEL_NAME);
		} else {
			List<Role> roles = permissionService
					.findRolesByUserId(ThreadVariable.getSession().getUserId());

			Integer maxWorkBenchType = 2;
			Role maxRole = null;
			for (Role role : roles) {
				if (role.getWorkBenchType() == null) {
					workBenchType.setInternalId(WorkBenchType.SUPER_LEVEL);
					workBenchType
							.setDisplayName(WorkBenchType.SUPER_LEVEL_NAME);
					role.setWorkBenchType(workBenchType);
					maxRole = role;
				} else {
					role.setWorkBenchType(propertyDictService
							.getPropertyDictById(role.getWorkBenchType()
									.getId()));
					Integer tempWorkBenchType = role.getWorkBenchType()
							.getInternalId();
					if (maxRole != null) {
						maxWorkBenchType = maxRole.getWorkBenchType()
								.getInternalId();
					}
					if (tempWorkBenchType <= maxWorkBenchType) {
						maxRole = role;
					}
				}
			}
			workBenchType = maxRole.getWorkBenchType();
		}
		return workBenchType;
	}

	public int getSwitchover() {
		return switchover;
	}

	public void setSwitchover(int switchover) {
		this.switchover = switchover;
	}

	public String getTabKeyName() {
		return tabKeyName;
	}

	public void setTabKeyName(String tabKeyName) {
		this.tabKeyName = tabKeyName;
	}

	public List<TabPatel> getTabPatels() {
		return tabPatels;
	}

	public void setTabPatels(List<TabPatel> tabPatels) {
		this.tabPatels = tabPatels;
	}

	public String getPersonalWorkBenchType() {
		return personalWorkBenchType;
	}

	public void setPersonalWorkBenchType(String personalWorkBenchType) {
		this.personalWorkBenchType = personalWorkBenchType;
	}
}
