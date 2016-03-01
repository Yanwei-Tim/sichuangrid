package com.tianque.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.PartyConstructionFilesDao;
import com.tianque.domain.Organization;
import com.tianque.domain.PartyConstructionFiles;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.PartyConstructionFilesService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Service("partyConstructionFilesService")
public class PartyConstructionFilesServiceImpl extends AbstractBaseService
		implements PartyConstructionFilesService {
	@Qualifier("partyConstructionFilesValidator")
	@Autowired
	private DomainValidator<PartyConstructionFiles> domainValidator;
	@Autowired
	private PartyConstructionFilesDao partyConstructionFilesDao;
	@Autowired
	OrganizationDubboService organizationDubboService;

	@Override
	public PartyConstructionFiles addPartyConstructionFiles(
			PartyConstructionFiles partyConstructionFiles) {
		validatePartyConstructionFiles(partyConstructionFiles);
		try {
			// saveFileToServer(partyConstructionFiles);
			partyConstructionFiles.setCreateUserRealName(ThreadVariable
					.getSession().getUserRealName());
			partyConstructionFiles = partyConstructionFilesDao
					.addPartyConstructionFiles(partyConstructionFiles);
		} catch (Exception e) {
			return dealException(this, "addPartyConstructionFiles",
					"保存党建文件信息出现错误", e);
		}
		return partyConstructionFiles;
	}

	private void saveFileToServer(PartyConstructionFiles partyConstructionFiles)
			throws Exception {
		StoredFile storedFile = FileUtil.copyTmpFileToStoredFile(
				partyConstructionFiles.getFileName(),
				GridProperties.UPLOAD_FILE_FOLDER);

		partyConstructionFiles.setFileSize(storedFile.getFileSize());
		partyConstructionFiles.setUrl(storedFile.getStoredFilePath()
				+ File.separator + storedFile.getStoredFileName());
	}

	private void validatePartyConstructionFiles(
			PartyConstructionFiles partyConstructionFiles) {
		autoFillOrganizationInternalCode(partyConstructionFiles);
		ValidateResult partyConstructionFilesValidator = domainValidator
				.validate(partyConstructionFiles);
		if (partyConstructionFilesValidator.hasError()) {
			throw new BusinessValidationException(
					partyConstructionFilesValidator.getErrorMessages());
		}
		autoFillChinesePinyin(partyConstructionFiles);
	}

	@Override
	public PartyConstructionFiles getPartyConstructionFilesById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("ID不能为空");
		}
		return partyConstructionFilesDao.getPartyConstructionFilesById(id);
	}

	@Override
	public PageInfo<PartyConstructionFiles> findPartyConstructionFilesByOrgId(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, String dalei) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		} else {
			return partyConstructionFilesDao.findPartyConstructionFilesByOrgId(
					orgId, pageNum, pageSize, sidx, sord, dalei);
		}

	}

	@Override
	public PartyConstructionFiles updatePartyConstructionFiles(
			PartyConstructionFiles partyConstructionFiles) {
		validatePartyConstructionFiles(partyConstructionFiles);
		try {
			// PartyConstructionFiles
			// old=partyConstructionFilesDao.getPartyConstructionFilesById(partyConstructionFiles.getId());
			// if (
			// !old.getFileName().equals(partyConstructionFiles.getFileName()))
			// {
			// String oldUrl = partyConstructionFiles.getUrl();
			// saveFileToServer(partyConstructionFiles);
			// partyConstructionFiles = partyConstructionFilesDao
			// .updatePartyConstructionFiles(partyConstructionFiles);
			// deleteAttachFile(oldUrl);
			// } else {
			// partyConstructionFiles = partyConstructionFilesDao
			// .updatePartyConstructionFilesBaseInfo(partyConstructionFiles);
			// }
			partyConstructionFiles = partyConstructionFilesDao
					.updatePartyConstructionFilesBaseInfo(partyConstructionFiles);
		} catch (Exception e) {
			return dealException(this, "updatePartyConstructionFiles",
					"更改党建文件时出错", e);
		}
		return partyConstructionFiles;

	}

	@Override
	public boolean deletePartyConstructionFilesById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id不能为空");
		}
		PartyConstructionFiles partyConstructionFiles = partyConstructionFilesDao
				.getPartyConstructionFilesById(id);
		if (partyConstructionFiles != null) {
			partyConstructionFilesDao.deletePartyConstructionFiles(id);
			deleteAttachFile(partyConstructionFiles.getUrl());
		}
		return false;
	}

	private void deleteAttachFile(String fileUrl) {
		if (null != fileUrl && "".equals(fileUrl)) {
			File file = new File(FileUtil.getWebRoot() + File.separator
					+ fileUrl);
			if (file.exists())
				file.delete();
		}
	}

	@Override
	public void deletePartyConstructionFilessByIdList(List<Long> idList) {
		if (null == idList) {
			throw new BusinessValidationException("党建文件idList不能为空");
		}
		for (Long id : idList) {
			deletePartyConstructionFilesById(id);
		}

	}

	@Override
	public List<PartyConstructionFiles> findPartyConstructionFilesByorgIdAndTitle(
			Long orgId, String title, Integer pageNum, String sidx, String sord) {
		if (orgId == null) {
			return new ArrayList<PartyConstructionFiles>();
		} else {
			return partyConstructionFilesDao
					.searchPartyConstructionFilesByTitleAndOrgId(title, orgId,
							sidx, sord);
		}
	}

	@Override
	public boolean hasDuplicatePartyConstructionFilesByTitle(String title,
			Long orgId, Long exceptedId) {
		PartyConstructionFiles exsited = partyConstructionFilesDao
				.getPartyConstructionFilesByTitleAndOrgId(title, orgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	private void autoFillOrganizationInternalCode(
			PartyConstructionFiles partyConstructionFiles) {
		if (partyConstructionFiles.getOrganization() == null) {
			throw new BusinessValidationException("找不到指定的网格");
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(partyConstructionFiles.getOrganization()
							.getId());
			if (organization == null) {
				throw new BusinessValidationException("找不到指定的网格");
			}
			partyConstructionFiles.setOrgInternalCode(organization
					.getOrgInternalCode());
		}
	}

	private void autoFillChinesePinyin(
			PartyConstructionFiles partyConstructionFiles) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(partyConstructionFiles.getTitle());
		partyConstructionFiles.setFullPinyin((String) pinyin.get("fullPinyin"));
		partyConstructionFiles.setSimplePinyin((String) pinyin
				.get("simplePinyin"));
	}

	private PageInfo<PartyConstructionFiles> constructEmptyPageInfo() {
		PageInfo<PartyConstructionFiles> result = new PageInfo<PartyConstructionFiles>();
		result.setResult(new ArrayList<PartyConstructionFiles>());
		return result;
	}
}
