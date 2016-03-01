package com.tianque.working.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.ExtTreeData;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.DailyDirectoryTypes;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.StatementsReportedType;
import com.tianque.domain.property.WorkingRecordSubmitstate;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.dao.DailyDirectoryDao;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyDirectoryAttachFile;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.domain.TimeLimitHelper;
import com.tianque.working.domain.WorkingRecord;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyLogService;
import com.tianque.working.service.DailyYearService;
import com.tianque.working.service.WorkingRecordService;
import com.tianque.working.vo.DailyDirectoryTreeData;

@Service("dailyDirectoryService")
@Transactional
public class DailyDirectoryServiceImpl extends AbstractBaseService implements
		DailyDirectoryService {

	@Autowired
	private DailyDirectoryDao dailyDirectoryDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private WorkingRecordService workingRecordService;
	@Autowired
	private DailyLogService dailyLogService;
	@Autowired
	private DailyYearService dailyYearService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public DailyDirectory addDailyDirectory(DailyDirectory dailyDirectory) {
		if (!validateByAdd(dailyDirectory))
			throw new BusinessValidationException();
		if (dailyDirectory.getParentDailyDirectory() == null
				|| dailyDirectory.getParentDailyDirectory().getId() == null) {
			dailyDirectory
					.setIndexId(getMainDirectIndexIdByDailyYearId(dailyDirectory
							.getDailyYear().getId()));
		} else {
			dailyDirectory.setIndexId(getIndexIdByParentId(dailyDirectory
					.getParentDailyDirectory().getId()));
		}
		autoFillChinesePinyin(dailyDirectory);
		DailyDirectory savedDailyDirectory = dailyDirectoryDao
				.addDailyDirectory(dailyDirectory);
		moveDailyTrunkDirectoryIndexId(savedDailyDirectory);
		try {
			List<DailyDirectoryAttachFile> files = convertToFileInfos(
					savedDailyDirectory,
					dailyDirectory.getDailyDirectoryAttachFiles());
			savedDailyDirectory
					.setDailyDirectoryAttachFiles(addDailyDirectoryAttachFiles(files));
		} catch (Exception e) {
			throw new ServiceValidationException("附件新增失败", e);
		}
		return savedDailyDirectory;
	}

	private void moveDailyTrunkDirectoryIndexId(DailyDirectory trunkDirectory) {
		if (trunkDirectory != null
				&& (trunkDirectory.getParentDailyDirectory() == null || trunkDirectory
						.getParentDailyDirectory().getId() == null)) {
			this.moveDailyTrunkDirectoryToPrevious(trunkDirectory.getId(),
					trunkDirectory.getDailyYear().getId(),
					trunkDirectory.getIndexId(), null);
		}
	}

	private List<DailyDirectoryAttachFile> convertToFileInfos(
			DailyDirectory savedDailyDirectory,
			List<DailyDirectoryAttachFile> files) throws Exception {
		if (files == null || files.size() <= 0)
			return null;
		for (DailyDirectoryAttachFile file : files) {
			if (file == null)
				continue;
			convertToFileInfo(file);
			file.setDailyYear(savedDailyDirectory.getDailyYear());
			file.setDailyDirectory(savedDailyDirectory);
		}
		return files;
	}

	private DailyDirectoryAttachFile convertToFileInfo(
			DailyDirectoryAttachFile info) throws Exception {
		if (info.getFileId() == null) {
			StoredFile sf = FileUtil.copyTmpFileToStoredFile(
					info.getFileName(), GridProperties.DAILY_DIRECTORY);
			info.setFileActualUrl(sf.getFullName());
			info.setFileName(info.getFileName());
		}
		return info;
	}

	private boolean validateByAdd(DailyDirectory dailyDirectory) {
		if (dailyDirectory == null)
			return false;
		if (dailyDirectory.getFullName() == null
				|| "".equals(dailyDirectory.getFullName()))
			return false;
		if (dailyDirectory.getDailyYear() == null
				|| dailyDirectory.getDailyYear().getId() == null)
			return false;

		return true;
	}

	private void autoFillChinesePinyin(DailyDirectory dailyDirectory) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(dailyDirectory.getFullName());
		dailyDirectory.setSimplePinyin(pinyin.get("simplePinyin"));
		dailyDirectory.setFullPinyin(pinyin.get("fullPinyin"));
	}

	public Integer getIndexIdByParentId(Long parentId) {
		Integer maxIndexId = dailyDirectoryDao.getIndexIdByParentId(parentId);
		if (maxIndexId == null) {
			maxIndexId = 0;
		}
		return ++maxIndexId;
	}

	public Integer getMainDirectIndexIdByDailyYearId(Long dailyYearId) {
		Integer maxIndexId = dailyDirectoryDao
				.getDailyTrunkDirectotyIndexIdByDailyYearId(dailyYearId);
		if (maxIndexId == null) {
			maxIndexId = 0;
		}
		return ++maxIndexId;
	}

	@Override
	public String deleteDailyDirectoryById(Long dailyDirectoryId) {
		if (dailyDirectoryId == null) {
			throw new BusinessValidationException();
		}
		String str = validateDeleteDailyDirectoryById(dailyDirectoryId);
		if (!"true".equals(str)) {
			return str;
		}
		return reallyDeleteDailyDirectoryById(dailyDirectoryId);
	}

	/** 从目录最底层节点开始删除，直到所传dailyDirectoryId节点位置 ，成返回字符"true" */
	private String reallyDeleteDailyDirectoryById(Long dailyDirectoryId) {
		List<DailyDirectory> result = dailyDirectoryDao
				.findDailySubDirectoryByParentId(dailyDirectoryId);
		if (null != result && result.size() > 0) {
			for (DailyDirectory directory : result) {
				// 删除子目录
				String str = reallyDeleteDailyDirectoryById(directory.getId());
				if (!"true".equals(str)) {
					return str;
				}
				;
			}
		}
		if (dailyDirectoryDao.deleteDailyDirectoryById(dailyDirectoryId) > 0) {
			deleteDailyDirectoryAttachFiles(findDailyDirectoryAttachFilesByDailyDirectoryId(dailyDirectoryId));
			return "true";
		} else {
			return "删除失败，请联系管理员!";
		}
	}

	@Override
	public String validateDeleteDailyDirectoryById(Long dailyDirectoryId) {
		if (dailyDirectoryId == null) {
			throw new BusinessValidationException();
		}
		StringBuffer message = new StringBuffer("");
		message = validateDeleteDailyDirectory(
				getSimpleDailyDirectoryById(dailyDirectoryId), message, "");
		if (StringUtil.isStringAvaliable(message.toString())) {
			if (message.toString().contains("非底层目录，不能删除！")) {
				return message.toString();
			} else if (message.toString().contains("必须存在一份报表，最后一份报表不能删除！")) {
				return message.toString();
			} else {
				return message.substring(0, message.length() - 1)
						+ "目录下有工作台帐信息，无法删除!";
			}
		}
		return "true";
	}

	/** 从目录最底层节点开始查看子目录，是否有工作台帐信息，直到所传dailyDirectory对象节点位置 */
	private StringBuffer validateDeleteDailyDirectory(
			DailyDirectory dailyDirectory, StringBuffer message, String all) {
		if (null == dailyDirectory) {
			throw new BusinessValidationException();
		}
		long count = workingRecordService.countSubTablesValue(
				dailyDirectory.getId()).longValue();
		if (count > 0) {
			message.append(dailyDirectory.getFullName()).append("、");
		}
		if (!StringUtil.isStringAvaliable(all)
				&& null != dailyDirectory.getType()
				&& null != dailyDirectory.getParentDailyDirectory()) {
			List<DailyDirectory> reportResult = dailyDirectoryDao
					.findDailySubDirectoryByParentId(dailyDirectory
							.getParentDailyDirectory().getId());
			List<DailyDirectory> directoryList = new ArrayList();
			for (DailyDirectory directory : reportResult) {
				directoryList.add(directory);
			}
			if (directoryList != null && !directoryList.isEmpty()
					&& directoryList.size() > 1) {
				return message;
			} else {
				message.append("必须存在一份报表，最后一份报表不能删除！");
				return message;
			}
		}
		List<DailyDirectory> result = dailyDirectoryDao
				.findDailySubDirectoryByParentId(dailyDirectory.getId());
		if (null == result || result.size() <= 0) {
			return message;
		}
		if (!StringUtil.isStringAvaliable(all) && null != result
				&& !result.isEmpty()) {
			message.append("非底层目录，不能删除！");
			return message;
		}

		for (DailyDirectory directory : result) {
			if (message.length() > 30) {
				return message.deleteCharAt(message.length() - 1).append("等、");
			}
			// 查看子目录是否有工作台帐信息
			validateDeleteDailyDirectory(directory, message, all);
		}
		return message;
	}

	@Override
	public List<DailyDirectory> findDailyAllTrunkDirectory() {
		return dailyDirectoryDao.findDailyAllTrunkDirectory();
	}

	@Override
	public PageInfo<DailyDirectory> findDailySubDirectoryByParentIdForPage(
			Long id, Integer page, Integer rows, String sidx, String sord) {
		return dailyDirectoryDao.findDailySubDirectoryByParentIdForPage(id,
				page, rows, sidx, sord);
	}

	@Override
	public DailyDirectory getSimpleDailyDirectoryById(Long id) {
		if (id == null) {
			throw new BusinessValidationException();
		}
		DailyDirectory info = dailyDirectoryDao.getSimpleDailyDirectoryById(id);
		if (info != null && info.getId() != null) {
			info.setDailyDirectoryAttachFiles(this
					.findDailyDirectoryAttachFilesByDailyDirectoryId(info
							.getId()));
		}
		return info;
	}

	@Override
	public DailyDirectory getFullDailyDirectoryById(Long id) {
		if (id == null) {
			throw new BusinessValidationException();
		}
		DailyDirectory dailyDirectory = this.getSimpleDailyDirectoryById(id);
		if (dailyDirectory.getType() != null
				&& dailyDirectory.getType().getId() != null) {
			dailyDirectory.setType(propertyDictService
					.getPropertyDictById(dailyDirectory.getType().getId()));
		}
		if (dailyDirectory.getDirectoryReportType() != null
				&& dailyDirectory.getDirectoryReportType().getId() != null) {
			dailyDirectory.setDirectoryReportType(propertyDictService
					.getPropertyDictById(dailyDirectory
							.getDirectoryReportType().getId()));
		}
		return dailyDirectory;
	}

	@Override
	public DailyDirectory updateDailyDirectory(DailyDirectory dailyDirectory) {
		if (!validateByupdate(dailyDirectory))
			throw new BusinessValidationException();
		autoFillChinesePinyin(dailyDirectory);
		DailyDirectory savedDailyDirectory = dailyDirectoryDao
				.updateDailyDirectory(dailyDirectory);
		savedDailyDirectory.setDailyDirectoryAttachFiles(updateAttachFiles(
				savedDailyDirectory, dailyDirectory));
		return savedDailyDirectory;
	}

	private List<DailyDirectoryAttachFile> updateAttachFiles(
			DailyDirectory savedDailyDirectory, DailyDirectory dailyDirectory) {
		List<DailyDirectoryAttachFile> newFiles = null;
		List<DailyDirectoryAttachFile> oldFiles = this
				.findDailyDirectoryAttachFilesByDailyDirectoryId(dailyDirectory
						.getId());
		try {
			newFiles = convertToFileInfos(savedDailyDirectory,
					dailyDirectory.getDailyDirectoryAttachFiles());
		} catch (Exception e) {
			throw new ServiceValidationException("附件更新失败", e);
		}
		if (newFiles == null || newFiles.size() <= 0) {
			deleteDailyDirectoryAttachFiles(oldFiles);
			return new ArrayList<DailyDirectoryAttachFile>();
		} else if (oldFiles == null || oldFiles.size() <= 0) {
			return addDailyDirectoryAttachFiles(newFiles);
		} else {
			List<DailyDirectoryAttachFile> existFiles = checkeOldFilesAndNewFiles(
					oldFiles, newFiles);// 检查附件已经存在的不操作
			List<DailyDirectoryAttachFile> addFiles = addDailyDirectoryAttachFiles(newFiles);// 新增附件
			deleteDailyDirectoryAttachFiles(oldFiles);// 删除不存在的附件
			existFiles.addAll(addFiles);
			return existFiles;
		}
	}

	private List<DailyDirectoryAttachFile> checkeOldFilesAndNewFiles(
			List<DailyDirectoryAttachFile> oldFiles,
			List<DailyDirectoryAttachFile> newFiles) {
		List<DailyDirectoryAttachFile> oldResult = new ArrayList<DailyDirectoryAttachFile>();
		List<DailyDirectoryAttachFile> newResult = new ArrayList<DailyDirectoryAttachFile>();
		for (DailyDirectoryAttachFile oldFile : oldFiles) {
			for (DailyDirectoryAttachFile newFile : newFiles) {
				if (oldFile.getFileId().equals(newFile.getFileId())) {
					oldResult.add(oldFile);
					newResult.add(newFile);
				}
			}
		}
		oldFiles.removeAll(oldResult);
		newFiles.removeAll(newResult);
		return oldResult;
	}

	private List<DailyDirectoryAttachFile> addDailyDirectoryAttachFiles(
			List<DailyDirectoryAttachFile> files) {
		List<DailyDirectoryAttachFile> attachFiles = new ArrayList<DailyDirectoryAttachFile>();
		if (files != null && files.size() > 0) {
			for (DailyDirectoryAttachFile file : files) {
				attachFiles.add(dailyDirectoryDao
						.addDailyDirectoryAttachFile(file));
			}
		}
		return attachFiles;
	}

	/**
	 * 删除上传的文件
	 */
	public void deleteDailyDirectoryAttachFiles(
			List<DailyDirectoryAttachFile> files) {
		if (files != null && files.size() > 0) {
			for (DailyDirectoryAttachFile file : files) {
				dailyDirectoryDao.deleteDailyDirectoryAttachFileByFileId(file
						.getFileId());
				File deleteFile = new File(FileUtil.getWebRoot()
						+ File.separator + file.getFileActualUrl());
				// 路径为文件且不为空则进行删除
				if (deleteFile.isFile() && deleteFile.exists()) {
					try {
						deleteFile.delete();
					} catch (Exception e) {
						logger.error("附件删除错误", e);
					}
				}
			}
		}
	}

	private boolean validateByupdate(DailyDirectory dailyDirectory) {
		if (dailyDirectory == null)
			return false;
		if (dailyDirectory.getFullName() == null
				|| "".equals(dailyDirectory.getFullName())
				|| dailyDirectory.getId() == null)
			return false;

		return true;
	}

	@Override
	public List<DailyDirectory> findDailySubDirectoryByParentId(Long id) {
		return dailyDirectoryDao.findDailySubDirectoryByParentId(id);
	}

	@Override
	public List<DailyDirectory> findDailyDirectoryByDailyYearId(Long id) {
		return dailyDirectoryDao.findDailyDirectoryByDailyYearId(id);
	}

	@Override
	public List<DailyDirectory> findDailyTrunkDirectoryByDailyYearId(Long id) {
		return dailyDirectoryDao.findDailyTrunkDirectoryByDailyYearId(id);
	}

	@Override
	public Integer countExsistedTrunkDirectory(String shortName,
			Long dailyYearId, Long besidesId) {
		if (dailyYearId == null)
			return 0;
		return dailyDirectoryDao.countExsistedTrunkDirectory(shortName,
				dailyYearId, besidesId);
	}

	public Integer countExsistedSubDirectory(String shortName, Long parentId,
			Long besidesId) {
		return dailyDirectoryDao.countExsistedSubDirectory(shortName, parentId,
				besidesId);
	}

	@Override
	public void moveDailySubDirectoryToFirst(Long id, Long parentId,
			Integer indexId) {
		dailyDirectoryDao.moveDailySubDirectoryToFirst(id, parentId, indexId);
	}

	@Override
	public void moveDailySubDirectoryToEnd(Long id, Long parentId,
			Integer indexId) {
		dailyDirectoryDao.moveDailySubDirectoryToEnd(id, parentId, indexId);
	}

	@Override
	public void moveDailySubDirectoryToPrevious(Long id, Long parentId,
			Integer indexId) {
		dailyDirectoryDao
				.moveDailySubDirectoryToPrevious(id, parentId, indexId);
	}

	@Override
	public void moveDailySubDirectoryToNext(Long id, Long parentId,
			Integer indexId) {
		dailyDirectoryDao.moveDailySubDirectoryToNext(id, parentId, indexId);
	}

	@Override
	public void moveDailyTrunkDirectoryToFirst(Long id, Long dailyYearId,
			Integer indexId) {
		dailyDirectoryDao.moveDailyTrunkDirectoryToFirst(id, dailyYearId,
				indexId);
	}

	@Override
	public void moveDailyTrunkDirectoryToEnd(Long id, Long dailyYearId,
			Integer indexId) {
		dailyDirectoryDao
				.moveDailyTrunkDirectoryToEnd(id, dailyYearId, indexId);
	}

	@Override
	public void moveDailyTrunkDirectoryToNext(Long id, Long dailyYearId,
			Integer indexId, Integer privOrNextIndexId) {
		dailyDirectoryDao.moveDailyTrunkDirectoryToNext(id, dailyYearId,
				indexId, privOrNextIndexId);
	}

	@Override
	public void moveDailyTrunkDirectoryToPrevious(Long id, Long dailyYearId,
			Integer indexId, Integer privOrNextIndexId) {
		dailyDirectoryDao.moveDailyTrunkDirectoryToPrevious(id, dailyYearId,
				indexId, privOrNextIndexId);
	}

	@Override
	public int isYesrsCount(Long yearId) {
		return dailyDirectoryDao.isYesrsCount(yearId);
	}

	@Override
	public List<DailyDirectory> findSubDirectoryByMakedOrgIdAndDirectoryTypeInterIdAndDirectoryId(
			Long orgId, String dailydirectoryTypeDomainName,
			int dailydirectoryTypeInterId, Long directoryId) {
		Long type = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(
						dailydirectoryTypeDomainName, dailydirectoryTypeInterId)
				.get(0).getId();
		return dailyDirectoryDao
				.findSubDirectoryByMakedOrgIdAndDirectoryTypeInterIdAndDirectoryId(
						orgId, type, directoryId);
	}

	@Override
	public DailyDirectoryAttachFile getDailyDirectoryAttachFilesByFileId(
			Long fileId) {
		if (fileId == null) {
			throw new BusinessValidationException();
		}
		return dailyDirectoryDao.getDailyDirectoryAttachFilesByFileId(fileId);
	}

	@Override
	public List<DailyDirectoryAttachFile> findDailyDirectoryAttachFilesByDailyDirectoryId(
			Long dailyDirectoryId) {
		if (dailyDirectoryId == null) {
			throw new BusinessValidationException();
		}
		return dailyDirectoryDao
				.findDailyDirectoryAttachFilesByDailyDirectoryId(dailyDirectoryId);
	}

	@Override
	public List<ExtTreeData> findExtTreeDataByTreeTypeAndParentIdAndDailyYearId(
			String treeType, Long parentId, Long dailyYearId) {
		List<ExtTreeData> extTreeDatas = new ArrayList<ExtTreeData>();
		List<DailyDirectory> dailyDirectorys = new ArrayList<DailyDirectory>();
		if (parentId == null) {
			dailyDirectorys = findDailyTrunkDirectoryByDailyYearId(dailyYearId);
		} else {
			dailyDirectorys = findDailySubDirectoryByParentId(parentId);
		}

		if ("dailyPortionDirectoryTree".equals(treeType)) {
			initStatementsDirectoryTreeData(dailyDirectorys, extTreeDatas);
		} else {
			initDirectoryTreeData(dailyDirectorys, extTreeDatas);
		}
		return extTreeDatas;
	}

	private void initDirectoryTreeData(List<DailyDirectory> dailyDirectorys,
			List<ExtTreeData> extTreeDatas) {
		if (dailyDirectorys == null)
			return;
		for (DailyDirectory dailyDirectior : dailyDirectorys) {
			setFullPropertyDict(dailyDirectior);
			createExtTreeDatas(dailyDirectior, extTreeDatas);
		}
	}

	private void initStatementsDirectoryTreeData(
			List<DailyDirectory> dailyDirectorys, List<ExtTreeData> extTreeDatas) {
		if (dailyDirectorys == null)
			return;
		for (DailyDirectory dailyDirectior : dailyDirectorys) {
			dailyDirectior = getFullDailyDirectoryById(dailyDirectior.getId());
			if (dailyDirectior.getType() != null) {
				int internalId = dailyDirectior.getType().getInternalId();
				if (internalId == DailyDirectoryTypes.STATEMENTS_REPORTED
						|| internalId == StatementsReportedType.CHECK
						|| internalId == StatementsReportedType.ISSUEDEAL
						|| internalId == StatementsReportedType.INVESTIGATION_REMEDIATION) {
					setFullPropertyDict(dailyDirectior);
					createExtTreeDatas(dailyDirectior, extTreeDatas);
				}
			}
		}
	}

	private void createExtTreeDatas(DailyDirectory dailyDirectior,
			List<ExtTreeData> extTreeDatas) {
		if (dailyDirectior == null)
			return;
		List<DailyDirectoryAttachFile> listFiles = findDailyDirectoryAttachFilesByDailyDirectoryId(dailyDirectior
				.getId());
		dailyDirectior.setDailyDirectoryAttachFiles(listFiles);
		List<DailyDirectory> directorys = findDailySubDirectoryByParentId(dailyDirectior
				.getId());
		if (directorys != null && directorys.size() > 0) {
			extTreeDatas.add(new DailyDirectoryTreeData(dailyDirectior,
					dailyLogService, false, true));
		} else {
			int count = workingRecordService
					.countWorkingRecordsByDailyDirectoryId(dailyDirectior
							.getId());
			if (count == 0) {
				extTreeDatas.add(new DailyDirectoryTreeData(dailyDirectior,
						dailyLogService, true, true));
			} else {
				extTreeDatas.add(new DailyDirectoryTreeData(dailyDirectior,
						dailyLogService, true, false));
			}
		}
	}

	private void setFullPropertyDict(DailyDirectory dailyDirectior) {
		if (dailyDirectior.getType() != null
				&& dailyDirectior.getType().getId() != null) {
			PropertyDict type = propertyDictService
					.getPropertyDictById(dailyDirectior.getType().getId());
			dailyDirectior.setType(type);
		}
		if (dailyDirectior.getDirectoryReportType() != null
				&& dailyDirectior.getDirectoryReportType().getId() != null) {
			PropertyDict reportType = propertyDictService
					.getPropertyDictById(dailyDirectior
							.getDirectoryReportType().getId());
			dailyDirectior.setDirectoryReportType(reportType);
		}
	}

	@Override
	public List<DailyDirectory> findDailyDirectorysForRemind() {
		List<DailyDirectory> list = dailyDirectoryDao
				.findDailyDirectorysForRemind();
		for (DailyDirectory dailyDirectory : list) {
			DailyYear dailyYear = dailyYearService
					.getSimpleDailyYearById(dailyDirectory.getDailyYear()
							.getId());
			Organization org = organizationDubboService.getSimpleOrgById(dailyYear
					.getMakedOrganization().getId());
			dailyYear.setMakedOrganization(org);
			dailyDirectory.setDailyYear(dailyYear);
		}
		return list;
	}

	@Override
	public DailyDirectory getDailyDirectoryByDailyYearIdAndTypeIdAndReportedTypeId(
			Long dailyYearId, Long typeId, Long reportedTypeId) {
		if (null == dailyYearId || null == typeId || null == reportedTypeId) {
			throw new BusinessValidationException();
		}
		return dailyDirectoryDao
				.getDailyDirectoryByDailyYearIdAndTypeIdAndReportedTypeId(
						dailyYearId, typeId, reportedTypeId);
	}

	@Override
	public DailyDirectory getDirectoryByOrgIdAndTypeIdAndReportTypeIdAndDirectoryId(
			Long orgId, Long typeId, Long reportTypeId, Long directoryId) {
		if (null == orgId || null == typeId || null == directoryId) {
			throw new BusinessValidationException();
		}
		return dailyDirectoryDao
				.getDirectoryByOrgIdAndTypeIdAndReportTypeIdAndDirectoryId(
						orgId, typeId, reportTypeId, directoryId);
	}

	@Override
	public Map<String, Integer> statisticsReport(Long orgId) {
		/*
		 * 当前用户上级的8个目录 对于每个目录查询报表 如报表不存在 按照日期修改already和goingTo 如果报表存在
		 * 查看submitStatus修改already和goingTo
		 */
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer already = 0;
		Integer goingTo = 0;
		Integer all = 0;

		Organization org = organizationDubboService.getFullOrgById(orgId);
		Organization parentOrg = org.getParentOrg();
		if (parentOrg == null) {
			all = already + goingTo;
			map.put("already", already);
			map.put("goingTo", goingTo);
			map.put("all", all);
			return map;
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		List<DailyDirectory> listDirectorys = findLeafDailyDirectorysByOrgIdAndYearDate(
				parentOrg.getId(), year);

		for (DailyDirectory dailyDirectory : listDirectorys) {
			if (dailyDirectory.getTimeLimit() != null
					&& dailyDirectory.getTimeLimit() == TimeLimitHelper.DEADLINE_TYPE) {
				Date endDate = TimeLimitHelper.getEndDate(dailyDirectory);
				List<WorkingRecord> listRecords = workingRecordService
						.findWorkingRecordByDailyDirectoryId(
								dailyDirectory.getId(), org.getId());
				if (listRecords == null || listRecords.size() == 0) {
					if (endDate.before(new Date())) {
						already++;
					} else {
						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.DATE,
								calendar.get(Calendar.DATE) + 5);
						if (endDate.before(calendar.getTime())) {
							goingTo++;
						}
					}
				} else {
					PropertyDict hasNotSubmit = propertyDictService
							.findPropertyDictByDomainNameAndInternalId(
									PropertyTypes.WORKING_RECORD_SUBMITSTATE,
									WorkingRecordSubmitstate.HAS_NOT_SUBMIT)
							.get(0);
					PropertyDict backState = propertyDictService
							.findPropertyDictByDomainNameAndInternalId(
									PropertyTypes.WORKING_RECORD_SUBMITSTATE,
									WorkingRecordSubmitstate.BACK_STATE).get(0);
					for (WorkingRecord workingRecord : listRecords) {
						PropertyDict propertyDict = propertyDictService
								.getPropertyDictById(workingRecord
										.getSubmitState().getId());
						if (hasNotSubmit.getId().equals(propertyDict.getId())
								|| backState.getId().equals(
										propertyDict.getId())) {
							if (endDate.before(new Date())) {
								already++;
							} else {
								Calendar calendar = Calendar.getInstance();
								calendar.set(Calendar.DATE,
										calendar.get(Calendar.DATE) + 5);
								if (endDate.before(calendar.getTime())) {
									goingTo++;
								}
							}
						}
					}

				}
			}
		}

		all = already + goingTo;
		map.put("already", already);
		map.put("goingTo", goingTo);
		map.put("all", all);
		return map;
	}

	private List<DailyDirectory> findLeafDailyDirectorysByOrgIdAndYearDate(
			Long orgId, Integer year) {
		return dailyDirectoryDao.findLeafDailyDirectorysByOrgIdAndYearDate(
				orgId, year);
	}

	@Override
	public String deleteDailyDirectoryByYearId(Long yearId) {
		List<DailyDirectory> result = findDailyTrunkDirectoryByDailyYearId(yearId);
		if (null == result || result.size() <= 0) {
			return "true";
		}
		for (DailyDirectory directory : result) {
			StringBuffer sb = new StringBuffer("");
			sb = validateDeleteDailyDirectory(directory, sb, "all");
			if (!"".equals(sb.toString())) {
				return sb.substring(0, sb.length() - 1) + "目录下有工作台帐信息，无法删除!";
			}
			String str = reallyDeleteDailyDirectoryById(directory.getId());
			if (!"true".equals(str)) {
				return str;
			}
		}
		return "true";
	}

	@Override
	public List<DailyDirectory> searchDirectorys(String searchText,
			Long dailyYearId) {
		return dailyDirectoryDao.searchDirectorys(searchText, dailyYearId);
	}

	@Override
	public List<DailyDirectory> findRangeDailyDirectoryByDailyYearId(Long id,
			List<Long> dailydirectoryIds) {
		return dailyDirectoryDao.findRangeDailyDirectoryByDailyYearId(id,
				dailydirectoryIds);
	}

}
