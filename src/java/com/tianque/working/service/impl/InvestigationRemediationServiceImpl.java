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

import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkingRecordSubmitstate;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.domain.InvestigationRemediation;
import com.tianque.working.domain.ReportWorkingRecord;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyYearService;
import com.tianque.working.service.InvestigationRemediationService;
import com.tianque.working.service.ReportWorkingRecordService;

@Service("investigationRemediationService")
@Scope("prototype")
@Transactional
public class InvestigationRemediationServiceImpl extends LogableService implements
		InvestigationRemediationService {

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
	public InvestigationRemediation addInvestigationRemediation(
			InvestigationRemediation investigationRemediation) throws Exception {
		ReportWorkingRecord reportWorkingRecord = new ReportWorkingRecord(investigationRemediation);
		reportWorkingRecord.setSubmitState(propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.WORKING_RECORD_SUBMITSTATE,
						WorkingRecordSubmitstate.HAS_NOT_SUBMIT).get(0));
		reportWorkingRecord = reportWorkingRecordService
				.addReportWorkingRecord(reportWorkingRecord);
		return new InvestigationRemediation(reportWorkingRecord);
	}

	@Override
	public InvestigationRemediation getInvestigationRemediationById(Long id) {
		reportWorkingRecordService.getReportWorkingRecordById(id);
		InvestigationRemediation investigationRemediation = null;
		try {
			investigationRemediation = new InvestigationRemediation(
					reportWorkingRecordService.getReportWorkingRecordById(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return investigationRemediation;
	}

	@Override
	public void deleteInvestigationRemediation(Long id) {
		reportWorkingRecordService.deleteReportWorkingRecord(id);
	}

	@Override
	public InvestigationRemediation updateInvestigationRemediation(
			InvestigationRemediation investigationRemediation) throws Exception {
		ReportWorkingRecord reportWorkingRecord = new ReportWorkingRecord(investigationRemediation);
		reportWorkingRecord = reportWorkingRecordService
				.updateReportWorkingRecord(reportWorkingRecord);
		return new InvestigationRemediation(reportWorkingRecord);
	}

	@Override
	public InvestigationRemediation updateSubmitState(Long id, Long submitStateId,
			Long expiredEntering) {
		int submintState = reportWorkingRecordService.updateSubmitState(id, submitStateId,
				expiredEntering);
		if (submintState == 1) {
			return getInvestigationRemediationById(id);
		}
		return null;
	}

	@Override
	public InvestigationRemediation backWorkingRecord(Long id, Long submitStateId) {
		int submintState = reportWorkingRecordService.backWorkingRecord(id, submitStateId);
		if (submintState == 1) {
			return getInvestigationRemediationById(id);
		}
		return null;
	}

	@Override
	public InvestigationRemediation reportSummarizing(Long reportTime, Long dailyDirectoryId,
			Long orgid) {
		ReportWorkingRecord record = reportWorkingRecordService.summarizingJudge(reportTime,
				dailyDirectoryId, orgid);
		DailyDirectory dailyDirectories = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectoryId);
		InvestigationRemediation investigationRemediation = null;
		if (record != null) {
			try {
				investigationRemediation = new InvestigationRemediation(record);
				return investigationRemediation;
			} catch (Exception e) {
				logger.error("reportSummarizing", e);
				System.err.println(e.getMessage());
			}
		}

		return allSummarizingMethod(dailyDirectoryId, orgid, reportTime, dailyDirectories,
				investigationRemediation);
	}

	@Override
	public boolean sunReportJudge(Long reportTime, Long dailyDirectoryId, Long orgid) {
		return reportJudge(orgid, reportTime, dailyDirectoryId);
	}

	private InvestigationRemediation allSummarizingMethod(Long dailyDirectoryId, Long orgid,
			Long reportTime, DailyDirectory dailyDirectorie,
			InvestigationRemediation investigationRemediation) {
		DailyYear dailyYear = dailyYearService.getSimpleDailyYearById(dailyDirectorie
				.getDailyYear().getId());
		if (reportJudge(orgid, reportTime, dailyDirectoryId)) {
			return null;
		}
		investigationRemediation = monthlyReport(orgid, reportTime, dailyDirectoryId);
		investigationRemediation.setDailyYear(dailyYear);
		return investigationRemediation;
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

	public InvestigationRemediation monthlyReport(Long orgId, Long reportTime, Long dailyDirectoryId) {
		List<ReportWorkingRecord> reportWorkingRecord = new ArrayList<ReportWorkingRecord>();
		reportWorkingRecord = reportWorkingRecordService
				.findSunReportWorkingRecordByOrgIdAndDealDate(orgId, reportTime, dailyDirectoryId);
		InvestigationRemediation allData = new InvestigationRemediation();
		for (int i = 0; i < reportWorkingRecord.size(); i++) {
			if (reportWorkingRecord.get(i) != null && reportWorkingRecord.get(i).getId() != null) {
				InvestigationRemediation investigationRemediation;
				try {
					investigationRemediation = new InvestigationRemediation(
							reportWorkingRecord.get(i));
					allData = generateInvestigationRemediationTeam(investigationRemediation,
							allData);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}

		return allData;
	}

	private InvestigationRemediation generateInvestigationRemediationTeam(
			InvestigationRemediation bean, InvestigationRemediation tempTeam) {
		Class beanclassType = bean.getClass();
		Field[] fields = beanclassType.getDeclaredFields();
		try {
			for (Field tempfield : fields) {
				if (tempfield.getType().getName().equals("long")) {
					String fieldName = tempfield.getName();
					String stringLetter = fieldName.substring(0, 1).toUpperCase();
					String getName = "get" + stringLetter + fieldName.substring(1);
					String setName = "set" + stringLetter + fieldName.substring(1);

					Method getMethod = beanclassType.getMethod(getName, new Class[] {});
					Method setMethod = beanclassType.getMethod(setName,
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
			e.printStackTrace();
			throw new ServiceValidationException("系统异常", e);
		}
		return tempTeam;
	}

}
