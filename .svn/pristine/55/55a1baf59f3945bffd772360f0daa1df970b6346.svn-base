package com.tianque.working.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.ReportWorkingRecordDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkingRecordSubmitstate;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.ReportWorkingRecord;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.Logable4WorkingRecordService;
import com.tianque.working.service.ReportWorkingRecordService;
import com.tianque.working.strategy.WorkingRecordAlgorithm;
import com.tianque.working.strategy.WorkingRecordStrategy;
import com.tianque.working.strategy.util.DateForWorkingRecordUtil;

@Service("reportWorkingRecordService")
@Transactional
public class ReportWorkingRecordServiceImpl extends
		Logable4WorkingRecordService implements ReportWorkingRecordService {
	@Autowired
	private ReportWorkingRecordDao reportWorkingRecordDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public ReportWorkingRecord addReportWorkingRecord(
			ReportWorkingRecord reportWorkingRecord) {
		validate(reportWorkingRecord);
		autoFillOrganizationInternalCode(reportWorkingRecord);
		reportWorkingRecord.setSubmitState(propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.WORKING_RECORD_SUBMITSTATE,
						WorkingRecordSubmitstate.HAS_NOT_SUBMIT).get(0));
		return reportWorkingRecordDao
				.addReportWorkingRecord(reportWorkingRecord);
	}

	@Override
	public void deleteReportWorkingRecord(Long id) {
		log(getReportWorkingRecordById(id),
				OperatorType.toString(OperatorType.DELETE), OperatorType.DELETE);
		reportWorkingRecordDao.deleteReportWorkingRecord(id);
	}

	@Override
	public PageInfo<ReportWorkingRecord> findReportWorkingRecordForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long dailyDirectoryId) {
		String allDailyDirectoryId = "";
		if (dailyDirectoryId == null) {
			throw new BusinessValidationException("找不到指定的目录");
		}
		List<DailyDirectory> dailyDirectories = dailyDirectoryService
				.findDailySubDirectoryByParentId(dailyDirectoryId);
		if (dailyDirectories.size() == 0) {
			allDailyDirectoryId = "'" + dailyDirectoryId + "'";
		} else {
			for (int i = 0; i < dailyDirectories.size(); i++) {
				if (i == 0) {
					allDailyDirectoryId = "'" + dailyDirectories.get(i).getId()
							+ "'";
				} else {
					allDailyDirectoryId = allDailyDirectoryId + ",'"
							+ dailyDirectories.get(i).getId() + "'";
				}
			}
		}
		PageInfo<ReportWorkingRecord> reportWorkingPage = reportWorkingRecordDao
				.findReportWorkingRecordForPageByOrgInternalCode(orgId,
						pageNum, pageSize, sidx, sord, allDailyDirectoryId);
		List<ReportWorkingRecord> reportWorkingList = reportWorkingPage
				.getResult();
		for (int i = 0; i < reportWorkingList.size(); i++) {
			ReportWorkingRecord reportRecord = reportWorkingList.get(i);
			Organization reportOrg = reportRecord.getOrganization();
			Organization org = organizationDubboService
					.getSimpleOrgById(reportOrg.getId());
			reportRecord.setOrganization(org);
			if (reportRecord.getSubmitState() != null
					&& reportRecord.getSubmitState().getId() != null) {
				PropertyDict reportTypeDict = propertyDictService
						.getPropertyDictByDomainNameAndDictId(
								PropertyTypes.WORKING_RECORD_SUBMITSTATE,
								reportRecord.getSubmitState().getId());
				reportRecord.setSubmitState(reportTypeDict);
			}
		}
		return reportWorkingPage;
	}

	@Override
	public ReportWorkingRecord getReportWorkingRecordById(Long id) {
		return reportWorkingRecordDao.getReportWorkingRecordById(id);
	}

	@Override
	public ReportWorkingRecord updateReportWorkingRecord(
			ReportWorkingRecord reportWorkingRecord) {
		validate(reportWorkingRecord);
		autoFillOrganizationInternalCode(reportWorkingRecord);
		return reportWorkingRecordDao
				.updateReportWorkingRecord(reportWorkingRecord);
	}

	private void autoFillOrganizationInternalCode(
			ReportWorkingRecord reportWorkingRecord) {
		Organization org = organizationDubboService
				.getSimpleOrgById(reportWorkingRecord.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		reportWorkingRecord.setOrgInternalCode(org.getOrgInternalCode());
	}

	private void validate(ReportWorkingRecord reportWorkingRecord) {
		if (reportWorkingRecord == null)
			throw new BusinessValidationException("找不到上报内容");
		if (reportWorkingRecord.getOrganization() == null)
			throw new BusinessValidationException("找不到指定的网格");
	}

	@Override
	public int updateSubmitState(Long id, Long submitStateId,
			Long expiredEntering) {
		if (id == null) {
			throw new BusinessValidationException("id不为空");
		}
		return reportWorkingRecordDao.updateSubmitState(id, submitStateId,
				expiredEntering);
	}

	@Override
	public int backWorkingRecord(Long id, Long submitStateId) {
		if (id == null) {
			throw new BusinessValidationException("id不为空");
		}
		return reportWorkingRecordDao.backWorkingRecord(id, submitStateId);
	}

	@Override
	/**
	 * 查出所有子orgId的workIngRecord，如果没有就new一个空对象
	 * */
	public List<ReportWorkingRecord> findSunReportWorkingRecordByOrgIdAndDealDate(
			Long orgId, Long reportTime, Long dailyDirectoryId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不为空");
		}
		List<Organization> orgs = organizationDubboService
				.findOrgsByParentIdAndOrgTypeInternalIds(
						orgId,
						new Long[] { Long
								.valueOf(OrganizationType.ADMINISTRATIVE_REGION) });
		if (orgs.size() == 0) {
			return new ArrayList<ReportWorkingRecord>();
		}
		DailyDirectory dailyDirectorie = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectoryId);
		PropertyDict reportTypeDict = propertyDictService
				.getPropertyDictByDomainNameAndDictId(
						PropertyTypes.DIRECTORY_REPORT_TYPE, dailyDirectorie
								.getDirectoryReportType().getId());

		List<PropertyDict> submitType = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						PropertyTypes.WORKING_RECORD_SUBMITSTATE,
						WorkingRecordSubmitstate.HAS_SUBMIT);
		List<ReportWorkingRecord> reportWorkingRecords = new ArrayList<ReportWorkingRecord>();
		for (int i = 0; i < orgs.size(); i++) {
			ReportWorkingRecord reportWorking = reportWorkingRecordDao
					.findSunReportWorkingRecordByOrgIdAndDealDate(submitType
							.get(0).getId(), orgs.get(i).getId(), reportTime,
							dailyDirectorie.getType().getId(), reportTypeDict
									.getId(), dailyDirectorie.getDailyYear()
									.getId());
			if (reportWorking != null && reportWorking.getId() != null) {
				reportWorkingRecords.add(reportWorking);
			} else {
				reportWorkingRecords.add(new ReportWorkingRecord());
			}
		}
		return reportWorkingRecords;
	}

	@Override
	public void deleteReportWorkingRecordByYearId(Long yearId) {
		reportWorkingRecordDao.deleteReportWorkingRecordByYearId(yearId);
	}

	@Override
	public boolean findSunReportWorkingByOrgIdAndDealDate(Long orgId,
			Date dealDate, Long dailyDirectoryId) {
		List<Organization> orgs = organizationDubboService
				.findOrganizationsByParentId(orgId);
		for (int i = 0; i < orgs.size(); i++) {
			ReportWorkingRecord reportWorking = reportWorkingRecordDao
					.findReportWorkingRecordByOrgIdAndDealDate(orgs.get(i)
							.getId(), dealDate, dailyDirectoryId);
			if (reportWorking != null && reportWorking.getSubmitState() != null
					&& reportWorking.getSubmitState().getInternalId() != 2) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String[][] findAllReportWorkingRecordByOrgIdAndYear(Long orgId,
			Date dealDate, Long dailyDirectoryId, Long dailyYearId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不为空");
		}
		List<Organization> orgs = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		DailyDirectory dailyDirectories = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectoryId);
		PropertyDict reportTypeDict = createPropertyDict(dailyDirectories);
		String[][] allReportWorkingRecords = new String[orgs.size()][14];
		for (int i = 0; i < orgs.size(); i++) {
			allReportWorkingRecords[i][0] = orgs.get(i).getId().toString();
			allReportWorkingRecords[i][1] = orgs.get(i).getOrgName();
			List<ReportWorkingRecord> reportWorking = reportWorkingRecordDao
					.findSunAllReportWorkingRecordByOrgIdAndYear(orgs.get(i)
							.getId(), dailyYearId, dailyDirectories.getType()
							.getId(), reportTypeDict.getId());
			// 如果年份大于当前年份则返回空
			if (DateForWorkingRecordUtil.compareYear(dealDate) == -1) {
				return allReportWorkingRecords;
			}
			WorkingRecordStrategy workingRecordStrategy = WorkingRecordAlgorithm
					.getWorkingRecordForMonthAlgorithm(propertyDictService);
			if (reportWorking.size() != 0) {
				allReportWorkingRecords = workingRecordStrategy.transform(
						reportWorking, allReportWorkingRecords, i, dealDate);
			} else {
				allReportWorkingRecords = workingRecordStrategy
						.createReportWorkingRecord(allReportWorkingRecords, i,
								dealDate);
			}
		}
		return allReportWorkingRecords;
	}

	@Override
	public String[][] findAllReportWorkingRecordByOrgIdAndYearForQuarter(
			Long orgId, Date dealDate, Long dailyDirectoryId, Long dailyYearId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不为空");
		}
		List<Organization> orgs = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		DailyDirectory dailyDirectories = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectoryId);
		PropertyDict reportTypeDict = createPropertyDict(dailyDirectories);
		String[][] allReportWorkingRecords = new String[orgs.size()][6];
		for (int i = 0; i < orgs.size(); i++) {
			allReportWorkingRecords[i][0] = orgs.get(i).getId().toString();
			allReportWorkingRecords[i][1] = orgs.get(i).getOrgName();
			List<ReportWorkingRecord> reportWorking = reportWorkingRecordDao
					.findSunAllReportWorkingRecordByOrgIdAndYearForQuert(orgs
							.get(i).getId(), dailyDirectoryId, dailyDirectories
							.getType().getId(), reportTypeDict.getId(),
							dailyYearId);
			// 如果年份大于当前年份则返回空
			if (DateForWorkingRecordUtil.compareYear(dealDate) == -1) {
				return allReportWorkingRecords;
			}
			WorkingRecordStrategy workingRecordStrategy = WorkingRecordAlgorithm
					.getWorkingRecordForQuarterAlgorithm(propertyDictService);
			if (reportWorking.size() != 0) {
				allReportWorkingRecords = workingRecordStrategy.transform(
						reportWorking, allReportWorkingRecords, i, dealDate);
			} else {
				allReportWorkingRecords = workingRecordStrategy
						.createReportWorkingRecord(allReportWorkingRecords, i,
								dealDate);
			}
		}
		return allReportWorkingRecords;
	}

	@Override
	public String[][] findAllReportWorkingRecordByOrgIdAndYearForHalf(
			Long orgId, Date dealDate, Long dailyDirectoryId, Long dailyYearId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不为空");
		}
		List<Organization> orgs = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		DailyDirectory dailyDirectories = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectoryId);
		PropertyDict reportTypeDict = createPropertyDict(dailyDirectories);
		String[][] allReportWorkingRecords = new String[orgs.size()][4];
		for (int i = 0; i < orgs.size(); i++) {
			allReportWorkingRecords[i][0] = orgs.get(i).getId().toString();
			allReportWorkingRecords[i][1] = orgs.get(i).getOrgName();
			List<ReportWorkingRecord> reportWorking = reportWorkingRecordDao
					.findSunAllReportWorkingRecordByOrgIdAndYearForQuert(orgs
							.get(i).getId(), dailyDirectoryId, dailyDirectories
							.getType().getId(), reportTypeDict.getId(),
							dailyYearId);
			// 如果年份大于当前年份则返回空
			if (DateForWorkingRecordUtil.compareYear(dealDate) == -1) {
				return allReportWorkingRecords;
			}
			WorkingRecordStrategy workingRecordStrategy = WorkingRecordAlgorithm
					.getWorkingRecordForHalfYearAlgorithm(propertyDictService);
			if (reportWorking.size() != 0) {
				allReportWorkingRecords = workingRecordStrategy.transform(
						reportWorking, allReportWorkingRecords, i, dealDate);
			} else {
				allReportWorkingRecords = workingRecordStrategy
						.createReportWorkingRecord(allReportWorkingRecords, i,
								dealDate);
			}
		}
		return allReportWorkingRecords;
	}

	private PropertyDict createPropertyDict(DailyDirectory dailyDirectory) {
		if (dailyDirectory.getDirectoryReportType() == null) {
			return propertyDictService.findPropertyDictByDomainName(
					PropertyTypes.DIRECTORY_REPORT_TYPE).get(0);
		}
		return propertyDictService.getPropertyDictByDomainNameAndDictId(
				PropertyTypes.DIRECTORY_REPORT_TYPE, dailyDirectory
						.getDirectoryReportType().getId());
	}

	@Override
	public List<ReportWorkingRecord> findReportWorkingRecordByOrgIdAndDealDate(
			Long orgId, Date dealDate, Long dailyDirectoryId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不为空");
		}
		List<ReportWorkingRecord> reportWorkingRecords = reportWorkingRecordDao
				.findAllReportWorkingRecordByOrgIdAndYear(orgId, dealDate,
						dailyDirectoryId);
		return reportWorkingRecords;
	}

	@Override
	public List<ReportWorkingRecord> findReportWorkingRecordByStartDateAndEndDate(
			Long orgId, Long startDate, Long endDate, Long dailyDirectoryId) {
		return reportWorkingRecordDao
				.findReportWorkingRecordByStartDateAndEndDate(orgId, startDate,
						endDate, dailyDirectoryId);
	}

	@Override
	public ReportWorkingRecord summarizingJudge(Long reprTime,
			Long dailyDirectoryId, Long orgid) {
		return reportWorkingRecordDao.summarizingJudge(reprTime,
				dailyDirectoryId, orgid);
	}

	@Override
	public String[][] findAllReportWorkingRecordByOrgIdAndYearForYear(
			Long orgId, Date dealDate, Long dailyDirectoryId, Long dailyYearId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不为空");
		}
		List<Organization> orgs = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		DailyDirectory dailyDirectories = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectoryId);
		PropertyDict reportTypeDict = createPropertyDict(dailyDirectories);
		String[][] allReportWorkingRecords = new String[orgs.size()][3];
		for (int i = 0; i < orgs.size(); i++) {
			allReportWorkingRecords[i][0] = orgs.get(i).getId().toString();
			allReportWorkingRecords[i][1] = orgs.get(i).getOrgName();
			List<ReportWorkingRecord> reportWorking = reportWorkingRecordDao
					.findSunAllReportWorkingRecordByOrgIdAndYearForQuert(orgs
							.get(i).getId(), dailyDirectoryId, dailyDirectories
							.getType().getId(), reportTypeDict.getId(),
							dailyYearId);
			// 如果年份大于当前年份则返回空
			if (DateForWorkingRecordUtil.compareYear(dealDate) == -1) {
				return allReportWorkingRecords;
			}
			WorkingRecordStrategy workingRecordStrategy = WorkingRecordAlgorithm
					.getWorkingRecordForYearAlgorithm(propertyDictService);
			if (reportWorking.size() != 0) {
				allReportWorkingRecords = workingRecordStrategy.transform(
						reportWorking, allReportWorkingRecords, i, dealDate);
			} else {
				allReportWorkingRecords = workingRecordStrategy
						.createReportWorkingRecord(allReportWorkingRecords, i,
								dealDate);
			}
		}
		return allReportWorkingRecords;
	}

	@Override
	public List<ReportWorkingRecord> findReportWorkingRecordByOrgIdAndReportTime(
			Long orgId, Long reportTime, Long dailyDirectoryId) {
		return reportWorkingRecordDao
				.findReportWorkingRecordByOrgIdAndReportTime(orgId, reportTime,
						dailyDirectoryId);
	}

	@Override
	public List<ReportWorkingRecord> findReportWorkingRecord(Long orgId,
			Long dailyYearId, Long dailyDirectoryId) {
		return reportWorkingRecordDao.findReportWorkingRecord(orgId,
				dailyYearId, dailyDirectoryId);
	}

}
