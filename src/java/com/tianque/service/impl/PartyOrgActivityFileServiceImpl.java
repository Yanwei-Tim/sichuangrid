package com.tianque.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.dao.PartyOrgActivityFileDao;
import com.tianque.domain.PartyOrgActivityFile;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.PartyOrgActivityFileService;

@Service("partyOrgActivityFileService")
@Transactional
public class PartyOrgActivityFileServiceImpl extends LogableService implements
		PartyOrgActivityFileService {

	private static Logger logger = LoggerFactory
			.getLogger(PartyOrgActivityFileServiceImpl.class);

	@Autowired
	private PartyOrgActivityFileDao partyOrgActivityFileDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgActivityFileService#addPartyOrgActivityFile
	 * (java.lang.Long, java.lang.String[])
	 */
	@Override
	public List<PartyOrgActivityFile> addPartyOrgActivityFile(
			Long orgActivityId, String[] attachFiles) {
		try {
			if (null != orgActivityId && null != attachFiles
					&& attachFiles.length > 0) {
				for (String fileName : attachFiles) {
					this.addAttachFile(orgActivityId, fileName);
				}
			}
			return this.partyOrgActivityFileDao
					.getPartyOrgActivityFileByOrgActivityId(orgActivityId);
		} catch (Exception e) {
			throw new ServiceValidationException("保存党组织活动记录附件信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgActivityFileService#getPartyOrgActivityFileById
	 * (java.lang.Long)
	 */
	@Override
	public PartyOrgActivityFile getPartyOrgActivityFileById(Long id) {
		if (null == id || id < 0L) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			return this.partyOrgActivityFileDao.getPartyOrgActivityFileById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("获取党组织活动记录附件信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.PartyOrgActivityFileService#
	 * getPartyOrgActivityFileByOrgActivityId(java.lang.Long)
	 */
	@Override
	public List<PartyOrgActivityFile> getPartyOrgActivityFileByOrgActivityId(
			Long orgActivityId) {
		if (null == orgActivityId || orgActivityId < 0L) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			return this.partyOrgActivityFileDao
					.getPartyOrgActivityFileByOrgActivityId(orgActivityId);
		} catch (Exception e) {
			return dealException(this,
					"getPartyOrgActivityFileByOrgActivityId",
					"获取党组织活动记录附件信息出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PartyOrgActivityFileService#updatePartyOrgActivityFile
	 * (java.lang.Long, java.lang.String[])
	 */
	@Override
	public List<PartyOrgActivityFile> updatePartyOrgActivityFile(
			Long orgActivityId, String[] attachFiles) {
		try {
			if (null != orgActivityId && attachFiles != null
					&& attachFiles.length > 0) {
				List<PartyOrgActivityFile> partyOrgActivityFileList = this.partyOrgActivityFileDao
						.getPartyOrgActivityFileByOrgActivityId(orgActivityId);
				List<String> partyOrgActivityFileName = new ArrayList<String>();
				for (PartyOrgActivityFile partyOrgActivityFile : partyOrgActivityFileList) {
					partyOrgActivityFileName.add(partyOrgActivityFile
							.getFileName());
				}
				for (String fileName : attachFiles) {
					if (!partyOrgActivityFileName.contains(fileName)) {
						this.addAttachFile(orgActivityId, fileName);
					}
				}
			}
			return this.partyOrgActivityFileDao
					.getPartyOrgActivityFileByOrgActivityId(orgActivityId);
		} catch (Exception e) {

			return dealException(this, "updatePartyOrgActivityFile",
					"更新党组织活动记录附件信息出现错误", e);

		}
	}

	private void addAttachFile(Long orgActivityId, String fileName)
			throws Exception {
		PartyOrgActivityFile partyOrgActivityFile = new PartyOrgActivityFile();
		partyOrgActivityFile.setOrgActivityId(orgActivityId);
		StoredFile storedFile = FileUtil.copyTmpFileToStoredFile(fileName,
				GridProperties.DAILYLOG_PATH);
		partyOrgActivityFile.setFileSize(storedFile.getFileSize());
		partyOrgActivityFile.setFileActualUrl(storedFile.getStoredFilePath()
				+ File.separator + storedFile.getStoredFileName());
		partyOrgActivityFile.setFileName(storedFile.getStoredTruthFileName());
		this.partyOrgActivityFileDao
				.addPartyOrgActivityFile(partyOrgActivityFile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.PartyOrgActivityFileService#
	 * deletePartyOrgActivityFileByOrgActivityId(java.util.List)
	 */
	@Override
	public void deletePartyOrgActivityFileByOrgActivityId(
			List<Long> orgActivityIds) {
		if (orgActivityIds.isEmpty()) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			for (Long orgActivityId : orgActivityIds) {
				List<PartyOrgActivityFile> orgActivityFileList = this.partyOrgActivityFileDao
						.getPartyOrgActivityFileByOrgActivityId(orgActivityId);
				for (PartyOrgActivityFile partyOrgActivityFile : orgActivityFileList) {
					this.deleteAttachFile(partyOrgActivityFile);
				}
				this.partyOrgActivityFileDao
						.deletePartyOrgActivityFileByOrgActivityId(orgActivityId);
			}
		} catch (Exception e) {

			dealException(this, "deletePartyOrgActivityFileByOrgActivityId",
					"删除党组织活动记录附件信息出现错误", e);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.PartyOrgActivityFileService#
	 * deletePartyOrgActivityFileById(java.lang.Long)
	 */
	@Override
	public void deletePartyOrgActivityFileById(Long id) {
		if (null == id || id < 0L) {
			throw new BusinessValidationException("传入参数为空");
		}
		try {
			PartyOrgActivityFile partyOrgActivityFile = this
					.getPartyOrgActivityFileById(id);
			if (null != partyOrgActivityFile) {
				this.deleteAttachFile(partyOrgActivityFile);
			}
			this.partyOrgActivityFileDao.deletePartyOrgActivityFileById(id);
		} catch (Exception e) {
			dealException(this, "deletePartyOrgActivityFileById",
					"删除党组织活动记录附件信息出现错误", e);
		}
	}

	private void deleteAttachFile(PartyOrgActivityFile partyOrgActivityFile) {
		if (null != partyOrgActivityFile) {
			File file = new File(partyOrgActivityFile.getFileActualUrl());
			if (file.exists())
				file.delete();
		}
	}

}
