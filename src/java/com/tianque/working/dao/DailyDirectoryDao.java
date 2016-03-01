package com.tianque.working.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyDirectoryAttachFile;

public interface DailyDirectoryDao {

	public DailyDirectory addDailyDirectory(DailyDirectory dailyDirectory);

	public DailyDirectory updateDailyDirectory(DailyDirectory dailyDirectory);

	public int deleteDailyDirectoryById(Long dailyDirectoryId);

	public DailyDirectory getSimpleDailyDirectoryById(Long id);

	public List<DailyDirectory> findDailyAllTrunkDirectory();

	public PageInfo<DailyDirectory> findDailySubDirectoryByParentIdForPage(
			Long id, Integer page, Integer rows, String sidx, String sord);

	public List<DailyDirectory> findDailySubDirectoryByParentId(Long id);

	public List<DailyDirectory> findDailyDirectoryByDailyYearId(Long id);

	public List<DailyDirectory> findDailyTrunkDirectoryByDailyYearId(Long id);

	public Integer countExsistedTrunkDirectory(String shortName,
			Long dailyYearId, Long besidesId);

	public Integer countExsistedSubDirectory(String shortName, Long parentId,
			Long besidesId);

	public List<DailyDirectory> findRangeDailyDirectoryByDailyYearId(Long id,
			List<Long> dailydirectoryIds);

	public Integer getIndexIdByParentId(Long parentId);

	public Integer getDailyTrunkDirectotyIndexIdByDailyYearId(Long dailyYearId);

	public void moveDailySubDirectoryToFirst(Long id, Long parentId,
			Integer indexId);

	public void moveDailySubDirectoryToEnd(Long id, Long parentId,
			Integer indexId);

	public void moveDailySubDirectoryToPrevious(Long id, Long parentId,
			Integer indexId);

	public void moveDailySubDirectoryToNext(Long id, Long parentId,
			Integer indexId);

	// 移动主目录
	public void moveDailyTrunkDirectoryToFirst(Long id, Long dailyYearId,
			Integer indexId);

	public void moveDailyTrunkDirectoryToEnd(Long id, Long dailyYearId,
			Integer indexId);

	public void moveDailyTrunkDirectoryToNext(Long id, Long dailyYearId,
			Integer indexId, Integer privOrNextIndexId);

	public void moveDailyTrunkDirectoryToPrevious(Long id, Long dailyYearId,
			Integer indexId, Integer privOrNextIndexId);

	public int isYesrsCount(Long yearId);

	public void deleteDailyDirectoryByYearId(Long yearId);

	public int isIssueinspectByDailyDirectoryId(Long id);

	public List<DailyDirectory> findSubDirectoryByMakedOrgIdAndDirectoryTypeInterIdAndDirectoryId(
			Long orgId, Long type, Long directoryId);

	/**
	 * 新增台账目录附件
	 * 
	 * @param dailyDirectoryAttachFile
	 */
	public DailyDirectoryAttachFile addDailyDirectoryAttachFile(
			DailyDirectoryAttachFile dailyDirectoryAttachFile);

	/**
	 * 根据台账目录录附件fileId删除台账目录附件
	 * 
	 * @param dailyDirectoryId
	 */
	public void deleteDailyDirectoryAttachFileByFileId(Long fileId);

	/**
	 * 根据台账目录ID删除台账目录附件
	 * 
	 * @param dailyDirectoryId
	 */
	public void deleteDailyDirectoryAttachFileByDailyDirectoryId(
			Long dailyDirectoryId);

	/**
	 * 根据台账目录附件fileId得到台账目录附件
	 * 
	 * @param fileId
	 * @return
	 */
	public DailyDirectoryAttachFile getDailyDirectoryAttachFilesByFileId(
			Long fileId);

	/**
	 * 根据台账目录ID得到台账目录附件
	 * 
	 * @param dailyDirectoryId
	 * @return
	 */
	public List<DailyDirectoryAttachFile> findDailyDirectoryAttachFilesByDailyDirectoryId(
			Long dailyDirectoryId);

	List<DailyDirectory> findDailyDirectorysForRemind();

	/**
	 * 根据年度台帐Id、目录类型Id、上报类型Id 得到台账目录
	 * 
	 * @param dailyYearId
	 *            年度台帐Id
	 * @param typeId
	 *            目录类型Id
	 * @param reportedTypeId
	 *            上报类型Id
	 * @return
	 */
	public DailyDirectory getDailyDirectoryByDailyYearIdAndTypeIdAndReportedTypeId(
			Long dailyYearId, Long typeId, Long reportedTypeId);

	/**
	 * 根据我的台账目录，查询我的父部门的相同类型的台账目录
	 * 
	 * @param orgId
	 * @param typeId
	 * @param directoryReportTypeId
	 * @param directoryId
	 * @return
	 */
	public DailyDirectory getDirectoryByOrgIdAndTypeIdAndReportTypeIdAndDirectoryId(
			Long orgId, Long typeId, Long reportTypeId, Long directoryId);

	List<DailyDirectory> findLeafDailyDirectorysByOrgIdAndYearDate(Long orgId,
			Integer year);

	/**
	 * 台账目录检索
	 * 
	 * @param searchText
	 * @param dailyYearId
	 * @return
	 */
	public List<DailyDirectory> searchDirectorys(String searchText,
			Long dailyYearId);
}
