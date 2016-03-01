package com.tianque.working.service;

import java.util.List;

import com.tianque.controller.vo.DailyLogVo;
import com.tianque.core.vo.PageInfo;
import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.working.domain.WorkingRecord;
import com.tianque.working.vo.SearchWorkingRecordVo;

public interface WorkingRecordService {
	/**
	 * 查询台账
	 * 
	 * @param searchWorkingRecordVo
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @return
	 */
	public PageInfo<WorkingRecord> findPagerBySearchVo(SearchWorkingRecordVo searchWorkingRecordVo,
			Integer pageNum, Integer pageSize, String sortField, String order);

	/**
	 * 新增台账
	 * 
	 * @param workingRecord
	 * @param attachFiles
	 * @return
	 */
	public WorkingRecord addWorkingRecord(WorkingRecord workingRecord, String[] attachFiles);

	/**
	 * 修改台账
	 * 
	 * @param workingRecord
	 * @param attachFiles
	 * @return
	 */
	public WorkingRecord updateWorkingRecord(WorkingRecord workingRecord, String[] attachFiles);

	/**
	 * 删除台账
	 */
	public void deleteWorkingRecordById(Long id);

	/**
	 * 默认展示台账
	 * 
	 * @param orgId
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param dailyDirectoryId
	 * @param displayLevel
	 * @return
	 */
	public PageInfo findWorkingRecordForPageByOrgIdAndDailyDirectoryId(Long orgId, Integer pageNum,
			Integer pageSize, String sidx, String sord, Long dailyDirectoryId, boolean displayLevel);

	/**
	 * 查了三张表 workingRecord、KeyAreasOfInvestigationInfos、SignificantIssuedeals
	 */
	public Long countSubTablesValue(Long directoryId);

	public WorkingRecord getWorkingRecordById(Long id);

	public List<WorkingRecord> findWorkingRecordByDailyDirectoryId(Long directoryId,
			Long organizationId);

	public List<DailyLogVo> findDailyLogVoByDetailedRuleId(Long detailedRuleId);

	public int countWorkingRecordsByDailyDirectoryId(Long id);

	public void deleteWorkingRecordByIds(String ids);

	public List<WorkingRecord> getWorkingRecordsByIds(String ids);

	public List<MyProfile> synchToMyProfile(String ids, Long sendMessage, Long resourcePoolTypeId,
			String setPermissionCacheId);

	/***
	 * 已办结事项转为台帐
	 * 
	 * @param workingRecord
	 * @param attachFiles
	 * @param ids
	 * @return
	 */
	public WorkingRecord convertToWorkingRecord(WorkingRecord workingRecord, String[] attachFiles,
			Long[] issueAttachFileIds);

}
