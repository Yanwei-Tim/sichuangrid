package com.tianque.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.service.PopulationTypeService;

@Namespace("/baseinfo/populationTypeManage")
@Transactional
@Scope("request")
@Controller("populationTypeController")
public class PopulationTypeController extends BaseAction {

	@Autowired
	private PopulationTypeService populationTypeService;

	private Long populationId;
	private String populationType;
	private PopulationTypeBean populationTypeBean;
	private List<PopulationTypeBean> populationTypeBeanList;
	private String orgInternalCode;

	@Action(value = "getPopulationTypeByPopulationIdAndType", results = { @Result(name = "success", type = "json", params = {
			"root", "populationTypeBean", "ignoreHierarchy", "false", "excludeNullProperties",
			"true" }) })
	public String getPopulationTypeByPopulationIdAndType() {
		populationTypeBean = populationTypeService.getPopulationTypeByPopulationIdAndType(
				populationId, populationType,orgInternalCode);
		return SUCCESS;
	}

	@Action(value = "getPopulationTypeByActualIdAndType", results = { @Result(name = "success", type = "json", params = {
			"root", "populationTypeBeanList", "ignoreHierarchy", "false", "excludeNullProperties",
			"true" }) })
	public String getPopulationTypeByActualIdAndType() {
		populationTypeBeanList = populationTypeService.getPopulationTypeByActualIdAndType(
				populationId, populationType,orgInternalCode);
		return SUCCESS;
	}

	public Long getPopulationId() {
		return populationId;
	}

	public void setPopulationId(Long populationId) {
		this.populationId = populationId;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public PopulationTypeBean getPopulationTypeBean() {
		return populationTypeBean;
	}

	public void setPopulationTypeBean(PopulationTypeBean populationTypeBean) {
		this.populationTypeBean = populationTypeBean;
	}

	public List<PopulationTypeBean> getPopulationTypeBeanList() {
		return populationTypeBeanList;
	}

	public void setPopulationTypeBeanList(List<PopulationTypeBean> populationTypeBeanList) {
		this.populationTypeBeanList = populationTypeBeanList;
	}

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

}
