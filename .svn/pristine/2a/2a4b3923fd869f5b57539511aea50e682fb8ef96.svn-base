package com.tianque.resourcePool.service;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.resourcePool.domain.MyProfile;

public interface MyProfileService {
	public MyProfile addMyProfile(MyProfile myProfile);

	public MyProfile getMyProfileById(Long id);

	public MyProfile savaMyProfile(MyProfile myProfile, String[] attachFiles);

	public PageInfo<MyProfile> findMyProfileForList(Long resourcePoolTypeId, int searchType,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public List<MyProfile> getMyProfileByIds(String ids);

	public List<MyProfile> addUserMyProfilePermission(String ids, Long sendMessage,
			Long resourcePoolTypeId, String setPermissionCacheId);

	/**
	 * @param ids
	 * @param sendMessage
	 * @param resourcePoolTypeId
	 * @param setPermissionCacheId
	 * @return 手机版
	 */
	public List<MyProfile> addUserMyProfilePermissionForMobile(String ids, Long sendMessage,
			Long resourcePoolTypeId, Integer levelType, Long userOrgId);

	public MyProfile updateMyprofile(MyProfile myProfile, String[] attachFiles);

	public void deleteMyProfileById(String ids);

	public void cancelSharingMyProfile(String ids);

	public PageInfo<MyProfile> searchMyProfile(MyProfile myProfile, int searchType,
			Date startReleaseTime, Long resourcePoolTypeId, Date startCreateTime, Long attached,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public PageInfo<MyProfile> fastSearchMyProfile(Long resourcePoolTypeId, int searchType,
			String searchText, Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 同步到我的资料库
	 * 
	 * @param file
	 * @param cahceId
	 * @return
	 */
	public void synchToMyProfile(String ids, List<MyProfile> myProfiles, Long sendMessage,
			Long resourcePoolTypeId, String cahceId);

}
