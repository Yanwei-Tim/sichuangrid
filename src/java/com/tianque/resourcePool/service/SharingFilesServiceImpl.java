package com.tianque.resourcePool.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.User;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.resourcePool.dao.SharingFilesDao;
import com.tianque.resourcePool.domain.DirectorySetting;
import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;

@Service("sharingFilesService")
@Transactional
public class SharingFilesServiceImpl extends LogableService implements
		SharingFilesService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SharingFilesDao sharingFilesDao;
	@Autowired
	private MyProfileAttachFileService myProfileAttachFileService;
	@Autowired
	private DirectorySettingService directorySettingService;
	@Autowired
	private PermissionService permissionService;

	@Override
	public PageInfo<MyProfile> findSharingFilesForList(Long orgId,
			Long resourcePoolTypeId, int searchType, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		PageInfo<MyProfile> pageInfo = sharingFilesDao.findSharingFilesForList(
				orgId, organizationDubboService.getSimpleOrgById(orgId)
						.getOrgInternalCode(), resourcePoolTypeId, searchType,
				pageNum, pageSize, sidx, sord);
		fillUserRealName(pageInfo.getResult());
		appendAttachFile(pageInfo);
		return pageInfo;
	}

	private void appendAttachFile(PageInfo<MyProfile> pageInfo) {
		if (pageInfo != null && pageInfo.getResult() != null) {
			for (MyProfile myProfile : pageInfo.getResult()) {
				DirectorySetting directorySetting = directorySettingService
						.findDirectorySettingById(myProfile
								.getResourcePoolType().getId());
				if (directorySetting != null) {
					myProfile.getResourcePoolType().setName(
							directorySetting.getName());
				}
				myProfile
						.setMyProfileAttachFile(myProfileAttachFileService
								.getMyProfileAttachFileByMyProfileId(myProfile
										.getId()));
			}
		}

	}

	@Override
	public PageInfo<MyProfile> fastSearchSharingFiles(Long resourcePoolTypeId,
			int searchType, String searchText, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		PageInfo<MyProfile> pageInfo = sharingFilesDao.fastSearchSharingFiles(
				resourcePoolTypeId, searchType, searchText, pageNum, pageSize,
				sidx, sord);
		fillUserRealName(pageInfo.getResult());
		appendAttachFile(pageInfo);
		return pageInfo;
	}

	@Override
	public PageInfo<MyProfile> fastSearchSharingFilesforMobile(
			Long resourcePoolTypeId, int searchType, String searchText,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		PageInfo<MyProfile> pageInfo = sharingFilesDao
				.fastSearchSharingFilesforMobile(resourcePoolTypeId,
						searchType, searchText, pageNum, pageSize, sidx, sord);
		fillUserRealName(pageInfo.getResult());
		appendAttachFile(pageInfo);
		return pageInfo;
	}

	@Override
	public PageInfo<MyProfile> searchSharingFiles(MyProfile myProfile,
			int searchType, Date startReleaseTime, Long resourcePoolTypeId,
			Date startCreateTime, Date startShareDate, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		List<Long> orgIdsList = null;
		if (myProfile.getOrganization().getOrgName() != null
				&& !"".equals(myProfile.getOrganization().getOrgName())
				&& myProfile.getOrganization().getId() == null) {
			OrganizationVo organizationVo = new OrganizationVo();
			organizationVo.setEqualToOrgName(myProfile.getOrganization()
					.getOrgName());
			orgIdsList = organizationDubboService
					.findOrgIdsBySearchVo(organizationVo);
		}
		PageInfo<MyProfile> pageInfo = sharingFilesDao.searchSharingFiles(
				myProfile, searchType, startReleaseTime, resourcePoolTypeId,
				startCreateTime, startShareDate, orgIdsList, pageNum, pageSize,
				sidx, sord);
		fillUserRealName(pageInfo.getResult());
		appendAttachFile(pageInfo);
		return pageInfo;
	}

	private void fillUserRealName(List<MyProfile> myProfiles) {
		if (myProfiles == null || myProfiles.size() < 1) {
			return;
		}
		for (MyProfile myProfile : myProfiles) {
			User user = permissionService.getSimpleUserById(myProfile
					.getUserId());
			if (user != null) {
				myProfile.setShareUserRealName(user.getUserName());
			}
		}

	}

	@Override
	public PageInfo<MyProfile> findSharingFilesForListByIds(String[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("查询文件共享资料信息失败");
		}
		PageInfo<MyProfile> pageInfo = sharingFilesDao
				.findSharingFilesForListByIds(ids);
		fillUserRealName(pageInfo.getResult());
		appendAttachFile(pageInfo);
		return pageInfo;
	}
}
