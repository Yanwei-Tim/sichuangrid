package com.tianque.resourcePool.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.resourcePool.dao.MyProfileDao;
import com.tianque.resourcePool.domain.DirectorySetting;
import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.resourcePool.domain.MyProfileAttachFile;
import com.tianque.resourcePool.domain.UserHasMyProfilePermission;
import com.tianque.service.TemporaryPopulationService;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.viewObject.service.ViewObjectService;
import com.tianque.viewObject.vo.ViewObjectVo;

@Service("myProfileService")
@Transactional
public class MyProfileServiceImpl extends LogableService implements
		MyProfileService {
	@Autowired
	private MyProfileDao myProfileDao;
	@Autowired
	private MyProfileAttachFileService myProfileAttachFileService;
	@Autowired
	private DirectorySettingService directorySettingService;
	@Autowired
	private UserHasMyProfilePermissionService userHasMyProfilePermissionService;
	@Autowired
	private ViewObjectService viewObjectService;
	@Autowired
	private TemporaryPopulationService temporaryPopulationService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public MyProfile addMyProfile(MyProfile myProfile) {
		return myProfileDao.addMyProfile(myProfile);
	}

	@Override
	public MyProfile getMyProfileById(Long id) {
		return myProfileDao.getMyProfileById(id);
	}

	@Override
	public void synchToMyProfile(String ids, List<MyProfile> myProfiles,
			Long sendMessage, Long resourcePoolTypeId, String cahceId) {
		if (myProfiles == null) {
			throw new BusinessValidationException("同步文件的数据没有获得");
		}
		StringBuffer stb = new StringBuffer();
		for (MyProfile file : myProfiles) {
			file.setId(saveMyProfile(file).getId());
			stb.append(file.getId());
			stb.append(",");
			for (MyProfileAttachFile fiel : file.getMyProfileAttachFile()) {
				myProfileAttachFileService.addMyProfileAttachFile(fiel);
			}

		}

		if (null != cahceId && !"".equals(cahceId)) {
			addUserMyProfilePermission(stb.toString(), sendMessage,
					resourcePoolTypeId, cahceId);
		}
	}

	@Override
	public MyProfile savaMyProfile(MyProfile myProfile, String[] attachFiles) {
		myProfile = saveMyProfile(myProfile);

		addMyProfileAttachFile(myProfile, attachFiles);

		return getMyProfileById(myProfile.getId());
	}

	private MyProfile saveMyProfile(MyProfile myProfile) {
		if (myProfile.getId() == null) {
			validate(myProfile);
			myProfile.setUserId(ThreadVariable.getUser().getId());
			myProfile.setShareState(MyProfile.shareState_NO);
			myProfile.setId(addMyProfile(myProfile).getId());

		}
		return myProfile;
	}

	private void validate(MyProfile myProfile) {
		if (myProfile.getName() == null || "".equals(myProfile.getName())) {
			throw new BusinessValidationException("文件名称没有获得");
		}
		myProfile.setOrganization(ThreadVariable.getUser().getOrganization());
		myProfile.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				ThreadVariable.getUser().getOrganization().getId())
				.getOrgInternalCode());
		autoFillChinesePinyin(myProfile);
	}

	private void autoFillChinesePinyin(MyProfile myProfile) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(myProfile.getName());
		myProfile.setSimplePinyin(pinyin.get("simplePinyin"));
		myProfile.setFullPinyin(pinyin.get("fullPinyin"));
	}

	private void addMyProfileAttachFile(MyProfile myProfile,
			String[] attachFiles) {
		if (attachFiles == null || attachFiles.length == 0) {

		} else {
			for (String fileName : attachFiles) {
				addAttachFile(myProfile, fileName);
			}
		}
	}

	private void addAttachFile(MyProfile myProfile, String fileName) {
		MyProfileAttachFile myProfileAttachFile = new MyProfileAttachFile();
		myProfileAttachFile.setMyPrifile(myProfile);
		StoredFile storedFile = null;
		try {
			storedFile = FileUtil.copyTmpFileToStoredFile(fileName,
					GridProperties.RESOURCEPOOL_PATH);

			myProfileAttachFile.setFileSize(storedFile.getFileSize());
			myProfileAttachFile.setFileActualUrl(storedFile.getStoredFilePath()
					+ File.separator + storedFile.getStoredFileName());
			myProfileAttachFile
					.setFileName(storedFile.getStoredTruthFileName());
			logger.error("storedFile.getStoredFilePath()+ File.separator + storedFile.getStoredFileName()"
					+ storedFile.getStoredFilePath()
					+ File.separator
					+ storedFile.getStoredFileName());
			myProfileAttachFile = myProfileAttachFileService
					.addMyProfileAttachFile(myProfileAttachFile);
			myProfile.getMyProfileAttachFile().add(myProfileAttachFile);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public PageInfo<MyProfile> findMyProfileForList(Long resourcePoolTypeId,
			int searchType, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		PageInfo<MyProfile> pageInfo = myProfileDao.findMyProfileForList(
				resourcePoolTypeId, searchType, pageNum, pageSize, sidx, sord);
		appendAttachFile(pageInfo);
		return pageInfo;
	}

	private void appendAttachFile(PageInfo<MyProfile> pageInfo) {
		for (MyProfile myProfile : pageInfo.getResult()) {
			DirectorySetting ds = directorySettingService
					.findDirectorySettingById(myProfile.getResourcePoolType()
							.getId());
			if (null != ds) {
				myProfile.getResourcePoolType().setName(ds.getName());
				myProfile
						.setMyProfileAttachFile(myProfileAttachFileService
								.getMyProfileAttachFileByMyProfileId(myProfile
										.getId()));
			}
		}
	}

	@Override
	public List<MyProfile> getMyProfileByIds(String ids) {
		if (ids == null || "".equals(ids)) {
			throw new BusinessValidationException("文件id没有获得");
		}
		// FIXME
		// add by zhanL at 2013/08/13 8:43 am
		List<String> parameters = Arrays.asList(StringUtils.split(ids, ","));
		List<MyProfile> myProfiles = myProfileDao
				.selectMyProfilesByIds(parameters);
		/*
		 * List<MyProfile> myProfiles = new ArrayList<MyProfile>(); String[] st
		 * = ids.split(","); for (String id : st) {
		 * myProfiles.add(getMyProfileById(Long.valueOf(id))); }
		 */
		return myProfiles;
	}

	@Override
	public List<MyProfile> addUserMyProfilePermission(String ids,
			Long sendMessage, Long resourcePoolTypeId,
			String setPermissionCacheId) {
		List<MyProfile> files = new ArrayList<MyProfile>();
		DirectorySetting shareDirectory = new DirectorySetting();
		if (resourcePoolTypeId == null) {
			throw new BusinessValidationException("没有设置文件存放的目录");
		}
		if (setPermissionCacheId == null || "".equals(setPermissionCacheId)) {
			throw new BusinessValidationException("没有设置查看权限");
		}
		shareDirectory.setId(resourcePoolTypeId);

		ViewObjectVo viewObjectVo = (ViewObjectVo) temporaryPopulationService
				.getObjectByIdFromTemporary(setPermissionCacheId);
		List<Long> list = viewObjectService
				.getSelectedOrgIdsByViewObjectVo(viewObjectVo);
		String[] st = ids.split(",");
		for (String id : st) {
			MyProfile file = new MyProfile();
			file.setId(Long.valueOf(id));
			file.setSendMessage(sendMessage);
			file.setShareState(MyProfile.shareState_YES);
			file.setShareDirectory(shareDirectory);
			myProfileDao.updateMyprofileBySendMesageAndShareDirectoryId(file);
			file = myProfileDao.getMyProfileById(Long.valueOf(id));
			files.add(file);

		}
		List<UserHasMyProfilePermission> domains = new ArrayList<UserHasMyProfilePermission>();
		for (MyProfile file : files) {
			UserHasMyProfilePermission domain = new UserHasMyProfilePermission();
			domain.setMyProfile(file);
			domains.add(domain);
		}

		userHasMyProfilePermissionService.addUserMyProfilePermission(domains,
				list, list, sendMessage, files);
		return null;
	}

	@Override
	public List<MyProfile> addUserMyProfilePermissionForMobile(String ids,
			Long sendMessage, Long resourcePoolTypeId, Integer levelType,
			Long userOrgId) {
		List<MyProfile> files = new ArrayList<MyProfile>();
		DirectorySetting shareDirectory = new DirectorySetting();
		if (resourcePoolTypeId == null) {
			throw new BusinessValidationException("没有设置文件存放的目录");
		}
		if (levelType == null) {
			throw new BusinessValidationException("没有设置查看权限");
		}
		shareDirectory.setId(resourcePoolTypeId);

		List<Long> list = viewObjectService.getOrgIdsMobile(userOrgId,
				levelType);
		String[] st = ids.split(",");
		for (String id : st) {
			MyProfile file = new MyProfile();
			file.setId(Long.valueOf(id));
			file.setSendMessage(sendMessage);
			file.setShareState(MyProfile.shareState_YES);
			file.setShareDirectory(shareDirectory);
			myProfileDao.updateMyprofileBySendMesageAndShareDirectoryId(file);
			file = myProfileDao.getMyProfileById(Long.valueOf(id));
			files.add(file);

		}
		List<UserHasMyProfilePermission> domains = new ArrayList<UserHasMyProfilePermission>();
		for (MyProfile file : files) {
			UserHasMyProfilePermission domain = new UserHasMyProfilePermission();
			domain.setMyProfile(file);
			domains.add(domain);
		}

		userHasMyProfilePermissionService.addUserMyProfilePermission(domains,
				list, list, sendMessage, files);
		return null;
	}

	@Override
	public MyProfile updateMyprofile(MyProfile myProfile, String[] attachFiles) {
		if (myProfile.getId() == null) {
			throw new BusinessValidationException("文件id没有获得");
		}
		if (myProfile.getName() == null || "".equals(myProfile.getName())) {
			throw new BusinessValidationException("文件名称没有获得");
		}
		myProfile.setUpdateUser(ThreadVariable.getUser().getName());
		myProfile = myProfileDao.updateMyprofile(myProfile);
		if (!margeAttachFiles(myProfile, attachFiles)) {
			throw new BusinessValidationException("操作失败！");
		}
		return myProfile;

	}

	private boolean margeAttachFiles(MyProfile myProfile, String[] attachFiles) {
		if (attachFiles == null || attachFiles.length == 0)
			return true;
		List<MyProfileAttachFile> myProfileAttachFiles = myProfileAttachFileService
				.getMyProfileAttachFileByMyProfileId(myProfile.getId());
		myProfile.setMyProfileAttachFile(myProfileAttachFiles);
		List<String> dailyLogAttachFileName = new ArrayList<String>();
		for (MyProfileAttachFile resourcePoolAttachFile : myProfileAttachFiles) {
			dailyLogAttachFileName.add(resourcePoolAttachFile.getFileName());
		}
		for (String fileName : attachFiles) {
			if (!dailyLogAttachFileName.contains(fileName))
				addAttachFile(myProfile, fileName);
		}
		return true;
	}

	@Override
	public void deleteMyProfileById(String ids) {
		if ("".equals(ids) || ids == null) {
			throw new BusinessValidationException("文件id没有获得");
		}
		String[] st = ids.split(",");
		if (st.length > 0) {
			userHasMyProfilePermissionService
					.deleteMyProfilePermissionByMyProfileIds(st);
			myProfileAttachFileService
					.deleteMyProfileAttachFileByMyProfileIds(st);
			myProfileDao.deleteMyProfileByIds(st);
		}
		/*
		 * for (String id : st) { userHasMyProfilePermissionService
		 * .deleteMyProfilePermissionByMyProfileId(Long.valueOf(id));
		 * myProfileAttachFileService
		 * .deleteMyProfileAttachFileByMyProfileId(Long.valueOf(id));
		 * myProfileDao.deleteMyProfileById(Long.valueOf(id)); }
		 */
	}

	@Override
	public void cancelSharingMyProfile(String ids) {
		if ("".equals(ids) || ids == null) {
			throw new BusinessValidationException("文件id没有获得");
		}
		String[] st = ids.split(",");
		for (String id : st) {
			myProfileDao.updateMyProfileOfshareState(Long.valueOf(id));
			userHasMyProfilePermissionService
					.deleteMyProfilePermissionByMyProfileId(Long.valueOf(id));
		}
	}

	@Override
	public PageInfo<MyProfile> searchMyProfile(MyProfile myProfile,
			int searchType, Date startReleaseTime, Long resourcePoolTypeId,
			Date startCreateTime, Long attached, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		PageInfo<MyProfile> pageInfo = myProfileDao.searchMyProfile(myProfile,
				searchType, startReleaseTime, resourcePoolTypeId,
				startCreateTime, attached, pageNum, pageSize, sidx, sord);
		appendAttachFile(pageInfo);
		return pageInfo;
	}

	@Override
	public PageInfo<MyProfile> fastSearchMyProfile(Long resourcePoolTypeId,
			int searchType, String searchText, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		PageInfo<MyProfile> pageInfo = myProfileDao.fastSearchMyProfile(
				ThreadVariable.getUser().getId(), resourcePoolTypeId,
				searchType, searchText, pageNum, pageSize, sidx, sord);
		appendAttachFile(pageInfo);
		return pageInfo;
	}

}
