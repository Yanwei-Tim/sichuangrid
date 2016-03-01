package com.tianque.working.service;

import java.util.List;
import java.util.Map;

import com.tianque.core.vo.ExtTreeData;
import com.tianque.core.vo.PageInfo;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyDirectoryAttachFile;

public interface DailyDirectoryService {

	public DailyDirectory addDailyDirectory(DailyDirectory dailyDirectory);

	public DailyDirectory updateDailyDirectory(DailyDirectory dailyDirectory);

	/**
	 * 成功删除返回 “true” 字符
	 * 
	 * @param dailyDirectoryId
	 * @return
	 */
	public String deleteDailyDirectoryById(Long dailyDirectoryId);

	public String validateDeleteDailyDirectoryById(Long dailyDirectoryId);

	public DailyDirectory getSimpleDailyDirectoryById(Long id);

	public DailyDirectory getFullDailyDirectoryById(Long id);

	public List<DailyDirectory> findDailyAllTrunkDirectory();

	public PageInfo<DailyDirectory> findDailySubDirectoryByParentIdForPage(Long id, Integer page,
			Integer rows, String sidx, String sord);

	public List<DailyDirectory> findDailySubDirectoryByParentId(Long id);

	public List<DailyDirectory> findDailyDirectoryByDailyYearId(Long id);

	public List<DailyDirectory> findDailyTrunkDirectoryByDailyYearId(Long id);

	public Integer countExsistedTrunkDirectory(String shortName, Long dailyYearId, Long besidesId);

	public Integer countExsistedSubDirectory(String shortName, Long parentId, Long besidesId);

	public void moveDailySubDirectoryToFirst(Long id, Long parentId, Integer indexId);

	public void moveDailySubDirectoryToEnd(Long id, Long parentId, Integer indexId);

	public void moveDailySubDirectoryToPrevious(Long id, Long parentId, Integer indexId);

	public void moveDailySubDirectoryToNext(Long id, Long parentId, Integer indexId);

	// 移动主目录
	public void moveDailyTrunkDirectoryToFirst(Long id, Long dailyYearId, Integer indexId);

	public void moveDailyTrunkDirectoryToEnd(Long id, Long dailyYearId, Integer indexId);

	public void moveDailyTrunkDirectoryToNext(Long id, Long dailyYearId, Integer indexId,
			Integer privOrNextIndexId);

	public void moveDailyTrunkDirectoryToPrevious(Long id, Long dailyYearId, Integer indexId,
			Integer privOrNextIndexId);

	public int isYesrsCount(Long yearId);

	/**
	 * 根据我的台账目录，查询我的父部门的相同类型的台账目录
	 * 
	 * @param orgId
	 * @param dailydirectoryType
	 * @param investigation
	 * @param directoryId
	 * @return
	 */
	public List<DailyDirectory> findSubDirectoryByMakedOrgIdAndDirectoryTypeInterIdAndDirectoryId(
			Long orgId, String dailydirectoryTypeDomainName, int dailydirectoryTypeInterId,
			Long directoryId);

	/**
	 * 根据台账目录附件fileId得到台账目录附件
	 * 
	 * @param fileId
	 * @return
	 */
	public DailyDirectoryAttachFile getDailyDirectoryAttachFilesByFileId(Long fileId);

	/**
	 * 根据台账目录ID得到台账目录附件
	 * 
	 * @param dailyDirectoryId
	 * @return
	 */
	public List<DailyDirectoryAttachFile> findDailyDirectoryAttachFilesByDailyDirectoryId(
			Long dailyDirectoryId);

	/**
	 * 根据树类型、父Id、年度台帐目录Id 得到台账目录模型树
	 * 
	 * @param treeType
	 * @param parentId
	 * @return
	 */
	List<ExtTreeData> findExtTreeDataByTreeTypeAndParentIdAndDailyYearId(String treeType,
			Long parentId, Long dailyYearId);

	/**
	 * 找出需要提醒的目录
	 * 
	 * @return
	 */
	List<DailyDirectory> findDailyDirectorysForRemind();

	/**
	 * 根据dailyYearId、typeId、reportedTypeId得到台账目录
	 * 
	 * @param dailyYearId
	 *        根据年度目录
	 * @param typeId
	 *        台账目录类型
	 * @param reportedTypeId
	 *        上报目录类型
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
	public DailyDirectory getDirectoryByOrgIdAndTypeIdAndReportTypeIdAndDirectoryId(Long orgId,
			Long typeId, Long reportTypeId, Long directoryId);

	/**
	 * @param orgId
	 * @return
	 */
	Map<String, Integer> statisticsReport(Long orgId);

	/**
	 * 根据年度台账ID删除台账目录 成功删除返回 “true” 字符
	 * 
	 * @param yearId
	 * @return
	 */
	public String deleteDailyDirectoryByYearId(Long yearId);

	/**
	 * 台账目录检索
	 * 
	 * @param searchText
	 * @param dailyYearId
	 * @return
	 */
	public List<DailyDirectory> searchDirectorys(String searchText, Long dailyYearId);

	public List<DailyDirectory> findRangeDailyDirectoryByDailyYearId(Long id,
			List<Long> dailydirectoryIds);
}
