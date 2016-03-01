package com.tianque.resourcePool.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.resourcePool.domain.MyProfile;

public interface MyProfileDao {
	public MyProfile addMyProfile(MyProfile myProfile);

	public MyProfile getMyProfileById(Long id);

	public PageInfo<MyProfile> findMyProfileForList(Long resourcePoolTypeId,
			int searchType, Integer pageNum, Integer pageSize, String sidx,
			String sord);

	public void updateMyprofileBySendMesageAndShareDirectoryId(
			MyProfile myProfile);

	public MyProfile updateMyprofile(MyProfile myProfile);

	public void deleteMyProfileById(Long id);

	public void deleteMyProfileByIds(String[] ids);

	public PageInfo<MyProfile> searchMyProfile(MyProfile myProfile,
			int searchType, Date startReleaseTime, Long resourcePoolTypeId,
			Date startCreateTime, Long attached, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public PageInfo<MyProfile> fastSearchMyProfile(Long userId,
			Long resourcePoolTypeId, int searchType, String searchText,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 修改资料的共享状态为1即为未共享状态
	 * 
	 * @param id
	 */
	public void updateMyProfileOfshareState(Long id);

	/**
	 * add by zhanL at 2013/08/13 8:47 am
	 * 
	 * @param split
	 * @return
	 */
	public List<MyProfile> selectMyProfilesByIds(List<String> array);

	public void batchUpateMyProfileBySendMessageAndShareDirectoryId(
			List<MyProfile> files);

}
