package com.tianque.resourcePool.service;

import java.util.Date;

import com.tianque.core.vo.PageInfo;
import com.tianque.resourcePool.domain.MyProfile;

public interface SharingFilesService {

	public PageInfo<MyProfile> findSharingFilesForList(Long orgId,
			Long resourcePoolTypeId, int searchType, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/***
	 * 通过IDS查询文件共享资料信息
	 * 
	 * @param ids
	 * @return
	 */
	public PageInfo<MyProfile> findSharingFilesForListByIds(String[] ids);

	/**
	 * 搜索
	 * 
	 * @param resourcePoolTypeId
	 * @param searchType
	 * @param searchText
	 * @return
	 */
	public PageInfo<MyProfile> fastSearchSharingFiles(Long resourcePoolTypeId,
			int searchType, String searchText, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 搜索手机
	 * 
	 * @param resourcePoolTypeId
	 * @param searchType
	 * @param searchText
	 * @return
	 */
	public PageInfo<MyProfile> fastSearchSharingFilesforMobile(
			Long resourcePoolTypeId, int searchType, String searchText,
			Integer pageNum, Integer pageSize, String sidx, String sord);

	public PageInfo<MyProfile> searchSharingFiles(MyProfile myProfile,
			int searchType, Date startReleaseTime, Long resourcePoolTypeId,
			Date startCreateTime, Date startShareDate, Integer pageNum,
			Integer pageSize, String sidx, String sord);

}
