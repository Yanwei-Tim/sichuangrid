package com.tianque.mobile.commonMethod.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.floatingPopulation.controller.FloatingPopulationController;
import com.tianque.baseInfo.householdStaff.controller.HouseholdStaffController;
import com.tianque.controller.vo.FullReportVo;
import com.tianque.controller.vo.HouseholdStaffVo;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.vo.SearchFloatingPopulationVo;
import com.tianque.issue.controller.IssueController;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.commonMethod.CommonMethodMobileAdapter;
import com.tianque.plugin.analyzing.controller.BaseinfoStatisticController;
import com.tianque.plugin.analyzing.controller.LeaderViewController;
import com.tianque.plugin.analyzing.controller.OrgLoginStanalsController;
import com.tianque.plugin.analyzing.domain.BaseinfoStatisticVo;
import com.tianque.statAnalyse.issueManage.listManage.controller.IssueReportController;
import com.tianque.statAnalyse.issueManage.listManage.domain.IssueAreaStat;

/**
 * 
 * @ClassName: CommonMethodMobileAdapterImpl
 * @Description: TODO手机端 领导视图公用方法
 * @author wanggz
 * @date 2014-7-9 下午03:30:20
 */

@Transactional
@Scope("request")
@Controller("commonMethodMobileAdapter")
@Namespace("/mobile/commonMethodManage")
public class CommonMethodMobileAdapterImpl extends BaseMobileAction implements
		CommonMethodMobileAdapter {

	@Autowired
	private HouseholdStaffController householdStaffController;

	@Autowired
	private FloatingPopulationController floatingPopulationController;

	@Autowired
	private IssueController issueController;

	@Autowired
	private IssueReportController issueReportController;

	@Autowired
	private OrgLoginStanalsController orgLoginStanalsController;

	@Autowired
	private LeaderViewController leaderViewController;
	@Autowired
	private BaseinfoStatisticController baseinfoStatisticController;

	private Long orgId;
	private HouseholdStaffVo householdStaffVo;
	private SearchFloatingPopulationVo searchFloatingPopulationVo;
	/** 事件处理记录实体类 */
	private IssueLogNew issueLogNew;
	/** 根据操作不同 可能是事件id、orgid、事件处理步骤id(issueStepId) */
	private Long keyId;
	/** 事件处理操作类型代码， 具体含义定义在IssueOperate中 */
	private int dealCode;

	private Integer year;
	private Integer month;
	private Long parentOrgId;
	private Integer queryType;
	private PropertyDict reportDateType;
	private List<IssueAreaStat> issueAreaStats;
	private List<IssueAreaStat> issueAreaStatsForMobile;
	private Integer internalId;
	private FullReportVo fullReportVo;
	private String tableType;
	private String domainName;

	private List<BaseinfoStatisticVo> statisticList;

	/**
	 * 实有人口 快速搜索
	 */
	@Override
	@Action(value = "householdStaffFastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String householdStaffFastSearch() throws Exception {
		if (orgId == null || rows == null || page == null || sidx == null
				|| sidx.equals("") || sord == null || sord.equals("")) {
			errorMessage = "请确认必传参数是否正确完善！";// 异常直接抛给手机端，明确提示
			return ERROR;
		}
		if (householdStaffVo == null) {
			householdStaffVo = new HouseholdStaffVo();
		}
		householdStaffController.setRows(rows);
		householdStaffController.setPage(page);
		householdStaffController.setSidx(sidx);
		householdStaffController.setSord(sord);
		householdStaffController.setOrgId(orgId);
		householdStaffController.setHouseholdStaffVo(householdStaffVo);
		householdStaffController.fastSearch();
		gridPage = householdStaffController.getGridPage();
		return SUCCESS;
	}

	@Override
	@Action(value = "floatingPopulationFastSearch", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String floatingPopulationFastSearch() throws Exception {
		if (orgId == null || rows == null || page == null || sidx == null
				|| sidx.equals("") || sord == null || sord.equals("")) {
			errorMessage = "请确认必传参数是否正确完善！";
			return ERROR;
		}
		if (searchFloatingPopulationVo == null) {
			searchFloatingPopulationVo = new SearchFloatingPopulationVo();
		}
		floatingPopulationController.setRows(rows);
		floatingPopulationController.setPage(page);
		floatingPopulationController.setSidx(sidx);
		floatingPopulationController.setSord(sord);
		floatingPopulationController.setOrganizationId(orgId);
		floatingPopulationController
				.setSearchFloatingPopulationVo(searchFloatingPopulationVo);
		floatingPopulationController.fastSearchFloatingPopulation();
		gridPage = floatingPopulationController.getGridPage();
		return SUCCESS;
	}

	/**
	 * 事件 加急
	 */
	@Override
	@Action(value = "dealIssueForUrgent", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dealIssueForUrgent() throws Exception {
		if (issueLogNew == null || issueLogNew.getDealOrg() == null
				|| issueLogNew.getDealOrg().getId() == null
				|| issueLogNew.getIssue() == null
				|| issueLogNew.getIssue().getId() == null || keyId == null
				|| issueLogNew.getDealUserName() == null
				|| "".equals(issueLogNew.getDealUserName())
				|| issueLogNew.getMobile() == null
				|| "".equals(issueLogNew.getMobile())
				|| issueLogNew.getContent() == null
				|| "".equals(issueLogNew.getContent())) {
			errorMessage = "请确认必传参数是否正确完善！";
			return ERROR;
		}
		issueController.setOperation(issueLogNew);
		issueController.setDealCode(dealCode);
		issueController.setKeyId(keyId);
		issueController.deal();
		return SUCCESS;
	}

	/**
	 * 事件 取消加急
	 */
	@Override
	@Action(value = "cancelDaalIssueForUrgent", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String cancelDaalIssueForUrgent() throws Exception {
		if (keyId == null) {
			errorMessage = "请确认必传参数是否正确完善！";
			return ERROR;
		}
		issueController.setDealCode(dealCode);
		issueController.setKeyId(keyId);
		issueController.deal();
		return SUCCESS;
	}

	/**
	 * 事件 督办
	 */
	@Override
	@Action(value = "dealIssueForSupervise", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dealIssueForSupervise() throws Exception {
		if (issueLogNew == null || issueLogNew.getDealOrg() == null
				|| issueLogNew.getDealOrg().getId() == null
				|| issueLogNew.getIssue() == null
				|| issueLogNew.getIssue().getId() == null || keyId == null
				|| issueLogNew.getDealUserName() == null
				|| "".equals(issueLogNew.getDealUserName())
				|| issueLogNew.getMobile() == null
				|| "".equals(issueLogNew.getMobile())
				|| issueLogNew.getContent() == null
				|| "".equals(issueLogNew.getContent())) {
			errorMessage = "请确认必传参数是否正确完善！";
			return ERROR;
		}
		issueController.setOperation(issueLogNew);
		issueController.setDealCode(dealCode);
		issueController.setKeyId(keyId);
		issueController.deal();
		return SUCCESS;
	}

	/**
	 * 事件 取消督办
	 */
	@Override
	@Action(value = "cancelDealIssueForSupervise", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String cancelDealIssueForSupervise() throws Exception {
		if (keyId == null) {
			errorMessage = "请确认必传参数是否正确完善！";
			return ERROR;
		}
		issueController.setDealCode(dealCode);
		issueController.setKeyId(keyId);
		issueController.deal();
		return SUCCESS;
	}

	/**
	 * 领导批示
	 */
	@Override
	@Action(value = "leaderInstruct", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String leaderInstruct() throws Exception {
		if (issueLogNew == null || issueLogNew.getDealOrg() == null
				|| issueLogNew.getDealOrg().getId() == null
				|| issueLogNew.getIssue() == null
				|| issueLogNew.getIssue().getId() == null || keyId == null
				|| issueLogNew.getDealUserName() == null
				|| "".equals(issueLogNew.getDealUserName())
				|| issueLogNew.getMobile() == null
				|| "".equals(issueLogNew.getMobile())
				|| issueLogNew.getContent() == null
				|| "".equals(issueLogNew.getContent())) {
			errorMessage = "请确认必传参数是否正确完善！";
			return ERROR;
		}
		issueController.setOperation(issueLogNew);
		issueController.setDealCode(dealCode);
		issueController.setKeyId(keyId);
		issueController.deal();
		return SUCCESS;
	}

	/**
	 * 按区域统计事件
	 */
	@Override
	@Action(value = "getDataColumnByAreaForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"issueAreaStatsForMobile", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getDataColumnByAreaForMobile() throws Exception {
		if (parentOrgId == null || year == null || month == null
				|| reportDateType == null || reportDateType.getId() == null) {
			errorMessage = "请确认必传参数是否正确完善！";
			return ERROR;
		}
		issueReportController.setParentOrgId(parentOrgId);
		issueReportController.setYear(year);
		issueReportController.setMonth(month);
		issueReportController.setReportDateType(reportDateType);
		issueReportController.setQueryType(queryType);
		issueReportController.getDataColumnByArea();
		issueAreaStats = issueReportController.getIssueAreaStats();
		issueAreaStatsForMobile = new ArrayList<IssueAreaStat>();
		for (IssueAreaStat ias : issueAreaStats) {
			IssueAreaStat iasForMobile = new IssueAreaStat();
			iasForMobile.setAddIssueSum(ias.getAddIssueSum());
			iasForMobile.setDoingIssueSum(ias.getDoingIssueSum());
			iasForMobile.setCompletionRate(ias.getCompletionRate());
			iasForMobile.setOrganization(ias.getOrganization());
			issueAreaStatsForMobile.add(iasForMobile);
		}
		return SUCCESS;
	}

	@Override
	@Action(value = "findOrgLoginStanalsByOrgIdForListPage", results = {
			@Result(name = "success", type = "json", params = { "root",
					"fullReportVo", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findOrgLoginStanalsByOrgIdForListPage() throws Exception {
		if (orgId == null || year == null || month == null) {
			errorMessage = "请确认必传参数是否正确完善！";
			return ERROR;
		}
		orgLoginStanalsController.setOrgId(orgId);
		orgLoginStanalsController.setNowYear(year);
		orgLoginStanalsController.setNowMonth(month);
		orgLoginStanalsController.setInternalId(internalId);
		orgLoginStanalsController.findOrgLoginStanalsByOrgIdForListPage();
		fullReportVo = orgLoginStanalsController.getFullReportVo();
		List list = new ArrayList();
		for (String columnVo : fullReportVo.getColumnCaption()) {
			if (columnVo != null) {
				list.add(columnVo);
			}
		}
		fullReportVo.setColumnCaption((String[]) list.toArray(new String[] {}));
		return SUCCESS;
	}

	/**
	 * 实有人口总况
	 */
	@Override
	@Action(value = "personGeneralConditionForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String personGeneralConditionForMobile() throws Exception {
		if (orgId == null || tableType == null || "".equals(tableType)
				|| rows == null || page == null || sidx == null
				|| sidx.equals("") || sord == null || sord.equals("")) {
			errorMessage = "请确认必传参数是否正确完善！";
			return ERROR;
		}
		leaderViewController.setOrgId(orgId);
		leaderViewController.setTableType(tableType);
		leaderViewController.setRows(rows);
		leaderViewController.setPage(page);
		leaderViewController.setSidx(sidx);
		leaderViewController.setSord(sord);
		leaderViewController.personGeneralCondition();
		gridPage = leaderViewController.getGridPage();
		return SUCCESS;
	}

	@Override
	@Action(value = "placeGeneralConditionForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String placeGeneralConditionForMobile() throws Exception {
		if (orgId == null || tableType == null || "".equals(tableType)
				|| rows == null || page == null || sidx == null
				|| sidx.equals("") || sord == null || sord.equals("")) {
			errorMessage = "请确认必传参数是否正确完善！";
			return ERROR;
		}
		leaderViewController.setOrgId(orgId);
		leaderViewController.setTableType(tableType);
		leaderViewController.setRows(rows);
		leaderViewController.setPage(page);
		leaderViewController.setSidx(sidx);
		leaderViewController.setSord(sord);
		leaderViewController.personGeneralCondition();
		gridPage = leaderViewController.getGridPage();
		return SUCCESS;
	}

	/**
	 * 实有房屋 总况
	 */
	@Override
	@Action(value = "houseConditionForMobile", results = {
			@Result(name = "success", type = "json", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String houseConditionForMobile() throws Exception {
		if (orgId == null || tableType == null || "".equals(tableType)
				|| rows == null || page == null || sidx == null
				|| sidx.equals("") || sord == null || sord.equals("")) {
			errorMessage = "请确认必传参数是否正确完善！";
			return ERROR;
		}
		leaderViewController.setOrgId(orgId);
		leaderViewController.setTableName(tableType);
		leaderViewController.setRows(rows);
		leaderViewController.setPage(page);
		leaderViewController.setSidx(sidx);
		leaderViewController.setSord(sord);
		leaderViewController.statisticsBaseInfo();
		gridPage = leaderViewController.getGridPage();
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: getBaseInfoStatisticList
	 * @Description: TODO手机端获取特殊人群研判分析接口
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-12-3 下午02:15:08
	 */
	@Override
	@Action(value = "getBaseInfoStatisticList", results = {
			@Result(name = "success", type = "json", params = { "root",
					"statisticList", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getBaseInfoStatisticList() throws Exception {
		if (orgId == null || year == null || month == null
				|| !StringUtil.isStringAvaliable(tableType)) {
			errorMessage = "请确认必传参数是否正确完善！";
			return ERROR;
		}
		if (StringUtil.isStringAvaliable(tableType)) {
			if (!StringUtil.isStringAvaliable(domainName)) {
				errorMessage = "请确认必传参数是否正确完善！";
				return ERROR;
			} else {
				baseinfoStatisticController.setDomainName(domainName);
			}
		}
		baseinfoStatisticController.setOrgId(orgId);
		baseinfoStatisticController.setYear(year);
		baseinfoStatisticController.setMonth(month);
		baseinfoStatisticController.setType(tableType);
		baseinfoStatisticController.getBaseInfoStatisticList();
		statisticList = baseinfoStatisticController.getStatisticList();
		return SUCCESS;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public HouseholdStaffVo getHouseholdStaffVo() {
		return householdStaffVo;
	}

	public void setHouseholdStaffVo(HouseholdStaffVo householdStaffVo) {
		this.householdStaffVo = householdStaffVo;
	}

	public SearchFloatingPopulationVo getSearchFloatingPopulationVo() {
		return searchFloatingPopulationVo;
	}

	public void setSearchFloatingPopulationVo(
			SearchFloatingPopulationVo searchFloatingPopulationVo) {
		this.searchFloatingPopulationVo = searchFloatingPopulationVo;
	}

	public IssueLogNew getIssueLogNew() {
		return issueLogNew;
	}

	public void setIssueLogNew(IssueLogNew issueLogNew) {
		this.issueLogNew = issueLogNew;
	}

	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public int getDealCode() {
		return dealCode;
	}

	public void setDealCode(int dealCode) {
		this.dealCode = dealCode;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public Integer getQueryType() {
		return queryType;
	}

	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
	}

	public PropertyDict getReportDateType() {
		return reportDateType;
	}

	public void setReportDateType(PropertyDict reportDateType) {
		this.reportDateType = reportDateType;
	}

	public List<IssueAreaStat> getIssueAreaStats() {
		return issueAreaStats;
	}

	public void setIssueAreaStats(List<IssueAreaStat> issueAreaStats) {
		this.issueAreaStats = issueAreaStats;
	}

	public List<IssueAreaStat> getIssueAreaStatsForMobile() {
		return issueAreaStatsForMobile;
	}

	public void setIssueAreaStatsForMobile(
			List<IssueAreaStat> issueAreaStatsForMobile) {
		this.issueAreaStatsForMobile = issueAreaStatsForMobile;
	}

	public Integer getInternalId() {
		return internalId;
	}

	public void setInternalId(Integer internalId) {
		this.internalId = internalId;
	}

	public FullReportVo getFullReportVo() {
		return fullReportVo;
	}

	public void setFullReportVo(FullReportVo fullReportVo) {
		this.fullReportVo = fullReportVo;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public List<BaseinfoStatisticVo> getStatisticList() {
		return statisticList;
	}

	public void setStatisticList(List<BaseinfoStatisticVo> statisticList) {
		this.statisticList = statisticList;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

}
