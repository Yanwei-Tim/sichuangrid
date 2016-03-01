package com.tianque.peopleLog.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.peopleLog.dao.PeopleLogDao;
import com.tianque.peopleLog.domain.PeopleLog;
import com.tianque.peopleLog.domain.PeopleLogAttachFiles;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("peopleLogService")
@Transactional
public class PeopleLogServiceImpl extends AbstractBaseService implements
		PeopleLogService {
	@Autowired
	private PeopleLogDao peopleLogDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	// FIXME 服务团队迁移
	// @Autowired
	// private ServiceRecordService serviceRecordService;

	@Override
	public PeopleLog addPeopleLog(PeopleLog peopleLog) {
		try {
			peopleLog.setUserId(ThreadVariable.getSession().getUserId());

			Organization org = organizationDubboService
					.getSimpleOrgById(ThreadVariable.getOrganization().getId());

			peopleLog.setOrganization(org);

			peopleLog.setOrgInternalCode(org.getOrgInternalCode());

			PropertyDict propertyDict = propertyDictService
					.findPropertyDictByDomainNameAndDictDisplayName(
							PropertyTypes.PEOPLELOG_LOGTYPE, "工作记录");

			peopleLog.setLogType(propertyDict);

			peopleLog.setCommentNums(0);

			return peopleLogDao.addPeopleLog(peopleLog);

		} catch (Exception e) {
			return dealException(this, "addPeopleLog", "保存数据错误", e);
		}

	}

	@Override
	public PeopleLog getPeopleLogById(Long id) {
		try {
			PeopleLog peopleLog = peopleLogDao.getPeopleLogById(id);
			peopleLog.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(peopleLog
							.getOrganization().getId(), organizationDubboService));
			peopleLog
					.setPeopleLogFiles(findPeopleLogAttachFilesByPeopleLogId(peopleLog
							.getId()));
			return peopleLog;
		} catch (Exception e) {
			return dealException(this, "getPeopleLogById", "查找数据错误", e);
		}
	}

	public List<PeopleLog> getPeopleLogByUserId(Long userId) {
		try {
			List<PeopleLog> peopleLogList = peopleLogDao
					.getPeopleLogByUserId(userId);
			return peopleLogList;
		} catch (Exception e) {
			return dealException(this, "getPeopleLogByUserId", "查找数据错误", e);
		}
	}

	@Override
	public PageInfo<PeopleLog> findPeopleLogForPageByUserId(Long userId,
			Integer pageNum, Integer pageSize, String sortField, String sord,
			Integer commentNums) {
		try {
			PageInfo<PeopleLog> peopleLogs = peopleLogDao
					.findPeopleLogForPageByUserId(userId, pageNum, pageSize,
							sortField, sord, commentNums);
			if (null != peopleLogs.getResult()
					&& peopleLogs.getResult().size() > 0) {
				for (PeopleLog peopleLog : peopleLogs.getResult()) {
					if (peopleLog.getIsAttachment())
						peopleLog
								.setPeopleLogFiles(findPeopleLogAttachFilesByPeopleLogId(peopleLog
										.getId()));
				}
			}
			return peopleLogs;
		} catch (Exception e) {
			return dealException(this, "findPeopleLogForPageByUserId",
					"查找数据错误", e);
		}
	}

	@Override
	public void deletePeopleLogByIds(List<Long> idList) {
		try {
			if (idList == null) {
				throw new BusinessValidationException("删除民情日志idList不能为空");
			}
			for (Long id : idList) {
				if (id == null || id == 0L) {
					throw new BusinessValidationException("删除民情日志id不能为空");
				}
				PeopleLog peopleLog = peopleLogDao.getPeopleLogById(id);
				if (peopleLog.getIsAttachment()) {
					removePeopleLogAttachFiles(peopleLog.getId());
				}
				peopleLogDao.deleteCommentLogByLogId(id);
				peopleLogDao.deletePeopleLogById(id);
			}
		} catch (Exception e) {
			dealException(this, "deletePeopleLogByIds", "删除数据错误", e);
		}
	}

	@Override
	public PeopleLog updatePeopleLog(PeopleLog peopleLog, String[] attachFiles) {
		try {
			updatePeopleLogFileForAdd(peopleLog.getId(), attachFiles);
			return peopleLogDao.updatePeopleLog(peopleLog);
		} catch (Exception e) {
			return dealException(this, "updatePeopleLog", "更新数据错误", e);
		}
	}

	private void updatePeopleLogFileForAdd(Long peopleLogId,
			String[] attachFiles) throws Exception {
		if (null != attachFiles && attachFiles.length > 0) {
			for (String fileName : attachFiles) {
				if (fileName.indexOf(",") < 1) {
					addAttachFile(peopleLogId, fileName);
				}
			}
		}

	}

	@Override
	public PageInfo<PeopleLog> findPeopleLogForPageByOrgInternalCode4Bench(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sortField, String sord, String orgName) {
		try {
			if ("orgName".equals(sortField)) {
				sortField = "orgInternalCode";
			}
			return peopleLogDao.findPeopleLogForPageByOrgInternalCode4Bench(
					orgInternalCode, pageNum, pageSize, sortField, sord,
					orgName);
		} catch (Exception e) {
			return dealException(this,
					"findPeopleLogForPageByOrgInternalCode4Bench", "查找数据错误", e);
		}
	}

	@Override
	public List<PeopleLogAttachFiles> addAttachFileByPeopleLogId(
			Long peopleLogId, String[] attachFiles) {
		if (null == peopleLogId || null == attachFiles)
			return null;
		try {
			if (attachFiles.length > 0) {
				for (String attachFileName : attachFiles) {
					addAttachFile(peopleLogId, attachFileName);
				}
			}
			return peopleLogDao
					.findPeopleLogAttachFilesByPeopleLogId(peopleLogId);
		} catch (Exception e) {
			return dealException(this, "addAttachFileByPeopleLogId",
					"保存民情日志附件信息出现错误", e);
		}
	}

	private void addAttachFile(Long peopleLogId, String fileName)
			throws Exception {
		PeopleLogAttachFiles PeopleLogAttachFiles = new PeopleLogAttachFiles();
		PeopleLogAttachFiles.setPeopleLogId(peopleLogId);
		StoredFile storeFile = FileUtil.copyTmpFileToStoredFile(fileName,
				GridProperties.PEOPLELOG_ATTACHFILE);
		PeopleLogAttachFiles.setFileActualUrl(storeFile.getStoredFilePath()
				+ File.separator + storeFile.getStoredFileName());
		PeopleLogAttachFiles.setFileName(storeFile.getStoredTruthFileName());
		peopleLogDao.addPeopleLogAttachFiles(PeopleLogAttachFiles);
	}

	@Override
	public List<PeopleLogAttachFiles> findPeopleLogAttachFilesByPeopleLogId(
			Long peopleLogId) {
		return peopleLogDao.findPeopleLogAttachFilesByPeopleLogId(peopleLogId);
	}

	@Override
	public void deletePeopleLogAttachFileById(Long id) {
		PeopleLogAttachFiles attach = peopleLogDao
				.getPeopleLogAttachFileById(id);
		File attachFile = new File(FileUtil.getWebRoot() + File.separator
				+ attach.getFileActualUrl());
		if (attachFile.exists()) {
			attachFile.delete();
		}
		peopleLogDao.deletePeopleLogAttachFileById(id);
	}

	private void removePeopleLogAttachFiles(Long peopleLogId) {
		List<PeopleLogAttachFiles> attachs = peopleLogDao
				.findPeopleLogAttachFilesByPeopleLogId(peopleLogId);
		String webPath = FileUtil.getWebRoot() + File.separator;
		for (PeopleLogAttachFiles attach : attachs) {
			File attachFile = new File(webPath + attach.getFileActualUrl());
			if (attachFile.exists())
				attachFile.delete();
		}
		peopleLogDao.deletePeopleLogAttachFilesByPeopleLogId(peopleLogId);
	}

	@Override
	public PeopleLogAttachFiles getPeopleLogAttachFileById(Long id) {
		return peopleLogDao.getPeopleLogAttachFileById(id);
	}

	@Override
	public PeopleLog addPeopleLogForServiceRecord(PeopleLog peopleLog,
			String[] attachFiles) {
		Long serviceRecordId = peopleLog.getServiceRecordId();
		peopleLog = addPeopleLog(peopleLog);
		if (null != attachFiles && attachFiles.length > 0) {
			for (String attachFile : attachFiles) {
				if (attachFile.indexOf(",") > 0) {
					try {
						addAttachFileFromServiceRecord(peopleLog.getId(),
								serviceRecordId, attachFile);
					} catch (IOException e) {
						throw new BusinessValidationException("保存附件文件不成功");
					}
				} else {
					try {
						addAttachFile(peopleLog.getId(), attachFile);
					} catch (Exception e) {
						throw new BusinessValidationException("保存附件文件不成功");
					}
				}
			}
		}
		return peopleLog;
	}

	// FIXME 服务团队迁移
	// 此方法用于根据服务记录生成民情日志，暂未添加此功能，developPeopleLog.jsp Line 11
	private void addAttachFileFromServiceRecord(Long id, Long serviceRecordId,
			String attachFile) throws IOException {
		String[] fileNames = attachFile.split(",");
		/*
		 * ServiceRecordAttachment attachment = serviceRecordService
		 * .getServiceRecordAttachmentById(Long.valueOf(fileNames[0]));
		 */
		/*
		 * File fromFile = new File(FileUtil.getWebRoot() + File.separator +
		 * attachment.getFileActualUrl());
		 */
		String[] storedPaths = FileUtil.createStoreFilePath(
				GridProperties.PEOPLELOG_ATTACHFILE, fileNames[1]);
		String storedFileName = FileUtil.createStoreFileName(fileNames[1]);
		File storedFile = new File(storedPaths[0] + File.separator
				+ storedFileName);
		/* FileUtil.copyFile(fromFile, storedFile); */
		PeopleLogAttachFiles PeopleLogAttachFiles = new PeopleLogAttachFiles();
		PeopleLogAttachFiles.setPeopleLogId(id);
		PeopleLogAttachFiles.setFileActualUrl(storedPaths[1] + File.separator
				+ storedFileName);
		PeopleLogAttachFiles.setFileName(fileNames[1]);
		peopleLogDao.addPeopleLogAttachFiles(PeopleLogAttachFiles);
	}
}
