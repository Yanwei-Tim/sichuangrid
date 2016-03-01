package com.tianque.plugin.analysisNew.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.plugin.analysisNew.domain.PrimaryOrganizationDataColumnTwoNewVo;
import com.tianque.plugin.analysisNew.service.SearchPrimaryOrganizationDataColumnNewService;

/**
 * 基层队伍统计
 */
@Transactional
@Scope("prototype")
@Controller("searchPrimaryOrganizationDataColumnNewController")
public class SearchPrimaryOrganizationDataColumnNewController extends
		BaseAction {
	private static final long serialVersionUID = 4830838554497222672L;

	@Autowired
	private SearchPrimaryOrganizationDataColumnNewService searchPrimaryOrganizationDataColumnNewService;

	// List<PrimaryOrganizationDataColumnVo> listDataColumnVo = null;

	List<PrimaryOrganizationDataColumnTwoNewVo> listDataColumnVo;

	private Long parentOrgId;

	private String orgIntroduce;

	/**
	 * 获取统计数据
	 * 
	 * @return
	 */
	public String getDataColumn() {
		if (parentOrgId == null) {
			errorMessage = "父类组织机构出错";
			return ERROR;
		}
		listDataColumnVo = searchPrimaryOrganizationDataColumnNewService
				.getPrimaryOrganizationDataColumnVoList(parentOrgId);
		return SUCCESS;
	}

	public String print() {
		return SUCCESS;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	// public List<PrimaryOrganizationDataColumnVo> getListDataColumnVo() {
	// if (null == listDataColumnVo) {
	// listDataColumnVo = new ArrayList<PrimaryOrganizationDataColumnVo>();
	// }
	// return listDataColumnVo;
	// }
	//
	// public void setListDataColumnVo(
	// List<PrimaryOrganizationDataColumnVo> listDataColumnVo) {
	// this.listDataColumnVo = listDataColumnVo;
	// }

	public String getOrgIntroduce() {
		return orgIntroduce;
	}

	public List<PrimaryOrganizationDataColumnTwoNewVo> getListDataColumnVo() {
		if (null == listDataColumnVo) {
			listDataColumnVo = new ArrayList<PrimaryOrganizationDataColumnTwoNewVo>();
		}
		return listDataColumnVo;
	}

	public void setListDataColumnVo(
			List<PrimaryOrganizationDataColumnTwoNewVo> listDataColumnVo) {
		this.listDataColumnVo = listDataColumnVo;
	}

	public void setOrgIntroduce(String orgIntroduce) {
		this.orgIntroduce = orgIntroduce;
	}

}
