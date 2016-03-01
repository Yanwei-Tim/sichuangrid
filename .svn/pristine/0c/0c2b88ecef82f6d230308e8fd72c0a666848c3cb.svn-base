package com.tianque.workBench.myVisitRecord.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationType;
import com.tianque.workBench.myVisitRecord.domain.MyVisitRecord;
import com.tianque.workBench.myVisitRecord.service.MyVisitRecordService;

@Namespace("/workBench/myVisitRecordManage")
@Transactional
@Scope("request")
@Controller("myVisitRecordManageController")
public class MyVisitRecordManageController extends BaseAction {
	private String personType;
	private List<MyVisitRecord> myVisits;

	@Autowired
	private MyVisitRecordService myVisitRecordService;

	/**
	 * 查找我的走访数据用于显示
	 */
	@Action(value = "findMyVisitRecordByPersonType", results = {
			@Result(name = "success", location = "/workBench/module/workBench-centre/myVisitRecordDetail.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findMyVisitRecordByPersonType() throws Exception {
		if (personType != null) {
			myVisits = myVisitRecordService.findMyVisitRecordByPersonType(
					getUserCode(),personType);
			return SUCCESS;
		} else {
			errorMessage = "人员类型获取失败";
			return ERROR;
		}
	}

	/**
	 * 查找我的走访数据用于显示
	 */
	@Action(value = "findMyVisitRecordForBigTypeByOrgInternalCode", results = {
			@Result(name = "success", location = "/workBench/module/workBench-centre/myVisitRecord.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findMyVisitRecordForBigTypeByOrgInternalCode()
			throws Exception {
		// String orgCode =
		// ThreadVariable.getSession().getOrgInternalCode();
		
		myVisits = myVisitRecordService
				.findMyVisitRecordForBigTypeByOrgInternalCode(getUserCode());
		return SUCCESS;
	}

	private String getUserCode(){
		String orgCode = "";
		//得到当前用户信息
		User user = ThreadVariable.getUser();
		//判断用户是职能部门用户
		if(OrganizationType.FUNCTIONAL_ORG==user.getOrganization().getOrgType().getInternalId()){
			//如果是职能部门用户则查询职能部门所属的行政部门组织机构CODE
			orgCode = user.getOrganization().getParentOrg().getOrgInternalCode();
		}else{
			orgCode = ThreadVariable.getSession().getOrgInternalCode();
		}
		return orgCode;
	}
	
	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public List<MyVisitRecord> getMyVisits() {
		return myVisits;
	}

	public void setMyVisits(List<MyVisitRecord> myVisits) {
		this.myVisits = myVisits;
	}

}
