package com.tianque.resourcePool.dao;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.resourcePool.domain.MyProfile;

public interface SharingFilesDao {

	/***
	 * 通过ID查询共享资料信息
	 * 
	 * @param ids
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<MyProfile> findSharingFilesForListByIds(String[] ids);

	public PageInfo<MyProfile> findSharingFilesForList(Long orgId,
			String orgInternalCode, Long resourcePoolTypeId, int searchType,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public PageInfo<MyProfile> fastSearchSharingFiles(Long resourcePoolTypeId,
			int searchType, String searchText, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	public PageInfo<MyProfile> fastSearchSharingFilesforMobile(
			Long resourcePoolTypeId, int searchType, String searchText,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	/**
	 * 查询
	 * 
	 * @param myProfile
	 * @param searchType
	 * @param startReleaseTime
	 * @param resourcePoolTypeId
	 * @param startCreateTime
	 */
	public PageInfo<MyProfile> searchSharingFiles(MyProfile myProfile,
			int searchType, Date startReleaseTime, Long resourcePoolTypeId,
			Date startCreateTime, Date startShareDate, List<Long> orgIdsList,
			Integer pageNum, Integer pageSize, String sidx, String sord);

}
