package com.tianque.partyBuilding.activityRecords.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.activityRecords.dao.ActivityRecordsDao;
import com.tianque.partyBuilding.activityRecords.domain.ActivityRecords;
import com.tianque.partyBuilding.activityRecords.domain.ActivityRecordsAttachFiles;
import com.tianque.partyBuilding.activityRecords.domain.vo.ActivityRecordsVo;
import com.tianque.partyBuilding.activityRecords.service.ActivityRecordsService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("activityRecordsService")
@Transactional
public class ActivityRecordsServiceImpl extends AbstractBaseService implements
		ActivityRecordsService {
	@Autowired
	ActivityRecordsDao activityRecordsDao;
	@Autowired
	OrganizationDubboService organizationDubboService;
	@Autowired
	private ValidateHelper validateHelper;

	@Override
	public PageInfo<ActivityRecords> findActivityRecordsForPageByOrgId(
			Long orgId, String orgCode, String organizationType,
			Long organizationId, Integer pageNum, Integer pageSize,
			String sortField, String sord) {
		try {
			if (organizationType == null || "".equals(organizationType.trim())) {
				throw new BusinessValidationException("参数错误");
			} else {
				Organization organization = organizationDubboService
						.getSimpleOrgById(orgId);
				if (null == organization) {
					return constructEmptyPageInfo();
				} else {
					PageInfo<ActivityRecords> ActivityRecords = this.activityRecordsDao
							.findActivityRecordsForPageByOrgId(orgId, orgCode,
									organizationType, organizationId, pageNum,
									pageSize, sortField, sord);
					if (null != ActivityRecords.getResult()
							&& ActivityRecords.getResult().size() > 0) {
						for (ActivityRecords activityRecord : ActivityRecords
								.getResult()) {
							if (activityRecord.getIsAttachment())
								activityRecord
										.setActivityRecordFiles(findActivityRecordAttachFilesByActivityRecordId(activityRecord
												.getId()));
						}
					}
					return ActivityRecords;
				}
			}
		} catch (Exception e) {

			return dealException("ActivityRecordsServiceImpl",
					"findActivityRecordsForPageByOrgId", "分页查询组织活动出现错误", e);
		}
	}

	@Override
	public List<ActivityRecordsAttachFiles> findActivityRecordAttachFilesByActivityRecordId(
			Long activityRecordId) {
		if (activityRecordId == null) {
			throw new BusinessValidationException("参数错误");
		}
		return activityRecordsDao
				.findActivityRecordAttachFilesByActivityRecordId(activityRecordId);
	}

	private PageInfo<ActivityRecords> constructEmptyPageInfo() {
		PageInfo<ActivityRecords> partyOrgActivityPageInfo = new PageInfo<ActivityRecords>();
		partyOrgActivityPageInfo.setResult(new ArrayList<ActivityRecords>());
		return partyOrgActivityPageInfo;
	}

	@Override
	public ActivityRecordsAttachFiles getActivityRecordsAttachFilesById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		return activityRecordsDao.getActivityRecordsAttachFilesById(id);
	}

	@Override
	public void deleteActivityRecordsAttachFilesById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		ActivityRecordsAttachFiles attach = activityRecordsDao
				.getActivityRecordsAttachFilesById(id);
		File attachFile = new File(FileUtil.getWebRoot() + File.separator
				+ attach.getFileActualUrl());
		if (attachFile.exists()) {
			attachFile.delete();
		}
		activityRecordsDao.deleteActivityRecordsAttachFilesById(id);
	}

	@Override
	public ActivityRecords addActivityRecords(ActivityRecords activityRecord) {
		if (null == activityRecord) {
			throw new BusinessValidationException("参数错误");
		}
		validateActivityRecord(activityRecord);
		return activityRecordsDao.addActivityRecords(activityRecord);
	}

	private void validateActivityRecord(ActivityRecords activityRecord) {
		if (null == activityRecord.getOrganizationId()) {
			throw new BusinessValidationException("必须选择已有的组织");
		}
		if (null == activityRecord.getActivityDate()) {
			throw new BusinessValidationException("必须选择活动时间");
		}

		if (!StringUtil.isStringAvaliable(activityRecord.getActivityPlace())) {
			throw new BusinessValidationException("必须输入活动地点");
		} else if (validateHelper.illegalStringLength(0, 280, activityRecord
				.getActivityPlace())) {
			throw new BusinessValidationException("活动地点不能大于280个字");
		}
		if (!StringUtil.isStringAvaliable(activityRecord.getActivityTheme())) {
			throw new BusinessValidationException("必须输入活动主题");
		} else if (validateHelper.illegalStringLength(0, 148, activityRecord
				.getActivityTheme())) {
			throw new BusinessValidationException("活动主题不能大于148个字");
		}
		if (validateHelper.illegalStringLength(0, 248, activityRecord
				.getOrganizer())) {
			throw new BusinessValidationException("组织者不能大于248个字");
		}
		if (validateHelper.illegalStringLength(0, 248, activityRecord
				.getParticipant())) {
			throw new BusinessValidationException("参与者不能大于248个字");
		}
		if (!StringUtil.isStringAvaliable(activityRecord.getDetails())) {
			throw new BusinessValidationException("必须输入活动内容");
		}
	}

	@Override
	public List<ActivityRecordsAttachFiles> addAttachFileByActivityRecordsId(
			Long activityRecordId, String[] attachFiles) {
		try {
			if (null != activityRecordId && null != attachFiles
					&& attachFiles.length > 0) {
				for (String fileName : attachFiles) {
					this.addAttachFile(activityRecordId, fileName);
				}
			}
			return this
					.findActivityRecordAttachFilesByActivityRecordId(activityRecordId);
		} catch (Exception e) {
			return dealException("ActivityRecordsServiceImpl",
					"addAttachFileByActivityRecordsId", "保存组织活动记录附件信息出现错误", e);
		}
	}

	private void addAttachFile(Long activityRecordId, String fileName)
			throws Exception {
		ActivityRecordsAttachFiles activityRecordsAttachFiles = new ActivityRecordsAttachFiles();
		activityRecordsAttachFiles.setActivityRecordId(activityRecordId);
		StoredFile storedFile = FileUtil.copyTmpFileToStoredFile(fileName,
				GridProperties.ACTIVITYRECORDS_ATTACHFILE);
		activityRecordsAttachFiles.setFileActualUrl(storedFile
				.getStoredFilePath()
				+ File.separator + storedFile.getStoredFileName());
		activityRecordsAttachFiles.setFileName(storedFile
				.getStoredTruthFileName());
		this.activityRecordsDao
				.addActivityRecordsAttachFiles(activityRecordsAttachFiles);
	}

	@Override
	public List findOrganizationByType(Long orgId, String orgInternalCode,
			String organizationType, String organizationName) {
		if (organizationType == null || "".equals(organizationType.trim())) {
			throw new BusinessValidationException("参数错误");
		}

		return activityRecordsDao.findOrganizationByType(orgId,
				orgInternalCode, organizationType, organizationName);
	}

	@Override
	public ActivityRecords getActivityRecordById(Long id,
			String organizationType) {
		try {
			ActivityRecords activityRecords = activityRecordsDao
					.getActivityRecordById(id);
			activityRecords.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							activityRecords.getOrganization().getId(),
							organizationDubboService));
			activityRecords.setOrganizationName(activityRecordsDao
					.findOrganizationById(activityRecords.getOrganizationId(),
							organizationType).getOrganizationName());
			activityRecords
					.setActivityRecordFiles(findActivityRecordAttachFilesByActivityRecordId(activityRecords
							.getId()));
			return activityRecords;
		} catch (Exception e) {
			return dealException("ActivityRecordsServiceImpl",
					"getActivityRecordById", "查找数据错误", e);
		}
	}

	@Override
	public void deleteActivityRecordByIds(List<Long> idList) {
		try {
			if (idList == null) {
				throw new BusinessValidationException("删除组织活动idList不能为空");
			}
			for (Long id : idList) {
				if (id == null || id == 0L) {
					throw new BusinessValidationException("删除组织活动id不能为空");
				}
				ActivityRecords activityRecords = activityRecordsDao
						.getActivityRecordById(id);
				if (activityRecords.getIsAttachment()) {
					removeActivityRecordAttachFiles(activityRecords.getId());
				}

				activityRecordsDao.deleteActivityRecordById(id);
			}
		} catch (Exception e) {
			dealException("ActivityRecordsServiceImpl",
					"deleteActivityRecordByIds", "删除数据错误", e);
		}

	}

	private void removeActivityRecordAttachFiles(Long activityRecordId) {
		List<ActivityRecordsAttachFiles> attachs = activityRecordsDao
				.findActivityRecordAttachFilesByActivityRecordId(activityRecordId);
		String webPath = FileUtil.getWebRoot() + File.separator;
		for (ActivityRecordsAttachFiles attach : attachs) {
			File attachFile = new File(webPath + attach.getFileActualUrl());
			if (attachFile.exists())
				attachFile.delete();
		}
		activityRecordsDao
				.deleteActivityRecordAttachFilesByActivityRecordId(activityRecordId);
	}

	@Override
	public ActivityRecords updateActivityRecord(
			ActivityRecords activityRecords, String[] attachFiles) {
		try {
			validateActivityRecord(activityRecords);
			updateActivityRecordsFileForAdd(activityRecords.getId(),
					attachFiles);
			return activityRecordsDao.updateActivityRecord(activityRecords);
		} catch (Exception e) {
			return dealException("ActivityRecordsServiceImpl",
					"updateActivityRecord", "更新数据错误", e);

		}
	}

	private void updateActivityRecordsFileForAdd(Long activityRecordId,
			String[] attachFiles) throws Exception {
		// 获取所有已保存的附件
		List<ActivityRecordsAttachFiles> files = activityRecordsDao
				.findActivityRecordAttachFilesByActivityRecordId(activityRecordId);
		// 所有已保存的附件名集合
		List<String> fileNames = new ArrayList<String>();
		if (files != null && files.size() > 0) {
			for (ActivityRecordsAttachFiles file : files) {
				fileNames.add(file.getFileName());
			}
		}
		if (null != attachFiles && attachFiles.length > 0) {
			for (String fileName : attachFiles) {
				if (!fileNames.contains(fileName)) {// 上传的附件未保存，进行保存
					addAttachFile(activityRecordId, fileName);
				}
			}
		}

	}

	@Override
	public PageInfo searchActivityRecordsBySearchVo(Integer pageNum,
			Integer pageSize, ActivityRecordsVo activityRecordsVo) {
		try {
			if (activityRecordsVo == null) {
				return constructEmptyPageInfo();
			}
			return this.activityRecordsDao.searchActivityRecordsBySearchVo(
					pageNum, pageSize, activityRecordsVo);
		} catch (Exception e) {
			return dealException("ActivityRecordsServiceImpl",
					"searchActivityRecordsBySearchVo", "分页查询组织活动信息出现错误", e);
		}
	}

	@Override
	public Integer countActivityRecordByOrgIdOrgInternalCode(Long internalId,
			String orgInternalCode, String organizationType) {

		try {
			OrganizationVo organizationVo = new OrganizationVo();
			organizationVo.setOrgInternalCode(orgInternalCode);
			organizationVo.setOrgLevelId(internalId);
			List<Long> orgIdList = organizationDubboService
					.findOrgIdsBySearchVo(organizationVo);
			return activityRecordsDao
					.countActivityRecordByOrgIdOrgInternalCode(orgIdList,
							organizationType);
		} catch (Exception e) {
			return dealException("ActivityRecordsServiceImpl",
					"countActivityRecordByOrgIdOrgInternalCode",
					"统计辖区组织活动信息数量出现错误", e);
		}
	}

	@Override
	public void deleteAllActivityRecordAndAttachFilesByOrganizationIdsAndOrganizationType(
			Long[] organizationIds, String organizationType) {
		try {
			if (organizationIds == null || organizationIds.length == 0
					|| !StringUtil.isStringAvaliable(organizationType)) {
				throw new BusinessValidationException("参数错误");
			}
			for (Long organizationId : organizationIds) {
				List<ActivityRecords> list = this
						.findAllActivityRecordByOrganizationIdAndOrganizationType(
								organizationId, organizationType);
				if (list != null && list.size() > 0) {
					for (ActivityRecords activityRecord : list) {
						if (activityRecord.getIsAttachment()) {
							removeActivityRecordAttachFiles(activityRecord
									.getId());
						}

						activityRecordsDao
								.deleteActivityRecordById(activityRecord
										.getId());
					}
				}
			}

		} catch (Exception e) {
			dealException(
					"ActivityRecordsServiceImpl",
					"deleteAllActivityRecordAndAttachFilesByOrganizationIdsAndOrganizationType",
					"删除数据错误", e);
		}
	}

	@Override
	public List<ActivityRecords> findAllActivityRecordByOrganizationIdAndOrganizationType(
			Long OrganizationId, String organizationType) {
		try {
			if (OrganizationId == null
					|| !StringUtil.isStringAvaliable(organizationType)) {
				throw new BusinessValidationException("参数错误");
			}
			return this.activityRecordsDao
					.findAllActivityRecordByOrganizationIdAndOrganizationType(
							OrganizationId, organizationType);
		} catch (Exception e) {
			return dealException("ActivityRecordsServiceImpl",
					"findAllActivityRecordByOrganizationIdAndOrganizationType",
					"查询数据错误", e);
		}

	}
}
