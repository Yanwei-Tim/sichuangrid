package com.tianque.plugin.analyzing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.plugin.analyzing.domain.PrimaryOrganizationDataColumnTwoVo;
import com.tianque.plugin.analyzing.service.SearchPrimaryOrganizationDataColumnService;

/**
 * 基层队伍统计
 */
@Transactional
@Scope("prototype")
@Controller("searchPrimaryOrganizationDataColumnController")
public class SearchPrimaryOrganizationDataColumnController extends BaseAction {
	private static final long serialVersionUID = 4830838554497222672L;

	@Autowired
	private SearchPrimaryOrganizationDataColumnService searchPrimaryOrganizationDataColumnService;

	List<PrimaryOrganizationDataColumnTwoVo> listDataColumnVo;

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
		listDataColumnVo = searchPrimaryOrganizationDataColumnService
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

	public List<PrimaryOrganizationDataColumnTwoVo> getListDataColumnVo() {
		if (null == listDataColumnVo) {
			listDataColumnVo = new ArrayList<PrimaryOrganizationDataColumnTwoVo>();
		}
		return listDataColumnVo;
	}

	public void setListDataColumnVo(
			List<PrimaryOrganizationDataColumnTwoVo> listDataColumnVo) {
		this.listDataColumnVo = listDataColumnVo;
	}

	public String getOrgIntroduce() {
		return orgIntroduce;
	}

	public void setOrgIntroduce(String orgIntroduce) {
		this.orgIntroduce = orgIntroduce;
	}

}
