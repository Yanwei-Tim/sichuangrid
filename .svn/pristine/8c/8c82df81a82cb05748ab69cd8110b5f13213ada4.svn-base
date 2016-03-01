package com.tianque.partyBuilding.organizations.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.organizations.dao.RegionalBuildInfoDao;
import com.tianque.partyBuilding.organizations.domain.ClaimRegionalBuildInfo;
import com.tianque.partyBuilding.organizations.domain.RegionalBuildInfo;
import com.tianque.partyBuilding.organizations.domain.RegionalBuildInfoAttachFile;
import com.tianque.partyBuilding.organizations.domain.vo.RegionalBuildInfoVo;
import com.tianque.partyBuilding.organizations.service.RegionalBuildInfoService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 区域联建情况业务处理实现
 * */
@Repository("regionalBuildInfoService")
@Transactional
public class RegionalBuildInfoServiceImpl extends AbstractBaseService implements
		RegionalBuildInfoService {

	@Autowired
	@Qualifier("regionalBuildInfoValidator")
	private DomainValidator<RegionalBuildInfo> domainValidator;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private RegionalBuildInfoDao regionalBuildInfoDao;

	@Override
	public PageInfo<RegionalBuildInfo> findRegionalBuildInfoBySearchVo(
			RegionalBuildInfoVo regionalBuildInfoVo, Integer pageNum,
			Integer pageSize, String sortField, String sord) {
		try {
			if (regionalBuildInfoVo.getOrganization().getId() == null) {
				throw new BusinessValidationException("参数错误");
			} else {
				Organization organization = organizationDubboService
						.getSimpleOrgById(regionalBuildInfoVo.getOrganization()
								.getId());
				if (null == organization) {
					return constructEmptyPageInfo();
				} else {
					PageInfo<RegionalBuildInfo> regionalBuildInfos = this.regionalBuildInfoDao
							.findRegionalBuildInfoPagerBySearchVo(
									regionalBuildInfoVo, pageNum, pageSize,
									sortField, sord);

					if (null != regionalBuildInfos.getResult()
							&& regionalBuildInfos.getResult().size() > 0) {
						for (RegionalBuildInfo regionalBuildInfo : regionalBuildInfos
								.getResult()) {
							if (regionalBuildInfo.getIsAttachment())
								regionalBuildInfo
										.setRegionalBuildInfoAttachFiles(findRegionalBuildInfoAttachFilesByRegionalBuildInfoId(regionalBuildInfo
												.getId()));
							regionalBuildInfo.setIsClaim(regionalBuildInfoDao
									.regionalBuildInfoIsClaim(regionalBuildInfo
											.getId()));
						}
					}
					return regionalBuildInfos;
				}
			}
		} catch (Exception e) {
			return dealException(this.getClass().getSimpleName(),
					"findRegionalBuildInfoBySearchVo", "分页查询区域联建情况出现错误", e);
		}
	}

	@Override
	public List<RegionalBuildInfoAttachFile> findRegionalBuildInfoAttachFilesByRegionalBuildInfoId(
			Long regionalBuildInfoId) {
		try {
			if (regionalBuildInfoId == null) {
				throw new BusinessValidationException("参数错误");
			}
			return regionalBuildInfoDao
					.findRegionalBuildInfoAttachFilesByRegionalBuildInfoId(regionalBuildInfoId);
		} catch (Exception e) {
			return dealException(this.getClass().getSimpleName(),
					"findRegionalBuildInfoAttachFilesByRegionalBuildInfoId",
					"查询区域联建情况附件出现错误", e);
		}
	}

	private PageInfo<RegionalBuildInfo> constructEmptyPageInfo() {
		PageInfo<RegionalBuildInfo> regionalBuildInfoPageInfos = new PageInfo<RegionalBuildInfo>();
		regionalBuildInfoPageInfos
				.setResult(new ArrayList<RegionalBuildInfo>());
		return regionalBuildInfoPageInfos;
	}

	@Override
	public RegionalBuildInfo getRegionalBuildInfoById(Long id) {
		RegionalBuildInfo regionalBuildInfo;
		try {
			if (id == null) {
				throw new BusinessValidationException("参数错误");
			}
			regionalBuildInfo = this.regionalBuildInfoDao
					.getRegionalBuildInfoById(id);
			if (regionalBuildInfo.getIsAttachment()) {
				regionalBuildInfo
						.setRegionalBuildInfoAttachFiles(findRegionalBuildInfoAttachFilesByRegionalBuildInfoId(regionalBuildInfo
								.getId()));
			}
		} catch (Exception e) {
			return dealException(this.getClass().getSimpleName(),
					"getRegionalBuildInfoById", "查询区域联建情况出现错误", e);
		}
		return regionalBuildInfo;
	}

	@Override
	public RegionalBuildInfoAttachFile getRegionalBuildInfoAttachFileById(
			Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		return regionalBuildInfoDao.getRegionalBuildInfoAttachFilesById(id);
	}

	@Override
	public void deleteRegionalBuildInfoAttachFileById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		RegionalBuildInfoAttachFile attach = regionalBuildInfoDao
				.getRegionalBuildInfoAttachFilesById(id);
		File attachFile = new File(FileUtil.getWebRoot() + File.separator
				+ attach.getFileActualUrl());
		if (attachFile.exists()) {
			attachFile.delete();
		}
		regionalBuildInfoDao.deleteRegionalBuildInfoAttachFilesById(id);

	}

	@Override
	public RegionalBuildInfo addRegionalBuildInfo(
			RegionalBuildInfo regionalBuildInfo) {
		if (null == regionalBuildInfo) {
			throw new BusinessValidationException("参数错误");
		}
		validateRegionalBuildInfo(regionalBuildInfo);
		return regionalBuildInfoDao.addRegionalBuildInfo(regionalBuildInfo);
	}

	private void validateRegionalBuildInfo(RegionalBuildInfo regionalBuildInfo) {
		ValidateResult baseDataValidator = domainValidator
				.validate(regionalBuildInfo);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(baseDataValidator
					.getErrorMessages());
		}
	}

	@Override
	public List<RegionalBuildInfoAttachFile> addAttachFileByRegionalBuildInfoId(
			Long regionalBuildInfoId, String[] attachFiles) {
		try {
			if (null != regionalBuildInfoId && null != attachFiles
					&& attachFiles.length > 0) {
				for (String fileName : attachFiles) {
					this.addAttachFile(regionalBuildInfoId, fileName);
				}
			}
			return this
					.findRegionalBuildInfoAttachFilesByRegionalBuildInfoId(regionalBuildInfoId);
		} catch (Exception e) {
			return dealException(this.getClass().getSimpleName(),
					"addAttachFileByRegionalBuildInfoId", "保存区域联建情况附件信息出现错误", e);
		}
	}

	private void addAttachFile(Long regionalBuildInfoId, String fileName)
			throws Exception {
		RegionalBuildInfoAttachFile regionalBuildInfoAttachFile = new RegionalBuildInfoAttachFile();
		regionalBuildInfoAttachFile.setRegionalBuildInfoId(regionalBuildInfoId);
		StoredFile storedFile = FileUtil.copyTmpFileToStoredFile(fileName,
				GridProperties.ACTIVITYRECORDS_ATTACHFILE);
		regionalBuildInfoAttachFile.setFileActualUrl(storedFile
				.getStoredFilePath()
				+ File.separator + storedFile.getStoredFileName());
		regionalBuildInfoAttachFile.setFileName(storedFile
				.getStoredTruthFileName());
		this.regionalBuildInfoDao
				.addRegionalBuildInfoAttachFile(regionalBuildInfoAttachFile);
	}

	@Override
	public RegionalBuildInfo updateRegionalBuildInfo(
			RegionalBuildInfo regionalBuildInfo, String[] attachFiles) {

		try {
			validateRegionalBuildInfo(regionalBuildInfo);
			updateRegionalBuildInfoFileForAdd(regionalBuildInfo.getId(),
					attachFiles);
			return regionalBuildInfoDao
					.updateRegionalBuildInfo(regionalBuildInfo);
		} catch (Exception e) {
			return dealException(this.getClass().getSimpleName(),
					"updateRegionalBuildInfo", "更新数据错误", e);
		}
	}

	private void updateRegionalBuildInfoFileForAdd(Long regionalBuildInfoId,
			String[] attachFiles) throws Exception {
		List<RegionalBuildInfoAttachFile> files = regionalBuildInfoDao
				.findRegionalBuildInfoAttachFilesByRegionalBuildInfoId(regionalBuildInfoId);
		// 所有保存的附件名集合
		List<String> fileNames = new ArrayList<String>();
		if (files != null && files.size() > 0) {
			for (RegionalBuildInfoAttachFile file : files) {
				fileNames.add(file.getFileName());
			}
		}
		if (null != attachFiles && attachFiles.length > 0) {
			for (String fileName : attachFiles) {
				if (!fileNames.contains(fileName)) {// 上传的附件未保存，进行保存
					addAttachFile(regionalBuildInfoId, fileName);
				}
			}
		}

	}

	@Override
	public void deleteRegionalBuildInfoByIds(List<Long> idList) {
		try {
			if (idList == null) {
				throw new BusinessValidationException("删除区域联建情况idList不能为空");
			}
			for (Long id : idList) {
				if (id == null || id == 0L) {
					throw new BusinessValidationException("删除区域联建情况id不能为空");
				}
				RegionalBuildInfo regionalBuildInfo = regionalBuildInfoDao
						.getRegionalBuildInfoById(id);
				if (regionalBuildInfo.getIsAttachment()) {
					removeRegionalBuildInfoAttachFiles(regionalBuildInfo
							.getId());
				}
				regionalBuildInfoDao
						.deleteClaimRegionalBuildInfoByRegionalBuildInfoId(regionalBuildInfo
								.getId());
				regionalBuildInfoDao.deleteRegionalBuildInfoById(id);
			}
		} catch (Exception e) {
			dealException(this.getClass().getSimpleName(),
					"deleteRegionalBuildInfoByIds", "删除数据错误", e);
		}
	}

	private void removeRegionalBuildInfoAttachFiles(Long regionalBuildInfoId) {
		List<RegionalBuildInfoAttachFile> attachs = regionalBuildInfoDao
				.findRegionalBuildInfoAttachFilesByRegionalBuildInfoId(regionalBuildInfoId);
		String webPath = FileUtil.getWebRoot() + File.separator;
		for (RegionalBuildInfoAttachFile attach : attachs) {
			File attachFile = new File(webPath + attach.getFileActualUrl());
			if (attachFile.exists())
				attachFile.delete();
		}
		regionalBuildInfoDao
				.deleteRegionalBuildInfoAttachFileByRegionalBuildInfoId(regionalBuildInfoId);
	}

	@Override
	public ClaimRegionalBuildInfo addClaimRegionalBuildInfo(
			ClaimRegionalBuildInfo claimRegionalBuildInfo) {
		try {
			if (claimRegionalBuildInfo == null
					|| claimRegionalBuildInfo.getRegionalBuildInfoId() == null) {
				throw new BusinessValidationException("参数错误");
			}
			return regionalBuildInfoDao
					.addClaimRegionalBuildInfo(claimRegionalBuildInfo);
		} catch (Exception e) {
			return dealException(this.getClass().getSimpleName(),
					"addClaimRegionalBuildInfo", "新增区域联建认领数据错误", e);
		}
	}

	@Override
	public ClaimRegionalBuildInfo updateClaimRegionalBuildInfo(
			ClaimRegionalBuildInfo claimRegionalBuildInfo) {
		try {
			if (claimRegionalBuildInfo == null
					|| claimRegionalBuildInfo.getRegionalBuildInfoId() == null) {
				throw new BusinessValidationException("参数错误");
			}
			return regionalBuildInfoDao
					.updateClaimRegionalBuildInfo(claimRegionalBuildInfo);
		} catch (Exception e) {
			return dealException(this.getClass().getSimpleName(),
					"updateClaimRegionalBuildInfo", "修改区域联建认领数据错误", e);
		}
	}

	@Override
	public PageInfo<ClaimRegionalBuildInfo> findClaimRegionalBuildInfoByRegionalBuildInfoId(
			Long regionalBuildInfoId, Integer pageNum, Integer pageSize,
			String sortField, String sord) {

		try {

			PageInfo<ClaimRegionalBuildInfo> claimRegionalBuildInfos = this.regionalBuildInfoDao
					.findClaimRegionalBuildInfoByRegionalBuildInfoId(
							regionalBuildInfoId, pageNum, pageSize, sortField,
							sord);

			return claimRegionalBuildInfos;

		} catch (Exception e) {
			return dealException(this.getClass().getSimpleName(),
					"findClaimRegionalBuildInfoByRegionalBuildInfoId",
					"分页查询区域联建情况的认领情况出现错误", e);
		}
	}

	@Override
	public void deleteClaimRegionalBuildInfoById(Long id) {
		try {
			if (id == null) {
				throw new BusinessValidationException("参数错误");
			}
			regionalBuildInfoDao.deleteClaimRegionalBuildInfoById(id);
		} catch (Exception e) {

			dealException(this.getClass().getSimpleName(),
					"deleteClaimRegionalBuildInfoById", "删除区域联建认领数据错误", e);
		}
	}

	@Override
	public ClaimRegionalBuildInfo getClaimRegionalBuildInfoById(Long id) {
		try {
			if (id == null) {
				throw new BusinessValidationException("参数错误");
			}
			return regionalBuildInfoDao.getClaimRegionalBuildInfoById(id);
		} catch (Exception e) {

			return dealException(this.getClass().getSimpleName(),
					"getClaimRegionalBuildInfoById", "查询区域联建认领数据错误", e);
		}
	}

	@Override
	public Integer countRegionalBuildInfoByOrgIdOrOrgInternalCode(
			Long internalId, String orgInternalCode) {
		try {
			OrganizationVo organizationVo = new OrganizationVo();
			organizationVo.setOrgInternalCode(orgInternalCode);
			organizationVo.setOrgLevelId(internalId);
			List<Long> orgIdsList = organizationDubboService
					.findOrgIdsBySearchVo(organizationVo);
			return regionalBuildInfoDao
					.countRegionalBuildInfoByOrgIdOrOrgInternalCode(orgIdsList);
		} catch (Exception e) {
			return dealException(this.getClass().getSimpleName(),
					"countRegionalBuildInfoByOrgIdOrOrgInternalCode",
					"统计区域联建数据错误", e);
		}
	}

}
