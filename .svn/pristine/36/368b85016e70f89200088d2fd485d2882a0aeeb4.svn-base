package com.tianque.plugin.analysisNew.controller;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.plugin.analysisNew.service.CompanyPlaceLeaderViewNewService;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;

@Controller("companyPlaceNewController")
@Namespace("/baseinfo/companyPlaceManageNew")
@Scope("request")
public class CompanyPlaceNewController extends BaseAction {
	private Long orgId;
	private String moduleKey;

	@Autowired
	private CompanyPlaceLeaderViewNewService companyPlaceLeaderViewService;

	/**
	 * 
	 * @Title: statisticsBaseInfo
	 * @Description: TODO单位场所 领导视图
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-8-12 下午02:28:56
	 */
	@Actions({ @Action(value = "statisticsBaseInfo", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String statisticsBaseInfo() throws Exception {
		PageInfo<StatisticsBaseInfoAddCountVo> pageInfo = new PageInfo<StatisticsBaseInfoAddCountVo>();
		if (orgId == null) {
			gridPage = new GridPage(pageInfo.emptyPage(rows));
			return SUCCESS;
		}
		List<StatisticsBaseInfoAddCountVo> datas = companyPlaceLeaderViewService
				.statisticsBaseInfo(orgId, moduleKey);
		pageInfo.setResult(datas);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: statisticsSummary
	 * @Description: TODO领导视图 线形图
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-8-12 下午03:12:32
	 */
	@Actions({ @Action(value = "statisticsSummary", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String statisticsSummary() throws Exception {
		PageInfo<StatisticsBaseInfoAddCountVo> pageInfo = new PageInfo<StatisticsBaseInfoAddCountVo>();
		if (orgId == null) {
			gridPage = new GridPage(pageInfo.emptyPage(rows));
			return SUCCESS;
		}
		List<StatisticsBaseInfoAddCountVo> datas = companyPlaceLeaderViewService
				.statisticsSummary(orgId, moduleKey);
		pageInfo.setResult(datas);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getModuleKey() {
		return moduleKey;
	}

	public void setModuleKey(String moduleKey) {
		this.moduleKey = moduleKey;
	}

}
