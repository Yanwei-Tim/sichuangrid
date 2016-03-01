package com.tianque.plugin.statistics.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationType;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.statistics.constants.TypeConstants;
import com.tianque.plugin.statistics.domain.GeneralSituation;
import com.tianque.plugin.statistics.service.GeneralSituationService;
import com.tianque.plugin.statistics.service.TaskListStatisticsService;
import com.tianque.plugin.statistics.vo.GeneralStituationSearchVo;
import com.tianque.plugin.statistics.vo.TaskListMapVo;
import com.tianque.plugin.statistics.vo.TaskListStatisticsVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.userAuth.api.PropertyDictDubboService;

@Scope("request")
@Namespace("/plugin/statistics/generalSituationManage")
@Controller("generalSituationController")
public class GeneralSituationController extends BaseAction {

	private Integer year;
	private Integer month;
	private Integer quarter;
	private Integer createDataType;
	private Integer yearType;
	private GeneralStituationSearchVo generalStituationSearchVo;
	private List<GeneralSituation> statisticList;
	private List<TaskListMapVo> mapList;
	private List<Object[]> personnelPie;
	private TaskListMapVo taskListMapVo;
	private TaskListStatisticsVo taskListStatisticsVo;
	private HighchartColumnVo highchartColumnVo;// 柱状图
	private HighchartColumnVo highchartTrendVo;// 线性图
	private String userOrgName;//当前用户的组织机构名称

	@Autowired
	private GeneralSituationService generalSituationService;
	@Autowired
	private TaskListStatisticsService taskListStatisticsService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictDubboService propertyDictDubboService;

	@Action(value = "createGeneralStituationHistory", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String createGeneralStituationHistory() throws Exception {
		if (year == null || createDataType == null) {
			errorMessage = "数据生成失败，未获得年月信息";
			return ERROR;
		}
		generalSituationService.createGeneralSituationByDate(year, month,
				quarter, createDataType, yearType);
		return SUCCESS;
	}
	
	/***
	 * 地图饼图
	 */
	@Action(value = "getPieForMap", results = {
			@Result(name = "success", type = "json", params = { "root", "personnelPie",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String getPieForMap() throws Exception {
		if (taskListStatisticsVo == null || !StringUtil.isStringAvaliable(taskListStatisticsVo.getOrgName())) {
			errorMessage = "查询失败，参数未获得";
			return ERROR;
		}
		personnelPie= taskListStatisticsService.getTaskListPieForMap(taskListStatisticsVo);
		return SUCCESS;
	}
	
	/***
	 * 根据地图ID查询签收数排名前三的组织ID
	 */
	@Action(value = "getMaxCreatSignOrgByType", results = {
			@Result(name = "success", type = "json", params = { "root", "mapList",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String getMaxCreatSignOrgByType() throws Exception {
		if (taskListStatisticsVo == null || !StringUtil.isStringAvaliable(taskListStatisticsVo.getOrgName())) {
			errorMessage = "查询失败，参数未获得";
			return ERROR;
		}
		mapList = taskListStatisticsService.getMaxCreatSignOrgByType(taskListStatisticsVo);
		return SUCCESS;
	}

	/***
	 * 地图统计数据查询
	 * @return
	 * @throws Exception
	 */
	@Action(value = "geTaskListMapStatistics", results = {
			@Result(name = "success", type = "json", params = { "root", "taskListMapVo",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String geTaskListMapStatistics() throws Exception {
		if (taskListStatisticsVo == null || !StringUtil.isStringAvaliable(taskListStatisticsVo.getOrgName())) {
			errorMessage = "查询失败，参数未获得";
			return ERROR;
		}
		taskListMapVo=taskListStatisticsService.getTaskListForMap(taskListStatisticsVo);
 		return SUCCESS;
	}

	/**
	 * 任务清单线性图统计
	 */
	@Action(value = "getTaskListOfTrend", results = {
			@Result(name = "success", type = "json", params = { "root",
					"highchartTrendVo" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getTaskListOfTrend() throws Exception {
		if (taskListStatisticsVo == null) {
			errorMessage = "查询失败，参数未获得";
			return ERROR;
		}
		highchartTrendVo = taskListStatisticsService
				.getTaskListTrend(taskListStatisticsVo);
		return SUCCESS;
	}
	
	/**
	 * 任务清单地图线性图统计
	 */
	@Action(value = "getTaskListOfMapTrend", results = {
			@Result(name = "success", type = "json", params = { "root",
					"highchartTrendVo" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getTaskListOfMapTrend() throws Exception {
		if (taskListStatisticsVo == null || !StringUtil.isStringAvaliable(taskListStatisticsVo.getOrgName())) {
			errorMessage = "查询失败，参数未获得";
			return ERROR;
		}
		
		Long orgId = getOrganizationIdByNames(taskListStatisticsVo.getOrgName());
		taskListStatisticsVo.setOrgId(orgId);
		if(taskListStatisticsVo.getYear()==null ){
			int year = CalendarUtil.getYear(CalendarUtil.getLastMonthStart(CalendarUtil.getYear(CalendarUtil.now()), CalendarUtil.getMonth(CalendarUtil.now())));
			taskListStatisticsVo.setYear(year);
		}
		if(taskListStatisticsVo.getMonth()==null ){
			int month = CalendarUtil.getMonth(CalendarUtil.getLastMonthStart(CalendarUtil.getYear(CalendarUtil.now()), CalendarUtil.getMonth(CalendarUtil.now())));
			taskListStatisticsVo.setMonth(month);
		}
		highchartTrendVo = taskListStatisticsService
				.getTaskListTrend(taskListStatisticsVo);
		return SUCCESS;
	}
	
	/**
	 * 民警、异常、隐患柱状图
	 */
	@Action(value = "getTaskListOfSubTypeColumn", results = {
			@Result(name = "success", type = "json", params = { "root",
					"highchartColumnVo" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getTaskListOfSubTypeColumn() throws Exception {
		if (taskListStatisticsVo == null) {
			errorMessage = "查询失败，参数未获得";
			return ERROR;
		}
		highchartColumnVo = taskListStatisticsService
				.getTaskListSubTypeOfColumn(taskListStatisticsVo);
		return SUCCESS;
	}

	/***
	 * 民警、异常、隐患饼状图
	 */
	@Action(value = "getTaskListOfSubTypePie", results = {
			@Result(name = "success", type = "json", params = { "root",
					"personnelPie" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getTaskListOfSubTypePie() throws Exception {
		if (taskListStatisticsVo == null) {
			errorMessage = "查询失败，参数未获得";
			return ERROR;
		}
		personnelPie = taskListStatisticsService
				.getTaskListSubTypeOfPie(taskListStatisticsVo);
		return SUCCESS;
	}

	/**
	 * 柱状图（拆分）
	 */
	@Action(value = "getTaskListColumn", results = {
			@Result(name = "success", type = "json", params = { "root",
					"highchartColumnVo" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getTaskListColumn() throws Exception {
		if (taskListStatisticsVo == null) {
			errorMessage = "查询失败，参数未获得";
			return ERROR;
		}
		highchartColumnVo = taskListStatisticsService
				.getTaskListColumn(taskListStatisticsVo);
		return SUCCESS;
	}
	

	/***
	 * 饼状图
	 */
	@Action(value = "getTaskListPie", results = {
			@Result(name = "success", type = "json", params = { "root",
					"personnelPie" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getTaskListPie() throws Exception {
		if (taskListStatisticsVo == null) {
			errorMessage = "查询失败，参数未获得";
			return ERROR;
		}
		personnelPie = taskListStatisticsService
				.getTaskListPie(taskListStatisticsVo);
		return SUCCESS;
	}

	/***
	 * 柱状图统计
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "getStatisticInfoByConditions", results = {
			@Result(name = "success", type = "json", params = { "root",
					"statisticList" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getStatisticInfoByConditions() throws Exception {
		if (generalStituationSearchVo == null) {
			errorMessage = "查询统计信息失败,未获得查询条件";
			return ERROR;
		}
		generalStituationSearchVo
				.setOrgIds(dealOrgIds(generalStituationSearchVo.getOrgStr()));
		generalSituationService.getTaskListOfPie(generalStituationSearchVo);
		// statisticList =
		// generalSituationService.getStatisticInfoByConditions(generalStituationSearchVo);
		return SUCCESS;
	}

	/***
	 * 饼状图统计
	 * 
	 * @param orgStr
	 * @return
	 */
	@Action(value = "getTaskListStatisticsPie", results = {
			@Result(name = "success", type = "json", params = { "root",
					"personnelPie" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getTaskListStatisticsPie() throws Exception {
		if (generalStituationSearchVo == null
				|| !StringUtil.isStringAvaliable(generalStituationSearchVo
						.getOrgStr())) {
			errorMessage = "查询统计信息失败,未获得查询条件";
			return ERROR;
		}
		if (generalStituationSearchVo.getBasicTypeStr() != null) {
			generalStituationSearchVo.setBasicType(generalStituationSearchVo
					.getBasicTypeStr().split(","));
		}
		if (generalStituationSearchVo.getDetailTypeStr() != null) {
			generalStituationSearchVo.setDetailType(generalStituationSearchVo
					.getDetailTypeStr().split(","));
		}
		if (generalStituationSearchVo.getBasicTypeStr() != null
				&& generalStituationSearchVo.getBasicTypeStr().equals(
						TypeConstants.HIDDENDANGGER_KEY)) {
			if (generalStituationSearchVo.getDetailTypeStr() != null) {
				generalStituationSearchVo
						.setSubType(dealSubType(generalStituationSearchVo
								.getDetailTypeStr()));
			}
		}
		generalStituationSearchVo
				.setOrgIds(dealOrgIds(generalStituationSearchVo.getOrgStr()));
		personnelPie = generalSituationService
				.getTaskListOfPie(generalStituationSearchVo);
		return SUCCESS;
	}
	
	/**
	 * 根据当前登录用户获得组织机构名称
	 * @return
	 */
	@Action(value = "getOrganizationInfoByOrgType", results = {
			@Result(name = "success", type = "json", params = { "root",
					"userOrgName" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getOrganizationInfoByOrgType() throws Exception {
		Long funcTypeId =  propertyDictDubboService
		.findPropertyDictByDomainNameAndDictDisplayName(
				OrganizationType.ORG_TYPE_KEY,
				OrganizationType.FUNCTION_KEY).getId();
		Organization org = ThreadVariable.getUser().getOrganization();
		//判断当前登录用户的组织机构是否是职能部门
		if(funcTypeId.equals(org.getOrgType().getId())){
			if(org.getParentOrg().getOrgName() == null){
				org = organizationDubboService.getFullOrgById(org.getId());
			}
			userOrgName = org.getParentOrg().getOrgName();
		}else{
			userOrgName = org.getOrgName();
		}
		if(userOrgName.equals("四川省")){
			userOrgName += ",false";
		}else{
			userOrgName += ",true";
		}
		return SUCCESS;
	}

	private Long[] dealSubType(String subStr) {
		Long[] ids = new Long[subStr.split(",").length];
		for (int i = 0; i < subStr.split(",").length; i++) {
			ids[i] = Long.parseLong(subStr.split(",")[i]);
		}
		return ids;
	}

	private List<Long> dealOrgIds(String orgStr) {
		List<Long> orgIds = new ArrayList<Long>();
		for (String orgId : orgStr.split(",")) {
			orgIds.add(Long.parseLong(orgId));
		}
		return orgIds;
	}
	
	/**
	 * 根据一个或多个组织机构名称获得组织机构Id
	 * @param orgNames 组织机构名称
	 * @return
	 */
	private Long getOrganizationIdByNames(String orgNames){
		String[] orgNameArray = orgNames.split(",");
		Long parentId = null;
		Long orgId = null;
		Organization  org = null;
		for(int i = 0;i < orgNameArray.length;i++){
			List<Organization> list = organizationDubboService.findOrganizationsByOrgNameAndOrgTypeForPage(orgNameArray[i], 1, 20);
			if(list!=null && list.size()!=0){
				parentId = list.get(0).getId();
				if(i == 0){
					orgId = list.get(0).getId();
				}
				//判断后面是否还有数据
				if(i+1 < orgNameArray.length){
					org = organizationDubboService.getOrganizationsByParentAndOrgName(parentId, orgNameArray[i+1]);
					if(org != null){
						orgId = org.getId();
					}else{
						return null;
					}
				}
			}
		}
		return orgId;
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

	public GeneralStituationSearchVo getGeneralStituationSearchVo() {
		return generalStituationSearchVo;
	}

	public void setGeneralStituationSearchVo(
			GeneralStituationSearchVo generalStituationSearchVo) {
		this.generalStituationSearchVo = generalStituationSearchVo;
	}

	public List<GeneralSituation> getStatisticList() {
		return statisticList;
	}

	public void setStatisticList(List<GeneralSituation> statisticList) {
		this.statisticList = statisticList;
	}

	public List<Object[]> getPersonnelPie() {
		return personnelPie;
	}

	public void setPersonnelPie(List<Object[]> personnelPie) {
		this.personnelPie = personnelPie;
	}

	public TaskListStatisticsVo getTaskListStatisticsVo() {
		return taskListStatisticsVo;
	}

	public void setTaskListStatisticsVo(
			TaskListStatisticsVo taskListStatisticsVo) {
		this.taskListStatisticsVo = taskListStatisticsVo;
	}

	public HighchartColumnVo getHighchartColumnVo() {
		return highchartColumnVo;
	}

	public void setHighchartColumnVo(HighchartColumnVo highchartColumnVo) {
		this.highchartColumnVo = highchartColumnVo;
	}

	public Integer getQuarter() {
		return quarter;
	}

	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}

	public Integer getCreateDataType() {
		return createDataType;
	}

	public void setCreateDataType(Integer createDataType) {
		this.createDataType = createDataType;
	}

	public HighchartColumnVo getHighchartTrendVo() {
		return highchartTrendVo;
	}

	public void setHighchartTrendVo(HighchartColumnVo highchartTrendVo) {
		this.highchartTrendVo = highchartTrendVo;
	}

	public Integer getYearType() {
		return yearType;
	}

	public void setYearType(Integer yearType) {
		this.yearType = yearType;
	}

	public TaskListMapVo getTaskListMapVo() {
		return taskListMapVo;
	}

	public void setTaskListMapVo(TaskListMapVo taskListMapVo) {
		this.taskListMapVo = taskListMapVo;
	}

	public List<TaskListMapVo> getMapList() {
		return mapList;
	}

	public void setMapList(List<TaskListMapVo> mapList) {
		this.mapList = mapList;
	}

	public String getUserOrgName() {
		return userOrgName;
	}

	public void setUserOrgName(String userOrgName) {
		this.userOrgName = userOrgName;
	}
	
	
	
}
