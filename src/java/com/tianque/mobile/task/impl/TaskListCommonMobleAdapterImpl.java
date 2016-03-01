package com.tianque.mobile.task.impl;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.task.TaskListCommonMobleAdapter;
import com.tianque.plugin.taskList.constants.Constants;
import com.tianque.plugin.taskList.controller.TaskListCommonController;
import com.tianque.plugin.taskList.domain.Receipt;
import com.tianque.sysadmin.service.PropertyDictService;

@Controller("taskListCommonMobleAdapter")
@Namespace("/mobile/taskListCommonManage")
@Scope("request")
public class TaskListCommonMobleAdapterImpl extends BaseMobileAction implements
		TaskListCommonMobleAdapter {
	@Autowired
	private TaskListCommonController taskListCommonController;
	@Autowired
	private PropertyDictService propertyDictService;

	/** 当前系统时间 **/
	private Date nowDate;
	/** 签收信息 **/
	private Receipt receipt;
	/** 标记是否可签收 **/
	private Boolean flag;
	/** 派出所还是卫生所签收 **/
	private String signType;

	@Override
	@Action(value = "getNowDate", results = {
			@Result(name = "success", type = "json", params = { "root",
					"nowDate" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getSystemNowDate() throws Exception {
		taskListCommonController.getNowDate();
		nowDate = taskListCommonController.getNowDate();
		return SUCCESS;
	}

	@Override
	@Action(value = "signRecord", results = {
			@Result(name = "success", type = "json", params = { "root", "true" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String signRecord() throws Exception {
		taskListCommonController.setReceipt(receipt);
		taskListCommonController.setSignType(signType);
		taskListCommonController.signRecord();
		return SUCCESS;
	}

	@Override
	@Action(value = "checkJusticeDepartment", results = {
			@Result(name = "success", type = "json", params = { "root", "flag" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String checkJusticeDepartment() throws Exception {
		flag = false;
		/** 获取 职能部门类型--司法部门 属性字典信息 **/
		PropertyDict functionalDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.FUNCTIONAL_ORG_TYPE,
						Constants.JUSTICE_DEPARTMENT);
		/** 获取组织机构层级 网格分级--乡镇（街道） 属性字典信息 **/
		PropertyDict orgLevelDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.TOWN_KEY);
		/** 获取当前用户信息 **/
		User user = ThreadVariable.getUser();
		/** 获取当前用户职能部门类型  **/
		Long functionalOrgTypeDictId = 0L;
		if(user != null && user.getOrganization() != null 
				&& user.getOrganization().getFunctionalOrgType() != null){
			functionalOrgTypeDictId = user.getOrganization().getFunctionalOrgType().getId();
		}
		/** 获取当前组织结构层级属性字典  **/
		Long orgLevelDictId = 0L;
		if(user != null && user.getOrganization() != null 
				&& user.getOrganization().getOrgLevel() != null){
			orgLevelDictId = user.getOrganization().getOrgLevel().getId();
		}
		
		if(functionalDict != null && functionalDict.getId() != null
				&& orgLevelDict != null && orgLevelDict.getId() != null){
			if(functionalDict.getId().equals(functionalOrgTypeDictId)
					&& orgLevelDict.getId().equals(orgLevelDictId)){
				flag = true;
			}
		}
		return SUCCESS;
	}
	
	@Override
	@Action(value = "checkPoliceDepartment", results = {
			@Result(name = "success", type = "json", params = { "root", "flag" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String checkPoliceDepartment() throws Exception {
		flag = false;
		/** 获取 职能部门类型--公安部门 属性字典信息 **/
		PropertyDict functionalDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.FUNCTIONAL_ORG_TYPE,
						Constants.POLICE_DEPARTMENT);
		/** 获取组织机构层级 网格分级--乡镇（街道） 属性字典信息 **/
		PropertyDict orgLevelDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.ORGANIZATION_LEVEL,
						OrganizationLevel.TOWN_KEY);
		/** 获取当前用户信息 **/
		User user = ThreadVariable.getUser();
		/** 获取当前用户职能部门类型  **/
		Long functionalOrgTypeDictId = 0L;
		if(user != null && user.getOrganization() != null 
				&& user.getOrganization().getFunctionalOrgType() != null){
			functionalOrgTypeDictId = user.getOrganization().getFunctionalOrgType().getId();
		}
		/** 获取当前组织结构层级属性字典  **/
		Long orgLevelDictId = 0L;
		if(user != null && user.getOrganization() != null 
				&& user.getOrganization().getOrgLevel() != null){
			orgLevelDictId = user.getOrganization().getOrgLevel().getId();
		}
		
		if(functionalDict != null && functionalDict.getId() != null
				&& orgLevelDict != null && orgLevelDict.getId() != null){
			if(functionalDict.getId().equals(functionalOrgTypeDictId)
					&& orgLevelDict.getId().equals(orgLevelDictId)){
				flag = true;
			}
		}
		return SUCCESS;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

}
