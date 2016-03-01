package com.tianque.working.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.DirectoryReportType;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkingRecordSubmitstate;
import com.tianque.domain.workingRecord.EmphasisSafetyDetail;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.domain.ReportWorkingRecord;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyYearService;
import com.tianque.working.service.EmphasisSafetyDetailService;
import com.tianque.working.service.Logable4WorkingRecordService;
import com.tianque.working.service.ReportWorkingRecordService;

@Service("emphasisSafetyDetailService")
@Scope("prototype")
@Transactional
public class EmphasisSafetyDetailServiceImpl extends Logable4WorkingRecordService implements
		EmphasisSafetyDetailService {
	private static Logger logger = LoggerFactory.getLogger(EmphasisSafetyDetailServiceImpl.class);
	@Autowired
	private ReportWorkingRecordService reportWorkingRecordService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private DailyYearService dailyYearService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public EmphasisSafetyDetail addEmphasisSafetyDetail(EmphasisSafetyDetail emphasisSafetyDetail)
			throws Exception {
		ReportWorkingRecord reportWorkingRecord = new ReportWorkingRecord(emphasisSafetyDetail);
		reportWorkingRecord.setSubmitState(propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.WORKING_RECORD_SUBMITSTATE,
						WorkingRecordSubmitstate.HAS_NOT_SUBMIT).get(0));
		log(reportWorkingRecord, OperatorType.toString(OperatorType.ADD), OperatorType.ADD);

		reportWorkingRecord = reportWorkingRecordService
				.addReportWorkingRecord(reportWorkingRecord);

		return new EmphasisSafetyDetail(reportWorkingRecord);
	}

	@Override
	public EmphasisSafetyDetail updateEmphasisSafetyDetail(EmphasisSafetyDetail emphasisSafetyDetail)
			throws Exception {
		ReportWorkingRecord reportWorkingRecord = new ReportWorkingRecord(emphasisSafetyDetail);
		reportWorkingRecord = reportWorkingRecordService
				.updateReportWorkingRecord(reportWorkingRecord);
		return new EmphasisSafetyDetail(reportWorkingRecord);
	}

	public EmphasisSafetyDetail monthlyReport(Long orgId, Long reportTime, Long dailyDirectoryId) {
		List<ReportWorkingRecord> reportWorkingRecord = new ArrayList<ReportWorkingRecord>();
		reportWorkingRecord = reportWorkingRecordService
				.findSunReportWorkingRecordByOrgIdAndDealDate(orgId, reportTime, dailyDirectoryId);
		EmphasisSafetyDetail allData = new EmphasisSafetyDetail();
		for (int i = 0; i < reportWorkingRecord.size(); i++) {
			if (reportWorkingRecord.get(i) != null && reportWorkingRecord.get(i).getId() != null) {
				EmphasisSafetyDetail emphasisSafetyDetail;
				try {
					emphasisSafetyDetail = new EmphasisSafetyDetail(reportWorkingRecord.get(i));
					allData = generateEmphasisSafetyDetailTeam(emphasisSafetyDetail, allData);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return allData;
	}

	private EmphasisSafetyDetail generateEmphasisSafetyDetailTeam(EmphasisSafetyDetail bean,
			EmphasisSafetyDetail tempTeam) {
		Class beanclassType = bean.getClass();
		Class tempBeanclassType = bean.getClass();
		Field[] beanfields = beanclassType.getDeclaredFields();
		Field[] tempfields = tempBeanclassType.getDeclaredFields();
		try {
			for (Field tempfield : tempfields) {
				for (Field beanfield : beanfields) {
					if (tempfield.getName().equals(beanfield.getName())
							&& tempfield.getType().getName().equals("long")) {
						String fieldName = tempfield.getName();
						String stringLetter = fieldName.substring(0, 1).toUpperCase();
						String getName = "get" + stringLetter + fieldName.substring(1);
						String setName = "set" + stringLetter + fieldName.substring(1);
						Method getMethod = tempBeanclassType.getMethod(getName, new Class[] {});
						Method setMethod = tempBeanclassType.getMethod(setName,
								new Class[] { tempfield.getType() });
						Object value = getMethod.invoke(tempTeam, new Object[] {});
						Method beanGetMethod = beanclassType.getMethod(getName, new Class[] {});
						Object beanValue = beanGetMethod.invoke(bean, new Object[] {});
						long sum = 0;
						sum = (value == null ? 0 : (Long) value)
								+ (beanValue == null ? 0 : (Long) beanValue);
						setMethod.invoke(tempTeam, new Object[] { sum });
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("系统异常", e);
		}
		return tempTeam;
	}

	private EmphasisSafetyDetail halfYearReport(Long orgId, Long month, int year,
			Long dailyDirectoryId) {
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

	private EmphasisSafetyDetail reportChildren(Long orgId, Long month, Long dailyDirectoryId) {
		List<ReportWorkingRecord> list = reportWorkingRecordService
				.findSunReportWorkingRecordByOrgIdAndDealDate(orgId, month, dailyDirectoryId);
		if (list.size() == 0) {
			return new EmphasisSafetyDetail();
		}
		return summarizing(list);
	}

	private EmphasisSafetyDetail qtrReport(Long orgId, Long month, int year, Long dailyDirectoryId) {
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

	private EmphasisSafetyDetail summarizing(List<ReportWorkingRecord> list) {
		EmphasisSafetyDetail allData = new EmphasisSafetyDetail();
		for (int i = 0; i < list.size(); i++) {
			try {
				EmphasisSafetyDetail emphasisSafetyDetail = new EmphasisSafetyDetail(list.get(i));
				allData = generateEmphasisSafetyDetailTeam(emphasisSafetyDetail, allData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return allData;
	}

	private EmphasisSafetyDetail yearReport(Long orgId, int year, Long dailyDirectoryId) {
		List<ReportWorkingRecord> list = new ArrayList<ReportWorkingRecord>();
		list = reportWorkingRecordService.findReportWorkingRecordByStartDateAndEndDate(orgId, 1L,
				12L, dailyDirectoryId);
		if (list.size() == 0) {
			return null;
		}
		return summarizing(list);
	}

	@Override
	public EmphasisSafetyDetail getEmphasisSafetyDetailById(Long id) {
		reportWorkingRecordService.getReportWorkingRecordById(id);
		EmphasisSafetyDetail emphasisSafetyDetail = null;
		try {
			emphasisSafetyDetail = new EmphasisSafetyDetail(
					reportWorkingRecordService.getReportWorkingRecordById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emphasisSafetyDetail;
	}

	@Override
	public void deleteEmphasisSafetyDetail(Long id) {
		reportWorkingRecordService.deleteReportWorkingRecord(id);
	}

	@Override
	public EmphasisSafetyDetail updateSubmitState(Long id, Long submitStateId, Long expiredEntering) {
		int submintState = reportWorkingRecordService.updateSubmitState(id, submitStateId,
				expiredEntering);
		if (submintState == 1) {
			return getEmphasisSafetyDetailById(id);
		}
		return null;
	}

	public EmphasisSafetyDetail backWorkingRecord(Long id, Long submitStateId) {
		int submintState = reportWorkingRecordService.backWorkingRecord(id, submitStateId);
		if (submintState == 1) {
			return getEmphasisSafetyDetailById(id);
		}
		return null;

	}

	@Override
	public EmphasisSafetyDetail reportSummarizing(Long reportTime, Long dailyDirectoryId, Long orgid) {
		ReportWorkingRecord record = reportWorkingRecordService.summarizingJudge(reportTime,
				dailyDirectoryId, orgid);
		DailyDirectory dailyDirectories = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectoryId);
		EmphasisSafetyDetail emphasisSafetyDetail = null;
		if (record != null) {
			try {
				emphasisSafetyDetail = new EmphasisSafetyDetail(record);
				return emphasisSafetyDetail;
			} catch (Exception e) {
				logger.error("reportSummarizing", e);
				e.printStackTrace();
			}
		}

		return allSummarizingMethod(dailyDirectoryId, orgid, reportTime, dailyDirectories,
				emphasisSafetyDetail);
	}

	private EmphasisSafetyDetail allSummarizingMethod(Long dailyDirectoryId, Long orgid,
			Long reportTime, DailyDirectory dailyDirectorie,
			EmphasisSafetyDetail emphasisSafetyDetail) {
		DailyYear dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectorie
				.getDailyYear().getId());
		PropertyDict dict = propertyDictService.getPropertyDictByDomainNameAndDictId(
				PropertyTypes.DIRECTORY_REPORT_TYPE, dailyDirectorie.getDirectoryReportType()
						.getId());
		if (reportJudge(orgid, reportTime, dailyDirectoryId)) {
			return null;
		}
		if (dict.getInternalId() == DirectoryReportType.MONTH_REPORT) {
			emphasisSafetyDetail = monthlyReport(orgid, reportTime, dailyDirectoryId);
		} else {
			if (dict.getInternalId() == DirectoryReportType.QUARTER_REPORT) {
				emphasisSafetyDetail = qtrReport(
						orgid,
						reportTime,
						dailyYear.getYearDate(),// Integer.parseInt(dailyYear.getName()),
						reportDailyDirectoryId(DirectoryReportType.MONTH_REPORT, dailyDirectorie
								.getParentDailyDirectory().getId()));
			} else if (dict.getInternalId() == DirectoryReportType.SEMIYEARLY_REPORT) {
				emphasisSafetyDetail = halfYearReport(
						orgid,
						reportTime,
						dailyYear.getYearDate(),// Integer.parseInt(dailyYear.getName()),
						reportDailyDirectoryId(DirectoryReportType.MONTH_REPORT, dailyDirectorie
								.getParentDailyDirectory().getId()));
			} else if (dict.getInternalId() == DirectoryReportType.YEAR_REPORT) {
				emphasisSafetyDetail = yearReport(orgid,
						dailyYear.getYearDate(),// Integer.parseInt(dailyYear.getName()),
						reportDailyDirectoryId(DirectoryReportType.MONTH_REPORT, dailyDirectorie
								.getParentDailyDirectory().getId()));
			}
			if (null == emphasisSafetyDetail) {
				// 如果没有新增月报，则判断是否有上报的季报，如果有则统计，否则返回null，jsp页面上会根据这个来判断
				emphasisSafetyDetail = reportChildren(orgid, reportTime, dailyDirectoryId);
				if (null == emphasisSafetyDetail) {
					return null;
				}
			}
		}
		emphasisSafetyDetail.setDailyYear(dailyYear);
		return emphasisSafetyDetail;
	}

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

	@Override
	public boolean sunReportJudge(Long reportTime, Long dailyDirectoryId, Long orgid) {
		return reportJudge(orgid, reportTime, dailyDirectoryId);
	}
}
