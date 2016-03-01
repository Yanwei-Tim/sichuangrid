package com.tianque.plugin.analyzing.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.newSocietyOrganizations.service.NewSocietyOrganitionsService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;
import com.tianque.plugin.analyzing.dao.BaseInfoStatisticDao;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.PopulationAreaDataVo;
import com.tianque.plugin.analyzing.service.BaseinfoStatisticService;
import com.tianque.plugin.analyzing.service.StatisticsPlaceService;
import com.tianque.plugin.analyzing.service.StatisticsPopulationService;
import com.tianque.service.EnterpriseService;
import com.tianque.service.OperateLogService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 实有人口研判分析 update 2013/07/26
 * 
 * @author lss
 */
@SuppressWarnings("serial")
@Controller("statisticsPopulationController")
@Scope("prototype")
public class StatisticsPopulationController extends BaseAction {
	// private static Logger logger = LoggerFactory
	// .getLogger(StatisticsPopulationController.class);
	@Autowired
	private StatisticsPopulationService statisticsPopulationService;
	@Autowired
	private StatisticsPlaceService statisticsPlaceService;
	@Autowired
	private BaseInfoStatisticDao baseInfoStatisticsDao;
	@Autowired
	private BaseinfoStatisticService baseinfoStatisticService;
	@Autowired
	private EnterpriseService enterpriseService;
	@Autowired
	private NewSocietyOrganitionsService newSocietyOrganizationsService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private OperateLogService operateLogService;
	private List<PopulationAreaDataVo> populationAreaData;
	private HighchartColumnVo personnelColumn;
	private Long orgId;
	private String departmentNo;
	private List<Object[]> personnelPie;
	private int year;
	private int month;
	private String populationType;

	private String changeInfo;

	private Map<String, Integer> query;
	private Integer orgLevelDistance;

	public String createStatisticsPopulationList() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			this.errorMessage = "所查月份数据尚未生产！";
			return ERROR;
		}
		statisticsPopulationService.addpopulationStat(year, month, orgId,
				populationType);
		return SUCCESS;
	}

	public String getStatisticsPopulationList() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}

		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			this.errorMessage = "所查月份数据尚未生产！";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == (Calendar.getInstance().get(Calendar.MONTH) + 1)) {
			populationAreaData = statisticsPopulationService
					.getCurrentAreaDate(orgId, populationType, orgLevelDistance);
		} else {
			populationAreaData = statisticsPopulationService.getAreaDateByDate(
					orgId, populationType, year, month, orgLevelDistance);
		}

		return SUCCESS;
	}

	public String getStatisticsPopulationColumn() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > Calendar.getInstance().get(Calendar.MONTH) + 1) {
			return ERROR;
		}

		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month == Calendar.getInstance().get(Calendar.MONTH) + 1) {
			if (populationType == null || "".equals(populationType)
					|| BaseInfoTables.POPULATION_KEY.equals(populationType)) {
				personnelColumn = statisticsPopulationService
						.getPopulationColumnByOrgId(orgId);
			} else {
				personnelColumn = statisticsPopulationService
						.getPopulationColumnByOrgIdAndType(orgId,
								populationType);
			}

		} else {
			// 加载历史数据
			personnelColumn = statisticsPopulationService
					.getPopulationColumnByTime(orgId, populationType, year,
							month);
		}
		return SUCCESS;

	}

	public String getStatisticsPopulationPie() throws Exception {
		if (orgId == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		if (year == Calendar.getInstance().get(Calendar.YEAR)
				&& month > Calendar.getInstance().get(Calendar.MONTH) + 1) {
			return ERROR;
		}

		personnelPie = statisticsPopulationService.getPopulationPieInfo(year,
				month, orgId, populationType);
		return SUCCESS;
	}

	/***
	 * 海宁首页地图展示信息
	 * 
	 * @return
	 */
	public String getInhabitantCountAndFamilyCountAndImportantPersonelByOrgIdOrHaiNing()
			throws Exception {

		if ("".equals(departmentNo) || departmentNo == null) {
			this.errorMessage = "部门编号错误!";
			return ERROR;
		}
		Organization organization = organizationDubboService
				.getOrgByDepartmentNo(departmentNo);
		if (organization == null) {
			this.errorMessage = "不存在该组织!";
			return ERROR;
		}
		orgId = organization.getId();

		query = new HashMap<String, Integer>();
		int inhabitantCount = 0;
		int floatingPopulation = 0;
		int importantPersonel = 0;
		int importantPlaceCount = 0;
		int lettingHouseCount = 0;
		int enterpriseCount = 0;
		int newSocietyFederationCount = 0;
		// 查询得到数据集合
		inhabitantCount = statisticsPopulationService.getCountByOrgId(orgId,
				"householdStaff");
		floatingPopulation = statisticsPopulationService.getCountByOrgId(orgId,
				"floatingPopulation");
		importantPersonel = baseinfoStatisticService
				.getImportPersonCount(orgId);
		importantPlaceCount = statisticsPlaceService
				.getImportantPlaceCountByOrgId(orgId);
		lettingHouseCount = baseinfoStatisticService
				.getrentalHouseCountByTypeAndId("rentalHouse", orgId);
		enterpriseCount = enterpriseService
				.getEnterpriseCountByOrgIdAndKeyType(orgId, "enterpriseKey");
		newSocietyFederationCount = newSocietyOrganizationsService
				.getNewSocietyOrganizationsCountByOrgId(orgId);

		query.put("inhabitantCount", inhabitantCount);
		query.put("trampResidentCount", floatingPopulation);
		query.put("importantPersonel", importantPersonel);
		query.put("importantPlaceCount", importantPlaceCount);
		query.put("lettingHouseCount", lettingHouseCount);
		query.put("enterpriseCount", enterpriseCount);
		query.put("newSocietyFederationCount", newSocietyFederationCount);

		return SUCCESS;
	}

	public String getChangeDate() throws Exception {
		List<Integer> list = new ArrayList<Integer>();
		list.add(OperatorType.ADD);
		list.add(OperatorType.DELETE);
		list.add(OperatorType.LOGOUT);
		list.add(OperatorType.CANCELLOGOUT);
		Map<String, Integer> map = null;
		if (populationType == null || "".equals(populationType)) {
			map = operateLogService.statisticsAllOperate(orgId, list,
					"POPULATION", year, month);
		} else {
			map = operateLogService.statisticsAllOperateByTableName(orgId,
					list, populationType, year, month);
		}
		changeInfo = "";
		for (Integer operate : list) {
			changeInfo = changeInfo + OperatorType.toString(operate) + " "
					+ map.get(OperatorType.toString(operate)) + " 人           ";
		}
		return SUCCESS;
	}

	public List<PopulationAreaDataVo> getPopulationAreaData() {
		return populationAreaData;
	}

	public void setPopulationAreaData(
			List<PopulationAreaDataVo> populationAreaData) {
		this.populationAreaData = populationAreaData;
	}

	public HighchartColumnVo getPersonnelColumn() {
		return personnelColumn;
	}

	public void setPersonnelColumn(HighchartColumnVo personnelColumn) {
		this.personnelColumn = personnelColumn;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public List<Object[]> getPersonnelPie() {
		return personnelPie;
	}

	public void setPersonnelPie(List<Object[]> personnelPie) {
		this.personnelPie = personnelPie;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public String getChangeInfo() {
		return changeInfo;
	}

	public void setChangeInfo(String changeInfo) {
		this.changeInfo = changeInfo;
	}

	public Map<String, Integer> getQuery() {
		return query;
	}

	public void setQuery(Map<String, Integer> query) {
		this.query = query;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public Integer getOrgLevelDistance() {
		return orgLevelDistance;
	}

	public void setOrgLevelDistance(Integer orgLevelDistance) {
		this.orgLevelDistance = orgLevelDistance;
	}

}
