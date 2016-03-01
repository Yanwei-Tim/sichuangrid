package com.tianque.working.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.DirectoryReportType;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkingRecordSubmitstate;
import com.tianque.domain.workingRecord.DisputEsexamine;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.domain.ReportWorkingRecord;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyYearService;
import com.tianque.working.service.DisputEsexamineService;
import com.tianque.working.service.Logable4WorkingRecordService;
import com.tianque.working.service.ReportWorkingRecordService;

@Service("disputEsexamineService")
@Transactional
public class DisputEsexamineServiceImpl extends Logable4WorkingRecordService implements
		DisputEsexamineService {
	@Autowired
	private ReportWorkingRecordService reportWorkingRecordService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private DailyYearService dailyYearService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public DisputEsexamine addDisputEsexamine(DisputEsexamine disputEsexamine) throws Exception {
		ReportWorkingRecord reportWorkingRecord = new ReportWorkingRecord(disputEsexamine);
		reportWorkingRecord.setSubmitState(propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.WORKING_RECORD_SUBMITSTATE,
						WorkingRecordSubmitstate.HAS_NOT_SUBMIT).get(0));
		reportWorkingRecord = reportWorkingRecordService
				.addReportWorkingRecord(reportWorkingRecord);
		log(reportWorkingRecord, OperatorType.toString(OperatorType.ADD), OperatorType.ADD);
		return new DisputEsexamine(reportWorkingRecord);
	}

	/**
	 * 求出指定半年度所有月报表long类型数据的和； 如果有月报表没有新增，则返回null
	 */
	private DisputEsexamine halfYearReport(Long orgId, Long month, Long dailyDirectoryId) {
		List<ReportWorkingRecord> list = new ArrayList<ReportWorkingRecord>();
		if (month == 1) {
			list = reportWorkingRecordService.findReportWorkingRecordByStartDateAndEndDate(orgId,
					1L, 6L, dailyDirectoryId);
		} else if (month == 2) {
			list = reportWorkingRecordService.findReportWorkingRecordByStartDateAndEndDate(orgId,
					7L, 12L, dailyDirectoryId);
		}
		if (list.size() == 0) {
			return null;
		}
		return summarizing(list);
	}

	/**
	 * 求出指定年度所有月报表long类型数据的和； 如果有月报表没有新增，则返回null
	 */
	private DisputEsexamine yearReport(Long orgId, Long month, Long dailyDirectoryId) {
		List<ReportWorkingRecord> list = reportWorkingRecordService
				.findReportWorkingRecordByStartDateAndEndDate(orgId, 1L, 12L, dailyDirectoryId);
		if (list.size() == 0) {
			return null;
		}
		return summarizing(list);
	}

	/**
	 * 所有子org该月的月报之和
	 */
	public DisputEsexamine monthlyReport(Long orgId, Long reportTime, Long dailyDirectoryId) {
		List<ReportWorkingRecord> reportWorkingRecord = new ArrayList<ReportWorkingRecord>();
		reportWorkingRecord = reportWorkingRecordService
				.findSunReportWorkingRecordByOrgIdAndDealDate(orgId, reportTime, dailyDirectoryId);
		DisputEsexamine allData = new DisputEsexamine();
		for (int i = 0; i < reportWorkingRecord.size(); i++) {
			if (reportWorkingRecord.get(i) != null && reportWorkingRecord.get(i).getId() != null) {
				DisputEsexamine disputEsexamine;
				try {
					disputEsexamine = new DisputEsexamine(reportWorkingRecord.get(i));
					allData = generateDisputEsexamineTeam(disputEsexamine, allData);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return allData;
	}

	/**
	 * 求指定季度所有月报表long类型数据的和; 如果有月报表没有新增，则返回null
	 */
	private DisputEsexamine qtrReport(Long orgId, Long month, Long dailyDirectoryId) {
		List<ReportWorkingRecord> list = new ArrayList<ReportWorkingRecord>();
		if (month == 1) {

			list = reportWorkingRecordService.findReportWorkingRecordByStartDateAndEndDate(orgId,
					1L, 3L, dailyDirectoryId);
		} else if (month == 2) {
			list = reportWorkingRecordService.findReportWorkingRecordByStartDateAndEndDate(orgId,
					4L, 6L, dailyDirectoryId);
		} else if (month == 3) {
			list = reportWorkingRecordService.findReportWorkingRecordByStartDateAndEndDate(orgId,
					7L, 9L, dailyDirectoryId);
		} else if (month == 4) {
			list = reportWorkingRecordService.findReportWorkingRecordByStartDateAndEndDate(orgId,
					10L, 12L, dailyDirectoryId);
		}
		if (list.size() == 0) {
			return null;
		}
		return summarizing(list);
	}

	private DisputEsexamine reportChildren(Long orgId, Long month, Long dailyDirectoryId) {
		List<ReportWorkingRecord> list = reportWorkingRecordService
				.findSunReportWorkingRecordByOrgIdAndDealDate(orgId, month, dailyDirectoryId);
		if (list.size() == 0) {
			return new DisputEsexamine();
		}
		return summarizing(list);
	}

	@Override
	public DisputEsexamine updateDisputEsexamine(DisputEsexamine disputEsexamine) throws Exception {
		ReportWorkingRecord reportWorkingRecord = new ReportWorkingRecord(disputEsexamine);
		reportWorkingRecord = reportWorkingRecordService
				.updateReportWorkingRecord(reportWorkingRecord);
		return new DisputEsexamine(reportWorkingRecord);
	}

	/**
	 * 返回值为后一个参数，多次调用，求出每个long类型数据的总和
	 */
	private DisputEsexamine generateDisputEsexamineTeam(DisputEsexamine bean,
			DisputEsexamine tempTeam) {
		Class tempBeanclassType = tempTeam.getClass();
		Field[] tempfields = tempBeanclassType.getDeclaredFields();
		try {
			for (Field tempfield : tempfields) {
				if (tempfield.getType().getName().equals("long")) {
					String fieldName = tempfield.getName();
					String stringLetter = fieldName.substring(0, 1).toUpperCase();
					String getName = "get" + stringLetter + fieldName.substring(1);
					String setName = "set" + stringLetter + fieldName.substring(1);
					Method getMethod = tempBeanclassType.getMethod(getName, new Class[] {});
					Method setMethod = tempBeanclassType.getMethod(setName,
							new Class[] { tempfield.getType() });
					Object value = getMethod.invoke(tempTeam, new Object[] {});
					Object beanValue = getMethod.invoke(bean, new Object[] {});
					long sum = 0;
					sum = (value == null ? 0 : (Long) value)
							+ (beanValue == null ? 0 : (Long) beanValue);
					setMethod.invoke(tempTeam, new Object[] { sum });
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("系统异常", e);
		}
		return tempTeam;
	}

	/**
	 * 求所有long类型数据的总和
	 */
	public DisputEsexamine summarizing(List<ReportWorkingRecord> list) {
		DisputEsexamine allData = new DisputEsexamine();
		for (int i = 0; i < list.size(); i++) {
			try {
				allData = generateDisputEsexamineTeam(new DisputEsexamine(list.get(i)), allData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return allData;
	}

	@Override
	public DisputEsexamine getdDisputEsexamineId(Long id) {
		DisputEsexamine disputEsexamine = null;
		try {
			disputEsexamine = new DisputEsexamine(
					reportWorkingRecordService.getReportWorkingRecordById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generateDataRate(disputEsexamine);
	}

	@Override
	public void deleteDisputEsexamine(Long id) {
		reportWorkingRecordService.deleteReportWorkingRecord(id);
	}

	@Override
	public DisputEsexamine updateSubmitState(Long id, Long submitStateId, Long expiredEntering) {
		int submintState = reportWorkingRecordService.updateSubmitState(id, submitStateId,
				expiredEntering);
		if (submintState == 1) {
			return getdDisputEsexamineId(id);
		}
		return null;
	}

	public DisputEsexamine backWorkingRecord(Long id, Long submitStateId) {
		int submintState = reportWorkingRecordService.backWorkingRecord(id, submitStateId);
		if (submintState == 1) {
			return getdDisputEsexamineId(id);
		}
		return null;
	}

	@Override
	public DisputEsexamine reportSummarizing(Long reportTime, Long dailyDirectoryId, Long orgid) {
		ReportWorkingRecord record = reportWorkingRecordService.summarizingJudge(reportTime,
				dailyDirectoryId, orgid);
		DailyDirectory dailyDirectories = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectoryId);
		DisputEsexamine disputEsexamine = null;
		if (record != null) {
			try {
				disputEsexamine = new DisputEsexamine(record);
				disputEsexamine = getRealTimeYearData(reportTime, disputEsexamine, dailyDirectories);
				return disputEsexamine;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		disputEsexamine = allSummarizingMethod(dailyDirectoryId, orgid, reportTime,
				dailyDirectories, disputEsexamine);
		if (null == disputEsexamine) {
			return null;
		} else {
			return generateDataRate(disputEsexamine);
		}
	}

	/**
	 * 算出所有%的数据
	 */
	private DisputEsexamine generateDataRate(DisputEsexamine disputEsexamine) {
		DecimalFormat df = new DecimalFormat("0.00");
		double totalCityFinishRate = (double) disputEsexamine.getTotalCityFinishcount()
				/ (double) disputEsexamine.getTotalCityDealcount() * 100;
		String formatCityFinishRate = totalCityFinishRate == 0 ? "0" : df
				.format(totalCityFinishRate) + "%";
		double totalCountyFinishRate = (double) disputEsexamine.getTotalCountyFinishcount()
				/ (double) disputEsexamine.getTotalCountyDealcount() * 100;
		String formatCountyFinishRate = totalCountyFinishRate == 0 ? "0" : df
				.format(totalCountyFinishRate) + "%";
		double totalTownFinishRate = (double) disputEsexamine.getTotalTwonFinishcount()
				/ (double) disputEsexamine.getTotalTwonDealcount() * 100;
		String formatTownFinishRate = totalTownFinishRate == 0 ? "0" : df
				.format(totalTownFinishRate) + "%";
		double totalVilFinishRate = (double) disputEsexamine.getTotalVilFinishcount()
				/ (double) disputEsexamine.getTotalVilDealcount() * 100;
		String formatVilFinishRate = totalVilFinishRate == 0 ? "0" : df.format(totalVilFinishRate)
				+ "%";
		double totalunFinishRate = (double) disputEsexamine.getTotalUnFinishcount()
				/ (double) disputEsexamine.getTotalUnDealcount() * 100;
		String formatunFinishRate = totalunFinishRate == 0 ? "0" : df.format(totalunFinishRate)
				+ "%";
		double totalYearFinishRate = (double) disputEsexamine.getTotalYearFinishcount()
				/ (double) disputEsexamine.getTotalYearDealcount() * 100;
		String formatYearFinishRate = totalYearFinishRate == 0 ? "0" : df
				.format(totalYearFinishRate) + "%";
		double totalCityDealRate = (double) disputEsexamine.getTotalCityDealcount()
				/ (double) disputEsexamine.getTotalCityIssuecount() * 100;
		String formatCityDealRate = totalCityDealRate == 0 ? "0" : df.format(totalCityDealRate)
				+ "%";
		double totalCountyDealRate = (double) disputEsexamine.getTotalCountyDealcount()
				/ (double) disputEsexamine.getTotalCountyIssuecount() * 100;
		String formatCountyDealRate = totalCountyDealRate == 0 ? "0" : df
				.format(totalCountyDealRate) + "%";
		double totalTownDealRate = (double) disputEsexamine.getTotalTwonDealcount()
				/ (double) disputEsexamine.getTotalTwonIssuecount() * 100;
		String formatTownDealRate = totalTownDealRate == 0 ? "0" : df.format(totalTownDealRate)
				+ "%";
		double totalVilDealRate = (double) disputEsexamine.getTotalVilDealcount()
				/ (double) disputEsexamine.getTotalVilIssuecount() * 100;
		String formatVilDealRate = totalVilDealRate == 0 ? "0" : df.format(totalVilDealRate) + "%";
		double totalunDealRate = (double) disputEsexamine.getTotalUnDealcount()
				/ (double) disputEsexamine.getTotalUnIssuecount() * 100;
		String formatunDealRate = totalunDealRate == 0 ? "0" : df.format(totalunDealRate) + "%";
		double totalYearDealRate = (double) disputEsexamine.getTotalYearDealcount()
				/ (double) disputEsexamine.getTotalYearIssuecount() * 100;
		String formatYearDealRate = totalYearDealRate == 0 ? "0" : df.format(totalYearDealRate)
				+ "%";

		double imptCityFinishrate = (double) disputEsexamine.getImptCityImptFinishcount()
				/ (double) disputEsexamine.getImptCityImptDealcount() * 100;
		String formatImptCityFinishrate = imptCityFinishrate == 0 ? "0" : df
				.format(imptCityFinishrate) + "%";
		double imptCountyFinishrate = (double) disputEsexamine.getImptCountyImptFinishcount()
				/ (double) disputEsexamine.getImptCountyImptDealcount() * 100;
		String formatImptCountyFinishrate = imptCountyFinishrate == 0 ? "0" : df
				.format(imptCountyFinishrate) + "%";
		double imptTwonFinishrate = (double) disputEsexamine.getImptTwonImptFinishcount()
				/ (double) disputEsexamine.getImptTwonImptDealcount() * 100;
		String formatImptTwonFinishrate = imptTwonFinishrate == 0 ? "0" : df
				.format(imptTwonFinishrate) + "%";
		double imptVilFinishrate = (double) disputEsexamine.getImptVilImptFinishcount()
				/ (double) disputEsexamine.getImptVilImptDealcount() * 100;
		String formatImptVilFinishrate = imptVilFinishrate == 0 ? "0" : df
				.format(imptVilFinishrate) + "%";
		double imptUnFinishrate = (double) disputEsexamine.getImptUnImptFinishcount()
				/ (double) disputEsexamine.getImptUnImptDealcount() * 100;
		String formatImptUnFinishrate = imptUnFinishrate == 0 ? "0" : df.format(imptUnFinishrate)
				+ "%";
		double imptYearFinishrate = (double) disputEsexamine.getImptYearImptFinishcount()
				/ (double) disputEsexamine.getImptYearImptDealcount() * 100;
		String formatImptYearFinishrate = imptYearFinishrate == 0 ? "0" : df
				.format(imptYearFinishrate) + "%";

		String cityFinishRate = disputEsexamine.getTotalCityIssuecount() == 0 ? "0"
				: formatCityFinishRate;
		String countyFinishRate = disputEsexamine.getTotalCountyIssuecount() == 0 ? "0"
				: formatCountyFinishRate;
		String twonFinishRate = disputEsexamine.getTotalTwonIssuecount() == 0 ? "0"
				: formatTownFinishRate;
		String vilFinishRate = disputEsexamine.getTotalVilIssuecount() == 0 ? "0"
				: formatVilFinishRate;
		String unFinishRate = disputEsexamine.getTotalUnIssuecount() == 0 ? "0"
				: formatunFinishRate;
		String yearFinishRate = disputEsexamine.getTotalYearIssuecount() == 0 ? "0"
				: formatYearFinishRate;
		String cityDealRate = disputEsexamine.getTotalCityIssuecount() == 0 ? "0"
				: formatCityDealRate;
		String countyDealRate = disputEsexamine.getTotalCountyIssuecount() == 0 ? "0"
				: formatCountyDealRate;
		String twonDealRate = disputEsexamine.getTotalTwonIssuecount() == 0 ? "0"
				: formatTownDealRate;
		String vilDealRate = disputEsexamine.getTotalVilIssuecount() == 0 ? "0" : formatVilDealRate;
		String unDealRate = disputEsexamine.getTotalUnIssuecount() == 0 ? "0" : formatunDealRate;
		String YearDealRate = disputEsexamine.getTotalYearIssuecount() == 0 ? "0"
				: formatYearDealRate;

		disputEsexamine.setTotalCityDealrate(cityDealRate);
		disputEsexamine.setTotalCountyDealrate(countyDealRate);
		disputEsexamine.setTotalTwonDealrate(twonDealRate);
		disputEsexamine.setTotalVilDealrate(vilDealRate);
		disputEsexamine.setTotalUnDealrate(unDealRate);
		disputEsexamine.setTotalYearDealrate(YearDealRate);
		disputEsexamine.setTotalCityFinishrate(cityFinishRate);
		disputEsexamine.setTotalCountyFinishrate(countyFinishRate);
		disputEsexamine.setTotalTwonFinishrate(twonFinishRate);
		disputEsexamine.setTotalVilFinishrate(vilFinishRate);
		disputEsexamine.setTotalUnFinishrate(unFinishRate);
		disputEsexamine.setTotalYearFinishrate(yearFinishRate);

		disputEsexamine
				.setImptCityImptFinishrate(disputEsexamine.getImptCityImptIssuecount() == 0 ? "0"
						: formatImptCityFinishrate);
		disputEsexamine
				.setImptCountyImptFinishrate(disputEsexamine.getImptCountyImptIssuecount() == 0 ? "0"
						: formatImptCountyFinishrate);

		disputEsexamine
				.setImptTwonImptFinishrate(disputEsexamine.getImptTwonImptIssuecount() == 0 ? "0"
						: formatImptTwonFinishrate);
		disputEsexamine
				.setImptVilImptFinishrate(disputEsexamine.getImptVilImptIssuecount() == 0 ? "0"
						: formatImptVilFinishrate);
		disputEsexamine
				.setImptUnImptFinishrate(disputEsexamine.getImptUnImptIssuecount() == 0 ? "0"
						: formatImptUnFinishrate);
		disputEsexamine
				.setImptYearImptFinishrate(disputEsexamine.getImptYearImptIssuecount() == 0 ? "0"
						: formatImptYearFinishrate);

		return disputEsexamine;
	}

	@Override
	public DisputEsexamine getRealTimeYearData(Long reportTime, DisputEsexamine disputEsexamine,
			DailyDirectory dailyDirectorie) {
		PropertyDict dict = propertyDictService.getPropertyDictByDomainNameAndDictId(
				PropertyTypes.DIRECTORY_REPORT_TYPE, dailyDirectorie.getDirectoryReportType()
						.getId());
		long orgid = disputEsexamine.getOrganization().getId();
		DisputEsexamine sumAllMonth = new DisputEsexamine();

		if (dict.getInternalId() == DirectoryReportType.MONTH_REPORT) {
			// sumAllMonth就包括了当前月，所以不用再像新增一样去加
			sumAllMonth = (DisputEsexamine) summarizingYearAccumulative(orgid, reportTime,
					dailyDirectorie.getId()).get(0);
			disputEsexamine = uninForYear(disputEsexamine, sumAllMonth, "give");

		} else if (dict.getInternalId() == DirectoryReportType.QUARTER_REPORT) {
			long monthDailyId = reportDailyDirectoryId(DirectoryReportType.MONTH_REPORT,
					dailyDirectorie.getParentDailyDirectory().getId());
			sumAllMonth = (DisputEsexamine) summarizingYearAccumulative(orgid, reportTime * 3,
					monthDailyId).get(0);
			disputEsexamine = uninForYear(disputEsexamine, sumAllMonth, "give");

		} else if (dict.getInternalId() == DirectoryReportType.SEMIYEARLY_REPORT) {
			long monthDailyId = reportDailyDirectoryId(DirectoryReportType.MONTH_REPORT,
					dailyDirectorie.getParentDailyDirectory().getId());
			sumAllMonth = (DisputEsexamine) summarizingYearAccumulative(orgid, reportTime * 6,
					monthDailyId).get(0);
			disputEsexamine = uninForYear(disputEsexamine, sumAllMonth, "give");

		} else if (dict.getInternalId() == DirectoryReportType.YEAR_REPORT) {
			long monthDailyId = reportDailyDirectoryId(DirectoryReportType.MONTH_REPORT,
					dailyDirectorie.getParentDailyDirectory().getId());
			sumAllMonth = (DisputEsexamine) summarizingYearAccumulative(orgid, 12L, monthDailyId)
					.get(0);

			disputEsexamine = uninForYear(disputEsexamine, sumAllMonth, "give");
		}

		return generateDataRate(disputEsexamine);
	}

	/**
	 * 算出所有的数据； 其中月报、季报、半年报、年报的数据都是根据月报来算出的 不用再去考虑年份，因为dailyDirectory是每年都有一套
	 */
	private DisputEsexamine allSummarizingMethod(Long dailyDirectoryId, Long orgid,
			Long reportTime, DailyDirectory dailyDirectorie, DisputEsexamine disputEsexamine) {
		DailyYear dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectorie
				.getDailyYear().getId());
		PropertyDict dict = propertyDictService.getPropertyDictByDomainNameAndDictId(
				PropertyTypes.DIRECTORY_REPORT_TYPE, dailyDirectorie.getDirectoryReportType()
						.getId());
		DisputEsexamine sumAllMonth = new DisputEsexamine();
		DisputEsexamine currentMonthAllSubOrg = new DisputEsexamine();
		if (reportJudge(orgid, reportTime, dailyDirectoryId)) {
			// 如果有子单位没有上报，则返回null，jsp页面上会根据这个来判断
			return null;
		}
		if (dict.getInternalId() == DirectoryReportType.MONTH_REPORT) {
			sumAllMonth = (DisputEsexamine) summarizingYearAccumulative(orgid, reportTime,
					dailyDirectoryId).get(0);
			currentMonthAllSubOrg = monthlyReport(orgid, reportTime, dailyDirectoryId);
			disputEsexamine = uninForYear(currentMonthAllSubOrg, sumAllMonth, "sum");
		} else {
			long monthDailyId = reportDailyDirectoryId(DirectoryReportType.MONTH_REPORT,
					dailyDirectorie.getParentDailyDirectory().getId());
			if (dict.getInternalId() == DirectoryReportType.QUARTER_REPORT) {
				sumAllMonth = (DisputEsexamine) summarizingYearAccumulative(orgid, reportTime * 3,
						monthDailyId).get(0);
				disputEsexamine = qtrReport(orgid, reportTime, monthDailyId);
			} else if (dict.getInternalId() == DirectoryReportType.SEMIYEARLY_REPORT) {
				sumAllMonth = (DisputEsexamine) summarizingYearAccumulative(orgid, reportTime * 6,
						monthDailyId).get(0);
				disputEsexamine = halfYearReport(orgid, reportTime, monthDailyId);
			} else if (dict.getInternalId() == DirectoryReportType.YEAR_REPORT) {
				sumAllMonth = (DisputEsexamine) summarizingYearAccumulative(orgid, 12L,
						monthDailyId).get(0);
				disputEsexamine = yearReport(orgid, reportTime, monthDailyId);
			}
			if (null == disputEsexamine) {
				// 如果没有新增月报，则判断是否有上报的报表，如果有则统计，否则返回null，jsp页面上会根据这个来判断
				disputEsexamine = reportChildren(orgid, reportTime, dailyDirectoryId);
				if (null == disputEsexamine) {
					return null;
				}
			}
			disputEsexamine = uninForYear(disputEsexamine, sumAllMonth, "give");
		}
		disputEsexamine.setDailyYear(dailyYear);
		return disputEsexamine;
	}

	/**
	 * 县级以上单位会查询所有子单位是否都已经上报
	 */
	private boolean reportJudge(Long orgid, Long reportTime, Long dailyDirectoryId) {
		Organization organization = organizationDubboService.getFullOrgById(orgid);
		if (organization.getOrgLevel().getInternalId() == OrganizationLevel.VILLAGE
				|| organization.getOrgLevel().getInternalId() == OrganizationLevel.GRID
				|| organization.getOrgLevel().getInternalId() == OrganizationLevel.TOWN) {
			return false;
		}
		List<ReportWorkingRecord> reportWorkingRecord = reportWorkingRecordService
				.findSunReportWorkingRecordByOrgIdAndDealDate(orgid, reportTime, dailyDirectoryId);
		for (int i = 0; i < reportWorkingRecord.size(); i++) {
			if (reportWorkingRecord.get(i) == null || reportWorkingRecord.get(i).getId() == null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据directoryReportType，和父dailyDirectoryId，
	 * 获得该directoryReportType的dailyDirectoryId
	 * dailyDirectoryId可以是月报、季报..也可以是会议、活动..
	 */
	private Long reportDailyDirectoryId(int directoryReportType, Long dailyDirectoryId) {
		List<DailyDirectory> dailyDirectories = dailyDirectoryService
				.findDailySubDirectoryByParentId(dailyDirectoryId);
		for (int i = 0; i < dailyDirectories.size(); i++) {
			PropertyDict dict = propertyDictService.getPropertyDictByDomainNameAndDictId(
					PropertyTypes.DIRECTORY_REPORT_TYPE, dailyDirectories.get(i)
							.getDirectoryReportType().getId());
			if (dict.getInternalId() == directoryReportType) {
				return dailyDirectoryId = dailyDirectories.get(i).getId();
			}
		}
		return dailyDirectoryId;
	}

	/**
	 * 本orgId、本台账目录下小于reportTime的月报总和； 有几个月报
	 */
	private List<Object> summarizingYearAccumulative(Long orgId, Long reportTime,
			Long dailyDirectoryId) {
		List<ReportWorkingRecord> reportWorkingRecord = reportWorkingRecordService
				.findReportWorkingRecordByOrgIdAndReportTime(orgId, reportTime, dailyDirectoryId);
		DisputEsexamine allData = new DisputEsexamine();
		for (int i = 0; i < reportWorkingRecord.size(); i++) {
			if (reportWorkingRecord.get(i) != null && reportWorkingRecord.get(i).getId() != null) {
				DisputEsexamine disputEsexamine;
				try {
					disputEsexamine = new DisputEsexamine(reportWorkingRecord.get(i));
					allData = generateDisputEsexamineTeam(disputEsexamine, allData);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		List<Object> returnList = new ArrayList<Object>();
		returnList.add(0, allData);
		returnList.add(1, reportWorkingRecord.size());
		return returnList;
	}

	/**
	 * 将后一个DisputEsexamine的合计赋值给前一个DisputEsexamine的年度累计
	 * 或者两个DisputEsexamine的合计相加再赋值给前一个DisputEsexamine的年度累计
	 */
	private DisputEsexamine uninForYear(DisputEsexamine disputEsexamine,
			DisputEsexamine disputEsexamineNew, String type) {
		Class disputEsexamineClassType = disputEsexamine.getClass();
		Field[] disputEsexamineFields = disputEsexamineClassType.getDeclaredFields();
		try {
			for (Field disputEsexamineField : disputEsexamineFields) {
				if (disputEsexamineField.getType().getName().equals("long")) {
					String fieldName = disputEsexamineField.getName();
					String[] levelName = { "UnIssue", "UnDeal", "UnFinish", "UnStages",
							"UnImptIssue", "UnImptDeal", "UnImptFinish" };
					for (int i = 0; i < levelName.length; i++) {
						if (fieldName.indexOf(levelName[i]) != -1) {
							String startLetter = fieldName.substring(0,
									fieldName.indexOf(levelName[i]));
							String endLetter = fieldName.substring(
									fieldName.indexOf(levelName[i]) + 2, fieldName.length());
							String nameLetter = startLetter + "Year" + endLetter;
							// 合计的
							String getName = "get" + fieldName.substring(0, 1).toUpperCase()
									+ fieldName.substring(1);
							Method getMethod = disputEsexamineClassType.getMethod(getName,
									new Class[] {});
							// 年度累计的
							String setNameForYear = "set"
									+ nameLetter.substring(0, 1).toUpperCase()
									+ nameLetter.substring(1);
							Method setMethodForYear = disputEsexamineClassType.getMethod(
									setNameForYear, new Class[] { disputEsexamineField.getType() });

							Object newvalue = getMethod.invoke(disputEsexamineNew, new Object[] {});
							if ("sum".equals(type)) {
								Object value = getMethod.invoke(disputEsexamine, new Object[] {});
								setMethodForYear.invoke(disputEsexamine,
										new Object[] { (value == null ? 0 : (Long) value)
												+ (newvalue == null ? 0 : (Long) newvalue) });
							} else if ("give".equals(type)) {
								setMethodForYear.invoke(disputEsexamine, new Object[] { newvalue });
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("系统异常", e);
		}
		return disputEsexamine;
	}

	@Override
	public boolean sunReportJudge(Long reportTime, Long dailyDirectoryId, Long orgid) {
		return reportJudge(orgid, reportTime, dailyDirectoryId);
	}
}
