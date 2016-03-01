package com.tianque.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.ModuleConstants;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.StatRegradedPoint;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.ReportDateType;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.service.CommonService;
import com.tianque.service.impl.OrganizationServiceHelper;
import com.tianque.statRegrad.controller.StatRegradeAlgorithm;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@SuppressWarnings("serial")
@Controller("statRegradedPointsController")
@Scope("request")
public class StatRegradedPointsController extends SearchBaseAction {

	private int minYear;
	private int minMonth;
	private int maxYear;
	private int maxMonth;
	private Integer nowYear;
	private Integer nowMonth;
	private Integer year;
	private Integer month;
	private Long targeOrgId;
	private int internalId;
	private StatRegradedPoint statRegradedPoint;
	private PropertyDict reportDateType;
	private List monthType;

	@Autowired
	private StatRegradeAlgorithm statRegradeAlgorithm;
	@Autowired
	private CommonService commonService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private SystemLogService systemLogService;
	private String nextOrgLevelName;

	public String dispatch() throws Exception {
		minYear = commonService.getSysBeginUseYear();
		minMonth = commonService.getSysBeginUseMonth();

		Calendar endCalendar = Calendar.getInstance();
		maxYear = endCalendar.get(Calendar.YEAR);
		maxMonth = endCalendar.get(Calendar.MONTH) + 1;
		if(maxMonth == 1){
			maxYear = maxYear-1;
		}

		String nowString = CalendarUtil.format(CalendarUtil.now("yyyy-MM-dd"));
		nowYear = new Integer(nowString.split("-")[0]);
		nowMonth = new Integer(nowString.split("-")[1]);
		return SUCCESS;
	}

	public String statRegradedPoints() throws Exception {
		gridPage = new GridPage(createPageInfo());
		return SUCCESS;
	}
	
	public String createRegradedPointStatTable() throws Exception {
		statRegradeAlgorithm.createRegradedPointStatTable(year, month);
		return SUCCESS;
	}

	private PageInfo<StatRegradedPoint> createPageInfo() {
		PageInfo<StatRegradedPoint> pageInfo = new PageInfo<StatRegradedPoint>();
		pageInfo.setResult(statRegradeAlgorithm.findStatRegradedPoints(year,
				month, reportDateType, targeOrgId, internalId, sidx, sord));
		return pageInfo;
	}

	private String[][] getExcelDefinesExportArray() {
		if (1 == internalId) {
			return SpecialGroupsContext.getFunctionalRegradedPointArray();
		} else {
			return SpecialGroupsContext.getAdministrativeRegradedPointArray();
		}
	}

	private String generateExportExcelHeader() {
		PropertyDict prop = propertyDictService
				.getPropertyDictById(reportDateType.getId());
		if (null == prop) {
			return "";
		}
		if (ReportDateType.GROUPBYYEAR_KEY.equals(prop.getDisplayName())) {
			return "(" + year + "年度)";
		} else if (ReportDateType.GROUPBYMONTH_KEY
				.equals(prop.getDisplayName())) {
			return "(" + year + "年" + month + "月)";
		} else if (ReportDateType.GROUPBYQUARTER_KEY.equals(prop
				.getDisplayName())) {
			switch (month) {
			case 1:
				return "(" + year + "年" + "第一季度)";
			case 4:
				return "(" + year + "年" + "第二季度)";
			case 7:
				return "(" + year + "年" + "第三季度)";
			case 10:
				return "(" + year + "年" + "第四季度)";
			default:
				return "";
			}
		}
		return "";
	}

	@Override
	public List getNeedExportDatas(int page) {
		// 该功能没有区分分页，故直接采取查询的结果集
		List data = createPageInfo().getResult();
		return data;
	}

	// @PermissionFilters(value = {
	// @PermissionFilter(ename = "downAdministrativeRegradedPoints"),
	// @PermissionFilter(ename = "downFunctionalRegradedPoints") })
	public String downloadRegradedPoints() throws Exception {
		if (null == targeOrgId) {
			errorMessage = "无法定位需要导出的数据";
			return ERROR;
		}
		List<StatRegradedPoint> statRegradedPoints = getNeedExportDatas(page);
		String[][] excelDefines = getExcelDefinesExportArray();
		excelDefines[0][2] += generateExportExcelHeader();
		inputStream = ExcelExportHelper.exportDateToExcel(excelDefines,
				statRegradedPoints);
		downloadFileName = new String(excelDefines[0][2].getBytes("gbk"),
				"ISO8859-1") + ".xls";
		systemLogService
				.log(com.tianque.core.logger.Logger.INFO,
						ModuleConstants.PERFORMANCE_APPRAISAL,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												targeOrgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId())
								+ excelDefines[0][2], ObjectToJSON
								.convertJSON(new HouseholdStaff()));
		return STREAM_SUCCESS;
	}

	public void downloadExcelExportAll() throws Exception {
		if (targeOrgId == null) {
			errorMessage = "无法定位需要导出的数据";
			return;
		}
		String[][] excelDefines = getExcelDefinesExportArray();
		excelDefines[0][2] += generateExportExcelHeader();
		exportDataAll(rows, excelDefines, excelDefines[0][2]);
		systemLogService
				.log(com.tianque.core.logger.Logger.INFO,
						ModuleConstants.PERFORMANCE_APPRAISAL,
						OperatorType.EXPORT,
						ThreadVariable.getSession().getUserName()
								+ "在"
								+ organizationDubboService
										.getOrganizationRelativeNameByRootOrgIdAndOrgId(
												targeOrgId,
												OrganizationServiceHelper
														.getRootOrg(
																organizationDubboService)
														.getId())
								+ excelDefines[0][2], ObjectToJSON
								.convertJSON(new HouseholdStaff()));
	}

	public String nextOrgLevelNameByOrgId() throws Exception {
		Organization organization = organizationDubboService
				.getSimpleOrgById(targeOrgId);
		organization.setOrgLevel(propertyDictService
				.getPropertyDictById(organization.getOrgLevel().getId()));
		if (organization.getOrgLevel().getInternalId() == OrganizationLevel.COUNTRY) {
			nextOrgLevelName = "全国";
		} else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.PROVINCE) {
			nextOrgLevelName = "省级";
		} else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.CITY) {
			nextOrgLevelName = "市级";
		} else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.DISTRICT) {
			nextOrgLevelName = "县区级";
		} else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
			nextOrgLevelName = "乡镇";
		} else if (organization.getOrgLevel().getInternalId() == OrganizationLevel.VILLAGE) {
			nextOrgLevelName = "村(社区)";
		} else {
			nextOrgLevelName = "片格";
		}
		return SUCCESS;
	}

	public String getMonthTypeList() throws Exception {
		PropertyDict prop = propertyDictService
				.getPropertyDictById(reportDateType.getId());
		monthType = new ArrayList<String>();
		if (null == prop
				|| ReportDateType.GROUPBYYEAR_KEY.equals(prop.getDisplayName())) {

		} else if (ReportDateType.GROUPBYMONTH_KEY
				.equals(prop.getDisplayName())) {
			Calendar calendar = Calendar.getInstance();
			int month = 0;
			if (year == calendar.get(Calendar.YEAR)) {
				month = calendar.get(Calendar.MONTH);
			} else {
				month = 12;
			}
			for (int i = month; i > 0; i--) {
				monthType.add(String.valueOf(i));
			}
		} else if (ReportDateType.GROUPBYQUARTER_KEY.equals(prop
				.getDisplayName())) {
			Calendar calendar = Calendar.getInstance();
			int quarter = 0;
			if (year == calendar.get(Calendar.YEAR)) {
				quarter = calendar.get(Calendar.MONTH) / 3 + 1;
			} else {
				quarter = 4;
			}
			for (int i = quarter; i >= 1; i--) {
				monthType.add("第" + i + "季度");
			}

		}
		return SUCCESS;
	}
	
	public String getLastMonthTypeList() throws Exception {
		PropertyDict prop = propertyDictService
				.getPropertyDictById(reportDateType.getId());
		monthType = new ArrayList<String>();
		if (null == prop
				|| ReportDateType.GROUPBYYEAR_KEY.equals(prop.getDisplayName())) {

		} else if (ReportDateType.GROUPBYMONTH_KEY
				.equals(prop.getDisplayName())) {
			Calendar calendar = Calendar.getInstance();
			int month = 0;
			if (year == calendar.get(Calendar.YEAR)) {
				month = calendar.get(Calendar.MONTH);
			} else {
				month = 12;
			}
			for (int i = month; i > 0; i--) {
				monthType.add(String.valueOf(i));
			}
		} else if (ReportDateType.GROUPBYQUARTER_KEY.equals(prop
				.getDisplayName())) {
			Calendar calendar = Calendar.getInstance();
			int quarter = 0;
			if (year == calendar.get(Calendar.YEAR)) {
				quarter = (calendar.get(Calendar.MONTH)-1) / 3 + 1;
			} else {
				quarter = 4;
			}
			for (int i = quarter; i >= 1; i--) {
				monthType.add("第" + i + "季度");
			}

		}
		return SUCCESS;
	}

	public Integer getNowYear() {
		return nowYear;
	}

	public void setNowYear(Integer nowYear) {
		this.nowYear = nowYear;
	}

	public Integer getNowMonth() {
		return nowMonth;
	}

	public void setNowMonth(Integer nowMonth) {
		this.nowMonth = nowMonth;
	}

	public int getMinYear() {
		return minYear;
	}

	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}

	public int getMinMonth() {
		return minMonth;
	}

	public void setMinMonth(int minMonth) {
		this.minMonth = minMonth;
	}

	public int getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}

	public int getMaxMonth() {
		return maxMonth;
	}

	public void setMaxMonth(int maxMonth) {
		this.maxMonth = maxMonth;
	}

	public Long getTargeOrgId() {
		return targeOrgId;
	}

	public void setTargeOrgId(Long targeOrgId) {
		this.targeOrgId = targeOrgId;
	}

	public int getInternalId() {
		return internalId;
	}

	public void setInternalId(int internalId) {
		this.internalId = internalId;
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

	public StatRegradedPoint getStatRegradedPoint() {
		return statRegradedPoint;
	}

	public void setStatRegradedPoint(StatRegradedPoint statRegradedPoint) {
		this.statRegradedPoint = statRegradedPoint;
	}

	public String getNextOrgLevelName() {
		return nextOrgLevelName;
	}

	public void setNextOrgLevelName(String nextOrgLevelName) {
		this.nextOrgLevelName = nextOrgLevelName;
	}

	public PropertyDict getReoprtDateType() {
		return reportDateType;
	}

	public void setReoprtDateType(PropertyDict reoprtDateType) {
		this.reportDateType = reoprtDateType;
	}

	public List getMonthType() {
		return monthType;
	}

	public void setMonthType(List monthType) {
		this.monthType = monthType;
	}

}
