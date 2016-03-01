package com.tianque.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.workingRecord.KeyAreasOfInvestigationInfoWorkingRecord;

public interface KeyAreasOfInvestigationInfoWorkingRecordDao {
	/**
	 * 添加日常工作中治安重点排查信息
	 * 
	 * @param KeyAreasOfInvestigationInfoWorkingRecord
	 *        治安重点排查信息对象
	 * @return KeyAreasOfInvestigationInfoWorkingRecord 治安重点排查信息对象
	 */
	public KeyAreasOfInvestigationInfoWorkingRecord addKeyAreasOfInvestigationInfoWorkingRecord(
			KeyAreasOfInvestigationInfoWorkingRecord keyAreasOfInvestigationInfoWorkingRecord);

	public KeyAreasOfInvestigationInfoWorkingRecord getKeyAreasOfInvestigationInfoWorkingRecordById(
			Long id);

	/**
	 * 修改治安重点排查信息
	 */
	public KeyAreasOfInvestigationInfoWorkingRecord updateKeyAreasOfInvestigationInfoWorkingRecord(
			KeyAreasOfInvestigationInfoWorkingRecord keyAreasOfInvestigationInfoWorkingRecord);

	/**
	 * 删除治安重点排查信息
	 */
	public void deleteKeyAreasOfInvestigationInfoWorkingRecord(Long id);

	/**
	 * 根据查询条件分页查询治安重点排查信息
	 */
	public PageInfo<KeyAreasOfInvestigationInfoWorkingRecord> findKeyAreasOfInvestigationInfoWorkingRecordForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx, String sord,
			String dailyDirectoryId);
}
