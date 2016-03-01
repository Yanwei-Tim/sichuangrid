package com.tianque.plugin.analysisNew.controller;

import java.util.Calendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.domain.Organization;
import com.tianque.plugin.analysisNew.domain.DepartmentPartyColumnVo;
import com.tianque.plugin.analysisNew.service.DepartmentPartyNewService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("request")
@Namespace("/baseInfo/departmentPartyManageNew")
@Controller("departmentPartyNewController")
public class DepartmentPartyNewController extends StatisticAction {
	private static Logger logger = LoggerFactory
			.getLogger(DepartmentPartyNewController.class);

	@Autowired
	private DepartmentPartyNewService departmentPartyNewService;
	// 原来的dubboService包下的
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private List<DepartmentPartyColumnVo> listDataColumnVo;
	/** 列表图用于区分显示那个图 */
	private Integer orgLevelDistance;

	/***
	 * 组织机构-党委部门报表数据
	 */
	@Action(value = "departmentPartyStatisticList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"listDataColumnVo" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String departmentPartyStatisticList() throws Exception {
		if (validateOtherDate()) {
			this.errorMessage = "所选时间数据统计失败";
			return ERROR;
		}
		listDataColumnVo = departmentPartyNewService
				.queryDepartmentPartyDataByYearMonthColumnVoForList(orgId,
						year, month, orgLevelDistance);
		setListDataColumnVo(listDataColumnVo);

		return SUCCESS;
	}

	/***
	 * 生成报表数据
	 * 
	 * @return
	 */
	@Action(value = "createHistoryStatistic", results = {
			@Result(name = "success", type = "json", params = { "root",
					"success" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String createHistoryStatistic() throws Exception {
		Organization organization = organizationDubboService
				.getSimpleOrgById(orgId);
		if (organization == null || organization.getOrgInternalCode() == null) {
			this.errorMessage = "操作失败，请重试";
			return ERROR;
		}
		departmentPartyNewService.createHistoryStatisticByType(year, month,
				type, organization.getOrgInternalCode());

		return SUCCESS;
	}

	/***
	 * 验证所统计的时间段是否大于当前月
	 * 
	 * @return
	 */
	private boolean validateOtherDate() {
		return year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	public List<DepartmentPartyColumnVo> getListDataColumnVo() {
		return listDataColumnVo;
	}

	public void setListDataColumnVo(
			List<DepartmentPartyColumnVo> listDataColumnVo) {
		this.listDataColumnVo = listDataColumnVo;
	}

	public Integer getOrgLevelDistance() {
		return orgLevelDistance;
	}

	public void setOrgLevelDistance(Integer orgLevelDistance) {
		this.orgLevelDistance = orgLevelDistance;
	}

}
