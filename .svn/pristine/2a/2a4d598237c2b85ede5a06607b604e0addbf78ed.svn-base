package com.tianque.working.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.vo.DailyLogVo;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.DateUtil;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.domain.IssueAttachFile;
import com.tianque.issue.factory.IssueServiceFactory;
import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.resourcePool.help.ParseToMyProfile;
import com.tianque.resourcePool.service.MyProfileService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.working.dao.WorkingRecordDao;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyLogAttachFile;
import com.tianque.working.domain.WorkingRecord;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyLogAttachFileService;
import com.tianque.working.service.WorkingRecordService;
import com.tianque.working.validator.WorkingRecordValidatorImpl;
import com.tianque.working.vo.SearchWorkingRecordVo;

@Service("workingRecordService")
@Transactional
public class WorkingRecordServiceImpl extends AbstractBaseService implements WorkingRecordService {

	@Qualifier("workingRecordValidator")
	@Autowired
	private WorkingRecordValidatorImpl workingRecordValidator;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private WorkingRecordDao workingRecordDao;
	@Autowired
	private DailyLogAttachFileService dailyLogAttachFileService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private MyProfileService myProfileService;
	@Autowired
	private IssueServiceFactory issueServiceFactory;

	@Override
	public PageInfo findWorkingRecordForPageByOrgIdAndDailyDirectoryId(Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord, Long dailyDirectoryId, boolean displayLevel) {
		try {
			String allDailyDirectoryId = "'" + dailyDirectoryId + "'";
			if (dailyDirectoryId == null) {
				throw new BusinessValidationException("找不到指定的目录");
			}
			List<DailyDirectory> dailyDirectories = new ArrayList<DailyDirectory>();
			findAllSubDirectoryByParentId(dailyDirectories, dailyDirectoryId);
			for (DailyDirectory dailyDirectory : dailyDirectories) {
				allDailyDirectoryId += ",'" + dailyDirectory.getId() + "'";
			}

			if (null == orgId) {
				return constructEmptyPageInfo();
			} else {
				Organization org = organizationDubboService.getSimpleOrgById(orgId);
				if (org == null) {
					return constructEmptyPageInfo();
				} else {
					PageInfo pageInfo = workingRecordDao
							.findWorkingRecordForPageByOrgCodeAndDailyDirectoryId(
									org.getOrgInternalCode(), pageNum, pageSize, sidx, sord,
									allDailyDirectoryId, displayLevel);
					appendAttachFile(pageInfo);
					return pageInfo;
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类WorkingRecordServiceImpl的findWorkingRecordForPageByOrgIdAndDailyDirectoryId方法出现异常，原因：",
					"展示台账出现错误", e);
		}
	}

	private void findAllSubDirectoryByParentId(List<DailyDirectory> dailyDirectories,
			Long dailyDirectoryId) {
		List<DailyDirectory> dailyDirectoriesSub = dailyDirectoryService
				.findDailySubDirectoryByParentId(dailyDirectoryId);
		dailyDirectories.addAll(dailyDirectoriesSub);
		if (dailyDirectoriesSub != null && dailyDirectoriesSub.size() != 0) {
			for (DailyDirectory dailyDirectory : dailyDirectoriesSub) {
				findAllSubDirectoryByParentId(dailyDirectories, dailyDirectory.getId());
			}
		}
	}

	@Override
	public PageInfo<WorkingRecord> findPagerBySearchVo(SearchWorkingRecordVo searchWorkingRecordVo,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		try {
			Long dailyDirectoryId = searchWorkingRecordVo.getDailyDirectoryId();
			String allDailyDirectoryId = "'" + dailyDirectoryId + "'";
			if (dailyDirectoryId == null) {
				throw new BusinessValidationException("找不到指定的目录");
			}
			List<DailyDirectory> dailyDirectories = new ArrayList<DailyDirectory>();
			findAllSubDirectoryByParentId(dailyDirectories, dailyDirectoryId);
			for (DailyDirectory dailyDirectory : dailyDirectories) {
				allDailyDirectoryId += ",'" + dailyDirectory.getId() + "'";
			}
			if (searchWorkingRecordVo != null && searchWorkingRecordVo.getOrgId() != null) {
				Organization org = organizationDubboService.getSimpleOrgById(searchWorkingRecordVo
						.getOrgId());
				if (org == null) {
					return constructEmptyPageInfo();
				} else {
					if (searchWorkingRecordVo.getCreateDateEnd() != null) {
						searchWorkingRecordVo.setCreateDateEnd(DateUtil
								.getEndTime(searchWorkingRecordVo.getCreateDateEnd()));
					}
					PageInfo pageInfo = workingRecordDao.findPagerBySearchVo(searchWorkingRecordVo,
							org.getOrgInternalCode(), allDailyDirectoryId, pageNum, pageSize,
							sortField, order);
					appendAttachFile(pageInfo);
					return pageInfo;
				}
			} else {
				return constructEmptyPageInfo();
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类WorkingRecordServiceImpl的findPagerBySearchVo方法出现异常，原因：", "查询台账出现错误", e);
		}
	}

	@Override
	public void deleteWorkingRecordById(Long id) {
		try {
			workingRecordDao.deleteWorkingRecordById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类WorkingRecordServiceImpl的deleteWorkingRecordById方法出现异常，原因：", "删除台账出现错误", e);
		}
	}

	private WorkingRecord updateWorkingRecord(WorkingRecord workingRecord) {
		try {
			autoFilled(workingRecord);

			workingRecord = validateWhenAdd(workingRecord);

			return workingRecordDao.update(workingRecord);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类WorkingRecordServiceImpl的updateWorkingRecord方法出现异常，原因：", "修改台账出现错误", e);
		}
	}

	@Override
	public WorkingRecord updateWorkingRecord(WorkingRecord workingRecord, String[] attachFiles) {
		updateWorkingRecord(workingRecord);
		dailyLogAttachFileService.updateDailyLogAttachFile(workingRecord, attachFiles);
		return workingRecord;
	}

	@Override
	public WorkingRecord getWorkingRecordById(Long id) {
		try {
			WorkingRecord workingRecord = workingRecordDao.getWorkingRecordById(id);
			if (workingRecord != null) {
				workingRecord.setDailyLogAttachFile(dailyLogAttachFileService
						.getSimpleDailyLogAttachFileByDailyLogId(workingRecord.getId()));
			}
			return workingRecord;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类WorkingRecordServiceImpl的getWorkingRecordById方法出现异常，原因：", "根据Id查询台账出现错误", e);
		}
	}

	@SuppressWarnings("unused")
	private void workingRecordValidator(WorkingRecord workingRecord) {
		ValidateResult baseDataValidator = workingRecordValidator.validate(workingRecord);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(baseDataValidator.getErrorMessages());
		}
	}

	private void autoFilled(WorkingRecord workingRecord) {
		autoFillOrgAndUser(workingRecord);
		autoFillChinesePinyin(workingRecord);
	}

	private void autoFillOrgAndUser(WorkingRecord workingRecord) {
		workingRecord.setOrganization(ThreadVariable.getUser().getOrganization());
		Organization org = organizationDubboService.getSimpleOrgById(workingRecord.getOrganization()
				.getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		workingRecord.setOrgInternalCode(org.getOrgInternalCode());
		workingRecord.setCreateUser(ThreadVariable.getUser().getName());
		workingRecord.setCreateDate(new Date());
	}

	private void autoFillChinesePinyin(WorkingRecord workingRecord) {
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(workingRecord.getName());
		workingRecord.setSimplePinyin(pinyin.get("simplePinyin"));
		workingRecord.setFullPinyin(pinyin.get("fullPinyin"));
	}

	private PageInfo<WorkingRecord> constructEmptyPageInfo() {
		PageInfo<WorkingRecord> result = new PageInfo<WorkingRecord>();
		result.setResult(new ArrayList<WorkingRecord>());
		return result;
	}

	private void appendAttachFile(PageInfo<WorkingRecord> pageInfo) {
		for (WorkingRecord workingRecord : pageInfo.getResult()) {
			workingRecord.setDailyLogAttachFile(dailyLogAttachFileService
					.getSimpleDailyLogAttachFileByDailyLogId(workingRecord.getId()));
		}
	}

	@Override
	public Long countSubTablesValue(Long directoryId) {

		return workingRecordDao.countSubTablesValue(directoryId);
	}

	@Override
	public List<WorkingRecord> findWorkingRecordByDailyDirectoryId(Long directoryId,
			Long organizationId) {

		return workingRecordDao.findWorkingRecordByDailyDirectoryId(directoryId, organizationId);
	}

	@Override
	public List<DailyLogVo> findDailyLogVoByDetailedRuleId(Long detailedRuleId) {

		// return workingRecordDao
		// .findDailyLogVoByDetailedRuleId(detailedRuleId,
		// Integer.valueOf(DailyDirectoryTypes.INVESTIGATION).longValue(),
		// Integer.valueOf(DailyDirectoryTypes.SIGNIFICANT_ISSUEDEAL).longValue()
		// );
		return new ArrayList<DailyLogVo>();
	}

	@Override
	public int countWorkingRecordsByDailyDirectoryId(Long id) {
		String orgCode = ThreadVariable.getUser().getOrgInternalCode();
		// return workingRecordDao.countWorkingRecordsByDailyDirectoryId(id);
		return workingRecordDao.countWorkingRecordsByDailyDirectoryId(orgCode, id);
	}

	@Override
	public void deleteWorkingRecordByIds(String ids) {
		if (ids == null || "".equals(ids)) {
			throw new BusinessValidationException("台账id没有获得");
		}
		String[] strs = ids.split(",");
		for (String idStr : strs) {
			long id = Long.parseLong(idStr);
			dailyLogAttachFileService.deleteDailyLogAttachFileByDailyLogId(id);
			deleteWorkingRecordById(id);
		}
	}

	@Override
	public List<WorkingRecord> getWorkingRecordsByIds(String ids) {
		List<Long> listIds = new ArrayList<Long>();
		if (ids == null || "".equals(ids)) {
			throw new BusinessValidationException("台账id没有获得");
		}
		String[] strs = ids.split(",");

		for (String idStr : strs) {
			long id = Long.parseLong(idStr);
			listIds.add(id);
		}
		return workingRecordDao.findWorkingRecordByIds(listIds);
	}

	@Override
	public List<MyProfile> synchToMyProfile(String ids, Long sendMessage, Long resourcePoolTypeId,
			String setPermissionCacheId) {
		if (ids == null || "".equals(ids)) {
			throw new BusinessValidationException("文件id没有获得");
		}
		if (resourcePoolTypeId == null) {
			throw new BusinessValidationException("没有设置文件存放的目录");
		}
		List<MyProfile> myProfiles = new ArrayList<MyProfile>();

		String[] st = ids.split(",");
		for (String idStr : st) {
			WorkingRecord workingRecord = new WorkingRecord();
			MyProfile myProfile = new MyProfile();
			workingRecord = getWorkingRecordById(Long.valueOf(idStr));
			workingRecord.setDailyLogAttachFile(dailyLogAttachFileService
					.getSimpleDailyLogAttachFileByDailyLogId(workingRecord.getId()));
			myProfile = ParseToMyProfile.parse(workingRecord, resourcePoolTypeId, sendMessage);

			myProfiles.add(myProfile);
		}
		myProfileService.synchToMyProfile(ids, myProfiles, sendMessage, resourcePoolTypeId,
				setPermissionCacheId);
		return myProfiles;
	}

	@Override
	public WorkingRecord addWorkingRecord(WorkingRecord workingRecord, String[] attachFiles) {
		workingRecord = validateWhenAdd(workingRecord);
		workingRecord = addWorkingRecord(workingRecord);
		addDailyLogAttachFile(workingRecord, attachFiles);
		return workingRecord;
	}

	@Override
	public WorkingRecord convertToWorkingRecord(WorkingRecord workingRecord, String[] attachFiles,
			Long[] issueAttachFileIds) {
		workingRecord = validateWhenAdd(workingRecord);
		workingRecord = addWorkingRecord(workingRecord);
		addDailyLogAndIssueAttachFile(workingRecord, attachFiles, issueAttachFileIds);
		return workingRecord;
	}

	private WorkingRecord addWorkingRecord(WorkingRecord workingRecord) {
		try {
			autoFilled(workingRecord);
			// workingRecordValidator(workingRecord);
			WorkingRecord workingRecordRtn = workingRecordDao.add(workingRecord);
			return workingRecordRtn;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类WorkingRecordServiceImpl的addWorkingRecord方法出现异常，原因：", "新增台账出现错误", e);
		}
	}

	// 是否超期录入
	private WorkingRecord validateWhenAdd(WorkingRecord workingRecord) {
		if (workingRecord != null && workingRecord.getDailyDirectory() != null
				&& workingRecord.getDailyDirectory().getId() != null) {
			DailyDirectory dailyDirectory = dailyDirectoryService
					.getFullDailyDirectoryById(workingRecord.getDailyDirectory().getId());
			if (dailyDirectory.getEffectiveDays() != null) {
				Date dateNow = new Date();
				Date dateDeal = workingRecord.getDealDate();
				if (dateDeal != null) {
					if (getQuot(dateNow, dateDeal) > dailyDirectory.getEffectiveDays()) {
						workingRecord.setExpiredEntering(1L);// 1超期录入
					} else {
						workingRecord.setExpiredEntering(0L);
					}
				}
			}
		}
		return workingRecord;
	}

	private int getQuot(Date date1, Date date2) {
		long quotTime = 0;
		quotTime = date1.getTime() - date2.getTime();
		quotTime = quotTime / 1000 / 60 / 60 / 24;
		return (int) quotTime;
	}

	private void addDailyLogAndIssueAttachFile(WorkingRecord workingRecord, String[] attachFiles,
			Long[] issueAttachFileIds) {
		addDailyLogAttachFile(workingRecord, attachFiles);
		addIssueAttachFile(workingRecord, issueAttachFileIds);
	}

	private boolean addIssueAttachFile(WorkingRecord workingRecord, Long[] issueAttachFileIds) {
		if (issueAttachFileIds == null || issueAttachFileIds.length == 0) {
			return true;
		}
		for (Long id : issueAttachFileIds) {
			IssueAttachFile issueAttachFile = issueServiceFactory.getIssueServiceBySeries(
					IssueServiceFactory.DEFAULT_SERIES).getIssueAttachFileById(id);
			if (issueAttachFile != null && issueAttachFile.getFileActualUrl() != null) {

				File fromFile = new File(FileUtil.getWebRoot() + File.separator
						+ issueAttachFile.getFileActualUrl());

				String newFileName = FileUtil.createStoreFileName(issueAttachFile.getFileName());

				String[] storedPaths = FileUtil.createStoreFilePath(
						GridProperties.WORKINGRECORD_PATH, newFileName);

				File toFile = new File(storedPaths[0] + File.separator + newFileName);

				try {
					FileUtil.copyFile(fromFile, toFile);
				} catch (Exception e) {
					e.printStackTrace();
				}

				DailyLogAttachFile dailyLogAttachFile = new DailyLogAttachFile();
				dailyLogAttachFile.setWorkingRecord(workingRecord);
				dailyLogAttachFile.setFileSize(fromFile.length());
				dailyLogAttachFile.setFileActualUrl(storedPaths[1] + File.separator + newFileName);
				dailyLogAttachFile.setFileName(issueAttachFile.getFileName());
				dailyLogAttachFile.setDailyYear(workingRecord.getDailyYear());
				workingRecord.getDailyLogAttachFile().add(
						dailyLogAttachFileService.addDailyLogAttachFile(dailyLogAttachFile));
			}
		}
		return true;

	}

	private boolean addDailyLogAttachFile(WorkingRecord workingRecord, String[] attachFiles) {
		if (attachFiles == null || attachFiles.length == 0) {
			return true;
		}
		for (String fileName : attachFiles) {
			if (!addAttachFile(workingRecord, fileName))
				return false;
		}
		return true;
	}

	private boolean addAttachFile(WorkingRecord workingRecord, String fileName) {
		DailyLogAttachFile dailyLogAttachFile = new DailyLogAttachFile();
		dailyLogAttachFile.setWorkingRecord(workingRecord);
		StoredFile storedFile = null;
		try {
			storedFile = FileUtil.copyTmpFileToStoredFile(fileName,
					GridProperties.WORKINGRECORD_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dailyLogAttachFile.setFileSize(storedFile.getFileSize());
		dailyLogAttachFile.setFileActualUrl(storedFile.getStoredFilePath() + File.separator
				+ storedFile.getStoredFileName());
		dailyLogAttachFile.setFileName(storedFile.getStoredTruthFileName());
		dailyLogAttachFile.setDailyYear(workingRecord.getDailyYear());
		workingRecord.getDailyLogAttachFile().add(
				dailyLogAttachFileService.addDailyLogAttachFile(dailyLogAttachFile));
		return true;
	}

}
