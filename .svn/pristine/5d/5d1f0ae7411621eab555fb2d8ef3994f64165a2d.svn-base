package com.tianque.init.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.DailyDirectoryTypes;
import com.tianque.domain.property.DirectoryReportType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.StatementsReportedType;
import com.tianque.init.Initialization;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyYearService;

public class DailyDirectoryInitialization implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private DailyYearService dailyYearService;
	private DailyDirectoryService dailyDirectoryService;
	private PropertyDictService propertyDirctDictService;

	public DailyDirectoryInitialization(DailyYearService dailyYearService,
			DailyDirectoryService dailyDirectoryService,
			PropertyDictService propertyDirctDictService) {
		this.dailyDirectoryService = dailyDirectoryService;
		this.dailyYearService = dailyYearService;
		this.propertyDirctDictService = propertyDirctDictService;
	}

	@Override
	public void init() throws Exception {
		DailyYear dailyYear = addDailyYear("初始模版");
		PropertyDict statementsReportedType = propertyDirctDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.DAILYDIRECTORY_TYPE,
						DailyDirectoryTypes.STATEMENTS_REPORTED_NAME);
		PropertyDict reportedType1 = propertyDirctDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.STATEMENTS_REPORTED_TYPE,
						StatementsReportedType.CHECK_NAME);
		PropertyDict reportedType2 = propertyDirctDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.STATEMENTS_REPORTED_TYPE,
						StatementsReportedType.ISSUEDEAL_NAME);
		PropertyDict reportedType3 = propertyDirctDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.STATEMENTS_REPORTED_TYPE,
						StatementsReportedType.INVESTIGATION_NAME);
		PropertyDict reportedType4 = propertyDirctDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.STATEMENTS_REPORTED_TYPE,
						StatementsReportedType.SIGNIFICANT_ISSUEDEAL_NAME);
		PropertyDict reportedType5 = propertyDirctDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.STATEMENTS_REPORTED_TYPE,
						StatementsReportedType.INVESTIGATION_REMEDIATION_NAME);

		// PropertyDict societyType = propertyDirctDictService
		// .findPropertyDictByDomainNameAndDictDisplayName(
		// PropertyTypes.DAILYDIRECTORY_TYPE,
		// DailyDirectoryTypes.SOCIETY_SECURITY_NAME);
		// PropertyDict securityType = propertyDirctDictService
		// .findPropertyDictByDomainNameAndDictDisplayName(
		// PropertyTypes.DAILYDIRECTORY_TYPE,
		// DailyDirectoryTypes.SECURITY_PROPAGANDA_NAME);
		// PropertyDict seriesType = propertyDirctDictService
		// .findPropertyDictByDomainNameAndDictDisplayName(
		// PropertyTypes.DAILYDIRECTORY_TYPE,
		// DailyDirectoryTypes.SERIES_SECURITY_NAME);
		// PropertyDict serviceType = propertyDirctDictService
		// .findPropertyDictByDomainNameAndDictDisplayName(
		// PropertyTypes.DAILYDIRECTORY_TYPE,
		// DailyDirectoryTypes.SERVICE_MANAGEMENT_NAME);
		// PropertyDict gridType = propertyDirctDictService
		// .findPropertyDictByDomainNameAndDictDisplayName(
		// PropertyTypes.DAILYDIRECTORY_TYPE,
		// DailyDirectoryTypes.GRID_MANAGEMENT_NORMAL_NAME);
		//
		// PropertyDict serviceCityType = propertyDirctDictService
		// .findPropertyDictByDomainNameAndDictDisplayName(
		// DailyDirectoryTypes.SERVICE_MANAGEMENT_NAME,
		// DailyDirectoryTypes.SERVICE_MANAGEMENT_CITY_NAME);
		// PropertyDict serviceTownType = propertyDirctDictService
		// .findPropertyDictByDomainNameAndDictDisplayName(
		// DailyDirectoryTypes.SERVICE_MANAGEMENT_NAME,
		// DailyDirectoryTypes.SERVICE_MANAGEMENT_TOWN_NAME);
		// PropertyDict serviceVillageType = propertyDirctDictService
		// .findPropertyDictByDomainNameAndDictDisplayName(
		// DailyDirectoryTypes.SERVICE_MANAGEMENT_NAME,
		// DailyDirectoryTypes.SERVICE_MANAGEMENT_VILLAGE_NAME);

		logger.info("重大调出：" + PropertyTypes.DAILYDIRECTORY_TYPE);

		DailyDirectory dailyDirectory4 = addDailyTrunkDirectory(dailyYear,
				"报表上报", "报表上报", 1, statementsReportedType);
		DailyDirectory dailyDirectory3 = addDailyTrunkDirectory(dailyYear,
				"会议", "会议", 2, null);
		DailyDirectory dailyDirectory1 = addDailyTrunkDirectory(dailyYear,
				"文件", "文件", 3, null);
		DailyDirectory dailyDirectory2 = addDailyTrunkDirectory(dailyYear,
				"活动", "活动", 4, null);

		addDailySubDirectory(dailyYear, dailyDirectory3, "党委会研究综治维稳工作",
				"党委会研究综治维稳工作", 1, null, "indate");
		addDailySubDirectory(dailyYear, dailyDirectory3, "综治委全体会议", "综治委全体会议",
				2, null, "indate");
		addDailySubDirectory(dailyYear, dailyDirectory3, "五部委联席会议(县级以上才有)",
				"五部委联席会议(县级以上才有)", 3, null, "indate");
		addDailySubDirectory(dailyYear, dailyDirectory3, "专门工作领导小组会议(县级以上才有)",
				"专门工作领导小组会议(县级以上才有)", 4, null, "indate");
		addDailySubDirectory(dailyYear, dailyDirectory3, "中心例会(乡镇仅有)",
				"中心例会(乡镇仅有)", 3, null, "indate");
		addDailySubDirectory(dailyYear, dailyDirectory3, "其他会议", "其他会议", 4,
				null, "indate");

		addDailySubDirectory(dailyYear, dailyDirectory1, "目标管理责任书", "目标管理责任书",
				1, null, "indate");
		addDailySubDirectory(dailyYear, dailyDirectory1, "综治实绩档案", "综治实绩档案", 2,
				null, "indate");
		addDailySubDirectory(dailyYear, dailyDirectory1, "表彰奖惩文件", "表彰奖惩文件", 3,
				null, "indate");
		addDailySubDirectory(dailyYear, dailyDirectory1, "经费保障文件", "经费保障文件", 4,
				null, "indate");
		addDailySubDirectory(dailyYear, dailyDirectory1, "其他文件", "其他文件", 5,
				null, "indate");

		addDailySubDirectory(dailyYear, dailyDirectory2, "村务厂务公开(仅适用于村、企业)",
				"村务厂务公开(仅适用于村、企业)", 1, null, "indate");
		addDailySubDirectory(dailyYear, dailyDirectory2, "风险评估(乡镇及以上才有)",
				"风险评估(乡镇及以上才有)", 2, null, "indate");
		addDailySubDirectory(dailyYear, dailyDirectory2, "领导调研", "领导调研", 3,
				null, "indate");
		addDailySubDirectory(dailyYear, dailyDirectory2, "专项活动", "专项活动", 4,
				null, "indate");
		addDailySubDirectory(dailyYear, dailyDirectory2, "其他", "其他", 5, null,
				"indate");

		DailyDirectory reportDirectory1 = addDailySubDirectory(dailyYear,
				dailyDirectory4, "治安重点整治", "治安重点整治", 1, reportedType1, null);
		DailyDirectory reportDirectory2 = addDailySubDirectory(dailyYear,
				dailyDirectory4, "矛盾纠纷排查", "矛盾纠纷排查", 1, reportedType2, null);
		DailyDirectory reportDirectory3 = addDailySubDirectory(dailyYear,
				dailyDirectory4, "治安重点排查情况", "治安重点排查情况", 1, reportedType3, null);
		DailyDirectory reportDirectory4 = addDailySubDirectory(dailyYear,
				dailyDirectory4, "重大矛盾纠纷排查调处情况", "重大矛盾纠纷排查调处情况", 1,
				reportedType4, null);
		DailyDirectory reportDirectory5 = addDailySubDirectory(dailyYear,
				dailyDirectory4, "排查整治强基促稳情况", "排查整治强基促稳情况", 1, reportedType5,
				null);

		addDailySubReportDirectoryType(dailyYear, reportDirectory1, "月报", "月报",
				1, reportedType1, DirectoryReportType.MONTH_REPORT);
		addDailySubReportDirectoryType(dailyYear, reportDirectory1, "季报", "季报",
				2, reportedType1, DirectoryReportType.QUARTER_REPORT);
		addDailySubReportDirectoryType(dailyYear, reportDirectory1, "半年报",
				"半年报", 3, reportedType1, DirectoryReportType.SEMIYEARLY_REPORT);
		addDailySubReportDirectoryType(dailyYear, reportDirectory1, "年报", "年报",
				4, reportedType1, DirectoryReportType.YEAR_REPORT);

		addDailySubReportDirectoryType(dailyYear, reportDirectory2, "月报", "月报",
				1, reportedType2, DirectoryReportType.MONTH_REPORT);
		addDailySubReportDirectoryType(dailyYear, reportDirectory2, "季报", "季报",
				2, reportedType2, DirectoryReportType.QUARTER_REPORT);
		addDailySubReportDirectoryType(dailyYear, reportDirectory2, "半年报",
				"半年报", 3, reportedType2, DirectoryReportType.SEMIYEARLY_REPORT);
		addDailySubReportDirectoryType(dailyYear, reportDirectory2, "年报", "年报",
				4, reportedType2, DirectoryReportType.YEAR_REPORT);

		addDailySubDirectory(dailyYear, reportDirectory3, "治安重点排查情况表",
				"治安重点排查情况表", 1, reportedType3, null);
		addDailySubDirectory(dailyYear, reportDirectory4, "重大矛盾纠纷排查调处情况表",
				"重大矛盾纠纷排查调处情况表", 1, reportedType4, null);
		addDailySubReportDirectoryType(dailyYear, reportDirectory5,
				"“排查整治、强基促稳”专项活动月报表", "“排查整治、强基促稳”专项活动月报表", 1, reportedType5,
				DirectoryReportType.MONTH_REPORT);

		// DailyDirectory dailyDirectory5 = addDailyTrunkDirectory(dailyYear,
		// "社会治安防控体系建设",
		// "社会治安防控体系建设", 5, societyType);
		// DailyDirectory dailyDirectory6 = addDailyTrunkDirectory(dailyYear,
		// "平安综治宣传", "平安综治宣传", 6,
		// securityType);
		// DailyDirectory dailyDirectory7 = addDailyTrunkDirectory(dailyYear,
		// "系列平安创建", "系列平安创建", 7,
		// seriesType);
		// DailyDirectory dailyDirectory8 = addDailyTrunkDirectory(dailyYear,
		// "社会服务管理", "社会服务管理", 8,
		// serviceType);
		// DailyDirectory dailyDirectory9 = addDailyTrunkDirectory(dailyYear,
		// "平时开展工作情况(网格化管理、组团式服务)",
		// "平时开展工作情况(网格化管理、组团式服务)", 9, gridType);
		//
		// addDailySubDirectory(dailyYear, dailyDirectory5, "社会治安防控体系建设情况",
		// "社会治安防控体系建设情况", 1,
		// societyType, null);
		// addDailySubDirectory(dailyYear, dailyDirectory6, "平安综治宣传情况",
		// "平安综治宣传情况", 1, securityType,
		// null);
		// addDailySubDirectory(dailyYear, dailyDirectory7, "系列平安创建情况",
		// "系列平安创建情况", 1, seriesType,
		// null);
		//
		// addDailySubDirectory(dailyYear, dailyDirectory8,
		// "社会服务管理中心建设(县及以上使用)",
		// "社会服务管理中心建设(县及以上使用)", 1, serviceCityType, null);
		// addDailySubDirectory(dailyYear, dailyDirectory8, "社会服务管理中心建设(乡镇使用)",
		// "社会服务管理中心建设(乡镇使用)", 2,
		// serviceTownType, null);
		// addDailySubDirectory(dailyYear, dailyDirectory8,
		// "社会服务管理室（站）建设(村社区使用)",
		// "社会服务管理室（站）建设(村社区使用)", 3, serviceVillageType, null);
		//
		// addDailySubDirectory(dailyYear, dailyDirectory9, "社会治安防控体系建设情况",
		// "社会治安防控体系建设情况", 1,
		// gridType, null);
	}

	private DailyDirectory addDailySubDirectory(DailyYear dailyYear,
			DailyDirectory parentDailyDirectory, String shortName,
			String fullName, Integer index, PropertyDict type, String indate) {
		DailyDirectory dailyDirectory = new DailyDirectory();
		dailyDirectory.setParentDailyDirectory(parentDailyDirectory);
		dailyDirectory.setShortName(shortName);
		dailyDirectory.setFullName(fullName);
		dailyDirectory.setDailyYear(dailyYear);
		dailyDirectory.setIndexId(index);
		dailyDirectory.setType(type);
		if (null != indate) {
			dailyDirectory.setEffectiveDate(true);
			dailyDirectory.setIndate(15L);
			dailyDirectory.setEffectiveDays(15);
		}
		dailyDirectory = dailyDirectoryService
				.addDailyDirectory(dailyDirectory);
		return dailyDirectory;
	}

	private DailyDirectory addDailySubReportDirectoryType(DailyYear dailyYear,
			DailyDirectory parentDailyDirectory, String shortName,
			String fullName, Integer index, PropertyDict type,
			int directoryReportTypeInternalId) {
		List<PropertyDict> propertyDicts = propertyDirctDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.DIRECTORY_REPORT_TYPE,
						directoryReportTypeInternalId);
		DailyDirectory dailyDirectory = new DailyDirectory();
		dailyDirectory.setParentDailyDirectory(parentDailyDirectory);
		dailyDirectory.setShortName(shortName);
		dailyDirectory.setFullName(fullName);
		dailyDirectory.setDailyYear(dailyYear);
		dailyDirectory.setIndexId(index);
		dailyDirectory.setType(type);
		dailyDirectory.setDirectoryReportType(propertyDicts.get(0));
		dailyDirectory = dailyDirectoryService
				.addDailyDirectory(dailyDirectory);
		return dailyDirectory;
	}

	private DailyDirectory addDailyTrunkDirectory(DailyYear dailyYear,
			String shortName, String fullName, Integer index, PropertyDict type) {
		logger.info("shortName:" + shortName);
		DailyDirectory dailyDirectory = new DailyDirectory();
		dailyDirectory.setShortName(shortName);
		dailyDirectory.setFullName(fullName);
		dailyDirectory.setDailyYear(dailyYear);
		dailyDirectory.setIndexId(index);
		dailyDirectory.setType(type);
		dailyDirectory = dailyDirectoryService
				.addDailyDirectory(dailyDirectory);
		return dailyDirectory;
	}

	private DailyYear addDailyYear(String name) {
		DailyYear dailyYear = new DailyYear();
		dailyYear.setName(name);
		dailyYear = dailyYearService.addDailyYear(dailyYear);
		return dailyYear;
	}
}
